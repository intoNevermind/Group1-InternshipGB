-- DROP DATABASE persons_statistics;

CREATE DATABASE persons_statistics
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;
	
-- DROP TABLE public.persons;

CREATE TABLE public.persons
(
  "ID" integer NOT NULL,
  "Name" character varying(2048)[] NOT NULL UNIQUE,
  "Active" boolean NOT NULL DEFAULT true,
  CONSTRAINT persons_pkey PRIMARY KEY ("ID")
);
COMMENT ON TABLE public.persons
  IS 'Таблица базы данных, отвечающая за хранение имен личностей. Каждой личности соответствует от 0 до бесконечности ключевых слов.';
	
-- DROP TABLE public.keywords;

CREATE TABLE public.keywords
(
  "ID" integer NOT NULL,
  "Name" character varying(2048)[] NOT NULL,
  "PersonID" integer NOT NULL,
  CONSTRAINT keywords_pkey PRIMARY KEY ("ID"),
  CONSTRAINT fk_person_keywords FOREIGN KEY ("PersonID")
      REFERENCES public.persons ("ID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);
COMMENT ON TABLE public.keywords
  IS 'Tаблица базы данных, отвечающая за хранение ключевых слов, соответствующих каждой конкретной личности.';

-- DROP TABLE public.sites;

CREATE TABLE public.sites
(
  "ID" integer NOT NULL,
  "Name" character varying(2048)[] NOT NULL UNIQUE,
  "URL" character varying(2048)[] NOT NULL,
  "Active" boolean NOT NULL DEFAULT true,
  CONSTRAINT sites_pkey PRIMARY KEY ("ID")
);
COMMENT ON TABLE public.sites
  IS 'Tаблица базы данных, содержит названия сайтов для анализа на упоминания.';

-- DROP TABLE public.pages;
  
CREATE TABLE public.pages
(
    "ID" serial,
    "URL" character varying(2048)[] NOT NULL,
	"SiteID" integer NOT NULL,
	"FoundDateTime" time without time zone NOT NULL,
	"LastScanDate" time without time zone NULL,
    PRIMARY KEY ("ID"),
    CONSTRAINT fk_site_pages FOREIGN KEY ("SiteID")
        REFERENCES public.sites ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);
COMMENT ON TABLE public.pages
    IS 'Таблица базы данных, содержит страницы сайта, которые были найдены при анализе сайтов из таблицы Sites.';
COMMENT ON COLUMN public.pages."FoundDateTime"
    IS 'Дата и время обнаружения страницы системой.';
COMMENT ON COLUMN public.pages."LastScanDate"
    IS 'Дата и время последней проверки на упоминания.';

-- DROP TABLE public.personpagerank;
	
CREATE TABLE public.personpagerank
(
    "PersonID" integer NOT NULL,
	"PageID" integer NOT NULL,
	"Rank" INT NOT NULL,
    PRIMARY KEY ("PersonID", "PageID"),
    CONSTRAINT fk_person_page_rank FOREIGN KEY ("PersonID")
        REFERENCES public.persons ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT fk_page_person_rank FOREIGN KEY ("PageID")
        REFERENCES public.pages ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);
COMMENT ON TABLE public.personpagerank
    IS 'Таблица базы данных, отвечающая за хранение количества упоминаний каждой личности на обработанной странице.';

CREATE TABLE public.users (
  "login" text PRIMARY KEY NOT NULL,
  "role" BOOLEAN NOT NULL,
  "password" text NOT NULL
);

CREATE OR REPLACE FUNCTION change_password() RETURNS trigger AS $BODY$
BEGIN	
 IF (TG_OP = 'INSERT') THEN
 NEW.password = crypt(NEW.password, gen_salt('bf', 8));
 return NEW;
END IF;
 IF (TG_OP = 'UPDATE') AND (OLD.password != NEW.password) THEN
 NEW.password = crypt(NEW.password, gen_salt('bf', 8));
 return NEW;
end IF;
return NEW;
END;
$BODY$
language 'plpgsql';

DROP TRIGGER IF EXISTS users_password ON public.users;
CREATE TRIGGER users_password
   BEFORE INSERT OR UPDATE
   ON users FOR EACH ROW
   EXECUTE PROCEDURE change_password();

CREATE OR REPLACE FUNCTION leaner_logon(text, text) RETURNS bool AS $BODY$
DECLARE res bool;
BEGIN
   SELECT 1 INTO res FROM learners WHERE "login" = $1 AND pwdhash = crypt($2, password);
    IF FOUND THEN    
       return true;
   ELSE
       return false;
   END IF;
END; $BODY$
language 'plpgsql';
COMMENT ON FUNCTION leaner_logon(text, text) IS
   'Check true if has leaner with given login and password';
   
CREATE USER read_stats WITH password 'Qwerty123';
GRANT CONNECT ON DATABASE persons_statistics TO read_stats;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO read_stats;

CREATE USER write_stats WITH password 'Asdfgh123';
GRANT CONNECT ON DATABASE persons_statistics TO write_stats;
GRANT ALL ON DATABASE persons_statistics TO write_stats;
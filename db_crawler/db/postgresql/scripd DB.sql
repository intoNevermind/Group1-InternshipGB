CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;
COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';

CREATE FUNCTION change_password() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN	
    IF (TG_OP = 'INSERT') THEN
        NEW."Password" = crypt(NEW."Password", gen_salt('bf', 8));
        return NEW;
    END IF;
    IF (TG_OP = 'UPDATE') AND (OLD."Password" != NEW."Password") THEN
        NEW."Password" = crypt(NEW."Password", gen_salt('bf', 8));
        return NEW;
    END IF;
    return NEW;
END;
$$;

ALTER FUNCTION public.change_password() OWNER TO scanner;

CREATE FUNCTION leaner_logon(text, text) RETURNS boolean
    LANGUAGE plpgsql
    AS $_$
DECLARE res bool;
BEGIN
    SELECT 1 INTO res FROM users WHERE "login" = $1 AND pwdhash = crypt($2, Password);
    IF FOUND THEN    
        return true;
    ELSE
        return false;
    END IF;
END;
$_$;

ALTER FUNCTION public.leaner_logon(text, text) OWNER TO scanner;
COMMENT ON FUNCTION leaner_logon(text, text) IS 'Check true if has leaner with given login and password';

CREATE FUNCTION user_logon(text, text) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
DECLARE res integer;
BEGIN
    SELECT CASE WHEN "Admin" THEN 2 ELSE 1 END INTO res FROM users WHERE "Login" = $1 AND "Password" = crypt($2, "Password") AND "Active";
    IF FOUND THEN    
        return res;
    ELSE
        return 0;
    END IF;
END;
$_$;

ALTER FUNCTION public.user_logon(text, text) OWNER TO scanner;
COMMENT ON FUNCTION user_logon(text, text) IS 'Check true if has leaner with given login and password';


SET default_tablespace = '';
SET default_with_oids = false;

CREATE TABLE keywords (
    "ID" integer NOT NULL,
    "Name" character varying(2048) NOT NULL,
    "PersonID" integer NOT NULL
);

ALTER TABLE keywords OWNER TO scanner;
COMMENT ON TABLE keywords IS 'Tаблица базы данных, отвечающая за хранение ключевых слов, соответствующих каждой конкретной личности.';

CREATE SEQUENCE "keywords_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE "keywords_ID_seq" OWNER TO scanner;

ALTER SEQUENCE "keywords_ID_seq" OWNED BY keywords."ID";

CREATE TABLE pages (
    "ID" integer NOT NULL,
    "URL" character varying(2048) NOT NULL,
    "SiteID" integer NOT NULL,
    "FoundDateTime" timestamp without time zone NOT NULL,
    "LastScanDate" timestamp without time zone,
    "InProgress" boolean DEFAULT false NOT NULL
);


ALTER TABLE pages OWNER TO scanner;
COMMENT ON TABLE pages IS 'Таблица базы данных, содержит страницы сайта, которые были найдены при анализе сайтов из таблицы Sites.';
COMMENT ON COLUMN pages."FoundDateTime" IS 'Дата и время обнаружения страницы системой.';
COMMENT ON COLUMN pages."LastScanDate" IS 'Дата и время последней проверки на упоминания.';

CREATE SEQUENCE "pages_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE "pages_ID_seq" OWNER TO scanner;

ALTER SEQUENCE "pages_ID_seq" OWNED BY pages."ID";

CREATE TABLE personpagerank (
    "PersonID" integer NOT NULL,
    "PageID" integer NOT NULL,
    "Rank" integer NOT NULL,
    "RankDate" date DEFAULT now() NOT NULL
);

ALTER TABLE personpagerank OWNER TO scanner;
COMMENT ON TABLE personpagerank IS 'Таблица базы данных, отвечающая за хранение количества упоминаний каждой личности на обработанной странице.';

CREATE TABLE persons (
    "ID" integer NOT NULL,
    "Name" character varying(2048) NOT NULL,
    "Active" boolean DEFAULT true NOT NULL,
    "UserID" integer
);

ALTER TABLE persons OWNER TO scanner;
COMMENT ON TABLE persons IS 'Таблица базы данных, отвечающая за хранение имен личностей. Каждой личности соответствует от 0 до бесконечности ключевых слов.';

CREATE SEQUENCE "persons_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE "persons_ID_seq" OWNER TO scanner;

ALTER SEQUENCE "persons_ID_seq" OWNED BY persons."ID";

CREATE TABLE sites (
    "ID" integer NOT NULL,
    "Name" character varying(2048) NOT NULL,
    "URL" character varying(2048) NOT NULL,
    "Active" boolean DEFAULT true NOT NULL,
    "UserID" integer,
    "LastUpdated" timestamp without time zone DEFAULT now() NOT NULL,
    "InProgress" boolean DEFAULT false NOT NULL
);

ALTER TABLE sites OWNER TO scanner;
COMMENT ON TABLE sites IS 'Tаблица базы данных, содержит названия сайтов для анализа на упоминания.';

CREATE SEQUENCE "sites_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE "sites_ID_seq" OWNER TO scanner;

ALTER SEQUENCE "sites_ID_seq" OWNED BY sites."ID";

CREATE TABLE users (
    "ID" integer NOT NULL,
    "Login" text NOT NULL,
    "Admin" boolean NOT NULL,
    "Password" text NOT NULL,
    "Active" boolean NOT NULL
);


ALTER TABLE users OWNER TO scanner;

CREATE SEQUENCE "users_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "users_ID_seq" OWNER TO scanner;

ALTER SEQUENCE "users_ID_seq" OWNED BY users."ID";

ALTER TABLE ONLY keywords ALTER COLUMN "ID" SET DEFAULT nextval('"keywords_ID_seq"'::regclass);

ALTER TABLE ONLY pages ALTER COLUMN "ID" SET DEFAULT nextval('"pages_ID_seq"'::regclass);

ALTER TABLE ONLY persons ALTER COLUMN "ID" SET DEFAULT nextval('"persons_ID_seq"'::regclass);

ALTER TABLE ONLY sites ALTER COLUMN "ID" SET DEFAULT nextval('"sites_ID_seq"'::regclass);

ALTER TABLE ONLY users ALTER COLUMN "ID" SET DEFAULT nextval('"users_ID_seq"'::regclass);

ALTER TABLE ONLY persons
    ADD CONSTRAINT key_person_unique UNIQUE ("Name", "UserID");

ALTER TABLE ONLY sites
    ADD CONSTRAINT key_sites_unique UNIQUE ("Name", "UserID");

ALTER TABLE ONLY keywords
    ADD CONSTRAINT keywords_pkey PRIMARY KEY ("ID");

ALTER TABLE ONLY pages
    ADD CONSTRAINT pages_pkey PRIMARY KEY ("ID");

ALTER TABLE ONLY pages
    ADD CONSTRAINT pages_url_unique UNIQUE ("URL");

ALTER TABLE ONLY personpagerank
    ADD CONSTRAINT personpagerank_pkey PRIMARY KEY ("PersonID", "PageID", "RankDate");

ALTER TABLE ONLY persons
    ADD CONSTRAINT "persons_Name_key" UNIQUE ("Name");

ALTER TABLE ONLY persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY ("ID");

ALTER TABLE ONLY sites
    ADD CONSTRAINT "sites_Name_key" UNIQUE ("Name");

ALTER TABLE ONLY sites
    ADD CONSTRAINT sites_pkey PRIMARY KEY ("ID");

ALTER TABLE ONLY users
    ADD CONSTRAINT users_login_key UNIQUE ("Login");

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("ID");

CREATE TRIGGER users_password BEFORE INSERT OR UPDATE ON users FOR EACH ROW EXECUTE PROCEDURE change_password();

ALTER TABLE ONLY keywords
    ADD CONSTRAINT fk_person_keywords FOREIGN KEY ("PersonID") REFERENCES persons("ID") ON DELETE CASCADE;

ALTER TABLE ONLY personpagerank
    ADD CONSTRAINT fk_person_page_rank FOREIGN KEY ("PersonID") REFERENCES persons("ID") ON DELETE CASCADE;

ALTER TABLE ONLY pages
    ADD CONSTRAINT fk_site_pages FOREIGN KEY ("SiteID") REFERENCES sites("ID") ON DELETE CASCADE;

ALTER TABLE ONLY persons
    ADD CONSTRAINT fk_user_persons FOREIGN KEY ("UserID") REFERENCES users("ID") ON DELETE CASCADE;

ALTER TABLE ONLY sites
    ADD CONSTRAINT fk_user_sites FOREIGN KEY ("UserID") REFERENCES users("ID") ON DELETE CASCADE;

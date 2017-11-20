-- persons
INSERT INTO public.persons("ID", "Name", "Active") VALUES (1, 'Путин', true);
INSERT INTO public.persons("ID", "Name", "Active") VALUES (2, 'Навальный', true);
INSERT INTO public.persons("ID", "Name", "Active") VALUES (3, 'Собчак', true);

-- keywords
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (1, 1, 'Путин');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (2, 1, 'Путину');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (3, 1, 'Путиным');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (4, 1, 'Путина');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (5, 2, 'Навальный');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (6, 2, 'Навальным');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (7, 2, 'Навальному');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (8, 2, 'Навального');
INSERT INTO public.keywords("ID", "Person","Keyword") VALUES (9, 3, 'Собчак');

-- sites
INSERT INTO public.sites("ID", "Name", "URL") VALUES (1, 'Лента.ру', 'http://lenta.ru/');
INSERT INTO public.sites("ID", "Name", "URL") VALUES (2, 'РБК', 'http://www.rbc.ru/');

-- persons
INSERT INTO public.persons("ID", "Site", "URL", "FoundDateTime", "LastScanDate") VALUES (1, 1, 'https://lenta.ru/news/2017/10/31/okueva/', "2017-25-10 09:43:00","2017-25-10 09:43:00");
INSERT INTO public.persons("ID", "Site", "URL", "FoundDateTime", "LastScanDate") VALUES (2, 1, 'https://lenta.ru/news/2017/10/25/malakhov_navalny/', "2017-26-10 09:43:00","2017-26-10 09:43:00");
INSERT INTO public.persons("ID", "Site", "URL", "FoundDateTime", "LastScanDate") VALUES (3, 1, 'https://lenta.ru/news/2017/10/26/navalny/', "2017-27-10 09:43:00","2017-27-10 09:43:00");
INSERT INTO public.persons("ID", "Site", "URL", "FoundDateTime", "LastScanDate") VALUES (4, 2, 'http://www.rbc.ru/politics/30/10/2017/59f726639a794714c553b09e', "2017-28-10 09:43:00","2017-28-10 09:43:00");
INSERT INTO public.persons("ID", "Site", "URL", "FoundDateTime", "LastScanDate") VALUES (5, 2, 'http://www.rbc.ru/politics/27/10/2017/59f345049a79475ffd648fec', "2017-29-10 09:43:00","2017-29-10 09:43:00");
INSERT INTO public.persons("ID", "Site", "URL", "FoundDateTime", "LastScanDate") VALUES (6, 2, 'http://www.rbc.ru/politics/27/10/2017/59f326769a794742f5503497', "2017-30-10 09:43:00","2017-30-10 09:43:00");

-- personpagerank
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (1, 1, 9);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (2, 1, 0);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (3, 1, 0);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (1, 2, 7);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (2, 2, 5);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (3, 2, 7);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (1, 3, 6);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (2, 3, 8);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (3, 3, 8);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (1, 4, 7);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (2, 4, 3);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (3, 4, 0);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (1, 5, 4);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (2, 5, 1);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (3, 5, 20);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (1, 6, 18);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (2, 6, 0);
INSERT INTO public.personpagerank("PersonID", "PageID", "Rank") VALUES (3, 6, 19);

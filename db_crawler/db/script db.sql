CREATE SCHEMA `persons_statistics`;

CREATE TABLE `persons_statistics`.`persons` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(2048) NOT NULL,
  `Active` BOOLEAN DEFAULT TRUE NOT NULL,
  PRIMARY KEY (`ID`))
COMMENT = 'Таблица базы данных, отвечающая за хранение имен личностей. Каждой личности соответствует от 0 до бесконечности ключевых слов.';

CREATE TABLE `persons_statistics`.`keywords` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(2048) NOT NULL,
  `PersonID` INT NOT NULL COMMENT 'Идентификатор личности, которой соответствует данное ключевое слово. Является внешним ключом к таблице Persons.',
  PRIMARY KEY (`ID`),
  INDEX `FK_Persons_idx` (`PersonID` ASC),
  CONSTRAINT `fk_person_keyword`
    FOREIGN KEY (`PersonID`)
    REFERENCES `persons_statistics`.`persons` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
COMMENT = 'Tаблица базы данных, отвечающая за хранение ключевых слов, соответствующих каждой конкретной личности.';

CREATE TABLE `persons_statistics`.`sites` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(2048) NOT NULL,
  `URL` NVARCHAR(2048) NOT NULL,
  `Active` BOOLEAN DEFAULT TRUE NOT NULL,
  PRIMARY KEY (`ID`))
COMMENT = 'Tаблица базы данных, содержит названия сайтов для анализа на упоминания.';

CREATE TABLE `persons_statistics`.`pages` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `URL` NVARCHAR(2048) NOT NULL,
  `SiteID` INT NOT NULL COMMENT 'Идентификатор сайта (ресурса), который предоставлен  администратором для анализа. Является внешним ключом к таблице Sites.',
  `FoundDateTime` DATETIME NOT NULL COMMENT 'Дата и время обнаружения страницы системой.',
  `LastScanDate` DATETIME NULL COMMENT 'Дата и время последней проверки на упоминания.',
  PRIMARY KEY (`ID`),
  INDEX `fk_site_pages_idx` (`SiteID` ASC),
  CONSTRAINT `fk_site_pages`
    FOREIGN KEY (`SiteID`)
    REFERENCES `persons_statistics`.`sites` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
COMMENT = 'Таблица базы данных, содержит страницы сайта, которые были найдены при анализе сайтов из таблицы Sites.';

CREATE TABLE `persons_statistics`.`personpagerank` (
  `PersonID` INT NOT NULL COMMENT 'Идентификатор личности, которой соответствует данное ключевое слово. Является внешним ключом к таблице Persons.',
  `PageID` INT NOT NULL COMMENT 'Идентификатор страницы сайта, на которой найдены упоминания о персонах. Является внешним ключом к таблице Pages.',
  `Rank` INT NOT NULL COMMENT 'Количество упоминаний личности на странице.',
  PRIMARY KEY (`PersonID`, `PageID`),
  INDEX `fk_page_person_rank_idx` (`PageID` ASC),
  CONSTRAINT `fk_person_page_rank`
    FOREIGN KEY (`PersonID`)
    REFERENCES `persons_statistics`.`persons` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_page_person_rank`
    FOREIGN KEY (`PageID`)
    REFERENCES `persons_statistics`.`pages` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
	
CREATE USER IF NOT EXISTS 'read_stats'@'localhost' IDENTIFIED BY 'Qwerty123';
GRANT SELECT ON persons_statistics.* TO 'read_stats'@'localhost';

CREATE USER IF NOT EXISTS 'write_stats'@'localhost' IDENTIFIED BY 'Asdfgh123';
GRANT SELECT, INSERT, UPDATE, DELETE ON persons_statistics.* TO 'write_stats'@'localhost';

FLUSH PRIVILEGES;


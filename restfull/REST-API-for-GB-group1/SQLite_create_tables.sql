CREATE TABLE `persons` (
  `ID`     INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT,
  `Name`   NVARCHAR(2048) NOT NULL,
  `Active` BOOLEAN                             DEFAULT TRUE NOT NULL
);

CREATE TABLE `keywords` (
  `ID`       INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT,
  `Name`     NVARCHAR(2048) NOT NULL,
  `PersonID` INT            NOT NULL
);

CREATE TABLE `sites` (
  `ID`     INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT,
  `Name`   NVARCHAR(2048) NOT NULL,
  `URL`    NVARCHAR(2048) NOT NULL,
  `Active` BOOLEAN                             DEFAULT TRUE NOT NULL
);

CREATE TABLE `pages` (
  `ID`            INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT,
  `URL`           NVARCHAR(2048) NOT NULL,
  `SiteID`        INT            NOT NULL,
  `FoundDateTime` DATETIME       NOT NULL,
  `LastScanDate`  DATETIME       NULL
);

CREATE TABLE `personpagerank` (
  `PersonID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `PageID`   INT     NOT NULL,
  `Rank`     INT     NOT NULL
);

CREATE TABLE test (
  someline VARCHAR(2048)
);
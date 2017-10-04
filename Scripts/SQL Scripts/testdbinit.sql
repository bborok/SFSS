-- auto-generated definition
CREATE TABLE User
(
  SFU_ID          VARCHAR(30)  NOT NULL
    PRIMARY KEY,
  Name            VARCHAR(100) NOT NULL,
  Email           VARCHAR(100) NOT NULL,
  PhoneNumber     BIGINT(11)   NOT NULL,
  PreferredCampus VARCHAR(15)  NULL,
  StdNum          BIGINT(11)   NULL,
  Role            VARCHAR(15)  NOT NULL,
  CONSTRAINT SFU_ID_UNIQUE
  UNIQUE (SFU_ID),
  CONSTRAINT Email
  UNIQUE (Email),
  CONSTRAINT StdNum_UNIQUE
  UNIQUE (StdNum)
);
-- auto-generated definition
CREATE TABLE TaskList
(
  TaskID      INT AUTO_INCREMENT
    PRIMARY KEY,
  TaskName    VARCHAR(200) NOT NULL,
  Burnaby     TINYINT      NOT NULL,
  Surrey      TINYINT      NOT NULL,
  Vancouver   TINYINT      NOT NULL,
  Deactivated TINYINT      NOT NULL,
  CONSTRAINT idTask_UNIQUE
  UNIQUE (TaskID),
  CONSTRAINT TaskName_UNIQUE
  UNIQUE (TaskName)
);

-- auto-generated definition
CREATE TABLE TimeCardList
(
  TimeCard_ID BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  Date        DATETIME    NOT NULL,
  UserID      VARCHAR(30) NOT NULL,
  CONSTRAINT idTimeCard_UNIQUE
  UNIQUE (TimeCard_ID),
  CONSTRAINT SFU_ID
  FOREIGN KEY (UserID) REFERENCES User (SFU_ID)
);
CREATE INDEX SFU_ID_idx
  ON TimeCardList (UserID);


CREATE TABLE IF NOT EXISTS `CLIENT_COMPANY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID client company',
  `NAME` varchar(128) NOT NULL COMMENT 'Name client company',
  PRIMARY KEY  (`ID`),
  constraint client_company_uk01 UNIQUE(NAME)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table client company';

CREATE TABLE IF NOT EXISTS `DEPARTMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID department',
  `NAME` varchar(128) NOT NULL COMMENT 'Name department',
  `ID_CLIENT_COMPANY` int(11) NOT NULL COMMENT 'ID client company',
  PRIMARY KEY  (`ID`),
  constraint department_uk01 UNIQUE(NAME),
  constraint department_client_company_fk01 FOREIGN KEY(ID_CLIENT_COMPANY) REFERENCES CLIENT_COMPANY(ID)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table department';

CREATE TABLE IF NOT EXISTS `PROJECT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID project',
  `NAME` varchar(128) NOT NULL COMMENT 'Name project',
  `ID_DEPARTMENT` int(11) NOT NULL COMMENT 'ID department',
  PRIMARY KEY  (`ID`),
  constraint project_uk01 UNIQUE(NAME),
  constraint project_department_fk01 FOREIGN KEY(ID_DEPARTMENT) REFERENCES DEPARTMENT(ID)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table project';

CREATE TABLE IF NOT EXISTS `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID user',
  `LOGIN` varchar(128) NOT NULL COMMENT 'Login user',
  `LAST_NAME` varchar(128) NOT NULL COMMENT 'Last name user',
  `FIRST_NAME` varchar(128) NOT NULL COMMENT 'First name user',
  `ROLES` varchar(128) COMMENT 'Roles user',
  `PASSWORD` varchar(128) COMMENT 'Password user',
  PRIMARY KEY  (`ID`),
  constraint subject_uk01 UNIQUE(LOGIN)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table user';

CREATE TABLE IF NOT EXISTS `USER_PROJECT` (
  `ID_USER` int(11)  NOT NULL COMMENT 'Id user',
  `ID_PROJECT` int(11)  NOT NULL COMMENT 'Id project',
  PRIMARY KEY  (`ID_USER`, `ID_PROJECT`),
  constraint user_project_fk1 FOREIGN KEY(ID_USER) REFERENCES USER(ID),
  constraint user_project_fk02 FOREIGN KEY(ID_PROJECT) REFERENCES PROJECT(ID)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table user and his project';

CREATE TABLE IF NOT EXISTS `USER_DEPARTMENT` (
  `ID_USER` int(11)  NOT NULL COMMENT 'Id user',
  `ID_DEPARTMENT` int(11)  NOT NULL COMMENT 'Id department',
  PRIMARY KEY  (`ID_USER`, `ID_DEPARTMENT`),
  constraint user_department_fk1 FOREIGN KEY(ID_USER) REFERENCES USER(ID),
  constraint user_department_fk02 FOREIGN KEY(ID_DEPARTMENT) REFERENCES DEPARTMENT(ID)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table user and his department';

CREATE TABLE IF NOT EXISTS `USER_CLIENT_COMPANY` (
  `ID_USER` int(11)  NOT NULL COMMENT 'Id user',
  `ID_CLIENT_COMPANY` int(11)  NOT NULL COMMENT 'Id client company',
  PRIMARY KEY  (`ID_USER`, `ID_CLIENT_COMPANY`),
  constraint user_client_company_fk1 FOREIGN KEY(ID_USER) REFERENCES USER(ID),
  constraint user_client_company_fk02 FOREIGN KEY(ID_CLIENT_COMPANY) REFERENCES DEPARTMENT(ID)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table user and his client company';
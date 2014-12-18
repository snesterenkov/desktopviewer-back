CREATE TABLE IF NOT EXISTS `SNAPSHOT` (
  `ID` int(11)  NOT NULL AUTO_INCREMENT COMMENT 'Id Snapshot',
  `NOTE` varchar(255) NOT NULL COMMENT 'Snapshot note',
  `MESSAGE` varchar(255) NOT NULL COMMENT 'Snapshot massage',
  `DATE` TIMESTAMP NOT NULL COMMENT 'Snapshot date',
  `ID_PROJECT` int(11) COMMENT 'Snapshot project',
  `FILENAME` varchar(255) NOT NULL COMMENT 'Snapshot project',
  `ID_USER` int(11) NOT NULL COMMENT 'Snapshot project',
  PRIMARY KEY  (`ID`),
  constraint snapshot_user_fk1 FOREIGN KEY(ID_USER) REFERENCES USER(ID),
  constraint snapshot_project_fk02 FOREIGN KEY(ID_PROJECT) REFERENCES PROJECT(ID)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='Table test';
ALTER TABLE `PROJECT` ADD `ID_OWNER` int(11) NOT NULL COMMENT 'Id owner';
ALTER TABLE `PROJECT`
ADD CONSTRAINT `project_owner_fk1`
  FOREIGN KEY (ID_OWNER)
  REFERENCES USER(ID);
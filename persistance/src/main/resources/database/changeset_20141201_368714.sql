ALTER TABLE `DEPARTMENT` ADD `ID_OWNER` int(11) NOT NULL COMMENT 'Id owner';
ALTER TABLE `DEPARTMENT`
ADD CONSTRAINT `department_owner_fk1`
  FOREIGN KEY (ID_OWNER)
  REFERENCES USER(ID);
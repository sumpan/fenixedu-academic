
DROP TABLE IF EXISTS ADMINISTRATIVE_OFFICE_PERMISSION_GROUP;
DROP TABLE IF EXISTS ADMINISTRATIVE_OFFICE_PERMISSION;
ALTER TABLE RESOURCE DROP COLUMN KEY_ADMINISTRATIVE_OFFICE_PERMISSION_GROUP;

create table `ADMINISTRATIVE_OFFICE_PERMISSION_GROUP` (
  `ID_INTERNAL` int(11) NOT NULL auto_increment,
  `KEY_ADMINISTRATIVE_OFFICE` int(11) NOT NULL,
  `KEY_CAMPUS` int(11) NOT NULL,
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL,
  primary key (ID_INTERNAL),
  unique (KEY_ADMINISTRATIVE_OFFICE, KEY_CAMPUS),
  index (KEY_ADMINISTRATIVE_OFFICE),
  index (KEY_CAMPUS),
  index (KEY_ROOT_DOMAIN_OBJECT)
) type=InnoDB, character set latin1 ;

create table `ADMINISTRATIVE_OFFICE_PERMISSION` (
  `ID_INTERNAL` int(11) NOT NULL auto_increment,
  `KEY_ADMINISTRATIVE_OFFICE_PERMISSION_GROUP` int(11) NOT NULL,
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL,
  `OJB_CONCRETE_CLASS` text NOT NULL,
  `PERMISSION_MEMBERS_GROUP` text,
  `PERMISSION_TYPE` text,
  primary key (ID_INTERNAL),
  index (KEY_ADMINISTRATIVE_OFFICE_PERMISSION_GROUP),
  index (KEY_ROOT_DOMAIN_OBJECT)
) type=InnoDB, character set latin1 ;


SET NAMES utf8 ;

DROP TABLE IF EXISTS account;
CREATE TABLE account (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(100) DEFAULT NULL,
  sip_server varchar(100) DEFAULT NULL,
  sip_port varchar(5) DEFAULT '5060',
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUEa (id),
  UNIQUE KEY username_UNIQUE (username));

DROP TABLE IF EXISTS vendor;
CREATE TABLE vendor (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  lines_count int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUEpa (id),
  UNIQUE KEY name_UNIQUE (name)
);



DROP TABLE IF EXISTS phone;
CREATE TABLE phone (
  id int(11) NOT NULL AUTO_INCREMENT,
  mac varchar(12) NOT NULL,
  inventory_num varchar(20) NOT NULL,
  mol varchar(100) DEFAULT NULL,
  room varchar(30) DEFAULT NULL,
  ntp_server varchar(50) DEFAULT NULL,
  dhcp tinyint(1) DEFAULT '0',
  ip_address varchar(50) DEFAULT NULL,
  subnet_mask varchar(20) DEFAULT NULL,
  default_gw varchar(50) DEFAULT NULL,
  dns1 varchar(50) DEFAULT NULL,
  dns2 varchar(50) DEFAULT NULL,
  prov_url varchar(100) DEFAULT NULL,
  admin_login varchar(50) DEFAULT NULL,
  admin_password varchar(50) DEFAULT NULL,
  id_vendor int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUEp (id),
  UNIQUE KEY mac_UNIQUE (mac),
  UNIQUE KEY inventory_UNIQUE (inventory_num),
  KEY idVendor_idx (id_vendor),
  CONSTRAINT idVendor FOREIGN KEY (id_vendor) REFERENCES vendor (id));



DROP TABLE IF EXISTS phone_account;
CREATE TABLE phone_account (
  id int(11) NOT NULL AUTO_INCREMENT,
  phone_id int(11) NOT NULL,
  account_id int(11) DEFAULT '4',
  line_number int(11) DEFAULT '1',
  PRIMARY KEY (id),
  KEY phoneId_idx (phone_id),
  KEY accountId_idx (account_id),
  CONSTRAINT accountId FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT phoneId FOREIGN KEY (phone_id) REFERENCES phone (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO account VALUES (1,'-----',NULL,NULL,'5060'),(8,'0339','fcjdsaktjer','pbx.irgups.ru','5060'),(9,'7775','jfkasljertewio','pbx.irgups.ru','5060'),(10,'0513','fjeowirje','pbx.irgups.ru','5060'),(11,'0189','nfdeksjahr','pbx.irgups.ru','5060'),(12,'NotUsed',NULL,NULL,NULL),(13,'6666','njk3j32sd','pbx.irgups.ru','5060');
INSERT INTO vendor VALUES (19,'Yealink/SIPT20P',2),(22,'Yealink/SIPT22P',3),(25,'Escene/ES290PN',2),(41,'DLink/DPH150S',2);
INSERT INTO phone VALUES (22,'001565832F1C','004325934254','Хоменко А.П.','','172.20.79.254',1,'','','','','','tftp://pbx.irgups.ru','admin','fgsdt4353f',19),(24,'000561FDDDEE','436548943','Догданова Т.В.','','172.20.79.254',1,'','','','','','tftp://pbx.irgups.ru','admin','6v5vy6tbg54',41),(25,'AABBCCDDEEFF','4355476765','Арбатский Ю.Н.','','172.20.79.254',0,'172.20.64.112','255.255.255.0','172.20.64.1','172.20.64.1','172.20.4.1','tftp://pbx.irgups.ru','admin','g45g66g3g6',25),(26,'112233445566','4365467','Солодков С.А.','','172.20.79.254',1,'','','','','','tftp://pbx.irgups.ru','admin','64g543g634g',22),(27,'123412341234','57432897543','Шишкин Ю.Н.','А-517','172.20.79.254',0,'172.20.64.142','255.255.255.0','172.20.64.1','172.20.64.1','172.20.4.1','tftp://pbx.irgups.ru','admin','jh342uiqhrfas',22),(28,'00268B5C11FE','742353847256832','Шишкин Ю.Н.','А-502','172.20.79.254',1,'','','','','','tftp://pbx.irgups.ru','root','feyhwuir324bfd',25);
INSERT INTO phone_account VALUES (41,24,9,1),(43,24,11,2),(83,25,11,1),(87,25,9,2),(95,22,10,1),(97,22,8,2),(101,26,9,1),(102,26,8,2),(103,26,11,3),(104,27,8,1),(107,27,12,2),(108,27,12,3),(109,28,9,1),(111,28,11,2);

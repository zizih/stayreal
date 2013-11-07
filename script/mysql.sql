

CREATE TABLE `vip`(
  `ID` int(11) unsigned NOT NULL auto_increment,
  `NAME` ENUM('rain', 'dad', 'jack', 'aaron') NOT NULL COMMENT '重要的人',
  `DESC` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用';

INSERT INTO vip(`NAME`,`DESC`) values('dad','内心很强大'),('jack','暑假回韶关'),('rain','不用心成长'),('aaron','吉他很厉害');
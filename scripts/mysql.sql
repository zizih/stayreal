/*
 auther: hezi;
 desc:写iserver测试用
*/

DROP TABLE IF EXISTS vip;
CREATE TABLE `vip`(
  `ID` int(11) unsigned NOT NULL auto_increment,
  `NAME` ENUM('rain', 'dad', 'jack', 'aaron') NOT NULL COMMENT '重要的人',
  `DESC` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用';

INSERT INTO vip(`NAME`,`DESC`) values('dad','内心很强大'),('jack','每年暑假到韶关和江璇玩,长大后就再也不去了因为没暑假了，^^'),('rain','没人教导，又不用心成长'),('aaron','吉他很厉害，唱歌很厉害，但是不教我');


DROP TABLE IF EXISTS comment;
CREATE TABLE `comment`(
  `ID` int(11) unsigned NOT NULL auto_increment,
  `TIME` datetime NOT NULL COMMENT '重要的人',
  `CONTENT` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用';

INSERT INTO comment(`TIME`,`CONTENT`) values('1384000055678', '单线程socket server处理http请求处理'),('1384001055678', '增加后台页面模板渲染');

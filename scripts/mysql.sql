/*
 auther: hezi;
 desc:写iserver测试用
*/

DROP TABLE IF EXISTS vip;
CREATE TABLE `vip`(
  `ID` int(2) unsigned NOT NULL auto_increment,
  `NAME` ENUM('rain', 'dad', 'jack', 'aaron') NOT NULL COMMENT '重要的人',
  `DESC` varchar(255) NOT NULL COMMENT '描述',
  `HUMOROUS` boolean default false COMMENT '描述',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用';

INSERT INTO vip(`NAME`,`DESC`) values('dad','内心很强大'),
  ('jack','每年暑假到韶关和江璇玩,长大后就再也不去了因为没暑假了，^^'),
  ('rain','没人教导，又不用心成长'),
  ('aaron','吉他很厉害，唱歌很厉害，但是不教我');


DROP TABLE IF EXISTS comment;
CREATE TABLE `comment`(
  `ID` int(2) unsigned NOT NULL auto_increment,
  `TIME` datetime NOT NULL COMMENT '重要的人',
  `CONTENT` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试用';

INSERT INTO comment(`TIME`,`CONTENT`) values('2013-11-7 22:15:23', '单线程socket server处理http请求处理'),('2013-11-9 15:16:02', '增加后台页面模板渲染');



DROP TABLE IF EXISTS album;
CREATE TABLE `album`(
  `TAGID` int(2) unsigned NOT NULL auto_increment,
  `TAGNAME` varchar(50) NOT NULL COMMENT '相册名字',
  `SELECT` boolean default false COMMENT 'carousel数据结构需要',
  PRIMARY KEY (`TAGID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试 album切换到服务器数据';

INSERT INTO album(`TAGNAME`,`SELECT`) values('美好',true),('现实',false);



DROP TABLE IF EXISTS carousel;
CREATE TABLE `carousel`(
  `ID` int(2) unsigned NOT NULL auto_increment,
  `ALBUMID` int(2) DEFAULT 0,
  `ISFIRST` boolean default false COMMENT 'carousel数据结构需要',
  `IMGURL` varchar(255) NOT NULL COMMENT '图片资料',
  `IMGALT` varchar(255),
  `HREF` varchar(255) COMMENT '图片链接',
  `CAPTION` varchar(255) COMMENT '图片字幕',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='测试 album切换到服务器数据';

INSERT INTO carousel(`ALBUMID`,`ISFIRST`,`IMGURL`,`CAPTION`) values(1,true,'./public/images/reality.png','经济学和管理学都会'),
(1,false,'./public/images/reality.JPG','父亲还在我身边'),
(2,true,'./public/images/dream00.jpg','地球环境都这样'),
(2,false,'./public/images/dream01.jpg','再回到这里');

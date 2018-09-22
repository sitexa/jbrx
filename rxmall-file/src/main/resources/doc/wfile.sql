-- 文件中心表
create DATABASE wfile;

use wfile;

CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fileDomain` varchar(128) NOT NULL DEFAULT '' COMMENT '文件访问域名',  
  `filePath` varchar(128) NOT NULL DEFAULT '' COMMENT '文件相对路径',
  `fileName` varchar(256) NOT NULL DEFAULT '' COMMENT '文件名',
  `sourceFileName` varchar(256) NOT NULL DEFAULT '' COMMENT '原文件名',
  `fileCode` int(10) NOT NULL DEFAULT '0' COMMENT '文件编码，默认是按500表分表设置的编码',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '文件状态：0可用  1失效',
  `fileSize` bigint(20) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `filePathFileName` (`filePath`,`fileName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='文件表';

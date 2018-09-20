create database message;
use message;

CREATE TABLE `channel` (
  `channel` varchar(50) NOT NULL COMMENT '通道账户（唯一） 如邮件：impl@wxh.mall.com  ,极光短信：jpushsms1 ，极光推送账户为：jpush1',
  `channelName` varchar(50) NOT NULL DEFAULT '' COMMENT '第3方发送短信平台名称或邮件名称',
  `rate` int(10) NOT NULL DEFAULT '0' COMMENT '速率',
  `maxSendCount` int(4) NOT NULL DEFAULT '0' COMMENT '单位时间最大发送次数,0代表无限制',
  `unitTimes` int(11) NOT NULL DEFAULT '0' COMMENT '单位时间,分钟',  
  PRIMARY KEY (`channel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通道表';

CREATE TABLE `templates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `templateType` varchar(20) NOT NULL COMMENT '模板类型',   
  `templateName` varchar(50) NOT NULL COMMENT '模板名称', 
  `content` text NOT NULL COMMENT '模板内容', 
  `paramIndexs` varchar(256) NOT NULL COMMENT '参数索引：计算需要替换的参数位置，用于快速索引替换参数,格式： {["start":2,"end":5],["start":10,"end":16],["start":18,"end":25]}', 
  `createTime` datetime DEFAULT now() COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板表';

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `messageType` int(2) NOT NULL DEFAULT '0' COMMENT '消息类型：1 sms（短信） 2 email（邮件） 3 push（推送消息）',
  `channel` varchar(50) NOT NULL DEFAULT '' COMMENT ' 通道账户：发送者',
  `receiver` varchar(50) NOT NULL DEFAULT '' COMMENT '接收者email或手机号码',  
  `title` varchar(150) NOT NULL DEFAULT '' COMMENT '標題',
  `templateId` bigint(20) NOT NULL COMMENT '模板id',  
  `templateParam` varchar(512) NOT NULL COMMENT '模板参数',
  `weight` int(2) NOT NULL DEFAULT '0' COMMENT '权重',  
  `failCount` int(5) NOT NULL DEFAULT '0' COMMENT '发送失败次数',  
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0：未发送 1：发送中 2：发送成功 3：发送失败  4：发送成功',
  `recoveryStatus` int(11) NOT NULL DEFAULT '0' COMMENT '0：待回收',  
  `scheduleTime` datetime DEFAULT NULL COMMENT '预约发送时间',  
  `doneTime` datetime DEFAULT NULL COMMENT '完成时间',    
  `createTime` datetime DEFAULT now() COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

CREATE TABLE `messageHistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `messageType` int(2) NOT NULL DEFAULT '0' COMMENT '消息类型：1 sms 2 email 3 push',
  `channel` varchar(50) NOT NULL DEFAULT '' COMMENT ' 通道账户：发送者',
  `receiver` varchar(50) NOT NULL DEFAULT '' COMMENT '接收者email或手机号码',  
  `title` varchar(150) NOT NULL DEFAULT '' COMMENT '標題',
  `templateId` bigint(20) NOT NULL COMMENT '模板id',  
  `templateParam` varchar(512) NOT NULL COMMENT '模板参数',
  `weight` int(2) NOT NULL DEFAULT '0' COMMENT '权重',  
  `failCount` int(5) NOT NULL DEFAULT '0' COMMENT '发送失败次数',  
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '已完成',
  `scheduleTime` datetime DEFAULT NULL COMMENT '预约发送时间',  
  `doneTime` datetime DEFAULT NULL COMMENT '完成时间',    
  `createTime` datetime DEFAULT now() COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息历史表';




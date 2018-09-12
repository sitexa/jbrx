-- 系统用户--
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `plateformId` bigint(20) NOT NULL COMMENT '商家ID  ID为0则为管理员，1以上为平台',
  `userName` varchar(36) NOT NULL COMMENT '登录名',
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '头像图片',
  `password` varchar(36) NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(36) NOT NULL DEFAULT '' COMMENT '盐',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1可用  2已禁用  3已删除',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';
-- 用户 --
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `pic` varchar(2000) NOT NULL DEFAULT '' COMMENT '用户头像',
  `nickName` varchar(200) NOT NULL DEFAULT '' COMMENT '昵称',
  `realName` varchar(200) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` int(2) NOT NULL DEFAULT 0 COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `mobilePhone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(36) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(36) NOT NULL DEFAULT '' COMMENT '盐',
  `failCount` int(4) NOT NULL DEFAULT 1 COMMENT '失败次数',
  `lockTime` datetime NOT NULL COMMENT '锁定时间',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1可用  2已同步',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 平台 --
DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '平台ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '平台名称',
  `day` bigint(20) NOT NULL default 0 COMMENT '运转天数',
  `descx` varchar(200) NOT NULL DEFAULT '' COMMENT '平台描述',
  `investMoney` varchar(36) NOT NULL DEFAULT '' COMMENT '投资总额',
  `shareMoney` varchar(36) NOT NULL DEFAULT '' COMMENT '分享财富',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT '加入平台url',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1启用2禁用3删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表';

-- 推荐平台 --
DROP TABLE IF EXISTS `recommendPlatform`;
CREATE TABLE `recommendPlatform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '推荐平台ID',
  `platformId` bigint(20) NOT NULL default 0 COMMENT '推荐平台ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '平台名称',
  `day` bigint(20) NOT NULL default 0 COMMENT '运转天数',
  `descx` varchar(200) NOT NULL DEFAULT '' COMMENT '平台描述',
  `investMoney` varchar(36) NOT NULL DEFAULT '' COMMENT '投资总额',
  `shareMoney` varchar(36) NOT NULL DEFAULT '' COMMENT '分享财富',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT '加入平台url',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1启用2禁用3删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐平台表';

-- 用户关注平台 --
DROP TABLE IF EXISTS `userPlatform`;
CREATE TABLE `userPlatform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关注平台ID',
  `platformId` bigint(20) NOT NULL default 0 COMMENT '平台ID',
  `userId` bigint(20) NOT NULL default 0 COMMENT '用户ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '平台名称',
  `day` bigint(20) NOT NULL default 0 COMMENT '运转天数',
  `descx` varchar(200) NOT NULL DEFAULT '' COMMENT '平台描述',
  `investMoney` varchar(36) NOT NULL DEFAULT '' COMMENT '投资总额',
  `shareMoney` varchar(36) NOT NULL DEFAULT '' COMMENT '分享财富',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT '加入平台url',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1关注2取消关注',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注平台表';

-- 策略 --
DROP TABLE IF EXISTS `tactics`;
CREATE TABLE `tactics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '策略id',
  `tacticsSn` varchar(200) NOT NULL DEFAULT '' COMMENT '策略编号，用于对接CRM系统的编号',
  `platformId` bigint(20) NOT NULL default 0 COMMENT '平台ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '策略名称',
  `minRate` varchar(200) NOT NULL DEFAULT '' COMMENT '最小年化率',
  `maxRate` varchar(36) NOT NULL DEFAULT '' COMMENT '最大年化率',
  `topRisk` varchar(36) NOT NULL DEFAULT '' COMMENT '最大风险',
  `day` bigint(20) NOT NULL default 0 COMMENT '操作周期（天）',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1启用2禁用3删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='策略表';

-- 用户关注策略 --
DROP TABLE IF EXISTS `userTactics`;
CREATE TABLE `userTactics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户策略ID',
  `userId` bigint(20) NOT NULL default 0 COMMENT '用户ID',
  `tacticsId` bigint(20) NOT NULL default 0 COMMENT '策略ID',
  `tacticsSn` varchar(200) NOT NULL DEFAULT '' COMMENT '策略编号，用于对接CRM系统的编号',
  `platformId` bigint(20) NOT NULL default 0 COMMENT '平台ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '策略名称',
  `minRate` varchar(200) NOT NULL DEFAULT '' COMMENT '最小年化率',
  `maxRate` varchar(36) NOT NULL DEFAULT '' COMMENT '最大年化率',
  `topRisk` varchar(36) NOT NULL DEFAULT '' COMMENT '最大风险',
  `day` bigint(20) NOT NULL default 0 COMMENT '操作周期（天）',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态：1关注2取消关注',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注策略表';

CREATE TABLE `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` bigint(20) DEFAULT 0 COMMENT '上传用户ID',
  `title` varchar(255) DEFAULT '0' COMMENT '视频标题',
  `path` varchar(255) DEFAULT '0' COMMENT '视频地址',
  `count` bigint(20) DEFAULT 0 COMMENT '点击次数',
  `times` varchar(255) DEFAULT '0' COMMENT '视频时长',
  `state` int(2) DEFAULT 0 COMMENT '视频状态：1--审核通过，0--待审核，2--审核不通过，3--删除',
  `createTime` datetime DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '创建时间',
  `updateTime` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='视频表';

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `videoId` bigint(20) DEFAULT 0 COMMENT '视频主键',
  `userId` bigint(20) DEFAULT 0 COMMENT '用户ID',
  `userName` varchar(255) DEFAULT '0' COMMENT '用户昵称',
  `content` varchar(255) DEFAULT '0' COMMENT '评论内容',
  `state` int(11) DEFAULT 0 COMMENT '状态：1--使用，0--删除或停用',
  `iscomment` int(11) DEFAULT 0 COMMENT '是否评论：1--已评论，0--未做评论',
  `createTime` datetime DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '创建时间',
  `updateTime` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='评论表';

CREATE TABLE `thumb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` varchar(255) DEFAULT '0' COMMENT '用户手机唯一编号',
  `type` int(11) DEFAULT 0 COMMENT '1--视频点赞，2--评论点赞',
  `aId` bigint(20) DEFAULT 0 COMMENT '对应的评论或视频ID',
  `createTime` datetime DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '创建时间',
  `updateTime` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='点赞表';

-- 上传平台的用户 --
DROP TABLE IF EXISTS `uploadUserPlatform`;
CREATE TABLE `uploadUserPlatform` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '关注平台ID',
  `platformId` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '平台ID',
  `userId` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `name` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '平台名称',
  `day` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '运转天数',
  `descx` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '平台描述',
  `investMoney` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '投资总额',
  `shareMoney` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '分享财富',
  `url` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '加入平台url',
  `status` INT(2) NOT NULL DEFAULT 1 COMMENT '状态：1审核  2未审核',
  `createTime` DATETIME NOT NULL COMMENT '创建时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户上传平台表';

-- 上传策略的用户 --
DROP TABLE IF EXISTS `uploadUserTactics`;
CREATE TABLE `uploadUserTactics` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户策略ID',
  `userId` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `tacticsId` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '策略ID',
  `tacticsSn` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '策略编号，用于对接CRM系统的编号',
  `platformId` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '平台ID',
  `name` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '策略名称',
  `minRate` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '最小年化率',
  `maxRate` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '最大年化率',
  `topRisk` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '最大风险',
  `day` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '操作周期（天）',
  `status` INT(2) NOT NULL DEFAULT 1 COMMENT '状态：1关注2取消关注',
  `createTime` DATETIME NOT NULL COMMENT '创建时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户上传策略表';


--banner表--
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '广告ID',
  `name` varchar(400) NOT NULL DEFAULT '' COMMENT '广告名称',
  `category` int (1) NOT NULL DEFAULT 1 COMMENT '栏目 1 启动页 2 首页banner ',
  `murl` varchar(400) NOT NULL DEFAULT '' COMMENT '媒介地址：mediaurl=/media?id=xxx&type=image/video',
  `aurl` varchar(400) NOT NULL DEFAULT '' COMMENT '动作地址：actionurl=/xxx?param1=x&param2=y&param3=z...，app内有效地址，如商品页面，活动页面等。不负责目标地址的访问权限。',
  `startDate` datetime NOT NULL COMMENT '开始时间',
  `endDate` datetime NOT NULL COMMENT '结束时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '是否有效：1可用，2删除',
  `count` bigint(20) DEFAULT '0' COMMENT '点击次数',
  `createTime` DATETIME NOT NULL COMMENT '创建时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告banner表';


--用户表 添加字段 20180910-----
ALTER TABLE `user`
ADD `idcard` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '证件号码';
ALTER TABLE `user`
ADD `idcardImg` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '证件照片正面';
ALTER TABLE `user`
ADD `idcardImgBack` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '证件照片背面';
ALTER TABLE `user`
ADD `bank` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '开户银行';
ALTER TABLE `user`
ADD `bankch` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '支行信息';
ALTER TABLE `user`
ADD `bankNum` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '银行卡号';
ALTER TABLE `user`
ADD `bankImg` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '银行卡照片正面';
ALTER TABLE `user`
ADD `bankImgBack` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '银行卡照片背面';
ALTER TABLE `user`
ADD `bankcardOwner` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '持卡人姓名';
ALTER TABLE `user`
ADD `mobile` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '绑定手机号';
ALTER TABLE `user`
ADD `country` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '国籍';
ALTER TABLE `user`
ADD `cardType` INT(1) NOT NULL DEFAULT 1 COMMENT '证件类型:0无 1身份证 2护照 3其他';  --
ALTER TABLE `user`
ADD `addr` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '详细地址';
ALTER TABLE `user`
ADD `email` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '邮箱';
ALTER TABLE `user`
ADD `birthday` DATETIME NOT NULL COMMENT '生日';
ALTER TABLE `user`
ADD `pro` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '省';
ALTER TABLE `user`
ADD `city` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '市';
ALTER TABLE `user`
ADD `area` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '区';
ALTER TABLE `user`
ADD `idcardIsA` INT(1) NOT NULL DEFAULT '2' COMMENT '身份证是否认证:1认证  2未认证';
ALTER TABLE `user`
ADD `bankIsA` INT(1) NOT NULL DEFAULT '2' COMMENT '银行卡是否认证:1认证  2未认证';






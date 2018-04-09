CREATE TABLE `thirdAppInfo` (
  `appId` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '第三方应用ID',
  `appClientId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '在第三方的应用ID',
  `appName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '第三方应用名',
  `appApiUrl` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对方放置getCookie的URL',
  `appOpenIdUrl` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '换取openid的地址',
  `appTokenUrl` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '换取对方信息url',
  `appeMd5Pass` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `appDomain` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '域名',
  `userNameAddit` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名头',
  `cookieExpiresMinute` int(11) DEFAULT NULL COMMENT 'Cookie过期时间（分钟）',
  `status` int(11) DEFAULT NULL COMMENT '状态，1正常，0 停用',
  PRIMARY KEY (`appId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='第三方应用信息表';
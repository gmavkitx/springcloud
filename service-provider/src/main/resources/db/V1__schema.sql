-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `balance` decimal(19,2) DEFAULT NULL COMMENT '余额',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '账户状态',
  #公共字段，每个表都要添加以下字段，由框架自动生成，维护
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '锁版本',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_by` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息';
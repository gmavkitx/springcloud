-- 生产环境请修改此文件
-- please modify this file when you are in production environment

DELETE FROM `user`;
-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'user1', 1, 16.00, '2018-08-05 16:52:57', 'WOMAN');
INSERT INTO `user` VALUES (2, 'user2', 2, 15.00, '2018-08-05 16:41:25', 'MAN');
INSERT INTO `user` VALUES (3, 'user3', 3, 14.00, '2018-08-05 16:41:25', 'MAN');
INSERT INTO `user` VALUES (4, 'user4', 4, 13.00, '2018-08-05 16:41:25', 'WOMAN');
INSERT INTO `user` VALUES (5, 'user5', 5, 12.00, '2018-08-05 16:41:25', 'MAN');
INSERT INTO `user` VALUES (6, 'user6', 6, 11.00, '2018-08-05 16:41:25', 'WOMAN');
COMMIT;
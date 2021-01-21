/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL5.7_LOCALHOST
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : sysam

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 21/01/2021 11:23:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据值',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `orderNum` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级编号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabel` int(2) NULL DEFAULT NULL COMMENT '是否启用',
  `isdel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否删除',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `updateBy` int(255) NULL DEFAULT NULL COMMENT '更新者',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新日期',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户组名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menuname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父菜单id',
  `authstr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int(11) NULL DEFAULT NULL COMMENT '排序',
  `enable` int(11) NULL DEFAULT 1 COMMENT '启用状态  0-未启用 1已启用',
  `isdel` int(11) NULL DEFAULT 0 COMMENT '是否删除  0-未删除 1已删除',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `updateBy` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '用户信息管理', 'system/user/', NULL, 0, NULL, 1, NULL, 1, 1, 0, NULL, NULL, '2020-01-09 08:45:25', '2020-06-16 15:16:07', NULL);
INSERT INTO `sys_menu` VALUES (2, '基础信息管理', '/admin/user/userList', NULL, 1, 'userList', 2, NULL, 1, 1, 0, NULL, NULL, '2020-01-10 12:18:00', '2020-11-06 16:51:16', NULL);
INSERT INTO `sys_menu` VALUES (3, '权限信息管理', '/admin/role/roleList', NULL, 1, 'roleList', 2, NULL, 2, 1, 0, NULL, NULL, '2020-01-10 12:18:14', '2020-11-10 15:21:15', NULL);
INSERT INTO `sys_menu` VALUES (4, '浏览', '/admin/user/getUserList', NULL, 2, 'userList', 4, 'glyphicon glyphicon-search', 1, 1, 0, NULL, NULL, '2020-06-16 15:05:19', '2020-11-06 16:53:52', NULL);
INSERT INTO `sys_menu` VALUES (5, '添加', '/admin/user/userAdd', NULL, 2, 'userAdd', 3, 'glyphicon glyphicon-search', 2, 1, 0, NULL, NULL, '2020-06-16 15:05:19', '2020-11-05 19:47:13', NULL);
INSERT INTO `sys_menu` VALUES (6, '查看', '/admin/user/userInfo', NULL, 2, 'userInfo', 3, 'glyphicon glyphicon-search', 3, 1, 0, NULL, NULL, '2020-11-06 16:36:05', '2020-11-06 16:37:27', NULL);
INSERT INTO `sys_menu` VALUES (7, '修改', '/admin/user/userEdit', NULL, 2, 'userUpdate', 3, 'glyphicon glyphicon-search', 4, 1, 0, NULL, NULL, '2020-11-10 15:19:12', '2020-11-10 20:45:37', NULL);
INSERT INTO `sys_menu` VALUES (8, '删除', '/admin/user/userDel', NULL, 2, 'userDel', 3, 'glyphicon glyphicon-search', 5, 1, 0, NULL, NULL, '2020-11-10 15:19:38', '2020-11-10 15:19:52', NULL);
INSERT INTO `sys_menu` VALUES (9, '浏览', '/admin/role/getRoleList', NULL, 3, 'roleList', 4, 'glyphicon glyphicon-search', 6, 1, 0, NULL, NULL, '2020-11-10 15:20:26', '2020-11-25 22:06:38', NULL);
INSERT INTO `sys_menu` VALUES (10, '添加', '/admin/role/roleAdd', NULL, 3, 'roleAdd', 3, 'glyphicon glyphicon-search', 2, 1, 0, NULL, NULL, '2020-06-16 15:05:19', '2020-11-25 20:36:05', NULL);
INSERT INTO `sys_menu` VALUES (11, '查看', '/admin/role/roleInfo', NULL, 3, 'roleInfo', 3, 'glyphicon glyphicon-search', 3, 1, 0, NULL, NULL, '2020-11-06 16:36:05', '2020-11-25 20:36:05', NULL);
INSERT INTO `sys_menu` VALUES (12, '修改', '/admin/role/roleEdit', NULL, 3, 'roleUpdate', 3, 'glyphicon glyphicon-search', 4, 1, 0, NULL, NULL, '2020-11-10 15:19:12', '2020-11-25 20:56:20', NULL);
INSERT INTO `sys_menu` VALUES (13, '删除', '/admin/role/roleDel', NULL, 3, 'roleDel', 3, 'glyphicon glyphicon-search', 5, 1, 0, NULL, NULL, '2020-11-10 15:19:38', '2020-11-25 20:36:05', NULL);

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES (1, '管理', '0001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES (2, '检索', '0002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES (3, '添加', '0003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES (4, '修改', '0004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES (5, '删除', '0005', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统许可表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_menu`;
CREATE TABLE `sys_permission_menu`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `permissionId` int(11) NULL DEFAULT NULL,
  `menuId` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_operation`;
CREATE TABLE `sys_permission_operation`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `permissionId` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `operation` int(11) NULL DEFAULT NULL COMMENT '操作id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色主键id',
  `rolesign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `rolename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` int(11) NULL DEFAULT NULL COMMENT '启用状态  0-未启用 1已启用',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isdel` int(11) NULL DEFAULT 0 COMMENT '是否删除  0-未删除 1已删除',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '管理员', '管理员', NULL, NULL, '2020-01-09 08:41:38', '2020-11-15 20:11:58', NULL, NULL);
INSERT INTO `sys_role` VALUES (2, 'ROLE_USER', '用户', '用户', NULL, NULL, '2020-01-09 08:41:38', '2020-11-15 20:12:01', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_group`;
CREATE TABLE `sys_role_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NULL DEFAULT NULL,
  `groupId` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单关联主键id',
  `roleId` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menuId` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isdel` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `describes` int(255) NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, '2020-01-09 08:48:10', '2020-10-31 17:15:40', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, '2020-01-10 12:18:36', '2020-11-01 13:16:37', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, '2020-01-10 12:18:39', '2020-10-31 17:15:43', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (5, 1, 4, '2020-11-05 18:08:02', '2020-11-05 18:08:02', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (6, 1, 5, '2020-11-05 19:47:26', '2020-11-05 19:47:26', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (7, 1, 6, '2020-11-06 16:37:34', '2020-11-06 16:37:34', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (8, 1, 7, '2020-11-10 15:32:14', '2020-11-10 15:32:14', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (9, 1, 8, '2020-11-10 15:32:22', '2020-11-10 15:32:22', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (10, 1, 9, '2020-11-10 15:32:24', '2020-11-10 15:32:24', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (11, 1, 10, '2020-11-25 20:36:35', '2020-11-25 20:36:35', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (12, 1, 11, '2020-11-25 20:36:35', '2020-11-25 20:36:35', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (13, 1, 12, '2020-11-25 20:36:35', '2020-11-25 20:36:35', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (14, 1, 13, '2020-11-25 20:36:35', '2020-11-25 20:36:35', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (25, 2, 2, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (26, 2, 5, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (27, 2, 6, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (28, 2, 7, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (29, 2, 8, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (30, 2, 4, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);
INSERT INTO `sys_role_menu` VALUES (31, 2, 1, '2020-12-05 17:15:33', '2020-12-05 17:15:33', 0, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  `permissionId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` int(11) NOT NULL DEFAULT 1 COMMENT '性别  0-女 1-男',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `enable` int(11) NULL DEFAULT 1 COMMENT '启用状态  0-未启用 1已启用',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isdel` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$l.2kTmKD7/wU6HdKba/LDeCob1Hc7RpREkSCBXb/CkkBMod1LyeyS', '管理员', 1, 20, '15645678956', '123@qq.com', 1, '2020-01-09 08:38:40', '2020-11-24 21:25:32', 0, NULL);
INSERT INTO `sys_user` VALUES (2, 'admin3', '$2a$10$l.2kTmKD7/wU6HdKba/LDeCob1Hc7RpREkSCBXb/CkkBMod1LyeyS', '管理员3', 1, NULL, NULL, NULL, 1, '2020-02-08 11:23:02', '2020-02-08 11:23:14', 0, NULL);
INSERT INTO `sys_user` VALUES (3, 'user1', '$2a$10$vt1OEdTGsLEhSQCxQgw4D.KHyRPxqDRgbskqnidMUwkDdAaYr7XE6', 'user1', 1, 20, '12345678911', '', 1, '2020-12-04 21:43:10', '2020-12-04 21:43:10', 0, NULL);
INSERT INTO `sys_user` VALUES (4, 'user2', '$2a$10$OSCspadQH1m350qYxxNjCeZHVG0aI5HWmJr0gG6Xij9wCjCmy0Ekm', 'user2', 1, 20, '12345678911', '123@qq.com', 1, '2020-12-06 14:57:11', '2020-12-06 14:57:11', 0, NULL);
INSERT INTO `sys_user` VALUES (5, 'user3', '123456', 'user3', 1, 20, '12345678911', '123@qq.com', 1, '2020-12-06 15:30:00', '2021-01-20 20:51:05', 0, NULL);

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `groupId` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联主键id',
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `roleId` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isdel` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 2, 1, '2020-02-08 11:23:55', '2020-02-08 11:23:55', 0, NULL);
INSERT INTO `sys_user_role` VALUES (8, 1, 1, '2020-11-24 21:25:32', '2020-11-24 21:25:32', 0, NULL);
INSERT INTO `sys_user_role` VALUES (9, 1, 2, '2020-11-24 21:25:32', '2020-11-24 21:25:32', 0, NULL);
INSERT INTO `sys_user_role` VALUES (10, 3, 2, '2020-12-04 21:43:10', '2020-12-04 21:43:10', 0, NULL);
INSERT INTO `sys_user_role` VALUES (12, 5, 2, '2020-12-06 15:30:00', '2020-12-06 15:30:00', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : yjxxt
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : sms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 01/06/2022 20:58:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_count` int NULL DEFAULT NULL,
  `is_valid` int NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES (1, '1701', 23, 1, '2022-06-01 20:33:04', '2022-06-01 20:33:07');
INSERT INTO `t_class` VALUES (2, '1702', 32, 1, '2022-06-01 20:33:21', '2022-06-01 20:33:24');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名',
  `class_id` int NULL DEFAULT NULL COMMENT '授课班级id',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '开课时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结课时间',
  `is_vaild` int NULL DEFAULT NULL COMMENT '是否有效',
  `creat _date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `uodate_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `class_id`(`class_id`) USING BTREE,
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `t_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES (1, 'C语言', 1, '2022-05-01 20:33:45', '2022-07-31 20:33:53', 1, '2022-06-01 20:34:01', '2022-06-01 20:34:04');
INSERT INTO `t_course` VALUES (6, 'Java', 2, '2022-05-01 20:34:18', '2022-07-03 20:34:22', 1, '2022-06-01 20:34:28', '2022-06-01 20:34:32');

-- ----------------------------
-- Table structure for t_course_time
-- ----------------------------
DROP TABLE IF EXISTS `t_course_time`;
CREATE TABLE `t_course_time`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程表id',
  `course_id` int NULL DEFAULT NULL COMMENT '课程id',
  `time` datetime NULL DEFAULT NULL COMMENT '课程时间',
  `is_valid` int NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `class_id` int NULL DEFAULT NULL COMMENT '班级id',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  INDEX `class_id`(`class_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `t_course_time_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_course_time_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `t_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_course_time_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course_time
-- ----------------------------
INSERT INTO `t_course_time` VALUES (4, 1, '2022-06-01 20:44:16', 1, '2022-06-01 20:44:20', '2022-06-01 20:44:23', 1, 1);

-- ----------------------------
-- Table structure for t_homework
-- ----------------------------
DROP TABLE IF EXISTS `t_homework`;
CREATE TABLE `t_homework`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '作业id',
  `homework_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业名称',
  `course_id` int NULL DEFAULT NULL COMMENT '作业课程',
  `source_id` int NULL DEFAULT NULL COMMENT '源对象老师或学生',
  `target_teacher_id` int NULL DEFAULT NULL COMMENT '目标教师id',
  `target_class_id` int NULL DEFAULT NULL COMMENT '目标班级id',
  `homework_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业文件',
  `is_valid` int NULL DEFAULT NULL COMMENT '作业文件',
  `create_date` date NULL DEFAULT NULL COMMENT '是否有效',
  `update_date` date NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course`(`course_id`) USING BTREE,
  INDEX `source_id`(`source_id`) USING BTREE,
  INDEX `target_teacher_id`(`target_teacher_id`) USING BTREE,
  INDEX `target_class_id`(`target_class_id`) USING BTREE,
  CONSTRAINT `course` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `source_id` FOREIGN KEY (`source_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `target_teacher_id` FOREIGN KEY (`target_teacher_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_homework_ibfk_1` FOREIGN KEY (`target_class_id`) REFERENCES `t_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_homework
-- ----------------------------
INSERT INTO `t_homework` VALUES (1, 'C语言课后作业', 1, 1, NULL, 1, '位置', 1, '2022-06-01', '2022-06-01');

-- ----------------------------
-- Table structure for t_homework_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_homework_grade`;
CREATE TABLE `t_homework_grade`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '作业-成绩表id',
  `homework_id` int NULL DEFAULT NULL COMMENT '作业id',
  `student_id` int NULL DEFAULT NULL COMMENT '学生id',
  `teacher_id` int NULL DEFAULT NULL COMMENT '老师id',
  `grade` int NULL DEFAULT NULL COMMENT '成绩<0-100>',
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `作业id`(`homework_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `homeworkid` FOREIGN KEY (`homework_id`) REFERENCES `t_homework` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_homework_grade_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_homework_grade_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_homework_grade
-- ----------------------------
INSERT INTO `t_homework_grade` VALUES (1, 1, 2, 1, 100, '2022-06-01 20:53:23', '2022-06-01 20:53:25');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '留言的id',
  `target_id` int NULL DEFAULT NULL COMMENT '目标对象的id',
  `source_id` int NULL DEFAULT NULL COMMENT '源对象的id',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '留言内容',
  `is_read` int NULL DEFAULT NULL COMMENT '查看是否标记',
  `read_count` int NULL DEFAULT NULL COMMENT '已读人数',
  `is_valid` int NULL DEFAULT NULL COMMENT 'is_valid',
  `create_date` datetime NULL DEFAULT NULL COMMENT 'create_date',
  `update_date` datetime NULL DEFAULT NULL COMMENT 'update_date',
  `class_id` int NULL DEFAULT NULL COMMENT '目标班级id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `target_id`(`target_id`) USING BTREE,
  INDEX `source_id`(`source_id`) USING BTREE,
  INDEX `class_id`(`class_id`) USING BTREE,
  CONSTRAINT `t_message_ibfk_1` FOREIGN KEY (`target_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_message_ibfk_2` FOREIGN KEY (`source_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_message_ibfk_3` FOREIGN KEY (`class_id`) REFERENCES `t_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (1, NULL, 1, '你好', NULL, 20, 1, '2022-06-01 20:55:53', '2022-06-01 20:55:50', 1);

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资源模块id',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模块名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问地址',
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '级别',
  `parent_id` int NULL DEFAULT NULL COMMENT '父类id',
  `opt_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选择值',
  `is_valid` int NULL DEFAULT NULL COMMENT '是否有效',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES (1, '用户信息管理', NULL, '0', -1, '10', 1, '2022-06-01 20:57:24', '2022-06-01 20:57:27');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `is_valid` int NULL DEFAULT NULL COMMENT '角色数据是否有效',
  `create_date` datetime NULL DEFAULT NULL COMMENT '角色创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '角色更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 1, '2022-06-01 20:57:42', '2022-06-01 20:57:44');

-- ----------------------------
-- Table structure for t_role_module
-- ----------------------------
DROP TABLE IF EXISTS `t_role_module`;
CREATE TABLE `t_role_module`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色-模块表id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `module_id` int NULL DEFAULT NULL COMMENT '模块id',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `module_id`(`module_id`) USING BTREE,
  CONSTRAINT `t_role_module_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_role_module_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `t_module` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_module
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户名id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_class_id` int NULL DEFAULT NULL COMMENT '班级id',
  `user_sex` int NULL DEFAULT NULL COMMENT '用户性别',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户电话',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_hobby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户兴趣',
  `is_valid` int NULL DEFAULT NULL COMMENT '用户数据是否有效',
  `create_date` datetime NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_class_id`(`user_class_id`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`user_class_id`) REFERENCES `t_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', 1, 1, '15535363579', 'admin@qq.com', 'hobbys', 1, '2022-06-01 20:46:36', '2022-06-01 20:46:38');
INSERT INTO `t_user` VALUES (2, '小明', '4QrcOUm6Wau+VuBX8g+IPg==', 1, 1, '15535363579', 'xiaoming@qq.com', '篮球', 1, '2022-06-01 20:52:55', '2022-06-01 20:52:58');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户-角色表id',
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_date` datetime NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1, '2022-06-01 20:58:13', '2022-06-01 20:58:15');

SET FOREIGN_KEY_CHECKS = 1;

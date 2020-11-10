DROP TABLE IF EXISTS `sys_provinces`;
CREATE TABLE `sys_provinces`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '唯一标识',
  `province_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'code',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '省份',
  `country_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '国家',
  `province_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份英文名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1_province`(`country_id`, `province_id`) USING BTREE,
  INDEX `idx2_province`(`province_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_provinces
-- ----------------------------
INSERT INTO `sys_provinces` VALUES ('1', '110000', '北京市', '001', 'beijingshi');
INSERT INTO `sys_provinces` VALUES ('2', '120000', '天津市', '001', 'tianjinshi');
INSERT INTO `sys_provinces` VALUES ('3', '130000', '河北省', '001', 'hebeisheng');
INSERT INTO `sys_provinces` VALUES ('4', '140000', '山西省', '001', 'shanxisheng');
INSERT INTO `sys_provinces` VALUES ('5', '150000', '内蒙古自治区', '001', 'neimengguzizhiqu');
INSERT INTO `sys_provinces` VALUES ('6', '210000', '辽宁省', '001', 'liaoningsheng');
INSERT INTO `sys_provinces` VALUES ('7', '220000', '吉林省', '001', 'jilinsheng');
INSERT INTO `sys_provinces` VALUES ('8', '230000', '黑龙江省', '001', 'heilongjiangsheng');
INSERT INTO `sys_provinces` VALUES ('9', '310000', '上海市', '001', 'shanghaishi');
INSERT INTO `sys_provinces` VALUES ('10', '320000', '江苏省', '001', 'jiangsusheng');
INSERT INTO `sys_provinces` VALUES ('11', '330000', '浙江省', '001', 'zhejiangsheng');
INSERT INTO `sys_provinces` VALUES ('12', '340000', '安徽省', '001', 'anhuisheng');
INSERT INTO `sys_provinces` VALUES ('13', '350000', '福建省', '001', 'fujiansheng');
INSERT INTO `sys_provinces` VALUES ('14', '360000', '江西省', '001', 'jiangxisheng');
INSERT INTO `sys_provinces` VALUES ('15', '370000', '山东省', '001', 'shandongsheng');
INSERT INTO `sys_provinces` VALUES ('16', '410000', '河南省', '001', 'henansheng');
INSERT INTO `sys_provinces` VALUES ('17', '420000', '湖北省', '001', 'hubeisheng');
INSERT INTO `sys_provinces` VALUES ('18', '430000', '湖南省', '001', 'hunansheng');
INSERT INTO `sys_provinces` VALUES ('19', '440000', '广东省', '001', 'guangdongsheng');
INSERT INTO `sys_provinces` VALUES ('20', '450000', '广西壮族自治区', '001', 'guangxizhuangzuzizhiqu');
INSERT INTO `sys_provinces` VALUES ('21', '460000', '海南省', '001', 'hainansheng');
INSERT INTO `sys_provinces` VALUES ('22', '500000', '重庆市', '001', 'zhongqingshi');
INSERT INTO `sys_provinces` VALUES ('23', '510000', '四川省', '001', 'sichuansheng');
INSERT INTO `sys_provinces` VALUES ('24', '520000', '贵州省', '001', 'guizhousheng');
INSERT INTO `sys_provinces` VALUES ('25', '530000', '云南省', '001', 'yunnansheng');
INSERT INTO `sys_provinces` VALUES ('26', '540000', '西藏自治区', '001', 'xicangzizhiqu');
INSERT INTO `sys_provinces` VALUES ('27', '610000', '陕西省', '001', 'shanxisheng');
INSERT INTO `sys_provinces` VALUES ('28', '620000', '甘肃省', '001', 'gansusheng');
INSERT INTO `sys_provinces` VALUES ('29', '630000', '青海省', '001', 'qinghaisheng');
INSERT INTO `sys_provinces` VALUES ('30', '640000', '宁夏回族自治区', '001', 'ningxiahuizuzizhiqu');
INSERT INTO `sys_provinces` VALUES ('31', '650000', '新疆维吾尔自治区', '001', 'xinjiangweiwuerzizhiqu');
INSERT INTO `sys_provinces` VALUES ('32', '710000', '台湾省', '001', 'taiwansheng');
INSERT INTO `sys_provinces` VALUES ('33', '810000', '香港特别行政区', '001', 'xianggangtebiexingzhengqu');
INSERT INTO `sys_provinces` VALUES ('34', '820000', '澳门特别行政区', '001', 'aomentebiexingzhengqu');

SET FOREIGN_KEY_CHECKS = 1;
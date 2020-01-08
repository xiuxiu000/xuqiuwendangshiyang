/*
 Navicat Premium Data Transfer

 Source Server         : shiyang
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/01/2020 22:33:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码');
INSERT INTO `category` VALUES ('172934bd636d485c98fd2d3d9cccd409', '运动户外');
INSERT INTO `category` VALUES ('2', '电脑办公');
INSERT INTO `category` VALUES ('3', '家具家居');
INSERT INTO `category` VALUES ('4', '鞋靴箱包');
INSERT INTO `category` VALUES ('5', '图书音像');
INSERT INTO `category` VALUES ('6', '母婴孕婴');
INSERT INTO `category` VALUES ('afdba41a139b4320a74904485bdb7719', '汽车用品');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `itemid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `subtotal` double NULL DEFAULT NULL,
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `fk_0001`(`pid`) USING BTREE,
  INDEX `fk_0002`(`oid`) USING BTREE,
  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1a36f179-6fa7-4f3e-9232-7ae5885133a8', 1, 4199, '33', '8d027216-b5d4-4741-bb7d-28d3960aac7d');
INSERT INTO `orderitem` VALUES ('21e78e95-0f8a-4aaa-a27e-6f771353cef1', 1, 1299, '1', 'a71d6549-cd69-44ed-b8f0-33ee1a6a7e29');
INSERT INTO `orderitem` VALUES ('33ce82d9-00cb-4297-96c2-027fbee9abb2', 1, 6688, '32', 'ba7210d7-9884-4dd2-b7be-6346e8bd57da');
INSERT INTO `orderitem` VALUES ('34343a02-24e6-4523-93b0-ead607349547', 1, 2298, '11', '8d027216-b5d4-4741-bb7d-28d3960aac7d');
INSERT INTO `orderitem` VALUES ('517cc05d-98ac-4ef2-b6e0-1ee9214dc977', 1, 4199, '33', '72258714-9324-4701-8faf-940442b8eae4');
INSERT INTO `orderitem` VALUES ('5a6aa82e-8702-42ba-ab4f-963eacc4c5e8', 1, 1299, '1', '72258714-9324-4701-8faf-940442b8eae4');
INSERT INTO `orderitem` VALUES ('6dd43d25-262c-412f-b4e3-cddcb59e7c34', 1, 6688, '32', 'a3847d02-3e3b-4dbe-a06a-2c54aca58590');
INSERT INTO `orderitem` VALUES ('6f55e4c7-8d87-48f7-8f1c-07a0f3b94265', 1, 1299, '1', '966e0361-821e-4aa3-b688-c18001200ad8');
INSERT INTO `orderitem` VALUES ('718744ee-44c6-479b-959b-26d8c6332dc6', 1, 2298, '11', 'fcee5a90-8b9e-4853-ad73-54519f084836');
INSERT INTO `orderitem` VALUES ('746ce079-e07d-4389-901e-265cb3ea5f31', 1, 1299, '1', '028de506-95e3-4141-8788-a9922c5fce49');
INSERT INTO `orderitem` VALUES ('7e62da36-7ae4-48a8-ab40-c8ff7dbd208b', 1, 1299, '1', '574b2505-c9cf-4375-8560-959fa5d426e7');
INSERT INTO `orderitem` VALUES ('7eb443db-d365-41c8-b6ac-1ae938def9d5', 1, 1299, '1', '1d6d5e76-70a8-4bf8-bdaa-9419a91efcf1');
INSERT INTO `orderitem` VALUES ('848fae26-7e02-4f73-9451-8000025d79a8', 1, 1299, '1', '66976ef8-c3f4-4890-a334-4b2443fa8b5d');
INSERT INTO `orderitem` VALUES ('87aacfcc-003a-434d-a834-c21015f2d6b6', 1, 2299, '31', 'c98253fa-da22-4433-8c9f-a91e48ffd793');
INSERT INTO `orderitem` VALUES ('93373d44-8c1c-49cf-ac8a-21491ec20e1d', 1, 4199, '33', 'a5ae9854-66ab-492d-88b5-9ec590352342');
INSERT INTO `orderitem` VALUES ('96c15ba6-c1a6-4437-a033-31f65bd56003', 1, 2298, '11', 'd5941136-43ec-4011-87cf-ac501042cd9c');
INSERT INTO `orderitem` VALUES ('9c3ba7b2-815a-4724-b90d-07f0390ca948', 1, 2298, '11', '8775e131-2bb9-4564-baa6-c1cd32b548b9');
INSERT INTO `orderitem` VALUES ('9cb42753-8f3b-4c8a-95c8-54f3f48d37d2', 1, 1299, '1', '3a4c3736-5912-44e6-b09d-f4379b92c06c');
INSERT INTO `orderitem` VALUES ('a4a81343-4dca-4c5a-bea3-76c69b501c58', 1, 1299, '1', 'a256b35e-090f-4a94-99c7-8f71613f619c');
INSERT INTO `orderitem` VALUES ('a95bca4a-566e-41ca-8e53-3d4eeefb250b', 10, 39990, '17', 'd5ff72e7-951b-4598-b8ea-ea37e9da34dc');
INSERT INTO `orderitem` VALUES ('af3d0e2b-5a04-46e4-bc57-21387cc2706b', 1, 1299, '1', 'fcee5a90-8b9e-4853-ad73-54519f084836');
INSERT INTO `orderitem` VALUES ('b1519ef6-40f1-4bb1-b0a4-f5670caa5918', 1, 1299, '1', 'eb46a1a7-2b9a-4d47-8714-64cc14c410c2');
INSERT INTO `orderitem` VALUES ('b3017949-2c08-4148-bf26-04a5661a6c65', 1, 1299, '1', '38e0f812-7389-49db-a651-f34158f97ffb');
INSERT INTO `orderitem` VALUES ('c20978c6-b179-4c15-96d7-fba3659f7ee3', 1, 6688, '32', '966e0361-821e-4aa3-b688-c18001200ad8');
INSERT INTO `orderitem` VALUES ('d02fdeae-f459-4f98-b523-379d4aca1f1c', 1, 2499, '13', '792fdaf1-164f-49a9-b060-edb361599c94');
INSERT INTO `orderitem` VALUES ('dcc9fb28-996c-45b4-b14e-be59a6e512fa', 1, 1299, '1', 'bbb7b8f4-8cae-4ac8-895e-f668a2f75b8e');
INSERT INTO `orderitem` VALUES ('f2945633-9eb6-4e13-80cf-c4b3a5f5d34c', 1, 1299, '1', '0eefd90c-fea9-403b-bb67-d6a7c1d7dfcb');
INSERT INTO `orderitem` VALUES ('f3f69f19-7155-44c1-bd7a-4a7c8308e55b', 1, 2499, '13', 'd5941136-43ec-4011-87cf-ac501042cd9c');
INSERT INTO `orderitem` VALUES ('f4c9cb67-fcbc-4a6f-bb71-8223b7862b71', 1, 4199, '33', '8775e131-2bb9-4564-baa6-c1cd32b548b9');
INSERT INTO `orderitem` VALUES ('f97bd058-b80f-4c5b-a2ce-5c7ef94155f9', 1, 2599, '10', '751e2d82-cb3b-4d8f-a32a-f0942bffad80');
INSERT INTO `orderitem` VALUES ('fb1d4a53-f258-42e7-b93e-a737891619d3', 1, 4199, '33', 'd5941136-43ec-4011-87cf-ac501042cd9c');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ordertime` datetime(0) NULL DEFAULT NULL,
  `total` double NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('028de506-95e3-4141-8788-a9922c5fce49', NULL, 1299, 0, '意图', '意图', '123', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('0eefd90c-fea9-403b-bb67-d6a7c1d7dfcb', NULL, 1299, 0, NULL, NULL, NULL, 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('1d6d5e76-70a8-4bf8-bdaa-9419a91efcf1', NULL, 1299, 0, '意图', '意图', '1111111', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('38e0f812-7389-49db-a651-f34158f97ffb', NULL, 1299, 0, 'java', 'java', '123', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('3a4c3736-5912-44e6-b09d-f4379b92c06c', NULL, 1299, 0, NULL, NULL, NULL, 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('574b2505-c9cf-4375-8560-959fa5d426e7', NULL, 1299, 0, NULL, NULL, NULL, 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('66976ef8-c3f4-4890-a334-4b2443fa8b5d', NULL, 1299, 0, '青岛', '刘海洋', '18660602763', 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');
INSERT INTO `orders` VALUES ('72258714-9324-4701-8faf-940442b8eae4', NULL, 5498, 0, '青岛', '联合国', '111', 'a48745e1-e7f9-4e49-825e-3d881f33c5f5');
INSERT INTO `orders` VALUES ('751e2d82-cb3b-4d8f-a32a-f0942bffad80', NULL, 2599, 0, NULL, NULL, NULL, 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');
INSERT INTO `orders` VALUES ('792fdaf1-164f-49a9-b060-edb361599c94', NULL, 2499, 0, '', '刘海洋', '', 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');
INSERT INTO `orders` VALUES ('8775e131-2bb9-4564-baa6-c1cd32b548b9', NULL, 6497, 0, '', '联合国', '', 'a48745e1-e7f9-4e49-825e-3d881f33c5f5');
INSERT INTO `orders` VALUES ('8d027216-b5d4-4741-bb7d-28d3960aac7d', NULL, 6497, 0, NULL, NULL, NULL, 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');
INSERT INTO `orders` VALUES ('966e0361-821e-4aa3-b688-c18001200ad8', NULL, 7987, 0, '青岛', '刘海洋', '', 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');
INSERT INTO `orders` VALUES ('a256b35e-090f-4a94-99c7-8f71613f619c', NULL, 1299, 0, NULL, NULL, NULL, 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('a3847d02-3e3b-4dbe-a06a-2c54aca58590', NULL, 6688, 0, 'liuchengye', '刘海洋', '123', 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');
INSERT INTO `orders` VALUES ('a5ae9854-66ab-492d-88b5-9ec590352342', NULL, 4199, 0, 'aaa', 'ui', 'aa', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('a71d6549-cd69-44ed-b8f0-33ee1a6a7e29', NULL, 1299, 0, '意图', '意图', '1', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('ba7210d7-9884-4dd2-b7be-6346e8bd57da', NULL, 6688, 0, 'java', 'java', '', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('bbb7b8f4-8cae-4ac8-895e-f668a2f75b8e', NULL, 1299, 0, NULL, NULL, NULL, 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('c98253fa-da22-4433-8c9f-a91e48ffd793', NULL, 2299, 0, 'java', '123', '', 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('d5941136-43ec-4011-87cf-ac501042cd9c', NULL, 8996, 0, '', '联合国', '', 'a48745e1-e7f9-4e49-825e-3d881f33c5f5');
INSERT INTO `orders` VALUES ('d5ff72e7-951b-4598-b8ea-ea37e9da34dc', NULL, 39990, 1, '', '联合国', '', 'a48745e1-e7f9-4e49-825e-3d881f33c5f5');
INSERT INTO `orders` VALUES ('eb46a1a7-2b9a-4d47-8714-64cc14c410c2', NULL, 1299, 0, NULL, NULL, NULL, 'db8c5ed5-2020-4b13-a5b2-ba25df66dc7e');
INSERT INTO `orders` VALUES ('fcee5a90-8b9e-4853-ad73-54519f084836', NULL, 3597, 0, '青岛大学', '刘海洋', '18660602763', 'bfb0a57f-dd40-4b9d-8e2b-e57dc155b38b');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `market_price` double NULL DEFAULT NULL,
  `shop_price` double NULL DEFAULT NULL,
  `pimage` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pdate` date NULL DEFAULT NULL,
  `is_hot` int(11) NULL DEFAULT NULL,
  `pdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pflag` int(11) NULL DEFAULT NULL,
  `cid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `sfk_0001`(`cid`) USING BTREE,
  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 4c 标准版', 1399, 1299, 'products/1/c_0001.jpg', '2019-03-18', 1, '小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待', 0, '1');
INSERT INTO `product` VALUES ('10', '华为 Ascend Mate7', 2699, 2599, 'products/1/c_0010.jpg', '2015-11-02', 1, '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', 0, '1');
INSERT INTO `product` VALUES ('11', 'vivo X5Pro', 2399, 2298, 'products/1/c_0014.jpg', '2015-11-02', 1, '移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术', 0, '1');
INSERT INTO `product` VALUES ('12', '努比亚（nubia）My 布拉格', 1899, 1799, 'products/1/c_0013.jpg', '2015-11-02', 0, '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', 0, '1');
INSERT INTO `product` VALUES ('13', '华为 麦芒4', 2599, 2499, 'products/1/c_0012.jpg', '2015-11-02', 1, '华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖', 0, '1');
INSERT INTO `product` VALUES ('14', 'vivo X5M', 1899, 1799, 'products/1/c_0011.jpg', '2015-11-02', 0, 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', 0, '1');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', 4399, 4288, 'products/1/c_0015.jpg', '2015-11-02', 1, 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', 0, '1');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', 4200, 4087, 'products/1/c_0016.jpg', '2015-11-03', 0, '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', 0, '1');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', 4099, 3999, 'products/1/c_0017.jpg', '2015-11-02', 0, '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', 0, '1');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', 3599, 3499, 'products/1/c_0018.jpg', '2015-11-02', 0, 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', 0, '1');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', 1599, 1469, 'products/1/c_0020.jpg', '2015-11-02', 1, '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', 0, '1');
INSERT INTO `product` VALUES ('2', '中兴 AXON', 2899, 2699, 'products/1/c_0002.jpg', '2015-11-05', 1, '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', 0, '1');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', 649, 549, 'products/1/c_0019.jpg', '2015-11-02', 0, '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', 0, '1');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', 1099, 999, 'products/1/c_0021.jpg', '2015-11-02', 0, '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', 0, '1');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', 2099, 1999, 'products/1/c_0022.jpg', '2015-11-02', 1, '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', 0, '1');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', 1799, 1699, 'products/1/c_0023.jpg', '2015-11-09', 1, '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', 0, '1');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', 3988, 3888, 'products/1/c_0024.jpg', '2015-11-02', 1, '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', 0, '1');
INSERT INTO `product` VALUES ('25', 'Apple iPhone 6 Plus (A1524) 16GB 金色', 5188, 4988, 'products/1/c_0025.jpg', '2015-11-02', 0, 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', 0, '1');
INSERT INTO `product` VALUES ('26', 'Apple iPhone 6s (A1700) 64G 玫瑰金色', 6388, 6088, 'products/1/c_0026.jpg', '2015-11-02', 0, 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', 0, '1');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', 5588, 5388, 'products/1/c_0027.jpg', '2015-11-02', 0, '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', 0, '1');
INSERT INTO `product` VALUES ('28', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', 5999, 5888, 'products/1/c_0028.jpg', '2015-11-02', 0, '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', 0, '1');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', 3018, 2978, 'products/1/c_0029.jpg', '2015-11-02', 0, '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', 0, '1');
INSERT INTO `product` VALUES ('3', '华为荣耀6', 1599, 1499, 'products/1/c_0003.jpg', '2015-11-02', 0, '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', 0, '1');
INSERT INTO `product` VALUES ('30', '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', 1099, 999, 'products/1/c_0030.jpg', '2015-11-02', 0, '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', 0, '1');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', 2399, 2299, 'products/1/c_0031.jpg', '2015-11-02', 0, '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', 0, '2');
INSERT INTO `product` VALUES ('32', 'Apple MacBook Air MJVE2CH/A 13.3英寸', 6788, 6688, 'products/1/c_0032.jpg', '2015-11-02', 0, '宽屏笔记本电脑 128GB 闪存', 0, '2');
INSERT INTO `product` VALUES ('33', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)', 4399, 4199, 'products/1/c_0033.jpg', '2015-11-02', 0, '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', 0, '2');
INSERT INTO `product` VALUES ('34', '联想（Lenovo）小新V3000经典版', 4599, 4499, 'products/1/c_0034.jpg', '2015-11-02', 0, '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', 0, '2');
INSERT INTO `product` VALUES ('35', '华硕（ASUS）经典系列R557LI', 3799, 3699, 'products/1/c_0035.jpg', '2015-11-02', 0, '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', 0, '2');
INSERT INTO `product` VALUES ('36', '华硕（ASUS）X450J', 4599, 4399, 'products/1/c_0036.jpg', '2015-11-02', 0, '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', 0, '2');
INSERT INTO `product` VALUES ('37', '戴尔（DELL）灵越 飞匣3000系列', 3399, 3299, 'products/1/c_0037.jpg', '2015-11-03', 0, ' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', 0, '2');
INSERT INTO `product` VALUES ('38', '惠普(HP)WASD 暗影精灵', 5699, 5499, 'products/1/c_0038.jpg', '2015-11-02', 0, '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', 0, '2');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', 11299, 10288, 'products/1/c_0039.jpg', '2015-11-02', 0, 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', 0, '2');
INSERT INTO `product` VALUES ('4', '联想 P1', 2199, 1999, 'products/1/c_0004.jpg', '2015-11-02', 0, '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', 0, '1');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', 6799, 6599, 'products/1/c_0040.jpg', '2015-11-02', 0, '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', 0, '2');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', 5699, 5499, 'products/1/c_0041.jpg', '2015-11-02', 0, '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', 0, '2');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', 6199, 5999, 'products/1/c_0042.jpg', '2015-11-02', 0, '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', 0, '2');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', 5699, 5499, 'products/1/c_0043.jpg', '2015-11-02', 0, '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', 0, '2');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', 3199, 3099, 'products/1/c_0044.jpg', '2015-11-02', 0, '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', 0, '2');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', 10999, 9899, 'products/1/c_0045.jpg', '2015-11-02', 0, '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', 0, '2');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', 3299, 3199, 'products/1/c_0046.jpg', '2015-11-02', 0, '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', 0, '2');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', 5099, 4899, 'products/1/c_0047.jpg', '2015-11-11', 0, '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', 0, '2');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', 2088, 1888, 'products/1/c_0048.jpg', '2015-11-02', 0, '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', 0, '2');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', 1399, 1299, 'products/1/c_0049.jpg', '2015-11-02', 0, 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', 0, '2');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', 1799, 1699, 'products/1/c_0005.jpg', '2015-11-01', 0, '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', 0, '1');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', 2399, 2299, 'products/1/c_0050.jpg', '2015-11-12', 0, '（9.7英寸 16G WLAN 机型 银色）', 0, '2');
INSERT INTO `product` VALUES ('6', '魅族 MX5 16GB 银黑色', 1899, 1799, 'products/1/c_0006.jpg', '2015-11-02', 0, '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', 0, '1');
INSERT INTO `product` VALUES ('7', '三星 Galaxy On7', 1499, 1398, 'products/1/c_0007.jpg', '2015-11-14', 0, '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', 0, '1');
INSERT INTO `product` VALUES ('8', 'NUU NU5', 1288, 1190, 'products/1/c_0008.jpg', '2015-11-02', 0, 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', 0, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('54bcd76c-646f-4c3d-986f-b3064c40ab5a', 'uyyu', '123456', 'ui', 'itchengye6@126.com', NULL, NULL, 'male', 0, '4f3ca455-9d9e-484e-823e-caa542d049dc');
INSERT INTO `user` VALUES ('8a4e8807-286b-4b67-8140-f52667a68c72', '', '12', '', '', NULL, NULL, NULL, 0, '1377e3a9-855c-4ee8-bb7f-5fe682504c33');
INSERT INTO `user` VALUES ('a48745e1-e7f9-4e49-825e-3d881f33c5f5', 'aaa', '123456', '联合国', 'itchengye6@126.com', NULL, '1998-01-01', 'male', 1, 'a800d7fd-8b5c-4991-aa56-4dee292e9600');
INSERT INTO `user` VALUES ('db8c5ed5-2020-4b13-a5b2-ba25df66dc7e', 'www', '123456', 'ui', 'itchengye6@126.com', NULL, '2019-06-03', 'male', 1, 'b5d4d725-8561-41f2-bd1c-3a5f94525ac6');

SET FOREIGN_KEY_CHECKS = 1;

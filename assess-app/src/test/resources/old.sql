/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.201-assess
Source Server Version : 80015
Source Host           : 192.168.2.201:3309
Source Database       : pmcc_assess

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-12-19 16:26:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_assets_customize_data_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_assets_customize_data_field`;
CREATE TABLE `tb_assets_customize_data_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `type_customize` int(11) DEFAULT NULL COMMENT '数据字典配置的类型',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name_list` varchar(1000) DEFAULT NULL COMMENT '字段名称集合',
  `sorting` int(16) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=429 DEFAULT CHARSET=utf8 COMMENT='资产评估 阶段 自定义名称表';

-- ----------------------------
-- Table structure for tb_base_accounting_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_accounting_subject`;
CREATE TABLE `tb_base_accounting_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `subject` varchar(255) DEFAULT NULL COMMENT '会计科目',
  `first_number` varchar(255) DEFAULT NULL COMMENT '一级编号',
  `second_number` varchar(255) DEFAULT NULL COMMENT '二级编号',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2090 DEFAULT CHARSET=utf8 COMMENT='会计科目配置';

-- ----------------------------
-- Table structure for tb_base_assist
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_assist`;
CREATE TABLE `tb_base_assist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '显示名称',
  `assist_name` varchar(100) DEFAULT NULL COMMENT '自定义FORMBean名称',
  `assist_type` varchar(100) DEFAULT NULL COMMENT '所属分类',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='自定义Form配置';

-- ----------------------------
-- Table structure for tb_base_data_dic
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_data_dic`;
CREATE TABLE `tb_base_data_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `key_value` varchar(2000) DEFAULT NULL COMMENT 'key_value方式存储json数据',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_sys_data_dic_field_name_uindex` (`field_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2094 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for tb_base_file_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_file_template`;
CREATE TABLE `tb_base_file_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `唯一值` (`name`) USING BTREE COMMENT '唯一值'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='系统附件模板';

-- ----------------------------
-- Table structure for tb_base_parameter
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_parameter`;
CREATE TABLE `tb_base_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `par_key` varchar(100) DEFAULT NULL COMMENT '参数名称',
  `par_values` varchar(100) DEFAULT NULL COMMENT '参数值',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述说明',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否有效',
  `class_name` varchar(255) DEFAULT NULL COMMENT '所属分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_par_key` (`par_key`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='参数表';

-- ----------------------------
-- Table structure for tb_base_project_classify
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_project_classify`;
CREATE TABLE `tb_base_project_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `use_same_field_name` varchar(100) DEFAULT NULL COMMENT '使用相同配置所对应的key值',
  `apply_url` varchar(255) DEFAULT NULL COMMENT '立项申请地址',
  `detail_url` varchar(255) DEFAULT NULL COMMENT '详情页面地址',
  `bis_default` bit(1) DEFAULT NULL,
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=358 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for tb_base_report_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_report_field`;
CREATE TABLE `tb_base_report_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `name` varchar(100) NOT NULL COMMENT '显示名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `replace_type` varchar(255) DEFAULT NULL COMMENT '替换方式(文本、附件、自定义)',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_base_report_field_name_uindex` (`name`) USING BTREE,
  UNIQUE KEY `tb_base_report_field_field_name_uindex` (`field_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2121 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for tb_base_report_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_report_template`;
CREATE TABLE `tb_base_report_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '报告名称',
  `use_unit` int(11) DEFAULT NULL COMMENT '报告使用单位',
  `type` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT '0' COMMENT '项目类别',
  `report_type` int(11) DEFAULT NULL COMMENT '报告类型id',
  `entrust_purpose` varchar(255) DEFAULT NULL COMMENT '委托目的',
  `loan_type` int(11) DEFAULT NULL COMMENT '贷款类型',
  `insert_row_index` int(11) DEFAULT NULL COMMENT '汇总模板数据写入开始行号',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否有效',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='定义模板书签字段';

-- ----------------------------
-- Table structure for tb_basic_apply
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_apply`;
CREATE TABLE `tb_basic_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `case_estate_id` int(11) DEFAULT '0' COMMENT '案例中楼盘id',
  `case_building_id` int(11) DEFAULT '0' COMMENT '案例中楼栋主表id',
  `case_unit_id` int(11) DEFAULT '0' COMMENT '案例中单元id',
  `case_house_id` int(11) DEFAULT '0' COMMENT '案例中房屋id',
  `basic_estate_id` int(11) DEFAULT NULL,
  `basic_building_id` int(11) DEFAULT NULL,
  `basic_unit_id` int(11) DEFAULT NULL,
  `basic_house_id` int(11) DEFAULT NULL,
  `plan_details_id` int(15) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `type` int(11) DEFAULT '0' COMMENT '类型 0非工业仓储 1工业仓储 2构筑物',
  `estate_name` varchar(255) DEFAULT NULL COMMENT '楼盘名称',
  `building_number` varchar(50) DEFAULT NULL COMMENT '楼栋号',
  `unit_number` varchar(50) DEFAULT NULL,
  `house_number` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `draft_flag` tinyint(1) DEFAULT '0' COMMENT '草稿标志',
  `estate_part_in_mode` varchar(50) DEFAULT NULL COMMENT '楼盘参与方式',
  `building_part_in_mode` varchar(50) DEFAULT NULL COMMENT '楼栋参与方式',
  `unit_part_in_mode` varchar(50) DEFAULT NULL COMMENT '单元参与方式',
  `house_part_in_mode` varchar(50) DEFAULT NULL COMMENT '房屋参与方式',
  `write_back_block_flag` tinyint(1) DEFAULT '0' COMMENT '是否回写版块到基础数据',
  `copy_id` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1371 DEFAULT CHARSET=utf8 COMMENT='楼栋';

-- ----------------------------
-- Table structure for tb_basic_apply_batch
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_apply_batch`;
CREATE TABLE `tb_basic_apply_batch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `plan_details_id` int(11) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `type` int(11) DEFAULT NULL COMMENT '类型 0非工业仓储 1工业仓储 2构筑物 3在建工程',
  `estate_id` int(11) DEFAULT NULL,
  `estate_name` varchar(255) DEFAULT NULL COMMENT '楼盘名称',
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `draft_flag` tinyint(1) DEFAULT '0' COMMENT '草稿标志',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `quote_id` int(11) DEFAULT NULL COMMENT '引用数据id',
  `base_type` varchar(50) DEFAULT NULL COMMENT '引用数据所属库',
  `show_tab` tinyint(1) DEFAULT '0' COMMENT '0-可以引用项目 1-可以引用案列',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2105 DEFAULT CHARSET=utf8 COMMENT='楼盘批量申请主表';

-- ----------------------------
-- Table structure for tb_basic_apply_batch_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_apply_batch_detail`;
CREATE TABLE `tb_basic_apply_batch_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT NULL,
  `apply_batch_id` int(11) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL COMMENT 'estate&building&unit&house',
  `table_id` int(11) DEFAULT NULL COMMENT '关联数据的id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `display_name` varchar(255) DEFAULT NULL COMMENT '显示名称',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `bis_standard` bit(1) DEFAULT b'0' COMMENT '是否标准',
  `quote_id` int(11) DEFAULT NULL COMMENT '引用数据id',
  `base_type` varchar(50) DEFAULT NULL COMMENT '引用数据所属库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2819 DEFAULT CHARSET=utf8 COMMENT='楼盘批量申请子表';

-- ----------------------------
-- Table structure for tb_basic_building
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_building`;
CREATE TABLE `tb_basic_building` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(11) DEFAULT NULL,
  `estate_id` int(11) DEFAULT NULL COMMENT 'estate_id',
  `residence_use_year` int(12) DEFAULT NULL COMMENT '非工业仓储建筑使用年限',
  `industry_use_year` int(12) DEFAULT NULL COMMENT '工业与仓储建筑使用年限',
  `building_number` varchar(50) DEFAULT NULL COMMENT '楼栋编号',
  `building_name` varchar(255) DEFAULT NULL COMMENT '楼栋名称',
  `floor_count` varchar(50) DEFAULT NULL COMMENT '总层数',
  `location` varchar(255) DEFAULT NULL COMMENT '所在位置',
  `builder` int(11) DEFAULT NULL COMMENT '建造商id',
  `builder_name` varchar(100) DEFAULT NULL COMMENT '建造商名称',
  `property` int(11) DEFAULT NULL COMMENT '物业公司Id',
  `property_name` varchar(255) DEFAULT NULL COMMENT '物业公司名称',
  `unit_interval` varchar(255) DEFAULT NULL COMMENT '户型区间',
  `property_fee` decimal(13,2) DEFAULT NULL COMMENT '物业费',
  `facilities_use_fee` decimal(13,2) DEFAULT NULL COMMENT '公共设施使用费',
  `building_height` varchar(255) DEFAULT NULL COMMENT '建筑高度',
  `building_area` decimal(12,2) DEFAULT NULL COMMENT '建筑面积',
  `cover_an_area` decimal(12,2) DEFAULT NULL COMMENT '占地面积',
  `property_type` int(11) DEFAULT NULL COMMENT '物业类型(住宅、商业、办公等）',
  `property_category` int(11) DEFAULT NULL COMMENT '物业类别',
  `open_time` datetime DEFAULT NULL COMMENT '开盘时间',
  `room_time` datetime DEFAULT NULL COMMENT '交房时间',
  `completed_time_type` int(11) DEFAULT NULL COMMENT '竣工时间获取方式',
  `be_completed_time` datetime DEFAULT NULL COMMENT '竣工时间',
  `floor_height` varchar(255) DEFAULT NULL COMMENT '层高',
  `diameter_depth` decimal(10,2) DEFAULT NULL COMMENT '径深',
  `land_use_year` int(11) DEFAULT NULL COMMENT '土地使用年限',
  `net_height` decimal(10,2) DEFAULT NULL COMMENT '净高',
  `building_structure_type` int(11) DEFAULT NULL COMMENT '建筑结构类型',
  `building_structure_category` int(11) DEFAULT NULL COMMENT '建筑结构类别',
  `first_floor` int(11) DEFAULT NULL COMMENT '首层',
  `max_floor` int(11) DEFAULT NULL COMMENT '最高层',
  `in_jacket_area` varchar(255) DEFAULT NULL COMMENT '套内面积',
  `use_area` varchar(255) DEFAULT NULL COMMENT '使用面积',
  `construction_quality` int(11) DEFAULT NULL COMMENT '工程质量',
  `appearance_style` int(11) DEFAULT NULL COMMENT '外观风格',
  `appearance_new_and_old` int(11) DEFAULT NULL COMMENT '外观新旧',
  `between_distance` int(11) DEFAULT NULL COMMENT '楼间距',
  `between_distance_description` varchar(255) DEFAULT NULL COMMENT '楼间距描述',
  `v_structura` varchar(255) DEFAULT NULL COMMENT '构造特征',
  `v_specifications` varchar(2000) DEFAULT NULL COMMENT '规格',
  `v_structural_construction` varchar(255) DEFAULT NULL COMMENT '结构构造',
  `v_basic_practice` varchar(500) DEFAULT NULL COMMENT '基础作法',
  `v_structural_practice` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `property_social_prestige` int(22) DEFAULT NULL COMMENT '物业公司社会信誉',
  `property_company_nature` int(22) DEFAULT NULL COMMENT '物业公司公司性质',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1804 DEFAULT CHARSET=utf8 COMMENT='楼栋';

-- ----------------------------
-- Table structure for tb_basic_building_function
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_building_function`;
CREATE TABLE `tb_basic_building_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(24) DEFAULT NULL COMMENT '类型',
  `building_id` int(11) DEFAULT NULL,
  `decoration_part` int(11) DEFAULT NULL COMMENT '装修部位',
  `decorating_material` int(11) DEFAULT NULL COMMENT '装修材料',
  `material_price` int(11) DEFAULT NULL COMMENT '材料价格区间',
  `construction_technology` int(11) DEFAULT NULL COMMENT '施工工艺',
  `remark` varchar(500) DEFAULT NULL COMMENT '现状描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1068 DEFAULT CHARSET=utf8 COMMENT='楼栋建筑功能';

-- ----------------------------
-- Table structure for tb_basic_building_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_building_maintenance`;
CREATE TABLE `tb_basic_building_maintenance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `building_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `category` int(11) DEFAULT NULL COMMENT '分类',
  `material_quality` int(11) DEFAULT NULL COMMENT '材质',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `build_number` varchar(244) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COMMENT='维护结构';

-- ----------------------------
-- Table structure for tb_basic_building_outfit
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_building_outfit`;
CREATE TABLE `tb_basic_building_outfit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `building_id` int(11) DEFAULT NULL,
  `decoration_part` varchar(100) DEFAULT NULL COMMENT '装修部位',
  `decorating_material` int(11) DEFAULT NULL COMMENT '装修材料',
  `material_price` int(11) DEFAULT NULL COMMENT '材料价格区间',
  `construction_technology` int(11) DEFAULT NULL COMMENT '施工工艺',
  `material_grade` int(11) DEFAULT NULL COMMENT '材料档次',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `build_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=964 DEFAULT CHARSET=utf8 COMMENT='楼栋外装情况';

-- ----------------------------
-- Table structure for tb_basic_building_property_service_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_building_property_service_item`;
CREATE TABLE `tb_basic_building_property_service_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `master_id` int(11) DEFAULT NULL,
  `building_id` int(11) DEFAULT NULL,
  `service_type` int(11) DEFAULT NULL COMMENT '服务类型',
  `service_content` int(11) DEFAULT NULL COMMENT '服务内容',
  `service_time` varchar(255) DEFAULT NULL COMMENT '服务时间',
  `grade_evaluation` int(11) DEFAULT NULL COMMENT '等级评价',
  `charges_notes` varchar(255) DEFAULT NULL COMMENT '收费标准',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=utf8 COMMENT='楼栋 物业服务内容';

-- ----------------------------
-- Table structure for tb_basic_building_surface
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_building_surface`;
CREATE TABLE `tb_basic_building_surface` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `building_id` int(11) DEFAULT NULL,
  `structure` int(11) DEFAULT NULL COMMENT '层面结构',
  `description` varchar(200) DEFAULT NULL COMMENT '层级结构描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `build_number` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COMMENT='层面结构';

-- ----------------------------
-- Table structure for tb_basic_estate
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate`;
CREATE TABLE `tb_basic_estate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '类型 0非工业仓储 1工业仓储',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `district` varchar(20) DEFAULT NULL COMMENT '区县',
  `block_id` int(11) DEFAULT NULL,
  `block_name` varchar(255) DEFAULT NULL COMMENT '版块名称',
  `block_description` varchar(200) DEFAULT NULL COMMENT '基础版块描述',
  `developer` varchar(255) DEFAULT NULL COMMENT '开发商名称',
  `developer_name` varchar(255) DEFAULT NULL COMMENT '开发商名称',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `street_number` varchar(100) DEFAULT NULL COMMENT '街道号',
  `street` varchar(100) DEFAULT NULL COMMENT '街道',
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `land_level` int(25) DEFAULT NULL COMMENT '土地级别',
  `attach_number` varchar(50) DEFAULT NULL COMMENT '附号',
  `floor_area` decimal(12,2) DEFAULT NULL COMMENT '建筑面积',
  `cover_an_area` decimal(12,2) DEFAULT NULL COMMENT '占地面积',
  `volumetric_rate` varchar(100) DEFAULT NULL COMMENT '容积率',
  `greening_rate` varchar(100) DEFAULT NULL COMMENT '绿化率',
  `building_number` int(20) DEFAULT NULL COMMENT '总栋数 (0一栋 1多栋)',
  `position` int(11) DEFAULT NULL COMMENT '方位',
  `description` varchar(4000) DEFAULT NULL COMMENT '楼盘概况',
  `average_price` varchar(200) DEFAULT NULL COMMENT '均价',
  `price_range` varchar(255) DEFAULT NULL COMMENT '价格区间',
  `supply_heating` int(11) DEFAULT NULL COMMENT '供热信息 有无',
  `supply_power` int(11) DEFAULT NULL COMMENT '供电信息 有无',
  `supply_communication` int(11) DEFAULT NULL COMMENT '通讯信息 有无',
  `supply_road` int(11) DEFAULT NULL COMMENT '道路信息 有无',
  `supply_water` int(11) DEFAULT NULL COMMENT '供排水情况 有无',
  `drain_water` int(11) DEFAULT '0' COMMENT '排水',
  `infrastructure` varchar(255) DEFAULT NULL COMMENT '基础设施情况',
  `supply_gas` int(11) DEFAULT NULL COMMENT '供气信息 有无',
  `infrastructure_completeness` int(22) DEFAULT NULL COMMENT '基础设施完备度',
  `location_describe` varchar(4000) DEFAULT NULL COMMENT '区位描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `open_time` datetime DEFAULT NULL COMMENT '开盘时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2401 DEFAULT CHARSET=utf8 COMMENT='楼盘';

-- ----------------------------
-- Table structure for tb_basic_estate_land_state
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate_land_state`;
CREATE TABLE `tb_basic_estate_land_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(12) DEFAULT NULL,
  `estate_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '土地名称',
  `land_use_type` int(11) DEFAULT NULL COMMENT '土地用途类型',
  `land_use_category` int(25) DEFAULT NULL COMMENT '土地用途类别',
  `land_factor_total_score` decimal(11,2) DEFAULT '0.00' COMMENT '土地因素合计分值',
  `land_level_content` json DEFAULT NULL COMMENT '土地级别json',
  `land_level` int(11) DEFAULT NULL COMMENT '土地级别',
  `land_area` varchar(100) DEFAULT NULL COMMENT '土地面积',
  `east_to` varchar(100) DEFAULT NULL COMMENT '东至',
  `south_to` varchar(100) DEFAULT NULL COMMENT '南至',
  `west_to` varchar(100) DEFAULT NULL COMMENT '西至',
  `north_to` varchar(100) DEFAULT NULL COMMENT '北至',
  `shape_state` int(11) DEFAULT NULL COMMENT '土地形状状况',
  `shape_state_remark` varchar(255) DEFAULT NULL COMMENT '土地形状备注',
  `planeness` int(11) DEFAULT NULL COMMENT '地形',
  `topographic_terrain` int(11) DEFAULT NULL COMMENT '地势',
  `development_degree` int(11) DEFAULT NULL COMMENT '土地开发程度',
  `development_degree_remark` varchar(255) DEFAULT NULL COMMENT '土地开发程度备注',
  `development_degree_content` varchar(255) DEFAULT NULL COMMENT '开发程度熟地内容',
  `plot_ratio` varchar(255) DEFAULT NULL COMMENT '容积率',
  `building_density` varchar(255) DEFAULT NULL COMMENT '建筑密度',
  `green_space_rate` varchar(255) DEFAULT NULL COMMENT '绿地率',
  `compatible_ratio` varchar(255) DEFAULT NULL COMMENT '兼容比例',
  `bearing_capacity` varchar(255) DEFAULT NULL COMMENT '承载力',
  `contaminated` varchar(255) DEFAULT NULL COMMENT '污染',
  `ph` varchar(255) DEFAULT NULL COMMENT '酸碱度',
  `fertility` varchar(255) DEFAULT NULL COMMENT '肥力',
  `conclusion` varchar(255) DEFAULT NULL COMMENT '土地实体结论',
  `hold_on` varchar(255) DEFAULT NULL COMMENT '稳定性',
  `building_height_limit` decimal(16,3) DEFAULT NULL COMMENT '建筑限高',
  `investment_intensity` decimal(17,3) DEFAULT NULL COMMENT '投资强度（万元/亩）',
  `special_restrictions` varchar(1000) DEFAULT NULL COMMENT '特殊限制',
  `present_situation_land_use` varchar(255) DEFAULT NULL COMMENT '土地利用现状',
  `infrastructure_completeness` int(11) DEFAULT NULL COMMENT '基础设施完备度',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2156 DEFAULT CHARSET=utf8 COMMENT='土地实体情况';

-- ----------------------------
-- Table structure for tb_basic_estate_network
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate_network`;
CREATE TABLE `tb_basic_estate_network` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) NOT NULL,
  `supplier` int(11) DEFAULT NULL COMMENT '通信网络名称',
  `service_content` int(11) DEFAULT NULL COMMENT '服务内容',
  `remark` varchar(1000) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2389 DEFAULT CHARSET=utf8 COMMENT='通信网络';

-- ----------------------------
-- Table structure for tb_basic_estate_parking
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate_parking`;
CREATE TABLE `tb_basic_estate_parking` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `estate_id` int(11) DEFAULT NULL,
  `number` int(14) DEFAULT NULL COMMENT '车位数量',
  `parking_type` int(11) DEFAULT NULL COMMENT '车位类型',
  `parking_estate` int(11) DEFAULT NULL COMMENT '楼盘车位',
  `location` int(11) DEFAULT NULL COMMENT '所在位置',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1047 DEFAULT CHARSET=utf8 COMMENT='车位信息';

-- ----------------------------
-- Table structure for tb_basic_estate_supply
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate_supply`;
CREATE TABLE `tb_basic_estate_supply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型（供气、供水、排水、供热、供电）',
  `name` varchar(100) DEFAULT NULL COMMENT '供应商名称',
  `reputation` int(11) DEFAULT NULL COMMENT '供应商信誉',
  `grade` int(11) DEFAULT NULL COMMENT '供应商等级',
  `line_grade` int(11) DEFAULT NULL COMMENT '保障等级',
  `power` varchar(200) DEFAULT NULL COMMENT '功率',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=491 DEFAULT CHARSET=utf8 COMMENT='供应信息 ';

-- ----------------------------
-- Table structure for tb_basic_estate_tagging
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate_tagging`;
CREATE TABLE `tb_basic_estate_tagging` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(11) DEFAULT NULL,
  `table_id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型（楼盘、楼栋、单元、其它）',
  `lng` varchar(100) DEFAULT NULL COMMENT '经度',
  `lat` varchar(100) DEFAULT NULL COMMENT '纬度',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `path_array` json DEFAULT NULL COMMENT '经纬度 数组对象',
  `attachment_id` int(11) DEFAULT NULL COMMENT '附件id',
  `deg` int(11) DEFAULT NULL COMMENT '旋转度数',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5130 DEFAULT CHARSET=utf8 COMMENT='楼盘标注信息';

-- ----------------------------
-- Table structure for tb_basic_estate_tagging_c
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_estate_tagging_c`;
CREATE TABLE `tb_basic_estate_tagging_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型（楼盘、楼栋、单元、其它）',
  `lng` varchar(100) DEFAULT NULL COMMENT '经度',
  `lat` varchar(100) DEFAULT NULL COMMENT '纬度',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `path_array` json DEFAULT NULL COMMENT '经纬度 数组对象',
  `attachment_id` int(11) DEFAULT NULL COMMENT '附件id',
  `deg` int(11) DEFAULT NULL COMMENT '旋转度数',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `table_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55193064 DEFAULT CHARSET=utf8 COMMENT='楼盘标注信息';

-- ----------------------------
-- Table structure for tb_basic_house
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house`;
CREATE TABLE `tb_basic_house` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(12) DEFAULT NULL,
  `unit_id` int(11) NOT NULL DEFAULT '0',
  `house_number` varchar(50) DEFAULT NULL COMMENT '房号',
  `floor` varchar(100) DEFAULT NULL COMMENT '所在楼层',
  `floor_desc` varchar(255) DEFAULT NULL COMMENT '楼层的描述',
  `huxing_id` int(11) DEFAULT NULL COMMENT '选择的户型id',
  `huxing_name` varchar(100) DEFAULT NULL COMMENT '户型id',
  `new_huxing_name` varchar(100) DEFAULT NULL COMMENT '新户型名称',
  `spatial_distribution` int(11) DEFAULT NULL COMMENT '空间布局',
  `spatial_distribution_desc` varchar(255) DEFAULT NULL COMMENT '空间布局描述',
  `cert_use` int(11) DEFAULT NULL COMMENT '证载用途',
  `practical_use` int(11) DEFAULT NULL COMMENT '实际用途',
  `orientation` int(11) DEFAULT NULL COMMENT '朝向',
  `area` decimal(13,2) DEFAULT NULL COMMENT '面积',
  `area_desc` varchar(255) DEFAULT NULL COMMENT '面积描述',
  `research_type` int(11) DEFAULT NULL COMMENT '调查方式',
  `right_interests_restriction` varchar(255) DEFAULT NULL COMMENT '权益限制',
  `use_environment` int(11) DEFAULT NULL COMMENT '使用环境',
  `description` varchar(1000) DEFAULT NULL COMMENT '房屋出租占用情况途描述',
  `case_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '案例日期',
  `use_year` int(18) DEFAULT NULL COMMENT '使用年限',
  `floor_price` decimal(10,3) DEFAULT NULL COMMENT '楼面地价 万/m2',
  `land_location` varchar(255) DEFAULT NULL COMMENT '地块位置',
  `new_degree` varchar(255) DEFAULT NULL COMMENT '成新度',
  `use_condition` int(11) DEFAULT NULL COMMENT '使用情况',
  `use_condition_description` varchar(255) DEFAULT NULL COMMENT '使用情况描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `price_connotation` int(11) DEFAULT NULL COMMENT '单价内涵',
  `decorate_situation` int(11) DEFAULT NULL COMMENT '装修情况',
  `decorate_situation_description` varchar(255) DEFAULT NULL COMMENT '装修情况描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2043 DEFAULT CHARSET=utf8 COMMENT='房屋';

-- ----------------------------
-- Table structure for tb_basic_house_corollary_equipment
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_corollary_equipment`;
CREATE TABLE `tb_basic_house_corollary_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `name` varchar(255) DEFAULT NULL COMMENT '设备名称',
  `equipment_use` int(11) DEFAULT NULL COMMENT '设备用途',
  `parameter_index` varchar(255) DEFAULT NULL COMMENT '参数指标',
  `price` varchar(100) DEFAULT NULL COMMENT '价格',
  `maintenance_status` int(11) DEFAULT NULL COMMENT '维护状况',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='配套设备设施';

-- ----------------------------
-- Table structure for tb_basic_house_damaged_degree
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_damaged_degree`;
CREATE TABLE `tb_basic_house_damaged_degree` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `entity_condition` varchar(50) DEFAULT NULL COMMENT '实体状况',
  `entity_condition_content` varchar(1000) DEFAULT NULL COMMENT '实体状况内容',
  `score` decimal(11,2) DEFAULT NULL COMMENT '得分',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23011 DEFAULT CHARSET=utf8 COMMENT='房屋完损度';

-- ----------------------------
-- Table structure for tb_basic_house_damaged_degree_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_damaged_degree_detail`;
CREATE TABLE `tb_basic_house_damaged_degree_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) DEFAULT NULL,
  `damaged_degree_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `entity_condition` varchar(50) DEFAULT NULL COMMENT '实体状况',
  `entity_condition_content` varchar(1000) DEFAULT NULL COMMENT '实体状况内容',
  `score` decimal(11,2) DEFAULT NULL COMMENT '得分',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1750 DEFAULT CHARSET=utf8 COMMENT='房屋完损度明细';

-- ----------------------------
-- Table structure for tb_basic_house_equipment
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_equipment`;
CREATE TABLE `tb_basic_house_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型（空调、新风、供热）',
  `grade` int(11) DEFAULT NULL COMMENT '档次',
  `category` int(11) DEFAULT NULL COMMENT '类型',
  `supply_weast` varchar(255) DEFAULT NULL COMMENT '供应方式',
  `equipment` varchar(255) DEFAULT NULL COMMENT '空调设备',
  `supply_mode` int(11) DEFAULT NULL COMMENT '供应方式',
  `equipment_price` int(11) DEFAULT NULL COMMENT '设备价格区间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1647 DEFAULT CHARSET=utf8 COMMENT='设备 包含（空调、新风、供暖）';

-- ----------------------------
-- Table structure for tb_basic_house_face_street
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_face_street`;
CREATE TABLE `tb_basic_house_face_street` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `street_name` varchar(255) DEFAULT NULL COMMENT '街道名称',
  `street_level` int(11) DEFAULT NULL COMMENT '街道级别',
  `traffic_flow` int(11) DEFAULT NULL COMMENT '交通流量',
  `visitors_flowrate` int(11) DEFAULT NULL COMMENT '人流量',
  `position` int(12) DEFAULT NULL COMMENT '方位',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=726 DEFAULT CHARSET=utf8 COMMENT='临街（路）状况';

-- ----------------------------
-- Table structure for tb_basic_house_intelligent
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_intelligent`;
CREATE TABLE `tb_basic_house_intelligent` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `switch_circuit` int(11) DEFAULT NULL COMMENT '开关回路',
  `laying_method` int(11) DEFAULT NULL COMMENT '铺设方式',
  `lamps_lanterns` varchar(100) DEFAULT NULL COMMENT '灯具',
  `grade` int(11) DEFAULT NULL COMMENT '档次',
  `intelligent_system` json DEFAULT NULL COMMENT '智能系统',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=828 DEFAULT CHARSET=utf8 COMMENT='电力通讯网络';

-- ----------------------------
-- Table structure for tb_basic_house_room
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_room`;
CREATE TABLE `tb_basic_house_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `room_type` varchar(100) DEFAULT NULL COMMENT '房间类型',
  `name` varchar(255) DEFAULT NULL COMMENT '房间名称',
  `area` decimal(10,2) DEFAULT NULL COMMENT '面积',
  `orientation` varchar(255) DEFAULT NULL COMMENT '朝向',
  `aeration` varchar(255) DEFAULT NULL COMMENT '通风',
  `Illumination` varchar(255) DEFAULT NULL COMMENT '光照',
  `sound_insulation` varchar(255) DEFAULT NULL COMMENT '隔音',
  `sunshine` varchar(255) DEFAULT NULL COMMENT '日照',
  `lighting` varchar(255) DEFAULT NULL COMMENT '采光',
  `opening` varchar(255) DEFAULT NULL COMMENT '开间',
  `depth` varchar(255) DEFAULT NULL COMMENT '进深',
  `layer_height` decimal(11,2) DEFAULT NULL COMMENT '层高',
  `clear_height` decimal(11,2) DEFAULT NULL COMMENT '净高',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1364 DEFAULT CHARSET=utf8 COMMENT='房间';

-- ----------------------------
-- Table structure for tb_basic_house_room_decorate
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_room_decorate`;
CREATE TABLE `tb_basic_house_room_decorate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `room_id` int(11) DEFAULT NULL COMMENT '房间id',
  `part` int(11) DEFAULT NULL COMMENT '装修部位',
  `material` int(11) DEFAULT NULL COMMENT '装修材料',
  `construction_technology` int(11) DEFAULT NULL COMMENT '施工工艺',
  `material_price` int(11) DEFAULT NULL COMMENT '装修材料价格区间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2125 DEFAULT CHARSET=utf8 COMMENT='房间装修';

-- ----------------------------
-- Table structure for tb_basic_house_trading
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_trading`;
CREATE TABLE `tb_basic_house_trading` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(12) DEFAULT NULL,
  `house_id` int(11) NOT NULL,
  `trading_time` datetime DEFAULT NULL COMMENT '交易时间',
  `trading_type` int(11) DEFAULT NULL COMMENT '交易类型',
  `trading_total_price` decimal(50,2) DEFAULT NULL COMMENT '交易总价',
  `trading_unit_price` decimal(50,2) DEFAULT NULL COMMENT '交易单价',
  `buyer_extra_tax` varchar(255) DEFAULT NULL COMMENT '买方额外支付的税',
  `buyer_extra_fee` varchar(255) DEFAULT NULL COMMENT '买方额外支付的费',
  `renting_extra_tax` varchar(255) DEFAULT NULL COMMENT '承租方额外支付的税',
  `renting_extra_fee` varchar(255) DEFAULT NULL COMMENT '承租方额外支付的费',
  `description_type` int(11) DEFAULT NULL COMMENT '说明事项类型',
  `description_content` varchar(200) DEFAULT NULL COMMENT '说明事项内容',
  `installment_interest_rate` varchar(255) DEFAULT NULL COMMENT '分期支付利率',
  `payment_method` int(11) DEFAULT NULL COMMENT '付款方式',
  `transaction_situation` int(11) DEFAULT NULL COMMENT '交易情况',
  `tax_burden` int(11) DEFAULT NULL COMMENT '税费负担',
  `scope_property` int(11) DEFAULT NULL COMMENT '财产范围',
  `scope_include` varchar(255) DEFAULT NULL COMMENT '范围包含',
  `scope_not_include` varchar(255) DEFAULT NULL COMMENT '范围不包含',
  `scope_property_explain` varchar(500) DEFAULT NULL COMMENT '财产范围说明',
  `down_payment_ratio` varchar(255) DEFAULT NULL COMMENT '首付比例',
  `lending_rate` varchar(100) DEFAULT NULL COMMENT '贷款利率',
  `loan_period` int(11) DEFAULT NULL COMMENT '贷款期限',
  `deposit` varchar(255) DEFAULT NULL COMMENT '押金',
  `information_type` int(11) DEFAULT NULL COMMENT '信息来源类型',
  `information_category` int(11) DEFAULT NULL COMMENT '信息来源类别',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `land_buyer_seller` varchar(255) DEFAULT NULL COMMENT '土地买售人',
  `price_type` int(22) DEFAULT NULL COMMENT '价格类型',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `price_connotation` int(11) DEFAULT NULL COMMENT '单价内涵',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2359 DEFAULT CHARSET=utf8 COMMENT='房屋交易信息';

-- ----------------------------
-- Table structure for tb_basic_house_trading_lease
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_trading_lease`;
CREATE TABLE `tb_basic_house_trading_lease` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) DEFAULT NULL,
  `rent_payment_time_start` datetime DEFAULT NULL COMMENT '租金支付时间起',
  `rent_payment_time_end` datetime DEFAULT NULL COMMENT '租金支付时间止',
  `rent_growth_rate` varchar(255) DEFAULT NULL COMMENT '租金增长比率',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='房屋出租';

-- ----------------------------
-- Table structure for tb_basic_house_trading_sell
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_trading_sell`;
CREATE TABLE `tb_basic_house_trading_sell` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) DEFAULT NULL,
  `instalment_period_start` datetime DEFAULT NULL COMMENT '分期支付时间起',
  `instalment_period_end` datetime DEFAULT NULL COMMENT '分期支付时间止',
  `instalment_interest` varchar(255) DEFAULT NULL COMMENT '分期支付利息',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=410 DEFAULT CHARSET=utf8 COMMENT='房屋出售';

-- ----------------------------
-- Table structure for tb_basic_house_water
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_water`;
CREATE TABLE `tb_basic_house_water` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `supply_mode` int(11) DEFAULT NULL COMMENT '给水方式',
  `piping_layout` int(11) DEFAULT NULL COMMENT '给水管道布置',
  `pipe_material` int(11) DEFAULT NULL COMMENT '给水管材料',
  `grade` int(11) DEFAULT NULL COMMENT '档次',
  `booster_equipment` int(11) DEFAULT NULL COMMENT '给水升压设备',
  `pretreated_water` int(11) DEFAULT NULL COMMENT '前置净水',
  `purification_equipment_price` int(11) DEFAULT NULL COMMENT '前置净水设备价格区间',
  `fire_water_supply` int(11) DEFAULT NULL COMMENT '消防给水',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=752 DEFAULT CHARSET=utf8 COMMENT='供排水情况';

-- ----------------------------
-- Table structure for tb_basic_house_water_drain
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_house_water_drain`;
CREATE TABLE `tb_basic_house_water_drain` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `house_id` int(11) NOT NULL,
  `drain_system` int(11) DEFAULT NULL COMMENT '排水系统',
  `type` int(11) DEFAULT NULL COMMENT '类别',
  `organization` int(11) DEFAULT NULL COMMENT '体系',
  `processing_mode` int(11) DEFAULT NULL COMMENT '排水处理方式',
  `evaluate` varchar(500) DEFAULT NULL COMMENT '排水系统评价',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=utf8 COMMENT='供排水情况';

-- ----------------------------
-- Table structure for tb_basic_matching_education
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_education`;
CREATE TABLE `tb_basic_matching_education` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) DEFAULT NULL,
  `school_name` varchar(100) DEFAULT NULL COMMENT '学校名称',
  `school_nature` int(11) DEFAULT NULL COMMENT '学校性质',
  `school_gradation` int(11) DEFAULT NULL COMMENT '学校级次',
  `school_level` varchar(100) DEFAULT NULL COMMENT '学校等级',
  `distance` int(11) DEFAULT NULL COMMENT '距离',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2643 DEFAULT CHARSET=utf8 COMMENT='教育条件';

-- ----------------------------
-- Table structure for tb_basic_matching_environment
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_environment`;
CREATE TABLE `tb_basic_matching_environment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL COMMENT '类型（自然环境要素、人文环境要素、景观要素）',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `Influence_degree` int(11) DEFAULT NULL COMMENT '影响程度',
  `human_impact` varchar(255) DEFAULT NULL COMMENT '影响结论',
  `remark` varchar(1000) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2048 DEFAULT CHARSET=utf8 COMMENT='环境因素';

-- ----------------------------
-- Table structure for tb_basic_matching_finance
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_finance`;
CREATE TABLE `tb_basic_matching_finance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `nature` int(11) DEFAULT NULL COMMENT '金融机构性质',
  `service_content` varchar(200) DEFAULT NULL COMMENT '服务内容',
  `auto_service_content` varchar(200) DEFAULT NULL COMMENT '自动服务内容',
  `distance` varchar(255) DEFAULT NULL COMMENT '与金融机构距离',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3660 DEFAULT CHARSET=utf8 COMMENT='金融服务';

-- ----------------------------
-- Table structure for tb_basic_matching_leisure_place
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_leisure_place`;
CREATE TABLE `tb_basic_matching_leisure_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) NOT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型 (超市、餐饮、娱乐)',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `grade` int(11) DEFAULT NULL COMMENT '档次',
  `distance` int(11) DEFAULT NULL COMMENT '距离',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8823 DEFAULT CHARSET=utf8 COMMENT='休闲场所 包含-购物、娱乐、餐饮';

-- ----------------------------
-- Table structure for tb_basic_matching_material
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_material`;
CREATE TABLE `tb_basic_matching_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `scale` int(11) DEFAULT NULL COMMENT '规模',
  `distance` int(11) DEFAULT NULL COMMENT '距离',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='原料供应及销售条件';

-- ----------------------------
-- Table structure for tb_basic_matching_medical
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_medical`;
CREATE TABLE `tb_basic_matching_medical` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `estate_id` int(11) NOT NULL,
  `organization_name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `organization_level` int(11) DEFAULT NULL COMMENT '机构等级',
  `bed_number` int(11) DEFAULT NULL COMMENT '床位数',
  `distance` int(11) DEFAULT NULL COMMENT '距离',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2135 DEFAULT CHARSET=utf8 COMMENT='医养条件';

-- ----------------------------
-- Table structure for tb_basic_matching_traffic
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_matching_traffic`;
CREATE TABLE `tb_basic_matching_traffic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nature` int(24) DEFAULT NULL COMMENT '性质',
  `estate_id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL COMMENT '类型（地铁、公交、主干道、客运站等）',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `distance` int(11) DEFAULT NULL COMMENT '距离',
  `line_name` varchar(200) DEFAULT NULL COMMENT '线路名称',
  `the_line` varchar(200) DEFAULT NULL COMMENT '所在线路',
  `limit_speed` varchar(255) DEFAULT NULL COMMENT '限行速度',
  `limit_time` varchar(255) DEFAULT NULL COMMENT '限行时间',
  `limit_speial` int(11) DEFAULT NULL COMMENT '特殊限行',
  `flag` tinyint(1) DEFAULT NULL COMMENT '是否限行',
  `cost_standard` varchar(255) DEFAULT NULL COMMENT '收费标准',
  `position` int(11) DEFAULT NULL COMMENT '方位',
  `traffic_flow` int(11) DEFAULT NULL COMMENT '交通流量',
  `visitors_flowrate` int(11) DEFAULT NULL COMMENT '人流量',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9340 DEFAULT CHARSET=utf8 COMMENT='交通条件';

-- ----------------------------
-- Table structure for tb_basic_unit
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_unit`;
CREATE TABLE `tb_basic_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `apply_id` int(12) DEFAULT NULL,
  `building_id` int(11) NOT NULL DEFAULT '0',
  `unit_number` varchar(50) DEFAULT NULL COMMENT '单元编号',
  `elevator_household_ratio` varchar(50) DEFAULT NULL COMMENT '户梯比',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `huxing_explain` varchar(255) DEFAULT NULL COMMENT '户型说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1510 DEFAULT CHARSET=utf8 COMMENT='单元';

-- ----------------------------
-- Table structure for tb_basic_unit_decorate
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_unit_decorate`;
CREATE TABLE `tb_basic_unit_decorate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unit_id` int(11) DEFAULT NULL,
  `decoration_part` int(11) DEFAULT NULL COMMENT '装修部位',
  `decorating_material` int(11) DEFAULT NULL COMMENT '装修材料',
  `material_price_range` int(11) DEFAULT NULL COMMENT '材料价格区间',
  `construction_technology` int(11) DEFAULT NULL COMMENT '施工工艺',
  `material_grade` int(11) DEFAULT NULL COMMENT '材料档次',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=971 DEFAULT CHARSET=utf8 COMMENT='室内公共装修';

-- ----------------------------
-- Table structure for tb_basic_unit_elevator
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_unit_elevator`;
CREATE TABLE `tb_basic_unit_elevator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unit_id` int(11) DEFAULT NULL,
  `maintenance` int(11) DEFAULT NULL COMMENT '电梯维护情况',
  `type` int(11) DEFAULT NULL COMMENT '电梯类型',
  `brand` varchar(100) DEFAULT NULL COMMENT '电梯品牌',
  `number` int(11) DEFAULT NULL COMMENT '电梯数量',
  `quasi_load_number` int(11) DEFAULT NULL COMMENT '准载人数',
  `quasi_load_weight` varchar(50) DEFAULT NULL COMMENT '准载重量',
  `running_speed` varchar(50) DEFAULT NULL COMMENT '运行速度',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=762 DEFAULT CHARSET=utf8 COMMENT='配备电梯';

-- ----------------------------
-- Table structure for tb_basic_unit_huxing
-- ----------------------------
DROP TABLE IF EXISTS `tb_basic_unit_huxing`;
CREATE TABLE `tb_basic_unit_huxing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unit_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `area` decimal(10,2) DEFAULT NULL COMMENT '面积',
  `orientation` varchar(255) DEFAULT NULL COMMENT '朝向',
  `span_length` decimal(10,2) DEFAULT NULL COMMENT '跨长',
  `span_width` int(11) DEFAULT NULL COMMENT '跨宽',
  `span_number` decimal(10,2) DEFAULT NULL COMMENT '跨数',
  `bay` decimal(11,2) DEFAULT NULL COMMENT '开间',
  `deep` decimal(11,2) DEFAULT NULL COMMENT '进深',
  `house_category` varchar(255) DEFAULT NULL COMMENT '内容',
  `description` varchar(1000) DEFAULT NULL COMMENT '户型描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=844 DEFAULT CHARSET=utf8 COMMENT='户型';

-- ----------------------------
-- Table structure for tb_compile_report
-- ----------------------------
DROP TABLE IF EXISTS `tb_compile_report`;
CREATE TABLE `tb_compile_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 COMMENT='报告编写';

-- ----------------------------
-- Table structure for tb_compile_report_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_compile_report_detail`;
CREATE TABLE `tb_compile_report_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `report_analysis_type` int(11) DEFAULT NULL COMMENT '报告分析类型',
  `market_background_type` int(11) DEFAULT NULL COMMENT '市场背景分析明细类型',
  `report_analysis_name` varchar(50) DEFAULT NULL COMMENT '报告分析类型名称',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板内容',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `json_content` json DEFAULT NULL COMMENT '字段保存的信息',
  `bis_modifiable` bit(1) DEFAULT b'1' COMMENT '是否可编辑',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=673 DEFAULT CHARSET=utf8 COMMENT='报告编写明细';

-- ----------------------------
-- Table structure for tb_csr_borrower
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_borrower`;
CREATE TABLE `tb_csr_borrower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `borrower_id` varchar(64) DEFAULT NULL,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `project_id` int(11) DEFAULT NULL COMMENT '运行项目id',
  `group_id` int(11) DEFAULT NULL COMMENT '项目分组id',
  `first_level_branch` varchar(100) DEFAULT NULL COMMENT '一级分行',
  `second_level_branch` varchar(100) DEFAULT NULL COMMENT '二级分行',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `marital_status` varchar(50) DEFAULT NULL COMMENT '婚否',
  `education` varchar(100) DEFAULT NULL COMMENT '学历',
  `work_unit` varchar(100) DEFAULT NULL COMMENT '工作单位',
  `post` varchar(100) DEFAULT NULL COMMENT '职务',
  `domicile_place` varchar(255) DEFAULT NULL COMMENT '户籍地',
  `present_address` varchar(255) DEFAULT NULL COMMENT '现居住地址',
  `replace_record_id` int(11) DEFAULT NULL COMMENT '替换书签数据id',
  `bis_import` bit(1) DEFAULT NULL COMMENT '是否导入的数据',
  `bis_generate` bit(1) DEFAULT NULL COMMENT '是否已生成报告',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18623 DEFAULT CHARSET=utf8 COMMENT='借款人';

-- ----------------------------
-- Table structure for tb_csr_borrower_entering
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_borrower_entering`;
CREATE TABLE `tb_csr_borrower_entering` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `first_level_branch` varchar(100) DEFAULT NULL COMMENT '一级分行',
  `second_level_branch` varchar(100) DEFAULT NULL COMMENT '二级分行',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `marital_status` varchar(50) DEFAULT NULL COMMENT '婚否',
  `education` varchar(100) DEFAULT NULL COMMENT '学历',
  `work_unit` varchar(100) DEFAULT NULL COMMENT '工作单位',
  `post` varchar(100) DEFAULT NULL COMMENT '职务',
  `domicile_place` varchar(255) DEFAULT NULL COMMENT '户籍地',
  `present_address` varchar(255) DEFAULT NULL COMMENT '现居住地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='债权抵押物';

-- ----------------------------
-- Table structure for tb_csr_borrower_mortgage
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_borrower_mortgage`;
CREATE TABLE `tb_csr_borrower_mortgage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `contract_number` varchar(100) DEFAULT NULL COMMENT '合同号',
  `sign_data` varchar(100) DEFAULT NULL COMMENT '合同签订日期',
  `mortgagor` varchar(100) DEFAULT NULL COMMENT '抵押人',
  `mortgage_company` varchar(100) DEFAULT NULL COMMENT '委估对象id',
  `loan_amount` varchar(100) DEFAULT NULL COMMENT '贷款金额',
  `mortgage_address` varchar(255) DEFAULT NULL COMMENT '抵押物地址',
  `land_nature` varchar(100) DEFAULT NULL COMMENT '土地性质',
  `land_area` varchar(100) DEFAULT NULL COMMENT '土地面积',
  `house_nature` varchar(100) DEFAULT NULL COMMENT '房产性质',
  `area` varchar(100) DEFAULT NULL COMMENT '面积',
  `evaluation_value` varchar(100) DEFAULT NULL COMMENT '外评价值',
  `principal_balance` varchar(100) DEFAULT NULL COMMENT '本金余额',
  `mortgage_status` varchar(255) DEFAULT NULL COMMENT '抵押物现状',
  `bis_seal_up` varchar(10) DEFAULT NULL COMMENT '是否起诉查封',
  `excel_row_index` int(11) DEFAULT NULL COMMENT 'excel中行号',
  `bis_import` bit(1) DEFAULT NULL COMMENT '是否导入的数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10584 DEFAULT CHARSET=utf8 COMMENT='债权抵押物';

-- ----------------------------
-- Table structure for tb_csr_calculation
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_calculation`;
CREATE TABLE `tb_csr_calculation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `appraisal_method` varchar(100) DEFAULT NULL COMMENT '评估方法',
  `pawn_area` varchar(11) DEFAULT '0' COMMENT '数量（面积平方米）',
  `pawn_price` varchar(11) DEFAULT '0' COMMENT '市场单价',
  `pawn_amount` varchar(11) DEFAULT '0' COMMENT '市场价值判断（元）',
  `pawn_realization_coefficient` varchar(11) DEFAULT '0' COMMENT '变现系数',
  `pawn_realization` varchar(11) DEFAULT '0' COMMENT '变现价值（元）',
  `pawn_realization_amount` varchar(11) DEFAULT '0' COMMENT '抵（质）押物变现价值合计（元）',
  `dispose_auction_coefficient` varchar(11) DEFAULT '0' COMMENT '拍卖费系数',
  `dispose_auction` varchar(11) DEFAULT '0' COMMENT '拍卖费',
  `dispose_lawsuit_coefficient` varchar(11) DEFAULT '0' COMMENT '诉讼费系数',
  `dispose_lawsuit` varchar(11) DEFAULT '0' COMMENT '诉讼费',
  `dispose_execute_coefficient` varchar(11) DEFAULT '0' COMMENT '执行费系数',
  `dispose_execute` varchar(11) DEFAULT '0' COMMENT '执行费',
  `dispose_authenticate_cofficient` varchar(11) DEFAULT '0' COMMENT '司法鉴定费系数 ',
  `dispose_authenticate` varchar(11) DEFAULT '0' COMMENT '司法鉴定费',
  `dispoet_other_cofficient` varchar(11) DEFAULT '0' COMMENT '其他费系数',
  `dispoet_other` varchar(11) DEFAULT '0' COMMENT '其它费',
  `dispoet_amount` varchar(11) DEFAULT '0' COMMENT '处理相关费用小计',
  `tax_stamp_cofficient` varchar(11) DEFAULT '0' COMMENT '契税及印花税系数',
  `tax_stamp` varchar(11) DEFAULT '0' COMMENT '契税及印花税',
  `tax_addedvalue_cofficient` varchar(11) DEFAULT '0' COMMENT '增值税及附加系数',
  `tax_addedvalue` varchar(11) DEFAULT '0' COMMENT '增值税及附加',
  `tax_land_cofficient` varchar(11) DEFAULT '0' COMMENT '土地增值税系数',
  `tax_land` varchar(11) DEFAULT '0' COMMENT '土地增值税',
  `tax_other` varchar(11) DEFAULT '0' COMMENT '其它税金',
  `tax_amount` varchar(11) DEFAULT '0' COMMENT '税金小计',
  `cost_amount` varchar(11) DEFAULT '0' COMMENT '费用合计（处置费用+税金）',
  `pawn_realization_value_amount` varchar(11) DEFAULT '0' COMMENT '抵（质）押物变现净收入额',
  `recovery_income_ordinary` varchar(11) DEFAULT '0' COMMENT '预计追偿借款人一般净收入',
  `recovery_income_all` varchar(11) DEFAULT '0' COMMENT '预计追偿全部保证人净收入',
  `recovery_income_third_party` varchar(11) DEFAULT '0' COMMENT '预计可追偿第三方净收入',
  `recovery_incom_amount` varchar(11) DEFAULT '0' COMMENT '预计可追偿净收入合计',
  `recovery_bad_loans` varchar(11) DEFAULT '0' COMMENT '不良贷款预计受偿收回金额合计',
  `recovery_limit` varchar(100) DEFAULT NULL COMMENT '预计收回时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT='债权测算';

-- ----------------------------
-- Table structure for tb_csr_contract
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_contract`;
CREATE TABLE `tb_csr_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `contract_number` varchar(100) DEFAULT NULL COMMENT '借款合同号',
  `loan_time` varchar(50) DEFAULT NULL COMMENT '贷款发放时间',
  `amount` varchar(100) DEFAULT NULL COMMENT '金额',
  `variety` varchar(100) DEFAULT NULL COMMENT '品种',
  `term` varchar(100) DEFAULT NULL COMMENT '期限',
  `guarantee_method` varchar(100) DEFAULT NULL COMMENT '担保方式',
  `bis_import` bit(1) DEFAULT NULL COMMENT '是否导入的数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18649 DEFAULT CHARSET=utf8 COMMENT='债权合同';

-- ----------------------------
-- Table structure for tb_csr_field_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_field_relation`;
CREATE TABLE `tb_csr_field_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(50) DEFAULT NULL COMMENT '显示名称',
  `table_name` varchar(100) DEFAULT NULL COMMENT '表名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '字段名称',
  `another_name` varchar(100) DEFAULT NULL COMMENT '别名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='债权字段关联';

-- ----------------------------
-- Table structure for tb_csr_guarantor
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_guarantor`;
CREATE TABLE `tb_csr_guarantor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `name` varchar(50) DEFAULT NULL COMMENT '保证人姓名',
  `bis_import` bit(1) DEFAULT NULL COMMENT '是否导入的数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='债权担保人';

-- ----------------------------
-- Table structure for tb_csr_invalid_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_invalid_rule`;
CREATE TABLE `tb_csr_invalid_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `column_name` varchar(50) DEFAULT NULL COMMENT '列名称',
  `column_value` varchar(100) DEFAULT NULL COMMENT '列的值',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COMMENT='债权过滤规则';

-- ----------------------------
-- Table structure for tb_csr_litigation
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_litigation`;
CREATE TABLE `tb_csr_litigation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `litigation_preservation` varchar(200) DEFAULT NULL COMMENT '诉讼保全',
  `litigation_preservation_info` varchar(1000) DEFAULT NULL COMMENT '诉讼保全情况',
  `bis_import` bit(1) DEFAULT NULL COMMENT '是否导入的数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3247 DEFAULT CHARSET=utf8 COMMENT='债权诉讼';

-- ----------------------------
-- Table structure for tb_csr_principal_interest
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_principal_interest`;
CREATE TABLE `tb_csr_principal_interest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `borrower_id` varchar(64) DEFAULT NULL COMMENT '借款人id',
  `analysis_datum_date` varchar(50) DEFAULT NULL COMMENT '分析基准日',
  `principal` varchar(100) DEFAULT NULL COMMENT '本金',
  `interest` varchar(100) DEFAULT NULL COMMENT '利息',
  `principal_interest_total` varchar(100) DEFAULT NULL COMMENT '本金利息合计',
  `bis_import` bit(1) DEFAULT NULL COMMENT '是否导入的数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18642 DEFAULT CHARSET=utf8 COMMENT='债权本金利息';

-- ----------------------------
-- Table structure for tb_csr_project_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_project_info`;
CREATE TABLE `tb_csr_project_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_class_id` int(11) DEFAULT NULL COMMENT '项目类型id',
  `project_type_id` int(11) DEFAULT NULL COMMENT '项目类别id',
  `project_category_id` int(11) DEFAULT NULL COMMENT '项目范围id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `customer_type` int(11) DEFAULT NULL COMMENT '客户类型',
  `entrustment_unit_id` int(11) DEFAULT NULL COMMENT '委托单位id',
  `entrustment_unit_name` varchar(100) DEFAULT NULL COMMENT '委托单位名称',
  `entrust_purpose` int(11) DEFAULT NULL COMMENT '委估目的id',
  `valuation_date` date DEFAULT NULL COMMENT '评估基准日',
  `distribution_user` varchar(50) DEFAULT NULL COMMENT '项目分配人',
  `remark` varchar(500) DEFAULT NULL COMMENT '项目说明',
  `start_row_number` int(11) DEFAULT NULL COMMENT '取行序号',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `project_status` varchar(20) DEFAULT NULL COMMENT '项目状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 COMMENT='债权项目信息';

-- ----------------------------
-- Table structure for tb_csr_project_info_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_csr_project_info_group`;
CREATE TABLE `tb_csr_project_info_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csr_project_id` int(11) DEFAULT NULL COMMENT '债权项目id',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `project_manager` varchar(50) DEFAULT NULL COMMENT '项目经理',
  `project_member` varchar(2000) DEFAULT NULL COMMENT '项目成员',
  `number` varchar(50) DEFAULT NULL COMMENT '文号',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `valuation_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评估基准日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8 COMMENT='债权项目分组';

-- ----------------------------
-- Table structure for tb_data_allocation_correction_coefficient_volume_ratio
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_allocation_correction_coefficient_volume_ratio`;
CREATE TABLE `tb_data_allocation_correction_coefficient_volume_ratio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '县',
  `purpose` int(11) DEFAULT NULL COMMENT '用途',
  `evaluation_date` datetime DEFAULT NULL COMMENT '估价期日',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='容积率修正系数配置';

-- ----------------------------
-- Table structure for tb_data_allocation_correction_coefficient_volume_ratio_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_allocation_correction_coefficient_volume_ratio_detail`;
CREATE TABLE `tb_data_allocation_correction_coefficient_volume_ratio_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allocation_volume_ratio_id` int(11) DEFAULT NULL COMMENT '主表id',
  `plot_ratio` decimal(11,3) DEFAULT NULL COMMENT '容积率',
  `correction_factor` decimal(11,3) DEFAULT NULL COMMENT '修正系数',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='容积率修正系数配置 详细';

-- ----------------------------
-- Table structure for tb_data_assets_appraisal_dic
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_assets_appraisal_dic`;
CREATE TABLE `tb_data_assets_appraisal_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0' COMMENT '上级编号',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `setting` bit(1) DEFAULT b'1' COMMENT '是否是配置项',
  `edit_file_enable` bit(1) DEFAULT b'1' COMMENT '是编辑文件',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `field_name_list` varchar(1000) DEFAULT NULL COMMENT '字段名称集合',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `key_value` varchar(2000) DEFAULT NULL COMMENT 'key_value方式存储json数据',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='资产评估 文件和字段配置';

-- ----------------------------
-- Table structure for tb_data_best_use_description
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_best_use_description`;
CREATE TABLE `tb_data_best_use_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `bis_enable` bit(1) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='最佳利用描述';

-- ----------------------------
-- Table structure for tb_data_block
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_block`;
CREATE TABLE `tb_data_block` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `position` varchar(500) DEFAULT NULL COMMENT '方位',
  `regional_nature` varchar(200) DEFAULT NULL COMMENT '区域性质',
  `remark` varchar(2000) DEFAULT NULL COMMENT '区域描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='版块';

-- ----------------------------
-- Table structure for tb_data_builder
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_builder`;
CREATE TABLE `tb_data_builder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `company_nature` int(11) DEFAULT NULL COMMENT '公司性质',
  `qualification_grade` int(11) DEFAULT NULL COMMENT '资质等级',
  `social_prestige` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='建造商';

-- ----------------------------
-- Table structure for tb_data_building_install_cost
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_building_install_cost`;
CREATE TABLE `tb_data_building_install_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `publish_time` datetime DEFAULT NULL COMMENT '发布日期',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='建筑安装工程费';

-- ----------------------------
-- Table structure for tb_data_building_new_rate
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_building_new_rate`;
CREATE TABLE `tb_data_building_new_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_structure` varchar(255) DEFAULT NULL COMMENT '建筑结构',
  `building_use` int(11) DEFAULT NULL COMMENT '用途类型',
  `durable_life` int(11) DEFAULT NULL COMMENT '耐用年限',
  `residual_value` varchar(255) DEFAULT NULL COMMENT '残值率',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COMMENT='建筑成新率';

-- ----------------------------
-- Table structure for tb_data_contract_calculate_tool
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_contract_calculate_tool`;
CREATE TABLE `tb_data_contract_calculate_tool` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `reference_number` varchar(255) DEFAULT NULL COMMENT '文号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='合同计算工具配置';

-- ----------------------------
-- Table structure for tb_data_customer_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_customer_field`;
CREATE TABLE `tb_data_customer_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `business_type` varchar(200) DEFAULT NULL COMMENT '业务类型',
  `assess_type` varchar(200) DEFAULT NULL COMMENT '评估类型',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8 COMMENT='客户立项字段';

-- ----------------------------
-- Table structure for tb_data_damaged_degree
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_damaged_degree`;
CREATE TABLE `tb_data_damaged_degree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `standard_score` decimal(11,2) DEFAULT NULL COMMENT '标准分',
  `weight` decimal(11,2) DEFAULT NULL COMMENT '权重',
  `intact` varchar(1000) DEFAULT NULL COMMENT '完好标准',
  `basically_intact` varchar(1000) DEFAULT NULL COMMENT '基本完好标准',
  `general_damage` varchar(1000) DEFAULT NULL COMMENT '一般损坏标准',
  `serious_damage` varchar(1000) DEFAULT NULL COMMENT '严重损坏标准',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1733 DEFAULT CHARSET=utf8 COMMENT='房屋完损度';

-- ----------------------------
-- Table structure for tb_data_developer
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_developer`;
CREATE TABLE `tb_data_developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `company_nature` int(11) DEFAULT NULL COMMENT '公司性质',
  `social_prestige` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='开发商';

-- ----------------------------
-- Table structure for tb_data_dispatch_register
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_dispatch_register`;
CREATE TABLE `tb_data_dispatch_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dispatch_date` varchar(50) DEFAULT NULL COMMENT '发文日期',
  `dispatch_number` varchar(50) DEFAULT NULL COMMENT '发文号',
  `business_type` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `entrust_purpose` varchar(50) DEFAULT NULL COMMENT '委托目的',
  `project_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `client_company` varchar(50) DEFAULT NULL COMMENT '客户公司',
  `entrust_unit` varchar(50) DEFAULT NULL COMMENT '委托单位',
  `assess_area` varchar(50) DEFAULT NULL COMMENT '评估面积',
  `assess_amount` varchar(50) DEFAULT NULL COMMENT '评估额(元)',
  `send_number` varchar(50) DEFAULT NULL COMMENT '外送份数',
  `remain_number` varchar(50) DEFAULT NULL COMMENT '留存份数',
  `operator` varchar(50) DEFAULT NULL COMMENT '经办人',
  `approver` varchar(50) DEFAULT NULL COMMENT '审批人',
  `deposit_person` varchar(50) DEFAULT NULL COMMENT '交存人',
  `redact_person` varchar(50) DEFAULT NULL COMMENT '编存人',
  `pigeonhole_date` varchar(50) DEFAULT NULL COMMENT '归档日期',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='项目发文登记';

-- ----------------------------
-- Table structure for tb_data_early_warning
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_early_warning`;
CREATE TABLE `tb_data_early_warning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entrust_purpose` int(11) DEFAULT NULL COMMENT '委托目的',
  `type` int(11) DEFAULT NULL COMMENT '预计类型',
  `near_day` int(11) DEFAULT NULL COMMENT '接近天数',
  `color` varchar(255) DEFAULT NULL COMMENT '预计颜色',
  `mode` int(11) DEFAULT NULL COMMENT '预计方式',
  `object` varchar(255) DEFAULT NULL COMMENT '预计对象',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='预警设置';

-- ----------------------------
-- Table structure for tb_data_evaluation_basis
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_evaluation_basis`;
CREATE TABLE `tb_data_evaluation_basis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '思路名称',
  `method` varchar(100) DEFAULT NULL COMMENT '适用方法',
  `entrustment_purpose` varchar(100) DEFAULT NULL COMMENT '委托目的',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `pub_date` datetime DEFAULT NULL COMMENT '发布日期',
  `field_name` varchar(255) DEFAULT NULL,
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `bis_modifiable` bit(1) DEFAULT b'0' COMMENT '是否可修改',
  `bis_enable` bit(1) DEFAULT b'0' COMMENT '是否启用',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='评估依据';

-- ----------------------------
-- Table structure for tb_data_evaluation_hypothesis
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_evaluation_hypothesis`;
CREATE TABLE `tb_data_evaluation_hypothesis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '思路名称',
  `method` varchar(100) DEFAULT NULL COMMENT '适用方法',
  `entrustment_purpose` varchar(100) DEFAULT NULL COMMENT '委托目的',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `pub_date` datetime DEFAULT NULL COMMENT '发布日期',
  `field_name` varchar(255) DEFAULT NULL,
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `bis_modifiable` bit(1) DEFAULT b'0' COMMENT '是否可修改',
  `bis_enable` bit(1) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='评估假设';

-- ----------------------------
-- Table structure for tb_data_evaluation_method
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_evaluation_method`;
CREATE TABLE `tb_data_evaluation_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `method` int(11) DEFAULT NULL COMMENT '评估方法',
  `applicable_reason` varchar(2000) DEFAULT NULL COMMENT '适用原因模板',
  `not_applicable_reason` varchar(2000) DEFAULT NULL COMMENT '不适用原因模板',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `bis_enable` bit(1) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='评估方法';

-- ----------------------------
-- Table structure for tb_data_evaluation_principle
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_evaluation_principle`;
CREATE TABLE `tb_data_evaluation_principle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '思路名称',
  `method` varchar(100) DEFAULT NULL COMMENT '适用方法',
  `entrustment_purpose` varchar(100) DEFAULT NULL COMMENT '委托目的',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `pub_date` datetime DEFAULT NULL COMMENT '发布日期',
  `field_name` varchar(255) DEFAULT NULL COMMENT '字段key值',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `bis_modifiable` bit(1) DEFAULT b'0' COMMENT '是否可修改',
  `bis_enable` bit(1) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='评估原则';

-- ----------------------------
-- Table structure for tb_data_evaluation_thinking
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_evaluation_thinking`;
CREATE TABLE `tb_data_evaluation_thinking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '思路名称',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `method` varchar(100) DEFAULT NULL COMMENT '适用方法',
  `template_content` varchar(2000) DEFAULT NULL COMMENT '适用原因模板',
  `bis_enable` bit(1) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='评估思路';

-- ----------------------------
-- Table structure for tb_data_examine_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_examine_task`;
CREATE TABLE `tb_data_examine_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT '0',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `apply_url` varchar(255) DEFAULT NULL COMMENT '数据管理视图路径',
  `detail_url` varchar(255) DEFAULT NULL COMMENT '数据详情视图路径',
  `bis_must` bit(1) DEFAULT b'0' COMMENT '是否必须项',
  `bis_repeat` bit(1) DEFAULT b'0' COMMENT '是否重复表',
  `bis_upload` bit(1) DEFAULT b'0' COMMENT '是否需要上传附件',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='调查任务配置表';

-- ----------------------------
-- Table structure for tb_data_his_right_info_publicity
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_his_right_info_publicity`;
CREATE TABLE `tb_data_his_right_info_publicity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `content` varchar(500) DEFAULT NULL COMMENT '他权信息公示',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8 COMMENT='土地级别';

-- ----------------------------
-- Table structure for tb_data_house_price_index
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_house_price_index`;
CREATE TABLE `tb_data_house_price_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purpose` int(11) DEFAULT NULL COMMENT '用途',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `evaluation_date` datetime DEFAULT NULL COMMENT '估价期日',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '区县',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='房价指数';

-- ----------------------------
-- Table structure for tb_data_house_price_index_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_house_price_index_detail`;
CREATE TABLE `tb_data_house_price_index_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_price_id` int(11) DEFAULT NULL COMMENT '主表id',
  `index_number` decimal(11,3) DEFAULT NULL COMMENT '指数',
  `start_date` datetime DEFAULT NULL COMMENT '开始月份',
  `end_date` datetime DEFAULT NULL COMMENT '结束月份',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='房价指数 详情';

-- ----------------------------
-- Table structure for tb_data_infrastructure
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_infrastructure`;
CREATE TABLE `tb_data_infrastructure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '区县',
  `dispatch_unit` varchar(255) DEFAULT NULL COMMENT '发文单位',
  `number` varchar(255) DEFAULT NULL COMMENT '文号',
  `project_type` varchar(255) DEFAULT NULL COMMENT '项目类别',
  `start_date` datetime DEFAULT NULL COMMENT '执行开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '执行结束日期',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='基础设施';

-- ----------------------------
-- Table structure for tb_data_infrastructure_children
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_infrastructure_children`;
CREATE TABLE `tb_data_infrastructure_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称 tb_data_infrastructure_matching_cost，tb_data_infrastructure_dev_tax，tb_data_infrastructure_matching_cost',
  `number` decimal(18,2) DEFAULT NULL COMMENT '金额 tb_data_infrastructure_matching_cost，tb_data_infrastructure_dev_tax，tb_data_infrastructure_matching_cost',
  `tax` decimal(18,2) DEFAULT NULL COMMENT '税费 tb_data_infrastructure_dev_tax',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `pid` int(11) DEFAULT NULL COMMENT '关联主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='配套设施费用';

-- ----------------------------
-- Table structure for tb_data_land_approximation_method_setting
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_land_approximation_method_setting`;
CREATE TABLE `tb_data_land_approximation_method_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL COMMENT '省，直辖市',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '县',
  `category` int(10) DEFAULT NULL COMMENT '类别',
  `symbol` varchar(255) DEFAULT NULL COMMENT '文号',
  `amount_money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='土地逼近法补偿配置';

-- ----------------------------
-- Table structure for tb_data_land_detail_achievement
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_land_detail_achievement`;
CREATE TABLE `tb_data_land_detail_achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level_detail_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL COMMENT '类别',
  `grade` int(11) DEFAULT NULL COMMENT '等级（优，良，劣）',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `achievement` decimal(10,2) DEFAULT NULL COMMENT '分值',
  `reamark` varchar(500) DEFAULT NULL COMMENT '说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4115 DEFAULT CHARSET=utf8 COMMENT='土地级别详情从表';

-- ----------------------------
-- Table structure for tb_data_land_level
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_land_level`;
CREATE TABLE `tb_data_land_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `valuation_date` datetime DEFAULT NULL COMMENT '评估基准日',
  `execution_time` datetime DEFAULT NULL COMMENT '执行时间',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `word_symbol` varchar(255) DEFAULT NULL COMMENT '文号',
  `land_definition` varchar(1000) DEFAULT NULL COMMENT '基准地价定义',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=333 DEFAULT CHARSET=utf8 COMMENT='土地级别';

-- ----------------------------
-- Table structure for tb_data_land_level_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_land_level_detail`;
CREATE TABLE `tb_data_land_level_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `land_level_id` int(11) DEFAULT NULL,
  `classify` varchar(100) DEFAULT NULL COMMENT '大类',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `category` varchar(100) DEFAULT NULL COMMENT '类别',
  `level_range` varchar(2000) DEFAULT NULL COMMENT '级别范围',
  `main_street` varchar(4000) DEFAULT NULL COMMENT '主要街道',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `land_acquisition_proportion` varchar(255) DEFAULT NULL COMMENT '征地比例',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=432 DEFAULT CHARSET=utf8 COMMENT='土地级别';

-- ----------------------------
-- Table structure for tb_data_locale_survey_picture
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_locale_survey_picture`;
CREATE TABLE `tb_data_locale_survey_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `file_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `certify_part` int(11) DEFAULT NULL COMMENT '对应查勘部位',
  `certify_part_category` int(11) DEFAULT NULL COMMENT '附件类别',
  `bis_enable` bit(1) DEFAULT NULL COMMENT '是否上报告',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='现场查勘图片模板';

-- ----------------------------
-- Table structure for tb_data_method_formula
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_method_formula`;
CREATE TABLE `tb_data_method_formula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method` int(11) DEFAULT NULL COMMENT '方法',
  `formula` varchar(2000) DEFAULT NULL COMMENT '公式内容',
  `relevant_parameter` varchar(2000) DEFAULT NULL COMMENT '相关参数',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='评估方法公式';

-- ----------------------------
-- Table structure for tb_data_number_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_number_rule`;
CREATE TABLE `tb_data_number_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_type` int(11) DEFAULT NULL COMMENT '报告类型',
  `field_name` varchar(100) DEFAULT NULL COMMENT 'key值',
  `group_name` varchar(255) DEFAULT NULL COMMENT '分组',
  `prefix` varchar(100) DEFAULT NULL COMMENT '前缀',
  `number_rule` varchar(100) DEFAULT NULL COMMENT '文号规则',
  `figures` int(11) DEFAULT NULL COMMENT '位数',
  `start_year` int(11) DEFAULT NULL COMMENT '起始年份',
  `start_number` int(11) DEFAULT NULL COMMENT '起始编号',
  `same_report_type` int(11) DEFAULT NULL COMMENT '同号的报告类型',
  `bis_enable` bit(1) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='文号规则';

-- ----------------------------
-- Table structure for tb_data_position
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_position`;
CREATE TABLE `tb_data_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `remark` varchar(2000) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='方位维护';

-- ----------------------------
-- Table structure for tb_data_price_timepoint_description
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_price_timepoint_description`;
CREATE TABLE `tb_data_price_timepoint_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `type` int(13) DEFAULT NULL COMMENT '类型',
  `category` int(14) DEFAULT NULL COMMENT '类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='价值时点说明';

-- ----------------------------
-- Table structure for tb_data_property
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_property`;
CREATE TABLE `tb_data_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `company_nature` int(11) DEFAULT NULL COMMENT '公司性质',
  `social_prestige` int(11) DEFAULT NULL COMMENT '社会信誉',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='物业';

-- ----------------------------
-- Table structure for tb_data_property_service_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_property_service_item`;
CREATE TABLE `tb_data_property_service_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `master_id` int(11) DEFAULT NULL,
  `service_type` int(11) DEFAULT NULL COMMENT '服务类型',
  `service_content` int(11) DEFAULT NULL COMMENT '服务内容',
  `service_time` varchar(255) DEFAULT NULL COMMENT '服务时间',
  `grade_evaluation` int(11) DEFAULT NULL COMMENT '等级评价',
  `charges_notes` varchar(255) DEFAULT NULL COMMENT '收费标准',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='物业服务内容';

-- ----------------------------
-- Table structure for tb_data_qualification
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_qualification`;
CREATE TABLE `tb_data_qualification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `qualification_type` varchar(100) DEFAULT NULL COMMENT '名称',
  `user_account` varchar(2000) DEFAULT NULL COMMENT '公司性质',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='资质';

-- ----------------------------
-- Table structure for tb_data_report_analysis
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_report_analysis`;
CREATE TABLE `tb_data_report_analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `market_background_type` int(11) DEFAULT NULL COMMENT '市场背景描述类型',
  `report_analysis_type` int(11) DEFAULT NULL COMMENT '报告分析类型',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板',
  `entrustment_purpose` varchar(100) DEFAULT NULL COMMENT '委托目的',
  `set_use` int(11) DEFAULT NULL COMMENT '设定用途',
  `rel_year` int(11) DEFAULT NULL COMMENT '年份',
  `block_id` int(11) DEFAULT NULL COMMENT '版块',
  `block_name` varchar(100) DEFAULT NULL COMMENT '板块名称',
  `field_name` varchar(255) DEFAULT NULL,
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `bis_modifiable` bit(1) DEFAULT b'0' COMMENT '是否可修改',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='报告分析';

-- ----------------------------
-- Table structure for tb_data_report_template_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_report_template_item`;
CREATE TABLE `tb_data_report_template_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT NULL COMMENT '关联主表id',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `template` varchar(3000) DEFAULT NULL COMMENT '模板',
  `field_name` varchar(100) DEFAULT NULL COMMENT '字段key',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8 COMMENT='报告模板对应的子项内容';

-- ----------------------------
-- Table structure for tb_data_set_use_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_set_use_field`;
CREATE TABLE `tb_data_set_use_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) DEFAULT NULL COMMENT '使用该数据的字段名称',
  `type` varchar(100) DEFAULT NULL COMMENT '类型(house、land)',
  `set_use` int(11) DEFAULT NULL COMMENT '设定用途',
  `level` varchar(255) DEFAULT NULL COMMENT '层次',
  `bis_primary_key` bit(1) DEFAULT NULL COMMENT '是否为主键字段',
  `bis_price` bit(1) DEFAULT NULL COMMENT '是否为价格字段',
  `bis_only_view` bit(1) DEFAULT NULL COMMENT '仅用于显示',
  `bis_enable` bit(1) DEFAULT NULL COMMENT '是否启用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `can_shrink` bit(1) DEFAULT NULL COMMENT '是否可收缩',
  `bis_show` bit(1) DEFAULT b'1' COMMENT '是否显示',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=443 DEFAULT CHARSET=utf8 COMMENT='市场比较法设定用途对应字段';

-- ----------------------------
-- Table structure for tb_data_stage_weight_proportion
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_stage_weight_proportion`;
CREATE TABLE `tb_data_stage_weight_proportion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entrust_purpose` int(11) DEFAULT NULL COMMENT '委托目的',
  `stage` int(11) DEFAULT NULL COMMENT '阶段',
  `proportion` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COMMENT='阶段权重配置';

-- ----------------------------
-- Table structure for tb_data_tax_rate_allocation
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_tax_rate_allocation`;
CREATE TABLE `tb_data_tax_rate_allocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '区县',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `ex_explain` varchar(255) DEFAULT NULL COMMENT '说明',
  `tax_rate` decimal(18,4) DEFAULT NULL COMMENT '税率',
  `amount` decimal(13,2) DEFAULT NULL COMMENT '金額',
  `bis_national_unity` bit(1) DEFAULT b'0' COMMENT '是否全国统一',
  `calculate_base` varchar(255) DEFAULT NULL COMMENT '计算基数',
  `calculation_formula` varchar(255) DEFAULT NULL COMMENT '计算公式',
  `taxes_burden` int(11) DEFAULT NULL COMMENT '税费负担方',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `field_name` varchar(255) DEFAULT NULL COMMENT '字段名称',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='税率配置';

-- ----------------------------
-- Table structure for tb_data_value_definition
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_value_definition`;
CREATE TABLE `tb_data_value_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '思路名称',
  `entrustment_purpose` varchar(100) DEFAULT NULL COMMENT '委托目的',
  `value_type` varchar(100) DEFAULT NULL COMMENT '价值类型',
  `property_scope` varchar(255) DEFAULT NULL COMMENT '评估(财产)范围',
  `scope_include` varchar(255) DEFAULT NULL COMMENT '范围包含',
  `scope_not_include` varchar(255) DEFAULT NULL COMMENT '范围不包含',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板',
  `type` varchar(100) DEFAULT NULL COMMENT '项目类型',
  `category` varchar(100) DEFAULT NULL COMMENT '项目类别',
  `bis_modifiable` bit(1) DEFAULT b'0' COMMENT '是否可修改',
  `bis_enable` bit(1) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='价值定义';

-- ----------------------------
-- Table structure for tb_declare_apply
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_apply`;
CREATE TABLE `tb_declare_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划详细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `apply_type` varchar(100) DEFAULT NULL COMMENT '申请类型',
  `client` varchar(255) DEFAULT NULL COMMENT '委托单位',
  `date_limit` datetime DEFAULT NULL COMMENT '完成时限',
  `status` varchar(255) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `name` varchar(225) DEFAULT NULL COMMENT '名称',
  `assess_area` decimal(20,2) DEFAULT NULL COMMENT '评估面积',
  `assess_money` decimal(20,2) DEFAULT NULL COMMENT '评估金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8 COMMENT='申报信息';

-- ----------------------------
-- Table structure for tb_declare_building_construction_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_building_construction_permit`;
CREATE TABLE `tb_declare_building_construction_permit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `certificate_number` varchar(50) DEFAULT NULL COMMENT '证书编号',
  `issuing_organ` varchar(255) DEFAULT NULL COMMENT '发证机关',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `build_unit` varchar(255) DEFAULT NULL COMMENT '建设单位（个人）',
  `name` varchar(255) DEFAULT NULL COMMENT '工程名称',
  `build_address` varchar(255) DEFAULT NULL COMMENT '建设地址',
  `scale_construction` varchar(255) DEFAULT NULL COMMENT '建设规模',
  `reconnaissance_unit` varchar(255) DEFAULT NULL COMMENT '勘察单位',
  `design_unit` varchar(255) DEFAULT NULL COMMENT '设计单位',
  `construction_unit` varchar(255) DEFAULT NULL COMMENT '施工单位',
  `construction_control_unit` varchar(255) DEFAULT NULL COMMENT '监理单位',
  `reconnaissance_unit_person` varchar(255) DEFAULT NULL COMMENT '勘察单位项目负责人',
  `design_unit_person` varchar(255) DEFAULT NULL COMMENT '设计单位项目负责人',
  `construction_unit_person` varchar(255) DEFAULT NULL COMMENT '施工单位项目负责人',
  `chief_engineer_construction_inspection` varchar(255) DEFAULT NULL COMMENT '总监理工程师',
  `contract_period` datetime DEFAULT NULL COMMENT '合同日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8 COMMENT='在建工程 建筑工程施工许可证';

-- ----------------------------
-- Table structure for tb_declare_building_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_building_permit`;
CREATE TABLE `tb_declare_building_permit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `certificate_number` varchar(90) DEFAULT NULL COMMENT '证书编号',
  `issuing_organ` varchar(255) DEFAULT NULL COMMENT '发证机关',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `unit` varchar(255) DEFAULT NULL COMMENT '建设单位（个人）',
  `name` varchar(255) DEFAULT NULL COMMENT '建设项目名称',
  `location` varchar(255) DEFAULT NULL COMMENT '建设位置',
  `scale_construction` varchar(255) DEFAULT NULL COMMENT '建设规模',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在建工程 建设工程规划许可证';

-- ----------------------------
-- Table structure for tb_declare_build_economic_indicators
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_build_economic_indicators`;
CREATE TABLE `tb_declare_build_economic_indicators` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '主表id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `custom_key` varchar(100) DEFAULT NULL COMMENT '定义的key值',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `content` varchar(200) DEFAULT NULL COMMENT '内容项',
  `child_data` json DEFAULT NULL COMMENT '子项数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 COMMENT='在建工程 规划经济指标';

-- ----------------------------
-- Table structure for tb_declare_build_economic_indicators_center
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_build_economic_indicators_center`;
CREATE TABLE `tb_declare_build_economic_indicators_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(12) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=622 DEFAULT CHARSET=utf8 COMMENT='在建工程 规划经济指标 中间表';

-- ----------------------------
-- Table structure for tb_declare_build_engineering
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_build_engineering`;
CREATE TABLE `tb_declare_build_engineering` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(15) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `province` varchar(11) DEFAULT NULL COMMENT '省',
  `city` varchar(11) DEFAULT NULL COMMENT '市',
  `district` varchar(11) DEFAULT NULL COMMENT '区县',
  `occupancy_unit` varchar(255) DEFAULT NULL COMMENT '占有单位',
  `be_located` varchar(255) DEFAULT NULL COMMENT '坐落',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `structure` varchar(255) DEFAULT NULL COMMENT '结构',
  `build_area` decimal(25,0) DEFAULT NULL COMMENT '建筑面积',
  `start_date` datetime DEFAULT NULL COMMENT '开工日期',
  `expected_completion_date` datetime DEFAULT NULL COMMENT '预开工日期',
  `speed_progress` varchar(200) DEFAULT NULL COMMENT '形象进度',
  `payment_ratio` varchar(255) DEFAULT NULL COMMENT '付款比例',
  `book_net_value` varchar(255) DEFAULT NULL COMMENT '帐面净值',
  `book_value` varchar(255) DEFAULT NULL COMMENT '帐面价值',
  `declarer` varchar(255) DEFAULT NULL COMMENT '申报人',
  `declaration_date` datetime DEFAULT NULL COMMENT '申报日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `enable` varchar(255) DEFAULT NULL COMMENT '是否启用(不属于此实体信息的字段专用来区分某些数据的)',
  `declare_type` varchar(255) DEFAULT NULL COMMENT '申报证书类型',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8 COMMENT='在建工程 土建';

-- ----------------------------
-- Table structure for tb_declare_build_engineering_and_equipment_center
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_build_engineering_and_equipment_center`;
CREATE TABLE `tb_declare_build_engineering_and_equipment_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(12) DEFAULT NULL,
  `building_construction_permit_id` int(11) DEFAULT NULL COMMENT '建筑工程施工许可证',
  `building_permit_id` int(11) DEFAULT NULL COMMENT '建设工程规划许可证',
  `land_use_permit_id` int(11) DEFAULT NULL COMMENT '建设用地规划许可证',
  `pre_sale_permit_id` int(11) DEFAULT NULL COMMENT '商品房预售许可证',
  `land_id` int(11) DEFAULT NULL COMMENT '土地证',
  `house_id` int(11) DEFAULT NULL COMMENT '房产证',
  `indicator_id` int(11) DEFAULT NULL COMMENT '经济指标',
  `real_estate_id` int(11) DEFAULT NULL COMMENT '不动产证',
  `build_engineering_id` int(11) DEFAULT NULL COMMENT '在建工程 土建',
  `build_equipment_id` int(11) DEFAULT NULL COMMENT '在建工程 设备安装',
  `type` varchar(255) DEFAULT NULL COMMENT '类型(土建,设备安装,房产证)',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=936 DEFAULT CHARSET=utf8 COMMENT='在建工程 中间表';

-- ----------------------------
-- Table structure for tb_declare_build_equipment_install
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_build_equipment_install`;
CREATE TABLE `tb_declare_build_equipment_install` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(15) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `province` varchar(11) DEFAULT NULL COMMENT '省',
  `city` varchar(11) DEFAULT NULL COMMENT '市',
  `district` varchar(11) DEFAULT NULL COMMENT '区县',
  `occupancy_unit` varchar(255) DEFAULT NULL COMMENT '占有单位',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `be_located` varchar(255) DEFAULT NULL COMMENT '坐落',
  `specification_model` varchar(255) DEFAULT NULL COMMENT '规格型号',
  `manufacturer` varchar(255) DEFAULT NULL COMMENT '生产厂家',
  `measurement_unit` varchar(255) DEFAULT NULL COMMENT '计量单位',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `expected_completion_date` datetime DEFAULT NULL COMMENT '预开工日期',
  `start_date` datetime DEFAULT NULL COMMENT '开工日期',
  `book_equipment_fee` varchar(255) DEFAULT NULL COMMENT '帐面设备费',
  `book_capital_cost` varchar(200) DEFAULT NULL COMMENT '账面资金成本',
  `book_installation_fee` varchar(255) DEFAULT NULL COMMENT '账面安装费',
  `other` varchar(255) DEFAULT NULL COMMENT '其它',
  `declaration_date` datetime DEFAULT NULL COMMENT '申报日期',
  `declarer` varchar(255) DEFAULT NULL COMMENT '申报人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `enable` varchar(255) DEFAULT NULL COMMENT '是否启用(不属于此实体信息的字段专用来区分某些数据的)',
  `declare_type` varchar(255) DEFAULT NULL COMMENT '申报证书类型',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8 COMMENT='在建工程 设备安装';

-- ----------------------------
-- Table structure for tb_declare_economic_indicators_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_economic_indicators_content`;
CREATE TABLE `tb_declare_economic_indicators_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indicators_head_id` int(12) DEFAULT NULL,
  `plan_details_id` int(12) DEFAULT NULL COMMENT '项目计划详情id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `custom_key` varchar(255) DEFAULT NULL COMMENT '定义的key值',
  `salability_number` varchar(200) DEFAULT NULL COMMENT '可出售面积（㎡或车位个数）',
  `assess_salability_number` varchar(255) DEFAULT NULL COMMENT '评估出售面积（㎡或车位个数）',
  `plan_index` varchar(255) DEFAULT NULL COMMENT '规划指标',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `child_data` json DEFAULT NULL COMMENT '子项',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1158 DEFAULT CHARSET=utf8 COMMENT='估价对象技术经济指标';

-- ----------------------------
-- Table structure for tb_declare_economic_indicators_head
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_economic_indicators_head`;
CREATE TABLE `tb_declare_economic_indicators_head` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(12) DEFAULT NULL COMMENT '项目计划详情id',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `cert_use` varchar(255) DEFAULT NULL COMMENT '土地用途',
  `grade` varchar(255) DEFAULT NULL COMMENT '项目档次（楼盘',
  `building_structure` varchar(255) DEFAULT NULL COMMENT '建筑结构',
  `building_height_limit` decimal(22,2) DEFAULT NULL COMMENT '建筑限高（m）',
  `building_base_coverage` decimal(22,2) DEFAULT NULL COMMENT '建筑基底占地面积（㎡)',
  `set_volumetric_rate` varchar(255) DEFAULT NULL COMMENT '设定容积率',
  `volumetric_rate` varchar(255) DEFAULT NULL COMMENT '容积率',
  `building_density` varchar(255) DEFAULT NULL COMMENT '建筑密度',
  `green_space_rate` varchar(255) DEFAULT NULL COMMENT '绿地率',
  `plan_date` datetime DEFAULT NULL COMMENT '规划日期',
  `plan_total_build_area` decimal(22,2) DEFAULT NULL COMMENT '规划总建筑面积（㎡）',
  `plan_net_construction_land_area` decimal(22,2) DEFAULT NULL COMMENT '规划建设净用地面积（㎡）',
  `assess_total_build_area` decimal(22,2) DEFAULT NULL COMMENT '评估总建筑面积',
  `assess_use_land_area` decimal(22,2) DEFAULT NULL COMMENT '评估用地面积（㎡',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=651 DEFAULT CHARSET=utf8 COMMENT='估价对象技术经济指标（最佳开发条件）';

-- ----------------------------
-- Table structure for tb_declare_land_use_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_land_use_permit`;
CREATE TABLE `tb_declare_land_use_permit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `certificate_number` varchar(90) DEFAULT NULL COMMENT '证书编号',
  `issuing_organ` varchar(255) DEFAULT NULL COMMENT '发证机关',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `unit` varchar(255) DEFAULT NULL COMMENT '用地单位',
  `name` varchar(255) DEFAULT NULL COMMENT '用地项目名称',
  `location` varchar(255) DEFAULT NULL COMMENT '用地位置',
  `nature` varchar(255) DEFAULT NULL COMMENT '用地性质',
  `area` decimal(25,0) DEFAULT NULL COMMENT '用地面积',
  `scale_construction` varchar(255) DEFAULT NULL COMMENT '建设规模',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在建工程 建设用地规划许可证';

-- ----------------------------
-- Table structure for tb_declare_pre_sale_permit
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_pre_sale_permit`;
CREATE TABLE `tb_declare_pre_sale_permit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `certificate_number` varchar(50) DEFAULT NULL COMMENT '证书编号',
  `issuing_organ` varchar(255) DEFAULT NULL COMMENT '发证机关',
  `sales_unit` varchar(255) DEFAULT NULL COMMENT '售房单位',
  `legal_representative` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `be_located` varchar(255) DEFAULT NULL COMMENT '项目坐落',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `pre_sale_area` decimal(25,0) DEFAULT NULL COMMENT '预售面积',
  `pre_sale_scope` varchar(255) DEFAULT NULL COMMENT '预售范围',
  `housing_use` varchar(255) DEFAULT NULL COMMENT '房屋用途',
  `building_structure` varchar(255) DEFAULT NULL COMMENT '建筑结构',
  `pre_sale_supervision_information` varchar(255) DEFAULT NULL COMMENT '预售款监管信息',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `mortgage_situation` varchar(255) DEFAULT NULL COMMENT '在建工程抵押情况',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在建工程 商品房预售许可证';

-- ----------------------------
-- Table structure for tb_declare_realty_house_cert
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_realty_house_cert`;
CREATE TABLE `tb_declare_realty_house_cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(15) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL,
  `cert_name` varchar(255) DEFAULT NULL COMMENT '房产权证号',
  `province` varchar(11) DEFAULT NULL COMMENT '省',
  `city` varchar(11) DEFAULT NULL COMMENT '市',
  `district` varchar(11) DEFAULT NULL COMMENT '区县',
  `type` varchar(200) DEFAULT NULL COMMENT '房产证类型',
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `ownership` varchar(50) DEFAULT NULL COMMENT '房屋所有权人',
  `public_situation` int(11) DEFAULT NULL COMMENT '共有情况',
  `be_located` varchar(100) DEFAULT NULL COMMENT '坐落',
  `street_number` varchar(50) DEFAULT NULL COMMENT '街道号',
  `attached_number` varchar(50) DEFAULT NULL COMMENT '附号',
  `building_number` varchar(50) DEFAULT NULL COMMENT '幢号',
  `unit` varchar(50) DEFAULT NULL COMMENT '单元',
  `floor` varchar(50) DEFAULT NULL COMMENT '楼层',
  `room_number` varchar(50) DEFAULT NULL COMMENT '房号',
  `registration_time` datetime DEFAULT NULL COMMENT '登记时间',
  `nature` int(11) DEFAULT NULL COMMENT '房屋性质',
  `floor_count` varchar(50) DEFAULT NULL COMMENT '总层数',
  `cert_use` int(11) DEFAULT NULL COMMENT '证载用途',
  `housing_structure` varchar(255) DEFAULT NULL COMMENT '房屋结构',
  `floor_area` varchar(255) DEFAULT NULL COMMENT '建筑面积',
  `location` varchar(255) DEFAULT NULL COMMENT '所在地',
  `inner_area` decimal(25,2) DEFAULT NULL COMMENT '套内面积',
  `evidence_area` decimal(25,2) DEFAULT NULL COMMENT '证载面积',
  `other` varchar(255) DEFAULT NULL COMMENT '其它',
  `land_number` varchar(255) DEFAULT NULL COMMENT '土地证号',
  `land_acquisition` varchar(255) DEFAULT NULL COMMENT '土地取得方式',
  `use_start_date` datetime DEFAULT NULL COMMENT '土地使用开始日期',
  `use_end_date` datetime DEFAULT NULL COMMENT '土地使用结束日期',
  `public_area` decimal(18,2) DEFAULT NULL COMMENT '公摊面积',
  `other_note` varchar(255) DEFAULT NULL COMMENT '附记其它',
  `registration_authority` varchar(50) DEFAULT NULL COMMENT '登记机关',
  `land_registration_date` datetime DEFAULT NULL COMMENT '登记日期土地',
  `registration_date` datetime DEFAULT NULL COMMENT '登记日期',
  `declare_type` varchar(255) DEFAULT NULL COMMENT '申报证书类型',
  `enable` varchar(255) DEFAULT NULL COMMENT '是否启用(不属于此实体信息的字段专用来区分某些数据的)',
  `bis_record` bit(1) DEFAULT b'0' COMMENT '是否写入到申报记录中',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `ground_num` varchar(255) DEFAULT NULL COMMENT '丘地号',
  `apportionment_area` decimal(20,2) DEFAULT NULL COMMENT '分摊面积',
  `cert_use_category` int(11) DEFAULT NULL COMMENT '用途类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=606 DEFAULT CHARSET=utf8 COMMENT='房产房产证';

-- ----------------------------
-- Table structure for tb_declare_realty_land_cert
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_realty_land_cert`;
CREATE TABLE `tb_declare_realty_land_cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(15) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `land_cert_get_question` int(11) DEFAULT NULL COMMENT '权证有无',
  `land_cert_name` varchar(255) DEFAULT NULL COMMENT '土地权证号',
  `province` varchar(11) DEFAULT NULL COMMENT '省',
  `city` varchar(11) DEFAULT NULL COMMENT '市',
  `district` varchar(11) DEFAULT NULL COMMENT '区县',
  `land_right_type` int(11) DEFAULT NULL COMMENT '权利类型 （国用 集用）',
  `location` varchar(255) DEFAULT NULL COMMENT '所在地',
  `year` varchar(50) DEFAULT NULL COMMENT '年份',
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `ownership` varchar(50) DEFAULT NULL COMMENT '土地使用权人',
  `public_situation` int(11) DEFAULT NULL COMMENT '共有情况',
  `be_located` varchar(100) DEFAULT NULL COMMENT '坐落',
  `street_number` varchar(50) DEFAULT NULL COMMENT '街道号',
  `attached_number` varchar(50) DEFAULT NULL COMMENT '附号',
  `building_number` varchar(50) DEFAULT NULL COMMENT '幢号',
  `unit` varchar(50) DEFAULT NULL COMMENT '单元',
  `floor` varchar(50) DEFAULT NULL COMMENT '楼层',
  `room_number` varchar(50) DEFAULT NULL COMMENT '房号',
  `land_number` varchar(50) DEFAULT NULL COMMENT '地号',
  `cert_use` int(11) DEFAULT NULL COMMENT '证载用途（住宅用地,工矿仓储用地,商业用地,公共管理与公共服务用地,交通运输用地）',
  `housing_structure` varchar(255) DEFAULT NULL COMMENT '结构',
  `graph_number` varchar(50) DEFAULT NULL COMMENT '图号',
  `land_right_nature` int(11) DEFAULT NULL COMMENT '权利性质(划拨和出证)',
  `acquisition_price` decimal(20,2) DEFAULT NULL COMMENT '取得价格',
  `termination_date` datetime DEFAULT NULL COMMENT '终止日期',
  `use_right_area` decimal(20,2) DEFAULT NULL COMMENT '使用权面积',
  `acreage` decimal(20,2) DEFAULT NULL COMMENT '独用面积',
  `apportionment_area` decimal(20,2) DEFAULT NULL COMMENT '分摊面积',
  `memo` varchar(255) DEFAULT NULL COMMENT '记事',
  `registration_authority` varchar(50) DEFAULT NULL COMMENT '登记机关',
  `registration_date` datetime DEFAULT NULL COMMENT '登记日期',
  `approval_name` varchar(255) DEFAULT NULL COMMENT '批文名称',
  `approval_time` datetime DEFAULT NULL COMMENT '批文时间',
  `approval_mechanism` varchar(255) DEFAULT NULL COMMENT '批文机关',
  `declare_type` varchar(255) DEFAULT NULL COMMENT '申报证书类型',
  `enable` varchar(255) DEFAULT NULL COMMENT '是否启用(不属于此实体信息的字段专用来区分某些数据的)',
  `bis_record` bit(1) DEFAULT b'0' COMMENT '是否写入到申报记录中',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `cert_use_category` int(11) DEFAULT NULL COMMENT '证载用途类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=485 DEFAULT CHARSET=utf8 COMMENT='房产 土地证';

-- ----------------------------
-- Table structure for tb_declare_realty_real_estate_cert
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_realty_real_estate_cert`;
CREATE TABLE `tb_declare_realty_real_estate_cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '主表id',
  `province` varchar(11) DEFAULT NULL COMMENT '省',
  `city` varchar(11) DEFAULT NULL COMMENT '市',
  `district` varchar(11) DEFAULT NULL COMMENT '区县',
  `land_cert_get_question` int(11) DEFAULT NULL COMMENT '权证有无',
  `cert_name` varchar(255) DEFAULT NULL COMMENT '权证号或者批文文号',
  `location` varchar(255) DEFAULT NULL COMMENT '所在地',
  `be_located` varchar(255) DEFAULT NULL COMMENT '座落',
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `year` varchar(50) DEFAULT NULL COMMENT '年份',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `ownership` varchar(50) DEFAULT NULL COMMENT '房屋所有权人',
  `public_situation` int(11) DEFAULT NULL COMMENT '共有情况',
  `street_number` varchar(50) DEFAULT NULL COMMENT '街道号',
  `attached_number` varchar(50) DEFAULT NULL COMMENT '附号',
  `building_number` varchar(50) DEFAULT NULL COMMENT '幢号',
  `unit` varchar(50) DEFAULT NULL COMMENT '单元',
  `floor` varchar(50) DEFAULT NULL COMMENT '楼层',
  `room_number` varchar(50) DEFAULT NULL COMMENT '房号',
  `registration_time` datetime DEFAULT NULL COMMENT '登记时间',
  `nature` int(11) DEFAULT NULL COMMENT '房屋性质',
  `house_cert_use` int(11) DEFAULT NULL COMMENT '房屋用途',
  `housing_structure` varchar(255) DEFAULT NULL COMMENT '房屋结构',
  `floor_count` varchar(255) DEFAULT NULL COMMENT '总层数',
  `floor_area` decimal(18,2) DEFAULT NULL COMMENT '建筑面积',
  `evidence_area` decimal(16,2) DEFAULT NULL COMMENT '证载面积',
  `inner_area` decimal(18,2) DEFAULT NULL COMMENT '套内面积',
  `other` varchar(255) DEFAULT NULL COMMENT '其它',
  `land_number` varchar(50) DEFAULT NULL COMMENT '地号',
  `graph_number` varchar(50) DEFAULT NULL COMMENT '图号',
  `land_acquisition` varchar(255) DEFAULT NULL COMMENT '土地取得方式',
  `use_start_date` datetime DEFAULT NULL COMMENT '土地使用开始日期',
  `use_end_date` datetime DEFAULT NULL COMMENT '土地使用结束日期',
  `public_area` decimal(18,2) DEFAULT NULL COMMENT '公摊面积',
  `other_note` varchar(255) DEFAULT NULL COMMENT '附记其它',
  `registration_authority` varchar(50) DEFAULT NULL COMMENT '登记机关',
  `registration_date` datetime DEFAULT NULL COMMENT '登记日期',
  `land_right_nature` int(11) DEFAULT NULL COMMENT '权利性质(划拨和出证)',
  `land_right_type` int(11) DEFAULT NULL COMMENT '土地权利类型',
  `acquisition_price` decimal(10,2) DEFAULT NULL COMMENT '取得价格',
  `land_cert_use` int(11) DEFAULT NULL COMMENT '土地用途',
  `termination_date` datetime DEFAULT NULL COMMENT '终止日期',
  `memo` varchar(255) DEFAULT NULL COMMENT '记事',
  `apportionment_area` decimal(20,2) DEFAULT NULL COMMENT '分摊面积',
  `acreage` decimal(20,2) DEFAULT NULL COMMENT '独用面积',
  `use_right_area` decimal(20,2) DEFAULT NULL COMMENT '使用权面积',
  `enable` varchar(255) DEFAULT NULL COMMENT '是否启用(不属于此实体信息的字段专用来区分某些数据的)',
  `declare_type` varchar(255) DEFAULT NULL COMMENT '申报证书类型',
  `approval_mechanism` varchar(255) DEFAULT NULL COMMENT '批文机关',
  `approval_time` datetime DEFAULT NULL COMMENT '批文时间',
  `approval_name` varchar(255) DEFAULT NULL COMMENT '批文名称',
  `real_estate_unit_number` varchar(200) DEFAULT NULL COMMENT '不动产单元号',
  `bis_record` bit(1) DEFAULT b'0' COMMENT '是否写入到申报记录中',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `land_apportion_area` decimal(20,2) DEFAULT NULL COMMENT '土地分摊面积',
  `land_cert_use_category` int(11) DEFAULT NULL COMMENT '土地用途类型',
  `house_cert_use_category` int(11) DEFAULT NULL COMMENT '房屋用途类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=715 DEFAULT CHARSET=utf8 COMMENT='房产 不动产';

-- ----------------------------
-- Table structure for tb_declare_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_record`;
CREATE TABLE `tb_declare_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `data_table_name` varchar(50) DEFAULT NULL COMMENT '申报表类型',
  `data_table_id` int(11) DEFAULT NULL COMMENT '数据来源表id',
  `data_from_type` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `type` varchar(255) DEFAULT NULL COMMENT '类型(纯土地、土地加房产)',
  `has_cert` bit(1) DEFAULT b'1' COMMENT '是否有证',
  `name` varchar(200) DEFAULT NULL COMMENT '权证名称',
  `ownership` varchar(100) DEFAULT NULL COMMENT '所有权人',
  `seat` varchar(255) DEFAULT NULL COMMENT '坐落',
  `street_number` varchar(100) DEFAULT NULL COMMENT '街道号',
  `attached_number` varchar(100) DEFAULT NULL COMMENT '附号',
  `building_number` varchar(100) DEFAULT NULL COMMENT '栋号',
  `unit` varchar(100) DEFAULT NULL COMMENT '单元',
  `floor` varchar(100) DEFAULT NULL COMMENT '楼层',
  `room_number` varchar(100) DEFAULT NULL COMMENT '房号',
  `cert_use` varchar(100) DEFAULT NULL COMMENT '证载用途',
  `practical_use` varchar(100) DEFAULT NULL COMMENT '实际用途',
  `land_cert_use` varchar(100) DEFAULT NULL COMMENT '土地证载用途',
  `land_practical_use` varchar(100) DEFAULT NULL COMMENT '土地实际用途',
  `public_situation` varchar(100) DEFAULT NULL COMMENT '共有情况',
  `floor_area` decimal(18,2) DEFAULT NULL COMMENT '证载面积',
  `practical_area` decimal(18,2) DEFAULT NULL COMMENT '实际面积',
  `nature` varchar(255) DEFAULT NULL COMMENT '房屋性质',
  `land_right_type` varchar(255) DEFAULT NULL COMMENT '土地权利类型',
  `land_right_nature` varchar(255) DEFAULT NULL COMMENT '权利性质',
  `land_use_right_area` decimal(11,2) DEFAULT NULL COMMENT '土地使用权面积',
  `housing_structure` varchar(255) DEFAULT NULL COMMENT '房屋结构',
  `area_group_id` int(11) DEFAULT NULL COMMENT '区域分组id',
  `house_use_end_date` datetime DEFAULT NULL COMMENT '房产有效使用时间',
  `land_use_end_date` datetime DEFAULT NULL COMMENT '土地有效使用时间',
  `inventory_content_key` varchar(255) DEFAULT NULL COMMENT '清查内容项key值',
  `registration_date` datetime DEFAULT NULL COMMENT '登记日期',
  `price` decimal(18,2) DEFAULT NULL COMMENT '评估单价',
  `bis_part_in` bit(1) DEFAULT b'1' COMMENT '是否参与报告出具',
  `bis_generate` bit(1) DEFAULT b'0' COMMENT '是否生成了清查与查勘任务项',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=799 DEFAULT CHARSET=utf8 COMMENT='申报记录';

-- ----------------------------
-- Table structure for tb_declare_record_extend
-- ----------------------------
DROP TABLE IF EXISTS `tb_declare_record_extend`;
CREATE TABLE `tb_declare_record_extend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `declare_id` int(11) DEFAULT NULL COMMENT '项目id',
  `registration_authority` varchar(255) DEFAULT NULL COMMENT '登记机关',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=809 DEFAULT CHARSET=utf8 COMMENT='申报记录';

-- ----------------------------
-- Table structure for tb_document_opinion
-- ----------------------------
DROP TABLE IF EXISTS `tb_document_opinion`;
CREATE TABLE `tb_document_opinion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(100) DEFAULT NULL COMMENT '发文文号',
  `title` varchar(100) DEFAULT NULL COMMENT '发文标题',
  `contract_type` int(11) DEFAULT NULL COMMENT '发文类型',
  `extend_conten` json DEFAULT NULL COMMENT '明细内容',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  `uuid` varchar(64) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `area_group_id` int(11) DEFAULT NULL COMMENT '区域分组id',
  `report_type_id` int(11) DEFAULT NULL COMMENT '报告类型id',
  `generation_id` int(11) DEFAULT NULL COMMENT '生成报告id',
  `suggestion` varchar(255) DEFAULT NULL COMMENT '处理意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_document_send
-- ----------------------------
DROP TABLE IF EXISTS `tb_document_send`;
CREATE TABLE `tb_document_send` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(100) DEFAULT NULL COMMENT '发文文号',
  `title` varchar(100) DEFAULT NULL COMMENT '发文标题',
  `contract_type` int(11) DEFAULT NULL COMMENT '发文类型',
  `extend_conten` json DEFAULT NULL COMMENT '明细内容',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  `uuid` varchar(64) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_document_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_document_template`;
CREATE TABLE `tb_document_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `prefix` varchar(10) DEFAULT NULL COMMENT '文号规则',
  `template_type` int(11) DEFAULT NULL COMMENT '模板类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_document_template_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `tb_document_template_bookmark`;
CREATE TABLE `tb_document_template_bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` int(11) DEFAULT NULL COMMENT '模板id',
  `name` varchar(255) DEFAULT NULL COMMENT '书签的名称',
  `remarks` varchar(100) DEFAULT NULL COMMENT '书签说明',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `field_id` int(11) DEFAULT NULL COMMENT '字段编号',
  `field_name` varchar(100) DEFAULT NULL,
  `formater` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_document_template_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_document_template_field`;
CREATE TABLE `tb_document_template_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` int(11) DEFAULT NULL COMMENT '模板id',
  `name` varchar(255) DEFAULT NULL COMMENT '字段的名称',
  `display_name` varchar(255) DEFAULT NULL COMMENT '字段显示名称',
  `data_source` varchar(500) DEFAULT NULL COMMENT '数据源',
  `field_type` int(11) DEFAULT NULL COMMENT '字段的类型',
  `field_length` int(11) DEFAULT NULL COMMENT '字段长度',
  `default_value` varchar(255) DEFAULT NULL COMMENT '默认值',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `bis_required` bit(1) DEFAULT b'0' COMMENT '是否必填',
  `bis_show` bit(1) DEFAULT b'0' COMMENT '是否显示',
  `bis_enable` bit(1) DEFAULT b'0' COMMENT '是否启用',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_funi_block
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_block`;
CREATE TABLE `tb_funi_block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sfbh` int(11) DEFAULT NULL COMMENT '省份编号',
  `csbh` int(11) DEFAULT NULL COMMENT '城市编号',
  `qybh` int(11) DEFAULT NULL COMMENT '区域编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_funi_developers
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_developers`;
CREATE TABLE `tb_funi_developers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developers_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1936 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_funi_houses
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses`;
CREATE TABLE `tb_funi_houses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lpmc` varchar(255) DEFAULT NULL COMMENT '楼盘名称',
  `jzmj` varchar(100) DEFAULT NULL COMMENT '建筑面积',
  `zdmj` varchar(100) DEFAULT NULL COMMENT '占地面积',
  `rjl` varchar(100) DEFAULT NULL COMMENT '容积率',
  `lhl` varchar(100) DEFAULT NULL COMMENT '绿化率',
  `cwxx` varchar(100) DEFAULT NULL COMMENT '车位信息',
  `lpdz` varchar(100) DEFAULT NULL COMMENT '楼盘地址',
  `sldz` varchar(100) DEFAULT NULL COMMENT '销售地址',
  `kfsbh` int(11) DEFAULT NULL,
  `kfs` varchar(255) DEFAULT NULL COMMENT '开发商',
  `xsxkz` varchar(1000) DEFAULT NULL COMMENT '销售许可证',
  `lpjs` varchar(5000) DEFAULT NULL COMMENT '楼盘介绍',
  `jd` varchar(100) DEFAULT NULL COMMENT '经度',
  `wd` varchar(50) DEFAULT NULL COMMENT '纬度',
  `funiweb` varchar(100) DEFAULT NULL COMMENT '透明网详情地址',
  `qybh` int(11) DEFAULT NULL COMMENT '区域编号',
  `qy` varchar(255) DEFAULT NULL COMMENT '区域',
  `sqbh` int(11) DEFAULT NULL COMMENT '商圈编号',
  `sq` varchar(255) DEFAULT NULL COMMENT '商圈',
  `sfbh` int(11) DEFAULT NULL COMMENT '省编号',
  `sf` varchar(255) DEFAULT NULL COMMENT '省份',
  `csbh` int(11) DEFAULT NULL COMMENT '城市编号',
  `csmc` varchar(255) DEFAULT NULL COMMENT '城市名称',
  `bis_edit` bit(1) DEFAULT b'0' COMMENT '是否已获取数据',
  `xmdz` varchar(255) DEFAULT NULL COMMENT '楼盘地址',
  `lptp` varchar(500) DEFAULT '' COMMENT '项目图片',
  `lpjj` varchar(15) DEFAULT '0.00' COMMENT '楼盘均价',
  `complete` bit(1) DEFAULT b'0' COMMENT '已被分派任务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2862 DEFAULT CHARSET=utf8 COMMENT='楼盘';

-- ----------------------------
-- Table structure for tb_funi_houses_build
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses_build`;
CREATE TABLE `tb_funi_houses_build` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lpbh` int(11) DEFAULT NULL COMMENT '楼盘编号',
  `ld` varchar(100) DEFAULT NULL COMMENT '楼栋',
  `cs` varchar(10) DEFAULT NULL COMMENT '层数',
  `cg` varchar(10) DEFAULT NULL COMMENT '层高',
  `jzgd` varchar(10) DEFAULT NULL COMMENT '建筑高度',
  `wzqk` varchar(500) DEFAULT NULL COMMENT '外装情况',
  `ggzxqk` varchar(500) DEFAULT NULL COMMENT '公共部分装修情况',
  `pbdt` varchar(100) DEFAULT NULL COMMENT '配备电梯',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='楼幢';

-- ----------------------------
-- Table structure for tb_funi_houses_build_unit
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses_build_unit`;
CREATE TABLE `tb_funi_houses_build_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lpbh` int(11) DEFAULT NULL COMMENT '楼盘编号',
  `ldbh` int(11) DEFAULT NULL COMMENT '楼栋编号',
  `dymc` varchar(100) DEFAULT NULL COMMENT '单元名称',
  `thb` varchar(10) DEFAULT NULL COMMENT '梯户比',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='楼幢单元';

-- ----------------------------
-- Table structure for tb_funi_houses_mating
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses_mating`;
CREATE TABLE `tb_funi_houses_mating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lpbh` int(11) DEFAULT NULL COMMENT '楼盘编号',
  `gj` varchar(100) DEFAULT NULL COMMENT '公交',
  `dt` varchar(100) DEFAULT NULL COMMENT '地铁',
  `yey` varchar(100) DEFAULT NULL COMMENT '幼儿园',
  `xx` varchar(100) DEFAULT NULL COMMENT '小学',
  `zx` varchar(100) DEFAULT NULL COMMENT '中学',
  `dx` varchar(100) DEFAULT NULL COMMENT '大学',
  `yy` varchar(100) DEFAULT NULL COMMENT '医院',
  `yd` varchar(100) DEFAULT NULL COMMENT '药店',
  `sc` varchar(100) DEFAULT NULL COMMENT '商场',
  `cs` varchar(100) DEFAULT NULL COMMENT '超市',
  `csc` varchar(100) DEFAULT NULL COMMENT '菜市场',
  `yh` varchar(100) DEFAULT NULL COMMENT '银行',
  `atm` varchar(100) DEFAULT NULL COMMENT 'ATM机',
  `ct` varchar(100) DEFAULT NULL COMMENT '餐厅',
  `kfg` varchar(100) DEFAULT NULL COMMENT '咖啡馆',
  `gy` varchar(100) DEFAULT NULL COMMENT '公园',
  `dyy` varchar(100) DEFAULT NULL COMMENT '电影院',
  `jsf` varchar(100) DEFAULT NULL COMMENT '健身房',
  `tyg` varchar(100) DEFAULT '' COMMENT '体育馆',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='楼盘配套';

-- ----------------------------
-- Table structure for tb_funi_houses_property
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses_property`;
CREATE TABLE `tb_funi_houses_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lpbh` int(11) DEFAULT NULL COMMENT '楼盘名称',
  `jzmj` varchar(100) DEFAULT NULL COMMENT '建筑面积',
  `zdmj` varchar(100) DEFAULT NULL COMMENT '占地面积',
  `glfs` varchar(100) DEFAULT NULL COMMENT '供暖方式',
  `gsfs` varchar(100) DEFAULT NULL COMMENT '供水方式',
  `tx` varchar(100) DEFAULT NULL COMMENT '通讯',
  `wl` varchar(100) DEFAULT NULL COMMENT '网络',
  `wylx` varchar(100) DEFAULT NULL COMMENT '物业类型',
  `jzlb` varchar(100) DEFAULT NULL COMMENT '建筑类别',
  `zxqk` varchar(100) DEFAULT NULL COMMENT '装修情况',
  `tdsynx` varchar(100) DEFAULT NULL COMMENT '土地使用年限 ',
  `zhs` varchar(100) DEFAULT NULL COMMENT '总户数',
  `wyf` varchar(100) DEFAULT NULL COMMENT '物业费',
  `wygsbh` int(11) DEFAULT NULL COMMENT '物业公司编号',
  `wygs` varchar(255) DEFAULT NULL COMMENT '物业公司',
  `yxdl` varchar(100) DEFAULT NULL COMMENT '营销代理',
  `kpsj` varchar(100) DEFAULT NULL COMMENT '开盘时间',
  `jfsj` varchar(100) DEFAULT NULL COMMENT '交房时间',
  `jd` varchar(100) DEFAULT NULL COMMENT '经度',
  `wd` varchar(100) DEFAULT NULL COMMENT '纬度',
  `hxqj` varchar(100) DEFAULT NULL COMMENT '户型区间',
  `kts` varchar(10) DEFAULT NULL COMMENT '客梯数',
  `hts` varchar(10) DEFAULT NULL COMMENT '货梯数',
  `cg` varchar(50) DEFAULT NULL COMMENT '层高',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3931 DEFAULT CHARSET=utf8 COMMENT='物业类型';

-- ----------------------------
-- Table structure for tb_funi_houses_siting
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses_siting`;
CREATE TABLE `tb_funi_houses_siting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dybh` int(11) DEFAULT NULL COMMENT '单元编号',
  `lc` varchar(10) DEFAULT NULL COMMENT '楼层',
  `mph` varchar(10) DEFAULT NULL COMMENT '门牌号',
  `fxbh` int(11) DEFAULT NULL COMMENT '户型编号',
  `mj` varchar(10) DEFAULT NULL COMMENT '面积 ',
  `cx` varchar(50) DEFAULT NULL COMMENT '朝向',
  `ch` varchar(100) DEFAULT NULL COMMENT '窗户',
  `hxt` varchar(100) DEFAULT NULL COMMENT '户型图',
  `tf` varchar(100) DEFAULT NULL COMMENT '通风',
  `gz` varchar(100) DEFAULT NULL COMMENT '光照',
  `zxqk` varchar(100) DEFAULT NULL COMMENT '装修情况',
  `cf` varchar(100) DEFAULT NULL COMMENT '厨房',
  `cs` varchar(100) DEFAULT NULL COMMENT '厕所',
  `flggqy` varchar(100) DEFAULT NULL COMMENT '房内公用区域',
  `fj` varchar(100) DEFAULT NULL COMMENT '房间',
  `gpsqk` varchar(100) DEFAULT NULL COMMENT '供排水情况',
  `fwsjyt` varchar(100) DEFAULT NULL COMMENT '房屋实际用途',
  `dl` varchar(100) DEFAULT NULL COMMENT '电力',
  `tx` varchar(100) DEFAULT NULL COMMENT '通讯',
  `wl` varchar(100) DEFAULT NULL COMMENT '网络',
  `jtznxt` varchar(100) DEFAULT NULL COMMENT '家庭智能系统',
  `zjycjysj` varchar(50) DEFAULT NULL COMMENT '最近一次交易时间',
  `zjycjyjg` varchar(15) DEFAULT NULL COMMENT '最近一次交易价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源信息';

-- ----------------------------
-- Table structure for tb_funi_houses_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_houses_type`;
CREATE TABLE `tb_funi_houses_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lpbh` int(11) DEFAULT '0' COMMENT '楼盘编号',
  `ldbh` int(11) DEFAULT '0' COMMENT '楼栋编号',
  `dybh` int(11) DEFAULT '0' COMMENT '单元编号',
  `hx` varchar(100) DEFAULT NULL COMMENT '户型',
  `mj` varchar(100) DEFAULT NULL COMMENT '面积 ',
  `cx` varchar(50) DEFAULT NULL COMMENT '朝向',
  `ch` varchar(100) DEFAULT NULL COMMENT '窗户',
  `hxt` varchar(100) DEFAULT NULL COMMENT '户型图',
  `tf` varchar(100) DEFAULT NULL COMMENT '通风',
  `gz` varchar(100) DEFAULT NULL COMMENT '光照',
  `zxqk` varchar(100) DEFAULT NULL COMMENT '装修情况',
  `cf` varchar(100) DEFAULT NULL COMMENT '厨房',
  `cs` varchar(100) DEFAULT NULL COMMENT '厕所',
  `flggqy` varchar(100) DEFAULT NULL COMMENT '房内公用区域',
  `fj` varchar(100) DEFAULT NULL COMMENT '房间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24958 DEFAULT CHARSET=utf8 COMMENT='户型表';

-- ----------------------------
-- Table structure for tb_funi_property_management
-- ----------------------------
DROP TABLE IF EXISTS `tb_funi_property_management`;
CREATE TABLE `tb_funi_property_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `property_management_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=867 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_generate_report_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_generate_report_info`;
CREATE TABLE `tb_generate_report_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `project_plan_id` int(11) DEFAULT NULL,
  `qualification_type` varchar(255) DEFAULT NULL COMMENT '资质类型',
  `investigations_start_date` datetime DEFAULT NULL COMMENT '现场查勘开始日期',
  `investigations_end_date` datetime DEFAULT NULL COMMENT '现场查勘结束日期',
  `report_issuance_date` datetime DEFAULT NULL COMMENT '报告出具日期',
  `home_work_end_time` datetime DEFAULT NULL COMMENT '作业结束时间',
  `real_estate_appraiser` varchar(200) DEFAULT NULL COMMENT '房地产估价师',
  `area_group_id` int(11) DEFAULT NULL COMMENT '区域分组id',
  `process_ins_id` varchar(100) DEFAULT NULL COMMENT '流程id',
  `query_code` varchar(150) DEFAULT NULL COMMENT '查询码',
  `record_date` datetime DEFAULT NULL COMMENT '备案日期',
  `record_no` varchar(100) DEFAULT NULL COMMENT '备案号',
  `assess_category` int(12) DEFAULT NULL COMMENT '评估类别',
  `status` varchar(100) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8 COMMENT='报告生成';

-- ----------------------------
-- Table structure for tb_initiate_consignor
-- ----------------------------
DROP TABLE IF EXISTS `tb_initiate_consignor`;
CREATE TABLE `tb_initiate_consignor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `cs_type` int(11) DEFAULT NULL COMMENT '委托人类型:如1表示法人,0表示自然人',
  `cs_entrustment_unit` varchar(100) DEFAULT NULL COMMENT '委托单位',
  `cs_legal_representative` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `cs_sociology_code` varchar(255) DEFAULT NULL COMMENT '社会统一信用代码',
  `cs_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `cs_scope_operation` varchar(1000) DEFAULT NULL COMMENT '经营范围',
  `cs_unit_properties` varchar(255) DEFAULT NULL COMMENT '单位性质',
  `cs_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `cs_spare_field` varchar(255) DEFAULT NULL COMMENT '备用字段',
  `cs_idcard` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=624 DEFAULT CHARSET=utf8 COMMENT='立项委托人';

-- ----------------------------
-- Table structure for tb_initiate_contacts
-- ----------------------------
DROP TABLE IF EXISTS `tb_initiate_contacts`;
CREATE TABLE `tb_initiate_contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_type` int(11) DEFAULT NULL COMMENT '类型如:委托人,资产占有人,报告使用单位',
  `c_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `c_dept` varchar(255) DEFAULT NULL COMMENT '部门',
  `c_phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `c_email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `c_spare_field` varchar(255) DEFAULT NULL COMMENT '备用字段',
  `c_pid` int(11) DEFAULT NULL COMMENT '主表(关联的主表,如:委托人id)',
  `crm_id` varchar(255) DEFAULT NULL COMMENT 'crm中获取时的id',
  `customer_id` varchar(255) DEFAULT NULL COMMENT '客户id',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3658 DEFAULT CHARSET=utf8 COMMENT='立项联系人信息';

-- ----------------------------
-- Table structure for tb_initiate_possessor
-- ----------------------------
DROP TABLE IF EXISTS `tb_initiate_possessor`;
CREATE TABLE `tb_initiate_possessor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `p_type` int(11) DEFAULT NULL COMMENT '委托人类型:如1表示法人,0表示自然人',
  `p_entrustment_unit` varchar(100) DEFAULT NULL COMMENT '委托单位',
  `p_legal_representative` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `p_sociology_code` varchar(255) DEFAULT NULL COMMENT '社会统一信用代码',
  `p_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `p_scope_operation` varchar(1000) DEFAULT NULL COMMENT '经营范围',
  `p_unit_properties` varchar(255) DEFAULT NULL COMMENT '单位性质',
  `p_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `spare_field` varchar(255) DEFAULT NULL COMMENT '备用字段',
  `p_idcard` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=624 DEFAULT CHARSET=utf8 COMMENT='立项占有人';

-- ----------------------------
-- Table structure for tb_initiate_unit_information
-- ----------------------------
DROP TABLE IF EXISTS `tb_initiate_unit_information`;
CREATE TABLE `tb_initiate_unit_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `info_write` tinyint(1) DEFAULT NULL COMMENT '是否写入到crm中',
  `u_use_unit_name` varchar(255) DEFAULT NULL COMMENT '报告使用单位名称',
  `u_unit_properties` varchar(255) DEFAULT NULL COMMENT '单位性质',
  `u_scope_operation` varchar(1000) DEFAULT NULL COMMENT '经营范围',
  `u_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `u_certificate_number` varchar(255) DEFAULT NULL COMMENT '证照号',
  `u_legal_representative` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `u_use_unit` varchar(255) DEFAULT NULL COMMENT '报告使用单位',
  `business_type` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `assess_type` varchar(255) DEFAULT NULL COMMENT '评估类型',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=623 DEFAULT CHARSET=utf8 COMMENT='立项报告使用单位';

-- ----------------------------
-- Table structure for tb_md_architectural_obj
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_architectural_obj`;
CREATE TABLE `tb_md_architectural_obj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `json_content` json DEFAULT NULL COMMENT '建筑安装工程费json数据或者成新率数据或者其他数据',
  `database_name` varchar(255) DEFAULT NULL COMMENT '数据库名称',
  `plan_details_id` int(22) DEFAULT NULL COMMENT '计划id',
  `type` varchar(255) DEFAULT NULL COMMENT '存储类型',
  `pid` int(11) DEFAULT NULL COMMENT '存入数据库表的父id',
  `price` decimal(16,4) DEFAULT NULL COMMENT '价格',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1080 DEFAULT CHARSET=utf8 COMMENT='建筑安装工程费或者成新率或者其他什么的';

-- ----------------------------
-- Table structure for tb_md_base_land_price
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_base_land_price`;
CREATE TABLE `tb_md_base_land_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reward_rate_id` int(11) DEFAULT NULL COMMENT '报酬率id',
  `reward_rate` varchar(100) DEFAULT NULL COMMENT '报酬率',
  `has_develop_correct` bit(1) DEFAULT NULL COMMENT '开发程度是否修正',
  `develop_correct` decimal(13,2) DEFAULT NULL COMMENT '开发程度修正',
  `period_amend` decimal(13,4) DEFAULT NULL COMMENT '年期修正系数',
  `parcel_price` decimal(13,2) DEFAULT NULL COMMENT '宗地单价',
  `parcel_bhou_price` decimal(13,2) DEFAULT NULL COMMENT '宗地亩价',
  `parcel_total_price` decimal(13,2) DEFAULT NULL COMMENT '宗地总价',
  `floor_premium` decimal(13,2) DEFAULT NULL COMMENT '楼面地价',
  `correction_difference` varchar(100) DEFAULT NULL COMMENT '修正差额',
  `standard_premium` decimal(13,2) DEFAULT NULL COMMENT '基准地价单价',
  `date_amend` decimal(13,2) DEFAULT NULL COMMENT '期日修正系数',
  `legal_age` decimal(13,2) DEFAULT NULL COMMENT '法定年限',
  `land_surplus_year` decimal(13,2) DEFAULT NULL COMMENT '剩余使用年限',
  `has_fraction_amend` bit(1) DEFAULT NULL COMMENT '容积率是否修正',
  `volume_fraction_amend` decimal(13,4) DEFAULT NULL COMMENT '容积率修正',
  `area_and_several_amend` decimal(13,2) DEFAULT NULL COMMENT '区域及个别修正系数',
  `evaluation_area` decimal(13,2) DEFAULT NULL COMMENT '委估宗地面积',
  `volumetric_rate` decimal(13,2) DEFAULT NULL COMMENT '委估对象容积率',
  `process_ins_id` varchar(100) DEFAULT NULL COMMENT '流程实例id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='基准地价系数修正法';

-- ----------------------------
-- Table structure for tb_md_calculating_method_engineering_cost
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_calculating_method_engineering_cost`;
CREATE TABLE `tb_md_calculating_method_engineering_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称 ',
  `area` decimal(18,4) DEFAULT NULL COMMENT '面积 ',
  `price` decimal(15,4) DEFAULT NULL COMMENT '价格',
  `data_table_name` varchar(50) DEFAULT NULL COMMENT '表名',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `plan_details_id` int(12) DEFAULT NULL COMMENT '计划id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `architectural_obj_id` int(11) DEFAULT NULL COMMENT '建筑安装工程费明细',
  `process_ins_id` varchar(11) DEFAULT NULL COMMENT '流程实例编号',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=567 DEFAULT CHARSET=utf8 COMMENT='测算方法 工程费';

-- ----------------------------
-- Table structure for tb_md_cost
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_cost`;
CREATE TABLE `tb_md_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT NULL COMMENT '委估对象名称',
  `area` decimal(13,2) DEFAULT NULL COMMENT '委估对象面积',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划id',
  `type` varchar(255) DEFAULT NULL COMMENT '区分建筑物和在建工程(tb_md_cost_building,tb_md_cost_construction)',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_md_cost_approach
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_cost_approach`;
CREATE TABLE `tb_md_cost_approach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reward_rate_id` int(11) DEFAULT NULL COMMENT '报酬率id',
  `reward_rate` varchar(100) DEFAULT NULL COMMENT '报酬率',
  `imprevision_cost` varchar(100) DEFAULT NULL COMMENT '不可预见费',
  `circulation_expense` decimal(13,2) DEFAULT NULL COMMENT '宗地外流通费用',
  `flat_expense` decimal(13,2) DEFAULT NULL COMMENT '场平费用',
  `machine_cycle` decimal(13,2) DEFAULT NULL COMMENT '计息周期',
  `calculated_interest` varchar(100) DEFAULT NULL COMMENT '计算利息',
  `profit_margin` varchar(100) DEFAULT NULL COMMENT '利润率',
  `incremental_benefit` varchar(100) DEFAULT NULL COMMENT '增值收益率',
  `process_ins_id` varchar(100) DEFAULT NULL COMMENT '流程实例id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='成本逼近法测算表';

-- ----------------------------
-- Table structure for tb_md_cost_construction
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_cost_construction`;
CREATE TABLE `tb_md_cost_construction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcel_setting_outer` varchar(255) DEFAULT NULL COMMENT '宗地外设定',
  `parcel_setting_inner` varchar(255) DEFAULT NULL COMMENT '宗地内设定',
  `construction_installation_engineering_fee_ids` varchar(255) DEFAULT NULL COMMENT '建筑安装工程费ids',
  `construction_assessment_price_correcting` decimal(10,2) DEFAULT NULL COMMENT '在建工程单位价',
  `construction_assessment_value` varchar(255) DEFAULT NULL COMMENT '在建工程评估价值',
  `investment_profit` varchar(255) DEFAULT NULL COMMENT '投资利润',
  `interest_investment` varchar(255) DEFAULT NULL COMMENT '投资利息',
  `construction_subtotal` varchar(255) DEFAULT NULL COMMENT '建设成本小计',
  `land_get_cost_total` varchar(255) DEFAULT NULL COMMENT '土地取得成本合计',
  `json_content` json DEFAULT NULL COMMENT 'json数据',
  `develop_land_area_tax` decimal(22,2) DEFAULT NULL COMMENT '开发土地面积',
  `develop_build_area_tax` decimal(22,2) DEFAULT NULL COMMENT '开发建筑面积(㎡)',
  `develop_year_number_tax` decimal(22,2) DEFAULT NULL COMMENT '开发期',
  `economic_id` int(11) DEFAULT NULL COMMENT '经济指标id',
  `mc_id` int(11) DEFAULT NULL COMMENT '比较法id',
  `land_purchase_price` decimal(22,2) DEFAULT NULL COMMENT '土地购买价格',
  `land_purchase_price_explain` varchar(255) DEFAULT NULL COMMENT '土地购买价格说明',
  `land_get_relevant` decimal(22,4) DEFAULT NULL COMMENT '土地取得相关税费率',
  `land_get_relevant_explain` varchar(255) DEFAULT NULL COMMENT '土地取得相关税费率说明',
  `reconnaissance_design` decimal(22,4) DEFAULT NULL COMMENT '勘察设计和前期工程费率',
  `construction_installation_engineering_fee` decimal(22,2) DEFAULT NULL COMMENT '建筑安装工程费',
  `infrastructure_cost` decimal(22,2) DEFAULT NULL COMMENT '基础设施建设费',
  `infrastructure_matching_cost` decimal(22,2) DEFAULT NULL COMMENT '公共配套设施建设费',
  `infrastructure_matching_cost_explain` varchar(255) DEFAULT NULL COMMENT '公共配套设施建设费 说明',
  `dev_during` decimal(22,2) DEFAULT NULL COMMENT '开发期间税费',
  `other_engineering_cost` decimal(22,2) DEFAULT NULL COMMENT '其它工程费',
  `unforeseen_expenses` decimal(22,4) DEFAULT NULL COMMENT '不可预见费率',
  `unforeseen_expenses_explain` varchar(255) DEFAULT NULL COMMENT '不可预见费率说明',
  `management_expense` decimal(22,6) DEFAULT NULL COMMENT '管理费率',
  `management_expense_explain` varchar(255) DEFAULT NULL COMMENT '管理费率说明',
  `sales_fee` decimal(22,4) DEFAULT NULL COMMENT '销售费率',
  `sales_fee_explain` varchar(255) DEFAULT NULL COMMENT '销售费率说明',
  `interest_investment_tax` decimal(22,4) DEFAULT NULL COMMENT '投资利息率',
  `interest_investment_tax_explain` varchar(255) DEFAULT NULL COMMENT '投资利息率说明',
  `sales_tax_and_additional` decimal(22,4) DEFAULT NULL COMMENT '销售税金及附加率',
  `sales_tax_and_additional_explain` varchar(255) DEFAULT NULL COMMENT '销售税金及附加率说明',
  `investment_profit_tax` decimal(22,4) DEFAULT NULL COMMENT '开发利润率',
  `investment_profit_tax_explain` varchar(255) DEFAULT NULL COMMENT '开发利润率说明',
  `construction_assessment_value2` decimal(22,2) DEFAULT NULL COMMENT '在建工程评估价值2',
  `additional_cost_land_acquisition` decimal(22,2) DEFAULT NULL COMMENT '土地取得附加成本',
  `infrastructure_cost_explain` varchar(255) DEFAULT NULL COMMENT '基础设施建设费说明',
  `dev_during_explain` varchar(255) DEFAULT NULL COMMENT '开发期间税费说明',
  `other_engineering_cost_explain` varchar(255) DEFAULT NULL COMMENT '其它工程费说明',
  `additional_cost_land_acquisition_explain` varchar(255) DEFAULT NULL COMMENT '土地取得附加成本说明',
  `reconnaissance_design_explain` varchar(255) DEFAULT NULL COMMENT '勘察设计和前期工程费率说明',
  `residue_ratio_id` int(22) DEFAULT NULL COMMENT '成新率id',
  `residue_ratio` decimal(20,4) DEFAULT NULL COMMENT '成新率',
  `pid` int(12) DEFAULT NULL COMMENT '成本法id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_md_development
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_development`;
CREATE TABLE `tb_md_development` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcel_setting_outer` varchar(255) DEFAULT NULL COMMENT '宗地外设定',
  `parcel_setting_inner` varchar(255) DEFAULT NULL COMMENT '宗地内设定',
  `name` varchar(1000) DEFAULT NULL COMMENT '委估对象名称',
  `area` decimal(13,2) DEFAULT NULL COMMENT '委估对象面积',
  `price` decimal(10,2) DEFAULT NULL COMMENT '测算价格(最终结果)',
  `type` varchar(255) DEFAULT NULL COMMENT '区分土地测算和工程测算',
  `economic_id` int(11) DEFAULT NULL COMMENT '经济指标id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `construction_cost_subtotal` decimal(22,2) DEFAULT NULL COMMENT '工程建设成本小计',
  `interest_investment` decimal(22,2) DEFAULT NULL COMMENT '投资利息',
  `investment_profit` decimal(22,2) DEFAULT NULL COMMENT '投资利润',
  `assess_price` decimal(22,2) DEFAULT NULL COMMENT '评估单价',
  `content` json DEFAULT NULL COMMENT '数据主体 json',
  `project_construction_period` decimal(22,0) DEFAULT NULL COMMENT '项目建设期(年',
  `developed_year` decimal(22,2) DEFAULT NULL COMMENT '已开发时间',
  `remaining_development_year` decimal(22,2) DEFAULT NULL COMMENT '剩余开发时间',
  `reward_rate_id` int(12) DEFAULT NULL COMMENT '报酬率id',
  `total_saleable_area_price` decimal(20,2) DEFAULT NULL COMMENT '总可售面积售价',
  `saleable_area` decimal(20,2) DEFAULT NULL COMMENT '可售面积',
  `planned_building_area` decimal(20,2) DEFAULT NULL COMMENT '规划建筑面积',
  `unsaleable_building_area` decimal(20,2) DEFAULT NULL COMMENT '不可售建筑面积',
  `reconnaissance_design` decimal(20,2) DEFAULT NULL COMMENT '勘察设计和前期工程费率',
  `construction_installation_engineering_fee_ids` varchar(255) DEFAULT NULL COMMENT '建筑安装工程费ids',
  `construction_installation_engineering_fee` decimal(20,2) DEFAULT NULL COMMENT '建筑安装工程费',
  `infrastructure_cost` decimal(20,2) DEFAULT NULL COMMENT '基础设施配套费',
  `infrastructure_matching_cost` decimal(20,2) DEFAULT NULL COMMENT '公共配套设施建设费',
  `dev_during` decimal(20,2) DEFAULT NULL COMMENT '开发期间税费',
  `other_engineering_cost` decimal(20,4) DEFAULT NULL COMMENT '其它工程费率',
  `unforeseen_expenses` decimal(20,4) DEFAULT NULL COMMENT '不可预见费率',
  `reconnaissance_design_explain` varchar(255) DEFAULT NULL COMMENT '勘察设计和前期工程费率说明',
  `infrastructure_cost_explain` varchar(255) DEFAULT NULL COMMENT '基础设施建设费说明',
  `infrastructure_matching_cost_explain` varchar(255) DEFAULT NULL COMMENT '公共配套设施建设费 说明',
  `dev_during_explain` varchar(255) DEFAULT NULL COMMENT '开发期间税费说明',
  `other_engineering_cost_explain` varchar(255) DEFAULT NULL COMMENT '其它工程费说明',
  `unforeseen_expenses_explain` varchar(255) DEFAULT NULL COMMENT '不可预见费率说明',
  `deed_tax_rate` decimal(20,4) DEFAULT NULL COMMENT '契税率',
  `deed_tax_rate_explain` varchar(255) DEFAULT NULL COMMENT '契税率说明',
  `transaction_tax_rate` decimal(20,4) DEFAULT NULL COMMENT '交易费率',
  `transaction_tax_rate_explain` varchar(255) DEFAULT NULL COMMENT '交易费率说明',
  `management_expense` decimal(20,4) DEFAULT NULL COMMENT '管理费率',
  `management_expense_explain` varchar(255) DEFAULT NULL COMMENT '管理费率说明',
  `land_get_relevant` decimal(20,2) DEFAULT NULL COMMENT '土地取得附加成本',
  `land_get_relevant_explain` varchar(255) DEFAULT NULL COMMENT '土地取得附加成本说明',
  `sales_fee` decimal(20,4) DEFAULT NULL COMMENT '销售费用率',
  `sales_fee_explain` varchar(255) DEFAULT NULL COMMENT '销售费用率说明',
  `interest_investment_tax` decimal(20,4) DEFAULT NULL COMMENT '投资利息率',
  `interest_investment_tax_explain` varchar(255) DEFAULT NULL COMMENT '投资利息率说明',
  `investment_profit_tax` decimal(20,4) DEFAULT NULL COMMENT '开发利润率',
  `investment_profit_tax_explain` varchar(255) DEFAULT NULL COMMENT '开发利润率说明',
  `sales_tax_and_additional` decimal(20,4) DEFAULT NULL COMMENT '销售环节增值税及附加',
  `sales_tax_and_additional_explain` varchar(255) DEFAULT NULL COMMENT '销售环节增值税及附加说明',
  `land_value_added_tax` decimal(20,4) DEFAULT NULL COMMENT '土地增值税',
  `land_value_added_tax_explain` varchar(255) DEFAULT NULL COMMENT '土地增值税说明',
  `project_development_income_tax` decimal(20,4) DEFAULT NULL COMMENT '项目开发所得税',
  `project_development_income_tax_explain` varchar(255) DEFAULT NULL COMMENT '项目开发所得税说明',
  `remuneration_rate` decimal(20,4) DEFAULT NULL COMMENT '土地还原率或者报酬率',
  `statutory_life` decimal(20,2) DEFAULT NULL COMMENT '法定年限',
  `remaining_years` decimal(20,2) DEFAULT NULL COMMENT '剩余年限',
  `amendment_status_rights` decimal(20,4) DEFAULT NULL COMMENT '权利状况修正',
  `amendment_status_rights_explain` varchar(255) DEFAULT NULL COMMENT '权利状况修正说明',
  `other_amendments` decimal(20,4) DEFAULT NULL COMMENT '其他修正',
  `other_amendments_explain` varchar(255) DEFAULT NULL COMMENT '其他修正说明',
  `development_degree_revision` decimal(20,2) DEFAULT NULL COMMENT '开发程度修正',
  `development_degree_revision_explain` varchar(255) DEFAULT NULL COMMENT '开发程度修正说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='假设开发法';

-- ----------------------------
-- Table structure for tb_md_development_infrastructure_children
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_development_infrastructure_children`;
CREATE TABLE `tb_md_development_infrastructure_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称 ',
  `number` decimal(18,2) DEFAULT NULL COMMENT '金额 ',
  `tax` decimal(18,2) DEFAULT NULL COMMENT '税费 ',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划详情id',
  `type` varchar(255) DEFAULT NULL COMMENT '类别',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `pid` int(11) DEFAULT NULL COMMENT '关联主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=455 DEFAULT CHARSET=utf8 COMMENT='假设开发法 配套设施费用';

-- ----------------------------
-- Table structure for tb_md_economic_indicators
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_economic_indicators`;
CREATE TABLE `tb_md_economic_indicators` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcel_setting_outer` varchar(255) DEFAULT NULL COMMENT '宗地外设定',
  `parcel_setting_inner` varchar(255) DEFAULT NULL COMMENT '宗地内设定',
  `plan_details_id` int(12) DEFAULT NULL COMMENT '项目计划详情id',
  `project_file_name` varchar(255) DEFAULT NULL COMMENT '项目文件名称(假设法报告中相关依据)',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `grade` varchar(255) DEFAULT NULL COMMENT '项目档次（楼盘',
  `cert_use` varchar(255) DEFAULT NULL COMMENT '土地用途',
  `building_structure` varchar(255) DEFAULT NULL COMMENT '建筑结构',
  `building_base_coverage` decimal(22,2) DEFAULT NULL COMMENT '建筑基底占地面积（㎡)',
  `building_height_limit` decimal(22,2) DEFAULT NULL COMMENT '建筑限高（m）',
  `volumetric_rate` varchar(255) DEFAULT NULL COMMENT '容积率',
  `building_density` varchar(255) DEFAULT NULL COMMENT '建筑密度',
  `green_space_rate` varchar(255) DEFAULT NULL COMMENT '绿地率',
  `plan_net_construction_land_area` decimal(22,2) DEFAULT NULL COMMENT '规划建设净用地面积（㎡）',
  `plan_total_build_area` decimal(22,2) DEFAULT NULL COMMENT '规划总建筑面积（㎡）',
  `set_volumetric_rate` varchar(255) DEFAULT NULL COMMENT '设定容积率',
  `assess_use_land_area` decimal(22,2) DEFAULT NULL COMMENT '评估用地面积（㎡',
  `assess_total_build_area` decimal(22,2) DEFAULT NULL COMMENT '评估总建筑面积',
  `plan_date` datetime DEFAULT NULL COMMENT '规划日期',
  `remark` varchar(500) DEFAULT NULL COMMENT '建筑层数及功能分布',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2976 DEFAULT CHARSET=utf8 COMMENT='经济规划指标';

-- ----------------------------
-- Table structure for tb_md_economic_indicators_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_economic_indicators_item`;
CREATE TABLE `tb_md_economic_indicators_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `economic_id` int(11) DEFAULT NULL COMMENT '主表id',
  `plan_details_id` int(12) DEFAULT NULL COMMENT '项目计划详情id',
  `data_key` varchar(255) DEFAULT NULL COMMENT 'key值',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `planned_building_area` decimal(18,4) DEFAULT NULL COMMENT '规划建筑面积 ',
  `saleable_area` decimal(18,4) DEFAULT NULL COMMENT '可售面积 ',
  `assess_area` decimal(22,4) DEFAULT NULL COMMENT '评估面积',
  `number` int(11) DEFAULT NULL COMMENT '个数 ',
  `unit_price` decimal(18,4) DEFAULT NULL COMMENT '单位售价',
  `remark` varchar(500) DEFAULT NULL COMMENT '说明 ',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3312 DEFAULT CHARSET=utf8 COMMENT='经济规划指标';

-- ----------------------------
-- Table structure for tb_md_income
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income`;
CREATE TABLE `tb_md_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT NULL COMMENT '委估对象名称',
  `area` decimal(18,2) DEFAULT NULL COMMENT '委估对象面积',
  `price` decimal(18,2) DEFAULT NULL COMMENT '价格',
  `operation_mode` int(11) DEFAULT NULL COMMENT '经营方式 0自营 1租赁',
  `lease_mode` int(11) DEFAULT NULL COMMENT '租赁方式 0限制 1无限制',
  `form_type` int(11) DEFAULT '0' COMMENT '0 默认 1(餐饮、酒店、宾馆)',
  `restriction_explain` varchar(1000) DEFAULT NULL COMMENT '租约限制说明',
  `average_profit_rate` varchar(50) DEFAULT NULL COMMENT '行业经营平均利润率',
  `average_profit_rate_remark` varchar(255) DEFAULT NULL COMMENT '行业经营平均利润率说明',
  `reward_rate` decimal(18,4) DEFAULT NULL COMMENT '报酬率',
  `reward_rate_id` int(11) DEFAULT NULL COMMENT '报酬率测算过程数据id',
  `house_remaining_year` decimal(11,2) DEFAULT NULL COMMENT '房产剩余年限',
  `land_remaining_year` decimal(11,2) DEFAULT NULL COMMENT '土地剩余年限',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8 COMMENT='收益法主表';

-- ----------------------------
-- Table structure for tb_md_income_date_section
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_date_section`;
CREATE TABLE `tb_md_income_date_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `operation_mode` int(11) DEFAULT NULL COMMENT '经营方式 0自营 1租赁',
  `begin_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `year_count` decimal(18,2) DEFAULT NULL COMMENT '年份总数',
  `income_total` decimal(18,2) DEFAULT NULL COMMENT '总收入',
  `cost_total` decimal(18,2) DEFAULT NULL COMMENT '总成本',
  `operating_profit` decimal(18,2) DEFAULT NULL COMMENT '经营利润',
  `net_profit` varchar(50) DEFAULT NULL COMMENT '房地产年净收益',
  `rental_growth_rate` decimal(18,4) DEFAULT NULL COMMENT '租金增长率',
  `rental_growth_rate_explain` varchar(255) DEFAULT NULL COMMENT '租金增长率说明',
  `rental_growth_rate_explain_supplement` varchar(255) DEFAULT NULL COMMENT '租金增长率说明补充',
  `correction_factor` decimal(18,6) DEFAULT NULL COMMENT '年修正系数',
  `present_value_factor` decimal(18,6) DEFAULT NULL COMMENT '收益现值系数',
  `income_price` decimal(18,2) DEFAULT NULL COMMENT '收益价格',
  `sorting` int(11) DEFAULT '0' COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `rate_increase` decimal(18,4) DEFAULT NULL COMMENT '增长率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_md_income_forecast
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_forecast`;
CREATE TABLE `tb_md_income_forecast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 收入 1成本',
  `initial_amount` decimal(13,2) DEFAULT NULL COMMENT '初始金额',
  `initial_amount_remark` varchar(100) DEFAULT NULL COMMENT '初始金额说明',
  `operating_cost` decimal(13,2) DEFAULT NULL COMMENT '经营成本',
  `operating_cost_remark` varchar(100) DEFAULT NULL COMMENT '经营成本说明',
  `operating_expenses` decimal(13,2) DEFAULT NULL COMMENT '经营费用',
  `operating_expenses_remark` varchar(100) DEFAULT NULL COMMENT '经营费用说明',
  `operating_tax` decimal(13,2) DEFAULT NULL COMMENT '经营税金及附加',
  `operating_tax_remark` varchar(100) DEFAULT NULL COMMENT '经营税金及附加说明',
  `management_cost` decimal(13,2) DEFAULT NULL COMMENT '管理费用',
  `management_cost_remark` varchar(100) DEFAULT NULL COMMENT '管理费用说明',
  `financial_cost` decimal(13,2) DEFAULT NULL COMMENT '财务费用',
  `financial_cost_remark` varchar(100) DEFAULT NULL COMMENT '财务费用说明',
  `operating_profit` decimal(13,2) DEFAULT NULL COMMENT '经营利润',
  `operating_profit_remark` varchar(100) DEFAULT NULL COMMENT '经营利润说明',
  `excess_profit` decimal(13,2) DEFAULT NULL COMMENT '特许权超额利润',
  `excess_profit_remark` varchar(100) DEFAULT NULL COMMENT '特许权超额利润说明',
  `growth_rate` decimal(18,4) DEFAULT NULL COMMENT '增长率',
  `growth_rate_remark` varchar(100) DEFAULT NULL COMMENT '成长率说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `operating_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '经营成本比率',
  `operating_expenses_ratio` decimal(18,4) DEFAULT NULL COMMENT '经营费用比率',
  `operating_tax_ratio` decimal(18,4) DEFAULT NULL COMMENT '税金及附加比率',
  `management_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '管理比率',
  `financial_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '财务费用比率',
  `operating_profit_ratio` decimal(18,4) DEFAULT NULL COMMENT '利润比率',
  `excess_profit_ratio` decimal(18,4) DEFAULT NULL COMMENT '超额利润比率',
  `operating_cost_item` json DEFAULT NULL COMMENT '经营成本明细',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=380 DEFAULT CHARSET=utf8 COMMENT='自营费用信息';

-- ----------------------------
-- Table structure for tb_md_income_forecast_analyse
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_forecast_analyse`;
CREATE TABLE `tb_md_income_forecast_analyse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 收入 1成本',
  `form_type` int(11) DEFAULT NULL COMMENT '表单类型',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `amount_money` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `quantitative_trend` decimal(13,4) DEFAULT NULL COMMENT '数量趋势',
  `univalent_trend` decimal(13,4) DEFAULT NULL COMMENT '单价趋势',
  `bis_participate_in` bit(1) DEFAULT b'0' COMMENT '是否参与',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `source_type` varchar(50) DEFAULT NULL COMMENT '来源类型',
  `cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '成本比率',
  `earned_profit_ratio` decimal(18,4) DEFAULT NULL COMMENT '经营利润比率',
  `earned_profit` decimal(18,4) DEFAULT NULL COMMENT '经营利润',
  `operating_expenses_ratio` decimal(18,4) DEFAULT NULL COMMENT '经营费用比率',
  `operating_tax_ratio` decimal(18,4) DEFAULT NULL COMMENT '税金及附加比率',
  `management_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '管理费用比率',
  `financial_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '财务费用比率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8 COMMENT='自营费用信息';

-- ----------------------------
-- Table structure for tb_md_income_forecast_analyse_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_forecast_analyse_item`;
CREATE TABLE `tb_md_income_forecast_analyse_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `forecast_analyse_id` int(11) DEFAULT NULL,
  `form_type` int(11) DEFAULT '0' COMMENT '表单类型',
  `type` int(11) DEFAULT NULL COMMENT '0收入 1成本',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `source_type` varchar(50) DEFAULT NULL COMMENT '来源',
  `accounting_subject` int(11) DEFAULT NULL COMMENT '会计科目',
  `first_level_number` varchar(50) DEFAULT NULL COMMENT '一级编号',
  `second_level_number` varchar(50) DEFAULT NULL COMMENT '二级编号',
  `amount_money` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `number` int(11) DEFAULT NULL COMMENT '个数',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1397 DEFAULT CHARSET=utf8 COMMENT='预测分析数据明细';

-- ----------------------------
-- Table structure for tb_md_income_forecast_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_forecast_item`;
CREATE TABLE `tb_md_income_forecast_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_forecast_id` int(11) DEFAULT NULL,
  `accounting_subject` int(11) DEFAULT NULL COMMENT '会计科目',
  `first_level_number` varchar(50) DEFAULT NULL COMMENT '一级编号',
  `second_level_number` varchar(50) DEFAULT NULL COMMENT '二级编号',
  `amount_money` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `rate_increase` decimal(18,4) DEFAULT NULL COMMENT '增长率',
  `rate_increase_explain` varchar(200) DEFAULT NULL COMMENT '增长率说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='有效毛收入明细';

-- ----------------------------
-- Table structure for tb_md_income_forecast_month
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_forecast_month`;
CREATE TABLE `tb_md_income_forecast_month` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 收入 1成本',
  `accounting_subject` int(11) DEFAULT NULL COMMENT '会计科目',
  `first_level_number` varchar(50) DEFAULT NULL COMMENT '一级编号',
  `second_level_number` varchar(50) DEFAULT NULL COMMENT '二级编号',
  `month` varchar(50) DEFAULT NULL COMMENT '月份',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `amount_money` decimal(13,2) DEFAULT NULL COMMENT '金额',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自营费用信息';

-- ----------------------------
-- Table structure for tb_md_income_forecast_year
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_forecast_year`;
CREATE TABLE `tb_md_income_forecast_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `forecast_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 收入类 1成本类',
  `begin_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `amount` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `income_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2100 DEFAULT CHARSET=utf8 COMMENT='自营费用信息';

-- ----------------------------
-- Table structure for tb_md_income_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_history`;
CREATE TABLE `tb_md_income_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `forecast_analyse_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 收入 1成本',
  `form_type` int(11) DEFAULT NULL COMMENT '表单类型 0默认 1餐饮、酒店、宾馆',
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `accounting_subject` int(11) DEFAULT NULL COMMENT '会计科目',
  `first_level_number` varchar(50) DEFAULT NULL COMMENT '一级编号',
  `second_level_number` varchar(50) DEFAULT NULL COMMENT '二级编号',
  `unit` varchar(100) DEFAULT NULL COMMENT '单位',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `amount_money` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `begin_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `utilization_ratio` decimal(13,4) DEFAULT NULL COMMENT '利用率',
  `utilization_ratio_explain` varchar(255) DEFAULT NULL COMMENT '利用率说明',
  `make_price` decimal(13,2) DEFAULT NULL COMMENT '标价',
  `make_price_explain` varchar(255) DEFAULT NULL COMMENT '标价说明',
  `executive_price` decimal(13,2) DEFAULT NULL COMMENT '执行价',
  `discount_rate` decimal(13,4) DEFAULT NULL COMMENT '折扣率',
  `discount_rate_explain` varchar(255) DEFAULT NULL COMMENT '折扣率说明',
  `bis_forecast` bit(1) DEFAULT b'0' COMMENT '是否为预测数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `source_type` varchar(50) DEFAULT NULL COMMENT '来源类型',
  `deprecition_royalty` varchar(100) DEFAULT NULL COMMENT '房屋折旧和使用费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1200 DEFAULT CHARSET=utf8 COMMENT='自营费用信息';

-- ----------------------------
-- Table structure for tb_md_income_lease
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_lease`;
CREATE TABLE `tb_md_income_lease` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL,
  `mc_id` int(11) DEFAULT NULL COMMENT '市场比较法id',
  `rental_income_remark` varchar(255) DEFAULT NULL COMMENT '月租金收入说明',
  `rental_income` decimal(13,2) DEFAULT NULL COMMENT '月租金收入',
  `rentals` decimal(18,4) DEFAULT NULL COMMENT '出租率',
  `rentals_remark` varchar(255) DEFAULT NULL COMMENT '出租率说明',
  `month_number` int(11) DEFAULT NULL COMMENT '全年月份数',
  `deposit` decimal(13,2) DEFAULT NULL COMMENT '押金',
  `deposit_remark` varchar(255) DEFAULT NULL COMMENT '押金说明',
  `deposit_rate` decimal(18,4) DEFAULT NULL COMMENT '押金利率',
  `deposit_rate_remark` varchar(255) DEFAULT NULL COMMENT '押金利率说明',
  `other_income` decimal(13,2) DEFAULT NULL COMMENT '其它收入',
  `other_income_remark` varchar(255) DEFAULT NULL COMMENT '其它收入说明',
  `gross_income` decimal(18,2) DEFAULT NULL COMMENT '毛收入',
  `additional_capture` varchar(255) DEFAULT NULL COMMENT '有效收缴率',
  `additional_capture_remark` varchar(255) DEFAULT NULL COMMENT '有效收缴率说明',
  `sorting` int(11) DEFAULT '0' COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_md_income_lease_cost
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_lease_cost`;
CREATE TABLE `tb_md_income_lease_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL,
  `replacement_value` decimal(18,4) DEFAULT NULL COMMENT '计算基数重置价格(元/㎡）',
  `management_cost` varchar(50) DEFAULT NULL COMMENT '管理费用',
  `management_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '管理费用比率',
  `maintenance` varchar(50) DEFAULT NULL COMMENT '维护保养',
  `maintenance_cost_ratio` decimal(18,4) DEFAULT NULL COMMENT '维护保养费比率',
  `additional` varchar(50) DEFAULT NULL COMMENT '租赁税费',
  `additional_ratio` decimal(18,4) DEFAULT NULL COMMENT '租赁税费比率',
  `insurance_premium` varchar(50) DEFAULT NULL COMMENT '保险费',
  `insurance_premium_ratio` decimal(18,4) DEFAULT NULL COMMENT '保险费率',
  `land_use_tax` decimal(13,2) DEFAULT NULL COMMENT '土地使用税',
  `use_tax_parameter` varchar(50) DEFAULT NULL COMMENT '使用税参数',
  `transaction_taxe_fee_ratio` decimal(13,4) DEFAULT NULL COMMENT '其它交易税费',
  `transaction_taxe_fee_explain` varchar(255) DEFAULT NULL COMMENT '其它交易税费说明',
  `transaction_taxe_fee_explain_supplement` varchar(255) DEFAULT NULL COMMENT '其它交易税费说明补充',
  `property_tax_ratio` decimal(18,4) DEFAULT NULL COMMENT '房产税率',
  `stamp_duty_ratio` decimal(18,4) DEFAULT NULL COMMENT '印花税率',
  `sales_tax_ratio` decimal(18,4) DEFAULT NULL COMMENT '增值税率',
  `construction_tax_ratio` decimal(18,4) DEFAULT NULL COMMENT '城建税率',
  `local_education_ratio` decimal(18,4) DEFAULT NULL COMMENT '地方教育费附加税率',
  `education_ratio` decimal(18,4) DEFAULT NULL COMMENT '教育费附加税率',
  `sorting` int(11) DEFAULT '0' COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_md_income_price_investigation
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_income_price_investigation`;
CREATE TABLE `tb_md_income_price_investigation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品价格调查';

-- ----------------------------
-- Table structure for tb_md_market_compare
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_market_compare`;
CREATE TABLE `tb_md_market_compare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT NULL COMMENT '委估对象名称',
  `value_time_point` datetime DEFAULT NULL COMMENT '评估基准日',
  `reward_rate` decimal(11,2) DEFAULT NULL COMMENT '报酬率',
  `reward_rate_id` int(11) DEFAULT NULL COMMENT '报酬率id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=549 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_md_market_compare_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_md_market_compare_item`;
CREATE TABLE `tb_md_market_compare_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mc_id` int(11) DEFAULT NULL COMMENT '市场比较主表id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '0 委估对象 1案例',
  `plan_details_id` int(11) DEFAULT NULL,
  `json_content` json DEFAULT NULL COMMENT 'json数据',
  `trading_time_explain` varchar(200) DEFAULT NULL COMMENT '交易时间修正说明',
  `residue_ratio_id` int(11) DEFAULT NULL COMMENT '成新率id',
  `used_year` int(11) DEFAULT NULL COMMENT '成新率使用年限',
  `usable_year` int(11) DEFAULT NULL COMMENT '成新率可用年限',
  `house_id` int(11) DEFAULT NULL COMMENT '成新率完损度houseId',
  `area` decimal(13,2) DEFAULT NULL COMMENT '面积',
  `initial_price` decimal(13,2) DEFAULT NULL COMMENT '初始价格',
  `must_adjust_price` bit(1) DEFAULT NULL COMMENT '是否必须调整成交价',
  `annual_coefficient` decimal(13,4) DEFAULT NULL COMMENT '年期修正系数',
  `volume_ratio_coefficient` decimal(13,4) DEFAULT NULL COMMENT '容积率修正系数',
  `specific_price` varchar(50) DEFAULT NULL COMMENT '比准价格',
  `correction_difference` varchar(50) DEFAULT NULL COMMENT '修正差额',
  `case_difference` varchar(50) DEFAULT NULL COMMENT '案例差异',
  `weight` varchar(20) DEFAULT NULL COMMENT '权重',
  `weight_description` varchar(200) DEFAULT NULL COMMENT '权重说明',
  `average_price` decimal(13,2) DEFAULT NULL COMMENT '测算价',
  `deve_degree` decimal(13,2) DEFAULT NULL COMMENT '开发程度',
  `evaluate_price` decimal(13,2) DEFAULT NULL COMMENT '评估价格',
  `location_factor_ratio` decimal(13,4) DEFAULT NULL COMMENT '区位修正因素',
  `equity_factor_ratio` decimal(13,4) DEFAULT NULL COMMENT '权益修正因素',
  `entity_factor_ratio` decimal(13,4) DEFAULT NULL COMMENT '实体修正因素',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_net_info_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_net_info_record`;
CREATE TABLE `tb_net_info_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `notice_date` varchar(255) DEFAULT NULL COMMENT '公告日期',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `source_site_name` varchar(255) DEFAULT NULL COMMENT '数据来源网站名称',
  `source_site_url` varchar(255) DEFAULT NULL COMMENT '数据来源网站地址',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `current_price` varchar(50) DEFAULT NULL COMMENT '成交价',
  `consult_price` varchar(50) DEFAULT NULL COMMENT '估算价',
  `init_price` varchar(50) DEFAULT NULL COMMENT '拍卖价',
  `liquid_ratios` varchar(50) DEFAULT NULL COMMENT '变现比率',
  `unit_name` varchar(50) DEFAULT NULL COMMENT '单位',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `assess_base_date` datetime DEFAULT NULL COMMENT '评估基准日',
  `area` decimal(10,2) DEFAULT NULL COMMENT '面积',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164105 DEFAULT CHARSET=utf8 COMMENT='网络案例记录表';

-- ----------------------------
-- Table structure for tb_net_info_record_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_net_info_record_content`;
CREATE TABLE `tb_net_info_record_content` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `record_id` int(11) DEFAULT NULL COMMENT '记录id',
  `full_description` text COMMENT '完整描述',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101495 DEFAULT CHARSET=utf8 COMMENT='网络案例内容明细表';

-- ----------------------------
-- Table structure for tb_project_change_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_change_log`;
CREATE TABLE `tb_project_change_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `old_record` json DEFAULT NULL COMMENT '原始记录',
  `new_record` json DEFAULT NULL COMMENT '变更后记录',
  `change_reason` varchar(200) DEFAULT NULL COMMENT '变更原因',
  `change_type` varchar(20) DEFAULT NULL COMMENT '变更类型(暂停、重启、项目信息变更、成员变更等枚举值)',
  `process_ins_id` varchar(64) DEFAULT '0' COMMENT '流程实例ID',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8 COMMENT='项目变更日志表';

-- ----------------------------
-- Table structure for tb_project_close
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_close`;
CREATE TABLE `tb_project_close` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `activity_name` varchar(200) DEFAULT NULL COMMENT '当前项目阶段',
  `reason` varchar(500) DEFAULT NULL COMMENT '原因',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(64) DEFAULT NULL COMMENT '状态',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目关闭记录';

-- ----------------------------
-- Table structure for tb_project_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_follow`;
CREATE TABLE `tb_project_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `user_account` varchar(10) DEFAULT NULL COMMENT '关注人',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否有效',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `follow_reason` varchar(100) DEFAULT NULL COMMENT '关注说明',
  `cancel_reason` varchar(100) DEFAULT NULL COMMENT '取消原因',
  `cancel_date` datetime DEFAULT NULL COMMENT '取消时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='个人关注项目';

-- ----------------------------
-- Table structure for tb_project_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_info`;
CREATE TABLE `tb_project_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_class_id` int(11) DEFAULT NULL COMMENT '项目大类',
  `project_type_id` int(11) DEFAULT NULL COMMENT '项目类型',
  `project_category_id` int(11) DEFAULT NULL COMMENT '项目类别',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `urgency` int(11) DEFAULT NULL COMMENT '紧急程度',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `valuation_date` datetime DEFAULT NULL COMMENT '评估基准日',
  `entrust_purpose` int(11) DEFAULT NULL COMMENT '委托目的',
  `entrust_aim_type` int(11) DEFAULT NULL COMMENT '委托目的类别',
  `remark_entrust_purpose` varchar(255) DEFAULT NULL COMMENT '委托说明备注',
  `value_type` int(11) DEFAULT NULL COMMENT '价值类型',
  `remark_value_type` varchar(500) DEFAULT NULL COMMENT '价值定义',
  `department_id` int(11) DEFAULT NULL COMMENT '执业部门',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `complete_date_plan` datetime DEFAULT NULL COMMENT '计划完成时间',
  `complete_date_actual` datetime DEFAULT NULL COMMENT '实际完成日期',
  `complete_date_start` datetime DEFAULT NULL COMMENT '接收任务时间',
  `process_ins_id` varchar(100) DEFAULT NULL COMMENT '流程实例编号',
  `property_scope` varchar(255) DEFAULT NULL COMMENT '评估(财产)范围',
  `scope_include` varchar(255) DEFAULT NULL COMMENT '范围包含',
  `scope_not_include` varchar(255) DEFAULT NULL COMMENT '范围不包含',
  `loan_type` int(11) DEFAULT NULL COMMENT '贷款类型',
  `contract_id` varchar(500) DEFAULT NULL COMMENT '合同id',
  `contract_name` varchar(255) DEFAULT NULL COMMENT '合同名称',
  `contract_price` decimal(12,2) DEFAULT NULL COMMENT '合同金额',
  `service_come_from` varchar(255) DEFAULT NULL COMMENT '业务来源',
  `service_come_from_explain` varchar(500) DEFAULT NULL COMMENT '业务来源说明',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  `project_status` varchar(10) DEFAULT NULL COMMENT '项目状态',
  `public_project_id` int(11) DEFAULT NULL COMMENT 'ERP中公共项目ID',
  `assign_process_ins_id` varchar(64) DEFAULT NULL COMMENT '任务再分派流程实例编号',
  `assign_status` varchar(10) DEFAULT NULL COMMENT '任务再分派流程状态',
  `estate_name` varchar(50) DEFAULT NULL COMMENT '楼盘名称',
  `preaudit_number_date` datetime DEFAULT NULL COMMENT '出预评报告时间',
  `result_number_date` datetime DEFAULT NULL COMMENT '出结果报告文号时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=696 DEFAULT CHARSET=utf8 COMMENT='项目信息';

-- ----------------------------
-- Table structure for tb_project_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_member`;
CREATE TABLE `tb_project_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `user_account_manager` varchar(200) DEFAULT NULL COMMENT '项目经理',
  `user_account_member` varchar(200) DEFAULT NULL COMMENT '项目成员',
  `user_account_quality` varchar(200) DEFAULT NULL COMMENT '质量控制人员',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否有效',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注说明',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=628 DEFAULT CHARSET=utf8 COMMENT='项目成员信息';

-- ----------------------------
-- Table structure for tb_project_member_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_member_history`;
CREATE TABLE `tb_project_member_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `user_account_manager_new` varchar(200) DEFAULT NULL COMMENT '项目经理',
  `user_account_member_new` varchar(200) DEFAULT NULL COMMENT '项目成员',
  `user_account_quality_new` varchar(200) DEFAULT NULL COMMENT '质量控制人员',
  `user_account_manager_old` varchar(200) DEFAULT NULL COMMENT '项目经理修改前',
  `user_account_member_old` varchar(200) DEFAULT NULL COMMENT '项目成员修改前',
  `user_account_quality_old` varchar(200) DEFAULT NULL COMMENT '质量控制人员修改前',
  `creator` varchar(50) DEFAULT NULL COMMENT '修改人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='项目组成员修改记录';

-- ----------------------------
-- Table structure for tb_project_number_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_number_record`;
CREATE TABLE `tb_project_number_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `report_type` int(11) DEFAULT NULL COMMENT '报告类型',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `number_value` varchar(100) DEFAULT NULL COMMENT '文号',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `bis_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8 COMMENT='文号规则';

-- ----------------------------
-- Table structure for tb_project_number_record_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_number_record_history`;
CREATE TABLE `tb_project_number_record_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `report_type` int(11) DEFAULT NULL COMMENT '报告类型',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `number_value` varchar(100) DEFAULT NULL COMMENT '文号',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8 COMMENT='文号规则';

-- ----------------------------
-- Table structure for tb_project_phase
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_phase`;
CREATE TABLE `tb_project_phase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '上级编号',
  `project_class_id` int(11) DEFAULT NULL COMMENT '项目类型',
  `project_type_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目类别',
  `project_category_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目范围',
  `work_stage_id` int(11) NOT NULL COMMENT '阶段类',
  `assets_setting_id` int(11) DEFAULT NULL COMMENT '资产字段配置',
  `project_phase_name` varchar(255) NOT NULL COMMENT '公司事项名称',
  `phase_time` decimal(13,1) NOT NULL DEFAULT '0.0' COMMENT '计划工时',
  `phase_form` varchar(100) DEFAULT NULL COMMENT '阶段表单路径',
  `box_name` varchar(50) DEFAULT NULL COMMENT '模型',
  `phase_key` varchar(100) DEFAULT NULL COMMENT '标识事项类型的唯一key',
  `phase_sort` int(11) NOT NULL COMMENT '排序',
  `bis_wait` bit(1) DEFAULT b'0' COMMENT '审批之后是否允许任务被挂起',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `bis_use_box` bit(1) DEFAULT b'0' COMMENT '是否使用流程',
  `bis_can_return` bit(1) DEFAULT b'0' COMMENT '是否允许退回',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`),
  KEY `tb_project_phase_box` (`box_name`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COMMENT='工作事项';

-- ----------------------------
-- Table structure for tb_project_plan
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_plan`;
CREATE TABLE `tb_project_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `process_ins_id` varchar(11) DEFAULT NULL COMMENT '流程实例编号',
  `work_stage_id` int(11) DEFAULT NULL COMMENT '阶段编号',
  `stage_sort` int(11) DEFAULT NULL COMMENT '执行顺序',
  `category_id` int(11) DEFAULT NULL COMMENT '项目类别编号',
  `plan_name` varchar(100) DEFAULT NULL COMMENT '项目计划名称',
  `plan_remarks` varchar(255) DEFAULT NULL COMMENT '计划说明',
  `project_plan_start` datetime DEFAULT NULL COMMENT '计划总体开始日期',
  `project_plan_end` datetime DEFAULT NULL COMMENT '总体计划结束日期',
  `process_ins_id_approval` varchar(11) DEFAULT NULL COMMENT '计划审批流程编号',
  `status` varchar(10) DEFAULT '' COMMENT '流程状态',
  `approval_status` varchar(10) DEFAULT '' COMMENT '审批流程状态',
  `restart_reason` varchar(255) DEFAULT NULL COMMENT '重启阶段原因',
  `bis_restart` bit(1) DEFAULT b'0' COMMENT '是否重启',
  `bis_auto_complete` bit(1) DEFAULT b'0' COMMENT '是否自动完成',
  `finish_date` datetime DEFAULT NULL COMMENT '完成日期',
  `project_status` varchar(20) DEFAULT NULL COMMENT '项目状态',
  `specific_gravity` int(11) DEFAULT '100' COMMENT '权重',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT NULL COMMENT '最后一次修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3942 DEFAULT CHARSET=utf8 COMMENT='项目计划';

-- ----------------------------
-- Table structure for tb_project_plan_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_plan_details`;
CREATE TABLE `tb_project_plan_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT '0' COMMENT '上级编号',
  `project_phase_name` varchar(8000) DEFAULT NULL COMMENT '工作事项',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `plan_id` int(11) DEFAULT NULL COMMENT '项目计划编号',
  `project_work_stage_id` int(11) DEFAULT NULL COMMENT '工作阶段编号',
  `project_phase_id` int(11) DEFAULT NULL COMMENT '任务节点编号',
  `project_phase_details_id` int(11) DEFAULT '0' COMMENT '工作内容编号',
  `plan_start_date` datetime DEFAULT NULL COMMENT '计划开始日期',
  `plan_end_date` datetime DEFAULT NULL COMMENT '计划结束日期',
  `plan_hours` decimal(18,2) DEFAULT NULL COMMENT '计划工时',
  `plan_remarks` varchar(100) DEFAULT NULL COMMENT '计划说明',
  `execute_user_account` varchar(10) DEFAULT NULL COMMENT '执行人',
  `execute_department_id` int(11) DEFAULT NULL COMMENT '执行部门编号',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `proportion` decimal(18,2) DEFAULT NULL COMMENT '权重占比',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `first_pid` int(11) DEFAULT '0' COMMENT '第一级上级，如果为第一级则为0',
  `bis_start` bit(1) DEFAULT b'0' COMMENT '任务是否已开始',
  `process_ins_id` varchar(10) DEFAULT '0' COMMENT '流程实例编号',
  `task_submit_time` datetime DEFAULT NULL COMMENT '最终成果提交时间',
  `task_remarks` varchar(500) DEFAULT NULL COMMENT '工作成果描述',
  `actual_hours` decimal(13,4) DEFAULT NULL COMMENT '实际工时',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  `return_details_id` int(11) DEFAULT '0' COMMENT '退回前任务编号',
  `return_details_reason` varchar(255) DEFAULT NULL COMMENT '退回说明',
  `bis_new` bit(1) DEFAULT b'1' COMMENT '是否最新任务',
  `bis_last_layer` bit(1) DEFAULT b'1' COMMENT '是否最后一个层级',
  `bis_restart` bit(1) DEFAULT b'0' COMMENT '是否为重启任务',
  `declare_record_id` int(11) DEFAULT NULL COMMENT '申报记录id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16216 DEFAULT CHARSET=utf8 COMMENT='项目计划明细';

-- ----------------------------
-- Table structure for tb_project_plan_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_plan_history`;
CREATE TABLE `tb_project_plan_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `plan_id` int(11) DEFAULT NULL COMMENT '原计划编号',
  `project_phase_name` varchar(255) DEFAULT NULL,
  `before_plan_start` datetime DEFAULT NULL COMMENT '原开始日期',
  `before_plan_end` datetime DEFAULT NULL COMMENT '原结束日期',
  `before_plan_remarks` varchar(255) DEFAULT NULL COMMENT '原备注说明',
  `after_plan_start` datetime DEFAULT NULL COMMENT '变更后开始日期',
  `after_plan_end` datetime DEFAULT NULL COMMENT '变更后结束日期',
  `after_plan_remarks` varchar(255) DEFAULT NULL COMMENT '变更后说明',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目记录历史';

-- ----------------------------
-- Table structure for tb_project_plan_task_all
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_plan_task_all`;
CREATE TABLE `tb_project_plan_task_all` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `project_plan_id` int(11) DEFAULT NULL COMMENT '项目计划编号',
  `project_work_stage_id` int(11) DEFAULT NULL COMMENT '工作阶段编号',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='整体提交记录';

-- ----------------------------
-- Table structure for tb_project_qrcode_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_qrcode_record`;
CREATE TABLE `tb_project_qrcode_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `report_type` int(11) DEFAULT NULL COMMENT '报告类型',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `qrcode` text COMMENT '二维码',
  `project_document_id` int(11) DEFAULT NULL COMMENT 'erp二维码报告id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8 COMMENT='文号规则';

-- ----------------------------
-- Table structure for tb_project_subsequent
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_subsequent`;
CREATE TABLE `tb_project_subsequent` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `suggestion` varchar(500) DEFAULT NULL COMMENT '处理意见',
  `process_ins_id` varchar(64) DEFAULT '0' COMMENT '流程实例ID',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='项目后续事项';

-- ----------------------------
-- Table structure for tb_project_suspend
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_suspend`;
CREATE TABLE `tb_project_suspend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `suspend_user_account` varchar(10) DEFAULT NULL COMMENT '暂时人',
  `supend_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `suspend_reason` varchar(200) DEFAULT NULL COMMENT '暂停原因',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '暂停是否有效',
  `restart_date` datetime DEFAULT NULL COMMENT '启用时间',
  `restart_user_account` varchar(10) DEFAULT NULL COMMENT '启动人',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目暂停';

-- ----------------------------
-- Table structure for tb_project_take_number
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_take_number`;
CREATE TABLE `tb_project_take_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(255) DEFAULT NULL COMMENT '流程id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `report_type` int(11) DEFAULT NULL COMMENT '报告类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `status` varchar(100) DEFAULT NULL COMMENT '状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `number_record_id` int(11) DEFAULT NULL COMMENT '记录id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='项目拿号';

-- ----------------------------
-- Table structure for tb_project_task_return_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_task_return_record`;
CREATE TABLE `tb_project_task_return_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT 'planDetailsId',
  `reason` varchar(200) DEFAULT NULL COMMENT '重启原因',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `return_person` varchar(50) DEFAULT NULL COMMENT '重启人',
  `return_time` datetime DEFAULT NULL COMMENT '重启时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COMMENT='重启记录';

-- ----------------------------
-- Table structure for tb_project_work_stage
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_work_stage`;
CREATE TABLE `tb_project_work_stage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `project_class_id` int(11) DEFAULT NULL COMMENT '项目类型',
  `project_type_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目类别',
  `project_category_id` int(11) DEFAULT '0' COMMENT '项目范围',
  `work_stage_name` varchar(255) NOT NULL COMMENT '阶段名称',
  `stage_form` varchar(100) DEFAULT NULL COMMENT '阶段表单路径',
  `box_name` varchar(50) DEFAULT NULL COMMENT '计划模型',
  `stage_sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `bis_load_defalut` bit(1) DEFAULT b'1' COMMENT '是否加载默认工作事项',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `box_role_type` varchar(10) DEFAULT NULL COMMENT '角色分类1、部门角色，2、公用角色',
  `box_role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  `box_role_key` varchar(100) DEFAULT NULL COMMENT '公用角色KEY',
  `box_role_name` varchar(100) DEFAULT NULL COMMENT '角色',
  `allow_issued` bit(1) DEFAULT b'0' COMMENT '允许下发',
  `specific_gravity` int(11) NOT NULL DEFAULT '100' COMMENT '比重',
  `review_box_name` varchar(50) DEFAULT NULL COMMENT '复核模型',
  PRIMARY KEY (`id`),
  KEY `tb_work_stage_box_name` (`box_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='项目阶段';

-- ----------------------------
-- Table structure for tb_project_work_stage_restart
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_work_stage_restart`;
CREATE TABLE `tb_project_work_stage_restart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `project_restart_stage_id` int(11) DEFAULT NULL COMMENT '重启阶段编号',
  `project_restart_stage_name` varchar(100) DEFAULT NULL COMMENT '重启阶段名称',
  `project_plan_old_id` int(11) DEFAULT NULL COMMENT '原项目计划编号',
  `project_plan_id` int(11) DEFAULT NULL COMMENT '新建计划的计划编号',
  `project_this_work_stage` varchar(100) DEFAULT NULL COMMENT '当前阶段',
  `restart_reason` varchar(255) DEFAULT NULL COMMENT '重启原因',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例编号',
  `status` varchar(10) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(10) DEFAULT NULL COMMENT '创建人',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='项目阶段重启';

-- ----------------------------
-- Table structure for tb_report_project_debt
-- ----------------------------
DROP TABLE IF EXISTS `tb_report_project_debt`;
CREATE TABLE `tb_report_project_debt` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `public_project_id` int(11) DEFAULT NULL COMMENT '项目公共id',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `consignor_name` varchar(255) DEFAULT NULL COMMENT '委托人',
  `entrust_purpose_name` varchar(255) DEFAULT NULL COMMENT '委托目的',
  `department_name` varchar(255) DEFAULT NULL COMMENT '评估部门',
  `loan_type_name` varchar(255) DEFAULT NULL COMMENT '贷款类型',
  `report_use_unit_name` varchar(255) DEFAULT NULL COMMENT '报告使用单位',
  `preaudit_number` varchar(255) DEFAULT NULL COMMENT '预评报告文号',
  `result_number` varchar(255) DEFAULT NULL COMMENT '结果报告文号',
  `project_manager_name` varchar(255) DEFAULT NULL COMMENT '项目经理',
  `contract_price` decimal(18,2) DEFAULT NULL,
  `amount` decimal(18,2) DEFAULT NULL COMMENT '开票金额',
  `actual_amount` decimal(18,2) DEFAULT NULL COMMENT '实际开票金额（总金额）',
  `pay_amount` decimal(18,2) DEFAULT NULL,
  `debt_amount` decimal(18,2) DEFAULT NULL COMMENT '欠款金额',
  `bis_has_debt` tinyint(1) DEFAULT '1' COMMENT '是否有欠款',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2249 DEFAULT CHARSET=utf8 COMMENT='项目欠款报表';

-- ----------------------------
-- Table structure for tb_scheme_area_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_area_group`;
CREATE TABLE `tb_scheme_area_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `area_name` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `value_time_point` datetime DEFAULT NULL COMMENT '评估基准日',
  `time_point_explain` varchar(255) DEFAULT NULL COMMENT '评估基准日说明',
  `entrust_purpose` int(11) DEFAULT NULL COMMENT '委托目的',
  `remark_entrust_purpose` varchar(255) DEFAULT NULL COMMENT '委托目的描述',
  `value_definition` int(11) DEFAULT NULL COMMENT '价值类型',
  `value_definition_desc` varchar(500) DEFAULT NULL COMMENT '价值类型描述',
  `value_connotation` varchar(100) DEFAULT NULL COMMENT '价值内涵',
  `value_connotation_desc` varchar(500) DEFAULT NULL COMMENT '价值内涵描述',
  `property_scope` int(11) DEFAULT NULL COMMENT '评估(财产)范围',
  `scope_include` varchar(255) DEFAULT NULL COMMENT '范围包含',
  `scope_not_include` varchar(255) DEFAULT NULL COMMENT '范围不包含',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_merge` bit(1) DEFAULT NULL COMMENT '是否为合并区域',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=421 DEFAULT CHARSET=utf8 COMMENT='方案区域分组';

-- ----------------------------
-- Table structure for tb_scheme_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_info`;
CREATE TABLE `tb_scheme_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `method_type` int(11) DEFAULT NULL COMMENT '方法类型',
  `method_data_id` int(11) DEFAULT NULL COMMENT '方法测算结果记录数据id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=643 DEFAULT CHARSET=utf8 COMMENT='方案信息';

-- ----------------------------
-- Table structure for tb_scheme_judge_function
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_judge_function`;
CREATE TABLE `tb_scheme_judge_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_group_id` int(11) DEFAULT NULL COMMENT '区域id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `name` varchar(50) DEFAULT NULL COMMENT '方法的名称',
  `method_type` int(11) DEFAULT NULL COMMENT '评估方法类别',
  `bis_applicable` bit(1) DEFAULT NULL COMMENT '是否适用',
  `applicable_reason` varchar(2000) DEFAULT NULL COMMENT '适用原因',
  `not_applicable_reason` varchar(2000) DEFAULT NULL COMMENT '不适用原因',
  `thinking` varchar(2000) DEFAULT NULL COMMENT '评估思路',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1490 DEFAULT CHARSET=utf8 COMMENT='估计对象评估方法与思路';

-- ----------------------------
-- Table structure for tb_scheme_judge_object
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_judge_object`;
CREATE TABLE `tb_scheme_judge_object` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `area_group_id` int(11) DEFAULT NULL COMMENT '区域分组id',
  `original_area_group_id` int(11) DEFAULT NULL COMMENT '原始区域id',
  `declare_record_id` int(11) DEFAULT NULL COMMENT '申报记录id',
  `number` varchar(1000) DEFAULT NULL COMMENT '委估对象编号',
  `original_number` varchar(100) DEFAULT NULL COMMENT '原委估对象编号',
  `split_number` int(11) DEFAULT NULL COMMENT '拆分编号',
  `name` varchar(2000) DEFAULT NULL COMMENT '权证名称',
  `cert_name` varchar(255) DEFAULT NULL COMMENT '权证名称',
  `ownership` varchar(200) DEFAULT NULL COMMENT '所有权人',
  `seat` varchar(2000) DEFAULT NULL COMMENT '座落',
  `cert_use` varchar(100) DEFAULT NULL COMMENT '证载用途',
  `practical_use` varchar(100) DEFAULT NULL COMMENT '实际用途',
  `land_cert_use` varchar(100) DEFAULT NULL COMMENT '土地证载用途',
  `land_practical_use` varchar(100) DEFAULT NULL COMMENT '土地实际用途',
  `land_use_end_date` datetime DEFAULT NULL COMMENT '土地终止时间',
  `land_legal_year` decimal(11,2) DEFAULT NULL COMMENT '土地法定年限',
  `land_remaining_year` decimal(11,2) DEFAULT NULL COMMENT '土地剩余年限',
  `set_use` int(11) DEFAULT NULL COMMENT '设定用途',
  `best_use` int(11) DEFAULT NULL COMMENT '最佳利用设置',
  `floor_area` decimal(18,2) DEFAULT NULL COMMENT '证载面积',
  `evaluation_area` decimal(18,2) DEFAULT NULL COMMENT '评估面积',
  `price` decimal(13,2) DEFAULT NULL COMMENT '委估对象单价',
  `original_price` decimal(13,2) DEFAULT NULL COMMENT '原单价',
  `set_plot_ratio` decimal(13,2) DEFAULT NULL COMMENT '设定容积率',
  `plan_plot_ratio` decimal(13,2) DEFAULT NULL COMMENT '规划容积率',
  `actual_plot_ratio` decimal(13,2) DEFAULT NULL COMMENT '实际容积率',
  `standard_judge_id` int(11) DEFAULT NULL COMMENT '标准估价对象id',
  `standard_judge_explain` varchar(255) DEFAULT NULL COMMENT '设置为标准估价对象的说明',
  `not_applicable_reason` varchar(2000) DEFAULT NULL COMMENT '评估方法不适用原因',
  `bis_split` bit(1) DEFAULT NULL COMMENT '是否为拆分',
  `bis_merge` bit(1) DEFAULT b'0' COMMENT '是否为合并的委估对象',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否可用',
  `bis_set_function` bit(1) DEFAULT b'0' COMMENT '是否已设置评估方法',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=702 DEFAULT CHARSET=utf8 COMMENT='估价对象';

-- ----------------------------
-- Table structure for tb_scheme_liquidation_analysis
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_liquidation_analysis`;
CREATE TABLE `tb_scheme_liquidation_analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `total` decimal(18,2) DEFAULT NULL COMMENT '合计值',
  `liquid_ratios` varchar(50) DEFAULT NULL COMMENT '变现比率',
  `liquid_time` varchar(50) DEFAULT NULL COMMENT '变现时间',
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COMMENT='变现分析税费';

-- ----------------------------
-- Table structure for tb_scheme_liquidation_analysis_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_liquidation_analysis_group`;
CREATE TABLE `tb_scheme_liquidation_analysis_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `record_ids` varchar(255) DEFAULT NULL COMMENT '权证id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `total` decimal(18,2) DEFAULT NULL COMMENT '合计值',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='变现分析税费';

-- ----------------------------
-- Table structure for tb_scheme_liquidation_analysis_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_liquidation_analysis_item`;
CREATE TABLE `tb_scheme_liquidation_analysis_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `group_id` int(11) DEFAULT NULL COMMENT '分组id',
  `type_key` varchar(255) DEFAULT NULL,
  `tax_rate_id` int(11) DEFAULT NULL COMMENT '税率id',
  `tax_rate_value` varchar(100) DEFAULT NULL COMMENT '税率的值',
  `calculation_method` int(11) DEFAULT NULL COMMENT '计算方式(0绝对值 1相对值)',
  `tax_rate_name` varchar(100) DEFAULT NULL COMMENT '税率名称',
  `calculate_base` varchar(255) DEFAULT NULL COMMENT '计算基数',
  `calculation_formula` varchar(255) DEFAULT NULL COMMENT '计算公式',
  `taxes_burden` varchar(255) DEFAULT NULL COMMENT '税费负担方',
  `price` decimal(18,2) DEFAULT NULL COMMENT '计算的价格',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8 COMMENT='变现分析税费明细';

-- ----------------------------
-- Table structure for tb_scheme_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_reimbursement`;
CREATE TABLE `tb_scheme_reimbursement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `area_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8 COMMENT='法定优先受偿款';

-- ----------------------------
-- Table structure for tb_scheme_reimbursement_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_reimbursement_item`;
CREATE TABLE `tb_scheme_reimbursement_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称如(1,2号)来自估价对象',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `master_id` int(11) DEFAULT NULL COMMENT '主表',
  `inventory_right_record_id` int(11) DEFAULT NULL COMMENT 'tb_survey_asset_inventory_right_record(他项权力组id)他项权利组对应一个SchemeReimbursementItem',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `not_set_up_unit_price` decimal(18,2) DEFAULT NULL COMMENT '未设立单价',
  `not_set_up_total_price` decimal(18,2) DEFAULT NULL COMMENT '未设立总价',
  `know_total_price` decimal(18,2) DEFAULT NULL COMMENT '知悉总价',
  `mortgaged_total_price` decimal(18,2) DEFAULT NULL COMMENT '已抵押总价',
  `owed_total_price` decimal(18,2) DEFAULT NULL COMMENT '拖欠总价',
  `other_total_price` decimal(18,2) DEFAULT NULL COMMENT '其它受偿款总价',
  `mortgage_unit_price` decimal(18,2) DEFAULT NULL COMMENT '抵押价值单价',
  `mortgage_total_price` decimal(18,2) DEFAULT NULL COMMENT '抵押价值总价',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COMMENT='法定优先受偿款明细';

-- ----------------------------
-- Table structure for tb_scheme_report_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_report_file`;
CREATE TABLE `tb_scheme_report_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `area_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='报告附件';

-- ----------------------------
-- Table structure for tb_scheme_report_file_custom
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_report_file_custom`;
CREATE TABLE `tb_scheme_report_file_custom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `declare_record_id` int(11) DEFAULT NULL COMMENT '权证id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='报告自定义块附件';

-- ----------------------------
-- Table structure for tb_scheme_report_file_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_report_file_item`;
CREATE TABLE `tb_scheme_report_file_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_details_id` int(11) DEFAULT NULL,
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `attachment_id` int(11) DEFAULT NULL COMMENT '附件id',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `declare_record_id` int(11) DEFAULT NULL COMMENT '权证id',
  `certify_part` int(11) DEFAULT NULL COMMENT '对应查勘部位',
  `certify_part_category` int(11) DEFAULT NULL COMMENT '附件类别',
  `bis_enable` bit(1) DEFAULT b'1' COMMENT '是否上报告',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=utf8 COMMENT='报告附件明细项';

-- ----------------------------
-- Table structure for tb_scheme_support
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_support`;
CREATE TABLE `tb_scheme_support` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8 COMMENT='方案信息';

-- ----------------------------
-- Table structure for tb_scheme_support_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_support_info`;
CREATE TABLE `tb_scheme_support_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `plan_details_id` int(11) DEFAULT NULL,
  `support_type` int(11) DEFAULT NULL COMMENT '类型 0假设 1原则 2依据 3利用描述 4价值时点',
  `support_type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `template` varchar(2000) DEFAULT NULL COMMENT '模板内容',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `json_content` json DEFAULT NULL COMMENT '字段保存的信息',
  `bis_modifiable` bit(1) DEFAULT b'0' COMMENT '是否可编辑',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1617 DEFAULT CHARSET=utf8 COMMENT='方案支撑信息 依据 假设 原则等';

-- ----------------------------
-- Table structure for tb_scheme_sure_price
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_sure_price`;
CREATE TABLE `tb_scheme_sure_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `weight_explain` varchar(200) DEFAULT NULL COMMENT '权重说明',
  `price` decimal(13,2) DEFAULT NULL COMMENT '委估对象单价',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COMMENT='确定单价';

-- ----------------------------
-- Table structure for tb_scheme_sure_price_factor
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_sure_price_factor`;
CREATE TABLE `tb_scheme_sure_price_factor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `declare_id` int(11) DEFAULT NULL COMMENT '申报记录id',
  `judge_object_id` int(11) DEFAULT NULL COMMENT '估价对象id',
  `factor` varchar(100) DEFAULT NULL COMMENT '因素',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `coefficient` decimal(13,4) DEFAULT NULL COMMENT '系数',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8 COMMENT='确定单价';

-- ----------------------------
-- Table structure for tb_scheme_sure_price_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_sure_price_item`;
CREATE TABLE `tb_scheme_sure_price_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judge_object_id` int(11) DEFAULT NULL COMMENT '委估对象id',
  `method_type` int(11) DEFAULT NULL COMMENT '方法类型',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `trial_price` decimal(13,2) DEFAULT NULL COMMENT '试算价格',
  `weight` decimal(18,4) DEFAULT NULL COMMENT '权重',
  `error_rate` decimal(18,4) DEFAULT NULL COMMENT '误差率',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1164 DEFAULT CHARSET=utf8 COMMENT='确定单价';

-- ----------------------------
-- Table structure for tb_scheme_sure_price_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_scheme_sure_price_record`;
CREATE TABLE `tb_scheme_sure_price_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '方法名称',
  `record_price` decimal(13,2) DEFAULT NULL COMMENT '记录的单价',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='确定单价记录';

-- ----------------------------
-- Table structure for tb_survey_asset_inventory
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_asset_inventory`;
CREATE TABLE `tb_survey_asset_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_detail_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `declare_record_id` int(11) DEFAULT NULL COMMENT '申报记录id',
  `evaluator` varchar(255) DEFAULT NULL COMMENT '评估人员',
  `check_date` datetime DEFAULT NULL COMMENT '核对日期',
  `bis_check_original` bit(1) DEFAULT NULL COMMENT '是否查看原件',
  `remark` varchar(500) DEFAULT NULL COMMENT '说明',
  `special_case` varchar(500) DEFAULT NULL COMMENT '特殊情况',
  `location` json DEFAULT NULL COMMENT '定位信息',
  `segmentation_limit` varchar(50) DEFAULT NULL COMMENT '分割限制',
  `can_use` varchar(50) DEFAULT NULL COMMENT '能否使用',
  `application` int(11) DEFAULT NULL COMMENT '用途',
  `certificate` varchar(50) DEFAULT NULL COMMENT '是否办证',
  `rim_is_normal` varchar(20) DEFAULT NULL COMMENT '周边环境是否正常',
  `zone_damage` varchar(255) DEFAULT NULL COMMENT '区位损坏调查',
  `abnormality` varchar(255) DEFAULT NULL COMMENT '不正常原因',
  `entity_is_damage` varchar(255) DEFAULT NULL COMMENT '实物状况是否损坏',
  `entity_damage` varchar(255) DEFAULT NULL COMMENT '实物损坏调查',
  `damage_remark` varchar(255) DEFAULT NULL COMMENT '损坏原因',
  `payment_status` varchar(50) DEFAULT NULL COMMENT '缴纳情况',
  `payment_content` varchar(255) DEFAULT NULL COMMENT '缴纳明细',
  `transfer_limit` varchar(255) DEFAULT NULL COMMENT '转让限制',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `other_project` varchar(255) DEFAULT NULL COMMENT '影响评估的其他事项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8 COMMENT='资产清查';

-- ----------------------------
-- Table structure for tb_survey_asset_inventory_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_asset_inventory_content`;
CREATE TABLE `tb_survey_asset_inventory_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `declare_id` int(11) DEFAULT NULL COMMENT '申报id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `inventory_content` int(11) DEFAULT NULL COMMENT '清查内容',
  `are_consistent` varchar(50) DEFAULT '不一致' COMMENT '是否一致',
  `registration` varchar(255) DEFAULT NULL COMMENT '登记地址',
  `actual` varchar(255) DEFAULT NULL COMMENT '实际地址',
  `difference_reason` varchar(255) DEFAULT NULL COMMENT '差异原因',
  `credential` varchar(255) DEFAULT NULL COMMENT '证明文件',
  `credential_accessory` varchar(255) DEFAULT NULL COMMENT '证明文件附件',
  `voucher` varchar(50) DEFAULT NULL COMMENT '证明人',
  `survey_time` datetime DEFAULT NULL COMMENT '调查时间',
  `sure_consistent` varchar(50) DEFAULT NULL COMMENT '确认一致',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2308 DEFAULT CHARSET=utf8 COMMENT='清查核对记录';

-- ----------------------------
-- Table structure for tb_survey_asset_inventory_right
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_asset_inventory_right`;
CREATE TABLE `tb_survey_asset_inventory_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `inventory_right_record_id` int(12) DEFAULT NULL COMMENT 'tb_survey_asset_inventory_right_record id',
  `cert_name` varchar(255) DEFAULT NULL COMMENT '权证名称',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `number` varchar(100) DEFAULT NULL COMMENT '他权证编号',
  `obligor` varchar(50) DEFAULT NULL COMMENT '义务人',
  `obligee` varchar(50) DEFAULT NULL COMMENT '权利人',
  `register_area` varchar(255) DEFAULT NULL COMMENT '登记面积',
  `right_rank` varchar(255) DEFAULT NULL COMMENT '他权级次',
  `register_amount` varchar(255) DEFAULT NULL COMMENT '登记金额',
  `actual_amount` varchar(255) DEFAULT NULL COMMENT '行权金额',
  `register_date` datetime DEFAULT NULL COMMENT '他项权力登记日期',
  `begin_date` datetime DEFAULT NULL COMMENT '实际行权人行权日期',
  `end_date` datetime DEFAULT NULL COMMENT '预计到期日',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `influence` varchar(500) DEFAULT NULL COMMENT '对变现能力的影响',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=605 DEFAULT CHARSET=utf8 COMMENT='他项权利';

-- ----------------------------
-- Table structure for tb_survey_asset_inventory_right_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_asset_inventory_right_record`;
CREATE TABLE `tb_survey_asset_inventory_right_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `inventory_right_record_center_id` int(12) DEFAULT NULL,
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `specialCase` varchar(500) DEFAULT NULL COMMENT '特殊情况',
  `record_ids` varchar(255) DEFAULT NULL COMMENT '申报id列表',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=856 DEFAULT CHARSET=utf8 COMMENT='他项权利申报表(他权组)';

-- ----------------------------
-- Table structure for tb_survey_asset_inventory_right_record_center
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_asset_inventory_right_record_center`;
CREATE TABLE `tb_survey_asset_inventory_right_record_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=703 DEFAULT CHARSET=utf8 COMMENT='他项权利申报表(center)';

-- ----------------------------
-- Table structure for tb_survey_case_study
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_case_study`;
CREATE TABLE `tb_survey_case_study` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `declare_id` int(11) DEFAULT NULL COMMENT '申报id',
  `basic_apply_id` int(11) DEFAULT NULL,
  `json_content` json DEFAULT NULL COMMENT '同步更新的权证数据',
  `location` json DEFAULT NULL COMMENT '定位信息',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=552 DEFAULT CHARSET=utf8 COMMENT='案例';

-- ----------------------------
-- Table structure for tb_survey_examine_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_examine_info`;
CREATE TABLE `tb_survey_examine_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `examine_type` int(11) DEFAULT NULL COMMENT '调查类型 0查勘 1案例',
  `examine_form_type` varchar(50) DEFAULT NULL COMMENT '调查表单类型 ',
  `transaction_type` int(11) DEFAULT NULL COMMENT '交易类型',
  `declare_record_id` int(11) DEFAULT NULL COMMENT '申报记录id',
  `bis_assignment` bit(1) DEFAULT NULL COMMENT '是否已分派',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=914 DEFAULT CHARSET=utf8 COMMENT='查勘';

-- ----------------------------
-- Table structure for tb_survey_examine_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_examine_task`;
CREATE TABLE `tb_survey_examine_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `data_task_id` int(11) DEFAULT NULL COMMENT '调查配置任务id',
  `plan_details_id` int(11) DEFAULT NULL,
  `declare_id` int(11) DEFAULT NULL COMMENT '申报记录id',
  `examine_type` int(11) DEFAULT NULL COMMENT '调查类型 0查勘 1案例',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `user_account` varchar(100) DEFAULT NULL COMMENT '责任人',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `bis_must` bit(1) DEFAULT NULL COMMENT '是否必须填写项',
  `task_status` varchar(50) DEFAULT NULL COMMENT '任务状态',
  `bis_show` bit(1) DEFAULT b'0' COMMENT '是否显示',
  `bis_record_data` bit(1) DEFAULT b'0' COMMENT '是否记录了数据',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28203 DEFAULT CHARSET=utf8 COMMENT='调查任务配置表';

-- ----------------------------
-- Table structure for tb_survey_scene_explore
-- ----------------------------
DROP TABLE IF EXISTS `tb_survey_scene_explore`;
CREATE TABLE `tb_survey_scene_explore` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `plan_details_id` int(11) DEFAULT NULL COMMENT '计划明细id',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT '流程实例id',
  `declare_id` int(11) DEFAULT NULL COMMENT '申报id',
  `basic_apply_id` int(11) DEFAULT NULL COMMENT 'basic申请id',
  `json_content` json DEFAULT NULL COMMENT '同步更新的权证数据',
  `location` json DEFAULT NULL COMMENT '定位信息',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  `batch_apply_id` int(11) DEFAULT NULL COMMENT '批量申请表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=749 DEFAULT CHARSET=utf8 COMMENT='案例';

-- ----------------------------
-- Table structure for tb_task_case_assign
-- ----------------------------
DROP TABLE IF EXISTS `tb_task_case_assign`;
CREATE TABLE `tb_task_case_assign` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `executor` varchar(50) DEFAULT NULL COMMENT '执行任务人员',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '区',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `lp_info` varchar(255) DEFAULT NULL COMMENT '楼盘信息',
  `process_ins_id` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT '流程状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='案例参考数据分派任务';

-- ----------------------------
-- Table structure for tb_tool_residue_ratio
-- ----------------------------
DROP TABLE IF EXISTS `tb_tool_residue_ratio`;
CREATE TABLE `tb_tool_residue_ratio` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0年限法 1观察法 2综合法',
  `house_id` int(11) DEFAULT NULL,
  `parameter_value` json DEFAULT NULL COMMENT '参数值',
  `used_year` decimal(18,2) DEFAULT NULL COMMENT '已用年限',
  `usable_year` decimal(18,2) DEFAULT NULL COMMENT '可用年限',
  `observe_rate` decimal(18,2) DEFAULT NULL COMMENT '观察权重',
  `age_rate` decimal(18,2) DEFAULT NULL COMMENT '年限权重',
  `result_value` varchar(50) DEFAULT NULL COMMENT '结果值',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COMMENT='成新法';

-- ----------------------------
-- Table structure for tb_tool_residue_ratio_observe
-- ----------------------------
DROP TABLE IF EXISTS `tb_tool_residue_ratio_observe`;
CREATE TABLE `tb_tool_residue_ratio_observe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `master_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `category` int(11) DEFAULT NULL COMMENT '类别',
  `entity_condition` varchar(50) DEFAULT NULL COMMENT '实体状况',
  `entity_condition_content` varchar(1000) DEFAULT NULL COMMENT '实体状况内容',
  `standard_score` decimal(11,2) DEFAULT NULL COMMENT '标准得分',
  `score` decimal(11,2) DEFAULT NULL COMMENT '得分',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=utf8 COMMENT='报酬率观察法明细';

-- ----------------------------
-- Table structure for tb_tool_reward_rate
-- ----------------------------
DROP TABLE IF EXISTS `tb_tool_reward_rate`;
CREATE TABLE `tb_tool_reward_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parameter_value` json DEFAULT NULL COMMENT '参数值',
  `result_value` varchar(100) DEFAULT NULL COMMENT '结果值',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COMMENT='报酬率';

-- ----------------------------
-- View structure for vw_db_columns_info
-- ----------------------------
DROP VIEW IF EXISTS `vw_db_columns_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_db_columns_info` AS select `columns`.`COLUMN_NAME` AS `column_name`,`columns`.`COLUMN_COMMENT` AS `column_comment`,`columns`.`DATA_TYPE` AS `data_type`,`columns`.`TABLE_NAME` AS `table_name`,`columns`.`TABLE_SCHEMA` AS `table_schema` from `information_schema`.`COLUMNS` where (`columns`.`TABLE_SCHEMA` = 'pmcc_chks') ;

-- ----------------------------
-- View structure for vw_db_table_info
-- ----------------------------
DROP VIEW IF EXISTS `vw_db_table_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_db_table_info` AS select `tables`.`TABLE_NAME` AS `TABLE_NAME`,`tables`.`TABLE_COMMENT` AS `TABLE_COMMENT` from `information_schema`.`TABLES` where (`tables`.`TABLE_SCHEMA` = 'pmcc_chks') ;

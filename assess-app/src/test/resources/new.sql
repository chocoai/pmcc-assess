/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.206
Source Server Version : 50717
Source Host           : 192.168.2.206:3306
Source Database       : pmcc_hr

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-10-09 17:54:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_hr_admission_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_admission_bookmark`;
CREATE TABLE `tb_hr_admission_bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `template_id` int(11) DEFAULT NULL COMMENT ''模板id'',
  `template_name` varchar(100) DEFAULT NULL COMMENT ''模板名称'',
  `name` varchar(255) DEFAULT NULL COMMENT ''书签的名称'',
  `remarks` varchar(100) DEFAULT NULL COMMENT ''书签说明'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `field_name` varchar(100) DEFAULT NULL COMMENT ''字段'',
  `formater` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT=''面试通知模板书签'';

-- ----------------------------
-- Table structure for tb_hr_admission_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_admission_file`;
CREATE TABLE `tb_hr_admission_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `name` varchar(10) DEFAULT NULL COMMENT ''名称'',
  `remark` varchar(10) DEFAULT NULL COMMENT ''备注'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `template_apply_type` varchar(50) DEFAULT NULL COMMENT ''模板应用'',
  `template_apply_type_name` varchar(50) DEFAULT NULL COMMENT ''模板应用名称'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT=''面试通知模板'';

-- ----------------------------
-- Table structure for tb_hr_annual_leave_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_annual_leave_record`;
CREATE TABLE `tb_hr_annual_leave_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(100) DEFAULT NULL COMMENT ''员工账号'',
  `year` int(11) DEFAULT NULL COMMENT ''年份'',
  `hours` decimal(11,5) DEFAULT NULL COMMENT ''小时数'',
  `bis_exipre` bit(1) DEFAULT b''0'' COMMENT ''是否过期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1128 DEFAULT CHARSET=utf8 COMMENT=''年假记录'';

-- ----------------------------
-- Table structure for tb_hr_attendance_analyze
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_attendance_analyze`;
CREATE TABLE `tb_hr_attendance_analyze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL COMMENT ''年份'',
  `month` int(11) DEFAULT NULL COMMENT ''月份'',
  `user_name` varchar(50) DEFAULT NULL COMMENT ''姓名'',
  `user_account` varchar(50) DEFAULT NULL COMMENT ''账号'',
  `be_late_number` int(11) DEFAULT NULL COMMENT ''迟到次数'',
  `leave_early_number` int(11) DEFAULT NULL COMMENT ''早退次数'',
  `absenteeism_number` int(11) DEFAULT NULL COMMENT ''旷工次数'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  `clock_in` varchar(50) DEFAULT NULL COMMENT ''上班打卡时间'',
  `clock_out` varchar(50) DEFAULT NULL COMMENT ''下班打卡时间'',
  `specific_time` datetime DEFAULT NULL COMMENT ''具体时间'',
  `be_late` bigint(20) DEFAULT NULL COMMENT ''迟到'',
  `leave_early` bigint(20) DEFAULT NULL COMMENT ''早退'',
  `absenteeism` bigint(20) DEFAULT NULL COMMENT ''旷工'',
  `bis_true` bit(1) DEFAULT b''0'' COMMENT ''默认0表示未确认'',
  `sure_be_late_number` int(11) DEFAULT NULL COMMENT ''最终迟到次数'',
  `sure_leave_early_number` int(11) DEFAULT NULL COMMENT ''最终早退次数'',
  `sure_absenteeism_number` int(11) DEFAULT NULL COMMENT ''最终旷工次数'',
  `sure_be_late` bigint(20) DEFAULT NULL COMMENT ''最终迟到时间'',
  `sure_leave_early` bigint(20) DEFAULT NULL COMMENT ''最终早退时间'',
  `sure_absenteeism` bigint(20) DEFAULT NULL COMMENT ''最终旷工时间'',
  `remarks` varchar(2000) DEFAULT NULL,
  `verify_sum` int(11) DEFAULT ''0'' COMMENT ''核实次数'',
  `bis_verify` bit(1) DEFAULT b''0'' COMMENT ''是否已最终核实，0表示未核实，默认0'',
  `bis_attend` bit(1) DEFAULT b''1'' COMMENT ''是否考勤核实'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107537 DEFAULT CHARSET=utf8 COMMENT=''考勤数据分析'';

-- ----------------------------
-- Table structure for tb_hr_attendance_checkin
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_attendance_checkin`;
CREATE TABLE `tb_hr_attendance_checkin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL COMMENT ''员工唯一标识ID'',
  `timestamp` datetime DEFAULT NULL COMMENT ''签到时间'',
  `remark` varchar(200) DEFAULT NULL COMMENT ''签到备注'',
  `place` varchar(200) DEFAULT NULL COMMENT ''签到地址'',
  `name` varchar(100) DEFAULT NULL COMMENT ''成员名称'',
  `longitude` varchar(255) DEFAULT NULL COMMENT ''纬度'',
  `latitude` varchar(255) DEFAULT NULL COMMENT ''经度'',
  `detailPlace` varchar(255) DEFAULT NULL COMMENT ''签到详细地址'',
  `avatar` varchar(500) DEFAULT NULL COMMENT ''头像url'',
  `imageList` varchar(1000) DEFAULT NULL COMMENT ''签到照片url列表'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34788 DEFAULT CHARSET=utf8 COMMENT=''钉钉签到记录表'';

-- ----------------------------
-- Table structure for tb_hr_attendance_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_attendance_record`;
CREATE TABLE `tb_hr_attendance_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `approve_id` int(11) DEFAULT NULL COMMENT ''关联的审批id，当该字段非空时，表示打卡记录与请假、加班等审批有关'',
  `base_accuracy` varchar(300) DEFAULT NULL COMMENT ''基准定位精度'',
  `base_address` varchar(300) DEFAULT NULL COMMENT ''基准地址'',
  `base_check_time` datetime DEFAULT NULL COMMENT ''计算迟到和早退，基准时间'',
  `base_atitude` varchar(255) DEFAULT NULL COMMENT ''基准纬度'',
  `base_longitude` varchar(255) DEFAULT NULL COMMENT ''基准经度'',
  `base_mac_addr` varchar(255) DEFAULT NULL COMMENT ''基准 Mac 地址'',
  `base_ssid` varchar(255) DEFAULT NULL COMMENT ''基准wifi ssid'',
  `check_type` varchar(100) DEFAULT NULL COMMENT ''考勤类型 OnDuty：上班 OffDuty：下班'',
  `device_id` varchar(200) DEFAULT NULL COMMENT ''设备id'',
  `gmt_create` timestamp NULL DEFAULT NULL,
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `group_id` int(11) DEFAULT NULL COMMENT ''考勤组ID'',
  `is_legal` varchar(255) DEFAULT NULL COMMENT ''是否合法，当timeResult和locationResult都为Normal时，该值为Y；否则为N'',
  `location_method` varchar(255) DEFAULT NULL COMMENT ''定位方法'',
  `location_result` varchar(100) DEFAULT NULL COMMENT ''位置结果;Normal：范围内；Outside：范围外；NotSigned：未打卡'',
  `outside_remark` varchar(300) DEFAULT NULL COMMENT ''打卡备注'',
  `plan_check_time` datetime DEFAULT NULL COMMENT ''排班打卡时间'',
  `plan_id` int(11) DEFAULT NULL COMMENT ''排班ID'',
  `procInst_id` int(11) DEFAULT NULL COMMENT ''关联的审批实例id，当该字段非空时，表示打卡记录与请假、加班等审批有关。可以与 获取单个审批数据配合使用'',
  `source_type` varchar(100) DEFAULT NULL COMMENT ''数据来源;ATM：考勤机;BEACON：IBeacon;DING_ATM：钉钉考勤机;USER：用户打卡;BOSS：老板改签;APPROVE：审批系统;SYSTEM：考勤系统;AUTO_CHECK：自动打卡'',
  `time_result` varchar(100) DEFAULT NULL COMMENT ''时间结果;Normal：正常;Early：早退;Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到；NotSigned：未打卡'',
  `user_accuracy` varchar(255) DEFAULT NULL COMMENT ''用户打卡定位精度'',
  `user_address` varchar(300) DEFAULT NULL COMMENT ''用户打卡地址'',
  `user_check_time` datetime DEFAULT NULL COMMENT ''实际打卡时间,  用户打卡时间的毫秒数'',
  `user_id` varchar(100) DEFAULT NULL COMMENT ''用户ID'',
  `user_latitude` varchar(255) DEFAULT NULL COMMENT ''用户打卡纬度'',
  `user_longitude` varchar(255) DEFAULT NULL COMMENT ''用户打卡经度'',
  `user_mac_addr` varchar(255) DEFAULT NULL COMMENT ''用户打卡wifi Mac地址'',
  `user_ssid` varchar(100) DEFAULT NULL COMMENT ''用户打卡wifi SSID'',
  `work_date` datetime DEFAULT NULL COMMENT ''工作日'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18622 DEFAULT CHARSET=utf8 COMMENT=''钉钉考勤记录表'';

-- ----------------------------
-- Table structure for tb_hr_attendance_setting_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_attendance_setting_record`;
CREATE TABLE `tb_hr_attendance_setting_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `setting_user_account` varchar(200) DEFAULT NULL COMMENT ''设置人员'',
  `setting_user_name` varchar(200) DEFAULT NULL COMMENT ''设置人员'',
  `setting_status` bit(1) DEFAULT NULL COMMENT ''是否考勤'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `creator` varchar(50) DEFAULT NULL,
  `creator_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base`;
CREATE TABLE `tb_hr_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `process_title` varchar(50) DEFAULT NULL COMMENT ''主题'',
  `content` varchar(500) DEFAULT NULL COMMENT ''内容'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_club_level
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_club_level`;
CREATE TABLE `tb_hr_base_club_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL COMMENT ''年度'',
  `club_level_wage` decimal(10,2) DEFAULT NULL COMMENT ''社平工资'',
  `is_valid` bit(1) DEFAULT b''1'' COMMENT ''是否有效'',
  `creator` varchar(255) DEFAULT NULL,
  `calculate_cardinality_id` int(11) DEFAULT NULL COMMENT ''社平工资基数'',
  `calculate_cardinality_item` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_data_dic
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_data_dic`;
CREATE TABLE `tb_hr_base_data_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT ''0'',
  `name` varchar(100) DEFAULT NULL COMMENT ''名称'',
  `group_key` varchar(100) DEFAULT NULL COMMENT ''分组key'',
  `bis_enable` bit(1) DEFAULT b''1'' COMMENT ''是否可用'',
  `bis_delete` bit(1) DEFAULT b''0'' COMMENT ''是否删除'',
  `remark` varchar(500) DEFAULT NULL COMMENT ''备注'',
  `sorting` int(11) DEFAULT NULL COMMENT ''排序'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `field_name` varchar(100) DEFAULT NULL COMMENT ''使用该数据的字段名称'',
  `url` varchar(255) DEFAULT NULL COMMENT ''申请页地址'',
  `forbid_modify` bit(1) DEFAULT b''0'' COMMENT ''禁止修改'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_sys_data_dic_field_name_uindex` (`field_name`)
) ENGINE=InnoDB AUTO_INCREMENT=404 DEFAULT CHARSET=utf8 COMMENT=''数据字典'';

-- ----------------------------
-- Table structure for tb_hr_base_form
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_form`;
CREATE TABLE `tb_hr_base_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `bis_enable` bit(1) DEFAULT b''1'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_form_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_form_list`;
CREATE TABLE `tb_hr_base_form_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `form_name` varchar(255) DEFAULT NULL,
  `bis_enable` bit(1) DEFAULT b''1'',
  `bis_configure` bit(1) DEFAULT NULL COMMENT ''是否为通用的配置表单'',
  `table_name` varchar(100) DEFAULT NULL COMMENT ''记录数据表名称'',
  `foreign_key_name` varchar(100) DEFAULT NULL COMMENT ''外键字段名称'',
  `bis_multiple` bit(1) DEFAULT NULL COMMENT ''是否为从表'',
  `custom_url` varchar(255) DEFAULT NULL COMMENT ''自定义表单地址'',
  `custome_display_url` varchar(255) DEFAULT NULL COMMENT ''自定义表单显示地址'',
  `bis_card` bit(1) DEFAULT NULL COMMENT ''是否为卡片显示方式'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_form_list_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_form_list_field`;
CREATE TABLE `tb_hr_base_form_list_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_list_id` int(11) DEFAULT NULL COMMENT ''表单id'',
  `name` varchar(255) DEFAULT NULL COMMENT ''字段的名称'',
  `json_name` varchar(255) DEFAULT NULL COMMENT ''存储json数据字段的名称'',
  `display_name` varchar(255) DEFAULT NULL COMMENT ''字段显示名称'',
  `table_name` varchar(255) DEFAULT NULL COMMENT ''字段数据存储的表名'',
  `field_type` int(11) DEFAULT NULL COMMENT ''字段的类型'',
  `field_length` int(11) DEFAULT NULL COMMENT ''字段长度'',
  `default_value` varchar(255) DEFAULT NULL COMMENT ''默认值'',
  `data_source_sql` varchar(1000) DEFAULT NULL COMMENT ''数据源sql'',
  `sorting` int(11) DEFAULT NULL COMMENT ''排序'',
  `bis_cache_data_source` bit(1) DEFAULT NULL COMMENT ''是否缓存数据源'',
  `custom_url` varchar(255) DEFAULT NULL COMMENT ''自定义字段视图地址'',
  `custom_display_url` varchar(255) DEFAULT NULL COMMENT ''自定义字段视图显示地址'',
  `data_view_sql` varchar(1000) DEFAULT NULL COMMENT ''数据字段显示的sql'',
  `width` int(11) DEFAULT ''3'' COMMENT ''宽度'',
  `list_width` int(11) DEFAULT NULL COMMENT ''列表显示的宽度'',
  `bis_cache_data_view` bit(1) DEFAULT NULL COMMENT ''是否缓存显示的值'',
  `bis_json` bit(1) DEFAULT b''0'' COMMENT ''是否为json字段'',
  `bis_required` bit(1) DEFAULT b''0'' COMMENT ''是否必填'',
  `bis_show` bit(1) DEFAULT b''0'' COMMENT ''是否显示'',
  `bis_list_show` bit(1) DEFAULT NULL COMMENT ''是否列表显示'',
  `bis_alone_line` bit(1) DEFAULT NULL COMMENT ''是否独占一行'',
  `bis_query` bit(1) DEFAULT NULL COMMENT ''是否查询条件'',
  `bis_enable` bit(1) DEFAULT b''0'' COMMENT ''是否启用'',
  `bis_delete` bit(1) DEFAULT b''0'' COMMENT ''是否删除'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `professional_id` int(11) DEFAULT NULL COMMENT ''资质ID'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_income_tax_rank
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_income_tax_rank`;
CREATE TABLE `tb_hr_base_income_tax_rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `series` int(11) DEFAULT NULL COMMENT ''级数'',
  `apply_to_type` varchar(500) DEFAULT NULL COMMENT ''适用对象'',
  `annual_ratepaying_start` bigint(20) DEFAULT NULL,
  `annual_ratepaying_end` bigint(20) DEFAULT NULL COMMENT ''全年应纳税所得额'',
  `tax_rate` int(10) DEFAULT NULL COMMENT ''税率'',
  `creator` varchar(255) DEFAULT NULL,
  `deduction` bigint(20) DEFAULT NULL COMMENT ''速算扣除'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_parameter
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_parameter`;
CREATE TABLE `tb_hr_base_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `par_key` varchar(100) DEFAULT NULL COMMENT ''参数名称'',
  `par_values` varchar(100) DEFAULT NULL COMMENT ''参数值'',
  `remarks` varchar(255) DEFAULT NULL COMMENT ''描述说明'',
  `bis_enable` bit(1) DEFAULT b''1'' COMMENT ''是否有效'',
  `class_name` varchar(255) DEFAULT NULL COMMENT ''所属分类'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_par_key` (`par_key`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_position
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_position`;
CREATE TABLE `tb_hr_base_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `name` varchar(255) DEFAULT NULL COMMENT ''名称'',
  `department_id` int(11) DEFAULT NULL COMMENT ''所属部门'',
  `bis_enable` bit(1) DEFAULT b''1'' COMMENT ''是否有效'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT=''职级信息表'';

-- ----------------------------
-- Table structure for tb_hr_base_post
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_post`;
CREATE TABLE `tb_hr_base_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `department_id` int(11) DEFAULT NULL COMMENT ''所属部门'',
  `name` varchar(100) DEFAULT NULL COMMENT ''名称'',
  `bis_enable` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT=''岗位信息表'';

-- ----------------------------
-- Table structure for tb_hr_base_post_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_post_grade`;
CREATE TABLE `tb_hr_base_post_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `name` varchar(255) DEFAULT NULL COMMENT ''名称'',
  `post_id` int(11) DEFAULT NULL COMMENT ''所属岗位'',
  `bis_enable` bit(1) DEFAULT NULL COMMENT ''是否有效'',
  `wages` bigint(20) DEFAULT NULL COMMENT ''岗位工资'',
  `other_subsidy` varchar(255) DEFAULT NULL COMMENT ''其它补贴项'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT=''岗级信息'';

-- ----------------------------
-- Table structure for tb_hr_base_post_score
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_post_score`;
CREATE TABLE `tb_hr_base_post_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT ''评价项名称'',
  `inspection_score` varchar(2000) DEFAULT NULL COMMENT ''考核等级分数'',
  `standard` varchar(255) DEFAULT NULL COMMENT ''考核标准'',
  `weight` int(11) DEFAULT NULL COMMENT ''权重'',
  `post_id` int(11) DEFAULT NULL COMMENT ''所属岗位'',
  `bis_enablev` bit(1) DEFAULT NULL COMMENT ''是否有效'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_base_process
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_process`;
CREATE TABLE `tb_hr_base_process` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `name` varchar(100) DEFAULT NULL COMMENT ''标识事项类型的唯一key'',
  `cn_name` varchar(255) NOT NULL COMMENT ''公司事项名称'',
  `base_form` varchar(100) DEFAULT NULL COMMENT ''阶段表单路径'',
  `box_name` varchar(50) DEFAULT NULL COMMENT ''模型'',
  `table_name` varchar(100) DEFAULT NULL COMMENT ''记录流程数据的主表名称'',
  `executor` varchar(255) DEFAULT NULL COMMENT ''监听器名称'',
  `bis_enable` bit(1) DEFAULT b''1'' COMMENT ''启用'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  KEY `tb_project_phase_box` (`box_name`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT=''工作事项'';

-- ----------------------------
-- Table structure for tb_hr_base_process_form
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_process_form`;
CREATE TABLE `tb_hr_base_process_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '' 编号'',
  `process` varchar(100) DEFAULT NULL COMMENT ''工作内容名称'',
  `box_name` varchar(255) DEFAULT NULL COMMENT ''模型name'',
  `name` varchar(100) DEFAULT NULL COMMENT ''所属工作项'',
  `cn_name` varchar(100) DEFAULT NULL COMMENT ''自定义服务'',
  `bis_enable` bit(1) DEFAULT b''1'' COMMENT ''是否有效'',
  `sorting` int(11) DEFAULT ''0'' COMMENT ''排序'',
  `box_re_activity_name` varchar(50) DEFAULT NULL COMMENT ''填写节点'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COMMENT=''工作事项具体工作项'';

-- ----------------------------
-- Table structure for tb_hr_base_rule_number
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_base_rule_number`;
CREATE TABLE `tb_hr_base_rule_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `year` int(11) DEFAULT NULL COMMENT ''年份'',
  `rule` varchar(10) DEFAULT NULL COMMENT ''规则'',
  `current_number` int(11) DEFAULT NULL COMMENT ''当前编号'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_department`;
CREATE TABLE `tb_hr_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''部门编号'',
  `name` varchar(200) DEFAULT NULL COMMENT ''部门名称'',
  `pid` int(11) DEFAULT ''0'' COMMENT ''直属上级'',
  `bis_enable` bit(1) DEFAULT b''0'' COMMENT ''是否可用'',
  `level` varchar(50) DEFAULT ''非本级'' COMMENT ''级别'',
  `bis_alone` bit(1) DEFAULT b''0'' COMMENT ''是否独自管理'',
  `company_id` int(11) DEFAULT NULL COMMENT ''所属公司ID'',
  `erp_department_id` int(11) DEFAULT ''0'' COMMENT ''ERP部门组织机构ID'',
  `erp_department_id_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT=''部门信息'';

-- ----------------------------
-- Table structure for tb_hr_dismiss_apply
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_dismiss_apply`;
CREATE TABLE `tb_hr_dismiss_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dismiss_staff_account` varchar(255) DEFAULT NULL COMMENT ''辞退人账号'',
  `dismiss_staff_name` varchar(255) DEFAULT NULL COMMENT ''辞退人姓名'',
  `dismiss_cause` varchar(1000) DEFAULT NULL COMMENT ''辞退原因'',
  `dismiss_date` datetime DEFAULT NULL COMMENT ''拟辞退日期'',
  `process_inst_id` varchar(100) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator_name` varchar(255) DEFAULT NULL COMMENT ''创建人姓名'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_dismiss_backlog
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_dismiss_backlog`;
CREATE TABLE `tb_hr_dismiss_backlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT ''标题'',
  `description` varchar(1000) DEFAULT NULL COMMENT ''待办描述'',
  `backlog_time` datetime DEFAULT NULL COMMENT ''时间'',
  `backlog_type` int(2) DEFAULT NULL COMMENT ''待办类型'',
  `dispose_way_id` int(11) DEFAULT NULL COMMENT ''处理方式ID'',
  `dispose_way_name` varchar(50) DEFAULT NULL COMMENT ''处理方式name'',
  `dismiss_id` int(11) DEFAULT NULL COMMENT ''辞退主表ID'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_dismiss_vacation
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_dismiss_vacation`;
CREATE TABLE `tb_hr_dismiss_vacation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dismiss_id` int(11) DEFAULT NULL COMMENT ''辞退申请主表ID'',
  `vacation_type` int(2) DEFAULT NULL COMMENT ''休假类型'',
  `year` int(11) DEFAULT NULL COMMENT ''休假年份'',
  `hours` decimal(11,5) DEFAULT NULL COMMENT ''小时数'',
  `user_account` varchar(100) DEFAULT NULL COMMENT ''员工账号'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''员工姓名'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_emergency
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_emergency`;
CREATE TABLE `tb_hr_emergency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `incident_type` int(11) DEFAULT NULL COMMENT ''突发事件大类ID'',
  `incident_name` varchar(50) DEFAULT NULL COMMENT ''突发事件大类name'',
  `happen_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT ''发生时间'',
  `happen_site` varchar(200) DEFAULT NULL COMMENT ''发生地点'',
  `matter` varchar(2000) DEFAULT NULL COMMENT ''事件内容'',
  `report_object` varchar(100) DEFAULT NULL COMMENT ''报告对象'',
  `impacted_impact` varchar(2000) DEFAULT NULL COMMENT ''事件影响'',
  `suggest_process_mode` varchar(2000) DEFAULT NULL COMMENT ''建议处理方式'',
  `scene_dispose_status` varchar(2000) DEFAULT NULL COMMENT ''现场处理状况'',
  `emergency_contact` varchar(100) DEFAULT NULL COMMENT ''紧急联系人'',
  `emergency_phone` varchar(100) DEFAULT NULL COMMENT ''紧急联系方式'',
  `dispose_result` varchar(300) DEFAULT NULL COMMENT ''处理结果'',
  `process_inst_id` varchar(100) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `project_id` int(11) DEFAULT NULL COMMENT ''项目ID'',
  `project_name` varchar(500) DEFAULT NULL COMMENT ''项目名称'',
  `name` varchar(1000) DEFAULT NULL COMMENT ''名称'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门ID'',
  `department_name` varchar(200) DEFAULT NULL,
  `creator_name` varchar(200) DEFAULT NULL COMMENT ''申请人'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_everyday_patrol
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_everyday_patrol`;
CREATE TABLE `tb_hr_everyday_patrol` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''日常巡查'',
  `patrol_type` int(11) DEFAULT NULL COMMENT ''巡查类型ID'',
  `patrol_name` varchar(50) DEFAULT NULL COMMENT ''巡查类型名称'',
  `patrolman` varchar(100) DEFAULT NULL COMMENT ''巡查人账号'',
  `patrol_date` datetime DEFAULT NULL COMMENT ''巡查日期'',
  `patrol_site` varchar(300) DEFAULT NULL COMMENT ''巡查地点'',
  `patrol_matter` varchar(300) DEFAULT NULL COMMENT ''巡查事项'',
  `patrol_verdict` varchar(500) DEFAULT NULL COMMENT ''巡查结论'',
  `principal` varchar(100) DEFAULT NULL COMMENT ''责任人账号'',
  `principal_name` varchar(100) DEFAULT NULL COMMENT ''责任人姓名'',
  `dispose_opinion` varchar(300) DEFAULT NULL COMMENT ''处理意见'',
  `reorganize_suggest` varchar(300) DEFAULT NULL COMMENT ''整改建议'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_exception_event
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_exception_event`;
CREATE TABLE `tb_hr_exception_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `ref_id` int(11) DEFAULT NULL COMMENT ''关联相关记录的id'',
  `ref_description` varchar(500) DEFAULT NULL COMMENT ''关联的流程描述'',
  `ref_box_id` int(11) DEFAULT NULL COMMENT ''关联数据的boxId'',
  `application_number` varchar(20) DEFAULT NULL COMMENT ''申请单号'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `process_mode` int(11) DEFAULT ''0'' COMMENT ''处理方式 0 取消 1调整'',
  `process_type` varchar(100) DEFAULT NULL COMMENT ''处理类型（加班、外勤）'',
  `begin_date` datetime DEFAULT NULL COMMENT ''开始时间'',
  `end_date` datetime DEFAULT NULL COMMENT ''结束时间'',
  `hours` decimal(10,2) DEFAULT NULL COMMENT ''工时'',
  `application_user_account` varchar(20) DEFAULT NULL COMMENT ''申请人账号'',
  `reason` varchar(500) DEFAULT NULL COMMENT ''请假事由'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `par_process_ins_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_hiring_requisition
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_hiring_requisition`;
CREATE TABLE `tb_hr_hiring_requisition` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `application_company_id` int(11) DEFAULT NULL COMMENT ''公司Id'',
  `application_company_name` varchar(100) DEFAULT NULL COMMENT ''申请公司'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门id'',
  `department_id_name` varchar(100) DEFAULT NULL COMMENT ''申请部门名称'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT=''招聘申请'';

-- ----------------------------
-- Table structure for tb_hr_hiring_requisition_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_hiring_requisition_detail`;
CREATE TABLE `tb_hr_hiring_requisition_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main_id` int(11) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL COMMENT ''岗位'',
  `population` int(11) DEFAULT NULL COMMENT ''人数'',
  `expected_time` datetime DEFAULT NULL COMMENT ''期望到岗时间'',
  `educational_requirement` varchar(100) DEFAULT NULL COMMENT ''学历要求'',
  `qualification_requirement` varchar(100) DEFAULT NULL COMMENT ''资格要求'',
  `age_requirement` varchar(100) DEFAULT NULL COMMENT ''年龄要求'',
  `skill_requirement` varchar(100) DEFAULT NULL COMMENT ''技能要求'',
  `other_requirement` varchar(100) DEFAULT NULL COMMENT ''其他要求'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT=''招聘申请明细'';

-- ----------------------------
-- Table structure for tb_hr_holiday_manage
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_holiday_manage`;
CREATE TABLE `tb_hr_holiday_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT ''假期名称'',
  `begin_date` datetime DEFAULT NULL COMMENT ''开始时间'',
  `end_date` datetime DEFAULT NULL COMMENT ''结束时间'',
  `bis_switch` bit(1) DEFAULT NULL COMMENT ''是否调班'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT=''节假日维护'';

-- ----------------------------
-- Table structure for tb_hr_interview_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_interview_question`;
CREATE TABLE `tb_hr_interview_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_type_id` int(11) DEFAULT NULL,
  `question_type_name` varchar(100) DEFAULT NULL,
  `question_title` varchar(500) DEFAULT NULL,
  `question_answer` varchar(1000) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8 COMMENT=''面试题库'';

-- ----------------------------
-- Table structure for tb_hr_interview_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_interview_record`;
CREATE TABLE `tb_hr_interview_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `personnel_id` varchar(50) DEFAULT NULL COMMENT ''人员Id'',
  `personnel_name` varchar(50) DEFAULT NULL COMMENT ''人员'',
  `interview_time` varchar(50) DEFAULT NULL COMMENT ''面试时间'',
  `interview_site` varchar(50) DEFAULT NULL COMMENT ''面试地点'',
  `interview_result` int(11) DEFAULT NULL COMMENT ''面试结果'',
  `interview_result_name` varchar(50) DEFAULT NULL COMMENT ''面试结果名称'',
  `interviewer` varchar(50) DEFAULT NULL COMMENT ''面试人员'',
  `interviewer_name` varchar(50) DEFAULT NULL COMMENT ''面试人员名'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `interview_idea_id` varchar(11) DEFAULT NULL COMMENT ''面试意见ID'',
  `interview_idea_name` varchar(50) DEFAULT NULL COMMENT ''面试意见'',
  `post_id` int(11) DEFAULT NULL,
  `post_name` varchar(100) DEFAULT NULL COMMENT ''岗位'',
  `post_level_id` int(11) DEFAULT NULL,
  `post_level_name` varchar(100) DEFAULT NULL COMMENT ''岗级'',
  `salary` varchar(100) DEFAULT NULL COMMENT ''工资情况'',
  `interviewer_position` varchar(100) DEFAULT NULL COMMENT ''面试岗位名称'',
  `interviewer_post_id` int(11) DEFAULT NULL COMMENT ''面试岗位ID'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT=''面试记录'';

-- ----------------------------
-- Table structure for tb_hr_interview_record_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_interview_record_detail`;
CREATE TABLE `tb_hr_interview_record_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main_id` int(11) DEFAULT NULL,
  `interview_topic` varchar(100) DEFAULT NULL COMMENT ''面试题目'',
  `conclusion` varchar(100) DEFAULT NULL COMMENT ''回答结论'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `existing` bit(1) DEFAULT b''0'' COMMENT ''是否是已有的'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT=''面试记录明细'';

-- ----------------------------
-- Table structure for tb_hr_interview_record_sign
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_interview_record_sign`;
CREATE TABLE `tb_hr_interview_record_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main_id` int(11) DEFAULT NULL,
  `evaluate` varchar(100) DEFAULT NULL COMMENT ''评价'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `health` varchar(11) DEFAULT NULL COMMENT '' 健康'',
  `attire` varchar(11) DEFAULT NULL COMMENT ''气质礼貌、举止谈吐'',
  `expression` varchar(11) DEFAULT NULL COMMENT ''沟通表达'',
  `awareness` varchar(11) DEFAULT NULL COMMENT ''客户意识'',
  `confidence` varchar(11) DEFAULT NULL COMMENT ''自信心'',
  `conformity` varchar(11) DEFAULT NULL COMMENT ''求职者应聘岗位符合度'',
  `discipline_matching` varchar(11) DEFAULT NULL COMMENT ''学历专业匹配度'',
  `work_experience` varchar(11) DEFAULT NULL COMMENT ''工作经历匹配度'',
  `skill` varchar(11) DEFAULT NULL COMMENT ''业务知识/专业技能'',
  `interview_idea_id` varchar(11) DEFAULT NULL COMMENT ''面试意见ID'',
  `interview_idea_name` varchar(50) DEFAULT NULL COMMENT ''面试意见'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT=''面试记录会签'';

-- ----------------------------
-- Table structure for tb_hr_legwork
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_legwork`;
CREATE TABLE `tb_hr_legwork` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `application_number` varchar(20) DEFAULT NULL COMMENT ''申请单号'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `application_user_account` varchar(200) DEFAULT NULL COMMENT ''申请人账号'',
  `application_user_name` varchar(200) DEFAULT NULL COMMENT ''申请人姓名'',
  `reasons` varchar(500) DEFAULT NULL COMMENT ''外勤事由'',
  `legwork_content` varchar(500) DEFAULT NULL COMMENT ''工作内容'',
  `legwork_address` varchar(100) DEFAULT NULL COMMENT ''外出地点'',
  `legwork_vehicle` varchar(100) DEFAULT NULL COMMENT ''交通工具'',
  `plan_date_start` datetime DEFAULT NULL COMMENT ''预计开始时间'',
  `plan_date_end` datetime DEFAULT NULL COMMENT ''预计结束时间'',
  `plan_hours` decimal(5,2) DEFAULT NULL COMMENT ''预计工时'',
  `project_id` varchar(200) DEFAULT ''0'' COMMENT ''项目编号列表'',
  `project_name` varchar(3000) DEFAULT '''' COMMENT ''项目名称列表'',
  `project_activity_name` varchar(200) DEFAULT '''' COMMENT ''项目节点列表'',
  `accommodation_days` int(11) DEFAULT NULL COMMENT ''住宿天数'',
  `accommodation_method` int(11) DEFAULT ''0'' COMMENT ''住宿方式（0：自住；1：客户）'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `bis_project` bit(1) DEFAULT b''0'' COMMENT ''是否项目直接发起'',
  `actual_date_start` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `legwork_user` varchar(2000) DEFAULT NULL COMMENT ''外勤人员'',
  `actual_hours` decimal(10,0) DEFAULT NULL COMMENT ''实际工时'',
  `actual_legwork_content` varchar(1000) DEFAULT NULL COMMENT ''实际外勤内容'',
  `actual_address` varchar(100) DEFAULT NULL COMMENT ''实际地址'',
  `actual_legwork_user_name` varchar(1000) DEFAULT NULL COMMENT ''实际外勤人员姓名'',
  `actual_legwork_user` varchar(1000) DEFAULT NULL COMMENT ''实际外勤人员账号'',
  `legwork_user_name` varchar(1000) DEFAULT NULL COMMENT ''外勤人员姓名'',
  `actual_date_end` datetime DEFAULT NULL COMMENT ''实际结束时间'',
  `real_accommodation_days` int(11) DEFAULT NULL COMMENT ''实际住宿天数'',
  `real_accommodation_method` int(11) DEFAULT NULL COMMENT ''实际住宿方式'',
  `bis_expends` bit(1) DEFAULT b''0'' COMMENT ''是否报销'',
  `original_id` int(11) DEFAULT NULL COMMENT ''原外勤ID'',
  `change_type` bit(1) DEFAULT NULL COMMENT ''类型（0：变更 1：撤销）'',
  `change_remarks` varchar(500) DEFAULT NULL COMMENT ''变更备注'',
  `is_valid` bit(1) DEFAULT b''1'' COMMENT ''是否有效（0：无效 1：有效）默认有效'',
  `outcome` varchar(2000) DEFAULT NULL COMMENT ''外勤成果'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT=''外勤内容'';

-- ----------------------------
-- Table structure for tb_hr_legwork_single
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_legwork_single`;
CREATE TABLE `tb_hr_legwork_single` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(200) DEFAULT NULL COMMENT ''外勤人员账号'',
  `user_name` varchar(200) DEFAULT NULL COMMENT ''外勤人员姓名'',
  `plan_date_start` datetime DEFAULT NULL COMMENT ''预计开始时间'',
  `plan_date_end` datetime DEFAULT NULL COMMENT ''预计结束时间'',
  `plan_hours` decimal(5,2) DEFAULT NULL COMMENT ''预计工时'',
  `actual_date_start` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `actual_date_end` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `actual_hours` decimal(10,0) DEFAULT NULL COMMENT ''实际工时'',
  `main_id` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `bis_expends` bit(1) DEFAULT b''0'' COMMENT ''是否报销'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=899 DEFAULT CHARSET=utf8 COMMENT=''外勤人员单独时间段设置'';

-- ----------------------------
-- Table structure for tb_hr_overtime
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_overtime`;
CREATE TABLE `tb_hr_overtime` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `application_number` varchar(20) DEFAULT NULL COMMENT ''申请单号'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `application_user_account` varchar(200) DEFAULT NULL COMMENT ''申请人账号'',
  `reason` varchar(500) DEFAULT NULL COMMENT ''加班事由'',
  `content` varchar(500) DEFAULT NULL COMMENT ''加班内容'',
  `project_id` varchar(200) DEFAULT NULL COMMENT ''项目id'',
  `project_name` varchar(3000) DEFAULT NULL COMMENT ''项目名称'',
  `compensate_type` int(10) DEFAULT NULL COMMENT ''补偿方式 0调休 1等价薪酬'',
  `plan_date_start` datetime DEFAULT NULL COMMENT ''预计开始时间'',
  `plan_date_end` datetime DEFAULT NULL COMMENT ''预计结束时间'',
  `plan_hours` decimal(10,2) DEFAULT NULL COMMENT ''预计工时'',
  `ratio` decimal(10,2) DEFAULT NULL COMMENT ''补偿系数'',
  `actual_date_start` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `actual_date_end` datetime DEFAULT NULL COMMENT ''实际结束时间'',
  `actual_hours` decimal(10,2) DEFAULT NULL COMMENT ''实际工时'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `overtime_user` varchar(2000) DEFAULT NULL COMMENT ''加班人员'',
  `bis_project` bit(1) DEFAULT b''0'' COMMENT ''是否为项目加班'',
  `overtime_user_name` varchar(2000) DEFAULT NULL,
  `plan_address` varchar(100) DEFAULT NULL,
  `actual_address` varchar(100) DEFAULT NULL,
  `actual_overtime_user` varchar(100) DEFAULT NULL,
  `actual_overtime_user_name` varchar(100) DEFAULT NULL,
  `content_result` varchar(500) DEFAULT NULL COMMENT ''成果描述'',
  `content_url` varchar(2000) DEFAULT NULL COMMENT ''成果地址'',
  `original_id` int(11) DEFAULT NULL COMMENT ''原加班ID'',
  `change_type` bit(1) DEFAULT NULL COMMENT ''类型（0：变更 1：撤销）'',
  `change_remarks` varchar(500) DEFAULT NULL COMMENT ''变更备注'',
  `is_valid` bit(1) DEFAULT b''1'' COMMENT ''是否有效（0：无效 1：有效）默认有效'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT=''加班信息表'';

-- ----------------------------
-- Table structure for tb_hr_overtime_result
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_overtime_result`;
CREATE TABLE `tb_hr_overtime_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `overtime_id` int(11) DEFAULT NULL COMMENT ''加班主表ID'',
  `result_describe` varchar(1000) DEFAULT NULL COMMENT ''成果描述'',
  `result_detailed_list` varchar(1000) DEFAULT NULL COMMENT ''成果清单'',
  `submitter_account` varchar(50) DEFAULT NULL COMMENT ''提交人账号'',
  `submitter_name` varchar(50) DEFAULT NULL COMMENT ''提交人姓名'',
  `process_main_id` int(11) DEFAULT ''0'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT=''加班成果会签'';

-- ----------------------------
-- Table structure for tb_hr_overtime_result_process
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_overtime_result_process`;
CREATE TABLE `tb_hr_overtime_result_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT=''加班成果审批'';

-- ----------------------------
-- Table structure for tb_hr_overtime_single
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_overtime_single`;
CREATE TABLE `tb_hr_overtime_single` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(200) DEFAULT NULL COMMENT ''加班人员账号'',
  `user_name` varchar(200) DEFAULT NULL COMMENT ''加班人员姓名'',
  `plan_date_start` datetime DEFAULT NULL COMMENT ''预计开始时间'',
  `plan_date_end` datetime DEFAULT NULL COMMENT ''预计结束时间'',
  `plan_hours` decimal(5,2) DEFAULT NULL COMMENT ''预计工时'',
  `actual_date_start` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `actual_date_end` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `actual_hours` decimal(10,0) DEFAULT NULL COMMENT ''实际工时'',
  `main_id` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT=''加班人员时间段设置表'';

-- ----------------------------
-- Table structure for tb_hr_participation_patrol
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_participation_patrol`;
CREATE TABLE `tb_hr_participation_patrol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patrol_time` datetime DEFAULT NULL COMMENT ''巡查时间'',
  `patrol_staff_account` varchar(100) DEFAULT NULL COMMENT ''巡查人账号'',
  `patrol_staff` varchar(100) DEFAULT NULL COMMENT ''巡查人姓名'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=371 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_patrol_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_patrol_details`;
CREATE TABLE `tb_hr_patrol_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `by_examine_person_account` varchar(255) DEFAULT NULL COMMENT ''被查人员账号'',
  `by_examine_person` varchar(100) DEFAULT NULL COMMENT ''被查人员姓名'',
  `system_garrison_situation` varchar(300) DEFAULT NULL COMMENT ''系统在岗情况'',
  `patrol_explain` varchar(300) DEFAULT NULL COMMENT ''巡查说明'',
  `patrol_verdict_id` int(11) DEFAULT NULL COMMENT ''巡查结论ID'',
  `patrol_verdict_name` varchar(100) DEFAULT NULL COMMENT ''巡查结论NAME'',
  `participation_id` int(11) DEFAULT NULL COMMENT ''所属巡查ID'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `feedback_id` int(11) DEFAULT NULL COMMENT ''巡查后响应id'',
  `feedback_name` varchar(255) DEFAULT NULL COMMENT ''巡查后响应name'',
  `patrol_verify` bit(1) DEFAULT b''0'' COMMENT ''复核考勤异常信息，0表示未确认，1表示已确认'',
  `bis_abnormal` bit(1) DEFAULT b''0'' COMMENT ''是否异常数据'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4581 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_promoted
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_promoted`;
CREATE TABLE `tb_hr_promoted` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) DEFAULT NULL COMMENT ''部门ID'',
  `department_name` varchar(255) DEFAULT NULL COMMENT ''部门NAME'',
  `superior_id` varchar(100) DEFAULT NULL COMMENT ''直接上级'',
  `superior_name` varchar(255) DEFAULT NULL COMMENT ''直接上级姓名'',
  `job_id` int(11) DEFAULT NULL COMMENT ''当前岗位ID'',
  `job_name` varchar(255) DEFAULT NULL COMMENT ''当前岗位NAME'',
  `job_rank_id` int(11) DEFAULT NULL COMMENT ''当前岗级'',
  `job_rank_name` varchar(255) DEFAULT NULL COMMENT ''当前岗级'',
  `entry_date` datetime DEFAULT NULL COMMENT ''入职日期'',
  `compact_superior_date` datetime DEFAULT NULL COMMENT ''合同转正日期'',
  `promoted_type_id` int(11) DEFAULT NULL COMMENT ''类型'',
  `promoted_type_name` varchar(255) DEFAULT NULL COMMENT ''类型'',
  `promoted_category_id` int(11) DEFAULT NULL COMMENT ''类别'',
  `promoted_category_name` varchar(255) DEFAULT NULL COMMENT ''类别'',
  `expect_date` datetime DEFAULT NULL COMMENT ''期望日期'',
  `expect_job_id` int(11) DEFAULT NULL COMMENT ''期望岗位'',
  `expect_job_name` varchar(255) DEFAULT NULL COMMENT ''期望岗位'',
  `expect_job_rank_id` int(11) DEFAULT NULL COMMENT ''期望岗级'',
  `expect_job_rank_name` varchar(255) DEFAULT NULL COMMENT ''期望岗级'',
  `summarize` varchar(255) DEFAULT NULL COMMENT ''自定义总结评价项'',
  `career_planning` varchar(255) DEFAULT NULL COMMENT ''个人职业规划'',
  `affirm_job_id` int(11) DEFAULT NULL COMMENT ''确认岗位ID'',
  `affirm_job_name` varchar(255) DEFAULT NULL COMMENT ''确认岗位名称'',
  `affirm_job_rank_id` int(11) DEFAULT NULL COMMENT ''确认岗级ID'',
  `affirm_job_rank_name` varchar(255) DEFAULT NULL COMMENT ''确认岗级名称'',
  `affirm_date` datetime DEFAULT NULL COMMENT ''确认转正晋级日期'',
  `affirm_staff_account` varchar(255) DEFAULT NULL COMMENT ''确认人账号'',
  `affirm_staff_name` varchar(255) DEFAULT NULL COMMENT ''确认人姓名'',
  `whether_meeting` int(5) DEFAULT ''0'' COMMENT ''是否召开会议0：表示不召开；1：表示召开'',
  `process_inst_id` varchar(100) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `work_summarize` varchar(2000) DEFAULT NULL COMMENT ''工作总结'',
  `bis_update` bit(1) DEFAULT b''0'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_promoted_appraise
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_promoted_appraise`;
CREATE TABLE `tb_hr_promoted_appraise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scoring_items_id` int(11) DEFAULT NULL COMMENT ''评分项ID'',
  `scoring_items` varchar(255) DEFAULT NULL COMMENT ''评分项'',
  `standard` varchar(255) DEFAULT NULL COMMENT ''考核标准'',
  `rank_examination` varchar(255) DEFAULT NULL COMMENT ''考核等级'',
  `rank_score` int(11) DEFAULT NULL COMMENT ''考核等级分数'',
  `promoted_id` int(11) DEFAULT NULL COMMENT ''转正晋级主ID'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator_name` varchar(255) DEFAULT NULL,
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `node_activity_id` int(11) DEFAULT NULL COMMENT ''评分节点ID'',
  `node_activity_name` varchar(200) DEFAULT NULL COMMENT ''评分节点名称'',
  `their_post_id` int(11) DEFAULT NULL COMMENT ''所属岗位ID'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_promoted_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_promoted_evaluate`;
CREATE TABLE `tb_hr_promoted_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluate_name` varchar(255) DEFAULT NULL COMMENT ''评分项名称'',
  `inspection_level_id` int(11) DEFAULT NULL COMMENT ''考核等级'',
  `inspection_level_name` varchar(100) DEFAULT NULL COMMENT ''考核等级'',
  `weight` int(11) DEFAULT NULL COMMENT ''权重'',
  `inspection_score` int(11) DEFAULT NULL COMMENT ''考核等级分数'',
  `evaluate_explain` varchar(500) DEFAULT NULL COMMENT ''考核说明'',
  `post_score_id` int(11) DEFAULT NULL COMMENT ''岗位评分项主表ID'',
  `standard` varchar(255) DEFAULT NULL COMMENT ''考核标准'',
  `promoted_id` int(11) DEFAULT NULL COMMENT ''转正晋级主表ID'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_promotion_conference
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_promotion_conference`;
CREATE TABLE `tb_hr_promotion_conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attend_staff_id` int(11) DEFAULT NULL COMMENT ''申请人员账号'',
  `attend_staff_name` varchar(255) DEFAULT NULL COMMENT ''申请人员姓名'',
  `attend_staff_account` varchar(500) DEFAULT NULL COMMENT ''申请人员账号'',
  `participants_account` varchar(500) DEFAULT NULL COMMENT ''参会人账号'',
  `participants_name` varchar(500) DEFAULT NULL COMMENT ''参会人姓名'',
  `first_exam` int(11) DEFAULT NULL COMMENT ''初考比例'',
  `first_exam_over_date` datetime DEFAULT NULL COMMENT ''初考结束日期'',
  `theme` varchar(255) DEFAULT NULL COMMENT ''会议主题'',
  `meeting_time` datetime DEFAULT NULL COMMENT ''会议时间'',
  `meeting_site` varchar(255) DEFAULT NULL COMMENT ''会议地点'',
  `conferee_account` varchar(500) DEFAULT NULL COMMENT ''参会人员账号'',
  `conferee_name` varchar(500) DEFAULT NULL COMMENT ''参会人员姓名'',
  `assessment_deadline` datetime DEFAULT NULL COMMENT ''考核提交截止时间'',
  `affirm_job_id` int(11) DEFAULT NULL COMMENT ''确认岗位ID'',
  `affirm_job_name` varchar(255) DEFAULT NULL COMMENT ''确认岗位名称'',
  `affirm_job_rank_id` int(11) DEFAULT NULL COMMENT ''确认岗级ID'',
  `affirm_job_rank_name` varchar(255) DEFAULT NULL COMMENT ''确认岗级名称'',
  `affirm_date` datetime DEFAULT NULL COMMENT ''确认转正晋级日期'',
  `affirm_staff_account` varchar(255) DEFAULT NULL COMMENT ''确认人账号'',
  `affirm_staff_name` varchar(255) DEFAULT NULL COMMENT ''确认人姓名'',
  `process_inst_id` varchar(100) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_promotion_conference_meeting
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_promotion_conference_meeting`;
CREATE TABLE `tb_hr_promotion_conference_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) DEFAULT NULL COMMENT ''会议主题'',
  `meeting_time` datetime DEFAULT NULL COMMENT ''会议时间'',
  `meeting_site` varchar(255) DEFAULT NULL COMMENT ''会议地点'',
  `participants_account` varchar(500) DEFAULT NULL COMMENT ''参会人账号'',
  `participants_name` varchar(500) DEFAULT NULL COMMENT ''参会人姓名'',
  `assess_deadline` datetime DEFAULT NULL COMMENT ''考核截止时间'',
  `conference_id` int(11) DEFAULT NULL COMMENT ''转正晋级会议主表ID'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_promotion_conference_opinion
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_promotion_conference_opinion`;
CREATE TABLE `tb_hr_promotion_conference_opinion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluate_name` varchar(255) DEFAULT NULL COMMENT ''评分项名称'',
  `inspection_level_id` int(11) DEFAULT NULL COMMENT ''考核等级'',
  `inspection_level_name` varchar(100) DEFAULT NULL COMMENT ''考核等级'',
  `weight` int(11) DEFAULT NULL COMMENT ''权重'',
  `inspection_score` int(11) DEFAULT NULL COMMENT ''考核等级分数'',
  `opinion` varchar(500) DEFAULT NULL COMMENT ''个人意见'',
  `evaluate_id` int(11) DEFAULT NULL COMMENT ''岗位评分项主表ID'',
  `standard` varchar(255) DEFAULT NULL COMMENT ''考核标准'',
  `conference_id` int(11) DEFAULT NULL COMMENT ''转正晋级会议主表ID'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_provident_fund
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_provident_fund`;
CREATE TABLE `tb_hr_provident_fund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_number` varchar(50) DEFAULT NULL COMMENT ''个人客户号'',
  `name` varchar(20) DEFAULT NULL COMMENT ''姓名'',
  `identity_card` varchar(50) DEFAULT NULL COMMENT ''身份证号码'',
  `phase_account` varchar(10) DEFAULT NULL COMMENT ''做账期号'',
  `belongs_to_phase` varchar(10) DEFAULT NULL COMMENT ''费款所属期'',
  `deposite_base` decimal(10,2) DEFAULT NULL COMMENT ''缴存基数'',
  `staff_deposite_quota` decimal(10,2) DEFAULT NULL COMMENT ''职工缴存额'',
  `company_deposite_quota` decimal(10,2) DEFAULT NULL COMMENT ''单位缴存额'',
  `person_deposite_quota` decimal(10,2) DEFAULT NULL COMMENT ''个人缴存额'',
  `company_deposite_limit` decimal(10,2) DEFAULT NULL COMMENT ''单位缴存额（重复）'',
  `month_deposite_total_quota` decimal(10,2) DEFAULT NULL COMMENT ''月缴总额'',
  `master_id` int(11) DEFAULT NULL,
  `personal_sustain` decimal(10,2) DEFAULT NULL COMMENT ''个人全额承担'',
  `company_personal_sustain` decimal(10,2) DEFAULT NULL COMMENT ''单位个人承担部分'',
  `subtotal` decimal(10,2) DEFAULT NULL COMMENT ''小计'',
  `remarks` varchar(500) DEFAULT NULL COMMENT ''备注'',
  `company_sum` decimal(11,2) DEFAULT NULL COMMENT ''单位累计缴费'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=773 DEFAULT CHARSET=utf8 COMMENT=''导入公积金信息'';

-- ----------------------------
-- Table structure for tb_hr_provident_fund_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_provident_fund_master`;
CREATE TABLE `tb_hr_provident_fund_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门id'',
  `department_name` varchar(200) DEFAULT NULL COMMENT ''部门名称'',
  `import_date` varchar(20) DEFAULT NULL COMMENT ''导入日期月份'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_provident_fund_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_provident_fund_template`;
CREATE TABLE `tb_hr_provident_fund_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `department_id` int(11) NOT NULL COMMENT ''部门(公司)ID'',
  `level_name` varchar(100) NOT NULL COMMENT ''级别名称'',
  `base_percentage` decimal(10,2) DEFAULT NULL COMMENT ''缴纳百分比'',
  `base_amount` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''缴纳基数(分)'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  KEY `template_department_id` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT=''部门(公司)公积金模板'';

-- ----------------------------
-- Table structure for tb_hr_recruitment_applicant_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_recruitment_applicant_info`;
CREATE TABLE `tb_hr_recruitment_applicant_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `name` varchar(100) DEFAULT NULL COMMENT ''姓名'',
  `matched_degree` varchar(100) DEFAULT NULL COMMENT ''匹配度'',
  `serial_number` varchar(100) DEFAULT NULL COMMENT ''简历编号'',
  `position` varchar(100) DEFAULT NULL COMMENT ''应聘职位'',
  `company` varchar(100) DEFAULT NULL COMMENT ''应聘公司'',
  `release_city` varchar(100) DEFAULT NULL COMMENT ''发布城市'',
  `apply_date` varchar(100) DEFAULT NULL COMMENT ''应聘日期'',
  `sex` varchar(100) DEFAULT NULL COMMENT ''性别'',
  `birth_date` varchar(10) DEFAULT NULL COMMENT ''出生日期'',
  `reside` varchar(100) DEFAULT NULL COMMENT ''目前居住地'',
  `household` varchar(100) DEFAULT NULL COMMENT ''户口/国籍'',
  `working_years` varchar(100) DEFAULT NULL COMMENT ''工作年限'',
  `degree` varchar(100) DEFAULT NULL COMMENT ''学历/学位'',
  `school_tag` varchar(100) DEFAULT NULL COMMENT ''毕业学校'',
  `major` varchar(100) DEFAULT NULL COMMENT ''专业'',
  `phone` varchar(100) DEFAULT NULL COMMENT ''联系电话'',
  `mail` varchar(100) DEFAULT NULL COMMENT ''电子邮件'',
  `address` varchar(100) DEFAULT NULL COMMENT ''地址'',
  `postcode` varchar(100) DEFAULT NULL COMMENT ''邮编'',
  `last_company` varchar(100) DEFAULT NULL COMMENT ''最近一家公司'',
  `last_post` varchar(100) DEFAULT NULL COMMENT ''最近一个职位'',
  `now_income` varchar(100) DEFAULT NULL COMMENT ''目前年收入'',
  `salary_expectation` varchar(100) DEFAULT NULL COMMENT ''期望薪资'',
  `job_status` varchar(100) DEFAULT NULL COMMENT ''求职状态'',
  `stage` varchar(100) DEFAULT NULL COMMENT ''所处阶段'',
  `main_id` int(11) DEFAULT NULL,
  `classification` int(11) DEFAULT NULL COMMENT ''归类'',
  `classification_name` varchar(100) DEFAULT NULL COMMENT ''归类名称'',
  `notice` int(11) DEFAULT NULL COMMENT ''面试通知结果'',
  `notice_name` varchar(50) DEFAULT NULL COMMENT ''面试通知结果名称'',
  `interview_time` varchar(50) DEFAULT NULL COMMENT ''面试时间'',
  `interview_site` varchar(50) DEFAULT NULL COMMENT ''面试地点'',
  `remark` varchar(100) DEFAULT NULL COMMENT ''其他说明'',
  `entry_notice` int(11) DEFAULT NULL COMMENT ''录取通知结果'',
  `entry_notice_name` varchar(50) DEFAULT NULL COMMENT ''录取通知结果名称'',
  `entry_date` varchar(50) DEFAULT NULL COMMENT ''入职日期'',
  `register_time` varchar(50) DEFAULT NULL COMMENT ''报道时间'',
  `dept_id` int(11) DEFAULT NULL COMMENT ''入职部门'',
  `dept_name` varchar(50) DEFAULT NULL COMMENT ''部门名称'',
  `post_id` int(11) DEFAULT NULL COMMENT ''岗位'',
  `post_name` varchar(50) DEFAULT NULL COMMENT ''岗位名称'',
  `probation_salary` int(11) DEFAULT NULL COMMENT ''试用期薪资'',
  `probation_time` varchar(50) DEFAULT NULL COMMENT ''试用期期限'',
  `obtainment_salary` int(11) DEFAULT NULL COMMENT ''转正薪资'',
  `other_description` varchar(100) DEFAULT NULL COMMENT ''其他说明'',
  `interview_result` int(11) DEFAULT NULL COMMENT ''面试结果'',
  `interview_result_name` varchar(50) DEFAULT NULL COMMENT ''面试结果名称'',
  `import_date` varchar(50) DEFAULT NULL COMMENT ''导入日期'',
  `issue_result` int(11) DEFAULT ''0'' COMMENT ''通知书发放结果'',
  `remarks` varchar(200) DEFAULT NULL COMMENT ''备注'',
  `resume_json` json DEFAULT NULL COMMENT ''简历JSON字符串'',
  `classification_reason` varchar(500) DEFAULT NULL COMMENT ''归类原因'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8 COMMENT=''应聘者信息'';

-- ----------------------------
-- Table structure for tb_hr_recruitment_information_neaten
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_recruitment_information_neaten`;
CREATE TABLE `tb_hr_recruitment_information_neaten` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `channel_id` int(11) DEFAULT NULL COMMENT ''招聘渠道'',
  `channel_name` varchar(100) DEFAULT NULL COMMENT ''招聘渠道名称'',
  `post_id` int(11) DEFAULT NULL COMMENT ''所属岗位'',
  `post_id_name` varchar(100) DEFAULT NULL COMMENT ''所属岗位名称'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT=''招聘信息整理'';

-- ----------------------------
-- Table structure for tb_hr_recruitment_information_release
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_recruitment_information_release`;
CREATE TABLE `tb_hr_recruitment_information_release` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `department_id` int(11) DEFAULT NULL COMMENT ''发布单位id'',
  `department_name` varchar(100) DEFAULT NULL COMMENT ''发布单位名称'',
  `channel_id` int(11) DEFAULT NULL COMMENT ''发布渠道id'',
  `channel_name` varchar(100) DEFAULT NULL COMMENT ''发布渠道名称'',
  `publish_time` varchar(100) DEFAULT NULL COMMENT ''发布日期'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT=''招聘信息发布'';

-- ----------------------------
-- Table structure for tb_hr_recruitment_information_release_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_recruitment_information_release_detail`;
CREATE TABLE `tb_hr_recruitment_information_release_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `main_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL COMMENT ''岗位id'',
  `post_id_name` varchar(100) DEFAULT NULL COMMENT ''岗位名称'',
  `population` int(11) DEFAULT NULL COMMENT ''人数'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT=''招聘信息发布明细'';

-- ----------------------------
-- Table structure for tb_hr_salary_adjustment
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_adjustment`;
CREATE TABLE `tb_hr_salary_adjustment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `relieve_explain` varchar(500) DEFAULT NULL COMMENT ''说明'',
  `bis_update` bit(1) DEFAULT b''0'' COMMENT ''定时任务是否已更新标识'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT=''员工工资调整主 表'';

-- ----------------------------
-- Table structure for tb_hr_salary_adjustment_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_adjustment_details`;
CREATE TABLE `tb_hr_salary_adjustment_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''人员账号'',
  `adjustment_id` int(11) DEFAULT NULL COMMENT ''系统调编号'',
  `salary_class_id` int(11) DEFAULT NULL COMMENT ''工资组成分类编号'',
  `salary_template_id` int(11) DEFAULT NULL COMMENT ''工资等级编号'',
  `bussiness_id` int(11) DEFAULT NULL COMMENT ''资格、职称、岗位等工资项的业务ID'',
  `salary_name` varchar(100) DEFAULT NULL COMMENT ''工资等级名称'',
  `basic_salary` bigint(10) DEFAULT NULL COMMENT ''默认工资金额'',
  `salary` bigint(10) DEFAULT NULL COMMENT ''实际工资金额'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `social_security` int(11) DEFAULT NULL COMMENT ''社保等级'',
  `provident_fund` int(11) DEFAULT NULL COMMENT ''公积金等级'',
  `qualification_wage` bigint(20) DEFAULT NULL COMMENT ''资格工资'',
  `job_title_wage` bigint(20) DEFAULT NULL COMMENT ''职称工资'',
  `kickback_performance` bit(1) DEFAULT b''0'' COMMENT ''是否回扣绩效工资'',
  `internship_wage` bigint(20) DEFAULT NULL COMMENT ''实习工资'',
  `effective_date` date DEFAULT NULL COMMENT ''生效日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT=''人员工资调整明细'';

-- ----------------------------
-- Table structure for tb_hr_salary_adjustment_rank
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_adjustment_rank`;
CREATE TABLE `tb_hr_salary_adjustment_rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(20) DEFAULT NULL COMMENT ''人员账号'',
  `adjustment_id` int(11) DEFAULT NULL COMMENT ''系统调编号'',
  `user_department` int(11) DEFAULT NULL COMMENT ''所属部门'',
  `user_department_name` varchar(100) DEFAULT NULL COMMENT ''所属部门名称'',
  `base_pay` int(11) DEFAULT NULL COMMENT ''基本工资等级ID'',
  `base_pay_name` varchar(100) DEFAULT NULL COMMENT ''基本工资等级名称'',
  `merit_pay` int(11) DEFAULT NULL COMMENT ''绩效工资等级ID'',
  `merit_pay_name` varchar(100) DEFAULT NULL COMMENT ''绩效工资等级名称'',
  `wage_subsidy` varchar(255) DEFAULT NULL COMMENT ''补贴工资选项ID'',
  `wage_subsidy_name` varchar(500) DEFAULT NULL COMMENT ''补贴工资选项名称'',
  `other_wage` varchar(255) DEFAULT NULL COMMENT ''其他工资等级ID'',
  `other_wage_name` varchar(500) DEFAULT NULL COMMENT ''其他工资等级名称'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `social_security` int(11) DEFAULT NULL COMMENT ''社保等级'',
  `provident_fund` int(11) DEFAULT NULL COMMENT ''公积金等级'',
  `qualification_wage` bigint(20) DEFAULT NULL COMMENT ''资格工资'',
  `job_title_wage` bigint(20) DEFAULT NULL COMMENT ''职称工资'',
  `kickback_performance` bit(1) DEFAULT b''0'' COMMENT ''是否回扣绩效工资'',
  `internship_wage` bigint(20) DEFAULT NULL COMMENT ''实习工资'',
  `ote_that` varchar(1000) DEFAULT NULL COMMENT ''备注说明'',
  `post_amount` bigint(20) DEFAULT NULL COMMENT ''岗位工资'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_salary_adjustment_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_adjustment_user`;
CREATE TABLE `tb_hr_salary_adjustment_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `adjustment_id` int(11) DEFAULT NULL COMMENT ''调整编号'',
  `user_account` varchar(10) DEFAULT NULL COMMENT ''调整人员账号'',
  `user_name` varchar(50) DEFAULT NULL COMMENT ''姓名'',
  `salary_count` bigint(20) DEFAULT NULL COMMENT ''人员工资总和'',
  `last_salary_count` bigint(20) DEFAULT NULL COMMENT ''当前工资总和'',
  `adjustment_proportion` int(11) DEFAULT NULL COMMENT ''调整比例'',
  `execution_time` datetime DEFAULT NULL COMMENT ''执行时间'',
  `adjustment_reason_id` int(11) DEFAULT NULL COMMENT ''调整原因名称ID'',
  `adjustment_reason_name` varchar(255) DEFAULT NULL COMMENT ''调整原因名称'',
  `remark` varchar(1000) DEFAULT NULL COMMENT ''备注'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `bis_update` bit(1) DEFAULT b''0'' COMMENT ''定时任务是否已更新标识'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT=''本次调整人员信息表'';

-- ----------------------------
-- Table structure for tb_hr_salary_check_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_check_set`;
CREATE TABLE `tb_hr_salary_check_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''主键编号'',
  `cost_main_id` int(11) DEFAULT NULL COMMENT ''成本单位id'',
  `cost_main_name` varchar(100) DEFAULT NULL COMMENT ''成本单位名称'',
  `salary_type` varchar(255) DEFAULT NULL COMMENT ''工资类型'',
  `salary_type_name` varchar(255) DEFAULT NULL COMMENT ''显示名称'',
  `bis_enable` bit(1) DEFAULT b''1'' COMMENT ''是否有效'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_salary_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_class`;
CREATE TABLE `tb_hr_salary_class` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `salary_class_name` varchar(50) NOT NULL COMMENT ''类型名称'',
  `salary_tag` varchar(50) DEFAULT NULL COMMENT ''标记(用于特殊处理时的标识)'',
  `bis_fixed` bit(1) DEFAULT b''0'' COMMENT ''固定类型'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT=''薪水类型'';

-- ----------------------------
-- Table structure for tb_hr_salary_department_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_department_template`;
CREATE TABLE `tb_hr_salary_department_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `salary_class_id` int(11) NOT NULL COMMENT ''薪资类型ID'',
  `department_id` int(11) NOT NULL COMMENT ''部门ID'',
  `professional_pid` int(11) DEFAULT ''0'' COMMENT ''职称分类ID'',
  `professional_id` int(11) DEFAULT ''0'' COMMENT ''职称ID'',
  `qualifications_pid` int(11) DEFAULT ''0'' COMMENT ''资质分类ID'',
  `qualifications_id` int(11) DEFAULT ''0'' COMMENT ''资质ID'',
  `post_id` int(11) DEFAULT ''0'' COMMENT ''岗位ID'',
  `post_grade_id` int(11) DEFAULT ''0'' COMMENT ''岗级ID'',
  `work_years_start` int(11) DEFAULT ''0'' COMMENT ''工龄开始'',
  `work_years_end` int(11) DEFAULT ''0'' COMMENT ''工龄结束'',
  `salary_level_name` varchar(100) NOT NULL COMMENT ''薪资级别名称'',
  `salary_amount` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''薪资金额(分)'',
  `bis_template` bit(1) NOT NULL DEFAULT b''0'' COMMENT ''是否是模板项'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  KEY `template_department_id` (`department_id`),
  KEY `template_salary_class_id` (`salary_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT=''部门(公司)薪水模板'';

-- ----------------------------
-- Table structure for tb_hr_salary_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user`;
CREATE TABLE `tb_hr_salary_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(255) DEFAULT NULL COMMENT ''人员账号'',
  `salary_class_id` int(11) DEFAULT NULL COMMENT ''工资组成分类编号'',
  `salary_template_id` int(11) DEFAULT NULL COMMENT ''工资等级编号'',
  `salary_name` varchar(100) DEFAULT NULL COMMENT ''工资等级名称'',
  `basic_salary` bigint(20) DEFAULT NULL COMMENT ''原始基础工资'',
  `salary` bigint(10) DEFAULT NULL COMMENT ''实际工资金额'',
  `settlement_begintime` date NOT NULL COMMENT ''工资计算开始日期'',
  `settlement_overtime` date DEFAULT NULL COMMENT ''工资结算到最终日期'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modifier` varchar(20) DEFAULT NULL COMMENT ''修改人'',
  `modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后一次修改时间'',
  `bussiness_id` int(11) DEFAULT NULL COMMENT ''资格、职称、岗位等工资项的业务ID'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=''人员工资表'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_achievements
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_achievements`;
CREATE TABLE `tb_hr_salary_user_achievements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(20) DEFAULT NULL COMMENT ''账号'',
  `user_name` varchar(20) DEFAULT NULL COMMENT ''姓名'',
  `years_month` varchar(20) DEFAULT NULL COMMENT ''工资月份'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门id'',
  `department_name` varchar(200) DEFAULT NULL COMMENT ''部门名称'',
  `project_achievement` bigint(11) DEFAULT ''0'' COMMENT ''项目绩效'',
  `histrory_beforehand` bigint(11) DEFAULT ''0'' COMMENT ''历史预发余额'',
  `current_deduction` bigint(11) DEFAULT ''0'' COMMENT ''预发绩效本次抵扣'',
  `current_beforehand` bigint(20) DEFAULT ''0'' COMMENT ''当期预发'',
  `all_achievement` bigint(11) DEFAULT ''0'' COMMENT ''纳入工资'',
  `bis_check` bit(1) DEFAULT b''0'' COMMENT ''是否确认，默认0：未确认；1：已确认'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `master_id` int(11) DEFAULT NULL,
  `bis_quit` bit(1) DEFAULT b''0'' COMMENT ''是否为手动添加的离职人员'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3720 DEFAULT CHARSET=utf8 COMMENT=''老系统绩效工资'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_achievements_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_achievements_history`;
CREATE TABLE `tb_hr_salary_user_achievements_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(20) DEFAULT NULL,
  `achievement_amount` bigint(20) DEFAULT NULL COMMENT ''剩余预发绩效'',
  `not_included_salary_total` bigint(20) DEFAULT NULL COMMENT ''不纳入工资总额'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT=''个人预发绩效记录'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_achievements_history_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_achievements_history_record`;
CREATE TABLE `tb_hr_salary_user_achievements_history_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(20) DEFAULT NULL COMMENT ''人员'',
  `pay_date` datetime DEFAULT NULL COMMENT ''日期'',
  `pay_methord` varchar(20) DEFAULT NULL COMMENT ''操作方式'',
  `amount` bigint(20) DEFAULT NULL COMMENT ''发生额'',
  `amount_type` varchar(10) DEFAULT NULL COMMENT ''金额分类'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT=''预发绩效明细记录\r\n'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_achievements_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_achievements_master`;
CREATE TABLE `tb_hr_salary_user_achievements_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `pay_date` varchar(20) DEFAULT NULL COMMENT ''发放日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant`;
CREATE TABLE `tb_hr_salary_user_grant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salary_date` varchar(20) DEFAULT NULL COMMENT ''发放年月'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `creator` varchar(20) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8 COMMENT=''工资发放表'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_list`;
CREATE TABLE `tb_hr_salary_user_grant_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT NULL COMMENT ''主表编号'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''人员账号'',
  `user_name` varchar(20) DEFAULT NULL COMMENT ''人员姓名'',
  `salary_list` json DEFAULT NULL COMMENT ''工资明细'',
  `salary_amount` bigint(20) DEFAULT NULL COMMENT ''工资金额'',
  `costs_company` varchar(100) DEFAULT NULL COMMENT ''费用主题'',
  `bis_special` bit(1) DEFAULT NULL,
  `deduction_amount` bigint(20) DEFAULT NULL COMMENT ''扣款'',
  `grant_amount` bigint(20) DEFAULT NULL COMMENT ''发放'',
  `ids` varchar(20) DEFAULT NULL,
  `costs_company_id` int(11) DEFAULT NULL COMMENT ''费用主题ID'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4541 DEFAULT CHARSET=utf8 COMMENT=''工资发放明细表'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_nonpayment_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_nonpayment_list`;
CREATE TABLE `tb_hr_salary_user_grant_nonpayment_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL COMMENT ''账号'',
  `name` varchar(50) DEFAULT NULL COMMENT ''姓名'',
  `unpaid_month` varchar(20) DEFAULT NULL COMMENT ''未缴纳日期'',
  `unpaid_type` bit(1) DEFAULT NULL COMMENT ''未缴纳类型（1表示社保；0表示公积金）'',
  `calculate_month` varchar(20) DEFAULT NULL COMMENT ''计算日期'',
  `master_id` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2353 DEFAULT CHARSET=utf8 COMMENT=''存放未缴纳社保公积金人员名单'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_pay
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_pay`;
CREATE TABLE `tb_hr_salary_user_grant_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `pay_date` varchar(20) DEFAULT NULL COMMENT ''发放日期'',
  `years` int(11) DEFAULT NULL COMMENT ''工资发放年'',
  `months` int(11) DEFAULT NULL COMMENT ''工资发放月'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT=''工资发放'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_pay_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_pay_list`;
CREATE TABLE `tb_hr_salary_user_grant_pay_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''所属流程'',
  `account` varchar(100) DEFAULT NULL COMMENT ''账号'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''姓名'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门id'',
  `department_name` varchar(200) DEFAULT NULL COMMENT ''部门名称'',
  `pay_date` varchar(20) DEFAULT NULL COMMENT ''发放日期'',
  `basic_salary` bigint(11) DEFAULT ''0'' COMMENT ''基本工资收入'',
  `deduction` bigint(255) DEFAULT NULL COMMENT ''专项扣除'',
  `base_deduction` bigint(20) DEFAULT NULL COMMENT ''保险扣除'',
  `deduction_other` bigint(20) DEFAULT NULL COMMENT ''其它扣除(考勤)'',
  `tax_base` bigint(20) DEFAULT NULL COMMENT ''计税收入'',
  `current_tax` bigint(11) DEFAULT ''0'' COMMENT ''当前扣税金额'',
  `practical_pay_salary` bigint(11) DEFAULT ''0'' COMMENT ''实际发放工资'',
  `bis_affirm` bit(1) DEFAULT b''0'' COMMENT ''是否确认，默认0：未确认；1：已确认'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `bis_update` bit(1) DEFAULT b''0'' COMMENT ''是否修改绩效，默认0：未修改;1：有修改过'',
  `is_change` bit(1) DEFAULT b''0'' COMMENT ''对比上月是否有变化'',
  `ids` varchar(20) DEFAULT NULL,
  `tax_deduction` bigint(20) DEFAULT NULL COMMENT ''算税速算扣除数'',
  `tax_rant` bigint(20) DEFAULT NULL COMMENT ''税率'',
  `tax_before_adjust_salary` bigint(11) DEFAULT ''0'' COMMENT ''调整金额'',
  `tax_before` bit(1) DEFAULT b''0'' COMMENT ''是否税前调整'',
  `final_salary` bigint(20) DEFAULT NULL COMMENT ''最终发放'',
  `adjust_remark` varchar(2000) DEFAULT NULL COMMENT ''调整备注'',
  `costs_company_id` int(11) DEFAULT NULL COMMENT ''费用主体ID'',
  `project_achievement` bigint(11) DEFAULT ''0'' COMMENT ''项目绩效'',
  `histrory_beforehand` bigint(11) DEFAULT ''0'' COMMENT ''历史预发余额'',
  `current_deduction` bigint(11) DEFAULT ''0'' COMMENT ''预发绩效本次抵扣'',
  `current_beforehand` bigint(20) DEFAULT ''0'' COMMENT ''当期预发'',
  `all_achievement` bigint(11) DEFAULT ''0'' COMMENT ''纳入工资'',
  `bis_tax` bit(1) DEFAULT b''0'' COMMENT ''是否已计税'',
  `tax_after` bit(1) DEFAULT b''0'' COMMENT ''是否税后调整'',
  `tax_after_adjust_salary` bigint(11) DEFAULT ''0'' COMMENT ''税后调整金额'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3528 DEFAULT CHARSET=utf8 COMMENT=''工资发放名单明细'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_tax
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_tax`;
CREATE TABLE `tb_hr_salary_user_grant_tax` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_accout` varchar(20) DEFAULT NULL,
  `usre_name` varchar(20) DEFAULT NULL,
  `ids` varchar(20) DEFAULT NULL,
  `years` int(11) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL COMMENT ''累计收入'',
  `reduce` bigint(20) DEFAULT NULL COMMENT ''累计减除'',
  `pension` bigint(20) DEFAULT NULL COMMENT ''养老保险'',
  `medical` bigint(20) DEFAULT NULL COMMENT ''医疗保险'',
  `unemployment` bigint(20) DEFAULT NULL COMMENT ''失业保险'',
  `accumulation` bigint(20) DEFAULT NULL COMMENT ''住房公积金'',
  `children` bigint(20) DEFAULT NULL COMMENT ''子女教育'',
  `education` bigint(20) DEFAULT NULL COMMENT ''继续教育'',
  `loan` bigint(20) DEFAULT NULL COMMENT ''住户贷款'',
  `rent` bigint(20) DEFAULT NULL COMMENT ''住户租金'',
  `elderly` bigint(20) DEFAULT NULL COMMENT ''赡养老人'',
  `tax_base` bigint(20) DEFAULT NULL COMMENT ''累计应缴税基数'',
  `tax_amount` bigint(20) DEFAULT NULL COMMENT ''累计应缴税'',
  `tax_amount_do` bigint(20) DEFAULT NULL COMMENT ''已缴税'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=596 DEFAULT CHARSET=utf8 COMMENT=''个税申报汇总'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_tax_deduction
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_tax_deduction`;
CREATE TABLE `tb_hr_salary_user_grant_tax_deduction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_accout` varchar(20) DEFAULT NULL,
  `usre_name` varchar(20) DEFAULT NULL,
  `children` bigint(20) DEFAULT NULL COMMENT ''子女教育'',
  `education` bigint(20) DEFAULT NULL COMMENT ''继续教育'',
  `loan` bigint(20) DEFAULT NULL COMMENT ''住户贷款'',
  `rent` bigint(20) DEFAULT NULL COMMENT ''住户租金'',
  `elderly` bigint(20) DEFAULT NULL COMMENT ''赡养老人'',
  `bis_enable` bit(1) DEFAULT b''1'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT=''人员专项扣除设置'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_grant_tax_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_grant_tax_list`;
CREATE TABLE `tb_hr_salary_user_grant_tax_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_accout` varchar(20) DEFAULT NULL,
  `usre_name` varchar(20) DEFAULT NULL,
  `ids` varchar(20) DEFAULT NULL,
  `years` int(11) DEFAULT NULL,
  `months` int(11) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL COMMENT ''本期收入'',
  `pension` bigint(20) DEFAULT NULL COMMENT ''养老保险'',
  `medical` bigint(20) DEFAULT NULL COMMENT ''医疗保险'',
  `unemployment` bigint(20) DEFAULT NULL COMMENT ''失业保险'',
  `accumulation` bigint(20) DEFAULT NULL COMMENT ''住房公积金'',
  `children` bigint(20) DEFAULT NULL COMMENT ''子女教育'',
  `education` bigint(20) DEFAULT NULL COMMENT ''继续教育'',
  `loan` bigint(20) DEFAULT NULL COMMENT ''住户贷款'',
  `rent` bigint(20) DEFAULT NULL COMMENT ''住户租金'',
  `elderly` bigint(20) DEFAULT NULL COMMENT ''赡养老人'',
  `tax_start_date` datetime DEFAULT NULL COMMENT ''纳税开始'',
  `tax_start_end` datetime DEFAULT NULL COMMENT ''纳税结束'',
  `tax` bigint(20) DEFAULT NULL COMMENT ''基本缴税'',
  `deduction` bigint(20) DEFAULT NULL COMMENT ''速算扣除数'',
  `tax_rant` bigint(20) DEFAULT NULL COMMENT ''税率'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3432 DEFAULT CHARSET=utf8 COMMENT=''个税申报数据'';

-- ----------------------------
-- Table structure for tb_hr_salary_user_performance
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_user_performance`;
CREATE TABLE `tb_hr_salary_user_performance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `user_account` varchar(32) NOT NULL COMMENT ''用户系统账户名'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''用户姓名'',
  `salary_year` int(11) NOT NULL COMMENT ''绩效工资年份'',
  `salary_month` int(11) NOT NULL COMMENT ''绩效工资月份'',
  `salary_amount` bigint(20) NOT NULL COMMENT ''金额(分)'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `process_inst_id` varchar(64) DEFAULT NULL COMMENT ''流程实例'',
  `status` varchar(20) DEFAULT NULL COMMENT ''状态'',
  `serial_number` varchar(100) DEFAULT NULL COMMENT ''导入操作流水号'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_account_year_month` (`user_account`,`salary_year`,`salary_month`),
  KEY `index_user_account` (`user_account`),
  KEY `index_serial_number` (`serial_number`),
  KEY `index_year_month` (`salary_year`,`salary_month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=''员工绩效工资导入表'';

-- ----------------------------
-- Table structure for tb_hr_salary_work_flow
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_salary_work_flow`;
CREATE TABLE `tb_hr_salary_work_flow` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `work_flow_describe` varchar(500) DEFAULT NULL COMMENT ''描述'',
  `department_id` int(11) DEFAULT NULL COMMENT ''公司部门'',
  `process_inst_id` varchar(64) DEFAULT NULL COMMENT ''流程实例ID'',
  `status` varchar(20) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  KEY `index_process_inst_id` (`process_inst_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=''工资相关工作流辅助表'';

-- ----------------------------
-- Table structure for tb_hr_sick_leave_deduction_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_sick_leave_deduction_set`;
CREATE TABLE `tb_hr_sick_leave_deduction_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `leave_days_begin` decimal(10,2) DEFAULT NULL COMMENT ''请假天数起'',
  `leave_days_end` decimal(11,2) DEFAULT NULL COMMENT ''请假天数止'',
  `working_life_begin` decimal(11,2) DEFAULT NULL COMMENT ''工作年限起'',
  `working_life_end` decimal(11,2) DEFAULT NULL COMMENT ''工作年限止'',
  `ratio` decimal(10,1) DEFAULT NULL COMMENT ''扣款比例'',
  `bis_start` bit(1) DEFAULT b''0'' COMMENT ''是否启用'',
  `base_pay` decimal(10,2) DEFAULT NULL COMMENT ''基本工资'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=''病假扣减工资规则设置'';

-- ----------------------------
-- Table structure for tb_hr_social_security
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_social_security`;
CREATE TABLE `tb_hr_social_security` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personal_number` varchar(100) DEFAULT NULL COMMENT ''个人编号'',
  `declare_salary` decimal(10,2) DEFAULT ''0.00'' COMMENT ''申报工资'',
  `social_security_number` varchar(20) DEFAULT NULL COMMENT ''社会保障号码'',
  `name` varchar(50) DEFAULT NULL COMMENT ''姓名'',
  `category` varchar(10) DEFAULT NULL COMMENT ''人员类别'',
  `belongs_to_phase` varchar(10) DEFAULT NULL COMMENT ''费款所属期'',
  `phase_account` varchar(10) DEFAULT NULL COMMENT ''做账期号'',
  `company_count` decimal(10,2) DEFAULT ''0.00'' COMMENT ''单位合计'',
  `person_count` decimal(10,2) DEFAULT ''0.00'' COMMENT ''个人合计'',
  `late_fees_count` decimal(10,2) DEFAULT ''0.00'' COMMENT ''滞纳金合计'',
  `interest_count` decimal(10,2) DEFAULT ''0.00'' COMMENT ''利息合计'',
  `total_count` decimal(10,2) DEFAULT ''0.00'' COMMENT ''总合计'',
  `pension_pay_type` varchar(50) DEFAULT NULL COMMENT ''养老缴费类型'',
  `pension_company_pay_base` decimal(10,2) DEFAULT NULL COMMENT ''养老单位缴费基数'',
  `pension_company_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''养老单位缴费比例'',
  `pension_company_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''养老单位缴费金额'',
  `pension_person_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''养老个人缴费比例'',
  `pension_person_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''养老个人缴费金额'',
  `pension_interest` decimal(10,2) DEFAULT ''0.00'' COMMENT ''养老利息'',
  `pension_late_fees` decimal(10,2) DEFAULT ''0.00'' COMMENT ''养老滞纳金'',
  `pension_arrival_account_sign` varchar(50) DEFAULT NULL COMMENT ''养老到账标志'',
  `unemployment_pay_type` varchar(50) DEFAULT NULL COMMENT ''失业缴费类型'',
  `unemployment_company_pay_base` decimal(10,2) DEFAULT NULL COMMENT ''失业单位缴费基数'',
  `unemployment_company_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''失业单位缴费比例'',
  `unemployment_company_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''失业单位缴费金额'',
  `unemployment_person_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''失业个人缴费比例'',
  `unemployment_person_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''失业个人缴费金额'',
  `unemployment_interest` decimal(10,2) DEFAULT ''0.00'' COMMENT ''失业利息'',
  `unemployment_late_fees` decimal(10,2) DEFAULT ''0.00'' COMMENT ''失业滞纳金'',
  `unemployment_arrival_account_sign` varchar(50) DEFAULT NULL COMMENT ''失业到账标志'',
  `medical_pay_type` varchar(50) DEFAULT NULL COMMENT ''医疗缴费类型'',
  `medical_company_pay_base` decimal(10,2) DEFAULT NULL COMMENT ''医疗单位缴费基数'',
  `medical_company_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''医疗单位缴费比例'',
  `medical_company_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''医疗单位缴费金额'',
  `medical_retire_sign` varchar(50) DEFAULT NULL COMMENT ''医疗退休标识'',
  `servious_illness_retire_sign` varchar(50) DEFAULT NULL COMMENT ''大病退休标识'',
  `medical_person_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''医疗个人缴费比例'',
  `medical_person_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''医疗个人缴费金额'',
  `medical_interest` decimal(10,2) DEFAULT ''0.00'' COMMENT ''医疗利息'',
  `medical_late_fees` decimal(10,2) DEFAULT ''0.00'' COMMENT ''医疗滞纳金'',
  `medical_arrival_account_sign` varchar(50) DEFAULT NULL COMMENT ''医疗到账标志'',
  `big_medical_pay_type` varchar(50) DEFAULT NULL COMMENT ''大额医疗缴费类型'',
  `big_medical_company_pay_base` decimal(10,2) DEFAULT NULL COMMENT ''大额医疗单位缴费基数'',
  `big_medical_company_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''大额医疗单位缴费比例'',
  `big_medical_company_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''大额医疗单位缴费金额'',
  `big_medical_company_pay_scale` decimal(10,3) DEFAULT ''0.000'' COMMENT ''大额医疗单位缴费比例(重复)'',
  `big_medical_company_pay_coins` decimal(10,2) DEFAULT ''0.00'' COMMENT ''大额医疗单位缴费金额(重复)'',
  `big_medical_late_fees` decimal(10,2) DEFAULT ''0.00'' COMMENT ''大额医疗滞纳金'',
  `big_medical_arrival_account_sign` varchar(50) DEFAULT NULL COMMENT ''大额医疗到账标志'',
  `work_injury_pay_type` varchar(50) DEFAULT NULL COMMENT ''工伤缴费类型'',
  `work_injury_pay_base` decimal(10,2) DEFAULT NULL COMMENT ''工伤缴费基数'',
  `work_injury_company_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''工伤单位缴费比例'',
  `work_injury_company_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''工伤单位缴费金额'',
  `work_injury_late_fees` decimal(10,2) DEFAULT ''0.00'' COMMENT ''工伤滞纳金'',
  `work_injury_arrival_account_sign` varchar(50) DEFAULT NULL COMMENT ''工伤到账标志'',
  `reproduction_pay_type` varchar(50) DEFAULT NULL COMMENT ''生育缴费类型'',
  `reproduction_pay_base` decimal(10,2) DEFAULT NULL COMMENT ''生育缴费基数'',
  `reproduction_company_pay_ratio` decimal(10,3) DEFAULT ''0.000'' COMMENT ''生育单位缴费比例'',
  `reproduction_company_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''生育单位缴费金额'',
  `reproduction_company_pay_coins` decimal(10,2) DEFAULT ''0.00'' COMMENT ''生育单位缴费金额(重复)'',
  `reproduction_person_pay_money` decimal(10,2) DEFAULT ''0.00'' COMMENT ''生育个人缴费金额'',
  `reproduction_late_fees` decimal(10,2) DEFAULT ''0.00'' COMMENT ''工伤滞纳金'',
  `reproduction_arrival_account_sign` varchar(50) DEFAULT NULL COMMENT ''生育到账标志'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `master_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8 COMMENT=''导入社保信息'';

-- ----------------------------
-- Table structure for tb_hr_social_security_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_social_security_master`;
CREATE TABLE `tb_hr_social_security_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门id'',
  `department_name` varchar(200) DEFAULT NULL COMMENT ''部门名称'',
  `import_date` varchar(20) DEFAULT NULL COMMENT ''导入日期月份'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_social_security_proportion
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_social_security_proportion`;
CREATE TABLE `tb_hr_social_security_proportion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `belong_company` int(11) DEFAULT NULL COMMENT ''所属公司ID'',
  `belong_company_name` varchar(255) DEFAULT NULL COMMENT ''所属公司名称'',
  `social_security_name` varchar(50) NOT NULL COMMENT ''险种名称'',
  `base_proportion` decimal(10,4) NOT NULL DEFAULT ''0.0000'' COMMENT ''全局占基数比例'',
  `company_proportion` decimal(10,4) NOT NULL DEFAULT ''0.0000'' COMMENT ''公司缴费占比'',
  `person_proportion` decimal(10,4) NOT NULL DEFAULT ''0.0000'' COMMENT ''个人缴费占比'',
  `social_security_type` varchar(20) NOT NULL COMMENT ''险种类型'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT=''系统社保全局占比表'';

-- ----------------------------
-- Table structure for tb_hr_social_security_provident_fund_agents_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_social_security_provident_fund_agents_master`;
CREATE TABLE `tb_hr_social_security_provident_fund_agents_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `process_inst_id` varchar(64) NOT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT ''状态'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_social_security_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_social_security_template`;
CREATE TABLE `tb_hr_social_security_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `department_id` int(11) NOT NULL COMMENT ''部门(公司)ID'',
  `level_name` varchar(100) NOT NULL COMMENT ''级别名称'',
  `medicare_base_percent` decimal(10,2) DEFAULT NULL COMMENT ''医疗保险缴纳百分比'',
  `medicare_base_amount` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''医疗保险缴纳基数(分)'',
  `pension_base_percent` decimal(10,2) DEFAULT NULL COMMENT ''养老保险缴纳百分比'',
  `pension_base_amount` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''养老保险缴纳基数(分)'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  KEY `template_department_id` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT=''部门(公司)社保模板'';

-- ----------------------------
-- Table structure for tb_hr_staff_wage_setting
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_staff_wage_setting`;
CREATE TABLE `tb_hr_staff_wage_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_name` varchar(50) DEFAULT NULL COMMENT ''员工姓名'',
  `staff_account` varchar(50) DEFAULT NULL COMMENT ''账号'',
  `staff_department` int(11) DEFAULT NULL COMMENT ''所属部门'',
  `staff_department_name` varchar(100) DEFAULT NULL COMMENT ''所属部门名称'',
  `base_pay` int(11) DEFAULT NULL COMMENT ''基本工资等级ID'',
  `base_pay_name` varchar(100) DEFAULT NULL COMMENT ''基本工资等级名称'',
  `merit_pay` int(11) DEFAULT NULL COMMENT ''绩效工资等级ID'',
  `merit_pay_name` varchar(100) DEFAULT NULL COMMENT ''绩效工资等级名称'',
  `wage_subsidy` varchar(255) DEFAULT NULL COMMENT ''补贴工资选项ID'',
  `wage_subsidy_name` varchar(500) DEFAULT NULL COMMENT ''补贴工资选项名称'',
  `other_wage` varchar(255) DEFAULT NULL COMMENT ''其他工资等级ID'',
  `other_wage_name` varchar(500) DEFAULT NULL COMMENT ''其他工资等级名称'',
  `social_security` int(11) DEFAULT NULL COMMENT ''社保等级'',
  `provident_fund` int(11) DEFAULT NULL COMMENT ''公积金等级'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `ote_that` varchar(1000) DEFAULT NULL COMMENT ''备注说明'',
  `qualification_wage` bigint(20) DEFAULT NULL COMMENT ''资格工资'',
  `job_title_wage` bigint(20) DEFAULT NULL COMMENT ''职称工资'',
  `internship_wage` bigint(20) DEFAULT NULL COMMENT ''实习工资'',
  `kickback_performance` bit(1) DEFAULT b''0'' COMMENT ''是否回扣绩效工资'',
  `bis_valid` bit(1) DEFAULT b''0'' COMMENT ''默认0：表示无效，1：有效'',
  `effective_date` datetime DEFAULT NULL COMMENT ''生效日期'',
  `social_security_base` bigint(20) DEFAULT ''0'' COMMENT ''社保基数'',
  `social_security_pension` bigint(20) DEFAULT ''0'' COMMENT ''养老基数'',
  `provident_fund_base` bigint(20) DEFAULT ''0'' COMMENT ''公积金基数'',
  `post_amount` bigint(20) DEFAULT ''0'' COMMENT ''岗位工资'',
  `bis_pause_social_security` bit(1) DEFAULT b''0'' COMMENT ''是否暂停缴纳社保'',
  `bis_pause_provident_fund` bit(1) DEFAULT b''0'' COMMENT ''是否暂停缴纳公积金'',
  `bis_pause_pay_salary` bit(1) DEFAULT b''0'' COMMENT ''是否暂停发放工资'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_take_off_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_take_off_record`;
CREATE TABLE `tb_hr_take_off_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(100) DEFAULT NULL COMMENT ''员工账号'',
  `hours` decimal(11,2) DEFAULT NULL COMMENT ''小时数'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT=''调休假记录'';

-- ----------------------------
-- Table structure for tb_hr_train_content_affirm
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_content_affirm`;
CREATE TABLE `tb_hr_train_content_affirm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `participant_id` varchar(100) DEFAULT NULL COMMENT ''参会人员id'',
  `participant_name` varchar(255) DEFAULT NULL COMMENT ''参会人员名称'',
  `submit_proportion` int(11) DEFAULT NULL COMMENT ''提交比例'',
  `end_time` datetime DEFAULT NULL COMMENT ''提交结束时间'',
  `survey_id` varchar(100) DEFAULT NULL COMMENT ''调查单id'',
  `survey_name` varchar(255) DEFAULT NULL COMMENT ''调查单名称'',
  `meeting_theme` varchar(255) DEFAULT NULL COMMENT ''会议主题'',
  `meeting_time` datetime DEFAULT NULL COMMENT ''会议时间'',
  `meeting_site` varchar(50) DEFAULT NULL COMMENT ''会议地点'',
  `new_participant_id` varchar(100) DEFAULT NULL COMMENT ''新参会人员id'',
  `new_participant_name` varchar(255) DEFAULT NULL COMMENT ''新参会人员名称'',
  `train_name` varchar(255) DEFAULT NULL COMMENT ''培训名称'',
  `content` varchar(255) DEFAULT NULL COMMENT ''内容'',
  `set_target` varchar(255) DEFAULT NULL COMMENT ''设定目标'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT=''培训内容确认主表'';

-- ----------------------------
-- Table structure for tb_hr_train_content_affirm_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_content_affirm_detail`;
CREATE TABLE `tb_hr_train_content_affirm_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `train_name` varchar(255) DEFAULT NULL COMMENT ''培训项目名称'',
  `content` varchar(255) DEFAULT NULL COMMENT ''内容'',
  `set_goals` varchar(255) DEFAULT NULL COMMENT ''设定目标'',
  `opinion` varchar(255) DEFAULT NULL COMMENT ''调查规整意见'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `survey_detail_id` int(11) DEFAULT NULL COMMENT ''绑定调查需求明细id'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8 COMMENT=''培训内容确认项目信息明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_content_affirm_detail_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_content_affirm_detail_project`;
CREATE TABLE `tb_hr_train_content_affirm_detail_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `train_name` varchar(255) DEFAULT NULL COMMENT ''培训项目名称'',
  `content` varchar(255) DEFAULT NULL COMMENT ''内容'',
  `set_goals` varchar(255) DEFAULT NULL COMMENT ''设定目标'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `detail_id` int(11) DEFAULT NULL COMMENT ''关联detail表'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT=''培训内容确认项目表'';

-- ----------------------------
-- Table structure for tb_hr_train_content_affirm_detail_sub
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_content_affirm_detail_sub`;
CREATE TABLE `tb_hr_train_content_affirm_detail_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_id` int(11) DEFAULT ''0'' COMMENT ''关联Details表中Id'',
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `creator_name` varchar(20) DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `opinion` varchar(255) DEFAULT NULL COMMENT ''建议内容'',
  `survey_detail_id` int(11) DEFAULT NULL COMMENT ''绑定调查需求明细id'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT=''培训内容确认会签意见表'';

-- ----------------------------
-- Table structure for tb_hr_train_courseware_examine
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_courseware_examine`;
CREATE TABLE `tb_hr_train_courseware_examine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_batch` varchar(100) DEFAULT NULL COMMENT ''培训批次id'',
  `train_batch_name` varchar(255) DEFAULT NULL COMMENT ''培训批次名称'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT=''课件审查主表'';

-- ----------------------------
-- Table structure for tb_hr_train_courseware_examine_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_courseware_examine_detail`;
CREATE TABLE `tb_hr_train_courseware_examine_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) NOT NULL DEFAULT ''0'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `curriculum` varchar(100) DEFAULT NULL COMMENT ''课程'',
  `teacher` varchar(50) DEFAULT NULL COMMENT ''培训老师'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT=''课件审查课程和教师明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_courseware_examine_detail_sub
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_courseware_examine_detail_sub`;
CREATE TABLE `tb_hr_train_courseware_examine_detail_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_id` int(11) DEFAULT ''0'' COMMENT ''关联detail表ID'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `examine_content` varchar(255) DEFAULT NULL COMMENT ''审查内容'',
  `examine_conclusion` varchar(255) DEFAULT NULL COMMENT ''审查结论'',
  `amending_advice` varchar(255) DEFAULT NULL COMMENT ''修改建议'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT=''课件审查明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_effect_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_effect_evaluate`;
CREATE TABLE `tb_hr_train_effect_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_date` datetime DEFAULT NULL COMMENT ''培训日期'',
  `curriculum` varchar(100) DEFAULT NULL COMMENT ''课程'',
  `teacher` varchar(50) DEFAULT NULL COMMENT ''老师'',
  `evaluation_mode` varchar(255) DEFAULT NULL COMMENT ''考核方式'',
  `is_anonymity_id` varchar(5) DEFAULT NULL COMMENT ''是否匿名id'',
  `is_anonymity_name` varchar(50) DEFAULT NULL COMMENT ''是否匿名名称'',
  `inform_detail_id` int(11) DEFAULT NULL COMMENT ''关联培训通知从表id'',
  `log_template` text COMMENT ''评价模版'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT=''培训效果评价表'';

-- ----------------------------
-- Table structure for tb_hr_train_external_achievement
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_external_achievement`;
CREATE TABLE `tb_hr_train_external_achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_batch` varchar(100) DEFAULT NULL COMMENT ''培训批次id'',
  `train_batch_name` varchar(255) DEFAULT NULL COMMENT ''培训批次名称'',
  `start_time` datetime DEFAULT NULL COMMENT ''开始日期'',
  `end_time` datetime DEFAULT NULL COMMENT ''结束日期'',
  `accompanying_people_id` varchar(255) DEFAULT NULL COMMENT ''随同人员id'',
  `accompanying_people_name` varchar(255) DEFAULT NULL COMMENT ''随同人员名称'',
  `department_id` varchar(50) DEFAULT NULL COMMENT ''所属外勤id'',
  `department_name` varchar(100) DEFAULT NULL COMMENT ''所属外勤名称'',
  `total_period` bigint(10) DEFAULT NULL COMMENT ''总学时'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT=''外部培训成果主表'';

-- ----------------------------
-- Table structure for tb_hr_train_external_achievement_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_external_achievement_detail`;
CREATE TABLE `tb_hr_train_external_achievement_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_date` datetime DEFAULT NULL COMMENT ''培训日期'',
  `curriculum` varchar(100) DEFAULT NULL COMMENT ''课程'',
  `teacher` varchar(50) DEFAULT NULL COMMENT ''老师'',
  `evaluation_mode` varchar(255) DEFAULT NULL COMMENT ''考核方式'',
  `inform_detail_id` int(11) DEFAULT NULL COMMENT ''培训通知从表id'',
  `period` bigint(10) DEFAULT NULL COMMENT ''学时'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COMMENT=''外部培训成果明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_external_apply
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_external_apply`;
CREATE TABLE `tb_hr_train_external_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `issue_unit_id` varchar(100) DEFAULT NULL COMMENT ''发布单位id'',
  `issue_unit_name` varchar(100) DEFAULT NULL COMMENT ''发布单位名称'',
  `issue_date` datetime DEFAULT NULL COMMENT ''发布日期'',
  `number` varchar(50) DEFAULT NULL COMMENT ''文号'',
  `total_period` bigint(10) DEFAULT NULL COMMENT ''总学时'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT=''外部培训申报主表'';

-- ----------------------------
-- Table structure for tb_hr_train_external_apply_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_external_apply_detail`;
CREATE TABLE `tb_hr_train_external_apply_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `creator_name` varchar(50) DEFAULT NULL COMMENT ''创建人名称'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_batch` varchar(255) DEFAULT NULL COMMENT ''培训批次'',
  `execute_subject` varchar(255) DEFAULT NULL COMMENT ''实施主体'',
  `date` datetime DEFAULT NULL COMMENT ''日期'',
  `train_course` varchar(255) DEFAULT NULL COMMENT ''培训课程'',
  `curriculum_nature_id` varchar(100) DEFAULT NULL COMMENT ''课程性质id'',
  `curriculum_nature_name` varchar(255) DEFAULT NULL COMMENT ''课程性质名称'',
  `period` bigint(10) DEFAULT NULL COMMENT ''学时'',
  `teach_unit` varchar(100) DEFAULT NULL COMMENT ''授课单位'',
  `teacher` varchar(50) DEFAULT NULL COMMENT ''授课人'',
  `teach_site` varchar(100) DEFAULT NULL COMMENT ''授课地点'',
  `description` varchar(255) DEFAULT NULL COMMENT ''说明'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT=''外部培训申报明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_external_approval
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_external_approval`;
CREATE TABLE `tb_hr_train_external_approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `issue_unit_id` varchar(100) DEFAULT NULL COMMENT ''发布单位id'',
  `issue_unit_name` varchar(100) DEFAULT NULL COMMENT ''发布单位名称'',
  `issue_date` datetime DEFAULT NULL COMMENT ''发布日期'',
  `number` varchar(50) DEFAULT NULL COMMENT ''文号'',
  `train_batch` varchar(100) DEFAULT NULL COMMENT ''培训批次id'',
  `train_batch_name` varchar(255) DEFAULT NULL COMMENT ''培训批次名称'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT=''外部培训审批主表'';

-- ----------------------------
-- Table structure for tb_hr_train_external_approval_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_external_approval_detail`;
CREATE TABLE `tb_hr_train_external_approval_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `apply_detail_id` int(11) DEFAULT NULL COMMENT ''关联申报从表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `execute_subject` varchar(255) DEFAULT NULL COMMENT ''实施主体'',
  `date` datetime DEFAULT NULL COMMENT ''日期'',
  `train_course` varchar(255) DEFAULT NULL COMMENT ''培训课程'',
  `period` bigint(10) DEFAULT NULL COMMENT ''学时'',
  `teach_unit` varchar(100) DEFAULT NULL COMMENT ''授课单位'',
  `teacher` varchar(50) DEFAULT NULL COMMENT ''授课人'',
  `teach_site` varchar(100) DEFAULT NULL COMMENT ''授课地点'',
  `description` varchar(255) DEFAULT NULL COMMENT ''说明'',
  `apply_people_id` varchar(50) DEFAULT NULL COMMENT ''申报人id'',
  `apply_people_name` varchar(50) DEFAULT NULL COMMENT ''申报人名称'',
  `curriculum_nature_id` varchar(100) DEFAULT NULL COMMENT ''课程性质id'',
  `curriculum_nature_name` varchar(255) DEFAULT NULL COMMENT ''课程性质名称'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT=''外部培训审批明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_inform
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_inform`;
CREATE TABLE `tb_hr_train_inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_batch` varchar(100) DEFAULT NULL COMMENT ''培训批次id'',
  `train_batch_name` varchar(255) DEFAULT NULL COMMENT ''培训批次名称'',
  `train_content` varchar(255) DEFAULT NULL COMMENT ''培训内容'',
  `start_time` datetime DEFAULT NULL COMMENT ''开始时间'',
  `end_time` datetime DEFAULT NULL COMMENT ''结束时间'',
  `train_site` varchar(255) DEFAULT NULL COMMENT ''培训地点'',
  `train_method` varchar(255) DEFAULT NULL COMMENT ''培训方式'',
  `train_department_id` varchar(100) DEFAULT NULL COMMENT ''培训部门id'',
  `train_department_name` varchar(255) DEFAULT NULL COMMENT ''培训部门名称'',
  `train_object_id` varchar(255) DEFAULT NULL COMMENT ''培训对象id'',
  `train_object_name` varchar(255) DEFAULT NULL COMMENT ''培训对象名称'',
  `deadline` datetime DEFAULT NULL COMMENT ''确认截止时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT=''培训通知主表'';

-- ----------------------------
-- Table structure for tb_hr_train_inform_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_inform_detail`;
CREATE TABLE `tb_hr_train_inform_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_date` datetime DEFAULT NULL COMMENT ''培训日期'',
  `curriculum` varchar(100) DEFAULT NULL COMMENT ''课程'',
  `teacher` varchar(50) DEFAULT NULL COMMENT ''老师'',
  `evaluation_mode` varchar(255) DEFAULT NULL COMMENT ''考核方式'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT=''培训通知明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_needs_survey
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_needs_survey`;
CREATE TABLE `tb_hr_train_needs_survey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `survey_type_id` int(11) DEFAULT NULL,
  `survey_type_name` varchar(255) DEFAULT NULL,
  `survey_unit_id` varchar(50) DEFAULT NULL COMMENT ''调查单位id'',
  `survey_unit_name` varchar(100) DEFAULT NULL COMMENT ''调查单位名称'',
  `principal_id` varchar(100) DEFAULT NULL COMMENT ''负责人id'',
  `principal_name` varchar(255) DEFAULT NULL COMMENT ''负责人名称'',
  `participant_id` varchar(100) DEFAULT NULL COMMENT ''参与人id'',
  `participant_name` varchar(255) DEFAULT NULL COMMENT ''参与人名称'',
  `main_contents` varchar(255) DEFAULT NULL COMMENT ''调查主要内容'',
  `deadline` datetime DEFAULT NULL COMMENT ''截止时间'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT=''培训需求调查主表'';

-- ----------------------------
-- Table structure for tb_hr_train_needs_survey_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_needs_survey_detail`;
CREATE TABLE `tb_hr_train_needs_survey_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `train_name` varchar(255) DEFAULT NULL COMMENT ''培训项目名称'',
  `content` varchar(255) DEFAULT NULL COMMENT ''内容'',
  `set_goals` varchar(255) DEFAULT NULL COMMENT ''设定目标'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT=''培训需求调查明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_needs_survey_detail_sub
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_needs_survey_detail_sub`;
CREATE TABLE `tb_hr_train_needs_survey_detail_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_id` int(11) DEFAULT ''0'' COMMENT ''关联Details表中Id'',
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `creator_name` varchar(20) DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `is_suit_id` varchar(10) DEFAULT NULL COMMENT ''是否适宜id'',
  `is_suit_name` varchar(50) DEFAULT NULL COMMENT ''是否适宜名称'',
  `suggest_content` varchar(255) DEFAULT NULL COMMENT ''建议内容'',
  `type` varchar(10) DEFAULT NULL COMMENT ''类型'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT=''培训需求调查会签意见表'';

-- ----------------------------
-- Table structure for tb_hr_train_plan
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_plan`;
CREATE TABLE `tb_hr_train_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''状态'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT=''培训计划主表'';

-- ----------------------------
-- Table structure for tb_hr_train_plan_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_plan_detail`;
CREATE TABLE `tb_hr_train_plan_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_batch` varchar(100) DEFAULT NULL COMMENT ''培训批次id'',
  `train_batch_name` varchar(255) DEFAULT NULL COMMENT ''培训批次名称'',
  `train_content` varchar(255) DEFAULT NULL COMMENT ''培训内容'',
  `remind_time` datetime DEFAULT NULL COMMENT ''培训计划提醒时间'',
  `log_template` text COMMENT ''日志模版HTML'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT=''培训计划批次表'';

-- ----------------------------
-- Table structure for tb_hr_train_plan_detail_sub
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_plan_detail_sub`;
CREATE TABLE `tb_hr_train_plan_detail_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_id` int(11) DEFAULT ''0'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `start_time` datetime DEFAULT NULL COMMENT ''开始时间'',
  `end_time` datetime DEFAULT NULL COMMENT ''结束时间'',
  `train_teachers` varchar(255) DEFAULT NULL COMMENT ''培训师资'',
  `train_site` varchar(255) DEFAULT NULL COMMENT ''培训地点'',
  `train_method` varchar(255) DEFAULT NULL COMMENT ''培训方式'',
  `evaluation_mode` varchar(255) DEFAULT NULL COMMENT ''考核方式'',
  `train_department_id` varchar(100) DEFAULT NULL COMMENT ''培训部门id'',
  `train_department_name` varchar(255) DEFAULT NULL COMMENT ''培训部门名称'',
  `train_object_id` varchar(255) DEFAULT NULL COMMENT ''培训对象id'',
  `train_object_name` varchar(255) DEFAULT NULL COMMENT ''培训对象名称'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT=''培训计划明细表'';

-- ----------------------------
-- Table structure for tb_hr_train_sign_in_spot_check
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_sign_in_spot_check`;
CREATE TABLE `tb_hr_train_sign_in_spot_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_batch` varchar(100) DEFAULT NULL COMMENT ''培训批次id'',
  `train_batch_name` varchar(255) DEFAULT NULL COMMENT ''培训批次名称'',
  `curriculum` varchar(100) DEFAULT NULL COMMENT ''课程id'',
  `curriculum_name` varchar(255) DEFAULT NULL COMMENT ''课程名称'',
  `train_time` datetime DEFAULT NULL COMMENT ''日期时间'',
  `train_site` varchar(255) DEFAULT NULL COMMENT ''培训地点'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT=''培训签到抽查主表'';

-- ----------------------------
-- Table structure for tb_hr_train_sign_in_spot_check_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_train_sign_in_spot_check_detail`;
CREATE TABLE `tb_hr_train_sign_in_spot_check_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''关联主表id'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `train_id` varchar(50) DEFAULT NULL COMMENT ''培训人员id'',
  `train_name` varchar(50) DEFAULT NULL COMMENT ''培训人员名称'',
  `is_absent_id` int(11) DEFAULT NULL COMMENT ''是否缺席id'',
  `is_absent_name` varchar(50) DEFAULT NULL COMMENT ''是否缺席名称'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT=''培训签到抽查明细表'';

-- ----------------------------
-- Table structure for tb_hr_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user`;
CREATE TABLE `tb_hr_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `user_account` varchar(32) NOT NULL COMMENT ''账户名'',
  `user_password` varchar(100) DEFAULT NULL COMMENT ''密码'',
  `user_nickname` varchar(100) DEFAULT NULL COMMENT ''姓名'',
  `ids` varchar(20) DEFAULT NULL COMMENT ''身份证号码'',
  `gender` varchar(10) DEFAULT NULL COMMENT ''性别'',
  `entry_date` datetime DEFAULT NULL COMMENT ''入职日期'',
  `formal_date` datetime DEFAULT NULL COMMENT ''转正日期'',
  `quit_date` datetime DEFAULT NULL COMMENT ''离职日期'',
  `employee_type` int(11) DEFAULT NULL COMMENT ''员工类型'',
  `department_id` int(11) DEFAULT NULL COMMENT ''所属部门'',
  `post` int(11) DEFAULT NULL COMMENT ''岗位'',
  `post_grade` int(11) DEFAULT NULL COMMENT ''岗级'',
  `position` int(11) DEFAULT NULL COMMENT ''职级'',
  `superior` varchar(100) DEFAULT NULL COMMENT ''直接上级'',
  `company_email` varchar(100) DEFAULT '''' COMMENT ''公司邮箱'',
  `mobile` varchar(20) DEFAULT ''0'' COMMENT ''手机号码'',
  `wechat` varchar(20) DEFAULT NULL COMMENT ''微信号'',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `bis_enable` bit(1) DEFAULT NULL COMMENT ''是否有效'',
  `user_status` varchar(10) DEFAULT ''未激活'' COMMENT ''人员当前状态'',
  `process_ins_id` varchar(64) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT ''finish'' COMMENT ''流程实例状态'',
  `bis_special` bit(1) DEFAULT b''0'' COMMENT ''是否特殊成员'',
  `relation_object` varchar(10) DEFAULT NULL COMMENT ''关联对象'',
  `relation_relationship` varchar(20) DEFAULT NULL COMMENT ''关系'',
  `relation_proportion` int(11) DEFAULT NULL COMMENT ''关联默认占比'',
  `working_life` int(11) DEFAULT ''0'' COMMENT ''在其它公司的工作年限'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `company_id` int(11) DEFAULT NULL COMMENT ''所属公司ID'',
  `bis_promotion` bit(1) DEFAULT b''0'' COMMENT ''是否已转正'',
  `generator_annual_leave_date` datetime DEFAULT NULL COMMENT ''最近一次生成年假记录的日期'',
  `erp_user_id` int(11) DEFAULT ''0'' COMMENT ''ERP中用户编号'',
  `attendance_number` varchar(100) DEFAULT NULL COMMENT ''考勤编号'',
  `quit_edit_user` varchar(20) DEFAULT NULL COMMENT ''离职操作人'',
  `salary_user` varchar(20) DEFAULT NULL COMMENT ''考勤计算人'',
  `contract_begin_date` datetime DEFAULT NULL COMMENT ''合同开始日期'',
  `contract_end_date` datetime DEFAULT NULL COMMENT ''合同结束日期'',
  `contract_id` int(11) DEFAULT NULL COMMENT ''合同编号'',
  `bis_attendance` bit(1) DEFAULT b''1'' COMMENT ''是否考勤'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_org_user_user_account_uindex` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=425 DEFAULT CHARSET=utf8 COMMENT=''组织机构用户表'';

-- ----------------------------
-- Table structure for tb_hr_user_bankcard
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_bankcard`;
CREATE TABLE `tb_hr_user_bankcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `bank_name` varchar(50) DEFAULT NULL COMMENT ''开户银行'',
  `bank_branch` varchar(50) DEFAULT NULL COMMENT ''支行'',
  `account_number` varchar(20) DEFAULT NULL COMMENT ''卡号'',
  `bis_enable` bit(1) DEFAULT NULL COMMENT ''是否有效'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT=''员工银行卡信息'';

-- ----------------------------
-- Table structure for tb_hr_user_contacts
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_contacts`;
CREATE TABLE `tb_hr_user_contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `contacts_name` varchar(20) DEFAULT NULL COMMENT ''联系人姓名'',
  `contacts_relationship` varchar(10) DEFAULT NULL COMMENT ''关系'',
  `contacts_telephone` varchar(15) DEFAULT NULL COMMENT ''电话'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `contacts_birthday` date DEFAULT NULL,
  `contacts_company` varchar(100) DEFAULT NULL,
  `bis_urgent` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT=''联系人信息表'';

-- ----------------------------
-- Table structure for tb_hr_user_department_adjustment
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_department_adjustment`;
CREATE TABLE `tb_hr_user_department_adjustment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `user_id` int(11) DEFAULT NULL,
  `user_account` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL COMMENT ''调动后部门'',
  `superior` varchar(20) DEFAULT NULL COMMENT ''直接上级'',
  `post_id` int(11) DEFAULT NULL COMMENT ''新岗位'',
  `post_grade_id` int(11) DEFAULT NULL COMMENT ''新岗级'',
  `adjustment_explain` varchar(500) DEFAULT NULL COMMENT ''调动说明'',
  `implement_date` datetime DEFAULT NULL COMMENT ''执行日期'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT NULL COMMENT ''创建时间'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `confirm_superior` varchar(255) DEFAULT NULL COMMENT ''确认上级'',
  `confirm_post_id` int(11) DEFAULT NULL COMMENT ''确认岗位'',
  `confirm_post_grade_id` int(11) DEFAULT NULL COMMENT ''确认岗级'',
  `confirm_implement_date` datetime DEFAULT NULL COMMENT ''确认调岗时间'',
  `actual_department_id` int(11) DEFAULT NULL COMMENT ''确认调岗部门'',
  `actual_post_id` int(11) DEFAULT NULL COMMENT ''确认岗位'',
  `actual_post_grade_id` int(11) DEFAULT NULL COMMENT ''确认岗级'',
  `actual_implement_date` datetime DEFAULT NULL COMMENT ''确认调岗日期'',
  `actual_position_id` int(11) DEFAULT NULL COMMENT ''确认职级'',
  `bis_update` bit(1) DEFAULT b''0'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT=''跨部门调岗'';

-- ----------------------------
-- Table structure for tb_hr_user_education
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_education`;
CREATE TABLE `tb_hr_user_education` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `education` int(11) DEFAULT NULL COMMENT ''学历或学位'',
  `certificate_number` varchar(30) DEFAULT NULL COMMENT ''证书编号'',
  `graduation_school` varchar(50) DEFAULT NULL COMMENT ''毕业学校'',
  `major` varchar(50) DEFAULT NULL COMMENT ''专业'',
  `form_education` int(11) DEFAULT NULL COMMENT ''教育形式'',
  `entrance_date` datetime DEFAULT NULL COMMENT ''入学日期'',
  `graduation_date` datetime DEFAULT NULL COMMENT ''毕业日期'',
  `reward` varchar(1000) DEFAULT NULL COMMENT ''证书或奖励'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT=''员工教育背景'';

-- ----------------------------
-- Table structure for tb_hr_user_family
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_family`;
CREATE TABLE `tb_hr_user_family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `family_name` varchar(20) DEFAULT NULL,
  `family_relationship` varchar(10) DEFAULT NULL,
  `family_birthday` datetime DEFAULT NULL,
  `family_telephone` varchar(15) DEFAULT NULL,
  `family_company` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT=''员工家庭成员表'';

-- ----------------------------
-- Table structure for tb_hr_user_formal
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_formal`;
CREATE TABLE `tb_hr_user_formal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''用户编号'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''用户账号'',
  `expect_formal_type` int(11) DEFAULT NULL COMMENT ''转正类型'',
  `expect_post_id` int(11) DEFAULT NULL COMMENT ''期望转正岗位'',
  `expect_post_grade_id` int(11) DEFAULT NULL COMMENT ''期望转正岗级'',
  `expect_formal_date` datetime DEFAULT NULL COMMENT ''期望转正日期'',
  `job_summary` varchar(500) DEFAULT NULL COMMENT ''工作总结'',
  `self_evaluation` varchar(500) DEFAULT NULL COMMENT ''自我评价'',
  `career_planning` varchar(500) DEFAULT NULL COMMENT ''个人职业规划'',
  `actual_formal_type` int(11) DEFAULT NULL COMMENT ''实际转正类型'',
  `actual_post_id` int(11) DEFAULT NULL COMMENT ''实际转正岗位'',
  `actual_post_grade_id` int(11) DEFAULT NULL COMMENT ''实际转正岗级'',
  `actual_formal_date` datetime DEFAULT NULL COMMENT ''实际转正日期'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `position` int(11) DEFAULT NULL COMMENT ''职级'',
  `convene_meeting` varchar(100) DEFAULT NULL COMMENT ''召开过转正会议'',
  `reason` varchar(1000) DEFAULT NULL COMMENT ''未召开会议原因'',
  `superior_suggestion_formal_type` int(11) DEFAULT NULL COMMENT ''上级建议转正类型'',
  `superior_suggestion_post_id` int(11) DEFAULT NULL COMMENT ''上级建议转正岗位'',
  `superior_suggestion_post_grade_id` int(11) DEFAULT NULL COMMENT ''上级建议转正岗级'',
  `superior_suggestion_formal_date` datetime DEFAULT NULL COMMENT ''上级建议转正日期'',
  `department_suggestion_formal_type` int(11) DEFAULT NULL COMMENT ''部门建议转正类型'',
  `department_suggestion_post_id` int(11) DEFAULT NULL COMMENT ''部门建议转正岗位'',
  `department_suggestion_post_grade_id` int(11) DEFAULT NULL COMMENT ''部门建议转正岗级'',
  `department_suggestion_formal_date` datetime DEFAULT NULL COMMENT ''部门建议转正日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT=''转正流程'';

-- ----------------------------
-- Table structure for tb_hr_user_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_history`;
CREATE TABLE `tb_hr_user_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''人员账号'',
  `begin_date` datetime DEFAULT NULL COMMENT ''开始日期'',
  `end_date` datetime DEFAULT NULL COMMENT ''结束日期'',
  `department_name` varchar(50) DEFAULT NULL COMMENT ''部门'',
  `post_name` varchar(50) DEFAULT NULL COMMENT ''岗位'',
  `post_grade_name` varchar(50) DEFAULT NULL COMMENT ''岗级'',
  `opation_type` varchar(20) DEFAULT NULL COMMENT ''操作说明'',
  `opation_process_ins_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=940 DEFAULT CHARSET=utf8 COMMENT=''员工本公司工作记录'';

-- ----------------------------
-- Table structure for tb_hr_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_info`;
CREATE TABLE `tb_hr_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''人员编号'',
  `nation` varchar(10) DEFAULT NULL COMMENT ''民族'',
  `birthday` datetime DEFAULT NULL COMMENT ''生日'',
  `political_visage` int(11) DEFAULT NULL COMMENT ''政治面貌'',
  `marriage` int(11) DEFAULT NULL COMMENT ''婚姻状况'',
  `bis_children` bit(1) DEFAULT NULL COMMENT ''有无子女'',
  `children_count` int(11) DEFAULT ''0'' COMMENT ''子女个数'',
  `ids_address` varchar(255) DEFAULT NULL COMMENT ''身份证地址'',
  `live_address` varchar(255) DEFAULT NULL COMMENT ''居住地址'',
  `skill` varchar(500) DEFAULT NULL COMMENT ''职业技能'',
  `mobile` varchar(20) DEFAULT NULL COMMENT ''手机号码'',
  `native_place` varchar(100) DEFAULT NULL COMMENT ''籍贯'',
  `height` varchar(20) DEFAULT NULL COMMENT ''身高'',
  `weight` varchar(20) DEFAULT NULL COMMENT ''体重'',
  `social_security_status` int(11) DEFAULT NULL COMMENT ''社保缴纳状态'',
  `location_file` varchar(255) DEFAULT NULL COMMENT ''档案所在地'',
  `qq` varchar(20) DEFAULT NULL COMMENT ''qq号'',
  `weixin` varchar(100) DEFAULT NULL COMMENT ''微信号'',
  `personal_email` varchar(100) DEFAULT NULL COMMENT ''个人邮箱'',
  `hobby` varchar(1000) DEFAULT NULL COMMENT ''兴趣爱好'',
  `self_evaluation` varchar(1000) DEFAULT NULL COMMENT ''自我评价'',
  `bis_lunar_calendar` bit(1) DEFAULT b''0'' COMMENT ''出生日期是否为阴历'',
  `employment_form` varchar(100) DEFAULT NULL COMMENT ''就业形式'',
  `position` int(11) DEFAULT NULL COMMENT ''职级'',
  `working_life` decimal(11,2) DEFAULT NULL COMMENT ''工作年限'',
  `social_security_number` varchar(50) DEFAULT NULL,
  `social_security_type` int(11) DEFAULT NULL,
  `accumulation_fund_account` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT=''员工个人基本信息表'';

-- ----------------------------
-- Table structure for tb_hr_user_insurance
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_insurance`;
CREATE TABLE `tb_hr_user_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''用户编号'',
  `insurance_type` int(11) DEFAULT NULL COMMENT ''保险类型'',
  `insurance_base` decimal(10,2) DEFAULT NULL COMMENT ''原单位缴纳基数'',
  `past_end_date` datetime DEFAULT NULL COMMENT ''原单位截止日期'',
  `now_start_date` datetime DEFAULT NULL COMMENT ''本单位开始缴纳日期'',
  `remarks` varchar(255) DEFAULT NULL COMMENT ''说明'',
  `creator` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT=''保险缴费情况'';

-- ----------------------------
-- Table structure for tb_hr_user_post_adjustment
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_post_adjustment`;
CREATE TABLE `tb_hr_user_post_adjustment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''员工账号'',
  `post_id` int(11) DEFAULT NULL COMMENT ''新岗位'',
  `post_grade_id` int(11) DEFAULT NULL COMMENT ''新岗级'',
  `implement_date` datetime DEFAULT NULL COMMENT ''执行时间'',
  `adjustment_explain` varchar(500) DEFAULT NULL COMMENT ''原因说明'',
  `adjustment_type` int(11) DEFAULT NULL COMMENT ''调岗类型'',
  `new_superior` varchar(50) DEFAULT NULL COMMENT ''新上级'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT NULL COMMENT ''创建时间'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `actual_position_id` int(11) DEFAULT NULL COMMENT ''确认职级'',
  `bis_update` bit(1) DEFAULT b''0'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT=''平调或降职流程'';

-- ----------------------------
-- Table structure for tb_hr_user_promotion
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_promotion`;
CREATE TABLE `tb_hr_user_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''用户编号'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''用户账号'',
  `expect_formal_type` int(11) DEFAULT NULL COMMENT ''晋升类型'',
  `expect_post_id` int(11) DEFAULT NULL COMMENT ''期望晋升岗位'',
  `expect_post_grade_id` int(11) DEFAULT NULL COMMENT ''期望晋升岗级'',
  `expect_position_id` int(11) DEFAULT NULL COMMENT ''期望晋升职级'',
  `expect_formal_date` datetime DEFAULT NULL COMMENT ''期望晋升日期'',
  `job_summary` varchar(500) DEFAULT NULL COMMENT ''工作总结'',
  `self_evaluation` varchar(500) DEFAULT NULL COMMENT ''自我评价'',
  `career_planning` varchar(500) DEFAULT NULL COMMENT ''个人职业规划'',
  `actual_formal_type` int(11) DEFAULT NULL COMMENT ''实际晋升类型'',
  `actual_post_id` int(11) DEFAULT NULL COMMENT ''实际晋升岗位'',
  `actual_post_grade_id` int(11) DEFAULT NULL COMMENT ''实际晋升岗级'',
  `actual_position_id` int(11) DEFAULT NULL COMMENT ''实际晋升职级'',
  `actual_formal_date` datetime DEFAULT NULL COMMENT ''实际晋升日期'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `superior_suggestion_formal_type` int(11) DEFAULT NULL COMMENT ''上级建议晋升类型'',
  `superior_suggestion_post_id` int(11) DEFAULT NULL COMMENT ''上级建议晋升岗位'',
  `superior_suggestion_post_grade_id` int(11) DEFAULT NULL COMMENT ''上级建议晋升岗级'',
  `superior_suggestion_formal_date` datetime DEFAULT NULL COMMENT ''上级建议晋升日期'',
  `department_suggestion_formal_type` int(11) DEFAULT NULL COMMENT ''部门建议晋升类型'',
  `department_suggestion_post_id` int(11) DEFAULT NULL COMMENT ''部门建议晋升岗位'',
  `department_suggestion_post_grade_id` int(11) DEFAULT NULL COMMENT ''部门建议晋升岗级'',
  `department_suggestion_formal_date` datetime DEFAULT NULL COMMENT ''部门建议晋升日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT=''晋升流程'';

-- ----------------------------
-- Table structure for tb_hr_user_quit
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_quit`;
CREATE TABLE `tb_hr_user_quit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `user_account` varchar(10) DEFAULT NULL COMMENT ''员工账号'',
  `expect_quit_date` datetime DEFAULT NULL COMMENT ''期望离职日期'',
  `apply_quit_date` datetime DEFAULT NULL COMMENT ''申请离职日期'',
  `quit_explain` int(11) DEFAULT NULL COMMENT ''离职原因'',
  `other_explain` varchar(500) DEFAULT NULL COMMENT ''其它说明'',
  `job_summary` varchar(500) DEFAULT NULL COMMENT ''工作小结'',
  `proposal` varchar(500) DEFAULT NULL COMMENT ''给公司的建议'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT NULL COMMENT ''创建日期'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `advice_quit_date` datetime DEFAULT NULL COMMENT ''其它领导建议离职日期'',
  `advice_quit_date_superior` datetime DEFAULT NULL COMMENT ''上级建议离职日期'',
  `actual_quit_date` datetime DEFAULT NULL COMMENT ''确认离职日期'',
  `job_transfer_user` varchar(50) DEFAULT NULL COMMENT ''工作交接人'',
  `user_name` varchar(200) DEFAULT NULL COMMENT ''姓名'',
  `quit_explain_name` varchar(200) DEFAULT NULL COMMENT ''离职原因文本'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT=''离职'';

-- ----------------------------
-- Table structure for tb_hr_user_quit_transfer
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_quit_transfer`;
CREATE TABLE `tb_hr_user_quit_transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `user_account` varchar(20) DEFAULT NULL COMMENT ''员工账号'',
  `quit_id` int(11) DEFAULT NULL COMMENT ''离职申请单'',
  `job_transfer_user` varchar(100) DEFAULT NULL COMMENT ''工作交接人'',
  `transfer_explain` varchar(500) DEFAULT NULL COMMENT ''交接说明'',
  `promise` varchar(500) DEFAULT NULL COMMENT ''员工承诺'',
  `email` varchar(50) DEFAULT NULL COMMENT ''邮箱'',
  `address` varchar(50) DEFAULT NULL COMMENT ''联系地址'',
  `mobile` varchar(20) DEFAULT NULL COMMENT ''联系电话'',
  `qq` varchar(20) DEFAULT NULL COMMENT ''QQ'',
  `wechat` varchar(20) DEFAULT NULL COMMENT ''微信'',
  `creator` varchar(20) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT NULL COMMENT ''创建时间'',
  `process_ins_id` varchar(64) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `fi_arrears_amount` decimal(11,2) DEFAULT ''0.00'' COMMENT ''财务欠款金额'',
  `hr_arrears_amount` decimal(11,2) DEFAULT ''0.00'' COMMENT ''人力资源欠款金额'',
  `bis_update` bit(1) DEFAULT b''0'',
  `rescission_date` datetime DEFAULT NULL COMMENT ''解除劳动合同日期'',
  `last_salary_settlement_date` datetime DEFAULT NULL COMMENT ''最后薪酬结算日期'',
  `ss_individual_undertake_start_time` datetime DEFAULT NULL COMMENT ''社保个人全额承担开始时间'',
  `ss_individual_undertake_end_time` datetime DEFAULT NULL COMMENT ''社保个人全额承担结束时间'',
  `pf_individual_undertake_start_time` datetime DEFAULT NULL COMMENT ''公积金个人全额承担开始时间'',
  `pf_individual_undertake_end_time` datetime DEFAULT NULL COMMENT ''公积金个人全额承担结束时间'',
  `social_security_arrears` decimal(11,2) DEFAULT ''0.00'' COMMENT ''社保欠款'',
  `provident_fund_arrears` decimal(11,2) DEFAULT ''0.00'' COMMENT ''公积金欠款'',
  `other_arrears` decimal(11,2) DEFAULT ''0.00'' COMMENT ''其他欠款'',
  `arrears_settlement_method` varchar(200) DEFAULT NULL COMMENT ''欠款结算方式'',
  `fi_other_arrears_amount` decimal(11,2) DEFAULT ''0.00'' COMMENT ''财务其他欠款'',
  `arrears_settlement_money` decimal(11,2) DEFAULT ''0.00'' COMMENT ''欠款回收金额'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT=''员工离职交接'';

-- ----------------------------
-- Table structure for tb_hr_user_quit_vacation
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_quit_vacation`;
CREATE TABLE `tb_hr_user_quit_vacation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_quit_id` int(11) DEFAULT NULL COMMENT ''离职申请主表ID'',
  `vacation_type` int(2) DEFAULT NULL COMMENT ''休假类型'',
  `year` int(11) DEFAULT NULL COMMENT ''休假年份'',
  `hours` decimal(11,5) DEFAULT NULL COMMENT ''小时数'',
  `user_account` varchar(100) DEFAULT NULL COMMENT ''员工账号'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''员工姓名'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_user_social_security_and_provident_fund
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_social_security_and_provident_fund`;
CREATE TABLE `tb_hr_user_social_security_and_provident_fund` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `user_account` varchar(32) DEFAULT '''' COMMENT ''用户系统账户名'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''用户姓名'',
  `welfare_account` varchar(100) DEFAULT '''' COMMENT ''社保公积金账号'',
  `template_id` int(11) DEFAULT NULL COMMENT ''社保公积金等级模板ID'',
  `payment_start_date` date DEFAULT NULL COMMENT ''申请缴纳日期'',
  `welfare_change_detail_id` int(11) DEFAULT NULL COMMENT ''社保公积金调整记录ID'',
  `welfare_status` bit(1) DEFAULT b''0'' COMMENT ''社保公积金状态'',
  `welfare_type` varchar(60) DEFAULT NULL COMMENT ''福利类型(社保、公积金)'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `need_pay` int(11) DEFAULT ''0'' COMMENT ''是否需要公司缴纳'',
  `last_company_stop_date` date DEFAULT NULL COMMENT ''上家公司停缴日期'',
  `remark` varchar(1000) DEFAULT NULL COMMENT ''备注'',
  `master_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `social_security_and_provident_fund_user_account` (`user_account`),
  KEY `user_social_security_template_id` (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 COMMENT=''员工用户社保公积金信息录入'';

-- ----------------------------
-- Table structure for tb_hr_user_social_security_and_provident_fund_agents
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_social_security_and_provident_fund_agents`;
CREATE TABLE `tb_hr_user_social_security_and_provident_fund_agents` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `user_account` varchar(32) NOT NULL DEFAULT '''' COMMENT ''用户系统账户名'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''用户姓名'',
  `welfare_account` varchar(100) NOT NULL DEFAULT '''' COMMENT ''社保公积金账号'',
  `agents_start_date` date DEFAULT NULL COMMENT ''开始日期'',
  `agents_end_date` date DEFAULT NULL COMMENT ''结束日期'',
  `agents_description` varchar(300) DEFAULT NULL COMMENT ''代理说明'',
  `agents_type` varchar(30) NOT NULL DEFAULT '''' COMMENT ''代理类型(补缴、代缴)'',
  `welfare_type` varchar(60) NOT NULL DEFAULT '''' COMMENT ''福利类型(社保、公积金)'',
  `template_id` int(11) DEFAULT NULL COMMENT ''代理的社保公积金等级模板ID'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `master_id` int(11) DEFAULT NULL,
  `agents_type_name` varchar(200) DEFAULT NULL,
  `welfare_type_name` varchar(200) DEFAULT NULL,
  `template_name` varchar(200) DEFAULT NULL,
  `salary_grant` varchar(200) DEFAULT NULL COMMENT ''工资发放'',
  `salary_grant_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `social_security_and_provident_fund_user_account` (`user_account`),
  KEY `user_social_security_template_id` (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT=''员工用户社保公积金代理(补缴、代缴)记录'';

-- ----------------------------
-- Table structure for tb_hr_user_social_security_and_provident_fund_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_social_security_and_provident_fund_master`;
CREATE TABLE `tb_hr_user_social_security_and_provident_fund_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `process_inst_id` varchar(64) NOT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT ''状态'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_user_social_security_and_provident_fund_transfer
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_social_security_and_provident_fund_transfer`;
CREATE TABLE `tb_hr_user_social_security_and_provident_fund_transfer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `user_account` varchar(32) NOT NULL DEFAULT '''' COMMENT ''用户系统账户名'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''用户姓名'',
  `transfer_date` date DEFAULT NULL COMMENT ''转入日期'',
  `transfer_description` varchar(300) DEFAULT NULL COMMENT ''转入说明'',
  `welfare_type` varchar(60) DEFAULT NULL COMMENT ''福利类型(社保、公积金)'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''操作人'',
  `process_inst_id` varchar(64) NOT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT ''状态'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_account_welfare_type_uindex` (`user_account`,`welfare_type`),
  KEY `social_security_and_provident_fund_user_account` (`user_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=''员工用户社保公积金转入记录'';

-- ----------------------------
-- Table structure for tb_hr_user_welfare_change
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_welfare_change`;
CREATE TABLE `tb_hr_user_welfare_change` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `welfare_type` varchar(60) DEFAULT NULL COMMENT ''福利类型(社保、公积金)'',
  `creator` varchar(50) NOT NULL COMMENT ''操作人'',
  `process_inst_id` varchar(64) NOT NULL COMMENT ''流程实例ID'',
  `change_description` varchar(300) DEFAULT NULL COMMENT ''变更描述'',
  `status` varchar(20) DEFAULT NULL COMMENT ''状态'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `bis_update` bit(1) DEFAULT b''0'',
  PRIMARY KEY (`id`),
  KEY `index_process_inst_id` (`process_inst_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT=''员工福利(社保公积金)变更记录主表'';

-- ----------------------------
-- Table structure for tb_hr_user_welfare_change_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_welfare_change_detail`;
CREATE TABLE `tb_hr_user_welfare_change_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键id'',
  `change_id` int(10) unsigned NOT NULL DEFAULT ''0'' COMMENT ''变更主表ID'',
  `user_account` varchar(32) NOT NULL COMMENT ''用户系统账户名'',
  `user_name` varchar(100) DEFAULT NULL COMMENT ''用户姓名'',
  `welfare_account` varchar(100) DEFAULT NULL COMMENT ''社保公积金账号'',
  `welfare_type` varchar(60) DEFAULT NULL COMMENT ''福利类型(社保、公积金)'',
  `template_id` int(11) NOT NULL COMMENT ''社保公积金等级模板ID'',
  `template_name` varchar(100) DEFAULT NULL COMMENT ''等级名称'',
  `effective_date` date NOT NULL COMMENT ''期缴日期(生效日期)'',
  `failure_date` date DEFAULT NULL COMMENT ''失效日期'',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `social_security_base` bigint(20) DEFAULT ''0'' COMMENT ''社保基数'',
  `social_security_pension` bigint(20) DEFAULT ''0'' COMMENT ''养老基数'',
  `provident_fund_base` bigint(20) DEFAULT ''0'' COMMENT ''公积金基数'',
  PRIMARY KEY (`id`),
  KEY `index_change_id` (`change_id`),
  KEY `index_user_account` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT=''员工福利(社保公积金)变更记录明细'';

-- ----------------------------
-- Table structure for tb_hr_user_work
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_user_work`;
CREATE TABLE `tb_hr_user_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `user_id` int(11) DEFAULT NULL COMMENT ''员工编号'',
  `begin_date` datetime DEFAULT NULL COMMENT ''开始日期'',
  `end_date` datetime DEFAULT NULL COMMENT ''结束日期'',
  `company_name` varchar(50) DEFAULT NULL COMMENT ''所在单位'',
  `company_position` varchar(50) DEFAULT NULL COMMENT ''担任职位'',
  `job_content` varchar(200) DEFAULT NULL COMMENT ''工作描述'',
  `superior_name` varchar(20) DEFAULT NULL COMMENT ''直接上级姓名'',
  `superior_position` varchar(50) DEFAULT NULL COMMENT ''直接上级职位'',
  `superior_telephone` varchar(20) DEFAULT NULL COMMENT ''直接上级联系电话'',
  `remuneration` varchar(100) DEFAULT NULL COMMENT ''在职薪酬'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT=''员工工作经历信息表'';

-- ----------------------------
-- Table structure for tb_hr_vacation
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_vacation`;
CREATE TABLE `tb_hr_vacation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''id'',
  `application_number` varchar(20) DEFAULT NULL COMMENT ''申请单号'',
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `application_user_account` varchar(10) DEFAULT NULL COMMENT ''申请人账号'',
  `reason` varchar(500) DEFAULT NULL COMMENT ''请假事由'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_vacation_change_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_vacation_change_record`;
CREATE TABLE `tb_hr_vacation_change_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_id` int(11) DEFAULT NULL COMMENT ''业务数据id'',
  `user_account` varchar(100) DEFAULT NULL COMMENT ''用户账户'',
  `category` varchar(100) DEFAULT NULL COMMENT ''假期类型'',
  `year` int(11) DEFAULT NULL COMMENT ''相关联的年份'',
  `apply_no` varchar(100) DEFAULT NULL COMMENT ''申请单号'',
  `init_vacation` decimal(10,0) DEFAULT NULL COMMENT ''已有的初始假期'',
  `hours` decimal(11,0) DEFAULT NULL COMMENT ''本次变动的假期时间'',
  `clearing_vacation` decimal(10,0) DEFAULT NULL COMMENT ''结算后的假期时间'',
  `remark` varchar(1000) DEFAULT NULL COMMENT ''备注'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8 COMMENT=''调休及年假变化记录表'';

-- ----------------------------
-- Table structure for tb_hr_vacation_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_vacation_detail`;
CREATE TABLE `tb_hr_vacation_detail` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `main_id` int(11) DEFAULT NULL,
  `vacation_type` int(11) DEFAULT NULL COMMENT ''请假类型'',
  `begin_time` datetime DEFAULT NULL COMMENT ''开始时间'',
  `end_time` datetime DEFAULT NULL COMMENT ''结束时间'',
  `sys_days` decimal(10,5) DEFAULT NULL COMMENT ''系统计算的天数'',
  `sys_hours` decimal(10,5) DEFAULT NULL COMMENT ''系统计算的小时数'',
  `modify_days` decimal(10,5) DEFAULT NULL COMMENT ''修改后的天数'',
  `modify_hours` decimal(10,5) DEFAULT NULL COMMENT ''修改后的小时数'',
  `actual_days` decimal(10,5) DEFAULT NULL COMMENT ''实际休假天数'',
  `actual_hours` decimal(10,5) DEFAULT NULL COMMENT ''实际休假小时数'',
  `creator` varchar(10) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  `actual_begin_time` datetime DEFAULT NULL,
  `actual_end_time` datetime DEFAULT NULL,
  `actual_sys_days` decimal(10,2) DEFAULT NULL,
  `actual_sys_hours` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_vacation_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_vacation_type`;
CREATE TABLE `tb_hr_vacation_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT ''假期名称'',
  `bis_deduction` bit(1) DEFAULT NULL COMMENT ''是否扣减周末及节假日'',
  `bis_deduct_wages` bit(1) DEFAULT b''0'' COMMENT ''是否扣减工资'',
  `bis_influence_attendance` bit(1) DEFAULT NULL COMMENT ''是否影响全勤'',
  `can_apply_hour` bit(1) DEFAULT b''0'' COMMENT ''是否允许按小时请假'',
  `category` varchar(100) DEFAULT NULL COMMENT ''所属分类'',
  `max_apply_days` decimal(11,2) DEFAULT NULL COMMENT ''最多申请天数'',
  `bis_delete` bit(1) DEFAULT NULL COMMENT ''是否删除'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT=''假期类型管理'';

-- ----------------------------
-- Table structure for tb_hr_welfare_cardinality
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_welfare_cardinality`;
CREATE TABLE `tb_hr_welfare_cardinality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_ins_id` varchar(20) DEFAULT NULL COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `bis_valid` bit(1) DEFAULT b''0'' COMMENT ''是否有效'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT=''社保公积金基数流程'';

-- ----------------------------
-- Table structure for tb_hr_welfare_cardinality_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_welfare_cardinality_list`;
CREATE TABLE `tb_hr_welfare_cardinality_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT ''0'' COMMENT ''流程表id'',
  `user_name` varchar(50) DEFAULT NULL COMMENT ''姓名'',
  `user_account` varchar(50) DEFAULT NULL COMMENT ''账号'',
  `department_id` int(11) DEFAULT NULL COMMENT ''部门id'',
  `department_name` varchar(200) DEFAULT NULL COMMENT ''部门名称'',
  `social_security_base` bigint(11) DEFAULT ''0'' COMMENT ''社保基数'',
  `social_security_adjust_base` bigint(11) DEFAULT ''0'' COMMENT ''社保调整基数'',
  `social_security_result_base` bigint(11) DEFAULT ''0'' COMMENT ''社保最终基数'',
  `social_security_pension_base` bigint(11) DEFAULT ''0'' COMMENT ''养老金基数'',
  `social_security_adjust_pension_base` bigint(11) DEFAULT ''0'' COMMENT ''养老金调整基数'',
  `social_security_result_pension_base` bigint(11) DEFAULT ''0'' COMMENT ''养老金最终基数'',
  `provident_fund_base` bigint(11) DEFAULT ''0'' COMMENT ''公积金基数'',
  `provident_fund_adjust_base` bigint(11) DEFAULT ''0'' COMMENT ''公积金调整基数'',
  `provident_fund_result_base` bigint(11) DEFAULT ''0'' COMMENT ''公积金最终基数'',
  `creator` varchar(50) DEFAULT NULL COMMENT ''创建人'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建日期'',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改日期'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3439 DEFAULT CHARSET=utf8 COMMENT=''社保公积金基数人员明细'';

-- ----------------------------
-- Table structure for tb_hr_work_schedule
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_work_schedule`;
CREATE TABLE `tb_hr_work_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''工作安排'',
  `project_id` int(11) DEFAULT NULL COMMENT ''所属项目ID'',
  `project` varchar(100) DEFAULT NULL COMMENT ''所属项目'',
  `start_date` datetime DEFAULT NULL COMMENT ''执行开始时间'',
  `end_date` datetime DEFAULT NULL COMMENT ''执行结束时间'',
  `work_content` varchar(500) DEFAULT NULL COMMENT ''工作内容'',
  `executor_account` varchar(255) DEFAULT NULL COMMENT ''执行人员账号'',
  `executor` varchar(100) DEFAULT NULL COMMENT ''执行人员'',
  `synergy_account` varchar(255) DEFAULT NULL COMMENT ''协同人员账号'',
  `synergy` varchar(100) DEFAULT NULL COMMENT ''协同人员'',
  `process_inst_id` varchar(100) DEFAULT ''0'' COMMENT ''流程实例编号'',
  `status` varchar(10) DEFAULT NULL COMMENT ''流程状态'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator_name` varchar(255) DEFAULT NULL,
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `practical_project_id` int(11) DEFAULT NULL COMMENT ''实际项目ID'',
  `practical_project` varchar(200) DEFAULT NULL COMMENT ''实际项目名称'',
  `practical_start_date` datetime DEFAULT NULL COMMENT ''实际开始时间'',
  `practical_end_date` datetime DEFAULT NULL COMMENT ''实际结束时间'',
  `practical_work_content` varchar(2000) DEFAULT NULL COMMENT ''实际工作内容'',
  `other_explain` varchar(1000) DEFAULT NULL COMMENT ''其他说明'',
  `update_explain` varchar(1000) DEFAULT NULL COMMENT ''修改说明'',
  `work_title` varchar(500) DEFAULT NULL COMMENT ''工作标题'',
  `work_classify_id` int(11) DEFAULT NULL COMMENT ''工作分类'',
  `work_classify_name` varchar(200) DEFAULT NULL,
  `work_name` varchar(1000) DEFAULT NULL COMMENT ''工作名称'',
  `work_classify_child_id` int(11) DEFAULT NULL COMMENT ''工作分类子类'',
  `work_classify_child_name` varchar(200) DEFAULT NULL,
  `bis_synergy` bit(1) DEFAULT b''0'' COMMENT ''是否协同'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_work_schedule_results
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_work_schedule_results`;
CREATE TABLE `tb_hr_work_schedule_results` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_id` int(11) DEFAULT NULL COMMENT ''主表ID'',
  `practical_project_id` int(11) DEFAULT NULL COMMENT ''实际所属项目ID'',
  `practical_project` varchar(500) DEFAULT NULL COMMENT ''实际所属项目'',
  `practical_start_date` varchar(50) DEFAULT NULL COMMENT ''实际执行开始时间'',
  `practical_end_date` varchar(50) DEFAULT NULL COMMENT ''实际执行结束时间'',
  `practical_work_content` varchar(500) DEFAULT NULL COMMENT ''实际工作内容'',
  `other_explain` varchar(300) DEFAULT NULL COMMENT ''其他说明'',
  `update_explain` varchar(300) DEFAULT NULL COMMENT ''修改说明'',
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''最后更新时间，记录变化后会自动更新时间戳'',
  `creator_name` varchar(255) DEFAULT NULL,
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `work_matter_id` int(11) DEFAULT NULL COMMENT ''工作事项'',
  `work_matter_name` varchar(200) DEFAULT NULL,
  `is_retreat` bit(1) DEFAULT b''0'' COMMENT ''是否退回工作成果'',
  `is_submit` bit(1) DEFAULT b''0'' COMMENT ''是否提交'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=470 DEFAULT CHARSET=utf8 COMMENT=''工作成果'';

-- ----------------------------
-- Table structure for tb_hr_work_schedule_results_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_work_schedule_results_log`;
CREATE TABLE `tb_hr_work_schedule_results_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reject_record` varchar(300) DEFAULT NULL COMMENT ''退回说明'',
  `master_id` int(11) DEFAULT NULL,
  `creator_name` varchar(255) DEFAULT NULL,
  `creator` varchar(10) DEFAULT '''' COMMENT ''创建人'',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_hr_work_shift_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_work_shift_set`;
CREATE TABLE `tb_hr_work_shift_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `begin_month` int(11) DEFAULT NULL COMMENT ''开始月份'',
  `end_month` int(11) DEFAULT NULL COMMENT ''结束月份'',
  `am_begin_time` varchar(100) DEFAULT NULL COMMENT ''上午上班时间'',
  `am_end_time` varchar(100) DEFAULT NULL COMMENT ''上午下班时间'',
  `pm_begin_time` varchar(100) DEFAULT NULL COMMENT ''下午上班时间'',
  `pm_end_time` varchar(100) DEFAULT NULL COMMENT ''下午下班时间'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT=''上班时间设定'';

-- ----------------------------
-- Table structure for tb_hr_work_year_annual_leave_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_work_year_annual_leave_set`;
CREATE TABLE `tb_hr_work_year_annual_leave_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `working_life_begin` int(11) DEFAULT NULL COMMENT ''工作年限起'',
  `working_life_end` int(11) DEFAULT NULL COMMENT ''工作年限止'',
  `vacation_days` int(11) DEFAULT NULL COMMENT ''年假天数'',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `mdified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''修改时间'',
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT=''工龄与年假天数规则设置'';

-- ----------------------------
-- Table structure for tb_hr_year_vacation
-- ----------------------------
DROP TABLE IF EXISTS `tb_hr_year_vacation`;
CREATE TABLE `tb_hr_year_vacation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `short_date` date DEFAULT NULL COMMENT ''日期'',
  `bis_rest_initial_value` bit(1) DEFAULT NULL COMMENT ''是否休假初始值'',
  `bis_rest` bit(1) DEFAULT NULL COMMENT ''是否休假'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7671 DEFAULT CHARSET=utf8 COMMENT=''全年假期记录'';

-- ----------------------------
-- View structure for vw_db_columns_info
-- ----------------------------
DROP VIEW IF EXISTS `vw_db_columns_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_db_columns_info` AS select `information_schema`.`columns`.`COLUMN_NAME` AS `column_name`,`information_schema`.`columns`.`COLUMN_COMMENT` AS `column_comment`,`information_schema`.`columns`.`DATA_TYPE` AS `data_type`,`information_schema`.`columns`.`TABLE_NAME` AS `table_name`,`information_schema`.`columns`.`TABLE_SCHEMA` AS `table_schema` from `information_schema`.`columns` where (`information_schema`.`columns`.`TABLE_SCHEMA` = ''pmcc_hr'') ;

-- ----------------------------
-- View structure for vw_db_table_info
-- ----------------------------
DROP VIEW IF EXISTS `vw_db_table_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_db_table_info` AS select `information_schema`.`tables`.`TABLE_NAME` AS `TABLE_NAME`,`information_schema`.`tables`.`TABLE_COMMENT` AS `TABLE_COMMENT` from `information_schema`.`tables` where (`information_schema`.`tables`.`TABLE_SCHEMA` = ''pmcc_hr'') ;

-- ----------------------------
-- View structure for vw_user_attendance_analyzes
-- ----------------------------
DROP VIEW IF EXISTS `vw_user_attendance_analyzes`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_user_attendance_analyzes` AS select `tb_hr_attendance_analyze`.`year` AS `year`,`tb_hr_attendance_analyze`.`month` AS `month`,`tb_hr_attendance_analyze`.`user_name` AS `user_name`,`tb_hr_attendance_analyze`.`user_account` AS `user_account`,sum(`tb_hr_attendance_analyze`.`sure_be_late_number`) AS `be_late_number`,sum(`tb_hr_attendance_analyze`.`sure_leave_early_number`) AS `leave_early_number`,sum(`tb_hr_attendance_analyze`.`sure_absenteeism_number`) AS `absenteeism_number` from `tb_hr_attendance_analyze` group by `tb_hr_attendance_analyze`.`year`,`tb_hr_attendance_analyze`.`month`,`tb_hr_attendance_analyze`.`user_name`,`tb_hr_attendance_analyze`.`user_account` ;

-- ----------------------------
-- View structure for vw_user_quit_temp
-- ----------------------------
DROP VIEW IF EXISTS `vw_user_quit_temp`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_user_quit_temp` AS select `u`.`user_nickname` AS `userName`,`d`.`name` AS `departmentName`,`p`.`name` AS `postName`,`g`.`name` AS `postGradeName`,`us`.`user_nickname` AS `transferName`,`q`.`advice_quit_date_superior` AS `quitDate`,`t`.`rescission_date` AS `rescissionDate`,`u`.`contract_begin_date` AS `contractBeginDate`,`u`.`contract_end_date` AS `contractEndDate`,`t`.`mobile` AS `mobile`,`t`.`email` AS `email`,`t`.`address` AS `address`,`t`.`last_salary_settlement_date` AS `lastDate`,`t`.`ss_individual_undertake_start_time` AS `startDate`,`t`.`ss_individual_undertake_end_time` AS `endDate`,`t`.`process_ins_id` AS `processId` from ((((((`tb_hr_user_quit` `q` left join `tb_hr_user_quit_transfer` `t` on((`q`.`user_account` = `t`.`user_account`))) left join `tb_hr_user` `u` on((`q`.`user_account` = `u`.`user_account`))) left join `tb_hr_user` `us` on((`t`.`job_transfer_user` = `us`.`user_account`))) left join `tb_hr_department` `d` on((`u`.`department_id` = `d`.`erp_department_id`))) left join `tb_hr_base_post` `p` on((`u`.`post` = `p`.`id`))) left join `tb_hr_base_post_grade` `g` on((`u`.`post_grade` = `g`.`id`))) ;

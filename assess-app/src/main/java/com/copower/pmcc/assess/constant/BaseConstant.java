package com.copower.pmcc.assess.constant;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 11:25
 */
public class BaseConstant {
    public static final String ASSESS_BOX_RE_GROUP_KEY = "assess";//考核系统模型分组
    public static final String CURRENT_DATABASE_NAME = "pmcc_assess";//数据库名称

    public static final String ASSESS_BASE_ASSIST_STAGE = "stage";//阶段信息表单分组
    public static final String ASSESS_BASE_ASSIST_MATTER = "matter";//工作事项表单分组信息

    public static final String PMCC_ASSESS_BASE_ASSIST = "pmcc:assess:base:assist";
    public static final String PMCC_ASSESS_BASE_ASSIST_ID = "pmcc:assess:base:assist:id";

    //高德地图相关
    public static final String MPA_API_URL = "http://restapi.amap.com/v3/staticmap";//静态图的请求地址
    public static final String MAP_WEB_SERVICE_KEY = "0d3f1144352d7e2b683e37dd3757156a";//webservice的key

    //申报记录关键字段
    public static final String PMCC_ASSESS_DECLARE_RECORD_NAME = "name"; //申报记录名称
    public static final String PMCC_ASSESS_DECLARE_RECORD_PROVINCE = "province"; //申报记录省
    public static final String PMCC_ASSESS_DECLARE_RECORD_CITY = "city"; //申报记录市
    public static final String PMCC_ASSESS_DECLARE_RECORD_DISTRICT = "district"; //申报记录区县
    public static final String PMCC_ASSESS_DECLARE_RECORD_FLOOR_AREA = "floor_area"; //申报记录建筑面积
}

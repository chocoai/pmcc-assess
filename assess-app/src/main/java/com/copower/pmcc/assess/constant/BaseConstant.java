package com.copower.pmcc.assess.constant;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 11:25
 */
public class BaseConstant {
    public static final String ASSESS_APP_KEY = "pmcc-assess";//pmcc_assess
    public static final String DATABASE_PMCC_ASSESS = "pmcc_assess";//pmcc_assess
    public static final String DATABASE_PMCC_ASSESS_CASE = "pmcc_assess_case";//pmcc_assess_case
    public static final String[] ASSESS_IGNORE_ARRAY = new String[]{"id", "creator", "gmtCreated", "gmtModified"};//对象拷贝忽略的默认字段

    public static final String ASSESS_BASE_ASSIST_STAGE = "stage";//阶段信息表单分组
    public static final String ASSESS_BASE_ASSIST_STAGE_AUTO = "stage_auto";//阶段后台处理
    public static final String ASSESS_BASE_ASSIST_MATTER = "matter";//工作事项表单分组信息

    public static final String PMCC_ASSESS_BASE_ASSIST = "pmcc:assess:base:assist";
    public static final String PMCC_ASSESS_BASE_ASSIST_ID = "pmcc:assess:base:assist:id";

    //高德地图相关
    public static final String MPA_API_URL = "http://restapi.amap.com/v3/staticmap";//静态图的请求地址
    public static final String MAP_WEB_SERVICE_KEY = "0d3f1144352d7e2b683e37dd3757156a";//webservice的key


    public static final String ASSESS_DATA_SET_USE_TYPE_HOUSE = "house";//房产
    public static final String ASSESS_DATA_SET_USE_TYPE_LAND = "land";//土地
    public static final String ASSESS_DATA_SET_USE_FIELD_HOUSE = "set.use.type.house";//设定用途字段-房产
    public static final String ASSESS_DATA_SET_USE_FIELD_LAND = "set.use.type.land";//设定用途字段-土地
    public static final String ASSESS_DATA_SET_USE_FIELD_LAND_AREA_FACTOR = "land.area.factor";
    public static final String ASSESS_DATA_SET_USE_FIELD_LAND_INDIVIDUAL_FACTOR = "land.individual.factor";

    //估价对象中文显示名称
    public static final String ASSESS_JUDGE_OBJECT_CN_NAME = "号估价对象";
    public static final String ASSESS_CertGetQuestion_YES_NAME = "有权证";
    public static final String ASSESS_CertGetQuestion_NO_NAME = "无权证";
    public static final String ASSESS_REALTY_HOUSE_CERT_RIGHT = "房权证";
    public static final String ASSESS_REALTY_HOUSE_CERT_CHECK = "监证";
}

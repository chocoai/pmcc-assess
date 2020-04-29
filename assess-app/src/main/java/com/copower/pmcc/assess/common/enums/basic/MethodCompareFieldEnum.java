package com.copower.pmcc.assess.common.enums.basic;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum MethodCompareFieldEnum {
    ESTATE_NAME("estate.name", "楼盘名称"), // ps:楼盘-楼盘名称
    SCOPE_PROPERTY("scope.property", "财产范围"),// ps:房屋-交易情况-财产范围
    FINANCING_CONDITIONS("financing.conditions", "融资条件"),// ps:房屋-交易情况-融资条件
    TAX_BURDEN("tax.burden", "税费负担"),// ps:房屋-交易情况-税费负担
    PAYMENT_METHOD("payment.method", "付款方式"),//ps:房屋-交易情况-付款方式
    TRADING_TRANSACTION_SITUATION("trading.transaction.situation", "交易情况"),//ps:房屋-交易情况-交易情况
    TRADING_PRICE("trading.price", "交易价格"),//ps:房屋-交易情况-交易价格
    TRADING_TIME("trading.time", "交易时间"),//ps:房屋-交易情况-交易时间
    PRICE_CONNOTATION("price.connotation", "单价内涵"),//ps:房屋-交易情况-单价内涵

    //区位状况-----------------------------------------------------------------------------------------------区位状况
    LOCATION("location", "坐落"),//ps:权证的坐落
    POSITION("position", "方位"),//ps:板块的方位、楼盘的方位字段组合
    WITH_IMPORTANT_LOCATION_DISTANCE("with.important.location.distance", "与重要场所的距离"),//ps:多条交通枢信息 （名称+距离）相组合显示
    TEMPORARY_ROAD_CONDITION("temporary.road.condition", "临街（路）状况"),//ps:临街（路）状况
    FLOOR("floor", "楼层"),//ps:房屋-所在层数
    ORIENTATION("orientation", "朝向"),//ps:房屋-朝向
    TRAFFIC_CONDITIONS("traffic.conditions", "交通条件"),//ps:地铁、公交、主干道、主要转换互通桥信息的所有数据叠加
    URBAN_INFRASTRUCTURE("urban.infrastructure", "城市基础设施"),//ps:道路、网络、供水、排水、供电、供气、供热信息，显示有无信息。工业仓储类型（则取所有信息显示）
    PUBLIC_SERVICE_FACILITIES("public.service.facilities", "公共服务设施"),//ps:教育、购物、医养、金融、休闲娱乐所有信息显示
    NATURAL("natural", "自然环境"),//ps:取类别与影响信息组合显示
    CULTURAL("cultural", "人文环境"),//ps:取类别与影响信息组合显示
    SCENERY("scenery", "景观"),//ps:取类别与影响信息组合显示

    //权益状况------------------------------------------------------------------------------------------------
    LAND_RIGHT_TYPE("land.right.type", "土地权利类型"),//ps:土地权利类型
    LAND_RIGHT_NATURE("land.right.nature", "土地权利性质"),//ps:土地权利性质

    HOUSE_NATURE("house.nature", "房屋性质"),//ps:房屋性质
    HOUSE_CERT_USE("house.cert.use", "房屋用途"),//ps:房屋用途
    PROPERTY_MANAGEMENT("property.management", "物业管理"),//ps:物业管理
    OTHER_SPECIAL_SITUATIONS("other.special.situations", "其它特殊情况"),//ps:他项权利特殊情况

    //实体状况------------------------------------------------------------------------------------------------
    BUILDING_AREA("building.area", "建筑面积"),//ps:楼盘-建筑面积
    FLOOR_HEIGHT("floor.height", "层高"),//ps:楼栋-层高
    NET_HEIGHT("net.height", "净高"),//ps:楼栋-净高
    BUILDING_SCALE("building.scale", "建筑规模"),//ps:楼栋-建筑规模
    BUILDING_STRUCTURE("building.structure", "建筑结构"),//ps:楼栋-建筑结构类别
    ARCHITECTURAL_OUTFIT("architectural.outfit", "建筑外装"),//ps:建筑外装所有字段信息组合
    APPEARANCE("appearance", "外观"),//ps:外观

    AERATION("aeration", "通风"),//ps:房间中通风类型下的信息字段组合
    LIGHTING("lighting", "采光"),//ps:房间中采光类型下的信息字段组合
    SUNSHINE("sunshine", "日照"),//ps:房间中日照类型下的信息字段组合
    SOUND_INSULATION("sound.insulation", "隔音"),//ps:房间中隔音类型下的信息字段组合
    HEAT_PRESERVATION("heat.preservation", "保温"),//ps:楼栋下的建筑功能信息中类型为保温的字段组合
    HEAT_INSULATION("heat.insulation", "隔热"),//ps:楼栋下的建筑功能信息中类型为隔热的字段组合
    WATERPROOF("waterproof", "防水"),//ps:楼栋下的建筑功能信息中类型为防水的字段组合

    INTELLIGENT_LEVEL("intelligent.level", "设施设备及智能化程度"),//ps:设施设备及智能化程度
    WATER_SUPPLY_DRAINAGE_MODE("water.supply.drainage.mode", "供（排）水方式"),//ps:供水和排水所有字段信息组合
    HEATING_MODE("heating.mode", "采暖供热方式"),//ps:采暖和供热所有字段信息组合
    NETWORK("network", "通讯网络"),//ps:通讯网络所有字段信息组合
    ELEVATOR_HOUSEHOLD_RATIO("elevator.household.ratio", "梯户比"),//ps:单元-梯户比
    INTERNAL_ASSEMBLY("internal.assembly", "内装"),//ps:楼栋内装信息+房屋内的装修信息
    PLANE_LAYOUT("plane.layout", "平面布局"),//ps:平面布局 实际用途为住宅取户型，其它用途时取户型与房间中的开间进深
    NEW_DEGREE("new.degree", "成新度"),//ps:成新度
    Other("other", "其它"),//ps:其它
    BUILDING_YEAR("building.year", "建造年份"),//ps:建造年份
    CONSTRUCTION_QUALITY("construction.quality", "工程质量"),//ps:工程质量

    //土地相关-----------------------------------------------------------------------------------------------------------------------
    LAND_NAME("land.name", "地块名称"),//ps:地块名称
    LAND_TRADING_PRICE("land.trading.price", "交易价格"),//ps:土地-交易情况-交易价格
    LAND_AREA_LOCATION("land.area.location", "区域位置"),//ps:区域位置
    LAND_AGGLOMERATION_DEGREE("land.agglomeration.degree", "产业聚集度"),//ps:产业聚集度
    LAND_TRAFFIC_CONDITIONS("land.traffic.conditions", "交通条件"),//ps:交通条件
    LAND_UPPORTING_FACILITY("land.upporting.facility", "配套设施条件"),//ps:配套设施条件
    LAND_ENVIRONMENT_CONDITION("land.environment.condition", "环境条件"),//ps:环境条件
    LAND_PLANNING_CONDITION("land.planning.condition", "规划条件"),//ps:规划条件
    LAND_AREA("land.area", "面积"),//ps:面积
    LAND_USE("land.use", "用途"),//ps:用途
    LAND_TEMPORARY_ROAD_CONDITION("land.temporary.road.condition", "临街（路）状况"),//ps:临街（路）状况
    LAND_TOPOGRAPHY("land.topography", "形状、地质、地形、地势"),//ps:形状、地质、地形、地势
    LAND_PLOT_RATIO("land.plot.ratio", "容积率"),//ps:容积率
    LAND_DEVELOPMENT_LEVEL("land.development.level", "宗地开发程度"),//ps:宗地开发程度
    LAND_USEFUL_LIFE("land.useful.life", "使用年限"),//ps:使用年限
    LAND_RIGHT_CONDITION("land.right.condition", "土地权利状况"),//ps:土地权利状况
    LAND_OTHER("land.other", "土地其它"),//ps:土地其它
    Land_Annual_Coefficient("annual.coefficient", "年期修正系数"),//ps:年期修正系数
    Land_VolumeRatio_Coefficient("volumeratio.coefficient", "容积率修正系数"),//ps:容积率修正系数
    ;
    private String key;

    private String name;

    private MethodCompareFieldEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static String getNameByKey(String key) {
        for (MethodCompareFieldEnum e : MethodCompareFieldEnum.values()) {
            if (e.getKey().equals(key)) {
                return e.getName();
            }
        }
        return null;
    }

    public static MethodCompareFieldEnum getEnumByName(String name) {
        for (MethodCompareFieldEnum e : MethodCompareFieldEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static MethodCompareFieldEnum getEnumByKey(String key) {
        for (MethodCompareFieldEnum e : MethodCompareFieldEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

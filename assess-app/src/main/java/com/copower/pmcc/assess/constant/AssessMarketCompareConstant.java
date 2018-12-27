package com.copower.pmcc.assess.constant;

/**
 * 描述:数据配置定义
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/3
 * @time: 13:45
 */
public class AssessMarketCompareConstant {

    public static final String ESTATE_NAME = "estate.name";//楼盘名称 ps:楼盘-楼盘名称

    //交易情况
    public static final String SCOPE_PROPERTY = "scope.property";//财产范围 ps:房屋-交易情况-财产范围
    public static final String FINANCING_CONDITIONS = "financing.conditions";//融资条件 ps:房屋-交易情况-融资条件
    public static final String TAX_BURDEN = "tax.burden";  //税费负担 ps:房屋-交易情况-税费负担


    public static final String PAYMENT_METHOD = "payment.method";  //付款方式 ps:房屋-交易情况-付款方式
    public static final String TRADING_PRICE = "trading.price";  //交易价格 ps:房屋-交易情况-交易价格

    //区位状况
    public static final String LOCATION = "location";  //房地产坐落及方位 ps:权证的坐落、板块的方位、楼盘的方位字段组合
    public static final String OFFICE_CONCENTRATION = "office.concentration";  //办公集聚度 ps:多条交通枢信息 （名称+距离）相组合显示
    public static final String FLOOR = "floor";  //楼层 ps:房屋-所在层数
    public static final String ORIENTATION = "orientation";  //朝向 ps:房屋-朝向
    public static final String TRAFFIC_CONDITIONS = "traffic.conditions";  //交通条件 ps:地铁、公交、主干道、主要转换互通桥信息的所有数据叠加
    public static final String URBAN_INFRASTRUCTURE = "urban.infrastructure";  //城市基础设施 ps:道路、网络、供水、排水、供电、供气、供热信息，显示有无信息。工业仓储类型（则取所有信息显示）
    public static final String PUBLIC_SERVICE_FACILITIES = "public.service.facilities";  //公共服务设施 ps:教育、购物、医养、金融、休闲娱乐所有信息显示
    //周边环境
    public static final String NATURAL = "natural";  //自然环境 ps:取类别与影响信息组合显示
    public static final String CULTURAL = "cultural";  //人文环境 ps:取类别与影响信息组合显示
    public static final String SCENERY = "scenery";  //景观 ps:取类别与影响信息组合显示

    //权益状况
    public static final String FEEINTEREST_TYPE = "feeInterest.type";  //土地权益类型 ps:土地证中获取信息
    public static final String LAND_CONTROL_SITUATION = "land.control.situation";  //土地管制情况 ps:土地权证中的“用途”、楼盘信息中“建筑面积”、“占地面积”、“ 容积率”、“绿化率”；楼栋信息“建筑高度”的最高值
    public static final String LAND_RIGHTS = "land.rights";  //土地他项权利 ps:他权信息中的类别
    public static final String HOUSING_OWNERSHIP = "housing.ownership";  //房屋所有权 ps:权证申报的类别、公有情况组合
    public static final String LEASEHOLD = "leasehold";  //租赁情况 ps:资产清查中获取类别为租赁权的信息，如果没有数据则显示为无。
    public static final String PROPERTY_MANAGEMENT = "property.management";  //物业管理情况 ps:楼栋中的物业公司名称+公司信誉+物业费
    public static final String OTHER_SPECIAL_SITUATIONS = "other.special.situations";  //其它特殊情况 ps:土地资产清查中特殊情况字段获取

    //实体状况
    public static final String LAND_ENTITY_STATUS = "land.entity.status";  //土地实体状况 ps:楼盘中土地实体状况所有字段信息
    public static final String BUILDING_SCALE = "building.scale";  //建筑规模
    public static final String BUILDING_AREA = "building.area";  //建筑面积（㎡） ps:楼盘-建筑面积
    public static final String FLOOR_COUNT = "floor.count";  //层数 ps:楼栋-总层数
    public static final String FLOOR_HEIGHT = "floor.height";  //层高 ps:楼栋-层高
    public static final String NET_HEIGHT = "net.height";  //净高 ps:楼栋-净高
    public static final String BUILDING_HEIGHT = "building.height";  //建筑高度 ps:楼栋-建筑高度
    public static final String BUILDING_STRUCTURE = "building.structure";  //建筑结构 ps:楼栋-建筑结构类别
    public static final String ARCHITECTURAL_OUTFIT = "architectural.outfit";  //建筑外装 ps:建筑外装所有字段信息组合
    //建筑功能
    public static final String AERATION = "aeration";  //通风 ps:房间中通风类型下的信息字段组合
    public static final String LIGHTING = "lighting";  //采光 ps:房间中采光类型下的信息字段组合
    public static final String SUNSHINE = "sunshine";  //日照 ps:房间中日照类型下的信息字段组合
    public static final String SOUND_INSULATION = "sound.insulation";  //隔音 ps:房间中隔音类型下的信息字段组合
    public static final String HEAT_PRESERVATION = "heat.preservation";  //保温 ps:楼栋下的建筑功能信息中类型为保温的字段组合
    public static final String HEAT_INSULATION = "heat.insulation";  //隔热 ps:楼栋下的建筑功能信息中类型为隔热的字段组合
    public static final String WATERPROOF = "waterproof";  //防水 ps:楼栋下的建筑功能信息中类型为防水的字段组合

    public static final String INTELLIGENT_LEVEL = "intelligent.level";  //设施设备及智能化程度
    public static final String POWER_SUPPLY_MODE = "power.supply.mode";  //供电方式 ps:供电方式所有字段信息组合
    public static final String WATER_SUPPLY_DRAINAGE_MODE = "water.supply.drainage.mode";  //供（排）水方式 ps:供水和排水所有字段信息组合
    public static final String HEATING_MODE = "heating.mode";  //采暖供热方式 ps:采暖和供热所有字段信息组合
    public static final String GAS_SUPPLY_MODE = "gas.supply.mode";  //供气方式 ps:供气方式所有字段信息组合
    public static final String NETWORK = "network";  //通讯网络 ps:通讯网络所有字段信息组合
    public static final String ELEVATOR_HOUSEHOLD_RATIO = "elevator.household.ratio";  //电梯梯户比 ps:单元-梯户比
    public static final String INTERNAL_ASSEMBLY = "internal.assembly";  //内装 ps:楼栋内装信息+房屋内的装修信息
    public static final String PLANE_LAYOUT = "plane.layout";  //平面布局
    public static final String NEW_DEGREE = "new.degree";  //成新度
    public static final String MAINTENANCE_LOSS_STATUS = "maintenance.loss.status";  //维护保养和完损状况
    public static final String OTHER = "other";  //其它
}

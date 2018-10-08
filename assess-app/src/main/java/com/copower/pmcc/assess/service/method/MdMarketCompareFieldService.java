package com.copower.pmcc.assess.service.method;

import org.springframework.stereotype.Service;

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdMarketCompareFieldService {

    private String estateName = new String();//楼盘名称 ps:楼盘-楼盘名称
    //交易情况
    private String scopeProperty = new String();//财产范围 ps:房屋-交易情况-财产范围
    private String financingConditions = new String();//融资条件 ps:房屋-交易情况-融资条件
    private String taxBurden = new String();//税费负担 ps:房屋-交易情况-税费负担
    private String paymentMethod = new String();//付款方式 ps:房屋-交易情况-付款方式
    private String tradingPrice = new String();//交易价格 ps:房屋-交易情况-交易价格

    //区位状况
    private String location = new String();//房地产坐落及方位 ps:权证的坐落、板块的方位、楼盘的方位字段组合
    private String officeConcentration = new String();//办公集聚度 ps:多条交通枢信息 （名称+距离）相组合显示
    private String floor = new String();//楼层 ps:房屋-所在层数
    private String orientation = new String();//朝向 ps:房屋-朝向
    private String trafficConditions = new String();//交通条件 ps:地铁、公交、主干道、主要转换信息的所有数据叠加
    private String urbanInfrastructure = new String();//城市基础设施 ps:道路、网络、供水、排水、供电、供气、供热信息，显示有无信息。工业仓储类型（则取所有信息显示）
    private String publicServiceFacilities = new String();//公共服务设施 ps:教育、购物、医养、金融、休闲娱乐所有信息显示
    //周边环境
    private String natural = new String();//自然环境 ps:取类别与影响信息组合显示
    private String cultural  = new String();//人文环境 ps:取类别与影响信息组合显示
    private String scenery = new String();//景观 ps:取类别与影响信息组合显示

    //权益状况
    private String feeInterestType = new String();//土地权益类型 ps:土地证中获取信息
    private String landControlSituation = new String();//土地管制情况 ps:土地权证中的“用途”、楼盘信息中“建筑面积”、“占地面积”、“ 容积率”、“绿化率”；楼栋信息“建筑高度”的最高值
    private String landRights = new String();//土地他项权利 ps:他权信息中的类别
    private String housingOwnership = new String();//房屋所有权 ps:权证申报的类别、公有情况组合
    private String leasehold = new String();//租赁情况 ps:资产清查中获取类别为租赁权的信息，如果没有数据则显示为无。
    private String propertyManagement = new String();//物业管理情况 ps:楼栋中的物业公司名称+公司信誉+物业费
    private String otherSpecialSituations = new String();//其他特殊情况 ps:土地资产清查中特殊情况字段获取

    //实体情况
    private String landEntityStatus = new String();//土地实体状况 ps:楼盘中土地实体状况所有字段信息
    private String buildingScale = new String();//建筑规模
    private String buildingArea = new String();//建筑面积（㎡） ps:楼盘-建筑面积
    private String floorCount = new String();//层数 ps:楼栋-总层数
    private String floorHeight = new String();//层高 ps:楼栋-层高
    private String netHeight = new String();//净高 ps:楼栋-净高
    private String buildingHeight = new String();//建筑高度 ps:楼栋-建筑高度
    private String buildingStructure = new String();//建筑结构 ps:楼栋-建筑结构类别
    private String architecturalOutfit = new String();//建筑外装 ps:建筑外装所有字段信息组合
    //建筑功能
    private String aeration = new String();//通风 ps:房间中通风类型下的信息字段组合
    private String lighting = new String();//采光 ps:房间中采光类型下的信息字段组合
    private String sunshine = new String();//日照 ps:房间中日照类型下的信息字段组合
    private String soundInsulation = new String();//隔音 ps:房间中隔音类型下的信息字段组合
    private String heatPreservation = new String();//保温 ps:楼栋下的建筑功能信息中类型为保温的字段组合
    private String heatInsulation = new String();//隔热 ps:楼栋下的建筑功能信息中类型为隔热的字段组合
    private String waterproof = new String();//防水 ps:楼栋下的建筑功能信息中类型为防水的字段组合
    //------------------------------------------------------------------------------------------------------------------
    private String intelligentLevel = new String();//设施设备及智能化程度
    private String powerSupplyMode = new String();//供电方式 ps:供电方式所有字段信息组合
    private String waterSupplyDrainageMode = new String();//供（排）水方式 ps:供水和排水所有字段信息组合
    private String heatingMode = new String();//采暖供热方式 ps:采暖和供热所有字段信息组合
    private String gasSupplyMode = new String();//供气方式 ps:供气方式所有字段信息组合
    private String network = new String();//通讯网络 ps:通讯网络所有字段信息组合
    private String elevatorHouseholdRatio = new String();//电梯梯户比 ps:单元-梯户比
    private String internalAssembly = new String();//内装 ps:楼栋内装信息+房屋内的装修信息
    private String planeLayout = new String();//平面布局
    private String newDegree = new String();//成新度
    private String maintenanceAndLossStatus = new String();//维护保养和完损状况
    private String other = new String();//其    他

    public void getInfo(Integer planDetailsId){
        //获取所需数据

    }

    /**
     *
     */
    private void initField(){

    }

}

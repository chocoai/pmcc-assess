package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 银行 (基础)
 */
public enum ReportFieldUniversalBankEnum {
    Seat("银行通用坐落"),

    AssessArea("银行通用评估面积"),

    AssessPrice("银行通用评估单价"),

    StatutoryOptimumReimbursement("银行通用法定优先受偿款"),
    StatutoryOptimumReimbursementMortgage("银行通用已抵押担保债权数额"),

    StatutoryOptimumReimbursementEngineering("银行通用拖欠的建设工程价款"),

    StatutoryOptimumReimbursementOther("银行通用其他法定优先受偿款"),

    ownership("银行通用房屋所有权人"),
    Co_ownership("银行通用共有权情况"),
    RegistrationDate("银行通用登记时间"),
    houseNature("银行通用房屋性质"),
    BuildArea("银行通用建筑面积"),
    CoverArea("银行通用套内建筑面积"),
    AttachmentReark("银行通用附记"),
    FillingUnit("银行通用填发单位"),
    CERT_NAME("银行通用权证号"),
    floor("银行通用楼层"),
    floorCount("银行通用总层数"),
    UsageStatus("银行通用使用状况"),
    estateName("银行通用楼盘名称"),
    housingStructure("银行通用房屋结构"),
    BeCompletedTimeGetInteger("银行通用建成年代"),
    NetAssessmentGroundNum("银行通用丘地号"),
    ANALYSIS_CATEGORY_LIQUIDITY("银行通用变现能力分析"),



    landNumber("银行通用土地证号"),//LandCertificateField1
    landownership("银行通用土地所有权人"),//LandCertificateField2
    landcert_use("银行通用土地证载用途"),//LandCertificateField3
    land_right_nature("银行通用土地权利性质"),//LandCertificateField4
    landapportionment_area("银行通用土地分摊面积"),//LandCertificateField5
    landendTime("银行通用土地终止日期"),//LandCertificateField6
    landregistration_authority("银行通用土地登记机关"),//LandCertificateField7
    landregistration_date("银行通用土地登记日期"),//LandCertificateField8

    LandArea("银行通用土地面积"),

    HuxingLayout("银行通用户型及布局"),
    AversionFacility("银行通用厌恶设施"),
    NetAssessmentOther("银行通用其它"),
    Orientation("银行通用朝向"),
    exteriorWallDecorate("银行通用外墙装饰"),
    LobbyDecorate("银行通用大堂装饰"),
    LayerNumber("银行通用层户数"),
    StoreyHeight("银行通用层高"),
    FoundationAndWall("银行通用地基及墙面"),

    renovation_condition_door("银行通用门装修情况"),//JudgeObjectDamagedDegreeField1
    renovation_condition_window("银行通用窗装修情况"),//JudgeObjectDamagedDegreeField2
    renovation_condition_land("银行通用地面装修情况"),//JudgeObjectDamagedDegreeField3
    renovation_condition_wall("银行通用内墙装修情况"),//JudgeObjectDamagedDegreeField4
    renovation_condition_Canopy("银行通用天棚装修情况"),//JudgeObjectDamagedDegreeField5
    renovation_condition_bathroom("银行通用卫生间装修情况"),//JudgeObjectDamagedDegreeField6
    renovation_condition_kitchen("银行通用厨房装修情况"),//JudgeObjectDamagedDegreeField7
    renovation_condition_maintenance("银行通用维护保养状况"),//JudgeObjectDamagedDegreeField8


    BankGeneralcultural_environment("银行通用人文环境"),//JudgeObjectLoactionField10
    BankGeneralNatural_environment("银行通用自然环境"),//JudgeObjectLoactionField9
    BankGeneralMedical_Facilities("银行通用医疗设施"),//JudgeObjectLoactionField8
    BankGeneraleducational_facility("银行通用教育设施"),//JudgeObjectLoactionField7
    BankGeneralinfrastructure("银行通用基础设施"),//JudgeObjectLoactionField6
    BankGeneralExternal_facilities("银行通用基础外部设施"),//JudgeObjectLoactionField6B
    BankGeneralsubway("银行通用地铁"),//JudgeObjectLoactionField5
    BankGeneralTraffic_accessibility("银行通用交通通达度"),//JudgeObjectLoactionField4
    Bus_Convenience("银行通用公交便捷度"),//JudgeObjectLoactionField3
    BankGeneralShoppingConditions("银行通用购物条件"),//JudgeObjectLoactionField2
    BankGeneralLocation("银行通用位置"),//BankGeneralLocation JudgeObjectLoactionField1

    BankGenerallandscape("银行通用个别景观"),//JudgeObjectOtherField1
    BankGeneralFrontage("银行通用临街状态"),//JudgeObjectOtherField2
    BankGeneralFloorSpacing("银行通用楼间距"),//JudgeObjectOtherField3
    BankGeneralGreenlandRate("银行通用绿地率"),//JudgeObjectOtherField4
    BankGeneralBuildingCoverage("银行通用建筑覆盖率"),//JudgeObjectOtherField5

    BankGeneralParkingLot("银行通用停车场"),//JudgeObjectOtherField6
    BankGeneralPropertyManagement("建行个贷物业管理"),//JudgeObjectOtherField7
    BankGeneralSuccessRate("银行通用成新率"),//JudgeObjectOtherField8

    ;
    private String name;

    private ReportFieldUniversalBankEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldUniversalBankEnum getEnumByName(String name) {
        for (ReportFieldUniversalBankEnum e : ReportFieldUniversalBankEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}

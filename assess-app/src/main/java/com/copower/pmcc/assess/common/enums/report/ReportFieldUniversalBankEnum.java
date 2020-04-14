package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 银行 (基础)
 */
public enum ReportFieldUniversalBankEnum {
    BankGeneralSeat("银行通用坐落"),

    BankGeneralAssessArea("银行通用评估面积"),

    BankGeneralAssessPrice("银行通用评估单价"),

    BankGeneralStatutoryOptimumReimbursement("银行通用法定优先受偿款"),
    BankGeneralStatutoryOptimumReimbursementMortgage("银行通用已抵押担保债权数额"),

    BankGeneralStatutoryOptimumReimbursementEngineering("银行通用拖欠的建设工程价款"),

    BankGeneralStatutoryOptimumReimbursementOther("银行通用其他法定优先受偿款"),

    BankGeneralOwnership("银行通用房屋所有权人"),
    BankGeneralCo_ownership("银行通用共有权情况"),
    BankGeneralRegistrationDate("银行通用登记时间"),
    BankGeneralhouseNature("银行通用房屋性质"),
    BankGeneralBuildArea("银行通用建筑面积"),
    BankGeneralCoverArea("银行通用套内建筑面积"),
    BankGeneralAttachmentReark("银行通用附记"),
    BankGeneralFillingUnit("银行通用填发单位"),
    BankGeneralCERT_NAME("银行通用权证号"),
    BankGeneralFloor("银行通用楼层"),
    BankGeneralFloorCount("银行通用总层数"),
    BankGeneralUsageStatus("银行通用使用状况"),
    BankGeneralEstateName("银行通用楼盘名称"),
    BankGeneralHousingStructure("银行通用房屋结构"),
    BankGeneralBeCompletedTimeGetInteger("银行通用建成年代"),
    BankGeneralNetAssessmentGroundNum("银行通用丘地号"),
    BankGeneralANALYSIS_CATEGORY_LIQUIDITY("银行通用变现能力分析"),


    BankGeneralLandNumber("银行通用土地证号"),//LandCertificateField1
    BankGenerallandownership("银行通用土地所有权人"),//LandCertificateField2
    BankGenerallandcert_use("银行通用土地证载用途"),//LandCertificateField3
    BankGeneralLand_right_nature("银行通用土地权利性质"),//LandCertificateField4
    BankGenerallandapportionment_area("银行通用土地分摊面积"),//LandCertificateField5
    BankGenerallandendTime("银行通用土地终止日期"),//LandCertificateField6
    BankGenerallandregistration_authority("银行通用土地登记机关"),//LandCertificateField7
    BankGenerallandregistration_date("银行通用土地登记日期"),//LandCertificateField8

    BankGeneralLandArea("银行通用土地面积"),

    BankGeneralHuxingLayout("银行通用户型及布局"),
    BankGeneralAversionFacility("银行通用厌恶设施"),
    BankGeneralNetAssessmentOther("银行通用其它"),
    BankGeneralOrientation("银行通用朝向"),
    BankGeneralexteriorWallDecorate("银行通用外墙装饰"),
    BankGeneralLobbyDecorate("银行通用大堂装饰"),
    BankGeneralLayerNumber("银行通用层户数"),
    BankGeneralStoreyHeight("银行通用层高"),
    BankGeneralFoundationAndWall("银行通用地基及墙面"),

    BankGeneralrenovation_condition_door("银行通用门装修情况"),//JudgeObjectDamagedDegreeField1
    BankGeneralrenovation_condition_window("银行通用窗装修情况"),//JudgeObjectDamagedDegreeField2
    BankGeneralrenovation_condition_land("银行通用地面装修情况"),//JudgeObjectDamagedDegreeField3
    BankGeneralrenovation_condition_wall("银行通用内墙装修情况"),//JudgeObjectDamagedDegreeField4
    BankGeneralrenovation_condition_Canopy("银行通用天棚装修情况"),//JudgeObjectDamagedDegreeField5
    BankGeneralrenovation_condition_bathroom("银行通用卫生间装修情况"),//JudgeObjectDamagedDegreeField6
    BankGeneralrenovation_condition_kitchen("银行通用厨房装修情况"),//JudgeObjectDamagedDegreeField7
    BankGeneralrenovation_condition_maintenance("银行通用维护保养状况"),//JudgeObjectDamagedDegreeField8


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

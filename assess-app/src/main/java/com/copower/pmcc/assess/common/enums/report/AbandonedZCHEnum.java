package com.copower.pmcc.assess.common.enums.report;

/**
 * Created by zch on 2020-3-19.
 * 用来保存整理枚举字段的存储地方
 * 当  整理报告字段完成后 此  枚举将删除
 * <p>
 * 配合 WordSortOut 里面的方法 完成此次任务
 */
public enum AbandonedZCHEnum {
    ReportNumber("文号"),
    queryCode("查询码"),
    recordNo("备案号"),
    recordDate("备案日期"),
    ReportNumber2("报告编号"),//文号中的具体编号
    ReportQrcode("报告二维码"),
    ReportUnitString("报告使用单位"),
    ReportingCategories("报告类别"),
    ReportIssuanceDate("报告出具日期"),
    ValueType("价值类型"),
    ValueTypeDesc("价值类型描述"),
    DelegatePurpose("委托目的"),
    ValueTimePoint("价值时点"),
    ValueTimePointRemark("价值时点说明"),
    StatementPurposeEntrustment("委托目的描述"),
    ThisYear("当前年份"),//今年
    CertificationPurpose2("小微贷证载用途"),
    EntrustedUnit("委托单位"),
    HotTip("特别提示"),
    Atypism2("小微贷不一致"),
    HotTipBank("金融机构特别提示"),
    Seat("坐落"),
    Seat2("小微贷坐落"),

    AssessArea("评估面积"),
    AssessPrice("评估单价"),
    AssessPriceClassification("评估总价分述"),

    AssessTotal("评估总价"),
    AssessTotalRMB("评估总价大写"),
    floorCount2("小微贷总层数"),
    unitType("户型"),
    DecorationStatus("装修状况"),
    StatutoryOptimumReimbursement2("拖欠的建设工程价款"),
    StatutoryOptimumReimbursement1("已抵押担保债权数额"),

    StatutoryOptimumReimbursement("法定优先受偿款"),
    StatutoryOptimumReimbursement3("其他法定优先受偿款"),

    CERT_NAME1("建行个贷权证号"),
    CERT_NAME3("金融机构权证号"),
    CERT_NAME2("小微贷权证号"),
    NetAssessmentGroundNum("建行个贷丘地号"),
    ownership("房屋所有权人"),
    Co_ownership("共有权情况"),
    RegistrationDate("登记时间"),
    houseNature("房屋性质"),

    CertificationPurpose("证载用途"),
    housingStructure2("小微贷房屋结构"),
    floorCount("建行个贷总层数"),
    floor("建行个贷楼层"),
    BuildArea("建筑面积"),
    CoverArea("套内建筑面积"),
    NetAssessmentOther("建行个贷其它"),
    AttachmentReark("附记"),
    FillingUnit("填发单位"),
    LandCertificateField1("土地证号"),
    LandCertificateField2("土地所有权人"),
    LandCertificateField3("土地证载用途"),
    LandCertificateField4("土地权利性质"),
    LandArea("土地面积"),
    LandCertificateField5("土地分摊面积"),
    LandCertificateField6("土地终止日期"),
    LandCertificateField7("土地登记机关"),
    LandCertificateField8("土地登记日期"),
    ArchivesDepositNumber("档案保管号"),
    estateName("楼盘名称"),
    BeCompletedTimeGetInteger("建成年代"),
    housingStructure("建行个贷房屋结构"),
    Orientation("建行个贷朝向"),
    exteriorWallDecorate("建行个贷外墙装饰"),
    LobbyDecorate("建行个贷大堂装饰"),

    LayerNumber("建行个贷层户数"),
    floor2("小微贷楼层"),
    StoreyHeight("建行个贷层高"),
    FoundationAndWall("建行个贷地基及墙面"),
    JudgeObjectLoactionField6("建行个贷基础设施"),
    JudgeObjectDamagedDegreeField1("建行个贷门装修情况"),
    JudgeObjectDamagedDegreeField2("建行个贷窗装修情况"),
    JudgeObjectDamagedDegreeField3("建行个贷地面装修情况"),
    JudgeObjectDamagedDegreeField4("建行个贷内墙装修情况"),

    JudgeObjectLoactionField10("建行个贷人文环境"),
    JudgeObjectLoactionField9("建行个贷自然环境"),
    JudgeObjectLoactionField8("建行个贷医疗设施"),
    JudgeObjectLoactionField7("建行个贷教育设施"),
    JudgeObjectLoactionField6B("建行个贷基础外部设施"),
    JudgeObjectLoactionField5("建行个贷地铁"),
    JudgeObjectLoactionField4("建行个贷交通通达度"),
    JudgeObjectLoactionField3("建行个贷公交便捷度"),
    JudgeObjectLoactionField2("建行个贷购物条件"),
    JudgeObjectLoactionField1("建行个贷位置"),
    JudgeObjectDamagedDegreeField5("建行个贷天棚装修情况"),
    JudgeObjectDamagedDegreeField8("建行个贷维护保养状况"),
    UsageStatus("建行个贷使用状况"),
    JudgeObjectOtherField1("建行个贷个别景观"),
    HuxingLayout("建行个贷户型及布局"),
    JudgeObjectOtherField2("建行个贷临街状态"),
    JudgeObjectOtherField3("建行个贷楼间距"),
    JudgeObjectOtherField4("建行个贷绿地率"),
    JudgeObjectOtherField5("建行个贷建筑覆盖率"),
    JudgeObjectOtherField6("建行个贷停车场"),
    JudgeObjectOtherField7("建行个贷物业管理"),
    JudgeObjectOtherField8("建行个贷成新率"),
    AversionFacility("建行个贷厌恶设施"),
    JudgeObjectDamagedDegreeField6("建行个贷卫生间装修情况"),
    JudgeObjectDamagedDegreeField7("建行个贷厨房装修情况"),

    ANALYSIS_CATEGORY_LIQUIDITY3("工商银行变现能力分析"),
    ANALYSIS_CATEGORY_LIQUIDITY2("建行个贷变现能力分析"),
    District_Analysis("建行个贷区位分析"),
    HotTip2("建行个贷特别提示"),
    ICBCValuationCaseInformationSheet("工行估价案例情况表"),
    StatutoryPriorityAmountTotal("法定优先受偿款总金额"),
    UsageStatus2("小微贷使用状况"),
    ValuationProjectName2("司法估价项目名称"),
    JudgeBuildResultSurveySheet3("司法估价结果一览表"),
    SetUse2("小微贷设定用途"),

    ValuationProjectName("估价项目名称"),
    PracticalUse("实际用途"),
    EvaluationMethod("评估方法"),
    HomeWorkEndTime("作业结束时间"),
    HomeWorkStartTime("作业开始时间"),
    AssistanceStaff("协助工作人员"),
    SetUse("设定用途"),
    PRINCIPAL("估价委托人"),
    EvaluationThink("估价技术思路"),
    PrincipalInfo("估价委托人信息"),
    PrincipalDescribe("估价对象描述"),
    PrincipalDataDescribe("估价信息描述"),
    RegisteredRealEstateValuerAndNumber("注册估价师及注册号"),
    RegisteredHouseRealEstateValuerAndNumber("注册房产估价师及注册号"),
    RegisteredRealEstateValuer("注册估价师"),
    XIEHE_organizationHouseInfo("房地产估价机构信息"),
    XIEHE_organizationInfo("估价机构信息"),
    XIEHE_organizationName("机构名称"),
    XIEHE_RealEstateValuationAgencyHouse("房地产估价机构"),
    XIEHE_RealEstateValuationAgency("估价机构"),
    XIEHE_organizationAddress("机构住所"),
    XIEHE_legalRepresentative("机构法定代表人"),
    XIEHE_registeredNo("机构工商注册号"),
    XIEHE_organizationRank("机构资质等级"),
    XIEHE_certificateNo("机构证书号"),
    XIEHE_certificateEffectiveDate("机构证书有效期"),


    ;
    private String name;

    private AbandonedZCHEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AbandonedZCHEnum getEnumByName(String name) {
        for (AbandonedZCHEnum e : AbandonedZCHEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }


}

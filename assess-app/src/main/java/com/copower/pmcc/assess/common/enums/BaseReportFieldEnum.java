package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段
 */
public enum BaseReportFieldEnum implements Serializable {
    ReportNumber("文号"),
    ReportNumber2("报告编号"),//文号中的具体编号
    ReportQrcode("报告二维码"),
    ReportUnitString("报告使用单位"),
    RegistrationDate("登记时间"),
    ReportingCategories("报告类别"),
    ValuationProjectName("估价项目名称"),
    ReportIssuanceDate("报告出具日期"),
    ValueType("价值类型"),
    ValueTypeDesc("价值类型描述"),
    DelegatePurpose("委托目的"),
    CertificationPurpose("证载用途"),
    CertificationPurpose2("小微贷证载用途"),
    ScopePropertyExplain("财产范围说明"),
    PracticalUse("实际用途"),
    ValueTimePoint("价值时点"),
    ValueTimePointRemark("价值时点说明"),
    EvaluationMethod("评估方法"),
    TotalRealEstatePrice("房地产总价"),
    CapitalizationAmount("房地产总价大写金额"),
    totalAmountMortgageValue("抵押价值总金额"),
    totalAmountMortgageValueCapitalization("抵押价值总金额大写"),
    HisRightType("他权类别"),
    HisRightTypeAndDetail("他权类别及明细"),
    ExpertWorkOverview("外聘专家工作概况"),
    BusinessScope("经营范围"),
    StatementPurposeEntrustment("委托目的描述"),
    BestUseDesc("最佳利用方式"),
    DecalreFormTypeAll("申报启用表单类型"),
    Co_ownership("共有权情况"),
    AssessTotal("评估总价"),
    AssessTotalRMB("评估总价大写"),
    AssessArea("评估面积"),
    BuildArea("建筑面积"),
    CoverArea("套内建筑面积"),
    AssessPrice("评估单价"),
    NetAssessmentOne("建行个贷评估净值one"),
    NetAssessmentTwo("建行个贷评估净值two"),
    NetAssessmentOther("建行个贷其它"),
    NetAssessmentGroundNum("建行个贷丘地号"),
    AssessPriceClassification("评估总价分述"),
    surveyExamineDate("现场查勘期"),
    surveyExamineCreate("现场查勘人员"),
    HomeWorkEndTime("作业结束时间"),
    HomeWorkStartTime("作业开始时间"),
    AssistanceStaff("协助工作人员"),
    SetUse("设定用途"),
    SetUse2("小微贷设定用途"),
    rentalPossessionDesc("出租或占用情况"),
    HouseType("房产类型"),
    Seat("坐落"),
    Seat2("小微贷坐落"),
    Atypism2("小微贷不一致"),
    housingStructure("建行个贷房屋结构"),
    housingStructure2("小微贷房屋结构"),
    CERT_NAME1("建行个贷权证号"),
    CERT_NAME2("小微贷权证号"),
    floorCount("建行个贷总层数"),
    floorCount2("小微贷总层数"),
    houseNature("房屋性质"),
    ownership("房屋所有权人"),
    ArchivesDepositNumber("档案保管号"),
    floor("建行个贷楼层"),
    floor2("小微贷楼层"),
    LayerNumber("建行个贷层户数"),
    unitType("户型"),
    ThisYear("当前年份"),//今年
    estateName("楼盘名称"),
    Orientation("建行个贷朝向"),
    StoreyHeight("建行个贷层高"),
    BeCompletedTimeGetInteger("建成年代"),
    FillingUnit("填发单位"),
    exteriorWallDecorate("建行个贷外墙装饰"),
    LobbyDecorate("建行个贷大堂装饰"),
    FoundationAndWall("建行个贷地基及墙面"),
    AttachmentReark("附记"),
    DecorationStatus("装修状况"),
    UsageStatus("建行个贷使用状况"),
    UsageStatus2("小微贷使用状况"),
    EntrustedUnit("委托单位"),
    HuxingLayout("建行个贷户型及布局"),
    CCB_Pre_Evaluation_Data_Form("建行预评数据表格"),
    LandCertificateField1("土地证号"),
    LandCertificateField2("土地所有权人"),
    LandCertificateField3("土地证载用途"),
    LandCertificateField4("土地权利性质"),
    LandCertificateField5("土地面积"),
    LandCertificateField6("土地终止日期"),
    LandCertificateField7("土地登记机关"),
    LandCertificateField8("土地登记日期"),
    JudgeObjectLoactionField1("建行个贷位置"),
    JudgeObjectLoactionField2("建行个贷购物条件"),
    JudgeObjectLoactionField3("建行个贷公交便捷度"),
    JudgeObjectLoactionField4("建行个贷交通通达度"),
    JudgeObjectLoactionField5("建行个贷地铁"),
    JudgeObjectLoactionField6("建行个贷基础设施"),
    JudgeObjectLoactionField7("建行个贷教育设施"),
    JudgeObjectLoactionField8("建行个贷医疗设施"),
    JudgeObjectLoactionField9("建行个贷自然环境"),
    JudgeObjectLoactionField10("建行个贷人文环境"),
    JudgeObjectDamagedDegreeField1("建行个贷门装修情况"),
    JudgeObjectDamagedDegreeField2("建行个贷窗装修情况"),
    JudgeObjectDamagedDegreeField3("建行个贷地面装修情况"),
    JudgeObjectDamagedDegreeField4("建行个贷内墙装修情况"),
    JudgeObjectDamagedDegreeField5("建行个贷天棚装修情况"),
    JudgeObjectDamagedDegreeField6("建行个贷卫生间装修情况"),
    JudgeObjectDamagedDegreeField7("建行个贷厨房装修情况"),
    JudgeObjectDamagedDegreeField8("建行个贷维护保养状况"),
    JudgeObjectOtherField1("建行个贷个别景观"),
    JudgeObjectOtherField2("建行个贷临街状态"),
    JudgeObjectOtherField3("建行个贷楼间距"),
    JudgeObjectOtherField4("建行个贷绿地率"),
    JudgeObjectOtherField5("建行个贷建筑覆盖率"),
    JudgeObjectOtherField6("建行个贷停车场"),
    JudgeObjectOtherField7("建行个贷物业管理"),
    JudgeObjectOtherField8("建行个贷成新率"),
    AversionFacility("建行个贷厌恶设施"),

    JudgeBuildResultSurveySheet("估价结果一览表"),
    JudgeBuildResultSurveySheet2("估价结果一览表不含坐落"),
    EquityStatusObjectSheet("估价对象权属"),
    JudgeObjectAreaStatusSheet("估价对象区位状况"),
    JudgeObjectLandStateSheet("估价土地实体状况"),
    JudgeBuildLandStateSheet("估价建筑物实体状况"),
    JudgeObjectEquitySheet("估价对象权益状况"),
    judgeSummarySheet("估价汇总表"),
    DetailedCalculationProcessValuationObject("估价对象详细测算过程"),

    PRINCIPAL("估价委托人"),
    EvaluationThink("估价技术思路"),
    PrincipalInfo("估价委托人信息"),
    PrincipalDescribe("估价对象描述"),
    PrincipalDataDescribe("估价信息描述"),
    DeterminationMarketValueValuationObject("估价对象市场价值的确定"),
    HotTip("特别提示"),
    HotTip2("建行个贷特别提示"),

    RegisteredRealEstateValuerAndNumber("注册房产估价师及注册号"),
    XIEHE_organizationInfo("房地产估价机构信息"),
    XIEHE_organizationName("机构名称"),
    XIEHE_RealEstateValuationAgency("房地产估价机构"),
    XIEHE_organizationAddress("机构住所"),
    XIEHE_legalRepresentative("机构法定代表人"),
    XIEHE_registeredNo("机构工商注册号"),
    XIEHE_organizationRank("机构资质等级"),
    XIEHE_certificateNo("机构证书号"),
    XIEHE_certificateEffectiveDate("机构证书有效期"),

    SelectionValuationMethod("估价对象选择估价方法"),
    NotSelectionValuationMethod("估价对象不选择估价方法"),
    EvaluationExpression("分类评估单价计算试"),
    EvaluationMethodResult("分类评估方法结果"),

    StatutoryOptimumReimbursement("法定优先受偿款"),
    StatutoryOptimumReimbursement3("其他法定优先受偿款"),
    StatutoryOptimumReimbursement2("拖欠的建设工程价款"),
    StatutoryOptimumReimbursement1("已抵押担保债权数额"),
    StatutoryPriorityAmountTotal("法定优先受偿款总金额"),

    ComputationProcess("主要计算过程"),
    SelectionApplicationParameters("相关参数选取与应用"),

    EVALUATION_HYPOTHESIS("评估假设"),
    EVALUATION_BASIS("评估依据"),
    EVALUATION_PRINCIPLE("评估原则"),
    ANALYSIS_CATEGORY_LIQUIDITY("变现能力分析"),
    ANALYSIS_CATEGORY_LIQUIDITY2("建行个贷变现能力分析"),
    LIQUIDATION_ANALYSIS("变现分析税费"),
    ANALYSIS_CATEGORY_RISK("风险提示"),
    ReportArea("出具报告区域"),


    JudgeObjectPrincipalCopySheet("估价委托书复印件"),
    EstimatedObjectLocationDiagram("估价对象位置示意图"),
    Valuation_Target_Live_Photos("估价对象实况照片"),
    Copies_the_Ownership_Certificate_the_Valuation_Object("估价对象权属证明复印件"),
    Special_documentation_referenced_in_valuation("估价中引用的专用文件资料"),
    CopyBusinessLicenseRealEstateValuationAgency("房地产估价机构营业执照复印件"),
    CopyQualificationCertificateRealEstateValuationInstitution("房地产估价机构资质证书复印件"),
    RegisteredRealEstateValuerValuationInstitution("注册房地产估价师注册证书复印件"),

    BACKGROUND_ANALYSIS_DEVELOPMENT("社会经济发展概况"),
    BACKGROUND_ANALYSIS_GENERAL("房地产市场总体概况"),
    BACKGROUND_ANALYSIS_MARKET("同类房地产市场状况"),
    BACKGROUND_ANALYSIS_BLOCK("同类房地产市场版块状况"),
    BACKGROUND_ANALYSIS_PROPERTY("背景估价对象区域物业总体状况"),;

    private String key;

    private String name;


    private BaseReportFieldEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    private BaseReportFieldEnum(String name) {
        this.name = name;
    }

    public static BaseReportFieldEnum getEnumByName(String id) {
        for (BaseReportFieldEnum e : BaseReportFieldEnum.values()) {
            if (e.getKey().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldEnum e : BaseReportFieldEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

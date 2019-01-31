package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/15 14:30
 * @Description:报告模板字段
 */
public enum BaseReportFieldEnum {
    REPORTNUMBER("", "文号", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    PRINCIPAL("", "委托人", BaseReportFieldReplaceEnum.TEXT.getKey()),
    Location("", "区位", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    powerPerson("", "权利人", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),//(区位)
    notPowerPerson("", "非权利人", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),//(区位)
    ValueType("", "价值类型", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    DefinitionValue("", "价值定义", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ValueImplication("", "价值含义", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    AssessArea("", "评估面积", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    UseRightType("", "使用权类型", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    LandPracticalUse("", "土地实际用途", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    StatementPurposeEntrustment("", "委托目的表述", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    SetUse("", "设定用途", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    LandUseControl("", "土地使用管制", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    rentalPossessionDesc("", "出租或占用情况", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    HouseType("", "房产类型", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ValueTimePoint("", "价值时点", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ValueTimePointRemark("", "价值时点说明", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationThink("", "估价技术思路", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    SelectionValuationMethod("", "选择估价方法", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationPriceCateGory("", "分类评估单价", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationAreaCateGory("", "分类评估面积", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationPriceCateGoryTotal("", "分类评估总价", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationExpression("", "分类评估单价计算试", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationMethodResult("", "分类评估方法结果", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    WeightSpecification("", "权重说明", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    TotalRealEstatePrice("", "房地产总价", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ValueExpressionResult("", "价值表达结果", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    StatutoryOptimumReimbursement("", "法定优选受偿款", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    reportIssuanceDate("", "报告出具日期", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CapitalizationAmount("", "大写金额", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ValuationProjectName("", "估价项目名称", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    ComputationProcess("", "计算过程", BaseReportFieldReplaceEnum.FILE.getKey()),
    SelectionApplicationParameters("", "参数选取与应用", BaseReportFieldReplaceEnum.FILE.getKey()),
    StatusBuildingRightsInterests("", "建筑物权益状况", BaseReportFieldReplaceEnum.FILE.getKey()),
    theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods("", "本次估价的总体思路和评估方法的选取", BaseReportFieldReplaceEnum.FILE.getKey()),

    CostAssistApplyReason("", "成本法适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CostAssistNotApplicableReason("", "成本法不适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CostAssistThink("", "成本法评估思路", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    CompareAssistApplyReason("", "市场比较法适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CompareAssistNotApplicableReason("", "市场比较法不适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CompareAssistThink("", "市场比较法评估思路", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    IncomeAssistApplyReason("", "收益法适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    IncomeAssistNotApplicableReason("", "收益法不适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    IncomeAssistThink("", "收益法评估思路", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    DevelopmentAssistApplyReason("", "假设开发法适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    DevelopmentAssistNotApplicableReason("", "假设开发法不适用原因", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    DevelopmentAssistThink("", "假设开发法评估思路", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    SeparationCertificateUses("", "证载用途分述", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    SummaryCertificateUses("", "证载用途总括", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CertificationPurpose("", "证载用途", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    RegisteredRealEstateValuer("", "注册房产估价师", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    RegisteredRealEstateValuerAndNumber("", "注册房产估价师及注册号", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    registrationNumber("", "注册号", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    surveyExamineDate("", "现场查勘期", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    surveyExamineCreate("", "现场查勘人员", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    HomeWorkEndTime("", "作业结束时间", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    HomeWorkStartTime("", "作业开始时间", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    EVALUATION_HYPOTHESIS("", "评估假设", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EVALUATION_BASIS("", "评估依据", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EVALUATION_PRINCIPLE("", "评估原则", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ReportAnalysis("", "报告分析", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EXECUTE_USER_ACCOUNT("", "报告分析", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    AssistanceStaff("", "协助工作人员", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    XIEHE_organizationName("", "机构名称", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_RealEstateValuationAgency("", "房地产估价机构", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_organizationAddress("", "机构住所", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_legalRepresentative("", "机构名称法定代表人", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_registeredNo("", "机构工商注册号", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_organizationRank("", "机构资质等级", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_certificateNo("", "机构证书号", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    XIEHE_certificateEffectiveDate("", "证书有效期", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    HousingOwnershipRegistrationStatementSheet("", "房屋所有权登记状况表", BaseReportFieldReplaceEnum.FILE_FIXED.getKey()),
    judgeObjectAreaStatusSheet("", "估价对象区位状况表", BaseReportFieldReplaceEnum.FILE_FIXED.getKey()),
    judgeObjectLandStateSheet("", "估价土地实体状况表", BaseReportFieldReplaceEnum.FILE_FIXED.getKey()),
    judgeBuildLandStateSheet("", "估价建筑物实体状况表", BaseReportFieldReplaceEnum.FILE_FIXED.getKey()),
    judgeBuildResultSurveySheet("", "估价结果一览表", BaseReportFieldReplaceEnum.FILE_FIXED.getKey()),
    judgeSummarySheet("", "汇总表", BaseReportFieldReplaceEnum.FILE_FIXED.getKey()),

    JUDGEOBJECTPRINCIPALCOPYSHEET("", "估价委托书复印件", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EstimatedObjectLocationDiagram("", "估计对象位置示意图", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    Valuation_Target_Live_Photos("", "估价对象实况照片", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    Copies_the_Ownership_Certificate_the_Valuation_Object("", "估价对象权属证明复印件", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    Special_documentation_referenced_in_valuation("", "估价中引用的专用文件资料", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    CopyBusinessLicenseRealEstateValuationAgency("", "房地产估价机构营业执照复印件", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CopyQualificationCertificateRealEstateValuationInstitution("", "房地产估价机构资质证书复印件", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    RegisteredRealEstateValuerValuationInstitution("", "注册房地产估价师注册证书复印件", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),

    inventoryRight("", "土地他项权利情况", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationMethod("", "评估方法", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    SummaryEvaluationMethod("", "评估方法总括", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    AssessmentMethods("", "评估方法分述", BaseReportFieldReplaceEnum.BOOKMARK.getKey());
    private String key;

    private String name;
    private String describe;

    private BaseReportFieldEnum(String key, String name, String describe) {
        this.name = name;
        this.key = key;
        this.describe = describe;
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

package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 公共
 */
public enum ReportFieldCommonEnum implements Serializable {
    CommonReportNumber("公共文号"),// ReportNumber("文号")
    CommonQueryCode("公共查询码"),//queryCode("查询码")
    CommonRecordNo("公共备案号"),//recordNo("备案号")
    CommonThisYear("公共当前年份"),//今年 ThisYear("当前年份")
    CommonRecordDate("公共备案日期"),//recordDate("备案日期")
    CommonReportNumbering("公共报告编号"),//ReportNumber2("报告编号")
    CommonReportQrcode("公共报告二维码"),//ReportQrcode("报告二维码")
    CommonReportingCategories("公共报告类别"),//ReportingCategories("报告类别")
    CommonReportUnitString("公共报告使用单位"),// ReportUnitString("报告使用单位")
    CommonReportIssuanceDate("公共报告出具日期"),//ReportIssuanceDate("报告出具日期")
    CommonDelegatePurpose("公共委托目的"),//DelegatePurpose("委托目的")
    CommonStatementPurposeEntrustment("公共委托目的描述"),//StatementPurposeEntrustment("委托目的描述")
    CommonValueTimePoint("公共价值时点"),//ValueTimePoint("价值时点") 评估基准日
    CommonValueTimePointRemark("公共价值时点说明"),//ValueTimePointRemark("价值时点说明") 评估基准日说明
    CommonValueType("公共价值类型"), //ValueType("价值类型")
    CommonValueTypeDesc("公共价值类型描述"), //ValueTypeDesc("价值类型描述")
    CommonEntrustedUnit("公共委托单位"),//
    CommonStatutoryPriorityAmountTotal("公共法定优先受偿款总金额"),
    CommonHotTip("公共特别提示"),
    CommonAssessTotal("公共评估总价"),
    CommonAssessTotalRMB("公共评估总价大写"),
    CommonCertificationPurpose("公共证载用途"),

    ValuationProjectName("公共估价项目名称"),
    PracticalUse("公共实际用途"),
    EvaluationMethod("公共评估方法"),
    HomeWorkEndTime("公共作业结束时间"),
    HomeWorkStartTime("公共作业开始时间"),
    AssistanceStaff("公共协助工作人员"),
    SetUse("公共设定用途"),
    PRINCIPAL("公共估价委托人"),
    EvaluationThink("公共估价技术思路"),
    PrincipalInfo("公共估价委托人信息"),
    PrincipalDescribe("公共估价对象描述"),
    PrincipalDataDescribe("公共估价信息描述"),
    RegisteredRealEstateValuerAndNumber("公共注册估价师及注册号"),
    RegisteredHouseRealEstateValuerAndNumber("公共注册房产估价师及注册号"),
    RegisteredRealEstateValuer("公共注册估价师"),
    XIEHE_organizationHouseInfo("公共房地产估价机构信息"),
    XIEHE_organizationInfo("公共估价机构信息"),
    XIEHE_organizationName("公共机构名称"),
    XIEHE_RealEstateValuationAgencyHouse("公共房地产估价机构"),
    XIEHE_RealEstateValuationAgency("公共估价机构"),
    XIEHE_organizationAddress("公共机构住所"),
    XIEHE_legalRepresentative("公共机构法定代表人"),
    XIEHE_registeredNo("公共机构工商注册号"),
    XIEHE_organizationRank("公共机构资质等级"),
    XIEHE_certificateNo("公共机构证书号"),
    XIEHE_certificateEffectiveDate("公共机构证书有效期"),

    ;
    private String name;

    private ReportFieldCommonEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldCommonEnum getEnumByName(String name) {
        for (ReportFieldCommonEnum e : ReportFieldCommonEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}

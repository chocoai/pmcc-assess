package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 公共
 */
public enum BaseReportCommonEnum implements Serializable {
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
    CommonCertificationPurpose("公共证载用途"),;
    private String name;

    private BaseReportCommonEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BaseReportCommonEnum getEnumByName(String name) {
        for (BaseReportCommonEnum e : BaseReportCommonEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}

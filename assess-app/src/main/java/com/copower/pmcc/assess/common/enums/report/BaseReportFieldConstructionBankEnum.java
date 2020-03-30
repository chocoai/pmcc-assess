package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 建设银行
 */
public enum BaseReportFieldConstructionBankEnum {
    AssessPriceClassification("建行评估总价分述"),
    SetUse("建行设定用途"),
    HotTipBank("建行特别提示"),
    ArchivesDepositNumber("建行档案保管号"),
    unitType("建行户型"),
    DecorationStatus("建行装修状况"),
    CertificationPurpose("建行证载用途"),
    CCB_Pre_Evaluation_Data_Form("建行预评数据表格"),
    NetAssessmentOne("建行个贷评估净值one"),
    NetAssessmentTwo("建行个贷评估净值two"),
    ;
    private String name;
    private BaseReportFieldConstructionBankEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BaseReportFieldConstructionBankEnum getEnumByName(String name) {
        for (BaseReportFieldConstructionBankEnum e : BaseReportFieldConstructionBankEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}

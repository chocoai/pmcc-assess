package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 建设银行
 */
public enum ReportFieldJiansheBankEnum {
    JiansheAssessPriceClassification("建行评估总价分述"),
    JiansheSetUse("建行设定用途"),
    JiansheHotTipBank("建行特别提示"),
    JiansheArchivesDepositNumber("建行档案保管号"),
    JiansheunitType("建行户型"),
    JiansheDecorationStatus("建行装修状况"),
    JiansheCertificationPurpose("建行证载用途"),
    JiansheCCB_Pre_Evaluation_Data_Form("建行预评数据表格"),
    JiansheNetAssessmentOne("建行个贷评估净值one"),
    JiansheNetAssessmentTwo("建行个贷评估净值two"),
    ;
    private String name;
    private ReportFieldJiansheBankEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldJiansheBankEnum getEnumByName(String name) {
        for (ReportFieldJiansheBankEnum e : ReportFieldJiansheBankEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}

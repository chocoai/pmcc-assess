package com.copower.pmcc.assess.common.enums.assets;

/**
 * Created by zch on 2019/9/17.
 * 资产评估 文件和字段配置
 */
public enum DataAssetsAppraisalTypeEnum {
    Assets_Declaration_ENUM("资产申报阶段","declaration"),
    Assets_Inventory_ENUM("资产清查阶段","inventory"),
    Assets_Issue_Report_ENUM("出具报告","report"),
    Assessment_Estimation_ENUM("评定估算阶段","estimation"),
    ;
    private String dec;
    private String key;

    DataAssetsAppraisalTypeEnum(String dec, String key) {
        this.dec = dec;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getDec() {
        return dec;
    }
}

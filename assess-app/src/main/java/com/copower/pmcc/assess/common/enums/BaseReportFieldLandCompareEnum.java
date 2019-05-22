package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/15 14:30
 * @Description:报告模板字段(市场比较法)
 */
public enum BaseReportFieldLandCompareEnum {
    BIDDING_HANGING_CASE("土地比较法招拍挂案例表"),
    REGIONAL_FACTORS("土地比较法相关区域因素"),
    INDIVIDUAL_FACTORS("土地比较法相关个别因素"),
    AMENDMENT_FACTOR_TABLE("土地比较法修正因素表"),
    REVISED_INDEX_TABLE("土地比较法修正指数表"),

    TRANSACTION_SITUATION("土地比较法交易情况修正说明"),
    DATE_AMENDMENT("土地比较法期日修正说明"),
    VOLUME_RATE_CORRECTION("土地比较法容积率修正说明"),
    SET_USE("土地比较法设定用途"),
    REMAINING_YEARS("土地比较法剩余年限"),
    USE_YEARS("土地比较法使用年限"),

    OPPORTUNITY_COST_STATEMENT("土地比较法机会成本说明"),
    OPPORTUNITY_COST_RATE("土地比较法机会成本率"),
    REMUNERATION_RATE_TABLE("土地比较法报酬率测算表"),
    REMUNERATION_RATE("土地比较法报酬率"),

    ANNUAL_CORRECTION_COEFFICIENT("土地比较法年期修正系数"),
    PARCEL_OUT_SET_USE("土地比较法宗地外设定用途"),
    PARCEL_IN_SET_USE("土地比较法宗地内设定用途"),
    PARCEL_OUT_DEVELOPMENT_LEVEL("土地比较法宗地外开发程度"),
    PARCEL_IN_DEVELOPMENT_LEVEL("土地比较法宗地内开发程度"),

    CORRECTION_COEFFICIENT_TABLE("土地比较法修正系数表"),
    CASE_COUNT("土地比较法案例个数"),
    CASE_PRICE("土地比较法案例价格"),
    PRICE_CALCULATION_METHOD("土地比较法价格计算方式"),
    COMPUTATION_PROCESS("土地比较法计算过程"),
    COMPUTATION_RESULT("土地比较法计算结果"),
    VALUATION_PRICE("土地比较法评估单价")
    ;
    private String key;

    private String name;


    private BaseReportFieldLandCompareEnum(String name) {
        this.name = name;
    }


    public static BaseReportFieldLandCompareEnum getEnumByName(String name) {
        for (BaseReportFieldLandCompareEnum e : BaseReportFieldLandCompareEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldLandCompareEnum e : BaseReportFieldLandCompareEnum.values()) {
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

package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/15 14:30
 * @Description:报告模板字段(市场比较法)
 */
public enum BaseReportFieldCompareEnum {
    CASE_NUMBER("比较法案例个数"),
    COMPARABLE_BASIS("比较法可比案例情况表"),
    LOCATION_CONDITION("比较法估价对象区位状况表"),
    ENTITY_CONDITION("比较法估价对象实体状况表"),
    RIGHTS_INTERESTS("比较法估价对象权益状况表"),

    PROPERTY_RANGE("比较法财产范围"),
    PAYMENT_METHOD("比较法付款方式"),
    FINANCING_CONDITION("比较法融资条件"),
    TAX_BURDEN("比较法税费负担"),
    TRANSACTION_MODIFICATION("比较法交易情况修正"),
    DATE_REVISION("比较法期日修正"),

    MARKET_ADJUSTMENT("比较法委估对象市场状况调整"),
    LOCATION_QUOTIENT("比较法估价对象区位指数表"),
    EQUITY_INDEX("比较法估价对象权益指数表"),
    ENTITY_INDEX("比较法估价对象实体指数表"),
    CALCULATION_RESULT("比较法测算结果表"),
    COUNT_COURSE("比较法测算过程"),
    COUNT_RESULT("比较法测算结果"),

    HOUSEPRICE_INDEX( "比较法房价指数表"),
    DESIGN_FORMULAS( "比较法计算公式"),
    ;
    private String key;

    private String name;
    private String describe;

    private BaseReportFieldCompareEnum(String key, String name, String describe) {
        this.name = name;
        this.key = key;
        this.describe = describe;
    }

    private BaseReportFieldCompareEnum(String name) {
        this.name = name;
    }


    public static BaseReportFieldCompareEnum getEnumByName(String name) {
        for (BaseReportFieldCompareEnum e : BaseReportFieldCompareEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldCompareEnum e : BaseReportFieldCompareEnum.values()) {
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

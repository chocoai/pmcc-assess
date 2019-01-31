package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/15 14:30
 * @Description:报告模板字段
 */
public enum BaseReportFieldCompareEnum {
    COMPARABLE_BASIS("", "委估对象可比基础", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    LOCATION_CONDITION("", "估价对象区位状况表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    RIGHTS_INTERESTS("", "估价对象权益状况表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ENTITY_CONDITION("", "估价对象实体状况表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    TRANSACTION_MODIFICATION("", "交易情况修正", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    DATE_REVISION("", "期日修正", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    MARKET_ADJUSTMENT("", "委估对象市场状况调整", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    LOCATION_QUOTIENT("", "估价对象区位指数表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EQUITY_INDEX("", "估价对象权益指数表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ENTITY_INDEX("", "估价对象实体指数表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    CALCULATION_RESULT("", "测算结果表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ;
    private String key;

    private String name;
    private String describe;

    private BaseReportFieldCompareEnum(String key, String name, String describe) {
        this.name = name;
        this.key = key;
        this.describe = describe;
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

package com.copower.pmcc.assess.common.enums.tool;

import com.copower.pmcc.erp.api.dto.KeyValueDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/25.
 */
public enum ToolRewardRateEnum {
    opportunityCost("opportunityCost", "机会成本"),
    riskCompensation("riskCompensation", "投资风险补偿"),
    manageCompensation("manageCompensation", "管理负担补偿"),
    liquidCompensation("liquidCompensation", "缺乏流动性补偿"),
    financingAdvantage("financingAdvantage", "易与获得融资的好处"),
    taxDeductionAdvantage("taxDeductionAdvantage", "所得税抵扣的好处");
    private String key;
    private String name;

    ToolRewardRateEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    // name替换id
    public static String getName(String key) {
        for (ToolRewardRateEnum c : ToolRewardRateEnum.values()) {
            if (StringUtils.equals(c.getKey(), key)) {
                return c.name;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getSchemeSupportTypeEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (ToolRewardRateEnum e : ToolRewardRateEnum.values()) {
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


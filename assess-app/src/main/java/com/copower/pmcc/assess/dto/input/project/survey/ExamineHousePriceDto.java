package com.copower.pmcc.assess.dto.input.project.survey;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/7/24 11:43
 * @Description:
 */
public class ExamineHousePriceDto implements Serializable {
    private String key;
    private String value;
    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

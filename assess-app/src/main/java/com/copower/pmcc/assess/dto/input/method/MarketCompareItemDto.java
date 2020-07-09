package com.copower.pmcc.assess.dto.input.method;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-7-23.
 */
public class MarketCompareItemDto {
    private String name;
    private String desc;
    private String value;
    private BigDecimal score;
    private BigDecimal ratio;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

}

package com.copower.pmcc.assess.dto.output;

import java.io.Serializable;
import java.math.BigDecimal;

public class KeyValueVo implements Serializable {

    private String key;

    private String value;

    private BigDecimal achievement;

    private Integer id;

    public KeyValueVo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueVo(String key, String value, BigDecimal achievement) {
        this.key = key;
        this.value = value;
        this.achievement = achievement;
    }

    public KeyValueVo(String key, String value, BigDecimal achievement, Integer id) {
        this.key = key;
        this.value = value;
        this.achievement = achievement;
        this.id = id;
    }

    public KeyValueVo() {
    }

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

    public BigDecimal getAchievement() {
        return achievement;
    }

    public void setAchievement(BigDecimal achievement) {
        this.achievement = achievement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValueVo that = (KeyValueVo) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (achievement != null ? !achievement.equals(that.achievement) : that.achievement != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (achievement != null ? achievement.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}

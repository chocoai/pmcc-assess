package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailAchievementVo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/5/22 11:37
 * @description:
 */
public class ExampleDDD  implements Serializable{
    private String number;
    private String type;
    private Integer price;
    private Integer number_;
    private List<DataLandLevelDetailAchievementVo> dataLandDetailAchievementVos = Lists.newArrayList();

    public String getNumber() {
        return number;
    }

    public ExampleDDD setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getType() {
        return type;
    }

    public ExampleDDD setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ExampleDDD setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getNumber_() {
        return number_;
    }

    public ExampleDDD setNumber_(Integer number_) {
        this.number_ = number_;
        return this;
    }

    public List<DataLandLevelDetailAchievementVo> getDataLandDetailAchievementVos() {
        return dataLandDetailAchievementVos;
    }

    public void setDataLandDetailAchievementVos(List<DataLandLevelDetailAchievementVo> dataLandDetailAchievementVos) {
        this.dataLandDetailAchievementVos = dataLandDetailAchievementVos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleDDD that = (ExampleDDD) o;
        return Objects.equal(number, that.number) &&
                Objects.equal(type, that.type) &&
                Objects.equal(price, that.price) &&
                Objects.equal(number_, that.number_);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number, type, price, number_);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExampleDDD{");
        sb.append("number='").append(number).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", price=").append(price);
        sb.append(", number_=").append(number_);
        sb.append('}');
        return sb.toString();
    }
}

package com.copower.pmcc.assess.dto.output;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @Auther: zch
 * @Date: 2019/1/18 20:56
 * @Description:aspose word 表格合并类
 */
public class MergeCellModel implements Serializable {
    private Integer startRowIndex;

    private Integer startColumnIndex;

    private Integer endRowIndex;

    private Integer endColumnIndex;


    public MergeCellModel(Integer startRowIndex, Integer startColumnIndex, Integer endRowIndex, Integer endColumnIndex) {
        this.startRowIndex = startRowIndex;
        this.startColumnIndex = startColumnIndex;
        this.endRowIndex = endRowIndex;
        this.endColumnIndex = endColumnIndex;
    }

    private MergeCellModel(){}

    public Integer getStartRowIndex() {
        return startRowIndex;
    }

    public Integer getStartColumnIndex() {
        return startColumnIndex;
    }

    public Integer getEndRowIndex() {
        return endRowIndex;
    }

    public Integer getEndColumnIndex() {
        return endColumnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MergeCellModel that = (MergeCellModel) o;

        return new EqualsBuilder()
                .append(startRowIndex, that.startRowIndex)
                .append(startColumnIndex, that.startColumnIndex)
                .append(endRowIndex, that.endRowIndex)
                .append(endColumnIndex, that.endColumnIndex)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(startRowIndex)
                .append(startColumnIndex)
                .append(endRowIndex)
                .append(endColumnIndex)
                .toHashCode();
    }
}

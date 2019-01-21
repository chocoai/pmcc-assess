package com.copower.pmcc.assess.dto.output;

/**
 * @Auther: zch
 * @Date: 2019/1/18 20:56
 * @Description:aspose word 表格合并类
 */
public class MergeCellModel {
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
}

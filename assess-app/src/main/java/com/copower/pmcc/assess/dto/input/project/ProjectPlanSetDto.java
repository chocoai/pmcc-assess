package com.copower.pmcc.assess.dto.input.project;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/6
 * @time: 9:38
 */
public class ProjectPlanSetDto {
    private String fastFileds;

    private String fastValue;

    private Integer fastRange;

    public String getFastFileds() {
        return fastFileds;
    }

    public void setFastFileds(String fastFileds) {
        this.fastFileds = fastFileds;
    }

    public String getFastValue() {
        return fastValue;
    }

    public void setFastValue(String fastValue) {
        this.fastValue = fastValue;
    }

    public Integer getFastRange() {
        return fastRange;
    }

    public void setFastRange(Integer fastRange) {
        this.fastRange = fastRange;
    }
}

package com.copower.pmcc.assess.dto.output.report;

/**
 * Created by kings on 2018-5-21.
 */
public class SurveyCorrelationCardVo {
    private Integer id;
    private String name;
    private Boolean bisChecked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBisChecked() {
        return bisChecked;
    }

    public void setBisChecked(Boolean bisChecked) {
        this.bisChecked = bisChecked;
    }
}

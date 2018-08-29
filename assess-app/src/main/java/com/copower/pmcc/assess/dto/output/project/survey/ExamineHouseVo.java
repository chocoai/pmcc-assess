package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouse;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineHouseVo extends ExamineHouse {
    private String huxingName;
    private String certUseName;
    private String newsHuxingName;

    private String practicalUseName;

    public String getHuxingName() {
        return huxingName;
    }

    public void setHuxingName(String huxingName) {
        this.huxingName = huxingName;
    }

    public String getCertUseName() {
        return certUseName;
    }

    public void setCertUseName(String certUseName) {
        this.certUseName = certUseName;
    }

    public String getPracticalUseName() {
        return practicalUseName;
    }

    public void setPracticalUseName(String practicalUseName) {
        this.practicalUseName = practicalUseName;
    }

    public String getNewsHuxingName() {
        return newsHuxingName;
    }

    public void setNewsHuxingName(String newsHuxingName) {
        this.newsHuxingName = newsHuxingName;
    }
}

package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeJudgeObjectVo extends SchemeJudgeObject {
    private String certUseName;
    private String practicalUseName;
    private String setUseName;
    private String bestUseName;
    private String coefficient;

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

    public String getSetUseName() {
        return setUseName;
    }

    public void setSetUseName(String setUseName) {
        this.setUseName = setUseName;
    }

    public String getBestUseName() {
        return bestUseName;
    }

    public void setBestUseName(String bestUseName) {
        this.bestUseName = bestUseName;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }
}

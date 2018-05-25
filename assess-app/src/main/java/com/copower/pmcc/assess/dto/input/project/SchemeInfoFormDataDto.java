package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.SchemeInfo;

/**
 * Created by 13426 on 2018/5/25.
 */
public class SchemeInfoFormDataDto {

    private SchemeInfo princiPle;
    private SchemeInfo hypothesis;
    private SchemeInfo basis;

    public SchemeInfo getPrinciPle() {
        return princiPle;
    }

    public void setPrinciPle(SchemeInfo princiPle) {
        this.princiPle = princiPle;
    }

    public SchemeInfo getHypothesis() {
        return hypothesis;
    }

    public void setHypothesis(SchemeInfo hypothesis) {
        this.hypothesis = hypothesis;
    }

    public SchemeInfo getBasis() {
        return basis;
    }

    public void setBasis(SchemeInfo basis) {
        this.basis = basis;
    }
}

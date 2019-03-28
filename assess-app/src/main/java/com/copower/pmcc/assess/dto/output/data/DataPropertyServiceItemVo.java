package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItem;

/**
 * Created by kings on 2018-12-24.
 */
public class DataPropertyServiceItemVo extends DataPropertyServiceItem {
    private String serviceTypeName;
    private String serviceContentName;
    private String gradeEvaluationName;

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getServiceContentName() {
        return serviceContentName;
    }

    public void setServiceContentName(String serviceContentName) {
        this.serviceContentName = serviceContentName;
    }

    public String getGradeEvaluationName() {
        return gradeEvaluationName;
    }

    public void setGradeEvaluationName(String gradeEvaluationName) {
        this.gradeEvaluationName = gradeEvaluationName;
    }
}

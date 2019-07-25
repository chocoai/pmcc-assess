package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItem;

/**
 * Created by zch on 2019/7/25.
 */
public class BasicBuildingPropertyServiceItemVo extends BasicBuildingPropertyServiceItem {
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

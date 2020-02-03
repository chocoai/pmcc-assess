package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurvey;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:56
 * @Description:
 */
public class DataLocaleSurveyVo extends DataLocaleSurvey {
    private String typeName;
    private String certifyPartName;
    private String certifyPartCategoryName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCertifyPartName() {
        return certifyPartName;
    }

    public void setCertifyPartName(String certifyPartName) {
        this.certifyPartName = certifyPartName;
    }

    public String getCertifyPartCategoryName() {
        return certifyPartCategoryName;
    }

    public void setCertifyPartCategoryName(String certifyPartCategoryName) {
        this.certifyPartCategoryName = certifyPartCategoryName;
    }
}

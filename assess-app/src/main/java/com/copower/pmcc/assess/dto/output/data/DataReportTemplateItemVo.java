package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItem;

/**
 * Created by 13426 on 2018/4/27.
 */
public class DataReportTemplateItemVo extends DataReportTemplateItem {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}

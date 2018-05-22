package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.entity.BaseReportTemplate;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/22
 * @time: 9:53
 */
public class BaseReportTemplateVo extends BaseReportTemplate {
    private String typeName;//数据类型名称

    private String dataPoolTypename;//数据来源

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDataPoolTypename() {
        return dataPoolTypename;
    }

    public void setDataPoolTypename(String dataPoolTypename) {
        this.dataPoolTypename = dataPoolTypename;
    }
}

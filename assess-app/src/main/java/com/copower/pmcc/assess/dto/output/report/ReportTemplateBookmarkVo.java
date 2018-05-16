package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/8
 * @time: 18:25
 */
public class ReportTemplateBookmarkVo extends ReportTemplateBookmark {
    private String fieldsName;

    private String fieldsDisplayName;

    public String getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName) {
        this.fieldsName = fieldsName;
    }

    public String getFieldsDisplayName() {
        return fieldsDisplayName;
    }

    public void setFieldsDisplayName(String fieldsDisplayName) {
        this.fieldsDisplayName = fieldsDisplayName;
    }
}

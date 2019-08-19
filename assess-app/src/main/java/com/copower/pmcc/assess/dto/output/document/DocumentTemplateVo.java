package com.copower.pmcc.assess.dto.output.document;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;

/**
 * Created by 13426 on 2018/4/27.
 */
public class DocumentTemplateVo extends DocumentTemplate {
    private String templateTypeName;

    public String getTemplateTypeName() {
        return templateTypeName;
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }
}

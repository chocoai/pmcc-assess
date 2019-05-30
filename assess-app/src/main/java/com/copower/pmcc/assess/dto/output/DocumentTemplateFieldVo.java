package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateField;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2019-05-30
 * @time: 18:32
 */
public class DocumentTemplateFieldVo extends DocumentTemplateField {
    private String fieldTypeName;

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }
}

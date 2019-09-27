package com.copower.pmcc.assess.dto.output.project.assets;

import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;

/**
 * Created by zch on 2019-9-23.
 */
public class AssetsCustomizeDataFieldVo extends AssetsCustomizeDataField {
    private String fileViewName;
    private String jsonString;
    private String categoryName;

    private String typeCustomizeName;
    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTypeCustomizeName() {
        return typeCustomizeName;
    }

    public void setTypeCustomizeName(String typeCustomizeName) {
        this.typeCustomizeName = typeCustomizeName;
    }
}

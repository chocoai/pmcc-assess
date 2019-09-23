package com.copower.pmcc.assess.dto.output.project.assets;

import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;

/**
 * Created by zch on 2019-9-23.
 */
public class AssetsCustomizeDataFieldVo extends AssetsCustomizeDataField {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}

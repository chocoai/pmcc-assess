package com.copower.pmcc.assess.dto.input.project.assets;

import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;

/**
 * Created by zch on 2019-9-24.
 */
public class AssetsCustomizeDataFieldDto extends AssetsCustomizeDataField {
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}

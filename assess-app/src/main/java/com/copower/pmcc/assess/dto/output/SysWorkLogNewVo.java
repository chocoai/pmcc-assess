package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.bpm.api.dto.SysWorkLogVo;

import java.io.Serializable;

/**
 * Created by zch on 2020-3-17.
 */
public class SysWorkLogNewVo extends SysWorkLogVo implements Serializable {
    private String fileHtml;
    private String creatorName;
    private String createdName;

    public String getFileHtml() {
        return fileHtml;
    }

    public void setFileHtml(String fileHtml) {
        this.fileHtml = fileHtml;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }
}

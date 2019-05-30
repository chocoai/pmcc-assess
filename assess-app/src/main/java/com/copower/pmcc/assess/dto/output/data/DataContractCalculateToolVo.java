package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateTool;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:33
 * @Description:
 */
public class DataContractCalculateToolVo extends DataContractCalculateTool {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}

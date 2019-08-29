package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTaskReturnRecord;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:39
 * @Description:
 */
public class ProjectTaskReturnRecordVo extends ProjectTaskReturnRecord {
    private String fileViewName;
    private String returnPersonName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getReturnPersonName() {
        return returnPersonName;
    }

    public void setReturnPersonName(String returnPersonName) {
        this.returnPersonName = returnPersonName;
    }
}

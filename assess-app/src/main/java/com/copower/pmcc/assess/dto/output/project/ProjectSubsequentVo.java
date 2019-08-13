package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequent;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:39
 * @Description:
 */
public class ProjectSubsequentVo extends ProjectSubsequent {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}

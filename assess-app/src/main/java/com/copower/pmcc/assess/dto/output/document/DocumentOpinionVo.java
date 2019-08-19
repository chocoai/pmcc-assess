package com.copower.pmcc.assess.dto.output.document;

import com.copower.pmcc.assess.dal.basis.entity.DocumentOpinion;

/**
 * Created by 13426 on 2018/4/27.
 */
public class DocumentOpinionVo extends DocumentOpinion {
    private String userName;
    private String areaGroupName;
    private String fileViewName;
    private String reportTypeName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAreaGroupName() {
        return areaGroupName;
    }

    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }
}

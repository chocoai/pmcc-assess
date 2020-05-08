package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairs;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:19
 * @Description:
 */
public class BasicUnitStairsVo extends BasicUnitStairs {
    private String creatorName;
    private String fileViewName;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}

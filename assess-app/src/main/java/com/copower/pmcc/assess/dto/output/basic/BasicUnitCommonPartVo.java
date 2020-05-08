package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPart;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:19
 * @Description:
 */
public class BasicUnitCommonPartVo extends BasicUnitCommonPart {
    private String unitMonadName;
    private String unitQuantityName;
    private String creatorName;
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUnitMonadName() {
        return unitMonadName;
    }

    public void setUnitMonadName(String unitMonadName) {
        this.unitMonadName = unitMonadName;
    }

    public String getUnitQuantityName() {
        return unitQuantityName;
    }

    public void setUnitQuantityName(String unitQuantityName) {
        this.unitQuantityName = unitQuantityName;
    }
}

package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:04
 * @Description:
 */
public class BasicHouseRoomDecorateVo extends BasicHouseRoomDecorate {
    private String materialName;
    private String constructionTechnologyName;
    private String partName;
    private String materialPriceName;
    private String fileViewName;
    private String creatorName;
    private String levelName;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getConstructionTechnologyName() {
        return constructionTechnologyName;
    }

    public void setConstructionTechnologyName(String constructionTechnologyName) {
        this.constructionTechnologyName = constructionTechnologyName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getMaterialPriceName() {
        return materialPriceName;
    }

    public void setMaterialPriceName(String materialPriceName) {
        this.materialPriceName = materialPriceName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}

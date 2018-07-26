package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorate;

/**
 * @Auther: zch
 * @Date: 2018/7/25 10:29
 * @Description:
 */
public class ExamineHouseRoomDecorateVo extends ExamineHouseRoomDecorate {
    private String partName;

    private String materialName;

    private String materialPriceName;

    private String constructionTechnologyName;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialPriceName() {
        return materialPriceName;
    }

    public void setMaterialPriceName(String materialPriceName) {
        this.materialPriceName = materialPriceName;
    }

    public String getConstructionTechnologyName() {
        return constructionTechnologyName;
    }

    public void setConstructionTechnologyName(String constructionTechnologyName) {
        this.constructionTechnologyName = constructionTechnologyName;
    }
}

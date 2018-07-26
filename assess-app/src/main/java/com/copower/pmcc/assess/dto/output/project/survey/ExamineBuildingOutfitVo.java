package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfit;

/**
 * @Auther: zch
 * @Date: 2018/7/24 15:16
 * @Description:
 */
public class ExamineBuildingOutfitVo extends ExamineBuildingOutfit {
    private String decorationPartName;

    private String decoratingMaterialName;

    private String materialPriceName;

    private String constructionTechnologyName;

    public String getDecorationPartName() {
        return decorationPartName;
    }

    public void setDecorationPartName(String decorationPartName) {
        this.decorationPartName = decorationPartName;
    }

    public String getDecoratingMaterialName() {
        return decoratingMaterialName;
    }

    public void setDecoratingMaterialName(String decoratingMaterialName) {
        this.decoratingMaterialName = decoratingMaterialName;
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

package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingFunction;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:36
 * @Description:
 */
public class BasicBuildingFunctionVo extends BasicBuildingFunction {
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

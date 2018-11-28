package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunction;

/**
 * @Auther: zch
 * @Date: 2018/9/18 15:42
 * @Description:
 */
public class CaseBuildingFunctionVo extends CaseBuildingFunction {
    private String typeName;
    private String decorationPartName;

    private String decoratingMaterialName;

    private String materialPriceName;

    private String constructionTechnologyName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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

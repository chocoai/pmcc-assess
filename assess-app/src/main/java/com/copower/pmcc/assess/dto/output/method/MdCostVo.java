package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCost;
import com.copower.pmcc.assess.dal.basis.entity.MdCostBuilding;

import java.io.Serializable;

/**
 * Created by zch on 2019-8-22.
 */
public class MdCostVo extends MdCost implements Serializable{
    private MdCostConstructionVo mdCostConstruction = new MdCostConstructionVo();
    private MdCostBuilding mdCostBuilding = new MdCostBuilding();

    public MdCostConstructionVo getMdCostConstruction() {
        return mdCostConstruction;
    }

    public void setMdCostConstruction(MdCostConstructionVo mdCostConstruction) {
        this.mdCostConstruction = mdCostConstruction;
    }

    public MdCostBuilding getMdCostBuilding() {
        return mdCostBuilding;
    }

    public void setMdCostBuilding(MdCostBuilding mdCostBuilding) {
        this.mdCostBuilding = mdCostBuilding;
    }
}

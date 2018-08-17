package com.copower.pmcc.assess.dto.input.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostBuilding;
import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/16 18:37
 * @Description:
 */
public class MdMarketCostDto implements Serializable {
    private List<SchemeSupportInfo> supportInfoList;
    private MdCostBuilding mdCostBuilding;
    private MdCostConstruction mdCostConstruction;
    private Integer id;

    public List<SchemeSupportInfo> getSupportInfoList() {
        return supportInfoList;
    }

    public void setSupportInfoList(List<SchemeSupportInfo> supportInfoList) {
        this.supportInfoList = supportInfoList;
    }

    public MdCostBuilding getMdCostBuilding() {
        return mdCostBuilding;
    }

    public void setMdCostBuilding(MdCostBuilding mdCostBuilding) {
        this.mdCostBuilding = mdCostBuilding;
    }

    public MdCostConstruction getMdCostConstruction() {
        return mdCostConstruction;
    }

    public void setMdCostConstruction(MdCostConstruction mdCostConstruction) {
        this.mdCostConstruction = mdCostConstruction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

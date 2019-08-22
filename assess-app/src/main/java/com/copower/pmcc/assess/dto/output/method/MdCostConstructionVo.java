package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2019-8-22.
 */
public class MdCostConstructionVo extends MdCostConstruction {
    private List<KeyValueDto> constructionInstallationEngineeringFeeDtos = new ArrayList<KeyValueDto>(0) ;

    public List<KeyValueDto> getConstructionInstallationEngineeringFeeDtos() {
        return constructionInstallationEngineeringFeeDtos;
    }

    public void setConstructionInstallationEngineeringFeeDtos(List<KeyValueDto> constructionInstallationEngineeringFeeDtos) {
        this.constructionInstallationEngineeringFeeDtos = constructionInstallationEngineeringFeeDtos;
    }
}

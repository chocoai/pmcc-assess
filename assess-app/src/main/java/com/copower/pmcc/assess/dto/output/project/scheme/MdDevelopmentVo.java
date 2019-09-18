package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2019/7/8.
 */
public class MdDevelopmentVo extends MdDevelopment {

    private String parcelSettingOuterName;

    private String parcelSettingInnerName;


    private List<KeyValueDto> constructionInstallationEngineeringFeeDtos = new ArrayList<KeyValueDto>(0) ;



    public List<KeyValueDto> getConstructionInstallationEngineeringFeeDtos() {
        return constructionInstallationEngineeringFeeDtos;
    }

    public void setConstructionInstallationEngineeringFeeDtos(List<KeyValueDto> constructionInstallationEngineeringFeeDtos) {
        this.constructionInstallationEngineeringFeeDtos = constructionInstallationEngineeringFeeDtos;
    }

    public String getParcelSettingOuterName() {
        return parcelSettingOuterName;
    }

    public void setParcelSettingOuterName(String parcelSettingOuterName) {
        this.parcelSettingOuterName = parcelSettingOuterName;
    }

    public String getParcelSettingInnerName() {
        return parcelSettingInnerName;
    }

    public void setParcelSettingInnerName(String parcelSettingInnerName) {
        this.parcelSettingInnerName = parcelSettingInnerName;
    }
}

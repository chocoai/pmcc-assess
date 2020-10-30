package com.copower.pmcc.assess.common.enums.aviator;

import java.io.Serializable;

/**
 * 模板 key 枚举
 */
public enum AviatorTemplateEnum implements Serializable {
    land_street_location_enum("临街位置", "street.location", "street_location"), //com.copower.pmcc.assess.service.aviator.AviatorTemplateRunService.land_street_location
    land_parcel_area_enum("宗地面积", "parcel.area", "parcel_area"), //com.copower.pmcc.assess.service.aviator.AviatorTemplateRunService.oneVarRun
    land_geological_conditions_enum("地质条件", "geological.conditions", "geological_conditions"),//com.copower.pmcc.assess.service.aviator.AviatorTemplateRunService.land_geological_conditions
    land_planeness_enum("地形", "geological.conditions", "planeness"), //com.copower.pmcc.assess.service.aviator.AviatorTemplateRunService.oneVarRun
    land_topographicTerrain_enum("地势", "topographicTerrain", "topographicTerrain"),
    land_developmentDegree_enum("开发程度", "developmentDegree", "developmentDegree"),
    land_land_rights_status_enum("土地权利状况", "land.rights.status", "land_rights_status"),
    land_land_constraints_enum("规划限制条件", "PLANNING_CONSTRAINTS", "land_constraints"),
    land_remainingYears_enum("土地剩余年限", "remainingYears"),
    land_summary_individual_factors_enum("个别因素总结", "land.summary.individual.factors"), //com.copower.pmcc.assess.service.aviator.AviatorTemplateRunService.land_summary_individual_factors
    ;
    private String templateName; //模板名称 不确保唯一
    private String key; //模板变量名称 确保唯一
    private String variable; //脚本中变量名称  不确保唯一

    AviatorTemplateEnum(String templateName, String key, String variable) {
        this.templateName = templateName;
        this.key = key;
        this.variable = variable;
    }

    AviatorTemplateEnum(String key, String variable) {
        this.key = key;
        this.variable = variable;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
}

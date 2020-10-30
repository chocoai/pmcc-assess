package com.copower.pmcc.assess.service.aviator;

import com.copower.pmcc.assess.common.enums.aviator.AviatorTemplateEnum;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseFaceStreetVo;
import com.copower.pmcc.assess.service.ScriptTemplateService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表达式 脚本调用 bean
 */
@Component
public class AviatorTemplateRunService {

    @Autowired
    private ScriptTemplateService scriptTemplateService;

    /**
     * @param basicHouseFaceStreetVoList 临街（路）状况
     * @param distance                   与地块距离（较远、适中、较近）
     * @return
     * @throws Exception
     */
    public String land_street_location(List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList, String distance) throws Exception {
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_street_location_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("streetRoad", basicHouseFaceStreetVoList);
        map.put("distance", distance);
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 地质条件
     *
     * @param holdOn
     * @param bearingCapacity
     * @param ph
     * @param contaminated
     * @return
     * @throws Exception
     */
    public String land_geological_conditions(String holdOn, String bearingCapacity, String ph, String contaminated) throws Exception {
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_geological_conditions_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("holdOn", holdOn);
        map.put("ph", ph);
        map.put("bearingCapacity", bearingCapacity);
        map.put("contaminated", contaminated);
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 土地开发程度
     *
     * @param infrastructureCompleteness 基础设施完备度
     * @param developmentDegree          土地开发程度
     * @return
     * @throws Exception
     */
    public String land_developmentDegree(String infrastructureCompleteness, String developmentDegree) throws Exception {
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_developmentDegree_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("infrastructureCompleteness", infrastructureCompleteness);
        map.put("developmentDegree", developmentDegree);
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 土地权利状况
     *
     * @param ownership       权力人
     * @param rightType       权利类型
     * @param rightNature     权利性质
     * @param landUseType     土地用途类型
     * @param landUseCategory 土地用途类别
     * @param otherRight      他项权力 map value 组装他项权利类型名称 ,key组装id
     * @return
     * @throws Exception
     */
    public String land_land_rights_status(String ownership, String rightType, String rightNature, String landUseType, String landUseCategory, Map<Integer, String> otherRight) throws Exception {
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_land_rights_status_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("ownership", ownership);
        map.put("rightType", rightType);
        map.put("rightNature", rightNature);
        map.put("landUseType", landUseType);
        map.put("landUseCategory", landUseCategory);
        map.put("otherRight", otherRight);
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 规划限制条件
     *
     * @param limitLandMap     地块限制条件
     * @param limitLocationMap 区域限制条件
     * @return
     * @throws Exception
     */
    public String land_land_constraints(Map<String, Object> limitLandMap, Map<String, Object> limitLocationMap) throws Exception {
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_land_constraints_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("limitLandMap", limitLandMap);
        map.put("limitLocationMap", limitLocationMap);
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 土地剩余年限比值
     *
     * @param remainingYear 土地剩余年限
     * @param useYear       法定用途年限
     * @return
     * @throws Exception
     */
    public String land_remainingYear(Date remainingYear, Date useYear) throws Exception {
        if (remainingYear == null || useYear == null) {
            return "";
        }
        double m = DateUtils.getYear(remainingYear) / DateUtils.getYear(useYear);
        m = m * 100;
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_remainingYears_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("remainingYear", m);
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 个别因素总结 结论
     * @param land_rights_status
     * @param land_constraints
     * @param developmentDegree
     * @param topographicTerrain
     * @param planeness
     * @param geological_conditions
     * @param parcel_area
     * @param street_location
     * @return
     * @throws Exception
     */
    public String land_summary_individual_factors(String land_rights_status,String land_constraints,String developmentDegree,String topographicTerrain,String planeness,String geological_conditions,String parcel_area,String street_location)throws Exception{
        AviatorTemplateEnum anEnum = AviatorTemplateEnum.land_summary_individual_factors_enum;
        Map<String, Object> map = new HashMap<>();
        map.put("land_rights_status", land_rights_status); //土地权利状况 属于参数 某种方法得出的结论
        map.put("land_constraints", land_constraints); //规划限制条件 属于参数 某种方法得出的结论
        map.put("developmentDegree", developmentDegree); //开发程度 属于参数 某种方法得出的结论
        map.put("topographicTerrain", topographicTerrain); //地势 属于参数 某种方法得出的结论
        map.put("planeness", planeness); // 地形 属于参数 某种方法得出的结论
        map.put("geological_conditions", geological_conditions); // 地质条件 属于参数 某种方法得出的结论
        map.put("parcel_area", parcel_area); // 宗地面积 属于参数 某种方法得出的结论
        map.put("street_location", street_location); // 临街位置 属于参数 某种方法得出的结论
        String value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }

    /**
     * 一个参数情况
     *
     * @param param
     * @param anEnum
     * @return
     * @throws Exception
     */
    public String oneVarRun(String param, AviatorTemplateEnum anEnum) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String value = "";
        String varName = "";//脚本 函数里面的参数名称
        switch (anEnum) {
            case land_parcel_area_enum: {
                varName = "parcelStandard";
                break;
            }
            case land_planeness_enum: {
                varName = "planeness";
                break;
            }
            case land_topographicTerrain_enum: {
                varName = "topographicTerrain";
                break;
            }

            default:
                break;
        }
        if (StringUtils.isNotBlank(varName)) {
            map.put(varName, param);
        }
        value = scriptTemplateService.executeScriptTemplate(anEnum.getKey(), map);
        return value;
    }


}

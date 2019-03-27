package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 土地实体信息
 */
@Service
public class GenerateLandEntityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String error = "无";

    /**
     * 综上所述
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getContent(BasicApply basicApply, SchemeJudgeObject schemeJudgeObject) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        stringBuilder.append(schemeJudgeObject.getNumber());
        if (schemeJudgeObject.getSplitNumber() != null){
            stringBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
        }
        stringBuilder.append("号");
        if (StringUtils.isNotBlank(landStateVo.getEastTo()) && StringUtils.isNotBlank(landStateVo.getWestTo())
                && StringUtils.isNotBlank(landStateVo.getSouthTo()) && StringUtils.isNotBlank(landStateVo.getNorthTo())) {
            stringBuilder.append("四至清淅").append(",");
        }
        if (StringUtils.isNotBlank(landStateVo.getLandUseTypeName())) {
            stringBuilder.append("用途明确").append(",");
        }
        if (StringUtils.isNotBlank(landStateVo.getLandArea())) {
            stringBuilder.append("面积确定").append(",");
        }
        //地形
        final boolean planEness = Objects.equal("高低不平不便布局", landStateVo.getPlanenessName());
        //地势
        final boolean topographicTerrain = Objects.equal("地势低排水较差", landStateVo.getTopographicTerrainName());
        //形状
        final boolean shapeState = Objects.equal("方便利用", landStateVo.getShapeStateName());
        String content = null;
        if (!planEness && !topographicTerrain && shapeState) {
            content = "有利用于提升估价对象价值";
        }
        if (!planEness && !topographicTerrain && !shapeState) {
            content = "不太利于提升估价对象价值";
        }
        if (!planEness && topographicTerrain && shapeState) {
            content = "相对有利于提升估价对象价值";
        }
        if (!planEness && topographicTerrain && !shapeState) {
            content = "不利于提升估价对象价值";
        }
        if (planEness && topographicTerrain && shapeState) {
            content = "将一定程度限制估价对象价值";
        }
        if (planEness && topographicTerrain && !shapeState) {
            content = "将严重限制估价对象价值";
        }
        if (StringUtils.isEmpty(content)) {
            content = "";
        }
        if (!Objects.equal(landStateVo.getDevelopmentDegreeName(), "熟地")) {
            stringBuilder.append(content).append(",");
            stringBuilder.append("对估价对象价值产生较有利的影响。");
        }else {
            stringBuilder.append("。");
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append(error);
        }
        return stringBuilder.toString();
    }


    /**
     * 用途
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getLandUse(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return String.format("%s", landStateVo.getLandUseTypeName());
    }

    /**
     * 形状
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getShapeState(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return String.format("%s", landStateVo.getShapeStateName());
    }

    /**
     * 基础设施完备度
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getInfrastructureCompleteness(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return String.format("基础设施完备度:%s", landStateVo.getInfrastructureCompletenessName());
    }

    /**
     * 开发程度
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getDevelopmentDegree(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        if (Objects.equal(landStateVo.getDevelopmentDegreeName(), "熟地")){
            return String.format("%s", landStateVo.getDevelopmentDegreeName());
        }else {
            return "空";
        }
    }

    /**
     * 土壤与地质
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getSoil(BasicApply basicApply) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        List<String> typeList = Lists.newArrayList("林地", "园地", "水域", "耕地", "草地");
        stringBuilder.append("土壤").append(StringUtils.isNotBlank(landStateVo.getContaminated()) ? landStateVo.getContaminated() : error);
        stringBuilder.append(StringUtils.isNotBlank(landStateVo.getPh()) ? landStateVo.getPh() : error);
        if (typeList.stream().anyMatch(s -> Objects.equal(s, landStateVo.getLandUseTypeName()))) {
            stringBuilder.append(StringUtils.isNotBlank(landStateVo.getFertility()) ? landStateVo.getFertility() : error);
        } else {
            stringBuilder.append(",地质稳定性").append(StringUtils.isNotBlank(landStateVo.getHoldOn()) ? landStateVo.getHoldOn() : error).append("、");
            stringBuilder.append("承载力");
            stringBuilder.append(StringUtils.isNotBlank(landStateVo.getBearingCapacity()) ? landStateVo.getBearingCapacity() : error);
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append(error);
        }
        return stringBuilder.toString();
    }

    /**
     * 地势
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTopographicTerrain(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return String.format("%s", landStateVo.getTopographicTerrainName());
    }

    /**
     * 土地面积
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getLandArea(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return String.format("%s ㎡", landStateVo.getLandArea());
    }

    /**
     * 土地名称
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getLandName(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        return landStateVo.getName();
    }

    /**
     * 四至
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String fourTheFor(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        StringBuilder stringBuilder = new StringBuilder(8);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        stringBuilder.append(String.format("东至:%s", StringUtils.isNotBlank(landStateVo.getEastTo()) ? landStateVo.getEastTo() : error));
        stringBuilder.append(String.format("南至:%s", StringUtils.isNotBlank(landStateVo.getEastTo()) ? landStateVo.getSouthTo() : error));
        stringBuilder.append(String.format("西至:%s", StringUtils.isNotBlank(landStateVo.getEastTo()) ? landStateVo.getWestTo() : error));
        stringBuilder.append(String.format("北至:%s", StringUtils.isNotBlank(landStateVo.getEastTo()) ? landStateVo.getNorthTo() : error));
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append(error);
        }
        return stringBuilder.toString();
    }
}

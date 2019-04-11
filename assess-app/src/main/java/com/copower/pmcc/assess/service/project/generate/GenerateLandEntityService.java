package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 土地实体信息
 */
@Service
public class GenerateLandEntityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String error = "无";
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;

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
        stringBuilder.append(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()).toString()).append("号");
        int startLength = stringBuilder.toString().length();
        boolean flag = Lists.newArrayList(landStateVo.getEastTo(), landStateVo.getWestTo(), landStateVo.getSouthTo(), landStateVo.getNorthTo()).stream().filter(s -> StringUtils.isNotBlank(s)).count() >= 4;
        if (flag) {
            stringBuilder.append("四至清淅");
        }
        if (StringUtils.isNotBlank(landStateVo.getLandUseTypeName())) {
            if (flag) {
                stringBuilder.append(",");
            }
            stringBuilder.append("用途明确");
        }
        if (StringUtils.isNotBlank(landStateVo.getLandArea())) {
            if (StringUtils.isNotBlank(landStateVo.getLandArea())) {
                stringBuilder.append(",");
            }
            stringBuilder.append("面积确定");
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
            if (stringBuilder.toString().length() != startLength) {
                stringBuilder.append(",");
            }
            stringBuilder.append(content).append(",");
            stringBuilder.append("对估价对象价值产生较有利的影响。");
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append(error);
        }
        return generateCommonMethod.trim(stringBuilder.toString());
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
        String v = landStateVo.getLandUseTypeName();
        return StringUtils.isNotBlank(v) ? v : error;
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
        String v = null;
        if (StringUtils.isNotBlank(landStateVo.getShapeStateName())) {
            v = String.format("%s", landStateVo.getShapeStateName());
        } else {
            v = error;
        }
        return v;
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
        return StringUtils.isNotBlank(landStateVo.getInfrastructureCompletenessName()) ? landStateVo.getInfrastructureCompletenessName() : error;
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
        if (Objects.equal(landStateVo.getDevelopmentDegreeName(), "熟地")) {
            if (StringUtils.isNotBlank(landStateVo.getDevelopmentDegreeContent())) {
                List<String> stringList = Lists.newArrayList(landStateVo.getDevelopmentDegreeContent().split(","));
                if (CollectionUtils.isNotEmpty(stringList)) {
                    Set<String> stringSet = Sets.newHashSet();
                    stringList.stream().forEach(s -> {
                        stringSet.add(baseDataDicService.getNameById(s));
                    });
                    if (CollectionUtils.isNotEmpty(stringSet)){
                        return StringUtils.join(stringSet,"、");
                    }
                }
            }
        }
        return error;
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
        if (StringUtils.isNotBlank(landStateVo.getContaminated())) {
            stringBuilder.append("土壤").append(landStateVo.getContaminatedName());
        }
        if (StringUtils.isNotBlank(landStateVo.getPh())) {
            stringBuilder.append("酸碱度").append(landStateVo.getPhName());
        }
        if (typeList.stream().anyMatch(s -> Objects.equal(s, landStateVo.getLandUseTypeName()))) {
            if (StringUtils.isNotBlank(landStateVo.getFertility())) {
                stringBuilder.append(landStateVo.getFertility());
            }
        } else {
            if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
                stringBuilder.append("，");
            }
            if (StringUtils.isNotBlank(landStateVo.getHoldOn())) {
                stringBuilder.append("地质稳定性").append(landStateVo.getHoldOnName());
            }
            if (StringUtils.isNotBlank(landStateVo.getBearingCapacity())) {
                stringBuilder.append("承载力");
                stringBuilder.append(landStateVo.getBearingCapacityName());
            }
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
        return StringUtils.isNotBlank(landStateVo.getTopographicTerrainName()) ? landStateVo.getTopographicTerrainName() : error;
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
        String value = landStateVo.getLandArea();
        if (StringUtils.isNotBlank(value)) {
            return String.format("%s ㎡", value);
        } else {
            return error;
        }
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
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        stringSet.add(landStateVo.getEastTo());
        stringSet.add(landStateVo.getSouthTo());
        stringSet.add(landStateVo.getWestTo());
        stringSet.add(landStateVo.getNorthTo());
        String v = StringUtils.join(stringSet.stream().filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList()), "、");
        return StringUtils.isNotBlank(v) ? v : error;
    }
}

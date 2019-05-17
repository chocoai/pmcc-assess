package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
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
    private final String error = "";
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
    public String getContent(BasicApply basicApply) throws Exception {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        if (Lists.newArrayList(landStateVo.getEastTo(), landStateVo.getWestTo(), landStateVo.getSouthTo(), landStateVo.getNorthTo()).stream().filter(s -> StringUtils.isNotBlank(s)).count() >= 4){
            linkedHashSet.add("四至清淅");
        }
        if (StringUtils.isNotBlank(landStateVo.getLandUseTypeName())) {
            linkedHashSet.add("用途明确");
        }
        if (StringUtils.isNotBlank(landStateVo.getLandArea())) {
            linkedHashSet.add("面积确定");
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
        if (Objects.equal(landStateVo.getDevelopmentDegreeName(), "熟地")) {
            if (StringUtils.isNotEmpty(content)){
                linkedHashSet.add(content);
            }
            linkedHashSet.add("对估价对象价值产生较有利的影响。");
        }
        return generateCommonMethod.trim(StringUtils.join(linkedHashSet,"，"));
    }


    /**
     * 用途
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getLandUse(BasicEstateLandStateVo landStateVo) throws Exception {
        String v = landStateVo.getLandUseTypeName();
        return StringUtils.isNotBlank(v) ? v : error;
    }

    /**
     * 形状
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getShapeState(BasicEstateLandStateVo landStateVo) throws Exception {
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
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getInfrastructureCompleteness(BasicEstateLandStateVo landStateVo) throws Exception {
        return StringUtils.isNotBlank(landStateVo.getInfrastructureCompletenessName()) ? landStateVo.getInfrastructureCompletenessName() : error;
    }

    /**
     * 开发程度
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getDevelopmentDegree(BasicEstateLandStateVo landStateVo) throws Exception {
        if (Objects.equal(landStateVo.getDevelopmentDegreeName(), "熟地")) {
            if (StringUtils.isNotBlank(landStateVo.getDevelopmentDegreeContent())) {
                List<String> stringList = Lists.newArrayList(landStateVo.getDevelopmentDegreeContent().split(","));
                if (CollectionUtils.isNotEmpty(stringList)) {
                    Set<String> stringSet = Sets.newHashSet();
                    stringList.stream().forEach(s -> {
                        stringSet.add(baseDataDicService.getNameById(s));
                    });
                    if (CollectionUtils.isNotEmpty(stringSet)) {
                        return StringUtils.join(stringSet, "、");
                    }
                }
            }
        }else {
            if (StringUtils.isNotBlank(landStateVo.getDevelopmentDegreeRemark())){
                return landStateVo.getDevelopmentDegreeRemark();
            }
        }
        return error;
    }

    /**
     * 土壤与地质
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getSoil(BasicEstateLandStateVo landStateVo) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
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
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getTopographicTerrain(BasicEstateLandStateVo landStateVo) throws Exception {
        return StringUtils.isNotBlank(landStateVo.getTopographicTerrainName()) ? landStateVo.getTopographicTerrainName() : error;
    }

    /**
     * 土地面积
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getLandArea(BasicEstateLandStateVo landStateVo) throws Exception {
        String value = landStateVo.getLandArea();
        if (StringUtils.isNotBlank(value)) {
            return String.format("%s㎡", value);
        } else {
            return error;
        }
    }

    /**
     * 土地名称
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String getLandName(BasicEstateLandStateVo landStateVo) throws Exception {
        return landStateVo.getName();
    }

    /**
     * 四至
     *
     * @param landStateVo
     * @return
     * @throws Exception
     */
    public String fourTheFor(BasicEstateLandStateVo landStateVo) throws Exception {
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        if (StringUtils.isNotBlank(landStateVo.getEastTo())) {
            stringSet.add(String.format("%s%s","东至",landStateVo.getEastTo()));
        }
        if (StringUtils.isNotBlank(landStateVo.getSouthTo())) {
            stringSet.add(String.format("%s%s","南至",landStateVo.getSouthTo()));
        }
        if (StringUtils.isNotBlank(landStateVo.getWestTo())) {
            stringSet.add(String.format("%s%s","西至",landStateVo.getWestTo()));
        }
        if (StringUtils.isNotBlank(landStateVo.getNorthTo())) {
            stringSet.add(String.format("%s%s","北至",landStateVo.getNorthTo()));
        }
        String v = StringUtils.join(stringSet.stream().filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList()), "、");
        return StringUtils.isNotBlank(v) ? v : error;
    }
}

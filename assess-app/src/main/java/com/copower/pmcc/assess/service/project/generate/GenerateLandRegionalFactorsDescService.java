package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * 土地报告 区域因素描述
 */
@Service
public class GenerateLandRegionalFactorsDescService {
    @Autowired
    private BaseService baseService;
    @Autowired
    private BasicApplyService basicApplyService;
    private static final String defaultString = "无";
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;

    /**
     * 区域位置
     *
     * @return
     * @throws Exception
     */
    public String getRegionalLocation(BasicEstate basicEstate) throws Exception {
        BasicEstateVo basicEstateVo = basicEstateService.getBasicEstateVo(basicEstate);
        DataBlock dataBlock = null;
        if (basicEstate.getBlockId() != null) {
            dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
        } else {
            List<DataBlock> dataBlockList = dataBlockService.getDataBlockListByArea(basicEstateVo.getProvince(), basicEstateVo.getCity(), basicEstateVo.getCity());
            if (CollectionUtils.isNotEmpty(dataBlockList)){
                dataBlock = dataBlockList.get(0);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(basicEstateVo.getProvinceName()).append(basicEstateVo.getCityName()).append(basicEstateVo.getDistrictName());
        if (dataBlock != null) {
            stringBuilder.append(dataBlock.getName()).append(dataBlock.getRemark());
        }
        return stringBuilder.toString();
    }

    /**
     * 产业聚集度
     *
     * @param judgeObjectList
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public String getIndustrialAgglomeration(List<SchemeJudgeObject> judgeObjectList, BasicEstate basicEstate) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(basicEstate.getId());
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(applyBatch);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(basicEstate.getName());
        KeyValueDto keyValueDto = new KeyValueDto("text-indent", "2em");
        //产业聚集度
        if (applyBatch.getType().equals(BasicApplyTypeEnum.INDUSTRY.getId())) {
            List<BasicMatchingMaterial> basicMatchingMaterialList = basicExamineHandle.getBasicMatchingMaterialList();
            if (CollectionUtils.isNotEmpty(basicMatchingMaterialList)) {
                BasicMatchingMaterial basicMatchingMaterial = basicMatchingMaterialList.get(0);
                stringBuilder.append(basicMatchingMaterial.getName());
                stringBuilder.append(baseDataDicService.getNameById(basicMatchingMaterial.getCategory()));
                stringBuilder.append(baseDataDicService.getNameById(basicMatchingMaterial.getScale())).append("规模");
                stringBuilder.append(baseDataDicService.getNameById(basicMatchingMaterial.getDistance())).append("距离\n");
            }
        }
        //商服区级别及商服繁华度
        if (applyBatch.getType().equals(BasicApplyTypeEnum.RESIDENCE.getId())) {
            List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = basicExamineHandle.getBasicMatchingLeisurePlaceList();
            List<BasicMatchingLeisurePlace> matchingLeisurePlaceList = basicMatchingLeisurePlaceList.stream().filter(basicMatchingLeisurePlace -> Objects.equal(basicMatchingLeisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())).collect(Collectors.toList());
            List<BasicMatchingLeisurePlace> matchingRecreationList = basicMatchingLeisurePlaceList.stream().filter(basicMatchingLeisurePlace -> Objects.equal(basicMatchingLeisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())).collect(Collectors.toList());
            List<BasicMatchingLeisurePlace> matchingRestaurantList = basicMatchingLeisurePlaceList.stream().filter(basicMatchingLeisurePlace -> Objects.equal(basicMatchingLeisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(matchingLeisurePlaceList)) {
                List<String> stringList = new ArrayList<>();
                for (BasicMatchingLeisurePlace obj : matchingLeisurePlaceList) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(obj.getName());
                    builder.append(baseDataDicService.getNameById(obj.getCategory()));
                    builder.append(baseDataDicService.getNameById(obj.getGrade()));
                    builder.append(baseDataDicService.getNameById(obj.getDistance()));
                    stringList.add(builder.toString());
                }
                String html = String.format("购物信息:%s", StringUtils.join(stringList, "；"));
                stringBuilder.append(AsposeUtils.getWarpCssHtml(html, keyValueDto.getKey(), keyValueDto.getValue()));
            }
            if (CollectionUtils.isNotEmpty(matchingRecreationList)) {
                List<String> stringList = new ArrayList<>();
                for (BasicMatchingLeisurePlace obj : matchingRecreationList) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(obj.getName());
                    builder.append(baseDataDicService.getNameById(obj.getCategory()));
                    builder.append(baseDataDicService.getNameById(obj.getGrade()));
                    builder.append(baseDataDicService.getNameById(obj.getDistance()));
                    stringList.add(builder.toString());
                }
                String html = String.format("休闲娱乐:%s", StringUtils.join(stringList, "；"));
                stringBuilder.append(AsposeUtils.getWarpCssHtml(html, keyValueDto.getKey(), keyValueDto.getValue()));
            }
            if (CollectionUtils.isNotEmpty(matchingRestaurantList)) {
                List<String> stringList = new ArrayList<>();
                for (BasicMatchingLeisurePlace obj : matchingRestaurantList) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(obj.getName());
                    builder.append(baseDataDicService.getNameById(obj.getCategory()));
                    builder.append(baseDataDicService.getNameById(obj.getGrade()));
                    builder.append(baseDataDicService.getNameById(obj.getDistance()));
                    stringList.add(builder.toString());
                }
                String html = String.format("餐饮:%s", StringUtils.join(stringList, "；"));
                stringBuilder.append(AsposeUtils.getWarpCssHtml(html, keyValueDto.getKey(), keyValueDto.getValue()));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 交通条件
     *
     * @param basicEstate
     * @param trafficTypeEnum
     * @return
     * @throws Exception
     */
    public String getTrafficConditions(BasicEstate basicEstate, ExamineMatchingTrafficTypeEnum trafficTypeEnum) throws Exception {
        //主干道信息  交通枢纽信息  主要转换互通桥信息   停车场 地铁信息  公交信息
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(basicEstate.getId());
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(applyBatch);
        StringBuilder stringBuilder = new StringBuilder();
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicExamineHandle.getBasicMatchingTrafficList();
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            List<String> stringList = new ArrayList<>();
            for (BasicMatchingTrafficVo obj : basicMatchingTrafficList) {
                StringBuilder builder = new StringBuilder();
                builder.append(obj.getName());
                builder.append(baseDataDicService.getNameById(obj.getNature()));
                if (StringUtils.isNotBlank(obj.getVisitorsFlowrateName())) {
                    builder.append(obj.getVisitorsFlowrateName());
                }
                if (StringUtils.isNotBlank(obj.getTrafficFlowName())) {
                    builder.append(obj.getTrafficFlowName());
                }
                if (StringUtils.isNotBlank(obj.getPositionName())) {
                    builder.append(obj.getPositionName());
                }
                if (StringUtils.isNotBlank(obj.getLineName())) {
                    builder.append(obj.getLineName());
                }
                if (StringUtils.isNotBlank(obj.getLimitSpeialName())) {
                    builder.append(obj.getLimitSpeialName());
                }
                builder.append(baseDataDicService.getNameById(obj.getDistance()));
                stringList.add(builder.toString());
            }
            stringBuilder.append(StringUtils.join(stringList, "；"));
        }
        return stringBuilder.toString();
    }

    /**
     * 基础供应信息
     * @param basicEstate
     * @param supplyEnumType
     * @return
     */
    public String getExamineEstateSupplyValue(BasicEstate basicEstate, ExamineEstateSupplyEnumType supplyEnumType){
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(basicEstate.getId());
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(applyBatch);
        StringBuilder stringBuilder = new StringBuilder();
        List<BasicEstateSupply> basicEstateSupplyList = basicExamineHandle.getBasicEstateSupplyList();
        List<BasicEstateSupply> basicEstateSupplies = basicEstateSupplyList.stream().filter(obj -> obj.getType().equals(supplyEnumType.getName())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicEstateSupplies)) {
            List<String> stringList = new ArrayList<>();
            for (BasicEstateSupply obj:basicEstateSupplies){
                StringBuilder builder = new StringBuilder();
                builder.append(obj.getName());
                builder.append(baseDataDicService.getNameById(obj.getLineGrade()));
                builder.append(baseDataDicService.getNameById(obj.getReputation()));
                builder.append(baseDataDicService.getNameById(obj.getGrade()));
                stringList.add(builder.toString());
            }
            stringBuilder.append(StringUtils.join(stringList, "；"));
        }
        return stringBuilder.toString();
    }

    public Map<String,String> getLivingFacilities(BasicEstate basicEstate){
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(basicEstate.getId());
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(applyBatch);
        Map<String,String> map = new HashMap<>() ;
        //“金融服务信息”、“教育条件信息”、“医养条件信息”
        List<BasicMatchingMedical> basicMatchingMedicalList = basicExamineHandle.getBasicMatchingMedicalList();
        List<BasicMatchingFinanceVo> basicMatchingFinanceList = basicExamineHandle.getBasicMatchingFinanceList();
        List<BasicMatchingEducation> basicMatchingEducationList = basicExamineHandle.getBasicMatchingEducatioListn();
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)){
            List<String> stringList = new ArrayList<>();
            for (BasicMatchingMedical obj:basicMatchingMedicalList){
                StringBuilder builder = new StringBuilder();
                builder.append(obj.getOrganizationName());
                builder.append(baseDataDicService.getNameById(obj.getOrganizationLevel()));
                builder.append(baseDataDicService.getNameById(obj.getDistance()));
                stringList.add(builder.toString());
            }
            map.put(BasicMatchingMedical.class.getSimpleName(),StringUtils.join(stringList, "；")) ;
        }
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceList)) {
            List<String> stringList = new ArrayList<>();
            for (BasicMatchingFinanceVo obj:basicMatchingFinanceList){
                StringBuilder builder = new StringBuilder();
                builder.append(obj.getName());
                builder.append(baseDataDicService.getNameById(obj.getCategory()));
                builder.append(baseDataDicService.getNameById(obj.getDistance()));
                stringList.add(builder.toString());
            }
            map.put(BasicMatchingFinance.class.getSimpleName(),StringUtils.join(stringList, "；")) ;
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
            List<String> stringList = new ArrayList<>();
            for (BasicMatchingEducation obj:basicMatchingEducationList){
                StringBuilder builder = new StringBuilder();
                builder.append(obj.getSchoolName());
                builder.append(baseDataDicService.getNameById(obj.getSchoolNature()));
                builder.append(baseDataDicService.getNameById(obj.getSchoolLevel()));
                builder.append(baseDataDicService.getNameById(obj.getDistance()));
                stringList.add(builder.toString());
            }
            map.put(BasicMatchingEducation.class.getSimpleName(),StringUtils.join(stringList, "；")) ;
        }
        return map;
    }

    /**
     * 环境情况
     * @param basicEstate
     * @param scienceEnum
     * @return
     */
    public String getEnvironmentalConditions(BasicEstate basicEstate , EnvironmentalScienceEnum scienceEnum){
        StringBuilder builder = new StringBuilder(8);
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(basicEstate.getId());
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(applyBatch);
        List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = basicExamineHandle.getBasicMatchingEnvironmentList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(scienceEnum.getKey());
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            List<BasicMatchingEnvironmentVo> collect = basicMatchingEnvironmentVoList.stream().filter(basicMatchingEnvironmentVo -> Objects.equal(basicMatchingEnvironmentVo.getType(), baseDataDic.getId().toString())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)) {
                basicMatchingEnvironmentVoList = Arrays.asList(collect.get(0));
            } else {
                basicMatchingEnvironmentVoList.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            for (BasicMatchingEnvironmentVo oo : basicMatchingEnvironmentVoList) {
                if (StringUtils.isNotBlank(oo.getRemark())) {
                    builder.append(oo.getRemark());
                } else if (StringUtils.isNotBlank(oo.getHumanImpactName())) {
                    String name = oo.getHumanImpactName();
                    if ("无".equals(name)) {
                        builder.append("无").append(oo.getCategoryName());
                    } else {
                        builder.append(oo.getCategoryName()).append(name);
                    }
                }
                if (StringUtils.isNotBlank(builder.toString())) {
                    hashSet.add(builder.toString());
                }
                builder.delete(0, builder.toString().length());
            }
        }
        String value = StringUtils.join(hashSet, ",");
        return value;
    }

    /**
     * 规划条件
     * @param judgeObjectList
     * @return
     */
    public String getPlanningConditions(List<SchemeJudgeObject> judgeObjectList){
        String value = null;
        try {
            Map<Integer, String> map = Maps.newHashMap();
            StringBuilder contentBuilder = new StringBuilder(8);
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
                if (basicApply == null || basicApply.getId() == 0) {
                    continue;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicEstateLandStateVo basicEstateLandState = generateBaseExamineService.getBasicEstateLandState();
                contentBuilder.append("估价对象") ;
                List<String> stringList = new ArrayList<>() ;
                BiConsumer<String,String> biConsumer = (((s, s2) -> {
                    if (StringUtils.isNotBlank(s) && StringUtils.isNotBlank(s2)){
                        stringList.add(String.format("%s%s",s,s2)) ;
                    }
                })) ;
                biConsumer.accept("容积率" ,basicEstateLandState.getPlotRatio());
                biConsumer.accept("建筑密度" ,basicEstateLandState.getBuildingDensity());
                biConsumer.accept("绿地率" ,basicEstateLandState.getGreenSpaceRate());
                biConsumer.accept("兼容比" ,basicEstateLandState.getCompatibleRatio());
                biConsumer.accept("建筑高度" ,AsposeUtils.getValue(basicEstateLandState.getBuildingHeightLimit()));
                contentBuilder.append(StringUtils.join(stringList,"、")) ;
                contentBuilder.append("区域规划利用为").append(basicEstateLandState.getLandUseTypeName()) ;
                if (StringUtils.isNotBlank(contentBuilder.toString())) {
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), contentBuilder.toString());
                }
                contentBuilder.delete(0, contentBuilder.toString().length());
            }
            String s = generateCommonMethod.judgeEachDesc2(map, "", ";", false);
            value = StringUtils.strip(s, "，");
        } catch (Exception ex) {
            baseService.writeExceptionInfo(ex);
            return "";
        }
        return StringUtils.isNotBlank(value) ? value : defaultString;
    }

    public String getSummaryRregionalFactors(List<SchemeJudgeObject> judgeObjectList){

        return  "区域因素总结，根据各类表单确定后，写规则自动生成文本，最后处理 区域土地利用状况询问后处理" ;
    }
}

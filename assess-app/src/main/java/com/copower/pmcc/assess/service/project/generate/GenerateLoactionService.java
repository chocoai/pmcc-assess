package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.ExamineEstateSupplyEnumType;
import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取区位数据
 */
@Service
public class GenerateLoactionService {
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyService basicApplyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private StringBuffer stringBuffer = new StringBuffer(8);
    private final String error = "无";


    /**
     * 获取坐落信息
     *
     * @param declareId
     * @param applyId
     * @return
     */
    public String getSeat(Integer declareId, Integer applyId) throws Exception {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(applyId);
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (declareRecord == null || StringUtils.isEmpty(declareRecord.getName())) {
            stringBuffer.append(error);
        } else {
            stringBuffer.append(declareRecord.getName());
        }
        if (basicEstate == null || StringUtils.isEmpty(basicEstate.getName())) {
            stringBuffer.append(error);
        } else {
            stringBuffer.append(basicEstate.getName());
        }
        return stringBuffer.toString();
    }

    /**
     * 临街（路）状况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getFaceStreet(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            basicHouseFaceStreetVoList.stream().forEach(basicHouseFaceStreetVo -> {
                stringBuffer.append(String.format("方位:%s", basicHouseFaceStreetVo.getPositionName())).append(basicHouseFaceStreetVo.getStreetName()).append(";");
            });
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 外部公共服务设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getExternalPublicServiceFacilities(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        stringBuffer.append(basicEstate.getName());
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
        //购物商场
        List<BasicMatchingLeisurePlace> matchingMarketList = basicMatchingLeisurePlaceList.stream()
                .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey(), basicMatchingLeisurePlace.getType()))
                .collect(Collectors.toList());
        //餐饮
        List<BasicMatchingLeisurePlace> matchingRestaurantList = basicMatchingLeisurePlaceList.stream()
                .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey(), basicMatchingLeisurePlace.getType()))
                .collect(Collectors.toList());
        //休闲娱乐
        List<BasicMatchingLeisurePlace> matchingRecreationList = basicMatchingLeisurePlaceList.stream()
                .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey(), basicMatchingLeisurePlace.getType()))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(matchingMarketList)) {
            matchingMarketList.stream().forEach(leisurePlace -> {
                stringBuffer.append("购物商场距离")
                        .append(baseDataDicService.getNameById(leisurePlace.getDistance()))
                        .append("有")
                        .append(baseDataDicService.getNameById(leisurePlace.getCategory()))
                        .append(leisurePlace.getName());
            });
            stringBuffer.append(";");
        }
        if (CollectionUtils.isNotEmpty(matchingRestaurantList)) {
            matchingRestaurantList.stream().forEach(leisurePlace -> {
                stringBuffer.append("餐饮距离")
                        .append(baseDataDicService.getNameById(leisurePlace.getDistance()))
                        .append("有")
                        .append(baseDataDicService.getNameById(leisurePlace.getCategory()))
                        .append(leisurePlace.getName());
            });
            stringBuffer.append(";");
        }
        if (CollectionUtils.isNotEmpty(matchingRecreationList)) {
            matchingRecreationList.stream().forEach(leisurePlace -> {
                stringBuffer.append("休闲娱乐距离")
                        .append(baseDataDicService.getNameById(leisurePlace.getDistance()))
                        .append("有")
                        .append(baseDataDicService.getNameById(leisurePlace.getCategory()))
                        .append(leisurePlace.getName());
            });
            stringBuffer.append(";");
        }
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            basicMatchingFinanceVoList.stream().forEach(basicMatchingFinanceVo -> {
                String v = null;
                if (NumberUtils.isNumber(basicMatchingFinanceVo.getDistance())) {
                    v = baseDataDicService.getNameById(basicMatchingFinanceVo.getDistance());
                    if (StringUtils.isEmpty(v)) {
                        v = basicMatchingFinanceVo.getDistance();
                    }
                } else {
                    v = basicMatchingFinanceVo.getDistance();
                }
                stringBuffer.append("金融距离")
                        .append(v)
                        .append("有")
                        .append(baseDataDicService.getNameById(basicMatchingFinanceVo.getCategory()))
                        .append(basicMatchingFinanceVo.getName());
            });
        }
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            basicMatchingMedicalList.stream().forEach(basicMatchingMedical -> {
                stringBuffer.append("医疗距离")
                        .append(baseDataDicService.getNameById(basicMatchingMedical.getDistance()))
                        .append("有")
                        .append(baseDataDicService.getNameById(basicMatchingMedical.getOrganizationLevel()))
                        .append(basicMatchingMedical.getOrganizationName());
            });
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
            basicMatchingEducationList.stream().forEach(basicMatchingEducation -> {
                stringBuffer.append("教育距离")
                        .append(baseDataDicService.getNameById(basicMatchingEducation.getDistance()))
                        .append("有")
                        .append(baseDataDicService.getNameById(basicMatchingEducation.getSchoolNature()))
                        .append(basicMatchingEducation.getSchoolName());
            });
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 环境
     *
     * @param basicApply
     * @param scienceEnum
     * @return
     * @throws Exception
     */
    public String getEnvironmentalScience(BasicApply basicApply, EnvironmentalScienceEnum scienceEnum) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = generateBaseExamineService.getBasicMatchingEnvironmentList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(scienceEnum.getKey());
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            String a = "无影响";
            String b = "影响一般";
            String c = "影响较大";
            String d = "有重大影响";
            //自然环境要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.NATURAL.getKey())) {
                environmentalScience(a, b, c, d, "空气", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "水体", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "美观", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "静密", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "空间", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "废弃", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("\r");
            }
            //人文环境要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.HUMANITY.getKey())) {
                environmentalScience(a, b, c, d, "职业", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "收入水平", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "文化程度", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "宗教信仰", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("\r");
            }
            //景观要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.SCENERY.getKey())) {
                environmentalScience(a, b, c, d, "海景", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "江景", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "河景", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "湖景", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "山景", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "公园", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "园林", stringBuffer, basicMatchingEnvironmentVoList);
                environmentalScience(a, b, c, d, "中庭景观", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("\r");
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    private void environmentalScience(String a, String b, String c, String d, String key, StringBuffer stringBuffer, List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList) {
        BasicMatchingEnvironmentVo matchingEnvironmentVo = null;
        try {
            matchingEnvironmentVo = basicMatchingEnvironmentVoList.stream().filter(basicMatchingEnvironmentVo -> {
                if (basicMatchingEnvironmentVo.getCategory() != null && StringUtils.isNotBlank(basicMatchingEnvironmentVo.getCategoryName())) {
                    if (basicMatchingEnvironmentVo.getCategoryName().indexOf(key) != -1) {
                        return true;
                    }
                }
                return false;
            }).findFirst().get();
        } catch (Exception e) {
            //经常出现null parsent value 异常
            logger.error(String.format("异常 com.copower.pmcc.assess.service.project.generate.GenerateLoactionService.environmentalScience %s", e.getMessage()), e);
        }
        if (matchingEnvironmentVo != null) {
            if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), a)) {
                matchingEnvironmentVo.setRemark("优良");
            }
            if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), b)) {
                matchingEnvironmentVo.setRemark("一般");
            }
            if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), c)) {
                matchingEnvironmentVo.setRemark("差");
            }
            if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), d)) {
                matchingEnvironmentVo.setRemark("非常差");
            }
        } else {
            matchingEnvironmentVo = new BasicMatchingEnvironmentVo();
            matchingEnvironmentVo.setRemark(String.format("无%s信息", key));
            matchingEnvironmentVo.setCategoryName(key);
            matchingEnvironmentVo.setInfluenceDegreeName("无影响要素");
        }
        stringBuffer.append(matchingEnvironmentVo.getCategoryName())
                .append(matchingEnvironmentVo.getInfluenceDegreeName())
                .append(matchingEnvironmentVo.getRemark());
    }

    /**
     * 综述
     *
     * @param schemeJudgeObject
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String content(SchemeJudgeObject schemeJudgeObject, BasicApply basicApply) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("区域内交通便捷度高、基础设施完备、属于成熟的区级中心商业区、医疗、教育、购物等公共配套设施完善，环境较好，" +
                "区域内人流量大，居家生活便捷度高、商业聚集程度较高");
        return stringBuilder.toString();
    }

    /**
     * 外部基础设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getExternalInfrastructure(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        List<BasicEstateSupply> basicEstateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            basicHouseFaceStreetVoList.stream().forEach(basicHouseFaceStreetVo -> {
                stringBuffer.append(basicHouseFaceStreetVo.getStreetName()).append(basicHouseFaceStreetVo.getStreetLevelName());
                String v = null;
                long a1 = 0;
                long a2 = 0;
                long a3 = 0;
                if (Objects.equal("主干道", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a1++;
                }
                if (Objects.equal("次干道", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a2++;
                }
                if (Objects.equal("支路", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a3++;
                }
                if (a1 > 0 && a2 > 0) {
                    //支路不必判断
                    v = "道路体系完善";
                }
                if (a1 <= 0 && a2 > 0 && a3 > 0) {
                    v = "道路体系相对完善";
                }
                if (a1 <= 0 && a2 <= 0 && a3 > 0) {
                    v = "道路体系不完善";
                }
                if (a1 <= 0 && a2 <= 0 && a3 <= 0) {
                    v = "无基本的配套道路体系";
                }
                if (Objects.equal("未选定", basicHouseFaceStreetVo.getPositionName())) {
                    v = "无基本的配套道路体系";
                }
                stringBuffer.append(v);
            });
        } else {
            stringBuffer.append("配套道路体系信息暂时无");
        }
        if (CollectionUtils.isNotEmpty(basicEstateSupplyList)) {
            StringBuilder stringBuilder = new StringBuilder(8);
            long estateSupplyGas = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName(), basicEstateSupply.getType())).count();
            long estateSupplyHeating = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName(), basicEstateSupply.getType())).count();
            long estateSupplyWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName(), basicEstateSupply.getType())).count();
            long estateDrainWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName(), basicEstateSupply.getType())).count();
            long estateSupplyPower = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName(), basicEstateSupply.getType())).count();
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName());
            if (estateSupplyGas == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName());
            if (estateSupplyHeating == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName());
            if (estateSupplyWater == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName());
            if (estateDrainWater == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName());
            if (estateSupplyPower == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            long count = estateSupplyGas + estateSupplyHeating + estateSupplyWater + estateDrainWater + estateSupplyPower;
            if (count == 0) {
                stringBuffer.append("无供应信息");
            } else {
                stringBuffer.append(stringBuilder.toString());
            }
        } else {
            stringBuffer.append("配套供应信息暂时无");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 道路状况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getRoadCondition(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            basicHouseFaceStreetVoList.stream().forEach(basicHouseFaceStreetVo -> {
                stringBuffer.append(basicHouseFaceStreetVo.getPositionName()).append(basicHouseFaceStreetVo.getStreetLevelName());
                stringBuffer.append(basicHouseFaceStreetVo.getStreetName());
                stringBuffer.append(basicHouseFaceStreetVo.getTrafficFlowName()).append(basicHouseFaceStreetVo.getVisitorsFlowrateName());
            });
        } else {
            stringBuffer.append(error);
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 出入可利用的交通工具
     *
     * @return
     * @throws Exception
     */
    public String getAccessAvailableMeansTransport(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
        //公交
        List<BasicMatchingTrafficVo> transitList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TRANSIT.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        //地铁
        List<BasicMatchingTrafficVo> metroList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.METRO.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(transitList)) {
            stringBuffer.append("距");
            transitList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getDistanceName());
                stringBuffer.append("有").append("所在线路").append(basicMatchingTrafficVo.getTheLine());
            });
        }
        if (CollectionUtils.isNotEmpty(metroList)) {
            stringBuffer.append("距");
            metroList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getDistanceName());
                stringBuffer.append("有").append("所在线路").append(basicMatchingTrafficVo.getTheLine());
            });
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 停车方便度
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getParkingConvenience(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicEstateParking> basicEstateParkingList = generateBaseExamineService.getBasicEstateParkingList();
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(basicEstateParkingList)) {
            basicEstateParkingList.stream().forEach(basicEstateParking -> {
                stringBuffer.append(baseDataDicService.getNameById(basicEstateParking.getLocation()));
                stringBuffer.append(baseDataDicService.getNameById(basicEstateParking.getParkingEstate()));
                stringBuffer.append(basicEstateParking.getNumber().toString());
                stringBuffer.append(";");
            });
            stringBuffer.append("。");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 交通收费情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficCharges(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (StringUtils.isNotBlank(basicMatchingTrafficVo.getCostStandard())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getCostStandard()).append(";");
            });
            stringBuffer.append("。");
        } else {
            stringBuffer.append("收费标准无");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 交通管制情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficControl(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainRoad.getName()) && basicMatchingTrafficVo.getFlag() != null) {
                if (basicMatchingTrafficVo.getFlag().booleanValue()) {
                    return basicMatchingTrafficVo.getFlag().booleanValue();
                }
            }
            return false;
        }).collect(Collectors.toList());
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            stringBuffer.append("限行情况:");
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getLimitSpeed()).append(basicMatchingTrafficVo.getLimitTime()).append(basicMatchingTrafficVo.getLimitSpeialName()).append(";");
            });
            stringBuffer.append("。");
        } else {
            stringBuffer.append("交通管制限制无");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 与重要场所的距离
     *
     * @return
     */
    public String getWithImportantLocationDistance(Integer applyId) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
        if (basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
            List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList().stream()
                    .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey(), basicMatchingLeisurePlace.getType()))
                    .collect(Collectors.toList());
            List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
            List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
            if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
                //交通枢纽
                List<BasicMatchingTrafficVo> trafficHubList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
                    if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(trafficHubList)) {
                    trafficHubList.stream().forEach(basicMatchingTrafficVo -> {
                        stringBuffer.append("距").append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getDistanceName());
                    });
                    stringBuffer.append(";");
                }
                //主要转换
                List<BasicMatchingTrafficVo> mainConversionList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
                    if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainConversion.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(mainConversionList)) {
                    mainConversionList.stream().forEach(basicMatchingTrafficVo -> {
                        stringBuffer.append("距").append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getDistanceName());
                    });
                    stringBuffer.append(";");
                }
            }
            //购物商场
            if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
                basicMatchingLeisurePlaceList.stream().forEach(leisurePlace -> {
                    stringBuffer.append("距").append(leisurePlace.getName()).append(baseDataDicService.getNameById(leisurePlace.getDistance()));
                });
                stringBuffer.append(";");
            }
            //金融服务
            if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
                basicMatchingFinanceVoList.stream().forEach(basicMatchingFinanceVo -> {
                    String value = null;
                    if (NumberUtils.isNumber(basicMatchingFinanceVo.getDistance())) {
                        value = baseDataDicService.getNameById(basicMatchingFinanceVo.getDistance());
                        if (StringUtils.isEmpty(value)) {
                            value = basicMatchingFinanceVo.getDistance();
                        }
                    } else {
                        value = basicMatchingFinanceVo.getDistance();
                    }
                    stringBuffer.append("距").append(basicMatchingFinanceVo.getName()).append(value);
                });
                stringBuffer.append(";");
            }
            //医疗
            if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
                basicMatchingMedicalList.stream().forEach(basicMatchingMedical -> {
                    stringBuffer.append("距").append(basicMatchingMedical.getOrganizationName()).append(baseDataDicService.getNameById(basicMatchingMedical.getDistance()));
                });
                stringBuffer.append(";");
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 朝向
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getOrientation(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        try {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            stringBuffer.append(baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getOrientation()));
        } catch (Exception e1) {
            logger.error("异常 com.copower.pmcc.assess.service.project.generate.GenerateLoactionService.getOrientation", e1);
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 方位
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public String getPosition(Integer applyId) throws Exception {
        BasicEstateVo basicEstate = basicEstateService.getBasicEstateVo(basicEstateService.getBasicEstateByApplyId(applyId));
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (basicEstate != null) {
            stringBuffer.append(basicEstate.getProvinceName()).append(basicEstate.getCityName()).append(basicEstate.getDistrictName());
        } else {
            stringBuffer.append(error);
        }
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                stringBuffer.append(baseDataDicService.getNameById(dataBlock.getPosition()));
                stringBuffer.append(dataBlock.getName());
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 获取楼层信息
     *
     * @param judgeObjectIds
     * @return
     */
    public String getFloor(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (int i = 0; i < judgeObjectList.size(); i++) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(i);
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply != null) {
                    if (judgeObjectList.size() != 1) {
                        stringBuffer.append("估价对象");
                        stringBuffer.append(schemeJudgeObject.getNumber());
                        stringBuffer.append("号");
                    }
                    BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
                    BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
                    if (basicBuilding == null || basicHouse == null) {
                        continue;
                    }
                    stringBuffer.append(basicBuilding.getFloorHeight().toString()).append("层");
                    stringBuffer.append("建筑的第");
                    stringBuffer.append(basicHouse.getFloor()).append("层数");
                }
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }
}

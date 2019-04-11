package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.data.SurveyDamageDto;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateLandEntityService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "dataReportAnalysisService")
public class DataReportAnalysisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataReportAnalysisDao dataReportAnalysisDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private GenerateLandEntityService generateLandEntityService;


    /**
     * 保存数据
     *
     * @param dataReportAnalysis
     */
    public void saveAndUpdate(DataReportAnalysis dataReportAnalysis) {
        if (dataReportAnalysis.getId() != null && dataReportAnalysis.getId() > 0) {
            dataReportAnalysisDao.updateReportAnalysis(dataReportAnalysis);
        } else {
            BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
            dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
            dataReportAnalysis.setCreator(commonService.thisUserAccount());
            dataReportAnalysisDao.addReportAnalysis(dataReportAnalysis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(dataReportAnalysis.getId(), SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_LIQUIDITY.getKey());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeReportAnalysis(Integer id) {
        return dataReportAnalysisDao.removeReportAnalysis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataReportAnalysis getReportAnalysis(Integer id) {
        return dataReportAnalysisDao.getReportAnalysis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getReportAnalysisList(String name, Integer type, Integer reportAnalysisType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataReportAnalysis> hypothesisList = dataReportAnalysisDao.getReportAnalysisList(name, type, reportAnalysisType);
        List<DataReportAnalysisVo> vos = LangUtils.transform(hypothesisList, p -> getReportAnalysisVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataReportAnalysisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据取数据列表
     *
     * @param reportAnalysisType
     * @return
     */
    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType) {
        return dataReportAnalysisDao.getReportAnalysisList(reportAnalysisType);
    }


    public DataReportAnalysisVo getReportAnalysisVo(DataReportAnalysis reportAnalysis) {
        if (reportAnalysis == null) return null;
        DataReportAnalysisVo vo = new DataReportAnalysisVo();
        BeanUtils.copyProperties(reportAnalysis, vo);
        vo.setReportAnalysisTypeName(baseDataDicService.getNameById(reportAnalysis.getReportAnalysisType()));
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        if (StringUtils.isNotBlank(reportAnalysis.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeName(baseDataDicService.getDataDicName(purposeDicList, reportAnalysis.getEntrustmentPurpose()));
        }
        if (reportAnalysis.getMarketBackgroundType() != null) {
            vo.setMarketBackgroundTypeName(baseDataDicService.getNameById(reportAnalysis.getMarketBackgroundType()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(reportAnalysis.getProvince()));//省
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(reportAnalysis.getCity()));//市或者县
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(reportAnalysis.getDistrict()));//县
        }

        return vo;
    }

    /**
     * 获取报告分析数据
     *
     * @param type
     * @param entrustmentPurpose
     * @return
     */
    public List<DataReportAnalysis> getDataReportAnalysisList(String province, String city, String district, Integer type, Integer entrustmentPurpose) {
        String entrustmentPurposeString = String.format(",%s,", entrustmentPurpose);
        return dataReportAnalysisDao.getReportAnalysisList(province, city, district, type, entrustmentPurposeString);
    }

    /**
     * 获取上报告的变现分析数据
     *
     * @return
     */
    public String getReportLiquidity(ProjectInfo projectInfo, Integer areaGroupId) throws Exception {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
        if (baseDataDic == null) return "";

        //变现比率
        SchemeLiquidationAnalysis data = schemeLiquidationAnalysisService.getDataByAreaId(areaGroupId);
        String liquidRatios = data.getLiquidRatios();
        //变现时间
        String liquidTime = data.getLiquidTime();
        //区域
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisDao.getReportAnalysisList(baseDataDic.getId());
        if (CollectionUtils.isEmpty(reportAnalysisList)) return "";
        StringBuilder stringBuilder = new StringBuilder();

        //对应委估对象
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", i + 1, dataReportAnalysis.getName())).append("</p>");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportAnalysis.getTemplate()).append("</p>");
            //开发程度分析
            if (AssessReportFieldConstant.DEVELOPMENT_LEVEL.equals(dataReportAnalysis.getFieldName())) {
                ArrayList<String> estateNames = new ArrayList<>();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    if (!estateNames.contains(basicEstate.getName())) {
                        estateNames.add(basicEstate.getName());
                    }
                }
                Map<BasicEstate, String> map = getSameEstate(judgeObjectList, estateNames);
                DataReportTemplateItem buildingProfile = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.BUILDING_PROFILE);
                for (Map.Entry<BasicEstate, String> entry : map.entrySet()) {
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(buildingProfile.getTemplate().replace("#{估价对象号}", entry.getValue()).replace("#{楼盘概况}", entry.getKey().getLocationDescribe())).append("</p>");
                }
            }
            //估价对象区位分析
            if (AssessReportFieldConstant.LOCATION_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                ArrayList<String> estateNames = new ArrayList<>();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    if (!estateNames.contains(basicEstate.getName())) {
                        estateNames.add(basicEstate.getName());
                    }
                }
                Map<BasicEstate, String> map = getSameEstate(judgeObjectList, estateNames);
                DataReportTemplateItem plate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.LOCATION_ANALYSIS_PLATE);
                for (Map.Entry<BasicEstate, String> entry : map.entrySet()) {
                    DataBlock block = dataBlockService.getDataBlockById(entry.getKey().getBlockId());
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(plate.getTemplate().replace("#{估价对象号}", entry.getValue()).replace("#{区县}", erpAreaService.getSysAreaName(entry.getKey().getDistrict())).replace("#{版块名称}", entry.getKey().getBlockName()).replace("#{版块描述}", block.getDistrict())).append("</p>");
                }
            }
            //估价区位分析
            if (AssessReportFieldConstant.ZONE_BIT_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                ArrayList<String> estateNames = new ArrayList<>();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    if (!estateNames.contains(basicEstate.getName())) {
                        estateNames.add(basicEstate.getName());
                    }
                }
                Map<BasicEstate, String> map = getSameEstate(judgeObjectList, estateNames);
                DataReportTemplateItem plate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ZONE_BIT_ANALYSIS_PLATE);
                for (Map.Entry<BasicEstate, String> entry : map.entrySet()) {
                    DataBlock block = dataBlockService.getDataBlockById(entry.getKey().getBlockId());
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(plate.getTemplate().replace("#{估价对象号}", entry.getValue()).replace("#{区县}", erpAreaService.getSysAreaName(entry.getKey().getDistrict())).replace("#{版块名称}", entry.getKey().getBlockName()).replace("#{版块描述}", block.getDistrict())).append("</p>");
                }
            }
            //估价对象土地实体分析
            if (AssessReportFieldConstant.LAND_ENTITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                ArrayList<String> estateNames = new ArrayList<>();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    if (!estateNames.contains(basicEstate.getName())) {
                        estateNames.add(basicEstate.getName());
                    }
                }
                Map<BasicEstate, List<SchemeJudgeObject>> map = getSameEstate2(judgeObjectList, estateNames);
                DataReportTemplateItem plate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.LAND_ENTITY_ANALYSIS_HOUSES);
                for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
                    StringBuilder numbers = new StringBuilder();
                    for (SchemeJudgeObject item: entry.getValue()) {
                        numbers.append(item.getNumber()).append(",");
                    }
                    BasicApply apply = basicApplyDao.getBasicApplyById(entry.getKey().getApplyId());
                    String content = generateLandEntityService.getContent(apply);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(plate.getTemplate().replace("#{估价对象号}", getSubstitutionPrincipleName(numbers.toString())).replace("#{区县}", erpAreaService.getSysAreaName(entry.getKey().getDistrict())).replace("#{楼盘名称}", entry.getKey().getName()).replace("#{土地实体结论}", content)).append("</p>");
                }
            }

            //独立性分析
            if (AssessReportFieldConstant.INDEPENDENCE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //完好委估对象
                StringBuilder intact = new StringBuilder();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    StringBuilder damageContent = new StringBuilder();
                    //对应资产清查内容
                    SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
                    if (!"不正常".equals(surveyAssetInventory.getRimIsNormal()) && !"损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                        intact.append(judgeObject.getNumber()).append(",");
                    } else {
                        damageContent.append("损坏");
                    }
                    if ("不正常".equals(surveyAssetInventory.getRimIsNormal())) {
                        List<SurveyDamageDto> zoneDamegeList = JSON.parseArray(surveyAssetInventory.getZoneDamage(), SurveyDamageDto.class);
                        if (CollectionUtils.isNotEmpty(zoneDamegeList)) {
                            for (SurveyDamageDto dto : zoneDamegeList) {
                                damageContent.append("项目:").append(dto.getZoneProjectName()).append(",明细").append(dto.getZoneProjectItem()).append(";");
                            }

                        }
                    }
                    if ("损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                        List<SurveyDamageDto> entityDamegeList = JSON.parseArray(surveyAssetInventory.getEntityDamage(), SurveyDamageDto.class);
                        if (CollectionUtils.isNotEmpty(entityDamegeList)) {
                            for (SurveyDamageDto dto : entityDamegeList) {
                                damageContent.append("项目:").append(dto.getEntityProjectName()).append(",明细").append(dto.getEntityProjectItem()).append(";");
                            }

                        }
                    }
                    if (StringUtils.isNotBlank(damageContent)) {
                        DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INDEPENDENCE_ANALYSIS_DAMAGE);
                        stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", judgeObject.getNumber() + "号").replace("#{区位状况损坏状况表}#{实体状况损坏状况表}；", damageContent)).append("</p>");
                    }

                }
                if (StringUtils.isNotBlank(intact)) {
                    String number = getSubstitutionPrincipleName(intact.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INDEPENDENCE_ANALYSIS_INTACT);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)).append("</p>");
                }
            }

            //变现价格与市场价格的差异度
            if (AssessReportFieldConstant.MARKET_VALUE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_VALUE_ANALYSIS_OTHER);
                stringBuilder.append("<p style=\"text-indent:2em\">").append(other.getTemplate()).append("</p>");
                //实现价格
                DataReportTemplateItem realized = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_VALUE_ANALYSIS_REALIZED_PRICE);
                stringBuilder.append("<p style=\"text-indent:2em\">").append(realized.getTemplate().replace("#{变现比率}", liquidRatios)).append("</p>");
            }

            //变现时间费税及清偿
            if (AssessReportFieldConstant.PAY_OFF_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_OTHER);
                stringBuilder.append("<p style=\"text-indent:2em\">").append(other.getTemplate()).append("</p>");
                //政策
                DataReportTemplateItem policy = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_POLICY);
                stringBuilder.append("<p style=\"text-indent:2em\">").append(policy.getTemplate().replace("#{变现时间}", liquidTime)).append("</p>");
                //费用一览表
                DataReportTemplateItem schedule = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_SCHEDULE);
                stringBuilder.append("<p style=\"text-indent:2em\">").append(schedule.getTemplate()).append("</p>");

            }

            //可分割分析
            if (AssessReportFieldConstant.DIVISIBLE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //不可分
                StringBuilder impartibility = new StringBuilder();
                //可分可办证
                StringBuilder detachableCanRush = new StringBuilder();
                //可分不可办证
                StringBuilder detachableNotRush = new StringBuilder();

                //可办证
                Integer passId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE_PASS).getId();
                //不可办证
                Integer refuseId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE_REFUSE).getId();


                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //对应资产清查内容
                    SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
                    if ("不可分".equals(surveyAssetInventory.getSegmentationLimit())) {
                        impartibility.append(judgeObject.getNumber()).append(",");
                    }
                    if ("可分".equals(surveyAssetInventory.getSegmentationLimit()) && passId.equals(Integer.valueOf(surveyAssetInventory.getCertificate()))) {
                        detachableCanRush.append(judgeObject.getNumber()).append(",");
                    }
                    if ("可分".equals(surveyAssetInventory.getSegmentationLimit()) && refuseId.equals(Integer.valueOf(surveyAssetInventory.getCertificate()))) {
                        detachableNotRush.append(judgeObject.getNumber()).append(",");
                    }
                }
                if (StringUtils.isNotBlank(impartibility)) {
                    String number = getSubstitutionPrincipleName(impartibility.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_IMPARTIBILITY);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)).append("</p>");
                }
                if (StringUtils.isNotBlank(detachableCanRush)) {
                    String number = getSubstitutionPrincipleName(detachableCanRush.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_DETACHABLE_CAN_RUSH);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)).append("</p>");
                }
                if (StringUtils.isNotBlank(detachableNotRush)) {
                    String number = getSubstitutionPrincipleName(detachableNotRush.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_DETACHABLE_NOT_RUSH);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)).append("</p>");
                }

            }

            //房地产市场状况
            if (AssessReportFieldConstant.HOUSE_MARKET_CONDITION.equals(dataReportAnalysis.getFieldName())) {
                Map<String, SchemeJudgeObject> map = new HashMap<>();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    Map<String, SchemeJudgeObject> setAndDis = getSameSetAndDis(judgeObjectList, Integer.valueOf(basicEstate.getDistrict()), judgeObject.getSetUse());
                    map.putAll(setAndDis);
                }
                for (Map.Entry<String, SchemeJudgeObject> entry : map.entrySet()) {
                    SchemeJudgeObjectVo vo = schemeJudgeObjectService.getSchemeJudgeObjectVo(entry.getValue());
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(entry.getValue().getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    DataReportTemplateItem purpose = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HOUSE_MARKET_CONDITION_PURPOSE);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(purpose.getTemplate().replace("#{估价对象号}", entry.getKey()).replace("#{区县}", erpAreaService.getSysAreaName(basicEstate.getDistrict())).replace("#{设定用途}", vo.getSetUseName())).append("</p>");
                }

            }
            //其他分析
            if (AssessReportFieldConstant.OTHER_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //出租
                StringBuilder rent = new StringBuilder();
                String rentRemark = new String();
                //抵押
                StringBuilder pledge = new StringBuilder();
                String pledgeRemark = new String();
                //其他
                StringBuilder other = new StringBuilder();
                String otherRemark = new String();

                Integer pledgeId = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_PLEDGE).getId();
                Integer otherId = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_OTHER).getId();
                Integer rentId = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_RENT).getId();


                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //对应的他权信息
                    List<SurveyAssetInventoryRight> rightList = Lists.newArrayList();
                    List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(judgeObject.getDeclareRecordId(), judgeObject.getProjectId());
                    if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                        rightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(surveyAssetInventoryRightRecordList.get(0).getId());
                    }

                    for (SurveyAssetInventoryRight inventoryRight : rightList) {
                        if (pledgeId.equals(inventoryRight.getCategory())) {
                            pledge.append(judgeObject.getNumber()).append(",");
                            pledgeRemark = inventoryRight.getRemark();
                        }
                        if (otherId.equals(inventoryRight.getCategory())) {
                            other.append(judgeObject.getNumber()).append(",");
                            otherRemark = inventoryRight.getRemark();
                        }
                        if (rentId.equals(inventoryRight.getCategory())) {
                            rent.append(judgeObject.getNumber()).append(",");
                            rentRemark = inventoryRight.getRemark();
                        }
                    }
                }

                if (StringUtils.isNotBlank(pledge)) {
                    String number = getSubstitutionPrincipleName(pledge.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_PLEDGE);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{他权描述}", pledgeRemark)).append("</p>");
                }
                if (StringUtils.isNotBlank(other)) {
                    String number = getSubstitutionPrincipleName(other.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_OTHER);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{他权描述}", otherRemark)).append("</p>");
                }
                if (StringUtils.isNotBlank(rent)) {
                    String number = getSubstitutionPrincipleName(rent.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_RENT);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{他权描述}", rentRemark)).append("</p>");
                }

            }
            //价值大小分析
            if (AssessReportFieldConstant.VALUE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                Integer num = judgeObjectList.size();
                BigDecimal totalRealEstate = generateCommonMethod.getTotalRealEstate(areaGroupId);
                BigDecimal rank = new BigDecimal("5000000");
                if (rank.compareTo(totalRealEstate) == 1 && num < 5) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION1);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate()).append("</p>");

                }
                if (rank.compareTo(totalRealEstate) == 1 && num >= 5) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION2);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate()).append("</p>");

                }
                if (rank.compareTo(totalRealEstate) < 1 && num < 5) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION3);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate()).append("</p>");

                }
                if (rank.compareTo(totalRealEstate) < 1 && num >= 5) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION4);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate()).append("</p>");

                }
            }
        }
        return stringBuilder.toString();
    }

    public Map<BasicEstate, String> getSameEstate(List<SchemeJudgeObject> judgeObjectList, ArrayList<String> estateNames) throws Exception {
        Map<BasicEstate, String> map = new HashMap<>();
        for (String estateName : estateNames) {
            BasicEstate basicEstate = new BasicEstate();
            ArrayList<Integer> numbers = new ArrayList<>();
            for (SchemeJudgeObject judgeObject : judgeObjectList) {
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                BasicEstate estate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                if (estateName.equals(estate.getName())) {
                    basicEstate = estate;
                    numbers.add(Integer.valueOf(judgeObject.getNumber()));
                }
            }
            String number = generateCommonMethod.convertNumber(numbers) + "号";
            map.put(basicEstate, number);
        }
        return map;
    }

    public Map<BasicEstate, List<SchemeJudgeObject>> getSameEstate2(List<SchemeJudgeObject> judgeObjectList, ArrayList<String> estateNames) throws Exception {
        Map<BasicEstate, List<SchemeJudgeObject>> map = new HashMap<>();
        for (String estateName : estateNames) {
            BasicEstate basicEstate = new BasicEstate();
            ArrayList<SchemeJudgeObject> judgeObjects = new ArrayList<>();
            for (SchemeJudgeObject judgeObject : judgeObjectList) {
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                BasicEstate estate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                if (estateName.equals(estate.getName())) {
                    basicEstate = estate;
                    judgeObjects.add(judgeObject);
                }
            }

            map.put(basicEstate, judgeObjects);
        }
        return map;
    }

    public String getSubstitutionPrincipleName(String str) {
        String[] s = str.toString().split(",");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String item : s) {
            if (!numbers.contains(Integer.valueOf(item))) {
                numbers.add(Integer.valueOf(item));
            }
        }
        return generateCommonMethod.convertNumber(numbers) + "号";
    }

    public Map<String, SchemeJudgeObject> getSameSetAndDis(List<SchemeJudgeObject> judgeObjectList, Integer districtId, Integer setUse) throws Exception {
        Map<String, SchemeJudgeObject> map = new HashMap<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        SchemeJudgeObject judge = new SchemeJudgeObject();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicEstate estate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            if (districtId.equals(Integer.valueOf(estate.getDistrict())) && setUse.equals(judgeObject.getSetUse())) {
                judge = judgeObject;
                numbers.add(Integer.valueOf(judgeObject.getNumber()));
            }
        }
        String number = generateCommonMethod.convertNumber(numbers) + "号";
        map.put(number, judge);

        return map;
    }
}

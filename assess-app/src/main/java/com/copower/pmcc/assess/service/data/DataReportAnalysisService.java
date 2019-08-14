package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.aspose.words.ControlChar;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.data.SurveyDamageDto;
import com.copower.pmcc.assess.dto.input.project.generate.EstateLiquidityAnalysisDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateHouseEntityService;
import com.copower.pmcc.assess.service.project.generate.GenerateLandEntityService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private PublicService publicService;
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
    @Autowired
    private GenerateHouseEntityService generateHouseEntityService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;


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
        if (baseDataDic == null) {
            return "";
        }
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
        SchemeLiquidationAnalysis data = schemeLiquidationAnalysisService.getDataByAreaId(areaGroupId);
        String liquidRatios = "";//变现比率
        String liquidTime = "";//变现时间
        if (data != null && StringUtils.isNotBlank(data.getLiquidRatios())) {
            liquidRatios = data.getLiquidRatios();//变现比率
        }
        if (data != null && StringUtils.isNotBlank(data.getLiquidTime())) {
            liquidTime = data.getLiquidTime();//变现时间
        }
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisDao.getReportAnalysisList(baseDataDic.getId());
        if (CollectionUtils.isEmpty(reportAnalysisList)) return "";
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);//区域下委估对象
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> estateGroupMap = generateCommonMethod.getEstateGroupByAreaId(areaGroupId);
        Map<String, EstateLiquidityAnalysisDto> analysisDtoMap = Maps.newHashMap();//用于处理变现能力综述
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : estateGroupMap.entrySet()) {
            EstateLiquidityAnalysisDto estateLiquidityAnalysisDto = new EstateLiquidityAnalysisDto();
            estateLiquidityAnalysisDto.setEstateName(entry.getKey().getName());
            analysisDtoMap.put(entry.getKey().getName(), estateLiquidityAnalysisDto);
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", i + 1, dataReportAnalysis.getName())));
            stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportAnalysis.getTemplate()));
            //估价对象区位分析与估价区位分析
            if (AssessReportFieldConstant.ZONE_BIT_ANALYSIS.equals(dataReportAnalysis.getFieldName()) || AssessReportFieldConstant.LOCATION_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getLocationAnalysis(estateGroupMap))));
            }
            //估价对象土地实体分析
            if (AssessReportFieldConstant.LAND_ENTITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getLandEntityAnalysis(estateGroupMap))));
            }
            //估价对象建筑实体分析
            if (AssessReportFieldConstant.ARCHITECTURAL_ENTITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getBuildingEntityAnalysis(estateGroupMap, schemeAreaGroup))));
            }
            //变现能力通用性分析
            if (AssessReportFieldConstant.UNIVERSALITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getUniversalityAnalysis(estateGroupMap, projectInfo.getId(), analysisDtoMap))));
            }
            //独立性分析
            if (AssessReportFieldConstant.INDEPENDENCE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getIndependenceAnalysis(judgeObjectList))));
            }
            //可分割分析
            if (AssessReportFieldConstant.DIVISIBLE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getDivisibleAnalysis(judgeObjectList))));
            }
            //开发程度分析
            if (AssessReportFieldConstant.DEVELOPMENT_LEVEL.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getDevelopmentLevelAnalysis(estateGroupMap))));
            }
            //价值大小分析
            if (AssessReportFieldConstant.VALUE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getValueAnalysis(judgeObjectList, areaGroupId))));
            }
            //房地产市场状况
            if (AssessReportFieldConstant.HOUSE_MARKET_CONDITION.equals(dataReportAnalysis.getFieldName())) {

            }
            //其他分析
            if (AssessReportFieldConstant.OTHER_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getOtherAnalysis(judgeObjectList))));
            }
            //变现价格与市场价格的差异度
            if (AssessReportFieldConstant.MARKET_VALUE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_VALUE_ANALYSIS_OTHER);
                stringBuilder.append(generateCommonMethod.getIndentHtml(other.getTemplate()));
                //实现价格
                DataReportTemplateItem realized = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_VALUE_ANALYSIS_REALIZED_PRICE);
                stringBuilder.append(generateCommonMethod.getIndentHtml(realized.getTemplate().replace("#{变现比率}", StringUtils.defaultString(liquidRatios))));
            }
            //变现时间费税及清偿
            if (AssessReportFieldConstant.PAY_OFF_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_OTHER);
                stringBuilder.append(generateCommonMethod.getIndentHtml(other.getTemplate()));
                //政策
                DataReportTemplateItem policy = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_POLICY);
                stringBuilder.append(generateCommonMethod.getIndentHtml(policy.getTemplate().replace("#{变现时间}", StringUtils.defaultString(liquidTime))));
                //费用一览表
                DataReportTemplateItem schedule = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_SCHEDULE);
                stringBuilder.append(generateCommonMethod.getIndentHtml(schedule.getTemplate()));
            }
            //变现能力综述
            if (AssessReportFieldConstant.CASHABILITY_SUMMARY.equals(dataReportAnalysis.getFieldName())) {
                DataReportTemplateItem templateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.CASHABILITY_SUMMARY_CONTENT);
                HashSet<String> stringHashSet = Sets.newHashSet();
                for (Map.Entry<String, EstateLiquidityAnalysisDto> entry : analysisDtoMap.entrySet()) {
                    EstateLiquidityAnalysisDto analysisDto = entry.getValue();
                    String s = templateItem.getTemplate()
                            .replace("#{通用性}", StringUtils.isEmpty(analysisDto.getGenerality()) ? "" : String.format(analysisDto.getGenerality()))
                            .replace("#{独立使用性}", StringUtils.isEmpty(analysisDto.getIndependence()) ? "" : String.format(analysisDto.getIndependence()))
                            .replace("#{单个产权面积}", StringUtils.isEmpty(analysisDto.getPropertyRight()) ? "" : String.format(analysisDto.getPropertyRight()));
                    String mobility = "好";
                    if (analysisDto.getGenerality().contains("弱") || analysisDto.getIndependence().contains("差")) {
                        mobility = "较差";
                    }
                    if (analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("适中")) {
                        mobility = "较好";
                    }
                    s = s.replace("#{流动性}", mobility);
                    stringHashSet.add(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(s)));
                }
                stringHashSet.forEach(o -> stringBuilder.append(o));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取上报告的变现分析数据 (注意这个方法用在小微快贷的报告,因此和上面得方法是不同得，此方法只有部分)
     * @param projectInfo
     * @param areaGroupId
     * @return
     * @throws Exception
     */
    public String getReportLiquidityLittle(ProjectInfo projectInfo, Integer areaGroupId) throws Exception {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
        if (baseDataDic == null) {
            return "";
        }
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisDao.getReportAnalysisList(baseDataDic.getId());
        if (CollectionUtils.isEmpty(reportAnalysisList)) {
            return "";
        }
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);//区域下委估对象
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> estateGroupMap = generateCommonMethod.getEstateGroupByAreaId(areaGroupId);
        Map<String, EstateLiquidityAnalysisDto> analysisDtoMap = Maps.newHashMap();//用于处理变现能力综述
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : estateGroupMap.entrySet()) {
            EstateLiquidityAnalysisDto estateLiquidityAnalysisDto = new EstateLiquidityAnalysisDto();
            estateLiquidityAnalysisDto.setEstateName(entry.getKey().getName());
            analysisDtoMap.put(entry.getKey().getName(), estateLiquidityAnalysisDto);
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            String fieldName = dataReportAnalysis.getFieldName();
            switch (fieldName) {
                case AssessReportFieldConstant.ZONE_BIT_ANALYSIS://估价对象区位分析与估价区位分析
                    stringBuilder.append(StringUtils.repeat(" ",5)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(StringUtils.trim(generateCommonMethod.trim(getLocationAnalysis(estateGroupMap)))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.LOCATION_ANALYSIS://估价对象区位分析与估价区位分析
                    stringBuilder.append(StringUtils.repeat(" ",5)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getLocationAnalysis(estateGroupMap))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.UNIVERSALITY_ANALYSIS://变现能力通用性分析
                    stringBuilder.append(StringUtils.repeat(" ",1)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(StringUtils.trimToEmpty(generateCommonMethod.trim(this.getUniversalityAnalysis(estateGroupMap, projectInfo.getId(), analysisDtoMap)))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.INDEPENDENCE_ANALYSIS://独立性分析
                    stringBuilder.append(StringUtils.repeat(" ",5)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getIndependenceAnalysis(judgeObjectList))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.DIVISIBLE_ANALYSIS://可分割分析
                    stringBuilder.append(StringUtils.repeat(" ",5)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getDivisibleAnalysis(judgeObjectList))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.VALUE_ANALYSIS: //价值大小分析
                    stringBuilder.append(StringUtils.repeat(" ",5)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getValueAnalysis(judgeObjectList, areaGroupId))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.CASHABILITY_SUMMARY://变现能力综述
                {
                    DataReportTemplateItem templateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.CASHABILITY_SUMMARY_CONTENT);
                    HashSet<String> stringHashSet = Sets.newHashSet();
                    if (templateItem ==null){
                        continue;
                    }
                    if (!analysisDtoMap.isEmpty()){
                        for (Map.Entry<String, EstateLiquidityAnalysisDto> entry : analysisDtoMap.entrySet()) {
                            EstateLiquidityAnalysisDto analysisDto = entry.getValue();
                            String s = templateItem.getTemplate()
                                    .replace("#{通用性}", StringUtils.isEmpty(analysisDto.getGenerality()) ? "" : String.format(analysisDto.getGenerality()))
                                    .replace("#{独立使用性}", StringUtils.isEmpty(analysisDto.getIndependence()) ? "" : String.format(analysisDto.getIndependence()))
                                    .replace("#{单个产权面积}", StringUtils.isEmpty(analysisDto.getPropertyRight()) ? "" : String.format(analysisDto.getPropertyRight()));
                            String mobility = "好";
                            if (analysisDto.getGenerality().contains("弱") || analysisDto.getIndependence().contains("差")) {
                                mobility = "较差";
                            }
                            if (analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("适中")) {
                                mobility = "较好";
                            }
                            s = s.replace("#{流动性}", mobility);
                            stringHashSet.add(generateCommonMethod.trim(s));
                        }
                    }
                    if (CollectionUtils.isNotEmpty(stringHashSet)) {
                        stringBuilder.append(StringUtils.repeat(" ",5)).append(StringUtils.trim(StringUtils.join(stringHashSet, ""))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    }
                }
                break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 获取区位分析
     *
     * @param map
     * @return
     */
    public String getLocationAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            StringBuilder builder = new StringBuilder();
            BasicEstate basicEstate = entry.getKey();
            builder.append(erpAreaService.getSysAreaName(basicEstate.getDistrict())).append(basicEstate.getBlockName());
            if (basicEstate.getBlockId() != null) {
                DataBlock block = dataBlockService.getDataBlockById(basicEstate.getBlockId());
                if (block != null)
                    builder.append(block.getRemark());
            }
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), builder.toString()));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取区位分析
     *
     * @param map
     * @return
     */
    public String getLandEntityAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) throws Exception {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            StringBuilder builder = new StringBuilder();
            BasicEstate basicEstate = entry.getKey();
            builder.append(erpAreaService.getSysAreaName(basicEstate.getDistrict())).append(basicEstate.getName());
            builder.append(generateLandEntityService.getContent(basicApplyDao.getBasicApplyById(basicEstate.getApplyId())));
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), builder.toString()));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取建筑实体分析
     *
     * @param map
     * @param schemeAreaGroup
     * @return
     * @throws Exception
     */
    public String getBuildingEntityAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map, SchemeAreaGroup schemeAreaGroup) throws Exception {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            String content = generateHouseEntityService.getBuildEntityAnalysis(entry.getValue(), schemeAreaGroup);
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), content));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取通用性分析
     *
     * @param map
     * @return
     */
    public String getUniversalityAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map, Integer projectId, Map<String, EstateLiquidityAnalysisDto> analysisDtoMap) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            StringBuilder content = new StringBuilder("位于");
            List<Integer> judgeNumbers = Lists.newArrayList();
            Map<Integer, String> certUseMap = Maps.newHashMap();
            Map<Integer, String> practicalUseMap = Maps.newHashMap();
            for (SchemeJudgeObject schemeJudgeObject : entry.getValue()) {
                Integer number = generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber());
                judgeNumbers.add(number);
                certUseMap.put(number, schemeJudgeObject.getCertUse());
                practicalUseMap.put(number, schemeJudgeObject.getPracticalUse());
            }
            content.append(publicService.fusinString(LangUtils.transform(entry.getValue(), o -> o.getSeat()), true)).append("，");
            content.append(generateCommonMethod.judgeSummaryDesc(certUseMap, "证载用途", false)).append("，");
            content.append(generateCommonMethod.judgeSummaryDesc(practicalUseMap, "实际用途", false)).append("，其用途符合该区域的未来发展方向，");
            List<SurveyJudgeObjectGroupDto> list = surveyAssetInventoryRightRecordService.groupJudgeObject(projectId, entry.getValue());
            if (CollectionUtils.isNotEmpty(list)) {
                Map<Integer, String> comprehensiveMap = Maps.newHashMap();
                Map<Integer, String> tempMap = Maps.newHashMap();
                for (SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto : list) {
                    tempMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), surveyJudgeObjectGroupDto.getResult());
                    if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "强"))
                        comprehensiveMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "产权清晰、权利明确、无特定转让限制");
                    if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "一般"))
                        comprehensiveMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "产权清晰、权利明确、转让受特定限制");
                    if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "弱"))
                        comprehensiveMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "产权清晰、权利明确、转让受到限制");
                }
                content.append(generateCommonMethod.judgeSummaryDesc(comprehensiveMap, "", false)).append("，");
                String desc = generateCommonMethod.judgeSummaryDesc(tempMap, "通用性", false);
                EstateLiquidityAnalysisDto analysisDto = analysisDtoMap.get(entry.getKey().getName());
                analysisDto.setGenerality(desc);
                content.append(desc).append("，");
            }
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), content.toString()));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取独立性分析
     *
     * @param judgeObjectList
     * @return
     */
    public String getIndependenceAnalysis(List<SchemeJudgeObject> judgeObjectList) {
        DataReportTemplateItem intactTemplateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INDEPENDENCE_ANALYSIS_INTACT);
        DataReportTemplateItem damageTemplateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INDEPENDENCE_ANALYSIS_DAMAGE);
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            //对应资产清查内容
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
            if (!"不正常".equals(surveyAssetInventory.getRimIsNormal()) && !"损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                resultMap.put(number, intactTemplateItem.getTemplate());
            } else {
                StringBuilder damageContent = new StringBuilder();
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
                    resultMap.put(number, String.format("%s；估价对象不能独立使用。", damageContent));
                }
            }
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    /**
     * 获取可分割分析
     *
     * @param judgeObjectList
     * @return
     */
    public String getDivisibleAnalysis(List<SchemeJudgeObject> judgeObjectList) {
        Integer passId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE_PASS).getId(); //可办证
        Integer refuseId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE_REFUSE).getId();//不可办证
        //不可分
        DataReportTemplateItem impartibility = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_IMPARTIBILITY);
        //可分可办证
        DataReportTemplateItem detachableCanRush = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_DETACHABLE_CAN_RUSH);
        //可分不可办证
        DataReportTemplateItem detachableNotRush = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_DETACHABLE_NOT_RUSH);
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
            if ("不可分".equals(surveyAssetInventory.getSegmentationLimit())) {
                resultMap.put(number, impartibility.getTemplate());
            } else if ("可分".equals(surveyAssetInventory.getSegmentationLimit())) {
                if (refuseId.equals(Integer.valueOf(surveyAssetInventory.getCertificate()))) {
                    resultMap.put(number, detachableNotRush.getTemplate());
                } else {
                    resultMap.put(number, detachableCanRush.getTemplate());
                }
            }
        }
        String s = generateCommonMethod.judgeEachDesc(resultMap, "", "", false);
        return s;
    }

    /**
     * 获取开发程度分析
     *
     * @param map
     * @return
     */
    public String getDevelopmentLevelAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), entry.getKey().getLocationDescribe()));
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    /**
     * 获取估价对象价值大小
     *
     * @param judgeObjectList
     * @param areaGroupId
     * @return
     */
    public String getValueAnalysis(List<SchemeJudgeObject> judgeObjectList, Integer areaGroupId) {
        Integer num = judgeObjectList.size();
        BigDecimal totalRealEstate = generateCommonMethod.getTotalRealEstate(areaGroupId);
        BigDecimal rank = new BigDecimal("5000000");
        DataReportTemplateItem dataReportTemplateByField = null;
        if (rank.compareTo(totalRealEstate) == 1 && num < 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION1);
        }
        if (rank.compareTo(totalRealEstate) == 1 && num >= 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION2);
        }
        if (rank.compareTo(totalRealEstate) < 1 && num < 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION3);
        }
        if (rank.compareTo(totalRealEstate) < 1 && num >= 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION4);
        }
        return dataReportTemplateByField.getTemplate();
    }

    /**
     * 获取房地产市场状况
     *
     * @param map
     * @return
     */
    public String getHouseMarketCondition(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            BasicEstate basicEstate = entry.getKey();
            for (SchemeJudgeObject schemeJudgeObject : entry.getValue()) {
                Integer number = generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber()));
                DataBestUseDescription bestUseDescription = dataBestUseDescriptionService.getCacheBestUseDescriptionById(schemeJudgeObject.getBestUse());
                String bestUseName = bestUseDescription == null ? "" : bestUseDescription.getName();
                resultMap.put(number, String.format("%s%s", erpAreaService.getSysAreaName(basicEstate.getDistrict()), bestUseName));
            }
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    /**
     * 获取其它分析
     *
     * @param judgeObjectList
     * @return
     */
    public String getOtherAnalysis(List<SchemeJudgeObject> judgeObjectList) {
        Integer pledgeId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_PLEDGE).getId();
        Integer otherId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER).getId();
        Integer rentId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_LEASEHOLD).getId();

        DataReportTemplateItem pledgeTemplate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_PLEDGE);
        DataReportTemplateItem rentTemplate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_RENT);
        DataReportTemplateItem otherTemplate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_OTHER);
        Map<Integer, String> pledgeMap = Maps.newHashMap();
        Map<Integer, String> rentMap = Maps.newHashMap();
        Map<Integer, String> otherMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            //对应的他权信息
            List<SurveyAssetInventoryRight> rightList = Lists.newArrayList();
            List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(judgeObject.getDeclareRecordId(), judgeObject.getProjectId());
            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                rightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(surveyAssetInventoryRightRecordList.get(0).getId());
            }
            for (SurveyAssetInventoryRight inventoryRight : rightList) {
                if (pledgeId.equals(inventoryRight.getCategory())) {//抵押
                    pledgeMap.put(number, pledgeTemplate.getTemplate().replace("#{他权描述}", StringUtils.defaultString(inventoryRight.getInfluence())));
                }
                if (rentId.equals(inventoryRight.getCategory())) {//出租
                    rentMap.put(number, rentTemplate.getTemplate().replace("#{他权描述}", StringUtils.defaultString(inventoryRight.getInfluence())));
                }
                if (otherId.equals(inventoryRight.getCategory())) {//其它
                    otherMap.put(number, otherTemplate.getTemplate().replace("#{他权描述}", StringUtils.defaultString(inventoryRight.getInfluence())));
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!pledgeMap.isEmpty())
            stringBuilder.append(generateCommonMethod.judgeEachDesc(pledgeMap, "", "。", true));
        if (!rentMap.isEmpty())
            stringBuilder.append(generateCommonMethod.judgeEachDesc(rentMap, "", "。", true));
        if (!otherMap.isEmpty())
            stringBuilder.append(generateCommonMethod.judgeEachDesc(otherMap, "", "。", true));
        if (pledgeMap.isEmpty() && rentMap.isEmpty() && otherMap.isEmpty()) {
            stringBuilder.append("无抵押，无租赁，无典当，无继承，无担保，无查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷。");
        }
        return stringBuilder.toString();
    }
}

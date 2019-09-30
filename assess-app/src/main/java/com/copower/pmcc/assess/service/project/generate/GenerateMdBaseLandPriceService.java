package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.BaseReportFieldMdBaseLandPriceEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandDetailAchievementService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateMdBaseLandPriceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer mlId;
    private Integer schemeJudgeObjectId;
    private Integer projectId;
    ;
    private Integer areaId;
    private MdBaseLandPrice mdBaseLandPrice;
    private SchemeAreaGroup schemeAreaGroup;
    private MdBaseLandPriceService mdBaseLandPriceService;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private GenerateCommonMethod generateCommonMethod;
    private SchemeAreaGroupService schemeAreaGroupService;
    private DeclareRecordService declareRecordService;
    private SurveyCommonService surveyCommonService;
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private BasicEstateService basicEstateService;
    private DataLandLevelDetailService dataLandLevelDetailService;
    private DataLandLevelService dataLandLevelService;
    private BasicEstateLandStateService basicEstateLandStateService;
    private BaseDataDicService baseDataDicService;
    private ProjectInfoService projectInfoService;
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    private DataHousePriceIndexDetailDao dataHousePriceIndexDetailDao;
    private ToolRewardRateService toolRewardRateService;
    private DataAllocationCorrectionCoefficientVolumeRatioDao dataLandDetailAchievementDao;
    private DataAllocationCorrectionCoefficientVolumeRatioDetailDao dataAllocationCorrectionCoefficientVolumeRatioDetailDao;
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    private DataLandDetailAchievementService dataLandDetailAchievementService;
    private BaseProjectClassifyService baseProjectClassifyService;

    private GenerateMdBaseLandPriceService() {
    }

    public GenerateMdBaseLandPriceService(Integer mlId, Integer schemeJudgeObjectId, Integer areaId, Integer projectId) throws Exception {
        this.mlId = mlId;
        this.schemeJudgeObjectId = schemeJudgeObjectId;
        this.areaId = areaId;
        this.projectId = projectId;
        ;
        this.mdBaseLandPriceService = SpringContextUtils.getBean(MdBaseLandPriceService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.surveyCommonService = SpringContextUtils.getBean(SurveyCommonService.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.basicEstateService = SpringContextUtils.getBean(BasicEstateService.class);
        this.dataLandLevelDetailService = SpringContextUtils.getBean(DataLandLevelDetailService.class);
        this.dataLandLevelService = SpringContextUtils.getBean(DataLandLevelService.class);
        this.basicEstateLandStateService = SpringContextUtils.getBean(BasicEstateLandStateService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.projectInfoService = SpringContextUtils.getBean(ProjectInfoService.class);
        this.dataHousePriceIndexDao = SpringContextUtils.getBean(DataHousePriceIndexDao.class);
        this.dataHousePriceIndexDetailDao = SpringContextUtils.getBean(DataHousePriceIndexDetailDao.class);
        this.toolRewardRateService = SpringContextUtils.getBean(ToolRewardRateService.class);
        this.dataLandDetailAchievementDao = SpringContextUtils.getBean(DataAllocationCorrectionCoefficientVolumeRatioDao.class);
        this.dataAllocationCorrectionCoefficientVolumeRatioDetailDao = SpringContextUtils.getBean(DataAllocationCorrectionCoefficientVolumeRatioDetailDao.class);
        this.declareRealtyHouseCertService = SpringContextUtils.getBean(DeclareRealtyHouseCertService.class);
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.declareRealtyRealEstateCertService = SpringContextUtils.getBean(DeclareRealtyRealEstateCertService.class);
        this.dataLandDetailAchievementService = SpringContextUtils.getBean(DataLandDetailAchievementService.class);
        this.baseProjectClassifyService = SpringContextUtils.getBean(BaseProjectClassifyService.class);

    }

    /**
     * 获取基准地价法主表
     *
     * @return
     */
    public MdBaseLandPrice getMdBaseLandPrice() {
        if (mdBaseLandPrice != null) return mdBaseLandPrice;
        MdBaseLandPrice mdBaseLandPrice = mdBaseLandPriceService.getSingleObject(this.mlId);
        this.mdBaseLandPrice = mdBaseLandPrice;
        return mdBaseLandPrice;
    }

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            this.schemeAreaGroup = schemeAreaGroupService.get(areaId);
        }
        return this.schemeAreaGroup;
    }

    private DataLandLevelDetail getDataLandLevelDetail(SchemeJudgeObject schemeJudgeObject) {
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
        BasicEstate estateByApplyId = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(estateByApplyId.getId());
        DataLandLevelDetail dataLandLevelDetailById = dataLandLevelDetailService.getDataLandLevelDetailById(landStateByEstateId.getLandLevel());
        return dataLandLevelDetailById;
    }

    //生成文件
    public String generateBaseLandPriceFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.BASE_LAND_PRICE_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Map<String, String> fileMap = AsposeUtils.getRegexExtendList(document);
        Map<String, String> textMap = AsposeUtils.getRegexExtendList(document);

        try {
            //文件替换
            if (fileMap != null && fileMap.size() > 0) {
                for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                    fileMap.put(entry.getKey(), this.getValueByKey(entry.getValue()));
                }
                AsposeUtils.replaceTextToFile(localPath, fileMap);
            }
            //文本替换
            if (textMap != null && textMap.size() > 0) {
                for (Map.Entry<String, String> entry : textMap.entrySet()) {
                    textMap.put(entry.getKey(), this.getValueText(entry.getValue()));
                }
                AsposeUtils.replaceText(localPath, textMap);
            }
        } catch (Exception e) {
            String error = e.getMessage();
            logger.error(error, e);
        }
        return localPath;
    }

    public String getValueByKey(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        BaseReportFieldMdBaseLandPriceEnum baseLandPriceEnum = BaseReportFieldMdBaseLandPriceEnum.getEnumByName(key);

        String localPath = null;
        if (baseLandPriceEnum != null) {
            String title = baseLandPriceEnum.getName();
            switch (baseLandPriceEnum) {
                case BaseLandPriceSchedule:
                    localPath = getBaseLandPriceSchedule(title);
                    break;
                case BaseLandPriceFactorExplain:
                    localPath = getBaseLandPriceFactorExplain(false);
                    break;
                case BaseLandPriceFactorCoefficient:
                    localPath = getBaseLandPriceFactorExplain(true);
                    break;
                case BaseLandPriceFactorAmend:
                    localPath = getBaseLandPriceFactorAmend();
                    break;
                case BaseLandPriceCalculateCourseDetail:
                    localPath = getBaseLandPriceCalculateCourseDetail();
                    break;
            }
        }
        return localPath;
    }

    /**
     * 基准地价测算过程明细表
     *
     * @return
     */
    public String getBaseLandPriceCalculateCourseDetail() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //设置表格属性
        generateCommonMethod.settingBuildingTable(builder);
        //基准地价系数修正法数据表
        MdBaseLandPrice mdBaseLandPrice = getMdBaseLandPrice();
        //头
        builder.insertCell();
        builder.write("项目");
        builder.insertCell();
        builder.write("参数");
        builder.insertCell();
        builder.write("参数与备注");
        builder.endRow();
        //基准地价
        builder.insertCell();
        builder.write("基准地价（元/㎡）");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getStandardPremium().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        //期日修正系数
        builder.insertCell();
        builder.write("期日修正系数");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getDateAmend().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        //年期修正系数
        builder.insertCell();
        builder.write("年期修正系数");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getPeriodAmend().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        //土地还原率
        builder.insertCell();
        builder.write("土地还原率");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getRewardRate());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        //法定年限
        builder.insertCell();
        builder.write("法定年限");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getLegalAge().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        //剩余使用年限
        builder.insertCell();
        builder.write("剩余使用年限");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getLandSurplusYear().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        //容积率修正
        if (mdBaseLandPrice.getHasFractionAmend()) {
            builder.insertCell();
            builder.write("容积率修正");
            builder.insertCell();
            builder.write(mdBaseLandPrice.getVolumeFractionAmend().toString());
            builder.insertCell();
            builder.write("");
            builder.endRow();
        }
        //开发程度修正
        if (mdBaseLandPrice.getHasDevelopCorrect()) {
            builder.insertCell();
            builder.write("开发程度修正");
            builder.insertCell();
            builder.write(mdBaseLandPrice.getLandSurplusYear().toString());
            builder.insertCell();
            builder.write("与基准地价的开发程度一致");
            builder.endRow();
        }
        //委估宗地单价（元/㎡）
        builder.insertCell();
        builder.write("委估宗地单价（元/㎡）");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getParcelPrice().toString());
        builder.insertCell();
        builder.write("地价＝P×K1×K2×……×（1±∑K）±L");
        builder.endRow();
        doc.save(localPath);
        //委估宗地单价（万元/亩）
        builder.insertCell();
        builder.write("委估宗地单价（万元/亩）");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getParcelBhouPrice().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        doc.save(localPath);
        //委估宗地面积（㎡）
        builder.insertCell();
        builder.write("委估宗地面积（㎡）");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getEvaluationArea().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        doc.save(localPath);
        //委估宗地总价（万元）
        builder.insertCell();
        builder.write("委估宗地总价（万元）");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getParcelTotalPrice().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        doc.save(localPath);
        //委估宗地总价（万元）
        builder.insertCell();
        builder.write("委估宗地总价（万元）");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getParcelTotalPrice().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();
        doc.save(localPath);

        ProjectInfo projectInfoById = projectInfoService.getProjectInfoById(projectId);
        //房产
        BaseProjectClassify type = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE);
        //楼面地价
        if (projectInfoById.getProjectCategoryId().equals(type)) {
            builder.insertCell();
            builder.write("楼面地价");
            builder.insertCell();
            builder.write(mdBaseLandPrice.getFloorPremium().toString());
            builder.insertCell();
            builder.write(mdBaseLandPrice.getVolumetricRate().toString());
            builder.endRow();
        }
        doc.save(localPath);

        return localPath;
    }


    /**
     * 基准地价因素修正表
     *
     * @return
     */
    public String getBaseLandPriceFactorAmend() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //设置表格属性
        generateCommonMethod.settingBuildingTable(builder);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
        BasicEstate estateByApplyId = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        BasicEstateLandState landStateByEstate = basicEstateLandStateService.getLandStateByEstateId(estateByApplyId.getId());
        //个别因素
        BaseDataDic dataIndividual = baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.PROGRAMME_MARKET_COSTAPPROACH_FACTOR_INDIVIDUAL);
        //区域因素
        BaseDataDic area = baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.PROGRAMME_MARKET_COSTAPPROACH_FACTOR_AREA);
        //因素等级
        List<BaseDataDic> dataGradeList = baseDataDicService.getCacheDataDicList(AssessReportFieldConstant.PROGRAMME_MARKET_COSTAPPROACH_GRADE);
        BaseDataDic excellent = baseDataDicService.getDataDicByName(dataGradeList, "优");
        BaseDataDic comparativelyExcellent = baseDataDicService.getDataDicByName(dataGradeList, "较优");
        BaseDataDic ordinary = baseDataDicService.getDataDicByName(dataGradeList, "一般");
        BaseDataDic inferior = baseDataDicService.getDataDicByName(dataGradeList, "较劣");
        BaseDataDic bad = baseDataDicService.getDataDicByName(dataGradeList, "劣");
        List<DataLandDetailAchievement> dataAll = JSON.parseArray(landStateByEstate.getLandLevelContent(), DataLandDetailAchievement.class);
        //个人因素土地级别详情从表
        List<DataLandDetailAchievement> individualAchievementList = Lists.newArrayList();
        //区域因素土地级别详情从表
        List<DataLandDetailAchievement> areaList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataAll)) {
            for (DataLandDetailAchievement item : dataAll) {
                if (item.getType().equals(dataIndividual.getId())) {
                    individualAchievementList.add(item);
                }
                if (item.getType().equals(area.getId())) {
                    areaList.add(item);
                }
            }
        }
        List<String> individualCategorys = getCategoryFromLandDetail(individualAchievementList);
        List<String> areaCategorys = getCategoryFromLandDetail(areaList);
        //生成表格
        Table table = builder.startTable();
        builder.insertCell();
        builder.insertCell();
        String level[] = {"优", "较优", "一般", "较劣", "劣"};
        for (int i = 0; i < level.length; i++) {
            builder.insertCell();
            builder.write(level[i]);
        }
        builder.endRow();
        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        mergeCellModelList.add(new MergeCellModel(0, 0, 0, 1));

        if (CollectionUtils.isNotEmpty(individualCategorys)) {
            int firstCellRow = 1;
            //个人因素
            for (int i = 0; i < individualCategorys.size(); i++) {
                builder.insertCell();
                if (i == 0) {
                    builder.write("个别因素");
                } else {
                    builder.write("");
                }
                //BaseDataDic categoryDic = baseDataDicService.getDataDicById(individualCategorys.get(i));
                builder.insertCell();
                builder.write(individualCategorys.get(i));
                //优
                DataLandDetailAchievement individualAchievement = getLandDetailFromJsonContent(individualAchievementList, dataLandLevelDetail.getId(), individualCategorys.get(i), excellent.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, individualAchievement, false);
                //较优
                DataLandDetailAchievement comparativelyExcellentAchievement = getLandDetailFromJsonContent(individualAchievementList, dataLandLevelDetail.getId(), individualCategorys.get(i), comparativelyExcellent.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, comparativelyExcellentAchievement, false);
                //一般
                DataLandDetailAchievement ordinaryAchievement = getLandDetailFromJsonContent(individualAchievementList, dataLandLevelDetail.getId(), individualCategorys.get(i), ordinary.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, ordinaryAchievement, false);
                //较劣
                DataLandDetailAchievement inferiorAchievement = getLandDetailFromJsonContent(individualAchievementList, dataLandLevelDetail.getId(), individualCategorys.get(i), inferior.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, inferiorAchievement, false);
                //劣
                DataLandDetailAchievement badAchievement = getLandDetailFromJsonContent(individualAchievementList, dataLandLevelDetail.getId(), individualCategorys.get(i), bad.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, badAchievement, false);
                builder.endRow();

                builder.insertCell();
                builder.write("");
                builder.insertCell();
                builder.write("");
                //优
                builder.insertCell();
                writeAchievement(builder, individualAchievement, true);
                //较优
                builder.insertCell();
                writeAchievement(builder, comparativelyExcellentAchievement, true);
                //一般
                builder.insertCell();
                writeAchievement(builder, ordinaryAchievement, true);
                //较劣
                builder.insertCell();
                writeAchievement(builder, inferiorAchievement, true);
                //劣
                builder.insertCell();
                writeAchievement(builder, badAchievement, true);
                builder.endRow();
                mergeCellModelList.add(new MergeCellModel(firstCellRow, 1, firstCellRow + 1, 1));
                firstCellRow += 2;
            }
            mergeCellModelList.add(new MergeCellModel(1, 0, individualCategorys.size() * 2, 0));
        }
        if (CollectionUtils.isNotEmpty(areaCategorys)) {
            int firstCellRow = individualCategorys.size() * 2;
            //区域因素
            for (int i = 0; i < areaCategorys.size(); i++) {
                builder.insertCell();
                if (i == 0) {
                    builder.write("区域因素");
                } else {
                    builder.write("");
                }
                //BaseDataDic categoryDic = baseDataDicService.getDataDicById(areaCategorys.get(i));
                builder.insertCell();
                builder.write(areaCategorys.get(i));
                //优
                DataLandDetailAchievement individualAchievement = getLandDetailFromJsonContent(areaList, dataLandLevelDetail.getId(), areaCategorys.get(i), excellent.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, individualAchievement, false);
                //较优
                DataLandDetailAchievement comparativelyExcellentAchievement = getLandDetailFromJsonContent(areaList, dataLandLevelDetail.getId(), areaCategorys.get(i), comparativelyExcellent.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, comparativelyExcellentAchievement, false);
                //一般
                DataLandDetailAchievement ordinaryAchievement = getLandDetailFromJsonContent(areaList, dataLandLevelDetail.getId(), areaCategorys.get(i), ordinary.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, ordinaryAchievement, false);
                //较劣
                DataLandDetailAchievement inferiorAchievement = getLandDetailFromJsonContent(areaList, dataLandLevelDetail.getId(), areaCategorys.get(i), inferior.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, inferiorAchievement, false);
                //劣
                DataLandDetailAchievement badAchievement = getLandDetailFromJsonContent(areaList, dataLandLevelDetail.getId(), areaCategorys.get(i), bad.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, badAchievement, false);
                builder.endRow();

                builder.insertCell();
                builder.write("");
                builder.insertCell();
                builder.write("");
                //优
                builder.insertCell();
                writeAchievement(builder, individualAchievement, true);
                //较优
                builder.insertCell();
                writeAchievement(builder, comparativelyExcellentAchievement, true);
                //一般
                builder.insertCell();
                writeAchievement(builder, ordinaryAchievement, true);
                //较劣
                builder.insertCell();
                writeAchievement(builder, inferiorAchievement, true);
                //劣
                builder.insertCell();
                writeAchievement(builder, badAchievement, true);
                builder.endRow();
                mergeCellModelList.add(new MergeCellModel(firstCellRow + 1, 1, firstCellRow + 2, 1));
                firstCellRow += 2;
            }
            mergeCellModelList.add(new MergeCellModel(individualCategorys.size() * 2 + 1, 0, individualCategorys.size() * 2 + areaCategorys.size() * 2, 0));
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);

        doc.save(localPath);
        return localPath;
    }

    public DataLandDetailAchievement getLandDetailFromJsonContent(List<DataLandDetailAchievement> data, Integer levelDetailId, String category, Integer grade, Integer type) {
        for (DataLandDetailAchievement item : data) {
            if (item.getLevelDetailId().equals(levelDetailId) && item.getCategory().equals(category) && item.getGrade().equals(grade) && item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }


    /**
     * 基准地价因素说明/系数表
     *
     * @return
     */
    public String getBaseLandPriceFactorExplain(boolean isIndex) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //设置表格属性
        generateCommonMethod.settingBuildingTable(builder);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        //个别因素
        BaseDataDic dataIndividual = baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.PROGRAMME_MARKET_COSTAPPROACH_FACTOR_INDIVIDUAL);
        //区域因素
        BaseDataDic area = baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.PROGRAMME_MARKET_COSTAPPROACH_FACTOR_AREA);
        //因素等级
        List<BaseDataDic> dataGradeList = baseDataDicService.getCacheDataDicList(AssessReportFieldConstant.PROGRAMME_MARKET_COSTAPPROACH_GRADE);
        BaseDataDic excellent = baseDataDicService.getDataDicByName(dataGradeList, "优");
        BaseDataDic comparativelyExcellent = baseDataDicService.getDataDicByName(dataGradeList, "较优");
        BaseDataDic ordinary = baseDataDicService.getDataDicByName(dataGradeList, "一般");
        BaseDataDic inferior = baseDataDicService.getDataDicByName(dataGradeList, "较劣");
        BaseDataDic bad = baseDataDicService.getDataDicByName(dataGradeList, "劣");
        //个人因素土地级别详情从表
        DataLandDetailAchievement dataLandDetailIndividual = new DataLandDetailAchievement();
        dataLandDetailIndividual.setLevelDetailId(dataLandLevelDetail.getId());
        dataLandDetailIndividual.setType(dataIndividual.getId());
        List<DataLandDetailAchievement> individualAchievementList = dataLandDetailAchievementService.getDataLandDetailAchievementList(dataLandDetailIndividual);
        List<String> individualCategorys = getCategoryFromLandDetail(individualAchievementList);

        //区域因素土地级别详情从表
        DataLandDetailAchievement dataLandDetailArea = new DataLandDetailAchievement();
        dataLandDetailArea.setLevelDetailId(dataLandLevelDetail.getId());
        dataLandDetailArea.setType(area.getId());
        List<DataLandDetailAchievement> areaAchievementList = dataLandDetailAchievementService.getDataLandDetailAchievementList(dataLandDetailArea);
        List<String> areaCategorys = getCategoryFromLandDetail(areaAchievementList);
        //生成表格
        Table table = builder.startTable();
        builder.insertCell();
        builder.insertCell();
        String level[] = {"优", "较优", "一般", "较劣", "劣"};
        for (int i = 0; i < level.length; i++) {
            builder.insertCell();
            builder.write(level[i]);
        }
        builder.endRow();
        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        mergeCellModelList.add(new MergeCellModel(0, 0, 0, 1));

        if (CollectionUtils.isNotEmpty(individualCategorys)) {
            //个人因素
            for (int i = 0; i < individualCategorys.size(); i++) {
                builder.insertCell();
                if (i == 0) {
                    builder.write("个别因素");
                } else {
                    builder.write("");
                }
                //BaseDataDic categoryDic = baseDataDicService.getDataDicById(individualCategorys.get(i));
                builder.insertCell();
                builder.write(individualCategorys.get(i));
                //优
                DataLandDetailAchievement individualAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), individualCategorys.get(i), excellent.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, individualAchievement, isIndex);
                //较优
                DataLandDetailAchievement comparativelyExcellentAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), individualCategorys.get(i), comparativelyExcellent.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, comparativelyExcellentAchievement, isIndex);
                //一般
                DataLandDetailAchievement ordinaryAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), individualCategorys.get(i), ordinary.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, ordinaryAchievement, isIndex);
                //较劣
                DataLandDetailAchievement inferiorAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), individualCategorys.get(i), inferior.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, inferiorAchievement, isIndex);
                //劣
                DataLandDetailAchievement badAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), individualCategorys.get(i), bad.getId(), dataIndividual.getId());
                builder.insertCell();
                writeAchievement(builder, badAchievement, isIndex);
                builder.endRow();
            }
            mergeCellModelList.add(new MergeCellModel(1, 0, individualCategorys.size(), 0));
        }
        if (CollectionUtils.isNotEmpty(areaCategorys)) {
            //区域因素
            for (int i = 0; i < areaCategorys.size(); i++) {
                builder.insertCell();
                if (i == 0) {
                    builder.write("区域因素");
                } else {
                    builder.write("");
                }
                //BaseDataDic categoryDic = baseDataDicService.getDataDicById(areaCategorys.get(i));
                builder.insertCell();
                builder.write(areaCategorys.get(i));
                //优
                DataLandDetailAchievement individualAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), areaCategorys.get(i), excellent.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, individualAchievement, isIndex);
                //较优
                DataLandDetailAchievement comparativelyExcellentAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), areaCategorys.get(i), comparativelyExcellent.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, comparativelyExcellentAchievement, isIndex);
                //一般
                DataLandDetailAchievement ordinaryAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), areaCategorys.get(i), ordinary.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, ordinaryAchievement, isIndex);
                //较劣
                DataLandDetailAchievement inferiorAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), areaCategorys.get(i), inferior.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, inferiorAchievement, isIndex);
                //劣
                DataLandDetailAchievement badAchievement = dataLandDetailAchievementService.getDataLandDetailAchievement(dataLandLevelDetail.getId(), areaCategorys.get(i), bad.getId(), area.getId());
                builder.insertCell();
                writeAchievement(builder, badAchievement, isIndex);
                builder.endRow();
            }
            mergeCellModelList.add(new MergeCellModel(individualCategorys.size() + 1, 0, individualCategorys.size() + areaCategorys.size(), 0));
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);

        doc.save(localPath);
        return localPath;
    }

    public void writeAchievement(DocumentBuilder builder, DataLandDetailAchievement data, boolean isIndex) throws Exception {
        if (data != null) {
            if (isIndex) {
                builder.write(data.getAchievement().toString());
            } else {
                builder.write(data.getReamark());
            }
        } else {
            builder.write("");
        }
    }

    /**
     * 基准地价价格一览表
     *
     * @return
     */
    public String getBaseLandPriceSchedule(String title) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //设置表格属性
        generateCommonMethod.settingBuildingTable(builder);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        DataLandLevel dataLandLevel = dataLandLevelService.getDataLandLevelById(dataLandLevelDetail.getLandLevelId());
        //对应区域下所有的土地级别
        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailService.getDataLandLevelDetailListByPid(dataLandLevel.getId());
        Map<String, Integer> map = countProperty(dataLandLevelDetailList);
        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        //最大级数
        Integer maxValue = list.get(list.size() - 1).getValue();
        Table table = builder.startTable();
        builder.insertCell();
        builder.insertCell();
        for (int i = 1; i < maxValue; i++) {
            builder.insertCell();
            builder.write(intToRoman(i));
        }
        builder.endRow();
        mergeCellModelList.add(new MergeCellModel(0, 0, 0, 1));
        int firstCellRow = 1;
        for (String key : map.keySet()) {
            builder.insertCell();
            builder.write(key);
            builder.insertCell();
            builder.write("元/平方米");
            for (int i = 1; i < maxValue; i++) {
                builder.insertCell();
                DataLandLevelDetail data = dataLandLevelDetailService.getDataByClassifyAndType(key, intToRoman(i), dataLandLevel.getId());
                if (data != null) {
                    builder.write(data.getPrice().setScale(0, BigDecimal.ROUND_HALF_UP).toString());
                } else {
                    builder.write("");
                }
            }
            builder.endRow();

            builder.insertCell();
            builder.write("");
            builder.insertCell();
            builder.write("万元/亩");
            for (int i = 1; i < maxValue; i++) {
                builder.insertCell();
                DataLandLevelDetail data = dataLandLevelDetailService.getDataByClassifyAndType(key, intToRoman(i), dataLandLevel.getId());
                if (data != null) {
                    builder.write(data.getPrice().multiply(new BigDecimal("666.67")).divide(new BigDecimal("10000")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
                } else {
                    builder.write("");
                }

            }
            builder.endRow();
            mergeCellModelList.add(new MergeCellModel(firstCellRow, 0, firstCellRow + 1, 0));
            firstCellRow += 2;
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);

        doc.save(localPath);
        return localPath;
    }


    /**
     * 根据字段key值获取被替换的文本
     *
     * @param key
     * @return
     */
    public String getValueText(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        BaseReportFieldMdBaseLandPriceEnum baseLandPriceEnum = BaseReportFieldMdBaseLandPriceEnum.getEnumByName(key);

        String str = null;
        if (baseLandPriceEnum != null) {
            switch (baseLandPriceEnum) {
                case BaseLandPriceProfile:
                    str = getBaseLandPriceProfile();
                    break;
                case BaseLandPriceArea:
                    str = getBaseLandPriceArea();
                    break;
                case BaseLandPriceLevelName:
                    str = getBaseLandPriceLevelName();
                    break;
                case BaseLandPriceUnivalence:
                    str = getBaseLandPriceUnivalence();
                    break;
                case BaseLandPriceDateAmendCalculatedMode:
                    str = getBaseLandPriceDateAmendCalculatedMode();
                    break;
                case BaseLandPriceDateAmendCalculatedResult:
                    str = getBaseLandPriceDateAmendCalculatedResult();
                    break;
                case BaseLandPriceDurableYears:
                    str = getBaseLandPriceDurableYears();
                    break;
                case BaseLandPriceResidueYears:
                    str = getBaseLandPriceResidueYears();
                    break;
                case BaseLandPriceOpportunityCostExplain:
                    str = getBaseLandPriceOpportunityCostExplain();
                    break;
                case BaseLandPriceOpportunityCostRatio:
                    str = getBaseLandPriceOpportunityCostRatio();
                    break;
                case BaseLandPriceSetPurpose:
                    str = getBaseLandPriceSetPurpose();
                    break;
                case BaseLandPriceRewardRatio:
                    str = getBaseLandPriceRewardRatio();
                    break;
                case BaseLandPricePlotRatioAmendExplain:
                    str = getBaseLandPricePlotRatioAmendExplain();
                    break;
                case BaseLandPeriodAmend:
                    str = getBaseLandPeriodAmend();
                    break;
                case BaseLandPriceDevelopmentLevelAmendExplain:
                    str = getBaseLandPriceDevelopmentLevelAmendExplain();
                    break;
                case BaseLandPriceParcelUnivalence:
                    str = getBaseLandPriceParcelUnivalence();
                    break;
                case BaseLandPriceAssessArea:
                    str = getBaseLandPriceAssessArea();
                    break;
                case BaseLandPriceParcelArea:
                    str = getBaseLandPriceParcelArea();
                    break;
                case BaseLandPriceVolumeFraction:
                    str = getBaseLandPriceVolumeFraction();
                    break;
                case BaseLandPriceFloorPremium:
                    str = getBaseLandPriceFloorPremium();
                    break;
                case BaseLandPriceIndex:
                    str = getBaseLandPriceIndex();
                    break;
            }
        }

        return str;
    }

    /**
     * 功能描述: 基准地价基础指数表
     */
    public String getBaseLandPriceIndex() {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
        List<DataHousePriceIndex> dataHouseIndexList = Lists.newArrayList();
        dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), declareRecord.getDistrict(), dataDic.getId());
        if (CollectionUtils.isEmpty(dataHouseIndexList)) {
            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), null, dataDic.getId());
        }
        StringBuilder content = new StringBuilder();
        if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
            Integer masterId = dataHouseIndexList.get(0).getId();
            DataHousePriceIndexDetail dataHousePriceIndexDetail = new DataHousePriceIndexDetail();
            dataHousePriceIndexDetail.setHousePriceId(masterId);
            List<DataHousePriceIndexDetail> detailList = dataHousePriceIndexDetailDao.getDataHousePriceIndexDetailList(dataHousePriceIndexDetail);
            for (DataHousePriceIndexDetail item : detailList) {
                content.append(DateUtils.formatDate(item.getStartDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.formatDate(item.getEndDate(), DateUtils.DATE_CHINESE_PATTERN)).append("指数为").append(item.getIndexNumber()).append("，");
            }
            content.deleteCharAt(content.length() - 1).append("。");
        }
        return content.toString();
    }

    /**
     * 功能描述: 基准地价楼面地价
     */
    public String getBaseLandPriceFloorPremium() {
        return getMdBaseLandPrice().getFloorPremium().toString();
    }

    /**
     * 功能描述: 基准地价容积率
     */
    public String getBaseLandPriceVolumeFraction() {
        return getMdBaseLandPrice().getVolumetricRate().toString();
    }

    /**
     * 功能描述: 基准地价宗地面积(总土地面积)
     */
    public String getBaseLandPriceParcelArea() {
        return getMdBaseLandPrice().getEvaluationArea().toString();
    }

    /**
     * 功能描述: 基准地价评估面积（总建筑面积）
     */
    public String getBaseLandPriceAssessArea() {
        //获取区域下完整的委估对象
        List<SchemeJudgeObject> list = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(areaId);
        BigDecimal result = new BigDecimal("0.00");
        if (CollectionUtils.isNotEmpty(list)) {
            for (SchemeJudgeObject item : list) {
                DeclareRecord declareRecordById = declareRecordService.getDeclareRecordById(item.getDeclareRecordId());
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(declareRecordById.getId());
                BasicEstate estateByApplyId = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                if (estateByApplyId.getFloorArea() != null) {
                    result.add(estateByApplyId.getFloorArea());
                }
            }
        }
        return result.toString();
    }

    /**
     * 功能描述: 基准地价宗地单价
     */
    public String getBaseLandPriceParcelUnivalence() {
        return getMdBaseLandPrice().getParcelPrice().toString();
    }

    /**
     * 功能描述: 基准地价开发程度修正说明
     */
    public String getBaseLandPriceDevelopmentLevelAmendExplain() {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        String setUseName = baseDataDicService.getNameById(schemeJudgeObject.getSetUse());
        if (!getMdBaseLandPrice().getHasDevelopCorrect()) {
            return String.format("由于本次估价对象%s用地设定的开发程度与对应的基准地价开发程度一致，因此不需要对开发程度修正。", setUseName);
        }
        return String.format("由于本次估价对象%s用地设定的开发程度与对应的基准地价开发程度不一致，因此综合考虑需要对估价对象开发程度修正%s元/㎡。", setUseName, getMdBaseLandPrice().getDevelopCorrect());
    }

    /**
     * 功能描述: 基准地价年期修正系数
     */
    public String getBaseLandPeriodAmend() {
        return getMdBaseLandPrice().getPeriodAmend().toString();
    }

    /**
     * 功能描述: 基准地价容积率修正系数说明
     */
    public String getBaseLandPricePlotRatioAmendExplain() {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        String setUseName = baseDataDicService.getNameById(schemeJudgeObject.getSetUse());
        if (!getMdBaseLandPrice().getHasFractionAmend()) {
            return String.format("考虑容积率对%s用地的影响不大，故不对容积率进行修正。", setUseName);
        }

        String s = "K3=%s=%s。";
        //根据容积率找到配置中对应的容积率修正
        List<DataAllocationCorrectionCoefficientVolumeRatio> coefficientVolumeRatioList;
        coefficientVolumeRatioList = dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioList(getSchemeAreaGroup().getProvince(), getSchemeAreaGroup().getCity(), getSchemeAreaGroup().getDistrict());
        if (CollectionUtils.isEmpty(coefficientVolumeRatioList)) {
            coefficientVolumeRatioList = dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioList(getSchemeAreaGroup().getProvince(), getSchemeAreaGroup().getCity(), null);
        }
        if (CollectionUtils.isNotEmpty(coefficientVolumeRatioList)) {
            Integer masterId = coefficientVolumeRatioList.get(0).getId();
            DataAllocationCorrectionCoefficientVolumeRatioDetail coefficientVolumeRatioDetail = new DataAllocationCorrectionCoefficientVolumeRatioDetail();
            coefficientVolumeRatioDetail.setAllocationVolumeRatioId(masterId);
            List<DataAllocationCorrectionCoefficientVolumeRatioDetail> detailList = dataAllocationCorrectionCoefficientVolumeRatioDetailDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailList(coefficientVolumeRatioDetail);
            for (DataAllocationCorrectionCoefficientVolumeRatioDetail detailItem : detailList) {
                //直接匹配
                if (detailItem.getPlotRatio().compareTo(getMdBaseLandPrice().getVolumetricRate()) == 0) {
                    return String.format(s, detailItem.getCorrectionFactor(), detailItem.getCorrectionFactor());
                }
            }
            //不能直接匹配
            return getAmend(detailList, getMdBaseLandPrice().getVolumetricRate(), s);
        }
        return null;

    }

    public String getAmend(List<DataAllocationCorrectionCoefficientVolumeRatioDetail> detailList, BigDecimal volumetricRate, String s) {
        String course = "";
        if (detailList.size() >= 2) {
            //排序
            Ordering<DataAllocationCorrectionCoefficientVolumeRatioDetail> ordering = Ordering.from((o1, o2) -> {
                return (o1.getPlotRatio().compareTo(o2.getPlotRatio()));
            });
            detailList.sort(ordering);
            //如果在两个值间
            for (int i = 0; i < detailList.size() - 1; i++) {
                if (detailList.get(i).getPlotRatio().compareTo(volumetricRate) == -1 &&
                        volumetricRate.compareTo(detailList.get(i + 1).getPlotRatio()) == -1) {
                    course = detailList.get(i).getCorrectionFactor() + "+（" + detailList.get(i + 1).getCorrectionFactor() + "-" + detailList.get(i).getCorrectionFactor() + "）/（" + detailList.get(i + 1).getPlotRatio() + "-" + detailList.get(i).getPlotRatio() + "）×（" + volumetricRate + "-" + detailList.get(i).getPlotRatio() + "）";
                }
            }
            //最小值
            DataAllocationCorrectionCoefficientVolumeRatioDetail minValue = detailList.get(0);
            //最大值
            DataAllocationCorrectionCoefficientVolumeRatioDetail maxValue = detailList.get(detailList.size() - 1);
            //小于最小值
            if (minValue.getPlotRatio().compareTo(volumetricRate) == 1) {
                DataAllocationCorrectionCoefficientVolumeRatioDetail tempValue = detailList.get(1);
                course = minValue.getCorrectionFactor() + "-（" + tempValue.getCorrectionFactor() + "-" + minValue.getCorrectionFactor() + "）/（" + tempValue.getPlotRatio() + "-" + minValue.getPlotRatio() + "）×（" + minValue.getPlotRatio() + "-" + volumetricRate + "）";
            }
            //大于最大值
            if (volumetricRate.compareTo(maxValue.getPlotRatio()) == 1) {
                DataAllocationCorrectionCoefficientVolumeRatioDetail tempValue = detailList.get(detailList.size() - 2);
                course = maxValue.getCorrectionFactor() + "+（" + maxValue.getCorrectionFactor() + "-" + tempValue.getCorrectionFactor() + "）/（" + maxValue.getPlotRatio() + "-" + tempValue.getPlotRatio() + "）×（" + volumetricRate + "-" + maxValue.getPlotRatio() + "）";
            }
        }
        return String.format(s, course, getMdBaseLandPrice().getVolumeFractionAmend());
    }


    /**
     * 功能描述: 基准地价报酬率
     */
    public String getBaseLandPriceRewardRatio() {
        return getMdBaseLandPrice().getRewardRate();
    }

    /**
     * 功能描述: 基准地价设定用途
     */
    public String getBaseLandPriceSetPurpose() {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        return baseDataDicService.getNameById(schemeJudgeObject.getSetUse());
    }

    /**
     * 功能描述: 基准地价机会成本率
     */
    public String getBaseLandPriceOpportunityCostRatio() throws Exception {
        JSONArray jsonArray = JSONObject.parseArray(toolRewardRateService.getToolRewardRateById(getMdBaseLandPrice().getRewardRateId()).getParameterValue());
        JSONObject jsonObject = getToolRewardRateHelp(jsonArray, "opportunityCost");
        BigDecimal ratio = new BigDecimal(jsonObject.getString("ratio"));
        String format = String.format("%.2f", ratio.multiply(new BigDecimal("100")));
        return format + "%";
    }

    /**
     * 功能描述: 基准地价机会成本说明
     */
    public String getBaseLandPriceOpportunityCostExplain() throws Exception {
        JSONArray jsonArray = JSONObject.parseArray(toolRewardRateService.getToolRewardRateById(getMdBaseLandPrice().getRewardRateId()).getParameterValue());
        JSONObject jsonObject = getToolRewardRateHelp(jsonArray, "opportunityCost");
        return jsonObject.getString("remark");
    }


    /**
     * 功能描述: 基准地价土地剩余年限
     */
    public String getBaseLandPriceResidueYears() {
        return getMdBaseLandPrice().getLegalAge().toString();
    }

    /**
     * 功能描述: 基准地价使用年限
     */
    public String getBaseLandPriceDurableYears() {
        return getMdBaseLandPrice().getLandSurplusYear().toString();
    }

    /**
     * 功能描述: 基准地价期日修正计算结果
     */
    public String getBaseLandPriceDateAmendCalculatedResult() {
        return getMdBaseLandPrice().getDateAmend().toString();
    }

    /**
     * 功能描述: 基准地价期日修正计算式
     */
    public String getBaseLandPriceDateAmendCalculatedMode() {
//        StringBuilder s = new StringBuilder();
//        ProjectInfo projectInfoById = projectInfoService.getProjectInfoById(projectId);
//        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
//        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
//        //评估基准日
//        Date valuationDate = projectInfoById.getValuationDate();
//        //找到评估基准日对应的土地因素
//        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
//        List<DataHousePriceIndex> dataHouseIndexList = Lists.newArrayList();
//        dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), declareRecord.getDistrict(), dataDic.getId());
//        if (CollectionUtils.isEmpty(dataHouseIndexList)) {
//            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), null, dataDic.getId());
//        }
//        if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
//            Integer masterId = dataHouseIndexList.get(0).getId();
//            DataHousePriceIndexDetail dataHousePriceIndexDetail = new DataHousePriceIndexDetail();
//            dataHousePriceIndexDetail.setHousePriceId(masterId);
//            List<DataHousePriceIndexDetail> detailList = dataHousePriceIndexDetailDao.getDataHousePriceIndexDetailList(dataHousePriceIndexDetail);
//            //最早月份的指数
//            DataHousePriceIndexDetail firstIndex = detailList.get(0);
//            if (CollectionUtils.isNotEmpty(detailList)) {
//                for (DataHousePriceIndexDetail item : detailList) {
//                    if (item.getStartDate().compareTo(valuationDate) != 1 && item.getEndDate().compareTo(valuationDate) != -1) {
//                        return s.append(item.getIndexNumber().toString()).append("/").append(firstIndex.getIndexNumber().toString()).toString();
//                    }
//                }
//            }
//        }
        return "配置不完全";
    }

    /**
     * 功能描述: 单价
     */
    public String getBaseLandPriceUnivalence() {
        return getMdBaseLandPrice().getStandardPremium().toString();
    }

    /**
     * 功能描述: 区域名称
     */
    public String getBaseLandPriceArea() {
        return getSchemeAreaGroup().getAreaName();
    }

    /**
     * 功能描述: 基准地价土地级别名称
     */
    public String getBaseLandPriceLevelName() {
        StringBuilder s = new StringBuilder();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        return s.append(dataLandLevelDetail.getClassify()).append("/").append(dataLandLevelDetail.getType()).toString();
    }

    /**
     * 功能描述: 基准地价概要
     */
    public String getBaseLandPriceProfile() {
        String s = "%s基准地价成果于%s年编制%s。城镇土地进行分类定级，共分%s大类别，即%s%s大类，%s。";
        //区域名称
        String areaName = getSchemeAreaGroup().getAreaName();

        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        DataLandLevel dataLandLevel = dataLandLevelService.getDataLandLevelById(dataLandLevelDetail.getLandLevelId());
        //编制年度
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(dataLandLevel.getReleaseDate());
        //文号
        String wordSymbol = dataLandLevel.getWordSymbol();

        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailService.getDataLandLevelDetailListByPid(dataLandLevel.getId());
        Map<String, Integer> map = countProperty(dataLandLevelDetailList);
        //类型个数
        String size = toChinese(String.valueOf(map.size()));
        //类型级数
        StringBuilder typesGrade = new StringBuilder();
        //类型
        StringBuilder types = new StringBuilder();
        for (String key : map.keySet()) {
            types.append(key).append("、");
            typesGrade.append(key).append("分为").append(toChinese(String.valueOf(map.get(key)))).append("级").append(",");
        }
        types.deleteCharAt(types.length() - 1);
        typesGrade.deleteCharAt(typesGrade.length() - 1);

        return String.format(s, areaName, year, wordSymbol, size, types, size, typesGrade);
    }


    /**
     * 统计类型
     */
    public Map<String, Integer> countProperty(List<DataLandLevelDetail> dataLandLevelDetailList) {
        Map<String, Integer> map = Maps.newHashMap();
        for (DataLandLevelDetail user : dataLandLevelDetailList) {
            if (map.containsKey(user.getClassify())) {
                int i = map.get(user.getClassify());
                i++;
                map.remove(user.getClassify());
                map.put(user.getClassify(), i);
            } else {
                map.put(user.getClassify(), 1);
            }
        }
        return map;
    }

    //数字转汉字
    public String toChinese(String str) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }

    private JSONObject getToolRewardRateHelp(JSONArray jsonArray, String filterName) {
        JSONObject jsonObject = null;
        if (CollectionUtils.isEmpty(jsonArray)) {
            return jsonObject;
        }
        if (jsonArray.stream().filter(o -> {
            JSONObject object = (JSONObject) o;
            if (object != null) {
                if (object.size() > 0) {
                    String text = object.getString("name");
                    if (Objects.equal(text, filterName)) {
                        return true;
                    }
                }
            }
            return false;
        }).count() >= 1) {
            jsonObject = (JSONObject) jsonArray.stream().filter(o -> {
                JSONObject object = (JSONObject) o;
                if (object != null) {
                    if (object.size() > 0) {
                        String text = object.getString("name");
                        if (Objects.equal(text, filterName)) {
                            return true;
                        }
                    }
                }
                return false;
            }).findFirst().get();
        }
        return jsonObject;
    }

    //int转罗马数字
    public String intToRoman(int num) {
        if (num > 3999 || num < 0) {
            return "";
        }
        String result = "";
        //千位数字 分别代表 千位没有值 1000 2000 3000
        String[] kb = new String[]{"", "M", "MM", "MMM"};
        //百位 分别代表 百位没有值 100 200 300 400 500 600 700 800 900
        String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        //十位
        String[] decade = new String[]{"", "Ⅹ", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        //个位
        String[] unit = new String[]{"", "Ⅰ", "Ⅱ", "Ⅲ", "Ⅳ", "Ⅴ", "Ⅵ", "Ⅶ", "Ⅷ", "Ⅸ"};
        result = kb[num / 1000] + hundreds[num % 1000 / 100] + decade[num % 100 / 10] + unit[num % 10];
        return String.format("%s%s", result, "级");
    }


    //对应土地级别从表拥个人区域的类别
    public List<String> getCategoryFromLandDetail(List<DataLandDetailAchievement> list) {
        List<String> categorys = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (DataLandDetailAchievement item : list) {
                if (!categorys.contains(item.getCategory())) {
                    categorys.add(item.getCategory());
                }
            }
        }
        return categorys;
    }
}

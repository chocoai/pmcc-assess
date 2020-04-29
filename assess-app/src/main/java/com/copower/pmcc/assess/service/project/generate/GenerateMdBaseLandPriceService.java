package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.ReportFieldMdBaseLandPriceEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailVolumeDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailAchievementVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.tool.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailAchievementService;
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
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class GenerateMdBaseLandPriceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer mlId;
    private SchemeInfo schemeInfo;
    private Integer projectId;
    private SchemeJudgeObject schemeJudgeObject;
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
    private DataLandLevelDetailVolumeDao dataLandLevelDetailVolumeDao;
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    private DataLandLevelDetailAchievementService dataLandLevelDetailAchievementService;
    private BaseProjectClassifyService baseProjectClassifyService;
    private BaseService baseService;


    /**
     * 获取替换后的报告模板
     *
     * @return
     * @throws Exception
     */
    public String generateBaseLandPriceFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.BASE_LAND_PRICE_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
        //获取待替换文本的集合
        List<String> regexS = generateCommonMethod.specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(AsposeUtils.getChinese(bookmarkCollection.get(i).getName())).setName(bookmarkCollection.get(i).getName());
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            for (String name : regexS) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(null).setName(name);
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        Map<String, String> textMap = Maps.newHashMap();
        Map<String, String> bookmarkMap = Maps.newHashMap();
        LinkedHashMap<String, String> fileMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isEmpty(bookmarkAndRegexDtoHashSet)) {
            return localPath;
        }
        for (BookmarkAndRegexDto bookmarkAndRegex : bookmarkAndRegexDtoHashSet) {
            String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
            try {
                //基准地价概要
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceProfile.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceProfile());
                }
                //基准地价区域
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceArea());
                }
                //基准地价土地级别名称
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceLevelName.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceLevelName());
                }
                //基准地价单价
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceUnivalence.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceUnivalence());
                }
                //基准地价期日修正计算式
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceDateAmendCalculatedMode.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceDateAmendCalculatedMode());
                }
                //基准地价期日修正计算结果
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceDateAmendCalculatedResult.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceDateAmendCalculatedResult());
                }
                //基准地价使用年限
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceDurableYears.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceDurableYears());
                }
                //基准地价土地剩余年限
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceResidueYears.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceResidueYears());
                }
                //基准地价机会成本说明
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceOpportunityCostExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceOpportunityCostExplain());
                }
                //基准地价机会成本率
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceOpportunityCostRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceOpportunityCostRatio());
                }
                //基准地价报酬率
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceRewardRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceRewardRatio());
                }
                //基准地价设定用途
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceSetPurpose.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceSetPurpose());
                }
                //基准地价年期修正系数
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPeriodAmend.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPeriodAmend());
                }
                //基准地价容积率修正系数说明
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPricePlotRatioAmendExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPricePlotRatioAmendExplain());
                }
                //基准地价权利状况修正
//                if (Objects.equal(name, BaseReportFieldMdBaseLandPriceEnum.BaseLandPriceRightStateAmend.getName())) {
//                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceRightStateAmend());
//                }
                //基准地价开发程度修正说明
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceDevelopmentLevelAmendExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceDevelopmentLevelAmendExplain());
                }
                //基准地价宗地单价
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceParcelUnivalence.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceParcelUnivalence());
                }
                //基准地价评估面积
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceAssessArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceAssessArea());
                }
                //基准地价宗地面积
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceParcelArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceParcelArea());
                }
                //基准地价容积率
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceVolumeFraction.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceVolumeFraction());
                }
                //基准地价楼面地价
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceFloorPremium.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceFloorPremium());
                }
                //基准地价价格一览表
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceSchedule.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getBaseLandPriceSchedule());
                }
                //基准地价因素说明表
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceFactorExplain.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getBaseLandPriceFactorExplain(false));
                }
                //基准地价因素系数表
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceFactorCoefficient.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getBaseLandPriceFactorExplain(true));
                }
                //基准地价因素修正表
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceFactorAmend.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getBaseLandPriceFactorAmend());
                }
                //基准地价基础指数表
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceIndex.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getBaseLandPriceIndex());
                }
                //基准地价测算过程明细表
                if (Objects.equal(name, ReportFieldMdBaseLandPriceEnum.BaseLandPriceCalculateCourseDetail.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getBaseLandPriceCalculateCourseDetail());
                }
            } catch (Exception e) {
                baseService.writeExceptionInfo(e);
            }
        }
        //替换
        if (!textMap.isEmpty()) {
            AsposeUtils.replaceText(localPath, textMap);
        }
        if (!bookmarkMap.isEmpty()) {
            AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
        }
        if (!fileMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, fileMap);
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
        builder.write(mdBaseLandPrice.getStandardPremiumRemark());
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
        builder.write(mdBaseLandPrice.getLegalAgeRemark());
        builder.endRow();
        //剩余使用年限
        builder.insertCell();
        builder.write("剩余使用年限");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getLandSurplusYear().toString());
        builder.insertCell();
        builder.write(mdBaseLandPrice.getLandSurplusYearRemark());
        builder.endRow();
        //容积率修正
        builder.insertCell();
        builder.write("容积率修正");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getVolumeFractionAmend().toString());
        builder.insertCell();
        builder.write("");
        builder.endRow();

        //开发程度修正
        builder.insertCell();
        builder.write("开发程度修正");
        builder.insertCell();
        builder.write(mdBaseLandPrice.getLandSurplusYear().toString());
        builder.insertCell();
        builder.write(mdBaseLandPrice.getLandSurplusYearRemark());
        builder.endRow();

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
        builder.write(mdBaseLandPrice.getEvaluationAreaRemark());
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
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        Table table = builder.startTable();
        //土地级别类别	土地级别类型	土地级别等级	说明  分值
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("土地级别类别", "土地级别类型", "土地级别等级", "说明", "分值")));
        //地价因素修正数据
        String landLevelContent = getMdBaseLandPrice().getLandLevelContent();
        JSONArray objects = JSON.parseArray(landLevelContent);
        List<DataLandLevelDetailAchievementVo> filterVoList = new ArrayList<>();
        if (objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                List<DataLandLevelDetailAchievementVo> vos = JSON.parseArray(JSON.toJSONString(objects.get(i)), DataLandLevelDetailAchievementVo.class);
                for (DataLandLevelDetailAchievementVo item : vos) {

                }
            }
        }
        List<List<DataLandLevelDetailAchievementVo>> listList = dataLandLevelDetailAchievementService.landLevelFilter2(filterVoList);
        Set<List<List<DataLandLevelDetailAchievementVo>>> set = dataLandLevelDetailAchievementService.landLevelFilter1(listList);

        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();

        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(set)) {
            Integer endRowIndex = 0;
            for (List<List<DataLandLevelDetailAchievementVo>> types : set) {
                for (int i = 0; i < types.size(); i++) {
                    if (i == 0) {
                        mergeCellModelList.add(new MergeCellModel(endRowIndex + 1, 0, endRowIndex + types.size(), 0));
                        endRowIndex += types.size();
                    }
                    if (StringUtils.isNotEmpty(types.get(i).get(0).getTypeName())) {
                        linkedList.add(types.get(i).get(0).getTypeName());
                    } else {
                        linkedList.add(nullValue);
                    }

                    for (DataLandLevelDetailAchievementVo item : types.get(i)) {
                        if (StringUtils.isNotEmpty(item.getReamark())) {
                            linkedList.add(item.getReamark());
                        } else {
                            linkedList.add(nullValue);
                        }
                        if (item.getAchievement() != null) {
                            linkedList.add(item.getAchievement().toString());
                        } else {
                            linkedList.add(nullValue);
                        }
                    }

                    generateCommonMethod.writeWordTitle(builder, linkedList);
                    linkedList.clear();
                }
            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        document.save(localPath);
        return localPath;
    }

    /**
     * 基准地价因素说明/系数表
     *
     * @return
     */
    public String getBaseLandPriceFactorExplain(boolean index) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        Table table = builder.startTable();
        //表头
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("土地级别类别", "土地级别类型", "优", "较优", "一般", "较劣", "劣")));
        //获取土地级别id
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        BasicEstateLandState landStateByEstateId = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        //拿到土地因素数据
        DataLandLevelDetailAchievement dataLandLevelDetailAchievement = new DataLandLevelDetailAchievement();
        dataLandLevelDetailAchievement.setLevelDetailId(landStateByEstateId.getLandLevel());
        List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementVoList = dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementList(dataLandLevelDetailAchievement);
        List<List<DataLandLevelDetailAchievementVo>> listList = dataLandLevelDetailAchievementService.landLevelFilter2(dataLandLevelDetailAchievementVoList.stream().map(po -> dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementVo(po)).collect(Collectors.toList()));
        Set<List<List<DataLandLevelDetailAchievementVo>>> set = dataLandLevelDetailAchievementService.landLevelFilter1(listList);

        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();

        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(set)) {
            Integer endRowIndex = 0;
            for (List<List<DataLandLevelDetailAchievementVo>> types : set) {
                for (int i = 0; i < types.size(); i++) {
                    if (i == 0) {
                        mergeCellModelList.add(new MergeCellModel(endRowIndex + 1, 0, endRowIndex + types.size(), 0));
                        endRowIndex += types.size();
                    }
                    if (StringUtils.isNotEmpty(types.get(i).get(0).getTypeName())) {
                        linkedList.add(types.get(i).get(0).getTypeName());
                    } else {
                        linkedList.add(nullValue);
                    }
                    for (DataLandLevelDetailAchievementVo item : types.get(i)) {
                        if (index) {
                            if (item.getAchievement() != null) {
                                linkedList.add(item.getAchievement().toString());
                            } else {
                                linkedList.add(nullValue);
                            }
                        } else {
                            if (StringUtils.isNotEmpty(item.getReamark())) {
                                linkedList.add(item.getReamark());
                            } else {
                                linkedList.add(nullValue);
                            }
                        }
                    }
                    generateCommonMethod.writeWordTitle(builder, linkedList);
                    linkedList.clear();
                }

            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        document.save(localPath);
        return localPath;
    }

    public void writeAchievement(DocumentBuilder builder, DataLandLevelDetailAchievement data, boolean isIndex) throws Exception {
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
    public String getBaseLandPriceSchedule() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //设置表格属性
        generateCommonMethod.settingBuildingTable(builder);
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        DataLandLevel dataLandLevel = dataLandLevelService.getDataLandLevelById(dataLandLevelDetail.getLandLevelId());
        //对应区域下所有的土地级别
        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailService.getDataLandLevelDetailListByPid(dataLandLevel.getId());
        Map<Integer, Integer> map = countProperty(dataLandLevelDetailList);
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
        for (Integer key : map.keySet()) {
            builder.insertCell();
            builder.write(baseDataDicService.getNameById(key));
            builder.insertCell();
            builder.write("元/平方米");
            for (int i = 1; i < maxValue; i++) {
                builder.insertCell();
                DataLandLevelDetail data = null;
                data = dataLandLevelDetailService.getDataByClassifyAndType(dataLandLevel.getId(), key.toString(), String.valueOf(i));
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
                DataLandLevelDetail data = dataLandLevelDetailService.getDataByClassifyAndType(dataLandLevel.getId(), key.toString(), String.valueOf(i));
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
     * 功能描述: 基准地价基础指数表
     */
    public String getBaseLandPriceIndex() {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
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
                    result = result.add(estateByApplyId.getFloorArea());
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
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        String setUseName = baseDataDicService.getNameById(schemeJudgeObject.getSetUse());
        if (getMdBaseLandPrice().getDevelopCorrect() == null || getMdBaseLandPrice().getDevelopCorrect().compareTo(new BigDecimal("0")) == 0) {
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
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        String setUseName = baseDataDicService.getNameById(schemeJudgeObject.getSetUse());
        if (getMdBaseLandPrice().getVolumeFractionAmend() == null || getMdBaseLandPrice().getVolumeFractionAmend().compareTo(new BigDecimal("0")) == 0) {
            return String.format("考虑容积率对%s用地的影响不大，故不对容积率进行修正。", setUseName);
        }

        String s = "K3=%s=%s。";
        //根据容积率找到配置中对应的容积率修正
        List<DataAllocationCorrectionCoefficientVolumeRatio> coefficientVolumeRatioList;
        coefficientVolumeRatioList = null;
        if (CollectionUtils.isNotEmpty(coefficientVolumeRatioList)) {
            Integer masterId = coefficientVolumeRatioList.get(0).getId();
            DataLandLevelDetailVolume coefficientVolumeRatioDetail = new DataLandLevelDetailVolume();
            coefficientVolumeRatioDetail.setLevelDetailId(masterId);
            List<DataLandLevelDetailVolume> detailList = dataLandLevelDetailVolumeDao.getDataLandLevelDetailVolumeList(coefficientVolumeRatioDetail);
            for (DataLandLevelDetailVolume detailItem : detailList) {
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

    public String getAmend(List<DataLandLevelDetailVolume> detailList, BigDecimal volumetricRate, String s) {
        String course = "";
        if (detailList.size() >= 2) {
            //排序
            Ordering<DataLandLevelDetailVolume> ordering = Ordering.from((o1, o2) -> {
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
            DataLandLevelDetailVolume minValue = detailList.get(0);
            //最大值
            DataLandLevelDetailVolume maxValue = detailList.get(detailList.size() - 1);
            //小于最小值
            if (minValue.getPlotRatio().compareTo(volumetricRate) == 1) {
                DataLandLevelDetailVolume tempValue = detailList.get(1);
                course = minValue.getCorrectionFactor() + "-（" + tempValue.getCorrectionFactor() + "-" + minValue.getCorrectionFactor() + "）/（" + tempValue.getPlotRatio() + "-" + minValue.getPlotRatio() + "）×（" + minValue.getPlotRatio() + "-" + volumetricRate + "）";
            }
            //大于最大值
            if (volumetricRate.compareTo(maxValue.getPlotRatio()) == 1) {
                DataLandLevelDetailVolume tempValue = detailList.get(detailList.size() - 2);
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
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        return baseDataDicService.getNameById(schemeJudgeObject.getSetUse());
    }

    /**
     * 功能描述: 基准地价机会成本率
     */
    public String getBaseLandPriceOpportunityCostRatio() throws Exception {
        JSONArray jsonArray = JSONObject.parseArray(toolRewardRateService.getToolRewardRateById(getMdBaseLandPrice().getRewardRateId()).getParameterValue());
        JSONObject jsonObject = getToolRewardRateHelp(jsonArray, "opportunityCost");
        BigDecimal ratio = new BigDecimal(jsonObject.getString("ratio"));
        return ArithmeticUtils.getPercentileSystem(ratio, 4, BigDecimal.ROUND_HALF_UP);
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
        StringBuilder s = new StringBuilder();
        ProjectInfo projectInfoById = projectInfoService.getProjectInfoById(projectId);
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        //评估基准日
        Date valuationDate = projectInfoById.getValuationDate();
        //找到评估基准日对应的土地因素
        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);
        List<DataHousePriceIndex> dataHouseIndexList = Lists.newArrayList();
        dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), declareRecord.getDistrict(), dataDic.getId());
        if (CollectionUtils.isEmpty(dataHouseIndexList)) {
            dataHouseIndexList = dataHousePriceIndexDao.getDataHouseIndexList(declareRecord.getProvince(), declareRecord.getCity(), null, dataDic.getId());
        }
        if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
            Integer masterId = dataHouseIndexList.get(0).getId();
            DataHousePriceIndexDetail dataHousePriceIndexDetail = new DataHousePriceIndexDetail();
            dataHousePriceIndexDetail.setHousePriceId(masterId);
            List<DataHousePriceIndexDetail> detailList = dataHousePriceIndexDetailDao.getDataHousePriceIndexDetailList(dataHousePriceIndexDetail);
            //最早月份的指数
            DataHousePriceIndexDetail firstIndex = detailList.get(0);
            if (CollectionUtils.isNotEmpty(detailList)) {
                for (DataHousePriceIndexDetail item : detailList) {
                    if (item.getStartDate().compareTo(valuationDate) != 1 && item.getEndDate().compareTo(valuationDate) != -1) {
                        return s.append(item.getIndexNumber().toString()).append("/").append(firstIndex.getIndexNumber().toString()).toString();
                    }
                }
            }
        }
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
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
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

        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        DataLandLevelDetail dataLandLevelDetail = getDataLandLevelDetail(schemeJudgeObject);
        DataLandLevel dataLandLevel = dataLandLevelService.getDataLandLevelById(dataLandLevelDetail.getLandLevelId());
        //编制年度
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(dataLandLevel.getReleaseDate());
        //文号
        String wordSymbol = dataLandLevel.getWordSymbol();

        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailService.getDataLandLevelDetailListByPid(dataLandLevel.getId());
        Map<Integer, Integer> map = countProperty(dataLandLevelDetailList);
        //类型个数
        String size = toChinese(String.valueOf(map.size()));
        //类型级数
        StringBuilder typesGrade = new StringBuilder();
        //类型
        StringBuilder types = new StringBuilder();
        for (Integer key : map.keySet()) {
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
    public Map<Integer, Integer> countProperty(List<DataLandLevelDetail> dataLandLevelDetailList) {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (DataLandLevelDetail user : dataLandLevelDetailList) {
            if (map.containsKey(user.getClassify())) {
                int i = map.get(user.getClassify());
                i++;
                map.remove(user.getClassify());
                if (NumberUtils.isNumber(user.getClassify())) {
                    map.put(Integer.parseInt(user.getClassify()), i);
                }
            } else {
                if (NumberUtils.isNumber(user.getClassify())) {
                    map.put(Integer.parseInt(user.getClassify()), 1);
                }
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
    public List<String> getCategoryFromLandLevelDetail(List<DataLandLevelDetailAchievement> list) {
        List<String> categorys = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (DataLandLevelDetailAchievement item : list) {
                if (!categorys.contains(item.getCategory())) {
                    categorys.add(item.getCategory());
                }
            }
        }
        return categorys;
    }

    private GenerateMdBaseLandPriceService() {
    }

    public GenerateMdBaseLandPriceService(SchemeInfo schemeInfo, Integer areaId) throws Exception {
        this.mlId = schemeInfo.getMethodDataId();
        ;
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;
        this.projectId = schemeInfo.getProjectId();
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
        this.dataLandLevelDetailVolumeDao = SpringContextUtils.getBean(DataLandLevelDetailVolumeDao.class);
        this.declareRealtyHouseCertService = SpringContextUtils.getBean(DeclareRealtyHouseCertService.class);
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.declareRealtyRealEstateCertService = SpringContextUtils.getBean(DeclareRealtyRealEstateCertService.class);
        this.dataLandLevelDetailAchievementService = SpringContextUtils.getBean(DataLandLevelDetailAchievementService.class);
        this.baseProjectClassifyService = SpringContextUtils.getBean(BaseProjectClassifyService.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
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
            this.schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
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

    private SchemeJudgeObject getSchemeJudgeObject() {
        if (schemeJudgeObject == null) {
            if (getSchemeInfo().getJudgeObjectId() != null) {
                schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(getSchemeInfo().getJudgeObjectId());
            }
            if (schemeJudgeObject == null) {
                schemeJudgeObject = new SchemeJudgeObject();
            }
        }
        return schemeJudgeObject;
    }

    private SchemeInfo getSchemeInfo() {
        return schemeInfo;
    }

    /**
     * 功能描述: 设置默认字体
     *
     * @param:
     * @return:
     * @author: huhao
     * @date: 2019/3/1 14:32
     */
    private DocumentBuilder getDefaultDocumentBuilderSetting(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        AsposeUtils.setDefaultFontSettings(builder);
        return builder;
    }

    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }

}

package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.data.DataBuildingInstallCostTypeEnum;
import com.copower.pmcc.assess.common.enums.method.EconomicIndicatorsItemEnum;
import com.copower.pmcc.assess.common.enums.report.ReportFieldDevelopmentEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.assess.dto.output.data.DataBuildingInstallCostVo;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataBuildingInstallCostService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.method.MdEconomicIndicatorsService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by zch on 2019/7/9.
 * 假设开发法
 */
public class GenerateMdDevelopmentService {
    private static final String errorStr = "无";

    private MdDevelopmentVo mdDevelopmentVo;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;

    private Integer areaId;
    private SchemeInfo schemeInfo;
    private Integer projectId;

    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private DataMethodFormulaService dataMethodFormulaService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private SchemeInfoService schemeInfoService;
    private GenerateCommonMethod generateCommonMethod;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private MdDevelopmentService mdDevelopmentService;
    private DataSetUseFieldService dataSetUseFieldService;
    private MdArchitecturalObjService mdArchitecturalObjService;
    private ProjectPlanDetailsService projectPlanDetailsService;
    private BaseService baseService;
    private DataBuildingInstallCostService dataBuildingInstallCostService;
    private MdEconomicIndicatorsService mdEconomicIndicatorsService;

    public GenerateMdDevelopmentService(Integer projectId, SchemeInfo schemeInfo, Integer areaId) {
        this.projectId = projectId;
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;

        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.mdDevelopmentService = SpringContextUtils.getBean(MdDevelopmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.mdArchitecturalObjService = SpringContextUtils.getBean(MdArchitecturalObjService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.dataBuildingInstallCostService = SpringContextUtils.getBean(DataBuildingInstallCostService.class);
        this.mdEconomicIndicatorsService = SpringContextUtils.getBean(MdEconomicIndicatorsService.class);
    }


    public String generateCompareFile() throws Exception {
        MdDevelopmentVo mdDevelopmentVo = getMdDevelopmentVo();
        String key = Objects.equal("1", mdDevelopmentVo.getType()) ? AssessReportFieldConstant.DEVELOPMENT_LAND_TEMPLATE : AssessReportFieldConstant.DEVELOPMENT_CONSTRUCTION_TEMPLATE;
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(key);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Map<ReportFieldDevelopmentEnum, String> map = Maps.newHashMap();
        final ConcurrentHashMap<String, String> textMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> bookmarkMap = new ConcurrentHashMap<String, String>(0);
        final ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsById(schemeInfo.getPlanDetailsId());
        Map<String, ReportFieldDevelopmentEnum> reportFieldEnumMap = new HashMap<String, ReportFieldDevelopmentEnum>(0);
        for (ReportFieldDevelopmentEnum reportFieldEnum : ReportFieldDevelopmentEnum.values()) {
            reportFieldEnumMap.put(reportFieldEnum.getName(), reportFieldEnum);
        }
        //获取待替换文本的集合
        List<String> stringList = generateCommonMethod.specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                if (reportFieldEnumMap.keySet().contains(bookmarkCollection.get(i).getName())) {
                    map.put(reportFieldEnumMap.get(bookmarkCollection.get(i).getName()), bookmarkCollection.get(i).getName());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (String name : stringList) {
                if (reportFieldEnumMap.keySet().contains(name)) {
                    map.put(reportFieldEnumMap.get(name), name);
                }
            }
        }
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (!map.isEmpty()) {
            for (Map.Entry<ReportFieldDevelopmentEnum, String> enumStringEntry : map.entrySet()) {
                try {
                    //经济指标 单独处理
                    if (Objects.equal(ReportFieldDevelopmentEnum.Development_EconomicIndicators.getName(), enumStringEntry.getKey().getName())) {
                        setDevelopment_EconomicIndicatorsValue(textMap, fileMap, bookmarkMap);
                    }
                    setFieldObjectValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, mdDevelopmentVo);
                    setMdDevelopmentCommonValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, mdDevelopmentVo, schemeJudgeObject, schemeAreaGroup, projectPlanDetails);
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                    throw e;
                }
            }
        }
        //替换
        if (!textMap.isEmpty()) {
            AsposeUtils.replaceText(localPath, textMap);
            PoiUtils.replaceText(textMap, localPath);
        }
        if (!bookmarkMap.isEmpty()) {
            AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
        }
        try {
            if (!fileMap.isEmpty()) {
                Document fileDoc = new Document(localPath) ;
                for (Map.Entry<String, String> stringStringEntry : fileMap.entrySet()) {
                    Pattern compile = Pattern.compile(AsposeUtils.escapeExprSpecialWord(stringStringEntry.getKey()));
                    IReplacingCallback callback = new IReplacingCallback() {
                        @Override
                        public int replacing(ReplacingArgs e) throws Exception {
                            DocumentBuilder iReplacingCallback = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                            iReplacingCallback.moveTo(e.getMatchNode());
                            Document doc = new Document(stringStringEntry.getValue());
                            iReplacingCallback.insertDocument(doc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
                            return ReplaceAction.REPLACE;
                        }
                    };
                    fileDoc.getRange().replace(compile, callback, false);
                }
                AsposeUtils.saveWord(localPath, fileDoc);
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
        if (!fileMap.isEmpty()) {
            AsposeUtils.replaceTextToFile(localPath, fileMap);
        }
        return localPath;
    }

    /**
     * 经济指标
     *
     * @param textMap
     * @param fileMap
     * @param bookmarkMap
     */
    private void setDevelopment_EconomicIndicatorsValue(final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap) throws Exception {
        List<MdEconomicIndicatorsItem> filterList = Lists.newArrayList();
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        generateCommonMethod.settingBuildingTable(builder);
        //设置具体宽度自动适应
        PreferredWidth preferredWidth = PreferredWidth.AUTO;
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.getCellFormat().setPreferredWidth(preferredWidth);
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
        MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = getMdEconomicIndicatorsApplyDto();
        List<MdEconomicIndicatorsItem> itemList = Lists.newArrayList();
        if (mdEconomicIndicatorsApplyDto != null && CollectionUtils.isNotEmpty(mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList())) {
            itemList.addAll(mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList());
            mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList().forEach(oo -> {
                if (oo.getMcId() != null) {
                    filterList.add(oo);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(itemList)) {
            LinkedList<String> stringLinkedList = Lists.newLinkedList();
            builder.startTable();
            stringLinkedList.addAll(Arrays.asList("名称", "规划建筑面积 (㎡)", "可出售面积 (㎡)", "评估面积 (㎡)/个数", "单位售价(元/㎡)", "备注"));
            AsposeUtils.writeWordTitle(builder, stringLinkedList);
            stringLinkedList.clear();
            for (MdEconomicIndicatorsItem indicatorsItem : itemList) {
                stringLinkedList.add(StringUtils.isNotBlank(indicatorsItem.getName()) ? indicatorsItem.getName() : "");
                stringLinkedList.add(ArithmeticUtils.getBigDecimalString(indicatorsItem.getPlannedBuildingArea()));
                stringLinkedList.add(ArithmeticUtils.getBigDecimalString(indicatorsItem.getSaleableArea()));
                String value = indicatorsItem.getNumber() == null ? "" : indicatorsItem.getNumber().toString();
                if (indicatorsItem.getAssessArea() != null) {
                    value += String.join("", "/", ArithmeticUtils.getBigDecimalString(indicatorsItem.getAssessArea()));
                }
                stringLinkedList.add(value);
                stringLinkedList.add(ArithmeticUtils.getBigDecimalString(indicatorsItem.getUnitPrice()));
                stringLinkedList.add(StringUtils.isNotBlank(indicatorsItem.getRemark()) ? indicatorsItem.getRemark() : "");
                AsposeUtils.writeWordTitle(builder, stringLinkedList);
                stringLinkedList.clear();
            }
            builder.endTable();
        }
        if (CollectionUtils.isNotEmpty(filterList)) {
            for (MdEconomicIndicatorsItem item : filterList) {
                GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(getSchemeJudgeObject().getId(), item.getMcId(), areaId);
                String key = RandomStringUtils.randomAlphabetic(7);
                StringBuilder stringBuilder = new StringBuilder(24);
                for (EconomicIndicatorsItemEnum indicatorsItemEnum : EconomicIndicatorsItemEnum.values()) {
                    if (Objects.equal(indicatorsItemEnum.getKey(), item.getDataKey())) {
                        stringBuilder.append(indicatorsItemEnum.getDescription());
                    }
                }
                stringBuilder.append(item.getName()).append("使用市场比较法取得单位售价");
                AsposeUtils.insertHtml(builder, AsposeUtils.getWarpCssHtml(stringBuilder.toString(), AsposeUtils.getKeyValueDtoList()));
                builder.write(key);
                fileMap.put(key, generateMdCompareService.generateCompareFile());

            }
        }
        AsposeUtils.saveWord(localPath, doc);
        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, ReportFieldDevelopmentEnum.Development_EconomicIndicators.getName(), localPath);
    }

    private void setMdDevelopmentCommonValue(ReportFieldDevelopmentEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, MdDevelopmentVo target, SchemeJudgeObject schemeJudgeObject, SchemeAreaGroup schemeAreaGroup, ProjectPlanDetails projectPlanDetails) {
        switch (key) {
            case Development_region: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), schemeAreaGroup.getAreaName());
                break;
            }
            case Development_formula: {
                DataMethodFormula formula = new DataMethodFormula();
                if(StringUtils.equals("1",target.getType())){
                    formula = dataMethodFormulaService.getMethodFormulaByMethodKey(AssessDataDicKeyConstant.HYPOTHESIS_DEVELOP_LAND);
                }
                if(StringUtils.equals("2",target.getType())){
                    formula = dataMethodFormulaService.getMethodFormulaByMethodKey(AssessDataDicKeyConstant.HYPOTHESIS_DEVELOP_ENGINEERING);
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), formula.getFormula());
                break;
            }
            case Development_Land_SetUse: {
                if (schemeJudgeObject.getSetUse() == null) {
                    break;
                }
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                if (dataSetUseField == null || StringUtils.isEmpty(dataSetUseField.getName())) {
                    break;
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), dataSetUseField.getName());
                break;
            }
            case Development_SetUse: {
                if (schemeJudgeObject.getSetUse() == null) {
                    break;
                }
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                if (dataSetUseField == null || StringUtils.isEmpty(dataSetUseField.getName())) {
                    break;
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), dataSetUseField.getName());
                break;
            }
            case Development_developed_value_sheet: {
                try {
                    Document doc = new Document();
                    DocumentBuilder builder = new DocumentBuilder(doc);
                    generateCommonMethod.settingBuildingTable(builder);
                    //设置具体宽度自动适应
                    PreferredWidth preferredWidth = PreferredWidth.AUTO;
                    builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    builder.getCellFormat().setPreferredWidth(preferredWidth);
                    builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
                    builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
                    builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);

                    MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = getMdEconomicIndicatorsApplyDto();
                    List<MdEconomicIndicatorsItem> itemList = Lists.newArrayList();
                    if (mdEconomicIndicatorsApplyDto != null && CollectionUtils.isNotEmpty(mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList())) {
                        itemList.addAll(mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList());
                    }
                    LinkedList<String> stringLinkedList = Lists.newLinkedList();
                    builder.startTable();
                    if (CollectionUtils.isNotEmpty(itemList)) {
                        stringLinkedList.addAll(Arrays.asList("规划项目名称", "评估面积 (㎡)", "单位售价(元/㎡)", "备注"));
                        AsposeUtils.writeWordTitle(builder, stringLinkedList);
                        stringLinkedList.clear();
                        for (MdEconomicIndicatorsItem indicatorsItem : itemList) {
                            stringLinkedList.add(StringUtils.isNotBlank(indicatorsItem.getName()) ? indicatorsItem.getName() : "");
                            stringLinkedList.add(ArithmeticUtils.getBigDecimalString(indicatorsItem.getAssessArea()));
                            stringLinkedList.add(ArithmeticUtils.getBigDecimalString(indicatorsItem.getUnitPrice()));
                            stringLinkedList.add(StringUtils.isNotBlank(indicatorsItem.getRemark()) ? indicatorsItem.getRemark() : "");
                            AsposeUtils.writeWordTitle(builder, stringLinkedList);
                            stringLinkedList.clear();
                        }
                    }
                    String totalSaleableAreaPrice = "";
                    if (target.getTotalSaleableAreaPrice() != null) {
                        totalSaleableAreaPrice = ArithmeticUtils.mul(target.getTotalSaleableAreaPrice().toString(), "10000", 0);
                    }
                    stringLinkedList.addAll(Arrays.asList("预期销售合计",
                            String.join("", "规划建筑面积(㎡)", ArithmeticUtils.getBigDecimalString(target.getPlannedBuildingArea())),
                            String.join("", "可售面积(㎡)", ArithmeticUtils.getBigDecimalString(target.getSaleableArea())),
                            String.join("", "总可售面积售价(元)", totalSaleableAreaPrice)));
                    AsposeUtils.writeWordTitle(builder, stringLinkedList);
                    stringLinkedList.clear();
                    builder.endTable();
                    String localPath = generateCommonMethod.getLocalPath();
                    AsposeUtils.saveWord(localPath, doc);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), localPath);
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                }
                break;
            }
            case Development_Planning_constraints: {
                MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = getMdEconomicIndicatorsApplyDto();
                MdEconomicIndicators economicIndicators = mdEconomicIndicatorsApplyDto.getEconomicIndicators();
                if (economicIndicators == null) {
                    break;
                }
                StringBuilder stringBuilder = new StringBuilder(24);
                stringBuilder.append("根据委托方提供的");
                stringBuilder.append("技术经济指标相关依据显示,");
                stringBuilder.append("其中容积率为").append(economicIndicators.getVolumetricRate());
                stringBuilder.append("、");
                stringBuilder.append("建筑密度为").append(economicIndicators.getBuildingDensity());
                stringBuilder.append("、");
                stringBuilder.append("绿地率为").append(economicIndicators.getGreenSpaceRate());
                stringBuilder.append("、");
                stringBuilder.append("建设净用地面积").append(ArithmeticUtils.getBigDecimalString(economicIndicators.getPlanNetConstructionLandArea())).append("平方");
                stringBuilder.append("、");
                stringBuilder.append("建筑基底占地面积").append(ArithmeticUtils.getBigDecimalString(economicIndicators.getBuildingBaseCoverage())).append("平方");
                stringBuilder.append("、");
                stringBuilder.append("建筑限高").append(ArithmeticUtils.getBigDecimalString(economicIndicators.getBuildingHeightLimit())).append("米");
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), stringBuilder.toString());
                break;
            }
            case Development_Relevant_basis_technical_economic_indicators: {
                MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = getMdEconomicIndicatorsApplyDto();
                MdEconomicIndicators economicIndicators = mdEconomicIndicatorsApplyDto.getEconomicIndicators();
                if (economicIndicators == null || StringUtils.isEmpty(economicIndicators.getProjectFileName())) {
                    break;
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), economicIndicators.getProjectFileName());
                break;
            }
            case Development_PriceForecast: {//假设法售价预测
                try {
                    Document doc = new Document();
                    DocumentBuilder builder = new DocumentBuilder(doc);
                    generateCommonMethod.settingBuildingTable(builder);
                    String localPath = generateCommonMethod.getLocalPath();
                    doc.save(localPath);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), localPath);
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                }
                break;
            }
            case Development_constructionSubtotal_ComputationalProcess: {
                LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap = Maps.newLinkedHashMap();
                mdArchitecturalObjService.setMdCalculatingMethodEngineeringCostMapData(costJSONObjectMap, projectPlanDetails, projectPlanDetails.getProjectId());
                if (costJSONObjectMap.isEmpty()) {
                    break;
                }
                String path = generateCommonMethod.getLocalPath(RandomStringUtils.randomAlphanumeric(4));
                try {
                    Document document = new Document();
                    DocumentBuilder documentBuilder = new DocumentBuilder(document);
                    //设置具体宽度自动适应
                    PreferredWidth preferredWidth = PreferredWidth.AUTO;
                    documentBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                    documentBuilder.getCellFormat().setPreferredWidth(preferredWidth);
                    documentBuilder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
                    documentBuilder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
                    documentBuilder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
                    documentBuilder.getCellFormat().setTopPadding(0);
                    documentBuilder.getCellFormat().setBottomPadding(0);
                    documentBuilder.getCellFormat().setLeftPadding(0);
                    documentBuilder.getCellFormat().setRightPadding(0);
                    documentBuilder.getFont().setSize(10.5);
                    documentBuilder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
                    documentBuilder.writeln();
                    mdArchitecturalObjService.writeCalculatingMethodEngineeringCostSheet(documentBuilder, costJSONObjectMap);

                    AsposeUtils.saveWord(path, document);
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), path);
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e, key.getName());
                }
                break;
            }
            case Development_constructionInstallationEngineeringFee_Basis: {
                StringBuilder stringBuilder = new StringBuilder(8);
                List<DataBuildingInstallCostVo> dataBuildingInstallCostVos = dataBuildingInstallCostService.dataBuildingInstallCostVos(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
                if (CollectionUtils.isNotEmpty(dataBuildingInstallCostVos)) {
                    for (DataBuildingInstallCostVo costVo : dataBuildingInstallCostVos) {
                        if (Objects.equal(costVo.getType(), DataBuildingInstallCostTypeEnum.constructionInstallationEngineeringFee.getKey())) {
                            stringBuilder.append(costVo.getContent());
                        }
                    }
                }
                String value = StringUtils.isNotBlank(stringBuilder.toString()) ? stringBuilder.toString() : "无";
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), value);
                break;
            }
            case Development_infrastructureCost_FileName: {
                StringBuilder stringBuilder = new StringBuilder(8);
                List<DataBuildingInstallCostVo> dataBuildingInstallCostVos = dataBuildingInstallCostService.dataBuildingInstallCostVos(schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
                if (CollectionUtils.isNotEmpty(dataBuildingInstallCostVos)) {
                    for (DataBuildingInstallCostVo costVo : dataBuildingInstallCostVos) {
                        if (Objects.equal(costVo.getType(), DataBuildingInstallCostTypeEnum.infrastructureCost.getKey())) {
                            stringBuilder.append(costVo.getContent());
                        }
                    }
                }
                String value = StringUtils.isNotBlank(stringBuilder.toString()) ? stringBuilder.toString() : "无";
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), value);
                break;
            }
            case Development_PublicTrialRealEstateComputing: {
                String result = "单价*面积*(1-估价时点完工程度)";
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), result);
                break;
            }
        }
    }

    private MdEconomicIndicatorsApplyDto getMdEconomicIndicatorsApplyDto() {
        MdDevelopmentVo mdDevelopmentVo = getMdDevelopmentVo();
        MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = new MdEconomicIndicatorsApplyDto();
        if (mdDevelopmentVo.getEconomicId() != null) {
            mdEconomicIndicatorsApplyDto = mdEconomicIndicatorsService.getEconomicIndicatorsInfo(mdDevelopmentVo.getEconomicId());
        }
        return mdEconomicIndicatorsApplyDto;
    }

    private SchemeInfo getSchemeInfo() {
        return schemeInfo;
    }

    private MdDevelopmentVo getMdDevelopmentVo() {
        if (this.mdDevelopmentVo == null) {
            MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(schemeInfo.getMethodDataId());
            if (mdDevelopment != null) {
                this.mdDevelopmentVo = mdDevelopmentService.getMdDevelopmentVo(mdDevelopment);
            } else {
                this.mdDevelopmentVo = new MdDevelopmentVo();
            }
        }
        return this.mdDevelopmentVo;
    }

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            this.schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
        }
        return this.schemeAreaGroup;
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


    private synchronized void setFieldObjectValue(ReportFieldDevelopmentEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, MdDevelopmentVo target) {
        String value = mdDevelopmentService.getFieldObjectValueReport(key, target);
        switch (key) {
            case Development_projectConstructionPeriod: {
                value = ArithmeticUtils.getBigDecimalString(target.getProjectConstructionPeriod());
                break;
            }
            case Development_projectConstructionPeriod2: {
                value = ArithmeticUtils.getBigDecimalString(target.getProjectConstructionPeriod());
                break;
            }
            case Development_developedYear: {
                value = ArithmeticUtils.getBigDecimalString(target.getDevelopedYear());
                break;
            }
            case Development_remainingDevelopmentYear: {
                value = ArithmeticUtils.getBigDecimalString(target.getRemainingDevelopmentYear());
                break;
            }
            case Development_constructionSubTotal: {
                value = ArithmeticUtils.getBigDecimalString(target.getConstructionCostSubtotal());
                break;
            }
            case Development_unforeseenExpensesTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_LandGetCostTotal: {
                String v1 = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_LandGetCost, target);
                value = String.join("", v1, "v", "+", ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant()));
                break;
            }
            case Development_LandGetCostTotal2: {
                String v1 = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_LandGetCost, target);
                value = String.join("", v1, "v", "+", ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant()));
                break;
            }
            case Development_LandGetRelevantValue: {
                value = ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant());
                break;
            }
            case Development_landGetRelevant: {
                value = ArithmeticUtils.getBigDecimalString(target.getLandGetRelevant());
                break;
            }
            case Development_managementExpenseTotal: {
                String v1 = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_managementExpenseCorrectRate, target);
                String v2 = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_managementExpenseTotal, target);
                value = String.join("", v1, "v", "+", v2);
                break;
            }
            case Development_managementExpenseRate: {
                value = ArithmeticUtils.getPercentileSystem(target.getManagementExpense(), 2);
                break;
            }
            case Development_salesFeeExplain: {
                value = target.getSalesFeeExplain();
                break;
            }
            case Development_salesFeeRate: {
                value = ArithmeticUtils.getPercentileSystem(target.getSalesFee(), 2);
                break;
            }
            case Development_salesFeeTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_TotalSaleableAreaPrice: {
                value = ArithmeticUtils.getBigDecimalString(target.getTotalSaleableAreaPrice());
                break;
            }
            case Development_infrastructureCostTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_infrastructureCost: {
                value = ArithmeticUtils.getBigDecimalString(target.getInfrastructureCost());
                break;
            }
            case Development_infrastructureMatchingCostTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_infrastructureMatchingCost: {
                value = ArithmeticUtils.getBigDecimalString(target.getInfrastructureMatchingCost());
                break;
            }
            case Development_devDuring: {
                value = ArithmeticUtils.getBigDecimalString(target.getDevDuring());
                break;
            }
            case Development_devDuringTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_otherEngineeringCostTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_otherEngineeringCost: {
                value = ArithmeticUtils.getBigDecimalString(target.getOtherEngineeringCost());
                break;
            }
            case Development_reconnaissanceDesignTotal: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_reconnaissanceDesign: {
                value = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_reconnaissanceDesignTotal, target);
                break;
            }
            case Development_interestInvestmentRate: {
                value = ArithmeticUtils.getPercentileSystem(target.getInterestInvestmentTax(), 2);
                break;
            }
            case Development_interestInvestment: {
                String v1 = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_interestInvestmentCorrectRate, target);
                v1 = ArithmeticUtils.round(v1, 5);
                String v2 = ArithmeticUtils.getBigDecimalString(target.getInterestInvestment());
                value = String.join("", v1, "v", "+", v2);
                break;
            }
            case Development_investmentProfitRate: {
                value = ArithmeticUtils.getPercentileSystem(target.getInvestmentProfitTax(), 2);
                break;
            }
            case Development_investmentProfit: {
                String v1 = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_investmentProfitCorrectRate, target);
                String v2 = ArithmeticUtils.getBigDecimalString(target.getInvestmentProfit());
                value = String.join("", v1, "v", "+", v2);
                break;
            }
            case Development_projectDevelopmentIncomeValue: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_LandPriceCorrectValue: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_assessPrice: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_Price: {
                value = mdDevelopmentService.getFieldObjectValue(key, target);
                break;
            }
            case Development_AmendmentStatusRightsRemark: {
                value = target.getAmendmentStatusRightsExplain();
                if (StringUtils.isEmpty(value)) {
                    value = String.join("", "无", ReportFieldDevelopmentEnum.Development_AmendmentStatusRights.getName(), "修正");
                }
                break;
            }
            case Development_AmendmentStatusRights: {
                value = ArithmeticUtils.getBigDecimalString(target.getAmendmentStatusRights());
                break;
            }
            case Development_OtherAmendmentsRemark: {
                value = target.getOtherAmendmentsExplain();
                if (StringUtils.isEmpty(value)) {
                    value = String.join("", "无", ReportFieldDevelopmentEnum.Development_OtherAmendments.getName(), "修正");
                }
                break;
            }
            case Development_OtherAmendments: {
                value = ArithmeticUtils.getBigDecimalString(target.getOtherAmendments());
                break;
            }
            case Development_intra_territorial_reality: {
                value = target.getParcelSettingInnerName();
                break;
            }
            case Development_extraterritorial_reality: {
                value = target.getParcelSettingOuterName();
                break;
            }
            case Development_total_area: {
//                MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = getMdEconomicIndicatorsApplyDto();
//                if (mdEconomicIndicatorsApplyDto != null && mdEconomicIndicatorsApplyDto.getEconomicIndicators() != null) {
//                    value = ArithmeticUtils.getBigDecimalString(mdEconomicIndicatorsApplyDto.getEconomicIndicators().getAssessUseLandArea());
//                }
                value = mdDevelopmentService.getFieldObjectValue(ReportFieldDevelopmentEnum.Development_total_saleableArea, target);
                if (StringUtils.isNotBlank(value)) {
                    value = ArithmeticUtils.round(value, 2);
                }
                break;
            }
            case Development_SalesTaxAndAdditional:
                value = ArithmeticUtils.getPercentileSystem(target.getSalesTaxAndAdditional(), 2);
                break;
            case Development_reconnaissanceDesignRate:
                value = ArithmeticUtils.getPercentileSystem(target.getReconnaissanceDesign(), 2);
                break;
            case Development_unforeseenExpensesRate:
                value = ArithmeticUtils.getPercentileSystem(target.getUnforeseenExpenses(), 2);
                break;
            case Development_deedCorrecting:
                value = ArithmeticUtils.getPercentileSystem(target.getDeedTaxRate(), 2);
                break;
            case Development_transactionCostCorrecting:
                value = ArithmeticUtils.getPercentileSystem(target.getTransactionTaxRate(), 2);
                break;
            case Development_landIncrementTax: {
                value = ArithmeticUtils.getPercentileSystem(target.getLandValueAddedTax(), 2);
                break;
            }
            case Development_DevelopmentDegreeCorrectionValue: {
                value = ArithmeticUtils.getBigDecimalString(target.getDevelopmentDegreeRevision());
                break;
            }
            case Development_DevelopmentDegreeCorrectionValueRemark: {
                value = target.getDevelopmentDegreeRevisionExplain();
                if (StringUtils.isEmpty(value)) {
                    value = String.join("", "无", ReportFieldDevelopmentEnum.Development_DevelopmentDegreeCorrectionValue.getName(), "修正");
                }
                break;
            }
            case Development_constructionSubtotalContent: {
                value = errorStr;
                break;
            }
            case Development_TotalCompletedPriceRealEstateDevelopment: {
                value = ArithmeticUtils.round(target.getTotalSaleableAreaPrice(), 2);
                break;
            }
            case Development_constructionInstallationEngineeringFee: {
                value = ArithmeticUtils.round(target.getConstructionInstallationEngineeringFee(), 2);
                break;
            }
            default:
                break;
        }
        if (StringUtils.isNotBlank(value)) {
            if (NumberUtils.isNumber(value)) {
                int length = ArithmeticUtils.getDecimalLength(value);
                if (length > 4) {
                    value = ArithmeticUtils.round(value, 2);
                }
            }
        }
        if (StringUtils.isEmpty(value)) {
            return;
        }
        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), value);
    }

    private ProjectPlanDetails getProjectPlanDetailsById(Integer id) {
        return projectPlanDetailsService.getProjectPlanDetailsById(id);
    }


}

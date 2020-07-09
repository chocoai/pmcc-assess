package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.data.DataBuildingInstallCostTypeEnum;
import com.copower.pmcc.assess.common.enums.report.ReportFieldCostMethodEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.dto.output.data.DataBuildingInstallCostVo;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.dto.output.method.MdCostVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataBuildingInstallCostService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdEconomicIndicatorsService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by zch on 2019-8-26.
 * 成本法报告模板
 */
public class GenerateMdCostService {
    private static final String errorStr = "无";

    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;
    private MdCostVo costVo;

    private Integer areaId;
    private SchemeInfo schemeInfo;
    private Integer projectId;

    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private GenerateCommonMethod generateCommonMethod;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private MdMarketCostService mdMarketCostService;
    private BaseService baseService;
    private TaskExecutor taskExecutor;
    private ResidueRatioService residueRatioService;
    private ProjectPlanDetailsService projectPlanDetailsService;
    private MdArchitecturalObjService mdArchitecturalObjService;
    private MdEconomicIndicatorsService mdEconomicIndicatorsService;
    private DataBuildingInstallCostService dataBuildingInstallCostService;
    private DataMethodFormulaService dataMethodFormulaService;
    private ApplicationContext applicationContext;


    public synchronized String generateCompareFile() throws Exception {
        MdCostVo costVo = getMdCostVo();
        String key = Objects.equal("1", costVo.getType()) ? AssessReportFieldConstant.COST_TEMPLATE_BUILDING : AssessReportFieldConstant.COST_TEMPLATE_CONSTRUCTION;
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(key);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new BusinessException("模板文件未找到");
        }
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Map<String, ReportFieldCostMethodEnum> reportFieldEnumMap = new HashMap<String, ReportFieldCostMethodEnum>(0);
        for (ReportFieldCostMethodEnum reportFieldEnum : ReportFieldCostMethodEnum.values()) {
            reportFieldEnumMap.put(reportFieldEnum.getName(), reportFieldEnum);
        }
        Map<ReportFieldCostMethodEnum, String> map = Maps.newHashMap();
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

        final ConcurrentHashMap<String, String> textMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<String, String>(0);
        final ConcurrentHashMap<String, String> bookmarkMap = new ConcurrentHashMap<String, String>(0);
        //设置必要的数据
        final ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsById(schemeInfo.getPlanDetailsId());
        final LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap = Maps.newLinkedHashMap();
        mdArchitecturalObjService.setMdCalculatingMethodEngineeringCostMapData(costJSONObjectMap, projectPlanDetails, projectPlanDetails.getProjectId());
        final MdCostVo mdCostVo = getMdCostVo();
        final SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        final SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        final ToolResidueRatio toolResidueRatio = residueRatioService.getToolResidueRatio(mdCostVo.getMdCostConstruction().getResidueRatioId());
        final MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = getMdEconomicIndicatorsApplyDto();
        if (!map.isEmpty()) {
            for (Map.Entry<ReportFieldCostMethodEnum, String> enumStringEntry : map.entrySet()) {
                try {
                    setFieldObjectValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, mdCostVo, schemeAreaGroup, schemeJudgeObject, toolResidueRatio, mdEconomicIndicatorsApplyDto);
                    setFieldObjectOtherValue(enumStringEntry.getKey(), textMap, fileMap, bookmarkMap, costJSONObjectMap);
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
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

    private void setFieldObjectOtherValue(ReportFieldCostMethodEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap) throws Exception {
        switch (key) {
            case MarketCost_CalculatingMethodEngineeringCostSheet: {
                if (costJSONObjectMap.isEmpty()) {
                    break;
                }
                String path = generateCommonMethod.getLocalPath(RandomStringUtils.randomAlphanumeric(4));
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

                try {
                    mdArchitecturalObjService.writeCalculatingMethodEngineeringCostSheet(documentBuilder, costJSONObjectMap);
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                    String error = e.getMessage();
                }

                AsposeUtils.saveWord(path, document);
                generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), path);
            }
            break;

            default:
                break;
        }
    }


    /**
     * 报告计算 只适合报告
     *
     * @param target
     * @param key
     * @return
     */
    private String getReportDataValue(final MdCostConstructionVo target, ReportFieldCostMethodEnum key) {
        switch (key) {
            case MarketCost_UnitAreaLandPrice: {//成本法单位面积地价
                return ArithmeticUtils.getBigDecimalString(target.getLandPurchasePrice());
            }
            case MarketCost_landPurchasePrice: {//成本法土地购买价格
                BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getDevelopLandAreaTax(), target.getLandPurchasePrice(), 2);
                return ArithmeticUtils.round(bigDecimal.toString(), 0);
            }
            case MarketCost_landGetRelevant: {//成本法土地取得税费
                String param1 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_landPurchasePrice);
                BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.createBigDecimal(param1), target.getLandGetRelevant());
                return ArithmeticUtils.round(bigDecimal.toString(), 0);
            }
            case MarketCost_landGetCost: {//成本法土地取得成本
                String param1 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_landPurchasePrice);
                String param2 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_landGetRelevant);
                String param3 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_additionalCostLandAcquisition);
                BigDecimal v = ArithmeticUtils.add(new BigDecimal[]{ArithmeticUtils.createBigDecimal(param1), ArithmeticUtils.createBigDecimal(param2), ArithmeticUtils.createBigDecimal(param3)});
                return ArithmeticUtils.round(v.toString(), 0);
            }
            case MarketCost_additionalCostLandAcquisition: {
                BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getAdditionalCostLandAcquisition(), target.getDevelopLandAreaTax());
                return ArithmeticUtils.round(bigDecimal.toString(), 0);
            }
            case MarketCost_reconnaissanceDesign: {
                BigDecimal bigDecimal = ArithmeticUtils.multiply(target.getReconnaissanceDesign(), target.getConstructionInstallationEngineeringFee());
                return ArithmeticUtils.round(bigDecimal.toString(), 0);
            }
            case MarketCost_reconnaissanceDesignTotal: {//成本法勘察设计和前期工程费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_reconnaissanceDesign, target);
            }
            case MarketCost_infrastructureCostTotal: {//成本法基础设施建设费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_infrastructureCost, target);
            }
            case MarketCost_infrastructureMatchingCostTotal: {//成本法基础设施建设费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_infrastructureMatchingCost, target);
            }
            case MarketCost_devDuringTotal: {//成本法开发期间税费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_devDuring, target);
            }
            case MarketCost_otherEngineeringCostTotal: {//成本法其他工程费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_otherEngineeringCost, target);
            }
            case MarketCost_landGetCostTotal: {//成本法土地取得成本总计
                return ArithmeticUtils.round(mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_landGetCostTotal, target), 2);
            }
            case MarketCost_constructionSub: {//成本法建设成本
                String param1 = target.getConstructionInstallationEngineeringFee().toString();
                String param2 = ArithmeticUtils.mul(param1, target.getReconnaissanceDesign().toString(), 2);
                BigDecimal param3 = target.getInfrastructureCost();
                BigDecimal param4 = target.getInfrastructureMatchingCost();
                BigDecimal param5 = target.getDevDuring();
                BigDecimal param6 = target.getOtherEngineeringCost();
                BigDecimal bigDecimal = ArithmeticUtils.add(new BigDecimal[]{ArithmeticUtils.createBigDecimal(param1), ArithmeticUtils.createBigDecimal(param2), param3, param4, param5, param6});
                return ArithmeticUtils.round(bigDecimal.toString(), 2);
            }
            case MarketCost_Unilateral_cost: {
                List<BigDecimal> bigDecimalList = Lists.newArrayList();
                bigDecimalList.add(target.getConstructionInstallationEngineeringFee());
                bigDecimalList.add(target.getInfrastructureCost());
                bigDecimalList.add(target.getInfrastructureMatchingCost());
                bigDecimalList.add(target.getOtherEngineeringCost());
                bigDecimalList.add(target.getDevDuring());
                BigDecimal bigDecimal = ArithmeticUtils.add(bigDecimalList);
                return ArithmeticUtils.round(bigDecimal.toString(), 0);
            }
            case MarketCost_constructionSubtotal: {//成本法建设成本总计
                String param1 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_constructionSub);
                BigDecimal bigDecimal = ArithmeticUtils.mul(param1, target.getDevelopBuildAreaTax().toString());
                bigDecimal = ArithmeticUtils.div(bigDecimal, ArithmeticUtils.createBigDecimal(10000));
                return ArithmeticUtils.round(bigDecimal.toString(), 2);
            }
            case MarketCost_unforeseenExpensesTotal: {//成本法不可预见费总计
                String param1 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_constructionSubtotal);
                BigDecimal bigDecimal = ArithmeticUtils.mul(param1, target.getUnforeseenExpenses().toString());
                return ArithmeticUtils.round(bigDecimal.toString(), 2);
            }
            case MarketCost_managementExpenseTotal: {//成本法管理费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_managementExpense, target);
            }
            case MarketCost_salesFeeTotal: {//成本法销售费总计
//                return mdMarketCostService.getFieldObjectValue(BaseReportMarketCostEnum.MarketCost_salesFee, target);
                String v1 = ArithmeticUtils.round(target.getSalesFee().toString(), 4);
                return String.join("", v1, "v");
            }
            case MarketCost_salesTaxAndAdditionalTotal: {//成本法销售税金及附加总计
//                return mdMarketCostService.getFieldObjectValue(BaseReportMarketCostEnum.MarketCost_salesTaxAndAdditionalTotal, target);

                String v1 = ArithmeticUtils.round(target.getSalesTaxAndAdditional().toString(), 4);
                return String.join("", v1, "v");
            }
            case MarketCost_interestInvestment: {
                String v1 = target.getInterestInvestment();
                String v2 = mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_interestInvestmentCorrectRate, target);
                return String.join("", v1, "+", v2 + "v");
            }
            case MarketCost_investmentProfit: {
                String v1 = target.getInvestmentProfit();
                String v2 = mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_investmentProfitCorrectRate, target);
                v2 = ArithmeticUtils.round(ArithmeticUtils.createBigDecimal(v2), 4);
                return String.join("", v1, "+", v2 + "v");
            }
            case MarketCost_constructionInstallationEngineeringFeeTotal: {//成本法建筑安装工程费总计
                return mdMarketCostService.getFieldObjectValue(ReportFieldCostMethodEnum.MarketCost_constructionInstallationEngineeringFee, target);
            }
            case MarketCost_EstateLandPrice: {//成本法楼面土地单价
                String v = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_landGetCost);
                v = ArithmeticUtils.div(v, target.getDevelopBuildAreaTax().toString());
                return ArithmeticUtils.round(v, 0);
            }
            case MarketCost_developBuildArea: {//成本法开发建筑面积
                return ArithmeticUtils.round(target.getDevelopBuildAreaTax().toString(), 0);
            }
            case MarketCost_CompleteCostTotalValue: {//成本法完全成本计算值总计 万元
                return target.getConstructionAssessmentValue();
            }
            case MarketCost_CompleteCostValue: {//成本法完全成本计算值
                String v = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_CompleteCostTotalValue);
                return ArithmeticUtils.mul(v, "10000", 0);
            }
            case MarketCost_constructionAssessmentPriceCorrecting: {//成本法单价
                String param1 = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_CompleteCostValue);
                String value = ArithmeticUtils.div(param1, target.getDevelopBuildAreaTax().toString());
                return ArithmeticUtils.round(value, 0);
            }
            case MarketCost_constructionAssessmentPriceCorrecting2: {
                String v = getReportDataValue(target, ReportFieldCostMethodEnum.MarketCost_constructionAssessmentPriceCorrecting);
                if (target.getResidueRatio() != null) {
                    v = ArithmeticUtils.mul(v, target.getResidueRatio().toString(), 2);
                }
                return v;
            }
            default:
                return "";
        }
    }

    /**
     * 传入的待组装对象最好是不可变的对象或者副本对象
     *
     * @param key
     * @param textMap
     * @param fileMap
     * @param bookmarkMap
     * @param mdCostVo
     * @param schemeAreaGroup
     * @param schemeJudgeObject
     * @param toolResidueRatio
     */
    private void setFieldObjectValue(ReportFieldCostMethodEnum key, final ConcurrentHashMap<String, String> textMap, final ConcurrentHashMap<String, String> fileMap, final ConcurrentHashMap<String, String> bookmarkMap, MdCostVo mdCostVo, SchemeAreaGroup schemeAreaGroup, SchemeJudgeObject schemeJudgeObject, ToolResidueRatio toolResidueRatio, MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto) throws Exception {
        MdCostConstructionVo target = mdCostVo.getMdCostConstruction();
        String algsValue = getReportDataValue(target, key);
        if (StringUtils.isNotBlank(algsValue)) {
            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), algsValue);
        }
        //非计算类数据
        switch (key) {
            case MarketCost_JudgeObject: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), String.join("", generateCommonMethod.getSchemeJudgeObjectShowName(schemeJudgeObject), "估价对象"));
            }
            break;
            case MarketCost_Merge_JudgeObject: {
                String v = "无合并对象";
                if (schemeJudgeObject.getBisMerge() != null) {
                    List<SchemeJudgeObject> schemeJudgeObjectVoList = schemeJudgeObjectService.getListByPid(schemeJudgeObject.getId());
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectVoList)) {
                        List<Integer> integerList = Lists.newArrayList();
                        for (SchemeJudgeObject schemeJudgeObjectVo : schemeJudgeObjectVoList) {
                            integerList.add(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObjectVo.getNumber()));
                        }
                        v = generateCommonMethod.convertNumber(integerList);
                        v = String.join("", v, "估价对象");
                    }
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), v);
            }
            break;
            case MarketCost_infrastructureCost: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.round(target.getInfrastructureCost().toString(), 2));
                break;
            }
            case MarketCost_formula: {
                DataMethodFormula formula = new DataMethodFormula();
                if(StringUtils.equals("1",mdCostVo.getType())){
                    formula = dataMethodFormulaService.getMethodFormulaByMethodKey(AssessDataDicKeyConstant.COST_BUILDING);
                }
                if(StringUtils.equals("2",mdCostVo.getType())){
                    formula = dataMethodFormulaService.getMethodFormulaByMethodKey(AssessDataDicKeyConstant.COST_ENGINEERING);
                }
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), formula.getFormula());
                break;
            }
            case MarketCost_infrastructureMatchingCost: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.round(target.getInfrastructureMatchingCost().toString(), 2));
                break;
            }
            case MarketCost_devDuring: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.round(target.getDevDuring().toString(), 2));
                break;
            }
            case MarketCost_otherEngineeringCost: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.round(target.getOtherEngineeringCost().toString(), 2));
                break;
            }
            case MarketCost_Assessment_land_use_right_area: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getBigDecimalString(target.getDevelopLandAreaTax()));
            }
            break;
            case MarketCost_landGetRelevantRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getLandGetRelevant(), 2));
            }
            break;
            case MarketCost_managementExpenseRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getManagementExpense(), 2));
            }
            break;
            case MarketCost_salesTaxAndAdditionalRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getSalesTaxAndAdditional(), 2));
            }
            break;
            case MarketCost_salesFeeRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getSalesFee(), 2));
            }
            break;
            case MarketCost_reconnaissanceDesignRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getReconnaissanceDesign(), 2));
            }
            break;
            case MarketCost_unforeseenExpensesRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getUnforeseenExpenses(), 2));
            }
            break;
            case MarketCost_interestInvestmentRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getInterestInvestmentTax(), 2));
            }
            break;
            case MarketCost_investmentProfitRate: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getInvestmentProfitTax(), 2));
            }
            break;
            case MarketCost_developYearNumberTax: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.round(target.getDevelopYearNumberTax().toString(), 2));
            }
            break;
            case MarketCost_Method: {
                if (mdCostVo.getMdCostConstruction().getMcId() != null) {
                    try {
                        GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(schemeJudgeObject.getId(), mdCostVo.getMdCostConstruction().getMcId(), schemeAreaGroup.getId());
                        generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, key.getName(), generateMdCompareService.generateCompareFile());
                    } catch (Exception e) {
                        baseService.writeExceptionInfo(e);
                    }
                } else {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "市场比较法");
                }
            }
            break;
            case MarketCost_extraterritorial_reality: {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply == null) {
                    return;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicEstateVo basicEstateVo = generateBaseExamineService.getEstate();
                if (basicEstateVo != null) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), basicEstateVo.getInfrastructureName());
                }
            }
            break;
            case MarketCost_intra_territorial_reality: {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply == null) {
                    return;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                try {
                    BasicEstateLandStateVo basicEstateLandStateVo = generateBaseExamineService.getBasicEstateLandState();
                    if (basicEstateLandStateVo != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), basicEstateLandStateVo.getDevelopmentDegreeContentName());
                    }
                } catch (Exception e) {
                }
            }
            break;
            case MarketCost_Degree_land_development: {
                BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                if (basicApply == null) {
                    return;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                try {
                    BasicEstateLandStateVo basicEstateLandStateVo = generateBaseExamineService.getBasicEstateLandState();
                    if (basicEstateLandStateVo != null) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), basicEstateLandStateVo.getDevelopmentDegreeName());
                    }
                } catch (Exception e) {
                }
            }
            break;
            case MarketCost_region: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), schemeAreaGroup.getAreaName());
            }
            break;
            case MarketCost_extraterritorial_setting: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getParcelSettingOuterName());
            }
            break;
            case MarketCost_intra_territorial_setting: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getParcelSettingInnerName());
            }
            break;
            case MarketCost_GroundFloor_AreaCounted_volume_ratio://并列case 匹配
            {
                String str = "groundBuildingArea";
                if (mdEconomicIndicatorsApplyDto == null) {
                    break;
                }
                List<MdEconomicIndicatorsItem> itemList = mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList();
                List<BigDecimal> bigDecimalList = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(itemList)) {
                    for (MdEconomicIndicatorsItem item : itemList) {
                        if (item.getPlannedBuildingArea() == null) {
                            continue;
                        }
                        if (!Objects.equal(item.getDataKey(), str)) {
                            continue;
                        }
                        bigDecimalList.add(item.getPlannedBuildingArea());
                    }
                }
                if (CollectionUtils.isNotEmpty(bigDecimalList)) {
                    ArithmeticUtils.add(bigDecimalList);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getBigDecimalString(ArithmeticUtils.add(bigDecimalList)));
                } else {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "0");
                }
                break;
            }
            case MarketCost_EconomicIndicatorsField1: {
                if (mdEconomicIndicatorsApplyDto == null) {
                    break;
                }
                List<MdEconomicIndicatorsItem> itemList = mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList();
                List<BigDecimal> bigDecimalList = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(itemList)) {
                    for (MdEconomicIndicatorsItem item : itemList) {
                        if (item.getAssessArea() == null) {
                            continue;
                        }

                        bigDecimalList.add(item.getAssessArea());
                    }
                }
                if (CollectionUtils.isNotEmpty(bigDecimalList)) {
                    ArithmeticUtils.add(bigDecimalList);
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getBigDecimalString(ArithmeticUtils.add(bigDecimalList)));
                } else {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "0");
                }
            }
            break;
            case MarketCost_constructionInstallationEngineeringFee: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getBigDecimalString(target.getConstructionInstallationEngineeringFee()));
            }
            break;
            case MarketCost_constructionInstallationEngineeringFee_Basis: {
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
            }
            break;
            case MarketCost_Planning_land_area_construction: {
                if (mdEconomicIndicatorsApplyDto != null && mdEconomicIndicatorsApplyDto.getEconomicIndicators() != null) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(),
                            ArithmeticUtils.getBigDecimalString(mdEconomicIndicatorsApplyDto.getEconomicIndicators().getPlanNetConstructionLandArea()));
                }
            }
            break;
            case MarketCost_AssessBuildArea: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(),
                        ArithmeticUtils.getBigDecimalString(target.getDevelopBuildAreaTax()));
            }
            break;
            case MarketCost_AssessUseLandArea: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(),
                        ArithmeticUtils.getBigDecimalString(target.getDevelopLandAreaTax()));
            }
            break;
            case MarketCost_landPurchasePriceExplain: {
                if (StringUtils.isNotBlank(target.getLandPurchasePriceExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getLandPurchasePriceExplain());
                }
            }
            break;
            case MarketCost_landGetRelevantExplain: {
                if (StringUtils.isNotBlank(target.getLandGetRelevantExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getLandGetRelevantExplain());
                }
            }
            break;
            case MarketCost_additionalCostLandAcquisitionExplain: {
                if (StringUtils.isNotBlank(target.getAdditionalCostLandAcquisitionExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getAdditionalCostLandAcquisitionExplain());
                }
            }
            break;
            case MarketCost_infrastructureCostBasis: {
                if (StringUtils.isNotBlank(target.getUnforeseenExpensesExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getInfrastructureCostExplain());
                }
            }
            break;
            case MarketCost_unforeseenExpensesExplain: {
                if (StringUtils.isNotBlank(target.getUnforeseenExpensesExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getUnforeseenExpensesExplain());
                }
            }
            break;
            case MarketCost_managementExpenseExplain: {
                if (StringUtils.isNotBlank(target.getManagementExpenseExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getManagementExpenseExplain());
                }
            }
            break;
            case MarketCost_salesFeeExplain: {
                if (StringUtils.isNotBlank(target.getSalesFeeExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getSalesFeeExplain());
                }
            }
            break;
            case MarketCost_interestInvestmentTaxExplain: {
                if (StringUtils.isNotBlank(target.getInterestInvestmentTaxExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getInterestInvestmentTaxExplain());
                }
            }
            break;
            case MarketCost_salesTaxAndAdditionalExplain: {
                if (StringUtils.isNotBlank(target.getSalesTaxAndAdditionalExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getSalesTaxAndAdditionalExplain());
                }
            }
            break;
            case MarketCost_investmentProfitTaxExplain: {
                if (StringUtils.isNotBlank(target.getInvestmentProfitTaxExplain())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getInvestmentProfitTaxExplain());
                }
            }
            break;
            case MarketCost_ResidueRatio: {
                generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), ArithmeticUtils.getPercentileSystem(target.getResidueRatio(), 2));
            }
            break;
            case MarketCost_ResidueRatio_method: {
                if (target.getResidueRatioId() != null) {
                    if (toolResidueRatio == null) {
                        break;
                    }
                    if (toolResidueRatio.getType() == null) {
                        break;
                    }
                    switch (toolResidueRatio.getType()) {
                        case 0:
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "年限法");
                            break;
                        case 1:
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "观察法");
                            break;
                        case 2:
                            generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "综合法");
                            break;
                    }
                }
            }
            break;
            case MarketCost_ResidueRatio_remark: {
                if (StringUtils.isNotBlank(target.getResidueRatioRemark())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), target.getResidueRatioRemark());
                } else {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "无说明");
                }
                break;
            }
            case MarketCost_ResidueRatio_formula: {
                if (toolResidueRatio == null) {
                    break;
                }
                if (toolResidueRatio.getType() == null) {
                    break;
                }
                switch (toolResidueRatio.getType()) {
                    case 0:
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "1-(1-残值率)×已使用年限÷耐用年限");
                        break;
                    case 1:
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "主题结构成新率+设备成新率+装饰成新率");
                        break;
                    case 2:
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), "(1-(1-残值率)×已使用年限÷耐用年限)×年限法权重+(主题结构成新率+设备成新率+装饰成新率)×观察法权重");
                        break;
                }
            }
            break;
            case MarketCost_ResidueRatio_value: {
                if (toolResidueRatio == null) {
                    break;
                }
                try {
                    StringBuilder stringBuilder = new StringBuilder(8);
                    switch (toolResidueRatio.getType()) {
                        case 0:
                            //"1-(1-残值率)×已使用年限÷耐用年限"
                            stringBuilder.append("1-(1-").append(ArithmeticUtils.getPercentileSystem(toolResidueRatio.getResidualRatio(), 2)).append(")");
                            stringBuilder.append("×");
                            stringBuilder.append(ArithmeticUtils.getBigDecimalString(toolResidueRatio.getUsedYear()));
                            stringBuilder.append("÷");
                            stringBuilder.append(ArithmeticUtils.getBigDecimalString(toolResidueRatio.getUsableYear()));
                            break;
                        case 1:
                            break;
                        case 2:
                            //(1-(1-残值率)×已使用年限÷耐用年限)×年限法权重+(主题结构成新率+设备成新率+装饰成新率)×观察法权重
                            stringBuilder.append("[");
                            stringBuilder.append("(1-(1-").append(ArithmeticUtils.getPercentileSystem(toolResidueRatio.getResidualRatio(), 2)).append(")");
                            stringBuilder.append("×");
                            stringBuilder.append(ArithmeticUtils.getBigDecimalString(toolResidueRatio.getUsedYear()));
                            stringBuilder.append("÷");
                            stringBuilder.append(ArithmeticUtils.getBigDecimalString(toolResidueRatio.getUsableYear()));
                            stringBuilder.append(")");
                            stringBuilder.append("]");
                            stringBuilder.append("×");
                            stringBuilder.append(ArithmeticUtils.getPercentileSystem(toolResidueRatio.getAgeRate(), 2));

                            stringBuilder.append(" + ");
                            double v = ArithmeticUtils.parseFormatBigDecimal(toolResidueRatio.getResultValue()).doubleValue();
                            double v1 = (1 - (1 - toolResidueRatio.getResidualRatio().doubleValue()) * toolResidueRatio.getUsedYear().doubleValue() / toolResidueRatio.getUsableYear().doubleValue()) * toolResidueRatio.getAgeRate().doubleValue();
                            double tax = (v - v1) / toolResidueRatio.getObserveRate().doubleValue();
                            stringBuilder.append(ArithmeticUtils.getPercentileSystem(ArithmeticUtils.createBigDecimal(tax), 2)).append("×");
                            stringBuilder.append(ArithmeticUtils.getPercentileSystem(toolResidueRatio.getObserveRate(), 2));
                            break;
                    }
                    if (StringUtils.isNotBlank(stringBuilder.toString())) {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), stringBuilder.toString());
                    } else {
                        generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, key.getName(), toolResidueRatio.getResultValue());
                    }
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                }
            }
            break;
            default:
                break;
        }
    }


    private MdCostVo getMdCostVo() {
        if (this.costVo == null) {
            synchronized (this) {
                this.costVo = mdMarketCostService.getMdCostVo(mdMarketCostService.getByMdCostId(schemeInfo.getMethodDataId()));
            }
        }
        return this.costVo;
    }

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            synchronized (this) {
                this.schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
            }
        }
        return this.schemeAreaGroup;
    }

    private SchemeJudgeObject getSchemeJudgeObject() {
        if (schemeJudgeObject == null) {
            synchronized (this) {
                if (schemeInfo.getJudgeObjectId() != null) {
                    schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeInfo.getJudgeObjectId());
                }
                if (schemeJudgeObject == null) {
                    schemeJudgeObject = new SchemeJudgeObject();
                }
            }
        }
        return schemeJudgeObject;
    }

    private ProjectPlanDetails getProjectPlanDetailsById(Integer id) {
        return projectPlanDetailsService.getProjectPlanDetailsById(id);
    }

    private MdEconomicIndicatorsApplyDto getMdEconomicIndicatorsApplyDto() {
        MdCostVo costVo = getMdCostVo();
        MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = new MdEconomicIndicatorsApplyDto();
        if (costVo.getMdCostConstruction().getEconomicId() != null) {
            mdEconomicIndicatorsApplyDto = mdEconomicIndicatorsService.getEconomicIndicatorsInfo(costVo.getMdCostConstruction().getEconomicId());
        }
        return mdEconomicIndicatorsApplyDto;
    }

    public GenerateMdCostService(Integer projectId, SchemeInfo schemeInfo, Integer areaId) {
        this.projectId = projectId;
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;
        this.applicationContext = SpringContextUtils.getApplicationContext();
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.mdMarketCostService = SpringContextUtils.getBean(MdMarketCostService.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.taskExecutor = SpringContextUtils.getBean(TaskExecutor.class);
        this.residueRatioService = SpringContextUtils.getBean(ResidueRatioService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
        this.mdArchitecturalObjService = SpringContextUtils.getBean(MdArchitecturalObjService.class);
        this.mdEconomicIndicatorsService = SpringContextUtils.getBean(MdEconomicIndicatorsService.class);
        this.dataBuildingInstallCostService = SpringContextUtils.getBean(DataBuildingInstallCostService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
    }


}

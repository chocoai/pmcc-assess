package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.ReportFieldCompareEnum;
import com.copower.pmcc.assess.common.enums.basic.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
import com.copower.pmcc.assess.service.data.DataHousePriceIndexService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kings on 2019-1-31.
 */
public class GenerateMdCompareService {

    private Integer mcId;
    private Integer schemeJudgeObjectId;
    private Date valueTimePoint;
    private Integer areaId;
    private List<DataSetUseField> setUseFieldList;
    private SchemeAreaGroup schemeAreaGroup;
    private MdMarketCompare marketCompare;
    private MdMarketCompareItem evaluationItem;
    private List<MdMarketCompareItem> caseItemList;


    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private CommonService commonService;
    private MdMarketCompareService mdMarketCompareService;
    private DataSetUseFieldService dataSetUseFieldService;
    private BasicApplyService basicApplyService;
    private BasicHouseTradingService basicHouseTradingService;
    private BaseDataDicService baseDataDicService;
    private DataMethodFormulaService dataMethodFormulaService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    private DataHousePriceIndexService dataHousePriceIndexService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GenerateCommonMethod generateCommonMethod;

    private GenerateMdCompareService() {
    }

    public GenerateMdCompareService(Integer schemeJudgeObjectId, Integer mcId, Integer areaId) throws Exception {

        this.commonService = SpringContextUtils.getBean(CommonService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.basicApplyService = SpringContextUtils.getBean(BasicApplyService.class);
        this.basicHouseTradingService = SpringContextUtils.getBean(BasicHouseTradingService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
        this.dataHousePriceIndexDao = SpringContextUtils.getBean(DataHousePriceIndexDao.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.dataHousePriceIndexService = SpringContextUtils.getBean(DataHousePriceIndexService.class);
        this.schemeJudgeObjectId = schemeJudgeObjectId;
        this.mcId = mcId;
        this.areaId = areaId;
        getEvaluationItemList();
        schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
        this.valueTimePoint = schemeAreaGroup.getValueTimePoint();
    }

    /**
     * 获取比较法主表
     *
     * @return
     */
    public MdMarketCompare getMarketCompare() {
        if (marketCompare != null) return marketCompare;
        MdMarketCompare mdMarketCompare = mdMarketCompareService.getMdMarketCompare(this.mcId);
        this.marketCompare = mdMarketCompare;
        return mdMarketCompare;
    }

    /**
     * 获取估价对象信息
     *
     * @return
     */
    public MdMarketCompareItem getEvaluationItemList() throws Exception {
        if (evaluationItem != null)
            return this.evaluationItem;
        MdMarketCompareItem marketCompareItem = mdMarketCompareService.getEvaluationByMcId(getMarketCompare().getId());
        this.evaluationItem = marketCompareItem;
        return marketCompareItem;
    }

    /**
     * 获取案例信息
     *
     * @return
     */
    public List<MdMarketCompareItem> getCaseItemList() {
        if (CollectionUtils.isNotEmpty(caseItemList))
            return this.caseItemList;
        List<MdMarketCompareItem> itemList = mdMarketCompareService.getCaseListByMcId(getMarketCompare().getId());
        this.caseItemList = itemList;
        return itemList;
    }

    //生成文件
    public String generateCompareFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.COMPARE_TEMPLATE);
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

    /**
     * 根据字段key值获取被替换的文本
     *
     * @param key
     * @return
     */
    public String getValueText(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        ReportFieldCompareEnum fieldCompareEnum = ReportFieldCompareEnum.getEnumByName(key);
        //估价对象数据
        MdMarketCompareItem evaluationItemList = getEvaluationItemList();
        List<MarketCompareItemDto> marketCompareItemDtos = JSON.parseArray(evaluationItemList.getJsonContent(), MarketCompareItemDto.class);
        //案列数据
        List<MdMarketCompareItem> caseItemList = getCaseItemList();
        //全部数据
        List<MdMarketCompareItem> data = Lists.newArrayList();
        data.add(evaluationItemList);
        data.addAll(caseItemList);

        String str = null;
        if (fieldCompareEnum != null) {
            switch (fieldCompareEnum) {
                case PRICE_CONNOTATION:
                    str = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.PRICE_CONNOTATION.getKey());
                    break;
                case CASE_NUMBER:
                    str = toChinese(String.valueOf(caseItemList.size()));
                    break;
                case PROPERTY_RANGE:
                    str = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.SCOPE_PROPERTY.getKey());
                    break;
                case PAYMENT_METHOD:
                    str = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.PAYMENT_METHOD.getKey());
                    break;
                case FINANCING_CONDITION:
                    str = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.FINANCING_CONDITIONS.getKey());
                    break;
                case TAX_BURDEN:
                    str = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.TAX_BURDEN.getKey());
                    break;
            }
        }
        return str;
    }

    /**
     * 根据字段key值获取被替换的文件路径
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        ReportFieldCompareEnum fieldCompareEnum = ReportFieldCompareEnum.getEnumByName(key);
        //估价对象数据
        MdMarketCompareItem evaluationItemList = getEvaluationItemList();
        List<MarketCompareItemDto> marketCompareItemDtos = JSON.parseArray(evaluationItemList.getJsonContent(), MarketCompareItemDto.class);
        //案列数据
        List<MdMarketCompareItem> caseItemList = getCaseItemList();
        //全部数据
        List<MdMarketCompareItem> data = Lists.newArrayList();
        data.add(evaluationItemList);
        data.addAll(caseItemList);

        String localPath = null;
        if (fieldCompareEnum != null) {
            String title = fieldCompareEnum.getName();
            switch (fieldCompareEnum) {
                case DESIGN_FORMULAS:
                    localPath = getDesignFormulas(title);
                    break;
                case COMPARABLE_BASIS:
                    localPath = getComparableBasisTable(caseItemList, false);
                    break;
                case LOCATION_CONDITION:
                    localPath = getTable(data, title, "location.condition", "", false);
                    break;
                case RIGHTS_INTERESTS:
                    localPath = getTable(data, title, "equity.condition", "", false);
                    break;
                case ENTITY_CONDITION:
                    localPath = getEntityConditionTable(marketCompareItemDtos, data, title, false);
                    break;
                case TRANSACTION_MODIFICATION:
                    localPath = getTransaction(caseItemList, MethodCompareFieldEnum.TRADING_TRANSACTION_SITUATION.getKey());
                    break;
                case DATE_REVISION:
                    localPath = getDateRevision(caseItemList, MethodCompareFieldEnum.TRADING_TIME.getKey());
                    break;
                case MARKET_ADJUSTMENT:
                    localPath = getTable(data, title, "comparative.basis", "", true);
                    break;
                case LOCATION_QUOTIENT:
                    localPath = getTable(data, title, "location.condition", "", true);
                    break;
                case EQUITY_INDEX:
                    localPath = getTable(data, title, "equity.condition", "", true);
                    break;
                case ENTITY_INDEX:
                    localPath = getEntityConditionTable(marketCompareItemDtos, data, title, true);
                    break;
                case CALCULATION_RESULT:
                    localPath = getCalculationTable(evaluationItemList, data);
                    break;
                case HOUSEPRICE_INDEX:
                    localPath = getHousepriceIndex(title, caseItemList);
                    break;
                case COUNT_COURSE:
                    localPath = getCountCourse(title, caseItemList, true);
                    break;
                case COUNT_RESULT:
                    localPath = getCountCourse(title, caseItemList, false);
                    break;
            }
        }
        return localPath;
    }


    /**
     * 生成状况表或对应指数表格
     *
     * @param caseItemList 数据
     * @param title        标题
     * @param isIndex      是否是对应的指数表
     * @return
     */
    public String getTable(List<MdMarketCompareItem> caseItemList, String title, String fieldName, String fieldMore, Boolean isIndex) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        //表格属性
        setTableProperty(builder);
        String localPath = generateCommonMethod.getLocalPath();
        //表头
        builder.insertCell();
        builder.write("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.write(caseItem.getName());
        }
        builder.endRow();
        //生成表格
        GenerateTable(fieldName, builder, caseItemList, isIndex);

        builder.endTable();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 生成可比案例情况表或对应指数表格
     *
     * @param caseItemList 数据
     * @param isIndex      是否是对应的指数表
     * @return
     */
    public String getComparableBasisTable(List<MdMarketCompareItem> caseItemList, Boolean isIndex) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        //表格属性
        setTableProperty(builder);
        String localPath = generateCommonMethod.getLocalPath();
        //表头
        builder.insertCell();
        builder.write("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.write(caseItem.getName());
        }
        builder.endRow();

        List<DataSetUseField> useFieldList = dataSetUseFieldService.getCacheSetUseFieldList("comparative.basis");
        List<DataSetUseField> tradFieldList = dataSetUseFieldService.getCacheSetUseFieldList("trading.status");
        useFieldList.addAll(tradFieldList);

        //楼盘名称放在第一行
        builder.insertCell();
        builder.write(MethodCompareFieldEnum.ESTATE_NAME.getName());
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto item2 : dtos) {
                    if (item2.getName().equals(MethodCompareFieldEnum.ESTATE_NAME.getKey())) {
                        builder.insertCell();
                        if (isIndex) {
                            builder.write(item2.getScore().toString());
                        } else {
                            builder.write(item2.getValue());
                        }
                    }
                }
            }
        }
        builder.endRow();

        //其他数据
        for (DataSetUseField useField : useFieldList) {
            builder.insertCell();
            builder.write(MethodCompareFieldEnum.getNameByKey(useField.getFieldName()));
            if (CollectionUtils.isNotEmpty(caseItemList)) {
                for (MdMarketCompareItem caseItem : caseItemList) {
                    List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                    for (MarketCompareItemDto item2 : dtos) {
                        if (item2.getName().equals(useField.getFieldName())) {
                            builder.insertCell();
                            if (isIndex) {
                                builder.write(item2.getScore() == null ? "" : item2.getScore().toString());
                            } else {
                                builder.write(StringUtil.isEmpty(item2.getValue()) ? "无" : item2.getValue());
                            }
                        }
                    }
                }
            }
            builder.endRow();
        }

        builder.endTable();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 生成实体状况表
     *
     * @param caseItemList 数据
     * @param title        标题
     * @param isIndex      是否是对应的指数表
     * @return
     */
    public String getEntityConditionTable(List<MarketCompareItemDto> list, List<MdMarketCompareItem> caseItemList, String title, Boolean isIndex) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        //表格属性
        setTableProperty(builder);
        String localPath = generateCommonMethod.getLocalPath();
        //表头
        builder.insertCell();
        builder.write("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.write(caseItem.getName());
        }
        builder.endRow();
        //生成表格
        GenerateEntityConditionTable(list, builder, caseItemList, isIndex);

        builder.endTable();
        doc.save(localPath);
        return localPath;
    }


    public void GenerateEntityConditionTable(List<MarketCompareItemDto> list, DocumentBuilder builder, List<MdMarketCompareItem> caseItemList, Boolean isIndex) throws Exception {
        //建造年份等
        for (MarketCompareItemDto item : list) {
            if (item.getName().equals(MethodCompareFieldEnum.BUILDING_YEAR.getKey()) || item.getName().equals(MethodCompareFieldEnum.CONSTRUCTION_QUALITY.getKey())
                    || item.getName().equals(MethodCompareFieldEnum.BUILDING_STRUCTURE.getKey()) || item.getName().equals(MethodCompareFieldEnum.APPEARANCE.getKey())
                    || item.getName().equals(MethodCompareFieldEnum.BUILDING_AREA.getKey())) {
                builder.insertCell();
                builder.write(MethodCompareFieldEnum.getNameByKey(item.getName()));
                if (CollectionUtils.isNotEmpty(caseItemList)) {
                    for (MdMarketCompareItem caseItem : caseItemList) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto data : dtos) {
                            if (data.getName().equals(item.getName())) {
                                if (isIndex) {
                                    builder.insertCell();
                                    builder.write(String.valueOf(data.getScore()));
                                } else {
                                    builder.insertCell();
                                    builder.write(data.getValue());
                                }
                            }
                        }
                    }
                }
                builder.endRow();
            }

        }
        //建筑规模
        builder.insertCell();
        builder.write("建筑规模");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            DecimalFormat df = new DecimalFormat("###.##");
            for (MdMarketCompareItem caseItem : caseItemList) {
                BigDecimal scoreTotal = new BigDecimal("0.00");
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.BUILDING_SCALE.getKey())) {
                        scoreTotal = data.getScore();
                        if (!isIndex) {
                            this.jointContent(content, data);
                        }
                    }
                }
                if (isIndex) {
                    builder.write(df.format(scoreTotal));
                } else {
                    builder.write(content.toString());
                }
            }
        }
        builder.endRow();
        //层高
        builder.insertCell();
        builder.write("层高");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            DecimalFormat df = new DecimalFormat("###.##");
            for (MdMarketCompareItem caseItem : caseItemList) {
                BigDecimal ratio = new BigDecimal("1");
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.FLOOR_HEIGHT.getKey()) || data.getName().equals(MethodCompareFieldEnum.NET_HEIGHT.getKey())) {
                        if(data.getScore().compareTo(new BigDecimal("100"))!=0){
                            ratio = ratio.multiply(data.getRatio());
                        }
                        if (!isIndex) {
                            if (StringUtil.isNotEmpty(data.getValue()) && !"无".equals(data.getValue())) {
                                content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                                content.append(String.format("%s%s", data.getValue(), "米"));
                            } else {
                                this.jointContent(content, data);
                            }
                            content.append(";");
                        }
                    }
                }
                if (isIndex) {
                    if(ratio.compareTo(new BigDecimal("1"))==0){
                        builder.write("100");
                    }else{
                        builder.write(df.format(new BigDecimal("100").divide(ratio, 2, BigDecimal.ROUND_HALF_UP)));
                    }
                } else {
                    content.deleteCharAt(content.length() - 1).toString();
                    builder.write(content.toString());
                }
            }
        }
        builder.endRow();
        //空间布局
        builder.insertCell();
        builder.write("空间布局");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            DecimalFormat df = new DecimalFormat("###.##");
            for (MdMarketCompareItem caseItem : caseItemList) {
                BigDecimal ratio = new BigDecimal("1");
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.ELEVATOR_HOUSEHOLD_RATIO.getKey()) || data.getName().equals(MethodCompareFieldEnum.PLANE_LAYOUT.getKey())) {
                        if(data.getScore().compareTo(new BigDecimal("100"))!=0){
                            ratio = ratio.multiply(data.getRatio());
                        }
                        if (!isIndex) {
                            this.jointContent(content, data);
                        }
                    }
                }
                if (isIndex) {
                    if(ratio.compareTo(new BigDecimal("1"))==0){
                        builder.write("100");
                    }else{
                        builder.write(df.format(new BigDecimal("100").divide(ratio, 2, BigDecimal.ROUND_HALF_UP)));
                    }
                } else {
                    builder.write(content.toString());
                }
            }
        }
        builder.endRow();
        //装饰装修
        builder.insertCell();
        builder.write("装饰装修");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            DecimalFormat df = new DecimalFormat("###.##");
            for (MdMarketCompareItem caseItem : caseItemList) {
                BigDecimal ratio = new BigDecimal("1");
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.ARCHITECTURAL_OUTFIT.getKey()) || data.getName().equals(MethodCompareFieldEnum.INTERNAL_ASSEMBLY.getKey())) {
                        if(data.getScore().compareTo(new BigDecimal("100"))!=0){
                            ratio = ratio.multiply(data.getRatio());
                        }
                        if (!isIndex) {
                            this.jointContent(content, data);
                        }
                    }
                }
                if (isIndex) {
                    if(ratio.compareTo(new BigDecimal("1"))==0){
                        builder.write("100");
                    }else{
                        builder.write(df.format(new BigDecimal("100").divide(ratio, 2, BigDecimal.ROUND_HALF_UP)));
                    }
                } else {
                    builder.write(content.toString());
                }
            }
        }
        builder.endRow();
        //设备设施
        builder.insertCell();
        builder.write("设备设施");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            DecimalFormat df = new DecimalFormat("###.##");
            for (MdMarketCompareItem caseItem : caseItemList) {
                BigDecimal ratio = new BigDecimal("1");
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.INTELLIGENT_LEVEL.getKey()) || data.getName().equals(MethodCompareFieldEnum.WATER_SUPPLY_DRAINAGE_MODE.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.HEATING_MODE.getKey()) || data.getName().equals(MethodCompareFieldEnum.NETWORK.getKey())) {
                        if(data.getScore().compareTo(new BigDecimal("100"))!=0){
                            ratio = ratio.multiply(data.getRatio());
                        }
                        if (!isIndex) {
                            this.jointContent(content, data);
                        }
                    }
                }
                if (isIndex) {
                    if(ratio.compareTo(new BigDecimal("1"))==0){
                        builder.write("100");
                    }else{
                        builder.write(df.format(new BigDecimal("100").divide(ratio, 2, BigDecimal.ROUND_HALF_UP)));
                    }
                } else {
                    builder.write(content.toString());
                }
            }
        }
        builder.endRow();
        //建筑功能
        builder.insertCell();
        builder.write("建筑功能");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            DecimalFormat df = new DecimalFormat("###.##");
            for (MdMarketCompareItem caseItem : caseItemList) {
                BigDecimal ratio = new BigDecimal("1");
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.AERATION.getKey()) || data.getName().equals(MethodCompareFieldEnum.LIGHTING.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.SUNSHINE.getKey()) || data.getName().equals(MethodCompareFieldEnum.SOUND_INSULATION.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.HEAT_PRESERVATION.getKey()) || data.getName().equals(MethodCompareFieldEnum.HEAT_INSULATION.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.WATERPROOF.getKey())) {
                        if(data.getScore().compareTo(new BigDecimal("100"))!=0){
                            ratio = ratio.multiply(data.getRatio());
                        }
                        if (!isIndex) {
                            this.jointContent(content, data);
                        }
                    }
                }
                if (isIndex) {
                    if(ratio.compareTo(new BigDecimal("1"))==0){
                        builder.write("100");
                    }else{
                        builder.write(df.format(new BigDecimal("100").divide(ratio, 2, BigDecimal.ROUND_HALF_UP)));
                    }
                } else {
                    builder.write(content.toString());
                }
            }
        }
        builder.endRow();
        //成新率及其他
        for (MarketCompareItemDto item : list) {
            if (item.getName().equals(MethodCompareFieldEnum.NEW_DEGREE.getKey()) || item.getName().equals(MethodCompareFieldEnum.Other.getKey())) {
                builder.insertCell();
                builder.write(MethodCompareFieldEnum.getNameByKey(item.getName()));
                if (CollectionUtils.isNotEmpty(caseItemList)) {
                    for (MdMarketCompareItem caseItem : caseItemList) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto data : dtos) {
                            if (data.getName().equals(item.getName())) {
                                if (isIndex) {
                                    builder.insertCell();
                                    builder.write(String.valueOf(data.getScore()));
                                } else {
                                    builder.insertCell();
                                    builder.write(StringUtils.isNotBlank(data.getValue()) ? data.getValue() : "无");
                                }
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }


    }

    /**
     * 测算结果表格
     *
     * @param mdMarketCompareItem 估价对象
     * @param caseItemList        数据
     * @return
     */
    public String getCalculationTable(MdMarketCompareItem
                                              mdMarketCompareItem, List<MdMarketCompareItem> caseItemList) throws Exception {

        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //表格属性
        setTableProperty(builder);
        //表头
        builder.insertCell();
        builder.write("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.write(caseItem.getName());
        }
        builder.endRow();

        //是否显示权重
        boolean flag = false;
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            //交易价格
            DataSetUseField tradingPrice = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.TRADING_PRICE.getKey());
            builder.insertCell();
            builder.write("交易价格");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> data = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto item : data) {
                    if (tradingPrice.getFieldName().equals(item.getName())) {
                        builder.write(item.getValue());
                    }
                }

            }
            builder.endRow();

            //交易情况修正系数
            builder.insertCell();
            builder.write("交易情况修正系数");
            DataSetUseField tradingTransaction = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.TRADING_TRANSACTION_SITUATION.getKey());
            for (MdMarketCompareItem item : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (tradingTransaction.getFieldName().equals(caseItem.getName())) {
                        if (caseItem.getScore() != null) {
                            builder.write(String.format("%.4f", caseItem.getRatio()));
                        } else {
                            builder.write("");
                        }
                    }
                }
            }
            builder.endRow();

            //市场状况修正系数
            builder.insertCell();
            builder.write("市场状况修正系数");
            DataSetUseField tradingTime = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.TRADING_TIME.getKey());
            for (MdMarketCompareItem item : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (tradingTime.getFieldName().equals(caseItem.getName())) {
                        if (caseItem.getScore() != null) {
                            builder.write(String.format("%.4f", caseItem.getRatio()));
                        } else {
                            builder.write("");
                        }
                    }
                }
            }
            builder.endRow();

            //单价内涵修正系数
            builder.insertCell();
            builder.write("单价内涵修正系数");
            DataSetUseField priceConnotation = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.PRICE_CONNOTATION.getKey());
            for (MdMarketCompareItem item : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (priceConnotation.getFieldName().equals(caseItem.getName())) {
                        if (caseItem.getScore() != null) {
                            builder.write(String.format("%.4f", caseItem.getRatio()));
                        } else {
                            builder.write("");
                        }
                    }
                }
            }
            builder.endRow();

            //区位状况修正系数
            builder.insertCell();
            builder.write("区位状况修正系数");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (caseItem.getLocationFactorRatio() != null) {
                    builder.write(String.format("%.4f", caseItem.getLocationFactorRatio()));
                } else {
                    builder.write(String.format("%.4f", new BigDecimal("1")));
                }
            }
            builder.endRow();

            //实物状况修正系数
            builder.insertCell();
            builder.write("实物状况修正系数");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (caseItem.getEntityFactorRatio() != null) {
                    builder.write(String.format("%.4f", caseItem.getEntityFactorRatio()));
                } else {
                    builder.write(String.format("%.4f", new BigDecimal("1")));
                }
            }
            builder.endRow();

            //权益状况修正系数
            builder.insertCell();
            builder.write("权益状况修正系数");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (caseItem.getEquityFactorRatio() != null) {
                    builder.write(String.format("%.4f", caseItem.getEquityFactorRatio()));
                } else {
                    builder.write(String.format("%.4f", new BigDecimal("1")));
                }
            }
            builder.endRow();

            //比准价格
            builder.insertCell();
            builder.write("比准价格");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (StringUtils.isNotBlank(caseItem.getSpecificPrice())) {
                    builder.write(caseItem.getSpecificPrice());
                } else {
                    builder.write("");
                }
            }
            builder.endRow();

            //权重
            for (MdMarketCompareItem caseItem : caseItemList) {
                if (StringUtils.isNotBlank(caseItem.getWeight()) && !"无".equals(caseItem.getWeight())) {
                    flag = true;
                }
            }
            if (flag) {
                builder.insertCell();
                builder.write("权重");
                for (MdMarketCompareItem caseItem : caseItemList) {
                    builder.insertCell();
                    if (StringUtils.isNotBlank(caseItem.getWeight()) && !"无".equals(caseItem.getWeight())) {
                        BigDecimal weight = new BigDecimal(caseItem.getWeight());
                        weight = weight.multiply(new BigDecimal("100"));
                        weight.setScale(2, RoundingMode.HALF_UP);//保留两位小数
                        builder.write(String.format("%s%s", weight, "%"));
                    } else {
                        builder.write("");
                    }
                }
                builder.endRow();
            }

        }
        //加权平均价
        builder.insertCell();
        if (flag) {
            builder.write("加权平均价");
        } else {
            builder.write("算数平均价");
        }
        builder.insertCell();
        if (mdMarketCompareItem.getAveragePrice() != null) {
            builder.write(mdMarketCompareItem.getAveragePrice().toString());
        } else {
            builder.write("");
        }
        builder.endRow();
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    /**
     * 期日修正
     *
     * @param caseItemList 数据
     * @return
     */
    public String getDateRevision(List<MdMarketCompareItem> caseItemList, String fieldName) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        //map按key值排序
        Map<Integer, Integer> normal = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Map<Integer, Integer> all = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (cacheSetUseFieldList.getFieldName().equals(caseItem.getName())) {
                        Integer num = Integer.valueOf(item.getName().substring(item.getName().length() - 1, item.getName().length()));
                        if (100 == caseItem.getScore().intValue()) {
                            normal.put(num, caseItem.getScore().intValue());
                        }
                        all.put(num, caseItem.getScore().intValue());
                    }
                }
            }
        }
        StringBuilder normalContent = new StringBuilder();
        StringBuilder abnormalityContent = new StringBuilder();


        if (normal.size() == all.size()) {
            Set<Integer> strings = normal.keySet();
            for (Integer num : strings) {
                normalContent.append(String.format("%s%s", "案列对象", num)).append("、");
            }
            normalContent.deleteCharAt(normalContent.length() - 1);
        } else {
            Set<Map.Entry<Integer, Integer>> entries = all.entrySet();
            for (Map.Entry<Integer, Integer> item : entries) {
                abnormalityContent.append(String.format("%s%s", "案列对象", item.getKey())).append("市场状况指数").append(item.getValue()).append(",");
            }
            abnormalityContent.replace(abnormalityContent.length() - 1, abnormalityContent.length(), "。");
        }
        if (StringUtils.isNotBlank(normalContent)) {
            builder.insertHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.getWarpCssHtml(String.format("%s%s%s", "根据所掌握的资料，", normalContent, "均为近期交易，故不需进行修正。"))));
        }
        if (StringUtils.isNotBlank(abnormalityContent)) {
            builder.insertHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.getWarpCssHtml(String.format("%s%s", "根据所掌握的资料，", abnormalityContent))));
        }
        doc.save(localPath);
        return localPath;
    }


    /**
     * 交易情况修正
     *
     * @param caseItemList 案列等数据
     * @return
     */
    public String getTransaction(List<MdMarketCompareItem> caseItemList, String fieldName) throws
            Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        //map按key值排序
        Map<Integer, Integer> normal = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Map<Integer, Integer> all = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (cacheSetUseFieldList.getFieldName().equals(caseItem.getName())) {
                        Integer num = Integer.valueOf(item.getName().substring(item.getName().length() - 1, item.getName().length()));
                        if (100 == caseItem.getScore().intValue()) {
                            normal.put(num, caseItem.getScore().intValue());
                        }
                        all.put(num, caseItem.getScore().intValue());
                    }
                }
            }
        }
        StringBuilder normalContent = new StringBuilder();
        StringBuilder abnormalityContent = new StringBuilder();

        if (normal.size() == all.size()) {
            Set<Integer> strings = normal.keySet();
            for (Integer num : strings) {
                normalContent.append(String.format("%s%s", "案列对象", num)).append("、");
            }
            if (StringUtils.isNotBlank(normalContent))
                normalContent.deleteCharAt(normalContent.length() - 1);
        } else {
            Set<Map.Entry<Integer, Integer>> entries = all.entrySet();
            for (Map.Entry<Integer, Integer> item : entries) {
                abnormalityContent.append(String.format("%s%s", "案列对象", item.getKey())).append("交易修正指数为").append(item.getValue()).append(",");
            }
            abnormalityContent.replace(abnormalityContent.length() - 1, abnormalityContent.length(), "。");
        }
        if (StringUtils.isNotBlank(normalContent)) {
            builder.insertHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.getWarpCssHtml(String.format("%s%s%s", "根据所掌握的资料，", normalContent, "均为正常交易，无需进行交易情况修正。"))));
        }
        if (StringUtils.isNotBlank(abnormalityContent)) {
            builder.insertHtml(generateCommonMethod.getIndentHtml(generateCommonMethod.getWarpCssHtml(String.format("%s%s", "根据所掌握的资料，", abnormalityContent))));
        }

        doc.save(localPath);
        return localPath;
    }

    /**
     * 房价指数表格
     *
     * @param title        标题
     * @param caseItemList 案列对象
     * @return
     */
    public String getHousepriceIndex(String title, List<MdMarketCompareItem> caseItemList) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        //表格属性
        setTableProperty(builder);
        builder.write(title);
        //表头
        builder.insertCell();
        builder.write("日期");
        builder.insertCell();
        builder.write("指数");
        builder.endRow();
        String localPath = generateCommonMethod.getLocalPath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat(" yyyy年MM月");
        List<String> dateList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> marketCompareItemDtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto dto : marketCompareItemDtos) {
                    if (MethodCompareFieldEnum.TRADING_TIME.getKey().equals(dto.getName())) {
                        dateList.add(dto.getValue());
                    }
                }
            }
            //排序
            Collections.sort(dateList);
            //获取最小日期
            Date startDate = sdf.parse(dateList.get(0));

            SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(this.areaId);
            //获取时间区间与地区内的房价指数
            DataHousePriceIndex query = new DataHousePriceIndex();
            query.setEvaluationDate(this.valueTimePoint);
            query.setCity(schemeAreaGroup.getCity());
            query.setDistrict(schemeAreaGroup.getDistrict());
            query.setProvince(schemeAreaGroup.getProvince());
            //startDate, this.valueTimePoint
            List<DataHousePriceIndexVo> dataHousePriceIndexList = dataHousePriceIndexService.getDataHousePriceIndexList(query);
            List<DataHousePriceIndexDetail> dataHousePriceIndexDetails = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(dataHousePriceIndexList)) {
                for (DataHousePriceIndex index : dataHousePriceIndexList) {
                    List<DataHousePriceIndexDetail> details = dataHousePriceIndexService.getDataHousePriceIndexDetailList(index.getId());
                    if (CollectionUtils.isNotEmpty(details)) {
                        dataHousePriceIndexDetails.addAll(details);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(dataHousePriceIndexDetails)) {
                for (DataHousePriceIndexDetail dataHousePriceIndexDetail : dataHousePriceIndexDetails) {
                    builder.insertCell();
                    builder.write(sdf2.format(dataHousePriceIndexDetail.getStartDate()));
                    builder.insertCell();
                    builder.write(DateUtils.format(dataHousePriceIndexDetail.getEndDate()));
                    builder.endRow();
                }
            }
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 比较基础
     *
     * @param marketCompareItemDtos 估价对象
     * @return
     */
    public String getComparisonBasis(List<MarketCompareItemDto> marketCompareItemDtos, String fieldName) {
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        String propertyRange = new String();
        for (MarketCompareItemDto item : marketCompareItemDtos) {
            if (cacheSetUseFieldList.getFieldName().equals(item.getName())) {
                propertyRange = item.getValue();
            }
        }
        return propertyRange;
    }


    /**
     * 测算结果
     *
     * @param title        标题
     * @param caseItemList 案列对象
     * @param isCourse     是否过程
     * @return
     */
    public String getCountCourse(String title, List<MdMarketCompareItem> caseItemList, boolean isCourse) throws
            Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //案列个数
        Integer size = caseItemList.size();
        //计算结论
        StringBuilder front = new StringBuilder();
        //计算过程
        StringBuilder allContent = new StringBuilder();
        StringBuilder content = new StringBuilder();
        //权重描述
        StringBuilder weightRemark = new StringBuilder();
        front.append("经以上分析计算，");
        allContent.append("比较价格：");
        BigDecimal num = new BigDecimal("0");
        content.append("(");
        //报告使用的单位
        String unit = "元/㎡";
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                if (StringUtils.isEmpty(item.getSpecificPrice())) {
                    item.setSpecificPrice("0");
                }
                if (StringUtils.isNotBlank(item.getWeight()) && !"无".equals(item.getWeight())) {
                    try {
                        BigDecimal temp = new BigDecimal(item.getSpecificPrice()).multiply(new BigDecimal(item.getWeight()));
                        num = num.add(temp);
                        content.append(item.getSpecificPrice() + "×" + item.getWeight() + "+");
                    } catch (Exception e) {
                        String error = e.getMessage();
                        logger.error(error, e);
                    }
                    weightRemark.append(item.getName()).append("权重说明").append(item.getWeightDescription()).append(",");
                } else {
                    num = num.add(new BigDecimal(item.getSpecificPrice()));
                    content.append(item.getSpecificPrice() + "+");
                }
            }
            if (StringUtils.isNotBlank(weightRemark.toString())) {
                weightRemark.replace(weightRemark.length() - 1, weightRemark.length(), "。");
            }
            //使用什么单位
            List<MarketCompareItemDto> dtos = JSON.parseArray(caseItemList.get(0).getJsonContent(), MarketCompareItemDto.class);
            for (MarketCompareItemDto item2 : dtos) {
                if (item2.getName().equals(MethodCompareFieldEnum.PRICE_CONNOTATION.getKey())) {
                    //建筑面积单价及套内面积单价用元/㎡
                    BaseDataDic buildAreaUnitPrice = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_BUILD_AREA_UNIT_PRICE);
                    BaseDataDic buildInteriorUnitPrice = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INTERIOR_AREA_UNIT_PRICE);
                    if (!item2.getValue().equals(buildAreaUnitPrice.getName()) && !item2.getValue().equals(buildInteriorUnitPrice.getName())) {
                        //获取单位
                        BasicApply apply = basicApplyService.getByBasicApplyId(getEvaluationItemList().getBasicApplyId());
                        if (apply != null) {
                            BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(apply.getBasicHouseId());
                            unit = houseTrading.getPriceConnotationUnit();
                        }
                    }
                }
            }
        }
        if (StringUtils.isEmpty(weightRemark.toString())) {
            front.append(toChinese(String.valueOf(size)) + "个比较价格差异幅度较小，我们认为" + toChinese(String.valueOf(size)) + "个比较实例与估价对象在同一区域范围内，其价格具有一致性，综合考虑各种因素，并结合该区域同类房地产交易价格水平，确定以" + toChinese(String.valueOf(size)) + "个交易案例比较价格的算术平均值作为估价对象的比较价格，计算过程如下：");
            num = num.divide(new BigDecimal(String.valueOf(caseItemList.size())), 2, RoundingMode.CEILING);
            String result = String.format("%.2f", num);
            content.deleteCharAt(content.length() - 1);
            content.append(")").append("÷").append(caseItemList.size()).append("≈").append(result);
            content.append(unit).append("；").append("即,估价对象房地产的单价为").append(result).append(unit);
        } else {
            front.append(weightRemark).append("计算过程如下：");
            String result = String.format("%.2f", num);
            content.deleteCharAt(0);
            content.deleteCharAt(content.length() - 1);
            content.append("=").append(result);
            content.append(unit).append("；").append("即,估价对象房地产的单价为").append(result).append(unit);
        }
        allContent.append(content);
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(front.toString())));
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(allContent.toString())));
        doc.save(localPath);
        return localPath;
    }


    //设置表格属性
    public void setTableProperty(DocumentBuilder builder) throws Exception {
        generateCommonMethod.settingBuildingTable(builder);
    }


    /**
     * 比较法计算公式
     *
     * @return
     */
    public String getDesignFormulas(String title) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //获取比较法公式
        DataMethodFormula formula = dataMethodFormulaService.getMethodFormulaByMethodKey(AssessDataDicKeyConstant.COMPARE_FORMULA);
        if(formula!=null){
            builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(formula.getFormula())), true);
        }
        doc.save(localPath);
        return localPath;
    }


    /**
     * 生成表格
     *
     * @param fieldName 对应部分
     * @param data      案列等数据
     * @param isIndex   是否是指数表
     * @return
     */
    public void GenerateTable(String fieldName, DocumentBuilder builder, List<MdMarketCompareItem> data, Boolean isIndex) throws Exception {
        List<DataSetUseField> useFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);

        for (DataSetUseField useField : useFieldList) {
            if (!useField.getFieldName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                builder.insertCell();
                builder.write(MethodCompareFieldEnum.getNameByKey(useField.getFieldName()));
                if (CollectionUtils.isNotEmpty(data)) {
                    for (MdMarketCompareItem caseItem : data) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto item2 : dtos) {
                            //楼层跳过，单独处理
                            if (item2.getName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                                continue;
                            }
                            if (item2.getName().equals(useField.getFieldName())) {
                                builder.insertCell();
                                if (isIndex) {
                                    builder.write(item2.getScore() == null ? "" : item2.getScore().toString());
                                } else {
                                    builder.write(StringUtil.isEmpty(item2.getValue()) ? "无" : item2.getValue());
                                }
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }
        //楼层单独处理
        for (DataSetUseField useField : useFieldList) {
            if (useField.getFieldName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                builder.insertCell();
                builder.write(MethodCompareFieldEnum.getNameByKey(useField.getFieldName()));
                if (CollectionUtils.isNotEmpty(data)) {
                    for (MdMarketCompareItem caseItem : data) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto item2 : dtos) {
                            if (item2.getName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                                builder.insertCell();
                                if (isIndex) {
                                    builder.write(item2.getScore() == null ? "" : item2.getScore().toString());
                                } else {
                                    builder.write(StringUtil.isEmpty(item2.getValue()) ? "无" : item2.getValue());
                                }
                            }

                        }
                    }
                }
                builder.endRow();
            }
        }

    }


    /**
     * 生成对应修正系数表格行
     *
     * @param fieldName 某一部分内容
     * @param data      案列等数据
     */
    public void getPartCoefficient(String fieldName, List<MdMarketCompareItem> data, DocumentBuilder builder) throws Exception {
        List<DataSetUseField> cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);
        for (MdMarketCompareItem caseItem : data) {
            BigDecimal temp = new BigDecimal("1");
            List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
            builder.insertCell();
            for (MarketCompareItemDto item2 : dtos) {
                for (DataSetUseField useField : cacheSetUseFieldList) {
                    if (useField.getFieldName().equals(item2.getName())) {
                        if (item2.getRatio() != null) {
                            temp = temp.multiply(new BigDecimal(String.valueOf(item2.getRatio())));
                        } else {
                            temp = new BigDecimal("0");
                        }
                    }
                }
            }
            if (temp.compareTo(new BigDecimal("0")) != 0) {
                builder.write(String.format("%.4f", temp));
            } else {
                builder.write("系数异常");
            }
        }
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

    /**
     * 拼接内容
     *
     * @param data 数据
     */
    public void jointContent(StringBuilder content, MarketCompareItemDto data) {
        if (StringUtil.isNotEmpty(data.getValue())) {
            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
            content.append(data.getValue());
        }
    }

}

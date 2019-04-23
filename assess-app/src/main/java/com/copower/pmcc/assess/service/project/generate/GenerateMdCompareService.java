package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldCompareEnum;
import com.copower.pmcc.assess.common.enums.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by kings on 2019-1-31.
 */
public class GenerateMdCompareService {

    private Integer mcId;
    private Integer schemeJudgeObjectId;
    private Date valueTimePoint;
    private Integer areaId;
    private List<DataSetUseField> setUseFieldList;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GenerateCommonMethod generateCommonMethod;

    private GenerateMdCompareService() {
    }

    public GenerateMdCompareService(Integer schemeJudgeObjectId, Integer mcId, Date date, Integer areaId) throws Exception {
        this.schemeJudgeObjectId = schemeJudgeObjectId;
        this.mcId = mcId;
        this.valueTimePoint = date;
        this.areaId = areaId;
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
        getEvaluationItemList();
    }

    /**
     * 获取设定用途字段
     *
     * @return
     */
    public List<DataSetUseField> getSetUseFieldList() {
        if (CollectionUtils.isNotEmpty(setUseFieldList)) return setUseFieldList;
        List<DataSetUseField> fields = mdMarketCompareService.getSetUseFieldList();
        this.setUseFieldList = fields;
        return fields;
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
        MdMarketCompareItem marketCompareItem = mdMarketCompareService.getEvaluationListByMcId(getMarketCompare().getId());
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
        Map<String, String> textMap = AsposeUtils.getRegexExtendList(document);
        BookmarkCollection bookmarks = AsposeUtils.getBookmarks(document);

        try {
            //文本替换
            if (textMap != null && textMap.size() > 0) {
                for (Map.Entry<String, String> entry : textMap.entrySet()) {
                    textMap.put(entry.getKey(), this.getValueByKey(entry.getValue()));
                }
                AsposeUtils.replaceTextToFile(localPath, textMap);
            }
            //书签替换
            if (bookmarks != null) {
                Map<String, String> bookmarkMap = Maps.newHashMap();
                for (Bookmark bookmark : bookmarks) {
                    bookmarkMap.put(bookmark.getName(), this.getValueByKey(bookmark.getName()));
                }
                AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
            }
        } catch (Exception e) {
            String error = e.getMessage();
            logger.error(error, e);
        }
        return localPath;
    }

    /**
     * 根据字段key值获取被替换的值
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        BaseReportFieldCompareEnum fieldCompareEnum = BaseReportFieldCompareEnum.getEnumByName(key);
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
                case PRICE_CONNOTATION:
                    localPath = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.PRICE_CONNOTATION.getKey());
                    break;
                case DESIGN_FORMULAS:
                    localPath = getDesignFormulas(title);
                    break;
                case CASE_NUMBER:
                    localPath = getCaseNumber(title, caseItemList.size());
                    break;
                case COMPARABLE_BASIS:
                    localPath = getTable(caseItemList, title, "comparative.basis", "trading.status", false);
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
                case PROPERTY_RANGE:
                    localPath = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.SCOPE_PROPERTY.getKey());
                    break;
                case PAYMENT_METHOD:
                    localPath = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.PAYMENT_METHOD.getKey());
                    break;
                case FINANCING_CONDITION:
                    localPath = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.FINANCING_CONDITIONS.getKey());
                    break;
                case TAX_BURDEN:
                    localPath = getComparisonBasis(marketCompareItemDtos, MethodCompareFieldEnum.TAX_BURDEN.getKey());
                    break;
                case TRANSACTION_MODIFICATION:

                    localPath = getTransaction(data, MethodCompareFieldEnum.TRADING_TRANSACTION_SITUATION.getKey());
                    break;
                case DATE_REVISION:
                    localPath = getDateRevision(data, MethodCompareFieldEnum.TRADING_TIME.getKey());
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
        if (!isIndex) {
            builder.write(title);
        }
        //表格属性
        setTableProperty(builder);
        String localPath = generateCommonMethod.getLocalPath();
        //表头
        builder.insertCell();
        builder.writeln("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.writeln(caseItem.getName());
        }
        builder.endRow();
        //生成表格
        GenerateTable(fieldName, builder, caseItemList, isIndex);


        //交易情况
        if (StringUtils.isNotBlank(fieldMore)) {
            //生成表格
            GenerateTable(fieldMore, builder, caseItemList, isIndex);
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
        if (!isIndex) {
            generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
            builder.write(title);
        }
        //表格属性
        setTableProperty(builder);
        String localPath = generateCommonMethod.getLocalPath();
        //表头
        builder.insertCell();
        builder.writeln("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.writeln(caseItem.getName());
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
                    || item.getName().equals(MethodCompareFieldEnum.BUILDING_STRUCTURE.getKey()) || item.getName().equals(MethodCompareFieldEnum.APPEARANCE.getKey())) {
                builder.insertCell();
                builder.writeln(MethodCompareFieldEnum.getNameByKey(item.getName()));
                if (CollectionUtils.isNotEmpty(caseItemList)) {
                    for (MdMarketCompareItem caseItem : caseItemList) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto data : dtos) {
                            if (data.getName().equals(item.getName())) {
                                if (isIndex) {
                                    builder.insertCell();
                                    builder.writeln(String.valueOf(data.getScore()));
                                } else {
                                    builder.insertCell();
                                    builder.writeln(data.getValue());
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
        builder.writeln("建筑规模");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.BUILDING_AREA.getKey()) || data.getName().equals(MethodCompareFieldEnum.BUILDING_SCALE.getKey())) {
                        if (isIndex) {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getScore()).append(";");
                        } else {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getValue()).append(";");
                        }
                    }
                }
                builder.writeln(content.deleteCharAt(content.length() - 1).toString());
            }
        }
        builder.endRow();
        //层高
        builder.insertCell();
        builder.writeln("层高");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.FLOOR_HEIGHT.getKey()) || data.getName().equals(MethodCompareFieldEnum.NET_HEIGHT.getKey())) {
                        if (isIndex) {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getScore()).append(";");
                        } else {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getValue()).append(";");
                        }
                    }
                }
                builder.writeln(content.deleteCharAt(content.length() - 1).toString());
            }
        }
        builder.endRow();
        //空间布局
        builder.insertCell();
        builder.writeln("空间布局");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.ELEVATOR_HOUSEHOLD_RATIO.getKey()) || data.getName().equals(MethodCompareFieldEnum.PLANE_LAYOUT.getKey())) {
                        if (isIndex) {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getScore()).append(";");
                        } else {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getValue()).append(";");
                        }
                    }
                }
                builder.writeln(content.deleteCharAt(content.length() - 1).toString());
            }
        }
        builder.endRow();
        //装饰装修
        builder.insertCell();
        builder.writeln("装饰装修");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.ARCHITECTURAL_OUTFIT.getKey()) || data.getName().equals(MethodCompareFieldEnum.INTERNAL_ASSEMBLY.getKey())) {
                        if (isIndex) {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getScore()).append(";");
                        } else {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getValue()).append(";");
                        }
                    }
                }
                builder.writeln(content.deleteCharAt(content.length() - 1).toString());
            }
        }
        builder.endRow();
        //设备设施
        builder.insertCell();
        builder.writeln("设备设施");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.INTELLIGENT_LEVEL.getKey()) || data.getName().equals(MethodCompareFieldEnum.WATER_SUPPLY_DRAINAGE_MODE.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.HEATING_MODE.getKey()) || data.getName().equals(MethodCompareFieldEnum.NETWORK.getKey())) {
                        if (isIndex) {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getScore()).append(";");
                        } else {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getValue()).append(";");
                        }
                    }
                }
                builder.writeln(content.deleteCharAt(content.length() - 1).toString());
            }
        }
        builder.endRow();
        //建筑功能
        builder.insertCell();
        builder.writeln("建筑功能");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                StringBuilder content = new StringBuilder();
                List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto data : dtos) {
                    if (data.getName().equals(MethodCompareFieldEnum.AERATION.getKey()) || data.getName().equals(MethodCompareFieldEnum.LIGHTING.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.SUNSHINE.getKey()) || data.getName().equals(MethodCompareFieldEnum.SOUND_INSULATION.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.HEAT_PRESERVATION.getKey()) || data.getName().equals(MethodCompareFieldEnum.HEAT_INSULATION.getKey())
                            || data.getName().equals(MethodCompareFieldEnum.WATERPROOF.getKey())) {
                        if (isIndex) {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getScore()).append(";");
                        } else {
                            content.append(MethodCompareFieldEnum.getNameByKey(data.getName())).append(":");
                            content.append(data.getValue()).append(";");
                        }
                    }
                }
                builder.writeln(content.deleteCharAt(content.length() - 1).toString());
            }
        }
        builder.endRow();
        //成新率及其他
        for (MarketCompareItemDto item : list) {
            if (item.getName().equals(MethodCompareFieldEnum.NEW_DEGREE.getKey()) || item.getName().equals(MethodCompareFieldEnum.Other.getKey())) {
                builder.insertCell();
                builder.writeln(MethodCompareFieldEnum.getNameByKey(item.getName()));
                if (CollectionUtils.isNotEmpty(caseItemList)) {
                    for (MdMarketCompareItem caseItem : caseItemList) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto data : dtos) {
                            if (data.getName().equals(item.getName())) {
                                if (isIndex) {
                                    builder.insertCell();
                                    builder.writeln(String.valueOf(data.getScore()));
                                } else {
                                    builder.insertCell();
                                    builder.writeln(StringUtils.isNotBlank(data.getValue()) ? data.getValue() : "无");
                                }
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }
        //修正系数
        if (isIndex) {
            builder.insertCell();
            builder.writeln("修正系数");
            this.getPartCoefficient("entity.condition", caseItemList, builder);
            builder.endRow();
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
        builder.writeln("项目");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.writeln(caseItem.getName());
        }
        builder.endRow();

        if (CollectionUtils.isNotEmpty(caseItemList)) {
            //交易价格
            DataSetUseField tradingPrice = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.TRADING_PRICE.getKey());
            builder.insertCell();
            builder.writeln("交易价格");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> data = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto item : data) {
                    if (tradingPrice.getFieldName().equals(item.getName())) {
                        builder.writeln(item.getValue());
                    }
                }

            }
            builder.endRow();

            //交易情况修正系数
            builder.insertCell();
            builder.writeln("交易情况修正系数");
            DataSetUseField tradingTransaction = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.TRADING_TRANSACTION_SITUATION.getKey());
            for (MdMarketCompareItem item : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (tradingTransaction.getFieldName().equals(caseItem.getName())) {
                        if (caseItem.getScore() != null) {
                            builder.writeln(String.format("%.2f", caseItem.getRatio()));
                        } else {
                            builder.writeln("空");
                        }
                    }
                }
            }
            builder.endRow();

            //市场状况修正系数
            builder.insertCell();
            builder.writeln("市场状况修正系数");
            DataSetUseField tradingTime = dataSetUseFieldService.getCacheSetUseFieldByFieldName(MethodCompareFieldEnum.TRADING_TIME.getKey());
            for (MdMarketCompareItem item : caseItemList) {
                builder.insertCell();
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (tradingTime.getFieldName().equals(caseItem.getName())) {
                        if (caseItem.getScore() != null) {
                            builder.writeln(String.format("%.2f", caseItem.getRatio()));
                        } else {
                            builder.writeln("空");
                        }
                    }
                }
            }
            builder.endRow();

            //区位状况修正系数
            builder.insertCell();
            builder.writeln("区位状况修正系数");
            this.getPartCoefficient("location.condition", caseItemList, builder);
            builder.endRow();

            //实物状况修正系数
            builder.insertCell();
            builder.writeln("实物状况修正系数");
            this.getPartCoefficient("entity.condition", caseItemList, builder);
            builder.endRow();

            //权益状况修正系数
            builder.insertCell();
            builder.writeln("权益状况修正系数");
            this.getPartCoefficient("equity.condition", caseItemList, builder);
            builder.endRow();

            //比准价格
            builder.insertCell();
            builder.writeln("比准价格");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getSpecificPrice())) {
                    builder.writeln(caseItem.getSpecificPrice());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();

            //权重
            builder.insertCell();
            builder.writeln("权重");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getWeight())) {
                    builder.writeln(caseItem.getWeight());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();

        }
        //加权平均价
        builder.insertCell();
        builder.writeln("加权平均价");
        builder.insertCell();
        if (mdMarketCompareItem.getAveragePrice() != null) {
            builder.writeln(mdMarketCompareItem.getAveragePrice().toString());
        } else {
            builder.writeln("空");
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
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        String localPath = generateCommonMethod.getLocalPath();
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        HashMap<String, Integer> normal = Maps.newHashMap();
        HashMap<String, Integer> all = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (cacheSetUseFieldList.getFieldName().equals(caseItem.getName())) {
                        if (100 == caseItem.getScore()) {
                            normal.put(item.getName(), caseItem.getScore());
                        }
                        all.put(item.getName(), caseItem.getScore());
                    }
                }
            }
        }
        StringBuilder normalContent = new StringBuilder();
        StringBuilder abnormalityContent = new StringBuilder();


        if (normal.size() == all.size()) {
            Set<String> strings = normal.keySet();
            for (String itemName : strings) {
                normalContent.append(itemName).append("、");
            }
            normalContent.deleteCharAt(normalContent.length() - 1);
        } else {
            Set<Map.Entry<String, Integer>> entries = all.entrySet();
            for (Map.Entry<String, Integer> item : entries) {
                abnormalityContent.append(item.getKey()).append("市场状况指数").append(item.getValue()).append(",");
            }
            abnormalityContent.replace(abnormalityContent.length() - 1, abnormalityContent.length(), "。");
        }
        if (!StringUtils.isEmpty(normalContent)) {
            builder.write("根据所掌握的资料，" + normalContent + "均为近期交易，故不需进行修正。");
        }
        if (!StringUtils.isEmpty(abnormalityContent)) {
            builder.write("根据所掌握的资料，" + abnormalityContent);
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
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        String localPath = generateCommonMethod.getLocalPath();
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        HashMap<String, Integer> normal = Maps.newHashMap();
        HashMap<String, Integer> all = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (cacheSetUseFieldList.getFieldName().equals(caseItem.getName())) {
                        if (100 == caseItem.getScore()) {
                            normal.put(item.getName(), caseItem.getScore());
                        }
                        all.put(item.getName(), caseItem.getScore());
                    }
                }
            }
        }
        StringBuilder normalContent = new StringBuilder();
        StringBuilder abnormalityContent = new StringBuilder();


        if (normal.size() == all.size()) {
            Set<String> strings = normal.keySet();
            for (String itemName : strings) {
                normalContent.append(itemName).append("、");
            }
            normalContent.deleteCharAt(normalContent.length() - 1);
        } else {
            Set<Map.Entry<String, Integer>> entries = all.entrySet();
            for (Map.Entry<String, Integer> item : entries) {
                abnormalityContent.append(item.getKey()).append("交易修正指数为").append(item.getValue()).append(",");
            }
            abnormalityContent.replace(abnormalityContent.length() - 1, abnormalityContent.length(), "。");
        }
        if (!StringUtils.isEmpty(normalContent)) {
            builder.write("根据所掌握的资料，" + normalContent + "均为正常交易，无需进行交易情况修正。");
        }
        if (!StringUtils.isEmpty(abnormalityContent)) {
            builder.write("根据所掌握的资料，" + abnormalityContent);
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
        builder.writeln(title);
        //表头
        builder.insertCell();
        builder.writeln("日期");
        builder.insertCell();
        builder.writeln("指数");
        builder.endRow();
        String localPath = String.format("%s\\" + title + "%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
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

            SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(this.areaId);
            //获取时间区间与地区内的房价指数
            List<DataHousePriceIndex> dataHousePriceIndexList = dataHousePriceIndexDao.listEndStart(startDate, this.valueTimePoint, schemeAreaGroup.getProvince(), schemeAreaGroup.getCity(), schemeAreaGroup.getDistrict());
            if (CollectionUtils.isEmpty(dataHousePriceIndexList)) {
                dataHousePriceIndexList = dataHousePriceIndexDao.listEndStart(startDate, this.valueTimePoint, schemeAreaGroup.getProvince(), schemeAreaGroup.getCity());
            }
            if (CollectionUtils.isNotEmpty(dataHousePriceIndexList)) {
                for (DataHousePriceIndex index : dataHousePriceIndexList) {
                    builder.insertCell();
                    builder.writeln(sdf2.format(index.getYearMonthCalendar()));
                    builder.insertCell();
                    builder.writeln(index.getIndexCalendar());
                    builder.endRow();
                }
            }
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 案例个数
     *
     * @param title 标题
     * @param size  案列个数
     * @return
     */
    public String getCaseNumber(String title, Integer size) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        builder.write(size.toString());
        String localPath = generateCommonMethod.getLocalPath();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 比较基础
     *
     * @param marketCompareItemDtos 估价对象
     * @return
     */
    public String getComparisonBasis(List<MarketCompareItemDto> marketCompareItemDtos, String fieldName) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        String propertyRange = new String();
        for (MarketCompareItemDto item : marketCompareItemDtos) {
            if (cacheSetUseFieldList.getFieldName().equals(item.getName())) {
                propertyRange = item.getValue();
            }
        }
        builder.write(propertyRange);
        doc.save(localPath);
        return localPath;
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
        generateCommonMethod.setDefaultDocumentBuilderSetting(builder);
        String localPath = generateCommonMethod.getLocalPath();
        //案列个数
        Integer size = caseItemList.size();
        //计算结论
        StringBuilder front = new StringBuilder();
        //计算过程
        StringBuilder content = new StringBuilder();
        //权重描述
        StringBuilder weightRemark = new StringBuilder();
        front.append("经以上分析计算，");
        content.append("比较价格：");
        BigDecimal num = new BigDecimal("0");
        content.append("(");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                if (StringUtils.isEmpty(item.getSpecificPrice())) {
                    item.setSpecificPrice("0");
                }
                if (!StringUtils.isEmpty(item.getWeight())) {
                    BigDecimal temp = new BigDecimal(item.getSpecificPrice()).multiply(new BigDecimal(item.getWeight()));
                    num = num.add(temp);
                    content.append(item.getSpecificPrice() + "*" + item.getWeight() + "+");
                    weightRemark.append(item.getName()).append("权重说明").append(item.getWeightDescription()).append(",");
                } else {
                    num = num.add(new BigDecimal(item.getSpecificPrice()));
                    content.append(item.getSpecificPrice() + "+");
                }
            }
            if (StringUtils.isNotBlank(weightRemark.toString())) {
                weightRemark.replace(weightRemark.length() - 1, weightRemark.length(), "。");
            }
        }
        front.append(weightRemark).append(size + "比较价格差异幅度较小，我们认为" + size + "个比较实例与估价对象在同一区域范围内，其价格具有一致性，综合考虑各种因素，并结合该区域同类房地产交易价格水平，确定以" + size + "个交易案例比较价格的算术平均值作为估价对象的比较价格，计算过程如下：");
        num = num.divide(new BigDecimal(String.valueOf(caseItemList.size())), 2, RoundingMode.CEILING);
        String result = String.format("%.2f", num);
        content.deleteCharAt(content.length() - 1);
        content.append(")").append("÷").append(caseItemList.size()).append("=").append(result);
        content.append("元/㎡；").append("即,估价对象房地产的单价为").append(result).append("元/㎡。");

        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(front.toString())));
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(content.toString())));
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
        String localPath = String.format("%s\\" + title + "%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        String number = getSubstitutionPrincipleName(schemeJudgeObject.getNumber());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(number).append("市场比较法");
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(stringBuilder.toString()), true);
        //获取比较法公式
        DataMethodFormula formula = new DataMethodFormula();
        BaseDataDic compareType = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        List<DataMethodFormula> formulaList = dataMethodFormulaService.getDataMethodFormulaList(compareType.getId());
        if (CollectionUtils.isNotEmpty(formulaList)) {
            formula = formulaList.get(0);
        }
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(generateCommonMethod.getIndentHtml(formula.getFormula())), true);
        doc.save(localPath);
        return localPath;
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
                builder.writeln(MethodCompareFieldEnum.getNameByKey(useField.getFieldName()));
                if (CollectionUtils.isNotEmpty(data)) {
                    for (MdMarketCompareItem caseItem : data) {
                        MdMarketCompare mdMarketCompare = mdMarketCompareService.getMdMarketCompare(caseItem.getMcId());
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto item2 : dtos) {
                            //楼层跳过，最后处理
                            if (item2.getName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                                continue;
                            }

                            if (item2.getName().equals(useField.getFieldName())) {
                                //交易时间为评估基准日
                                if (item2.getName().equals(MethodCompareFieldEnum.TRADING_TIME.getKey())) {
                                    builder.insertCell();
                                    if (isIndex) {
                                        builder.writeln(item2.getScore().toString());
                                    } else {
                                        builder.writeln(DateUtils.format(mdMarketCompare.getValueTimePoint(), DateUtils.DATE_CHINESE_PATTERN));
                                    }
                                } else {
                                    builder.insertCell();
                                    if (isIndex) {
                                        builder.writeln(item2.getScore().toString());
                                    } else {
                                        builder.writeln(item2.getValue());
                                    }
                                }
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }
        //单独处理楼层，使楼层放在最后一行
        for (DataSetUseField useField : useFieldList) {
            if (useField.getFieldName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                builder.insertCell();
                builder.writeln(MethodCompareFieldEnum.getNameByKey(useField.getFieldName()));
                if (CollectionUtils.isNotEmpty(data)) {
                    for (MdMarketCompareItem caseItem : data) {
                        List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                        for (MarketCompareItemDto item2 : dtos) {
                            if (item2.getName().equals(MethodCompareFieldEnum.FLOOR.getKey())) {
                                builder.insertCell();
                                if (isIndex) {
                                    builder.writeln(item2.getScore().toString());
                                } else {
                                    builder.writeln(item2.getValue());
                                }
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }

        //计算修正系数
        if (isIndex) {
            builder.insertCell();
            builder.writeln("修正系数");
            if (CollectionUtils.isNotEmpty(data)) {
                this.getPartCoefficient(fieldName, data, builder);
            }
            builder.endRow();
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
                        temp = temp.multiply(new BigDecimal(String.valueOf(item2.getRatio())));
                    }
                }
            }
            builder.writeln(String.format("%.2f", temp));
        }
    }

}

package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldCompareEnum;
import com.copower.pmcc.assess.common.enums.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by kings on 2019-1-31.
 */
public class GenerateMdCompareService {

    private Integer mcId;
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
    private SchemeAreaGroupService schemeAreaGroupService;
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GenerateCommonMethod generateCommonMethod;

    private GenerateMdCompareService() {
    }

    public GenerateMdCompareService(Integer mcId, Date date, Integer areaId) throws Exception {
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
            logger.error(error,e);
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
        MdMarketCompareItem evaluationItemList = getEvaluationItemList();
        List<MarketCompareItemDto> marketCompareItemDtos = JSON.parseArray(evaluationItemList.getJsonContent(), MarketCompareItemDto.class);
        List<MdMarketCompareItem> caseItemList = getCaseItemList();
        String localPath = null;
        if (fieldCompareEnum != null) {
            String title = fieldCompareEnum.getName();
            switch (fieldCompareEnum) {
                case CASE_NUMBER:
                    localPath = getCaseNumber(title, caseItemList.size());
                    break;
                case COMPARABLE_BASIS:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "comparative.basis", "trading.status", false);
                    break;
                case LOCATION_CONDITION:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "location.condition", "", false);
                    break;
                case RIGHTS_INTERESTS:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "equity.condition", "", false);
                    break;
                case ENTITY_CONDITION:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "entity.condition", "", false);
                    break;
                case PROPERTY_RANGE:
                    localPath = getComparePropertyRange(title, marketCompareItemDtos, caseItemList, "scope.property");
                    break;
                case PAYMENT_METHOD:
                    localPath = getComparePropertyRange(title, marketCompareItemDtos, caseItemList, "payment.method");
                    break;
                case FINANCING_CONDITION:
                    localPath = getComparePropertyRange(title, marketCompareItemDtos, caseItemList, "financing.conditions");
                    break;
                case TAX_BURDEN:
                    localPath = getComparePropertyRange(title, marketCompareItemDtos, caseItemList, "tax.burden");
                    break;
                case TRANSACTION_MODIFICATION:
                    localPath = getTransaction(title, caseItemList, "trading.transaction.situation");
                    break;
                case DATE_REVISION:
                    localPath = getDateRevision(title, caseItemList);
                    break;
                case MARKET_ADJUSTMENT:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "comparative.basis", "", true);
                    break;
                case LOCATION_QUOTIENT:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "location.condition", "", true);
                    break;
                case EQUITY_INDEX:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "equity.condition", "", true);
                    break;
                case ENTITY_INDEX:
                    localPath = getTable(marketCompareItemDtos, caseItemList, title, "entity.condition", "", true);
                    break;
                case CALCULATION_RESULT:
                    localPath = getCalculationTable(title, evaluationItemList, caseItemList);
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
     * @param list         估价对象
     * @param caseItemList 案列
     * @param title        标题
     * @param isIndex      是否是对应的指数表
     * @return
     */
    public String getTable(List<MarketCompareItemDto> list, List<MdMarketCompareItem> caseItemList, String title, String fieldName, String fieldMore, Boolean isIndex) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        //表格属性
        setTableProperty(builder);
        String localPath = generateCommonMethod.getLocalPath();
        List<DataSetUseField> cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);
        //表头
        builder.insertCell();
        builder.writeln("项目");
        builder.insertCell();
        builder.writeln("估价对象");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.writeln(caseItem.getName());
        }
        builder.endRow();
        //生成表格
        GenerateTable(list, cacheSetUseFieldList, builder, caseItemList, isIndex);


        //交易情况
        if (StringUtils.isNotBlank(fieldMore)) {
            builder.insertCell();
            builder.writeln("项目");
            builder.insertCell();
            builder.writeln("估价对象");
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                builder.writeln(caseItem.getName());
            }
            builder.endRow();

            List<DataSetUseField> tradingFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldMore);
            //生成表格
            GenerateTable(list, tradingFieldList, builder, caseItemList, isIndex);
        }
        builder.endTable();
        doc.save(localPath);
        return localPath;
    }

    public void GenerateTable(List<MarketCompareItemDto> list, List<DataSetUseField> tradingFieldList, DocumentBuilder builder, List<MdMarketCompareItem> caseItemList, Boolean isIndex) throws Exception {
        for (MarketCompareItemDto item : list) {
            for (DataSetUseField useField : tradingFieldList) {
                if (useField.getFieldName().equals(item.getName())) {
                    builder.insertCell();
                    builder.writeln(MethodCompareFieldEnum.getNameByKey(item.getName()));
                    builder.insertCell();
                    if (isIndex) {
                        builder.writeln(item.getScore().toString());
                    } else {
                        builder.writeln(item.getValue());
                    }
                    if (CollectionUtils.isNotEmpty(caseItemList)) {
                        for (MdMarketCompareItem caseItem : caseItemList) {
                            List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                            for (MarketCompareItemDto item2 : dtos) {
                                if (item2.getName().equals(item.getName())) {
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
        }

        //计算指数表税率
        if (isIndex) {
            BigDecimal result = new BigDecimal("1");
            builder.insertCell();
            builder.writeln("比率");
            for (MarketCompareItemDto item : list) {
                for (DataSetUseField useField : tradingFieldList) {
                    if (useField.getFieldName().equals(item.getName())) {
                        result = result.multiply(new BigDecimal(String.valueOf(item.getRatio())));
                    }
                }
            }
            builder.insertCell();
            builder.writeln(String.format("%.2f", result));
            if (CollectionUtils.isNotEmpty(caseItemList)) {
                for (MdMarketCompareItem caseItem : caseItemList) {
                    BigDecimal temp = new BigDecimal("1");
                    List<MarketCompareItemDto> dtos = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                    builder.insertCell();
                    for (MarketCompareItemDto item2 : dtos) {
                        for (DataSetUseField useField : tradingFieldList) {
                            if (useField.getFieldName().equals(item2.getName())) {
                                temp = temp.multiply(new BigDecimal(String.valueOf(item2.getRatio())));
                            }
                        }
                    }
                    builder.writeln(String.format("%.2f", temp));
                }
            }
            builder.endRow();
        }
    }


    /**
     * 测算结果表格
     *
     * @param title               标题
     * @param mdMarketCompareItem 估价对象
     * @param caseItemList        案列对象
     * @return
     */
    public String getCalculationTable(String title, MdMarketCompareItem
            mdMarketCompareItem, List<MdMarketCompareItem> caseItemList) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //表格属性
        setTableProperty(builder);
        //表头
        builder.insertCell();
        builder.writeln("项目");
        builder.insertCell();
        builder.writeln("估价对象");
        for (MdMarketCompareItem caseItem : caseItemList) {
            builder.insertCell();
            builder.writeln(caseItem.getName());
        }
        builder.endRow();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            builder.insertCell();
            builder.writeln("比准价格");
            builder.insertCell();
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getSpecificPrice())) {
                    builder.writeln(caseItem.getSpecificPrice());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();
            builder.insertCell();
            builder.writeln("修正差额");
            builder.insertCell();
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getCorrectionDifference())) {
                    builder.writeln(caseItem.getCorrectionDifference());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();
            builder.insertCell();
            builder.writeln("案例差异");
            builder.insertCell();
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getCaseDifference())) {
                    builder.writeln(caseItem.getCaseDifference());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();
            builder.insertCell();
            builder.writeln("权重");
            builder.insertCell();
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getWeight())) {
                    builder.writeln(caseItem.getWeight());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();
            builder.insertCell();
            builder.writeln("权重描述");
            builder.insertCell();
            for (MdMarketCompareItem caseItem : caseItemList) {
                builder.insertCell();
                if (!StringUtils.isEmpty(caseItem.getWeightDescription())) {
                    builder.writeln(caseItem.getWeightDescription());
                } else {
                    builder.writeln("空");
                }
            }
            builder.endRow();
        }
        builder.insertCell();
        builder.writeln("加权平均价");
        builder.insertCell();
        if (!StringUtils.isEmpty(mdMarketCompareItem.getAveragePrice().toString())) {
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
     * @param title        标题
     * @param caseItemList 案列对象
     * @return
     */
    public String getDateRevision(String title, List<MdMarketCompareItem> caseItemList) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = generateCommonMethod.getLocalPath();
        StringBuilder normal = new StringBuilder();
        StringBuilder abnormality = new StringBuilder();
        for (MdMarketCompareItem item : caseItemList) {
            if (StringUtils.isEmpty(item.getTradingTimeExplain())) {
                normal.append(item.getName() + "、");
            } else {
                abnormality.append(item.getName() + "修正过，修正内容为" + item.getTradingTimeExplain() + ",");
            }
        }
        String normalContent = new String();
        String abnormalityContent = new String();
        if (!StringUtils.isEmpty(normal)) {
            normalContent = normal.deleteCharAt(normal.length() - 1).toString();
        }
        if (!StringUtils.isEmpty(abnormality)) {
            abnormalityContent = abnormality.deleteCharAt(abnormality.length() - 1).toString();
        }
        if (!StringUtils.isEmpty(abnormality) && !StringUtils.isEmpty(normal)) {
            builder.write("根据所掌握的资料，" + normalContent + "均为近期交易，故不需进行修正。" + abnormalityContent + "。");
        } else if (StringUtils.isEmpty(abnormality) && !StringUtils.isEmpty(normal)) {
            builder.write("根据所掌握的资料，" + normalContent + "均为近期交易，故不需进行修正。");
        } else {
            builder.write("根据所掌握的资料" + abnormalityContent + "。");
        }
        doc.save(localPath);
        return localPath;
    }


    /**
     * 交易情况修正
     *
     * @param title        标题
     * @param caseItemList 案列对象
     * @return
     */
    public String getTransaction(String title, List<MdMarketCompareItem> caseItemList, String fieldName) throws
            Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = generateCommonMethod.getLocalPath();
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        StringBuilder normal = new StringBuilder();
        StringBuilder abnormality = new StringBuilder();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (cacheSetUseFieldList.getFieldName().equals(caseItem.getName())) {
                        if ("正常".equals(caseItem.getValue())) {
                            normal.append(item.getName() + "、");
                        } else {
                            abnormality.append(item.getName() + "、");
                        }
                    }
                }
            }
        }
        String normalContent = new String();
        String abnormalityContent = new String();
        if (!StringUtils.isEmpty(normal)) {
            normalContent = normal.deleteCharAt(normal.length() - 1).toString();
        }
        if (!StringUtils.isEmpty(abnormality)) {
            abnormalityContent = abnormality.deleteCharAt(abnormality.length() - 1).toString();
        }
        if (!StringUtils.isEmpty(abnormality) && !StringUtils.isEmpty(normal)) {
            builder.write("根据所掌握的资料，" + normalContent + "为正常交易，无需进行交易情况修正。" + abnormalityContent + "为不正常交易，需进行交易情况修正。");
        } else if (StringUtils.isEmpty(abnormality) && !StringUtils.isEmpty(normal)) {
            builder.write("根据所掌握的资料，" + normalContent + "均为正常交易，无需进行交易情况修正。");
        } else {
            builder.write("根据所掌握的资料，" + normalContent + "均为不正常交易，需进行交易情况修正。");
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
        generateCommonMethod.settingBuildingTable(builder);
        builder.write("共" + size.toString() + "个，情况详见下表：");
        String localPath = generateCommonMethod.getLocalPath();
        doc.save(localPath);
        return localPath;
    }


    /**
     * 财产范围
     *
     * @param title                 标题
     * @param caseItemList          案列对象
     * @param marketCompareItemDtos 估价对象
     * @return
     */
    public String getComparePropertyRange(String
                                                  title, List<MarketCompareItemDto> marketCompareItemDtos, List<MdMarketCompareItem> caseItemList, String
                                                  fieldName) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        generateCommonMethod.settingBuildingTable(builder);
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        String propertyRange = new String();
        for (MarketCompareItemDto item : marketCompareItemDtos) {
            if (cacheSetUseFieldList.getFieldName().equals(item.getName())) {
                propertyRange = item.getValue();
            }
        }
        StringBuilder accordance = new StringBuilder();
        StringBuilder difference = new StringBuilder();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                List<MarketCompareItemDto> dtos = JSON.parseArray(item.getJsonContent(), MarketCompareItemDto.class);
                for (MarketCompareItemDto caseItem : dtos) {
                    if (cacheSetUseFieldList.getFieldName().equals(caseItem.getName())) {
                        if (propertyRange.equals(caseItem.getValue())) {
                            accordance.append(item.getName() + "、");
                        } else {
                            difference.append(item.getName() + "为" + caseItem.getValue() + ",");
                        }
                    }
                }
            }
        }
        String accordanceContent = new String();
        String differenceContent = new String();
        if (!StringUtils.isEmpty(accordance)) {
            accordanceContent = accordance.deleteCharAt(accordance.length() - 1).toString();
        }
        if (!StringUtils.isEmpty(difference)) {
            differenceContent = difference.deleteCharAt(difference.length() - 1).toString();
        }
        if (!StringUtils.isEmpty(difference) && !StringUtils.isEmpty(accordance)) {
            builder.write("统一" + cacheSetUseFieldList.getName() + "： 估价对象与" + accordanceContent + "为" + propertyRange + "，" + differenceContent + "。");
        } else if (StringUtils.isEmpty(difference) && !StringUtils.isEmpty(accordance)) {
            builder.write("统一" + cacheSetUseFieldList.getName() + "： 估价对象与" + accordanceContent + "为" + propertyRange + "，已统一。");
        } else {
            builder.write("统一" + cacheSetUseFieldList.getName() + "： 估价对象为" + propertyRange + "，" + differenceContent + "。");
        }

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
        generateCommonMethod.settingBuildingTable(builder);
        String localPath = generateCommonMethod.getLocalPath();
        StringBuilder content = new StringBuilder();
        BigDecimal num = new BigDecimal("0");
        content.append("(");
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem item : caseItemList) {
                if (!StringUtils.isEmpty(item.getWeight())) {
                    BigDecimal temp = new BigDecimal(item.getSpecificPrice()).multiply(new BigDecimal(item.getWeight()));
                    num = num.add(temp);
                    content.append(item.getSpecificPrice() + "*" + item.getWeight() + "+");
                } else {
                    num = num.add(new BigDecimal(item.getSpecificPrice()));
                    content.append(item.getSpecificPrice() + "+");
                }
            }
        }
        num = num.divide(new BigDecimal(String.valueOf(caseItemList.size())));
        String result = String.format("%.2f", num);
        content.deleteCharAt(content.length() - 1);
        content.append(")").append("÷").append(caseItemList.size()).append("=").append(result);
        if (isCourse) {
            builder.write("比较价格:" + content.toString() + "元/㎡;");
        } else {
            builder.write("即,估价对象房地产的单价为" + result + "元/㎡。");
        }

        doc.save(localPath);
        return localPath;
    }


    //设置表格属性
    public void setTableProperty(DocumentBuilder builder) throws Exception {
        generateCommonMethod.settingBuildingTable(builder);
    }

}

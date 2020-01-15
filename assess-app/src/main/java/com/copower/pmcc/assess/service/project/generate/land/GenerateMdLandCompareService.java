package com.copower.pmcc.assess.service.project.generate.land;

import com.aspose.words.Document;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.BaseReportFieldLandCompareEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
import com.copower.pmcc.assess.service.data.DataHousePriceIndexService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2019-5-22.
 */
public class GenerateMdLandCompareService {
    private Integer mcId;
    private Integer schemeJudgeObjectId;
    private Date valueTimePoint;
    private Integer areaId;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;
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

    public GenerateMdLandCompareService(Integer schemeJudgeObjectId, Integer mcId, Integer areaId) {
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
        schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
        this.valueTimePoint = schemeAreaGroup.getValueTimePoint();
        this.schemeJudgeObjectId = schemeJudgeObjectId;
        this.mcId = mcId;
        this.areaId = areaId;
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
     * 获取比较法主表
     *
     * @return
     */
    public SchemeJudgeObject getSchemeJudgeObject() {
        if (schemeJudgeObject != null) return schemeJudgeObject;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(this.schemeJudgeObjectId);
        this.schemeJudgeObject = schemeJudgeObject;
        return schemeJudgeObject;
    }

    /**
     * 获取估价对象方法明细信息
     *
     * @return
     */
    public MdMarketCompareItem getEvaluationItem() {
        if (evaluationItem != null)
            return this.evaluationItem;
        MdMarketCompareItem marketCompareItem = mdMarketCompareService.getEvaluationByMcId(getMarketCompare().getId());
        this.evaluationItem = marketCompareItem;
        return marketCompareItem;
    }

    /**
     * 获取案例方法明细信息
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

    public String generateCompareFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.LAND_COMPARE_TEMPLATE);
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
        BaseReportFieldLandCompareEnum fieldCompareEnum = BaseReportFieldLandCompareEnum.getEnumByName(key);
        StringBuilder stringBuilder = new StringBuilder();
        switch (fieldCompareEnum) {
            case REGIONAL_FACTORS://土地比较法相关区域因素
                return getRegionalIndividualFactors(BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND_AREA_FACTOR);
            case INDIVIDUAL_FACTORS://土地比较法相关个别因素
                return getRegionalIndividualFactors(BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND_INDIVIDUAL_FACTOR);
            case TRANSACTION_SITUATION://土地比较法交易情况修正说明

                break;
            case DATE_AMENDMENT://土地比较法期日修正说明
                return getDateAmendment();
            case VOLUME_RATE_CORRECTION://土地比较法容积率修正说明

                break;
            case SET_USE://土地比较法设定用途
                return getSetUse();
            case REMAINING_YEARS://土地比较法剩余年限
                return getSchemeJudgeObject().getLandRemainingYear().toString();
            case USE_YEARS://土地比较法使用年限

                break;
            case OPPORTUNITY_COST_STATEMENT://土地比较法机会成本说明

                break;
            case OPPORTUNITY_COST_RATE://土地比较法机会成本率

                break;
            case REMUNERATION_RATE://土地比较法报酬率
                return String.format("%s%", getMarketCompare().getRewardRate().multiply(new BigDecimal("100")));
            case ANNUAL_CORRECTION_COEFFICIENT://土地比较法年期修正系数

                break;
            case PARCEL_OUT_SET_USE://土地比较法宗地外设定用途

                break;
            case PARCEL_IN_SET_USE://土地比较法宗地内设定用途

                break;
            case PARCEL_OUT_DEVELOPMENT_LEVEL://土地比较法宗地外开发程度

                break;
            case PARCEL_IN_DEVELOPMENT_LEVEL://土地比较法宗地内开发程度

                break;
            case CASE_COUNT://土地比较法案例个数
                return String.valueOf(getCaseItemList().size());
            case CASE_PRICE://土地比较法案例价格
                stringBuilder = new StringBuilder();
                for (MdMarketCompareItem mdMarketCompareItem : getCaseItemList()) {
                    stringBuilder.append(String.format("%s%s、", mdMarketCompareItem.getName(), mdMarketCompareItem.getSpecificPrice()));
                }
                return stringBuilder.toString();
            case PRICE_CALCULATION_METHOD://土地比较法价格计算方式

                break;
            case COMPUTATION_PROCESS://土地比较法计算过程

                break;
            case COMPUTATION_RESULT://土地比较法计算结果

                break;
            case VALUATION_PRICE://土地比较法评估单价

                break;
        }
        return "";
    }

    /**
     * 根据字段key值获取被替换的文件路径
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        BaseReportFieldLandCompareEnum fieldCompareEnum = BaseReportFieldLandCompareEnum.getEnumByName(key);
        switch (fieldCompareEnum) {
            case BIDDING_HANGING_CASE://土地比较法招拍挂案例表

                break;
            case AMENDMENT_FACTOR_TABLE://土地比较法修正因素表

                break;
            case REVISED_INDEX_TABLE://土地比较法修正指数表

                break;
            case REMUNERATION_RATE_TABLE://土地比较法报酬率测算表

                break;
            case CORRECTION_COEFFICIENT_TABLE://土地比较法修正系数表

                break;
        }
        return "";
    }

    /**
     * 获取区域或个别因素
     *
     * @param fieldName
     * @return
     */
    public String getRegionalIndividualFactors(String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        List<DataSetUseField> useFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);
        if (CollectionUtils.isEmpty(useFieldList)) return null;
        useFieldList.forEach(o -> stringBuilder.append(o.getName()).append("、"));
        return stringBuilder.toString();
    }

    public MarketCompareItemDto getCompareItemByName(List<MarketCompareItemDto> itemDtos, String name) {
        if (CollectionUtils.isEmpty(itemDtos)) return null;
        if (StringUtils.isBlank(name)) return null;
        for (MarketCompareItemDto itemDto : itemDtos) {
            if (StringUtils.equals(name, itemDto.getName()))
                return itemDto;
        }
        return null;
    }

    /**
     * 获取期日修正说明
     *
     * @return
     */
    public String getDateAmendment() {
        BigDecimal annualCoefficient = getEvaluationItem().getAnnualCoefficient();
        Boolean isCoefficient = false;
        for (MdMarketCompareItem mdMarketCompareItem : getCaseItemList()) {
            if (!annualCoefficient.equals(mdMarketCompareItem.getAnnualCoefficient()))
                isCoefficient = true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (isCoefficient) {
            stringBuilder.append(String.format("因受到国家宏观调控以及%s的%s土地市场政策变化的影响，", this.getAreaName(), this.getSetUse()));
            stringBuilder.append("交易案例的交易时间与估价基准日基本一致,故不修正。");
        } else {
            stringBuilder.append(String.format("因受到国家宏观调控以及%s的%s土地市场政策变化的影响，", this.getAreaName(), this.getSetUse()));
            stringBuilder.append("交易案例的交易时间与估价基准日基本不一致,故应修正其用地地价指数表详见下表：");
        }
        return stringBuilder.toString();
    }

    /**
     * 获取容积率修正说明
     *
     * @return
     */
    public String getVolumeRateCorrection() {
        //判断是工业还是非工业

        return null;
    }

    /**
     * 获取区域名称
     *
     * @return
     */
    public String getAreaName() {
        return schemeAreaGroup.getAreaName();
    }

    /**
     * 获取设定用途
     *
     * @return
     */
    public String getSetUse() {
        DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(getSchemeJudgeObject().getSetUse());
        if (setUseField == null) return null;
        return setUseField.getName();
    }
}

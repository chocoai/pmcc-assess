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
import com.copower.pmcc.assess.common.enums.report.ReportFieldMdCostApproachEnum;
import com.copower.pmcc.assess.common.enums.tool.ToolRewardRateEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailAchievementVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailAchievementService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.assess.service.project.ProjectLandAchievementGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.tool.ToolRewardRateService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author: huhao
 * @date: 2019/2/12 16:43
 * @description:成本逼近法报告字段处理
 */
public class GenerateMdCostApproachService implements Serializable {
    private Integer miId;
    private Integer areaId;
    private Integer judgeObjectId;
    private Integer projectId;
    private MdCostApproach mdCostApproach;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;

    private BaseReportFieldService baseReportFieldService;
    private MdCostApproachService mdCostApproachService;
    private BaseAttachmentService baseAttachmentService;
    private DataSetUseFieldService dataSetUseFieldService;
    private ToolRewardRateService toolRewardRateService;
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private GenerateCommonMethod generateCommonMethod;
    private BaseService baseService;
    private DataLandLevelDetailAchievementService dataLandLevelDetailAchievementService;
    private BasicApplyService basicApplyService;
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    private DataLandLevelDetailService dataLandLevelDetailService;
    private ProjectLandAchievementGroupService projectLandAchievementGroupService;

    public final String errorStr = "无";


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取替换后的报告模板
     *
     * @return
     * @throws Exception
     */
    public String generateCostApproachFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.COST_APPROACH_TEMPLATE);
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
                //设定用途
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.approachSetUse.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getApproachSetUse());
                }
                //区域
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.approachArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getApproachArea());
                }
                //计息周期
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelMachineCycle.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelMachineCycle());
                }
                //逼近法计息利率
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelCalculatedInterest.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelCalculatedInterest());
                }
                //逼近法代征地比例
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandAcquisitionRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelLandAcquisitionRatio());
                }
                //逼近法土地增值收益额
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelIncrementalBenefitPrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelIncrementalBenefitPrice());
                }
                //逼近法宗地外开发费用
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelCirculationExpense.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelCirculationExpense());
                }
                //逼近法宗地内开发费用
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelFlatExpense.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelFlatExpense());
                }
                //逼近法土地增值收益率
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelIncrementalBenefit.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelIncrementalBenefit());
                }
                //逼近法土地使用权价格
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandUsePrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelLandUsePrice());
                }
                //逼近法还原率
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelRewardRate.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelRewardRate());
                }
                //土地剩余年限
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandRemainingYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelLandRemainingYear());
                }
                //年期修正系数
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelYearFixed.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelYearFixed());
                }
                //逼近法个别因素修正系数
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelPlotRatioElementAmend.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelPlotRatioElementAmend());
                }
                //逼近法委估宗地单价
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelParcelUnit.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelParcelUnit());
                }
                //逼近法无限年期土地成本价格
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelInfiniteYearLandCostPrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelInfiniteYearLandCostPrice());
                }
                //逼近法容积率修正系数
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelPlotRatioAmendCoefficient.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelPlotRatioAmendCoefficient());
                }
                //逼近法投资利润率
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelInvestProfitMargin.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelInvestProfitMargin());
                }
                //逼近法含代征地每平税费
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelHaveAcquisitionPerTaxes.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelHaveAcquisitionPerTaxes());
                }
                //逼近法土地开发费用
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandDevelopmentExpenses.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelLandDevelopmentExpenses());
                }
                //逼近法投资利润
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelInvestProfit.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelInvestProfit());
                }
                //逼近法投资利息
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelInvestInterest.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelInvestInterest());
                }
                //逼近法容积率修正说明
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelPlotRatioAdjustRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelPlotRatioAdjustRemark());
                }
                //逼近法实际容积率
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelPlotRatioAdjust.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelPlotRatioAdjust());
                }
                //逼近法不含代征地每平税费
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelHaveNotAcquisitionPerTaxes.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelHaveNotAcquisitionPerTaxes());
                }
                //逼近法宗地外开发程度
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelDevelopmentLevelOuter.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelDevelopmentLevelOuter());
                }
                //逼近法宗地内开发程度
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelDevelopmentLevelInner.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelDevelopmentLevelInner());
                }
                //逼近法报酬率测算表
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelRewardRatioCalculate.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getParcelRewardRatioCalculate());
                }
                //逼近法无风险报酬率取值说明
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelNoRiskRewardRatioRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, StringUtils.isNotBlank(getMdCostApproach().getRewardRateRemark()) ?getMdCostApproach().getRewardRateRemark():errorStr);
                }
                //逼近法地价因素说明表
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandPriceElementExplain.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getParcelLandPriceElementExplainOrCoefficient(false));
                }
                //逼近法地价因素系数表
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandPriceElementCoefficient.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getParcelLandPriceElementExplainOrCoefficient(true));
                }
                //逼近法地价因素修正表
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelLandPriceElementAmend.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getParcelLandPriceElementAmend());
                }
                if (Objects.equal(name, ReportFieldMdCostApproachEnum.parcelCalculateSheet.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getParcelCalculateSheet());
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
     * 功能描述: 设定用途
     *
     * @auther: huhao
     * @date: 2019/2/27 17:35
     */
    private synchronized String getApproachSetUse() {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (schemeJudgeObject.getSetUse() != null) {
            DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
            if (dataSetUseField != null && StringUtils.isNotBlank(dataSetUseField.getName())) {
                return dataSetUseField.getName();
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 区域
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getApproachArea() throws Exception {
        String s = getSchemeAreaGroup().getAreaName();
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 功能描述: 计算周期
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelMachineCycle() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal machineCycle = mdCostApproach.getMachineCycle();
        if (machineCycle != null) {
            return machineCycle.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法计息利率
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelCalculatedInterest() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String calculatedInterest = mdCostApproach.getCalculatedInterest();
        if (StringUtils.isNotBlank(calculatedInterest)) {
            return calculatedInterest;
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法代征地比例
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelLandAcquisitionRatio() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        MdCostApproachTaxes mdCostApproachTaxes = mdCostApproachService.getMdCostApproachTaxesListByMasterId(mdCostApproach.getId(), AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_ACQUISITION);
        if (mdCostApproachTaxes != null) {
            if (mdCostApproachTaxes.getStandardFirst() != null) {
                return ArithmeticUtils.getPercentileSystem(mdCostApproachTaxes.getStandardFirst(), 4, BigDecimal.ROUND_HALF_UP);
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法宗地外开发费用
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelCirculationExpense() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal circulationExpense = mdCostApproach.getCirculationExpense();
        if (circulationExpense != null) {
            return circulationExpense.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法宗地内开发费用
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelFlatExpense() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal flatExpense = mdCostApproach.getFlatExpense();
        if (flatExpense != null) {
            return flatExpense.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法土地增值收益率
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelIncrementalBenefit() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String incrementalBenefit = mdCostApproach.getIncrementalBenefit();
        if (StringUtils.isNotEmpty(incrementalBenefit)) {
            return incrementalBenefit;
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法土地使用权价格
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelLandUsePrice() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landUsePrice = mdCostApproach.getLandUsePrice();
        if (landUsePrice != null) {
            return landUsePrice.toString();
        }
        return errorStr;
    }

    /**
     * 逼近法土地增值收益额
     * @return
     * @throws Exception
     */
    private synchronized String getParcelIncrementalBenefitPrice() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landAcquisitionUnit = mdCostApproach.getLandAcquisitionUnit();
        if (landAcquisitionUnit != null && StringUtils.isNotBlank(mdCostApproach.getIncrementalBenefit())) {
            if (NumberUtils.isNumber(mdCostApproach.getIncrementalBenefit())) {
                return ArithmeticUtils.mul(landAcquisitionUnit.toString(), mdCostApproach.getIncrementalBenefit(), 2);
            }else {
              return   ArithmeticUtils.mul(ArithmeticUtils.parseFormatString(mdCostApproach.getIncrementalBenefit()) ,landAcquisitionUnit.toString() ,2) ;
            }
        }
        return errorStr;
    }


    /**
     * 功能描述: 逼近法还原率
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelRewardRate() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String rewardRate = mdCostApproach.getRewardRate();
        if (StringUtils.isNotEmpty(rewardRate)) {
            return rewardRate;
        }
        return errorStr;
    }

    /**
     * 功能描述: 土地剩余年限
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelLandRemainingYear() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landRemainingYear = mdCostApproach.getLandRemainingYear();
        if (landRemainingYear != null) {
            return landRemainingYear.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 年期修正系数
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelYearFixed() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal yearFixed = mdCostApproach.getYearFixed();
        if (yearFixed != null) {
            return yearFixed.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法个别因素修正系数
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelPlotRatioElementAmend() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal plotRatioElementAmend = mdCostApproach.getPlotRatioElementAmend();
        if (plotRatioElementAmend != null) {
            return ArithmeticUtils.getPercentileSystem(plotRatioElementAmend, 4, BigDecimal.ROUND_HALF_UP);
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法委估宗地单价
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelParcelUnit() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal parcelUnit = mdCostApproach.getParcelUnit();
        if (parcelUnit != null) {
            return parcelUnit.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法无限年期土地成本价格
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelInfiniteYearLandCostPrice() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landCostPriceUnit = mdCostApproach.getLandCostPriceUnit();
        if (landCostPriceUnit != null) {
            return landCostPriceUnit.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法容积率修正系数
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelPlotRatioAmendCoefficient() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String plotRatioAdjust = mdCostApproach.getPlotRatioAdjust();
        if (StringUtils.isNotEmpty(plotRatioAdjust)) {
            return String.format("%s%s%s", "（1+", plotRatioAdjust, "）");
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法投资利润率
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelInvestProfitMargin() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String profitMargin = mdCostApproach.getProfitMargin();
        if (StringUtils.isNotEmpty(profitMargin)) {
            return profitMargin;
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法含代征地每平税费
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelHaveAcquisitionPerTaxes() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landAcquisitionUnit = mdCostApproach.getLandAcquisitionUnit();
        if (landAcquisitionUnit != null) {
            return landAcquisitionUnit.toString();
        }
        return errorStr;
    }


    /**
     * 功能描述: 逼近法土地开发费用
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelLandDevelopmentExpenses() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        //宗地外+宗地内
        BigDecimal circulationExpense = mdCostApproach.getCirculationExpense();
        BigDecimal flatExpense = mdCostApproach.getFlatExpense();
        if (circulationExpense != null && flatExpense != null) {
            return circulationExpense.add(flatExpense).toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法投资利润
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelInvestProfit() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landProductionProfitUnit = mdCostApproach.getLandProductionProfitUnit();
        if (landProductionProfitUnit != null) {
            return landProductionProfitUnit.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法投资利息
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelInvestInterest() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal landProductionInterestUnit = mdCostApproach.getLandProductionInterestUnit();
        if (landProductionInterestUnit != null) {
            return landProductionInterestUnit.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法实际容积率
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelPlotRatioAdjust() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String plotRatioAdjust = mdCostApproach.getPlotRatioAdjust();
        if (StringUtils.isNotEmpty(plotRatioAdjust)) {
            return plotRatioAdjust;
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法不含代征地每平税费
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelHaveNotAcquisitionPerTaxes() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        BigDecimal haveNotLandAcquisition = mdCostApproach.getHaveNotLandAcquisition();
        if (haveNotLandAcquisition != null) {
            return haveNotLandAcquisition.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 逼近法宗地内开发程度
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelDevelopmentLevelInner() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(mdCostApproach.getParcelSettingInner())) {
            List<Integer> ids = FormatUtils.transformString2Integer(mdCostApproach.getParcelSettingInner());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                for (Integer integer : ids) {
                    stringList.add(baseDataDicService.getNameById(integer));
                }
                return org.apache.commons.lang3.StringUtils.join(stringList, "，");
            }
        }

        return errorStr;
    }

    /**
     * 功能描述: 逼近法宗地外开发程度
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelDevelopmentLevelOuter() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(mdCostApproach.getParcelSettingOuter())) {
            List<Integer> ids = FormatUtils.transformString2Integer(mdCostApproach.getParcelSettingOuter());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                for (Integer integer : ids) {
                    stringList.add(baseDataDicService.getNameById(integer));
                }
                return org.apache.commons.lang3.StringUtils.join(stringList, "，");
            }
        }

        return errorStr;
    }

    /**
     * 功能描述: 逼近法报酬率测算表
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelRewardRatioCalculate() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(mdCostApproach.getRewardRateId());
        if (toolRewardRate == null) {
            return errorStr;
        }
        StringBuilder s = new StringBuilder();
        List<JSONObject> jsonObjects = JSONObject.parseArray(toolRewardRate.getParameterValue(), JSONObject.class);
        for (JSONObject item : jsonObjects) {
            String ratioText = item.getString("ratio");
            if (StringUtils.isNotEmpty(ratioText)) {
                String name = ToolRewardRateEnum.getName(item.getString("name"));
                BigDecimal ratio = new BigDecimal(ratioText);
                s.append(name).append("为").append(ArithmeticUtils.getPercentileSystem(ratio, 4, BigDecimal.ROUND_HALF_UP)).append(",");
            }
        }
        if (StringUtils.isNotEmpty(s.toString())) {
            return s.deleteCharAt(s.length() - 1).append("。").toString();
        }
        return errorStr;
    }


    /**
     * 逼近法地价因素说明表
     *
     * @return
     * @throws Exception
     */
    public String getParcelLandPriceElementExplainOrCoefficient(Boolean index) throws Exception {
        //地价因素修正数据
        List<List<ProjectLandAchievementGroupWithBLOBs>> achievementGroupData = projectLandAchievementGroupService.getMethodReportFilterProjectLandAchievementGroup(getSchemeJudgeObject()) ;
        if (CollectionUtils.isEmpty(achievementGroupData)) {
            return null;
        }
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        Table table = builder.startTable();
        //表头
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("土地级别类别", "土地级别类型", "优", "较优", "一般", "较劣", "劣")));
        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        Integer endRowIndex = 0;
        for (List<ProjectLandAchievementGroupWithBLOBs> types : achievementGroupData) {
            for (int i = 0; i < types.size(); i++) {
                ProjectLandAchievementGroupWithBLOBs group = types.get(i);
                List<DataLandLevelDetailAchievementVo> landLevelDetailAchievementList = new ArrayList<>();
                if (StringUtils.isNotBlank(group.getAchievementIds())) {
                    List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementList = dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementListByIds(FormatUtils.transformString2Integer(group.getAchievementIds()));
                    if (CollectionUtils.isNotEmpty(dataLandLevelDetailAchievementList)) {
                        landLevelDetailAchievementList = LangUtils.transform(dataLandLevelDetailAchievementList , obj -> dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementVo(obj)) ;
                    }
                }
                if (CollectionUtils.isEmpty(landLevelDetailAchievementList)) {
                    continue;
                }
                if (i == 0) {
                    mergeCellModelList.add(new MergeCellModel(endRowIndex + 1, 0, endRowIndex + types.size(), 0));
                    endRowIndex += types.size();
                }
                if (StringUtils.isNotEmpty(group.getType())) {
                    linkedList.add(group.getType());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(group.getClassification()) || StringUtils.isNotEmpty(group.getCategory())) {
                    StringBuilder s = new StringBuilder();
                    if (StringUtils.isNotEmpty(group.getClassification()) && StringUtils.isNotEmpty(group.getCategory())) {
                        s.append(group.getClassification()).append("/").append(group.getCategory());
                    } else if (StringUtils.isNotEmpty(group.getClassification())) {
                        s.append(group.getClassification());
                    } else {
                        s.append(group.getCategory());
                    }
                    linkedList.add(s.toString());
                } else {
                    linkedList.add(nullValue);
                }
                for (DataLandLevelDetailAchievementVo item : landLevelDetailAchievementList) {
                    if (index) {
                        if (item.getAchievement() != null) {
                            linkedList.add(ArithmeticUtils.getPercentileSystem(item.getAchievement(), 4));
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
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        document.save(localPath);
        return localPath;
    }


    /**
     * 逼近法地价因素修正表
     *
     * @return
     * @throws Exception
     */
    public String getParcelLandPriceElementAmend() throws Exception {
        //地价因素修正数据
        List<List<ProjectLandAchievementGroupWithBLOBs>> achievementGroupData = projectLandAchievementGroupService.getMethodReportFilterProjectLandAchievementGroup(getSchemeJudgeObject()) ;
        if (CollectionUtils.isEmpty(achievementGroupData)) {
            return null;
        }
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        Table table = builder.startTable();
        //土地级别类别	土地级别类型	土地级别等级	说明  分值
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("土地级别类别", "土地级别类型", "土地级别等级", "说明", "分值")));
        //需要合并的单元格
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        Integer endRowIndex = 0;
        for (List<ProjectLandAchievementGroupWithBLOBs> types : achievementGroupData) {
            for (int i = 0; i < types.size(); i++) {
                ProjectLandAchievementGroupWithBLOBs group = types.get(i);
                if (i == 0) {
                    mergeCellModelList.add(new MergeCellModel(endRowIndex + 1, 0, endRowIndex + types.size(), 0));
                    endRowIndex += types.size();
                }
                if (StringUtils.isNotEmpty(group.getType())) {
                    linkedList.add(group.getType());
                } else {
                    linkedList.add(nullValue);
                }
                DataLandLevelDetailAchievementVo achievement = new DataLandLevelDetailAchievementVo();
                if (group.getSelectId() != null) {
                    achievement = dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementVo(dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementById(group.getSelectId()));
                }
                if (StringUtils.isNotEmpty(group.getClassification()) || StringUtils.isNotEmpty(group.getCategory())) {
                    StringBuilder s = new StringBuilder();
                    if (StringUtils.isNotEmpty(group.getClassification()) && StringUtils.isNotEmpty(group.getCategory())) {
                        s.append(group.getClassification()).append("/").append(group.getCategory());
                    } else if (StringUtils.isNotEmpty(group.getClassification())) {
                        s.append(group.getClassification());
                    } else {
                        s.append(group.getCategory());
                    }
                    linkedList.add(s.toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(achievement.getGradeName())) {
                    linkedList.add(achievement.getGradeName());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(achievement.getReamark())) {
                    linkedList.add(achievement.getReamark());
                } else {
                    linkedList.add(nullValue);
                }
                if (achievement.getAchievement() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(achievement.getAchievement(), 4));
                } else {
                    linkedList.add(nullValue);
                }
                generateCommonMethod.writeWordTitle(builder, linkedList);
                linkedList.clear();
            }
        }
        generateCommonMethod.mergeCellTable(mergeCellModelList, table);
        builder.endTable();
        document.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 逼近法容积率修正说明
     *
     * @author: huhao
     * @date: 2019/2/28 14:26
     */
    private synchronized String getParcelPlotRatioAdjustRemark() throws Exception {
        MdCostApproach mdCostApproach = getMdCostApproach();
        String plotRatioAdjustRemark = mdCostApproach.getPlotRatioAdjustRemark();
        if (StringUtils.isNotEmpty(plotRatioAdjustRemark)) {
            return plotRatioAdjustRemark;
        }
        return errorStr;
    }

    public String getParcelCalculateSheet()throws Exception{
        Document doc = new Document();
        DocumentBuilder documentBuilder = new DocumentBuilder(doc);
        String localPath = generateCommonMethod.getLocalPath();
        //设置表格属性
        AsposeUtils.setDefaultFontSettings(documentBuilder);
        generateCommonMethod.settingBuildingTable(documentBuilder);
        AsposeUtils.setDefaultTable(documentBuilder);
        BiConsumer<LinkedList<String>,DocumentBuilder> consumer = (((strings, builder) -> {
            try {
                AsposeUtils.writeWordTitle(builder,strings);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            strings.clear();
        })) ;
        documentBuilder.startTable();
        MdCostApproach mdCostApproach = getMdCostApproach();
        LinkedList<String> linkedList = new LinkedList<>() ;
        linkedList.addAll(Arrays.asList("项目" ,"参数" ,"参数与备注"));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("宗地外六通费用(元/㎡)");
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getCirculationExpense()));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getCirculationExpenseRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("场平费用(元/㎡)");
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getFlatExpense()));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getFlatExpenseRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("计息周期");
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getMachineCycle()));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getMachineCycleRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("计息利率");
        linkedList.add(ArithmeticUtils.getPercentileSystem(ArithmeticUtils.createBigDecimal(mdCostApproach.getCalculatedInterest()) ,4));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getCalculatedInterestRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("开发利润率");
        linkedList.add(ArithmeticUtils.getPercentileSystem(ArithmeticUtils.createBigDecimal(mdCostApproach.getProfitMargin()) ,4));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getProfitMarginRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("土地增值率");
        linkedList.add(ArithmeticUtils.getPercentileSystem(ArithmeticUtils.createBigDecimal(mdCostApproach.getIncrementalBenefit()) ,4));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getIncrementalBenefitRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("容积率调整");
        linkedList.add(ArithmeticUtils.getPercentileSystem(ArithmeticUtils.createBigDecimal(mdCostApproach.getPlotRatioAdjust()) ,4));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getPlotRatioAdjustRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("剩余年限");
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getLandRemainingYear()));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getLandRemainingYearRemark()));
        consumer.accept(linkedList,documentBuilder);

        linkedList.add("还原利率");
        linkedList.add(ArithmeticUtils.getPercentileSystem(ArithmeticUtils.createBigDecimal(mdCostApproach.getRewardRate()) ,4));
        linkedList.add(AsposeUtils.getValue(mdCostApproach.getRewardRateRemark()));
        consumer.accept(linkedList,documentBuilder);

        documentBuilder.endTable();
        AsposeUtils.saveWord(localPath,doc);
        return localPath;
    }


    private SchemeJudgeObject getSchemeJudgeObject() {
        if (schemeJudgeObject == null) {
            if (judgeObjectId != null) {
                schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            }
            if (schemeJudgeObject == null) {
                schemeJudgeObject = new SchemeJudgeObject();
            }
        }
        return schemeJudgeObject;
    }

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            this.schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
        }
        return this.schemeAreaGroup;
    }

    public GenerateMdCostApproachService(SchemeInfo schemeInfo, Integer areaId) {
        new GenerateMdCostApproachService(schemeInfo.getProjectId(),areaId,schemeInfo.getMethodDataId(),schemeInfo.getJudgeObjectId()) ;
    }

    public GenerateMdCostApproachService(Integer projectId, Integer areaId ,Integer mlId,Integer  judgeObjectId) {
        this.miId = mlId;
        this.judgeObjectId = judgeObjectId;
        this.projectId = projectId;
        this.areaId = areaId;
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.toolRewardRateService = SpringContextUtils.getBean(ToolRewardRateService.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.dataLandLevelDetailAchievementService = SpringContextUtils.getBean(DataLandLevelDetailAchievementService.class);
        this.mdCostApproachService = SpringContextUtils.getBean(MdCostApproachService.class);
        this.projectLandAchievementGroupService = SpringContextUtils.getBean(ProjectLandAchievementGroupService.class);
        this.basicApplyService = SpringContextUtils.getBean(BasicApplyService.class);
        this.basicEstateLandCategoryInfoService = SpringContextUtils.getBean(BasicEstateLandCategoryInfoService.class);
        this.dataLandLevelDetailService = SpringContextUtils.getBean(DataLandLevelDetailService.class);
    }

    private MdCostApproach getMdCostApproach() {
        if (mdCostApproach == null) {
            this.mdCostApproach = mdCostApproachService.getDataById(miId);
        }
        return mdCostApproach;
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

    private GenerateMdCostApproachService() {
    }

    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }

}

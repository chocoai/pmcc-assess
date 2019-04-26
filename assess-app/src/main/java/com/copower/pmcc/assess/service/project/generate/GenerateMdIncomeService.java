package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldMdIncomeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.common.enums.CalculationMethodNameEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseCostVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.method.MdIncomeDateSectionService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/2/12 16:43
 * @description:收益法报告字段处理
 */
public class GenerateMdIncomeService implements Serializable {
    private Integer miId;
    private Integer projectId;
    private Integer areaId;
    private SchemeInfo schemeInfo;
    private MdIncome mdIncome;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;
    private List<MdIncomeDateSection> mdIncomeDateSectionList = Lists.newArrayList();
    private List<MdIncomeLeaseCostVo> leaseVoList = Lists.newArrayList();
    private List<MdIncomeLeaseVo> mdIncomeLeaseList = Lists.newArrayList();

    private MdIncomeService mdIncomeService;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private DataSetUseFieldService dataSetUseFieldService;
    private MdIncomeDateSectionService mdIncomeDateSectionService;
    private MdIncomeLeaseDao mdIncomeLeaseDao;
    private MdIncomeLeaseCostDao mdIncomeLeaseCostDao;
    private ToolRewardRateService toolRewardRateService;
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private DataBuildingNewRateService dataBuildingNewRateService;
    private EvaluationMethodService evaluationMethodService;
    private SchemeInfoService schemeInfoService;
    private DataMethodFormulaService dataMethodFormulaService;
    private GenerateCommonMethod generateCommonMethod;

    private final String name = "name";
    private final String ratio = "ratio";
    private final String remark = "remark";
    public final String errorStr = "无";
    //机会成本
    private final String opportunityCost = "opportunityCost";
    //投资风险补偿
    private final String riskCompensation = "riskCompensation";
    //管理负担补偿
    private final String manageCompensation = "manageCompensation";
    //缺乏流动性补偿
    private final String liquidCompensation = "liquidCompensation";
    //易与获得融资的好处
    private final String financingAdvantage = "financingAdvantage";
    //所得税抵扣的好处
    private final String taxDeductionAdvantage = "taxDeductionAdvantage";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取替换后的报告模板
     *
     * @return
     * @throws Exception
     */
    public String generateCompareFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.INCOME_TEMPLATE);
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
                regexDto.setChineseName(AsposeUtils.getChinese(bookmarkCollection.get(i).getName())).setName(bookmarkCollection.get(i).getName()).setType(BaseReportFieldReplaceEnum.BOOKMARK.getKey());
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        if (CollectionUtils.isNotEmpty(regexS)) {
            for (String name : regexS) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(null).setName(name).setType(BaseReportFieldReplaceEnum.TEXT.getKey());
                bookmarkAndRegexDtoHashSet.add(regexDto);
            }
        }
        Map<String, String> textMap = Maps.newHashMap();
        Map<String, String> bookmarkMap = Maps.newHashMap();
        Map<String, String> fileMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(bookmarkAndRegexDtoHashSet)) {
            return localPath;
        }
        for (BookmarkAndRegexDto bookmarkAndRegex : bookmarkAndRegexDtoHashSet) {
            String name = StringUtils.isNotBlank(bookmarkAndRegex.getChineseName()) ? bookmarkAndRegex.getChineseName() : bookmarkAndRegex.getName();
            try {
                //收益法租金增长率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RentGrowthForecast.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.RentGrowthForecast));
                }
                //收益法租金增长率说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RentalGrowthRateExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.RentalGrowthRateExplain));
                }
                //收益法出租率说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RestrictionExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRestrictionExplain());
                }
                //收益法月租金收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MonthRentalIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.MonthRentalIncome));
                }
                //月份数
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MonthNumber.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.MonthNumber));
                }
                //收益法出租率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.Rentals.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.Rentals));
                }
                //收益法其它收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.OtherIncome));
                }
                //收益法其他收入说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncomeExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.OtherIncomeExplain));
                }
                //收益法押金说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeDepositExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.IncomeDepositExplain));
                }
                //收益法其它交易费说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.TransactionTaxeFeeExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.TransactionTaxeFeeExplain));
                }

                //一年期定期存款利率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.YearDepositRate.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.YearDepositRate));
                }
                //有效收入公式
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.EffectiveIncomeFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getEffectiveIncomeFormula());
                }
                //收益法年有效毛收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.GrossIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.GrossIncome));
                }
                //重置成本/重置价格
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ReplacementCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.ReplacementCost));
                }
                //收益法维修保养费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MaintenanceCostRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.MaintenanceCostRatio));
                }
                //收益法有效收缴率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAdditionalCapture.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.IncomeAdditionalCapture));
                }
                //收益法有效收缴费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAdditionalCaptureCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.IncomeAdditionalCaptureCost));
                }
                //收益法有效收缴率说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAdditionalCaptureRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.IncomeAdditionalCaptureRemark));
                }
                //收益法押金收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeDepositCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum.IncomeDepositCost));
                }
                //收益法年维修费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MaintenanceCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum.MaintenanceCost));
                }
                //收益法土地使用税
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.LandUseTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.LandUseTax));
                }
                //收益法土地使用税费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.LandUseCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum.LandUseCost));
                }
                //收益法管理费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ManagementCostTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.ManagementCostTax));
                }
                //收益法管理费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ManagementCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum.ManagementCost));
                }
                //收益法租赁税费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAdditionalRatioCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum.IncomeAdditionalRatioCost));
                }
                //收益法租赁税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAdditionalRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomeAdditionalRatio));
                }
                //收益法保险费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.InsurancePremiumTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.InsurancePremiumTax));
                }
                //收益法年保险费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.InsurancePremiumCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum.InsurancePremiumCost));
                }
                //收益法地方教育费附加税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeLocalEducationRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomeLocalEducationRatio));
                }
                //收益法教育费附加税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeEducationRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomeEducationRatio));
                }
                //收益法城建税税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeconstructionTaxRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomeconstructionTaxRatio));
                }
                //收益法增值税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomesalesTaxRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomesalesTaxRatio));
                }
                //收益法增值修正税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomesalesTaxRatioCorrect.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomesalesTaxRatioCorrect));
                }
                //收益法房产税修正税率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePropertyTaxCorrect.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomePropertyTaxCorrect));
                }
                //城市地方教育修正
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeCityLocalEducationTaxCorrect.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomeCityLocalEducationTaxCorrect));
                }
                //年运营费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualOperatingCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.AnnualOperatingCost));
                }
                //年净收益
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualNetIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum.AnnualNetIncome));
                }
                //单价
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomePrice());
                }
                //收益法价格测算表
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeMethodPriceCalculatingSheet.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeMethodPriceCalculating());
                }

                //收益法租赁限制说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTenancyrestrictionReamrk());
                }
                //收益法申报产权证类型
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.PropertyRightCertificateIncomeLaw.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getPropertyRightCertificateIncomeLaw());
                }
                //收益法设定用途
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeSetUse.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSetUse());
                }
                //收益法土地终止日期
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.TerminationDateLand.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTerminationDateLand());
                }
                //收益法剩余土地使用年限
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeSurplusLandUseYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSurplusLandUseYear());
                }
                //收益法竣工时间
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeCompletionTime.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeCompletionTime());
                }
                //收益法建筑结构类别
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomebuildingStructureType.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomebuildingStructureType());
                }
                //收益法价值时点
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeValuePoint.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeValuePoint());
                }
                //收益法已使用年限
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeUsedLife.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeUsedLife());
                }
                //收益法经济耐用年限
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeBuildEconomicLife.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeBuildEconomicLife());
                }
                //收益法房产剩余年限
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeHouseSurplusYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeHouseSurplusYear());
                }
                //收益法收益年限
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeYears.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeYears());
                }
                //收益法中的比较法
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeGetMdCompare.getName())) {
                    String path = getIncomeGetMdCompare();
                    File file = new File(path);
                    if (file.isFile()) {
                        if (StringUtils.isNotBlank(path)) {
                            if (false) {
                                generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, path);
                            }
                        }
                    }
                }
                //收益法区域城市
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeRegionalCities.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeRegionalCities());
                }
                //收益法评估面积
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAssessmentArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeAssessmentArea());
                }
                //收益法房产税
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePropertyTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomePropertyTax));
                }
                //收益法印花税
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomestampTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomestampTax));
                }
                //收益法其它交易费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeTransactionTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum.IncomeTransactionTax));
                }
                //收益法其它相关费用 (收益法其它交易费)
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeTransaction.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum.IncomeTransaction));
                }
                //收益法公式
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeMethodFormula.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeMethodFormula());
                }
                //收益法报酬率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePayBack.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum.IncomePayBack));
                }
                //收益法机会成本说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeOpportunityCostReamrk.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum.IncomeOpportunityCostReamrk));
                }
                //收益法投资风险补偿
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeRiskCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum.IncomeRiskCompensation));
                }
                //收益法管理负担补偿
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeManageCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum.IncomeManageCompensation));
                }
                //收益法缺乏流动性补偿
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeLiquidCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum.IncomeLiquidCompensation));
                }
                //收益法投资带来的优惠
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeFinancingAdvantage.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum.IncomeFinancingAdvantage));
                }
                //收益法单价内涵
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeUnitPriceConnotation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeUnitPriceConnotation());
                }
                //收益法确定月租金方式
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeDetermineRentalWay.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeDetermineRentalWay());
                }
            } catch (Exception e) {
                String error = e.getMessage();
                logger.error(error, e);
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
            AsposeUtils.replaceBookmarkToFile(localPath, fileMap);
        }
        return localPath;
    }


    /**
     * 功能描述: 收益法区域城市
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    public String getIncomeRegionalCities() throws Exception {
        String s = getSchemeAreaGroup().getAreaName();
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法评估面积
     *
     * @author: zch
     * @date: 2019/2/28 14:31
     */
    public String getIncomeAssessmentArea() throws Exception {
        BigDecimal bigDecimal = getSchemeJudgeObject().getEvaluationArea();
        if (bigDecimal != null) {
            return String.format("%s%s", bigDecimal.toString(), "㎡");
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法建筑结构类别
     *
     * @auther: zch
     * @date: 2019/2/27 18:18
     */
    public String getIncomebuildingStructureType() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        if (basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            if (generateBaseExamineService.getBasicBuilding() != null) {
                if (generateBaseExamineService.getBasicBuilding().getBuildingStructureType() != null) {
                    return baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureType());
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法竣工时间
     *
     * @auther: zch
     * @date: 2019/2/27 18:10
     */
    public String getIncomeCompletionTime() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        if (basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            if (generateBaseExamineService.getBasicBuilding() != null) {
                if (generateBaseExamineService.getBasicBuilding().getBeCompletedTime() != null) {
                    return DateUtils.format(generateBaseExamineService.getBasicBuilding().getBeCompletedTime(), DateUtils.DATE_CHINESE_PATTERN);
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法已使用年限
     *
     * @author: zch
     * @date: 2019/2/28 10:34
     */
    public String getIncomeUsedLife() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        if (schemeAreaGroup.getValueTimePoint() != null && basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            if (generateBaseExamineService.getBasicApply() != null) {
                if (generateBaseExamineService.getBasicBuilding() != null) {
                    if (generateBaseExamineService.getBasicBuilding().getBeCompletedTime() != null) {
                        Date date = DateUtils.addYear(schemeAreaGroup.getValueTimePoint(), -DateUtils.getYear(generateBaseExamineService.getBasicBuilding().getBeCompletedTime()));
                        return String.format("%d", DateUtils.getYear(date));
                    }
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法经济耐用年限
     *
     * @author: zch
     * @date: 2019/2/28 13:41
     */
    public String getIncomeBuildEconomicLife() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        if (schemeAreaGroup.getValueTimePoint() != null && basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            if (generateBaseExamineService.getBasicBuilding() != null) {
                if (generateBaseExamineService.getBasicBuilding().getResidenceUseYear() != null) {
                    return baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getResidenceUseYear());
                }
                if (generateBaseExamineService.getBasicBuilding().getIndustryUseYear() != null) {
                    return dataBuildingNewRateService.getByiDdataBuildingNewRate(generateBaseExamineService.getBasicBuilding().getIndustryUseYear()).getDurableLife().toString();
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法价值时点
     *
     * @author: zch
     * @date: 2019/2/28 10:27
     */
    public String getIncomeValuePoint() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        if (schemeAreaGroup.getValueTimePoint() != null) {
            return DateUtils.formatDate(schemeAreaGroup.getValueTimePoint(), DateUtils.DATE_CHINESE_PATTERN);
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法剩余土地使用年限
     *
     * @auther: zch
     * @date: 2019/2/27 18:05
     */
    public String getIncomeSurplusLandUseYear() throws Exception {
        if (getMdIncome().getLandRemainingYear() != null) {
            return getMdIncome().getLandRemainingYear().toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法房产剩余年限
     *
     * @author: zch
     * @date: 2019/2/28 13:54
     */
    public String getIncomeHouseSurplusYear() throws Exception {
        if (getMdIncome().getHouseRemainingYear() != null) {
            return getMdIncome().getHouseRemainingYear().toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法收益年限
     *
     * @author: zch
     * @date: 2019/2/28 14:01
     */
    public String getIncomeYears() throws Exception {
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        List<Double> doubleList = Lists.newArrayList(new Double(0));
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().forEach(mdIncomeLeaseCostVo -> {
                MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                if (mdIncomeDateSection != null) {
                    if (mdIncomeDateSection.getYearCount() != null) {
                        doubleList.add(mdIncomeDateSection.getYearCount().doubleValue());
                    }
                }
            });
        }
        return String.valueOf(doubleList.stream().mapToDouble(Double::doubleValue).sum());
    }

    /**
     * 功能描述: 收益法中的比较法
     *
     * @author: zch
     * @date: 2019/2/28 14:13
     */
    public String getIncomeGetMdCompare() throws Exception {
        DataEvaluationMethod dataEvaluationMethod = null;
        SchemeInfo schemeInfo = null;
        List<DataEvaluationMethod> dataEvaluationMethodList = evaluationMethodService.getMethodAllList();
        if (CollectionUtils.isNotEmpty(dataEvaluationMethodList)) {
            if (dataEvaluationMethodList.stream().filter(oo -> CalculationMethodNameEnum.MdCompare.getName().indexOf(oo.getName()) != -1).count() >= 1) {
                dataEvaluationMethod = dataEvaluationMethodList.stream().filter(oo -> CalculationMethodNameEnum.MdCompare.getName().indexOf(oo.getName()) != -1).findFirst().get();
            }
        }
        if (dataEvaluationMethod != null) {
            schemeInfo = schemeInfoService.getSchemeInfo(getSchemeJudgeObject().getId(), dataEvaluationMethod.getMethod());
        }
        if (schemeInfo != null) {
            if (schemeInfo.getMethodDataId() != null) {
                try {
                    GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(getSchemeJudgeObject().getId(), schemeInfo.getMethodDataId(), new Date(), areaId);
                    String temp = generateMdCompareService.generateCompareFile();
                    File file = new File(temp);
                    if (file.isFile()) {
                        return temp;
                    }
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    //收益法单价内涵
    public String getIncomeUnitPriceConnotation() throws Exception {
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        if (basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicHouseTrading houseTrading = generateBaseExamineService.getBasicTrading();
            if (houseTrading != null && houseTrading.getPriceConnotation() != null) {
                return baseDataDicService.getNameById(houseTrading.getPriceConnotation());
            }
        }
        return errorStr;
    }

    //收益法确定月租金方式
    public String getIncomeDetermineRentalWay() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(document);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        boolean flag = false;
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            flag = mdIncomeLeaseList.stream().filter(oo -> oo.getMcId() != null).count() >= 1;
        }
        String val = " ";
        if (flag) {
            val = "租金采用市场比较法求取:比较价格V=可比实例价格×交易情况修正系数×市场状况调整系数×房地产状况调整系数";
        }
        if (!flag) {
            val = "租金采用市场调查方法求取:比较价格V=调查价格×∑修正系数";
        }
        documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(val), false);
        document.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 收益法公式
     *
     * @author: zch
     * @date: 2019/2/28 16:37
     */
    public String getIncomeMethodFormula() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        DocumentBuilder documentBuilder = getDefaultDocumentBuilderSetting(document);
        List<DataEvaluationMethod> dataEvaluationMethodList = evaluationMethodService.getMethodAllList();
        DataEvaluationMethod dataEvaluationMethod = null;
        BaseDataDic byFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
        if (CollectionUtils.isNotEmpty(dataEvaluationMethodList)) {
            if (dataEvaluationMethodList.stream().anyMatch(oo -> Objects.equal(byFieldName.getName(), oo.getName()))) {
                dataEvaluationMethod = dataEvaluationMethodList.stream().filter(oo -> Objects.equal(byFieldName.getName(), oo.getName())).findFirst().get();
            }
        }
        if (dataEvaluationMethod != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethod.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                if (StringUtils.isNotBlank(dataMethodFormulaList.stream().findFirst().get().getFormula())) {
                    String s = null;
                    if (leaseVoList.size() == 1) {
                        s = dataMethodFormulaList.stream().findFirst().get().getFormula();
                    } else {
                        s = "V1+V2";
                    }
                    documentBuilder.insertHtml(generateCommonMethod.getWarpCssHtml(s), false);
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 收益法土地终止日期
     *
     * @auther: zch
     * @date: 2019/2/27 17:44
     */
    public String getTerminationDateLand() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (schemeJudgeObject.getDeclareRecordId() != null) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null && declareRecord.getLandUseEndDate() != null) {
                return DateUtils.format(declareRecord.getLandUseEndDate(), DateUtils.DATE_CHINESE_PATTERN);
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法设定用途
     *
     * @auther: zch
     * @date: 2019/2/27 17:35
     */
    public String getIncomeSetUse() {
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
     * 功能描述: 收益法申报产权证类型
     *
     * @auther: zch
     * @date: 2019/2/27 17:21
     */
    public String getPropertyRightCertificateIncomeLaw() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (schemeJudgeObject.getDeclareRecordId() != null) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    return DeclareTypeEnum.LAND.getKey();
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    return DeclareTypeEnum.HOUSE.getKey();
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    return DeclareTypeEnum.RealEstate.getKey();
                }
            }
        }
        return errorStr;
    }

    /**
     * 出租率说明
     *
     * @return
     * @throws Exception
     */
    public String getRestrictionExplain() {
        if (StringUtils.isNotBlank(getMdIncome().getRestrictionExplain())) {
            return getMdIncome().getRestrictionExplain();
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法租赁限制说明
     *
     * @author: zch
     * @date: 2019/2/28 16:28
     */
    public String getTenancyrestrictionReamrk() throws Exception {
        StringBuilder builder = new StringBuilder(8);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
            if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                return false;
            }
            if (StringUtils.isEmpty(mdIncomeLeaseCostVo.getAdditional())) {
                return false;
            }
            return true;
        }).count() >= 1) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                if (StringUtils.isEmpty(mdIncomeLeaseCostVo.getAdditional())) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, mdIncomeLeaseCostVo.getAdditional(), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append("不考虑估价对象租赁因素影响");
        }
        return builder.toString();
    }

    /**
     * 有效收入公式
     *
     * @return
     */
    public String getEffectiveIncomeFormula() {
        return "租金收入+其它收入内容+年押金收入+年押金利息收入";
    }

    /**
     * 获取各个种类金额
     *
     * @param incomeEnum
     * @return
     */
    public String getMdIncomeLeaseCommon(BaseReportFieldMdIncomeEnum incomeEnum) {
        StringBuilder builder = new StringBuilder(8);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            if (mdIncomeLeaseList.stream().filter(costVo -> {
                if (costVo.getBeginDate() == null || costVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).count() >= 1) {
                mdIncomeLeaseList.stream().filter(incomeLeaseVo -> {
                    if (incomeLeaseVo.getBeginDate() == null || incomeLeaseVo.getEndDate() == null) {
                        return false;
                    }
                    return true;
                }).forEach(mdIncomeLeaseVo -> {
                    BigDecimal cost = null;
                    String value = null;
                    switch (incomeEnum) {
                        case MonthRentalIncome:
                            cost = mdIncomeLeaseVo.getRentalIncome();
                            break;
                        case IncomeAdditionalCapture:
                            if (NumberUtils.isNumber(mdIncomeLeaseVo.getAdditionalCapture())) {
                                BigDecimal bigDecimal = new BigDecimal(mdIncomeLeaseVo.getAdditionalCapture());
                                bigDecimal = bigDecimal.multiply(new BigDecimal(100));
                                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                                value = String.format("%s%s", bigDecimal.toString(), "%");
                            }
                            break;
                        case IncomeAdditionalCaptureRemark:
                            value = mdIncomeLeaseVo.getAdditionalCaptureRemark();
                            break;
                        case IncomeAdditionalCaptureCost:
                            if (NumberUtils.isNumber(mdIncomeLeaseVo.getAdditionalCapture())) {
                                MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseVo.getSectionId());
                                if (mdIncomeDateSection != null) {
                                    cost = mdIncomeDateSection.getIncomeTotal().multiply(new BigDecimal(mdIncomeLeaseVo.getAdditionalCapture()));
                                }
                            }
                            break;
                        case MonthNumber:
                            if (mdIncomeLeaseVo.getMonthNumber() != null) {
                                cost = new BigDecimal(mdIncomeLeaseVo.getMonthNumber());
                            }
                            break;
                        case IncomeDepositCost:
                            if (mdIncomeLeaseVo.getDeposit() != null && mdIncomeLeaseVo.getDepositRate() != null) {
                                cost = mdIncomeLeaseVo.getDeposit().multiply(mdIncomeLeaseVo.getDepositRate());
                            }
                            break;
                        case Rentals:
                            cost = mdIncomeLeaseVo.getRentals();
                            break;
                        case YearDepositRate:
                            cost = mdIncomeLeaseVo.getDepositRate().multiply(new BigDecimal(100));
                            break;
                        case OtherIncome:
                            cost = mdIncomeLeaseVo.getOtherIncome();
                            break;
                        case IncomeDepositExplain:
                            value = mdIncomeLeaseVo.getDepositRemark();
                            break;
                        case OtherIncomeExplain:
                            value = mdIncomeLeaseVo.getOtherIncomeRemark();
                            break;
                        default:
                            break;
                    }
                    if (cost != null) {
                        cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
                        this.appendElement(builder, cost.toString(), mdIncomeLeaseVo.getBeginDate(), mdIncomeLeaseVo.getEndDate());
                    }
                    if (StringUtils.isNotBlank(value)) {
                        this.appendElement(builder, value, mdIncomeLeaseVo.getBeginDate(), mdIncomeLeaseVo.getEndDate());
                    }
                });
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }


    /**
     * 获取各个种类金额 (其它)
     *
     * @param incomeEnum
     * @return
     */
    public String getMdIncomeLeaseCostOtherCommon(BaseReportFieldMdIncomeEnum incomeEnum) {
        StringBuilder builder = new StringBuilder(8);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            if (leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).count() >= 1) {
                leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                    if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                        return false;
                    }
                    return true;
                }).forEach(mdIncomeLeaseCostVo -> {
                    BigDecimal cost = null;
                    String value = null;
                    MdIncomeDateSection mdIncomeDateSection = null;
                    switch (incomeEnum) {
                        case ReplacementCost:
                            if (mdIncomeLeaseCostVo.getReplacementValue() != null) {
                                cost = mdIncomeLeaseCostVo.getReplacementValue();
                            }
                            break;
                        case GrossIncome:
                            mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                            if (mdIncomeDateSection != null) {
                                if (mdIncomeDateSection.getIncomeTotal() != null) {
                                    cost = mdIncomeDateSection.getIncomeTotal();
                                }
                            }
                            break;
                        case AnnualOperatingCost:
                            mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                            if (mdIncomeDateSection != null) {
                                if (mdIncomeDateSection.getCostTotal() != null) {
                                    cost = mdIncomeDateSection.getCostTotal();
                                }
                            }
                            break;
                        case RentGrowthForecast:
                            mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                            if (mdIncomeDateSection != null) {
                                if (mdIncomeDateSection.getRentalGrowthRate() != null) {
                                    cost = mdIncomeDateSection.getRentalGrowthRate();
                                }
                            }
                            break;
                        case AnnualNetIncome:
                            mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                            if (mdIncomeDateSection != null) {
                                if (NumberUtils.isNumber(mdIncomeDateSection.getNetProfit())) {
                                    cost = new BigDecimal(mdIncomeDateSection.getNetProfit());
                                }
                            }
                            break;
                        case TransactionTaxeFeeExplain:
                            value = mdIncomeLeaseCostVo.getTransactionTaxeFeeExplain();
                            break;
                        case RentalGrowthRateExplain:
                            mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                            if (mdIncomeDateSection != null) {
                                value = mdIncomeDateSection.getRentalGrowthRateExplain();
                            }
                            break;
                        default:
                            break;
                    }
                    if (cost != null) {
                        cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
                        this.appendElement(builder, cost.toString(), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
                    }
                    if (StringUtils.isNotBlank(value)) {
                        this.appendElement(builder, value, mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
                    }
                });
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }


    /**
     * 获取各个税收金额
     *
     * @param incomeEnum
     * @return
     */
    public String getMdIncomeLeaseCostCommon(BaseReportFieldMdIncomeEnum incomeEnum) {
        StringBuilder builder = new StringBuilder(8);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            if (leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).count() >= 1) {
                leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                    if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                        return false;
                    }
                    return true;
                }).forEach(mdIncomeLeaseCostVo -> {
                    BigDecimal cost = null;
                    switch (incomeEnum) {
                        case IncomeTransaction:
                            if (mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio() != null) {
                                cost = mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio();
                            }
                            break;
                        case MaintenanceCost:
                            if (mdIncomeLeaseCostVo.getMaintenanceCostRatio() != null) {
                                cost = mdIncomeLeaseCostVo.getMaintenanceCostRatio();
                            }
                            break;
                        case InsurancePremiumCost:
                            if (mdIncomeLeaseCostVo.getInsurancePremiumRatio() != null) {
                                cost = mdIncomeLeaseCostVo.getInsurancePremiumRatio();
                            }
                            break;
                        case ManagementCost:
                            if (mdIncomeLeaseCostVo.getManagementCostRatio() != null) {
                                cost = mdIncomeLeaseCostVo.getManagementCostRatio();
                            }
                            break;
                        case IncomeAdditionalRatioCost:
                            if (mdIncomeLeaseCostVo.getAdditionalRatio() != null) {
                                cost = mdIncomeLeaseCostVo.getAdditionalRatio();
                            }
                            break;
                        case LandUseCost:
                            if (mdIncomeLeaseCostVo.getLandUseTax() != null) {
                                cost = mdIncomeLeaseCostVo.getLandUseTax();
                            }
                            break;
                        default:
                            break;
                    }
                    MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                    if (mdIncomeDateSection != null) {
                        if (mdIncomeDateSection.getIncomeTotal() != null) {
                            BigDecimal multiply = cost.multiply(mdIncomeDateSection.getIncomeTotal());
                            multiply = multiply.setScale(2, BigDecimal.ROUND_HALF_UP);
                            this.appendElement(builder, multiply.toString(), mdIncomeDateSection.getBeginDate(), mdIncomeDateSection.getEndDate());
                        }
                    }
                });
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 获取各类税率
     *
     * @param incomeEnum
     * @return
     */
    public String getMdIncomeLeaseCostCommonTax(BaseReportFieldMdIncomeEnum incomeEnum) {
        StringBuilder builder = new StringBuilder(8);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            if (leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).count() >= 1) {
                leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                    if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                        return false;
                    }
                    return true;
                }).forEach(mdIncomeLeaseCostVo -> {
                    BigDecimal decimal = null;
                    switch (incomeEnum) {
                        case IncomesalesTaxRatio:
                            if (mdIncomeLeaseCostVo.getSalesTaxRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getSalesTaxRatio();
                            }
                            break;
                        case IncomesalesTaxRatioCorrect:
                            if (mdIncomeLeaseCostVo.getSalesTaxRatio() != null) {
                                decimal = new BigDecimal(1 + mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue());
                                decimal = decimal.multiply(mdIncomeLeaseCostVo.getSalesTaxRatio());
                            }
                            break;
                        case IncomePropertyTax:
                            if (mdIncomeLeaseCostVo.getPropertyTaxRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getPropertyTaxRatio();
                            }
                            break;
                        case IncomePropertyTaxCorrect:
                            if (mdIncomeLeaseCostVo.getSalesTaxRatio() != null && mdIncomeLeaseCostVo.getPropertyTaxRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getPropertyTaxRatio().multiply(new BigDecimal(1).add(mdIncomeLeaseCostVo.getSalesTaxRatio()));
                            }
                            break;
                        case IncomeCityLocalEducationTaxCorrect:
                            if (mdIncomeLeaseCostVo.getConstructionTaxRatio() == null) {
                                break;
                            }
                            if (mdIncomeLeaseCostVo.getEducationRatio() == null) {
                                break;
                            }
                            if (mdIncomeLeaseCostVo.getLocalEducationRatio() == null) {
                                break;
                            }
                            if (mdIncomeLeaseCostVo.getSalesTaxRatio() == null) {
                                break;
                            }
                            BigDecimal start = mdIncomeLeaseCostVo.getConstructionTaxRatio().add(mdIncomeLeaseCostVo.getEducationRatio()).add(mdIncomeLeaseCostVo.getLocalEducationRatio());
                            BigDecimal end = new BigDecimal(1 + mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue()).multiply(mdIncomeLeaseCostVo.getSalesTaxRatio());
                            decimal = start.multiply(end);
                            break;
                        case IncomestampTax:
                            if (mdIncomeLeaseCostVo.getStampDutyRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getStampDutyRatio();
                            }
                            break;
                        case IncomeconstructionTaxRatio:
                            if (mdIncomeLeaseCostVo.getConstructionTaxRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getConstructionTaxRatio();
                            }
                            break;
                        case IncomeEducationRatio:
                            if (mdIncomeLeaseCostVo.getEducationRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getEducationRatio();
                            }
                            break;
                        case IncomeLocalEducationRatio:
                            if (mdIncomeLeaseCostVo.getLocalEducationRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getLocalEducationRatio();
                            }
                            break;
                        case IncomeTransactionTax:
                            if (mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio();
                            }
                            break;
                        case LandUseTax:
                            if (mdIncomeLeaseCostVo.getLandUseTax() != null) {
                                decimal = mdIncomeLeaseCostVo.getLandUseTax();
                            }
                            break;
                        case InsurancePremiumTax:
                            if (mdIncomeLeaseCostVo.getInsurancePremiumRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getInsurancePremiumRatio();
                            }
                            break;
                        case MaintenanceCostRatio:
                            if (mdIncomeLeaseCostVo.getMaintenanceCostRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getMaintenanceCostRatio();
                            }
                            break;
                        case ManagementCostTax:
                            if (mdIncomeLeaseCostVo.getManagementCostRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getManagementCostRatio();
                            }
                            break;
                        case IncomeAdditionalRatio:
                            if (mdIncomeLeaseCostVo.getAdditionalRatio() != null) {
                                decimal = mdIncomeLeaseCostVo.getAdditionalRatio();
                            }
                            break;
                        default:
                            break;
                    }
                    if (decimal != null) {
                        this.appendElement(builder, generateCommonMethod.getPercentileSystem(decimal), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
                    }
                });
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 报酬率测算 公共方法
     *
     * @param incomeEnum
     * @return
     */
    public String getToolRewardRateIncomeCommon(BaseReportFieldMdIncomeEnum incomeEnum) throws Exception {
        StringBuilder builder = new StringBuilder(8);
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate == null) {
            return errorStr;
        }
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
        } catch (Exception e) {
        }
        String value = null;
        switch (incomeEnum) {
            case IncomePayBack:
                value = toolRewardRate.getResultValue();
                break;
            case IncomeOpportunityCostReamrk:
                jsonObject = getToolRewardRateHelp(jsonArray, opportunityCost);
                if (jsonObject != null) {
                    value = jsonObject.getString(remark);
                }
                break;
            case IncomeRiskCompensation:
                jsonObject = getToolRewardRateHelp(jsonArray, riskCompensation);
                if (jsonObject != null) {
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double d = Double.parseDouble(text);
                        d = d * 100;
                        value = String.format("%s%s", Double.toString(d), "%");
                    }
                }
                break;
            case IncomeManageCompensation:
                jsonObject = getToolRewardRateHelp(jsonArray, manageCompensation);
                if (jsonObject != null) {
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double d = Double.parseDouble(text);
                        d = d * 100;
                        value = String.format("%s%s", Double.toString(d), "%");
                    }
                }
                break;
            case IncomeLiquidCompensation:
                jsonObject = getToolRewardRateHelp(jsonArray, liquidCompensation);
                if (jsonObject != null) {
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double d = Double.parseDouble(text);
                        d = d * 100;
                        value = String.format("%s%s", Double.toString(d), "%");
                    }
                }
                break;
            case IncomeFinancingAdvantage:
                jsonObject = getToolRewardRateHelp(jsonArray, financingAdvantage);
                if (jsonObject != null) {
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double d = Double.parseDouble(text);
                        d = d * 100;
                        value = String.format("%s%s", Double.toString(d), "%");
                    }
                }
                break;
            default:
                break;
        }
        if (StringUtils.isNotBlank(value)) {
            builder.append(value);
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
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
                    String text = object.getString(name);
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
                        String text = object.getString(name);
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

    /**
     * 收益法价格测算表
     *
     * @return
     * @throws Exception
     */
    public String getIncomeMethodPriceCalculating() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        builder.startTable();
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("开始时间", "结束时间", "收益期限(n)", "毛收入", "运营费", "房地产年净收益", "年期修正系数(h)", "收益现值系数(k)", "房地产收益价格")));
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            for (MdIncomeDateSection mdIncomeDateSection : mdIncomeDateSectionList) {
                if (mdIncomeDateSection.getBeginDate() != null) {
                    linkedList.add(DateUtils.format(mdIncomeDateSection.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getEndDate() != null) {
                    linkedList.add(DateUtils.format(mdIncomeDateSection.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getYearCount() != null) {
                    linkedList.add(mdIncomeDateSection.getYearCount().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getIncomeTotal() != null) {
                    linkedList.add(mdIncomeDateSection.getIncomeTotal().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getCostTotal() != null) {
                    linkedList.add(mdIncomeDateSection.getCostTotal().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotBlank(mdIncomeDateSection.getNetProfit())) {
                    linkedList.add(mdIncomeDateSection.getNetProfit());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getCorrectionFactor() != null) {
                    linkedList.add(mdIncomeDateSection.getCorrectionFactor().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getPresentValueFactor() != null) {
                    linkedList.add(mdIncomeDateSection.getPresentValueFactor().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getIncomePrice() != null) {
                    linkedList.add(mdIncomeDateSection.getIncomePrice().toString());
                } else {
                    linkedList.add(nullValue);
                }
                generateCommonMethod.writeWordTitle(builder, linkedList);
                linkedList.clear();
            }
        }
        builder.endTable();
        document.save(localPath);
        return localPath;
    }

    /**
     * 单价
     *
     * @return
     */
    private String getIncomePrice() {
        BigDecimal price = getMdIncome().getPrice();
        if (price != null) {
            return price.toString();
        }
        return errorStr;
    }

    private void appendElement(StringBuilder builder, String content, Date start, Date end) {
        if (StringUtils.isEmpty(content)) {
            return;
        }
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        long count = mdIncomeDateSectionList.stream().filter(mdIncomeDateSection -> {
            if (mdIncomeDateSection.getIncomeId() == null) {
                return false;
            }
            if (mdIncomeDateSection.getBeginDate() == null) {
                return false;
            }
            if (mdIncomeDateSection.getEndDate() == null) {
                return false;
            }
            return true;
        }).count();
        if (count > 1) {
            builder.append(DateUtils.format(start, DateUtils.DATE_CHINESE_PATTERN));
            builder.append("-");
            builder.append(DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN));
            builder.append(":");
        }
        builder.append(content);
        if (count > 1) {
            builder.append(";");
        }
    }

    private List<MdIncomeDateSection> getMdIncomeDateSectionList() {
        if (CollectionUtils.isEmpty(this.mdIncomeDateSectionList)) {
            MdIncomeDateSection query = new MdIncomeDateSection();
            query.setIncomeId(miId);
            mdIncomeDateSectionList = mdIncomeDateSectionService.getMdIncomeDateSectionList(query);
        }
        return mdIncomeDateSectionList;
    }

    private List<MdIncomeLeaseVo> getMdIncomeLeaseList() {
        if (CollectionUtils.isEmpty(mdIncomeLeaseList)) {
            MdIncomeLease query = new MdIncomeLease();
            query.setIncomeId(miId);
            mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                    .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
        }
        return mdIncomeLeaseList;
    }

    private List<MdIncomeLeaseCostVo> getLeaseVoList() {
        if (CollectionUtils.isEmpty(this.leaseVoList)) {
            MdIncomeLeaseCost query = new MdIncomeLeaseCost();
            query.setIncomeId(miId);
            leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
        }
        return this.leaseVoList;
    }

    private SchemeInfo getSchemeInfo() {
        return schemeInfo;
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

    private SchemeAreaGroup getSchemeAreaGroup() {
        if (this.schemeAreaGroup == null) {
            this.schemeAreaGroup = schemeAreaGroupService.get(areaId);
        }
        return this.schemeAreaGroup;
    }

    public GenerateMdIncomeService(SchemeInfo schemeInfo, Integer projectId, Integer areaId) {
        this.miId = schemeInfo.getMethodDataId();
        this.schemeInfo = schemeInfo;
        this.projectId = projectId;
        this.areaId = areaId;
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.mdIncomeDateSectionService = SpringContextUtils.getBean(MdIncomeDateSectionService.class);
        this.mdIncomeLeaseDao = SpringContextUtils.getBean(MdIncomeLeaseDao.class);
        this.mdIncomeLeaseCostDao = SpringContextUtils.getBean(MdIncomeLeaseCostDao.class);
        this.toolRewardRateService = SpringContextUtils.getBean(ToolRewardRateService.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.dataBuildingNewRateService = SpringContextUtils.getBean(DataBuildingNewRateService.class);
        this.evaluationMethodService = SpringContextUtils.getBean(EvaluationMethodService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
    }

    private MdIncome getMdIncome() {
        if (mdIncome == null) {
            this.mdIncome = mdIncomeService.getIncomeById(miId);
        }
        return mdIncome;
    }

    /**
     * 功能描述: 设置默认字体
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/3/1 14:32
     */
    private DocumentBuilder getDefaultDocumentBuilderSetting(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        AsposeUtils.setDefaultFontSettings(builder);
        return builder;
    }

    private GenerateMdIncomeService() {
    }

    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }
}

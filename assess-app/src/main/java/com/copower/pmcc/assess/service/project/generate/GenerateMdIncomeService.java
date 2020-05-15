package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.ReportFieldMdIncomeEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseCostVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.tool.ToolRewardRateService;
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
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/2/12 16:43
 * @description:收益法报告字段处理
 */
public class GenerateMdIncomeService implements Serializable {
    private final LinkedList<String> chineseNumbers = Lists.newLinkedList(Arrays.asList("一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十"));
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
    private BaseService baseService;

    private static final String name = "name";
    private static final String ratio = "ratio";
    private static final String remark = "remark";
    private static final String errorStr = "无";
    //机会成本
    private static final String opportunityCost = "opportunityCost";
    //投资风险补偿
    private static final String riskCompensation = "riskCompensation";
    //管理负担补偿
    private static final String manageCompensation = "manageCompensation";
    //缺乏流动性补偿
    private static final String liquidCompensation = "liquidCompensation";
    //易与获得融资的好处
    private static final String financingAdvantage = "financingAdvantage";
    //所得税抵扣的好处
    private static final String taxDeductionAdvantage = "taxDeductionAdvantage";

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
                //收益法租金增长率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.RentGrowthForecast.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.RentGrowthForecast));
                }
                //收益法租金增长率说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.RentalGrowthRateExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.RentalGrowthRateExplain));
                }
                //收益法出租率说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.RestrictionExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.RestrictionExplain));
                }
                //收益法确定客观月租金
                if (Objects.equal(name, ReportFieldMdIncomeEnum.MonthRentalIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMonthRentalIncome(fileMap));
                }
                //月份数
                if (Objects.equal(name, ReportFieldMdIncomeEnum.MonthNumber.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.MonthNumber));
                }
                //收益法出租率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.Rentals.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.Rentals));
                }
                //收益法其它收入
                if (Objects.equal(name, ReportFieldMdIncomeEnum.OtherIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.OtherIncome));
                }
                //收益法其他收入说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.OtherIncomeExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.OtherIncomeExplain));
                }
                //收益法押金说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeDepositExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.IncomeDepositExplain));
                }
                //收益法其它交易费说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.TransactionTaxeFeeExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.TransactionTaxeFeeExplain));
                }

                //一年期定期存款利率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.YearDepositRate.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.YearDepositRate));
                }
                //有效收入公式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.EffectiveIncomeFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getEffectiveIncomeFormula());
                }
                //收益法年有效毛收入
                if (Objects.equal(name, ReportFieldMdIncomeEnum.GrossIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.GrossIncome));
                }

                //收益法年维修费公式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.MaintenanceCostFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getSimpleFormula(ReportFieldMdIncomeEnum.ReplacementCost, ReportFieldMdIncomeEnum.MaintenanceCostRatio));
                }
                //收益法年管理费公式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.ManagementCostFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getSimpleFormula(ReportFieldMdIncomeEnum.GrossIncomeValue, ReportFieldMdIncomeEnum.ManagementCostTax));
                }
                //收益法年保险费公式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.InsurancePremiumCostFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getSimpleFormula(ReportFieldMdIncomeEnum.ReplacementCost, ReportFieldMdIncomeEnum.InsurancePremiumTax));
                }
                //收益法租赁税费公式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAdditionalRatioCostFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getSimpleFormula(ReportFieldMdIncomeEnum.GrossIncomeValue, ReportFieldMdIncomeEnum.IncomeAdditionalRatio));
                }

                //收益法测算公式带值
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeCalculateFormulaValue.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeCalculateFormulaValue());
                }



                //重置成本/重置价格
                if (Objects.equal(name, ReportFieldMdIncomeEnum.ReplacementCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.ReplacementCost));
                }
                //收益法维修保养费率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.MaintenanceCostRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.MaintenanceCostRatio));
                }
                //收益法有效收缴率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAdditionalCapture.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.IncomeAdditionalCapture));
                }
                //收益法有效收缴费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAdditionalCaptureCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.IncomeAdditionalCaptureCost));
                }
                //收益法有效收缴率说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAdditionalCaptureRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.IncomeAdditionalCaptureRemark));
                }
                //收益法押金收入
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeDepositCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum.IncomeDepositCost));
                }
                //收益法年维修费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.MaintenanceCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum.MaintenanceCost));
                }
                //收益法土地使用税
                if (Objects.equal(name, ReportFieldMdIncomeEnum.LandUseTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.LandUseTax));
                }
                //收益法土地使用税费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.LandUseCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum.LandUseCost));
                }
                //收益法管理费率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.ManagementCostTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.ManagementCostTax));
                }
                //收益法管理费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.ManagementCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum.ManagementCost));
                }
                //收益法租赁税费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAdditionalRatioCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum.IncomeAdditionalRatioCost));
                }
                //收益法租赁税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAdditionalRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomeAdditionalRatio));
                }
                //收益法保险费率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.InsurancePremiumTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.InsurancePremiumTax));
                }
                //收益法年保险费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.InsurancePremiumCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum.InsurancePremiumCost));
                }
                //收益法地方教育费附加税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeLocalEducationRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomeLocalEducationRatio));
                }
                //收益法教育费附加税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeEducationRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomeEducationRatio));
                }
                //收益法城建税税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeconstructionTaxRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomeconstructionTaxRatio));
                }
                //收益法增值税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomesalesTaxRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomesalesTaxRatio));
                }
                //收益法增值修正税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomesalesTaxRatioCorrect.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomesalesTaxRatioCorrect));
                }
                //收益法房产税修正税率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomePropertyTaxCorrect.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomePropertyTaxCorrect));
                }
                //城市地方教育修正
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeCityLocalEducationTaxCorrect.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomeCityLocalEducationTaxCorrect));
                }
                //年运营费
                if (Objects.equal(name, ReportFieldMdIncomeEnum.AnnualOperatingCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.AnnualOperatingCost));
                }
                //年净收益
                if (Objects.equal(name, ReportFieldMdIncomeEnum.AnnualNetIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name,
                            getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum.AnnualNetIncome));
                }
                //单价
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomePrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomePrice());
                }
                //收益法价格测算表
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeMethodPriceCalculatingSheet.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeMethodPriceCalculating());
                }

                //收益法租赁限制说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTenancyrestrictionReamrk());
                }
                //收益法申报产权证类型
                if (Objects.equal(name, ReportFieldMdIncomeEnum.PropertyRightCertificateIncomeLaw.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getPropertyRightCertificateIncomeLaw());
                }
                //收益法设定用途
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeSetUse.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSetUse());
                }
                //收益法土地终止日期
                if (Objects.equal(name, ReportFieldMdIncomeEnum.TerminationDateLand.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTerminationDateLand());
                }
                //收益法剩余土地使用年限
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeSurplusLandUseYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSurplusLandUseYear());
                }
                //收益法竣工时间
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeCompletionTime.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeCompletionTime());
                }
                //收益法建筑结构类别
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomebuildingStructureType.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomebuildingStructureType());
                }
                //收益法价值时点
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeValuePoint.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeValuePoint());
                }
                //收益法已使用年限
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeUsedLife.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeUsedLife());
                }
                //收益法经济耐用年限
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeBuildEconomicLife.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeBuildEconomicLife());
                }
                //收益法房产剩余年限
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeHouseSurplusYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeHouseSurplusYear());
                }
                //收益法收益年限
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeYears.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeYears());
                }
                //收益法中的比较法
//                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeGetMdCompare.getName())) {
//                    String path = getIncomeGetMdCompare();
//                    File file = new File(path);
//                    if (file.isFile()) {
//                        if (StringUtils.isNotBlank(path)) {
//                            generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, path);
//                        }
//                    }
//                }
                //收益法区域城市
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeRegionalCities.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeRegionalCities());
                }
                //收益法评估面积
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeAssessmentArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeAssessmentArea());
                }
                //收益法房产税
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomePropertyTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomePropertyTax));
                }
                //收益法印花税
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomestampTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomestampTax));
                }
                //收益法其它交易费率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeTransactionTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum.IncomeTransactionTax));
                }
                //收益法其它相关费用 (收益法其它交易费)
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeTransaction.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum.IncomeTransaction));
                }
                //收益法公式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeMethodFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeMethodFormula());
                }
                //收益法报酬率
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomePayBack.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum.IncomePayBack));
                }
                //收益法机会成本说明
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeOpportunityCostReamrk.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum.IncomeOpportunityCostReamrk));
                }
                //收益法投资风险补偿
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeRiskCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum.IncomeRiskCompensation));
                }
                //收益法管理负担补偿
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeManageCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum.IncomeManageCompensation));
                }
                //收益法缺乏流动性补偿
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeLiquidCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum.IncomeLiquidCompensation));
                }
                //收益法投资带来的优惠
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeFinancingAdvantage.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum.IncomeFinancingAdvantage));
                }
                //收益法单价内涵
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeUnitPriceConnotation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeUnitPriceConnotation());
                }
                //收益法确定月租金方式
                if (Objects.equal(name, ReportFieldMdIncomeEnum.IncomeDetermineRentalWay.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeDetermineRentalWay());
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
     * 功能描述: 收益法区域城市
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private synchronized String getIncomeRegionalCities() throws Exception {
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
    private synchronized String getIncomeAssessmentArea() throws Exception {
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
    private synchronized String getIncomebuildingStructureType() throws Exception {
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
    private synchronized String getIncomeCompletionTime() throws Exception {
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
    private synchronized String getIncomeUsedLife() throws Exception {
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
    private synchronized String getIncomeBuildEconomicLife() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
        if (schemeAreaGroup.getValueTimePoint() != null && basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            if (generateBaseExamineService.getBasicBuilding() != null) {
                if (generateBaseExamineService.getBasicBuilding().getResidenceUseYear() != null) {
                    String v = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getResidenceUseYear());
                    return generateCommonMethod.getNumber(v);
                }
                if (generateBaseExamineService.getBasicBuilding().getIndustryUseYear() != null) {
                    String v = dataBuildingNewRateService.getByiDdataBuildingNewRate(generateBaseExamineService.getBasicBuilding().getIndustryUseYear()).getDurableLife().toString();
                    return generateCommonMethod.getNumber(v);
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
    private synchronized String getIncomeValuePoint() throws Exception {
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
    private synchronized String getIncomeSurplusLandUseYear() throws Exception {
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
    private synchronized String getIncomeHouseSurplusYear() throws Exception {
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
    private synchronized String getIncomeYears() throws Exception {
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
     * 收益法确定客观月租金
     *
     * @return
     * @throws Exception
     */
    private synchronized String getMonthRentalIncome(Map<String, String> fileMap)throws Exception  {
        List<MdIncomeLeaseVo> leaseVoList = getMdIncomeLeaseList();
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            for (int i = 0; i < leaseVoList.size(); i++) {
                MdIncomeLeaseVo vo = leaseVoList.get(i);
                StringBuilder stringBuilder = new StringBuilder(8);
                StringBuilder dateRangeBuilder = new StringBuilder(8);
                if (i != 0){
                    dateRangeBuilder.append(StringUtils.repeat(" ",3)) ;
                }
                dateRangeBuilder.append("第");
                dateRangeBuilder.append(getChineseNumber(i));
                dateRangeBuilder.append("段");
                dateRangeBuilder.append(DateUtils.format(vo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                dateRangeBuilder.append("-");
                if (vo.getEndDate() != null) {
                    dateRangeBuilder.append(DateUtils.format(vo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                } else {
                    dateRangeBuilder.append("未知");
                }
                stringBuilder.append(dateRangeBuilder.toString());

                StringBuilder priceExplainBuilder = new StringBuilder(8);
                String singleKey = new String();
                if (vo.getMcId() != null) {
                    String path = getMdComparePath(vo.getMcId());
                    if (StringUtils.isNotBlank(path)) {
                        String key = String.format("%s%s","compareMethod",RandomStringUtils.randomNumeric(12));
                        priceExplainBuilder.append("调用比较法测算");
                        priceExplainBuilder.append(String.join("",StringUtils.repeat(ControlChar.LINE_BREAK, 1)));
                        priceExplainBuilder.append(key);
                        singleKey = key;
                        fileMap.put(key,path) ;
                    }
                } else {//没有调用市场比较法则说出收入来源说明
                    if (StringUtils.isNotBlank(vo.getRentalIncomeRemark())) {
                        priceExplainBuilder.append(generateCommonMethod.trim(vo.getRentalIncomeRemark()));
                    }
                    priceExplainBuilder.append("即估价对象的比准租赁价格为");
                    priceExplainBuilder.append(vo.getRentalIncome().toString()).append("元/㎡月");
                }
                stringBuilder.append(priceExplainBuilder.toString());

                if(leaseVoList.size()>1){
                    linkedList.add(stringBuilder.toString());
                }else {
                    linkedList.add(singleKey);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(linkedList)) {
            return StringUtils.join(linkedList, String.join("",StringUtils.repeat(ControlChar.TAB,1),StringUtils.repeat(ControlChar.LINE_BREAK, 1))) ;
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法中的比较法
     *
     * @author: zch
     * @date: 2019/2/28 14:13
     */
    private synchronized String getIncomeGetMdCompare() throws Exception {
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        List<MdIncomeLeaseVo> leaseVoList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            for (MdIncomeLeaseVo vo : leaseVoList) {
                if (vo.getMcId() != null) {
                    String path = getMdComparePath(vo.getMcId());
                    if (StringUtils.isNotBlank(path)) {
                        document.appendDocument(new Document(path), ImportFormatMode.KEEP_SOURCE_FORMATTING);
                    }
                }
            }
        }
        builder.write("");
        String localPath = getLocalPath();
        document.save(localPath);
        return localPath;
    }

    private String getMdComparePath(Integer mcId) throws Exception {
        GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(getSchemeJudgeObject().getId(), mcId, areaId);
        String compareFile = generateMdCompareService.generateCompareFile();
        File file = new File(compareFile);
        if (file.isFile()) {
            return compareFile;
        }
        return null;
    }

    //收益法单价内涵
    private synchronized String getIncomeUnitPriceConnotation() throws Exception {
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
    private synchronized String getIncomeDetermineRentalWay() throws Exception {
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
        return val;
    }

    /**
     * 功能描述: 收益法公式
     *
     * @author: zch
     * @date: 2019/2/28 16:37
     */
    private synchronized String getIncomeMethodFormula() throws Exception {
        String s = "";
        BaseDataDic income = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
        if (income != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(income.getId());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                if (StringUtils.isNotBlank(dataMethodFormulaList.stream().findFirst().get().getFormula())) {
                    if (leaseVoList.size() == 1) {
                        s = dataMethodFormulaList.stream().findFirst().get().getFormula();
                    } else {
                        s = "V1+V2";
                    }
                }
            }
        }
        return s;
    }

    /**
     * 功能描述: 收益法土地终止日期
     *
     * @auther: zch
     * @date: 2019/2/27 17:44
     */
    private synchronized String getTerminationDateLand() throws Exception {
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
    private synchronized String getIncomeSetUse() {
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
    private synchronized String getPropertyRightCertificateIncomeLaw() throws Exception {
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
     * 功能描述: 收益法租赁限制说明
     *
     * @author: zch
     * @date: 2019/2/28 16:28
     */
    public synchronized String getTenancyrestrictionReamrk() throws Exception {
        return StringUtils.isNotBlank(getMdIncome().getRestrictionExplain()) ? getMdIncome().getRestrictionExplain() : "不考虑估价对象租赁因素影响";
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
    private synchronized String getMdIncomeLeaseCommon(ReportFieldMdIncomeEnum incomeEnum) {
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isEmpty(mdIncomeLeaseList)) {
            return errorStr;
        }
        String separator = "";
        int repeat = 0;
        final Map<Integer, String> map = Maps.newHashMap();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        mdIncomeLeaseList.forEach(mdIncomeLeaseVo -> {
            BigDecimal cost = null;
            String value = null;
            switch (incomeEnum) {
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
                        value = String.join("", mdIncomeLeaseVo.getDeposit().toString(), " * ", mdIncomeLeaseVo.getDepositRate().multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString(), "%", " = ", mdIncomeLeaseVo.getDeposit().multiply(mdIncomeLeaseVo.getDepositRate()).setScale(2, BigDecimal.ROUND_UP).toString(),"元/㎡");
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
                case RestrictionExplain:
                    value = mdIncomeLeaseVo.getRentalsRemark();
                    break;
                default:
                    break;
            }
            if (cost != null) {
                cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
                map.put(atomicInteger.get(), cost.toString());
                atomicInteger.incrementAndGet();
            }
            if (StringUtils.isNotBlank(value)) {
                map.put(atomicInteger.get(), value);
                atomicInteger.incrementAndGet();
            }
        });
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.IncomeDepositCost.getName())) {
            separator = "，";
        }
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.RestrictionExplain.getName())) {
            separator = String.format("%s%s",StringUtils.repeat(" ",2),ControlChar.LINE_BREAK);
            repeat = 3;
        }
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.IncomeAdditionalCaptureRemark.getName())) {
            separator = String.format("%s%s",StringUtils.repeat(" ",2),ControlChar.LINE_BREAK);
            repeat = 3;
        }
        return this.toEachDesc(map, "", "", separator,repeat);
    }


    /**
     * 获取各个种类金额 (其它)
     *
     * @param incomeEnum
     * @return
     */
    private synchronized String getMdIncomeLeaseCostOtherCommon(ReportFieldMdIncomeEnum incomeEnum) {
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        List<MdIncomeLeaseVo> mdIncomeLeaseVoList = getMdIncomeLeaseList();
        if (CollectionUtils.isEmpty(leaseVoList)) {
            return errorStr;
        }
        final Map<Integer, String> map = Maps.newHashMap();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        String separator = "";
        int repeat = 0;
        leaseVoList.forEach(mdIncomeLeaseCostVo -> {
            BigDecimal cost = null;
            String value = null;
            MdIncomeDateSection mdIncomeDateSection = null;
            switch (incomeEnum) {
                case ReplacementCost:
                    if (mdIncomeLeaseCostVo.getReplacementValue() != null) {
                        cost = mdIncomeLeaseCostVo.getReplacementValue();
                    }
                    break;
                case GrossIncomeValue:
                    mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                    if (mdIncomeDateSection.getIncomeTotal() != null) {
                        cost = mdIncomeDateSection.getIncomeTotal();
                    }
                    break;
                case GrossIncome: {
                    mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                    if (mdIncomeDateSection != null) {
                        if (mdIncomeDateSection.getIncomeTotal() != null) {
                            MdIncomeLeaseVo mdIncomeLeaseVo = mdIncomeLeaseVoList.stream().filter(mdIncomeLeaseVo1 -> Objects.equal(mdIncomeLeaseVo1.getSectionId(), mdIncomeLeaseCostVo.getSectionId())).findFirst().get();
                            StringBuilder stringBuilder = new StringBuilder(8);
                            stringBuilder.append(mdIncomeLeaseVo.getRentalIncome().toString());
                            stringBuilder.append(" * ");
                            stringBuilder.append(mdIncomeLeaseVo.getMonthNumber().toString());
                            stringBuilder.append(" * ");
                            stringBuilder.append(mdIncomeLeaseVo.getRentals().multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString()).append("%");
                            stringBuilder.append(" * ");
                            if (NumberUtils.isNumber(mdIncomeLeaseVo.getAdditionalCapture())) {
                                stringBuilder.append(new BigDecimal(mdIncomeLeaseVo.getAdditionalCapture()).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP)).append("%");
                            } else {
                                stringBuilder.append("0");
                            }
                            stringBuilder.append(" + ");
                            stringBuilder.append(mdIncomeLeaseVo.getOtherIncome().toString());
                            stringBuilder.append(" = ");
                            stringBuilder.append(mdIncomeDateSection.getIncomeTotal());
                            stringBuilder.append("元/㎡");
                            value = stringBuilder.toString();
                        }
                    }
                }
                break;
                case AnnualOperatingCost: {
                    mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                    MdIncomeLeaseVo mdIncomeLeaseVo = mdIncomeLeaseVoList.stream().filter(mdIncomeLeaseVo1 -> Objects.equal(mdIncomeLeaseVo1.getSectionId(), mdIncomeLeaseCostVo.getSectionId())).findFirst().get();
                    StringBuilder stringBuilder = new StringBuilder(8);
                    //年维修费
                    if (mdIncomeLeaseCostVo.getReplacementValue() != null && mdIncomeLeaseCostVo.getMaintenanceCostRatio() != null) {
                        stringBuilder.append(mdIncomeLeaseCostVo.getReplacementValue().multiply(mdIncomeLeaseCostVo.getMaintenanceCostRatio()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    } else {
                        stringBuilder.append("0");
                    }
                    stringBuilder.append(" + ");
                    //年管理费
                    if (mdIncomeDateSection.getIncomeTotal() != null && mdIncomeLeaseCostVo.getManagementCostRatio() != null) {
                        stringBuilder.append(mdIncomeDateSection.getIncomeTotal().multiply(mdIncomeLeaseCostVo.getManagementCostRatio()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    } else {
                        stringBuilder.append("0");
                    }
                    stringBuilder.append(" + ");
                    //年保险费
                    if (mdIncomeLeaseCostVo.getReplacementValue() != null && mdIncomeLeaseCostVo.getInsurancePremiumRatio() != null) {
                        stringBuilder.append(mdIncomeLeaseCostVo.getReplacementValue().multiply(mdIncomeLeaseCostVo.getInsurancePremiumRatio()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    } else {
                        stringBuilder.append("0");
                    }
                    stringBuilder.append(" + ");
                    //租赁税费
                    if (mdIncomeDateSection.getIncomeTotal() != null && mdIncomeLeaseCostVo.getAdditionalRatio() != null) {
                        stringBuilder.append(mdIncomeDateSection.getIncomeTotal().multiply(mdIncomeLeaseCostVo.getAdditionalRatio()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    } else {
                        stringBuilder.append("0");
                    }
                    //stringBuilder.append(" + ");
                    //土地使用税
                    //stringBuilder.append(mdIncomeLeaseCostVo.getLandUseTax());
                    //其它相关费用
//                    stringBuilder.append(" + ");
//                    if (mdIncomeLeaseVo.getRentalIncome() != null && mdIncomeLeaseVo.getMonthNumber() != null && mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio() != null) {
//                        stringBuilder.append(mdIncomeLeaseVo.getRentalIncome().multiply(new BigDecimal(mdIncomeLeaseVo.getMonthNumber())).multiply(mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio()).setScale(2, BigDecimal.ROUND_DOWN).toString());
//                    }
                    stringBuilder.append(" = ");
                    stringBuilder.append(mdIncomeDateSection.getCostTotal().toString());
                    stringBuilder.append("元/㎡");
                    value = stringBuilder.toString();
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
                case AnnualNetIncome: {
                    mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                    StringBuilder stringBuilder = new StringBuilder(8);
                    stringBuilder.append(mdIncomeDateSection.getIncomeTotal());
                    stringBuilder.append(" - ");
                    stringBuilder.append(mdIncomeDateSection.getCostTotal());
                    stringBuilder.append(" = ").append(mdIncomeDateSection.getNetProfit());
                    stringBuilder.append("元/㎡");
                    value = stringBuilder.toString();
                }
                break;
                case TransactionTaxeFeeExplain:
                    value = mdIncomeLeaseCostVo.getTransactionTaxeFeeExplain();
                    if (value.endsWith("，") || value.endsWith("。"))
                        value += mdIncomeLeaseCostVo.getTransactionTaxeFeeExplainSupplement();
                    else
                        value += "，" + mdIncomeLeaseCostVo.getTransactionTaxeFeeExplainSupplement();
                    break;
                case RentalGrowthRateExplain:
                    mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                    if (mdIncomeDateSection != null) {
                        value = mdIncomeDateSection.getRentalGrowthRateExplain();
                        if (mdIncomeDateSection.getRentalGrowthRate().doubleValue() > 0 && StringUtils.isNotBlank(mdIncomeDateSection.getRentalGrowthRateExplainSupplement())) {
                            if (value.endsWith("，") || value.endsWith("。"))
                                value += mdIncomeDateSection.getRentalGrowthRateExplainSupplement();
                            else
                                value += "，" + mdIncomeDateSection.getRentalGrowthRateExplainSupplement();
                        }
                        if(mdIncomeDateSection.getRentalGrowthRate()!=null){
                            String s = ArithmeticUtils.getPercentileSystem(mdIncomeDateSection.getRentalGrowthRate(), 4, BigDecimal.ROUND_HALF_UP);
                            value += String.format("本次结合估价对象的实际情况，取收益年递增率为%s。",s );
                        }
                        if (atomicInteger.get() != 0){
                            value = String.join("",StringUtils.repeat(" ",2),value);
                        }
                    }
                    break;
                default:
                    break;
            }
            if (cost != null) {
                cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
                map.put(atomicInteger.get(), cost.toString());
                atomicInteger.incrementAndGet();
            }
            if (StringUtils.isNotBlank(value)) {
                map.put(atomicInteger.get(), value);
                atomicInteger.incrementAndGet();
            }
        });
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.AnnualOperatingCost.getName())) {
            separator = ControlChar.LINE_BREAK;
        }
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.GrossIncome.getName())) {
            separator = ControlChar.LINE_BREAK;
        }
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.AnnualNetIncome.getName())) {
            separator = ControlChar.LINE_BREAK;
        }
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.RentalGrowthRateExplain.getName())) {
            separator = String.format("%s%s",StringUtils.repeat(" ",2),ControlChar.LINE_BREAK);
            repeat = 4;
        }
        if (Objects.equal(incomeEnum.getName(), ReportFieldMdIncomeEnum.TransactionTaxeFeeExplain.getName())) {
            separator = String.format("%s%s",StringUtils.repeat(" ",2),ControlChar.LINE_BREAK);
            repeat = 0;
        }
        String value = this.toEachDesc(map, "", "", separator,repeat) ;
        return value;
    }


    /**
     * 获取简单计算公式
     *
     * @param incomeEnum
     * @return
     */
    private synchronized String getSimpleFormula(ReportFieldMdIncomeEnum incomeEnum, ReportFieldMdIncomeEnum incomeEnumTax) throws Exception{
        String moneyStr = getMdIncomeLeaseCostOtherCommon(incomeEnum);
        String taxStr = getMdIncomeLeaseCostCommonTax(incomeEnumTax);
        BigDecimal money = new BigDecimal("0");
        BigDecimal tax = new BigDecimal("0");
        if(StringUtils.isNotEmpty(moneyStr)){
            money = new BigDecimal(moneyStr);
        }
        if(StringUtils.isNotEmpty(taxStr)){
            tax = new BigDecimal(ArithmeticUtils.parseFormatString(taxStr));
        }
        String value = money.multiply(tax).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        return String.format("%s * %s = %s元/㎡",money,ArithmeticUtils.getPercentileSystem(tax, 4, BigDecimal.ROUND_HALF_UP),value);
    }


    /**
     * 获取各个税收金额
     *
     * @param incomeEnum
     * @return
     */
    private synchronized String getMdIncomeLeaseCostCommon(ReportFieldMdIncomeEnum incomeEnum) {
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isEmpty(leaseVoList)) {
            return errorStr;
        }
        final Map<Integer, String> map = Maps.newHashMap();
        final AtomicInteger value = new AtomicInteger(0);
        int repeat = 0;
        leaseVoList.forEach(mdIncomeLeaseCostVo -> {
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
                    map.put(value.get(), multiply.toString());
                    value.incrementAndGet();
                }
            }
        });
        return this.toEachDesc(map, "", "", "",repeat);
    }

    /**
     * 获取各类税率
     *
     * @param incomeEnum
     * @return
     */
    private synchronized String getMdIncomeLeaseCostCommonTax(ReportFieldMdIncomeEnum incomeEnum) {
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isEmpty(leaseVoList)) {
            return errorStr;
        }
        final Map<Integer, String> map = Maps.newHashMap();
        int repeat = 0;
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        leaseVoList.forEach(mdIncomeLeaseCostVo -> {
            BigDecimal decimal = null;
            switch (incomeEnum) {
                case IncomesalesTaxRatio:
                    if (mdIncomeLeaseCostVo.getSalesTaxRatio() != null) {
                        decimal = mdIncomeLeaseCostVo.getSalesTaxRatio();
                    }
                    break;
                case IncomesalesTaxRatioCorrect:
                    if (mdIncomeLeaseCostVo.getSalesTaxRatio() != null) {
                        double div = ArithmeticUtils.div( mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue(),new BigDecimal(1 + mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue()).doubleValue(),4) ;
                        decimal = new BigDecimal(div) ;
                    }
                    break;
                case IncomePropertyTax:
                    if (mdIncomeLeaseCostVo.getPropertyTaxRatio() != null) {
                        decimal = mdIncomeLeaseCostVo.getPropertyTaxRatio();
                    }
                    break;
                case IncomePropertyTaxCorrect:
                    if (mdIncomeLeaseCostVo.getSalesTaxRatio() != null && mdIncomeLeaseCostVo.getPropertyTaxRatio() != null) {
                        BigDecimal div = mdIncomeLeaseCostVo.getPropertyTaxRatio().divide(new BigDecimal(1+mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue()), RoundingMode.HALF_UP) ;
                        decimal = new BigDecimal(div.toString());
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
                    double[] doubles = new double[]{mdIncomeLeaseCostVo.getConstructionTaxRatio().doubleValue(),mdIncomeLeaseCostVo.getEducationRatio().doubleValue(),mdIncomeLeaseCostVo.getLocalEducationRatio().doubleValue()} ;
                    double start = ArithmeticUtils.add(doubles) ;
                    double end = ArithmeticUtils.div(mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue(),new BigDecimal(1 + mdIncomeLeaseCostVo.getSalesTaxRatio().doubleValue()).doubleValue());
                    decimal = new BigDecimal(ArithmeticUtils.mul(start,end)).setScale(4,BigDecimal.ROUND_HALF_UP);
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
                map.put(atomicInteger.get(), ArithmeticUtils.getPercentileSystem(decimal,4,BigDecimal.ROUND_HALF_UP));
                atomicInteger.incrementAndGet();
            }
        });
        return this.toEachDesc(map, "", "", "",repeat);
    }

    /**
     * 报酬率测算 公共方法
     *
     * @param incomeEnum
     * @return
     */
    public String getToolRewardRateIncomeCommon(ReportFieldMdIncomeEnum incomeEnum) throws Exception {
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
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double d = Double.parseDouble(text);
                        d = d * 100;
                        value = String.format("%s%s", Double.toString(d), "%");
                    }
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
                double a = 0;
                if (jsonObject != null) {
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double d = Double.parseDouble(text);
                        a += d * 100;
                    }
                }
                jsonObject = getToolRewardRateHelp(jsonArray, taxDeductionAdvantage);
                if (jsonObject != null) {
                    String text = jsonObject.getString(ratio);
                    if (NumberUtils.isNumber(text)) {
                        double c = Double.parseDouble(text);
                        a += c * 100;
                    }
                }
                value = String.format("%s%s", Double.toString(a), "%");
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
    private synchronized String getIncomePrice() {
        BigDecimal price = getMdIncome().getPrice();
        if (price != null) {
            return price.toString();
        }
        return errorStr;
    }

    /**
     * 收益法测算公式带值
     *
     * @return
     * @throws Exception
     */
    public String getIncomeCalculateFormulaValue() throws Exception {
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        //排序
        Comparator<MdIncomeDateSection> comparator = new Comparator<MdIncomeDateSection>() {
            @Override
            public int compare(MdIncomeDateSection o1, MdIncomeDateSection o2) {
                return o1.getBeginDate().compareTo(o2.getBeginDate());
            }
        };
        Collections.sort(mdIncomeDateSectionList, comparator);

        StringBuilder s = new StringBuilder();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            BigDecimal t = new BigDecimal("0");
            for (MdIncomeDateSection section : mdIncomeDateSectionList) {
                StringBuilder s2 = new StringBuilder();
                s2.append(DateUtils.format(section.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(section.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                s.append(s2.toString());
                BigDecimal yearCount = section.getYearCount();
                t = t.add(yearCount);
                String formula = new String();
                if (yearCount.compareTo(new BigDecimal("1")) < 1) {
                    formula = ":At×(1 + g)/(1 + Y)^t]";
                } else {
                    formula = ":At×(1+Y)^-(T+1)×(1-((1+g)/(1+Y))^n)/(1-(1+g)/(1+Y))";
                }

                if (StringUtils.isNoneEmpty(section.getNetProfit())) {
                    formula = formula.replace("At", section.getNetProfit());
                }
                if (mdIncome.getRewardRate() != null) {
                    formula = formula.replace("Y", ArithmeticUtils.getPercentileSystem(mdIncome.getRewardRate(), 4, BigDecimal.ROUND_HALF_UP));
                }
                if (section.getRentalGrowthRate() != null) {
                    formula = formula.replace("g", ArithmeticUtils.getPercentileSystem(section.getRentalGrowthRate(), 4, BigDecimal.ROUND_HALF_UP));
                }
                if (t.compareTo(new BigDecimal("1")) != 0) {
                    formula = formula.replace("t", t.toString());
                    if (formula.contains("T")) {
                        formula = formula.replace("T", t.subtract(yearCount).toString());
                    }
                } else {
                    formula = formula.replace("^t", "");
                }
                if (section.getYearCount() != null) {
                    formula = formula.replace("n", section.getYearCount().toString());
                }
                s.append(formula);
                s.append(";");
            }
            s.deleteCharAt(s.length() - 1).append("。");
        }
        return s.toString();
    }



    private List<MdIncomeDateSection> getMdIncomeDateSectionList() {
        if (CollectionUtils.isEmpty(this.mdIncomeDateSectionList)) {
            MdIncomeDateSection query = new MdIncomeDateSection();
            query.setIncomeId(miId);
            this.mdIncomeDateSectionList = mdIncomeDateSectionService.getMdIncomeDateSectionList(query);
        }
        return mdIncomeDateSectionList;
    }

    private List<MdIncomeLeaseVo> getMdIncomeLeaseList() {
        if (CollectionUtils.isEmpty(mdIncomeLeaseList)) {
            MdIncomeLease query = new MdIncomeLease();
            query.setIncomeId(miId);
            List<MdIncomeLease> incomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query);
            this.mdIncomeLeaseList = incomeLeaseList.stream().map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
        }
        return this.mdIncomeLeaseList;
    }

    private List<MdIncomeLeaseCostVo> getLeaseVoList() {
        if (CollectionUtils.isEmpty(this.leaseVoList)) {
            MdIncomeLeaseCost query = new MdIncomeLeaseCost();
            query.setIncomeId(miId);
            List<MdIncomeLeaseCost> leaseCostList = mdIncomeLeaseCostDao.getLeaseCostList(query);
            this.leaseVoList = leaseCostList.stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
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
            this.schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
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
        this.baseService = SpringContextUtils.getBean(BaseService.class);
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

    /**
     * 拼接map元素
     *
     * @param map
     * @param explain
     * @param symbol
     * @param separator 分隔符
     * @return
     */
    private String toEachDesc(final Map<Integer, String> map,final String explain,final String symbol,final String separator,final int repeat) {
        if (map == null || map.size() <= 0) {
            return "";
        }
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        stringSet.addAll(map.values());
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final StringBuilder stringBuilder = new StringBuilder(8) ;
        if (map.values().size() == 1 || stringSet.size() == 1) {
            return stringSet.stream().findFirst().get();
        } else {
            stringSet.clear();
            map.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, String>>() {
                @Override
                public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }).forEach(integerStringEntry -> {
                if (atomicInteger.get() != 0){
                    stringBuilder.append(StringUtils.repeat(" ",repeat)) ;
                }
                stringBuilder.append("第") ;
                stringBuilder.append(getChineseNumber(integerStringEntry.getKey())) ;
                stringBuilder.append("段") ;
                stringBuilder.append(StringUtils.isNotBlank(explain) ? explain : "") ;
                stringBuilder.append(integerStringEntry.getValue()) ;
                stringBuilder.append(StringUtils.isNotBlank(symbol) ? symbol : "") ;
                stringSet.add(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.toString().length()) ;
                atomicInteger.incrementAndGet();
            });
            return StringUtils.join(stringSet, separator);
        }
    }

    private String getChineseNumber(Integer number) {
        int len = chineseNumbers.size();
        if (number < len) {
            return chineseNumbers.get(number);
        } else {
            return String.valueOf(number.intValue());
        }
    }

    private GenerateMdIncomeService() {
    }

    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }
}

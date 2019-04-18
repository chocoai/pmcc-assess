package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldMdIncomeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.common.enums.CalculationMethodNameEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.data.DataTaxRateAllocationVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseCostVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.*;
import com.copower.pmcc.assess.service.method.MdIncomeDateSectionService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
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
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/2/12 16:43
 * @description:收益法报告字段处理
 */
public class GenerateMdIncomeService {
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
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private SurveyCommonService surveyCommonService;
    private DataBuildingNewRateService dataBuildingNewRateService;
    private EvaluationMethodService evaluationMethodService;
    private SchemeInfoService schemeInfoService;
    private DataTaxRateAllocationService dataTaxRateAllocationService;
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
            //具体组装---------

            try {
                //租金增值预测
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RentGrowthForecast.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRentGrowthForecast());
                }
                //出租率说明 / 收益法出租率说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RestrictionExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRestrictionExplain());
                }
                //月租金
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MonthRentalIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMonthRentalIncome());
                }
                //月份数
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MonthNumber.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMonthNumber());
                }
                //有效出租率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.Rentals.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRentals());
                }

                //其它收入内容/其它收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncomeContents.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getOtherIncome());
                }
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getOtherIncome());
                }
                //收益法其他收入说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncomeExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getOtherIncomeExplain());
                }

                //年押金收入/年押金
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.YearDeposit.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getYearDeposit());
                }
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualDepositIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getYearDeposit());
                }
                //收益法押金说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeDepositExplain.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeDepositExplain());
                }

                //一年期定期存款利率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.YearDepositRate.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getYearDepositRate());
                }
                //年押金利息收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RentInterestIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRentInterestIncome());
                }
                //有效收入公式
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.EffectiveIncomeFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getEffectiveIncomeFormula());
                }

                //总收入/毛收入
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.GrossIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeTotal());
                }
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeTotal.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeTotal());
                }

                //管理费用说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ManagemenRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getManagemenRemark());
                }

                //重置成本/重置价格
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ReplacementCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getReplacementValue());
                }

                //维修保养费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MaintenanceCostRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMaintenanceCostRatio());
                }
                //收益法土地使用税
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.LandUseTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getLandUseTax());
                }
                //收益法土地使用税费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.LandTaxFees.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getLandTaxFees());
                }
                //收益法管理费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ManagementCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getManagementCost());
                }

                //收益法租赁税费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAdditionalRatio.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeAdditionalRatio());
                }

                //保险费/年保费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.InsurancePremium.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getInsurancePremium());
                }
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.InsurancePremium1.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getInsurancePremium());
                }

                //年运营费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualOperatingCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getAnnualOperatingCost());
                }
                //年净收益
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualNetIncome.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getAnnualNetIncome());
                }
                //单价
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomePrice());
                }

                //报酬率测算表
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RemunerationRateSheet.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getRemunerationRateSheet());
                }
                //收益法价格测算表
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeMethodPriceCalculatingSheet.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeMethodPriceCalculating());
                }

                //收益法租赁限制说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.TenancyrestrictionRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTenancyrestrictionReamrk());
                }
                //委托资料
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.EntrustedInformation.getName())) {
                    generateCommonMethod.putValue(false, false, false, textMap, bookmarkMap, fileMap, name, null);
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
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomePropertyTax());
                }
                //收益法印花税
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomestampTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomestampTax());
                }
                //收益法交易费率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeTransactionTax.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeTransactionTax());
                }
                //收益法合计税费
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeAggregateTaxesFees.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeAggregateTaxesFees());
                }
                //收益法公式
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeMethodFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeMethodFormula());
                }
                //收益法报酬率
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePayBack.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomePayBack());
                }
                //收益法机会成本说明
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeOpportunityCostReamrk.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeOpportunityCostReamrk());
                }
                //收益法投资风险补偿
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeRiskCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeRiskCompensation());
                }
                //收益法管理负担补偿
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeManageCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeManageCompensation());
                }
                //收益法缺乏流动性补偿
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeLiquidCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeLiquidCompensation());
                }
                //收益法投资带来的优惠
                if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeFinancingAdvantage.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeFinancingAdvantage());
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
     * @param:
     * @return:
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
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 14:31
     */
    public String getIncomeAssessmentArea() throws Exception {
        java.math.BigDecimal bigDecimal = getSchemeJudgeObject().getEvaluationArea();
        if (bigDecimal != null) {
            return String.format("%s%s", bigDecimal.toString(), "㎡");
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法建筑结构类别
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 18:18
     */
    public String getIncomebuildingStructureType() throws Exception {
        if (getSchemeInfo().getPlanDetailsId() != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(getSchemeInfo().getPlanDetailsId());
            if (generateBaseExamineService.getBasicApply() != null) {
                if (generateBaseExamineService.getBasicBuilding() != null) {
                    if (generateBaseExamineService.getBasicBuilding().getBuildingStructureType() != null) {
                        return baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureType());
                    }
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法竣工时间
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 18:10
     */
    public String getIncomeCompletionTime() throws Exception {
        if (getSchemeInfo().getPlanDetailsId() != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(getSchemeInfo().getPlanDetailsId());
            if (generateBaseExamineService.getBasicApply() != null) {
                if (generateBaseExamineService.getBasicBuilding() != null) {
                    if (generateBaseExamineService.getBasicBuilding().getBeCompletedTime() != null) {
                        return DateUtils.format(generateBaseExamineService.getBasicBuilding().getBeCompletedTime(), DateUtils.DATE_CHINESE_PATTERN);
                    }
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法已使用年限
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 10:34
     */
    public String getIncomeUsedLife() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        if (getSchemeInfo().getPlanDetailsId() != null && schemeAreaGroup.getValueTimePoint() != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(getSchemeInfo().getPlanDetailsId());
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
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 13:41
     */
    public String getIncomeBuildEconomicLife() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        if (getSchemeInfo().getPlanDetailsId() != null && schemeAreaGroup.getValueTimePoint() != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(getSchemeInfo().getPlanDetailsId());
            if (generateBaseExamineService.getBasicApply() != null) {
                if (generateBaseExamineService.getBasicBuilding() != null) {
                    if (generateBaseExamineService.getBasicBuilding().getResidenceUseYear() != null) {
                        try {//可能会有值但是找不到数据实体(后期更改配置数据的情况)允许异常
                            return dataBuildingNewRateService.getByiDdataBuildingNewRate(generateBaseExamineService.getBasicBuilding().getResidenceUseYear()).getDurableLife().toString();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法价值时点
     *
     * @param:
     * @return:
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
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 18:05
     */
    public String getIncomeSurplusLandUseYear() throws Exception {
        String s = null;
        if (getMdIncome().getLandRemainingYear() != null) {
            s = getMdIncome().getLandRemainingYear().toString();
        }
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法房产剩余年限
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 13:54
     */
    public String getIncomeHouseSurplusYear() throws Exception {
        String s = null;
        if (getMdIncome().getHouseRemainingYear() != null) {
            s = getMdIncome().getHouseRemainingYear().toString();
        }
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法收益年限
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 14:01
     */
    public String getIncomeYears() throws Exception {
        try {
            BigDecimal houseSurplusYear = getMdIncome().getHouseRemainingYear();
            BigDecimal landSurplusYear = getMdIncome().getLandRemainingYear();
            BigDecimal compare = houseSurplusYear.max(landSurplusYear);
            return compare.toString();
        } catch (Exception e) {
            return errorStr;
        }
    }

    /**
     * 功能描述: 收益法中的比较法
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 14:13
     */
    public String getIncomeGetMdCompare() throws Exception {
        DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(oo -> {
            if (oo.getName().equals(CalculationMethodNameEnum.MdCompare.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        if (dataEvaluationMethod != null) {
            SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(getSchemeJudgeObject().getId(), dataEvaluationMethod.getMethod());
            if (schemeInfo != null) {
                if (schemeInfo.getMethodDataId() != null) {
                    try {
                        GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(getSchemeJudgeObject().getId(),schemeInfo.getMethodDataId(), new Date(), areaId);
                        String temp = generateMdCompareService.generateCompareFile();
                        File file = new File(temp);
                        if (file.isFile()) {
                            return temp;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return null;
    }

    /**
     * 功能描述: 收益法公式
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 16:37
     */
    public String getIncomeMethodFormula() throws Exception {
        DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
            if (Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), dataEvaluation.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        if (dataEvaluationMethod != null) {
            List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethod.getMethod());
            if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                if (StringUtils.isNotBlank(dataMethodFormulaList.get(0).getFormula())) {
                    return dataMethodFormulaList.get(0).getFormula();
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法土地终止日期
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 17:44
     */
    public String getTerminationDateLand() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (schemeJudgeObject.getDeclareRecordId() != null) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                    if (declareRealtyLandCert != null && declareRealtyLandCert.getTerminationDate() != null) {
                        return DateUtils.format(declareRealtyLandCert.getTerminationDate(), DateUtils.DATE_CHINESE_PATTERN);
                    }
                }
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法设定用途
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/27 17:35
     */
    public String getIncomeSetUse() throws Exception {
        SchemeJudgeObject schemeJudgeObject = getSchemeJudgeObject();
        if (schemeJudgeObject.getSetUse() != null) {
            DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
            if (dataSetUseField != null) {
                return dataSetUseField.getName();
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法申报产权证类型
     *
     * @param:
     * @return:
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
     * 租金增值预测 收益法租金增长率
     *
     * @return
     * @throws Exception
     */
    public String getRentGrowthForecast() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            mdIncomeDateSectionList.stream().filter(mdIncomeDateSection -> mdIncomeDateSection.getIncomeId() != null).forEach(mdIncomeDateSection -> {
                if (mdIncomeDateSection.getBeginDate() != null && mdIncomeDateSection.getEndDate() != null && mdIncomeDateSection.getRentalGrowthRate() != null) {
                    this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeDateSection.getRentalGrowthRate()), mdIncomeDateSection.getBeginDate(), mdIncomeDateSection.getEndDate());

                }
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 出租率说明
     *
     * @return
     * @throws Exception
     */
    public String getRestrictionExplain() throws Exception {
        String s = getMdIncome().getRestrictionExplain();
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 月租金
     *
     * @return
     */
    public String getMonthRentalIncome() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getRentalIncome() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getRentalIncome().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 月份数
     *
     * @return
     */
    public String getMonthNumber() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getMonthNumber() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getMonthNumber().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 有效出租率
     *
     * @return
     */
    public String getRentals() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getRentals() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getRentals().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 其它收入
     *
     * @return
     */
    public String getOtherIncome() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getOtherIncome() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getOtherIncome().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法其他收入说明
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 14:38
     */
    public String getOtherIncomeExplain() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getOtherIncome() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getOtherIncomeRemark().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 租金收入
     *
     * @return
     */
    public String getRentalIncome() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getRentalIncome() == null) {
                    return false;
                }
                if (mdIncomeLease.getRentals() == null) {
                    return false;
                }
                if (mdIncomeLease.getMonthNumber() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                BigDecimal a = mdIncomeLease.getRentals().multiply(mdIncomeLease.getRentalIncome());
                BigDecimal b = a.multiply(new BigDecimal(mdIncomeLease.getMonthNumber()));
                this.appendElement(builder, b.toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 年押金
     *
     * @return
     */
    public String getYearDeposit() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getDeposit() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getDeposit().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法押金说明
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 14:44
     */
    public String getIncomeDepositExplain() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getDeposit() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getDepositRemark().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法租赁限制说明
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 16:28
     */
    public String getTenancyrestrictionReamrk() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                if (StringUtils.isNotBlank(mdIncomeLeaseCostVo.getAdditional())) {
                    this.appendElement(builder, mdIncomeLeaseCostVo.getAdditional(), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
                }
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 一年期定期存款利率
     *
     * @return
     */
    public String getYearDepositRate() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getDepositRate() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                this.appendElement(builder, mdIncomeLease.getDepositRate().toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 年押金利息收入
     *
     * @return
     */
    public String getRentInterestIncome() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = getMdIncomeLeaseList();
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseList)) {
            mdIncomeLeaseList.stream().filter(mdIncomeLease -> {
                if (mdIncomeLease.getBeginDate() == null || mdIncomeLease.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLease.getDepositRate() == null || mdIncomeLease.getDeposit() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLease -> {
                BigDecimal temp = mdIncomeLease.getDepositRate().multiply(mdIncomeLease.getDeposit());
                this.appendElement(builder, temp.toString(), mdIncomeLease.getBeginDate(), mdIncomeLease.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
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
     * 总收入/毛收入
     *
     * @return
     */
    public String getIncomeTotal() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            mdIncomeDateSectionList.stream().filter(mdIncomeDateSection -> mdIncomeDateSection.getIncomeId() != null).forEach(mdIncomeDateSection -> {
                if (mdIncomeDateSection.getBeginDate() != null && mdIncomeDateSection.getEndDate() != null && mdIncomeDateSection.getRentalGrowthRate() != null) {
                    this.appendElement(builder, mdIncomeDateSection.getIncomeTotal().toString(), mdIncomeDateSection.getBeginDate(), mdIncomeDateSection.getEndDate());
                }
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }


    /**
     * 管理费用说明
     *
     * @return
     */
    public String getManagemenRemark() {
        return "管理费用说明:暂无";
    }

    /**
     * 重置价格
     *
     * @return
     */
    public String getReplacementValue() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getReplacementValue() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, mdIncomeLeaseCostVo.getReplacementValue().toString(), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 维修保养费率
     *
     * @return
     */
    public String getMaintenanceCostRatio() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getMaintenanceCostRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getMaintenanceCostRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 维修保养费
     *
     * @return
     */
    public String getMaintenance() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, mdIncomeLeaseCostVo.getMaintenance().toString(), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 土地使用税
     *
     * @return
     */
    public String getLandUseTax() {
        StringBuilder builder = new StringBuilder(24);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getLandUseTax() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, mdIncomeLeaseCostVo.getLandUseTax().toString(), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法交易费率
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 15:55
     */
    public String getIncomeTransactionTax() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getTransactionTaxeFeeRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法房产税
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 15:31
     */
    public String getIncomePropertyTax() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLeaseCostVo.getPropertyTaxRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getPropertyTaxRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 印花税
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 15:38
     */
    public String getIncomestampTax() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLeaseCostVo.getStampDutyRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getStampDutyRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }


    /**
     * 功能描述: 收益法合计税费
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 16:01
     */
    public String getIncomeAggregateTaxesFees() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                //租赁税费比率
                if (mdIncomeLeaseCostVo.getAdditionalRatio() == null) {
                    return false;
                }
                //印花税
                if (mdIncomeLeaseCostVo.getStampDutyRatio() == null) {
                    return false;
                }
                //房产税
                if (mdIncomeLeaseCostVo.getPropertyTaxRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                BigDecimal bigDecimal = mdIncomeLeaseCostVo.getAdditionalRatio().add(mdIncomeLeaseCostVo.getStampDutyRatio()).add(mdIncomeLeaseCostVo.getPropertyTaxRatio());
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(bigDecimal), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /***
     * 土地使用税费
     * @return
     */
    public String getLandTaxFees() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLeaseCostVo.getLandUseTax() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                if (mdIncomeDateSection != null) {
                    if (mdIncomeDateSection.getIncomeTotal() != null) {
                        BigDecimal temp = mdIncomeLeaseCostVo.getLandUseTax().multiply(mdIncomeDateSection.getIncomeTotal());
                        this.appendElement(builder, temp.toString(), mdIncomeDateSection.getBeginDate(), mdIncomeDateSection.getEndDate());
                    }
                }
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 年运营费
     *
     * @return
     */
    public String getAnnualOperatingCost() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeDateSection> dateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(dateSectionList)) {
            dateSectionList.stream().filter(mdIncomeDateSection -> {
                if (mdIncomeDateSection.getBeginDate() == null || mdIncomeDateSection.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeDateSection.getCostTotal() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeDateSection -> {
                this.appendElement(builder, mdIncomeDateSection.getCostTotal().toString(), mdIncomeDateSection.getBeginDate(), mdIncomeDateSection.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 年净收益
     *
     * @return
     */
    public String getAnnualNetIncome() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeDateSection> dateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(dateSectionList)) {
            dateSectionList.stream().filter(mdIncomeDateSection -> {
                if (mdIncomeDateSection.getBeginDate() == null || mdIncomeDateSection.getEndDate() == null) {
                    return false;
                }
                if (StringUtils.isEmpty(mdIncomeDateSection.getNetProfit())) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeDateSection -> {
                this.appendElement(builder, mdIncomeDateSection.getNetProfit(), mdIncomeDateSection.getBeginDate(), mdIncomeDateSection.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 保险费/年报费 收益法保险费率
     *
     * @return
     */
    public String getInsurancePremium() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getInsurancePremiumRatio() != null) {
                    this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getInsurancePremiumRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
                }
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 管理费率
     *
     * @return
     */
    public String getManagementCost() {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLeaseCostVo.getManagementCostRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getManagementCostRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法租赁税率
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 15:24
     */
    public String getIncomeAdditionalRatio() throws Exception {
        StringBuilder builder = new StringBuilder(16);
        List<MdIncomeLeaseCostVo> leaseVoList = getLeaseVoList();
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                if (mdIncomeLeaseCostVo.getAdditionalRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                this.appendElement(builder, generateCommonMethod.getPercentileSystem(mdIncomeLeaseCostVo.getAdditionalRatio()), mdIncomeLeaseCostVo.getBeginDate(), mdIncomeLeaseCostVo.getEndDate());
            });
        }
        if (StringUtils.isEmpty(builder.toString().trim())) {
            builder.append(errorStr);
        }
        return builder.toString();
    }

    /**
     * 功能描述: 收益法报酬率
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 16:56
     */
    public String getIncomePayBack() throws Exception {
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate != null) {
            if (StringUtils.isNotBlank(toolRewardRate.getResultValue())) {
                return toolRewardRate.getResultValue();
            }
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法机会成本说明
     *
     * @param:
     * @return:
     * @author: zch
     * @date: 2019/2/28 17:09
     */
    public String getIncomeOpportunityCostReamrk() throws Exception {
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getParameterValue())) {
            try {
                JSONArray jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    Object object = jsonArray.stream().filter(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            if (jsonObject.size() > 0) {
                                String text = jsonObject.getString(name);
                                if (Objects.equal(text, opportunityCost)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).findFirst().get();
                    if (object != null) {
                        JSONObject jsonObject = (JSONObject) object;
                        String value = jsonObject.getString(remark);
                        if (StringUtils.isNotBlank(value.trim())) {
                            return value;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return errorStr;
    }

    //收益法投资风险补偿
    public String getIncomeRiskCompensation() throws Exception {
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getParameterValue())) {
            try {
                JSONArray jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    Object object = jsonArray.stream().filter(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            if (jsonObject.size() > 0) {
                                String text = jsonObject.getString(name);
                                if (Objects.equal(text, riskCompensation)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).findFirst().get();
                    if (object != null) {
                        JSONObject jsonObject = (JSONObject) object;
                        String text = jsonObject.getString(ratio);
                        if (NumberUtils.isNumber(text)) {
                            double d = Double.parseDouble(text);
                            d = d * 100;
                            return String.format("%s%s", Double.toString(d), "%");
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return errorStr;
    }

    //收益法管理负担补偿
    public String getIncomeManageCompensation() throws Exception {
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getParameterValue())) {
            try {
                JSONArray jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    Object object = jsonArray.stream().filter(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            if (jsonObject.size() > 0) {
                                String text = jsonObject.getString(name);
                                if (Objects.equal(text, manageCompensation)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).findFirst().get();
                    if (object != null) {
                        JSONObject jsonObject = (JSONObject) object;
                        String text = jsonObject.getString(ratio);
                        if (NumberUtils.isNumber(text)) {
                            double d = Double.parseDouble(text);
                            d = d * 100;
                            return String.format("%s%s", Double.toString(d), "%");
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return errorStr;
    }

    //收益法缺乏流动性补偿
    public String getIncomeLiquidCompensation() throws Exception {
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getParameterValue())) {
            try {
                JSONArray jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    Object object = jsonArray.stream().filter(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            if (jsonObject.size() > 0) {
                                String text = jsonObject.getString(name);
                                if (Objects.equal(text, liquidCompensation)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).findFirst().get();
                    if (object != null) {
                        JSONObject jsonObject = (JSONObject) object;
                        String text = jsonObject.getString(ratio);
                        if (NumberUtils.isNumber(text)) {
                            double d = Double.parseDouble(text);
                            d = d * 100;
                            return String.format("%s%s", Double.toString(d), "%");
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return errorStr;
    }

    //收益法投资带来的优惠
    public String getIncomeFinancingAdvantage() throws Exception {
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getParameterValue())) {
            try {
                JSONArray jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    Object object = jsonArray.stream().filter(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            if (jsonObject.size() > 0) {
                                String text = jsonObject.getString(name);
                                if (Objects.equal(text, financingAdvantage)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).findFirst().get();
                    if (object != null) {
                        JSONObject jsonObject = (JSONObject) object;
                        String text = jsonObject.getString(ratio);
                        if (NumberUtils.isNumber(text)) {
                            double d = Double.parseDouble(text);
                            d = d * 100;
                            return String.format("%s%s", Double.toString(d), "%");
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return errorStr;
    }


    /**
     * 报酬率测算表
     *
     * @return
     * @throws Exception
     */
    public String getRemunerationRateSheet() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        builder.writeln("报酬率测算表");
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        List<Map<String, JSONObject>> mapList = Lists.newArrayList();
        if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getParameterValue())) {
            JSONArray jsonArray = JSONObject.parseArray(toolRewardRate.getParameterValue());
            if (CollectionUtils.isNotEmpty(jsonArray)) {
                jsonArray.stream().filter(o -> {
                    JSONObject jsonObject = (JSONObject) o;
                    if (jsonObject != null) {
                        if (jsonObject.size() > 0) {
                            return true;
                        }
                    }
                    return false;
                }).forEach(o -> {
                    JSONObject jsonObject = (JSONObject) o;
                    String text = jsonObject.getString(name);
                    if (StringUtils.isNotBlank(text)) {
                        Map<String, JSONObject> jsonObjectMap = null;
                        switch (text) {
                            case opportunityCost:
                                jsonObjectMap = Maps.newHashMap();
                                jsonObjectMap.put(opportunityCost, jsonObject);
                                mapList.add(jsonObjectMap);
                                break;
                            case riskCompensation:
                                jsonObjectMap = Maps.newHashMap();
                                jsonObjectMap.put(riskCompensation, jsonObject);
                                mapList.add(jsonObjectMap);
                                break;
                            case manageCompensation:
                                jsonObjectMap = Maps.newHashMap();
                                jsonObjectMap.put(manageCompensation, jsonObject);
                                mapList.add(jsonObjectMap);
                                break;
                            case liquidCompensation:
                                jsonObjectMap = Maps.newHashMap();
                                jsonObjectMap.put(liquidCompensation, jsonObject);
                                mapList.add(jsonObjectMap);
                                break;
                            case financingAdvantage:
                                jsonObjectMap = Maps.newHashMap();
                                jsonObjectMap.put(financingAdvantage, jsonObject);
                                mapList.add(jsonObjectMap);
                                break;
                            case taxDeductionAdvantage:
                                jsonObjectMap = Maps.newHashMap();
                                jsonObjectMap.put(taxDeductionAdvantage, jsonObject);
                                mapList.add(jsonObjectMap);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        }
        if (mapList != null && !mapList.isEmpty() && mapList.size() > 0) {
            Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
            Table table = builder.startTable();
            for (int j = 0; j < 7; j++) {
                switch (j) {
                    case 0:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            switch (k) {
                                case 0:
                                    builder.writeln("机会成本");
                                    break;
                                case 1:
                                    builder.writeln("百分比");
                                    break;
                                case 2:
                                    JSONObject jsonObject = getJSONObjectAndRemunerationRateSheet(mapList, opportunityCost);
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(remark))) {
                                        builder.writeln(String.format("%s (备注)", jsonObject.getString(remark)));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        builder.endRow();
                        break;
                    case 1:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            JSONObject jsonObject = getJSONObjectAndRemunerationRateSheet(mapList, riskCompensation);
                            switch (k) {
                                case 0:
                                    builder.writeln("投资风险补偿");
                                    break;
                                case 1:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(ratio))) {
                                        if (NumberUtils.isNumber(jsonObject.getString(ratio))) {
                                            BigDecimal temp = new BigDecimal(jsonObject.getString(ratio)).multiply(new BigDecimal(100));
                                            builder.writeln(String.format("%s%s", temp.toString(), "%"));
                                        }
                                    }
                                    break;
                                case 2:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(remark))) {
                                        builder.writeln(jsonObject.getString(remark));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        builder.endRow();
                        break;
                    case 2:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            JSONObject jsonObject = getJSONObjectAndRemunerationRateSheet(mapList, manageCompensation);
                            switch (k) {
                                case 0:
                                    builder.writeln("管理负担补偿");
                                    break;
                                case 1:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(ratio))) {
                                        if (NumberUtils.isNumber(jsonObject.getString(ratio))) {
                                            BigDecimal temp = new BigDecimal(jsonObject.getString(ratio)).multiply(new BigDecimal(100));
                                            builder.writeln(String.format("%s%s", temp.toString(), "%"));
                                        }
                                    }
                                    break;
                                case 2:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(remark))) {
                                        builder.writeln(jsonObject.getString(remark));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        builder.endRow();
                        break;
                    case 3:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            JSONObject jsonObject = getJSONObjectAndRemunerationRateSheet(mapList, liquidCompensation);
                            switch (k) {
                                case 0:
                                    builder.writeln("缺乏流动性补偿");
                                    break;
                                case 1:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(ratio))) {
                                        if (NumberUtils.isNumber(jsonObject.getString(ratio))) {
                                            BigDecimal temp = new BigDecimal(jsonObject.getString(ratio)).multiply(new BigDecimal(100));
                                            builder.writeln(String.format("%s%s", temp.toString(), "%"));
                                        }
                                    }
                                    break;
                                case 2:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(remark))) {
                                        builder.writeln(jsonObject.getString(remark));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        builder.endRow();
                        break;
                    case 4:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            JSONObject jsonObject = getJSONObjectAndRemunerationRateSheet(mapList, financingAdvantage);
                            switch (k) {
                                case 0:
                                    builder.writeln("易与获得融资的好处");
                                    break;
                                case 1:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(ratio))) {
                                        if (NumberUtils.isNumber(jsonObject.getString(ratio))) {
                                            BigDecimal temp = new BigDecimal(jsonObject.getString(ratio)).multiply(new BigDecimal(100));
                                            builder.writeln(String.format("%s%s", temp.toString(), "%"));
                                        }
                                    }
                                    break;
                                case 2:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(remark))) {
                                        builder.writeln(jsonObject.getString(remark));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        builder.endRow();
                        break;
                    case 5:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            JSONObject jsonObject = getJSONObjectAndRemunerationRateSheet(mapList, taxDeductionAdvantage);
                            switch (k) {
                                case 0:
                                    builder.writeln("所得税抵扣的好处");
                                    break;
                                case 1:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(ratio))) {
                                        if (NumberUtils.isNumber(jsonObject.getString(ratio))) {
                                            BigDecimal temp = new BigDecimal(jsonObject.getString(ratio)).multiply(new BigDecimal(100));
                                            builder.writeln(String.format("%s%s", temp.toString(), "%"));
                                        }
                                    }
                                    break;
                                case 2:
                                    if (jsonObject != null && StringUtils.isNotBlank(jsonObject.getString(remark))) {
                                        builder.writeln(jsonObject.getString(remark));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        builder.endRow();
                        break;
                    case 6:
                        for (int k = 0; k < 3; k++) {
                            builder.insertCell();
                            if (k == 0) {
                                builder.writeln("合计");
                            }
                            if (k == 1) {
                                if (toolRewardRate != null && StringUtils.isNotBlank(toolRewardRate.getResultValue())) {
                                    builder.writeln(toolRewardRate.getResultValue());
                                }
                                mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                            }
                        }
                        builder.endRow();
                        break;
                    default:
                        break;
                }
            }
            if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                for (MergeCellModel mergeCellModel : mergeCellModelList) {
                    Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                    Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                    AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                }
            }
            builder.endTable();
        }
        document.save(localPath);
        return localPath;
    }

    private JSONObject getJSONObjectAndRemunerationRateSheet(List<Map<String, JSONObject>> mapList, String name) {
        JSONObject jsonObject = null;
        for (Map<String, JSONObject> stringJSONObjectMap : mapList) {
            for (Map.Entry<String, JSONObject> stringJSONObjectEntry : stringJSONObjectMap.entrySet()) {
                if (stringJSONObjectEntry.getKey().equals(name)) {
                    jsonObject = stringJSONObjectEntry.getValue();
                }
            }
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
        builder.writeln("收益法价格测算表");
        Table table = builder.startTable();
        final int startRow = 1;
        final int colLength = 9;
        for (int j = 0; j < startRow; j++) {
            for (int k = 0; k < colLength; k++) {
                builder.insertCell();
                switch (k) {
                    case 0:
                        builder.writeln("开始时间");
                        break;
                    case 1:
                        builder.writeln("结束时间");
                        break;
                    case 2:
                        builder.writeln("收益期限(n)");
                        break;
                    case 3:
                        builder.writeln("毛收入");
                        break;
                    case 4:
                        builder.writeln("运营费");
                        break;
                    case 5:
                        builder.writeln("房地产年净收益");
                        break;
                    case 6:
                        builder.writeln("年期修正系数(h)");
                        break;
                    case 7:
                        builder.writeln("收益现值系数(k)");
                        break;
                    case 8:
                        builder.writeln("房地产收益价格");
                        break;
                    default:
                        break;
                }
            }
            builder.endRow();
        }
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            for (int j = startRow; j < mdIncomeDateSectionList.size() + startRow; j++) {
                MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionList.get(j - startRow);
                for (int k = 0; k < colLength; k++) {
                    builder.insertCell();
                    switch (k) {
                        case 0:
                            if (mdIncomeDateSection.getBeginDate() != null) {
                                builder.writeln(DateUtils.format(mdIncomeDateSection.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                            }
                            break;
                        case 1:
                            if (mdIncomeDateSection.getEndDate() != null) {
                                builder.writeln(DateUtils.format(mdIncomeDateSection.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                            }
                            break;
                        case 2:
                            if (mdIncomeDateSection.getYearCount() != null) {
                                builder.writeln(mdIncomeDateSection.getYearCount().toString());
                            }
                            break;
                        case 3:
                            if (mdIncomeDateSection.getIncomeTotal() != null) {
                                builder.writeln(mdIncomeDateSection.getIncomeTotal().toString());
                            }
                            break;
                        case 4:
                            if (mdIncomeDateSection.getCostTotal() != null) {
                                builder.writeln(mdIncomeDateSection.getCostTotal().toString());
                            }
                            break;
                        case 5:
                            if (StringUtils.isNotBlank(mdIncomeDateSection.getNetProfit())) {
                                builder.writeln(mdIncomeDateSection.getNetProfit());
                            }
                            break;
                        case 6:
                            if (mdIncomeDateSection.getCorrectionFactor() != null) {
                                builder.writeln(mdIncomeDateSection.getCorrectionFactor().toString());
                            }
                            break;
                        case 7:
                            if (mdIncomeDateSection.getPresentValueFactor() != null) {
                                builder.writeln(mdIncomeDateSection.getPresentValueFactor().toString());
                            }
                            break;
                        case 8:
                            if (mdIncomeDateSection.getIncomePrice() != null) {
                                builder.writeln(mdIncomeDateSection.getIncomePrice().toString());
                            }
                            break;
                        default:
                            break;
                    }
                }
                builder.endRow();
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
        if (StringUtils.isEmpty(content)){
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
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.surveyCommonService = SpringContextUtils.getBean(SurveyCommonService.class);
        this.dataBuildingNewRateService = SpringContextUtils.getBean(DataBuildingNewRateService.class);
        this.evaluationMethodService = SpringContextUtils.getBean(EvaluationMethodService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.dataTaxRateAllocationService = SpringContextUtils.getBean(DataTaxRateAllocationService.class);
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

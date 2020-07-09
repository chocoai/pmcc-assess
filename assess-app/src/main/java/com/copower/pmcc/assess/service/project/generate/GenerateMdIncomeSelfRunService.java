package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.report.ReportFieldMdIncomeEnum;
import com.copower.pmcc.assess.common.enums.report.ReportFieldMdIncomeSelfEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeForecastDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeHistoryDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomePriceInvestigationDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.method.MdIncomeForecastVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseCostVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataMethodFormulaService;
import com.copower.pmcc.assess.service.tool.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdIncomeDateSectionService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
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

/**
 * @author: zch
 * @date: 2019/2/12 16:43
 * @description:收益法报告字段处理
 */
public class GenerateMdIncomeSelfRunService implements Serializable {
    private final LinkedList<String> chineseNumbers = Lists.newLinkedList(Arrays.asList("一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十"));
    private Integer miId;
    private Integer areaId;
    private SchemeInfo schemeInfo;
    private MdIncome mdIncome;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;
    private List<MdIncomeDateSection> mdIncomeDateSectionList = Lists.newArrayList();
    private List<MdIncomeLeaseCostVo> leaseVoList = Lists.newArrayList();

    private MdIncomeService mdIncomeService;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private DataSetUseFieldService dataSetUseFieldService;
    private MdIncomeDateSectionService mdIncomeDateSectionService;
    private DataMethodFormulaService dataMethodFormulaService;
    private ToolRewardRateService toolRewardRateService;
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private DataBuildingNewRateService dataBuildingNewRateService;
    private GenerateCommonMethod generateCommonMethod;
    private BaseService baseService;
    private MdIncomeHistoryDao mdIncomeHistoryDao;
    private MdIncomePriceInvestigationDao mdIncomePriceInvestigationDao;
    private MdIncomeForecastDao mdIncomeForecastDao;

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
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.INCOME_TEMPLATE_SELF_SUPPORT);
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
                //收益法公式
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeMethodFormula.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeMethodFormula());
                }
                //收益法申报产权证类型
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.PropertyRightCertificateIncomeLaw.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getPropertyRightCertificateIncomeLaw());
                }
                //收益法设定用途
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeSetUse.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSetUse());
                }
                //收益法土地终止日期
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.TerminationDateLand.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTerminationDateLand());
                }
                //收益法剩余土地使用年限
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeSurplusLandUseYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSurplusLandUseYear());
                }
                //收益法竣工时间
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeCompletionTime.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeCompletionTime());
                }
                //收益法价值时点
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeValuePoint.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeValuePoint());
                }
                //收益法已使用年限
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeUsedLife.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeUsedLife());
                }
                //收益法建筑结构类别
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomebuildingStructureType.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomebuildingStructureType());
                }
                //收益法经济耐用年限
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeBuildEconomicLife.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeBuildEconomicLife());
                }
                //收益法房产剩余年限
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeHouseSurplusYear.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeHouseSurplusYear());
                }
                //收益法收益年限
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeYears.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeYears());
                }
                //收益法报酬率
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomePayBack.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum.IncomePayBack));
                }
                //收益法机会成本说明
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeOpportunityCostReamrk.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum.IncomeOpportunityCostReamrk));
                }
                //收益法投资风险补偿
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeRiskCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum.IncomeRiskCompensation));
                }
                //收益法管理负担补偿
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeManageCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum.IncomeManageCompensation));
                }
                //收益法缺乏流动性补偿
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeLiquidCompensation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum.IncomeLiquidCompensation));
                }
                //收益法投资带来的优惠
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeFinancingAdvantage.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum.IncomeFinancingAdvantage));
                }
                //收益法区域
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeArea.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeRegionalCities());
                }
                //收益法自营经营情况
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeEngageSituation.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeEngageSituation());
                }
                //收益法自营经营收支一览表
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeEngageEarningAndOutGeneral.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeEngageEarningAndOutGeneral(null));
                }
                //收益法自营经营收入一览表
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeEngageEarningGeneral.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeEngageEarningAndOutGeneral(0));
                }
                //收益法自营同档次物业调查表
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeSurveyItems.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeSurveyItems());
                }
                //收益法自营经营成本
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeEngageCost.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeEngageCost());
                }
                //收益法自营增值税及附加取值说明
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeOperatingTaxRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeOperatingTaxRemark());
                }
                //收益法自营年营运费
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeSelfSupportYearOperating.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeSelfSupportYearOperating());
                }
                //收益法自营商业利润取值说明
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeOperatingProfitRemark.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeOperatingProfitRemark());
                }
                //估价对象运营费用测算汇总表
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeOperatingCalculateCollect.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeOperatingCalculateCollect());
                }
                //估价对象运营费一览表
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeOperatingGeneral.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeOperatingGeneral());
                }
                //收益法自营房地产净收益
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeRealtyNetEarning.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeRealtyNetEarning());
                }
                //收益法自营增长率
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeRateIncrease.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeRateIncrease());
                }
                //收益法自营公式计算结果
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeFormulaResult.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeFormulaResult(false));
                }
                //收益法自营测算价格
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeCalculatePrice.getName())) {
                    generateCommonMethod.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeFormulaResult(true));
                }
                //收益法自营测算公式带值
                if (Objects.equal(name, ReportFieldMdIncomeSelfEnum.IncomeCalculateFormulaValue.getName())) {
                    generateCommonMethod.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeCalculateFormulaValue());
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
     * 功能描述: 收益法竣工时间
     *
     * @auther: zch
     * @date: 2019/2/27 18:10
     */
    private  String getIncomeCompletionTime() throws Exception {
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
     * 功能描述: 收益法自营经营情况
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeEngageSituation() throws Exception {
        //主营业务收入
        String main = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 0, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_INCOME_MAIN);
        //其他业务收入
        String other = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 0, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_INCOME_OTHER);
        StringBuilder s = new StringBuilder();
        if (StringUtils.isNotEmpty(main)) {
            s.append(main);
        }
        if (StringUtils.isNotEmpty(other)) {
            s.append(";").append(other);
        }
        s.append("。");
        if (StringUtils.isNotBlank(s.toString())) {
            return s.toString();
        }
        return errorStr;
    }


    /**
     * 功能描述: 收益法自营经营成本
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeEngageCost() throws Exception {
        StringBuilder s = new StringBuilder();
        //主经营成本
        String main = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 1, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_MAIN_MANAGER_COST);
        if (StringUtils.isNotEmpty(main)) {
            s.append(main);
        }
        //其他经营成本
        String other = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 1, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_OTHER_MANAGER_COST);
        if (StringUtils.isNotEmpty(other)) {
            s.append(";").append(other);
        }
        //经营费用
        String engageCost = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 1, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_ENGAGE_COST);
        if (StringUtils.isNotEmpty(engageCost)) {
            s.append(";").append(engageCost);
        }
        //增值税及其附加
        String operatingCost = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 1, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_OPERATING_COST);
        if (StringUtils.isNotEmpty(operatingCost)) {
            s.append(";").append(operatingCost);
        }
        //管理费用
        String managerCost = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 1, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_MANAGER_COST);
        if (StringUtils.isNotEmpty(managerCost)) {
            s.append(";").append(managerCost);
        }
        //财务费用
        String financeCost = mdIncomeService.getEngageSituation(miId, mdIncome.getFormType(), 1, AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_FINANCE_COST);
        if (StringUtils.isNotEmpty(financeCost)) {
            s.append(";").append(financeCost);
        }
        s.append("。");
        if (StringUtils.isNotBlank(s.toString())) {
            return s.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法自营增值税及附加取值说明
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeOperatingTaxRemark() throws Exception {
        List<MdIncomeForecastVo> vos = getForecastList(1);
        //排序
        Comparator<MdIncomeForecastVo> comparator = new Comparator<MdIncomeForecastVo>() {
            @Override
            public int compare(MdIncomeForecastVo o1, MdIncomeForecastVo o2) {
                return o1.getBeginDate().compareTo(o2.getBeginDate());
            }
        };
        Collections.sort(vos, comparator);
        //说明一致的放在一个list中
        List<List<MdIncomeForecastVo>> allGoods = Lists.newArrayList();
        for (MdIncomeForecastVo item : vos) {
            if (CollectionUtils.isEmpty(allGoods)) {
                List<MdIncomeForecastVo> goods = Lists.newArrayList();
                goods.add(item);
                allGoods.add(goods);
            } else {
                //该类型物品是否已存在
                boolean flag = false;
                step:
                for (List<MdIncomeForecastVo> list : allGoods) {
                    for (MdIncomeForecastVo good : list) {
                        if (StringUtils.isNotEmpty(item.getOperatingTaxRemark()) && item.getOperatingTaxRemark().equals(good.getOperatingTaxRemark())) {
                            list.add(item);
                            flag = true;
                            break step;
                        }
                    }
                }
                //不存在则造一个list存放
                if (!flag) {
                    List<MdIncomeForecastVo> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                }
            }
        }
        if (CollectionUtils.isEmpty(allGoods)) return null;
        StringBuilder s = new StringBuilder();
        if (allGoods.size() == 1) {
            s.append(DateUtils.format(vos.get(0).getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(vos.get(0).getEndDate(), DateUtils.DATE_CHINESE_PATTERN)).
                    append("收益法自营增值税及附加取值说明为").append(vos.get(0).getOperatingTaxRemark()).append("。");
        } else {
            for (List<MdIncomeForecastVo> group : allGoods) {
                if (StringUtils.isNotEmpty(group.get(0).getOperatingTaxRemark())) {
                    for (MdIncomeForecastVo groupItem : group) {
                        s.append(DateUtils.format(groupItem.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(groupItem.getEndDate(), DateUtils.DATE_CHINESE_PATTERN)).append("、");
                    }
                    s.deleteCharAt(s.length() - 1);
                    s.append("收益法自营增值税及附加取值说明为").append(group.get(0).getOperatingTaxRemark()).append("；");
                }
            }
            s.deleteCharAt(s.length() - 1);
            s.append("。");
        }
        if (StringUtils.isNotBlank(s.toString())) {
            return s.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法自营增长率
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeRateIncrease() throws Exception {
        List<MdIncomeForecastVo> vos = getForecastList(0);
        //排序
        Comparator<MdIncomeForecastVo> comparator = new Comparator<MdIncomeForecastVo>() {
            @Override
            public int compare(MdIncomeForecastVo o1, MdIncomeForecastVo o2) {
                return o1.getBeginDate().compareTo(o2.getBeginDate());
            }
        };
        Collections.sort(vos, comparator);
        //增长率一致的放在一个list中
        List<List<MdIncomeForecastVo>> allGoods = Lists.newArrayList();
        for (MdIncomeForecastVo item : vos) {
            if (CollectionUtils.isEmpty(allGoods)) {
                List<MdIncomeForecastVo> goods = Lists.newArrayList();
                goods.add(item);
                allGoods.add(goods);
            } else {
                //该增长率物品是否已存在
                boolean flag = false;
                step:
                for (List<MdIncomeForecastVo> list : allGoods) {
                    for (MdIncomeForecastVo good : list) {
                        if (item.getRateIncrease() != null && item.getRateIncrease().compareTo(good.getRateIncrease()) == 0) {
                            list.add(item);
                            flag = true;
                            break step;
                        }
                    }
                }
                //不存在则造一个list存放
                if (!flag) {
                    List<MdIncomeForecastVo> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                }
            }
        }
        if (CollectionUtils.isEmpty(allGoods)) return null;
        StringBuilder s = new StringBuilder();
        if (allGoods.size() == 1) {
            s.append(DateUtils.format(vos.get(0).getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(vos.get(0).getEndDate(), DateUtils.DATE_CHINESE_PATTERN)).
                    append("收益法自营增长率为").append(ArithmeticUtils.getPercentileSystem(vos.get(0).getRateIncrease(), 4, BigDecimal.ROUND_HALF_UP)).append("。");
        } else {
            for (List<MdIncomeForecastVo> group : allGoods) {
                if (group.get(0).getRateIncrease() != null) {
                    for (MdIncomeForecastVo groupItem : group) {
                        s.append(DateUtils.format(groupItem.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(groupItem.getEndDate(), DateUtils.DATE_CHINESE_PATTERN)).append("、");
                    }
                    s.deleteCharAt(s.length() - 1);
                    s.append("收益法自营增长率为").append(ArithmeticUtils.getPercentileSystem(group.get(0).getRateIncrease(), 4, BigDecimal.ROUND_HALF_UP)).append("；");
                }
            }
            if (StringUtils.isNotBlank(s.toString())) {
                s.deleteCharAt(s.length() - 1);
            }
        }
        if (StringUtils.isNotBlank(s.toString())) {
            return s.toString();
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法自营商业利润取值说明
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeOperatingProfitRemark() throws Exception {
        String s = getMdIncome().getAverageProfitRateRemark();
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法自营年营运费
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private String getIncomeSelfSupportYearOperating() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);

        List<MdIncomeForecastVo> vos = getForecastList(1);

        if (CollectionUtils.isEmpty(vos)) return null;
        StringBuilder format = new StringBuilder();
        for (MdIncomeForecastVo item : vos) {
            StringBuilder s1 = new StringBuilder();
            //${收益法自营经营成本}+${收益法自营经营费用}+${收益法自营管理费用}+${收益法自营财务费用}+${收益法自营增值税及附加}+${特许权超额利润}+${经营利润}=${收益法自营年营运费}
            s1.append(DateUtils.format(item.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(item.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
            format.append(generateCommonMethod.getIndentHtml(s1.toString()));
            StringBuilder s = new StringBuilder();
            s.append("年营运费用 = ");
            BigDecimal result = new BigDecimal("0");
            if (item.getOperatingCost() != null) {
                result = result.add(item.getOperatingCost());
                s.append(item.getOperatingCost()).append(" + ");
            } else {
                s.append("0").append(" + ");
            }
            if (item.getOperatingExpenses() != null) {
                result = result.add(item.getOperatingExpenses());
                s.append(item.getOperatingExpenses()).append(" + ");
            } else {
                s.append("0").append(" + ");
            }
            if (item.getManagementCost() != null) {
                result = result.add(item.getManagementCost());
                s.append(item.getManagementCost()).append(" + ");
            } else {
                s.append("0").append(" + ");
            }
            if (item.getFinancialCost() != null) {
                result = result.add(item.getFinancialCost());
                s.append(item.getFinancialCost()).append(" + ");
            } else {
                s.append("0").append(" + ");
            }
            if (item.getOperatingTax() != null) {
                result = result.add(item.getOperatingTax());
                s.append(item.getOperatingTax());
            } else {
                s.append("0");
            }
            s.append(" = ").append(result.toString());
            format.append(generateCommonMethod.getIndentHtml(s.toString()));
        }
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(format.toString()));
        document.save(localPath);
        return localPath;
    }

    /**
     * 功能描述: 收益法自营房地产净收益
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private String getIncomeRealtyNetEarning() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);

        List<MdIncomeForecastVo> vos = getForecastList(1);

        if (CollectionUtils.isEmpty(vos)) return null;
        StringBuilder format = new StringBuilder();
        for (MdIncomeForecastVo item : vos) {
            StringBuilder s = new StringBuilder();
            MdIncomeDateSection section = mdIncomeDateSectionService.getDateSectionById(item.getSectionId());

            //房地产净收益=经营收入×（１－商业利润率）－运营费用
            s.append(DateUtils.format(item.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN)).append("至").append(DateUtils.format(item.getEndDate(), DateUtils.DATE_CHINESE_PATTERN)).append("：");
            if (section.getIncomeTotal() != null) {
                s.append(section.getIncomeTotal());
            } else {
                s.append(errorStr);
            }
            s.append(" ×（1 - ");
            if (StringUtils.isNoneEmpty(mdIncome.getAverageProfitRate())) {
                s.append(ArithmeticUtils.getPercentileSystem(new BigDecimal(mdIncome.getAverageProfitRate()), 4, BigDecimal.ROUND_HALF_UP));
            } else {
                s.append(errorStr);
            }
            s.append("）- ");
            if (section.getCostTotal() != null) {
                s.append(section.getCostTotal());
            } else {
                s.append(errorStr);
            }
            s.append(" = ").append(section.getNetProfit());
            format.append(generateCommonMethod.getIndentHtml(s.toString()));
        }
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(format.toString()));
        document.save(localPath);
        return localPath;
    }

    /**
     * 收益法自营经营收支一览表
     *
     * @return
     * @throws Exception
     */
    public String getIncomeEngageEarningAndOutGeneral(Integer type) throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        builder.startTable();
        if (mdIncome.getFormType() == 0) {
            generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("年度", "月度", "类型", "会计科目", "一级编号", "二级编号", "单位", "单价", "数量", "金额", "房屋折旧与使用费")));
        } else {
            generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("开始时间", "结束时间", "类型", "会计科目", "一级编号", "二级编号", "单位", "单价", "数量", "金额", "房屋折旧与使用费")));
        }
        List<MdIncomeHistory> mdIncomeHistoryList = getMdIncomeHistoryList(type);
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(mdIncomeHistoryList)) {
            for (MdIncomeHistory mdIncomeHistory : mdIncomeHistoryList) {
                if (mdIncome.getFormType() == 0) {
                    if (mdIncomeHistory.getYear() != null) {
                        linkedList.add(mdIncomeHistory.getYear().toString());
                    } else {
                        linkedList.add(nullValue);
                    }
                    if (mdIncomeHistory.getMonth() != null) {
                        linkedList.add(mdIncomeHistory.getMonth().toString());
                    } else {
                        linkedList.add(nullValue);
                    }
                } else {
                    if (mdIncomeHistory.getBeginDate() != null) {
                        linkedList.add(DateUtils.format(mdIncomeHistory.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                    } else {
                        linkedList.add(nullValue);
                    }
                    if (mdIncomeHistory.getEndDate() != null) {
                        linkedList.add(DateUtils.format(mdIncomeHistory.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                    } else {
                        linkedList.add(nullValue);
                    }
                }
                if (StringUtils.isNotEmpty(mdIncomeHistory.getSourceType())) {
                    linkedList.add(mdIncomeHistory.getSourceType());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeHistory.getAccountingSubject() != null) {
                    linkedList.add(baseDataDicService.getNameById(mdIncomeHistory.getAccountingSubject()));
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(mdIncomeHistory.getFirstLevelNumber())) {
                    linkedList.add(mdIncomeHistory.getFirstLevelNumber());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(mdIncomeHistory.getSecondLevelNumber())) {
                    linkedList.add(mdIncomeHistory.getSecondLevelNumber());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(mdIncomeHistory.getUnit())) {
                    linkedList.add(mdIncomeHistory.getUnit());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncome.getFormType() == 0) {
                    if (mdIncomeHistory.getUnitPrice() != null) {
                        linkedList.add(mdIncomeHistory.getUnitPrice().toString());
                    } else {
                        linkedList.add(nullValue);
                    }
                } else {
                    if (mdIncomeHistory.getMakePrice() != null) {
                        linkedList.add(mdIncomeHistory.getMakePrice().toString());
                    } else {
                        linkedList.add(nullValue);
                    }
                }
                if (mdIncomeHistory.getNumber() != null) {
                    linkedList.add(mdIncomeHistory.getNumber().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeHistory.getAmountMoney() != null) {
                    linkedList.add(mdIncomeHistory.getAmountMoney().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(mdIncomeHistory.getDeprecitionRoyalty())) {
                    linkedList.add(mdIncomeHistory.getDeprecitionRoyalty());
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
     * 收益法自营同档次物业调查表
     *
     * @return
     * @throws Exception
     */
    public String getIncomeSurveyItems() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        builder.startTable();
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("名称", "价格")));

        List<MdIncomePriceInvestigation> mdIncomePriceInvestigationList = getMdIncomePriceInvestigationList();
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(mdIncomePriceInvestigationList)) {
            for (MdIncomePriceInvestigation mdIncomePriceInvestigation : mdIncomePriceInvestigationList) {
                if (StringUtils.isNotEmpty(mdIncomePriceInvestigation.getName())) {
                    linkedList.add(mdIncomePriceInvestigation.getName());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomePriceInvestigation.getPrice() != null) {
                    linkedList.add(mdIncomePriceInvestigation.getPrice().toString());
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
     * 功能描述: 收益法区域
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeRegionalCities() throws Exception {
        String s = getSchemeAreaGroup().getAreaName();
        if (StringUtils.isNotBlank(s)) {
            return s;
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法自营公式计算结果
     * flag确认单位：true为万，false为元
     *
     * @author: zch
     * @date: 2019/2/28 14:26
     */
    private  String getIncomeFormulaResult(Boolean flag) throws Exception {
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        BigDecimal result = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            for (MdIncomeDateSection section : mdIncomeDateSectionList) {
                result = result.add(section.getIncomePrice());
            }
        }
        if (flag == true) {
            return result.divide(new BigDecimal("10000"), 2, BigDecimal.ROUND_HALF_UP).toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 估价对象运营费用测算汇总表
     *
     * @return
     * @throws Exception
     */
    public String getIncomeOperatingCalculateCollect() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        Table table = builder.startTable();
        //开始时间 结束时间	收益期限(n)	  收入	成本	经营利润	房地产年净收益	年期修正系数(h)	收益现值系数(k)	房地产收益价格
        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("开始时间", "结束时间", "收益期限", "收入", "成本", "经营利润", "房地产年净收益", "房地产收益价格")));

        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        int j = 0;
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            //需要合并的单元格
            Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
            BigDecimal total = new BigDecimal("0");
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
                if (mdIncomeDateSection.getOperatingProfit() != null) {
                    linkedList.add(mdIncomeDateSection.getOperatingProfit().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNotEmpty(mdIncomeDateSection.getNetProfit())) {
                    linkedList.add(mdIncomeDateSection.getNetProfit());
                } else {
                    linkedList.add(nullValue);
                }
                if (mdIncomeDateSection.getIncomePrice() != null) {
                    total = total.add(mdIncomeDateSection.getIncomePrice());
                    linkedList.add(mdIncomeDateSection.getIncomePrice().toString());
                } else {
                    linkedList.add(nullValue);
                }
                generateCommonMethod.writeWordTitle(builder, linkedList);
                linkedList.clear();
                j++;
            }
            //合计
            linkedList.addAll(Arrays.asList("合计", total.toString(), "", "", "", "", "", ""));
            AsposeUtils.writeWordTitle(builder, linkedList);
            linkedList.clear();
            mergeCellModelList.add(new MergeCellModel(j + 1, 1, j + 1, 7));
            if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                generateCommonMethod.mergeCellTable(mergeCellModelList, table);
            }
        }
        builder.endTable();
        document.save(localPath);
        return localPath;
    }


    /**
     * 估价对象运营费一览表
     *
     * @return
     * @throws Exception
     */
    public String getIncomeOperatingGeneral() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
        generateCommonMethod.settingBuildingTable(builder);
        builder.startTable();

        generateCommonMethod.writeWordTitle(builder, Lists.newLinkedList(Lists.newArrayList("开始时间", "结束时间", "年份数", "经营成本比率", "经营费用比率", "经营税金及附加比率", "管理费用比率", "财务费用比率", "经营利润率", "特许权超额利润比率")));

        List<MdIncomeForecastVo> forecastList = getForecastList(1);
        final String nullValue = "";
        LinkedList<String> linkedList = Lists.newLinkedList();
        if (CollectionUtils.isNotEmpty(forecastList)) {
            for (MdIncomeForecastVo forecast : forecastList) {
                MdIncomeDateSection section = mdIncomeDateSectionService.getDateSectionById(forecast.getSectionId());
                if (forecast.getBeginDate() != null) {
                    linkedList.add(DateUtils.format(forecast.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getEndDate() != null) {
                    linkedList.add(DateUtils.format(forecast.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                } else {
                    linkedList.add(nullValue);
                }
                if (section.getYearCount() != null) {
                    linkedList.add(section.getYearCount().toString());
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getOperatingCostRatio() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(forecast.getOperatingCostRatio(), 4, BigDecimal.ROUND_HALF_UP));
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getOperatingExpensesRatio() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(forecast.getOperatingExpensesRatio(), 4, BigDecimal.ROUND_HALF_UP));
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getOperatingTaxRatio() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(forecast.getOperatingTaxRatio(), 4, BigDecimal.ROUND_HALF_UP));
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getManagementCostRatio() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(forecast.getManagementCostRatio(), 4, BigDecimal.ROUND_HALF_UP));
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getFinancialCostRatio() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(forecast.getFinancialCostRatio(), 4, BigDecimal.ROUND_HALF_UP));
                } else {
                    linkedList.add(nullValue);
                }
                if (StringUtils.isNoneEmpty(mdIncome.getAverageProfitRate())) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(new BigDecimal(mdIncome.getAverageProfitRate()), 4, BigDecimal.ROUND_HALF_UP));
                } else {
                    linkedList.add(nullValue);
                }
                if (forecast.getExcessProfitRatio() != null) {
                    linkedList.add(ArithmeticUtils.getPercentileSystem(forecast.getExcessProfitRatio(), 4, BigDecimal.ROUND_HALF_UP));
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
     * 收益法自营测算公式带值
     *
     * @return
     * @throws Exception
     */
    public String getIncomeCalculateFormulaValue() throws Exception {
        String localPath = getLocalPath();
        Document document = new Document();
        DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);

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
                s.append(generateCommonMethod.getIndentHtml(s2.toString()));
                BigDecimal yearCount = section.getYearCount();
                t = t.add(yearCount);
                String formula = new String();
                if (yearCount.compareTo(new BigDecimal("1")) < 1) {
                    formula = "=At×(1 + g)/(1 + Y)^t]";
                } else {
                    formula = "=At×(1+Y)^-(T+1)×(1-((1+g)/(1+Y))^n)/(1-(1+g)/(1+Y))";
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

                s.append(generateCommonMethod.getIndentHtml(formula));
            }

        }
        builder.insertHtml(generateCommonMethod.getWarpCssHtml(s.toString()));
        document.save(localPath);
        return localPath;
    }


    /**
     * 功能描述: 收益法已使用年限
     *
     * @author: zch
     * @date: 2019/2/28 10:34
     */
    private  String getIncomeUsedLife() throws Exception {
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
     * 功能描述: 收益法建筑结构类别
     *
     * @auther: zch
     * @date: 2019/2/27 18:18
     */
    private  String getIncomebuildingStructureType() throws Exception {
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
     * 功能描述: 收益法经济耐用年限
     *
     * @author: zch
     * @date: 2019/2/28 13:41
     */
    private  String getIncomeBuildEconomicLife() throws Exception {
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
    private  String getIncomeValuePoint() throws Exception {
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        if (schemeAreaGroup.getValueTimePoint() != null) {
            return DateUtils.formatDate(schemeAreaGroup.getValueTimePoint(), DateUtils.DATE_CHINESE_PATTERN);
        }
        return errorStr;
    }

    /**
     * 功能描述: 收益法土地终止日期
     *
     * @auther: zch
     * @date: 2019/2/27 17:44
     */
    private  String getTerminationDateLand() throws Exception {
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
     * 功能描述: 收益法剩余土地使用年限
     *
     * @auther: zch
     * @date: 2019/2/27 18:05
     */
    private  String getIncomeSurplusLandUseYear() throws Exception {
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
    private  String getIncomeHouseSurplusYear() throws Exception {
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
    private  String getIncomeYears() throws Exception {
        List<Double> doubleList = Lists.newArrayList(new Double(0));
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        mdIncomeDateSectionList.stream().forEach(mdIncomeDateSection -> {
            if (mdIncomeDateSection.getYearCount() != null) {
                doubleList.add(mdIncomeDateSection.getYearCount().doubleValue());
            }
        });
        return String.valueOf(doubleList.stream().mapToDouble(Double::doubleValue).sum());
    }

    /**
     * 功能描述: 收益法公式
     *
     * @author: zch
     * @date: 2019/2/28 16:37
     */
    private  String getIncomeMethodFormula() throws Exception {
        DataMethodFormula formula = dataMethodFormulaService.getMethodFormulaByMethodKey(AssessDataDicKeyConstant.INCOME_SELF_SUPPORT);
        return formula.getFormula();
    }

    /**
     * 功能描述: 收益法设定用途
     *
     * @auther: zch
     * @date: 2019/2/27 17:35
     */
    private  String getIncomeSetUse() {
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
    private  String getPropertyRightCertificateIncomeLaw() throws Exception {
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
     * 报酬率测算 公共方法
     *
     * @param incomeEnum
     * @return
     */
    public String getToolRewardRateIncomeCommon(ReportFieldMdIncomeSelfEnum incomeEnum) throws Exception {
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

    public GenerateMdIncomeSelfRunService(SchemeInfo schemeInfo, Integer areaId) {
        this.miId = schemeInfo.getMethodDataId();
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.mdIncomeDateSectionService = SpringContextUtils.getBean(MdIncomeDateSectionService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
        this.toolRewardRateService = SpringContextUtils.getBean(ToolRewardRateService.class);
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.dataBuildingNewRateService = SpringContextUtils.getBean(DataBuildingNewRateService.class);
        this.generateCommonMethod = SpringContextUtils.getBean(GenerateCommonMethod.class);
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.mdIncomeHistoryDao = SpringContextUtils.getBean(MdIncomeHistoryDao.class);
        this.mdIncomePriceInvestigationDao = SpringContextUtils.getBean(MdIncomePriceInvestigationDao.class);
        this.mdIncomeForecastDao = SpringContextUtils.getBean(MdIncomeForecastDao.class);
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


    private String getChineseNumber(Integer number) {
        int len = chineseNumbers.size();
        if (number < len) {
            return chineseNumbers.get(number);
        } else {
            return String.valueOf(number.intValue());
        }
    }

    private GenerateMdIncomeSelfRunService() {
    }

    private String getLocalPath() {
        return generateCommonMethod.getLocalPath();
    }

    public List<MdIncomeHistory> getMdIncomeHistoryList(Integer type) {
        MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
        mdIncomeHistory.setIncomeId(miId);
        mdIncomeHistory.setFormType(mdIncome.getFormType());
        if (type != null) {
            mdIncomeHistory.setType(type);
        }
        return mdIncomeHistoryDao.getHistoryList(mdIncomeHistory);
    }

    public List<MdIncomePriceInvestigation> getMdIncomePriceInvestigationList() {
        MdIncomePriceInvestigation mdIncomePriceInvestigation = new MdIncomePriceInvestigation();
        mdIncomePriceInvestigation.setIncomeId(miId);
        return mdIncomePriceInvestigationDao.getIncomePriceInvestigationList(mdIncomePriceInvestigation);
    }

    public List<MdIncomeForecastVo> getForecastList(Integer type) {
        MdIncomeForecast where = new MdIncomeForecast();
        where.setType(type);
        where.setIncomeId(miId);
        List<MdIncomeForecast> historyList = mdIncomeForecastDao.getForecastList(where);
        List<MdIncomeForecastVo> vos = LangUtils.transform(historyList, p -> mdIncomeService.getForecastVo(p));
        return vos;
    }

    private List<MdIncomeDateSection> getMdIncomeDateSectionList() {
        if (CollectionUtils.isEmpty(this.mdIncomeDateSectionList)) {
            MdIncomeDateSection query = new MdIncomeDateSection();
            query.setIncomeId(miId);
            query.setOperationMode(0);
            this.mdIncomeDateSectionList = mdIncomeDateSectionService.getMdIncomeDateSectionList(query);
        }
        return mdIncomeDateSectionList;
    }
}

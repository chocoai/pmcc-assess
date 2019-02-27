package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldMdIncomeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.generate.BookmarkAndRegexDto;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseCostVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.ToolRewardRateService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdIncomeDateSectionService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private Integer baseReportTemplateId;
    private SchemeInfo schemeInfo;

    private MdIncomeService mdIncomeService;
    private CommonService commonService;
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

    public MdIncome getMdIncome() {
        return mdIncomeService.getIncomeById(miId);
    }

    private GenerateMdIncomeService() {

    }


    /**
     * 获取替换后的报告模板
     *
     * @return
     * @throws Exception
     */
    public String generateCompareFile() throws Exception {
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.INCOME_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList))
            throw new BusinessException("模板文件未找到");
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);

        Set<BookmarkAndRegexDto> bookmarkAndRegexDtoHashSet = Sets.newHashSet();
        //获取待替换文本的集合
        List<String> regexS = specialTreatment(AsposeUtils.getRegexList(document, null));
        //获取所有书签集合
        BookmarkCollection bookmarkCollection = AsposeUtils.getBookmarks(document);
        if (bookmarkCollection.getCount() >= 1) {
            for (int i = 0; i < bookmarkCollection.getCount(); i++) {
                BookmarkAndRegexDto regexDto = new BookmarkAndRegexDto();
                regexDto.setChineseName(getChinese(bookmarkCollection.get(i).getName())).setName(bookmarkCollection.get(i).getName()).setType(BaseReportFieldReplaceEnum.BOOKMARK.getKey());
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

            //租金增值预测
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RentGrowthForecast.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRentGrowthForecast());
            }
            //出租率说明
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RestrictionExplain.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRestrictionExplain());
            }
            //月租金
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MonthRentalIncome.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMonthRentalIncome());
            }
            //月份数
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MonthNumber.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMonthNumber());
            }
            //有效出租率
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.Rentals.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRentals());
            }

            //其它收入内容/其它收入
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncomeContents.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getOtherIncome());
            }
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.OtherIncome.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getOtherIncome());
            }

            //年押金收入/年押金
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.YearDeposit.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getYearDeposit());
            }
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualDepositIncome.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getYearDeposit());
            }

            //一年期定期存款利率
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.YearDepositRate.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getYearDepositRate());
            }
            //年押金利息收入
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RentInterestIncome.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getRentInterestIncome());
            }
            //有效收入公式
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.EffectiveIncomeFormula.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getEffectiveIncomeFormula());
            }

            //总收入/毛收入
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.GrossIncome.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeTotal());
            }
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeTotal.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeTotal());
            }

            //管理费用说明
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ManagemenRemark.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getManagemenRemark());
            }

            //重置成本/重置价格
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ReplacementCost.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getReplacementValue());
            }

            //维修保养费率
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.MaintenanceCostRatio.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getMaintenanceCostRatio());
            }
            //土地使用税
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.LandUseTax.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getLandUseTax());
            }
            //管理费
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.ManagementCost.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getManagementCost());
            }

            //保险费/年保费
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.InsurancePremium.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getInsurancePremium());
            }
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.InsurancePremium1.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getInsurancePremium());
            }

            //土地使用税费
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.LandTaxFees.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getLandTaxFees());
            }
            //年运营费
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualOperatingCost.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getAnnualOperatingCost());
            }
            //年净收益
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.AnnualNetIncome.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getAnnualNetIncome());
            }
            //单价
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomePrice.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomePrice());
            }

            //报酬率测算表
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.RemunerationRateSheet.getName())) {
                this.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getRemunerationRateSheet());
            }
            //收益法价格测算表
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeMethodPriceCalculatingSheet.getName())) {
                this.putValue(false, false, true, textMap, bookmarkMap, fileMap, name, getIncomeMethodPriceCalculating());
            }

            //租约限制
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.Tenancyrestriction.getName())) {
                this.putValue(false, false, false, textMap, bookmarkMap, fileMap, name, null);
            }
            //委托资料
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.EntrustedInformation.getName())) {
                this.putValue(false, false, false, textMap, bookmarkMap, fileMap, name, null);
            }

            //收益法申报产权证类型
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.PropertyRightCertificateIncomeLaw.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getPropertyRightCertificateIncomeLaw());
            }
            //收益法设定用途
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeSetUse.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSetUse());
            }
            //收益法土地终止日期
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.TerminationDateLand.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getTerminationDateLand());
            }
            //收益法剩余土地使用年限
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeSurplusLandUseYear.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeSurplusLandUseYear());
            }
            //收益法竣工时间
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomeCompletionTime.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomeCompletionTime());
            }
            //收益法建筑结构类别
            if (Objects.equal(name, BaseReportFieldMdIncomeEnum.IncomebuildingStructureType.getName())) {
                this.putValue(true, true, false, textMap, bookmarkMap, fileMap, name, getIncomebuildingStructureType());
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
     * 填充值
     *
     * @param text
     * @param bookmark
     * @param textMap
     * @param bookmarkMap
     * @param key
     * @param value
     */
    private void putValue(boolean text, boolean bookmark, boolean fileFlag, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, String key, String value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (text) {
            textMap.put(String.format("${%s}", key), value);
        }
        if (bookmark) {
            bookmarkMap.put(key, value);
        }
        if (fileFlag) {
            fileMap.put(key, value);
        }
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
            if (generateBaseExamineService.getBasicApply() != null && generateBaseExamineService.getBasicApply().getId() != null) {
                if (generateBaseExamineService.getBasicBuilding() != null && generateBaseExamineService.getBasicBuilding().getId() != null) {
                    if (generateBaseExamineService.getBasicBuilding().getBuildingStructureType() != null) {
                        return baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureType());
                    }
                }
            }
        }
        return "";
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
            if (generateBaseExamineService.getBasicApply() != null && generateBaseExamineService.getBasicApply().getId() != null) {
                if (generateBaseExamineService.getBasicBuilding() != null && generateBaseExamineService.getBasicBuilding().getId() != null) {
                    if (generateBaseExamineService.getBasicBuilding().getBeCompletedTime() != null) {
                        return DateUtils.format(generateBaseExamineService.getBasicBuilding().getBeCompletedTime(), DateUtils.DATE_CHINESE_PATTERN);
                    }
                }
            }
        }
        return "";
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
        MdIncome income = getMdIncome();
        if (income.getLandRemainingYear() != null) {
            return income.getLandRemainingYear().toString();
        }
        return "";
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
        return "";
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
        return "";
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
        return "";
    }


    /**
     * 租金增值预测
     *
     * @return
     * @throws Exception
     */
    public String getRentGrowthForecast() throws Exception {
        StringBuilder builder = new StringBuilder();
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            mdIncomeDateSectionList.stream().filter(mdIncomeDateSection -> mdIncomeDateSection.getIncomeId() != null).forEach(mdIncomeDateSection -> {
                if (mdIncomeDateSection.getBeginDate() != null && mdIncomeDateSection.getEndDate() != null && mdIncomeDateSection.getRentalGrowthRate() != null) {
                    builder.append(DateUtils.format(mdIncomeDateSection.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                    builder.append("-");
                    builder.append(DateUtils.format(mdIncomeDateSection.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                    builder.append(":");
                    builder.append(mdIncomeDateSection.getRentalGrowthRate().toString());
                    builder.append(" ");
                    builder.append(mdIncomeDateSection.getRentalGrowthRateExplain());
                    builder.append(";");
                }
            });
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
        return getMdIncome().getRestrictionExplain();
    }

    /**
     * 月租金
     *
     * @return
     */
    public String getMonthRentalIncome() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLease.getRentalIncome().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 月份数
     *
     * @return
     */
    public String getMonthNumber() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLease.getMonthNumber());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 有效出租率
     *
     * @return
     */
    public String getRentals() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLease.getRentals().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 其它收入
     *
     * @return
     */
    public String getOtherIncome() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLease.getOtherIncome().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 租金收入
     *
     * @return
     */
    public String getRentalIncome() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream().map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                BigDecimal a = mdIncomeLease.getRentals().multiply(mdIncomeLease.getRentalIncome());
                BigDecimal b = a.multiply(new BigDecimal(mdIncomeLease.getMonthNumber()));
                builder.append(b.toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 年押金
     *
     * @return
     */
    public String getYearDeposit() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLease.getDeposit().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 一年期定期存款利率
     *
     * @return
     */
    public String getYearDepositRate() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLease.getDepositRate().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 年押金利息收入
     *
     * @return
     */
    public String getRentInterestIncome() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLease query = new MdIncomeLease();
        query.setIncomeId(miId);
        List<MdIncomeLeaseVo> mdIncomeLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(query).stream()
                .map(mdIncomeLease -> mdIncomeService.getMdIncomeLeaseVo(mdIncomeLease)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLease.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLease.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                BigDecimal temp = mdIncomeLease.getDepositRate().multiply(mdIncomeLease.getDeposit());
                builder.append(temp.toString());
                builder.append(";");
            });
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
        StringBuilder builder = new StringBuilder();
        List<MdIncomeDateSection> mdIncomeDateSectionList = getMdIncomeDateSectionList();
        if (CollectionUtils.isNotEmpty(mdIncomeDateSectionList)) {
            mdIncomeDateSectionList.stream().filter(mdIncomeDateSection -> mdIncomeDateSection.getIncomeId() != null).forEach(mdIncomeDateSection -> {
                if (mdIncomeDateSection.getBeginDate() != null && mdIncomeDateSection.getEndDate() != null && mdIncomeDateSection.getRentalGrowthRate() != null) {
                    builder.append(DateUtils.format(mdIncomeDateSection.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                    builder.append("-");
                    builder.append(DateUtils.format(mdIncomeDateSection.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                    builder.append(":");
                    builder.append(mdIncomeDateSection.getIncomeTotal().toString());
                    builder.append(";");
                }
            });
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
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getReplacementValue() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLeaseCostVo.getReplacementValue().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 维修保养费率
     *
     * @return
     */
    public String getMaintenanceCostRatio() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getMaintenanceCostRatio() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLeaseCostVo.getMaintenanceCostRatio().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 维修保养费
     *
     * @return
     */
    public String getMaintenance() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLeaseCostVo.getMaintenance());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 土地使用税
     *
     * @return
     */
    public String getLandUseTax() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null || mdIncomeLeaseCostVo.getLandUseTax() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLeaseCostVo.getLandUseTax().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /***
     * 土地使用税费
     * @return
     */
    public String getLandTaxFees() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                if (mdIncomeDateSection != null) {
                    if (mdIncomeDateSection.getIncomeTotal() != null) {
                        BigDecimal temp = mdIncomeLeaseCostVo.getLandUseTax().multiply(mdIncomeDateSection.getIncomeTotal());
                        builder.append(temp.toString());
                    }
                }
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 年运营费
     *
     * @return
     */
    public String getAnnualOperatingCost() {
        List<MdIncomeDateSection> dateSectionList = getMdIncomeDateSectionList();
        StringBuilder builder = new StringBuilder();
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
                builder.append(DateUtils.format(mdIncomeDateSection.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeDateSection.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeDateSection.getCostTotal().toString());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 年净收益
     *
     * @return
     */
    public String getAnnualNetIncome() {
        List<MdIncomeDateSection> dateSectionList = getMdIncomeDateSectionList();
        StringBuilder builder = new StringBuilder();
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
                builder.append(DateUtils.format(mdIncomeDateSection.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeDateSection.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeDateSection.getNetProfit());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 保险费/年报费
     *
     * @return
     */
    public String getInsurancePremium() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(leaseVoList)) {
            leaseVoList.stream().filter(mdIncomeLeaseCostVo -> {
                if (mdIncomeLeaseCostVo.getBeginDate() == null || mdIncomeLeaseCostVo.getEndDate() == null) {
                    return false;
                }
                return true;
            }).forEach(mdIncomeLeaseCostVo -> {
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                builder.append(mdIncomeLeaseCostVo.getInsurancePremium());
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 管理费
     *
     * @return
     */
    public String getManagementCost() {
        StringBuilder builder = new StringBuilder();
        MdIncomeLeaseCost query = new MdIncomeLeaseCost();
        query.setIncomeId(miId);
        List<MdIncomeLeaseCostVo> leaseVoList = mdIncomeLeaseCostDao.getLeaseCostList(query).stream().map(mdIncomeLeaseCost -> mdIncomeService.getMdIncomeLeaseCostVo(mdIncomeLeaseCost)).collect(Collectors.toList());
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
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getBeginDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append("-");
                builder.append(DateUtils.format(mdIncomeLeaseCostVo.getEndDate(), DateUtils.DATE_CHINESE_PATTERN));
                builder.append(":");
                MdIncomeDateSection mdIncomeDateSection = mdIncomeDateSectionService.getDateSectionById(mdIncomeLeaseCostVo.getSectionId());
                if (mdIncomeDateSection != null) {
                    if (mdIncomeDateSection.getIncomeTotal() != null) {
                        BigDecimal temp = mdIncomeLeaseCostVo.getManagementCostRatio().multiply(mdIncomeDateSection.getIncomeTotal());
                        builder.append(temp.toString());
                    }
                }
                builder.append(";");
            });
        }
        return builder.toString();
    }

    /**
     * 报酬率测算表
     *
     * @return
     * @throws Exception
     */
    public String getRemunerationRateSheet() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.writeln("报酬率测算表");
        ToolRewardRate toolRewardRate = toolRewardRateService.getToolRewardRateById(getMdIncome().getRewardRateId());
        final String name = "name";
        final String ratio = "ratio";
        final String remark = "remark";
        final String opportunityCost = "opportunityCost";
        final String riskCompensation = "riskCompensation";
        final String manageCompensation = "manageCompensation";
        final String liquidCompensation = "liquidCompensation";
        final String financingAdvantage = "financingAdvantage";
        final String taxDeductionAdvantage = "taxDeductionAdvantage";
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
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
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
    public String getIncomePrice() {
        BigDecimal price = getMdIncome().getPrice();
        if (price != null) {
            return price.toString();
        }
        return "";
    }

    private List<MdIncomeDateSection> getMdIncomeDateSectionList() {
        MdIncomeDateSection query = new MdIncomeDateSection();
        query.setIncomeId(miId);
        return mdIncomeDateSectionService.getMdIncomeDateSectionList(query);
    }

    private List<String> specialTreatment(List<String> strings) {
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(strings)) {
            for (String s : strings) {
                String temp = s.substring(2, s.length() - 1);
                stringList.add(temp);
            }
        }
        return stringList;
    }

    /**
     * 利用 ascii 码 配合正则 提取中文
     *
     * @param paramValue
     * @return
     */
    public String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }

    public SchemeInfo getSchemeInfo() {
        return schemeInfo;
    }

    public SchemeJudgeObject getSchemeJudgeObject() {
        SchemeJudgeObject schemeJudgeObject = null;
        if (getSchemeInfo().getJudgeObjectId() != null) {
            schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(getSchemeInfo().getJudgeObjectId());
        }
        if (schemeJudgeObject == null) {
            schemeJudgeObject = new SchemeJudgeObject();
        }
        return schemeJudgeObject;
    }

    public GenerateMdIncomeService(SchemeInfo schemeInfo, Integer projectId, Integer areaId) {
        this.miId = schemeInfo.getMethodDataId();
        this.schemeInfo = schemeInfo;
        this.projectId = projectId;
        this.areaId = areaId;
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.commonService = SpringContextUtils.getBean(CommonService.class);
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
    }

}

package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.Document;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseCostVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdIncomeDateSectionService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2019/2/12 16:43
 * @Description:收益法报告字段处理
 */
public class GenerateMdIncomeService {
    private Integer miId;
    private Integer projectId;
    private Integer areaId;
    private Integer baseReportTemplateId;

    private MdIncomeService mdIncomeService;
    private CommonService commonService;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private DataSetUseFieldService dataSetUseFieldService;
    private MdIncomeDateSectionService mdIncomeDateSectionService;
    private MdIncomeLeaseDao mdIncomeLeaseDao;
    private MdIncomeLeaseCostDao mdIncomeLeaseCostDao;

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
        String a2 = getRentGrowthForecast();
        String a1 = getRentalIncome();

        return localPath;
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
     * @return
     */
    public String getMaintenanceCostRatio(){
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
     * 土地使用税
     * @return
     */
    public String getLandUseTax(){
        return "";
    }

    private List<MdIncomeDateSection> getMdIncomeDateSectionList() {
        MdIncomeDateSection query = new MdIncomeDateSection();
        query.setIncomeId(miId);
        return mdIncomeDateSectionService.getMdIncomeDateSectionList(query);
    }

    public GenerateMdIncomeService(Integer miId, Integer projectId, Integer areaId) {
        this.miId = miId;
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
    }
}

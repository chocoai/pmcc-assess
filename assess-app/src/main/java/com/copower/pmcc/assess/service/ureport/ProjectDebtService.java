package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ReportProjectDebtDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ReportProjectDebt;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.finance.api.dto.FinancialBillMakeOutProjectDto;
import com.copower.pmcc.finance.api.provider.FinanceRpcToolService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2019-8-2.
 */
@Service("projectDebtService")
public class ProjectDebtService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private FinanceRpcToolService financeRpcToolService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private InitiateConsignorService initiateConsignorService;
    @Autowired
    private InitiateUnitInformationService initiateUnitInformationService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ReportProjectDebtDao reportProjectDebtDao;
    @Autowired
    private DataNumberRuleService dataNumberRuleService;

    /**
     * 项目开票收款报表
     *
     * @param
     * @param maps
     * @return
     */
    public BeanPageDataSet getUReportProjectDebtData(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
        return getReportProjectDebtData(maps);
    }

    public BeanPageDataSet getReportProjectDebtData(Map<String, Object> maps) {
        String queryProjectName = "";
        String queryConsignorName = "";
        String queryReportUseUnitName = "";
        String preauditReportNumber = "";
        String resultReportNumber = "";
        String queryUserAccount = "";
        String queryEntrustPurposeName = "";
        String queryLoanTypeName = "";
        String queryDepartmentName = "";

        Integer pageIndex = objectToInteger(maps.get("_pageIndex"));
        Integer fixRows = objectToInteger(maps.get("_fixRows"));
        if (maps.get("queryProjectName") != null) {
            queryProjectName = (String) maps.get("queryProjectName");
        }
        if (maps.get("queryConsignorName") != null) {
            queryConsignorName = (String) maps.get("queryConsignorName");
        }
        if (maps.get("queryReportUseUnitName") != null) {
            queryReportUseUnitName = (String) maps.get("queryReportUseUnitName");
        }
        if (maps.get("preauditReportNumber") != null) {
            preauditReportNumber = (String) maps.get("preauditReportNumber");
        }
        if (maps.get("resultReportNumber") != null) {
            resultReportNumber = (String) maps.get("resultReportNumber");
        }

        if (maps.get("queryUserAccount") != null) {
            queryUserAccount = (String) maps.get("queryUserAccount");
        }
        if (maps.get("queryEntrustPurposeName") != null) {
            queryEntrustPurposeName = (String) maps.get("queryEntrustPurposeName");
        }
        if (maps.get("queryLoanTypeName") != null) {
            queryLoanTypeName = (String) maps.get("queryLoanTypeName");
        }
        if (maps.get("queryDepartmentName") != null) {
            queryDepartmentName = (String) maps.get("queryDepartmentName");
        }

        Page<PageInfo> page = PageHelper.startPage(pageIndex, fixRows);
        List<ReportProjectDebt> list = reportProjectDebtDao.getReportProjectDebtList(queryProjectName, queryConsignorName, queryReportUseUnitName, preauditReportNumber, resultReportNumber, queryUserAccount, queryEntrustPurposeName
                , queryLoanTypeName, queryDepartmentName);

        return new BeanPageDataSet(list, page.getPages());
    }

    //初始化数据
    public void init() {
        //比较项目表id集合与欠款表的projectId集合
        //id集合有差值时，则写入数据
        ProjectInfo projectInfo = new ProjectInfo();
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(projectInfo);
        List<Integer> projectInfoIds = LangUtils.transform(projectInfoList, o -> o.getId());

        ReportProjectDebt projectDebt = new ReportProjectDebt();
        List<ReportProjectDebt> reportProjectDebtList = reportProjectDebtDao.getReportProjectDebt(projectDebt);
        List<Integer> projectDebtIds = LangUtils.transform(reportProjectDebtList, o -> o.getProjectId());
        //差值
        projectInfoIds.removeAll(projectDebtIds);
        if (CollectionUtils.isNotEmpty(projectInfoIds)) {
            //预评报告
            BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
            Integer preauditId = preauditReport.getId();
            //结果报告
            BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
            Integer resultId = resultReport.getId();

            List<ProjectInfo> projectInfoByProjectIds = projectInfoService.getProjectInfoByProjectIds(projectInfoIds);
            for (ProjectInfo projectItem : projectInfoByProjectIds) {
                ReportProjectDebt reportProjectDebt = new ReportProjectDebt();
                reportProjectDebt.setProjectId(projectItem.getId());
                reportProjectDebt.setProjectName(projectItem.getProjectName());
                reportProjectDebt.setPublicProjectId(projectItem.getPublicProjectId());
                //合同金额
                reportProjectDebt.setContractPrice(projectItem.getContractPrice());
                //委托人
                InitiateConsignorVo consignorVo = initiateConsignorService.getDataByProjectId(projectItem.getId());
                if (consignorVo != null) {
                    reportProjectDebt.setConsignorName(consignorVo.getCsName());
                }
                //委托目的
                if (projectItem.getEntrustPurpose() != null) {
                    reportProjectDebt.setEntrustPurposeName(baseDataDicService.getNameById(projectItem.getEntrustPurpose()));
                }
                //部门
                if (projectItem.getDepartmentId() != null) {
                    reportProjectDebt.setDepartmentName(erpRpcDepartmentService.getDepartmentById(projectItem.getDepartmentId()).getName());
                }
                //贷款类型
                if (projectItem.getLoanType() != null) {
                    reportProjectDebt.setLoanTypeName(baseDataDicService.getNameById(projectItem.getLoanType()));
                }
                //报告使用单位
                InitiateUnitInformationVo initiateUnitInformationVo = initiateUnitInformationService.getDataByProjectId(projectItem.getId());
                if (initiateUnitInformationVo != null) {
                    reportProjectDebt.setReportUseUnitName(initiateUnitInformationVo.getuUseUnitName());
                }
                //预评报告文号
                List<Integer> sameGroupPreauditType = dataNumberRuleService.getSameGroupReportType(preauditId);
                List<String> preauditList = Lists.newArrayList();
                for (Integer type : sameGroupPreauditType) {
                    preauditList.addAll(projectNumberRecordService.getReportNumberList(projectItem.getId(), type));

                }
                if (CollectionUtils.isNotEmpty(preauditList)) {
                    reportProjectDebt.setPreauditNumber(StringUtils.join(preauditList, ","));
                }
                //结果报告文号
                List<Integer> sameGroupResultType = dataNumberRuleService.getSameGroupReportType(resultId);
                List<String> resultIdList = Lists.newArrayList();
                for (Integer resultType : sameGroupResultType) {
                    resultIdList.addAll(projectNumberRecordService.getReportNumberList(projectItem.getId(), resultType));

                }
                if (CollectionUtils.isNotEmpty(resultIdList)) {
                    reportProjectDebt.setResultNumber(StringUtils.join(resultIdList, ","));
                }
                //项目经理
                String projectManager = projectMemberService.getProjectManager(projectItem.getId());
                if (StringUtil.isNotEmpty(projectManager)) {
                    SysUserDto sysUser = erpRpcUserService.getSysUser(projectManager);
                    reportProjectDebt.setProjectManagerName(sysUser.getUserName());
                }
                List<Integer> publicProjectIds = Lists.newArrayList();
                publicProjectIds.add(projectItem.getPublicProjectId());
                List<FinancialBillMakeOutProjectDto> makeOutList = null;
                try {
                    makeOutList = financeRpcToolService.getProjectBillMakeOutList(LangUtils.filter(publicProjectIds, o -> o.intValue() > 0));
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                if (projectItem.getPublicProjectId() != null && CollectionUtils.isNotEmpty(makeOutList)) {
                    BigDecimal amount = new BigDecimal("0");
                    BigDecimal actualAmount = new BigDecimal("0");
                    BigDecimal payAmount = new BigDecimal("0");
                    for (FinancialBillMakeOutProjectDto makeOutProjectDto : makeOutList) {
                        if (projectItem.getPublicProjectId().equals(makeOutProjectDto.getProjectId())) {
                            if (makeOutProjectDto.getAmount() != null)
                                amount = amount.add(objectToBigDecimal(makeOutProjectDto.getAmount() / 100L));
                            if (makeOutProjectDto.getActualAmount() != null)
                                actualAmount = actualAmount.add(objectToBigDecimal(makeOutProjectDto.getActualAmount() / 100L));
                            if (makeOutProjectDto.getPayAmount() != null)
                                payAmount = payAmount.add(objectToBigDecimal(makeOutProjectDto.getPayAmount().divide(new BigDecimal("100"))));
                        }
                    }
                    //开票金额
                    reportProjectDebt.setAmount(amount);
                    //实际开票金额
                    reportProjectDebt.setActualAmount(actualAmount);
                    //付款金额
                    reportProjectDebt.setPayAmount(payAmount);
                    if (projectItem.getContractPrice() != null) {
                        //欠款金额
                        reportProjectDebt.setDebtAmount(projectItem.getContractPrice().subtract(payAmount));
                        //欠款金额>0 则为欠款状态
                        if (reportProjectDebt.getDebtAmount().compareTo(new BigDecimal("0")) > 0) {
                            reportProjectDebt.setBisHasDebt(true);
                        } else {
                            reportProjectDebt.setBisHasDebt(false);
                        }
                    }
                }
                reportProjectDebtDao.addReportProjectDebt(reportProjectDebt);
            }
        }
    }


    private String objectToString(Object obj) {
        if (obj == null) return "";
        return StringUtils.defaultString(String.valueOf(obj));
    }

    private Integer objectToInteger(Object obj) {
        String string = objectToString(obj);
        if (StringUtils.isNotBlank(string) && StringUtils.isNumeric(string))
            return Integer.valueOf(string);
        return 0;
    }

    private BigDecimal objectToBigDecimal(Object obj) {
        String string = objectToString(obj);
        if (StringUtils.isNotBlank(string) && NumberUtils.isNumber(string))
            return new BigDecimal(string);
        return new BigDecimal("0");
    }

}

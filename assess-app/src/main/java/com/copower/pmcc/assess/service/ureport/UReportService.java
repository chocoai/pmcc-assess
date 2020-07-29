package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dto.output.ureport.UProjectFinanceVo;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2019-8-2.
 */
@Service("uReportService")
public class UReportService {
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
    private ProjectNumberRecordDao projectNumberRecordDao;

    /**
     * 项目开票收款报表
     *
     * @param dsname
     * @param datasetName
     * @param maps
     * @return
     */
    public BeanPageDataSet getUProjectFinanceVoList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
        return getProjectFinanceDataSet(maps, null);
    }

    public BeanPageDataSet getProjectFinanceDataSet(Map<String, Object> maps, String userAccount) {
        String queryProjectName = "";
        String queryConsignorName = "";
        String queryReportUseUnitName = "";
        String queryReportNumber = "";
        String queryStartTime = "";
        String queryEndTime = "";
        String queryUserAccount = "";
        String queryServiceExplain = "";
        Integer queryEntrustment = null;
        Integer queryLoanType = null;
        Integer queryDepartmentId = null;
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
        if (maps.get("queryReportNumber") != null) {
            queryReportNumber = (String) maps.get("queryReportNumber");
        }
        if (maps.get("queryStartTime") != null) {
            queryStartTime = (String) maps.get("queryStartTime");
        }
        if (maps.get("queryEndTime") != null) {
            queryEndTime = (String) maps.get("queryEndTime");
        }
        if (maps.get("queryUserAccount") != null) {
            queryUserAccount = (String) maps.get("queryUserAccount");
        }
        if (maps.get("queryServiceExplain") != null) {
            queryServiceExplain = (String) maps.get("queryServiceExplain");
        }
        if (maps.get("queryEntrustment") != null) {
            queryEntrustment = objectToInteger(maps.get("queryEntrustment"));
        }
        if (maps.get("queryLoanType") != null) {
            queryLoanType = objectToInteger(maps.get("queryLoanType"));
        }
        if (maps.get("queryDepartmentId") != null) {
            queryDepartmentId = objectToInteger(maps.get("queryDepartmentId"));
        }
        //预评报告
        BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        Integer preauditId = preauditReport.getId();
        //技术报告
        BaseDataDic technologyReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
        Integer technologyId = technologyReport.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();
        //咨评报告
        BaseDataDic consultationReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_CONSULTATION);
        Integer consultationId = consultationReport.getId();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id,A.number_value,A.gmt_created,A.project_id,A.report_type,P.public_project_id,P.project_name,P.contract_name,P.contract_price,P.entrust_purpose,P.loan_type,P.department_id,P.service_come_from_explain," +
                " P.gmt_created as projectCreated,B.user_account_manager,C.cs_entrustment_unit,C.cs_name,D.u_use_unit" +
                " FROM tb_project_number_record A " +
                " LEFT JOIN tb_project_info P ON P.id=A.project_id" +
                " LEFT JOIN tb_project_member B ON B.project_id=A.project_id" +
                " LEFT JOIN tb_initiate_consignor C ON C.project_id=A.project_id" +
                " LEFT JOIN tb_initiate_unit_information D ON D.project_id=A.project_id");

        sql.append(" WHERE 1=1 AND A.bis_delete = 0");

        if (StringUtil.isNotEmpty(queryProjectName)) {
            sql.append(String.format(" AND P.project_name LIKE '%s%s%s'", "%", queryProjectName, "%"));
        }
        if (queryEntrustment != null && !queryEntrustment.equals(0)) {
            sql.append(String.format(" AND P.entrust_purpose = '%s'", queryEntrustment));
        }
        if (queryLoanType != null && !queryLoanType.equals(0)) {
            sql.append(String.format(" AND P.loan_type = '%s'", queryLoanType));
        }
        if (queryDepartmentId != null && !queryDepartmentId.equals(0)) {
            sql.append(String.format(" AND P.department_id = '%s'", queryDepartmentId));
        }

        if (StringUtil.isNotEmpty(queryConsignorName)) {
            sql.append(String.format(" AND (C.cs_entrustment_unit LIKE '%s%s%s' OR C.cs_name LIKE '%s%s%s')", "%", queryConsignorName, "%", "%", queryConsignorName, "%"));
        }
        if (StringUtil.isNotEmpty(queryReportUseUnitName)) {
            sql.append(String.format(" AND D.u_use_unit_name LIKE '%s%s%s'","%", queryReportUseUnitName,"%"));
        }
        if (StringUtil.isNotEmpty(queryReportNumber)) {
            sql.append(String.format(" AND A.number_value LIKE '%s%s%s'", "%", queryReportNumber, "%"));
        }
        if (StringUtil.isNotEmpty(queryStartTime)) {
            sql.append(String.format(" AND Date(A.gmt_created) >= '%s'", queryStartTime));
        }
        if (StringUtil.isNotEmpty(queryEndTime)) {
            sql.append(String.format(" AND Date(A.gmt_created) <= '%s'", queryEndTime));
        }
        if (StringUtils.isNotBlank(userAccount)) {
            sql.append(String.format(" AND B.user_account_manager = '%s'", userAccount));
        } else {
            if (StringUtil.isNotEmpty(queryUserAccount)) {
                sql.append(String.format(" AND B.user_account_manager = '%s'", queryUserAccount));
            }
        }
        if (StringUtil.isNotEmpty(queryServiceExplain)) {
            sql.append(String.format(" AND P.service_come_from_explain LIKE '%s%s%s'", "%", queryServiceExplain, "%"));
        }
        sql.append(" GROUP BY A.project_id");
        List<UProjectFinanceVo> list = Lists.newArrayList();
        Page<PageInfo> page = PageHelper.startPage(pageIndex, fixRows);
        List<Map> mapList = ddlMySqlAssist.customTableSelect(sql.toString());
        if (CollectionUtils.isNotEmpty(mapList)) {
            List<Integer> publicProjectIds = LangUtils.transform(mapList, o -> {
                String idString = objectToString(o.get("public_project_id"));
                if (StringUtils.isNotEmpty(idString)) return Integer.valueOf(idString);
                return 0;
            });
            List<FinancialBillMakeOutProjectDto> makeOutList = null;
            try {
                makeOutList = financeRpcToolService.getProjectBillMakeOutList(LangUtils.filter(publicProjectIds, o -> o.intValue() > 0));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            for (Map map : mapList) {
                try {
                    UProjectFinanceVo vo = new UProjectFinanceVo();
                    vo.setId(objectToInteger(map.get("id")));
                    Object projectCreated = map.get("projectCreated");
                    if (projectCreated != null) {
                        vo.setProjectCreated(DateUtils.convertDate(String.valueOf(projectCreated)));
                    }
                    Object reportNumberCreated = map.get("gmt_created");
                    if (reportNumberCreated != null) {
                        vo.setReportNumberCreated(DateUtils.convertDate(String.valueOf(reportNumberCreated)));
                    }
                    vo.setProjectName(objectToString(map.get("project_name")));
                    vo.setServiceComeFromExplain(objectToString(map.get("service_come_from_explain")));
                    //委托目的
                    vo.setEntrustPurposeName(baseDataDicService.getNameById(objectToString(map.get("entrust_purpose"))));
                    //贷款类型
                    vo.setLoanTypeName(baseDataDicService.getNameById(objectToString(map.get("loan_type"))));
                    //评估部门
                    String departmentId = objectToString(map.get("department_id"));
                    if (StringUtils.isNotEmpty(departmentId))
                        vo.setDepartmentName(erpRpcDepartmentService.getDepartmentById(Integer.valueOf(departmentId)).getName());

                    vo.setProjectName(objectToString(map.get("project_name")));

                    String userAccountManager = objectToString(map.get("user_account_manager"));
                    if (StringUtil.isNotEmpty(userAccountManager)) {
                        SysUserDto sysUser = erpRpcUserService.getSysUser(userAccountManager);
                        userAccountManager = sysUser.getUserName();
                    }
                    vo.setProjectManagerName(userAccountManager);
                    String consignorName = objectToString(map.get("cs_entrustment_unit"));
                    if (StringUtil.isEmpty(consignorName)) {
                        consignorName = objectToString(map.get("cs_name"));
                    }
                    vo.setConsignorName(consignorName);
                    String useUnit = objectToString(map.get("u_use_unit"));
                    if (StringUtil.isNotEmpty(useUnit)) {
                        CrmCustomerDto customer = crmRpcCustomerService.getCustomer(Integer.valueOf(useUnit));
                        if (customer != null) {
                            useUnit = customer.getName();
                        }
                    }
                    vo.setReportUseUnitName(useUnit);
                    //报告编号
                    Integer projectId = Integer.valueOf(objectToString(map.get("project_id")));
                    ProjectNumberRecord where = new ProjectNumberRecord();
                    where.setBisDelete(false);
                    where.setProjectId(projectId);
                    List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(where);
                    StringBuilder strPreaudit = new StringBuilder();
                    StringBuilder strTechnology = new StringBuilder();
                    StringBuilder strResult = new StringBuilder();

                    if (CollectionUtils.isNotEmpty(numberList)) {
                        for (ProjectNumberRecord item : numberList) {
                            if (item.getReportType().equals(preauditId)) {
                                strPreaudit.append(item.getNumberValue()).append("/");
                            }
                            if (item.getReportType().equals(technologyId)) {
                                strTechnology.append(item.getNumberValue()).append("/");
                            }
                            if (item.getReportType().equals(resultId) || item.getReportType().equals(consultationId)) {
                                strResult.append(item.getNumberValue()).append("/");
                            }
                        }
                    }
                    if (StringUtils.isNotEmpty(strPreaudit.toString())) {
                        vo.setPreauditNumber(strPreaudit.deleteCharAt(strPreaudit.length() - 1).toString());
                    }
                    if (StringUtils.isNotEmpty(strTechnology.toString())) {
                        vo.setTechnologyNumber(strTechnology.deleteCharAt(strTechnology.length() - 1).toString());
                    }
                    if (StringUtils.isNotEmpty(strResult.toString())) {
                        vo.setResultNumber(strResult.deleteCharAt(strResult.length() - 1).toString());
                    }

                    vo.setContractName(objectToString(map.get("contract_name")));
                    vo.setContractPrice(objectToBigDecimal(map.get("contract_price")));
                    Integer publicProjectId = objectToInteger(map.get("public_project_id"));
                    if (publicProjectId != null && CollectionUtils.isNotEmpty(makeOutList)) {
                        BigDecimal amount = new BigDecimal("0");
                        BigDecimal actualAmount = new BigDecimal("0");
                        BigDecimal payAmount = new BigDecimal("0");
                        for (FinancialBillMakeOutProjectDto makeOutProjectDto : makeOutList) {
                            if (publicProjectId.equals(makeOutProjectDto.getProjectId())) {
                                if (makeOutProjectDto.getAmount() != null)
                                    amount = amount.add(objectToBigDecimal(makeOutProjectDto.getAmount() / 100L));
                                if (makeOutProjectDto.getActualAmount() != null)
                                    actualAmount = actualAmount.add(objectToBigDecimal(makeOutProjectDto.getActualAmount() / 100L));
                                if (makeOutProjectDto.getPayAmount() != null)
                                    payAmount = payAmount.add(objectToBigDecimal(makeOutProjectDto.getPayAmount() / 100L));
                            }
                        }
                        vo.setAmount(amount);
                        vo.setActualAmount(actualAmount);
                        vo.setPayAmount(payAmount);
                    }
                    if (objectToBigDecimal(map.get("contract_price")) != null) {
                        vo.setDebtAmount(objectToBigDecimal(map.get("contract_price")).subtract(vo.getPayAmount() == null ? new BigDecimal("0") : vo.getPayAmount()));
                    }
                    list.add(vo);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        }
        return new BeanPageDataSet(list, page.getPages());
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

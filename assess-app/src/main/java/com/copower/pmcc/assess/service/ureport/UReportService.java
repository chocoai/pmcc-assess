package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.ureport.UProjectFinanceVo;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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

    /**
     * 项目开票收款报表
     *
     * @param dsname
     * @param datasetName
     * @param maps
     * @return
     */
    public BeanPageDataSet getUProjectFinanceVoList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
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
        //咨评报告
        BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT_CONSULTATION);
        Integer preauditId = preauditReport.getId();
        //技术报告
        BaseDataDic technologyReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_TECHNOLOGY);
        Integer technologyId = technologyReport.getId();
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        Integer resultId = resultReport.getId();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id,A.public_project_id,A.project_name,A.contract_name,A.contract_price,A.entrust_purpose,A.loan_type,A.department_id,A.service_come_from_explain," +
                "B.user_account_manager,C.cs_entrustment_unit,C.cs_name,D.u_use_unit," +
                " E.number_value as preaudit_number,F.number_value as technology_number,G.number_value as result_number,A.gmt_created" +
                " FROM tb_project_info A " +
                " LEFT JOIN tb_project_member B ON A.id=B.project_id" +
                " LEFT JOIN tb_initiate_consignor C ON A.id=C.project_id" +
                " LEFT JOIN tb_initiate_unit_information D ON A.id=D.project_id");
        sql.append(String.format(" LEFT JOIN tb_project_number_record E ON (A.id=E.project_id and E.report_type = %s)", preauditId));
        sql.append(String.format(" LEFT JOIN tb_project_number_record F ON (A.id=F.project_id and F.report_type = %s)", technologyId));
        sql.append(String.format(" LEFT JOIN tb_project_number_record G ON (A.id=G.project_id and G.report_type = %s)", resultId));
        sql.append(" WHERE 1=1");

        if (StringUtil.isNotEmpty(queryProjectName)) {
            sql.append(String.format(" AND A.project_name LIKE '%s%s%s'", "%", queryProjectName, "%"));
        }
        if (queryEntrustment != null && !queryEntrustment.equals(0)) {
            sql.append(String.format(" AND A.entrust_purpose = '%s'", queryEntrustment));
        }
         if (queryLoanType != null && !queryLoanType.equals(0)) {
            sql.append(String.format(" AND A.loan_type = '%s'", queryLoanType));
        }
         if (queryDepartmentId != null&& !queryDepartmentId.equals(0)) {
            sql.append(String.format(" AND A.department_id = '%s'", queryDepartmentId));
        }

        if (StringUtil.isNotEmpty(queryConsignorName)) {
            sql.append(String.format(" AND (C.cs_entrustment_unit LIKE '%s%s%s' OR C.cs_name LIKE '%s%s%s')", "%", queryConsignorName, "%", "%", queryConsignorName, "%"));
        }
        if (StringUtil.isNotEmpty(queryReportUseUnitName)) {
            sql.append(String.format(" AND D.u_use_unit = %s", queryReportUseUnitName));
        }
        if (StringUtil.isNotEmpty(queryReportNumber)) {
            sql.append(String.format(" AND E.number_value LIKE '%s%s%s'", "%", queryReportNumber, "%"));
            sql.append(String.format(" OR F.number_value LIKE '%s%s%s'", "%", queryReportNumber, "%"));
            sql.append(String.format(" OR G.number_value LIKE '%s%s%s'", "%", queryReportNumber, "%"));
        }
        if (StringUtil.isNotEmpty(queryStartTime)) {
            sql.append(String.format(" AND Date(A.gmt_created) >= '%s'", queryStartTime));
        }
        if (StringUtil.isNotEmpty(queryEndTime)) {
            sql.append(String.format(" AND Date(A.gmt_created) <= '%s'", queryEndTime));
        }
        if (StringUtil.isNotEmpty(queryUserAccount)) {
            sql.append(String.format(" AND B.user_account_manager = '%s'", queryUserAccount));
        }
        if (StringUtil.isNotEmpty(queryServiceExplain)) {
            sql.append(String.format(" AND A.service_come_from_explain LIKE '%s%s%s'", "%", queryServiceExplain, "%"));
        }

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
            Map<Integer, FinancialBillMakeOutProjectDto> mapFinance = null;
            if (CollectionUtils.isNotEmpty(makeOutList)) {
                mapFinance = FormatUtils.mappingSingleEntity(makeOutList, o -> o.getProjectId());
            }
            for (Map map : mapList) {
                UProjectFinanceVo vo = new UProjectFinanceVo();
                vo.setId(objectToInteger(map.get("id")));
                vo.setProjectName(objectToString(map.get("project_name")));
                vo.setServiceComeFromExplain(objectToString(map.get("service_come_from_explain")));
                //委托目的
                vo.setEntrustPurposeName(baseDataDicService.getNameById(objectToString(map.get("entrust_purpose"))));
                //贷款类型
                vo.setLoanTypeName(baseDataDicService.getNameById(objectToString(map.get("loan_type"))));
                //评估部门
                String departmentId = objectToString(map.get("department_id"));
                if(StringUtils.isNotEmpty(departmentId))
                vo.setDepartmentName(erpRpcDepartmentService.getDepartmentById(Integer.valueOf(departmentId)).getName());

                vo.setProjectName(objectToString(map.get("project_name")));
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
                vo.setPreauditNumber(objectToString(map.get("preaudit_number")));
                vo.setTechnologyNumber(objectToString(map.get("technology_number")));
                vo.setResultNumber(objectToString(map.get("result_number")));
                Object gmt_created = map.get("gmt_created");
                if (gmt_created != null) {
                    vo.setProjectCreated(DateUtils.convertDate(String.valueOf(gmt_created)));
                }
                vo.setContractName(objectToString(map.get("contract_name")));
                vo.setContractPrice(objectToString(map.get("contract_price")));
                Integer publicProjectId = objectToInteger(map.get("id"));
                if (mapFinance != null && mapFinance.get(publicProjectId) != null) {
                    FinancialBillMakeOutProjectDto makeOutProjectDto = mapFinance.get(publicProjectId);
                    if (makeOutProjectDto.getAmount() != null) {
                        vo.setAmount(objectToString(makeOutProjectDto.getAmount() / 100L));
                    }
                    if (makeOutProjectDto.getActualAmount() != null) {
                        vo.setActualAmount(objectToString(makeOutProjectDto.getActualAmount() / 100L));
                    }
                    if (makeOutProjectDto.getPayAmount() != null) {
                        vo.setPayAmount(objectToString(makeOutProjectDto.getPayAmount().divide(new BigDecimal("100"))));
                    }
                }
                list.add(vo);
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
}

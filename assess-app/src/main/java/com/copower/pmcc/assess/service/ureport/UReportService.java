package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.dto.output.ureport.UProjectFinanceVo;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
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
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id,A.public_project_id,A.project_name,A.contract_name,A.contract_price,B.user_account_manager,C.cs_entrustment_unit,C.cs_name,D.u_use_unit," +
                " E.number_value,E.gmt_created" +
                " FROM tb_project_info A " +
                " LEFT JOIN tb_project_member B ON A.id=B.project_id" +
                " LEFT JOIN tb_initiate_consignor C ON A.id=C.project_id" +
                " LEFT JOIN tb_initiate_unit_information D ON A.id=D.project_id" +
                " LEFT JOIN tb_project_number_record E ON A.id=E.project_id WHERE 1=1");
        if (StringUtil.isNotEmpty(queryProjectName)) {
            sql.append(String.format(" AND A.project_name LIKE '%s%s%s'", "%", queryProjectName, "%"));
        }
        if (StringUtil.isNotEmpty(queryConsignorName)) {
            sql.append(String.format(" AND C.cs_entrustment_unit LIKE '%s%s%s'", "%", queryConsignorName, "%"));
        }
        if (StringUtil.isNotEmpty(queryReportUseUnitName)) {
            sql.append(String.format(" AND D.u_use_unit = %s", queryReportUseUnitName));
        }
        if (StringUtil.isNotEmpty(queryReportNumber)) {
            sql.append(String.format(" AND E.number_value LIKE '%s%s%s'", "%", queryReportNumber, "%"));
        }
        if (StringUtil.isNotEmpty(queryStartTime)) {
            sql.append(String.format(" AND Date(E.gmt_created) >= '%s'", queryStartTime));
        }
        if (StringUtil.isNotEmpty(queryEndTime)) {
            sql.append(String.format(" AND Date(E.gmt_created) <= '%s'", queryEndTime));
        }
        List<UProjectFinanceVo> list = Lists.newArrayList();
        Page<PageInfo> page = PageHelper.startPage(pageIndex, fixRows);
        List<Map> mapList = ddlMySqlAssist.customTableSelect(sql.toString());
        if(CollectionUtils.isNotEmpty(mapList)){
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
                String userAccountManager = objectToString(map.get("user_account_manager"));
                if (StringUtil.isNotEmpty(userAccountManager)) {
                    SysUserDto sysUser = erpRpcUserService.getSysUser(userAccountManager);
                    userAccountManager = sysUser.getUserName();
                }
                vo.setProjectManagerName(userAccountManager);
                vo.setConsignorName(objectToString(map.get("cs_entrustment_unit")));
                String useUnit = objectToString(map.get("u_use_unit"));
                if (StringUtil.isNotEmpty(useUnit)) {
                    CrmCustomerDto customer = crmRpcCustomerService.getCustomer(Integer.valueOf(useUnit));
                    if (customer != null) {
                        useUnit = customer.getName();
                    }
                }
                vo.setReportUseUnitName(useUnit);
                vo.setReportNumber(objectToString(map.get("number_value")));
                Object gmt_created = map.get("gmt_created");
                if (gmt_created != null) {
                    vo.setReportNumberCreated(DateUtils.convertDate(String.valueOf(gmt_created)));
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
        return new BeanPageDataSet(list,page.getPages());
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

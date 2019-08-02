package com.copower.pmcc.assess.service.ureport;

import com.copower.pmcc.assess.dto.output.ureport.UProjectFinanceVo;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.finance.api.dto.FinancialBillMakeOutProjectDto;
import com.copower.pmcc.finance.api.provider.FinanceRpcToolService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 项目开票收款报表
     *
     * @param dsname
     * @param datasetName
     * @param maps
     * @return
     */
    public List<UProjectFinanceVo> getUProjectFinanceVoList(String dsname, String datasetName, Map<String, Object> maps) {
        String queryProjectName = "";
        String queryTitle = "";
        if (maps.get("queryProjectName") != null) {
            queryProjectName = (String) maps.get("queryProjectName");
        }
        if (maps.get("queryTitle") != null) {
            queryTitle = (String) maps.get("queryTitle");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id,A.public_project_id,A.project_name,A.contract_name,A.contract_price,B.user_account_manager,C.cs_entrustment_unit,C.cs_name,D.u_use_unit," +
                " E.number_value,E.gmt_created" +
                " FROM tb_project_info A " +
                " LEFT JOIN tb_project_member B ON A.id=B.project_id" +
                " LEFT JOIN tb_initiate_consignor C ON A.id=C.project_id" +
                " LEFT JOIN tb_initiate_unit_information D ON A.id=D.project_id" +
                " LEFT JOIN tb_project_number_record E ON A.id=E.project_id");

        List<Map> mapList = ddlMySqlAssist.customTableSelect(sql.toString());
        if (CollectionUtils.isEmpty(mapList)) return null;
        List<Integer> publicProjectIds = LangUtils.transform(mapList, o -> {
            return Integer.valueOf(String.valueOf(o.get("public_project_id")));
        });
        List<FinancialBillMakeOutProjectDto> makeOutList = financeRpcToolService.getProjectBillMakeOutList(publicProjectIds);
        if(CollectionUtils.isNotEmpty(makeOutList)){
            Map<Integer, FinancialBillMakeOutProjectDto> entity = FormatUtils.mappingSingleEntity(makeOutList, o -> o.getProjectId());
        }
        List<UProjectFinanceVo> list = Lists.newArrayList();
        for (Map map : mapList) {
            UProjectFinanceVo vo = new UProjectFinanceVo();
            vo.setId(Integer.valueOf(String.valueOf(map.get("id"))));
            vo.setProjectName(objectToString(map.get("project_name")));
            vo.setProjectManagerName(objectToString(map.get("user_account_manager")));
            vo.setConsignorName(objectToString(map.get("cs_entrustment_unit")));
            vo.setReportUseUnitName(objectToString(map.get("u_use_unit")));
            vo.setReportNumber(objectToString(map.get("number_value")));
           // vo.setReportNumberCreated(DateUtils.convertDate(String.valueOf(map.get("gmt_created"))));
            vo.setContractName(objectToString(map.get("contract_name")));
            vo.setContractPrice(objectToString(map.get("contract_price")));


            list.add(vo);
        }
        return list;
    }

    private String objectToString(Object obj){
        if(obj==null)return "";
        return StringUtils.defaultString(String.valueOf(obj));
    }
}

package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zch on 2019-11-5.
 * 财务引用评估项目列表数据
 */
@Service(value = "financialReferenceProjectService")
public class FinancialReferenceProjectService {
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseService baseService;

    /**
     * 财务引用评估项目列表数据
     *
     * @param dsname
     * @param datasetName
     * @param maps
     * @return
     * @throws Exception
     */
    public BeanPageDataSet getFinancialReferenceProjectList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
        String queryName = "", queryNumberRecord = "", queryManager = "";
        if (maps.get("queryName") != null) {
            queryName = (String) maps.get("queryName");
        }
        if (maps.get("queryNumberRecord") != null) {
            queryNumberRecord = (String) maps.get("queryNumberRecord");
        }
        if (maps.get("queryManager") != null) {
            queryManager = (String) maps.get("queryManager");
        }
        return getFinancialReferenceProjectList2(queryName, queryNumberRecord, queryManager, maps);
    }

    private BeanPageDataSet getFinancialReferenceProjectList2(String queryName, String queryNumberRecord, String queryManager, Map<String, Object> maps) throws Exception {
        List<FinancialReferenceProjectVo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(" ");

        List<String> queryList = new ArrayList<>();
        queryList.add("tb_project_info.project_name");
        queryList.add("tb_project_info.gmt_created");
        queryList.add("tb_project_info.preaudit_number_date");
        queryList.add("tb_project_info.result_number_date");

        queryList.add("tb_project_member.user_account_manager");

        queryList.add("tb_project_number_record.number_value");

        queryList.add("tb_initiate_consignor.cs_entrustment_unit");
        queryList.add("tb_initiate_consignor.cs_name");

        sql.append(String.join(",", queryList)).append(" ");


        sql.append("FROM").append(" ");
        //join 操作
        sql.append("( ( tb_project_info INNER JOIN tb_project_number_record ON tb_project_info.id = tb_project_number_record.project_id ) INNER JOIN").append(" ");
        sql.append("tb_project_member ON tb_project_info.id = tb_project_member.project_id )").append(" ");
        sql.append("INNER JOIN tb_initiate_consignor ON tb_project_info.id = tb_initiate_consignor.project_id").append(" ");
        //where 操作
        sql.append(" WHERE 1=1").append(" ");

        if (StringUtils.isNotBlank(queryName)) {
            sql.append("AND").append(" ").append("tb_project_info.project_name").append(" ").append("=").append(" '").append(queryName).append("' ");
        }
        if (StringUtils.isNotBlank(queryNumberRecord)) {
            sql.append("AND").append(" ").append("tb_project_number_record.number_value").append(" ").append("=").append(" '").append(queryNumberRecord).append("' ");
        }
        if (StringUtils.isNotBlank(queryManager)) {
            sql.append("AND").append(" ").append("tb_project_member.user_account_manager").append(" ").append("=").append(" '").append(queryManager).append("' ");
        }
        sql.append("");
        Integer pageIndex = objectToInteger(maps.get("_pageIndex"));
        Integer fixRows = objectToInteger(maps.get("_fixRows"));
        Page<PageInfo> page = PageHelper.startPage(pageIndex, fixRows);
        List<Map> mapList = ddlMySqlAssist.customTableSelect(sql.toString());
        String project_name = "project_name";
        String gmt_created = "gmt_created";
        String preaudit_number_date = "preaudit_number_date";
        String result_number_date = "result_number_date";
        String number_value = "number_value";
        String user_account_manager = "user_account_manager";
        String cs_entrustment_unit = "cs_entrustment_unit";
        String cs_name = "cs_name";
        for (Map map : mapList) {
            FinancialReferenceProjectVo vo = getFinancialReferenceProjectVo();

            try {
                if (map.get(project_name) != null) {
                    vo.setProjectName((String) map.get(project_name));
                }
                if (map.get(gmt_created) != null) {
                    vo.setGmtCreated(getDateValue(map.get(gmt_created)));
                }
                if (map.get(result_number_date) != null) {
                    vo.setResultNumberDate(getDateValue(map.get(result_number_date)));
                }
                if (map.get(preaudit_number_date) != null) {
                    vo.setPreauditNumberDate(getDateValue(map.get(preaudit_number_date)));
                }
                if (map.get(number_value) != null) {
                    vo.setNumberValue((String) map.get(number_value));
                }
                if (map.get(user_account_manager) != null) {
                    String value = (String) map.get(user_account_manager);
                    if (StringUtils.isNotBlank(value)) {
                        SysUserDto sysUser = erpRpcUserService.getSysUser(value);
                        if (sysUser != null) {
                            vo.setUserAccountManagerName(sysUser.getUserName());
                        }
                    }
                }
                if (map.get(cs_entrustment_unit) != null) {
                    vo.setCsEntrustmentUnit((String) map.get(cs_entrustment_unit));
                }
                if (map.get(cs_name) != null) {
                    vo.setCsName((String) map.get(cs_name));
                }
            } catch (Exception e) {
                baseService.writeExceptionInfo(e);
            }
            list.add(vo);

        }
        int pageNum = page.getPages();
        if (pageNum == 0) {
            pageNum = list.size();
        }
        return new BeanPageDataSet(list, pageNum);
    }

    private String getDateValue(Object value) {
        Date date = null;
        try {
            date = (Date) value;
            return DateUtils.format(date, DateUtils.DATE_CHINESE_PATTERN);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return "";
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

    public class FinancialReferenceProjectVo implements Serializable {
        private String projectName;
        private String userAccountManagerName;
        private String numberValue;
        private String csEntrustmentUnit;
        private String csName;
        private String gmtCreated;
        private String resultNumberDate;
        private String preauditNumberDate;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getUserAccountManagerName() {
            return userAccountManagerName;
        }

        public void setUserAccountManagerName(String userAccountManagerName) {
            this.userAccountManagerName = userAccountManagerName;
        }


        public String getCsEntrustmentUnit() {
            return csEntrustmentUnit;
        }

        public void setCsEntrustmentUnit(String csEntrustmentUnit) {
            this.csEntrustmentUnit = csEntrustmentUnit;
        }

        public String getCsName() {
            return csName;
        }

        public void setCsName(String csName) {
            this.csName = csName;
        }

        public String getGmtCreated() {
            return gmtCreated;
        }

        public void setGmtCreated(String gmtCreated) {
            this.gmtCreated = gmtCreated;
        }

        public String getResultNumberDate() {
            return resultNumberDate;
        }

        public void setResultNumberDate(String resultNumberDate) {
            this.resultNumberDate = resultNumberDate;
        }

        public String getPreauditNumberDate() {
            return preauditNumberDate;
        }

        public void setPreauditNumberDate(String preauditNumberDate) {
            this.preauditNumberDate = preauditNumberDate;
        }

        public String getNumberValue() {
            return numberValue;
        }

        public void setNumberValue(String numberValue) {
            this.numberValue = numberValue;
        }
    }

    public FinancialReferenceProjectVo getFinancialReferenceProjectVo() {
        return new FinancialReferenceProjectVo();
    }

}

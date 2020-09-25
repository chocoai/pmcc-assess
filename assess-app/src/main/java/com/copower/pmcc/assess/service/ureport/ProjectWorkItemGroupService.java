package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dto.output.ureport.ProjectWorkItemVo;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.DateUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2019-8-2.
 */
@Service("projectWorkItemGroupService")
public class ProjectWorkItemGroupService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private ErpRpcUserService erpRpcUserService;


    /**
     * 项目工作事项报表
     *
     * @param dsname
     * @param datasetName
     * @param maps
     * @return
     */
    public BeanPageDataSet getProjectWorkItemVoList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
        String queryProjectName = "";
        String queryWorkStageName = "";
        String queryPhaseName = "";
        String queryUserAccount = "";
        String queryExecuteUserAccount = "";
        String queryStartTime = "";
        String queryEndTime = "";

        Integer pageIndex = objectToInteger(maps.get("_pageIndex"));
        Integer fixRows = objectToInteger(maps.get("_fixRows"));
        if (maps.get("queryProjectName") != null) {
            queryProjectName = (String) maps.get("queryProjectName");
        }
        if (maps.get("queryWorkStageName") != null) {
            queryWorkStageName = (String) maps.get("queryWorkStageName");
        }
        if (maps.get("queryPhaseName") != null) {
            queryPhaseName = (String) maps.get("queryPhaseName");
        }
        if (maps.get("queryUserAccount") != null) {
            queryUserAccount = (String) maps.get("queryUserAccount");
        }
        if (maps.get("queryExecuteUserAccount") != null) {
            queryExecuteUserAccount = (String) maps.get("queryExecuteUserAccount");
        }
        if (maps.get("queryStartTime") != null) {
            queryStartTime = (String) maps.get("queryStartTime");
        }
        if (maps.get("queryEndTime") != null) {
            queryEndTime = (String) maps.get("queryEndTime");
        }
        Date endTimeParse = null;
        if (StringUtil.isNotEmpty(queryEndTime)) {
            endTimeParse = DateUtils.parse(queryEndTime);
            endTimeParse = DateUtils.addDay(endTimeParse, 1);
            queryEndTime = DateUtils.format(endTimeParse);
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT A.id,A.project_id,A.project_phase_name,sum(A.actual_hours) as actual_hours,A.status,A.task_submit_time,A.execute_user_account," +
                " B.project_name,C.work_stage_name,D.user_account_manager FROM tb_project_plan_details A" +
                " LEFT JOIN tb_project_info B ON A.project_id=B.id" +
                " LEFT JOIN tb_project_work_stage C ON A.project_work_stage_id=C.id" +
                " LEFT JOIN tb_project_member D ON A.project_id=D.project_id" +
                " WHERE A.actual_hours is not null");
        if (StringUtil.isNotEmpty(queryProjectName)) {
            sql.append(String.format(" AND B.project_name LIKE '%s%s%s'", "%", queryProjectName, "%"));
        }
        if (StringUtil.isNotEmpty(queryWorkStageName)) {
            sql.append(String.format(" AND C.work_stage_name LIKE '%s%s%s'", "%", queryWorkStageName, "%"));
        }
        if (StringUtil.isNotEmpty(queryPhaseName)) {
            sql.append(String.format(" AND A.project_phase_name LIKE '%s%s%s'", "%", queryPhaseName, "%"));
        }
        if (StringUtil.isNotEmpty(queryUserAccount)) {
            sql.append(String.format(" AND D.user_account_manager LIKE '%s%s%s'", "%", queryUserAccount, "%"));
        }
        if (StringUtil.isNotEmpty(queryExecuteUserAccount)) {
            sql.append(String.format(" AND A.execute_user_account LIKE '%s%s%s'", "%", queryExecuteUserAccount, "%"));
        }
        if (StringUtil.isNotEmpty(queryStartTime)) {
            sql.append(String.format(" AND Date(A.task_submit_time) >= '%s'", queryStartTime));
        }
        if (StringUtil.isNotEmpty(queryEndTime)) {
            sql.append(String.format(" AND Date(A.task_submit_time) < '%s'", queryEndTime));
        }
        sql.append(" GROUP BY A.project_id ORDER BY  A.project_id desc") ;
        List<ProjectWorkItemVo> list = Lists.newArrayList();
        Page<PageInfo> page = PageHelper.startPage(pageIndex, fixRows);
        List<Map> mapList = ddlMySqlAssist.customTableSelect(sql.toString());
        if (CollectionUtils.isNotEmpty(mapList)) {
            for (Map map : mapList) {
                ProjectWorkItemVo vo = new ProjectWorkItemVo();
                vo.setId(objectToInteger(map.get("id")));
                vo.setProjectPhaseName(objectToString(map.get("project_phase_name")));
                vo.setActualHours(getActualHoursFormat(objectToString(map.get("actual_hours"))));
                vo.setStatus(ProjectStatusEnum.getNameByKey(objectToString(map.get("status"))));
                Object task_submit_time = map.get("task_submit_time");
                if (task_submit_time != null) {
                    vo.setTaskSubmitTime(DateUtils.convertDate(String.valueOf(task_submit_time)));
                }

                String executeUserAccount = objectToString(map.get("execute_user_account"));
                if (StringUtil.isNotEmpty(executeUserAccount)) {
                    SysUserDto sysUser = erpRpcUserService.getSysUser(executeUserAccount);
                    executeUserAccount = sysUser.getUserName();
                }
                vo.setExecuteUserAccount(executeUserAccount);
                vo.setProjectName(objectToString(map.get("project_name")));
                vo.setWorkStageName(objectToString(map.get("work_stage_name")));

                String userAccountManager = objectToString(map.get("user_account_manager"));
                if (StringUtil.isNotEmpty(userAccountManager)) {
                    SysUserDto sysUser = erpRpcUserService.getSysUser(userAccountManager);
                    userAccountManager = sysUser.getUserName();
                }
                vo.setUserAccountManager(userAccountManager);
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

    //获取xx小时xx分钟格式
    public String getActualHoursFormat(String actualHours) {
        String hours = actualHours.substring(0, actualHours.indexOf("."));
        String minutesStr = String.format("%s%s", "0.", actualHours.substring(actualHours.indexOf(".") + 1));
        BigDecimal minutesBigDecimalVal = new BigDecimal(minutesStr);
        String minutes = minutesBigDecimalVal.multiply(new BigDecimal("60")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        if (minutesBigDecimalVal.compareTo(new BigDecimal("0")) == 0) {
            return String.format("%s小时", hours);
        }
        if (new BigDecimal(hours).compareTo(new BigDecimal("0")) == 0) {
            return String.format("%s分钟", minutes);
        }
        return String.format("%s小时%s分钟", hours, minutes);

    }
}

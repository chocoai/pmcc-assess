package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-5-29.
 */
@Service
public class PublicService {
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取当前公司
     * @return
     */
    public SysDepartmentDto getCurrentCompany(){
        return erpRpcDepartmentService.getDepartmentAssess();
    }

    /**
     * 获取到说明的视图信息
     *
     * @param title
     * @param message
     * @return
     */
    public ModelAndView getExplainPage(String title, String message) {
        ModelAndView modelAndView = new ModelAndView("/base/explainPage");
        modelAndView.addObject("title", title);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    /**
     * 获取用户名称by 账号
     *
     * @param account
     * @return
     */
    public String getUserNameByAccount(String account) {
        if (StringUtils.isBlank(account)) return "";
        List<String> list = FormatUtils.transformString2List(account);
        List<SysUserDto> sysUserList = erpRpcUserService.getSysUserList(list);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            return FormatUtils.transformListString(LangUtils.transform(sysUserList, p -> p.getUserName()));
        }
        return "";
    }

    public String getUserNameByAccountList(List<String> list) {
        if (CollectionUtils.isEmpty(list)) return "";
        List<SysUserDto> sysUserList = erpRpcUserService.getSysUserList(list);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            return FormatUtils.transformListString(LangUtils.transform(sysUserList, p -> p.getUserName()));
        }
        return "";
    }

    /**
     * 提取模板中的字段为json格式字符串
     *
     * @param template
     * @return
     */
    public String extractField(String template) {
        String regex = "\\{(.*?)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        List<Map<String, String>> maps = Lists.newArrayList();
        while (m.find()) {
            Map<String, String> map = Maps.newHashMap();
            String result = m.group();
            map.put("key", result.replaceAll("^\\{|\\}$", ""));
            map.put("value", "");
            maps.add(map);
        }
        return JSON.toJSONString(maps);
    }

    /**
     * 提取模板中的字段为map格式字符串
     *
     * @param template
     * @return
     */
    public List<Map<String, String>>  extractFieldMap(String template) {
        String regex = "\\{(.*?)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        List<Map<String, String>> maps = Lists.newArrayList();
        while (m.find()) {
            Map<String, String> map = Maps.newHashMap();
            String result = m.group();
            map.put("key", result.replaceAll("^\\{|\\}$", ""));
            map.put("value", "");
            maps.add(map);
        }
        return maps;
    }

    /**
     * 获取返回修改的审批模型数据
     *
     * @param approvalModelDto
     * @return
     */
    public ApprovalModelDto getEditApprovalModel(ApprovalModelDto approvalModelDto) {
        approvalModelDto.setOpinions("返回修改");
        approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setCurrentStep(-1);
        return approvalModelDto;
    }

    /**
     * 获取数据同步sql
     *
     * @param synchronousDataDto
     * @return
     */
    public String getSynchronousSql(SynchronousDataDto synchronousDataDto) {
        String sqlTemplate = "select column_name from information_schema.columns where table_schema='%s' and table_name='%s'";

        //获取源数据表的字段
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(String.format(sqlTemplate, synchronousDataDto.getSourceDataBase(), synchronousDataDto.getSourceTable()));
        List<String> sourceFieldList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            sourceFieldList.add(String.valueOf(map.get("column_name")));
        }

        //获取目标数据表的字段
        mapList = jdbcTemplate.queryForList(String.format(sqlTemplate, synchronousDataDto.getTargeDataBase(), synchronousDataDto.getTargeTable()));
        List<String> targeFieldList = Lists.newArrayList();
        for (Map<String, Object> map : mapList) {
            targeFieldList.add(String.valueOf(map.get("column_name")));
        }

        Map<String, String> resultMap = Maps.newHashMap();//字段对应关系map
        if (CollectionUtils.isEmpty(targeFieldList)) return null;
        for (String targetField : targeFieldList) {
            if (CollectionUtils.isNotEmpty(synchronousDataDto.getIgnoreField()) && synchronousDataDto.getIgnoreField().contains(targetField))
                continue;
            if (CollectionUtils.isNotEmpty(sourceFieldList) && sourceFieldList.contains(targetField)) {
                resultMap.put(targetField, targetField);
            }
        }

        if (synchronousDataDto.getFieldMapping() != null) {
            resultMap.putAll(synchronousDataDto.getFieldMapping());
        }

        if (synchronousDataDto.getFieldDefaultValue() != null) {
            for (Map.Entry<String, String> stringEntry : synchronousDataDto.getFieldDefaultValue().entrySet()) {
                resultMap.put(stringEntry.getKey(),String.format("'%s'",stringEntry.getValue()));
            }
        }
        StringBuilder sourceBuilder = new StringBuilder();
        StringBuilder targetBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            targetBuilder.append(entry.getKey()).append(",");
            sourceBuilder.append(entry.getValue()).append(",");
        }

        return String.format("INSERT INTO %s.%s(%s) SELECT %s FROM %s.%s WHERE %s",
                synchronousDataDto.getTargeDataBase(), synchronousDataDto.getTargeTable(),
                targetBuilder.deleteCharAt(targetBuilder.length() - 1).toString(), sourceBuilder.deleteCharAt(sourceBuilder.length() - 1).toString(),
                synchronousDataDto.getSourceDataBase(), synchronousDataDto.getSourceTable(),
                org.springframework.util.StringUtils.isEmpty(synchronousDataDto.getWhereSql()) ? "1=1" : synchronousDataDto.getWhereSql());
    }
}

package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 提取模板中的字段为json格式字符串
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
            map.put("key",result.replaceAll("^\\{|\\}$",""));
            map.put("value","");
            maps.add(map);
        }
        return JSON.toJSONString(maps);
    }

    /**
     * 获取返回修改的审批模型数据
     * @param approvalModelDto
     * @return
     */
    public ApprovalModelDto getEditApprovalModel(ApprovalModelDto approvalModelDto){
        approvalModelDto.setOpinions("返回修改");
        approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setCurrentStep(-1);
        return approvalModelDto;
    }
}

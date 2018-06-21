package com.copower.pmcc.assess.service;

import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
}

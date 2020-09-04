package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.service.CustomPublicService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.TemplateSetService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.ZtreeDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/5/21
 * @time: 14:50
 */
@Controller
@RequestMapping(value = "/customPublic", name = "自定义公共控制器")
public class CustomPublicController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CustomPublicService customPublicService;
    @Autowired
    private PublicService publicService;

    @RequestMapping(value = "/customEstateTimes", name = "进入查勘楼盘统计页面")
    public ModelAndView customEstateTimes() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/report/customEstateTimes");
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getSurveyEstateTimesList", name = "取得查勘楼盘统计列表 ", method = RequestMethod.GET)
    public BootstrapTableVo getSurveyEstateTimesList(String userAccount) {
        return customPublicService.getSurveyEstateTimesList(userAccount);
    }

}

package com.copower.pmcc.assess.controller.project.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.report.CustomReportLandAssociationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:54
 * @Description:中国房地产估价师协会报表
 */
@Controller
@RequestMapping(value = "/customReportLandAssociation")
public class CustomReportLandAssociationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomReportLandAssociationService customReportLandAssociationService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/report/customReportLandAssociation";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomReportLandAssociationList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getCustomReportLandAssociationList(String projectName, String numberValue, String unitName,
                                                                    String queryStartDate, String queryEndDate,Integer limit,Integer offset) {
        BootstrapTableVo vo = null;
        try {
            vo = customReportLandAssociationService.getCustomReportLandAssociationList(projectName, numberValue, unitName,
                    queryStartDate, queryEndDate,limit,offset);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/export", name = "导出")
    public void export(HttpServletRequest request, HttpServletResponse response, String projectName, String numberValue, String unitName,
                       String queryStartDate, String queryEndDate) throws Exception {
        try {
            customReportLandAssociationService.export(response, projectName, numberValue, unitName,
                    queryStartDate, queryEndDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

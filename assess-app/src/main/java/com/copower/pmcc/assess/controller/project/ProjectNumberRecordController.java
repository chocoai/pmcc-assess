package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:54
 * @Description:文号记录
 */
@RestController
@RequestMapping(value = "/projectNumberRecord")
public class ProjectNumberRecordController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/project/projectNumberRecord";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }

    @RequestMapping(value = "/getProjectNumberRecordList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getProjectNumberRecordList(String projectName, Integer reportType, String numberValue) {
        BootstrapTableVo vo = null;
        try {
            vo = projectNumberRecordService.getProjectNumberRecordList(projectName, reportType, numberValue);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return null;
        }
        return vo;
    }



}

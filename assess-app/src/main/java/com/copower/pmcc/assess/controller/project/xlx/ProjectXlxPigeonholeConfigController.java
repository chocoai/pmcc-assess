package com.copower.pmcc.assess.controller.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfig;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxPigeonholeConfigService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@RequestMapping(value = "/projectXlxPigeonholeConfig", name = "资料归档")
@Controller
public class ProjectXlxPigeonholeConfigController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectXlxPigeonholeConfigService projectXlxPigeonholeConfigService;

    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/customer/xlx/projectXlxPigeonholeConfigView");
        //获取到类型 类别 范围
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("keyValueDtoList", keyValueDtoList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list(String queryName, Integer queryType) {
        return projectXlxPigeonholeConfigService.getVoList(queryName, queryType);
    }


    @ResponseBody
    @RequestMapping(value = "/get", name = "获取", method = {RequestMethod.GET})
    public HttpResult get(@RequestParam(value = "id") Integer id) {
        try {
            return HttpResult.newCorrectResult(projectXlxPigeonholeConfigService.getProjectXlxPigeonholeConfig(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult save(ProjectXlxPigeonholeConfig projectXlxPigeonholeConfig) {
        try {
            projectXlxPigeonholeConfigService.saveAndUpdate(projectXlxPigeonholeConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            projectXlxPigeonholeConfigService.removeProjectXlxPigeonholeConfig(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}

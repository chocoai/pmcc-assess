package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicity;
import com.copower.pmcc.assess.dto.output.ZtreeVo;
import com.copower.pmcc.assess.service.data.DataHisRightInfoPublicityService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/dataHisRightInfoPublicity")
public class DataHisRightInfoPublicityController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataHisRightInfoPublicityService dataHisRightInfoPublicityService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataHisRightInfoPublicity";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/getDataHisRightInfoPublicityById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataHisRightInfoPublicity dataHisRightInfoPublicity = null;
        try {
            if (id != null) {
                dataHisRightInfoPublicity = dataHisRightInfoPublicityService.getByDataHisRightInfoPublicityId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataHisRightInfoPublicity);
    }

    @RequestMapping(value = "/deleteDataHisRightInfoPublicityById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataHisRightInfoPublicityService.deleteDataHisRightInfoPublicity(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataHisRightInfoPublicity", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataHisRightInfoPublicity dataHisRightInfoPublicity) {
        try {
            dataHisRightInfoPublicityService.saveObject(dataHisRightInfoPublicity);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @PostMapping(value = "/getDataHisRightInfoPublicityTree", name = "取得地区树")
    public List<ZtreeVo> getDataHisRightInfoPublicityTree(Integer pid) {
        if (pid == null) {
            pid = 0;
        }
        List<ZtreeVo> tree = dataHisRightInfoPublicityService.getDataHisRightInfoPublicityTree(pid);
        return tree;
    }

    @PostMapping(value = "/getContent", name = "获取公示信息")
    public HttpResult getContent(Integer areaId) {
        DataHisRightInfoPublicity dataHisRightInfoPublicity = null;
        try {
            if (areaId != null) {
                dataHisRightInfoPublicity = dataHisRightInfoPublicityService.getContent(areaId);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataHisRightInfoPublicity);
    }
}

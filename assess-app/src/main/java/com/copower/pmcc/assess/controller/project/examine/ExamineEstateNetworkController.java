package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateNetwork;
import com.copower.pmcc.assess.service.project.examine.ExamineEstateNetworkService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/7/18 14:36
 * @Description:
 */
@RequestMapping(value = "/examineEstateNetwork",name = "通信网络 控制器")
@Controller
public class ExamineEstateNetworkController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineEstateNetworkService examineEstateNetworkService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/task/survey/examine/residence/apply/estateNetwork" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineEstateNetworkById",method = {RequestMethod.GET},name = "获取通信网络")
    public HttpResult getById(Integer id) {
        ExamineEstateNetwork examineEstateNetwork = null;
        try {
            if (id!=null){
                examineEstateNetwork = examineEstateNetworkService.getEstateNetworkById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineEstateNetwork);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineEstateNetworkList",method = {RequestMethod.GET},name = "获取通信网络列表")
    public BootstrapTableVo getExamineEstateNetworkList(String name,Integer examineType) {
        BootstrapTableVo vo = null;
        try {
            ExamineEstateNetwork examineEstateNetwork = new ExamineEstateNetwork();
            if (!StringUtils.isEmpty(name)){
                examineEstateNetwork.setName(name);
            }
            if (!ObjectUtils.isEmpty(examineType)){
                examineEstateNetwork.setExamineType(examineType);
            }
            vo = examineEstateNetworkService.getExamineEstateNetworkList(examineEstateNetwork);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineEstateNetworkById",method = {RequestMethod.POST},name = "删除通信网络")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineEstateNetworkService.deleteEstateNetwork(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineEstateNetwork",method = {RequestMethod.POST},name = "更新通信网络")
    public HttpResult save(ExamineEstateNetwork examineEstateNetwork){
        try {
            if (examineEstateNetwork.getId()==null || examineEstateNetwork.getId().equals(0)){
                examineEstateNetworkService.saveExamineEstateNetwork(examineEstateNetwork);
            }else {
                examineEstateNetworkService.updateEstateNetwork(examineEstateNetwork);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}

package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitElevator;
import com.copower.pmcc.assess.service.project.examine.ExamineUnitElevatorService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/7/24 17:52
 * @Description:配备电梯
 */

@RequestMapping(value = "/examineUnitElevator")
@Controller
public class ExamineUnitElevatorController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineUnitElevatorService examineUnitElevatorService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

//    @Deprecated
//    @RequestMapping(value = "/view", name = "转到index页面 (临时)",method = {RequestMethod.GET})
//    public ModelAndView index() {
//        String view = "/task/survey/examine/residence/apply/unitElevator" ;
//        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
//        return modelAndView;
//    }

    @ResponseBody
    @RequestMapping(value = "/getExamineUnitElevatorById",method = {RequestMethod.GET},name = "获取配备电梯")
    public HttpResult getById(Integer id) {
        ExamineUnitElevator examineUnitElevator = null;
        try {
            if (id!=null){
                examineUnitElevator = examineUnitElevatorService.getEstateNetworkById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineUnitElevator);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineUnitElevatorList",method = {RequestMethod.GET},name = "获取配备电梯列表")
    public BootstrapTableVo getExamineUnitElevatorList(Integer examineType,Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineUnitElevator examineUnitElevator = new ExamineUnitElevator();
            if (!ObjectUtils.isEmpty(examineType)){
                examineUnitElevator.setExamineType(examineType);
            }
            if (!ObjectUtils.isEmpty(declareId)){
                examineUnitElevator.setDeclareId(declareId);
            }
            vo = examineUnitElevatorService.getExamineUnitElevatorList(examineUnitElevator);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineUnitElevatorById",method = {RequestMethod.POST},name = "删除配备电梯")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineUnitElevatorService.deleteEstateNetwork(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineUnitElevator",method = {RequestMethod.POST},name = "更新配备电梯")
    public HttpResult save(ExamineUnitElevator examineUnitElevator){
        try {
            if (examineUnitElevator.getId()==null || examineUnitElevator.getId().equals(0)){
                examineUnitElevatorService.saveExamineUnitElevator(examineUnitElevator);
            }else {
                examineUnitElevatorService.updateEstateNetwork(examineUnitElevator);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}

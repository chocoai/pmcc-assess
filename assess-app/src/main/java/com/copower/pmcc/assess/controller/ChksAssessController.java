package com.copower.pmcc.assess.controller;

import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.model.*;
import com.copower.pmcc.bpm.api.enums.BoxReChksProcessEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.assess.dto.output.ChksApprovalAssessListVo;
import com.copower.pmcc.assess.dto.output.ChksApprovalAssessVo;
import com.copower.pmcc.assess.dto.output.ChksApprovalBusinessVo;
import com.copower.pmcc.assess.dto.output.ChksApprovalInfoVo;
import com.copower.pmcc.assess.service.ChksApprovalBusinessService;
import com.copower.pmcc.assess.service.ChksApprovalService;
import com.copower.pmcc.assess.service.ChksAssessService;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/28
 * @time: 17:36
 */
@Controller
@RequestMapping(value = "/ChksAssess")
public class ChksAssessController {
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ChksApprovalService chksApprovalService;
    @Autowired
    private ChksApprovalBusinessService chksApprovalBusinessService;
    @Autowired
    private ChksAssessService chksAssessService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ServiceComponent serviceComponent;

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public ModelAndView apply(Integer boxId, Integer activityId, String processInsId) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksAssess/chksAssessApply");
        List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemList(boxId, activityId);
        modelAndView.addObject("assessmentItemDtos", assessmentItemDtos);
        ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalService.getChksApprovalInfoItem(boxId, activityId, processInsId);
        modelAndView.addObject("chksApprovalInfoVo", chksApprovalInfoVo);
        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByBusinessProcessInsId(processInsId);
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);
        return modelAndView;
    }

    @RequestMapping(value = "/repairAssessChks", method = RequestMethod.GET)
    public ModelAndView repairAssessChks(Integer id) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksAssess/repairAssessChks");
        ChksApprovalInfo chksApprovalInfo = chksApprovalService.getChksApprovalInfoById(id);
        ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalService.getChksApprovalInfoVo(chksApprovalInfo);
        modelAndView.addObject("chksApprovalInfoVo", chksApprovalInfoVo);

        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByBusinessProcessInsId(chksApprovalInfo.getProcessInsId());
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);

        List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemList(chksApprovalInfo.getBoxId(), chksApprovalInfo.getBoxActivityId());
        modelAndView.addObject("assessmentItemDtos", assessmentItemDtos);


        modelAndView.addObject("scoreRatio", 1.0 / chksApprovalInfoVo.getSysUserDtos().size());
        modelAndView.addObject("thisUserName", serviceComponent.getThisUserInfo().getUserName());
        return modelAndView;

    }

    @RequestMapping(value = "/editAssessChks", method = RequestMethod.GET)
    public ModelAndView editAssessChks(Integer id) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksAssess/editAssessChks");
        ChksApprovalInfo chksApprovalInfo = chksApprovalService.getChksApprovalInfoById(id);
        ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalService.getChksApprovalInfoVo(chksApprovalInfo);
        modelAndView.addObject("chksApprovalInfoVo", chksApprovalInfoVo);

        List<ChksApprovalAssessVo> chksApprovalAssessVos = chksAssessService.getChksApprovalAssessVoByActivityId(id);
        modelAndView.addObject("chksApprovalAssessVos", chksApprovalAssessVos);
        modelAndView.addObject("chksApprovalAssessDetails", chksApprovalAssessVos.get(0).getChksApprovalAssessDetails());
        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByBusinessProcessInsId(chksApprovalInfo.getProcessInsId());
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);
        modelAndView.addObject("scoreRatio", 1.0 / chksApprovalAssessVos.size());
        modelAndView.addObject("thisUserName", serviceComponent.getThisUserInfo().getUserName());
        return modelAndView;

    }

    @RequestMapping(value = "/applyByProcessInsId", method = RequestMethod.GET)
    public ModelAndView applyByProcessInsId(String processInsId) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksAssess/chksAssessApply");

        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByBusinessProcessInsId(processInsId);
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);
        BoxReDto boxReInfoByBoxId = bpmRpcBoxService.getBoxReInfoByBoxId(chksApprovalBusinessVo.getBusinessBoxId());
        BoxReChksProcessEnum boxReChksProcessEnum = BoxReChksProcessEnum.create(boxReInfoByBoxId.getChksProcess());
        List<ChksApprovalInfoVo> chksApprovalInfos = chksApprovalService.getChksApprovalInfoVoList(processInsId);
        if (CollectionUtils.isNotEmpty(chksApprovalInfos)) {
            List<ChksApprovalAssessListVo> chksApprovalAssessListVos = new ArrayList<>();
            ChksApprovalAssessListVo chksApprovalAssessListVo = new ChksApprovalAssessListVo();
            if (boxReChksProcessEnum.equals(BoxReChksProcessEnum.INDEPENDENT))//独立的考核流程
            {
                for (ChksApprovalInfoVo item : chksApprovalInfos) {
                    chksApprovalAssessListVo = new ChksApprovalAssessListVo();
                    List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemList(item.getBoxId(), item.getBoxActivityId());
                    chksApprovalAssessListVo.setChksApprovalInfoVos(item);
                    chksApprovalAssessListVo.setAssessmentItemDtos(assessmentItemDtos);
                    double v = 1.0 / item.getSysUserDtos().size();
                    chksApprovalAssessListVo.setScoreRatio(v);
                    chksApprovalAssessListVos.add(chksApprovalAssessListVo);
                }
                modelAndView.addObject("thisActivityName", "考核");
            } else {
                ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalInfos.get(0);

                modelAndView.addObject("thisActivityName", chksApprovalInfos.get(1).getActivityName());
                List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemList(chksApprovalInfoVo.getBoxId(), chksApprovalInfoVo.getBoxActivityId());
                chksApprovalAssessListVo.setChksApprovalInfoVos(chksApprovalInfoVo);
                chksApprovalAssessListVo.setAssessmentItemDtos(assessmentItemDtos);
                double v = 1.0 / chksApprovalInfoVo.getSysUserDtos().size();
                chksApprovalAssessListVo.setScoreRatio(v);
                chksApprovalAssessListVos.add(chksApprovalAssessListVo);
            }
            modelAndView.addObject("chksApprovalAssessListVos", chksApprovalAssessListVos);
        }

        modelAndView.addObject("thisUserName", serviceComponent.getThisUserInfo().getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "/approval", name = "进入审批页面")
    public ModelAndView approvalIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByProcessInsId(processInsId);
        BoxReDto businessBoxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(chksApprovalBusinessVo.getBusinessBoxId());
        BoxReChksProcessEnum boxReChksProcessEnum = BoxReChksProcessEnum.create(businessBoxReDto.getChksProcess());
        List<ChksApprovalAssessListVo> chksApprovalAssessListVos = new ArrayList<>();
        ModelAndView modelAndView = null;
        switch (boxReChksProcessEnum) {
            case ASYNCHRONOUS://复核异步
            {
                ActivitiTaskNodeDto activitiTaskNodeDto = null;
                try {
                    if (StringUtils.isNotBlank(agentUserAccount)) {
                        activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, agentUserAccount);
                    } else {
                        activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, serviceComponent.getThisUser());
                    }
                    if (activitiTaskNodeDto == null) {
                        modelAndView.setViewName("/");//设置一个无权查看的页面
                    }

                } catch (Exception e) {
                    modelAndView.setViewName("/");//设置一个错误的页面
                }
                modelAndView = controllerComponent.baseModelAndView("/chksAssess/chksAssessApproval");
                modelAndView.addObject("processInsId", processInsId);
                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
                modelAndView.addObject("thisTitle", boxReDto.getCnName());
                modelAndView.addObject("boxCnName", boxReDto.getCnName());
                modelAndView.addObject("boxdescription", boxReDto.getDescription());
                modelAndView.addObject("boxprocessIcon", boxReDto.getProcessIcon());
                modelAndView.addObject("boxId", boxId);
                modelAndView.addObject("taskId", taskId);
                modelAndView.addObject("boxName", boxReDto.getName());
                modelAndView.addObject("agentUserAccount", agentUserAccount);
                modelAndView.addObject("activityName", activitiTaskNodeDto.getTaskKey());
                modelAndView.addObject("CurrentStep", activitiTaskNodeDto.getCurrentStep());
                modelAndView.addObject("StepCount", activitiTaskNodeDto.getStepCount());
                //取考核标准
                List<ChksApprovalInfoVo> chksApprovalInfos = chksApprovalService.getChksApprovalInfoVoList(chksApprovalBusinessVo.getBusinessProcessInsId());
                int currentStep = Integer.parseInt(modelAndView.getModel().get("CurrentStep").toString());
                if (CollectionUtils.isNotEmpty(chksApprovalInfos)) {

                    ChksApprovalAssessListVo chksApprovalAssessListVo = new ChksApprovalAssessListVo();
                    ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalInfos.get(currentStep + 1);
                    List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemList(chksApprovalInfoVo.getBoxId(), chksApprovalInfoVo.getBoxActivityId());
                    chksApprovalAssessListVo.setChksApprovalInfoVos(chksApprovalInfoVo);
                    chksApprovalAssessListVo.setAssessmentItemDtos(assessmentItemDtos);
                    double v = 1.0 / chksApprovalInfoVo.getSysUserDtos().size();
                    chksApprovalAssessListVo.setScoreRatio(v);
                    chksApprovalAssessListVos.add(chksApprovalAssessListVo);
                }
                break;
            }
            case INDEPENDENT://单独考核
            {
                modelAndView = controllerComponent.baseFormModelAndView("/chksAssess/chksAssessApproval", processInsId, boxId, taskId, agentUserAccount);
                int currentStep = Integer.parseInt(modelAndView.getModel().get("CurrentStep").toString());
                BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, currentStep - 1);
                ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalService.getChksApprovalInfoItem(boxId, boxReActivityDto.getId(), processInsId);
                List<AssessmentItemDto> assessmentItemDtos = bpmRpcBoxService.getAssessmentItemList(boxId, boxReActivityDto.getId());
                ChksApprovalAssessListVo chksApprovalAssessListVo = new ChksApprovalAssessListVo();
                chksApprovalAssessListVo.setChksApprovalInfoVos(chksApprovalInfoVo);
                chksApprovalAssessListVo.setAssessmentItemDtos(assessmentItemDtos);
                double v = 1.0 / chksApprovalInfoVo.getSysUserDtos().size();
                chksApprovalAssessListVo.setScoreRatio(v);
                chksApprovalAssessListVos.add(chksApprovalAssessListVo);
            }
        }
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);
        modelAndView.addObject("chksApprovalAssessListVos", chksApprovalAssessListVos);
        return modelAndView;
    }

    @RequestMapping(value = "/details", name = "详情页")
    public ModelAndView details(String processInsId) {
        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByProcessInsId(processInsId);
        List<ChksApprovalAssessVo> chksApprovalAssessVos = chksAssessService.getChksApprovalAssessVoByBusinessId(chksApprovalBusinessVo.getId());
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksAssess/chksAssessDetails");
        modelAndView.addObject("chksApprovalAssessVos", chksApprovalAssessVos);
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);
        return modelAndView;
    }

    @RequestMapping(value = "/detailsBusiness", name = "根据业务流程实例查看详情页")
    public ModelAndView detailsBusiness(String processInsId) {
        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksByBusinessProcessInsId(processInsId);
        List<ChksApprovalAssessVo> chksApprovalAssessVos = chksAssessService.getChksApprovalAssessVoByBusinessId(chksApprovalBusinessVo.getId());
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksAssess/chksAssessDetails");
        modelAndView.addObject("chksApprovalAssessVos", chksApprovalAssessVos);
        modelAndView.addObject("chksApprovalBusinessVo", chksApprovalBusinessVo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAssessResult", name = "保存考核数据", method = RequestMethod.POST)
    public HttpResult saveAssessResult(String formData) {
        try {
            chksAssessService.saveAssessResult(formData, serviceComponent.getThisUser());
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/approvalAssessResult", name = "提交考核", method = RequestMethod.POST)
    public HttpResult approvalAssessResult(ApprovalModelDto approvalModelDto, String formData) {
        try {
            chksAssessService.approvalAssessResult(approvalModelDto, formData);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/editAssessChksSave", name = "保存修改的考核数据", method = RequestMethod.POST)
    public HttpResult editAssessChksSave(String formData) {
        try {
            chksAssessService.editAssessChksSave(formData);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    @ResponseBody
    @RequestMapping(value = "/repairAssessChksSave", name = "保存修改的考核数据", method = RequestMethod.POST)
    public HttpResult repairAssessChksSave(String formData) {
        try {
            chksAssessService.repairAssessChksSave(formData);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

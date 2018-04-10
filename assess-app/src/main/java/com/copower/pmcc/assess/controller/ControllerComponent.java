package com.copower.pmcc.assess.controller;

import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReviewTemplateDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Component
public class ControllerComponent {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;

    /**
     * 返回公共需要的ModelAndView
     *
     * @param viewUrl 需要返回的视图地址
     * @return
     */
    public ModelAndView baseModelAndView(String viewUrl) {
        ModelAndView modelAndView = commonService.baseView(viewUrl);
        BootstrapTableVo sysRemindUnRead = erpRpcToolsService.getSysRemindUnRead(1, 8, commonService.thisUserAccount());
        modelAndView.addObject("sysRemindUnReadCount", sysRemindUnRead.getTotal());
        modelAndView.addObject("sysRemindUnRead", sysRemindUnRead.getRows());
        return modelAndView;
    }

    /**
     * 返回流程申请表单的ModelAndView
     *
     * @param viewUrl
     * @param boxId
     * @return
     */
    public ModelAndView baseFormModelAndView(String viewUrl, String processInsId, Integer boxId, String taskId, String agentUserAccount) {
        SysUserDto sysUser = commonService.thisUser();

        ModelAndView modelAndView = commonService.baseView(viewUrl);
        modelAndView.addObject("flog", "approval");//页面属性，默认是审批页面
        modelAndView.addObject("currUserName", sysUser.getUserName());
        if (StringUtils.isBlank(processInsId)) {
            processInsId = "0";
        }
        modelAndView.addObject("processInsId", processInsId);

        if (boxId > 0) {
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
            modelAndView.addObject("thisTitle", boxReDto.getCnName());
            modelAndView.addObject("boxCnName", boxReDto.getCnName());
            modelAndView.addObject("boxdescription", boxReDto.getDescription());
            modelAndView.addObject("boxprocessIcon", boxReDto.getProcessIcon());
            modelAndView.addObject("boxId", boxId);
            modelAndView.addObject("taskId", taskId);
            modelAndView.addObject("boxName", boxReDto.getName());

        }
        ActivitiTaskNodeDto activitiTaskNodeDto = null;
        switch (taskId) {
            case "0"://新增
            {
                modelAndView.addObject("currentStepName", "发起申请");
                modelAndView.addObject("flog", "apply");//页面属性，默认是审批页面
                break;
            }
            case "-1"://查看详情
            {
                modelAndView.addObject("flog", "details");
                List<ActivitiTaskNodeDto> activitiTaskNodeDtos = null;
                try {
                    activitiTaskNodeDtos = bpmRpcActivitiProcessManageService.queryProcessCurrentTask(processInsId);
                    if (CollectionUtils.isNotEmpty(activitiTaskNodeDtos)) {
                        activitiTaskNodeDto = activitiTaskNodeDtos.get(0);
                    } else {
                        modelAndView.addObject("currUserName", "");
                        modelAndView.addObject("currentStepName", "结束");
                    }
                } catch (BpmException e) {
                    e.printStackTrace();
                }
                break;
            }
            default://待办任务
            {

                try {
                    if (StringUtils.isNotBlank(agentUserAccount)) {
                        activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, agentUserAccount);
                    } else {
                        activitiTaskNodeDto = bpmRpcActivitiProcessManageService.queryCurrentTask(taskId, commonService.thisUserAccount());
                    }
                    if (activitiTaskNodeDto == null) {
                        modelAndView.setViewName("/");//设置一个无权查看的页面
                    }

                } catch (Exception e) {
                    modelAndView.setViewName("/");//设置一个错误的页面
                }
                break;
            }
        }
        if (activitiTaskNodeDto != null) {
            int currentStep = 0;
            if (activitiTaskNodeDto.getCurrentStep() != null) {
                currentStep = activitiTaskNodeDto.getCurrentStep();
            }

            modelAndView.addObject("sorting", currentStep);
            String reActivityCnName = "";
            String reActivityName = "";

            BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, currentStep);
            if (activitiTaskNodeDto.getTaskKey().equals(ProcessActivityEnum.EDIT.getValue())) {
                boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByActivityNameSorting(boxId, ProcessActivityEnum.EDIT.getValue());
            }
            modelAndView.addObject("currentStepName", boxReActivityDto.getCnName());
            reActivityCnName = boxReActivityDto.getCnName();
            reActivityName = boxReActivityDto.getName();
            modelAndView.addObject("agentUserAccount", agentUserAccount);
            modelAndView.addObject("activityName", activitiTaskNodeDto.getTaskKey());
            modelAndView.addObject("reActivityName", reActivityName);
            modelAndView.addObject("activityCnName", reActivityCnName);
            modelAndView.addObject("activityReName", reActivityName);
            modelAndView.addObject("CurrentStep", currentStep);
            modelAndView.addObject("StepCount", activitiTaskNodeDto.getStepCount());
            modelAndView.addObject("activityId", boxReActivityDto.getId());
            if (!taskId.equals("-1")) {
                //如果是审批才取相应的复核和审批人相关信息
                //                //取复核模板
                List<BoxReviewTemplateDto> boxReviewTemplates = null;
                boxReviewTemplates = bpmRpcBoxService.getBoxReviewTemplateList(boxReActivityDto.getBoxId(),boxReActivityDto.getActivityId());
                if (CollectionUtils.isNotEmpty(boxReviewTemplates)) {
                    modelAndView.addObject("approvalReview", 1);
                    modelAndView.addObject("boxReviewTemplate", boxReviewTemplates);
                } else {
                    modelAndView.addObject("approvalReview", 0);
                }
                //处理是否最后一级
                if (activitiTaskNodeDto.getTaskKey().equals("edit")) {

                    modelAndView.addObject("lastNodes", 0);
                } else {

                    if (activitiTaskNodeDto.getStepCount() != null && currentStep >= 0) {

                        if (activitiTaskNodeDto.getStepCount() == currentStep + 1)//最后一个节点
                        {
                            modelAndView.addObject("lastNodes", 1);
                        } else {
                            //特殊处理工作成果任务节点

                            //计算当前节点后的循环节点还有几级
                            List<BoxReActivityDto> boxReActivityByThisCurrentStep = bpmRpcBoxService.getBoxReActivityByThisCurrentStep(boxReActivityDto.getSorting(), boxId, currentStep);
                            if (CollectionUtils.isNotEmpty(boxReActivityByThisCurrentStep)) {
                                modelAndView.addObject("lastNodes", 0);

                            } else {
                                modelAndView.addObject("lastNodes", 1);
                            }
                            //如果不是最后一级，则判断当前节点的下一个节点是否允许选择下级审批人
                            BoxReActivityDto nextBoxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, currentStep + 1);
                            if (nextBoxReActivityDto != null) {
                                if (!nextBoxReActivityDto.getBisSkip())//如果下级节点不允许跳过，则也设定为最后一个节点
                                {
                                    modelAndView.addObject("lastNodes", 1);
                                }
                            } else {
                                modelAndView.addObject("lastNodes", 1);
                            }
                        }
                    } else {//如果没有多级审批，则认定为最后一级
                        modelAndView.addObject("lastNodes", 1);
                    }
                }
            }
            //===============================
            if (CollectionUtils.isEmpty(activitiTaskNodeDto.getUsers())) {
                sysUser = erpRpcUserService.getSysUser(activitiTaskNodeDto.getAssignee());
                modelAndView.addObject("currUserName", sysUser.getUserName());

            } else {
                List<SysUserDto> sysUserList = erpRpcUserService.getSysUserList(activitiTaskNodeDto.getUsers());
                List<String> transform = LangUtils.transform(sysUserList, o -> o.getUserName());
                modelAndView.addObject("currUserName", FormatUtils.transformListString(transform));
            }
        }

        return modelAndView;

    }

    /**
     * 获取业务主表id by ruId
     *
     * @param ruId
     * @return
     */
    public Integer getTableIdByRuId(Integer ruId) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuById(ruId);
        return boxRuDto.getTableId();
    }

    public void getStepProportion(Integer boxId, ModelAndView modelAndView) {
        if (boxId <= 0) {
            return;
        }
        List<BoxReActivityDto> boxReActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        Integer sorting = Integer.valueOf(modelAndView.getModel().get("sorting").toString());
        int i = 1;
        int j = 1;
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BoxReActivityDto item : boxReActivityDtos) {
            if (!item.getSorting().equals(1) && !item.getSorting().equals(100)) {
                Integer sortMultilevel = item.getSortMultilevel();
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(i));
                keyValueDto.setValue(item.getCnName());
                if (sorting > sortMultilevel) {
                    keyValueDto.setExplain("done");
                    j++;
                } else {
                    if (sorting.equals(sortMultilevel)) {

                        keyValueDto.setExplain("selected");
                    } else {
                        keyValueDto.setExplain("disabled");
                    }
                }
                keyValueDtos.add(keyValueDto);
                i++;
            }
        }
        modelAndView.addObject("Steps", keyValueDtos);
        modelAndView.addObject("proportion", Math.ceil(((float) j / (i - 1)) * 100));
    }

}

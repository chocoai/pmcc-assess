package com.copower.pmcc.assess.service;

import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.model.*;
import com.copower.pmcc.bpm.api.dto.node.LoopTaskNodeArg;
import com.copower.pmcc.bpm.api.enums.BoxReChksProcessEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.erp.redis.client.SimpleRedisStandalone;
import com.copower.pmcc.assess.common.ApprovalUser;
import com.copower.pmcc.assess.dto.input.ProcessUserDto;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpc on 2017/7/21.
 */
@Component
public class ServiceComponent {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private SimpleRedisStandalone simpleRedisStandalone;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private ApprovalUser approvalUser;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    /**
     * 删除指定的KEYE
     *
     * @param key
     * @param value
     */
    public void RemoveRedisKeyValues(String key, String value) {

        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(key, value);
        if (StringUtils.isBlank(value)) {
            costsKeyPrefix = CacheConstant.getCostsKeyPrefix(key);
        }
        simpleRedisStandalone.del(costsKeyPrefix);
    }

    public SysUserDto getSysUserDtoByUserAccount(String userAccount) {
        return erpRpcUserService.getSysUser(userAccount);
    }
    public boolean userIsAdmin(String userAccount) {
        return erpRpcToolsService.userIsAdmin(userAccount);
    }
    public String getThisUser() {
        return commonService.thisUserAccount();
    }

    public SysUserDto getThisUserInfo() {
        return commonService.thisUser();
    }

    public ProcessUserDto processStart(ProcessInfo processInfo, String appointUserAccount, Boolean bisSign) throws BpmException {
        String startUser;
        if (StringUtils.isNotBlank(processInfo.getStartUser())) {
            startUser = processInfo.getStartUser();
        } else {
            startUser = commonService.thisUserAccount();
        }
        processInfo.setStartUser(startUser);
        processInfo.setAppKey(applicationConstant.getAppKey());
        ProcessUserDto processUserDto = getProcessNextApproval(processInfo.getBoxId(), -1, appointUserAccount, processInfo.getProjectId(), bisSign, false);

        LoopTaskNodeArg loopTaskNodeArg = new LoopTaskNodeArg();
        loopTaskNodeArg.setLoopCount(processUserDto.getStepCount());
        if (processUserDto.getSkipActivity().size() > 0) {
            loopTaskNodeArg.setUsers(Lists.newArrayList(getThisUser()));
        } else {
            loopTaskNodeArg.setUsers(processUserDto.getUserAccounts());
        }
        processInfo.setNextUser(processUserDto.getUserAccounts());
        processInfo.setTaskNodeArg(loopTaskNodeArg);
        processInfo.setSkipActivityIds(processUserDto.getSkipActivity());
        processInfo.setBisDraftStart(false);
        String processInsId = bpmRpcProcessInsManagerService.processStart(processInfo);
        processUserDto.setProcessInsId(processInsId);
        return processUserDto;
    }

    /**
     * 循环流程审批操作
     *
     * @param approvalModelDto pjrx
     */
    public void processSubmitLoopTaskNodeArg(ApprovalModelDto approvalModelDto, Boolean bisAuto) throws BpmException {

        if (!bisAuto) {
            if (approvalModelDto.getConclusion().toLowerCase().equals(TaskHandleStateEnum.AGREE.getValue())) {//如果是同意，则再进行取下级审批人

                boolean bisNext = false;
                if (approvalModelDto.getBisNext() != null) {
                    bisNext = approvalModelDto.getBisNext().equals("1") ? true : false;
                } else {
                    approvalModelDto.setBisNext("0");
                }
                if (CollectionUtils.isNotEmpty(approvalModelDto.getNextApproval())) {
                    BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(approvalModelDto.getBoxId(), approvalModelDto.getCurrentStep());
                    approvalModelDto.setActivityId(boxReActivityDto.getId());
                    approvalModelDto.setSkipActivity(new ArrayList<>());
                } else {
                    ProcessUserDto processUserDto = getProcessNextApproval(approvalModelDto.getBoxId(), approvalModelDto.getCurrentStep(), approvalModelDto.getAppointUserAccount(), approvalModelDto
                            .getProjectId(), false, bisNext);

                    approvalModelDto.setNextApproval(processUserDto.getUserAccounts());
                    approvalModelDto.setSkipActivity(processUserDto.getSkipActivity());
                    if (processUserDto.getCurrActivityId() != null) {
                        approvalModelDto.setActivityId(processUserDto.getCurrActivityId());
                    }
                }
            } else {
                approvalModelDto.setSkipActivity(new ArrayList<>());
                approvalModelDto.setBisNext("0");
            }
            approvalModelDto.setCurrUserAccount(getThisUser());
        }
        bpmRpcProcessInsManagerService.processSubmitLoopTaskNodeArg(approvalModelDto);
    }

    /**
     * 取节点审批人，通用
     *
     * @param boxId              模型编号
     * @param CurrStep           当前步聚，发起申请和返回修改传-1
     * @param appointUserAccount 指定人，默认可以为当前操作人
     * @param projectid          项目编号
     * @param bisSign            是否加签
     * @return
     * @throws BpmException
     */
    public ProcessUserDto getProcessNextApproval(Integer boxId, Integer CurrStep, String appointUserAccount, Integer projectid, Boolean bisSign, Boolean bisNext) throws BpmException {

        List<Integer> skipActivity = new ArrayList<>();
        List<String> users = null;
        List<BoxReActivityDto> boxReActivityDtos = bpmRpcBoxService.getBoxReActivityByBoxId(boxId);
        if (StringUtils.isBlank(appointUserAccount)) {
            appointUserAccount = getThisUser();
        }
        BoxReActivityDto ThisboxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(boxId, CurrStep);//当前节点
        ProcessUserDto processUserDto = new ProcessUserDto();
        for (int i = CurrStep + 1; i < boxReActivityDtos.size(); i++) {
            BoxReActivityDto boxReActivityDto = boxReActivityDtos.get(i);//取得当前
            if (CurrStep >= 0) {
                if (bisNext && boxReActivityDto.getSorting() == ThisboxReActivityDto.getSorting()) {
                    skipActivity.add(boxReActivityDto.getId());
                    continue;
                }
            }
            if (boxReActivityDto.getBisSign()) {
                if (!bisSign) {//如果页面设置不加签，则直接跳过
                    skipActivity.add(boxReActivityDto.getId());
                    continue;
                }
            }

            List<String> userAccounts = getActivityRoleUserAccount(appointUserAccount, projectid, boxReActivityDto.getId());
            if (CollectionUtils.isEmpty(userAccounts))//如果没有取到人时
            {
                if (boxReActivityDto.getBisSign())//如果是加签节点，则跳过加签节点，并重新取下一个节点
                {
                    skipActivity.add(boxReActivityDto.getId());
                    continue;
                } else //如果不是加签节点，则给出提示，没有取责任人
                {
                    break;
                }
            } else //如果取到到
            {
                String string = FormatUtils.transformListString(userAccounts);
                if (StringUtils.isNotBlank(string))//防止取到的是空串，List也是不为空的，所以转为字符串再进行判断
                {
                    if (string.equals(getThisUser()))//判断取得的人员是否和当前操作人一致
                    {
                        if (boxReActivityDto.getBisSkip())//允许跳过
                        {
                            skipActivity.add(boxReActivityDto.getId());
                            continue;
                        } else {
                            users = userAccounts;
                            break;
                        }

                    } else//如果不一致，则返回取得的人员
                    {
                        users = userAccounts;
                        break;
                    }

                } else//
                {
                    if (boxReActivityDto.getBisSign())//如果是加签节点，则跳过加签节点，并重新取下一个节点
                    {
                        skipActivity.add(boxReActivityDto.getId());
                        continue;
                    } else //如果不是加签节点，则给出提示，没有取责任人
                    {
                        break;
                    }
                }

            }

        }

        processUserDto.setStepCount(boxReActivityDtos.size());
        processUserDto.setUserAccounts(users);
        processUserDto.setSkipActivity(skipActivity);
        if (CurrStep >= 0) {
            processUserDto.setCurrActivityId(boxReActivityDtos.get(CurrStep).getId());
        }
        return processUserDto;
    }

    private List<String> getActivityRoleUserAccount(String appointUserAccount, Integer projectid, Integer activityId) throws BpmException {
        List<String> users = bpmRpcBoxService.getRoleUserByActivityId(activityId);
        List<BoxReActivityRoleDto> boxReActivityRole = bpmRpcBoxService.getBoxReActivityRole(activityId);
        if (users == null) {
            users = new ArrayList<>();
        }
        for (BoxReActivityRoleDto item : boxReActivityRole) {
            if (StringUtils.isNotBlank(item.getBoxRoleName())) {
                String roleUserAccountList = approvalUser.getRoleUserAccountList(item.getBoxRoleName(), appointUserAccount);
                List<String> strings = FormatUtils.transformString2List(roleUserAccountList);
                for (String s : strings) {
                    users.add(s);
                }
            }
        }
        return users;
    }

    public void AutoprocessSubmitLoopTaskNodeArg(ProcessInfo processInfo, ProcessUserDto processUserDto) throws BpmException {
        List<ActivitiTaskNodeDto> activitiTaskNodeDtos = bpmRpcActivitiProcessManageService.queryProcessCurrentTask(processUserDto.getProcessInsId());
        if (CollectionUtils.isNotEmpty(activitiTaskNodeDtos)) {
            ActivitiTaskNodeDto activitiTaskNodeDto = activitiTaskNodeDtos.get(0);//取得第一个任务

            ApprovalModelDto approvalModelDto = new ApprovalModelDto();
            approvalModelDto.setTaskId(activitiTaskNodeDto.getTaskId());
            approvalModelDto.setProcessInsId(approvalModelDto.getProcessInsId());
            approvalModelDto.setOpinions("系统自动提交");
            approvalModelDto.setBisAuto(true);
            approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
            approvalModelDto.setCurrUserAccount(processInfo.getStartUser());
            approvalModelDto.setBisNext("0");
            approvalModelDto.setCurrentStep(activitiTaskNodeDto.getCurrentStep());
            approvalModelDto.setAgentUserAccount("");
            LoopTaskNodeArg taskNodeArg = (LoopTaskNodeArg) processInfo.getTaskNodeArg();
            approvalModelDto.setStepCount(taskNodeArg.getLoopCount());
            approvalModelDto.setProjectId(processInfo.getProjectId());
            approvalModelDto.setBoxId(processInfo.getBoxId());
            approvalModelDto.setActivityKey("");
            approvalModelDto.setSkipActivity(new ArrayList<>());
            approvalModelDto.setProjectId(processInfo.getProjectId());
            approvalModelDto.setProcessInsId(processUserDto.getProcessInsId());
            approvalModelDto.setWorkStage(processInfo.getWorkStage());
            approvalModelDto.setWorkStageId(processInfo.getWorkStageId());
            approvalModelDto.setWorkPhaseId(processInfo.getWorkPhaseId());
            approvalModelDto.setAppKey(applicationConstant.getAppKey());
            List<Integer> skipActivity = processUserDto.getSkipActivity();

            approvalModelDto.setNextApproval(processInfo.getNextUser());
            approvalModelDto.setActivityId(processUserDto.getSkipActivity().get(0));
            skipActivity.remove(0);//移除第一个节点
            approvalModelDto.setSkipActivity(skipActivity);
            bpmRpcActivitiProcessManageService.changeUser(activitiTaskNodeDto.getTaskId(), FormatUtils.transformListString(taskNodeArg.getUsers()), getThisUser());
            processSubmitLoopTaskNodeArg(approvalModelDto, true);

        }
    }

}

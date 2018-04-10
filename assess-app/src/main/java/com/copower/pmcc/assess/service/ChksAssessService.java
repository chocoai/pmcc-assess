package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.bpm.api.dto.model.*;
import com.copower.pmcc.bpm.api.dto.node.LoopTaskNodeArg;
import com.copower.pmcc.bpm.api.enums.BoxReChksProcessEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.dao.ChksApprovalAssessDao;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssess;
import com.copower.pmcc.assess.dal.entity.ChksApprovalAssessDetails;
import com.copower.pmcc.assess.dal.entity.ChksApprovalBusiness;
import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.assess.dto.input.ChksApprovalAssessDto;
import com.copower.pmcc.assess.dto.output.ChksApprovalAssessVo;
import com.copower.pmcc.assess.dto.output.ChksApprovalBusinessVo;
import com.copower.pmcc.assess.dto.output.ChksApprovalInfoVo;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/29
 * @time: 10:41
 */
@Service
public class ChksAssessService {
    @Autowired
    private ChksApprovalAssessDao chksApprovalAssessDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private ChksApprovalBusinessService chksApprovalBusinessService;
    @Autowired
    private ChksApprovalService chksApprovalService;
    @Autowired
    private BaseParameterServcie baseParameterServcie;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;

    @Transactional(rollbackFor = Exception.class)
    public void saveAssessResult(String formData, String userAccount) throws BusinessException {
        //保存业务数据
        List<ChksApprovalAssessDto> chksApprovalAssessDtos = saveChksApprovalAssessDtos(formData, userAccount);
        ChksApprovalAssessDto chksApprovalAssessDto = chksApprovalAssessDtos.get(0);

        //业务数据
        ChksApprovalBusiness chksApprovalBusiness = chksApprovalBusinessService.getChksApprovalBusinessById(chksApprovalAssessDtos.get(0).getChksApprovalBusinessId());
        ChksApprovalBusinessVo chksApprovalBusinessVo = chksApprovalBusinessService.getChksApprovalBusinessVo(chksApprovalBusiness);

        //发起考核流程
        if (StringUtils.isBlank(chksApprovalBusiness.getProcessInsId())) {
            BoxReDto businessBoxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(chksApprovalAssessDto.getBoxId());
            BoxReChksProcessEnum boxReChksProcessEnum = BoxReChksProcessEnum.create(businessBoxReDto.getChksProcess());
            String processInsId = "0";
            String folio = String.format("%s【考评流程】", chksApprovalBusinessVo.getProcessTitle());
            String parameterValues = baseParameterServcie.getParameterValues(BaseConstant.CHKS_BASE_ASSESS_BOX_NAME);
            if (StringUtils.isBlank(parameterValues)) {
                throw new BusinessException("请先设置默认考核流程");
            }
            switch (boxReChksProcessEnum) {
                case SYNCHRO://复核同步
                {
                    chksApprovalBusiness.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                    break;
                }
                case ASYNCHRONOUS://复核异步
                {
                    //处理变量
                    List<ChksApprovalInfo> chksApprovalInfoList = chksApprovalService.getChksApprovalInfoList(chksApprovalBusiness.getBusinessProcessInsId());
                    if (chksApprovalInfoList.size() > 2) {
                        chksApprovalBusiness.setStatus(ProcessStatusEnum.RUN.getValue());
                        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(parameterValues);
                        //发起流程
                        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
                        ProcessInfo processInfo = new ProcessInfo();
                        processInfo.setProcessName(boxReDto.getProcessName());
                        processInfo.setGroupName(boxReDto.getGroupName());
                        processInfo.setFolio(folio);//流程描述
                        processInfo.setBoxId(boxReDto.getId());
                        processInfo.setProcessEventExecutorName(BaseProcessEvent.class.getSimpleName());
                        processInfo.setWorkPhaseId(0);
                        processInfo.setTableId(chksApprovalAssessDto.getChksApprovalBusinessId());
                        processInfo.setTableName("tb_chks_approval_business");
                        LoopTaskNodeArg loopTaskNodeArg = new LoopTaskNodeArg();
                        loopTaskNodeArg.setLoopCount(chksApprovalInfoList.size() - 2);

                        ChksApprovalInfo chksApprovalInfo = chksApprovalInfoList.get(2);
                        loopTaskNodeArg.setUsers(FormatUtils.transformString2List(chksApprovalInfo.getPersonString()));
                        processInfo.setTaskNodeArg(loopTaskNodeArg);
                        processInfo.setSkipActivityIds(new ArrayList<>());
                        try {
                            processInsId = serviceComponent.processStart(processInfo, serviceComponent.getThisUser(), boxReChksProcessEnum);//发起流程，并返回流程实例编号
                        } catch (BpmException e) {
                            throw new BusinessException(e.getMessage());
                        }
                    } else {
                        chksApprovalBusiness.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                    }
                    break;
                }
                case INDEPENDENT://单独考核
                {
                    chksApprovalBusiness.setStatus(ProcessStatusEnum.RUN.getValue());
                    String chksBoxName = businessBoxReDto.getChksBoxName();
                    if (StringUtils.isBlank(chksBoxName)) {
                        chksBoxName = parameterValues;
                    }
                    Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(chksBoxName);
                    //发起流程
                    BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
                    ProcessInfo processInfo = new ProcessInfo();
                    processInfo.setProcessName(boxReDto.getProcessName());
                    processInfo.setGroupName(boxReDto.getGroupName());
                    processInfo.setFolio(folio);//流程描述
                    processInfo.setBoxId(boxReDto.getId());
                    processInfo.setProcessEventExecutorName(BaseProcessEvent.class.getSimpleName());
                    processInfo.setWorkPhaseId(0);
                    processInfo.setWorkPhaseId(0);
                    processInfo.setTableId(chksApprovalAssessDto.getChksApprovalBusinessId());
                    try {
                        processInsId = serviceComponent.processStart(processInfo, serviceComponent.getThisUser(), boxReChksProcessEnum);//发起流程，并返回流程实例编号
                    } catch (BpmException e) {
                        throw new BusinessException(e.getMessage());
                    }
                    break;
                }
            }

            //更新相应的原始数据的考核状态
            chksApprovalBusiness.setProcessInsId(processInsId);

            chksApprovalBusiness.setBisCheck(true);
            chksApprovalBusinessService.updateChksApprovalBusiness(chksApprovalBusiness);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void repairAssessChksSave(String formData) throws BusinessException {
        //保存业务数据
        saveChksApprovalAssessDtos(formData, serviceComponent.getThisUser());

    }
    @Transactional(rollbackFor = Exception.class)
    public void editAssessChksSave(String formData) throws BusinessException {
        List<ChksApprovalAssessDto> chksApprovalAssessDtos = JSON.parseArray(formData, ChksApprovalAssessDto.class);
        /**
         * 处理过程
         * 1、将原数据设置为无效，并填写相应的更新信息
         * 2、将新数据添加到系统中
         */

        //更新原数据
        ChksApprovalAssess chksApprovalAssess = new ChksApprovalAssess();
        chksApprovalAssess.setBisEnable(false);
        chksApprovalAssess.setModifyUser(serviceComponent.getThisUser());
        chksApprovalAssessDao.updateAssessByActivityId(chksApprovalAssessDtos.get(0).getChksApprovalInfoId(), chksApprovalAssess);
        //保存业务数据
        saveChksApprovalAssessDtos(formData, serviceComponent.getThisUser());

    }

    private List<ChksApprovalAssessDto> saveChksApprovalAssessDtos(String formData, String userAccount) {
        List<ChksApprovalAssessDto> chksApprovalAssessDtos = JSON.parseArray(formData, ChksApprovalAssessDto.class);
        for (ChksApprovalAssessDto chksApprovalAssessDto : chksApprovalAssessDtos) {
            ChksApprovalAssess chksApprovalAssess = new ChksApprovalAssess();
            BeanUtils.copyProperties(chksApprovalAssessDto, chksApprovalAssess);
            chksApprovalAssess.setCreator(userAccount);
            chksApprovalAssessDao.addChksApprovalAssess(chksApprovalAssess);

            for (ChksApprovalAssessDetails item : chksApprovalAssessDto.getChksApprovalAssessDetails()) {
                item.setChksApprovalAssessId(chksApprovalAssess.getId());
                AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(item.getAssessModelId());
                item.setAssessModelTitle(assessmentItem.getAssessmentDes());
                item.setAssessModelMax(assessmentItem.getMaxScore());
                item.setAssessModelMin(assessmentItem.getMinScore());
                chksApprovalAssessDao.addChksApprovalAssessDetails(item);
            }
            //要所节点考核数据
            ChksApprovalInfo chksApprovalInfo = chksApprovalService.getChksApprovalInfoById(chksApprovalAssessDto.getChksApprovalInfoId());
            chksApprovalInfo.setBisChks(true);
            chksApprovalService.updateChksApprovalInfo(chksApprovalInfo);
        }
        return chksApprovalAssessDtos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void approvalAssessResult(ApprovalModelDto approvalModelDto, String formData) throws BusinessException {
        approvalModelDto.setAppKey(applicationConstant.getAppKey());
        approvalModelDto.setBisNext("0");
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setAgentUserAccount("");
        approvalModelDto.setBisAuto(false);
        List<ChksApprovalAssessDto> chksApprovalAssessDtos = saveChksApprovalAssessDtos(formData, serviceComponent.getThisUser());//保存考核数据
        ChksApprovalBusiness chksApprovalBusiness = chksApprovalBusinessService.getChksApprovalBusinessById(chksApprovalAssessDtos.get(0).getChksApprovalBusinessId());
        try {
            BoxReDto businessBoxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(chksApprovalBusiness.getBusinessBoxId());
            BoxReChksProcessEnum boxReChksProcessEnum = BoxReChksProcessEnum.create(businessBoxReDto.getChksProcess());
            switch (boxReChksProcessEnum) {
                case ASYNCHRONOUS://复核异步
                {
                    //处理变量
                    List<ChksApprovalInfo> chksApprovalInfoList = chksApprovalService.getChksApprovalInfoList(chksApprovalBusiness.getBusinessProcessInsId());
                    if (approvalModelDto.getCurrentStep() + 3 < chksApprovalInfoList.size()) {
                        ChksApprovalInfo chksApprovalInfo = chksApprovalInfoList.get(approvalModelDto.getCurrentStep() + 3);
                        approvalModelDto.setNextApproval(FormatUtils.transformString2List(chksApprovalInfo.getPersonString()));
                    }
                    BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoByBoxIdSorting(chksApprovalBusiness.getBusinessBoxId(), approvalModelDto.getCurrentStep());
                    approvalModelDto.setActivityId(boxReActivityDto.getId());
                    approvalModelDto.setSkipActivity(new ArrayList<>());
                    approvalModelDto.setCurrUserAccount(serviceComponent.getThisUser());
                    bpmRpcProcessInsManagerService.processSubmitLoopTaskNodeArg(approvalModelDto);
                    break;
                }
                case INDEPENDENT://单独考核
                {
                    serviceComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
                }
            }
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<ChksApprovalAssessVo> getChksApprovalAssessVoByBusinessId(Integer businessId) {
        ChksApprovalAssess chksApprovalAssess = new ChksApprovalAssess();
        chksApprovalAssess.setChksApprovalBusinessId(businessId);
        chksApprovalAssess.setBisEnable(true);
        List<ChksApprovalAssess> chksApprovalAssessList = chksApprovalAssessDao.getChksApprovalAssessList(chksApprovalAssess);
        List<ChksApprovalAssessVo> transform = LangUtils.transform(chksApprovalAssessList, o -> getChksApprovalAssessVo(o));
        return transform;
    }

    public List<ChksApprovalAssessVo> getChksApprovalAssessVoByActivityId(Integer activityId) {
        ChksApprovalAssess chksApprovalAssess = new ChksApprovalAssess();
        chksApprovalAssess.setChksApprovalInfoId(activityId);
        chksApprovalAssess.setBisEnable(true);
        List<ChksApprovalAssess> chksApprovalAssessList = chksApprovalAssessDao.getChksApprovalAssessList(chksApprovalAssess);
        List<ChksApprovalAssessVo> transform = LangUtils.transform(chksApprovalAssessList, o -> getChksApprovalAssessVo(o));
        return transform;
    }

    private ChksApprovalAssessVo getChksApprovalAssessVo(ChksApprovalAssess chksApprovalAssess) {
        ChksApprovalAssessVo chksApprovalAssessVo = new ChksApprovalAssessVo();
        if (chksApprovalAssess != null) {
            BeanUtils.copyProperties(chksApprovalAssess, chksApprovalAssessVo);
            SysUserDto sysUser = erpRpcUserService.getSysUser(chksApprovalAssess.getUserAccount());
            if (sysUser != null) {
                chksApprovalAssessVo.setUserName(sysUser.getUserName());
                chksApprovalAssessVo.setDepartmentName(sysUser.getDepartmentName());
            }
            sysUser = erpRpcUserService.getSysUser(chksApprovalAssess.getCreator());
            if (sysUser != null) {
                chksApprovalAssessVo.setCreatorName(sysUser.getUserName());
            }

            ChksApprovalInfo chksApprovalInfo = chksApprovalService.getChksApprovalInfoById(chksApprovalAssess.getChksApprovalInfoId());
            ChksApprovalInfoVo chksApprovalInfoVo = chksApprovalService.getChksApprovalInfoVo(chksApprovalInfo);
            chksApprovalAssessVo.setActivityName(chksApprovalInfoVo.getActivityName());
            ChksApprovalAssessDetails chksApprovalAssessDetails = new ChksApprovalAssessDetails();
            chksApprovalAssessDetails.setChksApprovalAssessId(chksApprovalAssess.getId());
            List<ChksApprovalAssessDetails> chksApprovalAssessDetailsList = chksApprovalAssessDao.getChksApprovalAssessDetailsList(chksApprovalAssessDetails);
            chksApprovalAssessVo.setChksApprovalAssessDetails(chksApprovalAssessDetailsList);
        }
        return chksApprovalAssessVo;
    }
}

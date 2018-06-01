package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.common.enums.CustomerTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessParameterConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.csr.CsrProjectInfoDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterServcie;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-5-31.
 */
@Service
public class CsrProjectInfoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CsrProjectInfoDao csrProjectInfoDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CsrInvalidRuleService csrInvalidRuleService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseParameterServcie baseParameterServcie;
    @Autowired
    private BaseDataDicService baseDataDicService;

    /**
     * 获取vo
     *
     * @param csrProjectInfo
     * @return
     */
    public CsrProjectInfoVo getCsrProjectInfoVo(CsrProjectInfo csrProjectInfo) {
        if (csrProjectInfo == null) return null;
        CsrProjectInfoVo csrProjectInfoVo = new CsrProjectInfoVo();
        BeanUtils.copyProperties(csrProjectInfo, csrProjectInfoVo);
        if (StringUtils.isNotBlank(csrProjectInfo.getDistributionUser())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(csrProjectInfo.getDistributionUser());
            if (sysUser != null)
                csrProjectInfoVo.setDistributionUserName(sysUser.getUserName());
        }
        if(csrProjectInfo.getEntrustPurpose()!=null){
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(csrProjectInfo.getEntrustPurpose());
            if(baseDataDic!=null)
                csrProjectInfoVo.setEntrustPurposeName(baseDataDic.getName());
        }
        if(csrProjectInfo.getCustomerType()!=null){
            CustomerTypeEnum[] customerTypeEnums = CustomerTypeEnum.values();
            for (CustomerTypeEnum customerTypeEnum : customerTypeEnums) {
                if(csrProjectInfo.getCustomerType().equals(customerTypeEnum.getId())){
                    csrProjectInfoVo.setCustomerTypeName(customerTypeEnum.getName());
                }
            }
        }
        return csrProjectInfoVo;
    }

    /**
     * 根据流程实例id获取数据
     * @param processInsId
     * @return
     */
    public CsrProjectInfoVo getCsrProjectInfoVo(String processInsId){
        CsrProjectInfo csrProjectInfo = csrProjectInfoDao.getCsrProjectInfo(processInsId);
        return getCsrProjectInfoVo(csrProjectInfo);
    }

    /**
     * 保存债权项目信息
     *
     * @param csrProjectInfo
     */
    public void saveCsrProjectInfo(CsrProjectInfo csrProjectInfo) {
        if (csrProjectInfo.getId() != null && csrProjectInfo.getId() > 0) {
            csrProjectInfoDao.updateCsrProjectInfo(csrProjectInfo);
        } else {
            csrProjectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getName());
            csrProjectInfo.setCreator(commonService.thisUserAccount());
            csrProjectInfoDao.addCsrProjectInfo(csrProjectInfo);
        }
    }


    /**
     * 发起项目
     *
     * @param csrProjectInfo
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void csrProjectApply(CsrProjectInfo csrProjectInfo) throws BusinessException {
        //1.保存项目信息 2发起流程
        if (csrProjectInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        saveCsrProjectInfo(csrProjectInfo);

        //更新过滤规则外键
        csrInvalidRuleService.updateCsrProjectId(csrProjectInfo.getId());

        //更新附件表tableId
        baseAttachmentService.relationToTable(AssessTableNameConstant.CSR_PROJECT_INFO, null, csrProjectInfo.getId());

        //读取导入的数据
        String boxName = baseParameterServcie.getParameterValues(AssessParameterConstant.CSR_PROJECT_APPLY_PROCESS_KEY);
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(csrProjectInfo.getName());//流程描述
        processInfo.setTableName(AssessTableNameConstant.CSR_PROJECT_INFO);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setStartUser(commonService.thisUserAccount());
        processInfo.setProcessEventExecutorName(BaseProcessEvent.class.getSimpleName());
        try {
            processInfo.setTableId(csrProjectInfo.getId());
            ProcessUserDto processUserDto = processControllerComponent.processStart(processInfo, csrProjectInfo.getDistributionUser(), false);

            csrProjectInfo.setStatus(ProcessStatusEnum.RUN.getValue());
            csrProjectInfo.setProcessInsId(processUserDto.getProcessInsId());
            csrProjectInfoDao.updateCsrProjectInfo(csrProjectInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 立项审批
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void crsProjectApproval(ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    /**
     * 返回修改
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void crsProjectEdit(CsrProjectInfo csrProjectInfo,ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        if (csrProjectInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        saveCsrProjectInfo(csrProjectInfo);
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setAppointUserAccount(csrProjectInfo.getDistributionUser());
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

}

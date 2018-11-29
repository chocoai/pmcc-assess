package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicApplyDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.basic.BasicApplyEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-10-24.
 */
@Service
public class BasicApplyService {
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BasicApply getByBasicApplyId(Integer id) {
        return basicApplyDao.getBasicApplyById(id);
    }

    public BasicApply getBasicApplyByProcessInsId(String processInsId) {
        BasicApply basicApply = new BasicApply();
        basicApply.setProcessInsId(processInsId);
        List<BasicApply> basicApplies = basicApplyDao.getBasicApplyList(basicApply);
        if (!ObjectUtils.isEmpty(basicApplies)) {
            return basicApplies.get(0);
        } else {
            return null;
        }
    }

    public Integer saveBasicApply(BasicApply basicApply) {
        if (basicApply.getId() == null || basicApply.getId() == 0) {
            basicApply.setCreator(commonService.thisUserAccount());
            basicApply.setStatus(ProjectStatusEnum.STARTAPPLY.getKey());
            basicApplyDao.addBasicApply(basicApply);
        } else {
            basicApplyDao.updateBasicApply(basicApply);
        }
        return basicApply.getId();
    }

    public boolean updateBasicApply(BasicApply basicApply) {
        return basicApplyDao.updateBasicApply(basicApply);
    }

    /**
     * 流程发起
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public ProcessUserDto processStartSubmit(BasicApply basicApply) throws Exception {
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(getFullName(basicApply.getEstateName(), basicApply.getBuildingNumber(), basicApply.getUnitNumber(), basicApply.getHouseNumber()));
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(BasicApply.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setProcessEventExecutor(BasicApplyEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
        processInfo.setProcessEventExecutorName(BasicApplyEvent.class.getSimpleName());
        processInfo.setTableId(basicApply.getId());
        try {
            processUserDto = processControllerComponent.processStart(processInfo, processControllerComponent.getThisUser(), false);
            basicApply.setProcessInsId(processUserDto.getProcessInsId());
            basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
            this.updateBasicApply(basicApply);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw e;
        }
        return processUserDto;
    }

    /**
     * 审批
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processApprovalSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 案例 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
       try{
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(String estateName, Boolean draftFlag) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicApply> basicApplyList = basicApplyDao.getBasicApplyListByName(estateName, draftFlag);
        List<BasicApplyVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(basicApplyList)) {
            for (BasicApply basicApply1 : basicApplyList) {
                vos.add(getBasicApplyVo(basicApply1));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicApplyVo>(10) : vos);
        return vo;
    }

    public BasicApplyVo getBasicApplyVo(BasicApply basicApply) {
        if (basicApply == null) {
            return null;
        }
        BasicApplyVo vo = new BasicApplyVo();
        BeanUtils.copyProperties(basicApply, vo);
        vo.setFullName(getFullName(basicApply.getEstateName(), basicApply.getBuildingNumber(), basicApply.getUnitNumber(), basicApply.getHouseNumber()));
        return vo;
    }

    /**
     * 获取申请完整名称
     *
     * @param estateName
     * @param buildingNumber
     * @param unitNumber
     * @param houseNumber
     * @return
     */
    public String getFullName(String estateName, String buildingNumber, String unitNumber, String houseNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(estateName))
            stringBuilder.append(estateName);
        if (StringUtils.isNotBlank(buildingNumber))
            stringBuilder.append(buildingNumber).append("栋");
        if (StringUtils.isNotBlank(unitNumber))
            stringBuilder.append(unitNumber).append("单元");
        if (StringUtils.isNotBlank(houseNumber))
            stringBuilder.append(houseNumber).append("号");
        return stringBuilder.toString();
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
    public void deleteBasicApply(Integer id) throws Exception {
        basicEstateService.clearInvalidData(id);
        basicBuildingService.clearInvalidData(id);
        basicUnitService.clearInvalidData(id);
        basicHouseService.clearInvalidData(id);
        basicApplyDao.deleteBasicApply(id);
    }

}

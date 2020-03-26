package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoUpgradeDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgrade;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordApprovalVo;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;

import com.copower.pmcc.assess.service.event.project.NetInfoUpgradeEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetInfoUpgradeService {
    @Autowired
    private NetInfoUpgradeDao netInfoUpgradeDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private NetInfoRecordHouseService netInfoRecordHouseService;
    @Autowired
    private NetInfoRecordLandService netInfoRecordLandService;
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private NetInfoRecordService netInfoRecordService;


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(Integer id, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            //验证
            NetInfoUpgrade netInfoUpgrade = this.getDataById(id);
            NetInfoUpgrade verifyData = new NetInfoUpgrade();
            verifyData.setDataId(netInfoUpgrade.getDataId());
            verifyData.setType(netInfoUpgrade.getType());
            verifyData.setStatus(ProcessStatusEnum.RUN.getValue());
            List<NetInfoUpgrade> upgradeList = netInfoUpgradeDao.getNetInfoUpgrade(verifyData);
            if (CollectionUtils.isNotEmpty(upgradeList)) {
                throw new BusinessException("已发起升级流程，未结束，请不要重复发起");
            }
            netInfoUpgrade.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(netInfoUpgrade, baseParameterEnum);
            if (processUserDto != null) netInfoUpgrade.setProcessInsId(processUserDto.getProcessInsId());
            netInfoUpgradeDao.modifyNetInfoUpgrade(netInfoUpgrade);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(NetInfoUpgrade netInfoUpgrade, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setFolio(String.format("【案例信息升级】%s%s", publicService.getUserNameByAccount(commonService.thisUserAccount()), DateUtils.todayDate()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoUpgrade.class));
        processInfo.setProcessEventExecutor(NetInfoUpgradeEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(netInfoUpgrade.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(netInfoUpgrade.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);

        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public NetInfoUpgrade getDataByProcessInsId(String processInsId) {
        NetInfoUpgrade netInfoUpgrade = new NetInfoUpgrade();
        netInfoUpgrade.setProcessInsId(processInsId);
        List<NetInfoUpgrade> netInfoUpgrades = netInfoUpgradeDao.getNetInfoUpgrade(netInfoUpgrade);
        if (CollectionUtils.isNotEmpty(netInfoUpgrades))
            netInfoUpgrade = netInfoUpgrades.get(0);
        return netInfoUpgrade;
    }

    public NetInfoUpgrade getDataById(Integer id) {
        return netInfoUpgradeDao.getDataById(id);
    }

    public void approvalCommit(ApprovalModelDto approvalModelDto, String processInsId) {
        try {
            NetInfoUpgrade upgrade = getDataByProcessInsId(processInsId);
            upgrade.setApprover(commonService.thisUserAccount());
            editData(upgrade);
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            e.printStackTrace();
            log.error("提交失败", e);
        }
    }

    /**
     * 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    public void editData(NetInfoUpgrade netInfoUpgrade) {
        netInfoUpgradeDao.modifyNetInfoUpgrade(netInfoUpgrade);
    }

    public void deleteData(Integer id) {
        netInfoUpgradeDao.deleteData(id);
    }

    public void addNetInfoUpgrade(NetInfoUpgrade netInfoUpgrade) {
        netInfoUpgradeDao.addNetInfoUpgrade(netInfoUpgrade);
    }

    public BootstrapTableVo getNetInfoRecordHouseList(Integer id, Integer applyId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordHouse> netInfoRecordHouses = Lists.newArrayList();
        NetInfoUpgrade upgrade = getDataById(applyId);
        if (StringUtils.isNotEmpty(upgrade.getJsonData())) {
            netInfoRecordHouses.add(JSON.parseObject(upgrade.getJsonData(), NetInfoRecordHouse.class));
        } else {
            netInfoRecordHouses.add(netInfoRecordHouseDao.getNetInfoRecordHouseById(id));
        }

        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);

        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordHouses == null ? new ArrayList() : LangUtils.transform(netInfoRecordHouses, o -> netInfoRecordHouseService.getNetInfoRecordHouseVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public BootstrapTableVo getNetInfoRecordLandList(Integer id, Integer applyId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordLand> netInfoRecordLands = Lists.newArrayList();
        NetInfoUpgrade upgrade = getDataById(applyId);
        if (StringUtils.isNotEmpty(upgrade.getJsonData())) {
            netInfoRecordLands.add(JSON.parseObject(upgrade.getJsonData(), NetInfoRecordLand.class));
        } else {
            netInfoRecordLands.add(netInfoRecordLandDao.getNetInfoRecordLandById(id));
        }

        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);

        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordLands == null ? new ArrayList() : LangUtils.transform(netInfoRecordLands, o -> netInfoRecordLandService.getNetInfoRecordLandVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    //清除无效数据
    public void cleanInvalidData(Integer dataId, String type) {
        List<NetInfoUpgrade> upgradeList = netInfoUpgradeDao.getNetInfoUpgradeList(dataId, type);
        if (CollectionUtils.isNotEmpty(upgradeList)) {
            for (NetInfoUpgrade item : upgradeList) {
                netInfoUpgradeDao.deleteData(item.getId());
            }
        }
    }
}

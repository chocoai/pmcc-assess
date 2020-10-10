package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoAssignTaskDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordApprovalVo;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
import com.copower.pmcc.assess.service.event.project.NetInfoAssignTaskEvent;
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
public class NetInfoAssignTaskService {
    @Autowired
    private NetInfoAssignTaskDao netInfoAssignTaskDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
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
    @Autowired
    private AssessmentCommonService assessmentCommonService;


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(Integer id, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            NetInfoAssignTask netInfoAssignTask = this.getDataById(id);
            netInfoAssignTask.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(netInfoAssignTask, baseParameterEnum);
            if (processUserDto != null) netInfoAssignTask.setProcessInsId(processUserDto.getProcessInsId());
            netInfoAssignTaskDao.modifyNetInfoAssignTask(netInfoAssignTask);
            //更新house、land中没有设置assignTaskId的数据
            if (StringUtils.isNotBlank(netInfoAssignTask.getNetInfoIds())) {
                List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(netInfoAssignTask.getNetInfoIds()));
                List<NetInfoRecord> infoRecords = LangUtils.transform(integers, o -> netInfoRecordDao.getInfoById(o));
                if (CollectionUtils.isNotEmpty(infoRecords)) {
                    for (NetInfoRecord netInfo : infoRecords) {
                        netInfo.setStatus(3);
                        netInfoRecordService.updateInfo(netInfo);
                    }
                }
                List<NetInfoRecordHouse> houses = netInfoRecordHouseDao.getHouseListByMasterIds(integers);
                if (CollectionUtils.isNotEmpty(houses)) {
                    for (NetInfoRecordHouse house : houses) {
                        if (house.getAssignTaskId() == null) {
                            house.setAssignTaskId(id);
                            netInfoRecordHouseDao.updateNetInfoRecordHouse(house,false);
                        }
                    }
                }
                List<NetInfoRecordLand> lands = netInfoRecordLandDao.getLandListByMasterIds(integers);
                if(CollectionUtils.isNotEmpty(lands)){
                    for (NetInfoRecordLand land : lands) {
                        if (land.getAssignTaskId() == null) {
                            land.setAssignTaskId(id);
                            netInfoRecordLandDao.updateNetInfoRecordLand(land, false);
                        }
                    }
                }
            }

            //修改状态
            List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getHouseListByAssignTaskId(id);
            if (CollectionUtils.isNotEmpty(netInfoRecordHouses)) {
                netInfoRecordHouses.forEach(o -> {
                    o.setStatus(2);
                    netInfoRecordHouseDao.updateNetInfoRecordHouse(o, false);
                });
            }
            List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getLandListByAssignTaskId(id);
            if (CollectionUtils.isNotEmpty(netInfoRecordLands)) {
                netInfoRecordLands.forEach(o -> {
                    o.setStatus(2);
                    netInfoRecordLandDao.updateNetInfoRecordLand(o, false);
                });
            }


        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(NetInfoAssignTask netInfoAssignTask, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setFolio(String.format("【案例整理】%s%s", publicService.getUserNameByAccount(commonService.thisUserAccount()), DateUtils.todayDate()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoAssignTask.class));
        processInfo.setProcessEventExecutor(NetInfoAssignTaskEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(netInfoAssignTask.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(netInfoAssignTask.getExecutor());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
            //关闭任务
            String url = String.format("/pmcc-assess/netInfoAssignTask/apply?id=%s", netInfoAssignTask.getId());
            ProjectResponsibilityDto projectPlanResponsibility = new ProjectResponsibilityDto();
            projectPlanResponsibility.setUrl(url);
            List<ProjectResponsibilityDto> projectResponsibilityDtoList = bpmRpcProjectTaskService.getProjectTaskList(projectPlanResponsibility);
            if (CollectionUtils.isNotEmpty(projectResponsibilityDtoList)) {
                for (ProjectResponsibilityDto oo : projectResponsibilityDtoList) {
                    bpmRpcProjectTaskService.deleteProjectTask(oo.getId());
                }
            }
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public NetInfoAssignTask getDataByProcessInsId(String processInsId) {
        NetInfoAssignTask netInfoAssignTask = new NetInfoAssignTask();
        netInfoAssignTask.setProcessInsId(processInsId);
        List<NetInfoAssignTask> netInfoAssignTasks = netInfoAssignTaskDao.getNetInfoAssignTask(netInfoAssignTask);
        if (CollectionUtils.isNotEmpty(netInfoAssignTasks))
            netInfoAssignTask = netInfoAssignTasks.get(0);
        return netInfoAssignTask;
    }

    public NetInfoAssignTask getDataById(Integer id) {
        return netInfoAssignTaskDao.getDataById(id);
    }

    public NetInfoAssignTask getNetInfoAssignTaskBySource(String source) {
        if (StringUtils.isBlank(source)) return null;
        return netInfoAssignTaskDao.getNetInfoAssignTaskBySource(source, commonService.thisUserAccount());
    }

    /**
     * 创建考核任务
     */
    public void createAssessmentTask(Integer boxId, String processInsId, String taskId) {
        assessmentCommonService.generateAssessmentTask(processInsId, boxId, taskId, null, null);
    }

    public void approvalCommit(ApprovalModelDto approvalModelDto, String processInsId) {
        try {
            //审批提交写入审批人
            NetInfoAssignTask data = getDataByProcessInsId(processInsId);
            List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getLandListByAssignTaskId(data.getId());
            if (CollectionUtils.isNotEmpty(netInfoRecordLands)) {
                netInfoRecordLands.forEach(o -> {
                    o.setApprover(commonService.thisUserAccount());
                    netInfoRecordLandDao.updateNetInfoRecordLand(o, false);
                });
            }
            List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getHouseListByAssignTaskId(data.getId());
            if (CollectionUtils.isNotEmpty(netInfoRecordHouses)) {
                netInfoRecordHouses.forEach(o -> {
                    o.setApprover(commonService.thisUserAccount());
                    netInfoRecordHouseDao.updateNetInfoRecordHouse(o, false);
                });
            }
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            assessmentCommonService.createProjectTask(approvalModelDto, null, null);
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


    public void editData(NetInfoAssignTask netInfoAssignTask) {
        netInfoAssignTaskDao.modifyNetInfoAssignTask(netInfoAssignTask);
    }

    public void deleteData(Integer id) {
        netInfoAssignTaskDao.deleteData(id);
    }

    public void addNetInfoAssignTask(NetInfoAssignTask netInfoAssignTask) {
        netInfoAssignTaskDao.addNetInfoAssignTask(netInfoAssignTask);
    }

    public BootstrapTableVo getNetInfoRecordHouseList(List<Integer> integers) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getHouseListByMasterIds(integers);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordHouses == null ? new ArrayList() : LangUtils.transform(netInfoRecordHouses, o -> netInfoRecordHouseService.getNetInfoRecordHouseVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public BootstrapTableVo getNetInfoRecordLandList(List<Integer> integers) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getLandListByMasterIds(integers);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordLands == null ? new ArrayList() : LangUtils.transform(netInfoRecordLands, o -> netInfoRecordLandService.getNetInfoRecordLandVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public BootstrapTableVo getHouseListByAssignTaskId(Integer assignTaskId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        if (assignTaskId == null) return bootstrapTableVo;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getHouseListByAssignTaskId(assignTaskId);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordHouses == null ? new ArrayList() : LangUtils.transform(netInfoRecordHouses, o -> netInfoRecordHouseService.getNetInfoRecordHouseVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public BootstrapTableVo getLandListByAssignTaskId(Integer assignTaskId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        if (assignTaskId == null) return bootstrapTableVo;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getLandListByAssignTaskId(assignTaskId);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordLands == null ? new ArrayList() : LangUtils.transform(netInfoRecordLands, o -> netInfoRecordLandService.getNetInfoRecordLandVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }


    public BootstrapTableVo getNetInfoRecordApprovalVos(List<Integer> integers) {
        if (CollectionUtils.isEmpty(integers)) return null;
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordApprovalVo> list = Lists.newArrayList();
        List<NetInfoRecord> infoRecords = LangUtils.transform(integers, o -> netInfoRecordDao.getInfoById(o));
        if (CollectionUtils.isNotEmpty(infoRecords)) {
            for (NetInfoRecord netInfo : infoRecords) {
                if (StringUtils.equals(netInfo.getBelongType(), "房产")) {
                    NetInfoRecordHouse house = new NetInfoRecordHouse();
                    house.setMasterId(netInfo.getId());
                    List<NetInfoRecordHouseVo> vos = netInfoRecordHouseService.getVos(house);
                    if (CollectionUtils.isNotEmpty(vos)) {
                        NetInfoRecordHouseVo netInfoRecordHouseVo = vos.get(vos.size() - 1);
                        NetInfoRecordApprovalVo vo = new NetInfoRecordApprovalVo();
                        BeanUtils.copyProperties(netInfoRecordHouseVo, vo);
                        vo.setSourceSiteUrl(netInfo.getSourceSiteUrl());
                        StringBuilder content = new StringBuilder();
                        content.append("大类:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getType()) ? "未知" : netInfoRecordHouseVo.getType()).append(";");
                        content.append("房产类型:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getBelongType()) ? "未知" : netInfoRecordHouseVo.getBelongType()).append(";");
                        content.append("房产类别:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getBelongCategory()) ? "未知" : netInfoRecordHouseVo.getBelongCategory()).append(";");
                        content.append("街道:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getStreet()) ? "未知" : netInfoRecordHouseVo.getStreet()).append(";");
                        content.append("面积:").append(netInfoRecordHouseVo.getArea() == null ? "未知" : netInfoRecordHouseVo.getArea()).append(";");
                        content.append("楼盘名称:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getName()) ? "未知" : netInfoRecordHouseVo.getName()).append(";");
                        content.append("交易方式:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getDealTypeName()) ? "未知" : netInfoRecordHouseVo.getDealTypeName()).append(";");
                        content.append("成交价:").append(netInfoRecordHouseVo.getCurrentPrice() == null ? "未知" : netInfoRecordHouseVo.getCurrentPrice()).append(";");
                        content.append("成交(协商)日期:").append(netInfoRecordHouseVo.getNegotiatedDate() == null ? "未知" : DateUtils.format(netInfoRecordHouseVo.getNegotiatedDate(), DateUtils.DATE_CHINESE_PATTERN)).append(";");
                        content.append("评估价:").append(netInfoRecordHouseVo.getConsultPrice() == null ? "未知" : netInfoRecordHouseVo.getConsultPrice()).append(";");
                        content.append("评估基准日:").append(netInfoRecordHouseVo.getAssessStandardDate() == null ? "未知" : DateUtils.format(netInfoRecordHouseVo.getAssessStandardDate(), DateUtils.DATE_CHINESE_PATTERN)).append(";");
                        content.append("单价:").append(netInfoRecordHouseVo.getUnitPrice() == null ? "未知" : netInfoRecordHouseVo.getUnitPrice()).append(";");
                        content.append("变现率:").append(netInfoRecordHouseVo.getHouseRealizationRatios() == null ? "未知" : ArithmeticUtils.getPercentileSystem(netInfoRecordHouseVo.getHouseRealizationRatios(), 4, BigDecimal.ROUND_HALF_UP)).append(";");
                        content.append("变现周期:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getRealizationCycle()) ? "未知" : netInfoRecordHouseVo.getRealizationCycle()).append(";");
                        content.append("成交对象概况:").append(StringUtils.isEmpty(netInfoRecordHouseVo.getDealPartInfo()) ? "未知" : netInfoRecordHouseVo.getDealPartInfo()).append(";");
                        vo.setContent(content.toString());
                        list.add(vo);
                    }
                }
                if (StringUtils.equals(netInfo.getBelongType(), "土地")) {
                    NetInfoRecordLand land = new NetInfoRecordLand();
                    land.setMasterId(netInfo.getId());
                    List<NetInfoRecordLandVo> vos = netInfoRecordLandService.getVos(land);
                    if (CollectionUtils.isNotEmpty(vos)) {
                        NetInfoRecordLandVo netInfoRecordLandVo = vos.get(vos.size() - 1);
                        NetInfoRecordApprovalVo vo = new NetInfoRecordApprovalVo();
                        BeanUtils.copyProperties(netInfoRecordLandVo, vo);
                        vo.setSourceSiteUrl(netInfo.getSourceSiteUrl());
                        StringBuilder content = new StringBuilder();
                        content.append("大类:").append(StringUtils.isEmpty(netInfoRecordLandVo.getType()) ? "未知" : netInfoRecordLandVo.getType()).append(";");
                        content.append("土地类型:").append(StringUtils.isEmpty(netInfoRecordLandVo.getBelongType()) ? "未知" : netInfoRecordLandVo.getBelongType()).append(";");
                        content.append("土地类别:").append(StringUtils.isEmpty(netInfoRecordLandVo.getBelongCategory()) ? "未知" : netInfoRecordLandVo.getBelongCategory()).append(";");
                        content.append("土地性质:").append(StringUtils.isEmpty(netInfoRecordLandVo.getLandPurpose()) ? "未知" : netInfoRecordLandVo.getLandPurpose()).append(";");
                        content.append("街道:").append(StringUtils.isEmpty(netInfoRecordLandVo.getStreet()) ? "未知" : netInfoRecordLandVo.getStreet()).append(";");
                        content.append("面积:").append(netInfoRecordLandVo.getArea() == null ? "未知" : netInfoRecordLandVo.getArea()).append(";");
                        content.append("单位（平方米、亩）:").append(netInfoRecordLandVo.getAreaUnit() == null ? "未知" : netInfoRecordLandVo.getAreaUnit()).append(";");
                        content.append("地块名称:").append(StringUtils.isEmpty(netInfoRecordLandVo.getName()) ? "未知" : netInfoRecordLandVo.getName()).append(";");
                        content.append("宗地编号:").append(netInfoRecordLandVo.getParcelNumber() == null ? "未知" : netInfoRecordLandVo.getParcelNumber()).append(";");
                        content.append("宗地位置:").append(netInfoRecordLandVo.getParcelSite() == null ? "未知" : netInfoRecordLandVo.getParcelSite()).append(";");
                        content.append("交易方式:").append(StringUtils.isEmpty(netInfoRecordLandVo.getDealTypeName()) ? "未知" : netInfoRecordLandVo.getDealTypeName()).append(";");
                        content.append("成交(协商)日期:").append(netInfoRecordLandVo.getNegotiatedDate() == null ? "未知" : DateUtils.format(netInfoRecordLandVo.getNegotiatedDate(), DateUtils.DATE_CHINESE_PATTERN)).append(";");
                        content.append("评估价:").append(netInfoRecordLandVo.getConsultPrice() == null ? "未知" : netInfoRecordLandVo.getConsultPrice()).append(";");
                        content.append("评估基准日:").append(netInfoRecordLandVo.getAssessStandardDate() == null ? "未知" : DateUtils.format(netInfoRecordLandVo.getAssessStandardDate(), DateUtils.DATE_CHINESE_PATTERN)).append(";");
                        content.append("单价:").append(netInfoRecordLandVo.getUnitPrice() == null ? "未知" : netInfoRecordLandVo.getUnitPrice()).append(";");
                        content.append("楼面地价:").append(netInfoRecordLandVo.getFloorPrice() == null ? "未知" : netInfoRecordLandVo.getFloorPrice()).append(";");
                        content.append("变现率:").append(netInfoRecordLandVo.getLandRealizationRatios() == null ? "未知" : ArithmeticUtils.getPercentileSystem(netInfoRecordLandVo.getLandRealizationRatios(), 4, BigDecimal.ROUND_HALF_UP)).append(";");
                        content.append("变现周期:").append(StringUtils.isEmpty(netInfoRecordLandVo.getRealizationCycle()) ? "未知" : netInfoRecordLandVo.getRealizationCycle()).append(";");
                        content.append("净用地面积:").append(netInfoRecordLandVo.getLandArea() == null ? "未知" : netInfoRecordLandVo.getLandArea()).append(";");
                        content.append("容积率:").append(netInfoRecordLandVo.getPlotRatio() == null ? "未知" : netInfoRecordLandVo.getPlotRatio()).append(";");
                        content.append("容积率说明:").append(netInfoRecordLandVo.getPlotRatioRemark() == null ? "未知" : netInfoRecordLandVo.getPlotRatioRemark()).append(";");
                        content.append("绿化率:").append(netInfoRecordLandVo.getGreeningRate() == null ? "未知" : ArithmeticUtils.getPercentileSystem(netInfoRecordLandVo.getGreeningRate(), 4, BigDecimal.ROUND_HALF_UP)).append(";");
                        content.append("绿化率说明:").append(StringUtils.isEmpty(netInfoRecordLandVo.getGreeningRateRemark()) ? "未知" : netInfoRecordLandVo.getGreeningRateRemark()).append(";");
                        content.append("建筑密度:").append(netInfoRecordLandVo.getBuildDensity() == null ? "未知" : netInfoRecordLandVo.getBuildDensity()).append(";");
                        content.append("建筑密度说明:").append(StringUtils.isEmpty(netInfoRecordLandVo.getBuildDensityRemark()) ? "未知" : netInfoRecordLandVo.getBuildDensityRemark()).append(";");
                        content.append("建筑高度:").append(netInfoRecordLandVo.getBuildHeight() == null ? "未知" : netInfoRecordLandVo.getBuildHeight()).append(";");
                        content.append("建筑高度说明:").append(StringUtils.isEmpty(netInfoRecordLandVo.getBuildHeightRemark()) ? "未知" : netInfoRecordLandVo.getBuildHeightRemark()).append(";");
                        content.append("指标款:").append(netInfoRecordLandVo.getIndexAmount() == null ? "未知" : netInfoRecordLandVo.getIndexAmount()).append(";");
                        content.append("指标款说明:").append(StringUtils.isEmpty(netInfoRecordLandVo.getIndexAmountRemark()) ? "未知" : netInfoRecordLandVo.getIndexAmountRemark()).append(";");
                        content.append("成交对象概况:").append(StringUtils.isEmpty(netInfoRecordLandVo.getDealPartInfo()) ? "未知" : netInfoRecordLandVo.getDealPartInfo()).append(";");
                        vo.setContent(content.toString());
                        list.add(vo);
                    }
                }
            }
        }
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(list) ? new ArrayList<NetInfoRecordApprovalVo>() : list);
        return bootstrapTableVo;


    }
}

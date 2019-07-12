package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyBatchVo;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.event.basic.BasicApplyBatchEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BasicApplyBatchService {
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private ResidueRatioService residueRatioService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private BasicHouseDamagedDegreeDao basicHouseDamagedDegreeDao;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseDamagedDegreeService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;


    private final Logger logger = LoggerFactory.getLogger(getClass());


    public List<ZtreeDto> getZtreeDto(Integer pid, Integer estateId) throws Exception {
        //主表
        BasicApplyBatch basicApplyBatch = getBasicApplyBatchByEstateId(estateId);
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        List<ZtreeDto> treeDtos = new ArrayList<>();
        for (BasicApplyBatchDetail item : basicApplyBatchDetails) {
            ZtreeDto treeDto2 = new ZtreeDto();
            treeDto2.setId(item.getId());
            treeDto2.setName(item.getName());
            treeDto2.setDisplayName(item.getDisplayName());
            treeDto2.setPid(item.getPid());
            //判断是否有子节点
            if (!haveChilds(item.getId(), basicApplyBatch.getId())) {
                treeDto2.setIsParent(false);
            }
            treeDtos.add(treeDto2);
        }
        return treeDtos;

    }

    public BasicApplyBatch getBasicApplyBatchByProcessInsId(String processInsId) throws Exception {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setProcessInsId(processInsId);
        List<BasicApplyBatch> basicApplyBatches = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(basicApplyBatches)) {
            return getBasicApplyBatchVo(basicApplyBatches.get(0));
        }
        return null;
    }


    public BasicApplyBatch getSingleData(BasicApplyBatch basicApplyBatch) {
        List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(infoList)) return infoList.get(0);
        return null;
    }


    public BasicApplyBatch getInfoById(Integer id) {
        return basicApplyBatchDao.getInfoById(id);
    }
    //删除
    public void deleteBasicBatchApply(Integer id) {
        basicApplyBatchDao.deleteInfo(id);
    }

    //获取草稿数据
    public BootstrapTableVo getBootstrapTableVo(String estateName, Boolean draftFlag) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicApplyBatch> BasicApplyBatchList = basicApplyBatchDao.getBasicApplyBatchListByName(estateName, commonService.thisUserAccount(), draftFlag);
        List<BasicApplyBatchVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(BasicApplyBatchList)) {
            for (BasicApplyBatch basicApplyBatch : BasicApplyBatchList) {
                vos.add(getBasicApplyBatchVo(basicApplyBatch));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicApplyBatchVo>(10) : vos);
        return vo;
    }


    /**
     * 通过estateId获取
     *
     * @param estateId
     * @return
     */
    public BasicApplyBatch getBasicApplyBatchByEstateId(Integer estateId) {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setEstateId(estateId);
        List<BasicApplyBatch> basicApplyBatches = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(basicApplyBatches)) {
            return basicApplyBatches.get(0);
        }
        return null;
    }

    /**
     * 是否子节点
     *
     * @param
     * @return
     */
    public Boolean haveChilds(Integer pid, Integer estateId) {
        List<BasicApplyBatchDetail> list = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(pid, estateId);
        if (CollectionUtils.isNotEmpty(list)) {
            return true;
        }
        return false;
    }

    //保存
    public BasicApplyBatch saveApplyInfo(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch.getId() != null && basicApplyBatch.getId() != 0) {
            BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
            BeanUtils.copyProperties(basicApplyBatch, basicEstate);
            basicEstate.setName(basicApplyBatch.getEstateName());
            basicEstate.setId(basicApplyBatch.getEstateId());
            basicEstateService.saveAndUpdateBasicEstate(basicEstate);
            basicApplyBatchDao.updateInfo(basicApplyBatch);
        } else {
            BasicEstate basicEstate = new BasicEstate();
            BeanUtils.copyProperties(basicApplyBatch, basicEstate);
            basicEstate.setName(basicApplyBatch.getEstateName());
            basicEstate.setId(null);
            basicEstateService.saveAndUpdateBasicEstate(basicEstate);
            basicApplyBatch.setEstateId(basicEstate.getId());
            basicApplyBatch.setCreator(commonService.thisUserAccount());
            BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
            basicEstateLandState.setEstateId(basicEstate.getId());
            basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState);
            basicApplyBatchDao.addInfo(basicApplyBatch);
        }
        return basicApplyBatch;
    }

    /**
     * 保存数据
     *
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDraft(String formData) throws Exception {
        String jsonContent = null;
        JSONObject jsonObject = JSON.parseObject(formData);
//        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_APPLY.getVar());
//        BasicApply basicApply = JSONObject.parseObject(jsonContent, BasicApply.class);
//        //检查是否标注了楼盘
//        if (!isDraft) {
//            taggingValid(basicApply);
//        }
//
//        if (basicApply.getId() == null || basicApply.getId() <= 0) {
//            basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
//            basicApply.setDraftFlag(isDraft);
//            basicApply.setCreator(commonService.thisUserAccount());
//            basicApplyService.saveBasicApply(basicApply);
//        } else {
//            basicApply = basicApplyService.getByBasicApplyId(basicApply.getId());
//        }

        //楼盘过程数据
        BasicEstate basicEstate = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            if (basicEstate != null) {
                basicEstateService.saveAndUpdateBasicEstate(basicEstate);
                BasicApplyBatch basicApplyBatch = getBasicApplyBatchByEstateId(basicEstate.getId());
                basicApplyBatch.setEstateName(basicEstate.getName());
                basicApplyBatchDao.updateInfo(basicApplyBatch);
                if (basicEstate.getId() != null) {
                    BasicEstateLandState basicEstateLandState = null;
                    String string = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar());
                    basicEstateLandState = JSONObject.parseObject(string, BasicEstateLandState.class);
                    if (basicEstateLandState != null) {
                        basicEstateLandState.setEstateId(basicEstate.getId());
                        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState);
                    }
                }
            }
        }

        //楼栋主数据
        BasicBuilding basicBuilding = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
            if (basicBuilding != null) {
                basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding);
                BasicApplyBatchDetail buildingDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_building", basicBuilding.getId());
                buildingDetail.setName(basicBuilding.getBuildingNumber());
                basicApplyBatchDetailDao.updateInfo(buildingDetail);
            }
        }

        //单元过程数据
        BasicUnit basicUnit = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
            if (basicUnit != null) {
                basicUnitService.saveAndUpdateBasicUnit(basicUnit);
                BasicApplyBatchDetail unitDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", basicUnit.getId());
                unitDetail.setName(basicUnit.getUnitNumber());
                basicApplyBatchDetailDao.updateInfo(unitDetail);
            }
        }

        //处理房屋数据
        BasicHouse basicHouse = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            if (basicHouse != null) {
                Integer house = basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                BasicApplyBatchDetail houseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_house", basicHouse.getId());
                houseDetail.setName(basicHouse.getHouseNumber());
                basicApplyBatchDetailDao.updateInfo(houseDetail);
                //交易信息
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar());
                BasicHouseTrading basicTrading = JSONObject.parseObject(jsonContent, BasicHouseTrading.class);
                if (basicTrading != null) {
                    basicTrading.setId(null);
                    basicTrading.setHouseId(house);
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading);
                }
                //完损度
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_DAMAGED_DEGREE.getVar());
                List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonContent, BasicHouseDamagedDegree.class);
                if (!org.springframework.util.CollectionUtils.isEmpty(damagedDegreeList)) {
                    for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                        Integer category = degree.getCategory();
                        BasicHouseDamagedDegree damagedDegree = basicHouseDamagedDegreeService.getDamagedDegreeByHouseIdAndCategory(house, category);
                        damagedDegree.setEntityCondition(degree.getEntityCondition());
                        damagedDegree.setEntityConditionContent(degree.getEntityConditionContent());
                        damagedDegree.setScore(degree.getScore());
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(damagedDegree);
                    }
                    //写入成新率
                    String newDegree = residueRatioService.getObservationalRatio(basicHouse.getId());
                    basicHouse.setNewDegree(newDegree);
                    basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                }
            }
        }

        //更新地图标注信息


    }

    /**
     * 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }


    /**
     * 流程发起
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ProcessUserDto processStartSubmit(Integer id) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchDao.getInfoById(id);

        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(String.format("%s%s", "批量申请_", applyBatch.getEstateName()));
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(BasicApply.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setProcessEventExecutor(BasicApplyBatchEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
        processInfo.setProcessEventExecutorName(BasicApplyBatchEvent.class.getSimpleName());
        processInfo.setTableId(id);
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, processControllerComponent.getThisUser(), false);
            applyBatch.setProcessInsId(processUserDto.getProcessInsId());
            applyBatch.setStatus(ProjectStatusEnum.RUNING.getKey());
            applyBatch.setDraftFlag(false);
            basicApplyBatchDao.updateInfo(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw e;
        }
        return processUserDto;
    }

    public BasicApplyBatchVo getBasicApplyBatchVo(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch == null) {
            return null;
        }
        BasicApplyBatchVo vo = new BasicApplyBatchVo();
        BeanUtils.copyProperties(basicApplyBatch, vo);
        if (StringUtils.isNotBlank(basicApplyBatch.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(basicApplyBatch.getProvince()));
        }
        if (StringUtils.isNotBlank(basicApplyBatch.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(basicApplyBatch.getCity()));
        }

        if (basicApplyBatch.getType() != null) {
            for (BasicApplyTypeEnum typeEnum : BasicApplyTypeEnum.values()) {
                if (basicApplyBatch.getType().intValue() == typeEnum.getId().intValue()) {
                    vo.setTypeName(typeEnum.getName());
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        //楼栋
        List<BasicApplyBatchDetail> buildingList = basicApplyBatchDetailService.getBuildingBatchDetailsByBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isNotEmpty(buildingList)) {
            for (BasicApplyBatchDetail build : buildingList) {
                stringBuilder.append(basicApplyBatch.getEstateName()).append(build.getName()).append("栋");
                //获取单元
                List<BasicApplyBatchDetail> unitDetails = basicApplyBatchDetailService.getUnitBatchDetailsByBatchId(basicApplyBatch.getId(), basicBuildingDao.getBasicBuildingById(build.getTableId()));
                if (CollectionUtils.isNotEmpty(unitDetails)) {
                    for (BasicApplyBatchDetail unit : unitDetails) {
                        stringBuilder.append(unit.getName()).append("单元");
                        //获取房屋
                        List<BasicApplyBatchDetail> houseDetails = basicApplyBatchDetailService.getHouseBatchDetailsByBatchId(basicApplyBatch.getId(), basicUnitService.getBasicUnitById(unit.getTableId()));
                        if (CollectionUtils.isNotEmpty(houseDetails)) {
                            StringBuilder houseStr = new StringBuilder();
                            for (BasicApplyBatchDetail house : houseDetails) {
                                houseStr.append(house.getName()).append("、");
                            }
                            houseStr.deleteCharAt(houseStr.length() - 1).append("号");
                            stringBuilder.append(houseStr);
                        }
                        stringBuilder.append("/");
                    }
                } else {
                    stringBuilder.append("/");
                }
            }
        }else {
            vo.setFullName(basicApplyBatch.getEstateName());
        }
        vo.setFullName(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
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
        if (org.apache.commons.lang3.StringUtils.isNotBlank(estateName))
            stringBuilder.append(estateName);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(buildingNumber))
            stringBuilder.append(buildingNumber).append("栋");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(unitNumber))
            stringBuilder.append(unitNumber).append("单元");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(houseNumber))
            stringBuilder.append(houseNumber).append("号");
        return stringBuilder.toString();
    }


    /**
     * 审批
     *
     * @param approvalModelDto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void processApprovalSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 复制到案例库
     *
     * @param id
     * @throws Exception
     */
    public void copy(Integer id) throws Exception {
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getInfoById(id);
        if (basicApplyBatch != null) {
            //取数据
            BasicEstate basicEstateOld = publicBasicService.getBasicEstateById(basicApplyBatch.getEstateId());
            BasicEstateLandState basicEstateLandStateOld = publicBasicService.getEstateLandStateByEstateId(basicApplyBatch.getEstateId());
            List<BasicBuilding> oldBuildings = basicApplyBatchDetailService.getBuildingListByBatchId(basicApplyBatch.getId());

            CaseEstate caseEstate = this.copyBasicEstate(basicEstateOld, basicEstateLandStateOld);//处理楼盘
            this.copyBasicBuilding(oldBuildings, caseEstate.getId(), basicApplyBatch);//处理楼栋

        }

    }

    /**
     * 楼盘拷贝
     *
     * @param basicEstateOld
     * @param basicEstateLandStateOld
     * @return
     * @throws Exception
     */
    private CaseEstate copyBasicEstate(BasicEstate basicEstateOld, BasicEstateLandState basicEstateLandStateOld) throws Exception {
        CaseEstate caseEstate = new CaseEstate();
        if (basicEstateOld != null) {
            BeanUtils.copyProperties(basicEstateOld, caseEstate);
            caseEstate.setId(null);
            caseEstate.setVersion(1);
            caseEstate.setGmtCreated(null);
            caseEstate.setGmtModified(null);
            caseEstateService.saveAndUpdateCaseEstate(caseEstate);

            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableId(basicEstateOld.getId());
            example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));

            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(caseEstate.getId());
            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
            baseAttachmentService.copyFtpAttachments(example, attachmentDto);
            if (basicEstateLandStateOld != null) {
                CaseEstateLandState caseEstateLandState = new CaseEstateLandState();
                BeanUtils.copyProperties(basicEstateLandStateOld, caseEstateLandState);
                caseEstateLandState.setId(null);
                caseEstateLandState.setEstateId(caseEstate.getId());
                caseEstateLandState.setGmtCreated(null);
                caseEstateLandState.setGmtModified(null);
                caseEstateLandStateService.saveAndUpdateCaseEstateLandState(caseEstateLandState);
            }
        }
        BasicEstateParking queryBasicEstateParkingOld = new BasicEstateParking();
        if (basicEstateOld.getId() != null) {
            queryBasicEstateParkingOld.setEstateId(basicEstateOld.getId());
        }

        List<BasicEstateParking> basicEstateParkingList = null;
        basicEstateParkingList = basicEstateParkingService.basicEstateParkingList(queryBasicEstateParkingOld);
        if (caseEstate != null) {
            if (caseEstate.getId() != null) {
                this.copyBasicParking(basicEstateParkingList, caseEstate);

                StringBuilder sqlBuilder = new StringBuilder();
                SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                HashMap<String, String> map = Maps.newHashMap();
                map.put("estate_id", String.valueOf(caseEstate.getId()));
                map.put("creator", commonService.thisUserAccount());
                synchronousDataDto.setFieldDefaultValue(map);
                synchronousDataDto.setSourceDataBase("pmcc_assess");
                synchronousDataDto.setTargeDataBase("pmcc_assess_case");
                synchronousDataDto.setWhereSql("estate_id=" + basicEstateOld.getId());
                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseEstateNetwork.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseEstateSupply.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingTraffic.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMedical.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMaterial.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingLeisurePlace.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingFinance.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEnvironment.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEducation.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

                sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.ESTATE, basicEstateOld.getId(), caseEstate.getId()));
                ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
            }
        }
        return caseEstate;
    }

    private void copyBasicParking(List<BasicEstateParking> basicEstateParkingList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateParkingList)) {
            for (BasicEstateParking oo : basicEstateParkingList) {
                CaseEstateParking estateParking = new CaseEstateParking();
                BeanUtils.copyProperties(oo, estateParking);
                estateParking.setId(null);
                estateParking.setGmtModified(null);
                estateParking.setGmtCreated(null);
                estateParking.setEstateId(caseEstate.getId());
                caseEstateParkingService.addEstateParking(estateParking);

                List<SysAttachmentDto> sysAttachmentDtoList = null;
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(estateParking.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
    }

    /**
     * 楼栋复制
     *
     * @param buildingOlds
     * @param estateId
     * @return
     * @throws Exception
     */
    private void copyBasicBuilding(List<BasicBuilding> buildingOlds, Integer estateId, BasicApplyBatch basicApplyBatch) throws Exception {
        if (CollectionUtils.isNotEmpty(buildingOlds))
            for (BasicBuilding buildingOld : buildingOlds) {
                CaseBuilding caseBuilding = new CaseBuilding();
                if (buildingOld != null) {
                    BeanUtils.copyProperties(buildingOld, caseBuilding);
                    caseBuilding.setEstateId(estateId);
                    caseBuilding.setVersion(1);
                    caseBuilding.setId(null);
                    caseBuilding.setGmtCreated(null);
                    caseBuilding.setGmtModified(null);
                    caseBuildingService.saveAndUpdateCaseBuilding(caseBuilding);

                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(buildingOld.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));

                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(caseBuilding.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                    StringBuilder sqlBuilder = new StringBuilder();
                    SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                    HashMap<String, String> map = Maps.newHashMap();
                    map.put("building_id", String.valueOf(caseBuilding.getId()));
                    map.put("creator", commonService.thisUserAccount());
                    synchronousDataDto.setFieldDefaultValue(map);
                    synchronousDataDto.setSourceDataBase("pmcc_assess");
                    synchronousDataDto.setTargeDataBase("pmcc_assess_case");
                    synchronousDataDto.setWhereSql("building_id=" + buildingOld.getId());
                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingOutfit.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingMaintenance.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingSurface.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingFunction.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

                    sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.BUILDING, buildingOld.getId(), caseBuilding.getId()));
                    ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
                    //复制对应的单元
                    List<BasicUnit> oldUnits = basicApplyBatchDetailService.getBasicUnitListByBatchId(basicApplyBatch.getId(), buildingOld);
                    this.copyBasicUnit(oldUnits, caseBuilding.getId(), basicApplyBatch);
                }

            }

    }


    /**
     * 单元复制
     *
     * @param unitOlds
     * @param caseBuildingId
     * @return
     * @throws Exception
     */
    private void copyBasicUnit(List<BasicUnit> unitOlds, Integer caseBuildingId, BasicApplyBatch basicApplyBatch) throws Exception {
        if (CollectionUtils.isNotEmpty(unitOlds))
            for (BasicUnit unitOld : unitOlds) {
                CaseUnit caseUnit = new CaseUnit();
                if (unitOld != null) {
                    BeanUtils.copyProperties(unitOld, caseUnit);
                    caseUnit.setBuildingId(caseBuildingId);
                    caseUnit.setVersion(1);
                    caseUnit.setId(null);
                    caseUnit.setGmtCreated(null);
                    caseUnit.setGmtModified(null);
                    caseUnitService.saveAndUpdateCaseUnit(caseUnit);

                    //附件拷贝
                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(unitOld.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(caseUnit.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                }
                BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
                if (unitOld != null) {
                    if (unitOld.getId() != null) {
                        queryBasicUnitHuxing.setUnitId(unitOld.getId());
                    }
                }
                List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(queryBasicUnitHuxing);
                if (caseUnit != null && caseUnit.getId() != null) {
                    this.copyBasicHuxing(basicUnitHuxingList, caseUnit);

                    StringBuilder sqlBuilder = new StringBuilder();
                    SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                    HashMap<String, String> map = Maps.newHashMap();
                    map.put("unit_id", String.valueOf(caseUnit.getId()));
                    map.put("creator", commonService.thisUserAccount());
                    synchronousDataDto.setFieldDefaultValue(map);
                    synchronousDataDto.setSourceDataBase("pmcc_assess");
                    synchronousDataDto.setTargeDataBase("pmcc_assess_case");
                    synchronousDataDto.setWhereSql("unit_id=" + unitOld.getId());
                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseUnitDecorate.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseUnitElevator.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

                    sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.UNIT, unitOld.getId(), caseUnit.getId()));
                    ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
                    //复制房屋
                    List<BasicHouse> oldHouses = basicApplyBatchDetailService.getBasicHouseListByBatchId(basicApplyBatch.getId(), unitOld);
                    copyBasicHouse(oldHouses, caseUnit.getId());
                }
            }
    }

    public void copyBasicHuxing(List<BasicUnitHuxing> basicUnitHuxingList, CaseUnit caseUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing unitHuxing : basicUnitHuxingList) {
                CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
                BeanUtils.copyProperties(unitHuxing, caseUnitHuxing);
                caseUnitHuxing.setId(null);
                caseUnitHuxing.setGmtCreated(null);
                caseUnitHuxing.setGmtModified(null);
                caseUnitHuxing.setUnitId(caseUnit.getId());
                caseUnitHuxingService.addCaseUnitHuxing(caseUnitHuxing);

                SysAttachmentDto example = new SysAttachmentDto();
                example.setTableId(unitHuxing.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));

                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(caseUnit.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                baseAttachmentService.copyFtpAttachments(example, attachmentDto);
            }
        }
    }

    /**
     * 房屋 复制
     *
     * @param houseOlds
     * @param newUnitId
     * @throws Exception
     */
    public void copyBasicHouse(List<BasicHouse> houseOlds, Integer newUnitId) throws Exception {
        if (CollectionUtils.isNotEmpty(houseOlds))
            for (BasicHouse houseOld : houseOlds) {
                BasicHouseTrading tradingOld = basicHouseTradingService.getTradingByHouseId(houseOld.getId());
                CaseHouse caseHouse = new CaseHouse();
                CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
                if (houseOld != null) {
                    BeanUtils.copyProperties(houseOld, caseHouse);
                    caseHouse.setUnitId(newUnitId);
                    caseHouse.setVersion(1);
                    caseHouse.setId(null);
                    caseHouse.setGmtCreated(null);
                    caseHouse.setGmtModified(null);
                    caseHouseService.saveAndUpdateCaseHouse(caseHouse);

                    if (tradingOld != null) {
                        BeanUtils.copyProperties(tradingOld, caseHouseTrading);
                        caseHouseTrading.setId(null);
                        caseHouseTrading.setGmtCreated(null);
                        caseHouseTrading.setGmtModified(null);
                        caseHouseTrading.setHouseId(caseHouse.getId());
                        caseHouseTradingService.saveAndUpdateCaseHouseTrading(caseHouseTrading);
                    }
                }
                List<BasicHouseRoom> basicHouseRoomList = null;
                List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = null;

                List<BasicHouseDamagedDegree> basicHouseDamagedDegreeList = null;
                BasicHouseRoom queryRoom = new BasicHouseRoom();
                BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
                BasicHouseDamagedDegree queryHouseDamagedDegree = new BasicHouseDamagedDegree();

                if (houseOld != null && houseOld.getId() != null) {
                    queryRoom.setHouseId(houseOld.getId());
                    queryBasicHouseCorollaryEquipment.setHouseId(houseOld.getId());
                    queryHouseDamagedDegree.setHouseId(houseOld.getId());
                }

                SysAttachmentDto example = new SysAttachmentDto();
                example.setTableId(houseOld.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(caseHouse.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
                baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
                basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
                basicHouseDamagedDegreeList = basicHouseDamagedDegreeDao.getDamagedDegreeList(queryHouseDamagedDegree);

                if (caseHouse != null && caseHouse.getId() != null) {
                    this.copyBasicHouseRoom(basicHouseRoomList, caseHouse);
                    this.copyBasicCorollaryEquipment(basicHouseCorollaryEquipmentList, caseHouse);
                    this.copyBasicDamagedDegree(basicHouseDamagedDegreeList, caseHouse);

                    StringBuilder sqlBuilder = new StringBuilder();
                    SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                    HashMap<String, String> map = Maps.newHashMap();
                    map.put("house_id", String.valueOf(caseHouse.getId()));
                    map.put("creator", commonService.thisUserAccount());
                    synchronousDataDto.setFieldDefaultValue(map);
                    synchronousDataDto.setWhereSql("house_id=" + houseOld.getId());
                    synchronousDataDto.setSourceDataBase("pmcc_assess");
                    synchronousDataDto.setTargeDataBase("pmcc_assess_case");
                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingSell.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseWater.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseWaterDrain.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseIntelligent.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseFaceStreet.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseEquipment.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

                    sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.HOUSE, houseOld.getId(), caseHouse.getId()));
                    ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
                }
            }

    }


    private void copyBasicCorollaryEquipment(List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseCorollaryEquipmentList)) {
            basicHouseCorollaryEquipmentList.forEach(oo -> {
                try {
                    CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, caseHouseCorollaryEquipment);
                    caseHouseCorollaryEquipment.setId(null);
                    caseHouseCorollaryEquipment.setGmtCreated(null);
                    caseHouseCorollaryEquipment.setGmtModified(null);
                    caseHouseCorollaryEquipment.setHouseId(caseHouse.getId());
                    caseHouseCorollaryEquipmentService.addCaseHouseCorollaryEquipment(caseHouseCorollaryEquipment);

                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(oo.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(caseHouseCorollaryEquipment.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouseCorollaryEquipment.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicHouseRoom(List<BasicHouseRoom> basicHouseRoomList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
            for (BasicHouseRoom oo : basicHouseRoomList) {
                CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
                BeanUtils.copyProperties(oo, caseHouseRoom);
                caseHouseRoom.setId(null);
                caseHouseRoom.setGmtCreated(null);
                caseHouseRoom.setGmtModified(null);
                caseHouseRoom.setHouseId(caseHouse.getId());
                caseHouseRoomService.saveCaseHouseRoom(caseHouseRoom);

                BasicHouseRoomDecorate query = new BasicHouseRoomDecorate();
                query.setRoomId(oo.getId());
                List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(query);
                if (!ObjectUtils.isEmpty(basicHouseRoomDecorateList)) {
                    for (BasicHouseRoomDecorate po : basicHouseRoomDecorateList) {
                        CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                        BeanUtils.copyProperties(po, caseHouseRoomDecorate);
                        caseHouseRoomDecorate.setGmtCreated(null);
                        caseHouseRoomDecorate.setGmtModified(null);
                        caseHouseRoomDecorate.setId(null);
                        caseHouseRoomDecorate.setRoomId(caseHouseRoom.getId());
                        caseHouseRoomDecorateService.addCaseHouseRoomDecorate(caseHouseRoomDecorate);
                    }
                }
            }
        }
    }

    private void copyBasicDamagedDegree(List<BasicHouseDamagedDegree> basicHouseDamagedDegrees, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseDamagedDegrees)) {
            for (BasicHouseDamagedDegree old : basicHouseDamagedDegrees) {
                CaseHouseDamagedDegree caseHouseDamagedDegree = new CaseHouseDamagedDegree();
                BeanUtils.copyProperties(old, caseHouseDamagedDegree);
                caseHouseDamagedDegree.setId(null);
                caseHouseDamagedDegree.setGmtCreated(null);
                caseHouseDamagedDegree.setGmtModified(null);
                caseHouseDamagedDegree.setHouseId(caseHouse.getId());
                caseHouseDamagedDegreeService.saveAndUpdateDamagedDegree(caseHouseDamagedDegree);

                List<BasicHouseDamagedDegreeDetail> degreeDetails = basicHouseDamagedDegreeService.getDamagedDegreeDetails(old.getId());
                if (!CollectionUtils.isEmpty(degreeDetails)) {
                    for (BasicHouseDamagedDegreeDetail degreeDetail : degreeDetails) {
                        CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail = new CaseHouseDamagedDegreeDetail();
                        BeanUtils.copyProperties(degreeDetail, caseHouseDamagedDegreeDetail);
                        caseHouseDamagedDegreeDetail.setId(null);
                        caseHouseDamagedDegreeDetail.setGmtCreated(null);
                        caseHouseDamagedDegreeDetail.setGmtModified(null);
                        caseHouseDamagedDegreeDetail.setDamagedDegreeId(caseHouseDamagedDegree.getId());
                        caseHouseDamagedDegreeDetail.setHouseId(caseHouse.getId());
                        caseHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(caseHouseDamagedDegreeDetail);
                    }
                }
            }
        }
    }

    /**
     * 标记复制
     *
     * @param typeEnum
     * @param tableIdSource
     * @param tableIdTarget
     * @throws Exception
     */
    private String copyBasicTagging(EstateTaggingTypeEnum typeEnum, Integer tableIdSource, Integer tableIdTarget) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        synchronousDataDto.setWhereSql(String.format("table_id=%s and type='%s'", tableIdSource, typeEnum.getKey()));
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseEstateTagging.class));
        synchronousDataDto.setSourceDataBase("pmcc_assess");
        synchronousDataDto.setTargeDataBase("pmcc_assess_case");
        HashMap<String, String> map = Maps.newHashMap();
        map.put("table_id", String.valueOf(tableIdTarget));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));
        return sqlBuilder.toString();
    }
}

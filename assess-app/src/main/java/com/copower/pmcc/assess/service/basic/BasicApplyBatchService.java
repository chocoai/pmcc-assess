package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineFileUpLoadFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomBasicAppBatchMapper;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyBatchVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.event.basic.BasicApplyBatchEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyTransferService basicApplyTransferService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Lazy
    @Autowired
    private CustomBasicAppBatchMapper customBasicAppBatchMapper;


    private final Logger logger = LoggerFactory.getLogger(getClass());


    public List<ZtreeDto> getZtreeDto(Integer estateId) throws Exception {
        List<ZtreeDto> treeDtos = new ArrayList<>();
        if (estateId == null) return treeDtos;
        BasicApplyBatch basicApplyBatch = getBasicApplyBatchByEstateId(estateId);
        if (basicApplyBatch == null) return treeDtos;
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isEmpty(basicApplyBatchDetails)) return treeDtos;
        for (BasicApplyBatchDetail item : basicApplyBatchDetails) {
            ZtreeDto ztreeDto = new ZtreeDto();
            ztreeDto.setId(item.getId());
            ztreeDto.setName(item.getName());
            if(basicApplyBatch.getPlanDetailsId()==null){
                ztreeDto.setDisplayName(item.getDisplayName());
            }else {
                ztreeDto.setDisplayName(String.format("%s(%s)",item.getDisplayName(),publicService.getUserNameByAccount(item.getExecutor())));
            }
            ztreeDto.setPid(item.getPid());
            ztreeDto.setTableName(item.getTableName());
            ztreeDto.setTableId(item.getTableId());
            ztreeDto.setType(getZtreeDtoType(item.getTableName()));
            ztreeDto.setCreator(item.getCreator());
            ztreeDto.setExecutor(item.getExecutor());
            ztreeDto.setCreatorName(publicService.getUserNameByAccount(item.getCreator()));
            ztreeDto.setBisStructure(item.getBisStructure());
            treeDtos.add(ztreeDto);
        }
        return treeDtos;
    }

    public String getZtreeDtoType(String tableName) {
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
            return EstateTaggingTypeEnum.ESTATE.getKey();
        }
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
            return EstateTaggingTypeEnum.HOUSE.getKey();
        }
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
            return EstateTaggingTypeEnum.BUILDING.getKey();
        }
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
            return EstateTaggingTypeEnum.UNIT.getKey();
        }
        return null;
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

    //申请时案例数据生成树
    public List<ZtreeDto> getCaseApplyZtreeDto(Integer caseEstateId, Integer applyBatchId) throws Exception {
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseEstateId);
        List<ZtreeDto> treeDtos = new ArrayList<>();
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(caseEstate.getId());
        ztreeDto.setDisplayName(caseEstate.getName());
        ztreeDto.setPid(0);
        ztreeDto.setType("estate");
        ztreeDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        ztreeDto.setBisModify(false);
        //楼盘是否升级过
        BasicApplyBatchDetail upgradeEstate = getUpgradeApplyBatchDetail(applyBatchId, caseEstate.getId(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        if (upgradeEstate != null) {
            ztreeDto.setTableId(upgradeEstate.getTableId());
            ztreeDto.setDisplayName(String.format("%s(升级)", upgradeEstate.getDisplayName()));
        }
        treeDtos.add(ztreeDto);
        //新增楼栋及下级节点
        treeDtos.addAll(getNewZtreeDto(caseEstateId, FormatUtils.entityNameConvertToTableName(BasicBuilding.class), applyBatchId));
        //案例库楼栋
        CaseBuilding caseBuilding = new CaseBuilding();
        caseBuilding.setEstateId(caseEstate.getId());
        caseBuilding.setNewVersions(true);
        List<CaseBuilding> caseBuildingList = caseBuildingService.getCaseBuildingList(caseBuilding);
        if (CollectionUtils.isNotEmpty(caseBuildingList)) {
            for (CaseBuilding building : caseBuildingList) {
                ZtreeDto ztreeDto1 = new ZtreeDto();
                ztreeDto1.setId(building.getId());
                ztreeDto1.setDisplayName(String.format("%s%s", building.getBuildingNumber(), "栋"));
                ztreeDto1.setPid(caseEstate.getId());
                ztreeDto1.setType("building");
                ztreeDto1.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                ztreeDto1.setBisModify(false);
                //楼栋是否升级过
                BasicApplyBatchDetail upgrade = getUpgradeApplyBatchDetail(applyBatchId, building.getId(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                if (upgrade != null) {
                    ztreeDto1.setTableId(upgrade.getTableId());
                    ztreeDto1.setDisplayName(String.format("%s栋(升级)", building.getBuildingNumber()));
                }
                treeDtos.add(ztreeDto1);
                //新增单元及下级节点
                treeDtos.addAll(getNewZtreeDto(building.getId(), FormatUtils.entityNameConvertToTableName(BasicUnit.class), applyBatchId));
                //案例库单元
                CaseUnit caseUnit = new CaseUnit();
                caseUnit.setBuildingId(building.getId());
                caseUnit.setNewVersions(true);
                List<CaseUnit> caseUnitList = caseUnitService.getCaseUnitList(caseUnit);
                if (CollectionUtils.isNotEmpty(caseUnitList)) {
                    for (CaseUnit unit : caseUnitList) {
                        ZtreeDto ztreeDto2 = new ZtreeDto();
                        ztreeDto2.setId(unit.getId());
                        ztreeDto2.setDisplayName(String.format("%s%s", unit.getUnitNumber(), "单元"));
                        ztreeDto2.setPid(building.getId());
                        ztreeDto2.setType("unit");
                        ztreeDto2.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                        ztreeDto2.setBisModify(false);
                        //单元是否升级过
                        BasicApplyBatchDetail upgradeUnit = getUpgradeApplyBatchDetail(applyBatchId, unit.getId(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                        if (upgradeUnit != null) {
                            ztreeDto2.setTableId(upgradeUnit.getTableId());
                            ztreeDto2.setDisplayName(String.format("%s(升级)", upgradeUnit.getDisplayName()));
                        }
                        treeDtos.add(ztreeDto2);
                        //新增房间
                        treeDtos.addAll(getNewZtreeDto(unit.getId(), FormatUtils.entityNameConvertToTableName(BasicHouse.class), applyBatchId));
                        //案例库房间
                        CaseHouse caseHouse = new CaseHouse();
                        caseHouse.setUnitId(unit.getId());
                        caseHouse.setNewVersions(true);
                        List<CaseHouse> caseHouseList = caseHouseService.getCaseHouseList(caseHouse);
                        if (CollectionUtils.isNotEmpty(caseHouseList)) {
                            for (CaseHouse house : caseHouseList) {
                                ZtreeDto ztreeDto3 = new ZtreeDto();
                                ztreeDto3.setId(house.getId());
                                ztreeDto3.setDisplayName(house.getHouseNumber());
                                ztreeDto3.setPid(unit.getId());
                                ztreeDto3.setType("house");
                                ztreeDto3.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                                ztreeDto3.setBisModify(false);
                                //房屋是否升级过
                                BasicApplyBatchDetail upgradeHouse = getUpgradeApplyBatchDetail(applyBatchId, house.getId(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                                if (upgradeHouse != null) {
                                    ztreeDto2.setTableId(upgradeUnit.getTableId());
                                    ztreeDto2.setDisplayName(String.format("%s(升级)", upgradeHouse.getDisplayName()));
                                }
                                treeDtos.add(ztreeDto3);
                            }
                        }
                    }
                }
            }
        }
        return treeDtos;
    }

    //案例库新增节点
    public List<ZtreeDto> getNewZtreeDto(Integer caseTablePid, String tableName, Integer applyBatchId) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setCaseTablePid(caseTablePid);
        basicApplyBatchDetail.setTableName(tableName);
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);

        List<ZtreeDto> treeDtos = new ArrayList<>();
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailList(basicApplyBatchDetail);
        if (CollectionUtils.isEmpty(basicApplyBatchDetails)) return treeDtos;
        for (BasicApplyBatchDetail item : basicApplyBatchDetails) {
            ZtreeDto ztreeDto = new ZtreeDto();
            ztreeDto.setId(item.getId());
            ztreeDto.setName(item.getName());
            ztreeDto.setDisplayName(String.format("%s(新增)", item.getDisplayName()));
            ztreeDto.setPid(item.getCaseTablePid());
            ztreeDto.setTableName(item.getTableName());
            ztreeDto.setType(getZtreeDtoType(item.getTableName()));
            ztreeDto.setTableId(item.getTableId());
            treeDtos.add(ztreeDto);
            //所有下级节点
            List<ZtreeDto> ztreeDtos = getZtreeDtoByBasicApplyBatchDetail(item);
            treeDtos.addAll(ztreeDtos);
        }
        return treeDtos;
    }

    //所有下级节点
    public List<ZtreeDto> getZtreeDtoByBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail) throws Exception {
        List<ZtreeDto> treeDtos = new ArrayList<>();
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(basicApplyBatchDetail.getId(), null);
        if (CollectionUtils.isNotEmpty(basicApplyBatchDetails)) {
            for (BasicApplyBatchDetail item : basicApplyBatchDetails) {
                ZtreeDto ztreeDto = new ZtreeDto();
                ztreeDto.setId(item.getId());
                ztreeDto.setName(item.getName());
                ztreeDto.setDisplayName(item.getDisplayName());
                ztreeDto.setPid(item.getPid());
                ztreeDto.setTableName(item.getTableName());
                ztreeDto.setTableId(item.getTableId());
                if (Objects.equal(item.getTableName(), FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
                    ztreeDto.setType(EstateTaggingTypeEnum.ESTATE.getKey());
                }
                if (Objects.equal(item.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                    ztreeDto.setType(EstateTaggingTypeEnum.HOUSE.getKey());
                }
                if (Objects.equal(item.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                    ztreeDto.setType(EstateTaggingTypeEnum.BUILDING.getKey());
                }
                if (Objects.equal(item.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                    ztreeDto.setType(EstateTaggingTypeEnum.UNIT.getKey());
                }
                treeDtos.add(ztreeDto);
                treeDtos.addAll(getZtreeDtoByBasicApplyBatchDetail(item));
            }
        }
        return treeDtos;
    }

    //审批时数据生成树
    public List<ZtreeDto> getCaseApprovalZtreeDto(Integer applyBatchId) throws Exception {
        List<ZtreeDto> treeDtos = new ArrayList<>();
        BasicApplyBatch basicApplyBatch = this.getBasicApplyBatchById(applyBatchId);
        //获取新增主节点
        List<BasicApplyBatchDetail> detailByApplyList = basicApplyBatchDetailDao.getCaseAddNodeDetail(basicApplyBatch.getId());
        //案例楼盘
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(basicApplyBatch.getCaseEstateId());
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(caseEstate.getId());
        ztreeDto.setDisplayName(caseEstate.getName());
        ztreeDto.setPid(0);
        ztreeDto.setType("estate");
        ztreeDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        //楼盘是否升级过
        BasicApplyBatchDetail upgradeEstate = getUpgradeApplyBatchDetail(applyBatchId, caseEstate.getId(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        if (upgradeEstate != null) {
            ztreeDto.setTableId(upgradeEstate.getTableId());
            ztreeDto.setDisplayName(String.format("%s(升级)", upgradeEstate.getDisplayName()));
        } else {
            ztreeDto.setBisModify(false);
        }
        treeDtos.add(ztreeDto);
        if (CollectionUtils.isNotEmpty(detailByApplyList)) {
            for (BasicApplyBatchDetail detail : detailByApplyList) {
                if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                    //新增楼栋及下级节点
                    treeDtos.addAll(getNewZtreeDto(caseEstate.getId(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class), applyBatchId));
                }
                if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                    //案例楼栋
                    CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(detail.getCaseTablePid());

                    ZtreeDto ztreeBuid = new ZtreeDto();
                    ztreeBuid.setId(caseBuilding.getId());
                    ztreeBuid.setDisplayName(String.format("%s栋(新增)", caseBuilding.getBuildingNumber()));
                    ztreeBuid.setPid(caseEstate.getId());
                    ztreeBuid.setType("building");
                    ztreeBuid.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    //楼栋是否升级过
                    BasicApplyBatchDetail upgrade = getUpgradeApplyBatchDetail(applyBatchId, caseBuilding.getId(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    if (upgrade != null) {
                        ztreeBuid.setTableId(upgrade.getTableId());
                        ztreeBuid.setDisplayName(String.format("%s栋(升级)", caseBuilding.getBuildingNumber()));
                    } else {
                        ztreeBuid.setBisModify(false);
                    }
                    treeDtos.add(ztreeBuid);
                    //新增单元及下级节点
                    treeDtos.addAll(getNewZtreeDto(caseBuilding.getId(), FormatUtils.entityNameConvertToTableName(BasicUnit.class), applyBatchId));
                }
                if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                    //案例单元
                    CaseUnit caseUnit = caseUnitService.getCaseUnitById(detail.getCaseTablePid());
                    //案例楼栋
                    CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());

                    //楼栋ztreeDto
                    ZtreeDto ztreeBuid = new ZtreeDto();
                    ztreeBuid.setId(caseBuilding.getId());
                    ztreeBuid.setDisplayName(String.format("%s栋(新增)", caseBuilding.getBuildingNumber()));
                    ztreeBuid.setPid(caseEstate.getId());
                    ztreeBuid.setType("building");
                    ztreeBuid.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    //楼栋是否升级过
                    BasicApplyBatchDetail upgrade = getUpgradeApplyBatchDetail(applyBatchId, caseBuilding.getId(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    if (upgrade != null) {
                        ztreeBuid.setTableId(upgrade.getTableId());
                        ztreeBuid.setDisplayName(String.format("%s栋(升级)", caseBuilding.getBuildingNumber()));
                    } else {
                        ztreeBuid.setBisModify(false);
                    }
                    treeDtos.add(ztreeBuid);
                    //单元ztreeDto
                    ZtreeDto ztreeUnit = new ZtreeDto();
                    ztreeUnit.setId(caseUnit.getId());
                    ztreeUnit.setDisplayName(String.format("%s号(新增)", caseUnit.getUnitNumber()));
                    ztreeUnit.setPid(caseBuilding.getId());
                    ztreeUnit.setType("unit");
                    ztreeUnit.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    //单元是否升级过
                    BasicApplyBatchDetail upgradeUnit = getUpgradeApplyBatchDetail(applyBatchId, caseUnit.getId(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    if (upgradeUnit != null) {
                        ztreeUnit.setTableId(upgradeUnit.getTableId());
                        ztreeUnit.setDisplayName(String.format("%s(升级)", upgradeUnit.getDisplayName()));
                    } else {
                        ztreeUnit.setBisModify(false);
                    }
                    treeDtos.add(ztreeUnit);
                    //房屋ztreeDto
                    ZtreeDto ztreeHouse = new ZtreeDto();
                    ztreeHouse.setId(detail.getId());
                    ztreeHouse.setDisplayName(detail.getDisplayName());
                    ztreeHouse.setPid(detail.getCaseTablePid());
                    ztreeHouse.setType("house");
                    ztreeHouse.setTableId(detail.getTableId());
                    ztreeHouse.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    treeDtos.add(ztreeHouse);
                }
            }
        }
        //升级节点处理
        List<BasicApplyBatchDetail> upgradeAddDetail = basicApplyBatchDetailDao.getUpgradeAddDetail(basicApplyBatch.getId());
        if (CollectionUtils.isNotEmpty(upgradeAddDetail)) {
            for (BasicApplyBatchDetail detail : upgradeAddDetail) {
                if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                    //升级的楼栋
                    ZtreeDto ztreeBuilding = new ZtreeDto();
                    ztreeBuilding.setId(detail.getUpgradeTableId());
                    ztreeBuilding.setDisplayName(String.format("%s(升级)", detail.getDisplayName()));
                    ztreeBuilding.setPid(detail.getPid());
                    ztreeBuilding.setType("building");
                    ztreeBuilding.setTableId(detail.getTableId());
                    ztreeBuilding.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    treeDtos.add(ztreeBuilding);
                }
                if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                    //上级楼栋
                    CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(detail.getPid());
                    ZtreeDto ztreeBuid = new ZtreeDto();
                    ztreeBuid.setId(caseBuilding.getId());
                    ztreeBuid.setDisplayName(String.format("%s栋", caseBuilding.getBuildingNumber()));
                    ztreeBuid.setPid(caseBuilding.getEstateId());
                    ztreeBuid.setType("building");
                    ztreeBuid.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    //上级楼栋是否升级过
                    BasicApplyBatchDetail upgrade = getUpgradeApplyBatchDetail(applyBatchId, caseBuilding.getId(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    if (upgrade != null) {
                        ztreeBuid.setTableId(upgrade.getTableId());
                        ztreeBuid.setDisplayName(String.format("%s栋(升级)", caseBuilding.getBuildingNumber()));
                    } else {
                        ztreeBuid.setBisModify(false);
                    }
                    treeDtos.add(ztreeBuid);
                    //升级的单元
                    ZtreeDto ztreeUnit = new ZtreeDto();
                    ztreeUnit.setId(detail.getUpgradeTableId());
                    ztreeUnit.setDisplayName(String.format("%s(升级)", detail.getDisplayName()));
                    ztreeUnit.setPid(detail.getPid());
                    ztreeUnit.setType("unit");
                    ztreeUnit.setTableId(detail.getTableId());
                    ztreeUnit.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    treeDtos.add(ztreeUnit);
                }
                if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                    //上级案例单元
                    CaseUnit caseUnit = caseUnitService.getCaseUnitById(detail.getPid());
                    //上级案例楼栋
                    CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());

                    //上级楼栋ztreeDto
                    ZtreeDto ztreeBuid = new ZtreeDto();
                    ztreeBuid.setId(caseBuilding.getId());
                    ztreeBuid.setDisplayName(String.format("%s栋", caseBuilding.getBuildingNumber()));
                    ztreeBuid.setPid(caseBuilding.getEstateId());
                    ztreeBuid.setType("building");
                    ztreeBuid.setBisModify(false);
                    ztreeBuid.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    //上级楼栋是否升级过
                    BasicApplyBatchDetail upgrade = getUpgradeApplyBatchDetail(applyBatchId, caseBuilding.getId(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    if (upgrade != null) {
                        ztreeBuid.setTableId(upgrade.getTableId());
                        ztreeBuid.setDisplayName(String.format("%s(升级)", caseBuilding.getBuildingNumber()));
                    } else {
                        ztreeBuid.setBisModify(false);
                    }
                    treeDtos.add(ztreeBuid);
                    //单元ztreeDto
                    ZtreeDto ztreeUnit = new ZtreeDto();
                    ztreeUnit.setId(caseUnit.getId());
                    ztreeUnit.setDisplayName(String.format("%s号", caseUnit.getUnitNumber()));
                    ztreeUnit.setPid(caseUnit.getBuildingId());
                    ztreeUnit.setType("unit");
                    ztreeUnit.setBisModify(false);
                    ztreeUnit.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    //上级单元是否升级过
                    BasicApplyBatchDetail upgradeUnit = getUpgradeApplyBatchDetail(applyBatchId, caseUnit.getId(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    if (upgradeUnit != null) {
                        ztreeUnit.setTableId(upgradeUnit.getTableId());
                        ztreeUnit.setDisplayName(String.format("%s(升级)", upgradeUnit.getDisplayName()));
                    } else {
                        ztreeUnit.setBisModify(false);
                    }
                    treeDtos.add(ztreeUnit);
                    //房屋ztreeDto
                    ZtreeDto ztreeHouse = new ZtreeDto();
                    ztreeHouse.setId(detail.getUpgradeTableId());
                    ztreeHouse.setDisplayName(String.format("%s(升级)", detail.getDisplayName()));
                    ztreeHouse.setPid(detail.getPid());
                    ztreeHouse.setType("house");
                    ztreeHouse.setTableId(detail.getTableId());
                    ztreeHouse.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    treeDtos.add(ztreeHouse);
                }
            }
        }

        //节点可能会重复，去重
        List<ZtreeDto> list = treeDtos.stream().filter(distinctByKey(ZtreeDto::getId))
                .collect(Collectors.toList());
        return list;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>(16);
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public BasicApplyBatchDetail getUpgradeApplyBatchDetail(Integer applyBatchId, Integer upgradeTableId, String tableName) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);
        basicApplyBatchDetail.setUpgradeTableId(upgradeTableId);
        basicApplyBatchDetail.setTableName(tableName);
        List<BasicApplyBatchDetail> details = basicApplyBatchDetailService.getBasicApplyBatchDetailList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(details)) {
            return details.get(0);
        }
        return null;
    }


    //验证楼盘是否已在案列库
    public Integer verification(BasicApplyBatch basicApplyBatch) throws Exception {
        CaseEstate caseEstate = new CaseEstate();
        caseEstate.setProvince(basicApplyBatch.getProvince());
        caseEstate.setCity(basicApplyBatch.getCity());
        caseEstate.setName(basicApplyBatch.getEstateName());
        caseEstate.setNewVersions(true);
        List<CaseEstate> caseEstateList = caseEstateService.getCaseEstateList(caseEstate);
        if (CollectionUtils.isNotEmpty(caseEstateList)) {
            return caseEstateList.get(0).getId();

        }
        return null;
    }


    public List<BaseDataDic> getFormClassifyList() {
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList
                (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY);
        List<BaseDataDic> resultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dataDicList)) return resultList;
        resultList = LangUtils.filter(dataDicList, o -> {
            return AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE.equals(o.getFieldName())
                    || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(o.getFieldName())
                    || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(o.getFieldName());
        });

        return resultList;
    }


    public BasicApplyBatch getSingleData(BasicApplyBatch basicApplyBatch) {
        List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(infoList)) return infoList.get(0);
        return null;
    }


    public BasicApplyBatch getBasicApplyBatchById(Integer id) {
        return basicApplyBatchDao.getBasicApplyBatchById(id);
    }

    //删除
    public void deleteBasicBatchApply(Integer id) {
        basicApplyBatchDao.deleteInfo(id);
    }

    //获取草稿数据
    public BootstrapTableVo getBootstrapTableVo(String estateName) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String creator = commonService.thisUserAccount();
        List<BasicApplyBatch> basicAppBatchDraftList = customBasicAppBatchMapper.getCustomDraftList(estateName, creator);
        List<BasicApplyBatchVo> voList = LangUtils.transform(basicAppBatchDraftList, o -> getBasicApplyBatchVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<BasicApplyBatchVo>() : voList);
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

    public BasicApplyBatch getBasicApplyBatchByPlanDetailsId(Integer planDetailsId) {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setPlanDetailsId(planDetailsId);
        List<BasicApplyBatch> basicApplyBatches = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(basicApplyBatches)) {
            return basicApplyBatches.get(0);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchByPlanDetailsId(Integer planDetailsId) throws Exception {
        if (planDetailsId == null) return;
        BasicApplyBatch applyBatch = getBasicApplyBatchByPlanDetailsId(planDetailsId);
        List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByPlanDetailsId(planDetailsId);
        if (CollectionUtils.isNotEmpty(basicApplyList)) {
            basicApplyList.forEach(o -> {
                try {
                    basicApplyService.deleteBasicApply(o.getId());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }
        if (applyBatch != null) {
            List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
            if (CollectionUtils.isNotEmpty(batchDetailList)) {
                for (BasicApplyBatchDetail basicApplyBatchDetail : batchDetailList) {
                    if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equals(basicApplyBatchDetail.getTableName())) {
                        basicBuildingService.clearInvalidData(basicApplyBatchDetail.getTableId());
                    }
                    if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equals(basicApplyBatchDetail.getTableName())) {
                        basicUnitService.clearInvalidData(basicApplyBatchDetail.getTableId());
                    }
                    if (FormatUtils.entityNameConvertToTableName(BasicHouse.class).equals(basicApplyBatchDetail.getTableName())) {
                        basicHouseService.clearInvalidData(basicApplyBatchDetail.getTableId());
                    }
                    basicApplyBatchDetailDao.deleteInfo(basicApplyBatchDetail.getId());
                }
            }
            basicEstateService.clearInvalidData(applyBatch.getEstateId());
            basicApplyBatchDao.deleteInfo(applyBatch.getId());
        }
    }

    public void addBasicApplyBatch(BasicApplyBatch applyBatch) {
        basicApplyBatchDao.addInfo(applyBatch);
    }

    /**
     * 初始化批量申请信息
     *
     * @param basicApplyBatch
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicApplyBatch initBasicApplyBatchInfo(BasicApplyBatch basicApplyBatch) throws Exception {
        //1.根据不同情况初始化不同的信息结构 2.初始化之前需先将原初始化信息删除
        deleteBatchByPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        if (basicApplyBatch.getClassify() == null) return basicApplyBatch;
        BaseDataDic classifyDataDic = baseDataDicService.getCacheDataDicById(basicApplyBatch.getClassify());
        //楼盘
        BasicEstate basicEstate = new BasicEstate();
        //申报表代入的信息
        DeclareRecord declareRecord = new DeclareRecord();
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatch.getPlanDetailsId());
        if (projectPlanDetails != null && projectPlanDetails.getDeclareRecordId() != null) {
            declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        }
        if (declareRecord != null) {
            basicEstate.setProvince(declareRecord.getProvince());
            basicEstate.setCity(declareRecord.getCity());
            basicEstate.setDistrict(declareRecord.getDistrict());
            basicEstate.setStreetNumber(declareRecord.getStreetNumber());
            basicEstate.setAttachNumber(declareRecord.getAttachedNumber());
        }
        basicEstateService.saveAndUpdateBasicEstate(basicEstate, false);
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        if (declareRecord != null) {
            basicEstateLandState.setLandUseType(declareRecord.getLandCertUse());
            basicEstateLandState.setLandUseCategory(declareRecord.getLandCertUseCategory());
        }
        basicEstateLandState.setEstateId(basicEstate.getId());
        basicEstateLandState.setCreator(commonService.thisUserAccount());
        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, false);
        String estateName = "楼盘信息";
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(classifyDataDic.getFieldName())
                || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(classifyDataDic.getFieldName())) {
            estateName = "地块信息";
        }
        basicApplyBatch.setEstateId(basicEstate.getId());
        basicApplyBatch.setEstateName(estateName);
        basicApplyBatch.setCreator(commonService.thisUserAccount());
        saveApplyInfo(basicApplyBatch);

        BasicApplyBatchDetail estateApplyBatchDetail = new BasicApplyBatchDetail();
        estateApplyBatchDetail.setPid(0);
        estateApplyBatchDetail.setBisStandard(false);
        estateApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        estateApplyBatchDetail.setTableId(basicEstate.getId());
        estateApplyBatchDetail.setName(estateName);
        estateApplyBatchDetail.setDisplayName(estateName);
        estateApplyBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateApplyBatchDetail);

        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_SINGEL.equals(classifyDataDic.getFieldName())) {
            //1.添加applyBatch 2.添加applyBatchDetail 3.添加楼盘、楼栋、单元、房屋主表 4.添加basicApply表
            BasicBuilding basicBuilding = new BasicBuilding();
            basicBuilding.setCreator(commonService.thisUserAccount());
            basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, false);
            BasicApplyBatchDetail buildingApplyBatchDetail = new BasicApplyBatchDetail();
            buildingApplyBatchDetail.setPid(estateApplyBatchDetail.getId());
            buildingApplyBatchDetail.setBisStandard(false);
            buildingApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
            buildingApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            buildingApplyBatchDetail.setTableId(basicBuilding.getId());
            buildingApplyBatchDetail.setName("楼栋信息");
            buildingApplyBatchDetail.setDisplayName("楼栋信息");
            buildingApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(buildingApplyBatchDetail);
            //单元
            BasicUnit basicUnit = new BasicUnit();
            basicUnit.setCreator(commonService.thisUserAccount());
            basicUnitService.saveAndUpdateBasicUnit(basicUnit, false);
            BasicApplyBatchDetail unitApplyBatchDetail = new BasicApplyBatchDetail();
            unitApplyBatchDetail.setBisStandard(false);
            unitApplyBatchDetail.setPid(buildingApplyBatchDetail.getId());
            unitApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
            unitApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
            unitApplyBatchDetail.setTableId(basicUnit.getId());
            unitApplyBatchDetail.setName("单元信息");
            unitApplyBatchDetail.setDisplayName("单元信息");
            unitApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(unitApplyBatchDetail);
            //房屋
            BasicHouse basicHouse = new BasicHouse();
            basicHouse.setCreator(commonService.thisUserAccount());
            basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            basicHouseTrading.setHouseId(basicHouse.getId());
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
            BasicApplyBatchDetail houseApplyBatchDetail = new BasicApplyBatchDetail();
            houseApplyBatchDetail.setBisStandard(true);
            houseApplyBatchDetail.setPid(unitApplyBatchDetail.getId());
            houseApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
            houseApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            houseApplyBatchDetail.setTableId(basicHouse.getId());
            houseApplyBatchDetail.setName("房屋信息");
            houseApplyBatchDetail.setDisplayName("房屋信息");
            houseApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(houseApplyBatchDetail);

            BasicApply basicApply = new BasicApply();
            basicApply.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
            basicApply.setBasicEstateId(basicEstate.getId());
            basicApply.setBasicBuildingId(basicBuilding.getId());
            basicApply.setBasicUnitId(basicUnit.getId());
            basicApply.setBasicHouseId(basicHouse.getId());
            basicApply.setCreator(commonService.thisUserAccount());
            basicApplyService.saveBasicApply(basicApply);
        } else if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(classifyDataDic.getFieldName())) {
            //纯土地中地块包一部分房屋相关信息
            BasicHouse basicHouse = new BasicHouse();
            basicHouse.setHouseNumber("0");
            basicHouse.setCreator(commonService.thisUserAccount());
            basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            basicHouseTrading.setHouseId(basicHouse.getId());
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);

            BasicApply basicApply = new BasicApply();
            basicApply.setBasicEstateId(basicEstate.getId());
            basicApply.setBasicHouseId(basicHouse.getId());
            basicApply.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
            basicApplyService.saveBasicApply(basicApply);
        }
        return basicApplyBatch;
    }

    //保存
    public void saveApplyInfo(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch.getId() != null && basicApplyBatch.getId() != 0) {
            basicApplyBatchDao.updateInfo(basicApplyBatch);
        } else {
            basicApplyBatch.setCreator(commonService.thisUserAccount());
            basicApplyBatchDao.addInfo(basicApplyBatch);
        }
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
        if (basicEstate != null) {
            basicEstate.setClassify(basicApplyBatch.getClassify());
            basicEstate.setType(basicApplyBatch.getType());
            basicEstateService.saveAndUpdateBasicEstate(basicEstate, false);
        }
    }

    /**
     * 保存数据
     *
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDraft(String formData, Integer applyBatchId,Integer planDetailsId) throws Exception {
        String jsonContent = null;
        JSONObject jsonObject = JSON.parseObject(formData);

        //楼盘过程数据
        BasicEstate basicEstate = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            //原来数据做记录,将老数据复制一条
            BasicEstate oldBasicEstate = basicEstateService.getBasicEstateById(basicEstate.getId());
            BasicEstateLandState oldLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
            BasicEstate version = basicApplyTransferService.copyBasicEstate(null, oldBasicEstate, oldLandState, false);
            version.setRelevanceId(oldBasicEstate.getId());
            basicEstateService.saveAndUpdateBasicEstate(version, true);

            if (basicEstate != null) {
                basicEstate.setClassify(oldBasicEstate.getClassify());
                basicEstate.setType(oldBasicEstate.getType());
                basicEstateService.saveAndUpdateBasicEstate(basicEstate, true);
                BasicApplyBatch basicApplyBatch = getBasicApplyBatchByEstateId(basicEstate.getId());
                if (basicApplyBatch != null) {
                    basicApplyBatch.setEstateName(basicEstate.getName());
                    basicApplyBatchDao.updateInfo(basicApplyBatch);
                }
                if (basicEstate.getId() != null) {
                    BasicEstateLandState basicEstateLandState = null;
                    String string = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar());
                    basicEstateLandState = JSONObject.parseObject(string, BasicEstateLandState.class);
                    if (basicEstateLandState != null) {
                        basicEstateLandState.setLandLevelContent(StringUtils.isNotEmpty(basicEstateLandState.getLandLevelContent()) ? basicEstateLandState.getLandLevelContent() : null);
                        basicEstateLandState.setEstateId(basicEstate.getId());
                        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, true);
                    }
                }
                BasicApplyBatchDetail estateDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicEstate.class), basicEstate.getId());
                if (estateDetail != null) {
                    estateDetail.setName(basicEstate.getName());
                    estateDetail.setDisplayName(basicEstate.getName());
                    basicApplyBatchDetailDao.updateInfo(estateDetail);
                }
                BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
                if (applyBatch != null) {
                    applyBatch.setEstateId(basicEstate.getId());
                    applyBatch.setEstateName(basicEstate.getName());
                    basicApplyBatchService.saveApplyInfo(applyBatch);
                }
            }
        }

        //楼栋主数据
        BasicBuilding basicBuilding = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
            //原来数据做记录,将老数据复制一条
            BasicBuilding oldBasicBuilding = basicBuildingService.getBasicBuildingById(basicBuilding.getId());
            BasicBuilding version = basicApplyTransferService.copyBasicBuilding(null, oldBasicBuilding, null, false);
            version.setRelevanceId(oldBasicBuilding.getId());
            basicBuildingService.saveAndUpdateBasicBuilding(version, true);

            if (basicBuilding != null) {
                basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, true);
                BasicApplyBatchDetail buildingDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), basicBuilding.getId());
                if (buildingDetail != null) {
                    buildingDetail.setName(basicBuilding.getBuildingNumber());
                    buildingDetail.setDisplayName(basicBuilding.getBuildingName());
                    basicApplyBatchDetailDao.updateInfo(buildingDetail);
                }
            }
        }

        //单元过程数据
        BasicUnit basicUnit = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
            //原来数据做记录,将老数据复制一条
            BasicUnit oldBasicUnit = basicUnitService.getBasicUnitById(basicUnit.getId());
            BasicUnit version = basicApplyTransferService.copyBasicUnit(null, oldBasicUnit, null, false);
            version.setRelevanceId(oldBasicUnit.getId());
            basicUnitService.saveAndUpdateBasicUnit(version, true);
            if (basicUnit != null) {
                basicUnitService.saveAndUpdateBasicUnit(basicUnit, true);
                BasicApplyBatchDetail unitDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicUnit.class), basicUnit.getId());
                if (unitDetail != null) {
                    unitDetail.setName(basicUnit.getUnitNumber());
                    unitDetail.setDisplayName(String.format("%s%s", basicUnit.getUnitNumber(), "单元"));
                    basicApplyBatchDetailDao.updateInfo(unitDetail);
                }
            }
        }

        //处理房屋数据
        BasicHouse basicHouse = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            //原来数据做记录,将老数据复制一条
            BasicHouse oldBasicHouse = basicHouseDao.getBasicHouseById(basicHouse.getId());
            BasicHouseTrading oldTradingByHouseId = basicHouseTradingService.getTradingByHouseId(oldBasicHouse.getId());
            BasicHouse version = basicApplyTransferService.copyBasicHouse(null, oldBasicHouse, oldTradingByHouseId, null, false);
            version.setRelevanceId(oldBasicHouse.getId());
            basicHouseService.saveAndUpdateBasicHouse(version, true);
            if (basicHouse != null) {
                Integer houseId = basicHouseService.saveAndUpdateBasicHouse(basicHouse, true);
                BasicApplyBatchDetail houseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicHouse.class), basicHouse.getId());
                if (houseDetail != null) {
                    houseDetail.setName(basicHouse.getHouseNumber());
                    houseDetail.setDisplayName(basicHouse.getHouseNumber());
                    basicApplyBatchDetailDao.updateInfo(houseDetail);

                    BasicApply where = new BasicApply();
                    where.setPlanDetailsId(planDetailsId);
                    where.setBasicHouseId(houseDetail.getTableId());
                    BasicApply basicApply = basicApplyService.getBasicApply(where);
                    Map<EstateTaggingTypeEnum, BasicApplyBatchDetail> map = basicApplyBatchDetailService.getApplyBatchDetailMap(houseDetail);
                    basicApply.setArea(basicHouse.getArea());
                    if(houseDetail.getCaseTablePid()==null){
                        //单元
                        BasicApplyBatchDetail unitBatchDetail = map.get(EstateTaggingTypeEnum.UNIT);
                        //楼栋
                        BasicApplyBatchDetail buildBatchDetail = map.get(EstateTaggingTypeEnum.BUILDING);
                        //楼盘
                        BasicApplyBatchDetail estateBatchDetail = map.get(EstateTaggingTypeEnum.ESTATE);
                        basicApply.setName(basicApplyService.getFullName(estateBatchDetail.getName(), buildBatchDetail.getName(), unitBatchDetail.getName(), houseDetail.getName()));
                    }else{
                        CaseUnit caseUnit = caseUnitService.getCaseUnitById(houseDetail.getCaseTablePid());
                        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());
                        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
                        basicApply.setName(basicApplyService.getFullName(caseEstate.getName(), caseBuilding.getBuildingNumber(), caseUnit.getUnitNumber(), houseDetail.getName()));
                    }
                    basicApplyService.saveBasicApply(basicApply);
                }
                //交易信息
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar());
                BasicHouseTrading basicTrading = JSONObject.parseObject(jsonContent, BasicHouseTrading.class);
                if (basicTrading != null) {
                    BasicHouseTrading houseTradingOld = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
                    basicTrading.setId(houseTradingOld.getId());
                    basicTrading.setHouseId(houseId);
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading, true);
                }
                //完损度
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_DAMAGED_DEGREE.getVar());
                List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonContent, BasicHouseDamagedDegree.class);
                if (!org.springframework.util.CollectionUtils.isEmpty(damagedDegreeList)) {
                    for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                        Integer category = degree.getCategory();
                        BasicHouseDamagedDegree damagedDegree = basicHouseDamagedDegreeService.getDamagedDegreeByHouseIdAndCategory(houseId, category);
                        damagedDegree.setEntityCondition(degree.getEntityCondition());
                        damagedDegree.setEntityConditionContent(degree.getEntityConditionContent());
                        damagedDegree.setScore(degree.getScore());
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(damagedDegree, false);
                    }
                    //写入成新率
                    String newDegree = residueRatioService.getObservationalRatio(basicHouse.getId());
                    basicHouse.setNewDegree(newDegree);
                    basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
                }
            }
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
        //验证是否已在审批
        this.verifyApproval(id);

        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(id);
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
            applyBatch.setDraftFlag(false);
            applyBatch.setProcessInsId(processUserDto.getProcessInsId());
            applyBatch.setStatus(ProjectStatusEnum.RUNING.getKey());
            basicApplyBatchDao.updateInfo(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw e;
        }
        return processUserDto;
    }


    //验证楼盘是否已发起审批
    public void verifyApproval(Integer id) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(id);
        //需要验证的数据
        BasicEstate basicEstate = basicEstateDao.getBasicEstateById(applyBatch.getEstateId());
        BasicApplyBatch verifyData = new BasicApplyBatch();
        //验证升级数据
        if (applyBatch.getCaseEstateId() != null) {
            verifyData.setEstateName(applyBatch.getEstateName());
            verifyData.setStatus(ProjectStatusEnum.RUNING.getKey());
            verifyData.setCaseEstateId(applyBatch.getCaseEstateId());
            List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(verifyData);
            if (CollectionUtils.isNotEmpty(infoList)) {
                throw new BusinessException("升级楼盘正在审批中，请不要重复申请");
            }
        } else {
            //获取所有审批中的数据
            BasicApplyBatch approvalData = new BasicApplyBatch();
            approvalData.setStatus(ProjectStatusEnum.RUNING.getKey());
            List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(approvalData);
            if (CollectionUtils.isNotEmpty(infoList)) {
                List<Integer> estateIds = LangUtils.transform(infoList, o -> o.getEstateId());
                List<BasicEstate> basicEstateList = LangUtils.transform(estateIds, p -> basicEstateDao.getBasicEstateById(p));
                for (BasicEstate item : basicEstateList) {
                    if (StringUtils.equals(item.getName(), basicEstate.getName()) && StringUtils.equals(item.getProvince()
                            , basicEstate.getProvince()) && StringUtils.equals(item.getCity(), basicEstate.getCity())) {
                        throw new BusinessException("该楼盘正在审批中，请不要重复申请");
                    }
                }
            }
        }


    }

    public BasicApplyBatchVo getBasicApplyBatchVo(BasicApplyBatch basicApplyBatch) {
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
//        StringBuilder stringBuilder = new StringBuilder();
//        //楼栋
//        List<BasicApplyBatchDetail> buildingList = basicApplyBatchDetailService.getBuildingBatchDetailsByBatchId(basicApplyBatch.getId());
//        if (CollectionUtils.isNotEmpty(buildingList)) {
//            for (BasicApplyBatchDetail build : buildingList) {
//                stringBuilder.append(basicApplyBatch.getEstateName()).append(build.getName()).append("栋");
//                //获取单元
//                List<BasicApplyBatchDetail> unitDetails = basicApplyBatchDetailService.getUnitBatchDetailsByBatchId(basicApplyBatch.getId(), basicBuildingDao.getBasicBuildingById(build.getTableId()));
//                if (CollectionUtils.isNotEmpty(unitDetails)) {
//                    for (BasicApplyBatchDetail unit : unitDetails) {
//                        stringBuilder.append(unit.getName()).append("单元");
//                        //获取房屋
//                        List<BasicApplyBatchDetail> houseDetails = basicApplyBatchDetailService.getHouseBatchDetailsByBatchId(basicApplyBatch.getId(), basicUnitService.getBasicUnitById(unit.getTableId()));
//                        if (CollectionUtils.isNotEmpty(houseDetails)) {
//                            StringBuilder houseStr = new StringBuilder();
//                            for (BasicApplyBatchDetail house : houseDetails) {
//                                houseStr.append(house.getName()).append("、");
//                            }
//                            houseStr.deleteCharAt(houseStr.length() - 1).append("号");
//                            stringBuilder.append(houseStr);
//                        }
//                        stringBuilder.append("/");
//                    }
//                } else {
//                    stringBuilder.append("/");
//                }
//            }
//            vo.setFullName(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
//        }else {
//            vo.setFullName(basicApplyBatch.getEstateName());
//        }
        vo.setFullName(basicApplyBatch.getEstateName());
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
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(id);
        if (basicApplyBatch != null) {
            if (basicApplyBatch.getCaseEstateId() != null) {
                //获取新增主节点
                List<BasicApplyBatchDetail> detailByApplyList = basicApplyBatchDetailDao.getCaseAddNodeDetail(basicApplyBatch.getId());
                if (CollectionUtils.isNotEmpty(detailByApplyList)) {
                    for (BasicApplyBatchDetail detail : detailByApplyList) {
                        if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                            List<BasicBuilding> oldBuildings = Lists.newArrayList();
                            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingById(detail.getTableId());
                            oldBuildings.add(basicBuilding);
                            this.copyBasicBuilding(oldBuildings, detail.getCaseTablePid(), basicApplyBatch);//处理楼栋
                        }
                        if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                            List<BasicUnit> unitOlds = Lists.newArrayList();
                            BasicUnit basicUnit = basicUnitService.getBasicUnitById(detail.getTableId());
                            unitOlds.add(basicUnit);
                            this.copyBasicUnit(unitOlds, detail.getCaseTablePid(), basicApplyBatch);
                        }
                        if (StringUtils.equals(detail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                            List<BasicHouse> oldHouses = Lists.newArrayList();
                            BasicHouse basicHouse = basicHouseService.getBasicHouseById(detail.getTableId());
                            oldHouses.add(basicHouse);
                            copyBasicHouse(oldHouses, detail.getCaseTablePid());
                        }
                    }
                }
                //升级的数据处理
                List<BasicApplyBatchDetail> upgradeDetailList = basicApplyBatchDetailDao.getUpgradeAddDetail(basicApplyBatch.getId());
                if (CollectionUtils.isNotEmpty(upgradeDetailList)) {
                    for (BasicApplyBatchDetail upgrade : upgradeDetailList) {
                        //1.老数据最新标识改变 2.添加升级数据，版本加一 3.关联老数据的下级改关联升级数据
                        if (StringUtils.equals(upgrade.getTableName(), FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
                            CaseEstate oldCaseEstate = caseEstateService.getCaseEstateById(upgrade.getUpgradeTableId());
                            oldCaseEstate.setNewVersions(false);
                            caseEstateService.saveAndUpdateCaseEstate(oldCaseEstate);

                            CaseEstate newCaseEstate = this.copyBasicEstate(basicEstateService.getBasicEstateById(upgrade.getTableId()), publicBasicService.getEstateLandStateByEstateId(upgrade.getTableId()));
                            newCaseEstate.setVersion(oldCaseEstate.getVersion() + 1);
                            newCaseEstate.setClassify(oldCaseEstate.getClassify());
                            newCaseEstate.setType(oldCaseEstate.getType());
                            caseEstateService.saveAndUpdateCaseEstate(newCaseEstate);

                            CaseBuilding building = new CaseBuilding();
                            building.setNewVersions(true);
                            building.setEstateId(oldCaseEstate.getId());
                            building.setEstateId(oldCaseEstate.getId());
                            List<CaseBuilding> caseBuildingList = caseBuildingService.getCaseBuildingList(building);
                            if (CollectionUtils.isNotEmpty(caseBuildingList)) {
                                for (CaseBuilding caseBuilding : caseBuildingList) {
                                    caseBuilding.setEstateId(newCaseEstate.getId());
                                    caseBuildingService.saveAndUpdateCaseBuilding(caseBuilding);
                                }
                            }
                        }
                        if (StringUtils.equals(upgrade.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                            CaseBuilding oldCaseBuilding = caseBuildingService.getCaseBuildingById(upgrade.getUpgradeTableId());
                            oldCaseBuilding.setNewVersions(false);
                            caseBuildingService.saveAndUpdateCaseBuilding(oldCaseBuilding);

                            List<BasicBuilding> oldBuildings = Lists.newArrayList();
                            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingById(upgrade.getTableId());
                            oldBuildings.add(basicBuilding);
                            CaseBuilding newCaseBuilding = this.copyBasicBuilding(oldBuildings, oldCaseBuilding.getEstateId(), basicApplyBatch).get(0);//处理楼栋
                            newCaseBuilding.setVersion(oldCaseBuilding.getVersion() + 1);
                            caseBuildingService.saveAndUpdateCaseBuilding(newCaseBuilding);

                            CaseUnit caseUnit = new CaseUnit();
                            caseUnit.setBuildingId(oldCaseBuilding.getId());
                            caseUnit.setNewVersions(true);
                            List<CaseUnit> caseUnitList = caseUnitService.getCaseUnitList(caseUnit);
                            if (CollectionUtils.isNotEmpty(caseUnitList)) {
                                for (CaseUnit item : caseUnitList) {
                                    item.setBuildingId(newCaseBuilding.getId());
                                    caseUnitService.saveAndUpdateCaseUnit(item);
                                }
                            }
                        }
                        if (StringUtils.equals(upgrade.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                            CaseUnit oldCaseUnit = caseUnitService.getCaseUnitById(upgrade.getUpgradeTableId());
                            oldCaseUnit.setNewVersions(false);
                            caseUnitService.saveAndUpdateCaseUnit(oldCaseUnit);

                            List<BasicUnit> unitOlds = Lists.newArrayList();
                            BasicUnit basicUnit = basicUnitService.getBasicUnitById(upgrade.getTableId());
                            unitOlds.add(basicUnit);
                            CaseUnit newCaseUnit = this.copyBasicUnit(unitOlds, oldCaseUnit.getBuildingId(), basicApplyBatch).get(0);
                            newCaseUnit.setVersion(oldCaseUnit.getVersion() + 1);
                            caseUnitService.saveAndUpdateCaseUnit(newCaseUnit);

                            CaseHouse caseHouse = new CaseHouse();
                            caseHouse.setUnitId(oldCaseUnit.getId());
                            caseHouse.setNewVersions(true);
                            List<CaseHouse> caseHouseList = caseHouseService.getCaseHouseList(caseHouse);
                            if (CollectionUtils.isNotEmpty(caseHouseList)) {
                                for (CaseHouse house : caseHouseList) {
                                    house.setUnitId(newCaseUnit.getId());
                                    caseHouseService.saveAndUpdateCaseHouse(house);
                                }
                            }

                        }
                        if (StringUtils.equals(upgrade.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                            CaseHouse oldCaseHouse = caseHouseService.getCaseHouseById(upgrade.getUpgradeTableId());
                            oldCaseHouse.setNewVersions(false);
                            caseHouseService.saveAndUpdateCaseHouse(oldCaseHouse);

                            List<BasicHouse> oldHouses = Lists.newArrayList();
                            BasicHouse basicHouse = basicHouseService.getBasicHouseById(upgrade.getTableId());
                            oldHouses.add(basicHouse);
                            CaseHouse newCaseHouse = copyBasicHouse(oldHouses, oldCaseHouse.getUnitId()).get(0);
                            newCaseHouse.setVersion(oldCaseHouse.getVersion() + 1);
                            caseHouseService.saveAndUpdateCaseHouse(newCaseHouse);
                        }
                    }
                }

            } else {
                BasicEstate basicEstateOld = publicBasicService.getBasicEstateById(basicApplyBatch.getEstateId());
                BasicEstateLandState basicEstateLandStateOld = publicBasicService.getEstateLandStateByEstateId(basicApplyBatch.getEstateId());
                List<BasicBuilding> oldBuildings = basicApplyBatchDetailService.getBuildingListByBatchId(basicApplyBatch.getId());

                CaseEstate caseEstate = this.copyBasicEstate(basicEstateOld, basicEstateLandStateOld);//处理楼盘
                this.copyBasicBuilding(oldBuildings, caseEstate.getId(), basicApplyBatch);//处理楼栋
            }
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
     * 楼栋复制（basic->case）
     *
     * @param buildingOlds
     * @param estateId
     * @return
     * @throws Exception
     */
    private List<CaseBuilding> copyBasicBuilding(List<BasicBuilding> buildingOlds, Integer estateId, BasicApplyBatch basicApplyBatch) throws Exception {
        List<CaseBuilding> CaseBuildings = Lists.newArrayList();
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
                CaseBuildings.add(caseBuilding);
            }
        return CaseBuildings;
    }


    /**
     * 楼栋复制（basic->basic）
     *
     * @param sourceBuilding
     * @param targetBuilding
     * @throws Exception
     */
    private void copyBasicBuildingToBasic(BasicBuilding sourceBuilding, BasicBuilding targetBuilding) throws Exception {
        if (sourceBuilding == null || targetBuilding == null) return;
        BeanUtils.copyProperties(sourceBuilding, targetBuilding, "id", "buildingNumber", "buildingName", "gmtCreated", "gmtModified");
        basicBuildingService.saveAndUpdateBasicBuilding(targetBuilding, false);

        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(sourceBuilding.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));

        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(targetBuilding.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        basicBuildingService.clearInvalidChildData(targetBuilding.getId());
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("building_id", String.valueOf(targetBuilding.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setWhereSql("building_id=" + sourceBuilding.getId());
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

        sqlBuilder.append(this.copyBasicTaggingToBasic(EstateTaggingTypeEnum.BUILDING, sourceBuilding.getId(), targetBuilding.getId()));
        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
    }


    /**
     * 单元复制(Basic->Case)
     *
     * @param unitOlds
     * @param caseBuildingId
     * @return
     * @throws Exception
     */
    private List<CaseUnit> copyBasicUnit(List<BasicUnit> unitOlds, Integer caseBuildingId, BasicApplyBatch basicApplyBatch) throws Exception {
        List<CaseUnit> caseUnits = Lists.newArrayList();
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
                    caseUnits.add(caseUnit);
                }
            }
        return caseUnits;
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
                example.setFieldsName(ExamineFileUpLoadFieldEnum.houseLatestFamilyPlanV.getName());

                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(caseUnitHuxing.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                baseAttachmentService.copyFtpAttachments(example, attachmentDto);
            }
        }
    }


    /**
     * 单元复制(Basic->Basic)
     *
     * @param sourceUnit
     * @param targetUnit
     * @throws Exception
     */
    private void copyBasicUnitToBasic(BasicUnit sourceUnit, BasicUnit targetUnit) throws Exception {
        if (sourceUnit == null || targetUnit == null) return;
        BeanUtils.copyProperties(sourceUnit, targetUnit, "id", "unitNumber", "gmtCreated", "gmtModified");
        basicUnitService.saveAndUpdateBasicUnit(targetUnit, false);
        basicUnitService.clearInvalidChildData(targetUnit.getId());
        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(sourceUnit.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(targetUnit.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        if (sourceUnit.getId() != null) {
            BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
            queryBasicUnitHuxing.setUnitId(sourceUnit.getId());
            List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(queryBasicUnitHuxing);
            this.copyBasicHuxingToBasic(basicUnitHuxingList, targetUnit);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("unit_id", String.valueOf(targetUnit.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setWhereSql("unit_id=" + sourceUnit.getId());
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

        sqlBuilder.append(this.copyBasicTaggingToBasic(EstateTaggingTypeEnum.UNIT, sourceUnit.getId(), targetUnit.getId()));
        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
    }

    public void copyBasicHuxingToBasic(List<BasicUnitHuxing> basicUnitHuxingList, BasicUnit basicUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing unitHuxing : basicUnitHuxingList) {
                BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                BeanUtils.copyProperties(unitHuxing, basicUnitHuxing);
                basicUnitHuxing.setId(null);
                basicUnitHuxing.setGmtCreated(null);
                basicUnitHuxing.setGmtModified(null);
                basicUnitHuxing.setUnitId(basicUnit.getId());
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, false);

                SysAttachmentDto example = new SysAttachmentDto();
                example.setTableId(unitHuxing.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                example.setFieldsName(ExamineFileUpLoadFieldEnum.houseLatestFamilyPlanV.getName());

                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(basicUnitHuxing.getId());
                baseAttachmentService.copyFtpAttachments(example, attachmentDto);
            }
        }
    }

    /**
     * 房屋 复制(Basic->Case)
     *
     * @param houseOlds
     * @param newUnitId
     * @throws Exception
     */
    public List<CaseHouse> copyBasicHouse(List<BasicHouse> houseOlds, Integer newUnitId) throws Exception {
        List<CaseHouse> caseHouses = Lists.newArrayList();
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
                    caseHouses.add(caseHouse);
                }
            }
        return caseHouses;
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
     * 房屋 复制（basic->basic）
     *
     * @param sourceHouse
     * @param targetHouse
     * @throws Exception
     */
    public void copyBasicHouseBasic(BasicHouse sourceHouse, BasicHouse targetHouse) throws Exception {
        if (sourceHouse == null || targetHouse == null) return;
        BeanUtils.copyProperties(sourceHouse, targetHouse, "id", "houseNumber", "gmtCreated", "gmtModified");
        basicHouseService.saveAndUpdateBasicHouse(targetHouse, false);
        basicHouseService.clearInvalidChildData(targetHouse.getId());
        //房屋交易信息
        BasicHouseTrading tradingOld = basicHouseTradingService.getTradingByHouseId(sourceHouse.getId());
        BasicHouseTrading basicHouseTrading = basicHouseTradingService.getTradingByHouseId(targetHouse.getId());
        if (tradingOld != null) {
            if (basicHouseTrading == null) {
                basicHouseTrading = new BasicHouseTrading();
                basicHouseTrading.setHouseId(targetHouse.getId());
            }
            BeanUtils.copyProperties(tradingOld, basicHouseTrading, "id", "houseId", "creator", "gmtCreated", "gmtModified");
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
        }

        List<BasicHouseRoom> basicHouseRoomList = null;
        List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = null;

        List<BasicHouseDamagedDegree> basicHouseDamagedDegreeList = null;
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        BasicHouseDamagedDegree queryHouseDamagedDegree = new BasicHouseDamagedDegree();

        if (sourceHouse != null && sourceHouse.getId() != null) {
            queryRoom.setHouseId(sourceHouse.getId());
            queryBasicHouseCorollaryEquipment.setHouseId(sourceHouse.getId());
            queryHouseDamagedDegree.setHouseId(sourceHouse.getId());
        }
        //清理附件
        SysAttachmentDto deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(targetHouse.getId());
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        baseAttachmentService.deleteAttachmentByDto(deleteExample);
        deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(basicHouseTrading.getId());
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseTrading.class));
        baseAttachmentService.deleteAttachmentByDto(deleteExample);

        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(sourceHouse.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(targetHouse.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
        basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
        basicHouseDamagedDegreeList = basicHouseDamagedDegreeDao.getDamagedDegreeList(queryHouseDamagedDegree);

        if (targetHouse != null && targetHouse.getId() != null) {
            this.copyBasicHouseRoomToBaisc(basicHouseRoomList, targetHouse);
            this.copyBasicCorollaryEquipmentToBaisc(basicHouseCorollaryEquipmentList, targetHouse);
            this.copyBasicDamagedDegreeToBaisc(basicHouseDamagedDegreeList, targetHouse);

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("house_id", String.valueOf(targetHouse.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("house_id=" + sourceHouse.getId());
            synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
            synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

            sqlBuilder.append(this.copyBasicTaggingToBasic(EstateTaggingTypeEnum.HOUSE, sourceHouse.getId(), targetHouse.getId()));
            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
    }

    private void copyBasicCorollaryEquipmentToBaisc(List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseCorollaryEquipmentList)) {
            basicHouseCorollaryEquipmentList.forEach(oo -> {
                try {
                    BasicHouseCorollaryEquipment basicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, basicHouseCorollaryEquipment);
                    basicHouseCorollaryEquipment.setId(null);
                    basicHouseCorollaryEquipment.setGmtCreated(null);
                    basicHouseCorollaryEquipment.setGmtModified(null);
                    basicHouseCorollaryEquipment.setHouseId(basicHouseNew.getId());
                    basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(basicHouseCorollaryEquipment, false);

                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(oo.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(basicHouseCorollaryEquipment.getId());
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicHouseRoomToBaisc(List<BasicHouseRoom> basicHouseRoomList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
            for (BasicHouseRoom oo : basicHouseRoomList) {
                BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
                BeanUtils.copyProperties(oo, basicHouseRoom);
                basicHouseRoom.setId(null);
                basicHouseRoom.setGmtCreated(null);
                basicHouseRoom.setGmtModified(null);
                basicHouseRoom.setHouseId(basicHouseNew.getId());
                basicHouseRoomService.saveAndUpdateBasicHouseRoom(basicHouseRoom, false);

                BasicHouseRoomDecorate query = new BasicHouseRoomDecorate();
                query.setRoomId(oo.getId());
                List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(query);
                if (!ObjectUtils.isEmpty(basicHouseRoomDecorateList)) {
                    for (BasicHouseRoomDecorate po : basicHouseRoomDecorateList) {
                        BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                        BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                        basicHouseRoomDecorate.setGmtCreated(null);
                        basicHouseRoomDecorate.setGmtModified(null);
                        basicHouseRoomDecorate.setId(null);
                        basicHouseRoomDecorate.setRoomId(basicHouseRoom.getId());
                        basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate, false);
                    }
                }
            }
        }
    }

    private void copyBasicDamagedDegreeToBaisc(List<BasicHouseDamagedDegree> basicHouseDamagedDegrees, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseDamagedDegrees)) {
            for (BasicHouseDamagedDegree old : basicHouseDamagedDegrees) {
                BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
                BeanUtils.copyProperties(old, basicHouseDamagedDegree);
                basicHouseDamagedDegree.setId(null);
                basicHouseDamagedDegree.setGmtCreated(null);
                basicHouseDamagedDegree.setGmtModified(null);
                basicHouseDamagedDegree.setHouseId(basicHouseNew.getId());
                basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree, false);

                List<BasicHouseDamagedDegreeDetail> degreeDetails = basicHouseDamagedDegreeService.getDamagedDegreeDetails(basicHouseDamagedDegree.getId());
                if (!CollectionUtils.isEmpty(degreeDetails)) {
                    for (BasicHouseDamagedDegreeDetail degreeDetail : degreeDetails) {
                        BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                        BeanUtils.copyProperties(degreeDetail, basicHouseDamagedDegreeDetail);
                        basicHouseDamagedDegreeDetail.setId(null);
                        basicHouseDamagedDegreeDetail.setGmtCreated(null);
                        basicHouseDamagedDegreeDetail.setGmtModified(null);
                        basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                        basicHouseDamagedDegreeDetail.setHouseId(basicHouseNew.getId());
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail, false);
                    }
                }
            }
        }
    }


    /**
     * 标记复制(Baisc->Case)
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

    /**
     * 标记复制(Basic->Basic)
     *
     * @param typeEnum
     * @param tableIdSource
     * @param tableIdTarget
     * @throws Exception
     */
    private String copyBasicTaggingToBasic(EstateTaggingTypeEnum typeEnum, Integer tableIdSource, Integer tableIdTarget) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        synchronousDataDto.setWhereSql(String.format("table_id=%s and type='%s'", tableIdSource, typeEnum.getKey()));
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        HashMap<String, String> map = Maps.newHashMap();
        map.put("table_id", String.valueOf(tableIdTarget));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));
        return sqlBuilder.toString();
    }

    /**
     * 粘贴调查信息
     *
     * @param sourceBatchDetailId
     * @param targetBatchDetailId
     * @throws Exception
     */
    public void pasteExamineInfo(Integer sourceBatchDetailId, Integer targetBatchDetailId) throws Exception {
        //被复制数据
        BasicApplyBatchDetail sourceBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(sourceBatchDetailId);
        BasicApplyBatchDetail targetBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(targetBatchDetailId);

        //复制楼栋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_building")) {
            BasicBuilding sourceBuilding = basicBuildingDao.getBasicBuildingById(sourceBasicApplyBatchDetail.getTableId());
            BasicBuilding targetBuilding = basicBuildingDao.getBasicBuildingById(targetBasicApplyBatchDetail.getTableId());
            copyBasicBuildingToBasic(sourceBuilding, targetBuilding);
        }
        //复制单元
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_unit")) {
            BasicUnit sourceUnit = basicUnitDao.getBasicUnitById(sourceBasicApplyBatchDetail.getTableId());
            BasicUnit targeUnit = basicUnitDao.getBasicUnitById(targetBasicApplyBatchDetail.getTableId());
            copyBasicUnitToBasic(sourceUnit, targeUnit);
        }
        //复制房屋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_house")) {
            BasicHouse sourceHouse = basicHouseDao.getBasicHouseById(sourceBasicApplyBatchDetail.getTableId());
            BasicHouse targetHouse = basicHouseDao.getBasicHouseById(targetBasicApplyBatchDetail.getTableId());
            copyBasicHouseBasic(sourceHouse, targetHouse);
        }
    }


    public BasicApplyBatch initCaseApplyBatch(Integer caseEstateId) throws Exception {
        //删除未保存且未提交数据
        List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoListNotCommit(caseEstateId);
        if (CollectionUtils.isNotEmpty(infoList)) {
            for (BasicApplyBatch item : infoList) {
                //删除子表及主表
                List<BasicApplyBatchDetail> detailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(item.getId());
                if (CollectionUtils.isNotEmpty(detailList)) {
                    for (BasicApplyBatchDetail detail : detailList) {
                        basicApplyBatchDetailDao.deleteInfo(detail.getId());
                    }
                }
                this.deleteBasicBatchApply(item.getId());
            }
        }
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseEstateId);
        BasicApplyBatch applyBatch = new BasicApplyBatch();
        applyBatch.setClassify(caseEstate.getClassify());
        applyBatch.setType(caseEstate.getType());
        applyBatch.setCaseEstateId(caseEstate.getId());
        applyBatch.setEstateName(String.format("案例楼盘(%s)", caseEstate.getName()));
        applyBatch.setDraftFlag(true);
        applyBatch.setCreator(commonService.thisUserAccount());
        return applyBatch;

    }


    public BasicApplyBatchDetail upgrade(Integer sourceCaseTableId, String type, Integer applyBatchId, Integer pid) throws Exception {
        if (sourceCaseTableId == null) return null;
        BasicApplyBatchDetail detail = new BasicApplyBatchDetail();
        detail.setApplyBatchId(applyBatchId);
        detail.setUpgradeTableId(sourceCaseTableId);
        detail.setPid(pid);
        List<BasicApplyBatchDetail> detailList = basicApplyBatchDetailDao.getInfoList(detail);
        if (CollectionUtils.isNotEmpty(detailList)) {
            return detailList.get(0);
        }

        if (StringUtils.equals("estate", type)) {
            CaseEstate caseEstate = caseEstateService.getCaseEstateById(sourceCaseTableId);
            BasicEstate estate = new BasicEstate();
            basicEstateService.saveAndUpdateBasicEstate(estate, false);
            caseEstateService.quoteCaseEstateToBasic(sourceCaseTableId, estate.getId());
            detail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            detail.setDisplayName(caseEstate.getName());
            detail.setName(caseEstate.getName());
            detail.setTableId(estate.getId());
        }
        if (StringUtils.equals("building", type)) {
            CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(sourceCaseTableId);
            BasicBuilding building = new BasicBuilding();
            basicBuildingService.saveAndUpdateBasicBuilding(building, false);
            caseBuildingService.quoteCaseBuildToBasic(sourceCaseTableId, building.getId());
            detail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            detail.setDisplayName(String.format("%s栋", caseBuilding.getBuildingNumber()));
            detail.setName(caseBuilding.getBuildingNumber());
            detail.setTableId(building.getId());
        }
        if (StringUtils.equals("unit", type)) {
            CaseUnit caseUnit = caseUnitService.getCaseUnitById(sourceCaseTableId);
            BasicUnit unit = new BasicUnit();
            basicUnitService.saveAndUpdateBasicUnit(unit, false);
            caseUnitService.quoteCaseUnitToBasic(sourceCaseTableId, unit.getId());
            detail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
            detail.setDisplayName(String.format("%s号", caseUnit.getUnitNumber()));
            detail.setName(caseUnit.getUnitNumber());
            detail.setTableId(unit.getId());
        }
        if (StringUtils.equals("house", type)) {
            CaseHouse caseHouse = caseHouseService.getCaseHouseById(sourceCaseTableId);
            BasicHouse house = new BasicHouse();
            basicHouseService.saveAndUpdateBasicHouse(house, false);
            caseHouseService.quoteCaseHouseToBasic(sourceCaseTableId, house.getId());
            detail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            detail.setDisplayName(caseHouse.getHouseNumber());
            detail.setName(caseHouse.getHouseNumber());
            detail.setTableId(house.getId());
        }
        detail.setCreator(commonService.thisUserAccount());
        basicApplyBatchDetailDao.addInfo(detail);
        return detail;
    }


    /**
     * 深复制
     *
     * @param sourceBatchDetailId
     * @throws Exception
     */
    public void deepCopy(Integer sourceBatchDetailId, Integer planDetailsId) throws Exception {
        //被复制数据
        BasicApplyBatchDetail sourceBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(sourceBatchDetailId);

        //复制楼栋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_building")) {
            BasicBuilding sourceBuilding = basicBuildingDao.getBasicBuildingById(sourceBasicApplyBatchDetail.getTableId());
            List<BasicBuilding> sourceBuildings = Lists.newArrayList();
            sourceBuildings.add(sourceBuilding);
            deepCopyBasicBuilding(sourceBuildings, sourceBasicApplyBatchDetail, planDetailsId);
        }
        //复制单元
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_unit")) {
            BasicUnit sourceUnit = basicUnitDao.getBasicUnitById(sourceBasicApplyBatchDetail.getTableId());
            List<BasicUnit> sourceUnits = Lists.newArrayList();
            sourceUnits.add(sourceUnit);
            deepCopyBasicUnit(sourceUnits, null, sourceBasicApplyBatchDetail, null, planDetailsId);
        }
        //复制房屋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_house")) {
            BasicHouse sourceHouse = basicHouseDao.getBasicHouseById(sourceBasicApplyBatchDetail.getTableId());
            List<BasicHouse> sourceHouses = Lists.newArrayList();
            sourceHouses.add(sourceHouse);
            deepCopyBasicHouse(sourceHouses, null, sourceBasicApplyBatchDetail, null, planDetailsId);
        }

    }

    /**
     * 楼栋深复制（basic->basic）
     *
     * @param sourceBuildings
     * @return
     * @throws Exception
     */
    private void deepCopyBasicBuilding(List<BasicBuilding> sourceBuildings, BasicApplyBatchDetail source, Integer planDetailsId) throws Exception {
        if (CollectionUtils.isNotEmpty(sourceBuildings))
            for (BasicBuilding buildingOld : sourceBuildings) {
                BasicBuilding targetBuilding = new BasicBuilding();
                if (buildingOld != null) {
                    BeanUtils.copyProperties(buildingOld, targetBuilding, "id", "gmtCreated", "gmtModified");
                    Integer targetId = basicBuildingService.saveAndUpdateBasicBuilding(targetBuilding, false);
                    BasicApplyBatchDetail newBatchDetail = new BasicApplyBatchDetail();
                    if (source != null) {
                        BeanUtils.copyProperties(source, newBatchDetail, "id", "tableId", "quoteId", "baseType", "caseTablePid", "upgradeTableId", "gmtCreated", "gmtModified");
                        newBatchDetail.setTableId(targetId);
                        newBatchDetail.setCreator(commonService.thisUserAccount());
                        basicApplyBatchDetailDao.addInfo(newBatchDetail);
                    }

                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(buildingOld.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));

                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(targetBuilding.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                    basicBuildingService.clearInvalidChildData(targetBuilding.getId());
                    StringBuilder sqlBuilder = new StringBuilder();
                    SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                    HashMap<String, String> map = Maps.newHashMap();
                    map.put("building_id", String.valueOf(targetBuilding.getId()));
                    map.put("creator", commonService.thisUserAccount());
                    synchronousDataDto.setFieldDefaultValue(map);
                    synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
                    synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
                    synchronousDataDto.setWhereSql("building_id=" + buildingOld.getId());
                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

                    sqlBuilder.append(this.copyBasicTaggingToBasic(EstateTaggingTypeEnum.BUILDING, buildingOld.getId(), targetBuilding.getId()));
                    ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
                    //复制单元
                    List<BasicApplyBatchDetail> unitBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(source.getId(), source.getApplyBatchId());
                    List<BasicUnit> oldUnits = LangUtils.transform(unitBatchDetailList, o -> basicUnitDao.getBasicUnitById(o.getTableId()));

                    deepCopyBasicUnit(oldUnits, targetBuilding.getId(), null, newBatchDetail, planDetailsId);
                }
            }
    }


    /**
     * 单元深复制(Basic->Basic)
     *
     * @param sourceUnits
     * @param targetBuildingId
     * @return
     * @throws Exception
     */
    private void deepCopyBasicUnit(List<BasicUnit> sourceUnits, Integer targetBuildingId, BasicApplyBatchDetail source, BasicApplyBatchDetail parent, Integer planDetailsId) throws Exception {
        if (CollectionUtils.isNotEmpty(sourceUnits))
            for (BasicUnit sourceUnit : sourceUnits) {
                if (sourceUnit != null) {
                    BasicUnit targetUnit = new BasicUnit();
                    if (targetBuildingId == null) {
                        BeanUtils.copyProperties(sourceUnit, targetUnit, "id", "gmtCreated", "gmtModified");
                    } else {
                        BeanUtils.copyProperties(sourceUnit, targetUnit, "id", "buildingId", "gmtCreated", "gmtModified");
                        targetUnit.setBuildingId(targetBuildingId);
                    }
                    Integer targetId = basicUnitService.saveAndUpdateBasicUnit(targetUnit, false);
                    if (source != null) {
                        BasicApplyBatchDetail newBatchDetail = new BasicApplyBatchDetail();
                        BeanUtils.copyProperties(source, newBatchDetail, "id", "tableId", "quoteId", "baseType", "caseTablePid", "upgradeTableId", "gmtCreated", "gmtModified");
                        newBatchDetail.setTableId(targetId);
                        newBatchDetail.setCreator(commonService.thisUserAccount());
                        basicApplyBatchDetailDao.addInfo(newBatchDetail);
                    }
                    if (parent != null && parent.getId() != null) {
                        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
                        basicApplyBatchDetail.setPid(parent.getId());
                        basicApplyBatchDetail.setApplyBatchId(parent.getApplyBatchId());
                        basicApplyBatchDetail.setTableId(targetUnit.getId());
                        basicApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                        basicApplyBatchDetail.setName(targetUnit.getUnitNumber());
                        basicApplyBatchDetail.setDisplayName(String.format("%s单元", targetUnit.getUnitNumber()));
                        basicApplyBatchDetail.setCreator(commonService.thisUserAccount());
                        basicApplyBatchDetail.setExecutor(commonService.thisUserAccount());
                        basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
                    }

                    basicUnitService.clearInvalidChildData(targetUnit.getId());
                    //附件拷贝
                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(sourceUnit.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(targetUnit.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                    if (sourceUnit.getId() != null) {
                        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
                        queryBasicUnitHuxing.setUnitId(sourceUnit.getId());
                        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(queryBasicUnitHuxing);
                        this.copyBasicHuxingToBasic(basicUnitHuxingList, targetUnit);
                    }

                    StringBuilder sqlBuilder = new StringBuilder();
                    SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                    HashMap<String, String> map = Maps.newHashMap();
                    map.put("unit_id", String.valueOf(targetUnit.getId()));
                    map.put("creator", commonService.thisUserAccount());
                    synchronousDataDto.setFieldDefaultValue(map);
                    synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
                    synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
                    synchronousDataDto.setWhereSql("unit_id=" + sourceUnit.getId());
                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

                    synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
                    synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
                    sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

                    sqlBuilder.append(this.copyBasicTaggingToBasic(EstateTaggingTypeEnum.UNIT, sourceUnit.getId(), targetUnit.getId()));
                    ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
                    //复制单元
                    BasicApplyBatchDetail newBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicUnit.class), targetUnit.getId());

                    List<BasicApplyBatchDetail> unitBatchDetailList = Lists.newArrayList();
                    if (source != null) {
                        unitBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(source.getId(), source.getApplyBatchId());
                    } else {
                        BasicApplyBatchDetail oldBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicUnit.class), sourceUnit.getId());
                        unitBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(oldBatchDetail.getId(), oldBatchDetail.getApplyBatchId());
                    }
                    List<BasicHouse> oldHouses = LangUtils.transform(unitBatchDetailList, o -> basicHouseDao.getBasicHouseById(o.getTableId()));

                    deepCopyBasicHouse(oldHouses, targetUnit.getId(), null, newBatchDetail, planDetailsId);
                }
            }
    }


    /**
     * 房屋深复制(Basic->Basic)
     *
     * @param sourceHouses
     * @param targetUnitId
     * @throws Exception
     */
    public void deepCopyBasicHouse(List<BasicHouse> sourceHouses, Integer targetUnitId, BasicApplyBatchDetail source, BasicApplyBatchDetail unitParent, Integer planDetailsId) throws Exception {
        if (CollectionUtils.isNotEmpty(sourceHouses))
            for (BasicHouse sourceHouse : sourceHouses) {
                if (sourceHouse != null) {
                    BasicHouse targetHouse = new BasicHouse();
                    if (targetUnitId == null) {
                        BeanUtils.copyProperties(sourceHouse, targetHouse, "id", "gmtCreated", "gmtModified");
                    } else {
                        BeanUtils.copyProperties(sourceHouse, targetHouse, "id", "unitId", "gmtCreated", "gmtModified");
                    }
                    Integer targetId = basicHouseService.saveAndUpdateBasicHouse(targetHouse, false);
                    if (source != null) {
                        BasicApplyBatchDetail newBatchDetail = new BasicApplyBatchDetail();
                        BeanUtils.copyProperties(source, newBatchDetail, "id", "tableId", "quoteId", "baseType", "caseTablePid", "upgradeTableId", "gmtCreated", "gmtModified");
                        newBatchDetail.setTableId(targetId);
                        newBatchDetail.setCreator(commonService.thisUserAccount());
                        basicApplyBatchDetailDao.addInfo(newBatchDetail);
                        //生成BasicApply
                        basicApplyBatchDetailService.insertBasicApply(newBatchDetail, planDetailsId);
                    }
                    if (unitParent != null && unitParent.getId() != null) {
                        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
                        basicApplyBatchDetail.setPid(unitParent.getId());
                        basicApplyBatchDetail.setApplyBatchId(unitParent.getApplyBatchId());
                        basicApplyBatchDetail.setTableId(targetHouse.getId());
                        basicApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                        basicApplyBatchDetail.setName(targetHouse.getHouseNumber());
                        basicApplyBatchDetail.setDisplayName(targetHouse.getHouseNumber());
                        basicApplyBatchDetail.setCreator(commonService.thisUserAccount());
                        basicApplyBatchDetail.setExecutor(commonService.thisUserAccount());
                        basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
                        basicApplyBatchDetailService.insertBasicApply(basicApplyBatchDetail, planDetailsId);
                    }
                    basicHouseService.clearInvalidChildData(targetHouse.getId());
                    //房屋交易信息
                    BasicHouseTrading tradingOld = basicHouseTradingService.getTradingByHouseId(sourceHouse.getId());
                    BasicHouseTrading basicHouseTrading = basicHouseTradingService.getTradingByHouseId(targetHouse.getId());
                    if (tradingOld != null) {
                        if (basicHouseTrading == null) {
                            basicHouseTrading = new BasicHouseTrading();
                            basicHouseTrading.setHouseId(targetHouse.getId());
                        }
                        BeanUtils.copyProperties(tradingOld, basicHouseTrading, "id", "houseId", "creator", "gmtCreated", "gmtModified");
                        basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
                    }

                    List<BasicHouseRoom> basicHouseRoomList = null;
                    List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = null;

                    List<BasicHouseDamagedDegree> basicHouseDamagedDegreeList = null;
                    BasicHouseRoom queryRoom = new BasicHouseRoom();
                    BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
                    BasicHouseDamagedDegree queryHouseDamagedDegree = new BasicHouseDamagedDegree();

                    if (sourceHouse != null && sourceHouse.getId() != null) {
                        queryRoom.setHouseId(sourceHouse.getId());
                        queryBasicHouseCorollaryEquipment.setHouseId(sourceHouse.getId());
                        queryHouseDamagedDegree.setHouseId(sourceHouse.getId());
                    }
                    //清理附件
                    SysAttachmentDto deleteExample = new SysAttachmentDto();
                    deleteExample.setTableId(targetHouse.getId());
                    deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    baseAttachmentService.deleteAttachmentByDto(deleteExample);
                    deleteExample = new SysAttachmentDto();
                    deleteExample.setTableId(basicHouseTrading.getId());
                    deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseTrading.class));
                    baseAttachmentService.deleteAttachmentByDto(deleteExample);

                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(sourceHouse.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(targetHouse.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                    basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
                    basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
                    basicHouseDamagedDegreeList = basicHouseDamagedDegreeDao.getDamagedDegreeList(queryHouseDamagedDegree);

                    if (targetHouse != null && targetHouse.getId() != null) {
                        this.copyBasicHouseRoomToBaisc(basicHouseRoomList, targetHouse);
                        this.copyBasicCorollaryEquipmentToBaisc(basicHouseCorollaryEquipmentList, targetHouse);
                        this.copyBasicDamagedDegreeToBaisc(basicHouseDamagedDegreeList, targetHouse);

                        StringBuilder sqlBuilder = new StringBuilder();
                        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                        HashMap<String, String> map = Maps.newHashMap();
                        map.put("house_id", String.valueOf(targetHouse.getId()));
                        map.put("creator", commonService.thisUserAccount());
                        synchronousDataDto.setFieldDefaultValue(map);
                        synchronousDataDto.setWhereSql("house_id=" + sourceHouse.getId());
                        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
                        synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

                        sqlBuilder.append(this.copyBasicTaggingToBasic(EstateTaggingTypeEnum.HOUSE, sourceHouse.getId(), targetHouse.getId()));
                        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
                    }
                }
            }
    }
}

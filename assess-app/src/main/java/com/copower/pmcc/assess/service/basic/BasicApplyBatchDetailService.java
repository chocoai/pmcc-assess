package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.dto.output.project.survey.BasicApplyBatchDetailVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BasicApplyBatchDetailService {
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;

    /**
     * 通过applyBatchId获取
     *
     * @param applyBatchId
     * @return
     */
    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailByApplyBatchId(Integer applyBatchId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public BasicApplyBatchDetail getBasicApplyBatchDetail(String tableName, Integer tableId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableId(tableId);
        basicApplyBatchDetail.setTableName(tableName);
        List<BasicApplyBatchDetail> infoList = basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(infoList))
            return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail).get(0);
        return null;
    }

    public void saveBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail) {
        if (basicApplyBatchDetail.getId() != null && basicApplyBatchDetail.getId() > 0) {
            basicApplyBatchDetailDao.updateInfo(basicApplyBatchDetail);
        } else {
            basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
            basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        }
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailList(BasicApplyBatchDetail examineInfo) {
        return basicApplyBatchDetailDao.getInfoList(examineInfo);
    }

    /**
     * 新增
     *
     * @param basicApplyBatchDetail
     * @return
     */
    public BasicApplyBatchDetail addBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail, Integer planDetailsId) throws Exception {
        basicApplyBatchDetail.setDisplayName(basicApplyBatchDetail.getName());
        basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
        switch (basicApplyBatchDetail.getTableName()) {
            case "tb_basic_building":
                basicApplyBatchDetail.setDisplayName(String.format("%s栋", basicApplyBatchDetail.getName()));
                BasicBuilding building = new BasicBuilding();
                building.setBuildingNumber(basicApplyBatchDetail.getName());
                BasicEstate basicEstate = getBasicEstateByBatchDetailId(basicApplyBatchDetail.getPid());
                if (basicEstate != null)
                    building.setEstateId(basicEstate.getId());
                building.setBuildingName(basicApplyBatchDetail.getDisplayName());
                basicBuildingDao.addBasicBuilding(building);
                basicApplyBatchDetail.setTableId(building.getId());
                //当前人与执行人不一致时发起子任务
                if (!StringUtils.equals(basicApplyBatchDetail.getCreator(), basicApplyBatchDetail.getExecutor())) {
                    addSonTask(planDetailsId, "building", building.getId(), basicApplyBatchDetail.getExecutor(), basicApplyBatchDetail.getApplyBatchId());
                }
                break;
            case "tb_basic_unit":
                basicApplyBatchDetail.setDisplayName(String.format("%s单元", basicApplyBatchDetail.getName()));
                BasicUnit unit = new BasicUnit();
                BasicBuilding basicBuilding = getBasicBuildingByBatchDetailId(basicApplyBatchDetail.getPid());
                if(basicBuilding!=null){
                    unit.setBuildingId(basicBuilding.getId());
                    unit.setEstateId(basicBuilding.getEstateId());
                }
                unit.setUnitNumber(basicApplyBatchDetail.getName());
                basicUnitService.saveAndUpdateBasicUnit(unit, false);
                basicApplyBatchDetail.setTableId(unit.getId());
                //当前人与执行人不一致时发起子任务
                if (!StringUtils.equals(basicApplyBatchDetail.getCreator(), basicApplyBatchDetail.getExecutor())) {
                    addSonTask(planDetailsId, "unit", unit.getId(), basicApplyBatchDetail.getExecutor(), basicApplyBatchDetail.getApplyBatchId());
                }
                break;
            case "tb_basic_house":
                BasicHouse house = new BasicHouse();
                house.setHouseNumber(basicApplyBatchDetail.getName());
                BasicUnit basicUnit = getBasicUnitByBatchDetailId(basicApplyBatchDetail.getPid());
                if(basicUnit!=null){
                    house.setUnitId(basicUnit.getId());
                    house.setBuildingId(basicUnit.getBuildingId());
                    house.setEstateId(basicUnit.getEstateId());
                }
                //申报表代入的信息
                DeclareRecord declareRecord = null;
                ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
                if (projectPlanDetails != null && projectPlanDetails.getDeclareRecordId() != null) {
                    declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
                    if (declareRecord != null) {
                        house.setCertUse(declareRecord.getCertUse());
                        house.setPracticalUse(declareRecord.getCertUse());
                    }
                }

                basicHouseService.saveAndUpdateBasicHouse(house, false);
                basicApplyBatchDetail.setTableId(house.getId());

                BasicHouseTrading houseTrading = new BasicHouseTrading();
                houseTrading.setHouseId(house.getId());
                if (projectPlanDetails != null) {
                    ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
                    houseTrading.setScopeInclude(projectInfo.getScopeInclude());
                    houseTrading.setScopeNotInclude(projectInfo.getScopeNotInclude());
                    if (NumberUtils.isNumber(projectInfo.getPropertyScope())) {
                        houseTrading.setScopeProperty(Integer.valueOf(projectInfo.getPropertyScope()));
                    }
                }
                basicHouseTradingService.saveAndUpdateBasicHouseTrading(houseTrading, false);
                //当前人与执行人不一致时发起子任务
                if (!StringUtils.equals(basicApplyBatchDetail.getCreator(), basicApplyBatchDetail.getExecutor())) {
                    addSonTask(planDetailsId, "house", house.getId(), basicApplyBatchDetail.getExecutor(), basicApplyBatchDetail.getApplyBatchId());
                }
                break;
        }
        basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        //存入basicApply
        this.insertBasicApply(basicApplyBatchDetail, planDetailsId);

        return basicApplyBatchDetail;
    }

    //添加子任务
    public void addSonTask(Integer planDetailsId, String key, Integer tableId, String executor, Integer basicApplyBatchId) {
        if (executor == null) return;
        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(basicApplyBatchId);
        if (StringUtils.equals(applyBatch.getCreator(), executor)) return;
        ProjectResponsibilityDto masterDto = new ProjectResponsibilityDto();
        masterDto.setPlanDetailsId(planDetailsId);
        masterDto.setPid(0);
        ProjectResponsibilityDto projectTaskMaster = bpmRpcProjectTaskService.getProjectTask(masterDto);
        ProjectResponsibilityDto sonDto = new ProjectResponsibilityDto();
        BeanUtils.copyProperties(projectTaskMaster, sonDto, "id", "gmtCreated", "gmtModified");
        sonDto.setPid(projectTaskMaster.getId());
        sonDto.setBusinessKey(key);
        sonDto.setBusinessId(tableId);
        sonDto.setUserAccount(executor);
        bpmRpcProjectTaskService.saveProjectTaskExtend(sonDto);
    }

    //修改子任务
    public void updateSonTask(Integer planDetailsId, String key, Integer tableId, String executor, String creator, Integer applyBatchId) {
        if (executor == null) return;
        ProjectResponsibilityDto oldDto = new ProjectResponsibilityDto();
        oldDto.setPlanDetailsId(planDetailsId);
        oldDto.setBusinessId(tableId);
        oldDto.setBusinessKey(key);
        ProjectResponsibilityDto oldTask = bpmRpcProjectTaskService.getProjectTask(oldDto);
        if (oldTask != null) {
            //执行人变为创建人则删除子任务
            if (StringUtils.equals(creator, executor)) {
                bpmRpcProjectTaskService.deleteProjectTask(oldTask.getId());
            } else {
                ProjectResponsibilityDto newDto = new ProjectResponsibilityDto();
                BeanUtils.copyProperties(oldTask, newDto, "userAccount", "gmtCreated", "gmtModified");
                newDto.setUserAccount(executor);
                bpmRpcProjectTaskService.updateProjectTask(newDto);
            }
        } else {
            if (!StringUtils.equals(creator, executor)) {
                addSonTask(planDetailsId, key, tableId, executor, applyBatchId);
            }
        }

    }

    //存入basicApply表单
    public void insertBasicApply(BasicApplyBatchDetail houseBasicApplyBatchDetail, Integer planDetailsId) throws Exception {
        if (!houseBasicApplyBatchDetail.getTableName().equals("tb_basic_house"))
            return;
        BasicApply where = new BasicApply();
        where.setPlanDetailsId(planDetailsId);
        where.setBasicHouseId(houseBasicApplyBatchDetail.getTableId());
        BasicApply basicApply = basicApplyService.getBasicApply(where);
        if (basicApply == null) {
            basicApply = new BasicApply();
            Map<EstateTaggingTypeEnum, BasicApplyBatchDetail> map = getApplyBatchDetailMap(houseBasicApplyBatchDetail);
            basicApply.setBasicHouseId(houseBasicApplyBatchDetail.getTableId());
            basicApply.setHouseNumber(houseBasicApplyBatchDetail.getName());
            BasicHouse basicHouse = basicHouseService.getBasicHouseById(houseBasicApplyBatchDetail.getTableId());
            if (basicHouse != null)
                basicApply.setArea(basicHouse.getArea());
            //单元
            BasicApplyBatchDetail unitBatchDetail = map.get(EstateTaggingTypeEnum.UNIT);
            if (unitBatchDetail != null) {
                basicApply.setUnitNumber(unitBatchDetail.getName());
                basicApply.setBasicUnitId(unitBatchDetail.getTableId());
            } else {
                CaseUnit caseUnit = caseUnitService.getCaseUnitById(houseBasicApplyBatchDetail.getCaseTablePid());
                if (caseUnit != null) {
                    CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());
                    CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
                    basicApply.setName(basicApplyService.getFullName(caseEstate.getName(), caseBuilding.getBuildingNumber(), caseUnit.getUnitNumber(), houseBasicApplyBatchDetail.getName()));
                }
            }
            //楼栋
            BasicApplyBatchDetail buildBatchDetail = map.get(EstateTaggingTypeEnum.BUILDING);
            if (buildBatchDetail != null) {
                basicApply.setBuildingNumber(buildBatchDetail.getName());
                basicApply.setBasicBuildingId(buildBatchDetail.getTableId());
            } else {
                if (unitBatchDetail != null) {
                    CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(unitBatchDetail.getCaseTablePid());
                    if (caseBuilding != null) {
                        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
                        basicApply.setName(basicApplyService.getFullName(caseEstate.getName(), caseBuilding.getBuildingNumber(), unitBatchDetail.getName(), houseBasicApplyBatchDetail.getName()));
                    }
                }
            }
            //楼盘
            BasicApplyBatchDetail estateBatchDetail = map.get(EstateTaggingTypeEnum.ESTATE);
            if (estateBatchDetail != null) {
                basicApply.setBasicEstateId(estateBatchDetail.getTableId());
            } else {
                if (buildBatchDetail != null) {
                    CaseEstate caseEstate = caseEstateService.getCaseEstateById(buildBatchDetail.getCaseTablePid());
                    if (caseEstate != null) {
                        basicApply.setName(basicApplyService.getFullName(caseEstate.getName(), buildBatchDetail.getName(), unitBatchDetail.getName(), houseBasicApplyBatchDetail.getName()));
                    }
                }
            }
            basicApply.setPlanDetailsId(planDetailsId);
            if (basicApply.getName() == null)
                basicApply.setName(basicApplyService.getFullName(estateBatchDetail.getName(), buildBatchDetail.getName(), unitBatchDetail.getName(), houseBasicApplyBatchDetail.getName()));
            basicApplyService.saveBasicApply(basicApply);
        }
    }

    /**
     * 通过pid获取
     *
     * @param pid
     * @return
     */
    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailByPid(Integer pid, Integer applyBatchId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setPid(pid);
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    /**
     * 获取一个完整估价Map
     *
     * @param houseBasicApplyBatchDetail
     * @return
     */
    public Map<EstateTaggingTypeEnum, BasicApplyBatchDetail> getApplyBatchDetailMap(BasicApplyBatchDetail houseBasicApplyBatchDetail) {
        Map<EstateTaggingTypeEnum, BasicApplyBatchDetail> map = Maps.newHashMap();
        map.put(EstateTaggingTypeEnum.HOUSE, houseBasicApplyBatchDetail);
        BasicApplyBatchDetail unitBatchDetail = basicApplyBatchDetailDao.getInfoById(houseBasicApplyBatchDetail.getPid());
        if (unitBatchDetail != null) {
            map.put(EstateTaggingTypeEnum.UNIT, unitBatchDetail);
            BasicApplyBatchDetail buildBatchDetail = basicApplyBatchDetailDao.getInfoById(unitBatchDetail.getPid());
            if (buildBatchDetail != null) {
                map.put(EstateTaggingTypeEnum.BUILDING, buildBatchDetail);
                BasicApplyBatchDetail estateBatchDetail = basicApplyBatchDetailDao.getInfoById(buildBatchDetail.getPid());
                if (estateBatchDetail != null) {
                    map.put(EstateTaggingTypeEnum.ESTATE, estateBatchDetail);
                }
            }
        }
        return map;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public void deleteBasicApplyBatchDetail(Integer id) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(id);
        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
        switch (basicApplyBatchDetail.getTableName()) {
            case "tb_basic_building":
                //删除原来楼栋数据
                basicBuildingDao.deleteBasicBuilding(basicApplyBatchDetail.getTableId());
                basicBuildingService.clearInvalidChildData(basicApplyBatchDetail.getTableId());
                List<BasicApplyBatchDetail> unitDetails = getBasicApplyBatchDetailByPid(id, basicApplyBatchDetail.getApplyBatchId());
                if (CollectionUtils.isNotEmpty(unitDetails)) {
                    for (BasicApplyBatchDetail unit : unitDetails) {
                        List<BasicApplyBatchDetail> houseDetails = getBasicApplyBatchDetailByPid(unit.getId(), basicApplyBatchDetail.getApplyBatchId());
                        if (CollectionUtils.isNotEmpty(houseDetails)) {
                            for (BasicApplyBatchDetail house : houseDetails) {
                                //删除房屋
                                basicHouseService.deleteHousesAndBasicApply(house.getTableId());
                                basicHouseTradingService.deleteBasicHouseTradingByHouseId(house.getTableId());
                                basicApplyBatchDetailDao.deleteInfo(house.getId());
                                //删除房屋子任务
                                deleteSonTask(applyBatch.getPlanDetailsId(), "house", house.getTableId());

                            }
                        }
                        //删除单元
                        basicApplyBatchDetailDao.deleteInfo(unit.getId());
                        basicUnitService.deleteBasicUnit(unit.getTableId());
                        basicUnitService.clearInvalidChildData(unit.getTableId());
                        //删除单元子任务
                        deleteSonTask(applyBatch.getPlanDetailsId(), "unit", unit.getTableId());
                    }
                }
                //删除楼栋子任务
                deleteSonTask(applyBatch.getPlanDetailsId(), "building", basicApplyBatchDetail.getTableId());
                break;
            case "tb_basic_unit":
                //删除原来单元数据
                basicUnitService.deleteBasicUnit(basicApplyBatchDetail.getTableId());
                basicUnitService.clearInvalidChildData(basicApplyBatchDetail.getTableId());
                List<BasicApplyBatchDetail> houseDetails = getBasicApplyBatchDetailByPid(id, basicApplyBatchDetail.getApplyBatchId());
                if (CollectionUtils.isNotEmpty(houseDetails)) {
                    for (BasicApplyBatchDetail house : houseDetails) {
                        //删除单元下房屋
                        basicHouseService.deleteHousesAndBasicApply(house.getTableId());
                        basicHouseTradingService.deleteBasicHouseTradingByHouseId(house.getTableId());
                        basicApplyBatchDetailDao.deleteInfo(house.getId());
                        //删除房屋子任务
                        deleteSonTask(applyBatch.getPlanDetailsId(), "house", house.getTableId());
                    }
                }
                //删除单元子任务
                deleteSonTask(applyBatch.getPlanDetailsId(), "unit", basicApplyBatchDetail.getTableId());
                break;
            case "tb_basic_house":
                //删除原来房屋数据
                basicHouseService.deleteHousesAndBasicApply(basicApplyBatchDetail.getTableId());
                basicHouseTradingService.deleteBasicHouseTradingByHouseId(basicApplyBatchDetail.getTableId());
                //删除房屋子任务
                deleteSonTask(applyBatch.getPlanDetailsId(), "house", basicApplyBatchDetail.getTableId());
                break;
        }
        basicApplyBatchDetailDao.deleteInfo(id);
    }

    //删除子任务
    public void deleteSonTask(Integer planDetailsId, String tableName, Integer tableId) {
        ProjectResponsibilityDto oldDto = new ProjectResponsibilityDto();
        oldDto.setPlanDetailsId(planDetailsId);
        oldDto.setBusinessId(tableId);
        oldDto.setBusinessKey(tableName);
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(oldDto);
        if (CollectionUtils.isNotEmpty(projectTaskList)) {
            projectTaskList.forEach(o -> bpmRpcProjectTaskService.deleteProjectTask(o.getId()));
        }
    }

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    public BasicApplyBatchDetail getDataById(Integer id) {
        return basicApplyBatchDetailDao.getInfoById(id);
    }

    public BasicApplyBatchDetailVo getBasicApplyBatchDetailVo(BasicApplyBatchDetail basicApplyBatchDetail) {
        BasicApplyBatchDetailVo vo = new BasicApplyBatchDetailVo();
        BeanUtils.copyProperties(basicApplyBatchDetail, vo);
        if (StringUtils.isNotEmpty(basicApplyBatchDetail.getExecutor())) {
            vo.setExecutorName(publicService.getUserNameByAccount(basicApplyBatchDetail.getExecutor()));
        }
        if (StringUtils.isNotEmpty(basicApplyBatchDetail.getCreator())) {
            vo.setCreatorName(publicService.getUserNameByAccount(basicApplyBatchDetail.getCreator()));
        }
        return vo;
    }

    public BasicApplyBatchDetail getBasicApplyBatchDetail(Integer basicApplyBatchId, String tableName, Integer tableId) {
        BasicApplyBatchDetail where = new BasicApplyBatchDetail();
        where.setApplyBatchId(basicApplyBatchId);
        where.setTableName(tableName);
        where.setTableId(tableId);
        return basicApplyBatchDetailDao.getBasicApplyBatchDetail(where);
    }


    //获取楼栋
    public List<BasicBuilding> getBuildingListByBatchId(Integer id) throws Exception {
        List<BasicApplyBatchDetail> infoList = this.getBuildingBatchDetailsByBatchId(id);
        List<BasicBuilding> basicBuildings = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(infoList))
            for (BasicApplyBatchDetail item : infoList) {
                BasicBuilding basicBuilding = basicBuildingDao.getBasicBuildingById(item.getTableId());
                if (basicBuilding != null) {
                    basicBuildings.add(basicBuildingDao.getBasicBuildingById(item.getTableId()));
                }
            }
        ;
        return basicBuildings;
    }

    public List<BasicApplyBatchDetail> getBuildingBatchDetailsByBatchId(Integer id) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableName("tb_basic_building");
        basicApplyBatchDetail.setApplyBatchId(id);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public List<BasicApplyBatchDetail> getBuildingBatchDetailsByBatchId2(Integer id) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setApplyBatchId(id);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }


    //获取单元
    public List<BasicUnit> getBasicUnitListByBatchId(Integer id, BasicBuilding basicBuilding) throws Exception {
        List<BasicApplyBatchDetail> infoList = this.getUnitBatchDetailsByBatchId(basicBuilding);
        List<BasicUnit> basicUnits = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(infoList))
            for (BasicApplyBatchDetail item : infoList) {
                basicUnits.add(basicUnitService.getBasicUnitById(item.getTableId()));
            }
        ;
        return basicUnits;
    }

    public List<BasicApplyBatchDetail> getUnitBatchDetailsByBatchId(BasicBuilding basicBuilding) {
        BasicApplyBatchDetail parent = getBasicApplyBatchDetail("tb_basic_building", basicBuilding.getId());
        if (parent == null) return null;
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableName("tb_basic_unit");
        basicApplyBatchDetail.setApplyBatchId(parent.getApplyBatchId());
        basicApplyBatchDetail.setPid(parent.getId());
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public BasicApplyBatchDetail getSingleData(BasicApplyBatchDetail basicApplyBatchDetail) {
        List<BasicApplyBatchDetail> infoList = basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(infoList)) return infoList.get(0);
        return null;
    }

    /**
     * 获取楼盘
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicEstate getBasicEstateByBatchDetailId(Integer basicApplyBatchDetailId) {
        BasicApplyBatchDetail applyBatchDetail = getDataById(basicApplyBatchDetailId);
        if (applyBatchDetail == null) return null;
        if (FormatUtils.entityNameConvertToTableName(BasicEstate.class).equalsIgnoreCase(applyBatchDetail.getTableName())) {
            return basicEstateService.getBasicEstateById(applyBatchDetail.getTableId());
        } else {
            if (applyBatchDetail.getPid() == null || applyBatchDetail.getPid() <= 0)
                return null;
            else
                return getBasicEstateByBatchDetailId(applyBatchDetail.getPid());
        }
    }

    /**
     * 获取楼栋
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicBuilding getBasicBuildingByBatchDetailId(Integer basicApplyBatchDetailId) {
        BasicApplyBatchDetail applyBatchDetail = getDataById(basicApplyBatchDetailId);
        if (applyBatchDetail == null) return null;
        if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equalsIgnoreCase(applyBatchDetail.getTableName())) {
            return basicBuildingService.getBasicBuildingById(applyBatchDetail.getTableId());
        } else {
            if (applyBatchDetail.getPid() == null || applyBatchDetail.getPid() <= 0)
                return null;
            else
                return getBasicBuildingByBatchDetailId(applyBatchDetail.getPid());
        }
    }

    /**
     * 获取单元
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicUnit getBasicUnitByBatchDetailId(Integer basicApplyBatchDetailId) {
        BasicApplyBatchDetail applyBatchDetail = getDataById(basicApplyBatchDetailId);
        if (applyBatchDetail == null) return null;
        if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equalsIgnoreCase(applyBatchDetail.getTableName())) {
            return basicUnitService.getBasicUnitById(applyBatchDetail.getTableId());
        } else {
            if (applyBatchDetail.getPid() == null || applyBatchDetail.getPid() <= 0)
                return null;
            else
                return getBasicUnitByBatchDetailId(applyBatchDetail.getPid());
        }
    }

}

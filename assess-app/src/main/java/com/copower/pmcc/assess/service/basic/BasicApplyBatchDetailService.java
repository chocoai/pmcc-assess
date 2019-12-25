package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.BasicApplyBatchDetailVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
        if (basicApplyBatchDetail.getId() != null && basicApplyBatchDetail.getId() > 0) {
            BasicApplyBatchDetail oldData = getDataById(basicApplyBatchDetail.getId());
            switch (basicApplyBatchDetail.getTableName()) {
                case "tb_basic_building":
                    basicApplyBatchDetail.setDisplayName(String.format("%s栋", basicApplyBatchDetail.getName()));
                    BasicBuilding building = basicBuildingDao.getBasicBuildingById(basicApplyBatchDetail.getTableId());
                    building.setBuildingNumber(basicApplyBatchDetail.getName());
                    building.setBuildingName(basicApplyBatchDetail.getDisplayName());
                    basicBuildingDao.updateBasicBuilding(building, false);
                    //执行人发生改变，子任务发生改变
                    if (!StringUtils.equals(oldData.getExecutor(), basicApplyBatchDetail.getExecutor())) {
                        updateSonTask(planDetailsId, "building", basicApplyBatchDetail.getTableId(), basicApplyBatchDetail.getExecutor());
                    }
                    break;
                case "tb_basic_unit":
                    basicApplyBatchDetail.setDisplayName(String.format("%s单元", basicApplyBatchDetail.getName()));
                    BasicUnit unit = basicUnitService.getBasicUnitById(basicApplyBatchDetail.getTableId());
                    unit.setUnitNumber(basicApplyBatchDetail.getName());
                    basicUnitService.saveAndUpdateBasicUnit(unit, false);
                    //执行人发生改变，子任务发生改变
                    if (!StringUtils.equals(oldData.getExecutor(), basicApplyBatchDetail.getExecutor())) {
                        updateSonTask(planDetailsId, "unit", basicApplyBatchDetail.getTableId(), basicApplyBatchDetail.getExecutor());
                    }
                    break;
                case "tb_basic_house":
                    BasicHouse house = basicHouseService.getBasicHouseById(basicApplyBatchDetail.getTableId());
                    house.setHouseNumber(basicApplyBatchDetail.getName());
                    basicHouseService.saveAndUpdateBasicHouse(house, false);
                    //执行人发生改变，子任务发生改变
                    if (!StringUtils.equals(oldData.getExecutor(), basicApplyBatchDetail.getExecutor())) {
                        updateSonTask(planDetailsId, "house", basicApplyBatchDetail.getTableId(), basicApplyBatchDetail.getExecutor());
                    }
                    break;
            }
            basicApplyBatchDetailDao.updateInfo(basicApplyBatchDetail);

        } else {
            basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
            switch (basicApplyBatchDetail.getTableName()) {
                case "tb_basic_building":
                    basicApplyBatchDetail.setDisplayName(String.format("%s栋", basicApplyBatchDetail.getName()));
                    BasicBuilding building = new BasicBuilding();
                    building.setBuildingNumber(basicApplyBatchDetail.getName());
                    if (basicApplyBatchDetail.getCaseTablePid() != null) {
                        building.setEstateId(0);
                    } else {
                        building.setEstateId(this.getParentTableId(basicApplyBatchDetail));
                    }
                    building.setBuildingName(basicApplyBatchDetail.getDisplayName());
                    basicBuildingDao.addBasicBuilding(building);
                    basicApplyBatchDetail.setTableId(building.getId());
                    //当前人与执行人不一致时发起子任务
                    if (!StringUtils.equals(basicApplyBatchDetail.getCreator(), basicApplyBatchDetail.getExecutor())) {
                        addSonTask(planDetailsId, "building", building.getId(), basicApplyBatchDetail.getExecutor());
                    }

                    break;
                case "tb_basic_unit":
                    basicApplyBatchDetail.setDisplayName(String.format("%s单元", basicApplyBatchDetail.getName()));
                    BasicUnit unit = new BasicUnit();
                    unit.setUnitNumber(basicApplyBatchDetail.getName());
                    if (basicApplyBatchDetail.getCaseTablePid() != null) {
                        unit.setBuildingId(0);
                    } else {
                        unit.setBuildingId(this.getParentTableId(basicApplyBatchDetail));
                    }
                    basicUnitService.saveAndUpdateBasicUnit(unit, false);
                    basicApplyBatchDetail.setTableId(unit.getId());
                    //当前人与执行人不一致时发起子任务
                    if (!StringUtils.equals(basicApplyBatchDetail.getCreator(), basicApplyBatchDetail.getExecutor())) {
                        addSonTask(planDetailsId, "unit", unit.getId(), basicApplyBatchDetail.getExecutor());
                    }
                    break;
                case "tb_basic_house":
                    BasicHouse house = new BasicHouse();
                    house.setHouseNumber(basicApplyBatchDetail.getName());
                    if (basicApplyBatchDetail.getCaseTablePid() != null) {
                        house.setUnitId(0);
                    } else {
                        house.setUnitId(this.getParentTableId(basicApplyBatchDetail));
                    }
                    //申报表代入的信息
                    DeclareRecord declareRecord = new DeclareRecord();
                    ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
                    if (projectPlanDetails != null && projectPlanDetails.getDeclareRecordId() != null) {
                        declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
                    }
                    if (declareRecord != null) {
                        house.setCertUse(declareRecord.getCertUse());
                        house.setPracticalUse(declareRecord.getCertUse());
                    }
                    basicHouseService.saveAndUpdateBasicHouse(house, false);
                    basicApplyBatchDetail.setTableId(house.getId());
                    BasicHouseTrading houseTrading = new BasicHouseTrading();
                    houseTrading.setHouseId(house.getId());
                    //引用一些项目信息
                    ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
                    if (planDetails != null) {
                        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(planDetails.getProjectId());
                        houseTrading.setScopeInclude(projectInfo.getScopeInclude());
                        houseTrading.setScopeNotInclude(projectInfo.getScopeNotInclude());
                        if (NumberUtils.isNumber(projectInfo.getPropertyScope())) {
                            houseTrading.setScopeProperty(Integer.valueOf(projectInfo.getPropertyScope()));
                        }
                    }
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(houseTrading, false);
                    //当前人与执行人不一致时发起子任务
                    if (!StringUtils.equals(basicApplyBatchDetail.getCreator(), basicApplyBatchDetail.getExecutor())) {
                        addSonTask(planDetailsId, "house", house.getId(), basicApplyBatchDetail.getExecutor());
                    }
                    break;
            }
            basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        }
        //存入basicApply
        this.insertBasicApply(basicApplyBatchDetail, planDetailsId);

        return basicApplyBatchDetail;
    }

    //添加子任务
    public void addSonTask(Integer planDetailsId, String key, Integer tableId, String executor) {
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
    public void updateSonTask(Integer planDetailsId, String key, Integer tableId, String executor) {
        ProjectResponsibilityDto oldDto = new ProjectResponsibilityDto();
        oldDto.setPlanDetailsId(planDetailsId);
        oldDto.setBusinessId(tableId);
        oldDto.setBusinessKey(key);
        ProjectResponsibilityDto oldTask = bpmRpcProjectTaskService.getProjectTask(oldDto);
        ProjectResponsibilityDto newDto = new ProjectResponsibilityDto();
        BeanUtils.copyProperties(oldTask, newDto, "userAccount", "gmtCreated", "gmtModified");
        newDto.setUserAccount(executor);
        bpmRpcProjectTaskService.updateProjectTask(newDto);
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
            basicApply.setBasicUnitId(unitBatchDetail.getTableId());
            basicApply.setUnitNumber(unitBatchDetail.getName());
            //楼栋
            BasicApplyBatchDetail buildBatchDetail = map.get(EstateTaggingTypeEnum.BUILDING);
            basicApply.setBasicBuildingId(buildBatchDetail.getTableId());
            basicApply.setBuildingNumber(buildBatchDetail.getName());
            //楼盘
            BasicApplyBatchDetail estateBatchDetail = map.get(EstateTaggingTypeEnum.ESTATE);
            basicApply.setBasicEstateId(estateBatchDetail.getTableId());
            basicApply.setPlanDetailsId(planDetailsId);
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
        map.put(EstateTaggingTypeEnum.UNIT, unitBatchDetail);
        BasicApplyBatchDetail buildBatchDetail = basicApplyBatchDetailDao.getInfoById(unitBatchDetail.getPid());
        map.put(EstateTaggingTypeEnum.BUILDING, buildBatchDetail);
        BasicApplyBatchDetail estateBatchDetail = basicApplyBatchDetailDao.getInfoById(buildBatchDetail.getPid());
        map.put(EstateTaggingTypeEnum.ESTATE, estateBatchDetail);
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
        ProjectResponsibilityDto sonTask = bpmRpcProjectTaskService.getProjectTask(oldDto);
        bpmRpcProjectTaskService.deleteProjectTask(sonTask.getId());
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
        return vo;
    }

    //获取上一级数据tableId
    public Integer getParentTableId(BasicApplyBatchDetail basicApplyBatchDetail) {
        if (basicApplyBatchDetail.getPid() != 0)
            return this.getDataById(basicApplyBatchDetail.getPid()).getTableId();
        return basicApplyBatchDao.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId()).getEstateId();
    }

    //获取上一级引用数据id
    public Integer getParentQuoteId(BasicApplyBatchDetail basicApplyBatchDetail) {
        if (basicApplyBatchDetail.getPid() != 0)
            return this.getDataById(basicApplyBatchDetail.getPid()).getQuoteId();
        return basicApplyBatchDao.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId()).getQuoteId();
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
        List<BasicApplyBatchDetail> infoList = this.getUnitBatchDetailsByBatchId(id, basicBuilding);
        List<BasicUnit> basicUnits = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(infoList))
            for (BasicApplyBatchDetail item : infoList) {
                basicUnits.add(basicUnitService.getBasicUnitById(item.getTableId()));
            }
        ;
        return basicUnits;
    }

    public List<BasicApplyBatchDetail> getUnitBatchDetailsByBatchId(Integer id, BasicBuilding basicBuilding) {
        BasicApplyBatchDetail parent = getBasicApplyBatchDetail("tb_basic_building", basicBuilding.getId());
        if (parent == null) return null;
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableName("tb_basic_unit");
        basicApplyBatchDetail.setApplyBatchId(id);
        basicApplyBatchDetail.setPid(parent.getId());
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }


    //获取房屋
    public List<BasicHouse> getBasicHouseListByBatchId(Integer id, BasicUnit basicUnit) throws Exception {
        List<BasicApplyBatchDetail> infoList = this.getHouseBatchDetailsByBatchId(id, basicUnit);
        List<BasicHouse> basicHouses = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(infoList))
            for (BasicApplyBatchDetail item : infoList) {
                basicHouses.add(basicHouseService.getBasicHouseById(item.getTableId()));
            }
        ;
        return basicHouses;
    }

    public List<BasicApplyBatchDetail> getHouseBatchDetailsByBatchId(Integer id, BasicUnit basicUnit) {
        BasicApplyBatchDetail parent = getBasicApplyBatchDetail("tb_basic_unit", basicUnit.getId());
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableName("tb_basic_house");
        basicApplyBatchDetail.setApplyBatchId(id);
        basicApplyBatchDetail.setPid(parent.getId());
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public BasicApplyBatchDetail getSingleData(BasicApplyBatchDetail basicApplyBatchDetail) {
        List<BasicApplyBatchDetail> infoList = basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(infoList)) return infoList.get(0);
        return null;
    }
}

package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            switch (basicApplyBatchDetail.getTableName()) {
                case "tb_basic_building":
                    basicApplyBatchDetail.setDisplayName(String.format("%s栋", basicApplyBatchDetail.getName()));
                    BasicBuilding building = basicBuildingDao.getBasicBuildingById(basicApplyBatchDetail.getTableId());
                    building.setBuildingNumber(basicApplyBatchDetail.getName());
                    building.setBuildingName(basicApplyBatchDetail.getDisplayName());
                    basicBuildingDao.updateBasicBuilding(building, false);
                    break;
                case "tb_basic_unit":
                    basicApplyBatchDetail.setDisplayName(String.format("%s单元", basicApplyBatchDetail.getName()));
                    BasicUnit unit = basicUnitService.getBasicUnitById(basicApplyBatchDetail.getTableId());
                    unit.setUnitNumber(basicApplyBatchDetail.getName());
                    basicUnitService.saveAndUpdateBasicUnit(unit, false);
                    break;
                case "tb_basic_house":
                    BasicHouse house = basicHouseService.getBasicHouseById(basicApplyBatchDetail.getTableId());
                    house.setHouseNumber(basicApplyBatchDetail.getName());
                    basicHouseService.saveAndUpdateBasicHouse(house, false);
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
                    break;
                case "tb_basic_house":
                    BasicHouse house = new BasicHouse();
                    house.setHouseNumber(basicApplyBatchDetail.getName());
                    if (basicApplyBatchDetail.getCaseTablePid() != null) {
                        house.setUnitId(0);
                    } else {
                        house.setUnitId(this.getParentTableId(basicApplyBatchDetail));
                    }
                    basicHouseService.saveAndUpdateBasicHouse(house, false);
                    basicApplyBatchDetail.setTableId(house.getId());
                    BasicHouseTrading houseTrading = new BasicHouseTrading();
                    houseTrading.setHouseId(house.getId());
                    //引用一些项目信息
                    ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
                    if(planDetails!=null){
                        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(planDetails.getProjectId());
                        houseTrading.setScopeInclude(projectInfo.getScopeInclude());
                        houseTrading.setScopeNotInclude(projectInfo.getScopeNotInclude());
                        houseTrading.setScopeProperty(Integer.valueOf(projectInfo.getPropertyScope()));
                    }
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(houseTrading, false);

                    break;
            }
            basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        }
        //若房屋设置为标准，则存入basicApply
        if (basicApplyBatchDetail.getBisStandard()) {
            this.standardIntoBasicApply(basicApplyBatchDetail, planDetailsId);
        }
        return basicApplyBatchDetail;
    }

    //存入basicApply表单
    public void standardIntoBasicApply(BasicApplyBatchDetail basicApplyBatchDetail, Integer planDetailsId) throws Exception {
        if (!basicApplyBatchDetail.getTableName().equals("tb_basic_house"))
            return;
        BasicApply where = new BasicApply();
        where.setPlanDetailsId(planDetailsId);
        where.setBasicHouseId(basicApplyBatchDetail.getTableId());
        BasicApply basicApplyOnly = basicApplyService.getBasicApplyOnly(where);
        if (basicApplyOnly == null && basicApplyBatchDetail.getBisStandard() == Boolean.TRUE) {
            BasicApply basicApply = new BasicApply();
            basicApply.setBasicHouseId(basicApplyBatchDetail.getTableId());
            basicApply.setHouseNumber(basicHouseService.getBasicHouseById(basicApplyBatchDetail.getTableId()).getHouseNumber());
            //单元
            BasicApplyBatchDetail unitBatchDetail = basicApplyBatchDetailDao.getInfoById(basicApplyBatchDetail.getPid());
            basicApply.setBasicUnitId(unitBatchDetail.getTableId());
            basicApply.setUnitNumber(basicUnitService.getBasicUnitById(unitBatchDetail.getTableId()).getUnitNumber());
            //楼栋
            BasicApplyBatchDetail buildBatchDetail = basicApplyBatchDetailDao.getInfoById(unitBatchDetail.getPid());
            basicApply.setBasicBuildingId(buildBatchDetail.getTableId());
            basicApply.setBuildingNumber(basicBuildingDao.getBasicBuildingById(buildBatchDetail.getTableId()).getBuildingNumber());
            //楼盘id
            BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
            basicApply.setBasicEstateId(applyBatch.getEstateId());
            basicApply.setType(applyBatch.getType());
            basicApply.setPlanDetailsId(planDetailsId);
            basicApplyService.saveBasicApply(basicApply);
        }
        if (basicApplyOnly != null && basicApplyBatchDetail.getBisStandard() == Boolean.FALSE) {
            basicApplyDao.deleteBasicApply(basicApplyOnly.getId());//取消标准时，删除原来的数据
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
     * 删除
     *
     * @param id
     * @return
     */
    public void deleteBasicApplyBatchDetail(Integer id) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(id);
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
                                basicApplyBatchDetailDao.deleteInfo(house.getId());
                            }
                        }
                        basicApplyBatchDetailDao.deleteInfo(unit.getId());
                        //删除房屋
                        basicHouseService.deleteHousesByUnitId(unit.getTableId());
                        //删除单元
                        basicUnitService.deleteBasicUnit(unit.getTableId());
                        basicUnitService.clearInvalidChildData(unit.getTableId());
                    }
                }
                break;
            case "tb_basic_unit":
                //删除原来单元数据
                basicUnitService.deleteBasicUnit(basicApplyBatchDetail.getTableId());
                basicUnitService.clearInvalidChildData(basicApplyBatchDetail.getTableId());
                //删除单元下房屋
                basicHouseService.deleteHousesByUnitId(basicApplyBatchDetail.getTableId());
                List<BasicApplyBatchDetail> houseDetails = getBasicApplyBatchDetailByPid(id, basicApplyBatchDetail.getApplyBatchId());
                if (CollectionUtils.isNotEmpty(houseDetails)) {
                    for (BasicApplyBatchDetail house : houseDetails) {
                        basicApplyBatchDetailDao.deleteInfo(house.getId());
                    }
                }
                break;
            case "tb_basic_house":
                //删除原来房屋数据
                basicHouseService.deleteBasicHouse(basicApplyBatchDetail.getTableId());
                basicHouseTradingService.deleteBasicHouseTradingByHouseId(basicApplyBatchDetail.getTableId());
                basicHouseService.clearInvalidChildData(basicApplyBatchDetail.getTableId());
                BasicApply basicApply = new BasicApply();
                basicApply.setBasicHouseId(basicApplyBatchDetail.getTableId());
                BasicApply basicApplyOnly = basicApplyService.getBasicApplyOnly(basicApply);
                //删除标准时，删除原来的数据
                if (basicApplyOnly != null) {
                    basicApplyDao.deleteBasicApply(basicApplyOnly.getId());
                }
                break;
        }
        basicApplyBatchDetailDao.deleteInfo(id);
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

    public List<BasicApplyBatchDetail> getBuildingBatchDetailsByBatchId(Integer id) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableName("tb_basic_building");
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

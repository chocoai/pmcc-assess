package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
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

    /**
     * 新增
     *
     * @param basicApplyBatchDetail
     * @return
     */
    public BasicApplyBatchDetail addBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail, Integer type) throws Exception {
        basicApplyBatchDetail.setDisplayName(basicApplyBatchDetail.getName());
        if (basicApplyBatchDetail.getId() != null && basicApplyBatchDetail.getId() > 0) {
            switch (basicApplyBatchDetail.getTableName()) {
                case "tb_basic_building":
                    basicApplyBatchDetail.setDisplayName(String.format("%s栋", basicApplyBatchDetail.getName()));
                    BasicBuilding building = basicBuildingDao.getBasicBuildingById(basicApplyBatchDetail.getTableId());
                    building.setBuildingNumber(basicApplyBatchDetail.getName());
                    building.setBuildingName(basicApplyBatchDetail.getDisplayName());
                    basicBuildingDao.updateBasicBuilding(building);
                    break;
                case "tb_basic_unit":
                    basicApplyBatchDetail.setDisplayName(String.format("%s单元", basicApplyBatchDetail.getName()));
                    BasicUnit unit = basicUnitService.getBasicUnitById(basicApplyBatchDetail.getTableId());
                    unit.setUnitNumber(basicApplyBatchDetail.getName());
                    basicUnitService.saveAndUpdateBasicUnit(unit);
                    break;
                case "tb_basic_house":
                    BasicHouse house = basicHouseService.getBasicHouseById(basicApplyBatchDetail.getTableId());
                    house.setHouseNumber(basicApplyBatchDetail.getName());
                    basicHouseService.saveAndUpdateBasicHouse(house);
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
                    building.setEstateId(this.getParentTableId(basicApplyBatchDetail));
                    building.setBuildingName(basicApplyBatchDetail.getDisplayName());
                    basicBuildingDao.addBasicBuilding(building);
                    basicApplyBatchDetail.setTableId(building.getId());
                    break;
                case "tb_basic_unit":
                    basicApplyBatchDetail.setDisplayName(String.format("%s单元", basicApplyBatchDetail.getName()));
                    BasicUnit unit = new BasicUnit();
                    unit.setUnitNumber(basicApplyBatchDetail.getName());
                    unit.setBuildingId(this.getParentTableId(basicApplyBatchDetail));
                    basicUnitService.saveAndUpdateBasicUnit(unit);
                    basicApplyBatchDetail.setTableId(unit.getId());
                    if (type != null && type.equals(3)) {
                        BasicHouse house = new BasicHouse();
                        house.setUnitId(unit.getId());
                        basicHouseService.saveAndUpdateBasicHouse(house);
                        BasicHouseTrading houseTrading = new BasicHouseTrading();
                        houseTrading.setHouseId(house.getId());
                        basicHouseTradingService.saveAndUpdateBasicHouseTrading(houseTrading);
                    }
                    break;
                case "tb_basic_house":
                    BasicHouse house = new BasicHouse();
                    house.setHouseNumber(basicApplyBatchDetail.getName());
                    house.setUnitId(this.getParentTableId(basicApplyBatchDetail));
                    basicHouseService.saveAndUpdateBasicHouse(house);
                    basicApplyBatchDetail.setTableId(house.getId());
                    BasicHouseTrading houseTrading = new BasicHouseTrading();
                    houseTrading.setHouseId(house.getId());
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(houseTrading);
                    break;
            }
            basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        }
        return basicApplyBatchDetail;
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
    public boolean deleteBasicApplyBatchDetail(Integer id) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(id);
        switch (basicApplyBatchDetail.getTableName()) {
            case "tb_basic_building":
                basicBuildingDao.deleteBasicBuilding(basicApplyBatchDetail.getTableId());
                break;
            case "tb_basic_unit":
                basicUnitService.deleteBasicUnit(basicApplyBatchDetail.getTableId());
                break;
            case "tb_basic_house":
                basicHouseService.deleteBasicHouse(basicApplyBatchDetail.getTableId());
                break;
        }
        return basicApplyBatchDetailDao.deleteInfo(id);
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
        return basicApplyBatchDao.getInfoById(basicApplyBatchDetail.getApplyBatchId()).getEstateId();
    }

    //获取楼栋
    public List<BasicBuilding> getBuildingListByBatchId(Integer id) throws Exception {
        List<BasicApplyBatchDetail> infoList = this.getBuildingBatchDetailsByBatchId(id);
        List<BasicBuilding> basicBuildings = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(infoList))
            for (BasicApplyBatchDetail item : infoList) {
                basicBuildings.add(basicBuildingDao.getBasicBuildingById(item.getTableId()));
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


    //获取房屋交易信息
    public List<BasicHouseTrading> getBasicHouseTradingsByHouses(List<BasicHouse> houses) throws Exception {
        List<BasicHouseTrading> basicHouseTradings = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(houses))
            for (BasicHouse item : houses) {
                basicHouseTradings.add(basicHouseTradingService.getTradingByHouseId(item.getId()));
            }
        ;
        return basicHouseTradings;
    }

    public BasicApplyBatchDetail getSingleData(BasicApplyBatchDetail basicApplyBatchDetail) {
        List<BasicApplyBatchDetail> infoList = basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(infoList)) return infoList.get(0);
        return null;
    }
}

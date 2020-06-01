package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kings on 2018-10-30
 */
@Component
public class BasicApplyBatchEvent extends BaseProcessEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        //当楼盘为新增时
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        if (applyBatch.getCaseEstateId() == 0) {
            //当楼盘为新增时
            applyBatch.setBisCase(true);
            basicApplyBatchDao.updateInfo(applyBatch);
            BasicEstate basicEstate = basicEstateService.getBasicEstateById(applyBatch.getEstateId());
            basicEstate.setBisCase(true);
            basicEstateDao.updateBasicEstate(basicEstate, false);
            //楼栋
            List<BasicApplyBatchDetail> details = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
            List<BasicApplyBatchDetail> buildingList = LangUtils.filter(details, d -> d.getType().startsWith(BasicFormClassifyEnum.BUILDING.getKey()));
            if (CollectionUtils.isNotEmpty(buildingList)) {
                for (BasicApplyBatchDetail buildItem : buildingList) {
                    BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingById(buildItem.getTableId());
                    basicBuilding.setEstateId(basicEstate.getId());
                    basicBuilding.setBisCase(true);
                    basicBuildingDao.updateBasicBuilding(basicBuilding, false);
                    //单元
                    List<BasicApplyBatchDetail> unitList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(buildItem.getId(), applyBatch.getId());
                    if (CollectionUtils.isNotEmpty(unitList)) {
                        for (BasicApplyBatchDetail unitItem : unitList) {
                            if (unitItem.getType().startsWith(BasicFormClassifyEnum.UNIT.getKey())) {
                                BasicUnit basicUnit = basicUnitService.getBasicUnitById(unitItem.getTableId());
                                basicUnit.setBuildingId(buildItem.getTableId());
                                basicUnit.setBisCase(true);
                                basicUnitDao.updateBasicUnit(basicUnit, false);
                                //房屋
                                List<BasicApplyBatchDetail> houseList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(unitItem.getId(), applyBatch.getId());
                                if (CollectionUtils.isNotEmpty(houseList)) {
                                    for (BasicApplyBatchDetail houseItem : houseList) {
                                        if (houseItem.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
                                            BasicHouse basicHouse = basicHouseService.getBasicHouseById(houseItem.getTableId());
                                            basicHouse.setUnitId(unitItem.getId());
                                            basicHouse.setBisCase(true);
                                            basicHouseDao.updateBasicHouse(basicHouse, false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else {
            //案列新增或升级 过滤数据
            List<BasicApplyBatchDetail> details = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
            //升级数据
            List<BasicApplyBatchDetail> upgradelList = LangUtils.filter(details, d -> {
                return d.getBisFromCase() == false && d.getUpgradeTableId() != 0;
            });
            if (CollectionUtils.isNotEmpty(upgradelList)) {
                for (BasicApplyBatchDetail sonDetail : upgradelList) {
                    if (StringUtils.equals(sonDetail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                        BasicApplyBatchDetail oldBuilding = basicApplyBatchDetailService.getBasicApplyBatchDetail(sonDetail.getTableName(), sonDetail.getUpgradeTableId());
                        BasicBuilding oldBasicBuilding = basicBuildingService.getBasicBuildingById(oldBuilding.getTableId());
                        BasicBuilding newBasicBuilding = basicBuildingService.getBasicBuildingById(sonDetail.getTableId());
                        newBasicBuilding.setBisCase(true);
                        newBasicBuilding.setVersion(oldBasicBuilding.getVersion() + 1);
                        basicBuildingDao.updateBasicBuilding(newBasicBuilding, false);
                        //被升级数据更改tableId
                        oldBuilding.setTableId(sonDetail.getTableId());
                        basicApplyBatchDetailDao.updateInfo(oldBuilding);

                    }
                    if (StringUtils.equals(sonDetail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                        BasicApplyBatchDetail oldBuilding = basicApplyBatchDetailService.getBasicApplyBatchDetail(sonDetail.getTableName(), sonDetail.getUpgradeTableId());
                        BasicUnit oldBasicUnit = basicUnitService.getBasicUnitById(oldBuilding.getTableId());
                        BasicUnit newBasicUnit = basicUnitService.getBasicUnitById(sonDetail.getTableId());
                        newBasicUnit.setBisCase(true);
                        newBasicUnit.setVersion(oldBasicUnit.getVersion() + 1);
                        basicUnitDao.updateBasicUnit(newBasicUnit, false);
                        //被升级数据更改tableId
                        oldBuilding.setTableId(sonDetail.getTableId());
                        basicApplyBatchDetailDao.updateInfo(oldBuilding);

                    }
                    if (StringUtils.equals(sonDetail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                        BasicApplyBatchDetail oldBuilding = basicApplyBatchDetailService.getBasicApplyBatchDetail(sonDetail.getTableName(), sonDetail.getUpgradeTableId());
                        BasicHouse oldBasicHouse = basicHouseDao.getBasicHouseById(oldBuilding.getTableId());
                        BasicHouse newBasicHouse = basicHouseDao.getBasicHouseById(sonDetail.getTableId());
                        newBasicHouse.setBisCase(true);
                        newBasicHouse.setVersion(oldBasicHouse.getVersion() + 1);
                        basicHouseDao.updateBasicHouse(newBasicHouse, false);
                        //被升级数据更改tableId
                        oldBuilding.setTableId(sonDetail.getTableId());
                        basicApplyBatchDetailDao.updateInfo(oldBuilding);

                    }

                }

            }
            //新增数据
            List<BasicApplyBatchDetail> addList = LangUtils.filter(details, d -> {
                return d.getBisFromCase() == false && d.getUpgradeTableId() == 0;
            });
            for (BasicApplyBatchDetail item:addList){
                BasicApplyBatchDetail applyBatchDetailParent = basicApplyBatchDetailDao.getInfoById(item.getPid());
                //新增数据第一个节点
                if(applyBatchDetailParent.getBisFromCase()==true||applyBatchDetailParent.getUpgradeTableId()!=0){
                    BasicApplyBatchDetail caseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchDetailParent.getTableName(), applyBatchDetailParent.getTableId());
                    BasicApplyBatchDetail source = basicApplyBatchDetailService.getDataById(item.getId());
                    BasicApplyBatchDetail target = new BasicApplyBatchDetail();
                    BeanUtils.copyProperties(source,target);
                    target.setPid(caseDetail.getId());
                    target.setApplyBatchId(caseDetail.getApplyBatchId());
                    basicApplyBatchDetailDao.addInfo(target);
                    basicApplyBatchDetailService.copyNode(source.getId(),source.getApplyBatchId(),target.getId(),target.getApplyBatchId());
                }
            }
        }
    }



}

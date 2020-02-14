package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        writeToCase(applyBatch);
    }

    /**
     * 数据写入到案例库
     *
     * @param applyBatch
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void writeToCase(BasicApplyBatch applyBatch) throws Exception {
        BasicEstate basicEstate;
        BasicBuilding basicBuilding;
        BasicUnit basicUnit;
        BasicHouse basicHouse;
        if (applyBatch.getCaseEstateId() != null && applyBatch.getCaseEstateId() > 0) {
            //1.找出新增或升级的数据 2.如果为新增则将相关数据挂到对于的案例数据下
            //3.如果为升级数据将原数据存为老版本，将新数据更新过去并更新版本号
            BasicApplyBatchDetail where = new BasicApplyBatchDetail();
            where.setBisFromCase(false);
            where.setApplyBatchId(applyBatch.getId());
            List<BasicApplyBatchDetail> list = basicApplyBatchDetailService.getBasicApplyBatchDetailList(where);
            if (CollectionUtils.isNotEmpty(list)) {
                List<String> ignoreList= Lists.newArrayList("version");
                for (BasicApplyBatchDetail batchDetail : list) {
                    try{
                        if (batchDetail.getUpgradeTableId() != null && batchDetail.getUpgradeTableId() > 0) {//升级
                            if (FormatUtils.entityNameConvertToTableName(BasicEstate.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicEstateService.copyBasicEstate(batchDetail.getUpgradeTableId(),null,true);
                                basicEstate = basicEstateService.copyBasicEstateIgnore(batchDetail.getTableId(), batchDetail.getUpgradeTableId(), true,ignoreList);
                                basicEstate.setVersion(basicEstate.getVersion()+1);
                                basicEstate.setBisCase(true);
                                basicEstate.setCreator(batchDetail.getCreator());
                                basicEstate.setGmtCreated(DateUtils.now());
                                basicEstateService.saveAndUpdateBasicEstate(basicEstate,false);
                            } else if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicBuildingService.copyBasicBuilding(batchDetail.getUpgradeTableId(),null,true);
                                basicBuilding = basicBuildingService.copyBasicBuildingIgnore(batchDetail.getTableId(), batchDetail.getUpgradeTableId(), true,ignoreList);
                                basicBuilding.setVersion(basicBuilding.getVersion()+1);
                                basicBuilding.setBisCase(true);
                                basicBuilding.setCreator(batchDetail.getCreator());
                                basicBuilding.setGmtCreated(DateUtils.now());
                                basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding,false);
                            } else if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicUnitService.copyBasicUnit(batchDetail.getUpgradeTableId(),null,true);
                                basicUnit = basicUnitService.copyBasicUnitIgnore(batchDetail.getTableId(), batchDetail.getUpgradeTableId(), true,ignoreList);
                                basicUnit.setVersion(basicUnit.getVersion()+1);
                                basicUnit.setBisCase(true);
                                basicUnit.setCreator(batchDetail.getCreator());
                                basicUnit.setGmtCreated(DateUtils.now());
                                basicUnitService.saveAndUpdateBasicUnit(basicUnit,false);
                            } else if (FormatUtils.entityNameConvertToTableName(BasicHouse.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicHouseService.copyBasicHouse(batchDetail.getUpgradeTableId(),null,true);
                                basicHouse = basicHouseService.copyBasicHouseIgnore(batchDetail.getTableId(), batchDetail.getUpgradeTableId(), true,ignoreList);
                                basicHouse.setVersion(basicHouse.getVersion()+1);
                                basicHouse.setBisCase(true);
                                basicHouse.setCreator(batchDetail.getCreator());
                                basicHouse.setGmtCreated(DateUtils.now());
                                basicHouseService.saveAndUpdateBasicHouse(basicHouse,false);
                            }
                        } else {//新增
                            if (FormatUtils.entityNameConvertToTableName(BasicEstate.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicEstate = basicEstateService.getBasicEstateById(batchDetail.getTableId());
                                if (basicEstate != null) {
                                    basicEstate.setBisCase(true);
                                    basicEstate.setVersion(1);
                                    basicEstateService.saveAndUpdateBasicEstate(basicEstate, false);
                                }
                            } else if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicBuilding = basicBuildingService.getBasicBuildingById(batchDetail.getTableId());
                                if (basicBuilding != null) {
                                    basicEstate = basicApplyBatchDetailService.getBasicEstateByBatchDetailId(batchDetail.getPid());
                                    if (basicEstate != null) {
                                        basicBuilding.setEstateId(basicEstate.getId());
                                    }
                                    basicBuilding.setBisCase(true);
                                    basicBuilding.setVersion(1);
                                    basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, false);
                                }
                            } else if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicUnit = basicUnitService.getBasicUnitById(batchDetail.getTableId());
                                if (basicUnit != null) {
                                    basicBuilding = basicApplyBatchDetailService.getBasicBuildingByBatchDetailId(batchDetail.getPid());
                                    if (basicBuilding != null) {
                                        basicUnit.setBuildingId(basicBuilding.getId());
                                        basicUnit.setEstateId(basicBuilding.getEstateId());
                                    }
                                    basicUnit.setBisCase(true);
                                    basicUnit.setVersion(1);
                                    basicUnitService.saveAndUpdateBasicUnit(basicUnit, false);
                                }
                            } else if (FormatUtils.entityNameConvertToTableName(BasicHouse.class).equalsIgnoreCase(batchDetail.getTableName())) {
                                basicHouse = basicHouseService.getBasicHouseById(batchDetail.getTableId());
                                if (basicHouse != null) {
                                    basicUnit = basicApplyBatchDetailService.getBasicUnitByBatchDetailId(batchDetail.getPid());
                                    if (basicUnit != null) {
                                        basicHouse.setUnitId(basicUnit.getId());
                                        basicHouse.setBuildingId(basicUnit.getBuildingId());
                                        basicHouse.setEstateId(basicUnit.getEstateId());
                                    }
                                    basicHouse.setBisCase(true);
                                    basicHouse.setVersion(1);
                                    basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
                                }
                            }
                        }
                    }catch (Exception e){

                    }
                }
            }

        } else {
            //1.直接将相关联的数据转换为案例数据
            List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
            if (CollectionUtils.isNotEmpty(batchDetailList)) {
                for (BasicApplyBatchDetail batchDetail : batchDetailList) {
                    if (FormatUtils.entityNameConvertToTableName(BasicEstate.class).equalsIgnoreCase(batchDetail.getTableName())) {
                        basicEstate = basicEstateService.getBasicEstateById(batchDetail.getTableId());
                        if (basicEstate != null) {
                            basicEstate.setBisCase(true);
                            basicEstate.setVersion(1);
                            basicEstateService.saveAndUpdateBasicEstate(basicEstate, false);
                        }
                    } else if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equalsIgnoreCase(batchDetail.getTableName())) {
                        basicBuilding = basicBuildingService.getBasicBuildingById(batchDetail.getTableId());
                        if (basicBuilding != null) {
                            basicBuilding.setBisCase(true);
                            basicBuilding.setVersion(1);
                            basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, false);
                        }
                    } else if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equalsIgnoreCase(batchDetail.getTableName())) {
                        basicUnit = basicUnitService.getBasicUnitById(batchDetail.getTableId());
                        if (basicUnit != null) {
                            basicUnit.setBisCase(true);
                            basicUnit.setVersion(1);
                            basicUnitService.saveAndUpdateBasicUnit(basicUnit, false);
                        }
                    } else if (FormatUtils.entityNameConvertToTableName(BasicHouse.class).equalsIgnoreCase(batchDetail.getTableName())) {
                        basicHouse = basicHouseService.getBasicHouseById(batchDetail.getTableId());
                        if (basicHouse != null) {
                            basicHouse.setBisCase(true);
                            basicHouse.setVersion(1);
                            basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
                        }
                    }
                }
            }
        }
    }

}

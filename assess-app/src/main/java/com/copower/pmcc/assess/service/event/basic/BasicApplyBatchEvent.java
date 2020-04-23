package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.utils.DateUtils;
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
    private PublicBasicService publicBasicService;
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


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        //当楼盘为新增时
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        applyBatch.setBisCase(true);
        basicApplyBatchDao.updateInfo(applyBatch);
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(applyBatch.getEstateId());
        basicEstate.setBisCase(true);
        basicEstateDao.updateBasicEstate(basicEstate,false);
    }

    /**
     * 数据写入到案例库
     *
     * @param applyBatch
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void writeToCase(BasicApplyBatch applyBatch) throws Exception {
        //找出有效数据，每条有效数据处理时，查看案例是否有相关数据，有则升级版本，没有则直接作为新增
        BasicApplyBatchDetail where = new BasicApplyBatchDetail();
        where.setBisFromCase(false);
        where.setApplyBatchId(applyBatch.getId());
        List<BasicApplyBatchDetail> list = basicApplyBatchDetailService.getBasicApplyBatchDetailList(where);
        if (CollectionUtils.isNotEmpty(list)) {
            BasicEstate latestVersionEstate = basicEstateDao.getLatestVersionEstate(applyBatch.getProvince(), applyBatch.getCity(), applyBatch.getEstateName());//确定楼盘案例库是否已存在
            List<String> ignoreList = Lists.newArrayList("version");
            for (BasicApplyBatchDetail batchDetail : list) {
                BasicFormClassifyEnum anEnum = BasicFormClassifyEnum.getEnumByTableName(batchDetail.getTableName());
                BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(batchDetail.getTableName());
                switch (anEnum) {
                    case ESTATE:
                        BasicEstate basicEstate = basicEstateDao.getBasicEstateById(batchDetail.getTableId());
                        if (latestVersionEstate != null) {//表示需要升级，先将案例库中最新版本拷贝一份存档，再将本次申请的新版本存入上个版本原id数据中，保证如楼栋等从数据关联无影响
                            entityAbstract.copyBasicEntity(latestVersionEstate.getId(), null, true);
                            basicEstate = (BasicEstate) entityAbstract.copyBasicEntityIgnore(batchDetail.getTableId(), latestVersionEstate.getId(), true, ignoreList);
                            basicEstate.setVersion(latestVersionEstate.getVersion() + 1);
                            basicEstate.setCreator(batchDetail.getCreator());
                            basicEstate.setGmtCreated(DateUtils.now());
                        }
                        basicEstate.setVersion(basicEstate.getVersion() == null ? 1 : basicEstate.getVersion());
                        basicEstate.setBisCase(true);
                        basicEstateDao.updateBasicEstate(basicEstate, false);
                        break;
                    case BUILDING:
                        BasicBuilding basicBuilding = basicBuildingDao.getBasicBuildingById(batchDetail.getTableId());
                        if (latestVersionEstate != null) {
                            BasicBuilding latestVersionBuilding = basicBuildingDao.getLatestVersionBuildingByFullName(basicBuilding.getFullName(), latestVersionEstate.getId());
                            if (latestVersionBuilding != null) {//表示需要升级，先将案例库中最新版本拷贝一份存档，再将本次申请的新版本存入上个版本原id数据中，保证如楼栋等从数据关联无影响
                                entityAbstract.copyBasicEntity(latestVersionBuilding.getId(), null, true);
                                basicBuilding = (BasicBuilding) entityAbstract.copyBasicEntityIgnore(batchDetail.getTableId(), latestVersionBuilding.getId(), true, ignoreList);
                                basicBuilding.setVersion(latestVersionBuilding.getVersion() + 1);
                                basicBuilding.setCreator(batchDetail.getCreator());
                                basicBuilding.setGmtCreated(DateUtils.now());
                            }
                        }
                        basicBuilding.setVersion(basicBuilding.getVersion() == null ? 1 : basicBuilding.getVersion());
                        basicBuilding.setBisCase(true);
                        basicBuildingDao.updateBasicBuilding(basicBuilding, false);
                        break;
                    case UNIT:
                        BasicUnit basicUnit = basicUnitDao.getBasicUnitById(batchDetail.getTableId());
                        if (latestVersionEstate != null) {
                            BasicUnit latestVersionUnit = basicUnitDao.getLatestVersionUnitByFullName(basicUnit.getFullName(), latestVersionEstate.getId());
                            if (latestVersionUnit != null) {//表示需要升级，先将案例库中最新版本拷贝一份存档，再将本次申请的新版本存入上个版本原id数据中，保证如楼栋等从数据关联无影响
                                entityAbstract.copyBasicEntity(latestVersionUnit.getId(), null, true);
                                basicUnit = (BasicUnit) entityAbstract.copyBasicEntityIgnore(batchDetail.getTableId(), latestVersionUnit.getId(), true, ignoreList);
                                basicUnit.setVersion(latestVersionUnit.getVersion() + 1);
                                basicUnit.setCreator(batchDetail.getCreator());
                                basicUnit.setGmtCreated(DateUtils.now());
                            }
                        }
                        basicUnit.setVersion(basicUnit.getVersion() == null ? 1 : basicUnit.getVersion());
                        basicUnit.setBisCase(true);
                        basicUnitDao.updateBasicUnit(basicUnit, false);
                        break;
                    case HOUSE:
                        BasicHouse basicHouse = basicHouseDao.getBasicHouseById(batchDetail.getTableId());
                        if (latestVersionEstate != null) {
                            BasicHouse latestVersionHouse = basicHouseDao.getLatestVersionHouseByFullName(basicHouse.getFullName(), latestVersionEstate.getId());
                            if (latestVersionHouse != null) {//表示需要升级，先将案例库中最新版本拷贝一份存档，再将本次申请的新版本存入上个版本原id数据中，保证如楼栋等从数据关联无影响
                                entityAbstract.copyBasicEntity(latestVersionHouse.getId(), null, true);
                                basicHouse = (BasicHouse) entityAbstract.copyBasicEntityIgnore(batchDetail.getTableId(), latestVersionHouse.getId(), true, ignoreList);
                                basicHouse.setVersion(latestVersionHouse.getVersion() + 1);
                                basicHouse.setCreator(batchDetail.getCreator());
                                basicHouse.setGmtCreated(DateUtils.now());
                            }
                        }
                        basicHouse.setVersion(basicHouse.getVersion() == null ? 1 : basicHouse.getVersion());
                        basicHouse.setBisCase(true);
                        basicHouseDao.updateBasicHouse(basicHouse, false);
                        break;
                }
            }
        }
    }

}

package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.basic.*;
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
        if (applyBatch.getCaseEstateId() != null && applyBatch.getCaseEstateId() > 0) {
            //1.找出新增或升级的数据 2.如果为新增则将相关数据挂到对于的案例数据下
            //3.如果为升级数据将原数据存为老版本，将新数据更新过去并更新版本号
            BasicApplyBatchDetail where = new BasicApplyBatchDetail();
            where.setBisFromCase(false);
            where.setApplyBatchId(applyBatch.getId());
            List<BasicApplyBatchDetail> list = basicApplyBatchDetailService.getBasicApplyBatchDetailList(where);
            if (CollectionUtils.isNotEmpty(list)) {
                List<String> ignoreList = Lists.newArrayList("version");
                for (BasicApplyBatchDetail batchDetail : list) {
                    try {
                        if (batchDetail.getUpgradeTableId() != null && batchDetail.getUpgradeTableId() > 0) {//升级
                            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(batchDetail.getTableName());
                            entityAbstract.copyBasicEntity(batchDetail.getUpgradeTableId(), null, true);
                            Object o = entityAbstract.copyBasicEntityIgnore(batchDetail.getTableId(), batchDetail.getUpgradeTableId(), true, ignoreList);
                            entityAbstract.setProperty(o,"bisCase",true);
                            entityAbstract.setProperty(o,"creator",batchDetail.getCreator());
                            entityAbstract.setProperty(o,"gmtCreated",DateUtils.now());
                            entityAbstract.setProperty(o,"version",((Integer)entityAbstract.getProperty(o,"version")+1));
                            entityAbstract.saveAndUpdate(o,false);
                            basicEstateService.saveAndUpdate(o, false);
                        } else {//新增
                            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(batchDetail.getTableName());
                            Object o = entityAbstract.getBasicEntityById(batchDetail.getTableId());
                            if (o != null) {
                                entityAbstract.setProperty(o, "bisCase", true);
                                entityAbstract.setProperty(o, "version", 1);
                                if (batchDetail.getPid() != null && batchDetail.getPid() > 0) {
                                    BasicApplyBatchDetail parent = basicApplyBatchDetailService.getDataById(batchDetail.getPid());
                                    BasicEntityAbstract parentEntityAbstract = publicBasicService.getServiceBeanByTableName(parent.getTableName());
                                    Object parentObject = parentEntityAbstract.getBasicEntityById(parent.getTableId());
                                    if (parentObject != null) {
                                        Object estateId = parentEntityAbstract.getProperty(parentObject, "estateId");
                                        if (estateId == null && parentObject instanceof BasicEstate) {
                                            estateId = parentEntityAbstract.getProperty(parentObject, "id");
                                        }
                                        Object buldingId = parentEntityAbstract.getProperty(parentObject, "buldingId");
                                        if (buldingId == null && parentObject instanceof BasicBuilding) {
                                            buldingId = parentEntityAbstract.getProperty(parentObject, "id");
                                        }
                                        Object unitId = parentEntityAbstract.getProperty(parentObject, "unitId");
                                        if (unitId == null && parentObject instanceof BasicUnit) {
                                            unitId = parentEntityAbstract.getProperty(parentObject, "id");
                                        }
                                        entityAbstract.setProperty(o, "estateId", estateId);
                                        entityAbstract.setProperty(o, "buldingId", buldingId);
                                        entityAbstract.setProperty(o, "unitId", unitId);
                                    }
                                }
                                entityAbstract.saveAndUpdate(o, false);
                            }
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(),e);
                    }
                }
            }
        } else {
            //1.直接将相关联的数据转换为案例数据
            List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
            if (CollectionUtils.isNotEmpty(batchDetailList)) {
                for (BasicApplyBatchDetail batchDetail : batchDetailList) {
                    BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(batchDetail.getTableName());
                    Object o = entityAbstract.getBasicEntityById(batchDetail.getTableId());
                    if (o != null) {
                        entityAbstract.setProperty(o, "bisCase", true);
                        entityAbstract.setProperty(o, "version", 1);
                        entityAbstract.saveAndUpdate(o, false);
                    }
                }
            }
        }
    }

}

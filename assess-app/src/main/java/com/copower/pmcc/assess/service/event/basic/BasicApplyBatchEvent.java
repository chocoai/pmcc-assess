package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private PublicBasicService publicBasicService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        recordToCase(processExecution);
    }

    @Transactional(rollbackFor = Exception.class)
    public void recordToCase(ProcessExecution processExecution) {
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        List<BasicApplyBatchDetail> details = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
        if (applyBatch.getCaseEstateId() == null || applyBatch.getCaseEstateId() == 0) { //楼盘为全新楼盘
            applyBatch.setBisCase(true);
            basicApplyBatchDao.updateInfo(applyBatch);
            //将从数据也设置为案例数据
            if (CollectionUtils.isNotEmpty(details)) {
                for (BasicApplyBatchDetail detail : details) {
                    BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(detail.getType());
                    Object entity = anAbstract.getBasicEntityById(detail.getTableId());
                    if (entity != null) {
                        anAbstract.setProperty(entity, "bisCase", true);
                        anAbstract.setProperty(entity, "version", 1);
                        anAbstract.saveAndUpdate(entity, false);
                    }
                }
            }
        } else {//在案例库中的楼盘，对部分数据进行升级或者添加新的案例数据
            //新增数据
            List<BasicApplyBatchDetail> addList = LangUtils.filter(details, d -> {
                return d.getBisFromCase() == false && (d.getUpgradeTableId() == null || d.getUpgradeTableId() <= 0);
            });
            if (CollectionUtils.isNotEmpty(addList)) {
                BasicApplyBatch applyBatchCase = basicApplyBatchService.getBasicApplyBatchByCaseEstateId(applyBatch.getCaseEstateId());
                List<BasicApplyBatchDetail> rootAddDetailList = getRootDetailList(addList);
                for (BasicApplyBatchDetail rootAddItem : rootAddDetailList) {
                    BasicApplyBatchDetail parentDetail = basicApplyBatchDetailService.getDataById(rootAddItem.getPid());
                    BasicApplyBatchDetail basicApplyBatchDetailSource = basicApplyBatchDetailService.getBasicApplyBatchDetail(parentDetail.getTableName(), parentDetail.getTableId());
                    if (basicApplyBatchDetailSource != null) {
                        BasicApplyBatchDetail newDetail = new BasicApplyBatchDetail();
                        BeanUtils.copyProperties(rootAddItem, newDetail);
                        newDetail.setApplyBatchId(applyBatchCase.getId());
                        newDetail.setPid(basicApplyBatchDetailSource.getId());
                        basicApplyBatchDetailService.saveBasicApplyBatchDetail(newDetail);
                        BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(newDetail.getType());
                        Object entity = anAbstract.getBasicEntityById(newDetail.getTableId());
                        if (entity != null) {
                            anAbstract.setProperty(entity, "bisCase", true);
                            anAbstract.setProperty(entity, "version", 1);
                            anAbstract.saveAndUpdate(entity, false);
                        }
                        //有下级，继续处理
                        recursiveMigration(rootAddItem, newDetail, applyBatch, applyBatchCase);
                    }
                }
            }

            List<BasicApplyBatchDetail> upgradelList = LangUtils.filter(details, d -> {
                return d.getBisFromCase() == false && d.getUpgradeTableId() > 0;
            });
            if (CollectionUtils.isNotEmpty(upgradelList)) {
                for (BasicApplyBatchDetail upgradelItem : upgradelList) {
                    //找出该数据应该挂到的位置
                    BasicApplyBatchDetail basicApplyBatchDetailSource = basicApplyBatchDetailService.getBasicApplyBatchDetail(upgradelItem.getTableName(), upgradelItem.getUpgradeTableId());
                    BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(upgradelItem.getType());
                    Object entity = anAbstract.getBasicEntityById(upgradelItem.getTableId());
                    if (entity != null) {
                        if (basicApplyBatchDetailSource != null) {
                            basicApplyBatchDetailSource.setTableId((Integer) anAbstract.getProperty(entity, "id"));
                            basicApplyBatchDetailService.saveBasicApplyBatchDetail(basicApplyBatchDetailSource);
                            anAbstract.setProperty(entity, "bisCase", true);
                            Object version = anAbstract.getProperty(entity, "version");
                            anAbstract.setProperty(entity, "version", (Integer) (version == null ? 1 : version) + 1);
                            anAbstract.saveAndUpdate(entity, false);
                        }
                    }
                }
            }
        }
    }

    //获取新增案例数据的新增根节点
    private List<BasicApplyBatchDetail> getRootDetailList(List<BasicApplyBatchDetail> details) {
        if (CollectionUtils.isEmpty(details)) return null;
        List<BasicApplyBatchDetail> rootAddList = Lists.newArrayList();
        for (BasicApplyBatchDetail detail : details) {
            collectionRootDetail(detail, rootAddList);
        }
        return rootAddList;
    }

    private void collectionRootDetail(BasicApplyBatchDetail detail, List<BasicApplyBatchDetail> rootAddList) {
        if (detail == null) return;
        if (rootAddList == null) return;
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getDataById(detail.getPid());
        if (batchDetail == null) return;
        List<Integer> list = LangUtils.transform(rootAddList, p -> p.getId());
        if (batchDetail.getBisFromCase() == Boolean.TRUE && detail.getBisFromCase() == Boolean.FALSE && !list.contains(detail.getId())) {
            rootAddList.add(detail);
        } else {
            collectionRootDetail(batchDetail, rootAddList);
        }
    }

    //递归迁移数据到原案例结构树下
    private void recursiveMigration(BasicApplyBatchDetail sourceDetail, BasicApplyBatchDetail targetParentDetail, BasicApplyBatch sourceApplyBatch, BasicApplyBatch targetApplyBatch) {
        List<BasicApplyBatchDetail> childDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(sourceDetail.getId(), sourceApplyBatch.getId());
        if (CollectionUtils.isNotEmpty(childDetailList)) {
            for (BasicApplyBatchDetail basicApplyBatchDetail : childDetailList) {
                BasicApplyBatchDetail newDetail = new BasicApplyBatchDetail();
                BeanUtils.copyProperties(basicApplyBatchDetail, newDetail);
                newDetail.setApplyBatchId(targetApplyBatch.getId());
                newDetail.setPid(targetParentDetail.getId());
                basicApplyBatchDetailService.saveBasicApplyBatchDetail(newDetail);
                BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(newDetail.getType());
                Object entity = anAbstract.getBasicEntityById(newDetail.getTableId());
                if (entity != null) {
                    anAbstract.setProperty(entity, "bisCase", true);
                    anAbstract.setProperty(entity, "version", 1);
                    anAbstract.saveAndUpdate(entity, false);
                }
                recursiveMigration(basicApplyBatchDetail, newDetail, sourceApplyBatch, targetApplyBatch);
            }
        }
    }
}

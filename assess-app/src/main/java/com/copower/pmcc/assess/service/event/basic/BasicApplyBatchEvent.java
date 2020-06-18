package com.copower.pmcc.assess.service.event.basic;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
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
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        recordToCase(processExecution);
    }

    @Transactional(rollbackFor = Exception.class)
    public void recordToCase(ProcessExecution processExecution) {
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processExecution.getProcessInstanceId());
        BasicApplyBatch caseBasicApplyBatch = basicApplyBatchService.getCaseBasicApplyBatch(applyBatch.getProvince(), applyBatch.getCity(), applyBatch.getEstateName());
        List<BasicApplyBatchDetail> sourceDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
        //新增
        if (caseBasicApplyBatch == null) {
            applyBatch.setBisCase(true);
            basicApplyBatchDao.updateInfo(applyBatch);
            //将从数据也设置为案例数据
            if (CollectionUtils.isNotEmpty(sourceDetails)) {
                for (BasicApplyBatchDetail source : sourceDetails) {
                    BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(source.getType());
                    Object entity = anAbstract.getBasicEntityById(source.getTableId());
                    if (entity != null) {
                        anAbstract.setProperty(entity, "bisCase", true);
                        anAbstract.setProperty(entity, "version", 1);
                        anAbstract.saveAndUpdate(entity, false);
                    }
                    //summary表处理
                    if (source.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
                        houseWriteToSummary(source, applyBatch);
                    }
                }
            }
        } else {
            //升级或新增节点
            if (CollectionUtils.isNotEmpty(sourceDetails)) {
                for (BasicApplyBatchDetail source : sourceDetails) {
                    if (source.getBisFromCase() != true) {
                        BasicApplyBatchDetail caseBasicApplyBatchDetail = basicApplyBatchService.getCaseBasicApplyBatchDetail(source, caseBasicApplyBatch.getId());
                        //升级
                        if (caseBasicApplyBatchDetail != null) {
                            //原数据对应表
                            BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(caseBasicApplyBatchDetail.getType());
                            Object entity = anAbstract.getBasicEntityById(caseBasicApplyBatchDetail.getTableId());
                            Object version = anAbstract.getProperty(entity, "version");
                            //升级后对应表
                            BasicEntityAbstract upgradeupgradeAbstract = publicBasicService.getServiceBeanByKey(source.getType());
                            Object upgradeEntity = upgradeupgradeAbstract.getBasicEntityById(source.getTableId());
                            if (upgradeEntity != null) {
                                upgradeupgradeAbstract.setProperty(upgradeEntity, "bisCase", true);
                                upgradeupgradeAbstract.setProperty(upgradeEntity, "version", (Integer) (version == null ? 0 : version) + 1);
                                upgradeupgradeAbstract.saveAndUpdate(upgradeEntity, false);

                                caseBasicApplyBatchDetail.setTableId((Integer) upgradeupgradeAbstract.getProperty(upgradeEntity, "id"));
                                basicApplyBatchDetailService.saveBasicApplyBatchDetail(caseBasicApplyBatchDetail);
                            }

                        } else {
                            //新增节点
                            BasicApplyBatchDetail caseParentBasicApplyBatchDetail = basicApplyBatchService.getCaseParentBasicApplyBatchDetail(source, caseBasicApplyBatch.getId());
                            if (caseParentBasicApplyBatchDetail != null) {
                                BasicApplyBatchDetail newDetail = new BasicApplyBatchDetail();
                                BeanUtils.copyProperties(source, newDetail, "id");
                                newDetail.setApplyBatchId(caseBasicApplyBatch.getId());
                                newDetail.setPid(caseParentBasicApplyBatchDetail.getId());
                                basicApplyBatchDetailService.saveBasicApplyBatchDetail(newDetail);
                                BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(newDetail.getType());
                                Object entity = anAbstract.getBasicEntityById(newDetail.getTableId());
                                if (entity != null) {
                                    anAbstract.setProperty(entity, "bisCase", true);
                                    anAbstract.setProperty(entity, "version", 1);
                                    anAbstract.saveAndUpdate(entity, false);
                                }
                            }
                        }
                        //summary表处理
                        if (source.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
                            houseWriteToSummary(source, applyBatch);
                        }
                    }

                }
            }
        }
        applyBatch.setStatus(ProjectStatusEnum.FINISH.getKey());
        basicApplyBatchDao.updateInfo(applyBatch);
    }

    //写入summary
    public void houseWriteToSummary(BasicApplyBatchDetail house, BasicApplyBatch applyBatch) {
        //准备需要的数据
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(applyBatch.getEstateId());
        BasicHouse basicHouse = basicHouseService.getBasicHouseById(house.getTableId());
        if (basicHouse == null) return;
        BasicEstateLandCategoryInfo landCategoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicHouse.getId());
        cleanSummary(house.getId());

        List<BasicHouseTrading> tradingList = basicHouseTradingService.getTradingListByHouseId(basicHouse.getId());
        if (CollectionUtils.isNotEmpty(tradingList)) {
            for (BasicHouseTrading trading : tradingList) {
                BasicHouseCaseSummary summary = new BasicHouseCaseSummary();
                summary.setCaseHouseId(house.getId());
                summary.setProvince(basicEstate.getProvince());
                summary.setCity(basicEstate.getCity());
                summary.setDistrict(basicEstate.getDistrict());
                summary.setBlockName(basicEstate.getBlockName());
                summary.setFullName(basicApplyBatchDetailService.getFullNameByBatchDetailId(house.getId()));
                summary.setStreet(basicEstate.getStreet());
                summary.setTradingType(trading.getTradingType());
                summary.setTradingTime(trading.getTradingTime());
                summary.setTradingUnitPrice(trading.getTradingUnitPrice());
                if (landCategoryInfo != null) {
                    summary.setHouseType(landCategoryInfo.getLandUseType());
                    summary.setHouseCategory(landCategoryInfo.getLandUseCategory());
                }
                summary.setArea(basicHouse.getArea());
                summary.setEstateName(basicEstate.getName());
                summary.setVersion(1);
                summary.setBisFromSelf(true);
                summary.setBisNewest(true);
                basicHouseCaseSummaryService.addBaseHouseSummary(summary);
            }
        }
    }

    public void cleanSummary(Integer houseId) {
        BasicHouseCaseSummary where = new BasicHouseCaseSummary();
        where.setCaseHouseId(houseId);
        where.setBisFromSelf(true);
        List<BasicHouseCaseSummary> caseSummaryList = basicHouseCaseSummaryService.getBaseHouseSummaryList(where);
        if (CollectionUtils.isNotEmpty(caseSummaryList)) {
            for (BasicHouseCaseSummary item : caseSummaryList) {
                basicHouseCaseSummaryService.deleteBaseHouseSummaryById(item.getId());
            }
        }
    }
}

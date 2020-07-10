package com.copower.pmcc.assess.service.basic.form.structure;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandCategoryInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.BasicFormStructureInterface;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasicStructureLandService implements BasicFormStructureInterface {
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateLandCategoryInfoDao basicEstateLandCategoryInfoDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BasicApplyBatch initBasicApplyBatch(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch == null) return null;
        //1.根据不同情况初始化不同的信息结构 2.初始化之前需先将原初始化信息删除
        List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isNotEmpty(batchDetailList)) {
            for (BasicApplyBatchDetail basicApplyBatchDetail : batchDetailList) {
                basicApplyBatchDetailService.deleteBasicApplyBatchDetail(basicApplyBatchDetail.getId());
            }
        }
        if (basicApplyBatch.getClassify() == null) return basicApplyBatch;
        BasicFormClassifyEnum formClassifyEnum = BasicFormClassifyEnum.ESTATE_LAND;
        BasicEstate basicEstate = new BasicEstate();//楼盘
        DeclareRecord declareRecord = null; //申报表代入的信息
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatch.getPlanDetailsId());
        if (projectPlanDetails != null && projectPlanDetails.getDeclareRecordId() != null) {
            basicApplyBatch.setProjectId(projectPlanDetails.getProjectId());
            declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        }
        if (declareRecord != null) {
            basicEstate.setProvince(declareRecord.getProvince());
            basicEstate.setCity(declareRecord.getCity());
            basicEstate.setDistrict(declareRecord.getDistrict());
        }
        basicEstateService.saveAndUpdate(basicEstate, false);
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setEstateId(basicEstate.getId());
        basicEstateLandState.setCreator(commonService.thisUserAccount());
        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, false);
        if (StringUtils.isBlank(basicApplyBatch.getEstateName())) {
            basicApplyBatch.setEstateName("地块信息");
        }
        basicApplyBatch.setEstateId(basicEstate.getId());
        basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);
        //默认生成一条土地类型
//        BasicEstateLandCategoryInfo categoryInfo = new BasicEstateLandCategoryInfo();
//        categoryInfo.setLandId(basicEstateLandState.getId());
//        categoryInfo.setCreator(commonService.thisUserAccount());
//        basicEstateLandCategoryInfoDao.saveBasicEstateLandCategoryInfo(categoryInfo);

        BasicApplyBatchDetail estateApplyBatchDetail = new BasicApplyBatchDetail();
        estateApplyBatchDetail.setPid(0);
        estateApplyBatchDetail.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        estateApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateApplyBatchDetail.setTableName(formClassifyEnum.getTableName());
        estateApplyBatchDetail.setTableId(basicEstate.getId());
        estateApplyBatchDetail.setName(basicApplyBatch.getEstateName());
        estateApplyBatchDetail.setDisplayName(basicApplyBatch.getEstateName());
        estateApplyBatchDetail.setExecutor(commonService.thisUserAccount());
        estateApplyBatchDetail.setType(formClassifyEnum.getKey());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateApplyBatchDetail);

        //纯土地中地块包一部分房屋相关信息
//        BasicHouse basicHouse = new BasicHouse();
//        basicHouse.setEstateId(basicEstate.getId());
//        basicHouse.setCreator(commonService.thisUserAccount());
//        basicHouseService.saveAndUpdate(basicHouse, false);
//
//        BasicFormClassifyEnum houseLandClassifyEnum = BasicFormClassifyEnum.HOUSE_LAND;
//        BasicApplyBatchDetail houseApplyBatchDetail = new BasicApplyBatchDetail();
//        houseApplyBatchDetail.setPid(estateApplyBatchDetail.getId());
//        houseApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
//        houseApplyBatchDetail.setTableName(houseLandClassifyEnum.getTableName());
//        houseApplyBatchDetail.setTableId(basicHouse.getId());
//        houseApplyBatchDetail.setName("房屋交易信息");
//        houseApplyBatchDetail.setDisplayName("房屋交易信息");
//        houseApplyBatchDetail.setExecutor(commonService.thisUserAccount());
//        houseApplyBatchDetail.setType(houseLandClassifyEnum.getKey());
//        basicApplyBatchDetailService.saveBasicApplyBatchDetail(houseApplyBatchDetail);
//
//        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
//        basicHouseTrading.setHouseId(basicHouse.getId());
//        basicHouseTrading.setCreator(commonService.thisUserAccount());
//        basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
//        BasicApply basicApply = new BasicApply();
//        basicApply.setBasicEstateId(basicEstate.getId());
//        basicApply.setBasicHouseId(basicHouse.getId());
//        basicApply.setBatchDetailId(houseApplyBatchDetail.getId());
//        basicApply.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
//        basicApply.setLandCategoryId(categoryInfo.getId());
//        basicApplyService.saveBasicApply(basicApply);
        return basicApplyBatch;
    }
}

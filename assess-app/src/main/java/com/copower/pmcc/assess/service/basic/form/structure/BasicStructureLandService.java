package com.copower.pmcc.assess.service.basic.form.structure;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.BasicFormStructureInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseQrcodeService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicStructureLandService implements BasicFormStructureInterface {
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BaseDataDicService baseDataDicService;
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
        BasicFormClassifyEnum formClassifyEnum = BasicFormClassifyEnum.ESTATE;
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
            formClassifyEnum = BasicFormClassifyEnum.ESTATE_LAND;
        }
        basicApplyBatch.setEstateId(basicEstate.getId());
        basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);

        BasicApplyBatchDetail estateApplyBatchDetail = new BasicApplyBatchDetail();
        estateApplyBatchDetail.setPid(0);
        estateApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateApplyBatchDetail.setTableName(formClassifyEnum.getTableName());
        estateApplyBatchDetail.setTableId(basicEstate.getId());
        estateApplyBatchDetail.setName(basicApplyBatch.getEstateName());
        estateApplyBatchDetail.setDisplayName(basicApplyBatch.getEstateName());
        estateApplyBatchDetail.setExecutor(commonService.thisUserAccount());
        estateApplyBatchDetail.setType(formClassifyEnum.getKey());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateApplyBatchDetail);

        BasicApply basicApply = new BasicApply();
        basicApply.setBasicEstateId(basicEstate.getId());
        if (declareRecord != null) {
            basicApply.setDeclareRecordId(declareRecord.getId());
        }
        basicApply.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        basicApplyService.saveBasicApply(basicApply);
        return basicApplyBatch;
    }
}

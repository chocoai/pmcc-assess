package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
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

import java.util.Collection;
import java.util.List;


@Component
public class ProjectTaskCIPEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseParameterService baseParameterService;
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
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processExecution.getProcessInstanceId());
            surveyCommonService.updateDeclarePracticalUse(projectPlanDetails);

            String parameter = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_AUTOMATIC_GENERATE_CASE_KEY);
            if(StringUtils.equals("true",parameter)){
                autoTransformToCase(projectPlanDetails);//自动处理案例
            }
        }
    }

    //自动转换为案例数据
    private void autoTransformToCase(ProjectPlanDetails projectPlanDetails){
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(projectPlanDetails.getId());
        BasicApplyBatch caseBasicApplyBatch = basicApplyBatchService.getCaseBasicApplyBatch(applyBatch.getProvince(), applyBatch.getCity(), applyBatch.getEstateName());

        List<BasicApplyBatchDetail> sourceDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
        if(CollectionUtils.isNotEmpty(sourceDetails)){
            //新建树形结构处理
            BasicApplyBatch targetApplyBatch = new BasicApplyBatch();
            BeanUtils.copyProperties(applyBatch, targetApplyBatch, "referenceApplyBatchId", "planDetailsId");
            if(caseBasicApplyBatch == null){
                targetApplyBatch.setBisCase(true);
            }
            targetApplyBatch.setStatus(ProjectStatusEnum.FINISH.getKey());
            basicApplyBatchDao.addBasicApplyBatch(targetApplyBatch);
            //需要处理的applyBatchDetailIds
            List<Integer> handleDetailIds = Lists.newArrayList();
            //新增时全部加入
            if (caseBasicApplyBatch == null) {
                sourceDetails.forEach(o->{
                    handleDetailIds.add(o.getId());
                });
            }else{
                for (BasicApplyBatchDetail sourceDetail:sourceDetails){
                    //只处理新增数据
                    BasicApplyBatchDetail caseBasicApplyBatchDetail = basicApplyBatchService.getCaseBasicApplyBatchDetail(sourceDetail, caseBasicApplyBatch.getId());
                    if(caseBasicApplyBatchDetail==null){
                        handleDetailIds.add(sourceDetail.getId());
                    }
                }
            }
            //生成树形子数据
            if (CollectionUtils.isNotEmpty(handleDetailIds)) {
                String detailIds = StringUtils.join(handleDetailIds, ",");
                for (Integer id : handleDetailIds) {
                    List<BasicApplyBatchDetail> list = Lists.newArrayList();
                    basicApplyBatchDetailService.collectionParentBatchDetails(id, list);
                    basicApplyBatchDetailService.copyNodeStructure(list, targetApplyBatch.getId(), applyBatch, FormatUtils.transformString2List(detailIds));
                }
            }
            //处理案例树形结构
        }
    }
}

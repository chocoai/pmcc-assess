package com.copower.pmcc.assess.service.project.compile;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.compile.CompileReportDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeEvaluationObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2018-5-29.
 */
@Service
public class CompileReportService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private CompileReportDetailDao compileReportDetailDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private SchemeEvaluationObjectDao schemeEvaluationObjectDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;

    /**
     * 初始化计划信息
     * @param projectPlan
     */
    public void initializePlan(ProjectPlan projectPlan) {
        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();
        ProjectPlanDetails projectPlanDetailsWhere=new ProjectPlanDetails();
        projectPlanDetailsWhere.setProjectId(projectId);
        projectPlanDetailsWhere.setPlanId(planId);
        List<ProjectPlanDetails> planDetails = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
        if (CollectionUtils.isNotEmpty(planDetails)) {
            return ;//避免重复初始化
        }
        List<SchemeAreaGroup> schemeAreaGroups = schemeAreaGroupDao.getSchemeAreaGroupByProjectId(projectId);
        int i = 1;
        //一级分类 地址
        if (CollectionUtils.isNotEmpty(schemeAreaGroups)) {
            for (SchemeAreaGroup schemeAreaGroup : schemeAreaGroups) {
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setProjectWorkStageId(workStageId);
                projectPlanDetails.setPlanId(planId);
                projectPlanDetails.setProjectId(projectId);
                projectPlanDetails.setProjectPhaseName(schemeAreaGroup.getAreaName());
                projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setSorting(i++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

                int j = 1;
                Integer pid = 0;
                Integer groupId = schemeAreaGroup.getId();
                List<SchemeEvaluationObject> evaluationObjects = schemeEvaluationObjectDao.getDataListByGroupId(groupId, projectId);
                //二级分类 评估对象
                pid = projectPlanDetails.getId();

                String name = "";
                Integer projectPhaseId = 0;
                if (CollectionUtils.isNotEmpty(evaluationObjects)) {
                    for (SchemeEvaluationObject evaluationObject : evaluationObjects) {
                        name = evaluationObject.getName();
                        projectPhaseId = evaluationObject.getId();
                        ProjectPlanDetails projectPlanDetailTwo = new ProjectPlanDetails();
                        projectPlanDetailTwo.setProjectWorkStageId(workStageId);
                        projectPlanDetailTwo.setPlanId(planId);
                        projectPlanDetailTwo.setProjectId(projectId);
                        projectPlanDetailTwo.setProjectPhaseName(name);
                        projectPlanDetailTwo.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        projectPlanDetailTwo.setPid(pid);
                        projectPlanDetailTwo.setFirstPid(pid);
                        projectPlanDetailTwo.setProjectPhaseId(projectPhaseId);
                        projectPlanDetailTwo.setSorting(j++);
                        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetailTwo);
                    }
                }
            }
        }
    }

    /**
     * 保存分析信息
     *
     * @param schemeReportDetail
     */
    public void saveReportDetail(CompileReportDetail schemeReportDetail) throws BusinessException {
        if (schemeReportDetail == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeReportDetail.getId() != null && schemeReportDetail.getId() > 0) {
            compileReportDetailDao.updateReportDetail(schemeReportDetail);
        } else {
            schemeReportDetail.setCreator(commonService.thisUserAccount());
            compileReportDetailDao.addReportDetail(schemeReportDetail);
        }
    }

    /**
     * 获取数据列表
     *
     * @param planDetailsId
     * @return
     */
    public List<CompileReportDetail> getReportDetailList(Integer planDetailsId) {
        CompileReportDetail where = new CompileReportDetail();
        where.setPlanDetailsId(planDetailsId);
        return compileReportDetailDao.getReportDetailList(where);
    }

    /**
     * 初始化该任务所需要的分析信息
     *
     * @param planDetailsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void initReportDetail(Integer planDetailsId, String phaseKey) {
        int count = compileReportDetailDao.getCountByPlanDetailsId(planDetailsId);
        if (count > 0) return;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(phaseKey);
        if (baseDataDic == null) return;
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisService.getReportAnalysisList(baseDataDic.getId());
        CompileReportDetail compileReportDetail = null;
        if (CollectionUtils.isNotEmpty(reportAnalysisList)) {
            for (DataReportAnalysis dataReportAnalysis : reportAnalysisList) {
                compileReportDetail = new CompileReportDetail();
                compileReportDetail.setCreator(commonService.thisUserAccount());
                compileReportDetail.setName(dataReportAnalysis.getName());
                compileReportDetail.setTemplate(dataReportAnalysis.getTemplate());
                compileReportDetail.setReportAnalysisType(baseDataDic.getId());
                compileReportDetail.setReportAnalysisName(baseDataDic.getName());
                compileReportDetail.setJsonContent(publicService.extractField(dataReportAnalysis.getTemplate()));
                compileReportDetail.setPlanDetailsId(planDetailsId);
                compileReportDetailDao.addReportDetail(compileReportDetail);
            }

        }

    }
}

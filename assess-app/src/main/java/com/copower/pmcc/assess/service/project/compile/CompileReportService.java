package com.copower.pmcc.assess.service.project.compile;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.compile.CompileReportDao;
import com.copower.pmcc.assess.dal.basis.dao.project.compile.CompileReportDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
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
    private CompileReportDao compileReportDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectInfoService projectInfoService;

    /**
     * 初始化计划信息
     *
     * @param projectPlan
     */
    public void initializePlan(ProjectPlan projectPlan) {
        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();
        ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
        projectPlanDetailsWhere.setProjectId(projectId);
        projectPlanDetailsWhere.setPlanId(planId);
        List<ProjectPlanDetails> planDetails = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
        if (CollectionUtils.isNotEmpty(planDetails)) {
            return;//避免重复初始化
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
                projectPlanDetails.setAreaId(schemeAreaGroup.getId());
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setSorting(i++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
            }
        }
    }

    /**
     * 根据区域id获取报告分析数据
     *
     * @param areaId
     * @param reportAnalysisType
     * @return
     */
    public List<CompileReportDetail> getCompileReportDetailList(Integer areaId, Integer reportAnalysisType) {
        CompileReportDetail where = new CompileReportDetail();
        where.setAreaId(areaId);
        where.setReportAnalysisType(reportAnalysisType);
        return compileReportDetailDao.getReportDetailList(where);
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

    public CompileReport getCompileReportByPlanDetailsId(Integer planDetailsId) {
        return compileReportDao.getCompileReportByPlanDetailsId(planDetailsId);
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
     * @param projectPlanDetails
     * @param phaseKey
     */
    @Transactional(rollbackFor = Exception.class)
    public void initReportDetail(ProjectPlanDetails projectPlanDetails, String phaseKey) {
        int count = compileReportDetailDao.getCountByPlanDetailsId(projectPlanDetails.getId());
        if (count > 0) return;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(phaseKey);
        if (baseDataDic == null) return;
        ProjectPlanDetails areaPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(projectPlanDetails.getPid());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        SchemeAreaGroup areaGroup = schemeAreaGroupDao.get(areaPlanDetails.getAreaId());
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisService.getDataReportAnalysisList(areaGroup.getProvince(), areaGroup.getCity(),
                areaGroup.getDistrict(), baseDataDic.getId(), projectInfo.getEntrustPurpose());
        CompileReportDetail compileReportDetail = null;
        if (CollectionUtils.isNotEmpty(reportAnalysisList)) {
            for (DataReportAnalysis dataReportAnalysis : reportAnalysisList) {
                compileReportDetail = new CompileReportDetail();
                compileReportDetail.setCreator(commonService.thisUserAccount());
                compileReportDetail.setName(dataReportAnalysis.getName());
                compileReportDetail.setTemplate(dataReportAnalysis.getTemplate());
                if (dataReportAnalysis.getBisModifiable() == Boolean.FALSE) {
                    compileReportDetail.setContent(dataReportAnalysis.getTemplate());
                }
                compileReportDetail.setAreaId(areaPlanDetails.getAreaId());
                compileReportDetail.setReportAnalysisType(baseDataDic.getId());
                compileReportDetail.setReportAnalysisName(baseDataDic.getName());
                compileReportDetail.setJsonContent(publicService.extractField(dataReportAnalysis.getTemplate()));
                compileReportDetail.setPlanDetailsId(projectPlanDetails.getId());
                compileReportDetail.setBisModifiable(dataReportAnalysis.getBisModifiable());
                compileReportDetailDao.addReportDetail(compileReportDetail);
            }
        }
    }

    /**
     * 获取上报告的分析内容
     *
     * @param areaId
     * @param reportAnalysisType
     * @return
     */
    public String getReportCompile(Integer areaId, String reportAnalysisType) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(reportAnalysisType);
        if (baseDataDic == null) return "";
        CompileReportDetail where = new CompileReportDetail();
        where.setAreaId(areaId);
        where.setReportAnalysisType(baseDataDic.getId());
        List<CompileReportDetail> reportDetailList = compileReportDetailDao.getReportDetailList(where);
        if (CollectionUtils.isEmpty(reportDetailList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < reportDetailList.size(); i++) {
            CompileReportDetail reportDetail = reportDetailList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s",i+1,reportDetail.getName())).append("</p>");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(reportDetail.getTemplate()).append("</p>");
        }
        return stringBuilder.toString();
    }

    /**
     * 根据名称获取内容信息
     *
     * @param areaId
     * @param name
     * @return
     */
    public String getContentByName(Integer areaId, String name) {
        CompileReportDetail where = new CompileReportDetail();
        where.setAreaId(areaId);
        where.setName(name);
        List<CompileReportDetail> detailList = compileReportDetailDao.getReportDetailList(where);
        if (CollectionUtils.isEmpty(detailList)) return "";
        return detailList.get(0).getContent();
    }
}

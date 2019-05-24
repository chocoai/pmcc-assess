package com.copower.pmcc.assess.service.project.compile;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.compile.CompileReportDao;
import com.copower.pmcc.assess.dal.basis.dao.project.compile.CompileReportDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisBackgroundService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private DataReportAnalysisBackgroundService dataReportAnalysisBackgroundService;

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
     */
    @Transactional(rollbackFor = Exception.class)
    public void initReportDetail(ProjectPlanDetails projectPlanDetails) {
        int count = compileReportDetailDao.getCountByPlanDetailsId(projectPlanDetails.getId());
        if (count > 0) return;
        BaseDataDic analysisType = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
        if (CollectionUtils.isEmpty(dataDicList)) return;
        CompileReportDetail compileReportDetail = null;
        for (BaseDataDic baseDataDic : dataDicList) {
            SchemeAreaGroup schemeAreaGroup = schemeAreaGroupDao.get(projectPlanDetails.getAreaId());
            DataReportAnalysis analysis = dataReportAnalysisBackgroundService.getReportAnalysisByAreaId(schemeAreaGroup.getDistrict(), baseDataDic.getId(), schemeAreaGroup.getValueTimePoint());
            //根据各种条件获取对应的模板数据
            compileReportDetail = new CompileReportDetail();
            compileReportDetail.setCreator(commonService.thisUserAccount());
            compileReportDetail.setName(baseDataDic.getName());
            compileReportDetail.setAreaId(projectPlanDetails.getAreaId());
            compileReportDetail.setReportAnalysisType(analysisType.getId());
            compileReportDetail.setReportAnalysisName(analysisType.getName());
            compileReportDetail.setPlanDetailsId(projectPlanDetails.getId());
            compileReportDetail.setBisModifiable(true);
            if (analysis != null) {
                compileReportDetail.setMarketBackgroundType(analysis.getMarketBackgroundType());
                compileReportDetail.setContent(tagfilter(analysis.getTemplate()));
            }
            compileReportDetailDao.addReportDetail(compileReportDetail);
        }
    }

    /**
     * 获取上报告的分析内容
     *
     * @param areaId
     * @param marketBackgroundType
     * @return
     */
    public String getReportCompile(Integer areaId, String marketBackgroundType) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(marketBackgroundType);
        if (baseDataDic == null) return "";
        CompileReportDetail where = new CompileReportDetail();
        where.setAreaId(areaId);
        where.setMarketBackgroundType(baseDataDic.getId());
        List<CompileReportDetail> reportDetailList = compileReportDetailDao.getReportDetailList(where);
        if (CollectionUtils.isEmpty(reportDetailList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < reportDetailList.size(); i++) {
            CompileReportDetail reportDetail = reportDetailList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(reportDetail.getContent()).append("</p>");
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


    public static String tagfilter(String str) {
        final String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }
}

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
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
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
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private DataReportAnalysisBackgroundService dataReportAnalysisBackgroundService;

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
            SchemeAreaGroup schemeAreaGroup = schemeAreaGroupDao.getSchemeAreaGroup(projectPlanDetails.getAreaId());
            if (schemeAreaGroup == null) continue;
            DataReportAnalysis analysis = dataReportAnalysisBackgroundService.getReportAnalysisByAreaId(schemeAreaGroup.getId(), baseDataDic.getId());
            //根据各种条件获取对应的模板数据
            compileReportDetail = new CompileReportDetail();
            compileReportDetail.setCreator(commonService.thisUserAccount());
            compileReportDetail.setName(baseDataDic.getName());
            compileReportDetail.setAreaId(projectPlanDetails.getAreaId());
            compileReportDetail.setReportAnalysisType(analysisType.getId());
            compileReportDetail.setReportAnalysisName(analysisType.getName());
            compileReportDetail.setPlanDetailsId(projectPlanDetails.getId());
            compileReportDetail.setMarketBackgroundType(baseDataDic.getId());
            compileReportDetail.setBisModifiable(true);
            if (analysis != null) {
                compileReportDetail.setContent(publicService.tagfilter(analysis.getTemplate()));
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
        //如果该区域是拆分的区域，则取同区域下非拆分区域数据
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSourceSplitAreaGroup(areaId);
        CompileReportDetail where = new CompileReportDetail();
        where.setAreaId(areaGroup.getId());
        where.setMarketBackgroundType(baseDataDic.getId());
        List<CompileReportDetail> reportDetailList = compileReportDetailDao.getReportDetailList(where);
        if (CollectionUtils.isEmpty(reportDetailList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < reportDetailList.size(); i++) {
            CompileReportDetail reportDetail = reportDetailList.get(i);
            stringBuilder.append(reportDetail.getContent());
        }
        return stringBuilder.toString();
    }
}

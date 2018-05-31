package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.CompileReportDao;
import com.copower.pmcc.assess.dal.dao.CompileReportDetailsDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.CompileReportDetails;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.dto.output.project.CompileReportDetailsVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-5-29.
 */
@Service
public class CompileReportService {
    @Autowired
    private CompileReportDao compileReportDao;
    @Autowired
    private CompileReportDetailsDao compileReportDetailsDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    /**
     * 初始化数据
     *
     * @param planDetailsId
     */
    public void initReportDetails(Integer planDetailsId) {
        //将配置数据初始化到details表中，方便后续操作
        List<CompileReportDetails> reportDetails = compileReportDetailsDao.getListByPlanDetailsId(planDetailsId);
        if (CollectionUtils.isEmpty(reportDetails)) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            if (projectPlanDetails == null) return;
            List<DataReportAnalysisVo> reportAnalysisVos = dataReportAnalysisService.getDataReportAnalysisByCategory(projectPlanDetails.getReportAnalysisCategory());
            if (CollectionUtils.isNotEmpty(reportAnalysisVos)) {
                for (DataReportAnalysisVo reportAnalysisVo : reportAnalysisVos) {
                    CompileReportDetails compileReportDetails = new CompileReportDetails();
                    compileReportDetails.setPlanDetailsId(planDetailsId);
                    compileReportDetails.setCategory(reportAnalysisVo.getCategory());
                    compileReportDetails.setCategoryField(reportAnalysisVo.getCategoryField());
                    compileReportDetails.setReportAnalysisId(reportAnalysisVo.getId());
                    compileReportDetails.setTemplate(reportAnalysisVo.getTemplate());
                    compileReportDetails.setCreator(commonService.thisUserAccount());
                    compileReportDetailsDao.add(compileReportDetails);
                }
            }
        }
    }

    /**
     * 保存
     * @param compileReportDetails
     */
    public void saveReportDetails(CompileReportDetails compileReportDetails){
        if(compileReportDetails.getId()!=null&&compileReportDetails.getId()>0){
            compileReportDetailsDao.update(compileReportDetails);
        }else{
            compileReportDetails.setCreator(commonService.thisUserAccount());
            compileReportDetailsDao.add(compileReportDetails);
        }
    }

    /**
     * 获取列表数据
     * @param planDetailsId
     * @param category
     * @return
     */
    public List<CompileReportDetailsVo> getCompileReportDetailsList(Integer planDetailsId,Integer category){
        CompileReportDetails queryParam=new CompileReportDetails();
        queryParam.setPlanDetailsId(planDetailsId);
        queryParam.setCategory(category);
        List<CompileReportDetails> reportDetailsList = compileReportDetailsDao.getReportDetailsList(queryParam);
        if(CollectionUtils.isEmpty(reportDetailsList)) return null;
        return LangUtils.transform(reportDetailsList, p -> {
            return getCompileReportDetailsVo(p);
        });
    }

    public CompileReportDetailsVo getCompileReportDetailsVo(CompileReportDetails compileReportDetails){
        CompileReportDetailsVo compileReportDetailsVo=new CompileReportDetailsVo();
        BeanUtils.copyProperties(compileReportDetails,compileReportDetailsVo);
        if(compileReportDetails.getCategory()!=null){
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(compileReportDetails.getCategory());
            if(baseDataDic!=null)
                compileReportDetailsVo.setCategoryName(baseDataDic.getName());
        }
        if(compileReportDetails.getCategoryField()!=null){
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(compileReportDetails.getCategoryField());
            if(baseDataDic!=null)
                compileReportDetailsVo.setCategoryFieldName(baseDataDic.getName());
        }
        return compileReportDetailsVo;
    }
}

package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyCaseStudyDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudy;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyCaseStudyService {

    @Autowired
    private SurveyCaseStudyDao surveyCaseStudyDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    /**
     * 数据保存
     *
     * @param surveyCaseStudy
     * @return
     * @throws BusinessException
     */
    public boolean saveSurveyCaseStudy(SurveyCaseStudy surveyCaseStudy) throws BusinessException {
        if (surveyCaseStudy == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyCaseStudy.getId() != null && surveyCaseStudy.getId() > 0) {
            return surveyCaseStudyDao.updateSurveyCaseStudy(surveyCaseStudy);
        } else {
            surveyCaseStudy.setCreator(commonService.thisUserAccount());
            return surveyCaseStudyDao.addSurveyCaseStudy(surveyCaseStudy);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public boolean deleteSurveyCaseStudy(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
        return surveyCaseStudyDao.deleteSurveyCaseStudy(id);

    }

    /**
     * 获取数据
     *
     * @param processInsId
     * @return
     */
    public SurveyCaseStudy getSurveyCaseStudy(String processInsId) {
        SurveyCaseStudy surveyCaseStudy = surveyCaseStudyDao.getSurveyCaseStudy(processInsId);
        return surveyCaseStudy;
    }

    /**
     * 获取案例调查所有任务
     *
     * @param planDetailsId
     * @return
     */
    public List<ProjectPlanDetailsVo> getCaseTaskList(Integer planDetailsId) {
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getPlanDetailsListRecursion(planDetailsId);
        List<ProjectPlanDetailsVo> planDetailsVoList = LangUtils.transform(planDetailsList, o -> projectPlanDetailsService.getProjectPlanDetailsVo(o));
        if(CollectionUtils.isNotEmpty(planDetailsVoList)){
            for (ProjectPlanDetailsVo projectPlanDetailsVo : planDetailsVoList) {
                if(projectPlanDetailsVo.getId().equals(planDetailsId))
                    projectPlanDetailsVo.set_parentId(null);//顶级节点parentId必须为空才能显示
            }
        }
        return planDetailsVoList;
    }

    /**
     * 保存案例任务
     * @param projectPlanDetails
     */
    public void saveCaseTask(ProjectPlanDetails projectPlanDetails,Integer planDetailsId) throws BusinessException {
        if(projectPlanDetails.getId()!=null&&projectPlanDetails.getId()>0){
            projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
        }else{
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            planDetails.setId(0);
            planDetails.setPid(projectPlanDetails.getPid());
            planDetails.setProjectPhaseName(projectPlanDetails.getProjectPhaseName());
            planDetails.setPlanStartDate(projectPlanDetails.getPlanStartDate());
            planDetails.setPlanEndDate(projectPlanDetails.getPlanEndDate());
            planDetails.setPlanHours(projectPlanDetails.getPlanHours());
            planDetails.setProportion(projectPlanDetails.getProportion());
            planDetails.setSorting(1);
            planDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            planDetails.setCreator(commonService.thisUserAccount());
            projectPlanDetailsService.saveProjectPlanDetails(planDetails);
        }
    }

    /**
     * 删除案例任务
     * @param planDetailsId
     */
    public void deleteCaseTask(Integer planDetailsId){

        //先验证是否满足删除条件

        projectPlanDetailsService.deleteProjectPlanDetails(planDetailsId);
    }
}

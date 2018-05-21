package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.dao.SchemeEvaluationObjectDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.collections.CollectionUtils;

import javax.rmi.PortableRemoteObject;
import java.util.List;

/**
 * Created by zly on 2018/5/21.
 */
@Service
public class ProjectPlanCompileService {

    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private SchemeEvaluationObjectDao schemeEvaluationObjectDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;

    public ModelAndView getInitialize(ModelAndView modelAndView, ProjectPlan projectPlan) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY);
        int size = baseDataDics.size();
        modelAndView.addObject("reportAnalysisList",baseDataDics);

        Integer planId = projectPlan.getId();
        Integer projectId = projectPlan.getProjectId();
        Integer workStageId = projectPlan.getWorkStageId();

        List<SchemeAreaGroup> schemeAreaGroups = schemeAreaGroupDao.getSchemeAreaGroupByProjectId(projectId);
        List<SchemeAreaGroup> voList = getVoList(schemeAreaGroups);
//        List<SchemeEvaluationObject> schemeEvaluationObjects = schemeEvaluationObjectDao.getSchemeEvaluationObjectByProjectId(projectId);

        String address = "";
        int i = 1;
        //一级分类 地址
        for (SchemeAreaGroup schemeAreaGroup : voList) {
            address = schemeAreaGroup.getProvince() + schemeAreaGroup.getCity() + schemeAreaGroup.getDistrict();

            List<ProjectPlanDetails> projectPlanDetailss = projectPlanDetailsDao.getProjectPlanDetailsByProjectIdAndName(projectId, address, workStageId);
            if (projectPlanDetailss != null && projectPlanDetailss.size() > 0) {
                return modelAndView;
            } else {
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setProjectWorkStageId(workStageId);
                projectPlanDetails.setPlanId(planId);
                projectPlanDetails.setProjectId(projectId);
                projectPlanDetails.setProjectPhaseName(address);
                projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setSorting(i++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
            }
            List<ProjectPlanDetails> projectPlanDetailsss = projectPlanDetailsDao.getProjectPlanDetailsByProjectIdAndName(projectId, address, workStageId);


            int j = 1;
            Integer pid = 0;
            Integer groupId = schemeAreaGroup.getId();
            List<SchemeEvaluationObject> evaluationObjects = schemeEvaluationObjectDao.getSchemeEvaluationObjectByGroupId(groupId, projectId);
            //二级分类 评估对象
            for (ProjectPlanDetails projectPlanDetail : projectPlanDetailsss) {
                pid = projectPlanDetail.getId();

                String name = "";
                Integer projectPhaseId = 0;
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
        return modelAndView;
    }
    //转换具体数据
    private List<SchemeAreaGroup> getVoList(List<SchemeAreaGroup> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SchemeAreaGroup schemeAreaGroup = new SchemeAreaGroup();
            BeanUtils.copyProperties(p, schemeAreaGroup);
            if (p.getProvince() != null) {
                SysAreaDto sysAreaDto = erpAreaService.getSysAreaDto(Integer.valueOf(p.getProvince()));
                if (sysAreaDto != null)
                    schemeAreaGroup.setProvince(sysAreaDto.getName());
            }
            if(p.getCity() != null){
                SysAreaDto sysAreaDto = erpAreaService.getSysAreaDto(Integer.valueOf(p.getCity()));
                if(sysAreaDto != null){
                    schemeAreaGroup.setCity(sysAreaDto.getName());
                }
            }
            if (p.getDistrict() !=null) {
                SysAreaDto sysAreaDto = erpAreaService.getSysAreaDto(Integer.valueOf(p.getDistrict()));
                if (sysAreaDto != null) {
                    schemeAreaGroup.setDistrict(sysAreaDto.getName());
                }
            }
            return schemeAreaGroup;
        });
    }
}

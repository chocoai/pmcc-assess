package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentWorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentWorkHoursService {
    @Autowired
    private ChksRpcAssessmentWorkHoursService chksRpcAssessmentWorkHoursService;

    /**
     * 生成工时考核任务
     * @param processInsId
     * @param currStep
     */
    public void generateWorkHoursTask(String processInsId, String reActivityName, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails){
        //1.生成工时考核任务，先查看该节点是否生成了任务，有则直接返回
        //2.没有生成则为当前节点生成对应的任务

    }
}

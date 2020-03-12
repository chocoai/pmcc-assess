package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;

/**
 * Created by wangpc on 2020/2/4.
 */
public interface AssessmentTaskInterface {
    /**
     * 创建考核任务
     * @param processInsId
     * @param activityId
     * @param byExamineUser
     * @param projectInfo
     * @param projectPlanDetails
     */
    void createAssessmentTask(String processInsId, Integer activityId,String taskId, String byExamineUser, ProjectInfo projectInfo, ProjectPlanDetails projectPlanDetails);
}

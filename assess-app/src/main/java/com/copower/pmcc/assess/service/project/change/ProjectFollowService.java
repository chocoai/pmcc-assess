package com.copower.pmcc.assess.service.project.change;

import com.copower.pmcc.assess.dal.basis.dao.project.manage.ProjectFollowDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFollow;
import com.copower.pmcc.assess.service.project.ProjectCenterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 13:16
 */
@Service
public class ProjectFollowService {
    @Autowired
    private ProjectFollowDao projectFollowDao;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectCenterService projectCenterService;

    /**
     * 关注项目
     */
    public void followProject(Integer projectId, String followReason) throws BusinessException {
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setProjectId(projectId);
        projectFollow.setUserAccount(processControllerComponent.getThisUser());
        projectFollow.setCreator(processControllerComponent.getThisUser());
        projectFollow.setFollowReason(followReason);
        if (!projectFollowDao.addFollow(projectFollow)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
    }

    /**
     * 取消项目关注
     */
    public void cancelFollowProject(Integer projectId, String cancelReason) throws BusinessException {
        ProjectFollow projectFollow = projectFollowDao.getProjectFollowByUserAccountAndProjectId(processControllerComponent.getThisUser(), projectId);
        projectFollow.setCancelReason(cancelReason);
        projectFollow.setCancelDate(new Date());
        projectFollow.setBisEnable(false);
        if (!projectFollowDao.editFollow(projectFollow)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
    }

    public ProjectFollow getProjectFollowByUser(Integer projectId) {
        ProjectFollow projectFollow = projectFollowDao.getProjectFollowByUserAccountAndProjectId(processControllerComponent.getThisUser(), projectId);
        return projectFollow;
    }
}

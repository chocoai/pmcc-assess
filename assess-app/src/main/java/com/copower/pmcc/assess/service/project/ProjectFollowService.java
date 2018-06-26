package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.project.ProjectFollowDao;
import com.copower.pmcc.assess.dal.entity.ProjectFollow;
import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public BootstrapTableVo getProjectProgressVO() {

        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectFollow> projectFollows = projectFollowDao.getProjectFollowByUserAccount(processControllerComponent.getThisUser());
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        if (CollectionUtils.isNotEmpty(projectFollows)) {
            List<Integer> projectIds = LangUtils.transform(projectFollows, input -> input.getProjectId());
            List<ProjectInfo> projectInfos = projectInfoService.getProjectInfoByProjectIds(projectIds);
            bootstrapTableVo.setRows(projectCenterService.getProjectProgressVOS(projectInfos, projectIds));
            bootstrapTableVo.setTotal(page.getTotal());
        }
        return bootstrapTableVo;

    }

    public ProjectFollow getProjectFollowByUser(Integer projectId) {
        ProjectFollow projectFollow = projectFollowDao.getProjectFollowByUserAccountAndProjectId(processControllerComponent.getThisUser(), projectId);
        return projectFollow;
    }
}

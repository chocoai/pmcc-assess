package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.service.project.ProjectFollowService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 13:43
 */
@Controller
@RequestMapping(value = "/projectFollow", name = "项目关注控制器")
public class ProjectFollowController {
    @Autowired
    private ProjectFollowService projectFollowService;

    @ResponseBody
    @RequestMapping(value = "/getFollowProjectProgress", name = "取得关注的项目进度", method = RequestMethod.GET)
    public BootstrapTableVo getFollowProjectProgress() {
        return projectFollowService.getProjectProgressVO();
    }

    @ResponseBody
    @RequestMapping(value = "/followProject", name = "关注项目", method = RequestMethod.POST)
    public HttpResult followProject(Integer projectId, String followReason) {
        try {
            projectFollowService.followProject(projectId, followReason);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/cancelFollowProject", name = "取消关注", method = RequestMethod.POST)
    public HttpResult cancelFollowProject(Integer projectId, String cancelReason) {
        try {
            projectFollowService.cancelFollowProject(projectId, cancelReason);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

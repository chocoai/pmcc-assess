package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
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
 * @data: 2017/9/18
 * @time: 10:50
 */
@Controller
@RequestMapping(value = "/projectMember")
public class ProjectMemberController {
    @Autowired
    private ProjectMemberService projectMemberService;

    @ResponseBody
    @RequestMapping(value = "/saveChangeProjectMemeber", name = "更新项目组成员", method = RequestMethod.POST)
    public HttpResult saveChangeProjectMemeber(ProjectMember projectMember) {
        try {
            projectMemberService.saveChangeProjectMemeber(projectMember);
        } catch (BusinessException e) {
            HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/setProjectManager", name = "设置项目经理", method = RequestMethod.POST)
    public HttpResult setProjectManager(ProjectMember projectMember, Integer planId) {
        try {
            projectMemberService.saveProjectMemeber(projectMember);
            //提交到下一个阶段
            //projectBidAssist.updatePlanStatus(planId);
        } catch (Exception e) {
            HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}

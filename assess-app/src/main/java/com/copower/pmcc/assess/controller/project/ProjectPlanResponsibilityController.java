package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/31
 * @time: 10:17
 */
@Controller
@RequestMapping(value = "/ProjectPlanResponsibility")
public class ProjectPlanResponsibilityController {
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;

    @ResponseBody
    @RequestMapping(value = "/getProjectPlanResponsibilityListByCurruser", name = "取得当前责任人工作任务", method = RequestMethod.GET)
    public HttpResult getProjectPlanResponsibilityListByCurruser() {
        List<ProjectResponsibilityDto> projectTaskListByUserAccount = bpmRpcProjectTaskService.getProjectTaskListByUserAccount(serviceComponent.getThisUser());
        return HttpResult.newCorrectResult(projectTaskListByUserAccount);
    }
}

package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.api.dto.AttachmentVo;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.BpmProcessMapDto;
import com.copower.pmcc.bpm.api.dto.ProcessGroupDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessMapService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysWorkPredictDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/12/18 15:34
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcProcessMapService bpmRpcProcessMapService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("main");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getworkPredict", name = "取当前责任的预报工作事项", method = RequestMethod.GET)
    public HttpResult getworkPredict() {

        List<SysWorkPredictDto> sysWorkPredict = erpRpcToolsService.getSysWorkPredict(commonService.thisUserAccount(), "", false);

        if (CollectionUtils.isNotEmpty(sysWorkPredict)) {
            return HttpResult.newCorrectResult(sysWorkPredict);
        } else {
            return HttpResult.newErrorResult("");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUserProcessList", name = "取当前责任人的流程地图", method = RequestMethod.POST)
    public HttpResult getUserProcessList() {
        List<BpmProcessMapDto> processMap = bpmRpcProcessMapService.getUserProcessList(commonService.thisUserAccount());
        return HttpResult.newCorrectResult(processMap);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyProcessGroup", method = RequestMethod.GET)
    public HttpResult getMyProcessGroup() {

        List<ProcessGroupDto> myProcessGroup = bpmRpcProcessInsManagerService.getMyProcessGroup("", "");
        return HttpResult.newCorrectResult(myProcessGroup);
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectList", name = "取得所有项目信息", method = RequestMethod.GET)
    public BootstrapTableVo getProjectList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo projectInfoByStatus = erpRpcProjectService.getProjectInfoByStatus(requestBaseParam.getOffset(), requestBaseParam.getLimit(), "", requestBaseParam.getSearch());
        return projectInfoByStatus;
    }

}

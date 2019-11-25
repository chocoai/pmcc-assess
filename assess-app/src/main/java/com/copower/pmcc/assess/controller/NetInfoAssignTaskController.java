package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.service.NetInfoAssignTaskService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: huhao
 * @data: 2018-9-3
 */
@Controller
@RequestMapping(value = "/netInfoAssignTask")
public class NetInfoAssignTaskController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private NetInfoAssignTaskService netInfoAssignTaskService;

    @RequestMapping(value = "/apply", name = "拍卖信息补充申请")
    public ModelAndView apply(String ids) throws BusinessException {
        NetInfoAssignTask netInfoAssignTask = new NetInfoAssignTask();
        netInfoAssignTask.setNetInfoIds(ids);
        netInfoAssignTask.setCreator(commonService.thisUserAccount());
        netInfoAssignTaskService.addNetInfoAssignTask(netInfoAssignTask);
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.NET_INFO_COMPLEMENT_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoAssignTaskApply", boxReDto.getId());
        modelAndView.addObject("netInfoAssignTask", netInfoAssignTask);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "拍卖信息补充申请提交")
    public HttpResult applyCommit(Integer id) {
        try {
            netInfoAssignTaskService.applyCommit(id, BaseParameterEnum.NET_INFO_COMPLEMENT_PROCESS_KEY);
        } catch (BusinessException e) {
            log.error("修改项目信息异常", e);
            e.printStackTrace();
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approvalView", name = "拍卖信息补充审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoAssignTaskApproval", processInsId, boxId, taskId, agentUserAccount);
        NetInfoAssignTask data = netInfoAssignTaskService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("netInfoAssignTask", data);
        return modelAndView;
    }

    @RequestMapping(value = "/detailView", name = "详情页", method = RequestMethod.GET)
    public ModelAndView detailsView(Integer boxId, String processInsId) {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @RequestMapping(value = "/approvalCommit", name = "审批提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            netInfoAssignTaskService.approvalCommit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("提交失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("net/netInfoAssignTaskApply", processInsId, boxId, taskId, agentUserAccount);
        NetInfoAssignTask data = netInfoAssignTaskService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("netInfoAssignTask", data);
        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(String businessDataJson, ApprovalModelDto approvalModelDto) {
        try {
            //Integer id = JSON.parseObject(businessDataJson, Integer.class);
            netInfoAssignTaskService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/landList", name = "取得土地信息", method = RequestMethod.GET)
    public BootstrapTableVo landList(String ids) {
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        return netInfoAssignTaskService.getNetInfoRecordLandList(integers);
    }
    @ResponseBody
    @RequestMapping(value = "/houseList", name = "取得房产信息", method = RequestMethod.GET)
    public BootstrapTableVo houseList(String ids) {
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        return netInfoAssignTaskService.getNetInfoRecordHouseList(integers);
    }
}

package com.copower.pmcc.assess.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.funi.FuniHousesDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.FuniHouses;
import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;
import com.copower.pmcc.assess.dto.output.TaskCaseAssignVo;
import com.copower.pmcc.assess.service.TaskCaseAssignService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/taskCaseAssign")
public class TaskCaseAssignController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TaskCaseAssignService taskCaseAssignService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private FuniHousesDao funiHousesDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/taskCaseAssign/taskCaseAssign";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> methods = baseDataDicService.getCacheDataDicList("data.evaluation.method");
        modelAndView.addObject("methods", methods);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesList", name = "取得楼盘信息", method = RequestMethod.GET)
    public BootstrapTableVo getHousesList(String province, String city, String district, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        FuniHouses funiHouses = new FuniHouses();
        List<FuniHouses> funiHousesList = Lists.newArrayList();
        if ("25".equals(province) && "321".equals(city)) {
            funiHousesList = funiHousesDao.getNotCompleteList(funiHouses, name);
        }
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(funiHousesList) ? new ArrayList<FuniHouses>() : funiHousesList);
        return bootstrapTableVo;
    }


    @RequestMapping(value = "/applyIndex", name = "申请页面", method = RequestMethod.GET)
    public ModelAndView applyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskCaseAssign/applyIndex", "0", 0, "0", "");
        SysUserDto thisUser = commonService.thisUser();
        modelAndView.addObject("thisUser", thisUser);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/applySubmit", name = "申请提交", method = RequestMethod.POST)
    public HttpResult basicApplySubmit(String formData) {
        try {
            TaskCaseAssign taskCaseAssign = JSON.parseObject(formData, TaskCaseAssign.class);
            Integer id = taskCaseAssignService.saveTaskCaseAssign(taskCaseAssign);
            //发起流程
            taskCaseAssignService.processStartSubmit(id);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskCaseAssign/approval", processInsId, boxId, taskId, agentUserAccount);
        try {
            TaskCaseAssign assign = taskCaseAssignService.getTaskCaseAssignByProcessInsId(processInsId);
            modelAndView.addObject("master", taskCaseAssignService.getTaskCaseAssignVo(assign));
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/approvalSubmit", name = "审批页面 提交")
    public HttpResult basicApprovalSubmit(ApprovalModelDto approvalModelDto, String executor) {
        try {
            if (approvalModelDto.getCurrentStep() == 0) {
                List<String> nextApproval = Lists.newArrayList();
                nextApproval.add(executor);
                approvalModelDto.setNextApproval(nextApproval);
            }
            taskCaseAssignService.processApprovalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/applyEdit", name = "返回修改页面", method = RequestMethod.GET)
    public ModelAndView applyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/taskCaseAssign/applyEdit", processInsId, boxId, taskId, agentUserAccount);
        try {
            TaskCaseAssign assign = taskCaseAssignService.getTaskCaseAssignByProcessInsId(processInsId);
            modelAndView.addObject("master", taskCaseAssignService.getTaskCaseAssignVo(assign));
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editSubmit", name = "案例返回修改 提交")
    public HttpResult editSubmit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            TaskCaseAssign taskCaseAssign = JSON.parseObject(formData, TaskCaseAssign.class);
            taskCaseAssignService.saveTaskCaseAssign(taskCaseAssign);
            taskCaseAssignService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("案例申请返回修改提交异常");
        }
    }


    @RequestMapping(value = "/basicApplyIndex", name = "楼盘录入 初始", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex(String lpName, String assignId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        //删除 所有 与 当前用户相关的临时数据
        try {
            basicEstateService.clearInvalidData(0);
            basicBuildingService.clearInvalidData(0);
            basicUnitService.clearInvalidData(0);
            basicHouseService.clearInvalidData(0);
            basicEstateTaggingService.clearInvalidData(0);

        } catch (Exception e1) {
            logger.error("清除数据异常", e1);
        }

        TaskCaseAssignVo assign = taskCaseAssignService.getByTaskCaseAssignId(Integer.valueOf(assignId));
        //设置初始参数
        BasicApply basicApply = new BasicApply();
        basicApply.setId(0);
        basicApply.setEstateName(lpName);
        modelAndView.addObject("basicApply", basicApply);
        modelAndView.addObject("province", assign.getProvince());
        modelAndView.addObject("city", assign.getCity());
        return modelAndView;
    }


    @RequestMapping(value = "/getTaskCaseAssignList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getExamineEstateNetworkList(String executor) {
        BootstrapTableVo vo = null;
        try {
            if (StringUtils.isEmpty(executor)) {
                executor = processControllerComponent.getThisUser();
            }
            vo = taskCaseAssignService.getListVos(executor);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

}

package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:38
 * @Description:土地级别维护
 */
@RequestMapping(value = "/dataLandLevel")
@RestController
public class DataLandLevelController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataLandLevelView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @GetMapping(value = "/submitDataLandLevel", name = "发起流程")
    public HttpResult submit(String formData) {
        try {
            DataLandLevel dataLandLevel = JSONObject.parseObject(formData, DataLandLevel.class);
            dataLandLevelService.submit(dataLandLevel);
            return HttpResult.newCorrectResult(200, dataLandLevel);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @GetMapping(value = "/comeInLandLevelApprovalSubmit", name = "审批提交")
    public HttpResult comeInLandLevelApprovalSubmit(ApprovalModelDto approvalModelDto, String blockName, Boolean writeBackBlockFlag) {
        try {
            dataLandLevelService.comeInLandLevelApprovalSubmit(approvalModelDto, blockName, writeBackBlockFlag);
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/comeInLandLevelEditSubmit", name = "编辑提交 (修改提交)")
    public HttpResult comeInLandLevelEditSubmit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            dataLandLevelService.comeInLandLevelEditSubmit(approvalModelDto, formData);
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @GetMapping(value = "/comeInLandLevelApproval", name = "审批页面")
    public ModelAndView comeInLandLevelApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/data/landModelDir/comeInLandLevelApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject(StringUtils.uncapitalize(DataLandLevel.class.getSimpleName()), dataLandLevelService.getByProcessInsId(processInsId));
        return modelAndView;
    }


    @GetMapping(value = "/comeInLandLevelEdit", name = "流程修改页面")
    public ModelAndView comeInLandLevelEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/data/landModelDir/comeInLandLevelEdit", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject(StringUtils.uncapitalize(DataLandLevel.class.getSimpleName()), dataLandLevelService.getByProcessInsId(processInsId));
        return modelAndView;
    }

    @RequestMapping(value = "/getDataLandLevelListVos", method = {RequestMethod.GET}, name = "获取土地级别维护列表")
    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel) {
        return dataLandLevelService.getDataLandLevelListVos(dataLandLevel);
    }

    @RequestMapping(value = "/deleteDataLandLevelById", method = {RequestMethod.POST}, name = "删除土地级别维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                int count = dataLandLevelDetailService.getCountByLandLevelId(id);
                if (count > 0) {
                    return HttpResult.newErrorResult("请先删除明细中的数据");
                }
                DataLandLevel dataLandLevel = new DataLandLevel();
                dataLandLevel.setId(id);
                dataLandLevelService.removeDataLandLevel(dataLandLevel);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataLandLevel", method = {RequestMethod.POST}, name = "更新土地级别维护")
    public HttpResult saveAndUpdate(String formData) {
        try {
            DataLandLevel dataLandLevel = JSON.parseObject(formData, DataLandLevel.class);
            dataLandLevelService.saveAndUpdateDataLandLevel(dataLandLevel);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @RequestMapping(value = "/getDataLandLevelDetailList", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public BootstrapTableVo getDataLandLevelDetailList(Integer landLevelId) {
        return dataLandLevelDetailService.getDataLandLevelDetailList(landLevelId);
    }

    @RequestMapping(value = "/getDataLandLevelDetailListByArea", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public BootstrapTableVo getDataLandLevelDetailListByArea(String province, String city, String district) {
        return dataLandLevelDetailService.getDataLandLevelDetailListByArea(province, city, district);
    }

    @RequestMapping(value = "/saveAndUpdateDataLandLevelDetail", method = {RequestMethod.POST}, name = "保存土地级别信息")
    public HttpResult saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        try {
            dataLandLevelDetailService.saveAndUpdateDataLandLevelDetail(dataLandLevelDetail);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @PostMapping(value = "/getDataLandLevelDetailLevel", name = "获取层级")
    public HttpResult getReportFieldLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataLandLevelDetailService.getDataLandLevelDetailLevel(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @RequestMapping(value = "/removeDataLandLevelDetail", method = {RequestMethod.POST}, name = "删除土地级别信息")
    public HttpResult removeDataLandLevelDetail(Integer id) {
        try {
            dataLandLevelDetailService.removeDataLandLevelDetail(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("删除异常");
        }
    }


}

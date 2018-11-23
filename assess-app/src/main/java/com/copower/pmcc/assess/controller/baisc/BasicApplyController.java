package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-10-24.
 * 案例基础数据
 */
@Controller
@RequestMapping("/basicApply")
public class BasicApplyController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;

    @RequestMapping(value = "/basicApplyIndex", name = "案例基础数据 初始", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        //删除 所有 与 当前用户相关的临时数据
        try {
            basicEstateService.initUpdateSon(0, null, null);
            basicBuildingService.clearInvalidData();
            basicUnitService.initUpdateSon(0, null, null);
            basicHouseService.init(0, null, null);
        } catch (Exception e1) {
            log.error("清除数据异常", e1);
        }

        //设置初始参数
        BasicApply basicApply = new BasicApply();
        basicApply.setId(0);
        basicApply.setCaseEstateId(0);
        basicApply.setCaseBuildingMainId(0);
        basicApply.setCaseUnitId(0);
        basicApply.setCaseHouseId(0);
        basicApply.setEstatePartInFlag(false);
        basicApply.setBuildingPartInFlag(false);
        basicApply.setUnitPartInFlag(false);
        basicApply.setHousePartInFlag(false);
        modelAndView.addObject("basicApply", basicApply);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApplySubmit", name = "案例数据申请 提交", method = RequestMethod.POST)
    public HttpResult basicApplySubmit(String formData) {
        try {
            BasicApply basicApply = publicBasicService.saveBasic(formData, false);
            //发起流程
            basicApplyService.sumTask(basicApply, FormatUtils.entityNameConvertToTableName(BasicApply.class));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/basicApplyApproval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
            this.setViewParam(basicApply, modelAndView, "");
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApprovalSubmit", name = "审批页面 提交")
    public HttpResult basicApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            publicBasicService.approvalAndWrite(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/basicApplyEdit", name = "返回修改页面", method = RequestMethod.GET)
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyEdit", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
            if (StringUtils.isNotBlank(processInsId)) {
                basicApply.setProcessInsId(processInsId);
            }
            this.setViewParam(basicApply, modelAndView, null);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicEditSubmit", name = "案例返回修改 提交")
    public HttpResult basicEditSubmit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            publicBasicService.basicEditSubmit(approvalModelDto, formData);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }



    @ResponseBody
    @RequestMapping(value = "/deleteBasicApply", name = "删除草稿数据")
    public HttpResult deleteBasicApply(Integer id) {
        try {
            basicApplyService.deleteBasicApply(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("删除草稿数据异常");
        }
    }

    /**
     * 设置参数
     *
     * @param basicApply
     * @param modelAndView
     * @param detail
     * @throws Exception
     */
    private void setViewParam(BasicApply basicApply, ModelAndView modelAndView, String detail) throws Exception {
        if (basicApply == null) {
            return;
        }
        BasicUnit basicUnit = publicBasicService.getByByAppIdBasicUnit(basicApply.getId());
        BasicEstateVo basicEstateVo = publicBasicService.getByAppIdBasicEstate(basicApply.getId());
        BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getByAppIdEstateLandState(basicApply.getId());
        BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getByAppIdBasicHouseTrading(basicApply.getId());
        BasicHouseVo basicHouseVo = publicBasicService.getByAppIdBasicHouseVo(basicApply.getId());
        BasicBuildingMain buildingMain = publicBasicService.getByAppIdBasicBuildingMain(basicApply.getId());
        if (basicApply != null) {
            modelAndView.addObject("basicApply", basicApplyService.getBasicApplyVo(basicApply));
        }
        modelAndView.addObject("basicEstate", basicEstateVo);
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
        modelAndView.addObject("basicBuildingMain", buildingMain);
        modelAndView.addObject("basicUnit", basicUnit);
        modelAndView.addObject("basicHouse", basicHouseVo);
        modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
        List<BasicBuilding> basicBuildingList = null;
        if (buildingMain != null) {
            basicBuildingList = publicBasicService.getMainById(buildingMain);
            modelAndView.addObject("basicBuildingList", basicBuildingList);
        }
    }

    @RequestMapping(value = "/basicAppListView", name = "我的提交页面", method = {RequestMethod.GET})
    public ModelAndView basicAppListView() {
        String view = "/basic/basicAppListView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", name = "我的提交数据", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(String estateName) {
        BootstrapTableVo vo = basicApplyService.getBootstrapTableVo(estateName, false);
        return vo;
    }

    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) {
        String view = "/basic/basicAppDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(id);
            this.setViewParam(basicApply, modelAndView, null);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/basicApplyStart", name = "案例草稿申请", method = RequestMethod.GET)
    public ModelAndView basicApplyStart(Integer applyId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
        if (basicApply != null) {
            try {
                this.setViewParam(basicApply, modelAndView, "详情");
            } catch (Exception e1) {
                log.info("参数处理错误!", e1);
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveDraft", name = "保存草稿", method = {RequestMethod.POST})
    public HttpResult saveDraft(String formData) {
        try {
            publicBasicService.saveBasic(formData,true);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/basicApplyDraft", name = "我的草稿", method = RequestMethod.GET)
    public ModelAndView basicApplyDraft() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicAppDraftListView", "0", 0, "0", "");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicAppDraftList", name = "获取草稿数据列表", method = {RequestMethod.GET})
    public BootstrapTableVo getBasicAppDraftList(String estateName) {
        return basicApplyService.getBootstrapTableVo(estateName, true);
    }
}

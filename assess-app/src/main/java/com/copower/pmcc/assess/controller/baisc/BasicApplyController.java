package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyTransferService basicApplyTransferService;

    @RequestMapping(value = "/basicApplyIndex", name = "案例基础数据 初始", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        //删除 所有 与 当前用户相关的临时数据
        try {
            basicEstateService.clearInvalidData(0);
            basicBuildingService.clearInvalidData(0);
            basicUnitService.clearInvalidData(0);
            basicHouseService.clearInvalidData(0);
            basicEstateTaggingService.clearInvalidData(0);
        } catch (Exception e1) {
            log.error("清除数据异常", e1);
        }

        //设置初始参数
        BasicApply basicApply = new BasicApply();
        basicApply.setId(0);
        modelAndView.addObject("basicApply", basicApply);
        return modelAndView;
    }

    @RequestMapping(value = "/basicApplyTransfer", name = "过程数据申请进入案例库", method = RequestMethod.GET)
    public ModelAndView basicApplyTransfer(Integer planDetailsId) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyTransfer", "0", 0, "0", "");
        //1.数据复制，包含各项关联表（可参考案例复制到过程表）
        BasicApply copy = basicApplyTransferService.copy(planDetailsId);
        //2.复制过来的数据默认为草稿数据，下次进入时默认显示草稿数据
        this.setViewParam(copy, modelAndView);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApplySubmit", name = "案例数据申请 提交", method = RequestMethod.POST)
    public HttpResult basicApplySubmit(String formData) {
        try {
            BasicApply basicApply = publicBasicService.saveBasicApply(formData, false);
            //发起流程
            basicApplyService.processStartSubmit(basicApply);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/basicApplyApproval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
            this.setViewParam(basicApply, modelAndView);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApprovalSubmit", name = "审批页面 提交")
    public HttpResult basicApprovalSubmit(ApprovalModelDto approvalModelDto, String blockName, Boolean writeBackBlockFlag) {
        try {
            basicApplyService.processApprovalSubmit(approvalModelDto, blockName, writeBackBlockFlag);
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
            this.setViewParam(basicApply, modelAndView);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicEditSubmit", name = "案例返回修改 提交")
    public HttpResult basicEditSubmit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            publicBasicService.saveBasicApply(formData, false);
            basicApplyService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("案例申请返回修改提交异常");
        }
    }

    /**
     * 设置参数
     *
     * @param basicApply
     * @param modelAndView
     * @throws Exception
     */
    private void setViewParam(BasicApply basicApply, ModelAndView modelAndView) throws Exception {
        if (basicApply == null) {
            return;
        }
        BasicUnit basicUnit = publicBasicService.getBasicUnitByAppId(basicApply.getId());
        BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateByAppId(basicApply.getId());
        BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getEstateLandStateByAppId(basicApply.getId());
        BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getBasicHouseTradingByAppId(basicApply.getId());
        BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoByAppId(basicApply.getId());
        BasicBuilding basicBuilding = publicBasicService.getBasicBuildingByAppId(basicApply.getId());
        if (basicApply != null) {
            modelAndView.addObject("basicApply", basicApplyService.getBasicApplyVo(basicApply));
        }
        modelAndView.addObject("basicEstate", basicEstateVo);
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
        modelAndView.addObject("basicBuilding", basicBuilding);
        modelAndView.addObject("basicUnit", basicUnit);
        modelAndView.addObject("basicHouse", basicHouseVo);
        modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
        setHouseElementRender(basicHouseTradingVo, modelAndView);
    }

    /**
     * 设置房屋中部分元素是否渲染
     *
     * @param basicHouseTradingVo
     * @param modelAndView
     */
    private void setHouseElementRender(BasicHouseTradingVo basicHouseTradingVo, ModelAndView modelAndView) {
        if (basicHouseTradingVo == null) return;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(basicHouseTradingVo.getTransactionSituation());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseAbnormal", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_SITUATION_ABNORMAL));
        }
        baseDataDic = baseDataDicService.getCacheDataDicById(basicHouseTradingVo.getPaymentMethod());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseInstallment", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_PAYMENT_METHOD_INSTALLMENT));
        }
        baseDataDic = baseDataDicService.getCacheDataDicById(basicHouseTradingVo.getInformationType());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseInfomationOpen", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_INFORMATION_SOURCE_TYPE_OPEN));
        }
        baseDataDic = baseDataDicService.getCacheDataDicById(basicHouseTradingVo.getTradingType());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseSell", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_SELL));
            modelAndView.addObject("isHouseLease", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_LEASE));
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
            this.setViewParam(basicApply, modelAndView);
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
                this.setViewParam(basicApply, modelAndView);
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
            publicBasicService.saveBasicApply(formData, true);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存草稿异常");
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

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdate", name = "单纯的就是修改或者添加", method = RequestMethod.POST)
    public HttpResult saveAndUpdate(BasicApply basicApply) {
        try {
            if (basicApply.getId() == null || basicApply.getId().equals(0)) {
                basicApplyService.saveBasicApply(basicApply);
            } else {
                basicApplyService.updateBasicApply(basicApply);
            }
            return HttpResult.newCorrectResult(basicApply);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("单纯的就是修改或者添加异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicApplyById", name = "根据id获取", method = RequestMethod.GET)
    public HttpResult getBasicApplyById(Integer id) {
        BasicApply basicApply = basicApplyService.getByBasicApplyId(id);
        return HttpResult.newCorrectResult(basicApply);
    }

}

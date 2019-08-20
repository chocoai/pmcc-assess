package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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
@RequestMapping("/basicApplyBatch")
public class BasicApplyBatchController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicHouseService basicHouseService;

    @RequestMapping(value = "/basicBatchApplyIndex", name = "申请首页", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", 0, "0", "");
        return modelAndView;
    }

    /**
     * 获取树
     *
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTree", method = RequestMethod.POST)
    public List<ZtreeDto> getZtreeDto(Integer pid, Integer estateId) throws Exception {
        return basicApplyBatchService.getZtreeDto(pid, estateId);
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyInfo(BasicApplyBatch basicApplyBatch) {
        try {
            return HttpResult.newCorrectResult(basicApplyBatchService.saveApplyInfo(basicApplyBatch));
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveItemData", name = "保存一条明细", method = {RequestMethod.POST})
    public HttpResult saveItemData(String formData, Integer type) {
        try {
            BasicApplyBatchDetail basicApplyBatchDetail = JSON.parseObject(formData, BasicApplyBatchDetail.class);
            if (basicApplyBatchDetail.getBisStandard() == null) {
                basicApplyBatchDetail.setBisStandard(false);
            }
            return HttpResult.newCorrectResult(basicApplyBatchDetailService.addBasicApplyBatchDetail(basicApplyBatchDetail));
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    /**
     * 删除操作手册信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetail", name = "删除一条明细", method = RequestMethod.POST)
    public HttpResult deleteDetail(Integer id) throws Exception {
        try {
            basicApplyBatchDetailService.deleteBasicApplyBatchDetail(id);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 获取和编辑
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAndEditDetail", name = "获取和编辑", method = RequestMethod.GET)
    public HttpResult getAndEditDetail(Integer id) {
        return HttpResult.newCorrectResult(basicApplyBatchDetailService.getDataById(id));
    }

    @RequestMapping(value = "/fillInformation", name = "填写信息页面", method = RequestMethod.GET)
    public ModelAndView fillInformation(Integer type, Integer id, Integer buildingType, Integer estateId) throws Exception {
        String view = "/basic/fillInformation";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        this.setViewParam(type, id, buildingType, estateId, modelAndView);

        return modelAndView;

    }

    @RequestMapping(value = "/informationDetail", name = "信息详情页面", method = RequestMethod.GET)
    public ModelAndView informationDetail(Integer type, Integer id, Integer buildingType, Integer estateId) throws Exception {
        String view = "/basic/informationDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id == 0) {
            modelAndView.addObject("tableId", estateId);
            BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(estateId);
            BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getEstateLandStateByEstateId(estateId);
            modelAndView.addObject("basicEstate", basicEstateVo);
            modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
        } else {
            BasicApplyBatchDetail detailData = basicApplyBatchDetailService.getDataById(id);
            switch (detailData.getTableName()) {
                case "tb_basic_building":
                    BasicBuildingVo basicBuilding = publicBasicService.getBasicBuildingById(detailData.getTableId());
                    modelAndView.addObject("basicBuilding", basicBuilding);
                    break;
                case "tb_basic_unit":
                    BasicUnit basicUnit = publicBasicService.getBasicUnitById(detailData.getTableId());
                    modelAndView.addObject("basicUnit", basicUnit);
                    break;
                case "tb_basic_house":
                    BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(detailData.getTableId());
                    modelAndView.addObject("basicHouse", basicHouseVo);
                    BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getBasicHouseTradingByHouseId(detailData.getTableId());
                    modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                    break;
            }
            modelAndView.addObject("tableId", detailData.getTableId());
        }
        this.setViewParam(type, id, buildingType, estateId, modelAndView);
        return modelAndView;

    }

    @ResponseBody
    @RequestMapping(value = "/saveDraft", name = "保存楼盘等")
    public HttpResult saveDraft(String formData) {
        try {
            basicApplyBatchService.saveDraft(formData);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicApplyBatchSubmit", name = "数据申请 提交", method = RequestMethod.POST)
    public HttpResult basicApplyBatchSubmit(Integer id) {
        try {
            //发起流程
            basicApplyBatchService.processStartSubmit(id);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApprovalSubmit", name = "审批页面 提交")
    public HttpResult basicApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            basicApplyBatchService.processApprovalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }


    @RequestMapping(value = "/applyEdit", name = "返回修改页面", method = RequestMethod.GET)
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/editSubmit", name = "返回修改 提交")
    public HttpResult basicEditSubmit(ApprovalModelDto approvalModelDto) {
        try {
            basicApplyBatchService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("返回修改提交异常");
        }
    }

    @RequestMapping(value = "/basicBatchAppDraftListView", name = "草稿页面", method = {RequestMethod.GET})
    public ModelAndView basicAppListView() {
        String view = "/basic/basicBatchAppDraftListView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBatchApply", name = "删除草稿数据")
    public HttpResult deleteBasicBatchApply(Integer id) {
        try {
            basicApplyBatchService.deleteBasicBatchApply(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("删除草稿数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicAppBatchDraftList", name = "获取草稿数据列表", method = {RequestMethod.GET})
    public BootstrapTableVo getBasicAppBatchDraftList(String estateName) throws Exception {
        return basicApplyBatchService.getBootstrapTableVo(estateName, true);
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyDraftInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyDraftInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatch.setDraftFlag(true);
            return HttpResult.newCorrectResult(basicApplyBatchService.saveApplyInfo(basicApplyBatch));
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @RequestMapping(value = "/applyAgain", name = "重新申请", method = RequestMethod.GET)
    public ModelAndView applyAgain(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", 0, "0", "");
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getInfoById(id);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    /**
     * 设置参数
     *
     * @param modelAndView
     * @throws Exception
     */
    private void setViewParam(Integer type, Integer id, Integer buildingType, Integer estateId, ModelAndView modelAndView) {
        if (buildingType == 0)
            modelAndView.addObject("buildingType", "estate");
        if (buildingType == 1)
            modelAndView.addObject("buildingType", "building");
        if (buildingType == 2)
            modelAndView.addObject("buildingType", "unit");
        if (buildingType == 3)
            modelAndView.addObject("buildingType", "house");

        if (id == 0) {
            BasicApplyBatch applyBatch = new BasicApplyBatch();
            applyBatch.setEstateId(estateId);
            BasicApplyBatch singleData = basicApplyBatchService.getSingleData(applyBatch);
            modelAndView.addObject("tableId", singleData.getEstateId());
        } else {
            BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
            basicApplyBatchDetail.setId(id);
            BasicApplyBatchDetail detailData = basicApplyBatchDetailService.getSingleData(basicApplyBatchDetail);
            modelAndView.addObject("tableId", detailData.getTableId());
        }
        BasicApply basicApply = new BasicApply();
        basicApply.setType(type);
        modelAndView.addObject("type", type);
        modelAndView.addObject("basicApply", basicApply);
        modelAndView.addObject("isApplyBatch", "show");
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
    }

}

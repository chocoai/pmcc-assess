package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
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
    private ErpAreaService erpAreaService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private TemporaryBasicService temporaryBasicService;
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
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        //可选的执业部门
        modelAndView.addObject("departmentAssess", erpRpcDepartmentService.getDepartmentAssess());
        //删除 所有 与 当前用户相关的临时数据
        try {
            basicEstateService.initUpdateSon(0, null, null);
            basicBuildingService.init(0, null, null, null);
            basicUnitService.initUpdateSon(0, null, null);
            basicHouseService.init(0, null, null);
        } catch (Exception e1) {
            log.error("清除数据异常",e1);
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
    @RequestMapping(value = "/basicApplySubmit", name = "案例基础数据 提交", method = RequestMethod.POST)
    public HttpResult basicApplySubmit(String formData, Boolean bisNextUser) {
        try {
            publicBasicService.saveBasic(formData, bisNextUser);
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
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        //可选的执业部门
        modelAndView.addObject("departmentAssess", erpRpcDepartmentService.getDepartmentAssess());
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
        modelAndView.addObject("processInsId", basicApply.getProcessInsId());
        BasicUnit basicUnit = publicBasicService.getByByAppIdBasicUnit(basicApply.getId());
        BasicEstateVo basicEstateVo = publicBasicService.getByAppIdBasicEstate(basicApply.getId());
        BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getByAppIdEstateLandState(basicApply.getId());
        BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getByAppIdBasicHouseTrading(basicApply.getId());
        BasicHouseVo basicHouseVo = publicBasicService.getByAppIdBasicHouseVo(basicApply.getId());
        BasicBuildingMain buildingMain = publicBasicService.getByAppIdBasicBuildingMain(basicApply.getId());
        //详情设置参数
        if (StringUtils.isNotBlank(detail)) {
            if (basicApply != null) {
                basicApply.setId(null);
                modelAndView.addObject("basicApply", basicApply);
            }
            if (basicEstateVo != null) {
                basicEstateVo.setId(null);
                modelAndView.addObject("basicEstate", basicEstateVo);
                modelAndView.addObject("basicEstateJson", JSONObject.toJSONString(basicEstateVo));
            }
            if (basicEstateLandStateVo != null) {
                basicEstateLandStateVo.setId(null);
                modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
                modelAndView.addObject("basicEstateLandStateJson", JSONObject.toJSONString(basicEstateLandStateVo));
            }
            if (buildingMain != null) {
                buildingMain.setId(null);
                modelAndView.addObject("basicBuildingMain", buildingMain);
            }
            if (basicUnit != null) {
                basicUnit.setId(null);
                modelAndView.addObject("basicUnit", basicUnit);
            }
            if (basicHouseVo != null) {
                basicHouseVo.setId(null);
                modelAndView.addObject("basicHouse", basicHouseVo);
                BasicHouseVo houseVo = new BasicHouseVo();
                BeanUtils.copyProperties(basicHouseVo,houseVo);
                houseVo.setHuxingName(null);
                modelAndView.addObject("basicHouseJson", JSONObject.toJSONString(houseVo));
            }
            if (basicHouseTradingVo != null) {
                basicHouseTradingVo.setId(null);
                modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                modelAndView.addObject("basicHouseTradingJson", JSONObject.toJSONString(basicHouseTradingVo));
            }
        }
        //非详情
        if (StringUtils.isEmpty(detail)) {
            if (basicApply != null) {
                modelAndView.addObject("basicApply", basicApply);
            }
            if (basicEstateVo != null) {
                modelAndView.addObject("basicEstate", basicEstateVo);
                modelAndView.addObject("basicEstateJson", JSONObject.toJSONString(basicEstateVo));
            }
            if (basicEstateLandStateVo != null) {
                modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
                modelAndView.addObject("basicEstateLandStateJson", JSONObject.toJSONString(basicEstateLandStateVo));
            }
            if (buildingMain != null) {
                modelAndView.addObject("basicBuildingMain", buildingMain);
            }
            if (basicUnit != null) {
                modelAndView.addObject("basicUnit", basicUnit);
            }
            if (basicHouseVo != null) {
                modelAndView.addObject("basicHouse", basicHouseVo);
                BasicHouseVo houseVo = new BasicHouseVo();
                BeanUtils.copyProperties(basicHouseVo,houseVo);
                houseVo.setHuxingName(null);
                modelAndView.addObject("basicHouseJson", JSONObject.toJSONString(houseVo));
            }
            if (basicHouseTradingVo != null) {
                modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                modelAndView.addObject("basicHouseTradingJson", JSONObject.toJSONString(basicHouseTradingVo));
            }
        }
        List<BasicBuilding> basicBuildingList = null;
        if (buildingMain != null) {
            basicBuildingList = publicBasicService.getMainById(buildingMain);
            Ordering<BasicBuilding> ordering = Ordering.from(new Comparator<BasicBuilding>() {
                @Override
                public int compare(BasicBuilding o1, BasicBuilding o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            }).reverse();
            Collections.sort(basicBuildingList, ordering);
            if (!ObjectUtils.isEmpty(basicBuildingList)) {
                int num = 0;
                if (basicBuildingList.size() > 4) {
                    num = 4;
                }
                if (basicBuildingList.size() <= 4) {
                    num = basicBuildingList.size();
                }
                for (int i = 0; i < num; i++) {
                    String port = basicBuildingList.get(i).getPart().toString();
                    if (StringUtils.isEmpty(port)) {
                        continue;
                    }
                    switch (port) {
                        case "1": {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(i));
                            if (StringUtils.isEmpty(detail)) {
                                modelAndView.addObject("oneBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("oneBasicBuilding", basicBuildingVo);
                            }
                            if (StringUtils.isNotBlank(detail)) {
                                basicBuildingVo.setId(null);
                                modelAndView.addObject("oneBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("oneBasicBuilding", basicBuildingVo);
                            }
                        }
                        break;
                        case "2": {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(i));
                            if (StringUtils.isEmpty(detail)) {
                                modelAndView.addObject("twoBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("twoBasicBuilding", basicBuildingVo);
                            }
                            if (StringUtils.isNotBlank(detail)) {
                                basicBuildingVo.setId(null);
                                modelAndView.addObject("twoBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("twoBasicBuilding", basicBuildingVo);
                            }
                        }
                        break;
                        case "3": {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(i));
                            if (StringUtils.isEmpty(detail)) {
                                modelAndView.addObject("threeBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("threeBasicBuilding", basicBuildingVo);
                            }
                            if (StringUtils.isNotBlank(detail)) {
                                basicBuildingVo.setId(null);
                                modelAndView.addObject("threeBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("threeBasicBuilding", basicBuildingVo);
                            }
                        }
                        break;
                        case "4": {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(i));
                            if (StringUtils.isEmpty(detail)) {
                                modelAndView.addObject("fourBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("fourBasicBuilding", basicBuildingVo);
                            }
                            if (StringUtils.isNotBlank(detail)) {
                                basicBuildingVo.setId(null);
                                modelAndView.addObject("fourBasicBuildingJson", JSONObject.toJSONString(basicBuildingVo));
                                modelAndView.addObject("fourBasicBuilding", basicBuildingVo);
                            }
                        }
                        break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", name = "过程数据 list", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(String estateName) {
        BootstrapTableVo vo = basicApplyService.getBootstrapTableVo(estateName, false);
        return vo;
    }

    @RequestMapping(value = "/basicAppListView", name = "转到basicAppListView页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/basic/basicAppListView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        //所有带有此标识符的BasicApply 都为 临时数据
        modelAndView.addObject("pauseApply", ProjectStatusEnum.PAUSEAPPLY.getKey());
        return modelAndView;
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

    @ResponseBody
    @RequestMapping(value = "/temporary", name = "临时 提交", method = {RequestMethod.POST})
    public HttpResult temporary(String formData) {
        try {
            temporaryBasicService.saveBasic(formData);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/basicApplyStart", name = "案例数据恢复 申请", method = RequestMethod.GET)
    public ModelAndView basicApplyStart(Integer applyId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        try {
            BasicApply basicApply = temporaryBasicService.startApply(applyId);
            if (basicApply != null) {
                try {
                    this.setViewParam(basicApply, modelAndView, "详情");
                } catch (Exception e1) {
                    log.info("参数处理错误!", e1);
                }
                modelAndView.addObject("startApply", ProjectStatusEnum.STARTAPPLY.getKey());
            }
        } catch (Exception e1) {
            log.error("数据异常!", e1);
        }
        return modelAndView;
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

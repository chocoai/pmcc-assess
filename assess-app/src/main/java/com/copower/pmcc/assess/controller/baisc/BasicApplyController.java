package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BasicApplyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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

        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApplySubmit", name = "案例基础数据 提交", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(String formData, Boolean bisNextUser) {
        try {
            publicBasicService.saveBasic(formData, bisNextUser);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/basicApplyApproval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyDetail(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
            this.setViewParam(basicApply, modelAndView, "");
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectApprovalSubmit", name = "审批页面 提交")
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            publicBasicService.approvalAndWrite(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/basicApplyEdit", name = "返回修改", method = RequestMethod.GET)
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyEdit", "0", 0, "0", "");
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        //可选的执业部门
        modelAndView.addObject("departmentAssess", erpRpcDepartmentService.getDepartmentAssess());
        try {
            BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
            this.setViewParam(basicApply, modelAndView, null);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
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
        if (basicApply != null) {
            if (StringUtils.isNotBlank(detail)) {
                basicApply.setId(null);
                modelAndView.addObject("basicApply", basicApply);
                BasicUnit basicUnit = publicBasicService.getByByAppIdBasicUnit(basicApply.getId());
                if (basicUnit != null) {
                    basicUnit.setId(null);
                }
                modelAndView.addObject("basicUnit", basicUnit);
                BasicEstateVo basicEstateVo = publicBasicService.getByAppIdBasicEstate(basicApply.getId());
                if (basicEstateVo != null) {
                    basicEstateVo.setId(null);
                }
                modelAndView.addObject("basicEstate", basicEstateVo);
                BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getByAppIdEstateLandState(basicApply.getId());
                if (basicEstateLandStateVo != null) {
                    basicEstateLandStateVo.setId(null);
                }
                modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
                BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getByAppIdBasicHouseTrading(basicApply.getId());
                if (basicHouseTradingVo != null) {
                    basicHouseTradingVo.setId(null);
                }
                modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                BasicHouseVo basicHouseVo = publicBasicService.getByAppIdBasicHouseVo(basicApply.getId());
                if (basicHouseVo != null) {
                    basicHouseVo.setId(null);
                }
                modelAndView.addObject("basicHouse", basicHouseVo);
            }
            if (StringUtils.isEmpty(detail)) {
                modelAndView.addObject("basicApply", basicApply);
                modelAndView.addObject("basicUnit", publicBasicService.getByByAppIdBasicUnit(basicApply.getId()));
                modelAndView.addObject("basicEstate", publicBasicService.getByAppIdBasicEstate(basicApply.getId()));
                try {
                    modelAndView.addObject("basicEstateJson", JSONObject.toJSONString(publicBasicService.getByAppIdBasicEstate(basicApply.getId())));
                } catch (Exception e1) {
                    logger.error("json解析异常,值可能没有",e1);
                }
                modelAndView.addObject("basicEstateLandState", publicBasicService.getByAppIdEstateLandState(basicApply.getId()));
                try {
                    modelAndView.addObject("basicEstateLandStateJson", JSONObject.toJSONString(publicBasicService.getByAppIdEstateLandState(basicApply.getId())));
                } catch (Exception e1) {
                    logger.error("json解析异常,值可能没有",e1);
                }
                modelAndView.addObject("basicHouseTrading", publicBasicService.getByAppIdBasicHouseTrading(basicApply.getId()));
                try {
                    modelAndView.addObject("basicHouseTradingJson", JSONObject.toJSONString(publicBasicService.getByAppIdBasicHouseTrading(basicApply.getId())));
                } catch (Exception e1) {
                    logger.error("json解析异常,值可能没有",e1);
                }
                try {
                    modelAndView.addObject("basicHouseJson", JSONObject.toJSONString(publicBasicService.getByAppIdBasicHouseVo(basicApply.getId())));
                } catch (Exception e1) {
                    logger.error("json解析异常,值可能没有",e1);
                }
                modelAndView.addObject("basicHouse", publicBasicService.getByAppIdBasicHouseVo(basicApply.getId()));
            }
            BasicBuildingMain buildingMain = publicBasicService.getByAppIdBasicBuildingMain(basicApply.getId());
            List<BasicBuilding> basicBuildingList = null;
            if (buildingMain != null) {
                if (StringUtils.isEmpty(detail)) {
                    modelAndView.addObject("basicBuildingMain", buildingMain);
                }
                if (StringUtils.isNotBlank(detail)) {
                    buildingMain.setId(null);
                    modelAndView.addObject("basicBuildingMain", buildingMain);
                }
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
                        if (i == 0) {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(0));
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
                        if (i == 1) {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(1));
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
                        if (i == 2) {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(2));
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
                        if (i == 3) {
                            BasicBuildingVo basicBuildingVo = publicBasicService.getBasicBuildingVo(basicBuildingList.get(3));
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
                    }
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", name = "过程数据 list", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicApply basicApply) {
        if (basicApply == null) {
            basicApply = new BasicApply();
        }
        BootstrapTableVo vo = basicApplyService.getBootstrapTableVo(basicApply);
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
            logger.error(e1.getMessage(), e1);
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
            logger.error(e1.getMessage(), e1);
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
                    logger.info("参数处理错误!",e1);
                }
                modelAndView.addObject("startApply",ProjectStatusEnum.STARTAPPLY.getKey());
            }
        } catch (Exception e1) {
            logger.error("数据异常!", e1);
        }
        return modelAndView;
    }
}

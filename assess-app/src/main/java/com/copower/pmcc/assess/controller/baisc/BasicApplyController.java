package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Ordering;
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


    @RequestMapping(value = "/basicApplyIndex", name = "案例基础数据 初始", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        //可选的执业部门
        modelAndView.addObject("departmentAssess", erpRpcDepartmentService.getDepartmentAssess());
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
            this.setViewParam(processInsId, modelAndView);
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
            this.setViewParam(processInsId, modelAndView);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    /**
     * 设置参数
     *
     * @param processInsId
     * @param modelAndView
     * @throws Exception
     */
    private void setViewParam(String processInsId, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("processInsId", processInsId);
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
        if (basicApply != null) {
            modelAndView.addObject("basicApply", basicApply);
            modelAndView.addObject("basicUnit", publicBasicService.getByByAppIdBasicUnit(basicApply.getId()));
            modelAndView.addObject("basicEstate", publicBasicService.getByAppIdBasicEstate(basicApply.getId()));
            modelAndView.addObject("basicEstateLandState", publicBasicService.getByAppIdEstateLandState(basicApply.getId()));
            modelAndView.addObject("basicHouseTrading", publicBasicService.getByAppIdBasicHouseTrading(basicApply.getId()));
            modelAndView.addObject("basicHouse", publicBasicService.getByAppIdBasicHouseVo(basicApply.getId()));
            BasicBuildingMain buildingMain = publicBasicService.getByAppIdBasicBuildingMain(basicApply.getId());
            List<BasicBuilding> basicBuildingList = null;
            if (buildingMain != null) {
                modelAndView.addObject("basicBuildingMain", buildingMain);
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
                            modelAndView.addObject("oneBasicBuildingJson", JSONObject.toJSONString(publicBasicService.getBasicBuildingVo(basicBuildingList.get(0))));
                            modelAndView.addObject("oneBasicBuilding", basicBuildingList.get(0));
                        }
                        if (i == 1) {
                            modelAndView.addObject("twoBasicBuildingJson", JSONObject.toJSONString(publicBasicService.getBasicBuildingVo(basicBuildingList.get(1))));
                            modelAndView.addObject("twoBasicBuilding", basicBuildingList.get(1));
                        }
                        if (i == 2) {
                            modelAndView.addObject("threeBasicBuildingJson", JSONObject.toJSONString(publicBasicService.getBasicBuildingVo(basicBuildingList.get(2))));
                            modelAndView.addObject("threeBasicBuilding", basicBuildingList.get(2));
                        }
                        if (i == 3) {
                            modelAndView.addObject("fourBasicBuildingJson", JSONObject.toJSONString(publicBasicService.getBasicBuildingVo(basicBuildingList.get(3))));
                            modelAndView.addObject("fourBasicBuilding", basicBuildingList.get(3));
                        }
                    }
                }
            }
        }
    }


}

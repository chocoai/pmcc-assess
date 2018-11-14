package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipment;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseHouseCorollaryEquipmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 14:57
 * @Description:
 */
@RequestMapping(value = "/caseHouseCorollaryEquipment")
@Controller
public class CaseHouseCorollaryEquipmentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseCorollaryEquipmentById", method = {RequestMethod.GET}, name = "获取配套设备设施")
    public HttpResult getById(Integer id) {
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = null;
        try {
            if (id != null) {
                caseHouseCorollaryEquipment = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouseCorollaryEquipment);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseCorollaryEquipmentList", method = {RequestMethod.GET}, name = "配套设备设施列表")
    public BootstrapTableVo getCaseHouseCorollaryEquipmentList(Integer houseId) {
        BootstrapTableVo vo = null;
        try {
            CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
            if (!ObjectUtils.isEmpty(houseId)) {
                caseHouseCorollaryEquipment.setHouseId(houseId);
            }
            vo = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentLists(caseHouseCorollaryEquipment);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseCorollaryEquipmentById", method = {RequestMethod.POST}, name = "删除配套设备设施")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(caseHouseCorollaryEquipmentService.deleteCaseHouseCorollaryEquipment(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouseCorollaryEquipment", method = {RequestMethod.POST}, name = "更新配套设备设施")
    public HttpResult save(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        try {
            if (caseHouseCorollaryEquipment.getId() == null || caseHouseCorollaryEquipment.getId().equals(0)) {
                caseHouseCorollaryEquipmentService.addCaseHouseCorollaryEquipment(caseHouseCorollaryEquipment);
            } else {
                caseHouseCorollaryEquipmentService.updateCaseHouseCorollaryEquipment(caseHouseCorollaryEquipment);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}

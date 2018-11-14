package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipment;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseHouseEquipmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:44
 * @Description:
 */
@RequestMapping(value = "/caseHouseEquipment")
@Controller
public class CaseHouseEquipmentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseEquipmentService caseHouseEquipmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseEquipmentById",method = {RequestMethod.GET},name = "获取设备包含（空调、新风、供暖）")
    public HttpResult getById(Integer id) {
        CaseHouseEquipment caseHouseEquipment = null;
        try {
            if (id!=null){
                caseHouseEquipment = caseHouseEquipmentService.getCaseHouseEquipmentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouseEquipment);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseEquipmentList",method = {RequestMethod.GET},name = "设备包含（空调、新风、供暖）列表")
    public BootstrapTableVo getCaseHouseEquipmentList( String type,Integer houseId) {
        BootstrapTableVo vo = null;
        try {
            CaseHouseEquipment caseHouseEquipment = new CaseHouseEquipment();
            if (houseId != null){
                caseHouseEquipment.setHouseId(houseId);
            }
            if (!StringUtils.isEmpty(type)){
                caseHouseEquipment.setType(type);
            }
            vo = caseHouseEquipmentService.getCaseHouseEquipmentLists(caseHouseEquipment);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseEquipmentById",method = {RequestMethod.POST},name = "删除设备包含（空调、新风、供暖）")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseHouseEquipmentService.deleteCaseHouseEquipment(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouseEquipment",method = {RequestMethod.POST},name = "更新设备包含（空调、新风、供暖）")
    public HttpResult save(CaseHouseEquipment caseHouseEquipment){
        try {
            if (caseHouseEquipment.getId()==null || caseHouseEquipment.getId().equals(0)){
                caseHouseEquipmentService.addCaseHouseEquipment(caseHouseEquipment);
            }else {
                caseHouseEquipmentService.updateCaseHouseEquipment(caseHouseEquipment);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}

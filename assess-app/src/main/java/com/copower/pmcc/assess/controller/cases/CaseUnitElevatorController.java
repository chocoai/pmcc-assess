package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevator;
import com.copower.pmcc.assess.service.cases.CaseUnitElevatorService;
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

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:43
 * @Description:
 */
@RequestMapping(value = "/caseUnitElevator")
@Controller
public class CaseUnitElevatorController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CommonService commonService;
    @ResponseBody
    @RequestMapping(value = "/getCaseUnitElevatorById",method = {RequestMethod.GET},name = "获取配备电梯")
    public HttpResult getById(Integer id) {
        CaseUnitElevator caseUnitElevator = null;
        try {
            if (id!=null){
                caseUnitElevator = caseUnitElevatorService.getUnitElevatorById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseUnitElevator);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseUnitElevatorList",method = {RequestMethod.GET},name = "获取配备电梯列表")
    public BootstrapTableVo getCaseUnitElevatorList(Integer unitId) {
        BootstrapTableVo vo = null;
        try {
            CaseUnitElevator caseUnitElevator = new CaseUnitElevator();
            if (!ObjectUtils.isEmpty(unitId)){
                caseUnitElevator.setUnitId(unitId);
            }
            vo = caseUnitElevatorService.getCaseUnitElevatorList(caseUnitElevator);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseUnitElevatorById",method = {RequestMethod.POST},name = "删除配备电梯")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseUnitElevatorService.deleteUnitElevator(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseUnitElevator",method = {RequestMethod.POST},name = "更新配备电梯")
    public HttpResult save(CaseUnitElevator caseUnitElevator){
        try {
            if (caseUnitElevator.getId()==null || caseUnitElevator.getId().equals(0)){
                caseUnitElevatorService.saveCaseUnitElevator(caseUnitElevator);
            }else {
                caseUnitElevatorService.updateUnitElevator(caseUnitElevator);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}

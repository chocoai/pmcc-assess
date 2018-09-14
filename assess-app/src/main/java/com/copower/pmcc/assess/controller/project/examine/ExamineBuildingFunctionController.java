package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingFunction;
import com.copower.pmcc.assess.service.project.examine.ExamineBuildingFunctionService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/9/7 11:56
 * @Description:建筑功能
 */
@RequestMapping(value = "/examineBuildingFunction")
@Controller
public class ExamineBuildingFunctionController {
    @Autowired
    private ExamineBuildingFunctionService examineBuildingFunctionService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingFunctionById",method = {RequestMethod.GET},name = "获取建筑功能")
    public HttpResult getById(Integer id) {
        ExamineBuildingFunction examineBuildingFunction = null;
        try {
            if (id!=null){
                examineBuildingFunction = examineBuildingFunctionService.getExamineBuildingFunctionById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuildingFunction);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingFunctionList",method = {RequestMethod.GET},name = "获取建筑功能列表")
    public BootstrapTableVo getExamineEstateNetworkList(String buildNumber,Integer planDetailsId,Integer examineType,Integer declareId,Integer buildingId) {
        ExamineBuildingFunction examineBuildingFunction = new ExamineBuildingFunction();
        BootstrapTableVo vo = null;
        try {
            if (!StringUtils.isEmpty(buildNumber)){
                examineBuildingFunction.setBuildNumber(buildNumber);
            }
            if (planDetailsId != null){
                examineBuildingFunction.setPlanDetailsId(planDetailsId);
            }
            if (examineType != null){
                examineBuildingFunction.setExamineType(examineType);
            }
            if (declareId != null){
                examineBuildingFunction.setDeclareId(declareId);
            }
            if (buildingId != null){
                examineBuildingFunction.setBuildingId(buildingId);
            }
            vo = examineBuildingFunctionService.getExamineBuildingFunctionListVos(examineBuildingFunction);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineBuildingFunctionById",method = {RequestMethod.POST},name = "删除建筑功能")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                ExamineBuildingFunction examineBuildingFunction = new ExamineBuildingFunction();
                examineBuildingFunction.setId(id);
                examineBuildingFunctionService.removeExamineBuildingFunction(examineBuildingFunction);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineBuildingFunction",method = {RequestMethod.POST},name = "更新建筑功能")
    public HttpResult saveAndUpdate(ExamineBuildingFunction examineBuildingFunction){
        try {
            examineBuildingFunctionService.saveAndUpdateExamineBuildingFunction(examineBuildingFunction);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}

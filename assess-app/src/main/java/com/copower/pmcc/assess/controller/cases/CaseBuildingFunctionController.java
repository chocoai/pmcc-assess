package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunction;
import com.copower.pmcc.assess.service.cases.CaseBuildingFunctionService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * @Date: 2018/9/18 16:08
 * @Description:
 */
@RequestMapping(value = "/caseBuildingFunction")
@Controller
public class CaseBuildingFunctionController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseBuildingFunctionService caseBuildingFunctionService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingFunctionById",method = {RequestMethod.GET},name = "获取建筑功能")
    public HttpResult getById(Integer id) {
        CaseBuildingFunction caseBuildingFunction = null;
        try {
            if (id!=null){
                caseBuildingFunction = caseBuildingFunctionService.getCaseBuildingFunctionById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuildingFunction);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingFunctionList",method = {RequestMethod.GET},name = "获取建筑功能列表")
    public BootstrapTableVo getExamineEstateNetworkList(String buildNumber, Integer buildingId) {
        CaseBuildingFunction caseBuildingFunction = new CaseBuildingFunction();
        BootstrapTableVo vo = null;
        try {
            if (!StringUtils.isEmpty(buildNumber)){
                caseBuildingFunction.setBuildNumber(buildNumber);
            }
            if (buildingId != null){
                caseBuildingFunction.setBuildingId(buildingId);
            }
            caseBuildingFunction.setCreator(commonService.thisUserAccount());
            vo = caseBuildingFunctionService.getCaseBuildingFunctionListVos(caseBuildingFunction);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingFunctionById",method = {RequestMethod.POST},name = "删除建筑功能")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                CaseBuildingFunction caseBuildingFunction = new CaseBuildingFunction();
                caseBuildingFunction.setId(id);
                caseBuildingFunctionService.removeCaseBuildingFunction(caseBuildingFunction);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuildingFunction",method = {RequestMethod.POST},name = "更新建筑功能")
    public HttpResult saveAndUpdate(CaseBuildingFunction caseBuildingFunction){
        try {
            caseBuildingFunctionService.saveAndUpdateCaseBuildingFunction(caseBuildingFunction);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}

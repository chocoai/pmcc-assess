package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironment;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingEnvironmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 16:20
 * @Description:
 */
@RequestMapping(value = "/caseMatchingEnvironment")
@Controller
public class CaseMatchingEnvironmentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingEnvironmentById",method = {RequestMethod.GET},name = "获取环境因素")
    public HttpResult getById(Integer id) {
        CaseMatchingEnvironment caseMatchingEnvironment = null;
        try {
            if (id!=null){
                caseMatchingEnvironment = caseMatchingEnvironmentService.getCaseMatchingEnvironmentById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingEnvironment);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingEnvironmentList",method = {RequestMethod.GET},name = "环境因素列表")
    public BootstrapTableVo getCaseMatchingEnvironmentList( Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingEnvironment caseMatchingEnvironment = new CaseMatchingEnvironment();
            if (estateId != null){
                caseMatchingEnvironment.setEstateId(estateId);
            }
            vo = caseMatchingEnvironmentService.getCaseMatchingEnvironmentLists(caseMatchingEnvironment);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingEnvironmentById",method = {RequestMethod.POST},name = "删除环境因素")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseMatchingEnvironmentService.deleteCaseMatchingEnvironment(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingEnvironment",method = {RequestMethod.POST},name = "更新环境因素")
    public HttpResult save(CaseMatchingEnvironment caseMatchingEnvironment){
        try {
            if (caseMatchingEnvironment.getId()==null || caseMatchingEnvironment.getId().equals(0)){
                caseMatchingEnvironmentService.addCaseMatchingEnvironment(caseMatchingEnvironment);
            }else {
                caseMatchingEnvironmentService.updateCaseMatchingEnvironment(caseMatchingEnvironment);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}

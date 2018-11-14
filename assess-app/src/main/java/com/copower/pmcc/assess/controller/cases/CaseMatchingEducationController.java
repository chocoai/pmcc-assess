package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEducation;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingEducationService;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 16:19
 * @Description:
 */
@RequestMapping(value = "/caseMatchingEducation")
@Controller
public class CaseMatchingEducationController {
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingEducationById",method = {RequestMethod.GET},name = "获取教育条件")
    public HttpResult getById(Integer id) {
        CaseMatchingEducation caseMatchingEducation = null;
        try {
            if (id!=null){
                caseMatchingEducation = caseMatchingEducationService.getCaseMatchingEducationById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingEducation);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingEducationList",method = {RequestMethod.GET},name = "教育条件列表")
    public BootstrapTableVo getCaseMatchingEducationList(String name,Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingEducation caseMatchingEducation = new CaseMatchingEducation();
            if (!StringUtils.isEmpty(name)){
                caseMatchingEducation.setSchoolName(name);
            }
            if (estateId != null){
                caseMatchingEducation.setEstateId(estateId);
            }
            vo = caseMatchingEducationService.getCaseMatchingEducationLists(caseMatchingEducation);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingEducationById",method = {RequestMethod.POST},name = "删除教育条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseMatchingEducationService.deleteCaseMatchingEducation(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingEducation",method = {RequestMethod.POST},name = "更新教育条件")
    public HttpResult save(CaseMatchingEducation caseMatchingEducation){
        try {
            if (caseMatchingEducation.getId()==null || caseMatchingEducation.getId().equals(0)){
                caseMatchingEducationService.addCaseMatchingEducation(caseMatchingEducation);
            }else {
                caseMatchingEducationService.updateCaseMatchingEducation(caseMatchingEducation);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}

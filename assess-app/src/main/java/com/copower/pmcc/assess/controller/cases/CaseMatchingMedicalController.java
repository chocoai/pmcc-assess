package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedical;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingMedicalService;
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
 * @Date: 2018/9/17 16:23
 * @Description:
 */
@RequestMapping(value = "/caseMatchingMedical")
@Controller
public class CaseMatchingMedicalController {
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingMedicalById",method = {RequestMethod.GET},name = "获取医养条件")
    public HttpResult getById(Integer id) {
        CaseMatchingMedical caseMatchingMedical = null;
        try {
            if (id!=null){
                caseMatchingMedical = caseMatchingMedicalService.getCaseMatchingMedicalById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingMedical);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingMedicalList",method = {RequestMethod.GET},name = "医养条件列表")
    public BootstrapTableVo getCaseMatchingMedicalList( Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingMedical caseMatchingMedical = new CaseMatchingMedical();
            if (estateId != null){
                caseMatchingMedical.setEstateId(estateId);
            }
            vo = caseMatchingMedicalService.getCaseMatchingMedicalLists(caseMatchingMedical);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingMedicalById",method = {RequestMethod.POST},name = "删除医养条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseMatchingMedicalService.deleteCaseMatchingMedical(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingMedical",method = {RequestMethod.POST},name = "更新医养条件")
    public HttpResult save(CaseMatchingMedical caseMatchingMedical){
        try {
            if (caseMatchingMedical.getId()==null || caseMatchingMedical.getId().equals(0)){
                caseMatchingMedicalService.addCaseMatchingMedical(caseMatchingMedical);
            }else {
                caseMatchingMedicalService.updateCaseMatchingMedical(caseMatchingMedical);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}

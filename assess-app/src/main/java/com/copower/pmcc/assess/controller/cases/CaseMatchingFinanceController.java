package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinance;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingFinanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
 * @Date: 2018/9/17 16:20
 * @Description:
 */
@RequestMapping(value = "/caseMatchingFinance")
@Controller
public class CaseMatchingFinanceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingFinanceById",method = {RequestMethod.GET},name = "获取金融服务")
    public HttpResult getById(Integer id) {
        CaseMatchingFinance caseMatchingFinance = null;
        try {
            if (id!=null){
                caseMatchingFinance = caseMatchingFinanceService.getCaseMatchingFinanceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingFinance);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingFinanceList",method = {RequestMethod.GET},name = "金融服务列表")
    public BootstrapTableVo getCaseMatchingFinanceList( Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingFinance caseMatchingFinance = new CaseMatchingFinance();
            if (!ObjectUtils.isEmpty(estateId)){
                caseMatchingFinance.setEstateId(estateId);
            }

            vo = caseMatchingFinanceService.getCaseMatchingFinanceLists(caseMatchingFinance);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingFinanceById",method = {RequestMethod.POST},name = "删除金融服务")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseMatchingFinanceService.deleteCaseMatchingFinance(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingFinance",method = {RequestMethod.POST},name = "更新金融服务")
    public HttpResult save(CaseMatchingFinance caseMatchingFinance){
        try {
            if (caseMatchingFinance.getId()==null || caseMatchingFinance.getId().equals(0)){
                caseMatchingFinanceService.addCaseMatchingFinance(caseMatchingFinance);
            }else {
                caseMatchingFinanceService.updateCaseMatchingFinance(caseMatchingFinance);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_finance_category",method = {RequestMethod.GET},name = "金融类别")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_FINANCE_CATEGORY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_finance_nature",method = {RequestMethod.GET},name = "金融机构性质")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_FINANCE_NATURE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_finance_service_content",method = {RequestMethod.GET},name = "金融服务内容")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_FINANCE_SERVICE_CONTENT);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}

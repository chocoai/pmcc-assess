package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinance;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingFinanceService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/20 17:30
 * @Description:金融服务
 */
@RequestMapping(value = "/examineMatchingFinance")
@Controller
public class ExamineMatchingFinanceController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingFinanceService examineMatchingFinanceService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingFinanceById",method = {RequestMethod.GET},name = "获取金融服务")
    public HttpResult getById(Integer id) {
        ExamineMatchingFinance examineMatchingFinance = null;
        try {
            if (id!=null){
                examineMatchingFinance = examineMatchingFinanceService.getExamineMatchingFinanceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingFinance);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingFinanceList",method = {RequestMethod.GET},name = "金融服务列表")
    public BootstrapTableVo getExamineMatchingFinanceList(Integer examineType, Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingFinance examineMatchingFinance = new ExamineMatchingFinance();
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingFinance.setExamineType(examineType);
            }
            if (declareId!=null){
                examineMatchingFinance.setDeclareId(declareId);
            }
            vo = examineMatchingFinanceService.getExamineMatchingFinanceLists(examineMatchingFinance);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingFinanceById",method = {RequestMethod.POST},name = "删除金融服务")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingFinanceService.deleteExamineMatchingFinance(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingFinance",method = {RequestMethod.POST},name = "更新金融服务")
    public HttpResult save(ExamineMatchingFinance examineMatchingFinance){
        try {
            if (examineMatchingFinance.getId()==null || examineMatchingFinance.getId().equals(0)){
                examineMatchingFinanceService.addExamineMatchingFinance(examineMatchingFinance);
            }else {
                examineMatchingFinanceService.updateExamineMatchingFinance(examineMatchingFinance);
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

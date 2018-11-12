package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupply;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseEstateSupplyService;
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
 * @Date: 2018/9/17 11:38
 * @Description:
 */
@RequestMapping(value = "/caseEstateSupply")
@Controller
public class CaseEstateSupplyController {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateSupplyById",method = {RequestMethod.GET},name = "获取供应信息")
    public HttpResult getById(Integer id) {
        CaseEstateSupply caseEstateSupply = null;
        try {
            if (id!=null){
                caseEstateSupply = caseEstateSupplyService.getCaseEstateSupplyById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseEstateSupply);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateSupplyList",method = {RequestMethod.GET},name = "供应信息列表")
    public BootstrapTableVo getCaseEstateSupplyList(String name,  String type,Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseEstateSupply caseEstateSupply = new CaseEstateSupply();
            if (!StringUtils.isEmpty(name)){
                caseEstateSupply.setName(name);
            }
            if (!StringUtils.isEmpty(type)){
                caseEstateSupply.setType(type);
            }
            if (estateId != null){
                caseEstateSupply.setEstateId(estateId);
            }
            caseEstateSupply.setCreator(commonService.thisUserAccount());
            vo = caseEstateSupplyService.getExamineEstateNetworkList(caseEstateSupply);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseEstateSupplyById",method = {RequestMethod.POST},name = "删除供应信息")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseEstateSupplyService.deleteCaseEstateSupply(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseEstateSupply",method = {RequestMethod.POST},name = "更新供应信息")
    public HttpResult save(CaseEstateSupply caseEstateSupply){
        try {
            if (caseEstateSupply.getId()==null || caseEstateSupply.getId().equals(0)){
                caseEstateSupplyService.addCaseEstateSupply(caseEstateSupply);
            }else {
                caseEstateSupplyService.updateCaseEstateSupply(caseEstateSupply);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/line_water_supply_pipe_grade",method = {RequestMethod.GET},name = "线路供水管等级")
    public HttpResult estate_distance() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_LINE_WATER_SUPPLY_PIPE_GRADE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/supplier_grade",method = {RequestMethod.GET},name = "供应商等级")
    public HttpResult supplier_grade() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLIER_GRADE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

}

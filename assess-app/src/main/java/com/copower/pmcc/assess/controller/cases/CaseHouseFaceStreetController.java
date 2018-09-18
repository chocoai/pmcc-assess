package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreet;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseHouseFaceStreetService;
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
 * @Date: 2018/9/18 10:45
 * @Description:
 */
@RequestMapping(value = "/caseHouseFaceStreet")
@Controller
public class CaseHouseFaceStreetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseFaceStreetService caseHouseFaceStreetService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseFaceStreetById",method = {RequestMethod.GET},name = "获取临街（路）状况")
    public HttpResult getById(Integer id) {
        CaseHouseFaceStreet caseHouseFaceStreet = null;
        try {
            if (id!=null){
                caseHouseFaceStreet = caseHouseFaceStreetService.getCaseHouseFaceStreetById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouseFaceStreet);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseFaceStreetList",method = {RequestMethod.GET},name = "临街（路）状况列表")
    public BootstrapTableVo getCaseHouseFaceStreetList(Integer houseId) {
        BootstrapTableVo vo = null;
        try {
            CaseHouseFaceStreet caseHouseFaceStreet = new CaseHouseFaceStreet();
            if (houseId != null){
                caseHouseFaceStreet.setHouseId(houseId);
            }
            vo = caseHouseFaceStreetService.getCaseHouseFaceStreetLists(caseHouseFaceStreet);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseFaceStreetById",method = {RequestMethod.POST},name = "删除临街（路）状况")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseHouseFaceStreetService.deleteCaseHouseFaceStreet(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouseFaceStreet",method = {RequestMethod.POST},name = "更新临街（路）状况")
    public HttpResult save(CaseHouseFaceStreet caseHouseFaceStreet){
        try {
            if (caseHouseFaceStreet.getId()==null || caseHouseFaceStreet.getId().equals(0)){
                caseHouseFaceStreetService.addCaseHouseFaceStreet(caseHouseFaceStreet);
            }else {
                caseHouseFaceStreetService.updateCaseHouseFaceStreet(caseHouseFaceStreet);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_street_level",method = {RequestMethod.GET},name = "街道级别")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_STREET_LEVEL);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_traffic_flow",method = {RequestMethod.GET},name = "交通流量")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_TRAFFIC_FLOW);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_house_visitors_flowrate",method = {RequestMethod.GET},name = "人流量")
    public HttpResult environment_influence_degree() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_VISITORS_FLOWRATE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}

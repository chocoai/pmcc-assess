package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTraffic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseMatchingTrafficService;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 16:24
 * @Description:
 */
@RequestMapping(value = "/caseMatchingTraffic")
@Controller
public class CaseMatchingTrafficController {
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingTrafficById",method = {RequestMethod.GET},name = "获取交通条件")
    public HttpResult getById(Integer id) {
        CaseMatchingTraffic caseMatchingTraffic = null;
        try {
            if (id!=null){
                caseMatchingTraffic = caseMatchingTrafficService.getMatchingTrafficById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseMatchingTraffic);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseMatchingTrafficList",method = {RequestMethod.GET},name = "交通条件列表")
    public BootstrapTableVo getCaseMatchingTrafficList( String type,Integer estateId ) {
        BootstrapTableVo vo = null;
        try {
            CaseMatchingTraffic caseMatchingTraffic = new CaseMatchingTraffic();
            if (!StringUtils.isEmpty(type)){
                caseMatchingTraffic.setType(type);
            }
            if (estateId != null){
                caseMatchingTraffic.setEstateId(estateId);
            }
            vo = caseMatchingTrafficService.getCaseMatchingTrafficList(caseMatchingTraffic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseMatchingTrafficById",method = {RequestMethod.POST},name = "删除交通条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseMatchingTrafficService.deleteMatchingTraffic(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseMatchingTraffic",method = {RequestMethod.POST},name = "更新交通条件")
    public HttpResult save(CaseMatchingTraffic caseMatchingTraffic){
        try {
            if (caseMatchingTraffic.getId()==null || caseMatchingTraffic.getId().equals(0)){
                caseMatchingTrafficService.addMatchingTraffic(caseMatchingTraffic);
            }else {
                caseMatchingTrafficService.updateMatchingTraffic(caseMatchingTraffic);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_distance",method = {RequestMethod.GET},name = "获取距离")
    public HttpResult estate_distance(Integer id) {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}

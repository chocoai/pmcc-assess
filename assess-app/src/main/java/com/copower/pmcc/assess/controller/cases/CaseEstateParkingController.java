package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParking;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseEstateParkingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
 * @Date: 2018/9/17 11:32
 * @Description:
 */
@RequestMapping(value = "/caseEstateParking")
@Controller
public class CaseEstateParkingController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateParkingById",method = {RequestMethod.GET},name = "获取车位")
    public HttpResult getById(Integer id) {
        CaseEstateParking caseEstateParking = null;
        try {
            if (id!=null){
                caseEstateParking = caseEstateParkingService.getEstateParkingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseEstateParking);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateParkingList",method = {RequestMethod.GET},name = "车位列表")
    public BootstrapTableVo getCaseEstateParkingList(String location,Integer estateId) {
        BootstrapTableVo vo = null;
        try {
            CaseEstateParking caseEstateParking = new CaseEstateParking();
            if (!StringUtils.isEmpty(location)){
                caseEstateParking.setLocation(location);
            }
            if (estateId != null){
                caseEstateParking.setEstateId(estateId);
            }
            caseEstateParking.setCreator(commonService.thisUserAccount());
            vo = caseEstateParkingService.getExamineEstateNetworkList(caseEstateParking);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseEstateParkingById",method = {RequestMethod.POST},name = "删除车位")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseEstateParkingService.deleteEstateParking(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseEstateParking",method = {RequestMethod.POST},name = "更新车位")
    public HttpResult save(CaseEstateParking caseEstateParking){
        try {
            if (caseEstateParking.getId()==null || caseEstateParking.getId().equals(0)){
                caseEstateParkingService.addEstateParking(caseEstateParking);
            }else {
                caseEstateParkingService.updateEstateParking(caseEstateParking);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_car_type",method = {RequestMethod.GET},name = "获取车位类型")
    public HttpResult estate_distance(Integer id) {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_CAR_TYPE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}

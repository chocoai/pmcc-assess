package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSell;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineHouseTradingSellAndLeaseDto;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineHouseTradingSellAndLeaseDtoService;
import com.copower.pmcc.assess.service.project.examine.ExamineUnitHuxingService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/27 16:53
 * @Description:房屋
 */
@RequestMapping(value = "/examineHouse")
@Controller
public class ExamineHouseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineUnitHuxingService examineUnitHuxingService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ExamineHouseTradingSellAndLeaseDtoService examineHouseTradingSellAndLeaseDtoService;



    @ResponseBody
    @RequestMapping(value = "/saveAndUpdatetExamineHouseTradingSellAndLeaseDto",method = {RequestMethod.POST},name = "更新 出租或者出售）")
    public HttpResult save(ExamineHouseTradingSellAndLeaseDto examineHouseTradingSellAndLeaseDto){
        try {
            if (examineHouseTradingSellAndLeaseDto.getId()==null || examineHouseTradingSellAndLeaseDto.getId().equals(0)){
                examineHouseTradingSellAndLeaseDtoService.save(examineHouseTradingSellAndLeaseDto);
            }else {
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineHouseTradingSellAndLeaseDtoList",method = {RequestMethod.GET},name = "房屋出售或者房屋出租 列表")
    public BootstrapTableVo getExamineHouseFaceStreetList(String type,ExamineHouseTradingSell examineHouseTradingSell,ExamineHouseTradingLease examineHouseTradingLease) {
        BootstrapTableVo vo = null;
        try {
            if (!StringUtils.isEmpty(type)){
                vo = examineHouseTradingSellAndLeaseDtoService.getVoList(type,examineHouseTradingSell,examineHouseTradingLease);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineHouseTradingSellAndLeaseDtoById",method = {RequestMethod.POST},name = "删除房屋出售或者房屋出租")
    public HttpResult delete(Integer id,String type) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineHouseTradingSellAndLeaseDtoService.remove(type, id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseDataDicById",method = {RequestMethod.GET},name = "获取数据字典信息")
    public HttpResult getBaseDataDicById(Integer id) {
        try {
            return HttpResult.newCorrectResult(examineHouseTradingSellAndLeaseDtoService.getBaseDataDicById(id));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}

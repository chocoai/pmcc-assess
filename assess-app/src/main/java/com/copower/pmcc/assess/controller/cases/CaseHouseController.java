package com.copower.pmcc.assess.controller.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.service.cases.CaseHouseService;
import com.copower.pmcc.assess.service.cases.CaseHouseTradingService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 18:16
 * @Description:
 */
@RequestMapping(value = "/caseHouse")
@Controller
public class CaseHouseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/appView", name = "转到新增页面 ", method = RequestMethod.GET)
    public ModelAndView appView(Integer unitId) {
        String view = "/case/caseHouse/apply/caseHouseView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("unitId", unitId);
        return modelAndView;
    }

    @RequestMapping(value = "/editView", name = "转到编辑页面 ", method = RequestMethod.GET)
    public ModelAndView editView(Integer id,@RequestParam(defaultValue = "false") boolean copy) {
        String view = "/case/caseHouse/apply/caseHouseView";
        CaseHouse caseHouse = null;
        CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
        caseHouseTrading.setHouseId(id);
        List<CaseHouseTradingVo> caseHouseTradingList = caseHouseTradingService.caseHouseTradingList(caseHouseTrading);
        if (!ObjectUtils.isEmpty(caseHouseTradingList)){
            caseHouseTrading = caseHouseTradingList.get(0);
        }
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        caseHouse = caseHouseService.getCaseHouseById(id) ;
        if (copy){
            //复制数据 需要把id设为null
            caseHouse.setId(null);
            caseHouseTrading.setId(null);
            //处理附件,所有附件则把附件复制后保存后的id传入页面显示
            //附件暂且不处理
        }
        modelAndView.addObject("caseHouse",caseHouse);
        modelAndView.addObject("caseHouseTrading",caseHouseTrading);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseById", method = {RequestMethod.GET}, name = "获取案例 房屋")
    public HttpResult getCaseHouseById(Integer id) {
        CaseHouse caseHouse = null;
        try {
            if (id != null) {
                caseHouse = caseHouseService.getCaseHouseById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouse);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseList", method = {RequestMethod.GET}, name = "获取案例 房屋列表")
    public BootstrapTableVo getCaseHouseList(Integer unitId) {
        CaseHouse caseHouse = new CaseHouse();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (unitId != null) {
                caseHouse.setUnitId(unitId);
                vo = caseHouseService.getCaseHouseListVos(caseHouse);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseById", method = {RequestMethod.POST}, name = "删除案例 房屋")
    public HttpResult deleteCaseHouseById(Integer id) {
        CaseHouse caseHouse = null;
        try {
            if (id != null) {
                caseHouse = caseHouseService.getCaseHouseById(id);
                caseHouseService.deleteCaseHouse(id);
                return HttpResult.newCorrectResult(caseHouse.getUnitId());
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouse", method = {RequestMethod.POST}, name = "更新案例 房屋")
    public HttpResult saveAndUpdateCaseHouse(String formData) {
        JSONObject jsonObject = JSON.parseObject(formData);
        CaseHouse caseHouse = null;
        CaseHouseTrading caseHouseTrading = null;
        String jsonContent = null;
        try {
            try {
                jsonContent = jsonObject.getString("house");
                caseHouse = JSONObject.parseObject(jsonContent, CaseHouse.class);
                jsonContent = null;
                jsonContent = jsonObject.getString("trading");
                caseHouseTrading = JSONObject.parseObject(jsonContent, CaseHouseTrading.class);
            } catch (Exception e1) {
                logger.error(String.format("exception: %s", e1.getMessage()), e1);
                return HttpResult.newErrorResult("解析异常");
            }
            caseHouseService.saveAndUpdateCaseHouse(caseHouse);
            caseHouseTradingService.saveAndUpdateCaseHouseTrading(caseHouseTrading);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}

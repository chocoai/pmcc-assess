package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCost;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 评估原则
 * Created by 13426 on 2018/4/28.
 */
@RequestMapping(value = "/income", name = "收益法")
@Controller
public class IncomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private MdIncomeService mdIncomeService;

    @ResponseBody
    @RequestMapping(value = "/getSelfSupportCostList", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo getSelfSupportCostList(Integer supportId,Integer type) {
        return mdIncomeService.getSelfSupportCostList(supportId,type);
    }

    @ResponseBody
    @RequestMapping(value = "/saveSelfSupportCost", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult saveSelfSupportCost(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        try {
            mdIncomeService.saveSelfSupportCost(mdIncomeSelfSupportCost);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteSelfSupportCost", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeService.deleteSelfSupportCost(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}

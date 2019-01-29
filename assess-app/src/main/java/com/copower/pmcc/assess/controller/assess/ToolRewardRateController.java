package com.copower.pmcc.assess.controller.assess;

import com.copower.pmcc.assess.dal.basis.entity.ToolRewardRate;
import com.copower.pmcc.assess.service.ToolRewardRateService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2019-1-17.
 */
@Controller
@RequestMapping("/toolRewardRate")
public class ToolRewardRateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ToolRewardRateService toolRewardRateService;


    @ResponseBody
    @RequestMapping(value = "/saveToolRewardRate", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveToolRewardRate(String formData) {
        try {
            return HttpResult.newCorrectResult(toolRewardRateService.saveToolRewardRate(formData));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initForm", method = {RequestMethod.POST}, name = "保存")
    public HttpResult initObserve(Integer rewardRateId) {
        try {
            ToolRewardRate toolRewardRate = toolRewardRateService.getDataById(rewardRateId);
            return HttpResult.newCorrectResult(toolRewardRate);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

}

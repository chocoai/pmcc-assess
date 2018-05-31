package com.copower.pmcc.assess.controller.csr;

import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.CsrInvalidRule;
import com.copower.pmcc.assess.service.csr.CsrInvalidRuleService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-5-31.
 */
@Controller
@RequestMapping("/crsInvalidRule")
public class CrsInvalidRuleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CsrInvalidRuleService csrInvalidRuleService;

    /**
     * 获取列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getInvalidRuleList")
    public BootstrapTableVo getInvalidRuleList(Integer csrProjectId) {
        return csrInvalidRuleService.getInvalidRuleList(csrProjectId);
    }
    //endregion


    /**
     * 保存数据
     *
     * @param csrInvalidRule
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveInvalidRule", method = RequestMethod.POST)
    public HttpResult saveInvalidRule(CsrInvalidRule csrInvalidRule) {
        try {
            csrInvalidRuleService.saveInvalidRule(csrInvalidRule);
        } catch (Exception e) {
            logger.error("保存数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteInvalidRule", method = RequestMethod.POST)
    public HttpResult deleteInvalidRule(Integer id) {
        try {
            csrInvalidRuleService.deleteInvalidRule(id);
        } catch (Exception e) {
            logger.error("保存数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

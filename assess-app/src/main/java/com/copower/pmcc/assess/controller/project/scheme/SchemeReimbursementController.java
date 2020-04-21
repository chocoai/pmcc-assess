package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.service.project.scheme.SchemeReimbursementService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-10-25.
 */
@Controller
@RequestMapping("/schemeReimbursement")
public class SchemeReimbursementController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeReimbursementService schemeReimbursementService;


    @ResponseBody
    @RequestMapping(value = "/getSchemeReimbursementList", name = "委估对象list", method = RequestMethod.POST)
    public HttpResult getSchemeReimbursementList(Integer masterId) {
        try {
            List<SchemeReimbursementItemVo> list = schemeReimbursementService.getItemByMasterId(masterId);
            return HttpResult.newCorrectResult(list);
        } catch (Exception e) {
            logger.error("SchemeReimbursementController.getSchemeReimbursementList 获取数据异常", e);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/repeatSchemeReimbursementInit", name = "重新初始化", method = RequestMethod.POST)
    public HttpResult repeatSchemeReimbursementInit(Integer id) {
        try {
            schemeReimbursementService.repeatInit(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("重新初始化异常", e);
            return HttpResult.newErrorResult("重新初始化异常");
        }
    }

}

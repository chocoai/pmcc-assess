package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.dto.input.project.scheme.SchemeInfoDetailVDto;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 13426 on 2018/5/28.
 */
@RequestMapping(value = "/schemeInfo")
@Controller
public class SchemeInfoController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SchemeInfoService schemeInfoService;

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "保存  方案主表")
    public Object save(SchemeInfoDetailVDto detailVDto) {//SchemeInfoFormDataDto
        try {
            if (!ObjectUtils.isEmpty(detailVDto)) {
                schemeInfoService.saveChange(detailVDto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

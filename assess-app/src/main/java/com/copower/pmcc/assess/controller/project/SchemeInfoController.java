package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dto.input.project.SchemeInfoFormDataDto;
import com.copower.pmcc.assess.service.project.SchemeInfoService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 13426 on 2018/5/28.
 */
@RequestMapping(value = "/schemeInfo")
@Controller
public class SchemeInfoController {

    @Autowired
    private SchemeInfoService schemeInfoService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/save",method = {RequestMethod.POST},name = "保存  方案主表")
    public Object save(String princiPle, String hypothesis, String basis) {//SchemeInfoFormDataDto
        try {
            if (!StringUtils.isEmpty(princiPle)) {
                if (!StringUtils.isEmpty(hypothesis) && !StringUtils.isEmpty(basis)) {
                    SchemeInfoFormDataDto  p = schemeInfoService.formDataDto(princiPle);
                    SchemeInfoFormDataDto  h = schemeInfoService.formDataDto(hypothesis);
                    SchemeInfoFormDataDto  b = schemeInfoService.formDataDto(basis);
                    schemeInfoService.saveChange(p,h,b);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

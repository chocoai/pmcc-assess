package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dto.input.cases.CaseEstateTaggingDto;
import com.copower.pmcc.assess.service.cases.CaseEstateTaggingService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/7 13:46
 * @Description:
 */
@RequestMapping(value = "/caseEstateTagging")
@Controller
public class CaseEstateTaggingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;

    @ResponseBody
    @RequestMapping(value = "/queryCaseEstateTagging",method = {RequestMethod.GET},name = "获取标记列表子类")
    public HttpResult queryCaseEstateTagging(Integer dataId,String type){
        try {
            List<CaseEstateTaggingDto> dtos = caseEstateTaggingService.queryCaseEstateTagging(dataId,type) ;
            return HttpResult.newCorrectResult(ObjectUtils.isEmpty(dtos)?new ArrayList<CaseEstateTaggingDto>():dtos);
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("异常");
        }
    }

}

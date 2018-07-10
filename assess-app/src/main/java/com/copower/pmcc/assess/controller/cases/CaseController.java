package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/case")
public class CaseController {

    @ResponseBody
    @GetMapping(name = "版块信息自动补全",value = "/autoCompleteBlock")
    public HttpResult autoCompleteBlock(String term,Integer maxRows){
        List<KeyValueDto> keyValueDtos= Lists.newArrayList();
        KeyValueDto keyValueDto=new KeyValueDto();
        keyValueDto.setKey("1");
        keyValueDto.setValue("张三");
        keyValueDtos.add(keyValueDto);
        keyValueDto.setKey("2");
        keyValueDto.setValue("李四");
        keyValueDtos.add(keyValueDto);
        return HttpResult.newCorrectResult(keyValueDtos);
    }
}

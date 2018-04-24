package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 13426 on 2018/4/24.
 */
@Controller
public class MethodFieldController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier(value = "evaluationMethodService")
    @Autowired
    private EvaluationMethodService service;

    @ResponseBody
    @RequestMapping(value = "/evaluationMethod/addField",method = RequestMethod.POST,name = "新增方法字段")
    public HttpResult add(EvaluationMethodFieldDto evaluationMethodFieldDto){
        try {
            if (evaluationMethodFieldDto.getId() != null && evaluationMethodFieldDto.getId() != 0) {//不再使用专门的 update controller
                service.update(evaluationMethodFieldDto);
                return HttpResult.newCorrectResult();
            } else {
                service.add(evaluationMethodFieldDto);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/evaluationMethod/get",method = RequestMethod.POST,name = "获取方法字段")
    public Object get(Integer id){
        try {
            EvaluationMethodFieldDto evaluationMethodFieldDto = service.getField(id);
            return evaluationMethodFieldDto;
        }catch (Exception e){
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}

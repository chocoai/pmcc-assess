package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingFieldDto;
import com.copower.pmcc.assess.service.data.EvaluationThinkingFieldService;
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
 * Created by 13426 on 2018/4/27.
 */
@RequestMapping(value = "/evaluationThinkingNG")
@Controller
public class EvaluationThinkingFieldController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EvaluationThinkingFieldService service;

    @ResponseBody
    @RequestMapping(value = "/listField",method = {RequestMethod.POST,RequestMethod.GET},name = "获取列表")
    public BootstrapTableVo list(Integer methodId){
        BootstrapTableVo vo = null;
        if (methodId!=null) vo = service.listVos(methodId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除",method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            service.remove(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/addField",method = RequestMethod.POST,name = "新增方法字段")
    public HttpResult add(EvaluationThinkingFieldDto evaluationThinkingFieldDto){
        try {
            if (evaluationThinkingFieldDto.getId() != null && evaluationThinkingFieldDto.getId() != 0) {//不再使用专门的 update controller
                service.update(evaluationThinkingFieldDto);
                return HttpResult.newCorrectResult();
            } else {
                boolean flag = service.add(evaluationThinkingFieldDto);
                if (flag){
                    return HttpResult.newCorrectResult();
                }else {
                    return HttpResult.newErrorResult("添加失败");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}

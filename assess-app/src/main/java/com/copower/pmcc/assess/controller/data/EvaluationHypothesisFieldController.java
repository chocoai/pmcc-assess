package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisFieldDto;
import com.copower.pmcc.assess.service.data.EvaluationHypothesisFieldService;
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
 * 评估假设 字段
 * Created by 13426 on 2018/4/28.
 */
@RequestMapping(value = "/evaluationHypothesisNG", name = "评估假设字段")
@Controller
public class EvaluationHypothesisFieldController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EvaluationHypothesisFieldService service;

    @ResponseBody
    @RequestMapping(value = "/addField",method = RequestMethod.POST,name = "新增方法字段")
    public HttpResult add(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        try {
            if (evaluationHypothesisFieldDto.getId() != null && evaluationHypothesisFieldDto.getId() != 0) {//不再使用专门的 update controller
                service.update(evaluationHypothesisFieldDto);
                return HttpResult.newCorrectResult();
            } else {
                boolean flag = service.add(evaluationHypothesisFieldDto);
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

    @ResponseBody
    @RequestMapping(value = "/listField",method = {RequestMethod.POST,RequestMethod.GET},name = "获取列表")
    public BootstrapTableVo list(Integer hypothesisId){
        BootstrapTableVo vo = null;
        if (hypothesisId!=null) vo = service.listBoot(hypothesisId);
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

}

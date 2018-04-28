package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
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
 * Created by 13426 on 2018/4/24.
 */
@RequestMapping(value = "/evaluationMethodNG")
@Controller
public class MethodFieldController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EvaluationMethodService service;

    @ResponseBody
    @RequestMapping(value = "/addField",method = RequestMethod.POST,name = "新增方法字段")
    public HttpResult add(EvaluationMethodFieldDto evaluationMethodFieldDto){
        try {
            if (evaluationMethodFieldDto.getId() != null && evaluationMethodFieldDto.getId() != 0) {//不再使用专门的 update controller
                service.update(evaluationMethodFieldDto);
                return HttpResult.newCorrectResult();
            } else {
                boolean flag = service.add(evaluationMethodFieldDto);
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
    public BootstrapTableVo list(Integer methodId){
        BootstrapTableVo vo = null;
        if (methodId!=null) vo = service.getVosField(methodId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除",method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            service.removeFild(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


}

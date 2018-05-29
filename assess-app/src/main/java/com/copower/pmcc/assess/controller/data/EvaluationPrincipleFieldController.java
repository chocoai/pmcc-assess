package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleFieldDto;
import com.copower.pmcc.assess.service.data.EvaluationPrincipleFieldService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 评估原则 字段
 * Created by 13426 on 2018/4/28.
 */
@RequestMapping(value = "/evaluationPrincipleNG", name = "评估原则字段")
@Controller
public class EvaluationPrincipleFieldController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EvaluationPrincipleFieldService service;

    @ResponseBody
    @RequestMapping(value = "/addField",method = RequestMethod.POST,name = "新增方法字段")
    public HttpResult add(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        try {
            if (evaluationPrincipleFieldDto.getId() != null && evaluationPrincipleFieldDto.getId() != 0) {//不再使用专门的 update controller
                service.update(evaluationPrincipleFieldDto);
                return HttpResult.newCorrectResult();
            } else {
                boolean flag = service.add(evaluationPrincipleFieldDto);
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
    public BootstrapTableVo list(Integer principleId){
        BootstrapTableVo vo = null;
        if (principleId!=null) vo = service.list(principleId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/listFields",method = {RequestMethod.POST,RequestMethod.GET},name = "获取列表")
    public Object lists(Integer principleId){
        List<EvaluationPrincipleFieldDto> vos = null;
        vos = service.listN(principleId);
        return vos;
    }

    @ResponseBody
    @RequestMapping(value = "/listFieldsS",method = {RequestMethod.POST,RequestMethod.GET},name = "获取列表")
    public Object listsS(String id){
        try {
            List<List<EvaluationPrincipleFieldDto>> voS = service.listN(id);
            if (!ObjectUtils.isEmpty(voS)){
                return voS;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
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

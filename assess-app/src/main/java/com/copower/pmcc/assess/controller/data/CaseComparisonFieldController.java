package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.CaseComparisonFieldDto;
import com.copower.pmcc.assess.service.data.CaseComparisonFieldService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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

import java.util.List;

/**
 * Created by 13426 on 2018/5/3.
 */
@RequestMapping(value = "/caseComparisonNG",name = "案例对比配置 字段")
@Controller
public class CaseComparisonFieldController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CaseComparisonFieldService service;

    @ResponseBody
    @RequestMapping(value = "/addField",method = RequestMethod.POST,name = "新增方法字段")
    public HttpResult add(CaseComparisonFieldDto dto){
        try {
            if (dto.getId() != null && dto.getId() != 0) {//不再使用专门的 update controller
                service.update(dto);
                return HttpResult.newCorrectResult();
            } else {
                boolean flag = service.add(dto);
                if (flag){
                    return HttpResult.newCorrectResult();
                }else {
                    return HttpResult.newErrorResult("添加的字段名称已存在!");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listField",method = {RequestMethod.POST,RequestMethod.GET},name = "获取列表")
    public BootstrapTableVo list(Integer caseId,String name){
        BootstrapTableVo vo = null;
        if (caseId!=null) vo = service.listVos(caseId,null);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/listTableField",method = {RequestMethod.POST,RequestMethod.GET},name = "获取数据库表种的字段")
    public Object getFieldTableList(String tableName){
        List<KeyValueDto> keyValueDtos = service.getFieldList(tableName);
        if (keyValueDtos!=null){
            return keyValueDtos;
        }else {
            return HttpResult.newErrorResult("error");
        }
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

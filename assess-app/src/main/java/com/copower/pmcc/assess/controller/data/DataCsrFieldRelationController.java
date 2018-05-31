package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.entity.DataCsrFieldRelation;
import com.copower.pmcc.assess.dto.output.data.DataCsrFieldRelationVo;
import com.copower.pmcc.assess.service.data.DataCsrFieldRelationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 债权独立模块 字段关联关系 控制器
 */

@RequestMapping(value = "/csrFieldRelation", name = "字段关联关系")
@Controller
public class DataCsrFieldRelationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataCsrFieldRelationService service;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/csrFieldRelationView");
        modelAndView.addObject("userList", service.getTableList());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method ={ RequestMethod.GET})
    public BootstrapTableVo list(String displayName) {
        BootstrapTableVo vo = null;
        vo = service.listVos(displayName);//关键字查询
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/get", name = "获取", method = {RequestMethod.GET})
    public Object get(@RequestParam(value = "id") Integer id) {
        DataCsrFieldRelationVo vo = null;
        try {
            vo = service.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdate", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult add(DataCsrFieldRelation csrFieldRelation) {
        try {
            if (!ObjectUtils.isEmpty(csrFieldRelation) && !ObjectUtils.isEmpty(csrFieldRelation.getId())) {//不再使用专门的 update controller
                service.update(csrFieldRelation);
            } else {
                service.add(csrFieldRelation);
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

    @ResponseBody
    @RequestMapping(value = "/listTableField", method = {RequestMethod.POST}, name = "获取数据库表种的字段")
    public Object getFieldTableList(String tableName) {
        try {
            if (!StringUtils.isEmpty(tableName)) {
                List<KeyValueDto> keyValueDtos = service.getFieldList(tableName);
                if (!ObjectUtils.isEmpty(keyValueDtos)) return keyValueDtos;
            } else {
                return HttpResult.newErrorResult("error object is not null");
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult("error:" + e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

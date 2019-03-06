package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:35
 * @Description:土地证控制器
 */
@RequestMapping(value = "/declareRecord")
@Controller
public class DeclareRecordController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRecordService declareRecordService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareRecordList", method = {RequestMethod.GET}, name = "获取申报记录数据")
    public BootstrapTableVo getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn) {
        return declareRecordService.getDeclareRecordList(projectId, name, seat, bisPartIn);
    }

    @ResponseBody
    @RequestMapping(value = "/addOrRemoveDeclareRecord", name = "添加或移除申报记录数据", method = RequestMethod.POST)
    public HttpResult addOrRemoveDeclareRecord(String ids, Boolean bisPartIn) {
        try {
            declareRecordService.addOrRemoveDeclareRecord(ids, bisPartIn);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}

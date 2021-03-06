package com.copower.pmcc.assess.controller.project.generate;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroup;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGroupService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:39
 * @Description:委估对象 分组
 */
@RequestMapping(value = "/generateReportGroup")
@RestController
public class GenerateReportGroupController {
    @Autowired
    private GenerateReportGroupService generateReportGroupService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getGenerateReportGroupById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getGenerateReportGroupById(Integer id) {
        try {
            return HttpResult.newCorrectResult(generateReportGroupService.getGenerateReportGroupById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/saveAndUpdateGenerateReportGroup", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateGenerateReportGroup(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            GenerateReportGroup generateReportGroup = JSONObject.parseObject(formData, GenerateReportGroup.class);
            generateReportGroupService.saveAndUpdateGenerateReportGroup(generateReportGroup, updateNull);
            return HttpResult.newCorrectResult(generateReportGroup);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteGenerateReportGroup", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteGenerateReportGroup(String id) {
        try {
            generateReportGroupService.deleteGenerateReportGroupById(id);
            return HttpResult.newCorrectResult("");
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(Integer projectId) {
        return generateReportGroupService.getBootstrapTableVo(projectId);
    }

    @RequestMapping(value = "/getGenerateReportGroupList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult getGenerateReportGroupList(GenerateReportGroup generateReportGroup) {
        try {
            List<GenerateReportGroup> list = generateReportGroupService.getGenerateReportGroupListByQuery(generateReportGroup);
            return HttpResult.newCorrectResult(list);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getValidData", name = "获取校验列表", method = {RequestMethod.GET})
    public HttpResult getValidData(Integer projectId) {
        try {
            return HttpResult.newCorrectResult(generateReportGroupService.getValidData(projectId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


}

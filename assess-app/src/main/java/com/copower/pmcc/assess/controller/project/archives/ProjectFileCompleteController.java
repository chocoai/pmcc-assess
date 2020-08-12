package com.copower.pmcc.assess.controller.project.archives;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFileComplete;
import com.copower.pmcc.assess.service.project.archives.ProjectFileCompleteService;
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
 * @Auther: zch
 * @Date: 2018/9/28 09:16
 * @Description:项目归档
 */
@RequestMapping(value = "/projectFileComplete")
@Controller
public class ProjectFileCompleteController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectFileCompleteService projectFileCompleteService;

    @ResponseBody
    @RequestMapping(value = "/getProjectFileCompleteById", method = {RequestMethod.GET}, name = "get")
    public HttpResult getById(Integer id) {
        ProjectFileComplete projectFileComplete = null;
        try {
            projectFileComplete = projectFileCompleteService.getProjectFileCompleteById(id);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(projectFileComplete);
    }

    @ResponseBody
    @RequestMapping(value = "/getFileNumberCount", method = {RequestMethod.GET}, name = "get")
    public HttpResult getFileNumberCount(String fileNumber) {
        try {
            return HttpResult.newCorrectResult(projectFileCompleteService.getFileNumberCount(fileNumber));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectFileCompleteList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getProjectFileCompleteList(Integer projectId, Integer year, String fileNumber, String fileName, String fileType) {
        return projectFileCompleteService.getProjectFileCompleteDatList(projectId, year, fileNumber, fileName, fileType);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProjectFileCompleteById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            projectFileCompleteService.deleteProjectFileComplete(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveProjectFileComplete", method = {RequestMethod.POST}, name = "更新")
    public HttpResult saveProjectFileComplete(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            ProjectFileComplete projectFileComplete = JSONObject.parseObject(formData, ProjectFileComplete.class);
            projectFileCompleteService.saveAndUpdate(projectFileComplete);
            return HttpResult.newCorrectResult(projectFileComplete);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/autoCreateProjectFileCompleteNow", method = {RequestMethod.POST}, name = "自动生成档案  (目前只考虑委托书)")
    public HttpResult autoCreateProjectFileCompleteNow(Integer projectId) {
        try {
            projectFileCompleteService.autoCreateProjectFileCompleteNow(projectId);
            return HttpResult.newCorrectResult(200,"success");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }


}

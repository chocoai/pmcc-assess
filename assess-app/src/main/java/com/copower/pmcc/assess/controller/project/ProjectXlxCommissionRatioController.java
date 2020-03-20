package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionRatio;
import com.copower.pmcc.assess.service.project.ProjectXlxCommissionRatioService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/projectXlxCommissionRatio")
public class ProjectXlxCommissionRatioController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectXlxCommissionRatioService commissionRatioService;


    @RequestMapping(value = "/getById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        ProjectXlxCommissionRatio commissionRatio = null;
        try {
            if (id != null) {
                commissionRatio = commissionRatioService.getDataById(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(commissionRatio);
    }

    @RequestMapping(value = "/getList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getProjectXlxCommissionRatioList(Integer masterId) {
        BootstrapTableVo vo = null;
        try {
            vo = commissionRatioService.getProjectXlxCommissionRatioList(masterId);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/deleteById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(commissionRatioService.deleteData(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdate", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(ProjectXlxCommissionRatio commissionRatio) {
        try {
            commissionRatioService.saveAndUpdate(commissionRatio);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/init", method = {RequestMethod.POST}, name = "保存")
    public HttpResult init(Integer projectId, Integer masterId) {
        try {
            commissionRatioService.initCommissionRatio(projectId,masterId);
            return HttpResult.newCorrectResult("success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}

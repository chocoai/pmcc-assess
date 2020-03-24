package com.copower.pmcc.assess.controller.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxRebateRatio;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxRebateRatioService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/projectXlxRebateRatio")
public class ProjectXlxRebateRatioController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectXlxRebateRatioService rebateRatioService;


    @RequestMapping(value = "/getById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        ProjectXlxRebateRatio rebateRatio = null;
        try {
            if (id != null) {
                rebateRatio = rebateRatioService.getDataById(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(rebateRatio);
    }

    @RequestMapping(value = "/getList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getProjectXlxRebateRatioList(Integer masterId) {
        BootstrapTableVo vo = null;
        try {
            vo = rebateRatioService.getProjectXlxRebateRatioList(masterId);
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
                return HttpResult.newCorrectResult(rebateRatioService.deleteData(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdate", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(ProjectXlxRebateRatio rebateRatio) {
        try {
            rebateRatioService.saveAndUpdate(rebateRatio);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}

package com.copower.pmcc.assess.controller.project.xlx;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.xlx.ProjectXlxPigeonholeConfigDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeRecord;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxPigeonholeRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@RequestMapping(value = "/projectXlxPigeonholeRecord", name = "归档记录")
@Controller
public class ProjectXlxPigeonholeRecordController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectXlxPigeonholeRecordService projectXlxPigeonholeRecordService;


    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer projectId, Integer pigeonholeId) {
        return projectXlxPigeonholeRecordService.getVoList(projectId, pigeonholeId);
    }


    @ResponseBody
    @RequestMapping(value = "/get", name = "获取", method = {RequestMethod.GET})
    public HttpResult get(@RequestParam(value = "id") Integer id) {
        try {
            return HttpResult.newCorrectResult(projectXlxPigeonholeRecordService.getProjectXlxPigeonholeRecord(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult save(String formData) {
        try {
            ProjectXlxPigeonholeRecord projectXlxPigeonholeRecord = JSON.parseObject(formData, ProjectXlxPigeonholeRecord.class);
            projectXlxPigeonholeRecordService.saveAndUpdate(projectXlxPigeonholeRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            projectXlxPigeonholeRecordService.removeProjectXlxPigeonholeRecord(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getListByProjectId", name = "获取数据", method = RequestMethod.GET)
    public HttpResult getListByProjectId(Integer projectId, Integer pigeonholeId) {
        try {
            List<ProjectXlxPigeonholeRecord> list = projectXlxPigeonholeRecordService.getListByProjectId(projectId, pigeonholeId);
            return HttpResult.newCorrectResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/refresh", name = "刷新", method = RequestMethod.POST)
    public HttpResult refresh(Integer projectId, Integer pigeonholeId) {
        try {
            projectXlxPigeonholeRecordService.refresh(projectId, pigeonholeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}

package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroupWithBLOBs;
import com.copower.pmcc.assess.service.project.ProjectLandAchievementGroupService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/projectLandAchievementGroup")
public class ProjectLandAchievementGroupController {
    @Autowired
    private ProjectLandAchievementGroupService projectLandAchievementGroupService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/updateProjectLandAchievementGroup" ,name = "地价修正因素 等级组 修改")
    public HttpResult updateProjectLandAchievementGroup(String formData) {
        try {
            ProjectLandAchievementGroupWithBLOBs obj = JSONObject.parseObject(formData, ProjectLandAchievementGroupWithBLOBs.class);
            projectLandAchievementGroupService.updateByPrimaryKeySelective(obj);
            return HttpResult.newCorrectResult(200, obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId", name = "根据三个参数获取数据")
    public HttpResult getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(Integer projectId, Integer dataTableId, String dataTableName) {
        try {
            return HttpResult.newCorrectResult(200, projectLandAchievementGroupService.getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId", name = "根据三个参数获取统计的土地系数")
    public HttpResult countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(Integer projectId, Integer dataTableId, String dataTableName) {
        try {
            return HttpResult.newCorrectResult(200, projectLandAchievementGroupService.countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getInitProjectLandAchievementGroupData", name = "获取初始化过的数据 ==> 会分组")
    public HttpResult getInitProjectLandAchievementGroupData(Integer projectId, Integer dataTableId, String dataTableName) {
        try {
            return HttpResult.newCorrectResult(200, projectLandAchievementGroupService.getInitProjectLandAchievementGroupData(projectId, dataTableId, dataTableName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/initProjectLandAchievementGroup", name = "初始化获取数据  假如已经初始化了那么返回曾经初始化过的数据  ==> 会分组")
    public HttpResult initProjectLandAchievementGroup(Integer levelDetailId, Integer projectId, Integer dataTableId, String dataTableName) {
        try {
            return HttpResult.newCorrectResult(200, projectLandAchievementGroupService.initProjectLandAchievementGroup(levelDetailId, projectId, dataTableId, dataTableName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/deleteProjectLandAchievementGroupByIds", name = "删除数据")
    public HttpResult deleteProjectLandAchievementGroupByIds(String id) {
        try {
            projectLandAchievementGroupService.deleteProjectLandAchievementGroupByIds(FormatUtils.transformString2Integer(id));
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


}

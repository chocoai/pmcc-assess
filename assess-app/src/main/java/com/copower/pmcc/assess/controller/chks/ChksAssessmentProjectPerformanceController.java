package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.AssessmentCommonService;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceQuery;
import com.copower.pmcc.chks.api.dto.ChksBootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 获取考核数据
 */
@RestController
@RequestMapping(value = "/chksAssessmentProjectPerformance")
public class ChksAssessmentProjectPerformanceController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private AssessmentCommonService assessmentCommonService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;


    @GetMapping(value = "/getChksBootstrapTableVo", name = "获取考核 bootstrap table")
    public ChksBootstrapTableVo getChksBootstrapTableVo(AssessmentProjectPerformanceDto where, Integer boxId, Integer activityId) {
        return chksAssessmentProjectPerformanceService.getChksBootstrapTableVo(where, boxId, activityId);
    }

    @GetMapping(value = "/getAssessmentProjectPerformanceDtoList", name = "获取考核后的数据")
    public HttpResult getAssessmentProjectPerformanceDtoList(AssessmentProjectPerformanceQuery query, String activityIdList) {
        try {
            if (StringUtils.isNotBlank(activityIdList)) {
                query.setActivityIds(FormatUtils.transformString2Integer(activityIdList));
            }
            return HttpResult.newCorrectResult(200, chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceDtoList(query));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取获取考核后的数据出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getAssessmentProjectPerformanceDetailByPerformanceIdList", name = "获取考核后的数据   子数据")
    public HttpResult getAssessmentProjectPerformanceDetailByPerformanceIdList(Integer performanceId) {
        try {
            return HttpResult.newCorrectResult(200, chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceDetailByPerformanceIdList(performanceId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取考核后的数据   子数据");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getAssessmentProjectPerformanceById", name = "根据id获取考核后的数据   ")
    public HttpResult getAssessmentProjectPerformanceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取考核后的数据   子数据");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @GetMapping(value = "/getAssessmentItemTemplate", name = "获取模板数据")
    public HttpResult getAssessmentItemTemplate(Integer boxId, Integer boxReActivitiId, String assessmentKey) {
        try {
            return HttpResult.newCorrectResult(200, chksAssessmentProjectPerformanceService.getAssessmentItemTemplate(boxId, boxReActivitiId, assessmentKey));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取数据出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }

    }

    @GetMapping(value = "/getActivityIdByUserAccountList", name = "获取节点下的审批人")
    public HttpResult getActivityIdByUserAccountList(Integer activityId) {
        try {
            return HttpResult.newCorrectResult(200, bpmRpcBoxService.getRoleUserByActivityId(activityId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取数据出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/saveAssessmentServer", name = "保存考核信息")
    public HttpResult saveAssessmentServer(String fomData, String chksScore, Integer planDetailsId) {
        try {
            AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = JSONObject.parseObject(fomData, AssessmentProjectPerformanceDto.class);
            List<AssessmentProjectPerformanceDetailDto> detailDtoList = JSONObject.parseArray(chksScore, AssessmentProjectPerformanceDetailDto.class);
            Integer id = chksAssessmentProjectPerformanceService.saveAssessmentServer(assessmentProjectPerformanceDto, detailDtoList, planDetailsId);
            return HttpResult.newCorrectResult(200, id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "保存抽查数据出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformance", name = "考核主表数据修改")
    public HttpResult updateAssessmentProjectPerformance(String fomData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = JSONObject.parseObject(fomData, AssessmentProjectPerformanceDto.class);
            chksAssessmentProjectPerformanceService.updateAssessmentProjectPerformanceDto(assessmentProjectPerformanceDto, updateNull);
            return HttpResult.newCorrectResult(200, assessmentProjectPerformanceDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核主表数据修改 出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformanceDetailList", name = "考核主表数据的从表修改")
    public HttpResult updateAssessmentProjectPerformanceDetailList(String fomData, boolean updateNull) {
        try {
            List<AssessmentProjectPerformanceDetailDto> detailDtoList = JSONObject.parseArray(fomData, AssessmentProjectPerformanceDetailDto.class);
            chksAssessmentProjectPerformanceService.updateAssessmentProjectPerformanceDetailDto(detailDtoList, updateNull);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核主表数据的从表修改 出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/deleteAssessmentProjectPerformanceDetailByIds", name = "考核从表数据 delete")
    public HttpResult deleteAssessmentProjectPerformanceDetailByIds(String id) {
        try {
            chksAssessmentProjectPerformanceService.deleteAssessmentProjectPerformanceDetailByIds(FormatUtils.transformString2Integer(id));
            return HttpResult.newCorrectResult(200, "成功");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核从表数据 delete 出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/deleteAssessmentProjectPerformanceByIds", name = "考核主表数据 delete")
    public HttpResult deleteAssessmentProjectPerformanceByIds(String id) {
        try {
            chksAssessmentProjectPerformanceService.deleteAssessmentProjectPerformanceByIds(FormatUtils.transformString2Integer(id));
            return HttpResult.newCorrectResult(200, "成功");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核主表数据 delete 出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/deleteResponsibilityById", name = "删除任务数据 delete")
    public HttpResult deleteResponsibilityById(String id) {
        try {
            chksAssessmentProjectPerformanceService.deleteResponsibilityById(id);
            return HttpResult.newCorrectResult(200, "成功");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "删除任务数据 delete 出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/pasteAll", name = "粘贴数据")
    public HttpResult pasteAll(Integer copyId, String ids) {
        try {
            return HttpResult.newCorrectResult(200, chksAssessmentProjectPerformanceService.pasteAll(copyId, ids));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "粘贴数据 出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/generateAssessmentTask", name = "生成考核任务")
    public HttpResult generateAssessmentTask(String processInsId, Integer boxId, String taskId, Integer projectId, Integer planDetailsId) {
        try {
            ProjectInfo projectInfo = projectId == null ? null : projectInfoService.getProjectInfoById(projectId);
            ProjectPlanDetails projectPlanDetails = planDetailsId == null ? null : projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            assessmentCommonService.generateAssessmentTask(processInsId, boxId, taskId, projectInfo, projectPlanDetails);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "生成考核任务异常");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}


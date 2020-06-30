package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.AssessmentWorkHoursService;
import com.copower.pmcc.assess.service.chks.AssessmentPerformanceService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 获取考核数据
 */
@RestController
@RequestMapping(value = "/assessmentPerformance")
public class AssessmentPerformanceController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private AssessmentPerformanceService assessmentPerformanceService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private AssessmentWorkHoursService assessmentWorkHoursService;
    @Autowired
    private CommonService commonService;


    @GetMapping(value = "/getPerformanceList", name = "获取质量考核数据列表")
    public BootstrapTableVo getPerformanceList(AssessmentPerformanceDto where, Integer boxId, String reActivityName, String flog) {
        return assessmentPerformanceService.getPerformanceList(where, boxId, reActivityName, flog);
    }

    @GetMapping(value = "/getAssessmentProjectPerformanceDetailByPerformanceIdList", name = "获取考核后的数据   子数据")
    public HttpResult getAssessmentProjectPerformanceDetailByPerformanceIdList(Integer performanceId) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.getPerformanceDetailsByPerformanceId(performanceId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取考核后的数据   子数据");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getAssessmentProjectPerformanceById", name = "根据id获取考核后的数据   ")
    public HttpResult getAssessmentProjectPerformanceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.getPerformanceById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取考核后的数据   子数据");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @GetMapping(value = "/getAssessmentItemTemplate", name = "获取模板数据")
    public HttpResult getAssessmentItemTemplate(Integer boxId, Integer boxReActivitiId, String assessmentKey) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.getAssessmentItemTemplate(boxId, boxReActivitiId, assessmentKey));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取数据出错");
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @GetMapping(value = "/getActivityIdByUserAccountList", name = "获取节点下的审批人")
    public HttpResult getActivityIdByUserAccountList(Integer activityId) {
        try {
            return HttpResult.newCorrectResult(bpmRpcBoxService.getRoleUserByActivityId(activityId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取数据出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/saveAssessmentServer", name = "保存质量考核信息")
    public HttpResult saveAssessmentServer(String fomData, String chksScore, String processInsId, Integer planDetailsId) {
        try {
            AssessmentPerformanceDto assessmentProjectPerformanceDto = JSONObject.parseObject(fomData, AssessmentPerformanceDto.class);
            List<AssessmentPerformanceDetailDto> detailDtoList = JSONObject.parseArray(chksScore, AssessmentPerformanceDetailDto.class);
            Integer id = assessmentPerformanceService.saveAssessmentServer(assessmentProjectPerformanceDto, detailDtoList, planDetailsId);
            //清理ProjectTask任务，根据流程id
            assessmentPerformanceService.clearAssessmentProjectTask(processInsId, commonService.thisUserAccount());
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "保存质量数据出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformance", name = "考核主表数据修改")
    public HttpResult updateAssessmentProjectPerformance(String fomData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            AssessmentPerformanceDto assessmentProjectPerformanceDto = JSONObject.parseObject(fomData, AssessmentPerformanceDto.class);
            assessmentPerformanceService.updatePerformanceDto(assessmentProjectPerformanceDto, updateNull);
            return HttpResult.newCorrectResult(assessmentProjectPerformanceDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核主表数据修改 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformanceDetailList", name = "考核主表数据的从表修改")
    public HttpResult updateAssessmentProjectPerformanceDetailList(String fomData, boolean updateNull) {
        try {
            List<AssessmentPerformanceDetailDto> detailDtoList = JSONObject.parseArray(fomData, AssessmentPerformanceDetailDto.class);
            assessmentPerformanceService.updatePerformanceDetailDto(detailDtoList, updateNull);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核主表数据的从表修改 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/deleteAssessmentProjectPerformanceDetailByIds", name = "考核从表数据 delete")
    public HttpResult deleteAssessmentProjectPerformanceDetailByIds(String id) {
        try {
            assessmentPerformanceService.deletePerformanceDetailByIds(FormatUtils.transformString2Integer(id));
            return HttpResult.newCorrectResult("成功");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核从表数据 delete 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/deleteAssessmentProjectPerformanceByIds", name = "考核主表数据 delete")
    public HttpResult deleteAssessmentProjectPerformanceByIds(String id) {
        try {
            assessmentPerformanceService.deletePerformanceByIds(FormatUtils.transformString2Integer(id));
            return HttpResult.newCorrectResult("成功");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "考核主表数据 delete 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/deleteResponsibilityById", name = "删除任务数据 delete")
    public HttpResult deleteResponsibilityById(String id) {
        try {
            assessmentPerformanceService.deleteResponsibilityById(id);
            return HttpResult.newCorrectResult("成功");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "删除任务数据 delete 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/pasteAll", name = "粘贴数据")
    public HttpResult pasteAll(Integer copyId, String ids) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.pasteAll(copyId, ids));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "粘贴数据 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getWorkingHoursList", name = "获取工时考核任务")
    public BootstrapTableVo getWorkingHoursList(String processInsId, String userAccount, String reActivityName, String flog) {
        return assessmentWorkHoursService.getWorkingHoursList(processInsId, userAccount, reActivityName, flog);
    }

    @PostMapping(value = "/saveWorkingHours", name = "保存工时考核任务")
    public HttpResult saveWorkingHours(Integer id, Integer spotId, Integer adjustId, BigDecimal examineScore, String remarks, String processInsId) {
        try {
            assessmentWorkHoursService.saveWorkHours(id, spotId, adjustId, examineScore, remarks);
            if (spotId == null || spotId <= 0)
                assessmentWorkHoursService.clearAssessmentProjectTask(processInsId, commonService.thisUserAccount());
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "保存工时考核任务出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/batchSetFinish", name = "批量设置完成质量考核")
    public HttpResult batchSetFinish(String ids) {
        try {
            List<Integer> list = FormatUtils.transformString2Integer(ids);
            assessmentPerformanceService.batchSetFinish(list);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "批量设置完成质量考核出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getPerformanceSpotListById", name = "获取质量抽查数据")
    public BootstrapTableVo getPerformanceSpotListById(Integer id) {
        return assessmentPerformanceService.getPerformanceSpotListById(id);
    }

    @GetMapping(value = "/getWorkHoursSpotListById", name = "获取工时抽查数据")
    public BootstrapTableVo getWorkingHoursSpotListById(Integer id) {
        return assessmentWorkHoursService.getWorkingHoursSpotListById(id);
    }

    @GetMapping(value = "/getAssessmentProjectWorkHoursHistoryList", name = "获取历史数据")
    public BootstrapTableVo getWorkHoursHistoryList(String processInsId,Integer activityId) {
        return assessmentWorkHoursService.getWorkHoursHistoryList(processInsId,activityId);
    }
}


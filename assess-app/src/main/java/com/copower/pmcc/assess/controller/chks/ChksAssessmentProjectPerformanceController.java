package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
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


    @GetMapping(value = "/getChksBootstrapTableVo", name = "获取考核 bootstrap table")
    public ChksBootstrapTableVo getChksBootstrapTableVo(AssessmentProjectPerformanceDto where, String activityIds) {
        return chksAssessmentProjectPerformanceService.getChksBootstrapTableVo(where, activityIds);
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

    @GetMapping(value = "/conversionProjectPerformanceDtoMap", name = "获取考核后的数据")
    public HttpResult conversionProjectPerformanceDtoMap(AssessmentProjectPerformanceQuery query, String activityIdList) {
        try {
            if (StringUtils.isNotBlank(activityIdList)) {
                query.setActivityIds(FormatUtils.transformString2Integer(activityIdList));
            }
            return HttpResult.newCorrectResult(200, chksAssessmentProjectPerformanceService.conversionProjectPerformanceDtoMap(chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceDtoList(query)));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取获取考核后的数据出错");
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

    @PostMapping(value = "/saveAssessmentServer", name = "保存考核信息")
    public HttpResult saveAssessmentServer(String fomData, String chksScore, Integer planDetailsId) {
        try {
            AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = JSONObject.parseObject(fomData, AssessmentProjectPerformanceDto.class);
            List<AssessmentProjectPerformanceDetailDto> detailDtoList = JSONObject.parseArray(chksScore, AssessmentProjectPerformanceDetailDto.class);
            chksAssessmentProjectPerformanceService.saveAssessmentServer(assessmentProjectPerformanceDto, detailDtoList, planDetailsId);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "保存抽查数据出错");
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformance", name = "考核主表数据修改")
    public HttpResult updateAssessmentProjectPerformance(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto, boolean updateNull) {
        try {
            chksAssessmentProjectPerformanceService.updateAssessmentProjectPerformanceDto(assessmentProjectPerformanceDto, updateNull);
            return HttpResult.newCorrectResult(200, "成功");
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

}


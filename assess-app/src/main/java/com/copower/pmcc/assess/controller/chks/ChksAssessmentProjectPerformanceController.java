package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.dto.ChksBootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ChksBootstrapTableVo getChksBootstrapTableVo(AssessmentProjectPerformanceDto where) {
        return chksAssessmentProjectPerformanceService.getChksBootstrapTableVo(where);
    }

    @GetMapping(value = "/getChkSpotAssessmentBySpotActivityId",name = "获取抽查保存后的数据")
    public HttpResult getChkSpotAssessmentBySpotActivityId(Integer boxId,Integer activityId,Integer spotActivityId,String processInsId,Integer projectId){
        try {
            return HttpResult.newCorrectResult(200,chksAssessmentProjectPerformanceService.getChkSpotAssessmentBySpotActivityId(boxId, activityId, spotActivityId, processInsId, projectId))  ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"获取抽查数据出错");
            return HttpResult.newErrorResult(500,e.getMessage()) ;
        }
    }

    @PostMapping(value = "/saveChkSpotAssessment",name = "保存抽查数据")
    public HttpResult saveChkSpotAssessment(String fomData, String chksScore,Integer planDetailsId ){
        try {
            AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = JSONObject.parseObject(fomData,AssessmentProjectPerformanceDto.class) ;
            List<AssessmentProjectPerformanceDetailDto> detailDtoList = JSONObject.parseArray(chksScore, AssessmentProjectPerformanceDetailDto.class);
            chksAssessmentProjectPerformanceService.saveAndUpdateChkSpotAssessment(assessmentProjectPerformanceDto,detailDtoList,planDetailsId);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"保存抽查数据出错");
            return HttpResult.newErrorResult(500,e.getMessage()) ;
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformance",name = "考核主表数据修改")
    public HttpResult updateAssessmentProjectPerformance(AssessmentProjectPerformanceDto assessmentProjectPerformanceDto,boolean updateNull){
        try {
            chksAssessmentProjectPerformanceService.updateAssessmentProjectPerformanceDto(assessmentProjectPerformanceDto,updateNull);
            return HttpResult.newCorrectResult(200,"成功") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"考核主表数据修改 出错");
            return HttpResult.newErrorResult(500,e.getMessage()) ;
        }
    }

    @PostMapping(value = "/updateAssessmentProjectPerformanceDetailList",name = "考核主表数据的从表修改")
    public HttpResult updateAssessmentProjectPerformanceDetailList(String fomData,boolean updateNull){
        try {
            List<AssessmentProjectPerformanceDetailDto> detailDtoList = JSONObject.parseArray(fomData,AssessmentProjectPerformanceDetailDto.class) ;
            chksAssessmentProjectPerformanceService.updateAssessmentProjectPerformanceDetailDto(detailDtoList,updateNull);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"考核主表数据的从表修改 出错");
            return HttpResult.newErrorResult(500,e.getMessage()) ;
        }
    }



}


package com.copower.pmcc.assess.service.chks;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.AssessmentItemDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDtoAndDetail;
import com.copower.pmcc.chks.api.dto.ChksBootstrapTableVo;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by zch on 2019-12-16.
 * 考核
 */
@org.springframework.stereotype.Service
public class ChksAssessmentProjectPerformanceService {

    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ChksRpcAssessmentService chksRpcAssessmentService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;


    public void saveAssessmentProjectPerformance(ApprovalModelDto approvalModelDto, String chksScore, String remarks, String byExaminePeople) {
        //添加主表
        AssessmentProjectPerformanceDto dto = new AssessmentProjectPerformanceDto();
        List<AssessmentProjectPerformanceDetailDto> detailDtos = Lists.newArrayList();
        dto.setAppKey(applicationConstant.getAppKey());
        dto.setProcessInsId(approvalModelDto.getProcessInsId());
        dto.setProjectId(approvalModelDto.getProjectId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(approvalModelDto.getProjectId());
        if (projectInfo != null) {
            dto.setProjectName(projectInfo.getProjectName());
        }
        dto.setActivityId(approvalModelDto.getActivityId());
        BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(approvalModelDto.getActivityId());
        if (boxReActivityDto != null) {
            dto.setActivityName(boxReActivityDto.getCnName());
        }
        dto.setBoxId(approvalModelDto.getBoxId());
        dto.setCreator(processControllerComponent.getThisUser());
        dto.setExaminePeople(processControllerComponent.getThisUser());
        dto.setByExaminePeople(byExaminePeople);
        dto.setRemarks(remarks);
        dto.setExamineDate(new Date());
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(approvalModelDto.getProcessInsId());
        if (projectPlanDetails != null) {
            dto.setTableId(projectPlanDetails.getId());
            dto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class));
            //业务标识
            if (projectPlanDetails.getProjectWorkStageId() != null) {
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
                if (projectWorkStage != null){
                    dto.setBusinessKey(projectWorkStage.getWorkStageName());
                }
            }
        }
        JSONObject jsonObject = JSON.parseObject(chksScore);
        Set<String> keySet = jsonObject.keySet();
        if (CollectionUtils.isNotEmpty(keySet)){
            for (String id : keySet) {
                if (!NumberUtils.isNumber(id)){
                    continue;
                }
                //添加从表
                AssessmentProjectPerformanceDetailDto detailDto = new AssessmentProjectPerformanceDetailDto();
                detailDto.setCreator(processControllerComponent.getThisUser());
                detailDto.setContentId(Integer.valueOf(id));
                AssessmentItemDto assessmentItem = bpmRpcBoxService.getAssessmentItem(Integer.valueOf(id));
                if (assessmentItem != null) detailDto.setContent(assessmentItem.getAssessmentDes());
                detailDto.setActualScore(jsonObject.getBigDecimal(id));
                detailDtos.add(detailDto);
            }
        }
        chksRpcAssessmentService.saveAssessmentProjectPerformance(JSON.toJSONString(dto), JSON.toJSONString(detailDtos));
    }

    public ChksBootstrapTableVo getHistoryChksData(String processInsId) {
        ChksBootstrapTableVo chksBootstrapTableVo = new ChksBootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        AssessmentProjectPerformanceDtoAndDetail dtoAndDetail = chksRpcAssessmentService.getAssessmentProjectPerformanceDtosByProcessInsId(processInsId, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (dtoAndDetail != null) {
            chksBootstrapTableVo = dtoAndDetail.getChksBootstrapTableVo();
        }
        return chksBootstrapTableVo;
    }

}

package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.AssessmentTypeEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class ProjectAssessmentBonusEvent extends BaseProcessEvent {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectAssessmentBonusService projectAssessmentBonusService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;
    @Autowired
    private ApplicationConstant applicationConstant;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        try {
            ProcessStatusEnum processStatusEnum = ProcessStatusEnum.create(processExecution.getProcessStatus().getValue());
            if (processStatusEnum == null) return;
            if (processStatusEnum.isFinish()) {
                //将各个成员的得分写入到考核系统中，
                ProjectAssessmentBonus assessmentBonus = projectAssessmentBonusService.getAssessmentBonusByProcessInsId(processExecution.getProcessInstanceId());
                if (assessmentBonus == null) return;
                List<ProjectAssessmentBonusItem> bonusItemList = projectAssessmentBonusService.getAssessmentBonusItemsByMasterId(assessmentBonus.getId());
                if (CollectionUtils.isNotEmpty(bonusItemList)) {
                    for (ProjectAssessmentBonusItem assessmentBonusItem : bonusItemList) {
                        String memberScoreCondition = assessmentBonusItem.getMemberScoreCondition();
                        if(StringUtils.isBlank(memberScoreCondition)) continue;
                        List<KeyValueDto> keyValueDtos = JSON.parseArray(memberScoreCondition, KeyValueDto.class);
                        for (KeyValueDto keyValueDto : keyValueDtos) {
                            AssessmentPerformanceDto performanceDto = new AssessmentPerformanceDto();
                            performanceDto.setProjectId(assessmentBonusItem.getProjectId());
                            performanceDto.setAppKey(applicationConstant.getAppKey());
                            performanceDto.setProjectName(assessmentBonusItem.getProjectName());
                            performanceDto.setByExaminePeople(keyValueDto.getKey());
                            performanceDto.setExaminePeople(assessmentBonusItem.getProjectManager());
                            performanceDto.setExamineScore(new BigDecimal(keyValueDto.getValue()));
                            performanceDto.setAssessmentType(AssessmentTypeEnum.WORK_HOURS.getValue());
                            performanceDto.setBisEffective(true);
                            performanceDto.setBisQualified(true);
                            performanceService.saveAndUpdatePerformanceDto(performanceDto, false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "ProjectAssessmentBonusEvent");
        }
    }
}

package com.copower.pmcc.assess.service.event;

import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomDdlTableMapper;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.executor.ProcessEventExecutor;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessExecutionService;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceQuery;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Sets;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by wangpc on 2017/8/21.
 */
@Component
public class BaseProcessEvent implements ProcessEventExecutor {
    @Autowired
    private BaseService baseService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CustomDdlTableMapper customDdlTableMapper;
    @Autowired
    private BpmRpcProcessExecutionService bpmRpcProcessExecutionService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;
    @Autowired
    private ChksRpcAssessmentService chksRpcAssessmentService;

    @Override
    public void processStartExecute(String s) {

    }

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processExecution.getProcessInstanceId());
        //处理流程结束的公共方法
        try {
            String sql = String.format("update %s set status='%s' where id=%s", boxRuDto.getTableName(), processExecution.getProcessStatus().getValue(), boxRuDto.getTableId());
            customDdlTableMapper.customTableSelect(sql);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }

        //生成考核任务
        try {
            processAssessmentTask(boxRuDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
    }

    /**
     * 生成考核任务的默认方法
     * 当生成考核任务有不同的方式时，其他的监听器需重载次方法
     *
     * @param boxRuDto
     */
    public void processAssessmentTask(BoxRuDto boxRuDto) {
        //1.首先检查该流程是否需要考核 2.需要考核时，读取审批日志与已生成考核任务
        //3.找出还需生成的考核任务节点，为其生成任务
        if (boxRuDto == null) return;
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxRuDto.getBoxId());
        if (boxReDto == null || boxReDto.getBisLaunchCheck() == Boolean.FALSE) return;

        List<BoxApprovalLogVo> logVoList = chksAssessmentProjectPerformanceService.getFilterBoxApprovalLogVoList(boxRuDto.getProcessInsId());
        if (CollectionUtils.isEmpty(logVoList)) return;
        HashSet<Integer> activityIdSet = Sets.newHashSet();
        logVoList.forEach(o -> activityIdSet.add(o.getActivityId()));

        AssessmentProjectPerformanceQuery query=new AssessmentProjectPerformanceQuery();
        query.setProcessInsId(boxRuDto.getProcessInsId());
        List<AssessmentProjectPerformanceDto> performanceDtoList = chksRpcAssessmentService.getAssessmentProjectPerformanceDtoList(query);
        List<Integer> list = LangUtils.transform(performanceDtoList, o -> o.getActivityId());
        for (Integer activityId : activityIdSet) {
            if(CollectionUtils.isNotEmpty(list)&&list.contains(activityId))
                continue;//表示该节点已生成了任务

            //chksRpcAssessmentService.saveAssessmentProjectPerformanceBase();
        }

    }

    public void processFinishExecuteExtend(ProcessExecution processExecution, Date executeDate, String beans) {
        beans = FormatUtils.toLowerCaseFirstChar(beans);
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processExecution.getProcessInstanceId());
            bpmRpcProcessExecutionService.addBoxProcessExecute(boxRuDto.getTableName(), boxRuDto.getTableId(), processExecution.getProcessInstanceId(), executeDate, beans);
        }
    }
}

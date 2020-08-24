package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectStateChangeService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProjectSchemeChangeEvent extends BaseProcessEvent {
    @Autowired
    private ProjectChangeLogDao projectChangeLogDao;
    @Autowired
    private ProjectStateChangeService stateChangeService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        ProjectChangeLog costsProjectChangeLog = stateChangeService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        costsProjectChangeLog.setStatus(ProcessStatusEnum.FINISH.getValue());
        projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);

        //更改数据
        List<SchemeAreaGroup> areaGroups = JSON.parseArray(costsProjectChangeLog.getNewRecord(), SchemeAreaGroup.class);
        if (CollectionUtils.isNotEmpty(areaGroups)) {
            for (SchemeAreaGroup item : areaGroups) {
                schemeAreaGroupService.saveAreaGroup(item);
            }
        }

    }
}

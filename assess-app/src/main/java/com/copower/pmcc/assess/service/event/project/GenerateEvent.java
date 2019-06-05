package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/2/6
 * @time: 10:53
 */
@Component
public class GenerateEvent extends BaseProcessEvent {
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private GenerateReportInfoService generateReportGenerationService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);

    }

}

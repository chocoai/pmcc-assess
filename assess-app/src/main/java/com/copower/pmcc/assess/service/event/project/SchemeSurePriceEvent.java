package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceRecord;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SchemeSurePriceEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeSurePriceRecordDao schemeSurePriceRecordDao;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if (processExecution.getProcessStatus().equals(ProcessStatusEnum.FINISH)) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processExecution.getProcessInstanceId());
            //确定单价保存记录
            SchemeSurePriceRecord schemeSurePriceRecord = new SchemeSurePriceRecord();
            schemeSurePriceRecord.setPlanDetailsId(projectPlanDetails.getId());
            schemeSurePriceRecord.setProjectId(projectPlanDetails.getProjectId());
            SchemeSurePrice surePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
            schemeSurePriceRecord.setRecordPrice(surePrice.getPrice());
            schemeSurePriceRecord.setCreator(commonService.thisUserAccount());
            schemeSurePriceRecordDao.addSchemeSurePriceRecord(schemeSurePriceRecord);
        }
    }
}

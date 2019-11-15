package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.service.NetInfoAssignTaskService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NetInfoAssignTaskEvent extends BaseProcessEvent {
    @Autowired
    private NetInfoAssignTaskService netInfoAssignTaskService;
    @Autowired
    private NetInfoRecordDao NetInfoRecordDao;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        NetInfoAssignTask data = netInfoAssignTaskService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        data.setStatus(ProcessStatusEnum.FINISH.getValue());
        netInfoAssignTaskService.editData(data);
        //补全信息的数据状态改变
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(data.getNetInfoIds()));
        for (Integer id : integers) {
            NetInfoRecord netInfo = NetInfoRecordDao.getInfoById(id);
            netInfo.setStatus(2);
            NetInfoRecordDao.updateInfo(netInfo);
        }
    }
}

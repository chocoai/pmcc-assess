package com.copower.pmcc.assess.service.event.data;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelVo;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/6
 * @time: 15:27
 */
@Component
public class DataLandLevelEvent extends BaseProcessEvent {
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDao dataLandLevelDao;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        List<DataLandLevelVo> landLevelVos = dataLandLevelService.getByProcessInsId(processExecution.getProcessInstanceId());
        if (CollectionUtils.isNotEmpty(landLevelVos)) {
            for (DataLandLevelVo landLevelVo : landLevelVos) {
                landLevelVo.setStatus(ProjectStatusEnum.FINISH.getKey());
                dataLandLevelDao.updateDataLandLevel(landLevelVo);
            }
        }
    }
}

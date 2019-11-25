package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.NetInfoAssignTaskService;
import com.copower.pmcc.assess.service.NetInfoRecordHouseService;
import com.copower.pmcc.assess.service.NetInfoRecordLandService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NetInfoAssignTaskEvent extends BaseProcessEvent {
    @Autowired
    private NetInfoAssignTaskService netInfoAssignTaskService;
    @Autowired
    private NetInfoRecordDao NetInfoRecordDao;
    @Autowired
    private NetInfoRecordHouseService netInfoRecordHouseService;
    @Autowired
    private NetInfoRecordLandService netInfoRecordLandService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        NetInfoAssignTask data = netInfoAssignTaskService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        data.setStatus(ProcessStatusEnum.FINISH.getValue());
        netInfoAssignTaskService.editData(data);
        //补全信息的数据状态改变
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(data.getNetInfoIds()));
        //修改土地或房产子表状态
        for (Integer id : integers) {
            NetInfoRecord netInfo = NetInfoRecordDao.getInfoById(id);
            if (StringUtils.equals(netInfo.getBelongType(), "房产")) {
                NetInfoRecordHouse house = new NetInfoRecordHouse();
                house.setMasterId(id);
                List<NetInfoRecordHouseVo> vos = netInfoRecordHouseService.getVos(house);
                NetInfoRecordHouseVo houseVo = vos.get(vos.size() - 1);
                house.setStatus(1);
                netInfoRecordHouseService.saveAndUpdateNetInfoRecordHouse(houseVo);
            }
            if (StringUtils.equals(netInfo.getBelongType(), "土地")) {
                NetInfoRecordLand land = new NetInfoRecordLand();
                land.setMasterId(id);
                List<NetInfoRecordLandVo> vos = netInfoRecordLandService.getVos(land);
                NetInfoRecordLandVo landVo = vos.get(vos.size() - 1);
                landVo.setStatus(1);
                netInfoRecordLandService.saveAndUpdateNetInfoRecordLand(landVo);
            }
            netInfo.setStatus(4);
            NetInfoRecordDao.updateInfo(netInfo);
        }
    }
}

package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.service.NetInfoAssignTaskService;
import com.copower.pmcc.assess.service.NetInfoRecordHouseService;
import com.copower.pmcc.assess.service.NetInfoRecordLandService;
import com.copower.pmcc.assess.service.cases.CaseBaseHouseService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;
    @Autowired
    private CaseBaseHouseService caseBaseHouseService;

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

            netInfo.setStatus(4);
            NetInfoRecordDao.updateInfo(netInfo);
        }

        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getHouseListByMasterIds(integers);
        if(CollectionUtils.isNotEmpty(netInfoRecordHouses)){
            netInfoRecordHouses.forEach(o->{
               o.setStatus(1);
                netInfoRecordHouseService.saveAndUpdateNetInfoRecordHouse(o);
                //验证后写入到标准表中
                if(caseBaseHouseService.checkFullName(getFullName(o.getName(),o.getBuildingNumber(),o.getUnitNumber(),o.getHouseNumber()))) {
                    CaseBaseHouse caseBaseHouse = new CaseBaseHouse();
                    caseBaseHouse.setProvince(o.getProvince());
                    caseBaseHouse.setCity(o.getCity());
                    caseBaseHouse.setDistrict(o.getDistrict());
                    caseBaseHouse.setFullName(getFullName(o.getName(), o.getBuildingNumber(), o.getUnitNumber(), o.getHouseNumber()));
                    caseBaseHouse.setStreet(o.getStreet());
                    caseBaseHouse.setTradingTime(o.getNegotiatedDate());
                    caseBaseHouse.setTradingUnitPrice(o.getUnitPrice());
                    caseBaseHouse.setVersion(1);
                    caseBaseHouse.setHouseType(o.getBelongType());
                    caseBaseHouse.setHouseCategory(o.getBelongCategory());
                    caseBaseHouse.setArea(o.getArea());
                    caseBaseHouse.setEstateName(o.getName());
                    caseBaseHouse.setDealType(o.getDealType());
                    caseBaseHouse.setCurrentPrice(o.getCurrentPrice());
                    caseBaseHouse.setConsultPrice(o.getConsultPrice());
                    caseBaseHouse.setAssessStandardDate(o.getAssessStandardDate());
                    caseBaseHouse.setRealizationRatios(o.getHouseRealizationRatios());
                    caseBaseHouse.setRealizationCycle(o.getRealizationCycle());
                    caseBaseHouse.setDealPartInfo(o.getDealPartInfo());
                    caseBaseHouse.setApprover(o.getApprover());
                    caseBaseHouse.setCreator(o.getCreator());
                    caseBaseHouseService.addBaseHouse(caseBaseHouse);
                }
            });
        }
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getLandListByMasterIds(integers);

        if(CollectionUtils.isNotEmpty(netInfoRecordLands)){
            netInfoRecordLands.forEach(o->{
                o.setStatus(1);
                netInfoRecordLandService.saveAndUpdateNetInfoRecordLand(o);
            });
        }
    }

    private String getFullName(String estateName, String buildingNumber, String unitNumber, String houseNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(estateName))
            stringBuilder.append(estateName);
        if (StringUtils.isNotBlank(buildingNumber))
            stringBuilder.append(buildingNumber).append("栋");
        if (StringUtils.isNotBlank(unitNumber))
            stringBuilder.append(unitNumber).append("单元");
        if (StringUtils.isNotBlank(houseNumber))
            stringBuilder.append(houseNumber).append("号");
        return stringBuilder.toString();
    }
}

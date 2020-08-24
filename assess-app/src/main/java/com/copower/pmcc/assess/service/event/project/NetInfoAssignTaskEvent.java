package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.NetInfoAssignTaskService;
import com.copower.pmcc.assess.service.NetInfoRecordHouseService;
import com.copower.pmcc.assess.service.NetInfoRecordLandService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicHouseCaseSummaryService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
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
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private PublicBasicService publicBasicService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
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
        if (CollectionUtils.isNotEmpty(netInfoRecordHouses)) {
            for (NetInfoRecordHouse o : netInfoRecordHouses) {
                o.setStatus(1);
                netInfoRecordHouseDao.updateNetInfoRecordHouse(o, true);
                //验证后写入到标准表中
                String fullName = publicBasicService.getFullName(o.getName(), o.getBuildingNumber(), o.getUnitNumber(), o.getHouseNumber());
                if (basicHouseCaseSummaryService.getCountByFullName(fullName) <= 0) {
                    BasicHouseCaseSummary basicHouseCaseSummary = new BasicHouseCaseSummary();
                    basicHouseCaseSummary.setCaseHouseId(o.getId());
                    basicHouseCaseSummary.setProvince(o.getProvince());
                    basicHouseCaseSummary.setCity(o.getCity());
                    basicHouseCaseSummary.setDistrict(o.getDistrict());
                    basicHouseCaseSummary.setFullName(publicBasicService.getFullName(o.getName(), o.getBuildingNumber(), o.getUnitNumber(), o.getHouseNumber()));
                    basicHouseCaseSummary.setStreet(o.getStreet());
                    basicHouseCaseSummary.setTradingTime(o.getNegotiatedDate());
                    basicHouseCaseSummary.setTradingUnitPrice(o.getUnitPrice());
                    basicHouseCaseSummary.setVersion(1);
                    basicHouseCaseSummary.setHouseType(o.getBelongType());
                    basicHouseCaseSummary.setHouseCategory(o.getBelongCategory());
                    basicHouseCaseSummary.setArea(o.getArea());
                    basicHouseCaseSummary.setEstateName(o.getName());
                    basicHouseCaseSummary.setDealType(o.getDealType());
                    basicHouseCaseSummary.setCurrentPrice(o.getCurrentPrice());
                    basicHouseCaseSummary.setConsultPrice(o.getConsultPrice());
                    basicHouseCaseSummary.setAssessStandardDate(o.getAssessStandardDate());
                    basicHouseCaseSummary.setRealizationRatios(o.getHouseRealizationRatios());
                    basicHouseCaseSummary.setRealizationCycle(o.getRealizationCycle());
                    basicHouseCaseSummary.setDealPartInfo(o.getDealPartInfo());
                    basicHouseCaseSummary.setApprover(o.getApprover());
                    basicHouseCaseSummary.setTradingType(o.getTradingType());
                    basicHouseCaseSummary.setCreator(o.getCreator());
                    basicHouseCaseSummaryService.addBaseHouseSummary(basicHouseCaseSummary);

                    //附件拷贝
                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(o.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(basicHouseCaseSummary.getId());
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCaseSummary.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                }
            }
        }
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getLandListByMasterIds(integers);

        if (CollectionUtils.isNotEmpty(netInfoRecordLands)) {
            netInfoRecordLands.forEach(o -> {
                o.setStatus(1);
                netInfoRecordLandDao.updateNetInfoRecordLand(o, true);
            });
        }
    }
}

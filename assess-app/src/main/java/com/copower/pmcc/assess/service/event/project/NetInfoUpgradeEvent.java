package com.copower.pmcc.assess.service.event.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseCaseSummaryDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgrade;
import com.copower.pmcc.assess.service.NetInfoUpgradeService;
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
public class NetInfoUpgradeEvent extends BaseProcessEvent {
    @Autowired
    private NetInfoUpgradeService netInfoUpgradeService;
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseCaseSummaryDao basicHouseCaseSummaryDao;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        super.processFinishExecute(processExecution);
        if(!processExecution.getProcessStatus().isFinish()) return;
        NetInfoUpgrade data = netInfoUpgradeService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        data.setStatus(ProcessStatusEnum.FINISH.getValue());
        netInfoUpgradeService.editData(data);
        //修改土地或房产子表状态
        if ("房产".equals(data.getType())) {
            NetInfoRecordHouse oldRecordHouse = netInfoRecordHouseDao.getNetInfoRecordHouseById(data.getDataId());
            oldRecordHouse.setBisNewest(false);
            netInfoRecordHouseDao.updateNetInfoRecordHouse(oldRecordHouse,true);
            NetInfoRecordHouse newRecordHouse = JSON.parseObject(data.getJsonData(), NetInfoRecordHouse.class);
            newRecordHouse.setStatus(1);
            newRecordHouse.setCreator(data.getCreator());
            newRecordHouse.setApprover(data.getApprover());
            newRecordHouse.setBeUpgradeId(oldRecordHouse.getBeUpgradeId()==null?newRecordHouse.getId():oldRecordHouse.getBeUpgradeId());
            newRecordHouse.setVersion(oldRecordHouse.getVersion() + 1);
            netInfoRecordHouseDao.addNetInfoRecordHouse(newRecordHouse);
            //附件拷贝
            baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class), oldRecordHouse.getId(), newRecordHouse.getId());//附件拷贝

            //标准表数据调整
            List<BasicHouseCaseSummary> caseSummaryList = basicHouseCaseSummaryDao.getByCaseHouseId(data.getDataId());
            if(CollectionUtils.isNotEmpty(caseSummaryList)){
                for (BasicHouseCaseSummary caseSummary: caseSummaryList) {
                    caseSummary.setBisNewest(false);
                    basicHouseCaseSummaryService.updateBaseHouseSummary(caseSummary);
                }
            }
            BasicHouseCaseSummary basicHouseCaseSummary = new BasicHouseCaseSummary();
            basicHouseCaseSummary.setCaseHouseId(newRecordHouse.getId());
            basicHouseCaseSummary.setProvince(newRecordHouse.getProvince());
            basicHouseCaseSummary.setCity(newRecordHouse.getCity());
            basicHouseCaseSummary.setDistrict(newRecordHouse.getDistrict());
            basicHouseCaseSummary.setFullName(publicBasicService.getFullName(newRecordHouse.getName(), newRecordHouse.getBuildingNumber(), newRecordHouse.getUnitNumber(), newRecordHouse.getHouseNumber()));
            basicHouseCaseSummary.setStreet(newRecordHouse.getStreet());
            basicHouseCaseSummary.setTradingTime(newRecordHouse.getNegotiatedDate());
            basicHouseCaseSummary.setTradingUnitPrice(newRecordHouse.getUnitPrice());
            basicHouseCaseSummary.setVersion(newRecordHouse.getVersion());
            basicHouseCaseSummary.setHouseType(newRecordHouse.getBelongType());
            basicHouseCaseSummary.setHouseCategory(newRecordHouse.getBelongCategory());
            basicHouseCaseSummary.setArea(newRecordHouse.getArea());
            basicHouseCaseSummary.setEstateName(newRecordHouse.getName());
            basicHouseCaseSummary.setDealType(newRecordHouse.getDealType());
            basicHouseCaseSummary.setCurrentPrice(newRecordHouse.getCurrentPrice());
            basicHouseCaseSummary.setConsultPrice(newRecordHouse.getConsultPrice());
            basicHouseCaseSummary.setAssessStandardDate(newRecordHouse.getAssessStandardDate());
            basicHouseCaseSummary.setRealizationRatios(newRecordHouse.getHouseRealizationRatios());
            basicHouseCaseSummary.setRealizationCycle(newRecordHouse.getRealizationCycle());
            basicHouseCaseSummary.setDealPartInfo(newRecordHouse.getDealPartInfo());
            basicHouseCaseSummary.setApprover(newRecordHouse.getApprover());
            basicHouseCaseSummary.setTradingType(newRecordHouse.getTradingType());
            basicHouseCaseSummary.setCreator(newRecordHouse.getCreator());
            basicHouseCaseSummaryService.addBaseHouseSummary(basicHouseCaseSummary);

            //附件拷贝
            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableId(oldRecordHouse.getId());
            example.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(basicHouseCaseSummary.getId());
            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCaseSummary.class));
            baseAttachmentService.copyFtpAttachments(example, attachmentDto);
        }
        if ("土地".equals(data.getType())) {
            NetInfoRecordLand oldRecordLand = netInfoRecordLandDao.getNetInfoRecordLandById(data.getDataId());
            oldRecordLand.setBisNewest(false);
            netInfoRecordLandDao.updateNetInfoRecordLand(oldRecordLand,true);
            NetInfoRecordLand newRecordLand = JSON.parseObject(data.getJsonData(), NetInfoRecordLand.class);
            newRecordLand.setStatus(1);
            newRecordLand.setCreator(data.getCreator());
            newRecordLand.setApprover(data.getApprover());
            newRecordLand.setStatus(1);
            newRecordLand.setBeUpgradeId(oldRecordLand.getBeUpgradeId()==null?newRecordLand.getId():oldRecordLand.getBeUpgradeId());
            newRecordLand.setVersion(oldRecordLand.getVersion() + 1);
            netInfoRecordLandDao.addNetInfoRecordLand(newRecordLand);
            //附件拷贝
            baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class), oldRecordLand.getId(), newRecordLand.getId());//附件拷贝

        }


    }
}

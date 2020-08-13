package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdEconomicIndicatorsItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 经济指标
 *
 * @author noOne
 */

@Service
public class MdEconomicIndicatorsService {
    @Autowired
    private MdEconomicIndicatorsDao mdEconomicIndicatorsDao;
    @Autowired
    private MdEconomicIndicatorsItemDao mdEconomicIndicatorsItemDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEconomicIndicatorsCenterList;

    public List<MdEconomicIndicators> getEconomicIndicatorsList(MdEconomicIndicators mdEconomicIndicators) {
        return mdEconomicIndicatorsDao.getEconomicIndicatorsList(mdEconomicIndicators);
    }

    public void saveEconomicIndicators(MdEconomicIndicators mdEconomicIndicators) {
        if (mdEconomicIndicators.getId() != null && mdEconomicIndicators.getId() > 0) {
            mdEconomicIndicatorsDao.updateEconomicIndicators(mdEconomicIndicators);
        } else {
            mdEconomicIndicators.setCreator(commonService.thisUserAccount());
            mdEconomicIndicatorsDao.addEconomicIndicators(mdEconomicIndicators);
        }
    }

    public void deleteById(Integer id) {
        mdEconomicIndicatorsDao.deleteEconomicIndicators(id);
        mdEconomicIndicatorsItemDao.deleteItemByEconomicId(id);
    }

    public MdEconomicIndicators saveEconomicIndicators(MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto) {
        MdEconomicIndicators mdEconomicIndicators = mdEconomicIndicatorsApplyDto.getEconomicIndicators();
        List<MdEconomicIndicatorsItem> mdEconomicIndicatorsItems = mdEconomicIndicatorsApplyDto.getEconomicIndicatorsItemList();
        if (mdEconomicIndicators != null) {
            saveEconomicIndicators(mdEconomicIndicators);
            if (mdEconomicIndicators.getId() != null) {
                mdEconomicIndicatorsItemDao.deleteItemByEconomicId(mdEconomicIndicators.getId());
            }
        }
        if (CollectionUtils.isNotEmpty(mdEconomicIndicatorsItems)) {
            for (MdEconomicIndicatorsItem mdEconomicIndicatorsItem : mdEconomicIndicatorsItems) {
                mdEconomicIndicatorsItem.setEconomicId(mdEconomicIndicators.getId());
                mdEconomicIndicatorsItemDao.addEconomicIndicatorsItem(mdEconomicIndicatorsItem);
            }
        }
        return mdEconomicIndicators;
    }

    public void copyDataEconomicIndicators(Integer copyId, MdEconomicIndicators target) {
        if (copyId == null) {
            return;
        }
        MdEconomicIndicatorsApplyDto applyDto = getEconomicIndicatorsInfo(copyId);
        if (applyDto == null) {
            return;
        }
        if (applyDto.getEconomicIndicators() == null) {
            return;
        }
        applyDto.getEconomicIndicators().setId(null);
        applyDto.getEconomicIndicators().setPlanDetailsId(null);
        BeanCopyHelp.copyPropertiesIgnoreNull(applyDto.getEconomicIndicators(), target);
        saveEconomicIndicators(target);
        if (CollectionUtils.isEmpty(applyDto.getEconomicIndicatorsItemList())) {
            return;
        }
        List<MdEconomicIndicatorsItem> list = new ArrayList<>() ;
        for (MdEconomicIndicatorsItem indicatorsItem : applyDto.getEconomicIndicatorsItemList()) {
            MdEconomicIndicatorsItem indicatorsItem2 = new MdEconomicIndicatorsItem();
            indicatorsItem.setPlanDetailsId(null);
            indicatorsItem.setMcId(null);
            indicatorsItem.setEconomicId(null);
            indicatorsItem.setPid(null);
            indicatorsItem.setId(null);
            BeanCopyHelp.copyPropertiesIgnoreNull(indicatorsItem, indicatorsItem2);
            indicatorsItem2.setEconomicId(target.getId());
            indicatorsItem2.setCreator(commonService.thisUserAccount());
            indicatorsItem2.setGmtCreated(DateUtils.now());
            indicatorsItem2.setGmtModified(DateUtils.now());
            list.add(indicatorsItem2) ;
        }
        if (CollectionUtils.isNotEmpty(list)){
            mdEconomicIndicatorsItemDao.batchInset(list);
        }
    }

    public MdEconomicIndicatorsApplyDto getEconomicIndicatorsInfo(Integer economicId) {
        MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = new MdEconomicIndicatorsApplyDto();
        MdEconomicIndicators economicIndicators = mdEconomicIndicatorsDao.getEconomicIndicatorsById(economicId);
        MdEconomicIndicatorsItem where = new MdEconomicIndicatorsItem();
        where.setEconomicId(economicId);
        List<MdEconomicIndicatorsItem> economicIndicatorsItemList = mdEconomicIndicatorsItemDao.getEconomicIndicatorsItemList(where);
        mdEconomicIndicatorsApplyDto.setEconomicIndicators(economicIndicators);
        mdEconomicIndicatorsApplyDto.setEconomicIndicatorsItemList(economicIndicatorsItemList);
        return mdEconomicIndicatorsApplyDto;
    }

    /**
     * 根据估价对象id获取经济指标
     *
     * @param judgeId
     * @return
     */
    public MdEconomicIndicators getMdEconomicIndicatorsByJudgeId(Integer judgeId) {
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeId);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
        DeclareBuildEngineeringAndEquipmentCenter where = new DeclareBuildEngineeringAndEquipmentCenter();
        where.setRealEstateId(declareRecord.getDataTableId());
        where.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
        List<DeclareBuildEngineeringAndEquipmentCenter> equipmentCenters = declareBuildEconomicIndicatorsCenterList.declareBuildEngineeringAndEquipmentCenterList(where);
        return mdEconomicIndicatorsDao.getEconomicIndicatorsById(equipmentCenters.get(0).getIndicatorId());
    }
}

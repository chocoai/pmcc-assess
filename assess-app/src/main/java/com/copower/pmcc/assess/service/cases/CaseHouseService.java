package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingLeaseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingSellVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:38
 * @Description:案例 房屋信息
 */
@Service
public class CaseHouseService {
    @Autowired
    private CaseHouseDao caseHouseDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseHouseTradingSellService caseHouseTradingSellService;
    @Autowired
    private CaseHouseTradingLeaseService caseHouseTradingLeaseService;
    @Autowired
    private CaseHouseEquipmentService caseHouseEquipmentService;
    @Autowired
    private CaseHouseFaceStreetService caseHouseFaceStreetService;
    @Autowired
    private CaseHouseIntelligentService caseHouseIntelligentService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseWaterService caseHouseWaterService;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseHouseListVos(CaseHouse caseHouse) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouse> caseHouses = getCaseHouseList(caseHouse);
        vo.setRows(caseHouses);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public void initAndUpdateSon(Integer oldId, Integer newId) {
        CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
        caseHouseTradingLease.setHouseId(oldId);
        CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
        caseHouseTradingSell.setHouseId(oldId);
        CaseHouseEquipment caseHouseEquipment = new CaseHouseEquipment();
        caseHouseEquipment.setHouseId(oldId);
        CaseHouseFaceStreet caseHouseFaceStreet = new CaseHouseFaceStreet();
        caseHouseFaceStreet.setHouseId(oldId);
        CaseHouseIntelligent caseHouseIntelligent = new CaseHouseIntelligent();
        caseHouseIntelligent.setHouseId(oldId);
        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
        caseHouseRoom.setHouseId(oldId);
        CaseHouseWater caseHouseWater = new CaseHouseWater();
        caseHouseWater.setHouseId(oldId);
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(oldId);
        CaseHouseTrading queryTrading = new CaseHouseTrading();
        queryTrading.setHouseId(oldId);

        List<CaseHouseTradingVo> caseHouseTradingList = caseHouseTradingService.caseHouseTradingList(queryTrading);
        List<CaseHouseTradingSellVo> caseHouseTradingSellVos = caseHouseTradingSellService.caseHouseTradingSellList(caseHouseTradingSell, null);
        List<CaseHouseTradingLeaseVo> caseHouseTradingLeaseVos = caseHouseTradingLeaseService.caseHouseTradingLeaseList(caseHouseTradingLease, null);
        List<CaseHouseEquipment> caseHouseEquipments = caseHouseEquipmentService.getCaseHouseEquipmentList(caseHouseEquipment);
        List<CaseHouseFaceStreet> caseHouseFaceStreets = caseHouseFaceStreetService.getCaseHouseFaceStreetList(caseHouseFaceStreet);
        List<CaseHouseIntelligent> caseHouseIntelligents = caseHouseIntelligentService.getCaseHouseIntelligentList(caseHouseIntelligent);
        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
        List<CaseHouseWater> caseHouseWaters = caseHouseWaterService.getCaseHouseWaterList(caseHouseWater);
        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        //初始化
        if (oldId == null) {
            if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)) {
                for (CaseHouseTradingSellVo oo : caseHouseTradingSellVos) {
                    CaseHouseTradingSell caseHouseTradingSell1 = new CaseHouseTradingSell();
                    caseHouseTradingSell1.setId(oo.getId());
                    caseHouseTradingSellService.deleteCaseHouseTradingSell(caseHouseTradingSell1);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseTradingLeaseVos)) {
                for (CaseHouseTradingLeaseVo oo : caseHouseTradingLeaseVos) {
                    CaseHouseTradingLease caseHouseTradingLease1 = new CaseHouseTradingLease();
                    caseHouseTradingLease1.setId(oo.getId());
                    caseHouseTradingLeaseService.deleteCaseHouseTradingLease(caseHouseTradingLease1);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseEquipments)) {
                for (CaseHouseEquipment oo : caseHouseEquipments) {
                    caseHouseEquipmentService.deleteCaseHouseEquipment(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseFaceStreets)) {
                for (CaseHouseFaceStreet oo : caseHouseFaceStreets) {
                    caseHouseFaceStreetService.deleteCaseHouseFaceStreet(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseIntelligents)) {
                for (CaseHouseIntelligent oo : caseHouseIntelligents) {
                    caseHouseIntelligentService.deleteCaseHouseIntelligent(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseRooms)) {
                for (CaseHouseRoom oo : caseHouseRooms) {
                    caseHouseRoomService.deleteCaseHouseRoom(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseWaters)) {
                for (CaseHouseWater oo : caseHouseWaters) {
                    caseHouseWaterService.deleteCaseHouseWater(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
                for (CaseHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                    caseHouseCorollaryEquipmentService.deleteCaseHouseCorollaryEquipment(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseTradingList)) {
                for (CaseHouseTradingVo oo : caseHouseTradingList) {
                    CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
                    caseHouseTrading.setId(oo.getId());
                    caseHouseTradingService.deleteCaseHouseTrading(caseHouseTrading);
                }
            }
        }
        //修改子类
        if (oldId != null) {
            if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)) {
                for (CaseHouseTradingSellVo oo : caseHouseTradingSellVos) {
                    oo.setHouseId(newId);
                    caseHouseTradingSellService.saveAndUpdateCaseHouseTradingSell(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseTradingLeaseVos)) {
                for (CaseHouseTradingLeaseVo oo : caseHouseTradingLeaseVos) {
                    oo.setHouseId(newId);
                    caseHouseTradingLeaseService.saveAndUpdateCaseHouseTradingLease(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseEquipments)) {
                for (CaseHouseEquipment oo : caseHouseEquipments) {
                    oo.setHouseId(newId);
                    caseHouseEquipmentService.updateCaseHouseEquipment(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseFaceStreets)) {
                for (CaseHouseFaceStreet oo : caseHouseFaceStreets) {
                    oo.setHouseId(newId);
                    caseHouseFaceStreetService.updateCaseHouseFaceStreet(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseIntelligents)) {
                for (CaseHouseIntelligent oo : caseHouseIntelligents) {
                    oo.setHouseId(newId);
                    caseHouseIntelligentService.updateCaseHouseIntelligent(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseRooms)) {
                for (CaseHouseRoom oo : caseHouseRooms) {
                    oo.setHouseId(newId);
                    caseHouseRoomService.updateCaseHouseRoom(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseWaters)) {
                for (CaseHouseWater oo : caseHouseWaters) {
                    oo.setHouseId(newId);
                    caseHouseWaterService.updateCaseHouseWater(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
                for (CaseHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                    oo.setHouseId(newId);
                    caseHouseCorollaryEquipmentService.updateCaseHouseCorollaryEquipment(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseTradingList)) {
                for (CaseHouseTradingVo oo : caseHouseTradingList) {
                    oo.setHouseId(newId);
                    caseHouseTradingService.saveAndUpdateCaseHouseTrading(oo);
                }
            }
        }
    }

    public List<CaseHouse> getCaseHouseList(CaseHouse caseHouse) {

        return caseHouseDao.getHouseList(caseHouse);
    }

    public CaseHouse getCaseHouseById(Integer id) {
        return caseHouseDao.getHouseById(id);
    }

    public Integer getVersion(Integer id){
        if(id==null) return 0;
        CaseHouse caseHouse = caseHouseDao.getHouseById(id);
        if(caseHouse==null) return 0;
        return caseHouse.getVersion();
    }

    public Integer saveAndUpdateCaseHouse(CaseHouse caseHouse) {
        Integer id = null;
        if (caseHouse.getId() == null || caseHouse.getId().intValue() == 0) {
            id = caseHouseDao.addHouse(caseHouse);
            return id;
        } else {
            caseHouseDao.updateHouse(caseHouse);
            return null;
        }
    }

    public Integer upgradeVersion(CaseHouse caseHouse) throws Exception {
        Integer id = null;
        if (caseHouse.getId() == null || caseHouse.getId().intValue() == 0) {
            caseHouse.setVersion(0);
            id = caseHouseDao.addHouse(caseHouse);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class), id);
            caseHouse.setId(id);
            return id;
        } else {
            CaseHouse oo = caseHouseDao.getHouseById(caseHouse.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseHouse, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            int oldId = caseHouse.getId();
            int newId = caseHouseDao.addHouse(oo);
            caseHouse.setId(newId);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class), newId);
            return newId;
        }
    }

    public boolean deleteCaseHouse(Integer id) {

        return caseHouseDao.deleteHouse(id);
    }

    public List<CaseHouse> autoCompleteCaseHouse(Integer unitId, String houseNumber, Integer maxRows) throws Exception {
        List<CaseHouse> caseHouseList = caseHouseDao.autoCompleteCaseHouse(unitId, houseNumber);
        Ordering<CaseHouse> ordering = Ordering.from(new Comparator<CaseHouse>() {
            @Override
            public int compare(CaseHouse o1, CaseHouse o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(caseHouseList, ordering);
        List<CaseHouse> list = new ArrayList<CaseHouse>(10);
        if (!ObjectUtils.isEmpty(caseHouseList)) {
            for (int i = 0; i < maxRows; i++) {
                if (i < caseHouseList.size()) {
                    list.add(caseHouseList.get(i));
                }
            }
        }
        return list;
    }

    public CaseHouseVo getCaseHouseVo(CaseHouse caseHouse){
        CaseHouseVo vo = new CaseHouseVo();
        BaseDataDic dataDic = null;
        BeanUtils.copyProperties(caseHouse,vo);
        if (caseHouse.getUseEnvironment() != null) {
            dataDic = baseDataDicService.getDataDicById(caseHouse.getUseEnvironment());
            vo.setUseEnvironmentName(dataDic.getName());
            dataDic = null;
        }
        if (caseHouse.getCertUse() != null) {
            dataDic = baseDataDicService.getDataDicById(caseHouse.getCertUse());
            vo.setCertUseName(dataDic.getName());
            dataDic = null;
        }
        if (caseHouse.getPracticalUse() != null) {
            dataDic = baseDataDicService.getDataDicById(caseHouse.getPracticalUse());
            vo.setPracticalUseName(dataDic.getName());
            dataDic = null;
        }
        vo.setNewsHuxingName(baseDataDicService.getNameById(caseHouse.getNewsHuxing()));
        return vo;
    }
}

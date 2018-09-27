package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingLeaseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingSellVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseHouseListVos(CaseHouse caseHouse){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouse> caseHouses = getCaseHouseList(caseHouse);
        vo.setRows(caseHouses);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public void initAndUpdateSon(Integer id){
        final int num = 0 ;
        CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
        caseHouseTradingLease.setHouseId(num);
        CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
        caseHouseTradingSell.setHouseId(num);
        CaseHouseEquipment caseHouseEquipment = new CaseHouseEquipment();
        caseHouseEquipment.setHouseId(num);
        CaseHouseFaceStreet caseHouseFaceStreet = new CaseHouseFaceStreet();
        caseHouseFaceStreet.setHouseId(num);
        CaseHouseIntelligent caseHouseIntelligent = new CaseHouseIntelligent();
        caseHouseIntelligent.setHouseId(num);
        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
        caseHouseRoom.setHouseId(num);
        CaseHouseWater caseHouseWater = new CaseHouseWater();
        caseHouseWater.setHouseId(num);
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(num);
        List<CaseHouseTradingSellVo> caseHouseTradingSellVos = caseHouseTradingSellService.caseHouseTradingSellList(caseHouseTradingSell,null);
        List<CaseHouseTradingLeaseVo> caseHouseTradingLeaseVos = caseHouseTradingLeaseService.caseHouseTradingLeaseList(caseHouseTradingLease,null);
        List<CaseHouseEquipment> caseHouseEquipments = caseHouseEquipmentService.getCaseHouseEquipmentList(caseHouseEquipment);
        List<CaseHouseFaceStreet> caseHouseFaceStreets = caseHouseFaceStreetService.getCaseHouseFaceStreetList(caseHouseFaceStreet);
        List<CaseHouseIntelligent> caseHouseIntelligents = caseHouseIntelligentService.getCaseHouseIntelligentList(caseHouseIntelligent);
        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
        List<CaseHouseWater> caseHouseWaters = caseHouseWaterService.getCaseHouseWaterList(caseHouseWater);
        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        if (id==null){//初始化
            if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)){
                for (CaseHouseTradingSellVo oo:caseHouseTradingSellVos){
                    CaseHouseTradingSell caseHouseTradingSell1 = new CaseHouseTradingSell();
                    caseHouseTradingSell1.setId(oo.getId());
                    caseHouseTradingSellService.deleteCaseHouseTradingSell(caseHouseTradingSell1);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseTradingLeaseVos)){
                for (CaseHouseTradingLeaseVo oo:caseHouseTradingLeaseVos){
                    CaseHouseTradingLease caseHouseTradingLease1 = new CaseHouseTradingLease();
                    caseHouseTradingLease1.setId(oo.getId());
                    caseHouseTradingLeaseService.deleteCaseHouseTradingLease(caseHouseTradingLease1);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseEquipments)){
                for (CaseHouseEquipment oo:caseHouseEquipments){
                    caseHouseEquipmentService.deleteCaseHouseEquipment(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseFaceStreets)){
                for (CaseHouseFaceStreet oo:caseHouseFaceStreets){
                    caseHouseFaceStreetService.deleteCaseHouseFaceStreet(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseIntelligents)){
                for (CaseHouseIntelligent oo:caseHouseIntelligents){
                    caseHouseIntelligentService.deleteCaseHouseIntelligent(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseRooms)){
                for (CaseHouseRoom oo:caseHouseRooms){
                    caseHouseRoomService.deleteCaseHouseRoom(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseWaters)){
                for (CaseHouseWater oo:caseHouseWaters){
                    caseHouseWaterService.deleteCaseHouseWater(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)){
                for (CaseHouseCorollaryEquipment oo :caseHouseCorollaryEquipments){
                    caseHouseCorollaryEquipmentService.deleteCaseHouseCorollaryEquipment(oo.getId());
                }
            }
        }

        if (id!=null){//修改子类
            if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)){
                for (CaseHouseTradingSellVo oo:caseHouseTradingSellVos){
                    oo.setHouseId(id);
                    caseHouseTradingSellService.saveAndUpdateCaseHouseTradingSell(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseTradingLeaseVos)){
                for (CaseHouseTradingLeaseVo oo:caseHouseTradingLeaseVos){
                    oo.setHouseId(id);
                    caseHouseTradingLeaseService.saveAndUpdateCaseHouseTradingLease(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseEquipments)){
                for (CaseHouseEquipment oo:caseHouseEquipments){
                    oo.setHouseId(id);
                    caseHouseEquipmentService.updateCaseHouseEquipment(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseFaceStreets)){
                for (CaseHouseFaceStreet oo:caseHouseFaceStreets){
                    oo.setHouseId(id);
                    caseHouseFaceStreetService.updateCaseHouseFaceStreet(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseIntelligents)){
                for (CaseHouseIntelligent oo:caseHouseIntelligents){
                    oo.setHouseId(id);
                    caseHouseIntelligentService.updateCaseHouseIntelligent(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseRooms)){
                for (CaseHouseRoom oo:caseHouseRooms){
                    oo.setHouseId(id);
                    caseHouseRoomService.updateCaseHouseRoom(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseWaters)){
                for (CaseHouseWater oo:caseHouseWaters){
                    oo.setHouseId(id);
                    caseHouseWaterService.updateCaseHouseWater(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)){
                for (CaseHouseCorollaryEquipment oo :caseHouseCorollaryEquipments){
                    oo.setHouseId(id);
                    caseHouseCorollaryEquipmentService.updateCaseHouseCorollaryEquipment(oo);
                }
            }
        }
    }

    public List<CaseHouse> getCaseHouseList(CaseHouse caseHouse){

        return caseHouseDao.getHouseList(caseHouse);
    }

    public CaseHouse getCaseHouseById(Integer id){

        return caseHouseDao.getHouseById(id);
    }

    public Integer saveAndUpdateCaseHouse(CaseHouse caseHouse){
        Integer id = null ;

        if (caseHouse.getId()==null || caseHouse.getId().intValue()==0){
            caseHouse.setCreator(commonService.thisUserAccount());
            caseHouse.setVersion(0);
            id =  caseHouseDao.addHouse(caseHouse);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class), id);
            return id;
        }else {
            CaseHouse oo = caseHouseDao.getHouseById(caseHouse.getId());
            if (oo != null){
                if (oo.getVersion()==null){
                    oo.setVersion(0);
                }
            }
            caseHouse.setVersion(oo.getVersion()+1);
            caseHouseDao.updateHouse(caseHouse);
            return  null;
        }
    }

    public boolean deleteCaseHouse(Integer id){

        return caseHouseDao.deleteHouse(id);
    }
}

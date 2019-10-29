package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingLeaseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingSellVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
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
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
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

        List<CaseHouseTradingVo> caseHouseTradingList = caseHouseTradingService.caseHouseTradingVoList(queryTrading);
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

    public Integer getVersion(Integer id) {
        if (id == null) return 0;
        CaseHouse caseHouse = caseHouseDao.getHouseById(id);
        if (caseHouse == null) return 0;
        return caseHouse.getVersion();
    }

    public Integer saveAndUpdateCaseHouse(CaseHouse caseHouse) {
        Integer id = null;
        if (caseHouse.getId() == null || caseHouse.getId().intValue() == 0) {
            id = caseHouseDao.addHouse(caseHouse);
            caseHouse.setId(id);
            return id;
        } else {
            caseHouseDao.updateHouse(caseHouse);
            return null;
        }
    }

    public int updateUnitId(Integer oldUnitId, Integer newUnitId) {
        return caseHouseDao.updateUnitId(oldUnitId, newUnitId);
    }

    public boolean deleteCaseHouse(Integer id) {

        return caseHouseDao.deleteHouse(id);
    }

    public List<CustomCaseEntity> autoCompleteCaseHouse(String houseNumber, Integer unitId) throws Exception {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> houseList = caseHouseDao.getLatestVersionHouseList(houseNumber, unitId);
        return houseList;
    }

    public CaseHouseVo getCaseHouseVo(CaseHouse caseHouse) {
        CaseHouseVo vo = new CaseHouseVo();
        BeanUtils.copyProperties(caseHouse, vo);
        vo.setUseEnvironmentName(baseDataDicService.getNameById(caseHouse.getUseEnvironment()));
        vo.setCertUseName(baseDataDicService.getNameById(caseHouse.getCertUse()));
        vo.setPracticalUseName(baseDataDicService.getNameById(caseHouse.getPracticalUse()));
        vo.setOrientationName(baseDataDicService.getNameById(caseHouse.getOrientation()));
        vo.setSpatialDistributionName(baseDataDicService.getNameById(caseHouse.getSpatialDistribution()));
        vo.setResearchTypeName(baseDataDicService.getNameById(caseHouse.getResearchType()));
        return vo;
    }

    public Boolean hasHouse(String houseNumber, Integer unitId) {
        return caseHouseDao.getHouseCount(houseNumber, unitId) > 0;
    }

    public CaseEstateTagging getCaseEstateTaggingByUnitId(Integer houseId) throws Exception {
        CaseEstateTagging query = new CaseEstateTagging();
        query.setDataId(houseId);
        query.setType(EstateTaggingTypeEnum.HOUSE.getKey());
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(caseEstateTaggingList)) {
            return caseEstateTaggingList.get(0);
        }
        return null;
    }

    /**
     * 引用案列中的数据
     *
     * @param id      caseEstate对应id
     * @param tableId basicEstate对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicHouse quoteCaseHouseToBasic(Integer id, Integer tableId) throws Exception {
        if (id == null || tableId == null) {
            throw new Exception("null point");
        }
        basicHouseService.clearInvalidChildData(tableId);
        //更新批量申请表信息
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicHouse.class), tableId);
        batchDetail.setQuoteId(id);
        batchDetail.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        basicApplyBatchDetailDao.updateInfo(batchDetail);
        BasicHouse basicHouse = basicHouseService.getBasicHouseById(tableId);
        CaseHouse oldCaseHouse = this.getCaseHouseById(id);
        BeanUtils.copyProperties(oldCaseHouse, basicHouse, "id");
        basicHouseService.saveAndUpdateBasicHouse(basicHouse,false);

        CaseHouseTrading queryTrading = new CaseHouseTrading();
        queryTrading.setHouseId(id);
        List<CaseHouseTradingVo> oldCaseHouseTradings = caseHouseTradingService.caseHouseTradingVoList(queryTrading);
        if (!ObjectUtils.isEmpty(oldCaseHouseTradings)) {
            BasicHouseTrading basicHouseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
            BeanUtils.copyProperties(oldCaseHouseTradings.get(0), basicHouseTrading, "id");
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading,false);
        }

        //删除原有的附件
        SysAttachmentDto deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(tableId);
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(deleteExample);
        if (!org.springframework.util.CollectionUtils.isEmpty(attachmentList)) {
            for (SysAttachmentDto item : attachmentList) {
                baseAttachmentService.deleteAttachment(item.getId());
            }
        }


        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(id);
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(tableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("house_id", String.valueOf(tableId));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("house_id=" + id);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingSell.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseWater.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseWaterDrain.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseIntelligent.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseFaceStreet.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseEquipment.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
        caseHouseRoom.setHouseId(id);
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(id);
        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        List<CaseHouseDamagedDegree> damagedDegreeList = caseHouseDamagedDegreeService.getDamagedDegreeListByHouseId(id);


        if (!ObjectUtils.isEmpty(caseHouseRooms)) {
            try {
                for (CaseHouseRoom oo : caseHouseRooms) {
                    BasicHouseRoom room = new BasicHouseRoom();
                    BeanUtils.copyProperties(oo, room);
                    room.setId(null);
                    room.setHouseId(tableId);
                    room.setCreator(commonService.thisUserAccount());
                    room.setGmtCreated(null);
                    room.setGmtModified(null);
                    basicHouseRoomService.saveAndUpdateBasicHouseRoom(room,false);
                    CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                    caseHouseRoomDecorate.setRoomId(oo.getId());
                    List<CaseHouseRoomDecorate> caseHouseRoomDecorateList = caseHouseRoomDecorateService.getCaseHouseRoomDecorateList(caseHouseRoomDecorate);
                    if (!ObjectUtils.isEmpty(caseHouseRoomDecorateList)) {
                        for (CaseHouseRoomDecorate po : caseHouseRoomDecorateList) {
                            BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                            BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                            basicHouseRoomDecorate.setRoomId(room.getId());
                            basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
                            basicHouseRoomDecorate.setGmtCreated(null);
                            basicHouseRoomDecorate.setGmtModified(null);
                            basicHouseRoomDecorate.setId(null);
                            basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate,false);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
            try {
                for (CaseHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                    SysAttachmentDto query = new SysAttachmentDto();
                    query.setTableId(oo.getId());
                    query.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouseCorollaryEquipment.class));
                    List<SysAttachmentDto> sysAttachmentDtoList1 = baseAttachmentService.getAttachmentList(query);
                    BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, po);
                    po.setId(null);
                    po.setHouseId(tableId);
                    po.setCreator(commonService.thisUserAccount());
                    po.setGmtCreated(null);
                    po.setGmtModified(null);
                    Integer EquipmentId = basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po, false);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList1)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList1) {
                            attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(EquipmentId);
                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        if (CollectionUtils.isNotEmpty(damagedDegreeList)) {
            try {
                for (CaseHouseDamagedDegree caseHouseDamagedDegree : damagedDegreeList) {
                    BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
                    BeanUtils.copyProperties(caseHouseDamagedDegree, basicHouseDamagedDegree);
                    basicHouseDamagedDegree.setId(null);
                    basicHouseDamagedDegree.setHouseId(tableId);
                    basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
                    basicHouseDamagedDegree.setGmtCreated(null);
                    basicHouseDamagedDegree.setGmtModified(null);
                    basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree,false);
                    List<CaseHouseDamagedDegreeDetail> damagedDegreeDetailList = caseHouseDamagedDegreeService.getDamagedDegreeDetails(caseHouseDamagedDegree.getId());
                    if (CollectionUtils.isNotEmpty(damagedDegreeDetailList)) {
                        for (CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail : damagedDegreeDetailList) {
                            BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                            BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetail);
                            basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                            basicHouseDamagedDegreeDetail.setId(null);
                            basicHouseDamagedDegreeDetail.setHouseId(tableId);
                            basicHouseDamagedDegreeDetail.setCreator(commonService.thisUserAccount());
                            basicHouseDamagedDegreeDetail.setGmtCreated(null);
                            basicHouseDamagedDegreeDetail.setGmtModified(null);
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail,false);
                        }
                    }
                }
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
        }
        return basicHouse;
    }
}

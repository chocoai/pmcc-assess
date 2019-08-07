package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDamagedDegreeDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kings on 2019-1-23.
 */
@Service
public class BasicApplyTransferService {
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private BasicHouseDamagedDegreeDao basicHouseDamagedDegreeDao;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 楼盘拷贝
     *
     * @param basicEstateOld
     * @param basicEstateLandStateOld
     * @return
     * @throws Exception
     */
    private BasicEstate copyBasicEstate(Integer appId, BasicEstate basicEstateOld, BasicEstateLandState basicEstateLandStateOld) throws Exception {
        BasicEstate basicEstateNew = new BasicEstate();
        if (basicEstateOld != null) {
            BeanUtils.copyProperties(basicEstateOld, basicEstateNew);
            basicEstateNew.setApplyId(appId);
            basicEstateNew.setId(null);
            basicEstateNew.setGmtCreated(null);
            basicEstateNew.setGmtModified(null);
            basicEstateService.saveAndUpdateBasicEstate(basicEstateNew);

            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableId(basicEstateOld.getId());
            example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));

            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(basicEstateNew.getId());
            baseAttachmentService.copyFtpAttachments(example,attachmentDto);
            if (basicEstateLandStateOld != null) {
                BasicEstateLandState basicEstateLandStateNew = new BasicEstateLandState();
                BeanUtils.copyProperties(basicEstateLandStateOld, basicEstateLandStateNew);
                basicEstateLandStateNew.setApplyId(appId);
                basicEstateLandStateNew.setId(null);
                basicEstateLandStateNew.setEstateId(basicEstateNew.getId());
                basicEstateLandStateNew.setGmtCreated(null);
                basicEstateLandStateNew.setGmtModified(null);
                basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandStateNew);
            }
        }
        BasicEstateParking queryBasicEstateParkingOld = new BasicEstateParking();
        if (basicEstateOld.getId() != null) {
            queryBasicEstateParkingOld.setEstateId(basicEstateOld.getId());
        }

        List<BasicEstateParking> basicEstateParkingList = null;
        basicEstateParkingList = basicEstateParkingService.basicEstateParkingList(queryBasicEstateParkingOld);
        if (basicEstateNew != null) {
            if (basicEstateNew.getId() != null) {
                this.copyBasicParking(basicEstateParkingList, basicEstateNew);

                StringBuilder sqlBuilder = new StringBuilder();
                SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                HashMap<String, String> map = Maps.newHashMap();
                map.put("estate_id", String.valueOf(basicEstateNew.getId()));
                map.put("creator", commonService.thisUserAccount());
                synchronousDataDto.setFieldDefaultValue(map);
                synchronousDataDto.setWhereSql("estate_id=" + basicEstateOld.getId());
                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

                sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.ESTATE, basicEstateOld.getApplyId(), appId));
                ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
            }
        }
        return basicEstateNew;
    }

    private void copyBasicParking(List<BasicEstateParking> basicEstateParkingList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateParkingList)) {
            for (BasicEstateParking oo : basicEstateParkingList) {
                BasicEstateParking estateParking = new BasicEstateParking();
                BeanUtils.copyProperties(oo, estateParking);
                estateParking.setId(null);
                estateParking.setGmtModified(null);
                estateParking.setGmtCreated(null);
                estateParking.setEstateId(basicEstateNew.getId());
                basicEstateParkingService.saveAndUpdateBasicEstateParking(estateParking);

                List<SysAttachmentDto> sysAttachmentDtoList = null;
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(estateParking.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
    }


    /**
     * 楼栋复制
     *
     * @param buildingOld
     * @param estateId
     * @return
     * @throws Exception
     */
    private BasicBuilding copyBasicBuilding(Integer appId, BasicBuilding buildingOld, Integer estateId) throws Exception {
        BasicBuilding basicBuildingNew = new BasicBuilding();
        if (buildingOld != null) {
            BeanUtils.copyProperties(buildingOld, basicBuildingNew);
            basicBuildingNew.setEstateId(estateId);
            basicBuildingNew.setApplyId(appId);
            basicBuildingNew.setId(null);
            basicBuildingNew.setGmtCreated(null);
            basicBuildingNew.setGmtModified(null);
            basicBuildingService.saveAndUpdateBasicBuilding(basicBuildingNew);

            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableId(buildingOld.getId());
            example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));

            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(basicBuildingNew.getId());
            baseAttachmentService.copyFtpAttachments(example,attachmentDto);

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("building_id", String.valueOf(basicBuildingNew.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("building_id=" + buildingOld.getId());
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

            sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.BUILDING, buildingOld.getApplyId(), appId));
            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return basicBuildingNew;
    }

    /**
     * 单元复制
     *
     * @param unitOld
     * @param basicBuildingId
     * @return
     * @throws Exception
     */
    private BasicUnit copyBasicUnit(Integer appId, BasicUnit unitOld, Integer basicBuildingId) throws Exception {
        BasicUnit basicUnitNew = new BasicUnit();
        if (unitOld != null) {
            BeanUtils.copyProperties(unitOld, basicUnitNew);
            basicUnitNew.setBuildingId(basicBuildingId);
            basicUnitNew.setApplyId(appId);
            basicUnitNew.setId(null);
            basicUnitNew.setGmtCreated(null);
            basicUnitNew.setGmtModified(null);
            basicUnitService.saveAndUpdateBasicUnit(basicUnitNew);

            //附件拷贝
            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableId(unitOld.getId());
            example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(basicUnitNew.getId());
            baseAttachmentService.copyFtpAttachments(example,attachmentDto);
        }
        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
        if (unitOld != null) {
            if (unitOld.getId() != null) {
                queryBasicUnitHuxing.setUnitId(unitOld.getId());
            }
        }
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(queryBasicUnitHuxing);
        if (basicUnitNew != null&&basicUnitNew.getId() != null) {
            this.copyBasicHuxing(basicUnitHuxingList, basicUnitNew);

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("unit_id", String.valueOf(basicUnitNew.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("unit_id=" + unitOld.getId());
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

            sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.UNIT, unitOld.getApplyId(), appId));
            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return basicUnitNew;
    }

    public void copyBasicHuxing(List<BasicUnitHuxing> basicUnitHuxingList, BasicUnit basicUnitNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing unitHuxing : basicUnitHuxingList) {
                BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                BeanUtils.copyProperties(unitHuxing, basicUnitHuxing);
                basicUnitHuxing.setId(null);
                basicUnitHuxing.setGmtCreated(null);
                basicUnitHuxing.setGmtModified(null);
                basicUnitHuxing.setUnitId(basicUnitNew.getId());
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing);

                SysAttachmentDto example = new SysAttachmentDto();
                example.setTableId(unitHuxing.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));

                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(basicUnitHuxing.getId());
                baseAttachmentService.copyFtpAttachments(example,attachmentDto);
            }
        }
    }

    /**
     * 房屋 复制
     *
     * @param houseOld
     * @param tradingOld
     * @param unitId
     * @throws Exception
     */
    public BasicHouse copyBasicHouse(Integer appId, BasicHouse houseOld, BasicHouseTrading tradingOld, Integer unitId) throws Exception {
        BasicHouse basicHouseNew = new BasicHouse();
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        if (houseOld != null) {
            BeanUtils.copyProperties(houseOld, basicHouseNew);
            basicHouseNew.setUnitId(unitId);
            basicHouseNew.setApplyId(appId);
            basicHouseNew.setId(null);
            basicHouseNew.setGmtCreated(null);
            basicHouseNew.setGmtModified(null);
            basicHouseService.saveAndUpdateBasicHouse(basicHouseNew);

            if (tradingOld != null) {
                BeanUtils.copyProperties(tradingOld, basicHouseTrading);
                basicHouseTrading.setApplyId(appId);
                basicHouseTrading.setId(null);
                basicHouseTrading.setGmtCreated(null);
                basicHouseTrading.setGmtModified(null);
                basicHouseTrading.setHouseId(basicHouseNew.getId());
                basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading);
            }
        }
        List<BasicHouseRoom> basicHouseRoomList = null;
        List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = null;

        List<BasicHouseDamagedDegree> basicHouseDamagedDegreeList = null;
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        BasicHouseDamagedDegree queryHouseDamagedDegree = new BasicHouseDamagedDegree();

        if (houseOld != null&&houseOld.getId() != null) {
            queryRoom.setHouseId(houseOld.getId());
            queryBasicHouseCorollaryEquipment.setHouseId(houseOld.getId());
            queryHouseDamagedDegree.setHouseId(houseOld.getId());
        }

        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(houseOld.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicHouseNew.getId());
        baseAttachmentService.copyFtpAttachments(example,attachmentDto);

        basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
        basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
        basicHouseDamagedDegreeList = basicHouseDamagedDegreeDao.getDamagedDegreeList(queryHouseDamagedDegree);

        if (basicHouseNew != null&&basicHouseNew.getId() != null) {
            this.copyBasicHouseRoom(basicHouseRoomList, basicHouseNew);
            this.copyBasicCorollaryEquipment(basicHouseCorollaryEquipmentList, basicHouseNew);
            this.copyBasicDamagedDegree(basicHouseDamagedDegreeList, basicHouseNew);

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("house_id", String.valueOf(basicHouseNew.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("house_id=" + houseOld.getId());
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

            sqlBuilder.append(this.copyBasicTagging(EstateTaggingTypeEnum.HOUSE, houseOld.getApplyId(), appId));
            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return basicHouseNew;
    }


    private void copyBasicCorollaryEquipment(List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseCorollaryEquipmentList)) {
            basicHouseCorollaryEquipmentList.forEach(oo -> {
                try {
                    BasicHouseCorollaryEquipment basicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, basicHouseCorollaryEquipment);
                    basicHouseCorollaryEquipment.setId(null);
                    basicHouseCorollaryEquipment.setGmtCreated(null);
                    basicHouseCorollaryEquipment.setGmtModified(null);
                    basicHouseCorollaryEquipment.setHouseId(basicHouseNew.getId());
                    basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(basicHouseCorollaryEquipment);

                    SysAttachmentDto example = new SysAttachmentDto();
                    example.setTableId(oo.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(basicHouseCorollaryEquipment.getId());
                    baseAttachmentService.copyFtpAttachments(example,attachmentDto);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicHouseRoom(List<BasicHouseRoom> basicHouseRoomList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
            for (BasicHouseRoom oo : basicHouseRoomList) {
                BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
                BeanUtils.copyProperties(oo, basicHouseRoom);
                basicHouseRoom.setId(null);
                basicHouseRoom.setGmtCreated(null);
                basicHouseRoom.setGmtModified(null);
                basicHouseRoom.setHouseId(basicHouseNew.getId());
                basicHouseRoomService.saveAndUpdateBasicHouseRoom(basicHouseRoom);

                BasicHouseRoomDecorate query = new BasicHouseRoomDecorate();
                query.setRoomId(oo.getId());
                List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(query);
                if (!ObjectUtils.isEmpty(basicHouseRoomDecorateList)) {
                    for (BasicHouseRoomDecorate po : basicHouseRoomDecorateList) {
                        BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                        BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                        basicHouseRoomDecorate.setGmtCreated(null);
                        basicHouseRoomDecorate.setGmtModified(null);
                        basicHouseRoomDecorate.setId(null);
                        basicHouseRoomDecorate.setRoomId(basicHouseRoom.getId());
                        basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
                    }
                }
            }
        }
    }

    private void copyBasicDamagedDegree(List<BasicHouseDamagedDegree> basicHouseDamagedDegrees, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseDamagedDegrees)) {
            for (BasicHouseDamagedDegree old : basicHouseDamagedDegrees) {
                BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
                BeanUtils.copyProperties(old, basicHouseDamagedDegree);
                basicHouseDamagedDegree.setId(null);
                basicHouseDamagedDegree.setGmtCreated(null);
                basicHouseDamagedDegree.setGmtModified(null);
                basicHouseDamagedDegree.setHouseId(basicHouseNew.getId());
                basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree);

                List<BasicHouseDamagedDegreeDetail> degreeDetails = basicHouseDamagedDegreeService.getDamagedDegreeDetails(basicHouseDamagedDegree.getId());
                if (!CollectionUtils.isEmpty(degreeDetails)) {
                    for (BasicHouseDamagedDegreeDetail degreeDetail : degreeDetails) {
                        BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                        BeanUtils.copyProperties(degreeDetail, basicHouseDamagedDegreeDetail);
                        basicHouseDamagedDegreeDetail.setId(null);
                        basicHouseDamagedDegreeDetail.setGmtCreated(null);
                        basicHouseDamagedDegreeDetail.setGmtModified(null);
                        basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                        basicHouseDamagedDegreeDetail.setHouseId(basicHouseNew.getId());
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
                    }
                }
            }
        }
    }

    /**
     * 复制
     *
     * @param planDetailsId
     * @throws Exception
     */
    public BasicApply copy(Integer planDetailsId) throws Exception {
        BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId);
        BasicApply basicApplycopy = new BasicApply();
        basicApplycopy.setCopyId(basicApply.getId());
        basicApplycopy.setDraftFlag(true);
        List<BasicApply> list = basicApplyDao.getBasicApplyList(basicApplycopy);
        //是否存在已复制数据
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        if (basicApply != null) {
            basicApplycopy.setId(null);
            basicApplycopy.setStatus(ProjectStatusEnum.STARTAPPLY.getKey());
            basicApplyService.saveBasicApply(basicApplycopy);

            //取数据
            BasicEstate basicEstateOld = publicBasicService.getBasicEstateByAppId(basicApply.getId());
            BasicEstateLandState basicEstateLandStateOld = publicBasicService.getEstateLandStateByAppId(basicApply.getId());
            BasicBuilding basicBuildingOld = publicBasicService.getBasicBuildingByAppId(basicApply.getId());
            BasicUnit basicUnitOld = publicBasicService.getBasicUnitByAppId(basicApply.getId());
            BasicHouse basicHouseOld = publicBasicService.getBasicHouseVoByAppId(basicApply.getId());
            BasicHouseTrading basicTradingOld = publicBasicService.getBasicHouseTradingByAppId(basicApply.getId());

            BasicEstate basicEstateNew = this.copyBasicEstate(basicApplycopy.getId(), basicEstateOld, basicEstateLandStateOld);//处理楼盘
            BasicBuilding basicBuilding = this.copyBasicBuilding(basicApplycopy.getId(), basicBuildingOld, basicEstateNew.getId());//处理楼栋
            BasicUnit basicUnit = this.copyBasicUnit(basicApplycopy.getId(), basicUnitOld, basicBuilding.getId());//处理单元
            BasicHouse basicHouse = this.copyBasicHouse(basicApplycopy.getId(), basicHouseOld, basicTradingOld, basicUnit.getId());//处理房屋

            //补全数据
            basicApplycopy.setPlanDetailsId(null);
            basicApplycopy.setProcessInsId(null);
            basicApplycopy.setProvince(basicEstateNew.getProvince());
            basicApplycopy.setCity(basicEstateNew.getCity());
            basicApplycopy.setEstateName(basicEstateNew.getName());
            basicApplycopy.setBuildingNumber(basicBuilding.getBuildingNumber());
            basicApplycopy.setUnitNumber(basicUnit.getUnitNumber());
            basicApplycopy.setHouseNumber(basicHouse.getHouseNumber());
            basicApplycopy.setBuildingPartInMode(BasicApplyPartInModeEnum.ADD.getKey());
            basicApplycopy.setEstatePartInMode(BasicApplyPartInModeEnum.ADD.getKey());
            basicApplycopy.setUnitPartInMode(BasicApplyPartInModeEnum.ADD.getKey());
            basicApplycopy.setHousePartInMode(BasicApplyPartInModeEnum.ADD.getKey());
            basicApplyService.updateBasicApply(basicApplycopy);
            return basicApplycopy;
        }
        return null;
    }

    /**
     * @param sourcePlanDetailsId
     * @param targetPlanDetailsId
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicApply copyForExamine(Integer sourcePlanDetailsId, Integer targetPlanDetailsId) throws Exception {
        BasicApply sourceBasicApply = basicApplyService.getBasicApplyByPlanDetailsId(sourcePlanDetailsId);
        if (sourceBasicApply == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        BasicApply targetBasicApply = basicApplyService.getBasicApplyByPlanDetailsId(targetPlanDetailsId);
        if (targetBasicApply != null) {
            basicApplyService.deleteBasicApply(targetBasicApply.getId());  //先清除數據
        }
        targetBasicApply = new BasicApply();
        BeanUtils.copyProperties(sourceBasicApply, targetBasicApply);
        targetBasicApply.setPlanDetailsId(targetPlanDetailsId);
        targetBasicApply.setStatus(ProjectStatusEnum.STARTAPPLY.getKey());
        targetBasicApply.setId(null);
        targetBasicApply.setCreator(commonService.thisUserAccount());
        targetBasicApply.setGmtCreated(new Date());
        targetBasicApply.setGmtModified(new Date());
        basicApplyService.saveBasicApply(targetBasicApply);

        //取数据
        BasicEstate basicEstateSource = publicBasicService.getBasicEstateByAppId(sourceBasicApply.getId());
        BasicEstateLandState basicEstateLandStateSource = publicBasicService.getEstateLandStateByAppId(sourceBasicApply.getId());
        BasicBuilding basicBuildingSource = publicBasicService.getBasicBuildingByAppId(sourceBasicApply.getId());
        BasicUnit basicUnitSource = publicBasicService.getBasicUnitByAppId(sourceBasicApply.getId());
        BasicHouse basicHouseSource = publicBasicService.getBasicHouseVoByAppId(sourceBasicApply.getId());
        BasicHouseTrading basicTradingSource = publicBasicService.getBasicHouseTradingByAppId(sourceBasicApply.getId());

        BasicEstate basicEstateNew = this.copyBasicEstate(targetBasicApply.getId(), basicEstateSource, basicEstateLandStateSource);//处理楼盘
        BasicBuilding basicBuilding = this.copyBasicBuilding(targetBasicApply.getId(), basicBuildingSource, basicEstateNew.getId());//处理楼栋
        BasicUnit basicUnit = this.copyBasicUnit(targetBasicApply.getId(), basicUnitSource, basicBuilding.getId());//处理单元
        this.copyBasicHouse(targetBasicApply.getId(), basicHouseSource, basicTradingSource, basicUnit.getId());//处理房屋
        return targetBasicApply;
    }


    /**
     * 标记复制
     *
     * @param typeEnum
     * @param applyIdSource
     * @param applyTarget
     * @throws Exception
     */
    private String copyBasicTagging(EstateTaggingTypeEnum typeEnum, Integer applyIdSource, Integer applyTarget) throws Exception {
        //sql 拼写有误
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        synchronousDataDto.setWhereSql(String.format("apply_id=%s and type='%s'", applyIdSource, typeEnum.getKey()));
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        HashMap<String, String> map = Maps.newHashMap();
        map.put("apply_id", String.valueOf(applyTarget));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));
        return sqlBuilder.toString();
    }
}

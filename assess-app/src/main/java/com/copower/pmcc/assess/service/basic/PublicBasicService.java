package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/25 10:31
 * @Description:
 */
@Service
public class PublicBasicService {
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseBaseHouseService caseBaseHouseService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private ResidueRatioService residueRatioService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 楼盘回写
     *
     * @param basicEstate
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    private CaseEstate flowWriteCaseEstate(BasicApply basicApply, BasicEstate basicEstate, BasicEstateLandState basicEstateLandState) throws Exception {
        if (basicEstate == null) {
            return null;
        }
        CaseEstate caseEstate = new CaseEstate();
        BeanUtils.copyProperties(basicEstate, caseEstate);
        if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getEstatePartInMode())) {
            caseEstate.setVersion(caseEstateService.getVersion(basicApply.getCaseEstateId()) + 1);
        } else {
            caseEstate.setVersion(1);
        }
        caseEstate.setId(null);
        caseEstate.setGmtCreated(null);
        caseEstate.setGmtModified(null);
        caseEstateService.saveAndUpdateCaseEstate(caseEstate);


        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(basicEstate.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(caseEstate.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        if (basicEstateLandState != null) {
            CaseEstateLandState caseEstateLandState = new CaseEstateLandState();
            BeanUtils.copyProperties(basicEstateLandState, caseEstateLandState);
            caseEstateLandState.setId(null);
            caseEstateLandState.setEstateId(caseEstate.getId());
            caseEstateLandState.setGmtCreated(null);
            caseEstateLandState.setGmtModified(null);
            caseEstateLandStateService.saveAndUpdateCaseEstateLandState(caseEstateLandState);
        }

        BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
        if (basicEstate.getId() != null) {
            queryBasicEstateParking.setEstateId(basicEstate.getId());
        }
        List<BasicEstateParking> basicEstateParkingList = null;
        basicEstateParkingList = basicEstateParkingService.basicEstateParkingList(queryBasicEstateParking);

        if (caseEstate != null) {
            if (caseEstate.getId() != null) {
                this.flowWriteCaseParking(basicEstateParkingList, caseEstate);

                StringBuilder sqlBuilder = new StringBuilder();
                SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                HashMap<String, String> map = Maps.newHashMap();
                map.put("estate_id", String.valueOf(caseEstate.getId()));
                synchronousDataDto.setFieldDefaultValue(map);
                synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
                synchronousDataDto.setIgnoreField(Lists.newArrayList("id", "gmt_created", "gmt_modified"));
                synchronousDataDto.setWhereSql("estate_id=" + basicEstate.getId());
                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseEstateNetwork.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseEstateSupply.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingTraffic.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMedical.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMaterial.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingLeisurePlace.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingFinance.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEnvironment.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEducation.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

                sqlBuilder.append(this.flowWriteCaseTagging(EstateTaggingTypeEnum.ESTATE, basicApply.getId(), caseEstate.getId()));
                ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
            }
        }
        return caseEstate;
    }

    private void flowWriteCaseParking(List<BasicEstateParking> basicEstateParkingList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateParkingList)) {
            for (BasicEstateParking oo : basicEstateParkingList) {
                CaseEstateParking estateParking = new CaseEstateParking();
                BeanUtils.copyProperties(oo, estateParking);
                estateParking.setId(null);
                estateParking.setGmtModified(null);
                estateParking.setGmtCreated(null);
                estateParking.setEstateId(caseEstate.getId());
                caseEstateParkingService.addEstateParking(estateParking);

                SysAttachmentDto example = new SysAttachmentDto();
                example.setTableId(oo.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(estateParking.getId());
                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                baseAttachmentService.copyFtpAttachments(example, attachmentDto);
            }
        }
    }

    /**
     * 标记回写
     *
     * @param typeEnum
     * @param applyIdSource
     * @param dataId
     * @throws Exception
     */
    private String flowWriteCaseTagging(EstateTaggingTypeEnum typeEnum, Integer applyIdSource, Integer dataId) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        synchronousDataDto.setWhereSql(String.format("apply_id=%s and type='%s'", applyIdSource, typeEnum.getKey()));
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseEstateTagging.class));
        synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setIgnoreField(Lists.newArrayList("id", "gmt_created", "gmt_modified"));
        HashMap<String, String> map = Maps.newHashMap();
        map.put("data_id", String.valueOf(dataId));
        synchronousDataDto.setFieldDefaultValue(map);
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));
        return sqlBuilder.toString();
    }

    /**
     * 主楼栋 回写
     *
     * @param basicBuilding
     * @param estateId
     * @return
     * @throws Exception
     */
    private CaseBuilding flowWriteCaseBuilding(BasicApply basicApply, BasicBuilding basicBuilding, Integer estateId) throws Exception {
        CaseBuilding caseBuilding = new CaseBuilding();
        if (basicBuilding != null) {
            BeanUtils.copyProperties(basicBuilding, caseBuilding);
            caseBuilding.setEstateId(estateId);
            caseBuilding.setType(basicApply.getType());
            if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getBuildingPartInMode())) {
                caseBuilding.setVersion(caseBuildingService.getVersion(basicApply.getCaseBuildingId()) + 1);
            } else {
                caseBuilding.setVersion(1);
            }
            caseBuilding.setId(null);
            caseBuilding.setGmtCreated(null);
            caseBuilding.setGmtModified(null);
            caseBuildingService.saveAndUpdateCaseBuilding(caseBuilding);

            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableId(basicBuilding.getId());
            example.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(caseBuilding.getId());
            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
            baseAttachmentService.copyFtpAttachments(example, attachmentDto);

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("building_id", String.valueOf(caseBuilding.getId()));
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
            synchronousDataDto.setIgnoreField(Lists.newArrayList("id", "gmt_created", "gmt_modified"));
            synchronousDataDto.setWhereSql("building_id=" + basicBuilding.getId());

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingOutfit.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingMaintenance.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingSurface.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseBuildingFunction.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

            sqlBuilder.append(this.flowWriteCaseTagging(EstateTaggingTypeEnum.BUILDING, basicApply.getId(), caseBuilding.getId()));
            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return caseBuilding;
    }

    /**
     * 单元 回写
     *
     * @param basicUnit
     * @param caseBuildingId
     * @return
     * @throws Exception
     */
    private CaseUnit flowWriteCaseUnit(BasicApply basicApply, BasicUnit basicUnit, Integer caseBuildingId) throws Exception {
        if (basicApply == null || basicUnit == null) return null;
        CaseUnit caseUnit = new CaseUnit();
        BeanUtils.copyProperties(basicUnit, caseUnit);
        caseUnit.setBuildingId(caseBuildingId);
        caseUnit.setType(basicApply.getType());
        if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getUnitPartInMode())) {
            caseUnit.setVersion(caseUnitService.getVersion(basicApply.getCaseUnitId()) + 1);
        } else {
            caseUnit.setVersion(1);
        }
        caseUnit.setId(null);
        caseUnit.setGmtCreated(null);
        caseUnit.setGmtModified(null);
        caseUnitService.saveAndUpdateCaseUnit(caseUnit);

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(basicUnit.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(caseUnit.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
        queryBasicUnitHuxing.setUnitId(basicUnit.getId());
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(queryBasicUnitHuxing);
        this.flowWriteCaseHuxing(basicUnitHuxingList, caseUnit);

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("unit_id", String.valueOf(caseUnit.getId()));
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setIgnoreField(Lists.newArrayList("id", "gmt_created", "gmt_modified"));
        synchronousDataDto.setWhereSql("unit_id=" + basicUnit.getId());
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseUnitDecorate.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseUnitElevator.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电梯维护sql

        sqlBuilder.append(this.flowWriteCaseTagging(EstateTaggingTypeEnum.UNIT, basicApply.getId(), caseUnit.getId()));
        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return caseUnit;
    }

    public void flowWriteCaseHuxing(List<BasicUnitHuxing> basicUnitHuxingList, CaseUnit caseUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing oo : basicUnitHuxingList) {
                CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
                BeanUtils.copyProperties(oo, caseUnitHuxing);
                caseUnitHuxing.setId(null);
                caseUnitHuxing.setGmtCreated(null);
                caseUnitHuxing.setGmtModified(null);
                caseUnitHuxing.setUnitId(caseUnit.getId());
                caseUnitHuxingService.addCaseUnitHuxing(caseUnitHuxing);

                List<SysAttachmentDto> sysAttachmentDtoList = null;
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(caseUnitHuxing.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
    }

    /**
     * 房屋 回写
     *
     * @param basicHouse
     * @param basicTrading
     * @param unitId
     * @throws Exception
     */
    public CaseHouse flowWriteCaseHouse(BasicApply basicApply, BasicHouse basicHouse, BasicHouseTrading basicTrading, Integer unitId) throws Exception {

        CaseHouse caseHouse = new CaseHouse();
        CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
        if (basicHouse != null) {
            BeanUtils.copyProperties(basicHouse, caseHouse);
            caseHouse.setUnitId(unitId);
            caseHouse.setType(basicApply.getType());
            if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getHousePartInMode())) {
                caseHouse.setVersion(caseHouseService.getVersion(basicApply.getCaseHouseId()) + 1);
            } else {
                caseHouse.setVersion(1);
            }
            caseHouse.setId(null);
            caseHouse.setGmtCreated(null);
            caseHouse.setGmtModified(null);
            caseHouseService.saveAndUpdateCaseHouse(caseHouse);

            if (basicTrading != null) {
                BeanUtils.copyProperties(basicTrading, caseHouseTrading);
                caseHouseTrading.setId(null);
                caseHouseTrading.setGmtCreated(null);
                caseHouseTrading.setGmtModified(null);
                caseHouseTrading.setHouseId(caseHouse.getId());
                caseHouseTradingService.saveAndUpdateCaseHouseTrading(caseHouseTrading);
            }
        }

        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(basicHouse.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(caseHouse.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        List<BasicHouseRoom> basicHouseRoomList = null;
        List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = null;
        List<BasicHouseDamagedDegree> basicHouseDamagedDegreeList = null;
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        queryRoom.setHouseId(basicHouse.getId());
        queryBasicHouseCorollaryEquipment.setHouseId(basicHouse.getId());

        basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
        basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
        basicHouseDamagedDegreeList = basicHouseDamagedDegreeService.getDamagedDegreeList(basicHouse.getId());

        if (caseHouse != null && caseHouse.getId() != null) {
            this.flowWriteCaseHouseRoom(basicHouseRoomList, caseHouse);
            this.flowWriteCaseCorollaryEquipment(basicHouseCorollaryEquipmentList, caseHouse);
            this.flowWriteCaseDamagedDegree(basicHouseDamagedDegreeList, caseHouse);

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("house_id", String.valueOf(caseHouse.getId()));
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setTargeDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
            synchronousDataDto.setIgnoreField(Lists.newArrayList("id", "gmt_created", "gmt_modified"));
            synchronousDataDto.setWhereSql("house_id=" + basicHouse.getId());

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingSell.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseWater.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseWaterDrain.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseIntelligent.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseFaceStreet.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(CaseHouseEquipment.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

            sqlBuilder.append(this.flowWriteCaseTagging(EstateTaggingTypeEnum.HOUSE, basicApply.getId(), caseHouse.getId()));
            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

            flowWriteCaseBaseHouse(basicApply, caseHouse, caseHouseTrading);
        }
        return caseHouse;
    }

    private void flowWriteCaseBaseHouse(BasicApply basicApply, CaseHouse caseHouse, CaseHouseTrading caseHouseTrading) {
        //根据房屋信息逐步找到对应楼盘的信息
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseHouse.getUnitId());
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
        String fullName = String.format("%s%s栋%s单元%s号", caseEstate.getName(), caseBuilding.getBuildingNumber(), caseUnit.getUnitNumber(), caseHouse.getHouseNumber());
        if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getHousePartInMode())) {
            //清除上个版本对应的数据
            CaseBaseHouse where = new CaseBaseHouse();
            where.setType(caseEstate.getType());
            where.setProvince(caseEstate.getProvince());
            where.setCity(caseEstate.getCity());
            where.setDistrict(caseEstate.getDistrict());
            where.setFullName(fullName);
            List<CaseBaseHouse> houseList = caseBaseHouseService.getBaseHouseList(where);
            if (!CollectionUtils.isEmpty(houseList)) {
                for (CaseBaseHouse house : houseList) {
                    caseBaseHouseService.deleteBaseHouseById(house.getId());
                }
            }
        }
        CaseBaseHouse caseBaseHouse = new CaseBaseHouse();
        caseBaseHouse.setCaseHouseId(caseHouse.getId());
        caseBaseHouse.setType(caseEstate.getType());
        caseBaseHouse.setProvince(caseEstate.getProvince());
        caseBaseHouse.setCity(caseEstate.getCity());
        caseBaseHouse.setDistrict(caseEstate.getDistrict());
        caseBaseHouse.setBlockName(caseEstate.getBlockName());
        caseBaseHouse.setFullName(fullName);
        caseBaseHouse.setStreet(caseEstate.getStreet());
        caseBaseHouse.setPracticalUse(caseHouse.getPracticalUse());
        caseBaseHouse.setTradingType(caseHouseTrading.getTradingType());
        caseBaseHouse.setTradingTime(caseHouseTrading.getTradingTime());
        caseBaseHouse.setTradingUnitPrice(caseHouseTrading.getTradingUnitPrice());
        caseBaseHouse.setVersion(caseHouse.getVersion());
        caseBaseHouse.setCreator(caseHouse.getCreator());
        caseBaseHouseService.addBaseHouse(caseBaseHouse);
    }

    private void flowWriteCaseCorollaryEquipment(List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseCorollaryEquipmentList)) {
            basicHouseCorollaryEquipmentList.forEach(oo -> {
                try {
                    CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, caseHouseCorollaryEquipment);
                    caseHouseCorollaryEquipment.setId(null);
                    caseHouseCorollaryEquipment.setGmtCreated(null);
                    caseHouseCorollaryEquipment.setGmtModified(null);
                    caseHouseCorollaryEquipment.setHouseId(caseHouse.getId());
                    caseHouseCorollaryEquipmentService.addCaseHouseCorollaryEquipment(caseHouseCorollaryEquipment);

                    List<SysAttachmentDto> sysAttachmentDtoList = null;
                    SysAttachmentDto queryFile = new SysAttachmentDto();
                    queryFile.setTableId(oo.getId());
                    queryFile.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                            SysAttachmentDto attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(caseHouseCorollaryEquipment.getId());
                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouseCorollaryEquipment.class));
                            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                        }
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void flowWriteCaseHouseRoom(List<BasicHouseRoom> basicHouseRoomList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
            for (BasicHouseRoom oo : basicHouseRoomList) {
                CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
                BeanUtils.copyProperties(oo, caseHouseRoom);
                caseHouseRoom.setId(null);
                caseHouseRoom.setGmtCreated(null);
                caseHouseRoom.setGmtModified(null);
                caseHouseRoom.setHouseId(caseHouse.getId());
                caseHouseRoomService.addCaseHouseRoom(caseHouseRoom);

                BasicHouseRoomDecorate query = new BasicHouseRoomDecorate();
                query.setRoomId(oo.getId());
                List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(query);
                if (!ObjectUtils.isEmpty(basicHouseRoomDecorateList)) {
                    for (BasicHouseRoomDecorate po : basicHouseRoomDecorateList) {
                        CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                        BeanUtils.copyProperties(po, caseHouseRoomDecorate);
                        caseHouseRoomDecorate.setGmtCreated(null);
                        caseHouseRoomDecorate.setGmtModified(null);
                        caseHouseRoomDecorate.setId(null);
                        caseHouseRoomDecorate.setRoomId(caseHouseRoom.getId());
                        caseHouseRoomDecorateService.addCaseHouseRoomDecorate(caseHouseRoomDecorate);
                    }
                }
            }
        }
    }

    private void flowWriteCaseDamagedDegree(List<BasicHouseDamagedDegree> basicHouseDamagedDegrees, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseDamagedDegrees)) {
            for (BasicHouseDamagedDegree basicHouseDamagedDegree : basicHouseDamagedDegrees) {
                CaseHouseDamagedDegree caseHouseDamagedDegree = new CaseHouseDamagedDegree();
                BeanUtils.copyProperties(basicHouseDamagedDegree, caseHouseDamagedDegree);
                caseHouseDamagedDegree.setId(null);
                caseHouseDamagedDegree.setGmtCreated(null);
                caseHouseDamagedDegree.setGmtModified(null);
                caseHouseDamagedDegree.setHouseId(caseHouse.getId());
                caseHouseDamagedDegreeService.addCaseHouseDamagedDegree(caseHouseDamagedDegree);

                List<BasicHouseDamagedDegreeDetail> degreeDetails = basicHouseDamagedDegreeService.getDamagedDegreeDetails(basicHouseDamagedDegree.getId());
                if (!CollectionUtils.isEmpty(degreeDetails)) {
                    for (BasicHouseDamagedDegreeDetail degreeDetail : degreeDetails) {
                        CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail = new CaseHouseDamagedDegreeDetail();
                        BeanUtils.copyProperties(degreeDetail, caseHouseDamagedDegreeDetail);
                        caseHouseDamagedDegreeDetail.setId(null);
                        caseHouseDamagedDegreeDetail.setGmtCreated(null);
                        caseHouseDamagedDegreeDetail.setGmtModified(null);
                        caseHouseDamagedDegreeDetail.setDamagedDegreeId(caseHouseDamagedDegree.getId());
                        caseHouseDamagedDegreeDetail.setHouseId(caseHouse.getId());
                        caseHouseDamagedDegreeService.addCaseHouseDamagedDegreeDetail(caseHouseDamagedDegreeDetail);
                    }
                }
            }
        }
    }

    /**
     * 回写数据并且升级版本
     *
     * @param processInsId
     * @throws Exception
     */
    @Transactional(value = "transactionManagerCase", rollbackFor = Exception.class)
    public void flowWrite(String processInsId) throws Exception {
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
        if (basicApply != null) {
            BasicEstate basicEstate = this.getBasicEstateByAppId(basicApply);
            BasicEstateLandState basicEstateLandState = this.getEstateLandStateByAppId(basicApply);
            BasicBuilding basicBuilding = this.getBasicBuildingByAppId(basicApply);
            BasicUnit basicUnit = this.getBasicUnitByAppId(basicApply);
            BasicHouse basicHouse = this.getBasicHouseVoByAppId(basicApply);
            BasicHouseTrading basicTrading = this.getBasicHouseTradingByAppId(basicApply);
            //1.如果楼盘升级，则新增一条楼盘数据，并将与该楼盘相关关联的楼栋数据关联id更新为新添加的楼盘数据id
            //2.楼栋与单元处理方式与楼盘一致
            Integer estateId = basicApply.getCaseEstateId();
            Integer buildingMainId = basicApply.getCaseBuildingId();
            Integer unitId = basicApply.getCaseUnitId();


            //处理楼盘
            if (StringUtils.isNotBlank(basicApply.getEstatePartInMode()) && basicEstate != null) {
                CaseEstate caseEstate = this.flowWriteCaseEstate(basicApply, basicEstate, basicEstateLandState);
                if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getEstatePartInMode()) && estateId != null && estateId > 0) {
                    //更新楼栋关联id
                    caseBuildingService.updateEstateId(estateId, caseEstate.getId());
                }
                estateId = caseEstate.getId();
                //回写版块到基础数据中
                if (basicApply.getWriteBackBlockFlag() == Boolean.TRUE) {
                    DataBlock dataBlock = new DataBlock();
                    dataBlock.setProvince(basicEstate.getProvince());
                    dataBlock.setCity(basicEstate.getCity());
                    dataBlock.setDistrict(basicEstate.getDistrict());
                    dataBlock.setName(basicEstate.getName());
                    dataBlock.setCreator(basicEstate.getCreator());
                    dataBlockService.saveAndUpdateDataBlock(dataBlock);
                }
            }

            //处理楼栋
            if (StringUtils.isNotBlank(basicApply.getBuildingPartInMode()) && basicBuilding != null) {
                CaseBuilding caseBuilding = this.flowWriteCaseBuilding(basicApply, basicBuilding, estateId);
                if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getBuildingPartInMode()) && buildingMainId != null && buildingMainId > 0) {
                    //更新单元关联id
                    caseUnitService.updateBuildingId(buildingMainId, caseBuilding.getId());
                }
                buildingMainId = caseBuilding.getId();
            }

            //处理单元
            if (StringUtils.isNotBlank(basicApply.getUnitPartInMode()) && basicUnit != null) {
                CaseUnit caseUnit = this.flowWriteCaseUnit(basicApply, basicUnit, buildingMainId);
                if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getUnitPartInMode()) && unitId != null && unitId > 0) {
                    //更新单元关联id
                    caseHouseService.updateUnitId(unitId, caseUnit.getId());
                }
                unitId = caseUnit.getId();
            }

            //处理房屋
            //1.如果是新增则将数据写入一份到baseHouse
            //2.如果是升级则将原baseHouse的数据删除，写入新的数据
            if (StringUtils.isNotBlank(basicApply.getHousePartInMode()) && basicHouse != null) {
                this.flowWriteCaseHouse(basicApply, basicHouse, basicTrading, unitId);
            }
        }
    }

    /**
     * 保存数据
     *
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicApply saveBasicApply(String formData, Boolean isDraft) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return null;
        }

        String jsonContent = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_APPLY.getVar());
        BasicApply basicApply = JSONObject.parseObject(jsonContent, BasicApply.class);
        //检查是否标注了楼盘
        if (!isDraft) {
            taggingValid(basicApply);
        }

        if (basicApply.getId() == null || basicApply.getId() <= 0) {
            basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
            basicApply.setDraftFlag(isDraft);
            basicApply.setCreator(commonService.thisUserAccount());
            basicApplyService.saveBasicApply(basicApply);
        } else {
            basicApply = basicApplyService.getByBasicApplyId(basicApply.getId());
        }

        BasicApplyPartInModeEnum modeEnum = null;
        //楼盘过程数据
        BasicEstate basicEstate = null;
        if (StringUtils.isNotBlank(basicApply.getEstatePartInMode())) {
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar());
            if (StringUtils.isNotBlank(jsonContent)) {
                basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);

                if (basicEstate != null) {
                    modeEnum = BasicApplyPartInModeEnum.getEnumByKey(basicApply.getEstatePartInMode());
                    switch (modeEnum) {
                        case ADD:
                        case REFERENCE:
                            if (caseEstateService.hasEstateByName(basicEstate.getName(), basicEstate.getProvince(), basicEstate.getCity())) {
                                throw new BusinessException("案例中已存在相同名称楼盘");
                            }
                            basicApply.setEstateName(basicEstate.getName());
                            break;
                    }

                    basicEstate.setApplyId(basicApply.getId());
                    basicEstate.setType(basicApply.getType());
                    basicEstateService.saveAndUpdateBasicEstate(basicEstate);
                    basicApply.setBasicEstateId(basicEstate.getId());
                    if (basicEstate.getId() != null) {
                        BasicEstateLandState basicEstateLandState = null;
                        if (StringUtils.isNotEmpty(jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar()))) {
                            basicEstateLandState = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar()), BasicEstateLandState.class);
                            if (basicEstateLandState != null) {
                                basicEstateLandState.setEstateId(basicEstate.getId());
                                basicEstateLandState.setApplyId(basicApply.getId());
                                basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState);
                            }
                        }
                    }
                }
            }
        }

        BasicBuilding basicBuilding = null;
        if (StringUtils.isNotBlank(basicApply.getBuildingPartInMode())) {
            //楼栋主数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
            if (StringUtils.isNotBlank(jsonContent)) {
                basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
                if (basicBuilding != null) {
                    modeEnum = BasicApplyPartInModeEnum.getEnumByKey(basicApply.getBuildingPartInMode());
                    switch (modeEnum) {
                        case ADD:
                        case REFERENCE:
                            //案例库中验证
                            if (basicApply.getCaseEstateId() != null && caseBuildingService.hasBuilding(basicBuilding.getBuildingNumber(), basicApply.getCaseEstateId())) {
                                throw new BusinessException("案例中已存在相同编号的楼栋");
                            }
                            basicApply.setBuildingNumber(basicBuilding.getBuildingNumber());
                            break;
                    }
                    basicBuilding.setApplyId(basicApply.getId());
                    basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding);
                    basicApply.setBasicBuildingId(basicBuilding.getId());
                }
            }
        }

        BasicUnit basicUnit = null;
        if (StringUtils.isNotBlank(basicApply.getUnitPartInMode())) {
            //单元过程数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
            if (StringUtils.isNotEmpty(jsonContent)) {
                basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
                if (basicUnit != null) {
                    modeEnum = BasicApplyPartInModeEnum.getEnumByKey(basicApply.getUnitPartInMode());
                    switch (modeEnum) {
                        case ADD:
                        case REFERENCE:
                            //案例库中验证
                            if (basicApply.getCaseBuildingId() != null && caseUnitService.hasUnit(basicUnit.getUnitNumber(), basicApply.getCaseBuildingId())) {
                                throw new BusinessException("案例中已存在相同编号的单元");
                            }
                            basicApply.setUnitNumber(basicUnit.getUnitNumber());
                            break;
                    }
                    basicUnit.setApplyId(basicApply.getId());
                    basicUnitService.saveAndUpdateBasicUnit(basicUnit);
                    basicApply.setBasicUnitId(basicUnit.getId());
                }
            }
        }

        BasicHouse basicHouse = null;
        if (StringUtils.isNotBlank(basicApply.getHousePartInMode())) {
            //处理房屋数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
            if (StringUtils.isNotEmpty(jsonContent)) {
                basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
                if (basicHouse != null) {
                    modeEnum = BasicApplyPartInModeEnum.getEnumByKey(basicApply.getHousePartInMode());
                    switch (modeEnum) {
                        case ADD:
                        case REFERENCE:
                            //案例库中验证
                            if (basicApply.getCaseUnitId() != null && caseHouseService.hasHouse(basicHouse.getHouseNumber(), basicApply.getCaseUnitId())) {
                                throw new BusinessException("案例中已存在相同编号的房屋");
                            }
                            basicApply.setHouseNumber(basicHouse.getHouseNumber());
                            break;
                    }
                    basicHouse.setApplyId(basicApply.getId());
                    Integer house = basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                    basicApply.setBasicHouseId(house);
                    //交易信息
                    jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar());
                    BasicHouseTrading basicTrading = JSONObject.parseObject(jsonContent, BasicHouseTrading.class);
                    if (basicTrading != null) {
                        basicTrading.setHouseId(house);
                        basicTrading.setApplyId(basicApply.getId());
                        basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading);
                    }
                    //完损度
                    jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_DAMAGED_DEGREE.getVar());
                    List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonContent, BasicHouseDamagedDegree.class);
                    if (!CollectionUtils.isEmpty(damagedDegreeList)) {
                        for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(degree);
                        }
                        //写入成新率
                        String newDegree = residueRatioService.getObservationalRatio(basicHouse.getId());
                        basicHouse.setNewDegree(newDegree);
                        basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                    }
                }
            }
        }
        //更新地图标注信息
        BasicEstateTagging where = new BasicEstateTagging();
        where.setApplyId(0);
        where.setCreator(commonService.thisUserAccount());
        List<BasicEstateTagging> taggings = basicEstateTaggingService.getBasicEstateTaggingList(where);
        if (!CollectionUtils.isEmpty(taggings)) {
            for (BasicEstateTagging tagging : taggings) {
                //防止用户反复修改如楼盘号码楼栋编号等数字
                if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.ESTATE.getKey())) {
                    if (basicEstate != null) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicEstate.getName())) {
                            tagging.setName(basicEstate.getName());
                        }
                    }
                }
                if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.BUILDING.getKey())) {
                    if (basicBuilding != null) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicBuilding.getBuildingNumber())) {
                            tagging.setName(basicBuilding.getBuildingNumber());
                        }
                    }
                }
                if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.UNIT.getKey())) {
                    if (basicUnit != null) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicUnit.getUnitNumber())) {
                            tagging.setName(basicUnit.getUnitNumber());
                        }
                    }
                }
                if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.HOUSE.getKey())) {
                    if (basicHouse != null) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicHouse.getHouseNumber())) {
                            tagging.setName(basicHouse.getHouseNumber());
                        }
                    }
                }
                tagging.setApplyId(basicApply.getId());
                basicEstateTaggingService.updateBasicEstateTagging(tagging);
            }
        }

        basicApply.setDraftFlag(isDraft);
        basicApplyService.updateBasicApply(basicApply);
        return basicApply;
    }

    /**
     * 标注信息验证
     *
     * @param basicApply
     * @throws BusinessException
     */
    private void taggingValid(BasicApply basicApply) throws BusinessException {
        if (StringUtils.isNotBlank(basicApply.getEstatePartInMode())) {
            BasicEstate estate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            if (!basicEstateTaggingService.hasBasicEstateTagging(estate.getId(), EstateTaggingTypeEnum.ESTATE)) {
                throw new BusinessException("楼盘位置信息还未标注");
            }
        }
        if (StringUtils.isNotBlank(basicApply.getBuildingPartInMode()) && basicApply.getType() != 2) {
            BasicBuildingVo building = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            if (!basicEstateTaggingService.hasBasicEstateTagging(building.getId(), EstateTaggingTypeEnum.BUILDING)) {
                throw new BusinessException("楼栋位置信息还未标注");
            }
        }
        if (StringUtils.isNotBlank(basicApply.getUnitPartInMode())) {
            BasicUnit unit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            if (!basicEstateTaggingService.hasBasicEstateTagging(unit.getId(), EstateTaggingTypeEnum.UNIT)) {
                throw new BusinessException("单元位置信息还未标注");
            }
        }
        if (StringUtils.isNotBlank(basicApply.getHousePartInMode())) {
            BasicHouse house = basicHouseService.getHouseByApplyId(basicApply.getId());
            if (!basicEstateTaggingService.hasBasicEstateTagging(house.getId(), EstateTaggingTypeEnum.HOUSE)) {
                throw new BusinessException("房屋户型图朝向还未设置");
            }
        }
    }

    public BasicEstateVo getBasicEstateByAppId(BasicApply basicApply) {
        BasicEstate estate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (estate == null) return null;
        return basicEstateService.getBasicEstateVo(estate);
    }

    public BasicEstateVo getBasicEstateById(Integer id) throws Exception {
        return basicEstateService.getBasicEstateVo(basicEstateService.getBasicEstateById(id));
    }

    public BasicEstateLandStateVo getEstateLandStateByAppId(BasicApply basicApply) throws Exception {
        BasicEstateVo vo = getBasicEstateByAppId(basicApply);
        if (vo == null) return null;
        BasicEstateLandState basicEstateLandState = basicEstateLandStateService.getLandStateByEstateId(vo.getId());
        if (basicEstateLandState == null) return null;
        return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState);
    }

    public BasicEstateLandStateVo getEstateLandStateByEstateId(Integer estateId) throws Exception {
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setEstateId(estateId);
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(basicEstateLandState);
        if (!ObjectUtils.isEmpty(basicEstateLandStateList)) {
            return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateList.get(0));
        } else {
            return null;
        }
    }

    public BasicBuildingVo getBasicBuildingByAppId(BasicApply basicApply) throws Exception {
        return basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
    }

    public BasicBuildingVo getBasicBuildingById(Integer id) throws Exception {
        return basicBuildingService.getBasicBuildingVo(basicBuildingService.getBasicBuildingById(id));
    }

    public BasicUnit getBasicUnitByAppId(BasicApply basicApply) {
        return basicUnitService.getBasicUnitByApplyId(basicApply.getId());
    }

    public BasicUnit getBasicUnitById(Integer id) throws Exception {
        return basicUnitService.getBasicUnitById(id);
    }

    public BasicHouseTradingVo getBasicHouseTradingByAppId(BasicApply basicApply) throws Exception {
        BasicHouseVo basicHouseVo = getBasicHouseVoByAppId(basicApply);
        if(basicHouseVo==null) return null;
        return getBasicHouseTradingByHouseId(basicHouseVo.getId());
    }

    public BasicHouseTradingVo getBasicHouseTradingByHouseId(Integer houseId) throws Exception {
        return basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(houseId));
    }

    public BasicHouseVo getBasicHouseVoByAppId(BasicApply basicApply) throws Exception {
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
        if (basicHouse == null) return null;
        return basicHouseService.getBasicHouseVo(basicHouse);
    }

    public BasicHouseVo getBasicHouseVoById(Integer id) throws Exception {
        return basicHouseService.getBasicHouseVo(basicHouseService.getBasicHouseById(id));
    }

    //处理标注老数据
    public void disposeTaggingData()throws Exception{
        //获取applyId不为null的标注
        List<BasicEstateTagging> basicEstateTaggingList = basicEstateTaggingService.getApplyIdIsNotNullList();
        for (BasicEstateTagging tagging: basicEstateTaggingList) {
            if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.ESTATE.getKey())) {
                BasicEstate estate = basicEstateService.getBasicEstateByApplyId(tagging.getApplyId());
                //判断是否已经存在标注
                if(!basicEstateTaggingService.hasBasicEstateTagging(estate.getId(), EstateTaggingTypeEnum.ESTATE)){
                    //不存在则写入tableId
                    tagging.setTableId(estate.getId());
                    basicEstateTaggingService.updateBasicEstateTagging(tagging);
                }
            }
            if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.BUILDING.getKey())) {
                BasicBuildingVo building = basicBuildingService.getBasicBuildingByApplyId(tagging.getApplyId());
                if(!basicEstateTaggingService.hasBasicEstateTagging(building.getId(), EstateTaggingTypeEnum.BUILDING)) {
                    tagging.setTableId(building.getId());
                    basicEstateTaggingService.updateBasicEstateTagging(tagging);
                }
            }
            if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.UNIT.getKey())) {
                BasicUnit unit = basicUnitService.getBasicUnitByApplyId(tagging.getApplyId());
                if(!basicEstateTaggingService.hasBasicEstateTagging(unit.getId(), EstateTaggingTypeEnum.UNIT)) {
                    tagging.setTableId(unit.getId());
                    basicEstateTaggingService.updateBasicEstateTagging(tagging);
                }
            }
            if (Objects.equal(tagging.getType(), EstateTaggingTypeEnum.HOUSE.getKey())) {
                BasicHouse house = basicHouseService.getHouseByApplyId(tagging.getApplyId());
                if(!basicEstateTaggingService.hasBasicEstateTagging(house.getId(), EstateTaggingTypeEnum.HOUSE)) {
                    tagging.setTableId(house.getId());
                    basicEstateTaggingService.updateBasicEstateTagging(tagging);
                }
            }
        }
    }

}

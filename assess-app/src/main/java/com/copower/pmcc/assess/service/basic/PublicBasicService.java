package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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
    private BasicHouseTradingLeaseService basicHouseTradingLeaseService;
    @Autowired
    private BasicHouseTradingSellService basicHouseTradingSellService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseWaterService basicHouseWaterService;
    @Autowired
    private BasicHouseIntelligentService basicHouseIntelligentService;
    @Autowired
    private BasicHouseWaterDrainService basicHouseWaterDrainService;
    @Autowired
    private BasicHouseFaceStreetService basicHouseFaceStreetService;
    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicUnitElevatorService basicUnitElevatorService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateNetworkService basicEstateNetworkService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicEstateSupplyService basicEstateSupplyService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicMatchingEducationService basicMatchingEducationService;
    @Autowired
    private BasicMatchingEnvironmentService basicMatchingEnvironmentService;
    @Autowired
    private BasicMatchingFinanceService basicMatchingFinanceService;
    @Autowired
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    @Autowired
    private BasicMatchingMaterialService basicMatchingMaterialService;
    @Autowired
    private BasicMatchingMedicalService basicMatchingMedicalService;
    @Autowired
    private BasicMatchingTrafficService basicMatchingTrafficService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicBuildingMainService basicBuildingMainService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicBuildingSurfaceService basicBuildingSurfaceService;
    @Autowired
    private BasicBuildingMaintenanceService basicBuildingMaintenanceService;
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private CaseBuildingFunctionService caseBuildingFunctionService;
    @Autowired
    private CaseBuildingMaintenanceService caseBuildingMaintenanceService;
    @Autowired
    private CaseBuildingOutfitService caseBuildingOutfitService;
    @Autowired
    private CaseBuildingSurfaceService caseBuildingSurfaceService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseUnitDecorateService caseUnitDecorateService;
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingSellService caseHouseTradingSellService;
    @Autowired
    private CaseHouseWaterDrainService caseHouseWaterDrainService;
    @Autowired
    private CaseHouseTradingLeaseService caseHouseTradingLeaseService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private CaseHouseEquipmentService caseHouseEquipmentService;
    @Autowired
    private CaseHouseFaceStreetService caseHouseFaceStreetService;
    @Autowired
    private CaseHouseIntelligentService caseHouseIntelligentService;
    @Autowired
    private CaseHouseWaterService caseHouseWaterService;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseBaseHouseService caseBaseHouseService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataBlockService dataBlockService;

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
        CaseEstate caseEstate = new CaseEstate();
        if (basicEstate != null) {
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

            List<SysAttachmentDto> sysAttachmentDtoList = null;
            SysAttachmentDto query = new SysAttachmentDto();
            query.setTableId(basicEstate.getId());
            query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(caseEstate.getId());
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                }
            }

            if (basicEstateLandState != null) {
                CaseEstateLandState caseEstateLandState = new CaseEstateLandState();
                BeanUtils.copyProperties(basicEstateLandState, caseEstateLandState);
                caseEstateLandState.setId(null);
                caseEstateLandState.setEstateId(caseEstate.getId());
                caseEstateLandState.setGmtCreated(null);
                caseEstateLandState.setGmtModified(null);
                caseEstateLandStateService.saveAndUpdateCaseEstateLandState(caseEstateLandState);
            }
        }

        BasicEstateNetwork queryBasicEstateNetwork = new BasicEstateNetwork();
        BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
        BasicEstateSupply queryBasicEstateSupply = new BasicEstateSupply();
        BasicEstateTagging queryBasicEstateTagging = new BasicEstateTagging();
        BasicMatchingEducation queryBasicMatchingEducation = new BasicMatchingEducation();
        BasicMatchingEnvironment queryBasicMatchingEnvironment = new BasicMatchingEnvironment();
        BasicMatchingFinance queryBasicMatchingFinance = new BasicMatchingFinance();
        BasicMatchingLeisurePlace queryBasicMatchingLeisurePlace = new BasicMatchingLeisurePlace();
        BasicMatchingMaterial queryBasicMatchingMaterial = new BasicMatchingMaterial();
        BasicMatchingMedical queryBasicMatchingMedical = new BasicMatchingMedical();
        BasicMatchingTraffic queryBasicMatchingTraffic = new BasicMatchingTraffic();

        if (basicEstate.getId() != null) {
            queryBasicEstateNetwork.setEstateId(basicEstate.getId());
            queryBasicEstateParking.setEstateId(basicEstate.getId());
            queryBasicEstateSupply.setEstateId(basicEstate.getId());
            queryBasicEstateTagging.setApplyId(basicApply.getId());
            queryBasicMatchingTraffic.setEstateId(basicEstate.getId());
            queryBasicMatchingMedical.setEstateId(basicEstate.getId());
            queryBasicMatchingMaterial.setEstateId(basicEstate.getId());
            queryBasicMatchingLeisurePlace.setEstateId(basicEstate.getId());
            queryBasicMatchingFinance.setEstateId(basicEstate.getId());
            queryBasicMatchingEnvironment.setEstateId(basicEstate.getId());
            queryBasicMatchingEducation.setEstateId(basicEstate.getId());
        }

        List<BasicEstateNetwork> basicEstateNetworkList = null;
        List<BasicEstateParking> basicEstateParkingList = null;
        List<BasicEstateSupply> basicEstateSupplyList = null;
        List<BasicEstateTagging> basicEstateTaggingList = null;
        List<BasicMatchingEducation> basicMatchingEducationList = null;
        List<BasicMatchingEnvironment> basicMatchingEnvironmentList = null;
        List<BasicMatchingFinance> basicMatchingFinanceList = null;
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = null;
        List<BasicMatchingMaterial> basicMatchingMaterialList = null;
        List<BasicMatchingMedical> basicMatchingMedicalList = null;
        List<BasicMatchingTraffic> basicMatchingTrafficList = null;

        basicEstateNetworkList = basicEstateNetworkService.basicEstateNetworkList(queryBasicEstateNetwork);
        basicEstateParkingList = basicEstateParkingService.basicEstateParkingList(queryBasicEstateParking);
        basicEstateSupplyList = basicEstateSupplyService.basicEstateSupplyList(queryBasicEstateSupply);
        basicEstateTaggingList = basicEstateTaggingService.basicEstateTaggingList(queryBasicEstateTagging);

        basicMatchingEducationList = basicMatchingEducationService.basicMatchingEducationList(queryBasicMatchingEducation);
        basicMatchingEnvironmentList = basicMatchingEnvironmentService.basicMatchingEnvironmentList(queryBasicMatchingEnvironment);
        basicMatchingFinanceList = basicMatchingFinanceService.basicMatchingFinanceList(queryBasicMatchingFinance);
        basicMatchingLeisurePlaceList = basicMatchingLeisurePlaceService.basicMatchingLeisurePlaceList(queryBasicMatchingLeisurePlace);
        basicMatchingMaterialList = basicMatchingMaterialService.basicMatchingMaterialList(queryBasicMatchingMaterial);
        basicMatchingMedicalList = basicMatchingMedicalService.basicMatchingMedicalList(queryBasicMatchingMedical);
        basicMatchingTrafficList = basicMatchingTrafficService.basicMatchingTrafficList(queryBasicMatchingTraffic);
        if (caseEstate != null) {
            if (caseEstate.getId() != null) {
                this.flowWriteCaseNetwork(basicEstateNetworkList, caseEstate);
                this.flowWriteCaseParking(basicEstateParkingList, caseEstate);
                this.flowWriteCaseSupply(basicEstateSupplyList, caseEstate);
                this.flowWriteCaseTraffic(basicMatchingTrafficList, caseEstate);
                this.flowWriteCaseMedical(basicMatchingMedicalList, caseEstate);
                this.flowWriteCaseMaterial(basicMatchingMaterialList, caseEstate);
                this.flowWriteCaseLeisurePlace(basicMatchingLeisurePlaceList, caseEstate);
                this.flowWriteCaseFinance(basicMatchingFinanceList, caseEstate);
                this.flowWriteCaseEnvironment(basicMatchingEnvironmentList, caseEstate);
                this.flowWriteCaseEducation(basicMatchingEducationList, caseEstate);
                this.flowWriteCaseTagging(basicEstateTaggingList,caseEstate);
            }
        }
        return caseEstate;
    }

    private void flowWriteCaseTraffic(List<BasicMatchingTraffic> basicMatchingTrafficList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingTrafficList)) {
            for (BasicMatchingTraffic oo : basicMatchingTrafficList) {
                CaseMatchingTraffic caseMatchingTraffic = new CaseMatchingTraffic();
                BeanUtils.copyProperties(oo, caseMatchingTraffic);
                caseMatchingTraffic.setId(null);
                caseMatchingTraffic.setGmtCreated(null);
                caseMatchingTraffic.setGmtModified(null);
                caseMatchingTraffic.setEstateId(caseEstate.getId());
                caseMatchingTrafficService.addMatchingTraffic(caseMatchingTraffic);
            }
        }
    }

    private void flowWriteCaseMedical(List<BasicMatchingMedical> basicMatchingMedicalList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingMedicalList)) {
            for (BasicMatchingMedical oo : basicMatchingMedicalList) {
                CaseMatchingMedical caseMatchingMedical = new CaseMatchingMedical();
                BeanUtils.copyProperties(oo, caseMatchingMedical);
                caseMatchingMedical.setId(null);
                caseMatchingMedical.setGmtCreated(null);
                caseMatchingMedical.setGmtModified(null);
                caseMatchingMedical.setEstateId(caseEstate.getId());
                caseMatchingMedicalService.addCaseMatchingMedical(caseMatchingMedical);
            }
        }
    }

    private void flowWriteCaseMaterial(List<BasicMatchingMaterial> basicMatchingMaterialList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingMaterialList)) {
            for (BasicMatchingMaterial oo : basicMatchingMaterialList) {
                CaseMatchingMaterial caseMatchingMaterial = new CaseMatchingMaterial();
                BeanUtils.copyProperties(oo, caseMatchingMaterial);
                caseMatchingMaterial.setId(null);
                caseMatchingMaterial.setGmtCreated(null);
                caseMatchingMaterial.setGmtModified(null);
                caseMatchingMaterial.setEstateId(caseEstate.getId());
                caseMatchingMaterialService.addCaseMatchingMaterial(caseMatchingMaterial);
            }
        }
    }

    private void flowWriteCaseLeisurePlace(List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingLeisurePlaceList)) {
            for (BasicMatchingLeisurePlace oo : basicMatchingLeisurePlaceList) {
                CaseMatchingLeisurePlace caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
                BeanUtils.copyProperties(oo, caseMatchingLeisurePlace);
                caseMatchingLeisurePlace.setId(null);
                caseMatchingLeisurePlace.setGmtCreated(null);
                caseMatchingLeisurePlace.setGmtModified(null);
                caseMatchingLeisurePlace.setEstateId(caseEstate.getId());
                caseMatchingLeisurePlaceService.addCaseMatchingLeisurePlace(caseMatchingLeisurePlace);
            }
        }
    }

    private void flowWriteCaseFinance(List<BasicMatchingFinance> basicMatchingFinanceList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingFinanceList)) {
            for (BasicMatchingFinance oo : basicMatchingFinanceList) {
                CaseMatchingFinance caseMatchingFinance = new CaseMatchingFinance();
                BeanUtils.copyProperties(oo, caseMatchingFinance);
                caseMatchingFinance.setId(null);
                caseMatchingFinance.setGmtCreated(null);
                caseMatchingFinance.setGmtModified(null);
                caseMatchingFinance.setEstateId(caseEstate.getId());
                caseMatchingFinanceService.addCaseMatchingFinance(caseMatchingFinance);
            }
        }
    }

    private void flowWriteCaseEnvironment(List<BasicMatchingEnvironment> basicMatchingEnvironmentList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingEnvironmentList)) {
            for (BasicMatchingEnvironment oo : basicMatchingEnvironmentList) {
                CaseMatchingEnvironment caseMatchingEnvironment = new CaseMatchingEnvironment();
                BeanUtils.copyProperties(oo, caseMatchingEnvironment);
                caseMatchingEnvironment.setId(null);
                caseMatchingEnvironment.setGmtCreated(null);
                caseMatchingEnvironment.setGmtModified(null);
                caseMatchingEnvironment.setEstateId(caseEstate.getId());
                caseMatchingEnvironmentService.addCaseMatchingEnvironment(caseMatchingEnvironment);
            }
        }
    }

    private void flowWriteCaseEducation(List<BasicMatchingEducation> basicMatchingEducationList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingEducationList)) {
            for (BasicMatchingEducation oo : basicMatchingEducationList) {
                CaseMatchingEducation caseMatchingEducation = new CaseMatchingEducation();
                BeanUtils.copyProperties(oo, caseMatchingEducation);
                caseMatchingEducation.setId(null);
                caseMatchingEducation.setGmtCreated(null);
                caseMatchingEducation.setGmtModified(null);
                caseMatchingEducation.setEstateId(caseEstate.getId());
                caseMatchingEducationService.addCaseMatchingEducation(caseMatchingEducation);
            }
        }
    }

    private void flowWriteCaseNetwork(List<BasicEstateNetwork> basicEstateNetworkList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateNetworkList)) {
            for (BasicEstateNetwork oo : basicEstateNetworkList) {
                CaseEstateNetwork caseEstateNetwork = new CaseEstateNetwork();
                BeanUtils.copyProperties(oo, caseEstateNetwork);
                caseEstateNetwork.setId(null);
                caseEstateNetwork.setGmtCreated(null);
                caseEstateNetwork.setGmtModified(null);
                caseEstateNetwork.setEstateId(caseEstate.getId());
                caseEstateNetworkService.saveCaseEstateNetwork(caseEstateNetwork);
            }
        }
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

                List<SysAttachmentDto> sysAttachmentDtoList = null;
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(estateParking.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
    }

    private void flowWriteCaseSupply(List<BasicEstateSupply> basicEstateSupplyList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateSupplyList)) {
            for (BasicEstateSupply oo : basicEstateSupplyList) {
                CaseEstateSupply caseEstateSupply = new CaseEstateSupply();
                BeanUtils.copyProperties(oo, caseEstateSupply);
                caseEstateSupply.setId(null);
                caseEstateSupply.setGmtCreated(null);
                caseEstateSupply.setGmtModified(null);
                caseEstateSupply.setEstateId(caseEstate.getId());
                caseEstateSupplyService.addCaseEstateSupply(caseEstateSupply);
            }
        }
    }

    private void flowWriteCaseTagging(List<BasicEstateTagging> basicEstateTaggingList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateTaggingList)) {
            for (BasicEstateTagging oo : basicEstateTaggingList) {
                CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
                BeanUtils.copyProperties(oo, caseEstateTagging);
                caseEstateTagging.setId(null);
                caseEstateTagging.setGmtCreated(null);
                caseEstateTagging.setGmtModified(null);
                caseEstateTagging.setDataId(caseEstate.getId());
                caseEstateTagging.setType(EstateTaggingTypeEnum.ESTATE.getKey());
                caseEstateTaggingService.saveCaseEstateTagging(caseEstateTagging);
            }
        }
    }

    /**
     * 主楼栋 回写
     *
     * @param basicBuildingMain
     * @param estateId
     * @return
     * @throws Exception
     */
    private CaseBuildingMain flowWriteCaseBuildingMain(BasicApply basicApply, BasicBuildingMain basicBuildingMain, Integer estateId) throws Exception {
        CaseBuildingMain caseBuildingMain = new CaseBuildingMain();
        if (basicBuildingMain != null) {
            BeanUtils.copyProperties(basicBuildingMain, caseBuildingMain);
            caseBuildingMain.setEstateId(estateId);
            caseBuildingMain.setType(basicApply.getType());
            if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getBuildingPartInMode())) {
                caseBuildingMain.setVersion(caseBuildingMainService.getVersion(basicApply.getCaseBuildingMainId()) + 1);
            } else {
                caseBuildingMain.setVersion(1);
            }
            caseBuildingMain.setId(null);
            caseBuildingMain.setGmtCreated(null);
            caseBuildingMain.setGmtModified(null);
            caseBuildingMainService.saveAndUpdate(caseBuildingMain);

            flowWriteCaseBuilding(basicBuildingMain, caseBuildingMain);
        }
        return caseBuildingMain;
    }

    /**
     * 楼栋 回写
     *
     * @param basicBuildingMain
     * @param caseBuildingMain
     * @throws Exception
     */
    private void flowWriteCaseBuilding(BasicBuildingMain basicBuildingMain, CaseBuildingMain caseBuildingMain) throws Exception {
        BasicBuilding basicBuildingQuery = new BasicBuilding();
        basicBuildingQuery.setBasicBuildingMainId(basicBuildingMain.getId());
        List<BasicBuilding> basicBuildingList = basicBuildingService.basicBuildingList(basicBuildingQuery);
        if (!ObjectUtils.isEmpty(basicBuildingList)) {
            for (BasicBuilding basicBuilding : basicBuildingList) {
                List<SysAttachmentDto> sysAttachmentDtoList = null;
                List<BasicBuildingOutfit> outfitList = null;
                List<BasicBuildingSurface> surfaceList = null;
                List<BasicBuildingMaintenance> maintenanceList = null;
                List<BasicBuildingFunction> functionList = null;
                //-----------------------------||---------------------------
                BasicBuildingOutfit queryOutfit = new BasicBuildingOutfit();
                BasicBuildingSurface querySurface = new BasicBuildingSurface();
                BasicBuildingMaintenance queryMaintenance = new BasicBuildingMaintenance();
                BasicBuildingFunction queryFunction = new BasicBuildingFunction();
                SysAttachmentDto queryFile = new SysAttachmentDto();

                queryFunction.setBuildingId(basicBuilding.getId());
                queryMaintenance.setBuildingId(basicBuilding.getId());
                querySurface.setBuildingId(basicBuilding.getId());
                queryOutfit.setBuildingId(basicBuilding.getId());
                queryFile.setTableId(basicBuilding.getId());
                queryFile.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));

                sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
                outfitList = basicBuildingOutfitService.basicBuildingOutfitList(queryOutfit);
                surfaceList = basicBuildingSurfaceService.basicBuildingSurfaceList(querySurface);
                maintenanceList = basicBuildingMaintenanceService.basicBuildingMaintenanceList(queryMaintenance);
                functionList = basicBuildingFunctionService.basicBuildingFunctionList(queryFunction);

                CaseBuilding caseBuilding = new CaseBuilding();
                BeanUtils.copyProperties(basicBuilding, caseBuilding);
                caseBuilding.setCaseBuildingMainId(caseBuildingMain.getId());
                caseBuilding.setId(null);
                caseBuilding.setGmtCreated(null);
                caseBuilding.setGmtModified(null);
                caseBuildingService.saveAndUpdateCaseBuilding(caseBuilding);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(caseBuilding.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }

                this.flowWriteCaseBuildingOutfit(outfitList, caseBuilding);
                this.flowWriteCaseBuildingMaintenance(maintenanceList, caseBuilding);
                this.flowWriteCaseBuildingSurface(surfaceList, caseBuilding);
                this.flowWriteCaseBuildingFunction(functionList, caseBuilding);
            }
        }
    }

    private void flowWriteCaseBuildingOutfit(List<BasicBuildingOutfit> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingOutfit oo : list) {
                CaseBuildingOutfit caseBuildingOutfit = new CaseBuildingOutfit();
                BeanUtils.copyProperties(oo, caseBuildingOutfit);
                caseBuildingOutfit.setId(null);
                caseBuildingOutfit.setGmtCreated(null);
                caseBuildingOutfit.setGmtModified(null);
                caseBuildingOutfit.setBuildingId(caseBuilding.getId());
                caseBuildingOutfitService.addCaseBuildingOutfit(caseBuildingOutfit);
            }
        }
    }

    private void flowWriteCaseBuildingMaintenance(List<BasicBuildingMaintenance> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingMaintenance oo : list) {
                CaseBuildingMaintenance caseBuildingMaintenance = new CaseBuildingMaintenance();
                BeanUtils.copyProperties(oo, caseBuildingMaintenance);
                caseBuildingMaintenance.setId(null);
                caseBuildingMaintenance.setGmtCreated(null);
                caseBuildingMaintenance.setGmtModified(null);
                caseBuildingMaintenance.setBuildingId(caseBuilding.getId());
                caseBuildingMaintenanceService.addCaseBuildingMaintenance(caseBuildingMaintenance);
            }
        }
    }

    private void flowWriteCaseBuildingSurface(List<BasicBuildingSurface> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingSurface oo : list) {
                CaseBuildingSurface caseBuildingSurface = new CaseBuildingSurface();
                BeanUtils.copyProperties(oo, caseBuildingSurface);
                caseBuildingSurface.setId(null);
                caseBuildingSurface.setGmtCreated(null);
                caseBuildingSurface.setGmtModified(null);
                caseBuildingSurface.setBuildingId(caseBuilding.getId());
                caseBuildingSurfaceService.addCaseBuildingSurface(caseBuildingSurface);
            }
        }
    }

    private void flowWriteCaseBuildingFunction(List<BasicBuildingFunction> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingFunction oo : list) {
                CaseBuildingFunction caseBuildingFunction = new CaseBuildingFunction();
                BeanUtils.copyProperties(oo, caseBuildingFunction);
                caseBuildingFunction.setId(null);
                caseBuildingFunction.setGmtCreated(null);
                caseBuildingFunction.setGmtModified(null);
                caseBuildingFunction.setBuildingId(caseBuilding.getId());
                caseBuildingFunctionService.saveAndUpdateCaseBuildingFunction(caseBuildingFunction);
            }
        }
    }

    /**
     * 单元 回写
     *
     * @param basicUnit
     * @param caseBuildingMainId
     * @return
     * @throws Exception
     */
    private CaseUnit flowWriteCaseUnit(BasicApply basicApply, BasicUnit basicUnit, Integer caseBuildingMainId) throws Exception {
        CaseUnit caseUnit = new CaseUnit();
        if (basicUnit != null) {
            BeanUtils.copyProperties(basicUnit, caseUnit);
            caseUnit.setBuildingMainId(caseBuildingMainId);
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
        }
        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
        BasicUnitElevator queryBasicUnitElevator = new BasicUnitElevator();
        BasicUnitDecorate queryBasicUnitDecorate = new BasicUnitDecorate();
        if (basicUnit != null) {
            if (basicUnit.getId() != null) {
                queryBasicUnitHuxing.setUnitId(basicUnit.getId());
                queryBasicUnitElevator.setUnitId(basicUnit.getId());
                queryBasicUnitDecorate.setUnitId(basicUnit.getId());
            }
        }
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(queryBasicUnitHuxing);
        List<BasicUnitElevator> basicUnitElevatorList = basicUnitElevatorService.basicUnitElevatorList(queryBasicUnitElevator);
        List<BasicUnitDecorate> basicUnitDecorateList = basicUnitDecorateService.basicUnitDecorateList(queryBasicUnitDecorate);
        if (caseUnit != null) {
            if (caseUnit.getId() != null) {
                this.flowWriteCaseDecorate(basicUnitDecorateList, caseUnit);
                this.flowWriteCaseHuxing(basicUnitHuxingList, caseUnit);
                this.flowWriteCaseElevator(basicUnitElevatorList, caseUnit);
            }
        }
        return caseUnit;
    }

    public void flowWriteCaseDecorate(List<BasicUnitDecorate> basicUnitDecorateList, CaseUnit caseUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitDecorateList)) {
            for (BasicUnitDecorate oo : basicUnitDecorateList) {
                CaseUnitDecorate caseUnitDecorate = new CaseUnitDecorate();
                BeanUtils.copyProperties(oo, caseUnitDecorate);
                caseUnitDecorate.setId(null);
                caseUnitDecorate.setGmtCreated(null);
                caseUnitDecorate.setGmtModified(null);
                caseUnitDecorate.setUnitId(caseUnit.getId());
                caseUnitDecorateService.addCaseUnitDecorate(caseUnitDecorate);
            }
        }
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

    public void flowWriteCaseElevator(List<BasicUnitElevator> basicUnitElevatorList, CaseUnit caseUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitElevatorList)) {
            for (BasicUnitElevator oo : basicUnitElevatorList) {
                CaseUnitElevator caseUnitElevator = new CaseUnitElevator();
                BeanUtils.copyProperties(oo, caseUnitElevator);
                caseUnitElevator.setId(null);
                caseUnitElevator.setGmtCreated(null);
                caseUnitElevator.setGmtModified(null);
                caseUnitElevator.setUnitId(caseUnit.getId());
                caseUnitElevatorService.saveCaseUnitElevator(caseUnitElevator);
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
    public void flowWriteCaseHouse(BasicApply basicApply, BasicHouse basicHouse, BasicHouseTrading basicTrading, Integer unitId) throws Exception {

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


        List<BasicHouseTradingSell> basicHouseTradingSellList = null;
        List<BasicHouseTradingLease> basicHouseTradingLeaseList = null;
        List<BasicHouseRoom> basicHouseRoomList = null;
        List<BasicHouseWater> basicHouseWaterList = null;
        List<BasicHouseIntelligent> basicHouseIntelligentList = null;
        List<BasicHouseFaceStreet> basicHouseFaceStreetList = null;
        List<BasicHouseEquipment> basicHouseEquipmentList = null;
        List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = null;
        List<SysAttachmentDto> sysAttachmentDtoList = null;
        List<BasicHouseWaterDrain> basicHouseWaterDrainList = null;

        BasicHouseTradingSell querySell = new BasicHouseTradingSell();
        BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseWater queryBasicHouseWater = new BasicHouseWater();
        BasicHouseIntelligent queryBasicHouseIntelligent = new BasicHouseIntelligent();
        BasicHouseFaceStreet queryBasicHouseFaceStreet = new BasicHouseFaceStreet();
        BasicHouseEquipment queryBasicHouseEquipment = new BasicHouseEquipment();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        BasicHouseWaterDrain queryWaterDrain = new BasicHouseWaterDrain();
        SysAttachmentDto queryFile = new SysAttachmentDto();

        if (basicHouse != null) {
            if (basicHouse.getId() != null) {
                queryLease.setHouseId(basicHouse.getId());
                querySell.setHouseId(basicHouse.getId());
                queryRoom.setHouseId(basicHouse.getId());
                queryBasicHouseWater.setHouseId(basicHouse.getId());
                queryBasicHouseIntelligent.setHouseId(basicHouse.getId());
                queryBasicHouseFaceStreet.setHouseId(basicHouse.getId());
                queryBasicHouseEquipment.setHouseId(basicHouse.getId());
                queryBasicHouseCorollaryEquipment.setHouseId(basicHouse.getId());
                queryFile.setTableId(basicHouse.getId());
                queryWaterDrain.setHouseId(basicHouse.getId());
                queryFile.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            }
        }

        sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
        basicHouseTradingSellList = basicHouseTradingSellService.basicHouseTradingSells(querySell);
        basicHouseTradingLeaseList = basicHouseTradingLeaseService.basicHouseTradingLeaseList(queryLease);
        basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
        basicHouseWaterList = basicHouseWaterService.basicHouseWaterList(queryBasicHouseWater);
        basicHouseIntelligentList = basicHouseIntelligentService.basicHouseIntelligentList(queryBasicHouseIntelligent);
        basicHouseFaceStreetList = basicHouseFaceStreetService.basicHouseFaceStreetList(queryBasicHouseFaceStreet);
        basicHouseEquipmentList = basicHouseEquipmentService.basicHouseEquipmentList(queryBasicHouseEquipment);
        basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
        basicHouseWaterDrainList = basicHouseWaterDrainService.basicHouseWaterDrainList(queryWaterDrain);

        if (caseHouse != null) {
            if (caseHouse.getId() != null) {
                this.flowWriteCaseSellAndLease(basicHouseTradingSellList, basicHouseTradingLeaseList, caseHouse);
                this.flowWriteCaseHouseRoom(basicHouseRoomList, caseHouse);
                this.flowWriteCaseWater(basicHouseWaterList, caseHouse);
                this.flowWriteCaseWaterDrain(basicHouseWaterDrainList, caseHouse);
                this.flowWriteCaseIntelligent(basicHouseIntelligentList, caseHouse);
                this.flowWriteCaseFaceStreet(basicHouseFaceStreetList, caseHouse);
                this.flowWriteCaseEquipment(basicHouseEquipmentList, caseHouse);
                this.flowWriteCaseCorollaryEquipment(basicHouseCorollaryEquipmentList, caseHouse);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(caseHouse.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
        flowWriteCaseBaseHouse(basicApply, caseHouse, caseHouseTrading);
    }

    private void flowWriteCaseBaseHouse(BasicApply basicApply, CaseHouse caseHouse, CaseHouseTrading caseHouseTrading) {
        if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getHousePartInMode())) {
            //清除上个版本对应的数据
            CaseHouse where = new CaseHouse();
            where.setUnitId(caseHouse.getUnitId());
            where.setHouseNumber(caseHouse.getHouseNumber());
            where.setVersion(caseHouse.getVersion() - 1);
            List<CaseHouse> houseList = caseHouseService.getCaseHouseList(where);
            if (!CollectionUtils.isEmpty(houseList)) {
                for (CaseHouse house : houseList) {
                    caseHouseService.deleteCaseHouse(house.getId());
                }
            }
        }
        //根据房屋信息逐步找到对应楼盘的信息
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseHouse.getUnitId());
        CaseBuildingMain buildingMain = caseBuildingMainService.getCaseBuildingMainById(caseUnit.getBuildingMainId());
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(buildingMain.getEstateId());
        CaseBaseHouse caseBaseHouse = new CaseBaseHouse();
        caseBaseHouse.setCaseHouseId(caseHouse.getId());
        caseBaseHouse.setType(caseEstate.getType());
        caseBaseHouse.setProvince(caseEstate.getProvince());
        caseBaseHouse.setCity(caseEstate.getCity());
        caseBaseHouse.setDistrict(caseEstate.getDistrict());
        caseBaseHouse.setBlockName(caseEstate.getBlockName());
        caseBaseHouse.setFullName(String.format("%s%s栋%s单元%s号", caseEstate.getName(), buildingMain.getBuildingNumber(), caseUnit.getUnitNumber(), caseHouse.getHouseNumber()));
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

    private void flowWriteCaseEquipment(List<BasicHouseEquipment> basicHouseEquipmentList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseEquipmentList)) {
            basicHouseEquipmentList.forEach(oo -> {
                try {
                    CaseHouseEquipment caseHouseEquipment = new CaseHouseEquipment();
                    BeanUtils.copyProperties(oo, caseHouseEquipment);
                    caseHouseEquipment.setId(null);
                    caseHouseEquipment.setGmtCreated(null);
                    caseHouseEquipment.setGmtModified(null);
                    caseHouseEquipment.setHouseId(caseHouse.getId());
                    caseHouseEquipmentService.addCaseHouseEquipment(caseHouseEquipment);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void flowWriteCaseFaceStreet(List<BasicHouseFaceStreet> basicHouseFaceStreetList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseFaceStreetList)) {
            basicHouseFaceStreetList.forEach(oo -> {
                try {
                    CaseHouseFaceStreet caseHouseFaceStreet = new CaseHouseFaceStreet();
                    BeanUtils.copyProperties(oo, caseHouseFaceStreet);
                    caseHouseFaceStreet.setId(null);
                    caseHouseFaceStreet.setGmtCreated(null);
                    caseHouseFaceStreet.setGmtModified(null);
                    caseHouseFaceStreet.setHouseId(caseHouse.getId());
                    caseHouseFaceStreetService.addCaseHouseFaceStreet(caseHouseFaceStreet);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void flowWriteCaseIntelligent(List<BasicHouseIntelligent> basicHouseIntelligentList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseIntelligentList)) {
            basicHouseIntelligentList.forEach(oo -> {
                try {
                    CaseHouseIntelligent caseHouseIntelligent = new CaseHouseIntelligent();
                    BeanUtils.copyProperties(oo, caseHouseIntelligent);
                    caseHouseIntelligent.setId(null);
                    caseHouseIntelligent.setGmtCreated(null);
                    caseHouseIntelligent.setGmtModified(null);
                    caseHouseIntelligent.setHouseId(caseHouse.getId());
                    caseHouseIntelligentService.addCaseHouseIntelligent(caseHouseIntelligent);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void flowWriteCaseWater(List<BasicHouseWater> basicHouseWaterList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseWaterList)) {
            basicHouseWaterList.forEach(oo -> {
                try {
                    CaseHouseWater caseHouseWater = new CaseHouseWater();
                    BeanUtils.copyProperties(oo, caseHouseWater);
                    caseHouseWater.setId(null);
                    caseHouseWater.setGmtCreated(null);
                    caseHouseWater.setGmtModified(null);
                    caseHouseWater.setHouseId(caseHouse.getId());
                    caseHouseWaterService.addCaseHouseWater(caseHouseWater);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void flowWriteCaseWaterDrain(List<BasicHouseWaterDrain> basicHouseWaterDrainList,CaseHouse caseHouse)throws Exception{
        if (!ObjectUtils.isEmpty(basicHouseWaterDrainList)){
            for (BasicHouseWaterDrain houseWaterDrain:basicHouseWaterDrainList){
                CaseHouseWaterDrain caseHouseWaterDrain = new CaseHouseWaterDrain();
                BeanUtils.copyProperties(houseWaterDrain,caseHouseWaterDrain);
                caseHouseWaterDrain.setId(null);
                caseHouseWaterDrain.setGmtCreated(null);
                caseHouseWaterDrain.setGmtModified(null);
                caseHouseWaterDrain.setHouseId(caseHouse.getId());
                caseHouseWaterDrainService.saveAndUpdateCaseHouseWaterDrain(caseHouseWaterDrain);
            }
        }
    }

    private void flowWriteCaseSellAndLease(List<BasicHouseTradingSell> basicHouseTradingSellList, List<BasicHouseTradingLease> basicHouseTradingLeaseList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseTradingSellList)) {
            for (BasicHouseTradingSell oo : basicHouseTradingSellList) {
                CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
                BeanUtils.copyProperties(oo, caseHouseTradingSell);
                caseHouseTradingSell.setId(null);
                caseHouseTradingSell.setGmtCreated(null);
                caseHouseTradingSell.setGmtModified(null);
                caseHouseTradingSell.setHouseId(caseHouse.getId());
                caseHouseTradingSellService.saveAndUpdateCaseHouseTradingSell(caseHouseTradingSell);
            }
        }

        if (!ObjectUtils.isEmpty(basicHouseTradingLeaseList)) {
            for (BasicHouseTradingLease oo : basicHouseTradingLeaseList) {
                CaseHouseTradingLease caseHouseTradingLease = null;
                caseHouseTradingLease = new CaseHouseTradingLease();
                BeanUtils.copyProperties(oo, caseHouseTradingLease);
                caseHouseTradingLease.setId(null);
                caseHouseTradingLease.setGmtCreated(null);
                caseHouseTradingLease.setGmtModified(null);
                caseHouseTradingLease.setHouseId(caseHouse.getId());
                caseHouseTradingLeaseService.saveAndUpdateCaseHouseTradingLease(caseHouseTradingLease);
            }
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
            BasicEstate basicEstate = this.getBasicEstateByAppId(basicApply.getId());
            BasicEstateLandState basicEstateLandState = this.getEstateLandStateByAppId(basicApply.getId());
            BasicBuildingMain basicBuildingMain = this.getBasicBuildingMainByAppId(basicApply.getId());
            BasicUnit basicUnit = this.getBasicUnitByAppId(basicApply.getId());
            BasicHouse basicHouse = this.getBasicHouseVoByAppId(basicApply.getId());
            BasicHouseTrading basicTrading = this.getBasicHouseTradingByAppId(basicApply.getId());

            //1.如果楼盘升级，则新增一条楼盘数据，并将与该楼盘相关关联的楼栋数据关联id更新为新添加的楼盘数据id
            //2.楼栋与单元处理方式与楼盘一致
            Integer estateId = basicApply.getCaseEstateId();
            Integer buildingMainId = basicApply.getCaseBuildingMainId();
            Integer unitId = basicApply.getCaseUnitId();


            //处理楼盘
            if (StringUtils.isNotBlank(basicApply.getEstatePartInMode()) && basicEstate != null) {
                CaseEstate caseEstate = this.flowWriteCaseEstate(basicApply, basicEstate, basicEstateLandState);
                if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getEstatePartInMode()) && estateId != null && estateId > 0) {
                    //更新楼栋关联id
                    caseBuildingMainService.updateEstateId(estateId, caseEstate.getId());
                }
                estateId = caseEstate.getId();
                //回写版块到基础数据中
                if (basicApply.getWriteBackBlockFlag() == Boolean.TRUE) {
                    DataBlock dataBlock=new DataBlock();
                    dataBlock.setProvince(basicEstate.getProvince());
                    dataBlock.setCity(basicEstate.getCity());
                    dataBlock.setDistrict(basicEstate.getDistrict());
                    dataBlock.setName(basicEstate.getName());
                    dataBlock.setCreator(basicEstate.getCreator());
                    dataBlockService.saveAndUpdateDataBlock(dataBlock);
                }
            }

            //处理楼栋
            if (StringUtils.isNotBlank(basicApply.getBuildingPartInMode()) && basicBuildingMain != null) {
                CaseBuildingMain caseBuildingMain = this.flowWriteCaseBuildingMain(basicApply, basicBuildingMain, estateId);
                if (BasicApplyPartInModeEnum.UPGRADE.getKey().equals(basicApply.getBuildingPartInMode()) && buildingMainId != null && buildingMainId > 0) {
                    //更新单元关联id
                    caseUnitService.updateBuildingMainId(buildingMainId, caseBuildingMain.getId());
                }
                buildingMainId = caseBuildingMain.getId();
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
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
    public BasicApply saveBasicApply(String formData, Boolean isDraft) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return null;
        }

        String jsonContent = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_APPLY.getVar());
        BasicApply basicApply = JSONObject.parseObject(jsonContent, BasicApply.class);
        //检查是否标注了楼盘
        if(!isDraft){
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
        if (StringUtils.isNotBlank(basicApply.getEstatePartInMode())) {
            //楼盘过程数据
            BasicEstate basicEstate = null;
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

        if (StringUtils.isNotBlank(basicApply.getBuildingPartInMode())) {
            //楼栋主数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING_MAIN.getVar());
            BasicBuildingMain basicBuildingMain = null;
            if (StringUtils.isNotBlank(jsonContent)) {
                basicBuildingMain = JSONObject.parseObject(jsonContent, BasicBuildingMain.class);
                if (basicBuildingMain != null) {
                    modeEnum = BasicApplyPartInModeEnum.getEnumByKey(basicApply.getBuildingPartInMode());
                    switch (modeEnum) {
                        case ADD:
                        case REFERENCE:
                            //案例库中验证
                            if (basicApply.getCaseEstateId() != null && caseBuildingMainService.hasBuildingMain(basicBuildingMain.getBuildingNumber(), basicApply.getCaseEstateId())) {
                                throw new BusinessException("案例中已存在相同编号的楼栋");
                            }
                            basicApply.setBuildingNumber(basicBuildingMain.getBuildingNumber());
                            break;
                    }
                    basicBuildingMain.setApplyId(basicApply.getId());
                    basicBuildingMainService.saveAndUpdateBasicBuildingMain(basicBuildingMain);
                }

                //楼栋部分数据
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
                BasicBuilding basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
                if (basicBuilding != null) {
                    basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding);
                }
            }
        }

        if (StringUtils.isNotBlank(basicApply.getUnitPartInMode())) {
            //单元过程数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
            BasicUnit basicUnit = null;
            if (StringUtils.isNotEmpty(jsonContent)) {
                basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
                if (basicUnit != null) {
                    modeEnum = BasicApplyPartInModeEnum.getEnumByKey(basicApply.getUnitPartInMode());
                    switch (modeEnum) {
                        case ADD:
                        case REFERENCE:
                            //案例库中验证
                            if (basicApply.getCaseBuildingMainId() != null && caseUnitService.hasUnit(basicUnit.getUnitNumber(), basicApply.getCaseBuildingMainId())) {
                                throw new BusinessException("案例中已存在相同编号的单元");
                            }
                            basicApply.setUnitNumber(basicUnit.getUnitNumber());
                            break;
                    }
                    basicUnit.setApplyId(basicApply.getId());
                    basicUnitService.saveAndUpdateBasicUnit(basicUnit);
                }
            }
        }

        if (StringUtils.isNotBlank(basicApply.getHousePartInMode())) {
            //处理房屋数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
            BasicHouse basicHouse = null;
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
                    jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar());
                    BasicHouseTrading basicTrading = JSONObject.parseObject(jsonContent, BasicHouseTrading.class);
                    if (basicTrading != null) {
                        basicTrading.setHouseId(house);
                        basicTrading.setApplyId(basicApply.getId());
                        basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading);
                    }
                }
            }
        }
        //更新地图标注信息
        BasicEstateTagging where=new BasicEstateTagging();
        where.setApplyId(0);
        where.setCreator(commonService.thisUserAccount());
        List<BasicEstateTagging> taggings = basicEstateTaggingService.basicEstateTaggingList(where);
        if(!CollectionUtils.isEmpty(taggings)){
            for (BasicEstateTagging tagging : taggings) {
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
     * @param basicApply
     * @throws BusinessException
     */
    private void taggingValid(BasicApply basicApply) throws BusinessException {
        if (StringUtils.isNotBlank(basicApply.getEstatePartInMode())) {
            if(!basicEstateTaggingService.hasBasicEstateTagging(basicApply.getId(), EstateTaggingTypeEnum.ESTATE)){
                throw new BusinessException("楼盘位置信息还未标注");
            }
        }
        if (StringUtils.isNotBlank(basicApply.getBuildingPartInMode())) {
            if(!basicEstateTaggingService.hasBasicEstateTagging(basicApply.getId(),EstateTaggingTypeEnum.BUILDING)){
                throw new BusinessException("楼栋位置信息还未标注");
            }
        }
        if (StringUtils.isNotBlank(basicApply.getUnitPartInMode())) {
            if(!basicEstateTaggingService.hasBasicEstateTagging(basicApply.getId(),EstateTaggingTypeEnum.UNIT)){
                throw new BusinessException("单元位置信息还未标注");
            }
        }
        if (StringUtils.isNotBlank(basicApply.getHousePartInMode())) {
            if(!basicEstateTaggingService.hasBasicEstateTagging(basicApply.getId(),EstateTaggingTypeEnum.HOUSE)){

            }
        }
    }

    public BasicEstateVo getBasicEstateByAppId(Integer appId) throws Exception {
        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setApplyId(appId);
        List<BasicEstate> basicEstates = basicEstateService.basicEstateList(basicEstate);
        if (!ObjectUtils.isEmpty(basicEstates)) {
            return basicEstateService.getBasicEstateVo(basicEstates.get(0));
        } else {
            return null;
        }
    }

    public BasicEstateLandStateVo getEstateLandStateByAppId(Integer appId) throws Exception {
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setApplyId(appId);
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(basicEstateLandState);
        if (!ObjectUtils.isEmpty(basicEstateLandStateList)) {
            return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateList.get(0));
        } else {
            return null;
        }
    }

    public BasicBuildingMain getBasicBuildingMainByAppId(Integer appId) throws Exception {
        BasicBuildingMain basicBuildingMain = new BasicBuildingMain();
        basicBuildingMain.setApplyId(appId);
        List<BasicBuildingMain> basicBuildingMains = basicBuildingMainService.basicBuildingMainList(basicBuildingMain);
        if (!ObjectUtils.isEmpty(basicBuildingMains)) {
            return basicBuildingMains.get(0);
        } else {
            return null;
        }
    }

    public BasicUnit getBasicUnitByAppId(Integer appId) throws Exception {
        BasicUnit basicUnit = new BasicUnit();
        basicUnit.setApplyId(appId);
        List<BasicUnit> unitList = basicUnitService.basicUnitList(basicUnit);
        if (!ObjectUtils.isEmpty(unitList)) {
            return unitList.get(0);
        } else {
            return null;
        }
    }

    public BasicHouseTradingVo getBasicHouseTradingByAppId(Integer appId) throws Exception {
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setApplyId(appId);
        List<BasicHouseTrading> basicHouseTradingList = basicHouseTradingService.basicHouseTradingList(basicHouseTrading);
        if (!ObjectUtils.isEmpty(basicHouseTradingList)) {
            return basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingList.get(0));
        } else {
            return null;
        }
    }

    public BasicHouseVo getBasicHouseVoByAppId(Integer appId) throws Exception {
        BasicHouse basicHouse = new BasicHouse();
        basicHouse.setApplyId(appId);
        List<BasicHouse> basicHouseList = basicHouseService.basicHouseList(basicHouse);
        if (!ObjectUtils.isEmpty(basicHouseList)) {
            BasicHouseVo vo = basicHouseService.getBasicHouseVo(basicHouseList.get(0));
            return vo;
        } else {
            return null;
        }
    }

    public List<BasicBuilding> getMainById(BasicBuildingMain buildingMain) throws Exception {
        BasicBuilding basicBuilding = new BasicBuilding();
        basicBuilding.setBasicBuildingMainId(buildingMain.getId());
        return basicBuildingService.basicBuildingList(basicBuilding);
    }


}

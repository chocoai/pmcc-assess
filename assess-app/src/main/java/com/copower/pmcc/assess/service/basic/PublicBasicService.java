package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingLeaseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingSellVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Ordering;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @Auther: zch
 * @Date: 2018/10/25 10:31
 * @Description:
 */
@Service
public class PublicBasicService {
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
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
    private ProcessControllerComponent processControllerComponent;
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
    private CommonService commonService;
    @Autowired
    private PublicService publicService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 楼盘回写
     *
     * @param basicEstate
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    @Transactional
    private CaseEstate flowWriteCaseEstate(BasicApply basicApply, BasicEstate basicEstate, BasicEstateLandState basicEstateLandState) throws Exception {
        CaseEstate caseEstate = new CaseEstate();
        if (basicEstate != null) {
            BeanUtils.copyProperties(basicEstate, caseEstate);
            caseEstate.setVersion(caseEstateService.getVersion(basicApply.getCaseEstateId())+1);
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
            caseBuildingMain.setVersion(caseBuildingMainService.getVersion(basicApply.getCaseBuildingMainId())+1);
            caseBuildingMain.setId(null);
            caseBuildingMain.setGmtCreated(null);
            caseBuildingMain.setGmtModified(null);
            caseBuildingMainService.saveAndUpdate(caseBuildingMain);

            flowWriteCaseBuilding(basicBuildingMain,caseBuildingMain);
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
            caseUnit.setCaseBuildingMainId(caseBuildingMainId);
            caseUnit.setVersion(caseUnitService.getVersion(basicApply.getCaseUnitId()) + 1);
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
        if (basicHouse != null) {
            BeanUtils.copyProperties(basicHouse, caseHouse);
            caseHouse.setUnitId(unitId);
            caseHouse.setVersion(caseHouseService.getVersion(basicApply.getCaseHouseId()) + 1);
            caseHouse.setId(null);
            caseHouse.setGmtCreated(null);
            caseHouse.setGmtModified(null);
            caseHouseService.saveAndUpdateCaseHouse(caseHouse);

            if (basicTrading != null) {
                CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
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

        BasicHouseTradingSell querySell = new BasicHouseTradingSell();
        BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseWater queryBasicHouseWater = new BasicHouseWater();
        BasicHouseIntelligent queryBasicHouseIntelligent = new BasicHouseIntelligent();
        BasicHouseFaceStreet queryBasicHouseFaceStreet = new BasicHouseFaceStreet();
        BasicHouseEquipment queryBasicHouseEquipment = new BasicHouseEquipment();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
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

        if (caseHouse != null) {
            if (caseHouse.getId() != null) {
                this.flowWriteCaseSellAndLease(basicHouseTradingSellList, basicHouseTradingLeaseList, caseHouse);
                this.flowWriteCaseHouseRoom(basicHouseRoomList, caseHouse);
                this.flowWriteCaseWater(basicHouseWaterList, caseHouse);
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
    @Transactional
    public void flowWrite(String processInsId) throws Exception {
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
        if (basicApply != null) {
            BasicEstate basicEstate = this.getByAppIdBasicEstate(basicApply.getId());
            BasicEstateLandState basicEstateLandState = this.getByAppIdEstateLandState(basicApply.getId());
            BasicBuildingMain basicBuildingMain = this.getByAppIdBasicBuildingMain(basicApply.getId());
            BasicUnit basicUnit = this.getByByAppIdBasicUnit(basicApply.getId());
            BasicHouse basicHouse = this.getByAppIdBasicHouseVo(basicApply.getId());
            BasicHouseTrading basicTrading = this.getByAppIdBasicHouseTrading(basicApply.getId());

            //1.如果楼盘升级，则新增一条楼盘数据，并将与该楼盘相关关联的楼栋数据关联id更新为新添加的楼盘数据id
            //2.楼栋与单元处理方式与楼盘一致
            Integer estateId = basicApply.getCaseEstateId();
            Integer buildingMainId = basicApply.getCaseBuildingMainId();
            Integer unitId = basicApply.getCaseUnitId();


            //处理楼盘
            if (basicApply.getEstatePartInFlag() == Boolean.TRUE && basicEstate != null) {
                CaseEstate caseEstate = this.flowWriteCaseEstate(basicApply, basicEstate, basicEstateLandState);
                if (estateId != null && estateId > 0) {
                    //更新楼栋关联id
                    caseBuildingMainService.updateEstateId(estateId, caseEstate.getId());
                }
                estateId = caseEstate.getId();
            }

            //处理楼栋
            if (basicApply.getBuildingPartInFlag() == Boolean.TRUE && basicBuildingMain != null) {
                CaseBuildingMain caseBuildingMain = this.flowWriteCaseBuildingMain(basicApply, basicBuildingMain, estateId);
                if (buildingMainId != null && buildingMainId > 0) {
                    //更新单元关联id
                    caseUnitService.updateBuildingMainId(buildingMainId, caseBuildingMain.getId());
                }
                buildingMainId = caseBuildingMain.getId();
            }

            //处理单元
            if (basicApply.getUnitPartInFlag() == Boolean.TRUE && basicUnit != null) {
                CaseUnit caseUnit = this.flowWriteCaseUnit(basicApply, basicUnit, buildingMainId);
                if (unitId != null && unitId > 0) {
                    //更新单元关联id
                    caseHouseService.updateUnitId(unitId, caseUnit.getId());
                }
                unitId = caseUnit.getId();
            }

            //处理房屋
            if (basicApply.getHousePartInFlag() == Boolean.TRUE && basicHouse != null) {
                this.flowWriteCaseHouse(basicApply, basicHouse, basicTrading, unitId);
            }
        }
    }

    /**
     * 审批
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void approvalAndWrite(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 案例 返回修改 提交
     *
     * @param approvalModelDto
     * @param formData
     * @throws Exception
     */
    public void basicEditSubmit(ApprovalModelDto approvalModelDto, String formData) throws Exception {
        try {
            if (StringUtils.isNotEmpty(formData)) {
                JSONObject jsonObject = JSON.parseObject(formData);
                BasicEstate basicEstate = null;
                BasicEstateLandState basicEstateLandState = null;
                BasicBuildingMain basicBuildingMain = null;
                List<BasicBuilding> basicBuildingList = null;
                BasicUnit basicUnit = null;
                BasicHouse basicHouse = null;
                BasicHouseTrading basicTrading = null;
                Integer estateId = null;
                Integer basicBuildingMainId = null;
                Integer unitId = null;
                Integer houseId = null;
                if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar()))) {
                    basicEstate = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar()), BasicEstate.class);
                }
                if (basicEstate != null) {
                    estateId = basicEstateService.saveAndUpdateBasicEstate(basicEstate);
                }
                if (StringUtils.isNotEmpty(jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar()))) {
                    basicEstateLandState = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar()), BasicEstateLandState.class);
                }
                if (basicEstateLandState != null) {
                    if (basicEstateLandState.getId() == null) {
                        if (estateId != null) {
                            basicEstateLandState.setEstateId(estateId);
                        }
                    }
                    basicEstateLandStateService.upgradeVersion(basicEstateLandState);
                }
                //注意:
                if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING_MAIN.getVar()))) {
                    basicBuildingMain = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING_MAIN.getVar()), BasicBuildingMain.class);
                    if (basicBuildingMain.getId() == null) {
                        if (estateId != null) {
                            basicBuildingMain.setEstateId(estateId);
                        }
                    }
                    basicBuildingMainService.upgradeVersion(basicBuildingMain);
                    basicBuildingMainId = basicBuildingMain.getId();
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar()))) {
                    BasicBuilding basicBuilding = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar()), BasicBuilding.class);
                    basicBuildingService.upgradeVersion(basicBuilding);
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar()))) {
                    basicUnit = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar()), BasicUnit.class);
                    basicUnitService.saveAndUpdateBasicUnit(basicUnit);
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar()))) {
                    basicHouse = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar()), BasicHouse.class);
                    basicHouseService.upgradeVersion(basicHouse);
                }

                if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar()))) {
                    basicTrading = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar()), BasicHouseTrading.class);
                    basicHouseTradingService.upgradeVersion(basicTrading);
                }
            } else {
                throw new Exception("null data");
            }
            //提交流程
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }


    /**
     * 保存数据
     *
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicApply saveBasic(String formData, Boolean isDraft) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return null;
        }
        String jsonContent = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_APPLY.getVar());
        BasicApply basicApply = JSONObject.parseObject(jsonContent, BasicApply.class);
        basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
        basicApply.setDraftFlag(isDraft);
        basicApply.setCreator(commonService.thisUserAccount());
        basicApplyService.saveBasicApply(basicApply);

        if (basicApply.getEstatePartInFlag() == Boolean.TRUE) {
            //楼盘过程数据
            BasicEstate basicEstate = null;
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar());
            if (StringUtils.isNotBlank(jsonContent)) {
                basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
                if (basicEstate != null) {
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

        if (basicApply.getBuildingPartInFlag() == Boolean.TRUE) {
            //楼栋主过程数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING_MAIN.getVar());
            BasicBuildingMain basicBuildingMain = null;
            if (StringUtils.isNotBlank(jsonContent)) {
                basicBuildingMain = JSONObject.parseObject(jsonContent, BasicBuildingMain.class);
                if (basicBuildingMain != null) {
                    basicBuildingMain.setApplyId(basicApply.getId());
                    basicBuildingMainService.saveAndUpdateBasicBuildingMain(basicBuildingMain);
                }

                //楼栋过程数据
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
                BasicBuilding basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
                if (basicBuilding != null) {
                    basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding);
                }
            }
        }

        if (basicApply.getUnitPartInFlag() == Boolean.TRUE) {
            //单元过程数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
            BasicUnit basicUnit = null;
            if (StringUtils.isNotEmpty(jsonContent)) {
                basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
                if (basicUnit != null) {
                    basicUnit.setApplyId(basicApply.getId());
                    basicUnitService.saveAndUpdateBasicUnit(basicUnit);
                }
            }
        }

        if (basicApply.getHousePartInFlag() == Boolean.TRUE) {
            //处理房屋数据
            jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
            BasicHouse basicHouse = null;
            if (StringUtils.isNotEmpty(jsonContent)) {
                basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
                if (basicHouse != null) {
                    basicHouse.setApplyId(basicApply.getId());
                    Integer house = basicHouseService.upgradeVersion(basicHouse);
                    BasicHouseTrading basicTrading = JSONObject.parseObject(jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar()), BasicHouseTrading.class);
                    if (basicTrading != null) {
                        basicTrading.setHouseId(house);
                        basicTrading.setApplyId(basicApply.getId());
                        basicHouseTradingService.upgradeVersion(basicTrading);
                    }
                }
            }
        }

        return basicApply;
    }

    /**
     * 处理地图标注
     *
     * @param basicEstate
     * @throws Exception
     */
    private void saveBasicBasicEstateTagging(BasicEstate basicEstate) throws Exception {
        BasicEstateTagging query = new BasicEstateTagging();
        query.setEstateId(0);
        query.setCreator(commonService.thisUserAccount());
        List<BasicEstateTagging> taggingList = basicEstateTaggingService.basicEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(taggingList)) {
            Ordering<BasicEstateTagging> ordering = Ordering.from(new Comparator<BasicEstateTagging>() {
                @Override
                public int compare(BasicEstateTagging o1, BasicEstateTagging o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            }).reverse();
            Collections.sort(taggingList, ordering);
            BasicEstateTagging basicEstateTagging = taggingList.get(0);
            basicEstateTagging.setEstateId(basicEstate.getId());
            int id = basicEstateTagging.getId();
            basicEstateTagging.setId(null);
            basicEstateTaggingService.saveBasicEstateTagging(basicEstateTagging);
            basicEstateTaggingService.deleteBasicEstateTagging(id);
        }
    }

    public BasicEstateVo getByAppIdBasicEstate(Integer appId) throws Exception {
        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setApplyId(appId);
        List<BasicEstate> basicEstates = basicEstateService.basicEstateList(basicEstate);
        if (!ObjectUtils.isEmpty(basicEstates)) {
            return basicEstateService.getBasicEstateVo(basicEstates.get(0));
        } else {
            return null;
        }
    }

    public BasicEstateLandStateVo getByAppIdEstateLandState(Integer appId) throws Exception {
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setApplyId(appId);
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(basicEstateLandState);
        if (!ObjectUtils.isEmpty(basicEstateLandStateList)) {
            return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateList.get(0));
        } else {
            return null;
        }
    }

    public BasicBuildingMain getByAppIdBasicBuildingMain(Integer appId) throws Exception {
        BasicBuildingMain basicBuildingMain = new BasicBuildingMain();
        basicBuildingMain.setApplyId(appId);
        List<BasicBuildingMain> basicBuildingMains = basicBuildingMainService.basicBuildingMainList(basicBuildingMain);
        if (!ObjectUtils.isEmpty(basicBuildingMains)) {
            return basicBuildingMains.get(0);
        } else {
            return null;
        }
    }

    public BasicUnit getByByAppIdBasicUnit(Integer appId) throws Exception {
        BasicUnit basicUnit = new BasicUnit();
        basicUnit.setApplyId(appId);
        List<BasicUnit> unitList = basicUnitService.basicUnitList(basicUnit);
        if (!ObjectUtils.isEmpty(unitList)) {
            return unitList.get(0);
        } else {
            return null;
        }
    }

    public BasicHouseTradingVo getByAppIdBasicHouseTrading(Integer appId) throws Exception {
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setApplyId(appId);
        List<BasicHouseTrading> basicHouseTradingList = basicHouseTradingService.basicHouseTradingList(basicHouseTrading);
        if (!ObjectUtils.isEmpty(basicHouseTradingList)) {
            return basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingList.get(0));
        } else {
            return null;
        }
    }

    public BasicHouseVo getByAppIdBasicHouseVo(Integer appId) throws Exception {
        BasicHouse basicHouse = new BasicHouseVo();
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

    public BasicBuildingVo getBasicBuildingVo(BasicBuilding basicBuilding) {
        return basicBuildingService.getBasicBuildingVo(basicBuilding);
    }



    /**
     * 将 CaseHouse 下的子类 转移到 BasicHouse下的子类中去 (用做过程数据)
     *
     * @param caseHouseId
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public Map<String, Object> appWriteHouse(Integer caseHouseId) throws Exception {
        if (caseHouseId == null) {
            throw new Exception("null ponit");
        }
        SysAttachmentDto queryFile = new SysAttachmentDto();
        CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
        caseHouseTradingLease.setHouseId(caseHouseId);
        CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
        caseHouseTradingSell.setHouseId(caseHouseId);
        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
        caseHouseRoom.setHouseId(caseHouseId);
        queryFile.setTableId(caseHouseId);
        queryFile.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
        CaseHouseEquipment caseHouseEquipment = new CaseHouseEquipment();
        caseHouseEquipment.setHouseId(caseHouseId);
        CaseHouseFaceStreet caseHouseFaceStreet = new CaseHouseFaceStreet();
        caseHouseFaceStreet.setHouseId(caseHouseId);
        CaseHouseIntelligent caseHouseIntelligent = new CaseHouseIntelligent();
        caseHouseIntelligent.setHouseId(caseHouseId);
        CaseHouseWater caseHouseWater = new CaseHouseWater();
        caseHouseWater.setHouseId(caseHouseId);
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(caseHouseId);

        List<CaseHouseTradingSellVo> caseHouseTradingSellVos = caseHouseTradingSellService.caseHouseTradingSellList(caseHouseTradingSell, null);
        List<CaseHouseTradingLeaseVo> caseHouseTradingLeaseVos = caseHouseTradingLeaseService.caseHouseTradingLeaseList(caseHouseTradingLease, null);
        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
        List<CaseHouseEquipment> caseHouseEquipments = caseHouseEquipmentService.getCaseHouseEquipmentList(caseHouseEquipment);
        List<CaseHouseFaceStreet> caseHouseFaceStreets = caseHouseFaceStreetService.getCaseHouseFaceStreetList(caseHouseFaceStreet);
        List<CaseHouseIntelligent> caseHouseIntelligents = caseHouseIntelligentService.getCaseHouseIntelligentList(caseHouseIntelligent);
        List<CaseHouseWater> caseHouseWaters = caseHouseWaterService.getCaseHouseWaterList(caseHouseWater);
        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
        if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)) {
            for (CaseHouseTradingSellVo oo : caseHouseTradingSellVos) {
                BasicHouseTradingSell querySell = new BasicHouseTradingSell();
                BeanUtils.copyProperties(oo, querySell);
                querySell.setId(null);
                querySell.setHouseId(0);
                querySell.setCreator(commonService.thisUserAccount());
                basicHouseTradingSellService.saveAndUpdateBasicHouseTradingSell(querySell);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseTradingLeaseVos)) {
            for (CaseHouseTradingLeaseVo oo : caseHouseTradingLeaseVos) {
                BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
                BeanUtils.copyProperties(oo, queryLease);
                queryLease.setId(null);
                queryLease.setHouseId(0);
                queryLease.setCreator(commonService.thisUserAccount());
                basicHouseTradingLeaseService.saveAndUpdateBasicHouseTradingLease(queryLease);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseRooms)) {
            for (CaseHouseRoom oo : caseHouseRooms) {
                BasicHouseRoom queryRoom = new BasicHouseRoom();
                BeanUtils.copyProperties(oo, queryRoom);
                queryRoom.setId(null);
                queryRoom.setHouseId(0);
                queryRoom.setCreator(commonService.thisUserAccount());
                basicHouseRoomService.saveAndUpdateBasicHouseRoom(queryRoom);
                CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                caseHouseRoomDecorate.setRoomId(oo.getId());
                List<CaseHouseRoomDecorate> caseHouseRoomDecorateList = caseHouseRoomDecorateService.getCaseHouseRoomDecorateList(caseHouseRoomDecorate);
                if (!ObjectUtils.isEmpty(caseHouseRoomDecorateList)) {
                    for (CaseHouseRoomDecorate po : caseHouseRoomDecorateList) {
                        BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                        BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                        basicHouseRoomDecorate.setRoomId(queryRoom.getId());
                        basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
                        basicHouseRoomDecorate.setGmtCreated(null);
                        basicHouseRoomDecorate.setGmtModified(null);
                        basicHouseRoomDecorate.setId(null);
                        basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
                    }
                }
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseEquipments)) {
            for (CaseHouseEquipment oo : caseHouseEquipments) {
                BasicHouseEquipment po = new BasicHouseEquipment();
                BeanUtils.copyProperties(oo, po);
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseEquipmentService.saveAndUpdateBasicHouseEquipment(po);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseFaceStreets)) {
            for (CaseHouseFaceStreet oo : caseHouseFaceStreets) {
                BasicHouseFaceStreet po = new BasicHouseFaceStreet();
                BeanUtils.copyProperties(oo, po);
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseFaceStreetService.saveAndUpdateBasicHouseFaceStreet(po);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseIntelligents)) {
            for (CaseHouseIntelligent oo : caseHouseIntelligents) {
                BasicHouseIntelligent po = new BasicHouseIntelligent();
                BeanUtils.copyProperties(oo, po);
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseIntelligentService.saveAndUpdateBasicHouseIntelligent(po);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseWaters)) {
            for (CaseHouseWater oo : caseHouseWaters) {
                BasicHouseWater po = new BasicHouseWater();
                BeanUtils.copyProperties(oo, po);
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseWaterService.saveAndUpdateBasicHouseWater(po);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
            for (CaseHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouseCorollaryEquipment.class));
                List<SysAttachmentDto> sysAttachmentDtoList1 = baseAttachmentService.getAttachmentList(query);
                BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                BeanUtils.copyProperties(oo, po);
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                Integer id = basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList1)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList1) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(id);
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(0);
                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
            }
        }
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        objectMap.put(CaseHouse.class.getSimpleName(), caseHouseService.getCaseHouseVo(caseHouseService.getCaseHouseById(caseHouseId)));
        CaseHouseTrading query = new CaseHouseTrading();
        query.setHouseId(caseHouseId);
        List<CaseHouseTrading> caseHouseTradingList = caseHouseTradingService.caseHouseTradingLists(query);
        objectMap.put(CaseHouseTrading.class.getSimpleName(), caseHouseTradingService.getCaseHouseTradingVo(caseHouseTradingList.get(0)));
        return objectMap;
    }
}

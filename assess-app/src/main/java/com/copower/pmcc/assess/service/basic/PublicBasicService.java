package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.cases.*;
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
import org.springframework.stereotype.Service;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 楼盘回写
     *
     * @param basicEstate
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    private CaseEstate flowWriteCaseEstate(BasicEstate basicEstate, BasicEstateLandState basicEstateLandState) throws Exception {
        CaseEstate caseEstate = null;
        CaseEstateLandState caseEstateLandState = null;
        List<SysAttachmentDto> sysAttachmentDtoList = null;
        SysAttachmentDto query = new SysAttachmentDto();
        query.setTableId(basicEstate.getId());
        query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (basicEstate.getCaseEstateId() != null) {
            caseEstate = caseEstateService.getCaseEstateById(basicEstate.getCaseEstateId());
            //虽然有 案例id但是无法找到具体数据的情况(一般不会发生)
            if (caseEstate == null) {
                caseEstate = new CaseEstate();
                BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate, caseEstate);
                caseEstate.setId(null);
                caseEstate.setGmtCreated(null);
                caseEstate.setGmtModified(null);
            }
            //有案例 id 并且找到
            if (caseEstate != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate, caseEstate);
                caseEstate.setId(basicEstate.getCaseEstateId());
                caseEstate.setGmtCreated(null);
                caseEstate.setGmtModified(null);
            }
        }
        //第一次情况
        if (basicEstate.getCaseEstateId() == null) {
            caseEstate = new CaseEstate();
            BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate, caseEstate);
            caseEstate.setId(null);
            caseEstate.setGmtCreated(null);
            caseEstate.setGmtModified(null);
        }
        if (caseEstate != null) {
            //升级版本 以及更改某些数据  或者 新添数据
            caseEstateService.upgradeVersion(caseEstate);
            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                    sysAttachmentDto.setTableId(caseEstate.getId());
                    sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
                }
            }
            if (caseEstate.getId() != null) {
                if (basicEstateLandState != null) {
                    if (basicEstateLandState.getCaseEstateLandStateId() != null) {
                        caseEstateLandState = caseEstateLandStateService.getCaseEstateLandStateById(basicEstateLandState.getCaseEstateLandStateId());
                        if (caseEstateLandState != null) {
                            BeanCopyHelp.copyPropertiesIgnoreNull(basicEstateLandState, caseEstateLandState);
                            caseEstateLandState.setId(basicEstateLandState.getCaseEstateLandStateId());
                            caseEstateLandState.setGmtCreated(null);
                            caseEstateLandState.setGmtModified(null);
                        }
                        if (caseEstateLandState == null) {
                            caseEstateLandState = new CaseEstateLandState();
                            BeanCopyHelp.copyPropertiesIgnoreNull(basicEstateLandState, caseEstateLandState);
                            caseEstateLandState.setId(null);
                            caseEstateLandState.setGmtCreated(null);
                            caseEstateLandState.setGmtModified(null);
                        }
                    }
                    if (basicEstateLandState.getCaseEstateLandStateId() == null) {
                        caseEstateLandState = new CaseEstateLandState();
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicEstateLandState, caseEstateLandState);
                        caseEstateLandState.setId(null);
                        caseEstateLandState.setGmtCreated(null);
                        caseEstateLandState.setGmtModified(null);
                    }
                    if (caseEstateLandState != null) {
                        caseEstateLandState.setEstateId(caseEstate.getId());
                        caseEstateLandStateService.upgradeVersion(caseEstateLandState);
                    }
                }
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

        if (basicEstate.getId() != null){
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
                CaseMatchingTraffic caseMatchingTraffic = null;
                if (oo.getCaseId() != null) {
                    caseMatchingTraffic = caseMatchingTrafficService.getMatchingTrafficById(oo.getCaseId());
                    if (caseMatchingTraffic != null) {
                        BeanUtils.copyProperties(oo, caseMatchingTraffic);
                        caseMatchingTraffic.setId(oo.getCaseId());
                        caseMatchingTraffic.setGmtCreated(null);
                        caseMatchingTraffic.setGmtModified(null);
                    }
                    if (caseMatchingTraffic == null) {
                        caseMatchingTraffic = new CaseMatchingTraffic();
                        BeanUtils.copyProperties(oo, caseMatchingTraffic);
                        caseMatchingTraffic.setId(null);
                        caseMatchingTraffic.setGmtCreated(null);
                        caseMatchingTraffic.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingTraffic = new CaseMatchingTraffic();
                    BeanUtils.copyProperties(oo, caseMatchingTraffic);
                    caseMatchingTraffic.setId(null);
                    caseMatchingTraffic.setGmtCreated(null);
                    caseMatchingTraffic.setGmtModified(null);
                }
                caseMatchingTraffic.setEstateId(caseEstate.getId());
                caseMatchingTrafficService.upgradeVersion(caseMatchingTraffic);
            }
        }
    }

    private void flowWriteCaseMedical(List<BasicMatchingMedical> basicMatchingMedicalList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingMedicalList)) {
            for (BasicMatchingMedical oo : basicMatchingMedicalList) {
                CaseMatchingMedical caseMatchingMedical = null;
                if (oo.getCaseId() != null) {
                    caseMatchingMedical = caseMatchingMedicalService.getCaseMatchingMedicalById(oo.getCaseId());
                    if (caseMatchingMedical != null) {
                        BeanUtils.copyProperties(oo, caseMatchingMedical);
                        caseMatchingMedical.setId(oo.getCaseId());
                        caseMatchingMedical.setGmtCreated(null);
                        caseMatchingMedical.setGmtModified(null);
                    }
                    if (caseMatchingMedical == null) {
                        caseMatchingMedical = new CaseMatchingMedical();
                        BeanUtils.copyProperties(oo, caseMatchingMedical);
                        caseMatchingMedical.setId(null);
                        caseMatchingMedical.setGmtCreated(null);
                        caseMatchingMedical.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingMedical = new CaseMatchingMedical();
                    BeanUtils.copyProperties(oo, caseMatchingMedical);
                    caseMatchingMedical.setId(null);
                    caseMatchingMedical.setGmtCreated(null);
                    caseMatchingMedical.setGmtModified(null);
                }
                caseMatchingMedical.setEstateId(caseEstate.getId());
                caseMatchingMedicalService.upgradeVersion(caseMatchingMedical);
            }
        }
    }

    private void flowWriteCaseMaterial(List<BasicMatchingMaterial> basicMatchingMaterialList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingMaterialList)) {
            for (BasicMatchingMaterial oo : basicMatchingMaterialList) {
                CaseMatchingMaterial caseMatchingMaterial = null;
                if (oo.getCaseId() != null) {
                    caseMatchingMaterial = caseMatchingMaterialService.getCaseMatchingMaterialById(oo.getCaseId());
                    if (caseMatchingMaterial != null) {
                        BeanUtils.copyProperties(oo, caseMatchingMaterial);
                        caseMatchingMaterial.setId(oo.getCaseId());
                        caseMatchingMaterial.setGmtCreated(null);
                        caseMatchingMaterial.setGmtModified(null);
                    }
                    if (caseMatchingMaterial == null) {
                        caseMatchingMaterial = new CaseMatchingMaterial();
                        BeanUtils.copyProperties(oo, caseMatchingMaterial);
                        caseMatchingMaterial.setId(null);
                        caseMatchingMaterial.setGmtCreated(null);
                        caseMatchingMaterial.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingMaterial = new CaseMatchingMaterial();
                    BeanUtils.copyProperties(oo, caseMatchingMaterial);
                    caseMatchingMaterial.setId(null);
                    caseMatchingMaterial.setGmtCreated(null);
                    caseMatchingMaterial.setGmtModified(null);
                }
                caseMatchingMaterial.setEstateId(caseEstate.getId());
                caseMatchingMaterialService.upgradeVersion(caseMatchingMaterial);
            }
        }
    }

    private void flowWriteCaseLeisurePlace(List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingLeisurePlaceList)) {
            for (BasicMatchingLeisurePlace oo : basicMatchingLeisurePlaceList) {
                CaseMatchingLeisurePlace caseMatchingLeisurePlace = null;
                if (oo.getCaseId() != null) {
                    caseMatchingLeisurePlace = caseMatchingLeisurePlaceService.getCaseMatchingLeisurePlaceById(oo.getCaseId());
                    if (caseMatchingLeisurePlace != null) {
                        BeanUtils.copyProperties(oo, caseMatchingLeisurePlace);
                        caseMatchingLeisurePlace.setId(oo.getCaseId());
                        caseMatchingLeisurePlace.setGmtCreated(null);
                        caseMatchingLeisurePlace.setGmtModified(null);
                    }
                    if (caseMatchingLeisurePlace == null) {
                        caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
                        BeanUtils.copyProperties(oo, caseMatchingLeisurePlace);
                        caseMatchingLeisurePlace.setId(null);
                        caseMatchingLeisurePlace.setGmtCreated(null);
                        caseMatchingLeisurePlace.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
                    BeanUtils.copyProperties(oo, caseMatchingLeisurePlace);
                    caseMatchingLeisurePlace.setId(null);
                    caseMatchingLeisurePlace.setGmtCreated(null);
                    caseMatchingLeisurePlace.setGmtModified(null);
                }
                caseMatchingLeisurePlace.setEstateId(caseEstate.getId());
                caseMatchingLeisurePlaceService.upgradeVersion(caseMatchingLeisurePlace);
            }
        }
    }

    private void flowWriteCaseFinance(List<BasicMatchingFinance> basicMatchingFinanceList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingFinanceList)) {
            for (BasicMatchingFinance oo : basicMatchingFinanceList) {
                CaseMatchingFinance caseMatchingFinance = null;
                if (oo.getCaseId() != null) {
                    caseMatchingFinance = caseMatchingFinanceService.getCaseMatchingFinanceById(oo.getCaseId());
                    if (caseMatchingFinance != null) {
                        BeanUtils.copyProperties(oo, caseMatchingFinance);
                        caseMatchingFinance.setId(oo.getCaseId());
                        caseMatchingFinance.setGmtCreated(null);
                        caseMatchingFinance.setGmtModified(null);
                    }
                    if (caseMatchingFinance == null) {
                        caseMatchingFinance = new CaseMatchingFinance();
                        BeanUtils.copyProperties(oo, caseMatchingFinance);
                        caseMatchingFinance.setId(null);
                        caseMatchingFinance.setGmtCreated(null);
                        caseMatchingFinance.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingFinance = new CaseMatchingFinance();
                    BeanUtils.copyProperties(oo, caseMatchingFinance);
                    caseMatchingFinance.setId(null);
                    caseMatchingFinance.setGmtCreated(null);
                    caseMatchingFinance.setGmtModified(null);
                }
                caseMatchingFinance.setEstateId(caseEstate.getId());
                caseMatchingFinanceService.upgradeVersion(caseMatchingFinance);
            }
        }
    }

    private void flowWriteCaseEnvironment(List<BasicMatchingEnvironment> basicMatchingEnvironmentList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingEnvironmentList)) {
            for (BasicMatchingEnvironment oo : basicMatchingEnvironmentList) {
                CaseMatchingEnvironment caseMatchingEnvironment = null;
                if (oo.getCaseId() != null) {
                    caseMatchingEnvironment = caseMatchingEnvironmentService.getCaseMatchingEnvironmentById(oo.getCaseId());
                    if (caseMatchingEnvironment != null) {
                        BeanUtils.copyProperties(oo, caseMatchingEnvironment);
                        caseMatchingEnvironment.setId(oo.getCaseId());
                        caseMatchingEnvironment.setGmtCreated(null);
                        caseMatchingEnvironment.setGmtModified(null);
                    }
                    if (caseMatchingEnvironment == null) {
                        caseMatchingEnvironment = new CaseMatchingEnvironment();
                        BeanUtils.copyProperties(oo, caseMatchingEnvironment);
                        caseMatchingEnvironment.setId(null);
                        caseMatchingEnvironment.setGmtCreated(null);
                        caseMatchingEnvironment.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingEnvironment = new CaseMatchingEnvironment();
                    BeanUtils.copyProperties(oo, caseMatchingEnvironment);
                    caseMatchingEnvironment.setId(null);
                    caseMatchingEnvironment.setGmtCreated(null);
                    caseMatchingEnvironment.setGmtModified(null);
                }
                caseMatchingEnvironment.setEstateId(caseEstate.getId());
                caseMatchingEnvironmentService.upgradeVersion(caseMatchingEnvironment);
            }
        }
    }

    private void flowWriteCaseEducation(List<BasicMatchingEducation> basicMatchingEducationList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingEducationList)) {
            for (BasicMatchingEducation oo : basicMatchingEducationList) {
                CaseMatchingEducation caseMatchingEducation = null;
                if (oo.getCaseId() != null) {
                    caseMatchingEducation = caseMatchingEducationService.getCaseMatchingEducationById(oo.getCaseId());
                    if (caseMatchingEducation != null) {
                        BeanUtils.copyProperties(oo, caseMatchingEducation);
                        caseMatchingEducation.setId(oo.getCaseId());
                        caseMatchingEducation.setGmtCreated(null);
                        caseMatchingEducation.setGmtModified(null);
                    }
                    if (caseMatchingEducation == null) {
                        caseMatchingEducation = new CaseMatchingEducation();
                        BeanUtils.copyProperties(oo, caseMatchingEducation);
                        caseMatchingEducation.setId(null);
                        caseMatchingEducation.setGmtCreated(null);
                        caseMatchingEducation.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseMatchingEducation = new CaseMatchingEducation();
                    BeanUtils.copyProperties(oo, caseMatchingEducation);
                    caseMatchingEducation.setId(null);
                    caseMatchingEducation.setGmtCreated(null);
                    caseMatchingEducation.setGmtModified(null);
                }
                caseMatchingEducation.setEstateId(caseEstate.getId());
                caseMatchingEducationService.upgradeVersion(caseMatchingEducation);
            }
        }
    }

    private void flowWriteCaseNetwork(List<BasicEstateNetwork> basicEstateNetworkList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateNetworkList)) {
            for (BasicEstateNetwork oo : basicEstateNetworkList) {
                CaseEstateNetwork caseEstateNetwork = null;
                if (oo.getCaseId() != null) {
                    caseEstateNetwork = caseEstateNetworkService.getEstateNetworkById(oo.getCaseId());
                    if (caseEstateNetwork != null) {
                        BeanUtils.copyProperties(oo, caseEstateNetwork);
                        caseEstateNetwork.setId(oo.getCaseId());
                        caseEstateNetwork.setGmtCreated(null);
                        caseEstateNetwork.setGmtModified(null);
                    }
                    if (caseEstateNetwork == null) {
                        caseEstateNetwork = new CaseEstateNetwork();
                        BeanUtils.copyProperties(oo, caseEstateNetwork);
                        caseEstateNetwork.setId(null);
                        caseEstateNetwork.setGmtCreated(null);
                        caseEstateNetwork.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseEstateNetwork = new CaseEstateNetwork();
                    BeanUtils.copyProperties(oo, caseEstateNetwork);
                    caseEstateNetwork.setId(null);
                    caseEstateNetwork.setGmtCreated(null);
                    caseEstateNetwork.setGmtModified(null);
                }
                caseEstateNetwork.setEstateId(caseEstate.getId());
                caseEstateNetworkService.upgradeVersion(caseEstateNetwork);
            }
        }
    }

    private void flowWriteCaseParking(List<BasicEstateParking> basicEstateParkingList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateParkingList)) {
            for (BasicEstateParking oo : basicEstateParkingList) {
                CaseEstateParking estateParking = null;
                if (oo.getCaseId() != null) {
                    estateParking = caseEstateParkingService.getEstateParkingById(oo.getCaseId());
                    if (estateParking != null) {
                        BeanUtils.copyProperties(oo, estateParking);
                        estateParking.setId(oo.getCaseId());
                        estateParking.setGmtModified(null);
                        estateParking.setGmtCreated(null);
                    }
                    if (estateParking == null) {
                        estateParking = new CaseEstateParking();
                        BeanUtils.copyProperties(oo, estateParking);
                        estateParking.setId(null);
                        estateParking.setGmtModified(null);
                        estateParking.setGmtCreated(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    estateParking = new CaseEstateParking();
                    BeanUtils.copyProperties(oo, estateParking);
                    estateParking.setId(null);
                    estateParking.setGmtModified(null);
                    estateParking.setGmtCreated(null);
                }
                estateParking.setEstateId(caseEstate.getId());
                caseEstateParkingService.upgradeVersion(estateParking);
            }
        }
    }

    private void flowWriteCaseSupply(List<BasicEstateSupply> basicEstateSupplyList, CaseEstate caseEstate) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateSupplyList)) {
            for (BasicEstateSupply oo : basicEstateSupplyList) {
                CaseEstateSupply caseEstateSupply = null;
                if (oo.getCaseId() != null) {
                    caseEstateSupply = caseEstateSupplyService.getCaseEstateSupplyById(oo.getCaseId());
                    if (caseEstateSupply != null) {
                        caseEstateSupply = new CaseEstateSupply();
                        BeanUtils.copyProperties(oo, caseEstateSupply);
                        caseEstateSupply.setId(oo.getCaseId());
                        caseEstateSupply.setGmtCreated(null);
                        caseEstateSupply.setGmtModified(null);
                    }
                    if (caseEstateSupply == null) {
                        caseEstateSupply = new CaseEstateSupply();
                        BeanUtils.copyProperties(oo, caseEstateSupply);
                        caseEstateSupply.setId(null);
                        caseEstateSupply.setGmtCreated(null);
                        caseEstateSupply.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseEstateSupply = new CaseEstateSupply();
                    BeanUtils.copyProperties(oo, caseEstateSupply);
                    caseEstateSupply.setId(null);
                    caseEstateSupply.setGmtCreated(null);
                    caseEstateSupply.setGmtModified(null);
                }
                caseEstateSupply.setEstateId(caseEstate.getId());
                caseEstateSupplyService.upgradeVersion(caseEstateSupply);
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
    private CaseBuildingMain flowWriteCaseBuildingMain(BasicBuildingMain basicBuildingMain, Integer estateId) throws Exception {
        CaseBuildingMain caseBuildingMain = null;
        Integer caseBuildingMainId = null;
        if (basicBuildingMain.getCaseBuildingMain() != null) {
            caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(basicBuildingMain.getCaseBuildingMain());
            if (caseBuildingMain != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
                caseBuildingMain.setId(basicBuildingMain.getCaseBuildingMain());
                caseBuildingMain.setGmtCreated(null);
                caseBuildingMain.setGmtModified(null);
            }
            if (caseBuildingMain == null) {
                caseBuildingMain = new CaseBuildingMain();
                BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
                caseBuildingMain.setId(null);
                caseBuildingMain.setGmtCreated(null);
                caseBuildingMain.setGmtModified(null);
            }
        }
        if (basicBuildingMain.getCaseBuildingMain() == null) {
            caseBuildingMain = new CaseBuildingMain();
            BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
            caseBuildingMain.setId(null);
            caseBuildingMain.setGmtCreated(null);
            caseBuildingMain.setGmtModified(null);
        }
        //升级版本 以及更改某些数据  或者 新添数据
        if (caseBuildingMain != null) {
            caseBuildingMain.setEstateId(estateId);
            caseBuildingMainId = caseBuildingMainService.upgradeVersion(caseBuildingMain);
            caseBuildingMain.setId(caseBuildingMainId);
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

                CaseBuilding caseBuilding = null;
                if (basicBuilding.getCaseBuildingId() != null) {
                    caseBuilding = caseBuildingService.getCaseBuildingById(basicBuilding.getCaseBuildingId());
                    if (caseBuilding != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicBuilding, caseBuilding);
                        caseBuilding.setId(basicBuilding.getCaseBuildingId());
                        caseBuilding.setCaseBuildingMainId(caseBuildingMain.getId());
                        caseBuilding.setGmtCreated(null);
                        caseBuilding.setGmtModified(null);
                    }
                    if (caseBuilding == null) {
                        caseBuilding = new CaseBuilding();
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicBuilding, caseBuilding);
                        caseBuilding.setCaseBuildingMainId(caseBuildingMain.getId());
                        caseBuilding.setId(null);
                        caseBuilding.setGmtCreated(null);
                        caseBuilding.setGmtModified(null);
                    }
                }
                if (basicBuilding.getCaseBuildingId() == null) {
                    caseBuilding = new CaseBuilding();
                    BeanCopyHelp.copyPropertiesIgnoreNull(basicBuilding, caseBuilding);
                    caseBuilding.setCaseBuildingMainId(caseBuildingMain.getId());
                    caseBuilding.setId(null);
                    caseBuilding.setGmtCreated(null);
                    caseBuilding.setGmtModified(null);
                }
                if (caseBuilding != null){
                    caseBuildingService.upgradeVersion(caseBuilding);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                            sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
                            sysAttachmentDto.setTableId(caseBuilding.getId());
                            baseAttachmentService.updateAttachment(sysAttachmentDto);
                        }
                    }
                    if (caseBuilding.getId() != null){
                        this.flowWriteCaseBuildingOutfit(outfitList, caseBuilding);
                        this.flowWriteCaseBuildingMaintenance(maintenanceList, caseBuilding);
                        this.flowWriteCaseBuildingSurface(surfaceList, caseBuilding);
                        this.flowWriteCaseBuildingFunction(functionList, caseBuilding);
                    }
                }
            }
        }
    }

    private void flowWriteCaseBuildingOutfit(List<BasicBuildingOutfit> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingOutfit oo : list) {
                CaseBuildingOutfit caseBuildingOutfit = null;
                if (oo.getCaseOutfitId() != null) {
                    caseBuildingOutfit = caseBuildingOutfitService.getCaseBuildingOutfitById(oo.getCaseOutfitId());
                    if (caseBuildingOutfit == null) {
                        caseBuildingOutfit = new CaseBuildingOutfit();
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingOutfit);
                        caseBuildingOutfit.setId(null);
                        caseBuildingOutfit.setGmtCreated(null);
                        caseBuildingOutfit.setGmtModified(null);
                    }
                    if (caseBuildingOutfit != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingOutfit);
                        caseBuildingOutfit.setId(oo.getCaseOutfitId());
                        caseBuildingOutfit.setGmtCreated(null);
                        caseBuildingOutfit.setGmtModified(null);
                    }
                }
                if (oo.getCaseOutfitId() == null) {
                    caseBuildingOutfit = new CaseBuildingOutfit();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingOutfit);
                    caseBuildingOutfit.setId(null);
                    caseBuildingOutfit.setGmtCreated(null);
                    caseBuildingOutfit.setGmtModified(null);
                }
                caseBuildingOutfit.setBuildingId(caseBuilding.getId());
                caseBuildingOutfitService.upgradeVersion(caseBuildingOutfit);
            }
        }
    }

    private void flowWriteCaseBuildingMaintenance(List<BasicBuildingMaintenance> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingMaintenance oo : list) {
                CaseBuildingMaintenance caseBuildingMaintenance = null;
                if (oo.getCaseMaintenanceId() != null) {
                    caseBuildingMaintenance = caseBuildingMaintenanceService.getCaseBuildingMaintenanceById(oo.getCaseMaintenanceId());
                    if (caseBuildingMaintenance != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingMaintenance);
                        caseBuildingMaintenance.setId(oo.getCaseMaintenanceId());
                        caseBuildingMaintenance.setGmtCreated(null);
                        caseBuildingMaintenance.setGmtModified(null);
                    }
                    if (caseBuildingMaintenance == null) {
                        caseBuildingMaintenance = new CaseBuildingMaintenance();
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingMaintenance);
                        caseBuildingMaintenance.setId(null);
                        caseBuildingMaintenance.setGmtCreated(null);
                        caseBuildingMaintenance.setGmtModified(null);
                    }
                }
                if (oo.getCaseMaintenanceId() == null) {
                    caseBuildingMaintenance = new CaseBuildingMaintenance();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingMaintenance);
                    caseBuildingMaintenance.setId(null);
                    caseBuildingMaintenance.setGmtCreated(null);
                    caseBuildingMaintenance.setGmtModified(null);
                }
                caseBuildingMaintenance.setBuildingId(caseBuilding.getId());
                caseBuildingMaintenanceService.upgradeVersion(caseBuildingMaintenance);
            }
        }
    }

    private void flowWriteCaseBuildingSurface(List<BasicBuildingSurface> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingSurface oo : list) {
                CaseBuildingSurface caseBuildingSurface = null;
                if (oo.getCaseSurfaceId() != null) {
                    caseBuildingSurface = caseBuildingSurfaceService.getCaseBuildingSurfaceById(oo.getCaseSurfaceId());
                    if (caseBuildingSurface != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingSurface);
                        caseBuildingSurface.setId(oo.getCaseSurfaceId());
                        caseBuildingSurface.setGmtCreated(null);
                        caseBuildingSurface.setGmtModified(null);
                    }
                    if (caseBuildingSurface == null) {
                        caseBuildingSurface = new CaseBuildingSurface();
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingSurface);
                        caseBuildingSurface.setId(null);
                        caseBuildingSurface.setGmtCreated(null);
                        caseBuildingSurface.setGmtModified(null);
                    }
                }
                if (oo.getCaseSurfaceId() == null) {
                    caseBuildingSurface = new CaseBuildingSurface();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingSurface);
                    caseBuildingSurface.setId(null);
                    caseBuildingSurface.setGmtCreated(null);
                    caseBuildingSurface.setGmtModified(null);
                }
                caseBuildingSurface.setBuildingId(caseBuilding.getId());
                caseBuildingSurfaceService.upgradeVersion(caseBuildingSurface);
            }
        }
    }

    private void flowWriteCaseBuildingFunction(List<BasicBuildingFunction> list, CaseBuilding caseBuilding) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingFunction oo : list) {
                CaseBuildingFunction caseBuildingFunction = null;
                if (oo.getCaseFunctionId() != null) {
                    caseBuildingFunction = caseBuildingFunctionService.getCaseBuildingFunctionById(oo.getCaseFunctionId());
                    if (caseBuildingFunction != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingFunction);
                        caseBuildingFunction.setId(oo.getCaseFunctionId());
                        caseBuildingFunction.setGmtCreated(null);
                        caseBuildingFunction.setGmtModified(null);
                    }
                    if (caseBuildingFunction == null) {
                        caseBuildingFunction = new CaseBuildingFunction();
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingFunction);
                        caseBuildingFunction.setId(null);
                        caseBuildingFunction.setGmtCreated(null);
                        caseBuildingFunction.setGmtModified(null);
                    }
                }
                if (oo.getCaseFunctionId() == null) {
                    caseBuildingFunction = new CaseBuildingFunction();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oo, caseBuildingFunction);
                    caseBuildingFunction.setId(null);
                    caseBuildingFunction.setGmtCreated(null);
                    caseBuildingFunction.setGmtModified(null);
                }
                caseBuildingFunction.setBuildingId(caseBuilding.getId());
                caseBuildingFunctionService.upgradeVersion(caseBuildingFunction);
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
    private CaseUnit flowWriteCaseUnit(BasicUnit basicUnit, Integer caseBuildingMainId) throws Exception {
        CaseUnit caseUnit = null;
        if (basicUnit.getCaseUnitId() != null) {
            caseUnit = caseUnitService.getCaseUnitById(basicUnit.getCaseUnitId());
            if (caseUnit != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicUnit, caseUnit);
                caseUnit.setId(basicUnit.getCaseUnitId());
                caseUnit.setGmtCreated(null);
                caseUnit.setGmtModified(null);
            }
            if (caseUnit == null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicUnit, caseUnit);
                caseUnit.setId(null);
                caseUnit.setGmtCreated(null);
                caseUnit.setGmtModified(null);
            }
        }
        if (basicUnit.getCaseUnitId() == null) {
            caseUnit = new CaseUnit();
            BeanCopyHelp.copyPropertiesIgnoreNull(basicUnit, caseUnit);
            caseUnit.setId(null);
            caseUnit.setGmtCreated(null);
            caseUnit.setGmtModified(null);
        }
        if (caseUnit != null) {
            caseUnit.setCaseBuildingMainId(caseBuildingMainId);
            caseUnitService.upgradeVersion(caseUnit);
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
                CaseUnitDecorate caseUnitDecorate = null;
                if (oo.getCaseId() != null) {
                    caseUnitDecorate = caseUnitDecorateService.getCaseUnitDecorateById(oo.getCaseId());
                    if (caseUnitDecorate == null) {
                        caseUnitDecorate = new CaseUnitDecorate();
                        BeanUtils.copyProperties(oo, caseUnitDecorate);
                        caseUnitDecorate.setId(null);
                        caseUnitDecorate.setGmtCreated(null);
                        caseUnitDecorate.setGmtModified(null);
                    }
                    if (caseUnitDecorate != null) {
                        BeanUtils.copyProperties(oo, caseUnitDecorate);
                        caseUnitDecorate.setId(oo.getCaseId());
                        caseUnitDecorate.setGmtCreated(null);
                        caseUnitDecorate.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseUnitDecorate = new CaseUnitDecorate();
                    BeanUtils.copyProperties(oo, caseUnitDecorate);
                    caseUnitDecorate.setId(null);
                    caseUnitDecorate.setGmtCreated(null);
                    caseUnitDecorate.setGmtModified(null);
                }
                caseUnitDecorate.setUnitId(caseUnit.getId());
                caseUnitDecorateService.upgradeVersion(caseUnitDecorate);
            }
        }
    }

    public void flowWriteCaseHuxing(List<BasicUnitHuxing> basicUnitHuxingList, CaseUnit caseUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing oo : basicUnitHuxingList) {
                CaseUnitHuxing caseUnitHuxing = null;
                if (oo.getCaseId() != null) {
                    caseUnitHuxing = caseUnitHuxingService.getCaseUnitHuxingById(oo.getCaseId());
                    if (caseUnitHuxing != null) {
                        BeanUtils.copyProperties(oo, caseUnitHuxing);
                        caseUnitHuxing.setId(oo.getCaseId());
                        caseUnitHuxing.setGmtCreated(null);
                        caseUnitHuxing.setGmtModified(null);
                    }
                    if (caseUnitHuxing == null) {
                        caseUnitHuxing = new CaseUnitHuxing();
                        BeanUtils.copyProperties(oo, caseUnitHuxing);
                        caseUnitHuxing.setId(null);
                        caseUnitHuxing.setGmtCreated(null);
                        caseUnitHuxing.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseUnitHuxing = new CaseUnitHuxing();
                    BeanUtils.copyProperties(oo, caseUnitHuxing);
                    caseUnitHuxing.setId(null);
                    caseUnitHuxing.setGmtCreated(null);
                    caseUnitHuxing.setGmtModified(null);
                }
                caseUnitHuxing.setUnitId(caseUnit.getId());
                caseUnitHuxingService.upgradeVersion(caseUnitHuxing);
            }
        }
    }

    public void flowWriteCaseElevator(List<BasicUnitElevator> basicUnitElevatorList, CaseUnit caseUnit) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitElevatorList)) {
            for (BasicUnitElevator oo : basicUnitElevatorList) {
                CaseUnitElevator caseUnitElevator = null;
                if (oo.getCaseId() != null) {
                    caseUnitElevator = caseUnitElevatorService.getEstateNetworkById(oo.getCaseId());
                    if (caseUnitElevator != null) {
                        BeanUtils.copyProperties(oo, caseUnitElevator);
                        caseUnitElevator.setId(oo.getCaseId());
                        caseUnitElevator.setGmtCreated(null);
                        caseUnitElevator.setGmtModified(null);
                    }
                    if (caseUnitElevator == null) {
                        caseUnitElevator = new CaseUnitElevator();
                        BeanUtils.copyProperties(oo, caseUnitElevator);
                        caseUnitElevator.setId(null);
                        caseUnitElevator.setGmtCreated(null);
                        caseUnitElevator.setGmtModified(null);
                    }
                }
                if (oo.getCaseId() == null) {
                    caseUnitElevator = new CaseUnitElevator();
                    BeanUtils.copyProperties(oo, caseUnitElevator);
                    caseUnitElevator.setId(null);
                    caseUnitElevator.setGmtCreated(null);
                    caseUnitElevator.setGmtModified(null);
                }
                caseUnitElevator.setUnitId(caseUnit.getId());
                caseUnitElevatorService.upgradeVersion(caseUnitElevator);
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
    public void flowWriteCaseHouse(BasicHouse basicHouse, BasicHouseTrading basicTrading, Integer unitId) throws Exception {
        CaseHouseTrading caseHouseTrading = null;
        CaseHouse caseHouse = null;
        if (basicHouse.getCaseHouseId() != null) {
            caseHouse = caseHouseService.getCaseHouseById(basicHouse.getCaseHouseId());
            if (caseHouse != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicHouse, caseHouse);
                caseHouse.setId(basicHouse.getCaseHouseId());
                caseHouse.setGmtCreated(null);
                caseHouse.setGmtModified(null);
            }
            if (caseHouse == null) {
                caseHouse = new CaseHouse();
                BeanCopyHelp.copyPropertiesIgnoreNull(basicHouse, caseHouse);
                caseHouse.setId(null);
                caseHouse.setGmtCreated(null);
                caseHouse.setGmtModified(null);
            }
        }
        if (basicHouse.getCaseHouseId() == null) {
            caseHouse = new CaseHouse();
            BeanCopyHelp.copyPropertiesIgnoreNull(basicHouse, caseHouse);
            caseHouse.setId(null);
            caseHouse.setGmtCreated(null);
            caseHouse.setGmtModified(null);
        }
        if (caseHouse != null) {
            caseHouse.setUnitId(unitId);
            caseHouseService.upgradeVersion(caseHouse);
            if (caseHouse.getId() != null) {
                if (basicTrading != null){
                    if (basicTrading.getCaseTradingId() != null) {
                        caseHouseTrading = caseHouseTradingService.getCaseHouseTradingById(basicTrading.getCaseTradingId());
                        if (caseHouseTrading != null) {
                            BeanCopyHelp.copyPropertiesIgnoreNull(basicTrading, caseHouseTrading);
                            caseHouseTrading.setId(basicTrading.getCaseTradingId());
                        }
                        if (caseHouseTrading == null) {
                            caseHouseTrading = new CaseHouseTrading();
                            BeanCopyHelp.copyPropertiesIgnoreNull(basicTrading, caseHouseTrading);
                            caseHouseTrading.setId(null);
                            caseHouseTrading.setGmtCreated(null);
                            caseHouseTrading.setGmtModified(null);
                        }
                    }
                    if (basicTrading.getCaseTradingId() == null) {
                        caseHouseTrading = new CaseHouseTrading();
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicTrading, caseHouseTrading);
                        caseHouseTrading.setId(null);
                        caseHouseTrading.setGmtCreated(null);
                        caseHouseTrading.setGmtModified(null);
                    }
                    caseHouseTrading.setHouseId(caseHouse.getId());
                    caseHouseTradingService.upgradeVersion(caseHouseTrading);
                }
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

        BasicHouseTradingSell querySell = new BasicHouseTradingSell();
        BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseWater queryBasicHouseWater = new BasicHouseWater();
        BasicHouseIntelligent queryBasicHouseIntelligent = new BasicHouseIntelligent();
        BasicHouseFaceStreet queryBasicHouseFaceStreet = new BasicHouseFaceStreet();
        BasicHouseEquipment queryBasicHouseEquipment = new BasicHouseEquipment();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();

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
            }
        }

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
            }
        }
    }

    private void flowWriteCaseCorollaryEquipment(List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseCorollaryEquipmentList)) {
            basicHouseCorollaryEquipmentList.forEach(oo -> {
                try {
                    CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = null;
                    if (oo.getCaseCorollaryEquipmentId() != null) {
                        caseHouseCorollaryEquipment = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentById(oo.getCaseCorollaryEquipmentId());
                        if (caseHouseCorollaryEquipment != null) {
                            caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
                            BeanUtils.copyProperties(oo, caseHouseCorollaryEquipment);
                            caseHouseCorollaryEquipment.setId(oo.getCaseCorollaryEquipmentId());
                            caseHouseCorollaryEquipment.setGmtCreated(null);
                            caseHouseCorollaryEquipment.setGmtModified(null);
                        }
                        if (caseHouseCorollaryEquipment == null) {
                            caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
                            BeanUtils.copyProperties(oo, caseHouseCorollaryEquipment);
                            caseHouseCorollaryEquipment.setId(null);
                            caseHouseCorollaryEquipment.setGmtCreated(null);
                            caseHouseCorollaryEquipment.setGmtModified(null);
                        }
                    }
                    if (oo.getCaseCorollaryEquipmentId() == null) {
                        caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
                        BeanUtils.copyProperties(oo, caseHouseCorollaryEquipment);
                        caseHouseCorollaryEquipment.setId(null);
                        caseHouseCorollaryEquipment.setGmtCreated(null);
                        caseHouseCorollaryEquipment.setGmtModified(null);
                    }
                    caseHouseCorollaryEquipment.setHouseId(caseHouse.getId());
                    caseHouseCorollaryEquipmentService.upgradeVersion(caseHouseCorollaryEquipment);
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
                    CaseHouseEquipment caseHouseEquipment = null;
                    if (oo.getCaseEquipmentId() != null) {
                        caseHouseEquipment = caseHouseEquipmentService.getCaseHouseEquipmentById(oo.getCaseEquipmentId());
                        if (caseHouseEquipment != null) {
                            BeanUtils.copyProperties(oo, caseHouseEquipment);
                            caseHouseEquipment.setId(oo.getCaseEquipmentId());
                            caseHouseEquipment.setGmtCreated(null);
                            caseHouseEquipment.setGmtModified(null);
                        }
                        if (caseHouseEquipment == null) {
                            caseHouseEquipment = new CaseHouseEquipment();
                            BeanUtils.copyProperties(oo, caseHouseEquipment);
                            caseHouseEquipment.setId(null);
                            caseHouseEquipment.setGmtCreated(null);
                            caseHouseEquipment.setGmtModified(null);
                        }
                    }
                    if (oo.getCaseEquipmentId() == null) {
                        caseHouseEquipment = new CaseHouseEquipment();
                        BeanUtils.copyProperties(oo, caseHouseEquipment);
                        caseHouseEquipment.setId(null);
                        caseHouseEquipment.setGmtCreated(null);
                        caseHouseEquipment.setGmtModified(null);
                    }
                    caseHouseEquipment.setHouseId(caseHouse.getId());
                    caseHouseEquipmentService.upgradeVersion(caseHouseEquipment);
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
                    CaseHouseFaceStreet caseHouseFaceStreet = null;
                    if (oo.getCaseFaceStreetId() != null) {
                        caseHouseFaceStreet = caseHouseFaceStreetService.getCaseHouseFaceStreetById(oo.getCaseFaceStreetId());
                        if (caseHouseFaceStreet != null) {
                            BeanUtils.copyProperties(oo, caseHouseFaceStreet);
                            caseHouseFaceStreet.setId(oo.getCaseFaceStreetId());
                            caseHouseFaceStreet.setGmtCreated(null);
                            caseHouseFaceStreet.setGmtModified(null);
                        }
                        if (caseHouseFaceStreet == null) {
                            caseHouseFaceStreet = new CaseHouseFaceStreet();
                            BeanUtils.copyProperties(oo, caseHouseFaceStreet);
                            caseHouseFaceStreet.setId(null);
                            caseHouseFaceStreet.setGmtCreated(null);
                            caseHouseFaceStreet.setGmtModified(null);
                        }
                    }
                    if (oo.getCaseFaceStreetId() == null) {
                        caseHouseFaceStreet = new CaseHouseFaceStreet();
                        BeanUtils.copyProperties(oo, caseHouseFaceStreet);
                        caseHouseFaceStreet.setId(null);
                        caseHouseFaceStreet.setGmtCreated(null);
                        caseHouseFaceStreet.setGmtModified(null);
                    }
                    caseHouseFaceStreet.setHouseId(caseHouse.getId());
                    caseHouseFaceStreetService.upgradeVersion(caseHouseFaceStreet);
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
                    CaseHouseIntelligent caseHouseIntelligent = null;
                    if (oo.getCaseIntelligentId() != null) {
                        caseHouseIntelligent = caseHouseIntelligentService.getCaseHouseIntelligentById(oo.getCaseIntelligentId());
                        if (caseHouseIntelligent != null) {
                            BeanUtils.copyProperties(oo, caseHouseIntelligent);
                            caseHouseIntelligent.setId(oo.getCaseIntelligentId());
                            caseHouseIntelligent.setGmtCreated(null);
                            caseHouseIntelligent.setGmtModified(null);
                        }
                        if (caseHouseIntelligent == null) {
                            caseHouseIntelligent = new CaseHouseIntelligent();
                            BeanUtils.copyProperties(oo, caseHouseIntelligent);
                            caseHouseIntelligent.setId(null);
                            caseHouseIntelligent.setGmtCreated(null);
                            caseHouseIntelligent.setGmtModified(null);
                        }
                    }
                    if (oo.getCaseIntelligentId() == null) {
                        caseHouseIntelligent = new CaseHouseIntelligent();
                        BeanUtils.copyProperties(oo, caseHouseIntelligent);
                        caseHouseIntelligent.setId(null);
                        caseHouseIntelligent.setGmtCreated(null);
                        caseHouseIntelligent.setGmtModified(null);
                    }
                    caseHouseIntelligent.setHouseId(caseHouse.getId());
                    caseHouseIntelligentService.upgradeVersion(caseHouseIntelligent);
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
                    CaseHouseWater caseHouseWater = null;
                    if (oo.getCaseHouseWaterId() != null) {
                        caseHouseWater = caseHouseWaterService.getCaseHouseWaterById(oo.getCaseHouseWaterId());
                        if (caseHouseWater != null) {
                            BeanUtils.copyProperties(oo, caseHouseWater);
                            caseHouseWater.setId(oo.getCaseHouseWaterId());
                            caseHouseWater.setGmtCreated(null);
                            caseHouseWater.setGmtModified(null);
                        }
                        if (caseHouseWater == null) {
                            caseHouseWater = new CaseHouseWater();
                            BeanUtils.copyProperties(oo, caseHouseWater);
                            caseHouseWater.setId(null);
                            caseHouseWater.setGmtCreated(null);
                            caseHouseWater.setGmtModified(null);
                        }
                    }
                    if (oo.getCaseHouseWaterId() == null) {
                        caseHouseWater = new CaseHouseWater();
                        BeanUtils.copyProperties(oo, caseHouseWater);
                        caseHouseWater.setId(null);
                        caseHouseWater.setGmtCreated(null);
                        caseHouseWater.setGmtModified(null);
                    }
                    caseHouseWater.setHouseId(caseHouse.getId());
                    caseHouseWaterService.upgradeVersion(caseHouseWater);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void flowWriteCaseSellAndLease(List<BasicHouseTradingSell> basicHouseTradingSellList, List<BasicHouseTradingLease> basicHouseTradingLeaseList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseTradingSellList)) {
            for (BasicHouseTradingSell oo : basicHouseTradingSellList) {
                CaseHouseTradingSell caseHouseTradingSell = null;
                if (oo.getCaseTradingSellId() != null) {
                    caseHouseTradingSell = caseHouseTradingSellService.getCaseHouseTradingSellById(oo.getCaseTradingSellId());
                    if (caseHouseTradingSell != null) {
                        BeanUtils.copyProperties(oo, caseHouseTradingSell);
                        caseHouseTradingSell.setId(oo.getCaseTradingSellId());
                        caseHouseTradingSell.setGmtCreated(null);
                        caseHouseTradingSell.setGmtModified(null);
                    }
                    if (caseHouseTradingSell == null) {
                        caseHouseTradingSell = new CaseHouseTradingSell();
                        BeanUtils.copyProperties(oo, caseHouseTradingSell);
                        caseHouseTradingSell.setId(null);
                        caseHouseTradingSell.setGmtCreated(null);
                        caseHouseTradingSell.setGmtModified(null);
                    }
                }
                if (oo.getCaseTradingSellId() == null) {
                    caseHouseTradingSell = new CaseHouseTradingSell();
                    BeanUtils.copyProperties(oo, caseHouseTradingSell);
                    caseHouseTradingSell.setId(null);
                    caseHouseTradingSell.setGmtCreated(null);
                    caseHouseTradingSell.setGmtModified(null);
                }
                caseHouseTradingSell.setHouseId(caseHouse.getId());
                caseHouseTradingSellService.upgradeVersion(caseHouseTradingSell);
            }
        }

        if (!ObjectUtils.isEmpty(basicHouseTradingLeaseList)) {
            for (BasicHouseTradingLease oo : basicHouseTradingLeaseList) {
                CaseHouseTradingLease caseHouseTradingLease = null;
                if (oo.getCaseTradingLeaseId() != null) {
                    caseHouseTradingLease = caseHouseTradingLeaseService.getCaseHouseTradingLeaseById(oo.getCaseTradingLeaseId());
                    if (caseHouseTradingLease != null) {
                        BeanUtils.copyProperties(oo, caseHouseTradingLease);
                        caseHouseTradingLease.setId(oo.getCaseTradingLeaseId());
                    }
                    if (caseHouseTradingLease == null) {
                        caseHouseTradingLease = new CaseHouseTradingLease();
                        BeanUtils.copyProperties(oo, caseHouseTradingLease);
                        caseHouseTradingLease.setId(null);
                    }
                }
                if (oo.getCaseTradingLeaseId() == null) {
                    caseHouseTradingLease = new CaseHouseTradingLease();
                    BeanUtils.copyProperties(oo, caseHouseTradingLease);
                    caseHouseTradingLease.setId(null);
                }
                caseHouseTradingLease.setHouseId(caseHouse.getId());
                caseHouseTradingLeaseService.upgradeVersion(caseHouseTradingLease);
            }
        }
    }

    private void flowWriteCaseHouseRoom(List<BasicHouseRoom> basicHouseRoomList, CaseHouse caseHouse) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
            for (BasicHouseRoom oo : basicHouseRoomList) {
                BasicHouseRoomDecorate query = new BasicHouseRoomDecorate();
                query.setRoomId(oo.getId());
                List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(query);
                CaseHouseRoom caseHouseRoom = null;
                if (oo.getCaseRoomId() != null) {
                    caseHouseRoom = caseHouseRoomService.getCaseHouseRoomById(oo.getCaseRoomId());
                    if (caseHouseRoom != null) {
                        BeanUtils.copyProperties(oo, caseHouseRoom);
                        caseHouseRoom.setId(oo.getCaseRoomId());
                        caseHouseRoom.setGmtCreated(null);
                        caseHouseRoom.setGmtModified(null);
                    }
                    if (caseHouseRoom == null) {
                        caseHouseRoom = new CaseHouseRoom();
                        BeanUtils.copyProperties(oo, caseHouseRoom);
                        caseHouseRoom.setId(null);
                        caseHouseRoom.setGmtCreated(null);
                        caseHouseRoom.setGmtModified(null);
                    }
                }
                if (oo.getCaseRoomId() == null) {
                    caseHouseRoom = new CaseHouseRoom();
                    BeanUtils.copyProperties(oo, caseHouseRoom);
                    caseHouseRoom.setId(null);
                    caseHouseRoom.setGmtCreated(null);
                    caseHouseRoom.setGmtModified(null);
                }
                caseHouseRoom.setHouseId(caseHouse.getId());
                caseHouseRoomService.upgradeVersion(caseHouseRoom);
                if (!ObjectUtils.isEmpty(basicHouseRoomDecorateList)) {
                    for (BasicHouseRoomDecorate po : basicHouseRoomDecorateList) {
                        CaseHouseRoomDecorate caseHouseRoomDecorate = null;
                        if (po.getCaseRoomDecorateId() != null) {
                            caseHouseRoomDecorate = caseHouseRoomDecorateService.getCaseHouseRoomDecorateById(po.getCaseRoomDecorateId());
                            if (caseHouseRoomDecorate != null) {
                                BeanUtils.copyProperties(po, caseHouseRoomDecorate);
                                caseHouseRoomDecorate.setGmtCreated(null);
                                caseHouseRoomDecorate.setGmtModified(null);
                                caseHouseRoomDecorate.setId(po.getCaseRoomDecorateId());
                                caseHouseRoomDecorate.setGmtCreated(null);
                                caseHouseRoomDecorate.setGmtModified(null);
                            } else {
                                caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                                BeanUtils.copyProperties(po, caseHouseRoomDecorate);
                                caseHouseRoomDecorate.setGmtCreated(null);
                                caseHouseRoomDecorate.setGmtModified(null);
                                caseHouseRoomDecorate.setId(null);
                            }
                        }
                        if (po.getCaseRoomDecorateId() == null) {
                            caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                            BeanUtils.copyProperties(po, caseHouseRoomDecorate);
                            caseHouseRoomDecorate.setGmtCreated(null);
                            caseHouseRoomDecorate.setGmtModified(null);
                            caseHouseRoomDecorate.setId(null);
                        }
                        caseHouseRoomDecorate.setRoomId(caseHouseRoom.getId());
                        caseHouseRoomDecorateService.upgradeVersion(caseHouseRoomDecorate);
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
    public void flowWrite(String processInsId) throws Exception {
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
        if (basicApply != null) {
            BasicEstate basicEstate = this.getByAppIdBasicEstate(basicApply.getId());
            BasicEstateLandState basicEstateLandState = this.getByAppIdEstateLandState(basicApply.getId());
            BasicBuildingMain basicBuildingMain = this.getByAppIdBasicBuildingMain(basicApply.getId());
            BasicUnit basicUnit = this.getByByAppIdBasicUnit(basicApply.getId());
            BasicHouse basicHouse = this.getByAppIdBasicHouseVo(basicApply.getId());
            BasicHouseTrading basicTrading = this.getByAppIdBasicHouseTrading(basicApply.getId());

            CaseEstate caseEstate = null;
            CaseBuildingMain caseBuildingMain = null;
            CaseUnit caseUnit = null;

            //处理楼盘
            if (basicEstate != null) {
                caseEstate = this.flowWriteCaseEstate(basicEstate, basicEstateLandState);
            }

            if (basicBuildingMain != null) {
                if (caseEstate == null && basicBuildingMain.getEstateId() == null) {
                    throw new Exception("未选择楼盘信息或者是未新增楼盘数据!");
                }
                Integer caseEstateId = null;
                if (caseEstate == null && basicBuildingMain.getEstateId() != null) {
                    caseEstateId = basicBuildingMain.getEstateId();
                }
                if (caseEstate != null) {
                    caseEstateId = caseEstate.getId();
                }
                if (caseEstateId != null) {
                    caseBuildingMain = this.flowWriteCaseBuildingMain(basicBuildingMain, caseEstateId);
                }
                if (caseBuildingMain != null) {
                    //处理楼栋 关联表
                    this.flowWriteCaseBuilding(basicBuildingMain, caseBuildingMain);
                }
            }

            if (basicUnit != null) {
                if (caseBuildingMain == null && basicUnit.getBasicBuildingMainId() == null) {
                    throw new Exception("未选择楼栋信息或者是未新增楼栋数据!");
                }
                Integer caseBuildingMainId = null;
                if (caseBuildingMain == null && basicUnit.getBasicBuildingMainId() != null) {
                    caseBuildingMainId = basicUnit.getBasicBuildingMainId();
                }
                if (caseBuildingMain != null) {
                    caseBuildingMainId = caseBuildingMain.getId();
                }
                if (caseBuildingMainId != null) {
                    //处理单元
                    caseUnit = this.flowWriteCaseUnit(basicUnit, caseBuildingMainId);
                }
            }
            if (basicHouse != null) {
                if (caseUnit == null && basicHouse.getUnitId() == null) {
                    throw new Exception("未选择单元信息或者是未新增单元数据!");
                }
                Integer unitId = null;
                if (caseUnit == null && basicHouse.getUnitId() != null) {
                    unitId = basicHouse.getUnitId();
                }
                if (caseUnit != null) {
                    unitId = caseUnit.getId();
                }
                if (unitId != null) {
                    //处理房屋
                    this.flowWriteCaseHouse(basicHouse, basicTrading, unitId);
                }
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
     * 保存数据
     *
     * @param formData
     * @param bisNextUser
     * @throws Exception
     */
    public void saveBasic(String formData, Boolean bisNextUser) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return;
        }
        BasicApply basicApply = new BasicApply();
        basicApplyService.saveBasicApply(basicApply);

        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = null;
        BasicEstate basicEstate = null;

        //楼盘过程数据
        jsonContent = jsonObject.getString("basicEstate");
        if (StringUtils.isNotBlank(jsonContent)) {
            basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            if (basicEstate != null) {
                basicEstate.setApplyId(basicApply.getId());
                if (StringUtils.isNotEmpty(basicEstate.getName())){
                    basicApply.setEstateName(basicEstate.getName());
                }
                if (basicEstate.getId() != null) {
                    basicEstate.setCaseEstateId(basicEstate.getId());
                    basicEstate.setId(null);
                }
                try {
                    basicEstateService.upgradeVersion(basicEstate);
                    if (basicEstate.getId() != null) {
                        BasicEstateLandState basicEstateLandState = null;
                        if (StringUtils.isNotEmpty(jsonObject.getString("basicEstateLandState"))) {
                            basicEstateLandState = JSONObject.parseObject(jsonObject.getString("basicEstateLandState"), BasicEstateLandState.class);
                            if (basicEstateLandState != null) {
                                if (basicEstateLandState.getId() != null) {
                                    basicEstateLandState.setCaseEstateLandStateId(basicEstateLandState.getId());
                                    basicEstateLandState.setId(null);
                                }
                                basicEstateLandState.setEstateId(basicEstate.getId());
                                basicEstateLandState.setApplyId(basicApply.getId());
                                basicEstateLandStateService.upgradeVersion(basicEstateLandState);
                            }
                        }
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        //楼栋主过程数据
        jsonContent = null;
        jsonContent = jsonObject.getString("basicBuildingMain");
        BasicBuildingMain basicBuildingMain = null;
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuildingMain = JSONObject.parseObject(jsonContent, BasicBuildingMain.class);
            if (basicBuildingMain != null) {
                basicBuildingMain.setApplyId(basicApply.getId());
                if (StringUtils.isNotEmpty(basicBuildingMain.getIdentifier())){
                    basicApply.setBuildIdentifier(basicBuildingMain.getIdentifier());
                }
                if (basicBuildingMain.getId() != null) {
                    basicBuildingMain.setCaseBuildingMain(basicBuildingMain.getId());
                    basicBuildingMain.setId(null);
                }
                //当楼栋数据未包含楼盘 那么楼盘必须是新增的情况
                //1
                if (basicEstate == null) {
                    if (basicBuildingMain.getEstateId() != null) {
                        //...............
                    }
                    if (basicBuildingMain.getEstateId() == null) {
                        throw new Exception("未选择楼盘!");
                    }
                }
                //2
                if (basicEstate != null) {
                    if (basicEstate.getId() != null) {
                        basicBuildingMain.setEstateId(basicEstate.getId());
                    }
                    if (basicEstate.getId() == null) {
                        throw new Exception("楼盘数据异常!");
                    }
                }
                try {
                    basicBuildingMainService.upgradeVersion(basicBuildingMain);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
            //楼栋过程数据
            jsonContent = null;
            jsonContent = jsonObject.getString("basicBuildings");
            List<BasicBuilding> basicBuildingList = null;
            try {
                basicBuildingList = JSONObject.parseArray(jsonContent, BasicBuilding.class);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
            if (!ObjectUtils.isEmpty(basicBuildingList)) {
                for (BasicBuilding basicBuilding : basicBuildingList) {
                    if (basicBuilding.getId() != null) {
                        basicBuilding.setCaseBuildingId(basicBuilding.getId());
                        basicBuilding.setId(null);
                    }
                    if (basicBuildingMain.getId() != null) {
                        basicBuilding.setBasicBuildingMainId(basicBuildingMain.getId());
                        try {
                            basicBuildingService.upgradeVersion(basicBuilding);
                        } catch (Exception e1) {
                            logger.error(e1.getMessage(), e1);
                        }
                    }
                }
            }
        }
        //单元过程数据
        jsonContent = null;
        jsonContent = jsonObject.getString("basicUnit");
        BasicUnit basicUnit = null;
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
            if (basicUnit != null) {
                basicUnit.setApplyId(basicApply.getId());
                if (StringUtils.isNotEmpty(basicUnit.getUnitNumber())){
                    basicApply.setUnitNumber(basicUnit.getUnitNumber());
                }
                if (basicUnit.getId() != null) {
                    basicUnit.setCaseUnitId(basicUnit.getId());
                    basicUnit.setId(null);
                }
                if (basicBuildingMain != null) {
                    if (basicBuildingMain.getId() != null) {
                        basicUnit.setBasicBuildingMainId(basicBuildingMain.getId());
                    }
                    if (basicBuildingMain.getId() == null) {
                        throw new Exception("楼栋主数据异常!");
                    }
                }
                if (basicBuildingMain == null) {
                    if (basicUnit.getBasicBuildingMainId() != null) {
                        //....................
                    }
                    if (basicUnit.getBasicBuildingMainId() == null) {
                        throw new Exception("未选择楼栋主数据!");
                    }
                }
                try {
                    basicUnitService.upgradeVersion(basicUnit);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        //处理房屋数据
        jsonContent = null;
        jsonContent = jsonObject.getString("basicHouse");
        BasicHouse basicHouse = null;
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            if (basicHouse != null) {
                if (basicHouse.getId() != null) {
                    basicHouse.setCaseHouseId(basicHouse.getId());
                    basicHouse.setId(null);
                }
                basicHouse.setApplyId(basicApply.getId());
                if (StringUtils.isNotEmpty(basicHouse.getHouseNumber())){
                    basicApply.setHouseNumber(basicHouse.getHouseNumber());
                }
                if (basicUnit == null) {
                    if (basicHouse.getUnitId() != null) {
                        //
                    }
                    if (basicHouse.getUnitId() == null) {
                        throw new Exception("未选择单元");
                    }
                }
                if (basicUnit != null) {
                    if (basicUnit.getId() != null) {
                        basicHouse.setUnitId(basicUnit.getId());
                    }
                    if (basicUnit.getId() == null) {
                        throw new Exception("单元exception");
                    }
                }
                Integer house = basicHouseService.upgradeVersion(basicHouse);
                try {
                    BasicHouseTrading basicTrading = JSONObject.parseObject(jsonObject.getString("basicTrading"), BasicHouseTrading.class);
                    if (basicTrading != null) {
                        if (basicTrading.getId() != null) {
                            basicTrading.setCaseTradingId(basicTrading.getId());
                            basicTrading.setId(null);
                        }
                        basicTrading.setHouseId(house);
                        basicTrading.setApplyId(basicApply.getId());
                        basicHouseTradingService.upgradeVersion(basicTrading);
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        //发起流程
        basicApplyService.sumTask(basicApply, FormatUtils.entityNameConvertToTableName(BasicApply.class));
    }

    public BasicEstateVo getByAppIdBasicEstate(Integer appId) throws Exception {
        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setApplyId(appId);
        List<BasicEstate> basicEstates = basicEstateService.basicEstateList(basicEstate);
        Ordering<BasicEstate> ordering = Ordering.from(new Comparator<BasicEstate>() {
            @Override
            public int compare(BasicEstate o1, BasicEstate o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(basicEstates)) {
            Collections.sort(basicEstates, ordering);
            return basicEstateService.getBasicEstateVo(basicEstates.get(0));
        } else {
            return null;
        }
    }

    public BasicEstateLandStateVo getByAppIdEstateLandState(Integer appId) throws Exception {
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setApplyId(appId);
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(basicEstateLandState);
        Ordering<BasicEstateLandState> ordering = Ordering.from(new Comparator<BasicEstateLandState>() {
            @Override
            public int compare(BasicEstateLandState o1, BasicEstateLandState o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(basicEstateLandStateList)) {
            Collections.sort(basicEstateLandStateList, ordering);
            return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateList.get(0));
        } else {
            return null;
        }
    }

    public BasicBuildingMain getByAppIdBasicBuildingMain(Integer appId) throws Exception {
        BasicBuildingMain basicBuildingMain = new BasicBuildingMain();
        basicBuildingMain.setApplyId(appId);
        List<BasicBuildingMain> basicBuildingMains = basicBuildingMainService.basicBuildingMainList(basicBuildingMain);
        Ordering<BasicBuildingMain> ordering = Ordering.from(new Comparator<BasicBuildingMain>() {
            @Override
            public int compare(BasicBuildingMain o1, BasicBuildingMain o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(basicBuildingMains)) {
            Collections.sort(basicBuildingMains, ordering);
            return basicBuildingMains.get(0);
        } else {
            return null;
        }
    }

    public BasicUnit getByByAppIdBasicUnit(Integer appId) throws Exception {
        BasicUnit basicUnit = new BasicUnit();
        basicUnit.setApplyId(appId);
        List<BasicUnit> unitList = basicUnitService.basicUnitList(basicUnit);
        Ordering<BasicUnit> ordering = Ordering.from(new Comparator<BasicUnit>() {
            @Override
            public int compare(BasicUnit o1, BasicUnit o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(unitList)) {
            Collections.sort(unitList, ordering);
            return unitList.get(0);
        } else {
            return null;
        }
    }

    public BasicHouseTradingVo getByAppIdBasicHouseTrading(Integer appId) throws Exception {
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setApplyId(appId);
        List<BasicHouseTrading> basicHouseTradingList = basicHouseTradingService.basicHouseTradingList(basicHouseTrading);
        Ordering<BasicHouseTrading> ordering = Ordering.from((o1, o2) -> {
            BasicHouseTrading a1 = (BasicHouseTrading) o1;
            BasicHouseTrading a2 = (BasicHouseTrading) o2;
            return a1.getId().compareTo(a2.getId());
        }).reverse();
        if (!ObjectUtils.isEmpty(basicHouseTradingList)) {
            Collections.sort(basicHouseTradingList, ordering);
            return basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingList.get(0));
        } else {
            return null;
        }
    }

    public BasicHouseVo getByAppIdBasicHouseVo(Integer appId) throws Exception {
        BasicHouse basicHouse = new BasicHouseVo();
        basicHouse.setApplyId(appId);
        List<BasicHouse> basicHouseList = basicHouseService.basicHouseList(basicHouse);
        Ordering<BasicHouse> ordering = Ordering.from(new Comparator<BasicHouse>() {
            @Override
            public int compare(BasicHouse o1, BasicHouse o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(basicHouseList)) {
            Collections.sort(basicHouseList, ordering);
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
     * 将CaseBuilding下的子类 转移到 BasicBuilding下的子类中去 (用做过程数据)
     *
     * @param caseMainBuildId
     * @throws Exception
     */
    public void appWriteBuilding(Integer caseMainBuildId) throws Exception {
        if (caseMainBuildId == null) {
            throw new Exception("null point");
        }

        CaseBuilding query = new CaseBuilding();
        CaseBuildingOutfit queryOutfit = new CaseBuildingOutfit();
        CaseBuildingMaintenance queryMaintenance = new CaseBuildingMaintenance();
        CaseBuildingSurface querySurface = new CaseBuildingSurface();
        CaseBuildingFunction queryFunction = new CaseBuildingFunction();

        List<CaseBuilding> buildingList = null;
        query.setCaseBuildingMainId(caseMainBuildId);
        buildingList = caseBuildingService.getCaseBuildingList(query);

        if (!ObjectUtils.isEmpty(buildingList)) {
            List<CaseBuildingOutfit> buildingOutfitList = null;
            List<CaseBuildingMaintenance> maintenanceList = null;
            List<CaseBuildingSurface> surfaceList = null;
            List<CaseBuildingFunction> functionList = null;
            for (CaseBuilding ooA : buildingList) {

                queryOutfit.setBuildingId(ooA.getId());
                queryMaintenance.setBuildingId(ooA.getId());
                querySurface.setBuildingId(ooA.getId());
                queryFunction.setBuildingId(ooA.getId());


                buildingOutfitList = caseBuildingOutfitService.getCaseBuildingOutfitList(queryOutfit);
                maintenanceList = caseBuildingMaintenanceService.getCaseBuildingMaintenanceList(queryMaintenance);
                surfaceList = caseBuildingSurfaceService.getCaseBuildingSurfaceList(querySurface);
                functionList = caseBuildingFunctionService.getCaseBuildingFunctionListO(queryFunction);

                if (!ObjectUtils.isEmpty(buildingOutfitList)) {
                    BasicBuildingOutfit basicBuildingOutfit = new BasicBuildingOutfit();
                    for (CaseBuildingOutfit oo : buildingOutfitList) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingOutfit);
                        basicBuildingOutfit.setBuildingId(0);
                        basicBuildingOutfit.setId(null);
                        basicBuildingOutfit.setCaseOutfitId(oo.getId());
                        basicBuildingOutfit.setCreator(commonService.thisUserAccount());
                        basicBuildingOutfitService.saveAndUpdateBasicBuildingOutfit(basicBuildingOutfit);
                    }
                }
                if (!ObjectUtils.isEmpty(maintenanceList)) {
                    BasicBuildingMaintenance basicBuildingMaintenance = new BasicBuildingMaintenance();
                    for (CaseBuildingMaintenance oo : maintenanceList) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingMaintenance);
                        basicBuildingMaintenance.setBuildingId(0);
                        basicBuildingMaintenance.setId(null);
                        basicBuildingMaintenance.setCaseMaintenanceId(oo.getId());
                        basicBuildingMaintenance.setCreator(commonService.thisUserAccount());
                        basicBuildingMaintenanceService.saveAndUpdateBasicBuildingMaintenance(basicBuildingMaintenance);
                    }
                }
                if (!ObjectUtils.isEmpty(surfaceList)) {
                    BasicBuildingSurface basicBuildingSurface = new BasicBuildingSurface();
                    for (CaseBuildingSurface oo : surfaceList) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingSurface);
                        basicBuildingSurface.setBuildingId(0);
                        basicBuildingSurface.setId(null);
                        basicBuildingSurface.setCaseSurfaceId(oo.getId());
                        basicBuildingSurface.setCreator(commonService.thisUserAccount());
                        basicBuildingSurfaceService.saveAndUpdateBasicBuildingSurface(basicBuildingSurface);
                    }
                }
                if (!ObjectUtils.isEmpty(functionList)) {
                    BasicBuildingFunction basicBuildingFunction = new BasicBuildingFunction();
                    for (CaseBuildingFunction oo : functionList) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingFunction);
                        basicBuildingFunction.setBuildingId(0);
                        basicBuildingFunction.setId(null);
                        basicBuildingFunction.setCaseFunctionId(oo.getId());
                        basicBuildingFunction.setCreator(commonService.thisUserAccount());
                        basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(basicBuildingFunction);
                    }
                }
            }
        }
    }

    /**
     * 将 CaseUnit 下的子类 转移到 BasicUnit下的子类中去 (用做过程数据)
     *
     * @param caseUnitId
     * @throws Exception
     */
    public CaseUnit appWriteUnit(Integer caseUnitId) throws Exception {
        if (caseUnitId == null) {
            throw new Exception("null point");
        }
        CaseUnitDecorate caseUnitDecorate = new CaseUnitDecorate();
        caseUnitDecorate.setUnitId(caseUnitId);
        CaseUnitElevator caseUnitElevator = new CaseUnitElevator();
        caseUnitElevator.setUnitId(caseUnitId);
        CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
        caseUnitHuxing.setUnitId(caseUnitId);
        List<CaseUnitDecorate> caseUnitDecorates = caseUnitDecorateService.getCaseUnitDecorateList(caseUnitDecorate);
        List<CaseUnitElevator> caseUnitElevators = caseUnitElevatorService.getEstateNetworkLists(caseUnitElevator);
        List<CaseUnitHuxingVo> caseUnitHuxings = caseUnitHuxingService.getCaseUnitHuxingList(caseUnitHuxing);
        if (!ObjectUtils.isEmpty(caseUnitDecorates)) {
            for (CaseUnitDecorate oo : caseUnitDecorates) {
                BasicUnitDecorate queryBasicUnitDecorate = new BasicUnitDecorate();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, queryBasicUnitDecorate);
                queryBasicUnitDecorate.setId(null);
                queryBasicUnitDecorate.setUnitId(0);
                queryBasicUnitDecorate.setCaseId(oo.getId());
                queryBasicUnitDecorate.setCreator(commonService.thisUserAccount());
                basicUnitDecorateService.saveAndUpdateBasicUnitDecorate(queryBasicUnitDecorate);
            }
        }
        if (!ObjectUtils.isEmpty(caseUnitElevators)) {
            for (CaseUnitElevator oo : caseUnitElevators) {
                BasicUnitElevator queryBasicUnitElevator = new BasicUnitElevator();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, queryBasicUnitElevator);
                queryBasicUnitElevator.setId(null);
                queryBasicUnitElevator.setUnitId(0);
                queryBasicUnitElevator.setCaseId(oo.getId());
                queryBasicUnitElevator.setCreator(commonService.thisUserAccount());
                basicUnitElevatorService.saveAndUpdateBasicUnitElevator(queryBasicUnitElevator);
            }
        }
        if (!ObjectUtils.isEmpty(caseUnitHuxings)) {
            for (CaseUnitHuxingVo oo : caseUnitHuxings) {
                BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, queryBasicUnitHuxing);
                queryBasicUnitHuxing.setId(null);
                queryBasicUnitHuxing.setUnitId(0);
                queryBasicUnitHuxing.setCaseId(oo.getId());
                queryBasicUnitHuxing.setCreator(commonService.thisUserAccount());
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(queryBasicUnitHuxing);
            }
        }
        return caseUnitService.getCaseUnitById(caseUnitId);
    }

    /**
     * 将 CaseEstate 下的子类 转移到 BasicEstate下的子类中去 (用做过程数据)
     *
     * @param caseEstateId
     * @throws Exception
     */
    public Map<String, Object> appWriteEstate(Integer caseEstateId) throws Exception {
        if (caseEstateId == null) {
            throw new Exception("null point");
        }
        CaseEstateParking estateParking = new CaseEstateParking();
        estateParking.setEstateId(caseEstateId);
        CaseEstateNetwork caseEstateNetwork = new CaseEstateNetwork();
        caseEstateNetwork.setEstateId(caseEstateId);
        CaseEstateSupply caseEstateSupply = new CaseEstateSupply();
        caseEstateSupply.setEstateId(caseEstateId);
        CaseMatchingTraffic caseMatchingTraffic = new CaseMatchingTraffic();
        caseMatchingTraffic.setEstateId(caseEstateId);
        CaseMatchingMedical caseMatchingMedical = new CaseMatchingMedical();
        caseMatchingMedical.setEstateId(caseEstateId);
        CaseMatchingMaterial caseMatchingMaterial = new CaseMatchingMaterial();
        caseMatchingMaterial.setEstateId(caseEstateId);
        CaseMatchingLeisurePlace caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
        caseMatchingLeisurePlace.setEstateId(caseEstateId);
        CaseMatchingFinance caseMatchingFinance = new CaseMatchingFinance();
        caseMatchingFinance.setEstateId(caseEstateId);
        CaseMatchingEnvironment caseMatchingEnvironment = new CaseMatchingEnvironment();
        caseMatchingEnvironment.setEstateId(caseEstateId);
        CaseMatchingEducation caseMatchingEducation = new CaseMatchingEducation();
        caseMatchingEducation.setEstateId(caseEstateId);

        List<CaseMatchingTraffic> caseMatchingTraffics = caseMatchingTrafficService.getMatchingTrafficList(caseMatchingTraffic);
        List<CaseMatchingMedical> caseMatchingMedicals = caseMatchingMedicalService.getCaseMatchingMedicalList(caseMatchingMedical);
        List<CaseMatchingMaterial> caseMatchingMaterials = caseMatchingMaterialService.getCaseMatchingMaterialList(caseMatchingMaterial);
        List<CaseMatchingLeisurePlace> caseMatchingLeisurePlaces = caseMatchingLeisurePlaceService.getCaseMatchingLeisurePlaceList(caseMatchingLeisurePlace);
        List<CaseMatchingFinance> caseMatchingFinances = caseMatchingFinanceService.getCaseMatchingFinanceList(caseMatchingFinance);
        List<CaseMatchingEnvironment> caseMatchingEnvironments = caseMatchingEnvironmentService.getCaseMatchingEnvironmentList(caseMatchingEnvironment);
        List<CaseMatchingEducation> caseMatchingEducations = caseMatchingEducationService.getCaseMatchingEducationList(caseMatchingEducation);
        List<CaseEstateParking> caseEstateParkings = caseEstateParkingService.getEstateParkingList(estateParking);
        List<CaseEstateNetwork> caseEstateNetworks = caseEstateNetworkService.getEstateNetworkLists(caseEstateNetwork);
        List<CaseEstateSupply> caseEstateSupplies = caseEstateSupplyService.getCaseEstateSupplyList(caseEstateSupply);

        if (!ObjectUtils.isEmpty(caseEstateParkings)) {
            for (CaseEstateParking caseEstateParking : caseEstateParkings) {
                BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
                BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateParking, queryBasicEstateParking);
                queryBasicEstateParking.setEstateId(0);
                queryBasicEstateParking.setCaseId(caseEstateParking.getId());
                queryBasicEstateParking.setId(null);
                queryBasicEstateParking.setCreator(commonService.thisUserAccount());
                basicEstateParkingService.saveAndUpdateBasicEstateParking(queryBasicEstateParking);
            }
        }
        if (!ObjectUtils.isEmpty(caseEstateNetworks)) {
            for (CaseEstateNetwork caseEstateNetwork1 : caseEstateNetworks) {
                BasicEstateNetwork queryBasicEstateNetwork = new BasicEstateNetwork();
                BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateNetwork1, queryBasicEstateNetwork);
                queryBasicEstateNetwork.setEstateId(0);
                queryBasicEstateNetwork.setCaseId(caseEstateNetwork1.getId());
                queryBasicEstateNetwork.setId(null);
                queryBasicEstateNetwork.setCreator(commonService.thisUserAccount());
                basicEstateNetworkService.saveAndUpdateBasicEstateNetwork(queryBasicEstateNetwork);
            }
        }
        if (!ObjectUtils.isEmpty(caseEstateSupplies)) {
            for (CaseEstateSupply caseEstateSupply1 : caseEstateSupplies) {
                BasicEstateSupply queryBasicEstateSupply = new BasicEstateSupply();
                BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateSupply1, queryBasicEstateSupply);
                queryBasicEstateSupply.setCaseId(caseEstateSupply1.getId());
                queryBasicEstateSupply.setEstateId(0);
                queryBasicEstateSupply.setId(null);
                queryBasicEstateSupply.setCreator(commonService.thisUserAccount());
                basicEstateSupplyService.saveAndUpdateBasicEstateSupply(queryBasicEstateSupply);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingTraffics)) {
            for (CaseMatchingTraffic oo : caseMatchingTraffics) {
                BasicMatchingTraffic queryBasicMatchingTraffic = new BasicMatchingTraffic();
                BeanUtils.copyProperties(oo, queryBasicMatchingTraffic);
                queryBasicMatchingTraffic.setEstateId(0);
                queryBasicMatchingTraffic.setCaseId(oo.getId());
                queryBasicMatchingTraffic.setId(null);
                queryBasicMatchingTraffic.setCreator(commonService.thisUserAccount());
                basicMatchingTrafficService.saveAndUpdateBasicMatchingTraffic(queryBasicMatchingTraffic);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingMedicals)) {
            for (CaseMatchingMedical oo : caseMatchingMedicals) {
                BasicMatchingMedical queryBasicMatchingMedical = new BasicMatchingMedical();
                BeanUtils.copyProperties(oo, queryBasicMatchingMedical);
                queryBasicMatchingMedical.setEstateId(0);
                queryBasicMatchingMedical.setCaseId(oo.getId());
                queryBasicMatchingMedical.setId(null);
                queryBasicMatchingMedical.setCreator(commonService.thisUserAccount());
                basicMatchingMedicalService.saveAndUpdateBasicMatchingMedical(queryBasicMatchingMedical);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingMaterials)) {
            for (CaseMatchingMaterial oo : caseMatchingMaterials) {
                BasicMatchingMaterial queryBasicMatchingMaterial = new BasicMatchingMaterial();
                BeanUtils.copyProperties(oo, queryBasicMatchingMaterial);
                queryBasicMatchingMaterial.setEstateId(0);
                queryBasicMatchingMaterial.setCaseId(oo.getId());
                queryBasicMatchingMaterial.setId(null);
                queryBasicMatchingMaterial.setCreator(commonService.thisUserAccount());
                basicMatchingMaterialService.saveAndUpdateBasicMatchingMaterial(queryBasicMatchingMaterial);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingLeisurePlaces)) {
            for (CaseMatchingLeisurePlace oo : caseMatchingLeisurePlaces) {
                BasicMatchingLeisurePlace queryBasicMatchingLeisurePlace = new BasicMatchingLeisurePlace();
                BeanUtils.copyProperties(oo, queryBasicMatchingLeisurePlace);
                queryBasicMatchingLeisurePlace.setEstateId(0);
                queryBasicMatchingLeisurePlace.setCaseId(oo.getId());
                queryBasicMatchingLeisurePlace.setId(null);
                queryBasicMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
                basicMatchingLeisurePlaceService.saveAndUpdateBasicMatchingLeisurePlace(queryBasicMatchingLeisurePlace);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingFinances)) {
            for (CaseMatchingFinance oo : caseMatchingFinances) {
                BasicMatchingFinance queryBasicMatchingFinance = new BasicMatchingFinance();
                BeanUtils.copyProperties(oo, queryBasicMatchingFinance);
                queryBasicMatchingFinance.setEstateId(0);
                queryBasicMatchingFinance.setCaseId(oo.getId());
                queryBasicMatchingFinance.setId(null);
                queryBasicMatchingFinance.setCreator(commonService.thisUserAccount());
                basicMatchingFinanceService.saveAndUpdateBasicMatchingFinance(queryBasicMatchingFinance);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingEnvironments)) {
            for (CaseMatchingEnvironment oo : caseMatchingEnvironments) {
                BasicMatchingEnvironment queryBasicMatchingEnvironment = new BasicMatchingEnvironment();
                BeanUtils.copyProperties(oo, queryBasicMatchingEnvironment);
                queryBasicMatchingEnvironment.setEstateId(0);
                queryBasicMatchingEnvironment.setCaseId(oo.getId());
                queryBasicMatchingEnvironment.setId(null);
                queryBasicMatchingEnvironment.setCreator(commonService.thisUserAccount());
                basicMatchingEnvironmentService.saveAndUpdateBasicMatchingEnvironment(queryBasicMatchingEnvironment);
            }
        }
        if (!ObjectUtils.isEmpty(caseMatchingEducations)) {
            for (CaseMatchingEducation oo : caseMatchingEducations) {
                BasicMatchingEducation queryBasicMatchingEducation = new BasicMatchingEducation();
                BeanUtils.copyProperties(oo, queryBasicMatchingEducation);
                queryBasicMatchingEducation.setEstateId(0);
                queryBasicMatchingEducation.setCaseId(oo.getId());
                queryBasicMatchingEducation.setId(null);
                queryBasicMatchingEducation.setCreator(commonService.thisUserAccount());
                basicMatchingEducationService.saveAndUpdateBasicMatchingEducation(queryBasicMatchingEducation);
            }
        }
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        objectMap.put(CaseEstate.class.getSimpleName(), caseEstateService.getCaseEstateVo(caseEstateService.getCaseEstateById(caseEstateId)));
        CaseEstateLandState query = new CaseEstateLandState();
        query.setEstateId(caseEstateId);
        List<CaseEstateLandState> landStateList = caseEstateLandStateService.getCaseEstateLandStateList(query);
        Ordering<CaseEstateLandState> ordering = Ordering.from(new Comparator<CaseEstateLandState>() {
            @Override
            public int compare(CaseEstateLandState o1, CaseEstateLandState o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(landStateList)) {
            Collections.sort(landStateList, ordering);
            objectMap.put(CaseEstateLandState.class.getSimpleName(), caseEstateLandStateService.getCaseEstateLandStateVo(landStateList.get(0)));
        }
        return objectMap;
    }

    /**
     * 将 CaseHouse 下的子类 转移到 BasicHouse下的子类中去 (用做过程数据)
     *
     * @param caseHouseId
     * @throws Exception
     */
    public Map<String, Object> appWriteHouse(Integer caseHouseId) throws Exception {
        if (caseHouseId == null) {
            throw new Exception("null ponit");
        }
        CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
        caseHouseTradingLease.setHouseId(caseHouseId);
        CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
        caseHouseTradingSell.setHouseId(caseHouseId);
        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
        caseHouseRoom.setHouseId(caseHouseId);

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
        if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)) {
            for (CaseHouseTradingSellVo oo : caseHouseTradingSellVos) {
                BasicHouseTradingSell querySell = new BasicHouseTradingSell();
                BeanUtils.copyProperties(oo, querySell);
                querySell.setCaseTradingSellId(oo.getId());
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
                queryLease.setCaseTradingLeaseId(oo.getId());
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
                queryRoom.setCaseRoomId(oo.getId());
                queryRoom.setId(null);
                queryRoom.setHouseId(0);
                queryRoom.setCreator(commonService.thisUserAccount());
                basicHouseRoomService.saveAndUpdateBasicHouseRoom(queryRoom);
                CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                List<CaseHouseRoomDecorate> caseHouseRoomDecorateList = caseHouseRoomDecorateService.getCaseHouseRoomDecorateList(caseHouseRoomDecorate);
                if (!ObjectUtils.isEmpty(caseHouseRoomDecorateList)) {
                    for (CaseHouseRoomDecorate po : caseHouseRoomDecorateList) {
                        BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                        BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                        basicHouseRoomDecorate.setRoomId(queryRoom.getId());
                        basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
                    }
                }
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseEquipments)) {
            for (CaseHouseEquipment oo : caseHouseEquipments) {
                BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                BeanUtils.copyProperties(oo, po);
                po.setCaseCorollaryEquipmentId(oo.getId());
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseFaceStreets)) {
            for (CaseHouseFaceStreet oo : caseHouseFaceStreets) {
                BasicHouseFaceStreet po = new BasicHouseFaceStreet();
                BeanUtils.copyProperties(oo, po);
                po.setCaseFaceStreetId(oo.getId());
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
                po.setCaseIntelligentId(oo.getId());
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
                po.setCaseHouseWaterId(oo.getId());
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseWaterService.saveAndUpdateBasicHouseWater(po);
            }
        }
        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
            for (CaseHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                BeanUtils.copyProperties(oo, po);
                po.setCaseCorollaryEquipmentId(oo.getId());
                po.setId(null);
                po.setHouseId(0);
                po.setCreator(commonService.thisUserAccount());
                basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po);
            }
        }
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        objectMap.put(CaseHouse.class.getSimpleName(), caseHouseService.getCaseHouseVo(caseHouseService.getCaseHouseById(caseHouseId)));
        CaseHouseTrading query = new CaseHouseTrading();
        query.setHouseId(caseHouseId);
        List<CaseHouseTrading> caseHouseTradingList = caseHouseTradingService.caseHouseTradingLists(query);
        Ordering<CaseHouseTrading> ordering = Ordering.from(new Comparator<CaseHouseTrading>() {
            @Override
            public int compare(CaseHouseTrading o1, CaseHouseTrading o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        if (!ObjectUtils.isEmpty(caseHouseTradingList)) {
            Collections.sort(caseHouseTradingList, ordering);
            objectMap.put(CaseHouseTrading.class.getSimpleName(), caseHouseTradingService.getCaseHouseTradingVo(caseHouseTradingList.get(0)));
        }
        return objectMap;
    }
}

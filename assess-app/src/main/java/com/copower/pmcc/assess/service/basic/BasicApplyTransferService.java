package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDamagedDegreeDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2019-1-23.
 */
@Service
public class BasicApplyTransferService {
    @Autowired
    private BasicEstateNetworkService basicEstateNetworkService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private BasicBuildingMaintenanceService basicBuildingMaintenanceService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicBuildingSurfaceService basicBuildingSurfaceService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicUnitElevatorService basicUnitElevatorService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
    @Autowired
    private BasicHouseTradingSellService basicHouseTradingSellService;
    @Autowired
    private BasicHouseWaterDrainService basicHouseWaterDrainService;
    @Autowired
    private BasicHouseTradingLeaseService basicHouseTradingLeaseService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;
    @Autowired
    private BasicHouseFaceStreetService basicHouseFaceStreetService;
    @Autowired
    private BasicHouseIntelligentService basicHouseIntelligentService;
    @Autowired
    private BasicHouseWaterService basicHouseWaterService;
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
    private BasicEstateSupplyService basicEstateSupplyService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicMatchingTrafficService basicMatchingTrafficService;
    @Autowired
    private BasicMatchingMedicalService basicMatchingMedicalService;
    @Autowired
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    @Autowired
    private BasicMatchingMaterialService basicMatchingMaterialService;
    @Autowired
    private BasicMatchingFinanceService basicMatchingFinanceService;
    @Autowired
    private BasicMatchingEnvironmentService basicMatchingEnvironmentService;
    @Autowired
    private BasicMatchingEducationService basicMatchingEducationService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private BasicHouseDamagedDegreeDao basicHouseDamagedDegreeDao;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private CommonService commonService;

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

            List<SysAttachmentDto> sysAttachmentDtoList = null;
            SysAttachmentDto query = new SysAttachmentDto();
            query.setTableId(basicEstateOld.getId());
            query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(basicEstateNew.getId());
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                }
            }

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

        BasicEstateNetwork queryBasicEstateNetworkoOld = new BasicEstateNetwork();
        BasicEstateParking queryBasicEstateParkingOld = new BasicEstateParking();
        BasicEstateSupply queryBasicEstateSupplyOld = new BasicEstateSupply();
        BasicMatchingEducation queryBasicMatchingEducationOld = new BasicMatchingEducation();
        BasicMatchingEnvironment queryBasicMatchingEnvironmentOld = new BasicMatchingEnvironment();
        BasicMatchingFinance queryBasicMatchingFinanceOld = new BasicMatchingFinance();
        BasicMatchingLeisurePlace queryBasicMatchingLeisurePlaceOld = new BasicMatchingLeisurePlace();
        BasicMatchingMaterial queryBasicMatchingMaterialOld = new BasicMatchingMaterial();
        BasicMatchingMedical queryBasicMatchingMedicalOld = new BasicMatchingMedical();
        BasicMatchingTraffic queryBasicMatchingTrafficOld = new BasicMatchingTraffic();

        if (basicEstateOld.getId() != null) {
            queryBasicEstateNetworkoOld.setEstateId(basicEstateOld.getId());
            queryBasicEstateParkingOld.setEstateId(basicEstateOld.getId());
            queryBasicEstateSupplyOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingTrafficOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingMedicalOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingMaterialOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingLeisurePlaceOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingFinanceOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingEnvironmentOld.setEstateId(basicEstateOld.getId());
            queryBasicMatchingEducationOld.setEstateId(basicEstateOld.getId());
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

        basicEstateNetworkList = basicEstateNetworkService.basicEstateNetworkList(queryBasicEstateNetworkoOld);
        basicEstateParkingList = basicEstateParkingService.basicEstateParkingList(queryBasicEstateParkingOld);
        basicEstateSupplyList = basicEstateSupplyService.basicEstateSupplyList(queryBasicEstateSupplyOld);

        basicMatchingEducationList = basicMatchingEducationService.basicMatchingEducationList(queryBasicMatchingEducationOld);
        basicMatchingEnvironmentList = basicMatchingEnvironmentService.basicMatchingEnvironmentList(queryBasicMatchingEnvironmentOld);
        basicMatchingFinanceList = basicMatchingFinanceService.basicMatchingFinanceList(queryBasicMatchingFinanceOld);
        basicMatchingLeisurePlaceList = basicMatchingLeisurePlaceService.basicMatchingLeisurePlaceList(queryBasicMatchingLeisurePlaceOld);
        basicMatchingMaterialList = basicMatchingMaterialService.basicMatchingMaterialList(queryBasicMatchingMaterialOld);
        basicMatchingMedicalList = basicMatchingMedicalService.basicMatchingMedicalList(queryBasicMatchingMedicalOld);
        basicMatchingTrafficList = basicMatchingTrafficService.basicMatchingTrafficList(queryBasicMatchingTrafficOld);
        if (basicEstateNew != null) {
            if (basicEstateNew.getId() != null) {
                this.copyBasicNetwork(basicEstateNetworkList, basicEstateNew);
                this.copyBasicParking(basicEstateParkingList, basicEstateNew);
                this.copyBasicSupply(basicEstateSupplyList, basicEstateNew);
                this.copyBasicTraffic(basicMatchingTrafficList, basicEstateNew);
                this.copyBasicMedical(basicMatchingMedicalList, basicEstateNew);
                this.copyBasicMaterial(basicMatchingMaterialList, basicEstateNew);
                this.copyBasicLeisurePlace(basicMatchingLeisurePlaceList, basicEstateNew);
                this.copyBasicFinance(basicMatchingFinanceList, basicEstateNew);
                this.copyBasicEnvironment(basicMatchingEnvironmentList, basicEstateNew);
                this.copyBasicEducation(basicMatchingEducationList, basicEstateNew);
            }
        }
        return basicEstateNew;
    }

    private void copyBasicTraffic(List<BasicMatchingTraffic> basicMatchingTrafficList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingTrafficList)) {
            for (BasicMatchingTraffic oo : basicMatchingTrafficList) {
                BasicMatchingTraffic basicMatchingTraffic = new BasicMatchingTraffic();
                BeanUtils.copyProperties(oo, basicMatchingTraffic);
                basicMatchingTraffic.setId(null);
                basicMatchingTraffic.setGmtCreated(null);
                basicMatchingTraffic.setGmtModified(null);
                basicMatchingTraffic.setEstateId(basicEstateNew.getId());
                basicMatchingTrafficService.saveAndUpdateBasicMatchingTraffic(basicMatchingTraffic);
            }
        }
    }

    private void copyBasicMedical(List<BasicMatchingMedical> basicMatchingMedicalList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingMedicalList)) {
            for (BasicMatchingMedical oo : basicMatchingMedicalList) {
                BasicMatchingMedical basicMatchingMedical = new BasicMatchingMedical();
                BeanUtils.copyProperties(oo, basicMatchingMedical);
                basicMatchingMedical.setId(null);
                basicMatchingMedical.setGmtCreated(null);
                basicMatchingMedical.setGmtModified(null);
                basicMatchingMedical.setEstateId(basicEstateNew.getId());
                basicMatchingMedicalService.saveAndUpdateBasicMatchingMedical(basicMatchingMedical);
            }
        }
    }

    private void copyBasicMaterial(List<BasicMatchingMaterial> basicMatchingMaterialList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingMaterialList)) {
            for (BasicMatchingMaterial oo : basicMatchingMaterialList) {
                BasicMatchingMaterial basicMatchingMaterial = new BasicMatchingMaterial();
                BeanUtils.copyProperties(oo, basicMatchingMaterial);
                basicMatchingMaterial.setId(null);
                basicMatchingMaterial.setGmtCreated(null);
                basicMatchingMaterial.setGmtModified(null);
                basicMatchingMaterial.setEstateId(basicEstateNew.getId());
                basicMatchingMaterialService.saveAndUpdateBasicMatchingMaterial(basicMatchingMaterial);
            }
        }
    }

    private void copyBasicLeisurePlace(List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingLeisurePlaceList)) {
            for (BasicMatchingLeisurePlace oo : basicMatchingLeisurePlaceList) {
                BasicMatchingLeisurePlace basicMatchingLeisurePlace = new BasicMatchingLeisurePlace();
                BeanUtils.copyProperties(oo, basicMatchingLeisurePlace);
                basicMatchingLeisurePlace.setId(null);
                basicMatchingLeisurePlace.setGmtCreated(null);
                basicMatchingLeisurePlace.setGmtModified(null);
                basicMatchingLeisurePlace.setEstateId(basicEstateNew.getId());
                basicMatchingLeisurePlaceService.saveAndUpdateBasicMatchingLeisurePlace(basicMatchingLeisurePlace);
            }
        }
    }

    private void copyBasicFinance(List<BasicMatchingFinance> basicMatchingFinanceList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingFinanceList)) {
            for (BasicMatchingFinance oo : basicMatchingFinanceList) {
                BasicMatchingFinance basicMatchingFinance = new BasicMatchingFinance();
                BeanUtils.copyProperties(oo, basicMatchingFinance);
                basicMatchingFinance.setId(null);
                basicMatchingFinance.setGmtCreated(null);
                basicMatchingFinance.setGmtModified(null);
                basicMatchingFinance.setEstateId(basicEstateNew.getId());
                basicMatchingFinanceService.saveAndUpdateBasicMatchingFinance(basicMatchingFinance);
            }
        }
    }

    private void copyBasicEnvironment(List<BasicMatchingEnvironment> basicMatchingEnvironmentList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingEnvironmentList)) {
            for (BasicMatchingEnvironment oo : basicMatchingEnvironmentList) {
                BasicMatchingEnvironment basicMatchingEnvironment = new BasicMatchingEnvironment();
                BeanUtils.copyProperties(oo, basicMatchingEnvironment);
                basicMatchingEnvironment.setId(null);
                basicMatchingEnvironment.setGmtCreated(null);
                basicMatchingEnvironment.setGmtModified(null);
                basicMatchingEnvironment.setEstateId(basicEstateNew.getId());
                basicMatchingEnvironmentService.saveAndUpdateBasicMatchingEnvironment(basicMatchingEnvironment);
            }
        }
    }

    private void copyBasicEducation(List<BasicMatchingEducation> basicMatchingEducationList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicMatchingEducationList)) {
            for (BasicMatchingEducation oo : basicMatchingEducationList) {
                BasicMatchingEducation basicMatchingEducation = new BasicMatchingEducation();
                BeanUtils.copyProperties(oo, basicMatchingEducation);
                basicMatchingEducation.setId(null);
                basicMatchingEducation.setGmtCreated(null);
                basicMatchingEducation.setGmtModified(null);
                basicMatchingEducation.setEstateId(basicEstateNew.getId());
                basicMatchingEducationService.saveAndUpdateBasicMatchingEducation(basicMatchingEducation);
            }
        }
    }

    private void copyBasicNetwork(List<BasicEstateNetwork> basicEstateNetworkList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateNetworkList)) {
            for (BasicEstateNetwork oo : basicEstateNetworkList) {
                BasicEstateNetwork basicEstateNetwork = new BasicEstateNetwork();
                BeanUtils.copyProperties(oo, basicEstateNetwork);
                basicEstateNetwork.setId(null);
                basicEstateNetwork.setGmtCreated(null);
                basicEstateNetwork.setGmtModified(null);
                basicEstateNetwork.setEstateId(basicEstateNew.getId());
                basicEstateNetworkService.saveAndUpdateBasicEstateNetwork(basicEstateNetwork);
            }
        }
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

    private void copyBasicSupply(List<BasicEstateSupply> basicEstateSupplyList, BasicEstate basicEstateNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicEstateSupplyList)) {
            for (BasicEstateSupply oo : basicEstateSupplyList) {
                BasicEstateSupply basicEstateSupply = new BasicEstateSupply();
                BeanUtils.copyProperties(oo, basicEstateSupply);
                basicEstateSupply.setId(null);
                basicEstateSupply.setGmtCreated(null);
                basicEstateSupply.setGmtModified(null);
                basicEstateSupply.setEstateId(basicEstateNew.getId());
                basicEstateSupplyService.saveAndUpdateBasicEstateSupply(basicEstateSupply);
            }
        }
    }

    /**
     * 标记回写
     *
     * @param typeEnum
     * @param dataIdOld
     * @param dataIdNew
     * @throws Exception
     */
    private void copyBasicTagging(EstateTaggingTypeEnum typeEnum, Integer dataIdOld, Integer dataIdNew) throws Exception {
        BasicEstateTagging query = new BasicEstateTagging();
        query.setApplyId(dataIdOld);
        query.setType(typeEnum.getKey());
        List<BasicEstateTagging> basicEstateTaggingList = basicEstateTaggingService.getBasicEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(basicEstateTaggingList)) {
            for (BasicEstateTagging oo : basicEstateTaggingList) {
                BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
                BeanUtils.copyProperties(oo, basicEstateTagging);
                basicEstateTagging.setId(null);
                basicEstateTagging.setGmtCreated(null);
                basicEstateTagging.setGmtModified(null);
                basicEstateTagging.setApplyId(dataIdNew);
                basicEstateTagging.setType(typeEnum.getKey());
                basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
            }
        }
    }

    /**
     * 主楼栋 复制
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

            queryFunction.setBuildingId(buildingOld.getId());
            queryMaintenance.setBuildingId(buildingOld.getId());
            querySurface.setBuildingId(buildingOld.getId());
            queryOutfit.setBuildingId(buildingOld.getId());
            queryFile.setTableId(buildingOld.getId());
            queryFile.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));

            sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
            outfitList = basicBuildingOutfitService.basicBuildingOutfitList(queryOutfit);
            surfaceList = basicBuildingSurfaceService.basicBuildingSurfaceList(querySurface);
            maintenanceList = basicBuildingMaintenanceService.basicBuildingMaintenanceList(queryMaintenance);
            functionList = basicBuildingFunctionService.basicBuildingFunctionList(queryFunction);

            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(basicBuildingNew.getId());
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                }
            }

            this.copyBasicBuildingOutfit(outfitList, basicBuildingNew);
            this.copyBasicBuildingMaintenance(maintenanceList, basicBuildingNew);
            this.copyBasicBuildingSurface(surfaceList, basicBuildingNew);
            this.copyBasicBuildingFunction(functionList, basicBuildingNew);
        }
        return basicBuildingNew;
    }


    private void copyBasicBuildingOutfit(List<BasicBuildingOutfit> list, BasicBuilding basicBuildingNew) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingOutfit oo : list) {
                BasicBuildingOutfit basicBuildingOutfit = new BasicBuildingOutfit();
                BeanUtils.copyProperties(oo, basicBuildingOutfit);
                basicBuildingOutfit.setId(null);
                basicBuildingOutfit.setGmtCreated(null);
                basicBuildingOutfit.setGmtModified(null);
                basicBuildingOutfit.setBuildingId(basicBuildingNew.getId());
                basicBuildingOutfitService.saveAndUpdateBasicBuildingOutfit(basicBuildingOutfit);
            }
        }
    }

    private void copyBasicBuildingMaintenance(List<BasicBuildingMaintenance> list, BasicBuilding basicBuildingNew) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingMaintenance oo : list) {
                BasicBuildingMaintenance basicBuildingMaintenance = new BasicBuildingMaintenance();
                BeanUtils.copyProperties(oo, basicBuildingMaintenance);
                basicBuildingMaintenance.setId(null);
                basicBuildingMaintenance.setGmtCreated(null);
                basicBuildingMaintenance.setGmtModified(null);
                basicBuildingMaintenance.setBuildingId(basicBuildingNew.getId());
                basicBuildingMaintenanceService.saveAndUpdateBasicBuildingMaintenance(basicBuildingMaintenance);
            }
        }
    }

    private void copyBasicBuildingSurface(List<BasicBuildingSurface> list, BasicBuilding basicBuildingNew) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingSurface oo : list) {
                BasicBuildingSurface basicBuildingSurface = new BasicBuildingSurface();
                BeanUtils.copyProperties(oo, basicBuildingSurface);
                basicBuildingSurface.setId(null);
                basicBuildingSurface.setGmtCreated(null);
                basicBuildingSurface.setGmtModified(null);
                basicBuildingSurface.setBuildingId(basicBuildingNew.getId());
                basicBuildingSurfaceService.saveAndUpdateBasicBuildingSurface(basicBuildingSurface);
            }
        }
    }

    private void copyBasicBuildingFunction(List<BasicBuildingFunction> list, BasicBuilding basicBuildingNew) throws Exception {
        if (!ObjectUtils.isEmpty(list)) {
            for (BasicBuildingFunction oo : list) {
                BasicBuildingFunction basicBuildingFunction = new BasicBuildingFunction();
                BeanUtils.copyProperties(oo, basicBuildingFunction);
                basicBuildingFunction.setId(null);
                basicBuildingFunction.setGmtCreated(null);
                basicBuildingFunction.setGmtModified(null);
                basicBuildingFunction.setBuildingId(basicBuildingNew.getId());
                basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(basicBuildingFunction);
            }
        }
    }

    /**
     * 单元 复制
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
        }
        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
        BasicUnitElevator queryBasicUnitElevator = new BasicUnitElevator();
        BasicUnitDecorate queryBasicUnitDecorate = new BasicUnitDecorate();
        if (unitOld != null) {
            if (unitOld.getId() != null) {
                queryBasicUnitHuxing.setUnitId(unitOld.getId());
                queryBasicUnitElevator.setUnitId(unitOld.getId());
                queryBasicUnitDecorate.setUnitId(unitOld.getId());
            }
        }
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(queryBasicUnitHuxing);
        List<BasicUnitElevator> basicUnitElevatorList = basicUnitElevatorService.basicUnitElevatorList(queryBasicUnitElevator);
        List<BasicUnitDecorate> basicUnitDecorateList = basicUnitDecorateService.basicUnitDecorateList(queryBasicUnitDecorate);
        if (basicUnitNew != null) {
            if (basicUnitNew.getId() != null) {
                this.copyBasicDecorate(basicUnitDecorateList, basicUnitNew);
                this.copyBasicHuxing(basicUnitHuxingList, basicUnitNew);
                this.copyBasicElevator(basicUnitElevatorList, basicUnitNew);
            }
        }
        return basicUnitNew;
    }

    public void copyBasicDecorate(List<BasicUnitDecorate> basicUnitDecorateList, BasicUnit basicUnitNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitDecorateList)) {
            for (BasicUnitDecorate oo : basicUnitDecorateList) {
                BasicUnitDecorate basicUnitDecorate = new BasicUnitDecorate();
                BeanUtils.copyProperties(oo, basicUnitDecorate);
                basicUnitDecorate.setId(null);
                basicUnitDecorate.setGmtCreated(null);
                basicUnitDecorate.setGmtModified(null);
                basicUnitDecorate.setUnitId(basicUnitNew.getId());
                basicUnitDecorateService.saveAndUpdateBasicUnitDecorate(basicUnitDecorate);
            }
        }
    }

    public void copyBasicHuxing(List<BasicUnitHuxing> basicUnitHuxingList, BasicUnit basicUnitNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing oo : basicUnitHuxingList) {
                BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                BeanUtils.copyProperties(oo, basicUnitHuxing);
                basicUnitHuxing.setId(null);
                basicUnitHuxing.setGmtCreated(null);
                basicUnitHuxing.setGmtModified(null);
                basicUnitHuxing.setUnitId(basicUnitNew.getId());
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing);

                List<SysAttachmentDto> sysAttachmentDtoList = null;
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(basicUnitHuxing.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
        }
    }

    public void copyBasicElevator(List<BasicUnitElevator> basicUnitElevatorList, BasicUnit basicUnitNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicUnitElevatorList)) {
            for (BasicUnitElevator oo : basicUnitElevatorList) {
                BasicUnitElevator basicUnitElevator = new BasicUnitElevator();
                BeanUtils.copyProperties(oo, basicUnitElevator);
                basicUnitElevator.setId(null);
                basicUnitElevator.setGmtCreated(null);
                basicUnitElevator.setGmtModified(null);
                basicUnitElevator.setUnitId(basicUnitNew.getId());
                basicUnitElevatorService.saveAndUpdateBasicUnitElevator(basicUnitElevator);
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
        List<BasicHouseDamagedDegree> basicHouseDamagedDegreeList = null;

        BasicHouseTradingSell querySell = new BasicHouseTradingSell();
        BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseWater queryBasicHouseWater = new BasicHouseWater();
        BasicHouseIntelligent queryBasicHouseIntelligent = new BasicHouseIntelligent();
        BasicHouseFaceStreet queryBasicHouseFaceStreet = new BasicHouseFaceStreet();
        BasicHouseEquipment queryBasicHouseEquipment = new BasicHouseEquipment();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        BasicHouseWaterDrain queryWaterDrain = new BasicHouseWaterDrain();
        BasicHouseDamagedDegree queryHouseDamagedDegree = new BasicHouseDamagedDegree();
        SysAttachmentDto queryFile = new SysAttachmentDto();

        if (houseOld != null) {
            if (houseOld.getId() != null) {
                queryLease.setHouseId(houseOld.getId());
                querySell.setHouseId(houseOld.getId());
                queryRoom.setHouseId(houseOld.getId());
                queryBasicHouseWater.setHouseId(houseOld.getId());
                queryBasicHouseIntelligent.setHouseId(houseOld.getId());
                queryBasicHouseFaceStreet.setHouseId(houseOld.getId());
                queryBasicHouseEquipment.setHouseId(houseOld.getId());
                queryBasicHouseCorollaryEquipment.setHouseId(houseOld.getId());
                queryFile.setTableId(houseOld.getId());
                queryWaterDrain.setHouseId(houseOld.getId());
                queryHouseDamagedDegree.setHouseId(houseOld.getId());
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
        basicHouseDamagedDegreeList = basicHouseDamagedDegreeDao.getDamagedDegreeList(queryHouseDamagedDegree);

        if (basicHouseNew != null) {
            if (basicHouseNew.getId() != null) {
                this.copyBasicSellAndLease(basicHouseTradingSellList, basicHouseTradingLeaseList, basicHouseNew);
                this.copyBasicHouseRoom(basicHouseRoomList, basicHouseNew);
                this.copyBasicWater(basicHouseWaterList, basicHouseNew);
                this.copyBasicWaterDrain(basicHouseWaterDrainList, basicHouseNew);
                this.copyBasicIntelligent(basicHouseIntelligentList, basicHouseNew);
                this.copyBasicFaceStreet(basicHouseFaceStreetList, basicHouseNew);
                this.copyBasicEquipment(basicHouseEquipmentList, basicHouseNew);
                this.copyBasicCorollaryEquipment(basicHouseCorollaryEquipmentList, basicHouseNew);
                this.copyBasicDamagedDegree(basicHouseDamagedDegreeList, basicHouseNew);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        SysAttachmentDto attachmentDto = new SysAttachmentDto();
                        attachmentDto.setTableId(basicHouseNew.getId());
                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                        baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                    }
                }
            }
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

                    List<SysAttachmentDto> sysAttachmentDtoList = null;
                    SysAttachmentDto queryFile = new SysAttachmentDto();
                    queryFile.setTableId(oo.getId());
                    queryFile.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                            SysAttachmentDto attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(basicHouseCorollaryEquipment.getId());
                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                        }
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicEquipment(List<BasicHouseEquipment> basicHouseEquipmentList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseEquipmentList)) {
            basicHouseEquipmentList.forEach(oo -> {
                try {
                    BasicHouseEquipment basicHouseEquipment = new BasicHouseEquipment();
                    BeanUtils.copyProperties(oo, basicHouseEquipment);
                    basicHouseEquipment.setId(null);
                    basicHouseEquipment.setGmtCreated(null);
                    basicHouseEquipment.setGmtModified(null);
                    basicHouseEquipment.setHouseId(basicHouseNew.getId());
                    basicHouseEquipmentService.saveAndUpdateBasicHouseEquipment(basicHouseEquipment);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicFaceStreet(List<BasicHouseFaceStreet> basicHouseFaceStreetList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseFaceStreetList)) {
            basicHouseFaceStreetList.forEach(oo -> {
                try {
                    BasicHouseFaceStreet basicHouseFaceStreet = new BasicHouseFaceStreet();
                    BeanUtils.copyProperties(oo, basicHouseFaceStreet);
                    basicHouseFaceStreet.setId(null);
                    basicHouseFaceStreet.setGmtCreated(null);
                    basicHouseFaceStreet.setGmtModified(null);
                    basicHouseFaceStreet.setHouseId(basicHouseNew.getId());
                    basicHouseFaceStreetService.saveAndUpdateBasicHouseFaceStreet(basicHouseFaceStreet);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicIntelligent(List<BasicHouseIntelligent> basicHouseIntelligentList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseIntelligentList)) {
            basicHouseIntelligentList.forEach(oo -> {
                try {
                    BasicHouseIntelligent basicHouseIntelligent = new BasicHouseIntelligent();
                    BeanUtils.copyProperties(oo, basicHouseIntelligent);
                    basicHouseIntelligent.setId(null);
                    basicHouseIntelligent.setGmtCreated(null);
                    basicHouseIntelligent.setGmtModified(null);
                    basicHouseIntelligent.setHouseId(basicHouseNew.getId());
                    basicHouseIntelligentService.saveAndUpdateBasicHouseIntelligent(basicHouseIntelligent);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicWater(List<BasicHouseWater> basicHouseWaterList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseWaterList)) {
            basicHouseWaterList.forEach(oo -> {
                try {
                    BasicHouseWater basicHouseWater = new BasicHouseWater();
                    BeanUtils.copyProperties(oo, basicHouseWater);
                    basicHouseWater.setId(null);
                    basicHouseWater.setGmtCreated(null);
                    basicHouseWater.setGmtModified(null);
                    basicHouseWater.setHouseId(basicHouseNew.getId());
                    basicHouseWaterService.saveAndUpdateBasicHouseWater(basicHouseWater);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }

    private void copyBasicWaterDrain(List<BasicHouseWaterDrain> basicHouseWaterDrainList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseWaterDrainList)) {
            for (BasicHouseWaterDrain houseWaterDrain : basicHouseWaterDrainList) {
                BasicHouseWaterDrain basicHouseWaterDrain = new BasicHouseWaterDrain();
                BeanUtils.copyProperties(houseWaterDrain, basicHouseWaterDrain);
                basicHouseWaterDrain.setId(null);
                basicHouseWaterDrain.setGmtCreated(null);
                basicHouseWaterDrain.setGmtModified(null);
                basicHouseWaterDrain.setHouseId(basicHouseNew.getId());
                basicHouseWaterDrainService.saveAndUpdateBasicHouseWaterDrain(basicHouseWaterDrain);
            }
        }
    }

    private void copyBasicSellAndLease(List<BasicHouseTradingSell> basicHouseTradingSellList, List<BasicHouseTradingLease> basicHouseTradingLeaseList, BasicHouse basicHouseNew) throws Exception {
        if (!ObjectUtils.isEmpty(basicHouseTradingSellList)) {
            for (BasicHouseTradingSell oo : basicHouseTradingSellList) {
                BasicHouseTradingSell basicHouseTradingSell = new BasicHouseTradingSell();
                BeanUtils.copyProperties(oo, basicHouseTradingSell);
                basicHouseTradingSell.setId(null);
                basicHouseTradingSell.setGmtCreated(null);
                basicHouseTradingSell.setGmtModified(null);
                basicHouseTradingSell.setHouseId(basicHouseNew.getId());
                basicHouseTradingSellService.saveAndUpdateBasicHouseTradingSell(basicHouseTradingSell);
            }
        }

        if (!ObjectUtils.isEmpty(basicHouseTradingLeaseList)) {
            for (BasicHouseTradingLease oo : basicHouseTradingLeaseList) {
                BasicHouseTradingLease basicHouseTradingLease = null;
                basicHouseTradingLease = new BasicHouseTradingLease();
                BeanUtils.copyProperties(oo, basicHouseTradingLease);
                basicHouseTradingLease.setId(null);
                basicHouseTradingLease.setGmtCreated(null);
                basicHouseTradingLease.setGmtModified(null);
                basicHouseTradingLease.setHouseId(basicHouseNew.getId());
                basicHouseTradingLeaseService.saveAndUpdateBasicHouseTradingLease(basicHouseTradingLease);
            }
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
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
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

            //处理楼盘
            BasicEstate basicEstateNew = this.copyBasicEstate(basicApplycopy.getId(), basicEstateOld, basicEstateLandStateOld);
            Integer estateId = basicEstateNew.getId();
            //回写版块到基础数据中
            if (basicApply.getWriteBackBlockFlag() == Boolean.TRUE) {
                DataBlock dataBlock = new DataBlock();
                dataBlock.setProvince(basicEstateNew.getProvince());
                dataBlock.setCity(basicEstateNew.getCity());
                dataBlock.setDistrict(basicEstateNew.getDistrict());
                dataBlock.setName(basicEstateNew.getName());
                dataBlock.setCreator(basicEstateNew.getCreator());
                dataBlockService.saveAndUpdateDataBlock(dataBlock);
            }
            this.copyBasicTagging(EstateTaggingTypeEnum.ESTATE, basicApply.getId(), basicApplycopy.getId());

            //处理楼栋
            BasicBuilding basicBuilding = this.copyBasicBuilding(basicApplycopy.getId(), basicBuildingOld, estateId);
            Integer buildingMainId = basicBuilding.getId();
            this.copyBasicTagging(EstateTaggingTypeEnum.BUILDING, basicApply.getId(), basicApplycopy.getId());

            //处理单元
            BasicUnit basicUnit = this.copyBasicUnit(basicApplycopy.getId(), basicUnitOld, buildingMainId);
            Integer unitId = basicUnit.getId();
            this.copyBasicTagging(EstateTaggingTypeEnum.UNIT, basicApply.getId(), basicApplycopy.getId());

            //处理房屋
            BasicHouse basicHouse = this.copyBasicHouse(basicApplycopy.getId(), basicHouseOld, basicTradingOld, unitId);
            this.copyBasicTagging(EstateTaggingTypeEnum.HOUSE, basicApply.getId(), basicApplycopy.getId());

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
        targetBasicApply.setCreator(commonService.thisUserAccount());
        targetBasicApply.setGmtCreated(new Date());
        targetBasicApply.setGmtModified(new Date());
        basicApplyService.saveBasicApply(targetBasicApply);

        //取数据
        BasicEstate basicEstateOld = publicBasicService.getBasicEstateByAppId(sourceBasicApply.getId());
        BasicEstateLandState basicEstateLandStateOld = publicBasicService.getEstateLandStateByAppId(sourceBasicApply.getId());
        BasicBuilding basicBuildingOld = publicBasicService.getBasicBuildingByAppId(sourceBasicApply.getId());
        BasicUnit basicUnitOld = publicBasicService.getBasicUnitByAppId(sourceBasicApply.getId());
        BasicHouse basicHouseOld = publicBasicService.getBasicHouseVoByAppId(sourceBasicApply.getId());
        BasicHouseTrading basicTradingOld = publicBasicService.getBasicHouseTradingByAppId(sourceBasicApply.getId());

        //处理楼盘
        BasicEstate basicEstateNew = this.copyBasicEstate(targetBasicApply.getId(), basicEstateOld, basicEstateLandStateOld);
        Integer estateId = basicEstateNew.getId();
        this.copyBasicTagging(EstateTaggingTypeEnum.ESTATE, sourceBasicApply.getId(), targetBasicApply.getId());

        //处理楼栋
        BasicBuilding basicBuilding = this.copyBasicBuilding(targetBasicApply.getId(), basicBuildingOld, estateId);
        Integer buildingMainId = basicBuilding.getId();
        this.copyBasicTagging(EstateTaggingTypeEnum.BUILDING, sourceBasicApply.getId(), targetBasicApply.getId());

        //处理单元
        BasicUnit basicUnit = this.copyBasicUnit(targetBasicApply.getId(), basicUnitOld, buildingMainId);
        Integer unitId = basicUnit.getId();
        this.copyBasicTagging(EstateTaggingTypeEnum.UNIT, sourceBasicApply.getId(), targetBasicApply.getId());

        //处理房屋
        BasicHouse basicHouse = this.copyBasicHouse(targetBasicApply.getId(), basicHouseOld, basicTradingOld, unitId);
        this.copyBasicTagging(EstateTaggingTypeEnum.HOUSE, sourceBasicApply.getId(), targetBasicApply.getId());

        return targetBasicApply;
    }
}

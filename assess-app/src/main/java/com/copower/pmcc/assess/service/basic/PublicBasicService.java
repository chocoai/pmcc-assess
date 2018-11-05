package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Ordering;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Comparator;
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
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicBuildingMainService basicBuildingMainService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
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
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private CaseEstate flowWriteCaseEstate(BasicEstate basicEstate, BasicEstateLandState basicEstateLandState) throws Exception {
        CaseEstate caseEstate = null;
        CaseEstateLandState caseEstateLandState = null;
        List<SysAttachmentDto> sysAttachmentDtoList = null;
        //-----------------------------------||---------------------------
        if (basicEstate != null) {
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
            //升级版本 以及更改某些数据  或者 新添数据
            if (caseEstate != null) {
                caseEstateService.upgradeVersion(caseEstate);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        sysAttachmentDto.setTableId(caseEstate.getId());
                        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
                    }
                }
            }
        }
        if (caseEstate != null) {
            if (caseEstate.getId() != null) {
                if (basicEstateLandState.getCaseEstateLandStateId() != null) {
                    caseEstateLandState = caseEstateLandStateService.getCaseEstateLandStateById(basicEstateLandState.getCaseEstateLandStateId());
                    if (caseEstateLandState != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicEstateLandState, caseEstateLandState);
                        caseEstateLandState.setId(basicEstateLandState.getCaseEstateLandStateId());
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
        return caseEstate;
    }

    private CaseBuildingMain flowWriteCaseBuildingMain(BasicBuildingMain basicBuildingMain, Integer estateId) throws Exception {
        CaseBuildingMain caseBuildingMain = null;
        Integer caseBuildingMainId = null;
        if (basicBuildingMain.getCaseBuildingMain() != null) {
            caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(basicBuildingMain.getCaseBuildingMain());
            if (caseBuildingMain != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
                caseBuildingMain.setId(basicBuildingMain.getCaseBuildingMain());
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
        }
        caseBuildingMain.setId(caseBuildingMainId);
        return caseBuildingMain;
    }

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
                caseBuildingService.upgradeVersion(caseBuilding);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
                        sysAttachmentDto.setTableId(caseBuilding.getId());
                        baseAttachmentService.updateAttachment(sysAttachmentDto);
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

    private CaseUnit flowWriteCaseUnit(BasicUnit basicUnit, Integer caseBuildingMainId) throws Exception {
        CaseUnit caseUnit = null;
        if (basicUnit.getCaseUnitId() != null) {
            caseUnit = caseUnitService.getCaseUnitById(basicUnit.getCaseUnitId());
            if (caseUnit != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicUnit, caseUnit);
                caseUnit.setId(basicUnit.getCaseUnitId());
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
        caseUnit.setCaseBuildingMainId(caseBuildingMainId);
        caseUnitService.upgradeVersion(caseUnit);
        return caseUnit;
    }

    public void flowWriteCaseHouse(BasicHouse basicHouse, BasicHouseTrading basicTrading, Integer unitId) throws Exception {
        CaseHouseTrading caseHouseTrading = null;
        CaseHouse caseHouse = null;
        if (basicHouse.getCaseHouseId() != null) {
            caseHouse = caseHouseService.getCaseHouseById(basicHouse.getCaseHouseId());
            if (caseHouse != null) {
                BeanCopyHelp.copyPropertiesIgnoreNull(basicHouse, caseHouse);
                caseHouse.setId(basicHouse.getCaseHouseId());
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
        caseHouse.setUnitId(unitId);
        caseHouseService.upgradeVersion(caseHouse);
        if (caseHouse.getId() != null) {
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
            Integer caseTrading = caseHouseTradingService.upgradeVersion(caseHouseTrading);
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
            caseEstate = this.flowWriteCaseEstate(basicEstate, basicEstateLandState);

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
                caseBuildingMain = this.flowWriteCaseBuildingMain(basicBuildingMain, caseEstateId);
                //处理楼栋 关联表
                this.flowWriteCaseBuilding(basicBuildingMain, caseBuildingMain);
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
                //处理单元
                caseUnit = this.flowWriteCaseUnit(basicUnit, caseBuildingMainId);
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
                //处理房屋
                this.flowWriteCaseHouse(basicHouse, basicTrading, unitId);
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
                        basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(basicBuildingFunction);
                    }
                }
            }
        }
    }

    /**
     * 将CaseUnit下的子类 转移到 BasicUnit下的子类中去 (用做过程数据)
     *
     * @param caseUnitId
     * @throws Exception
     */
    public void appWriteUnit(Integer caseUnitId) throws Exception {
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
                basicUnitDecorateService.saveAndUpdateBasicUnitDecorate(queryBasicUnitDecorate);
            }
        }
        if (!ObjectUtils.isEmpty(caseUnitElevators)){
            for (CaseUnitElevator oo:caseUnitElevators){
                BasicUnitElevator queryBasicUnitElevator = new BasicUnitElevator();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, queryBasicUnitElevator);
                queryBasicUnitElevator.setId(null);
                queryBasicUnitElevator.setUnitId(0);
                queryBasicUnitElevator.setCaseId(oo.getId());
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
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(queryBasicUnitHuxing);
            }
        }
    }
}

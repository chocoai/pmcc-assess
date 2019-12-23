package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingFunctionVo;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
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
 * @Date: 2018/9/11 14:40
 * @Description:案例 楼栋信息
 */
@Service
public class CaseBuildingService {
    @Autowired
    private CaseBuildingDao caseBuildingDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseBuildingFunctionService caseBuildingFunctionService;
    @Autowired
    private CaseBuildingMaintenanceService caseBuildingMaintenanceService;
    @Autowired
    private CaseBuildingOutfitService caseBuildingOutfitService;
    @Autowired
    private CaseBuildingSurfaceService caseBuildingSurfaceService;
    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuilderService dataBuilderService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseBuildingListVos(CaseBuilding caseBuilding) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuilding> caseBuildings = getCaseBuildingList(caseBuilding);
        vo.setRows(caseBuildings);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public void initAndUpdateSon(Integer oldId, Integer newId) {
        CaseBuildingFunction caseBuildingFunction = new CaseBuildingFunction();
        caseBuildingFunction.setBuildingId(oldId);
        CaseBuildingMaintenance caseBuildingMaintenance = new CaseBuildingMaintenance();
        caseBuildingMaintenance.setBuildingId(oldId);
        CaseBuildingOutfit caseBuildingOutfit = new CaseBuildingOutfit();
        caseBuildingOutfit.setBuildingId(oldId);
        CaseBuildingSurface caseBuildingSurface = new CaseBuildingSurface();
        caseBuildingSurface.setBuildingId(oldId);
        List<CaseBuildingFunctionVo> caseBuildingFunctions = caseBuildingFunctionService.getCaseBuildingFunctionList(caseBuildingFunction);
        List<CaseBuildingMaintenance> caseBuildingMaintenances = caseBuildingMaintenanceService.getCaseBuildingMaintenanceList(caseBuildingMaintenance);
        List<CaseBuildingOutfit> caseBuildingOutfits = caseBuildingOutfitService.getCaseBuildingOutfitList(caseBuildingOutfit);
        List<CaseBuildingSurface> caseBuildingSurfaces = caseBuildingSurfaceService.getCaseBuildingSurfaceList(caseBuildingSurface);
        if (oldId == null) {
            if (!ObjectUtils.isEmpty(caseBuildingFunctions)) {
                for (CaseBuildingFunctionVo oo : caseBuildingFunctions) {
                    CaseBuildingFunction caseBuildingFunction1 = new CaseBuildingFunction();
                    caseBuildingFunction1.setId(oo.getId());
                    caseBuildingFunctionService.removeCaseBuildingFunction(caseBuildingFunction1);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingMaintenances)) {
                for (CaseBuildingMaintenance oo : caseBuildingMaintenances) {
                    caseBuildingMaintenanceService.deleteCaseBuildingMaintenance(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingOutfits)) {
                for (CaseBuildingOutfit oo : caseBuildingOutfits) {
                    caseBuildingOutfitService.deleteCaseBuildingOutfit(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingSurfaces)) {
                for (CaseBuildingSurface oo : caseBuildingSurfaces) {
                    caseBuildingSurfaceService.deleteCaseBuildingSurface(oo.getId());
                }
            }
        }

        if (oldId != null) {
            if (!ObjectUtils.isEmpty(caseBuildingFunctions)) {
                for (CaseBuildingFunctionVo oo : caseBuildingFunctions) {
                    oo.setBuildingId(newId);
                    caseBuildingFunctionService.saveAndUpdateCaseBuildingFunction(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingMaintenances)) {
                for (CaseBuildingMaintenance oo : caseBuildingMaintenances) {
                    oo.setBuildingId(newId);
                    caseBuildingMaintenanceService.updateCaseBuildingMaintenance(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingOutfits)) {
                for (CaseBuildingOutfit oo : caseBuildingOutfits) {
                    oo.setBuildingId(newId);
                    caseBuildingOutfitService.updateCaseBuildingOutfit(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingSurfaces)) {
                for (CaseBuildingSurface oo : caseBuildingSurfaces) {
                    oo.setBuildingId(newId);
                    caseBuildingSurfaceService.updateCaseBuildingSurface(oo);
                }
            }
        }
    }

    public List<CaseBuilding> getCaseBuildingList(CaseBuilding caseBuilding) {
        return caseBuildingDao.getBuildingList(caseBuilding);
    }

    public Integer getVersion(Integer id) {
        if (id == null) return 0;
        CaseBuilding caseBuilding = caseBuildingDao.getBuildingById(id);
        if (caseBuilding == null) return 0;
        return caseBuilding.getVersion();
    }

    public int updateEstateId(Integer oldEstateId, Integer newEstateId) {
        return caseBuildingDao.updateEstateId(oldEstateId, newEstateId);
    }


    public CaseBuilding getCaseBuildingById(Integer id) {
        return caseBuildingDao.getBuildingById(id);
    }


    public Integer saveAndUpdateCaseBuilding(CaseBuilding caseBuilding) {
        Integer id = null;
        if (caseBuilding.getId() == null || caseBuilding.getId().intValue() == 0) {
            id = caseBuildingDao.addBuilding(caseBuilding);
            return id;
        } else {
            caseBuildingDao.updateBuilding(caseBuilding);
            return null;
        }
    }

    public boolean deleteCaseBuilding(Integer id) {
        return caseBuildingDao.deleteBuilding(id);
    }


    public CaseBuildingVo getCaseBuildingVo(CaseBuilding caseBuilding) {
        if (caseBuilding == null) {
            return null;
        }
        CaseBuildingVo vo = new CaseBuildingVo();
        BeanUtils.copyProperties(caseBuilding, vo);
        vo.setPropertyTypeName(baseDataDicService.getNameById(caseBuilding.getPropertyType()));
        vo.setPropertyCategoryName(baseDataDicService.getNameById(caseBuilding.getPropertyCategory()));
        vo.setBuildingStructureTypeName(baseDataDicService.getNameById(caseBuilding.getBuildingStructureType()));
        vo.setBuildingStructureCategoryName(baseDataDicService.getNameById(caseBuilding.getBuildingStructureCategory()));
        vo.setResidenceUseYearName(baseDataDicService.getNameById(caseBuilding.getResidenceUseYear()));
        vo.setBetweenDistanceName(baseDataDicService.getNameById(caseBuilding.getBetweenDistance()));
        if (caseBuilding.getCompletedTimeType() != null) {
            vo.setCompletedTimeTypeName(baseDataDicService.getDataDicById(caseBuilding.getCompletedTimeType()).getName());
        }
        if (caseBuilding.getIndustryUseYear() != null) {
            if (dataBuildingNewRateService.getByiDdataBuildingNewRate(caseBuilding.getIndustryUseYear()) != null) {
                vo.setIndustryUseYearName(dataBuildingNewRateService.getByiDdataBuildingNewRate(caseBuilding.getIndustryUseYear()).getBuildingStructure());
            }
        }
        if (caseBuilding.getOpenTime() != null) {
            vo.setOpenTimeName(DateUtils.format(caseBuilding.getOpenTime()));
        }
        if (caseBuilding.getRoomTime() != null) {
            vo.setRoomTimeName(DateUtils.format(caseBuilding.getRoomTime()));
        }
        if (caseBuilding.getBeCompletedTime() != null) {
            vo.setBeCompletedTimeName(DateUtils.format(caseBuilding.getBeCompletedTime()));
        }
        vo.setConstructionQualityName(baseDataDicService.getNameById(caseBuilding.getConstructionQuality()));
        vo.setAppearanceStyleName(baseDataDicService.getNameById(caseBuilding.getAppearanceStyle()));
        vo.setAppearanceNewAndOldName(baseDataDicService.getNameById(caseBuilding.getAppearanceNewAndOld()));
        if (caseBuilding.getPropertyCompanyNature() != null) {
            vo.setPropertyCompanyNatureName(crmRpcBaseDataDicService.getBaseDataDic(caseBuilding.getPropertyCompanyNature()).getName());
        }
        vo.setPropertySocialPrestigeName(baseDataDicService.getNameById(caseBuilding.getPropertySocialPrestige()));
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseBuilding(String buildingNumber, Integer estateId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> mainList = caseBuildingDao.getLatestVersionBuildingList(buildingNumber, estateId);
        return mainList;
    }

    public Boolean hasBuilding(String buildingNumber, Integer estateId) {
        return caseBuildingDao.getBuildingCount(buildingNumber, estateId) > 0;
    }

    /**
     * 引用案列中的数据
     *
     * @param id      caseEstate对应id
     * @param tableId basicEstate对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicBuilding quoteCaseBuildToBasic(Integer id, Integer tableId) throws Exception {
        if (id == null || tableId == null) {
            throw new Exception("null point");
        }
        //更新批量申请表信息
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_building", tableId);
        if (batchDetail != null) {
            batchDetail.setQuoteId(id);
            batchDetail.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
            basicApplyBatchDetailDao.updateInfo(batchDetail);
        }
        CaseBuilding oldCaseBuilding = this.getCaseBuildingById(id);
        basicBuildingService.clearInvalidChildData(tableId);
        BasicBuildingVo oldBasicBuilding = basicBuildingService.getBasicBuildingById(tableId);
        BasicBuilding basicBuilding = new BasicBuilding();
        BeanUtils.copyProperties(oldCaseBuilding, basicBuilding);
        basicBuilding.setCreator(commonService.thisUserAccount());
        basicBuilding.setGmtCreated(null);
        basicBuilding.setGmtModified(null);
        basicBuilding.setId(tableId);
        basicBuilding.setApplyId(null);
        basicBuilding.setEstateId(null);
        basicBuilding.setEstateId(oldBasicBuilding.getEstateId());

        basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, false);

        //删除原有的附件
        SysAttachmentDto deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(tableId);
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(deleteExample);
        if (!org.springframework.util.CollectionUtils.isEmpty(attachmentList)) {
            for (SysAttachmentDto item : attachmentList) {
                baseAttachmentService.deleteAttachment(item.getId());
            }
        }

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(id);
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(tableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        CaseEstateTagging oldCaseEstateTagging = new CaseEstateTagging();
        oldCaseEstateTagging.setTableId(id);
        oldCaseEstateTagging.setType(EstateTaggingTypeEnum.BUILDING.getKey());
        List<CaseEstateTagging> oldCaseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(oldCaseEstateTagging);
        if (!ObjectUtils.isEmpty(oldCaseEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldCaseEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setApplyId(null);
            basicEstateTagging.setTableId(tableId);

            basicEstateTagging.setName(null);
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("building_id", String.valueOf(tableId));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("building_id=" + id);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingOutfit.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingMaintenance.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingSurface.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingFunction.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return basicBuilding;
    }
}

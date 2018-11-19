package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingFunctionVo;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<CaseBuildingVo> caseBuildingVoList(CaseBuilding caseBuilding){
        List<CaseBuilding> caseBuildingList =  caseBuildingDao.getBuildingList(caseBuilding);
        List<CaseBuildingVo> vos = new ArrayList<CaseBuildingVo>(10);
        if (!ObjectUtils.isEmpty(caseBuildingList)){
            for (CaseBuilding building:caseBuildingList){
                vos.add(getCaseBuildingVo(building));
            }
        }
        return vos;
    }

    public CaseBuilding getCaseBuildingById(Integer id) {
        return caseBuildingDao.getBuildingById(id);
    }


    public Integer saveAndUpdateCaseBuilding(CaseBuilding caseBuilding) {
        Integer id = null;
        if (caseBuilding.getId() == null || caseBuilding.getId().intValue() == 0) {
            caseBuilding.setCreator(commonService.thisUserAccount());
            id = caseBuildingDao.addBuilding(caseBuilding);
            this.initAndUpdateSon(0, id);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class), id);
            return id;
        } else {
            caseBuildingDao.updateBuilding(caseBuilding);
            return null;
        }
    }

    public Integer upgradeVersion(CaseBuilding caseBuilding) {
        Integer id = null;
        if (caseBuilding.getId() == null || caseBuilding.getId().intValue() == 0) {
            caseBuilding.setCreator(commonService.thisUserAccount());
            caseBuilding.setVersion(0);
            id = caseBuildingDao.addBuilding(caseBuilding);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class), id);
            caseBuilding.setId(id);
            return id;
        } else {
            //更新版本
            CaseBuilding oo = caseBuildingDao.getBuildingById(caseBuilding.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseBuilding, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            int oldId = caseBuilding.getId();
            int newId = caseBuildingDao.addBuilding(oo);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class), newId);
            caseBuilding.setId(newId);
            return newId;
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
        BaseDataDic dataDic = null;
        if (caseBuilding.getPropertyType() != null) {
            dataDic = baseDataDicService.getDataDicById(caseBuilding.getPropertyType());
            if (dataDic != null) {
                vo.setPropertyTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (caseBuilding.getBuildingStructure() != null) {
            dataDic = baseDataDicService.getDataDicById(caseBuilding.getBuildingStructure());
            if (dataDic != null) {
                vo.setBuildingStructureName(dataDic.getName());
                dataDic = null;
            }
        }
        if (caseBuilding.getBuildingStructureLower() != null) {
            dataDic = baseDataDicService.getDataDicById(caseBuilding.getBuildingStructureLower());
            if (dataDic != null) {
                vo.setBuildingStructureLowerName(dataDic.getName());
                dataDic = null;
            }
        }
        if (caseBuilding.getBuildingCategory() != null) {
            dataDic = baseDataDicService.getDataDicById(caseBuilding.getBuildingCategory());
            if (dataDic != null) {
                vo.setBuildingCategoryName(dataDic.getName());
                dataDic = null;
            }
        }
        if (caseBuilding.getOpenTime() != null) {
            vo.setOpenTimeName(DateUtils.format(caseBuilding.getOpenTime()));
        }
        if (caseBuilding.getRoomTime() != null) {
            vo.setRoomTimeName(DateUtils.format(caseBuilding.getRoomTime()));
        }
        if (caseBuilding.getPropertyId() != null) {
            DataProperty dataProperty = dataPropertyService.getByDataPropertyId(caseBuilding.getPropertyId());
            if (dataProperty != null) {
                vo.setPropertyName(dataProperty.getName());
            }
        }
        if (caseBuilding.getBuilderId() != null) {
            DataBuilder dataBuilder = dataBuilderService.getByDataBuilderId(caseBuilding.getBuilderId());
            if (dataBuilder != null) {
                vo.setDataBuildingName(dataBuilder.getName());
            }
        }
        if (caseBuilding.getBeCompletedTime() != null){
            vo.setBeCompletedTimeName(DateUtils.format(caseBuilding.getBeCompletedTime()));
        }
        return vo;
    }
}

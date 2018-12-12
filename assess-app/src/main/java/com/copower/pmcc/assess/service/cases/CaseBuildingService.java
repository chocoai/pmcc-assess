package com.copower.pmcc.assess.service.cases;

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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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

    public List<CaseBuildingVo> getCaseBuildingListByMainId(Integer mainId) {
        CaseBuilding where =new CaseBuilding();
        where.setCaseBuildingMainId(mainId);
        return caseBuildingVoList(where);
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
        return vo;
    }
}

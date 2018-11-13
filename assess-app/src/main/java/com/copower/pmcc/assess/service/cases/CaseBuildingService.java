package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingFunctionVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
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

    public void initAndUpdateSon(Integer oldId,Integer newId) {
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
            if (!ObjectUtils.isEmpty(caseBuildingFunctions)){
                for (CaseBuildingFunctionVo oo:caseBuildingFunctions){
                    CaseBuildingFunction caseBuildingFunction1 = new CaseBuildingFunction();
                    caseBuildingFunction1.setId(oo.getId());
                    caseBuildingFunctionService.removeCaseBuildingFunction(caseBuildingFunction1);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingMaintenances)){
                for (CaseBuildingMaintenance oo:caseBuildingMaintenances){
                    caseBuildingMaintenanceService.deleteCaseBuildingMaintenance(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingOutfits)){
                for (CaseBuildingOutfit oo:caseBuildingOutfits){
                    caseBuildingOutfitService.deleteCaseBuildingOutfit(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingSurfaces)){
                for (CaseBuildingSurface oo:caseBuildingSurfaces){
                    caseBuildingSurfaceService.deleteCaseBuildingSurface(oo.getId());
                }
            }
        }

        if (oldId != null) {
            if (!ObjectUtils.isEmpty(caseBuildingFunctions)){
                for (CaseBuildingFunctionVo oo:caseBuildingFunctions){
                    oo.setBuildingId(newId);
                    caseBuildingFunctionService.saveAndUpdateCaseBuildingFunction(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingMaintenances)){
                for (CaseBuildingMaintenance oo:caseBuildingMaintenances){
                    oo.setBuildingId(newId);
                    caseBuildingMaintenanceService.updateCaseBuildingMaintenance(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingOutfits)){
                for (CaseBuildingOutfit oo:caseBuildingOutfits){
                    oo.setBuildingId(newId);
                    caseBuildingOutfitService.updateCaseBuildingOutfit(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseBuildingSurfaces)){
                for (CaseBuildingSurface oo:caseBuildingSurfaces){
                    oo.setBuildingId(newId);
                    caseBuildingSurfaceService.updateCaseBuildingSurface(oo);
                }
            }
        }
    }

    public List<CaseBuilding> getCaseBuildingList(CaseBuilding caseBuilding) {
        return caseBuildingDao.getBuildingList(caseBuilding);
    }

    public CaseBuilding getCaseBuildingById(Integer id) {
        return caseBuildingDao.getBuildingById(id);
    }



    public Integer saveAndUpdateCaseBuilding(CaseBuilding caseBuilding){
        Integer id = null;
        if (caseBuilding.getId() == null || caseBuilding.getId().intValue() == 0) {
            caseBuilding.setCreator(commonService.thisUserAccount());
            id = caseBuildingDao.addBuilding(caseBuilding);
            this.initAndUpdateSon(0,id);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class), id);
            return id;
        }else {
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
            BeanCopyHelp.copyPropertiesIgnoreNull(caseBuilding,oo);
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
}

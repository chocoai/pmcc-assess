package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingMainDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/26 17:01
 * @Description:
 */
@Service
public class CaseBuildingMainService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseBuildingMainDao caseBuildingMainDao;
    @Autowired
    private CaseUnitService caseUnitService;

    public BootstrapTableVo getBootstrapTableVo(CaseBuildingMain caseBuildingMain) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuildingMain> caseBuildingMainList = caseBuildingMainDao.getBuildingMainList(caseBuildingMain);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(caseBuildingMainList) ? new ArrayList<CaseBuildingMain>() : caseBuildingMainList);
        return vo;
    }


    public void initUpdateSon(Integer oldId, Integer newId) throws Exception {
        CaseUnit queryUnit = new CaseUnit();
        queryUnit.setBuildingMainId(oldId);
        List<CaseUnit> caseUnitList = caseUnitService.getCaseUnitList(queryUnit);
        if (newId == null) {
            if (!ObjectUtils.isEmpty(caseUnitList)) {
                for (CaseUnit oo : caseUnitList) {
                    caseUnitService.deleteCaseUnit(oo.getId());
                }
            }
        }
        if (newId != null) {
            if (!ObjectUtils.isEmpty(caseUnitList)) {
                for (CaseUnit oo : caseUnitList) {
                    oo.setBuildingMainId(newId);
                    caseUnitService.saveAndUpdateCaseUnit(oo);
                }
            }
        }
    }

    public List<CaseBuildingMain> getCaseBuildingMainList(CaseBuildingMain caseBuildingMain) {
        return caseBuildingMainDao.getBuildingMainList(caseBuildingMain);
    }

    public CaseBuildingMain getCaseBuildingMainById(Integer id) {
        return caseBuildingMainDao.getBuildingMainById(id);
    }

    public Integer getVersion(Integer id) {
        if (id == null) return 0;
        CaseBuildingMain caseBuildingMain = caseBuildingMainDao.getBuildingMainById(id);
        if (caseBuildingMain == null) return 0;
        return caseBuildingMain.getVersion();
    }

    public Integer saveAndUpdate(CaseBuildingMain caseBuildingMain) throws Exception {
        if (caseBuildingMain.getId() == null || caseBuildingMain.getId().intValue() == 0) {
            int id = caseBuildingMainDao.addBuildingMain(caseBuildingMain);
            return id;
        } else {
            caseBuildingMainDao.updateBuildingMain(caseBuildingMain);
            return null;
        }
    }

    public int updateEstateId(Integer oldEstateId, Integer newEstateId){
        return caseBuildingMainDao.updateEstateId(oldEstateId, newEstateId);
    }

    public List<CustomCaseEntity> autoCompleteCaseBuildingMain(String buildingNumber, Integer estateId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> mainList = caseBuildingMainDao.getLatestVersionBuildingMainList(buildingNumber, estateId);
        return mainList;
    }


    public boolean deleteCaseBuildingMain(Integer id) {
        return caseBuildingMainDao.deleteBuildingMain(id);
    }


    public Boolean hasBuildingMain(String buildingNumber, Integer estateId){
        return caseBuildingMainDao.getBuildingMainCount(buildingNumber, estateId) > 0;
    }
}

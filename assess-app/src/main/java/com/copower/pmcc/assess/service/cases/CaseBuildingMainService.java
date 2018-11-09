package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingMainDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Comparator;
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

    public void initUpdateSon(Integer oldId, Integer newId) throws Exception {
        CaseUnit queryUnit = new CaseUnit();
        queryUnit.setCaseBuildingMainId(oldId);
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
                    oo.setCaseBuildingMainId(newId);
                    caseUnitService.saveAndUpdateCaseUnit(oo);
                }
            }
        }
    }

    public List<CaseBuildingMain> getCaseBuildingMainList(CaseBuildingMain caseBuildingMain) {
        return caseBuildingMainDao.getEstateList(caseBuildingMain);
    }

    public CaseBuildingMain getCaseBuildingMainById(Integer id) {
        return caseBuildingMainDao.getEstateById(id);
    }

    public Integer saveAndUpdate(CaseBuildingMain caseBuildingMain) throws Exception {
        if (caseBuildingMain.getId() == null || caseBuildingMain.getId().intValue() == 0) {
            caseBuildingMain.setCreator(commonService.thisUserAccount());
            caseBuildingMain.setVersion(0);
            int id = caseBuildingMainDao.addEstate(caseBuildingMain);
            this.initUpdateSon(0, id);
            return id;
        } else {
            caseBuildingMainDao.updateEstate(caseBuildingMain);
            return null;
        }
    }

    public List<CaseBuildingMain> autoCompleteCaseBuildingMain(String identifier, Integer estateId, Integer maxRows) {
        List<CaseBuildingMain> caseBuildingMains = Lists.newArrayList();
        List<CaseBuildingMain> caseBuildingMainList = caseBuildingMainDao.autoCompleteCaseBuildingMain(identifier, estateId);
        Ordering<CaseBuildingMain> ordering = Ordering.from(new Comparator<CaseBuildingMain>() {
            @Override
            public int compare(CaseBuildingMain o1, CaseBuildingMain o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(caseBuildingMainList, ordering);
        if (!ObjectUtils.isEmpty(caseBuildingMainList)) {
            for (int i = 0; i < maxRows; i++) {
                if (i < caseBuildingMainList.size()) {
                    caseBuildingMains.add(caseBuildingMainList.get(i));
                }
            }
        }
        return caseBuildingMains;
    }

    public Integer upgradeVersion(CaseBuildingMain caseBuildingMain)throws Exception {
        if (caseBuildingMain.getId() == null || caseBuildingMain.getId().intValue() == 0) {
            caseBuildingMain.setCreator(commonService.thisUserAccount());
            caseBuildingMain.setVersion(0);
            int id = caseBuildingMainDao.addEstate(caseBuildingMain);
            this.initUpdateSon(0, id);
            return id;
        } else {
            //更新版本
            CaseBuildingMain oo = caseBuildingMainDao.getEstateById(caseBuildingMain.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseBuildingMain, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            int oldId = caseBuildingMain.getId() ;
            int newId = caseBuildingMainDao.addEstate(oo);
            this.initUpdateSon(oldId, newId);
            return newId;
        }
    }

    public boolean deleteCaseBuildingMain(Integer id) {
        return caseBuildingMainDao.deleteEstate(id);
    }
}

package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:41
 * @Description:
 */
@Service
public class CaseEstateService {
    @Autowired
    private CaseEstateDao caseEstateDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<CaseEstate> getCaseEstateList(CaseEstate caseEstate) {
        if (caseEstate == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.getEstateList(caseEstate);
    }

    public CaseEstate getCaseEstateById(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.getEstateById(id);
    }

    public Integer saveAndUpdateCaseEstate(CaseEstate caseEstate) {
        if (caseEstate == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseEstate.getId() == null || caseEstate.getId().intValue() == 0) {
            caseEstate.setCreator(commonService.thisUserAccount());
            caseEstate.setVersion(0);
            return caseEstateDao.addEstate(caseEstate);
        } else {
            //更新版本
            CaseEstate oo = caseEstateDao.getEstateById(caseEstate.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            caseEstate.setVersion(oo.getVersion()+1);
            caseEstateDao.updateEstate(caseEstate);
            return null;
        }
    }

    public boolean deleteCaseEstate(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        //
        return caseEstateDao.deleteEstate(id);
    }

    public List<CaseEstate> autoCompleteCaseEstate(String name, Integer maxRows) {
        if (maxRows == null || StringUtils.isEmpty(name)) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        List<CaseEstate> caseEstates = Lists.newArrayList();
        List<CaseEstate> caseEstateList = caseEstateDao.autoCompleteCaseEstate(name);
        if (!ObjectUtils.isEmpty(caseEstateList)) {
            for (int i = 0; i < maxRows; i++) {
                if (i < caseEstateList.size()) {
                    caseEstates.add(caseEstateList.get(i));
                }
            }
        }
        return caseEstates;
    }
}

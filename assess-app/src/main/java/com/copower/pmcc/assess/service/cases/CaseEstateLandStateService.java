package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateLandStateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/12 10:54
 * @Description:
 */
@Service
public class CaseEstateLandStateService {
    @Autowired
    private CaseEstateLandStateDao caseEstateLandStateDao;
    @Autowired
    private CommonService commonService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<CaseEstateLandState> getCaseEstateLandStateList(CaseEstateLandState caseEstateLandState) {

        return caseEstateLandStateDao.getEstateLandStateList(caseEstateLandState);
    }

    public CaseEstateLandState getCaseEstateLandStateById(Integer id) {

        return caseEstateLandStateDao.getEstateLandStateById(id);
    }

    public Integer upgradeVersion(CaseEstateLandState caseEstateLandState) throws Exception {
        if (caseEstateLandState.getId() == null || caseEstateLandState.getId().intValue() == 0) {
            caseEstateLandState.setCreator(commonService.thisUserAccount());
            caseEstateLandState.setVersion(0);
            Integer id = caseEstateLandStateDao.saveCaseEstateLandState(caseEstateLandState);
            caseEstateLandState.setId(id);
            return id;
        } else {
            //更新版本
            CaseEstateLandState oo = caseEstateLandStateDao.getEstateLandStateById(caseEstateLandState.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateLandState, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            Integer id = caseEstateLandStateDao.saveCaseEstateLandState(oo);
            caseEstateLandState.setId(id);
            return id;
        }
    }

    public boolean saveAndUpdateCaseEstateLandState(CaseEstateLandState caseEstateLandState) {
        if (caseEstateLandState.getId() == null || caseEstateLandState.getId().intValue() == 0) {
            caseEstateLandState.setCreator(commonService.thisUserAccount());
            return caseEstateLandStateDao.addEstateLandState(caseEstateLandState);
        } else {
            return caseEstateLandStateDao.updateEstateLandState(caseEstateLandState);
        }
    }

    public boolean deleteCaseEstateLandState(Integer id) {

        return caseEstateLandStateDao.deleteEstateLandState(id);
    }
}

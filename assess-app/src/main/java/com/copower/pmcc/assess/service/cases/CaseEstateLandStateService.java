package com.copower.pmcc.assess.service.cases;

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

    public List<CaseEstateLandState> getCaseEstateLandStateList(CaseEstateLandState caseEstateLandState){

        return caseEstateLandStateDao.getEstateLandStateList(caseEstateLandState);
    }

    public CaseEstateLandState getCaseEstateLandStateById(Integer id){

        return caseEstateLandStateDao.getEstateLandStateById(id);
    }

    public boolean saveAndUpdateCaseEstateLandState(CaseEstateLandState caseEstateLandState){

        if (caseEstateLandState.getId()==null || caseEstateLandState.getId().intValue()==0){
            caseEstateLandState.setCreator(commonService.thisUserAccount());
            caseEstateLandState.setVersion(0);
            return caseEstateLandStateDao.addEstateLandState(caseEstateLandState);
        }else {
            //更新版本
            CaseEstateLandState oo = caseEstateLandStateDao.getEstateLandStateById(caseEstateLandState.getId());
            if (oo != null){
                if (oo.getVersion()==null){
                    oo.setVersion(0);
                }
            }
            caseEstateLandState.setVersion(oo.getVersion()+1);
            return caseEstateLandStateDao.updateEstateLandState(caseEstateLandState);
        }
    }

    public boolean deleteCaseEstateLandState(Integer id){

        return caseEstateLandStateDao.deleteEstateLandState(id);
    }
}

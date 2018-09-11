package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<CaseEstate> getCaseEstateList(CaseEstate caseEstate){
        if (caseEstate==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.getEstateList(caseEstate);
    }

    public CaseEstate getCaseEstateById(Integer id){
        if (id==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.getEstateById(id);
    }

    public boolean saveAndUpdateCaseEstate(CaseEstate caseEstate){
        if (caseEstate==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseEstate.getId()==null || caseEstate.getId().intValue()==0){
            caseEstate.setCreator(commonService.thisUserAccount());
            return caseEstateDao.addEstate(caseEstate);
        }else {
            return caseEstateDao.updateEstate(caseEstate);
        }
    }

    public boolean deleteCaseEstate(Integer id){
        if (id==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.deleteEstate(id);
    }
}

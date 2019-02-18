package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/10/25 15:45
 * @Description:
 */
@Service
public class PublicCaseService {

    @Autowired
    private CaseEstateService caseEstateService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void basic(BasicEstate basicEstate)throws Exception{
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(basicEstate.getId());
        if (caseEstate != null){
            BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate,caseEstate);
            caseEstateService.saveAndUpdateCaseEstate(caseEstate);
        }
    }
}

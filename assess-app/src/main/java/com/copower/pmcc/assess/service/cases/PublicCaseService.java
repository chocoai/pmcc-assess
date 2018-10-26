package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/25 15:45
 * @Description:
 */
@Service
public class PublicCaseService {

    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void basic(BasicEstate basicEstate)throws Exception{
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(basicEstate.getId());
        if (caseEstate != null){
            BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate,caseEstate);
            caseEstateService.saveAndUpdateCaseEstate(caseEstate);
        }
    }

    public void basic(BasicBuildingMain basicBuildingMain, List<CaseBuilding> caseBuildingList)throws Exception{
        CaseBuildingMain caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(basicBuildingMain.getId());
    }
}

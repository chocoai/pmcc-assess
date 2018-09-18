package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingOutfitDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/18 12:06
 * @Description:
 */
@Service
public class CaseBuildingOutfitService {
    @Autowired
    private CaseBuildingOutfitDao caseBuildingOutfitDao;
}

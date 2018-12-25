package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDamagedDegreeDao;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDamagedDegreeDetailDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegree;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:59
 * @Description:房间
 */
@Service
public class CaseHouseDamagedDegreeService {
    @Autowired
    private CaseHouseDamagedDegreeDao caseHouseDamagedDegreeDao;
    @Autowired
    private CaseHouseDamagedDegreeDetailDao caseHouseDamagedDegreeDetailDao;

    public List<CaseHouseDamagedDegree> getDamagedDegreeListByHouseId(Integer houseId){
        CaseHouseDamagedDegree caseHouseDamagedDegree=new CaseHouseDamagedDegree();
        caseHouseDamagedDegree.setHouseId(houseId);
        return caseHouseDamagedDegreeDao.getDamagedDegreeList(caseHouseDamagedDegree);
    }

    public List<CaseHouseDamagedDegreeDetail> getDamagedDegreeDetailList(Integer damagedDegreeId){
        CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail=new CaseHouseDamagedDegreeDetail();
        caseHouseDamagedDegreeDetail.setDamagedDegreeId(damagedDegreeId);
        return caseHouseDamagedDegreeDetailDao.getDamagedDegreeDetailList(caseHouseDamagedDegreeDetail);
    }
}

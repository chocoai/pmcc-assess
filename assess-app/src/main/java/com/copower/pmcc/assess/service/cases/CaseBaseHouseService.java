package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by kings on 2018-12-3.
 */
@Service
public class CaseBaseHouseService {
    @Autowired
    private CaseBaseHouseDao caseBaseHouseDao;

    /**
     * 新增数据
     * @param caseBaseHouse
     */
    public void addBaseHouse(CaseBaseHouse caseBaseHouse){
        caseBaseHouseDao.addBaseHouse(caseBaseHouse);
    }

    /**
     * 更新数据
     * @param caseBaseHouse
     */
    public void updateBaseHouse(CaseBaseHouse caseBaseHouse){
        caseBaseHouseDao.updateBaseHouse(caseBaseHouse);
    }

    /**
     * 获取数据列表
     * @param caseBaseHouse
     * @return
     */
    public List<CaseBaseHouse> getBaseHouseList(CaseBaseHouse caseBaseHouse){
        return caseBaseHouseDao.getBaseHouseList(caseBaseHouse);
    }

    /**
     * 删除数据by caseHouseId
     * @param caseHouseId
     */
    public void deleteBaseHouseByHouseId(Integer caseHouseId){
        CaseBaseHouse caseBaseHouse=new CaseBaseHouse();
        caseBaseHouse.setCaseHouseId(caseHouseId);
        List<CaseBaseHouse> houseList = getBaseHouseList(caseBaseHouse);
        if(!CollectionUtils.isEmpty(houseList)){
            houseList.forEach(o->caseBaseHouseDao.deleteBaseHouse(o.getId()));
        }
    }
}

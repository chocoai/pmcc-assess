package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingPropertyServiceItem;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingPropertyServiceItemExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingPropertyServiceItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/7/24.
 */
@Repository
public class CaseBuildingPropertyServiceItemDao {

    @Autowired
    private CaseBuildingPropertyServiceItemMapper mapper;

    public boolean addCaseBuildingPropertyServiceItem(CaseBuildingPropertyServiceItem oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateCaseBuildingPropertyServiceItem(CaseBuildingPropertyServiceItem oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public CaseBuildingPropertyServiceItem getCaseBuildingPropertyServiceItemById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean deleteCaseBuildingPropertyServiceItemById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    @Deprecated
    public void removeCaseBuildingPropertyServiceItem(CaseBuildingPropertyServiceItem oo){
        CaseBuildingPropertyServiceItemExample example = new CaseBuildingPropertyServiceItemExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<CaseBuildingPropertyServiceItem> getCaseBuildingPropertyServiceItemListByExample(CaseBuildingPropertyServiceItem oo){
        CaseBuildingPropertyServiceItemExample example = new CaseBuildingPropertyServiceItemExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }
    
}

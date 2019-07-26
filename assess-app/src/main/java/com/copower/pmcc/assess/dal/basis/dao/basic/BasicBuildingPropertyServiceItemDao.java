package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItem;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingPropertyServiceItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/7/24.
 */
@Repository
public class BasicBuildingPropertyServiceItemDao {

    @Autowired
    private BasicBuildingPropertyServiceItemMapper mapper;

    public boolean addBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public void removeIds(List<Integer> integerList){
        BasicBuildingPropertyServiceItemExample example = new BasicBuildingPropertyServiceItemExample();
        example.createCriteria().andIdIn(integerList);
        mapper.deleteByExample(example) ;
    }

    public BasicBuildingPropertyServiceItem getBasicBuildingPropertyServiceItemById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean deleteBasicBuildingPropertyServiceItemById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    @Deprecated
    public void removeBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem oo){
        BasicBuildingPropertyServiceItemExample example = new BasicBuildingPropertyServiceItemExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<BasicBuildingPropertyServiceItem> getBasicBuildingPropertyServiceItemListByExample(BasicBuildingPropertyServiceItem oo){
        BasicBuildingPropertyServiceItemExample example = new BasicBuildingPropertyServiceItemExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }
}

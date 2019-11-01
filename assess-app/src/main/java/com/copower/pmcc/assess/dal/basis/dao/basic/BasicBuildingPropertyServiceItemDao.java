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

    public boolean addBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public void removeIds(List<Integer> integerList) {
        BasicBuildingPropertyServiceItemExample example = new BasicBuildingPropertyServiceItemExample();
        example.createCriteria().andIdIn(integerList);
        BasicBuildingPropertyServiceItem item = new BasicBuildingPropertyServiceItem();
        item.setBisDelete(true);
        mapper.updateByExampleSelective(item, example);
    }

    public BasicBuildingPropertyServiceItem getBasicBuildingPropertyServiceItemById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean deleteBasicBuildingPropertyServiceItemById(Integer id) {
        BasicBuildingPropertyServiceItem basicBuildingPropertyServiceItem = getBasicBuildingPropertyServiceItemById(id);
        if (basicBuildingPropertyServiceItem == null) return false;
        basicBuildingPropertyServiceItem.setBisDelete(true);
        return mapper.updateByPrimaryKeySelective(basicBuildingPropertyServiceItem) == 1;
    }

    @Deprecated
    public void removeBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem oo) {
        BasicBuildingPropertyServiceItemExample example = new BasicBuildingPropertyServiceItemExample();
        BasicBuildingPropertyServiceItemExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(true);
        MybatisUtils.convertObj2Criteria(oo, criteria);

        BasicBuildingPropertyServiceItem item = new BasicBuildingPropertyServiceItem();
        item.setBisDelete(true);
        mapper.updateByExampleSelective(item, example);
    }

    public List<BasicBuildingPropertyServiceItem> getBasicBuildingPropertyServiceItemListByExample(BasicBuildingPropertyServiceItem oo) {
        BasicBuildingPropertyServiceItemExample example = new BasicBuildingPropertyServiceItemExample();
        BasicBuildingPropertyServiceItemExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(oo, criteria);
        return mapper.selectByExample(example);
    }
}

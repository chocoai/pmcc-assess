package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingLeisurePlaceExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingLeisurePlaceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:06
 * @Description:
 */
@Repository
public class BasicMatchingLeisurePlaceDao {

    @Autowired
    private BasicMatchingLeisurePlaceMapper basicMatchingLeisurePlaceMapper;

    public BasicMatchingLeisurePlace getBasicMatchingLeisurePlaceById(Integer id) {
        return basicMatchingLeisurePlaceMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace) {
        basicMatchingLeisurePlaceMapper.insertSelective(basicMatchingLeisurePlace);
        return basicMatchingLeisurePlace.getId();
    }

    public boolean updateBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace, boolean updateNull) {
        return updateNull ? basicMatchingLeisurePlaceMapper.updateByPrimaryKey(basicMatchingLeisurePlace) == 1 : basicMatchingLeisurePlaceMapper.updateByPrimaryKeySelective(basicMatchingLeisurePlace) == 1;
    }

    public boolean deleteBasicMatchingLeisurePlace(Integer id) {
        BasicMatchingLeisurePlace basicMatchingLeisurePlace = getBasicMatchingLeisurePlaceById(id);
        if (basicMatchingLeisurePlace == null) return false;
        basicMatchingLeisurePlace.setBisDelete(true);
        return basicMatchingLeisurePlaceMapper.updateByPrimaryKeySelective(basicMatchingLeisurePlace) == 1;
    }

    public List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList(BasicMatchingLeisurePlace basicMatchingLeisurePlace) {
        BasicMatchingLeisurePlaceExample example = new BasicMatchingLeisurePlaceExample();
        BasicMatchingLeisurePlaceExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingLeisurePlace, criteria);
        example.setOrderByClause("id");
        return basicMatchingLeisurePlaceMapper.selectByExample(example);
    }

    public void removeIds(List<Integer> ids) {
        BasicMatchingLeisurePlaceExample example = new BasicMatchingLeisurePlaceExample();
        example.createCriteria().andBisDeleteEqualTo(false).andIdIn(ids);

        BasicMatchingLeisurePlace item = new BasicMatchingLeisurePlace();
        item.setBisDelete(true);
        basicMatchingLeisurePlaceMapper.updateByExampleSelective(item, example);
    }

}

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

    public BasicMatchingLeisurePlace getBasicMatchingLeisurePlaceById(Integer id) throws SQLException {
        return basicMatchingLeisurePlaceMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace) throws SQLException {
        basicMatchingLeisurePlaceMapper.insertSelective(basicMatchingLeisurePlace);
        return basicMatchingLeisurePlace.getId();
    }

    public boolean updateBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace) throws SQLException {
        return basicMatchingLeisurePlaceMapper.updateByPrimaryKeySelective(basicMatchingLeisurePlace) == 1;
    }

    public void removeBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace) throws SQLException {
        BasicMatchingLeisurePlaceExample example = new BasicMatchingLeisurePlaceExample();
        MybatisUtils.convertObj2Example(basicMatchingLeisurePlace, example);
        basicMatchingLeisurePlaceMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingLeisurePlace(Integer id) throws SQLException {
        return basicMatchingLeisurePlaceMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList(BasicMatchingLeisurePlace basicMatchingLeisurePlace) {
        BasicMatchingLeisurePlaceExample example = new BasicMatchingLeisurePlaceExample();
        MybatisUtils.convertObj2Example(basicMatchingLeisurePlace, example);
        example.setOrderByClause("id");
        return basicMatchingLeisurePlaceMapper.selectByExample(example);
    }

    public void removeIds(List<Integer> ids) {
        BasicMatchingLeisurePlaceExample example = new BasicMatchingLeisurePlaceExample();
        example.createCriteria().andIdIn(ids);
        basicMatchingLeisurePlaceMapper.deleteByExample(example);
    }

}

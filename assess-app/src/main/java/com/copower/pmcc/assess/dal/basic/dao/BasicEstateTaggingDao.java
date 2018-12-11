package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTaggingExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicEstateTaggingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:52
 * @Description:
 */
@Repository
public class BasicEstateTaggingDao {

    @Autowired
    private BasicEstateTaggingMapper basicEstateTaggingMapper;

    public BasicEstateTagging getBasicEstateTaggingById(Integer id) throws SQLException {
        return basicEstateTaggingMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws SQLException {
        basicEstateTaggingMapper.insertSelective(basicEstateTagging);
        return basicEstateTagging.getId();
    }

    public boolean updateBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws SQLException {
        return basicEstateTaggingMapper.updateByPrimaryKeySelective(basicEstateTagging) == 1;
    }

    public void removeBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws SQLException {
        BasicEstateTaggingExample example = new BasicEstateTaggingExample();
        MybatisUtils.convertObj2Example(basicEstateTagging, example);
        basicEstateTaggingMapper.deleteByExample(example);
    }

    public boolean deleteBasicEstateTagging(Integer id) throws SQLException {
        return basicEstateTaggingMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicEstateTagging> basicEstateTaggingList(BasicEstateTagging basicEstateTagging) throws SQLException {
        BasicEstateTaggingExample example = new BasicEstateTaggingExample();
        MybatisUtils.convertObj2Example(basicEstateTagging, example);
        return basicEstateTaggingMapper.selectByExample(example);
    }

    public Integer getEstateTaggingCount(BasicEstateTagging basicEstateTagging) {
        BasicEstateTaggingExample example = new BasicEstateTaggingExample();
        MybatisUtils.convertObj2Example(basicEstateTagging, example);
        return basicEstateTaggingMapper.countByExample(example);
    }
}

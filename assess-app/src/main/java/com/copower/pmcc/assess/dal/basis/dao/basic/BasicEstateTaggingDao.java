package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateTaggingExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateTaggingMapper;
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

    public BasicEstateTagging getBasicEstateTaggingById(Integer id) {
        return basicEstateTaggingMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstateTagging(BasicEstateTagging basicEstateTagging) {
        basicEstateTaggingMapper.insertSelective(basicEstateTagging);
        return basicEstateTagging.getId();
    }

    public boolean updateBasicEstateTagging(BasicEstateTagging basicEstateTagging, boolean updateNull) {
        return updateNull ? basicEstateTaggingMapper.updateByPrimaryKey(basicEstateTagging) == 1 : basicEstateTaggingMapper.updateByPrimaryKeySelective(basicEstateTagging) == 1;
    }

    public void removeBasicEstateTagging(BasicEstateTagging basicEstateTagging) {
        BasicEstateTaggingExample example = new BasicEstateTaggingExample();
        BasicEstateTaggingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateTagging, criteria);

        BasicEstateTagging tagging = new BasicEstateTagging();
        tagging.setBisDelete(true);
        basicEstateTaggingMapper.updateByExampleSelective(tagging, example);
    }

    public boolean deleteBasicEstateTagging(Integer id) {
        BasicEstateTagging estateTagging = getBasicEstateTaggingById(id);
        if (estateTagging == null) return false;
        estateTagging.setBisDelete(true);
        return basicEstateTaggingMapper.updateByPrimaryKeySelective(estateTagging) == 1;
    }

    public List<BasicEstateTagging> getBasicEstateTaggingList(BasicEstateTagging basicEstateTagging) {
        BasicEstateTaggingExample example = new BasicEstateTaggingExample();
        BasicEstateTaggingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateTagging, criteria);
        return basicEstateTaggingMapper.selectByExample(example);
    }

    public Integer getEstateTaggingCount(BasicEstateTagging basicEstateTagging) {
        BasicEstateTaggingExample example = new BasicEstateTaggingExample();
        BasicEstateTaggingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateTagging, criteria);
        return basicEstateTaggingMapper.countByExample(example);
    }

}

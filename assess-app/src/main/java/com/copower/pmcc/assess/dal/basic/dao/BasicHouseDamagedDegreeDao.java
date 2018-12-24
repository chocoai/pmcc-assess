package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseDamagedDegreeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:50
 * @Description:
 */
@Repository
public class BasicHouseDamagedDegreeDao {
    @Autowired
    private BasicHouseDamagedDegreeMapper basicHouseDamagedDegreeMapper;

    public BasicHouseDamagedDegree getBasicHouseDamagedDegreeById(Integer id) {
        return basicHouseDamagedDegreeMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        basicHouseDamagedDegreeMapper.insertSelective(basicHouseDamagedDegree);
        return basicHouseDamagedDegree.getId();
    }

    public boolean updateBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        return basicHouseDamagedDegreeMapper.updateByPrimaryKeySelective(basicHouseDamagedDegree) == 1;
    }

    public boolean deleteBasicHouseDamagedDegree(Integer id) {
        return basicHouseDamagedDegreeMapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean deleteBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        BasicHouseDamagedDegreeExample example = new BasicHouseDamagedDegreeExample();
        MybatisUtils.convertObj2Example(basicHouseDamagedDegree, example);
        return basicHouseDamagedDegreeMapper.deleteByExample(example) > 0;
    }


    public List<BasicHouseDamagedDegree> getDamagedDegreeList(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        BasicHouseDamagedDegreeExample example = new BasicHouseDamagedDegreeExample();
        MybatisUtils.convertObj2Example(basicHouseDamagedDegree, example);
        return basicHouseDamagedDegreeMapper.selectByExample(example);
    }
}

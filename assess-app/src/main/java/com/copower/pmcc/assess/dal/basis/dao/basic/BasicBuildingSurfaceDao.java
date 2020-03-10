package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingSurface;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingSurfaceExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingSurfaceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:49
 * @Description:
 */
@Repository
public class BasicBuildingSurfaceDao {

    @Autowired
    private BasicBuildingSurfaceMapper basicBuildingSurfaceMapper;

    public BasicBuildingSurface getBasicBuildingSurfaceById(Integer id) {
        return basicBuildingSurfaceMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface) {
        basicBuildingSurfaceMapper.insertSelective(basicBuildingSurface);
        return basicBuildingSurface.getId();
    }

    public boolean updateBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface, boolean updateNull) {
        return updateNull ? basicBuildingSurfaceMapper.updateByPrimaryKey(basicBuildingSurface) == 1 : basicBuildingSurfaceMapper.updateByPrimaryKeySelective(basicBuildingSurface) == 1;
    }

    public void removeBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface) {
        BasicBuildingSurfaceExample example = new BasicBuildingSurfaceExample();
        BasicBuildingSurfaceExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingSurface, criteria);
        basicBuildingSurfaceMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingSurface(Integer id) {
        BasicBuildingSurface basicBuildingSurface = getBasicBuildingSurfaceById(id);
        if (basicBuildingSurface == null) return false;
        basicBuildingSurface.setBisDelete(true);
        return basicBuildingSurfaceMapper.updateByPrimaryKeySelective(basicBuildingSurface) == 1;
    }

    public List<BasicBuildingSurface> basicBuildingSurfaceList(BasicBuildingSurface basicBuildingSurface) {
        BasicBuildingSurfaceExample example = new BasicBuildingSurfaceExample();
        BasicBuildingSurfaceExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingSurface, criteria);
        return basicBuildingSurfaceMapper.selectByExample(example);
    }

}

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

    public BasicBuildingSurface getBasicBuildingSurfaceById(Integer id) throws SQLException {
        return basicBuildingSurfaceMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface) throws SQLException {
        basicBuildingSurfaceMapper.insertSelective(basicBuildingSurface);
        return basicBuildingSurface.getId();
    }

    public boolean updateBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface, boolean updateNull) throws SQLException {
        return updateNull ? basicBuildingSurfaceMapper.updateByPrimaryKey(basicBuildingSurface) == 1 : basicBuildingSurfaceMapper.updateByPrimaryKeySelective(basicBuildingSurface) == 1;
    }

    public void removeBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface) throws SQLException {
        BasicBuildingSurfaceExample example = new BasicBuildingSurfaceExample();
        MybatisUtils.convertObj2Example(basicBuildingSurface, example);
        basicBuildingSurfaceMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingSurface(Integer id) throws SQLException {
        return basicBuildingSurfaceMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicBuildingSurface> basicBuildingSurfaceList(BasicBuildingSurface basicBuildingSurface) throws SQLException {
        BasicBuildingSurfaceExample example = new BasicBuildingSurfaceExample();
        MybatisUtils.convertObj2Example(basicBuildingSurface, example);
        return basicBuildingSurfaceMapper.selectByExample(example);
    }

}

package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMedical;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMedicalExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicMatchingMedicalMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:13
 * @Description:
 */
@Repository
public class BasicMatchingMedicalDao {

    @Autowired
    private BasicMatchingMedicalMapper basicMatchingMedicalMapper;

    public BasicMatchingMedical getBasicMatchingMedicalById(Integer id)throws SQLException {
        return basicMatchingMedicalMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical)throws SQLException{
        basicMatchingMedicalMapper.insertSelective(basicMatchingMedical);
        return basicMatchingMedical.getId();
    }

    public boolean updateBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical)throws SQLException{
        return basicMatchingMedicalMapper.updateByPrimaryKeySelective(basicMatchingMedical)==1;
    }

    public void removeBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical)throws SQLException{
        BasicMatchingMedicalExample example = new BasicMatchingMedicalExample();
        MybatisUtils.convertObj2Example(basicMatchingMedical, example);
        basicMatchingMedicalMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingMedical(Integer id)throws SQLException{
        return  basicMatchingMedicalMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicMatchingMedical> basicMatchingMedicalList(BasicMatchingMedical basicMatchingMedical)throws SQLException{
        BasicMatchingMedicalExample example = new BasicMatchingMedicalExample();
        MybatisUtils.convertObj2Example(basicMatchingMedical, example);
        return basicMatchingMedicalMapper.selectByExample(example);
    }
    
}

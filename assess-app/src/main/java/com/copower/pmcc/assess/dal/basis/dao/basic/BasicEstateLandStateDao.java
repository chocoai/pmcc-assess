package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandStateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateLandStateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicEstateLandStateDao {

    @Autowired
    private BasicEstateLandStateMapper basicEstateLandStateMapper;

    public BasicEstateLandState getBasicEstateLandStateById(Integer id) {
        return basicEstateLandStateMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstateLandState(BasicEstateLandState basicEstateLandState) {
        basicEstateLandStateMapper.insertSelective(basicEstateLandState);
        return basicEstateLandState.getId();
    }

    public boolean updateBasicEstateLandState(BasicEstateLandState basicEstateLandState, boolean updateNull) {
        basicEstateLandState.setBisDelete(false);
        return updateNull ? basicEstateLandStateMapper.updateByPrimaryKey(basicEstateLandState) == 1 : basicEstateLandStateMapper.updateByPrimaryKeySelective(basicEstateLandState) == 1;
    }

    public boolean deleteBasicEstateLandState(Integer id) {
        BasicEstateLandState basicEstateLandState = getBasicEstateLandStateById(id);
        if (basicEstateLandState == null) return false;
        basicEstateLandState.setBisDelete(true);
        return basicEstateLandStateMapper.updateByPrimaryKeySelective(basicEstateLandState) == 1;
    }

    public List<BasicEstateLandState> basicEstateLandStateList(BasicEstateLandState basicEstateLandState) {
        BasicEstateLandStateExample example = new BasicEstateLandStateExample();
        BasicEstateLandStateExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateLandState, criteria);
        return basicEstateLandStateMapper.selectByExample(example);
    }

    public BasicEstateLandState getLandStateByEstateId(Integer estateId) {
        BasicEstateLandStateExample example = new BasicEstateLandStateExample();
        example.createCriteria().andBisDeleteEqualTo(false).andEstateIdEqualTo(estateId);
        List<BasicEstateLandState> landStates = basicEstateLandStateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(landStates)) return null;
        return landStates.get(0);
    }
}

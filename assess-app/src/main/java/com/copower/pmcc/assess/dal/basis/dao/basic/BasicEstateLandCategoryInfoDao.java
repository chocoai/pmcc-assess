package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateLandCategoryInfoMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:46
 * @Description:
 */
@Repository
public class BasicEstateLandCategoryInfoDao {
    @Autowired
    private BasicEstateLandCategoryInfoMapper basicEstateLandCategoryInfoMapper;

    public BasicEstateLandCategoryInfo getBasicEstateLandCategoryInfoById(Integer id) {
        return basicEstateLandCategoryInfoMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateLandCategoryInfo(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo) {
        basicEstateLandCategoryInfoMapper.insertSelective(basicEstateLandCategoryInfo);
        return basicEstateLandCategoryInfo.getId();
    }

    public boolean updateBasicEstateLandCategoryInfo(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo, boolean updateNull) {
        return updateNull ? basicEstateLandCategoryInfoMapper.updateByPrimaryKey(basicEstateLandCategoryInfo) == 1 : basicEstateLandCategoryInfoMapper.updateByPrimaryKeySelective(basicEstateLandCategoryInfo) == 1;
    }

    public boolean deleteBasicEstateLandCategoryInfo(Integer id) {
        BasicEstateLandCategoryInfo basicEstateLandCategoryInfo = getBasicEstateLandCategoryInfoById(id);
        if (basicEstateLandCategoryInfo == null) return false;
        basicEstateLandCategoryInfo.setBisDelete(true);
        return basicEstateLandCategoryInfoMapper.updateByPrimaryKeySelective(basicEstateLandCategoryInfo) == 1;
    }

    public List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo) {
        BasicEstateLandCategoryInfoExample example = new BasicEstateLandCategoryInfoExample();
        BasicEstateLandCategoryInfoExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateLandCategoryInfo, criteria);
        return basicEstateLandCategoryInfoMapper.selectByExample(example);
    }
}

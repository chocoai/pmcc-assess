package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateStreetInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateStreetInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateStreetInfoMapper;
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
public class BasicEstateStreetInfoDao {
    @Autowired
    private BasicEstateStreetInfoMapper basicEstateStreetInfoMapper;

    public BasicEstateStreetInfo getBasicEstateStreetInfoById(Integer id) {
        return basicEstateStreetInfoMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateStreetInfo(BasicEstateStreetInfo basicEstateStreetInfo) {
        basicEstateStreetInfoMapper.insertSelective(basicEstateStreetInfo);
        return basicEstateStreetInfo.getId();
    }

    public boolean updateBasicEstateStreetInfo(BasicEstateStreetInfo basicEstateStreetInfo, boolean updateNull) {
        return updateNull ? basicEstateStreetInfoMapper.updateByPrimaryKey(basicEstateStreetInfo) == 1 : basicEstateStreetInfoMapper.updateByPrimaryKeySelective(basicEstateStreetInfo) == 1;
    }

    public boolean deleteBasicEstateStreetInfo(Integer id) {
        BasicEstateStreetInfo basicEstateStreetInfo = getBasicEstateStreetInfoById(id);
        if (basicEstateStreetInfo == null) return false;
        basicEstateStreetInfo.setBisDelete(true);
        return basicEstateStreetInfoMapper.updateByPrimaryKeySelective(basicEstateStreetInfo) == 1;
    }

    public List<BasicEstateStreetInfo> basicEstateStreetInfoList(BasicEstateStreetInfo basicEstateStreetInfo) {
        BasicEstateStreetInfoExample example = new BasicEstateStreetInfoExample();
        BasicEstateStreetInfoExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateStreetInfo, criteria);
        return basicEstateStreetInfoMapper.selectByExample(example);
    }
}

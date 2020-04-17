package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicCommonQuoteFieldInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicCommonQuoteFieldInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicCommonQuoteFieldInfoMapper;
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
public class BasicCommonQuoteFieldInfoDao {
    @Autowired
    private BasicCommonQuoteFieldInfoMapper basicCommonQuoteFieldInfoMapper;

    public BasicCommonQuoteFieldInfo getBasicCommonQuoteFieldInfoById(Integer id) {
        return basicCommonQuoteFieldInfoMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicCommonQuoteFieldInfo(BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo) {
        basicCommonQuoteFieldInfoMapper.insertSelective(basicCommonQuoteFieldInfo);
        return basicCommonQuoteFieldInfo.getId();
    }

    public boolean updateBasicCommonQuoteFieldInfo(BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo, boolean updateNull) {
        return updateNull ? basicCommonQuoteFieldInfoMapper.updateByPrimaryKey(basicCommonQuoteFieldInfo) == 1 : basicCommonQuoteFieldInfoMapper.updateByPrimaryKeySelective(basicCommonQuoteFieldInfo) == 1;
    }

    public boolean deleteBasicCommonQuoteFieldInfo(Integer id) {
        BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo = getBasicCommonQuoteFieldInfoById(id);
        if (basicCommonQuoteFieldInfo == null) return false;
        basicCommonQuoteFieldInfo.setBisDelete(true);
        return basicCommonQuoteFieldInfoMapper.updateByPrimaryKeySelective(basicCommonQuoteFieldInfo) == 1;
    }

    public List<BasicCommonQuoteFieldInfo> basicCommonQuoteFieldInfoList(BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo) {
        BasicCommonQuoteFieldInfoExample example = new BasicCommonQuoteFieldInfoExample();
        BasicCommonQuoteFieldInfoExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicCommonQuoteFieldInfo, criteria);
        return basicCommonQuoteFieldInfoMapper.selectByExample(example);
    }
}

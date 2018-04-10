package com.copower.pmcc.assess.dal.dao;


/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/14
 * @time: 13:56
 */
import com.copower.pmcc.assess.dal.entity.BaseParameter;
import com.copower.pmcc.assess.dal.entity.BaseParameterExample;
import com.copower.pmcc.assess.dal.mapper.BaseParameterMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 为了保证数据来源的一致性,建议各个模块的参数获取来源保持在本类中,方便统一管理维护
 */
@Repository
public class BaseParameterDao {
    @Autowired
    private BaseParameterMapper cmsBaseParameterMapper;

    /**
     * db获取参数对象
     *
     * @param key
     * @return
     */
    public BaseParameter getBaseParameter(String key) {
        BaseParameterExample example = new BaseParameterExample();
        example.createCriteria().andParKeyEqualTo(key).andBisEnableEqualTo(true);
        List<BaseParameter> sysParameters = cmsBaseParameterMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysParameters)) {
            return sysParameters.get(0);
        }

        return null;
    }

    public int updateBaseParameter(String key, String value) {
        BaseParameterExample example = new BaseParameterExample();
        example.createCriteria().andParKeyEqualTo(key);

        BaseParameter sysParameter = new BaseParameter();
        sysParameter.setParValues(value);

        return cmsBaseParameterMapper.updateByExampleSelective(sysParameter, example);
    }
}

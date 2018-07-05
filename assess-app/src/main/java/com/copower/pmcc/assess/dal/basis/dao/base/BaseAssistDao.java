package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseAssist;
import com.copower.pmcc.assess.dal.basis.entity.BaseAssistExample;
import com.copower.pmcc.assess.dal.basis.mapper.BaseAssistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 11:16
 */
@Repository
public class BaseAssistDao {
    @Autowired
    private BaseAssistMapper baseAssistMapper;

    public List<BaseAssist> getBaseAssist(String assistType) {
        BaseAssistExample example = new BaseAssistExample();
        example.createCriteria().andAssistTypeEqualTo(assistType).andBisEnableEqualTo(true);
        return baseAssistMapper.selectByExample(example);
    }

    public Boolean updateBaseAssistByAssistName(BaseAssist BaseAssist, String assistName) {
        BaseAssistExample example = new BaseAssistExample();
        example.createCriteria().andAssistNameEqualTo(assistName);
        return baseAssistMapper.updateByExampleSelective(BaseAssist, example) == 1;
    }
    public Boolean insertBaseAssist(BaseAssist BaseAssist) {
        return baseAssistMapper.insertSelective(BaseAssist)==1;
    }
}

package com.copower.pmcc.assess.dal.dao.Csr;


import com.copower.pmcc.assess.dal.entity.CsrInvalidRule;
import com.copower.pmcc.assess.dal.entity.CsrInvalidRuleExample;
import com.copower.pmcc.assess.dal.mapper.CsrInvalidRuleMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:23
 */
@Repository
public class CsrInvalidRuleDao {
    @Autowired
    private CsrInvalidRuleMapper csrInvalidRuleMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrInvalidRule getCsrInvalidRuleById(Integer id) {
        return csrInvalidRuleMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrInvalidRule
     * @return
     */
    public List<CsrInvalidRule> getCsrInvalidRuleList(CsrInvalidRule csrInvalidRule) {
        CsrInvalidRuleExample example = new CsrInvalidRuleExample();
        MybatisUtils.convertObj2Example(csrInvalidRule, example);
        return csrInvalidRuleMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrInvalidRule
     * @return
     */
    public boolean addCsrInvalidRule(CsrInvalidRule csrInvalidRule) {
        return csrInvalidRuleMapper.insertSelective(csrInvalidRule) > 0;
    }

    /**
     * 编辑
     * @param csrInvalidRule
     * @return
     */
    public boolean updateCsrInvalidRule(CsrInvalidRule csrInvalidRule) {
        return csrInvalidRuleMapper.updateByPrimaryKeySelective(csrInvalidRule) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrInvalidRule(Integer id){
        return csrInvalidRuleMapper.deleteByPrimaryKey(id) > 0;
    }
}

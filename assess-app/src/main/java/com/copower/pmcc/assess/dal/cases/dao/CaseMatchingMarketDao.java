package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMarket;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMarketExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingMarketMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class CaseMatchingMarketDao {
    @Autowired
    private CaseMatchingMarketMapper caseMatchingMarketMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingMarket getMatchingMarketById(Integer id) {
        return caseMatchingMarketMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingMarket
     * @return
     */
    public List<CaseMatchingMarket> getMatchingMarketList(CaseMatchingMarket caseMatchingMarket) {
        CaseMatchingMarketExample example = new CaseMatchingMarketExample();
        MybatisUtils.convertObj2Example(caseMatchingMarket, example);
        return caseMatchingMarketMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingMarket
     * @return
     */
    public boolean addMatchingMarket(CaseMatchingMarket caseMatchingMarket) {
        return caseMatchingMarketMapper.insertSelective(caseMatchingMarket) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingMarket
     * @return
     */
    public boolean updateMatchingMarket(CaseMatchingMarket caseMatchingMarket) {
        return caseMatchingMarketMapper.updateByPrimaryKeySelective(caseMatchingMarket) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMarket(Integer id){
        return caseMatchingMarketMapper.deleteByPrimaryKey(id) > 0;
    }

}
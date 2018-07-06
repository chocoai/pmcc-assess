package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMarket;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMarketExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingMarketMapper;
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
public class ExamineMatchingMarketDao {
    @Autowired
    private ExamineMatchingMarketMapper examineMatchingMarketMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingMarket getMatchingMarketById(Integer id) {
        return examineMatchingMarketMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingMarket
     * @return
     */
    public List<ExamineMatchingMarket> getMatchingMarketList(ExamineMatchingMarket examineMatchingMarket) {
        ExamineMatchingMarketExample example = new ExamineMatchingMarketExample();
        MybatisUtils.convertObj2Example(examineMatchingMarket, example);
        return examineMatchingMarketMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingMarket
     * @return
     */
    public boolean addMatchingMarket(ExamineMatchingMarket examineMatchingMarket) {
        return examineMatchingMarketMapper.insertSelective(examineMatchingMarket) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingMarket
     * @return
     */
    public boolean updateMatchingMarket(ExamineMatchingMarket examineMatchingMarket) {
        return examineMatchingMarketMapper.updateByPrimaryKeySelective(examineMatchingMarket) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMarket(Integer id){
        return examineMatchingMarketMapper.deleteByPrimaryKey(id) > 0;
    }

}
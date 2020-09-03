package com.copower.pmcc.assess.dal.basis.dao.basic;


import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummaryExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseCaseSummaryMapper;
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
public class BasicHouseCaseSummaryDao {
    @Autowired
    private BasicHouseCaseSummaryMapper basicHouseCaseSummaryMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public BasicHouseCaseSummary getBasicHouseCaseSummaryById(Integer id) {
        return basicHouseCaseSummaryMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param BasicHouseCaseSummary
     * @return
     */
    public List<BasicHouseCaseSummary> getBaseHouseSummaryList(BasicHouseCaseSummary BasicHouseCaseSummary) {
        BasicHouseCaseSummaryExample example = new BasicHouseCaseSummaryExample();
        MybatisUtils.convertObj2Example(BasicHouseCaseSummary, example);
        return basicHouseCaseSummaryMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param BasicHouseCaseSummary
     * @return
     */
    public boolean addBaseHouseSummary(BasicHouseCaseSummary BasicHouseCaseSummary) {
        return basicHouseCaseSummaryMapper.insertSelective(BasicHouseCaseSummary) > 0;
    }

    /**
     * 编辑
     * @param BasicHouseCaseSummary
     * @return
     */
    public boolean updateBaseHouseSummary(BasicHouseCaseSummary BasicHouseCaseSummary) {
        return basicHouseCaseSummaryMapper.updateByPrimaryKeySelective(BasicHouseCaseSummary) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBaseHouseSummaryById(Integer id){
        return basicHouseCaseSummaryMapper.deleteByPrimaryKey(id) > 0;
    }

    public Long getCountByFullName(String fullName){
        BasicHouseCaseSummaryExample example = new BasicHouseCaseSummaryExample();
        example.createCriteria().andFullNameEqualTo(fullName);
        return basicHouseCaseSummaryMapper.countByExample(example);
    }

    public List<BasicHouseCaseSummary> getByCaseHouseId(Integer caseHouseId){
        BasicHouseCaseSummaryExample example = new BasicHouseCaseSummaryExample();
        BasicHouseCaseSummaryExample.Criteria criteria = example.createCriteria();
        criteria.andDealTypeIsNotNull();
        if (caseHouseId != null) {
            criteria.andCaseHouseIdEqualTo(caseHouseId);
        }
        return basicHouseCaseSummaryMapper.selectByExample(example);
    }

}
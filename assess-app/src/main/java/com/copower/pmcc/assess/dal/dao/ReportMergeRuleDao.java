package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ReportMergeRule;
import com.copower.pmcc.assess.dal.entity.ReportMergeRuleExample;
import com.copower.pmcc.assess.dal.mapper.ReportMergeRuleMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class ReportMergeRuleDao {
    @Autowired
    private ReportMergeRuleMapper reportMergeRuleMapper;

    //region 获取数据列表

    /**
     * 获取数据列表
     *
     * @return
     */
    public List<ReportMergeRule> getListObject(ReportMergeRule reportMergeRule) {
        ReportMergeRuleExample example = new ReportMergeRuleExample();
        MybatisUtils.convertObj2Example(reportMergeRule, example);
        example.setOrderByClause("id");
        List<ReportMergeRule> list = reportMergeRuleMapper.selectByExample(example);
        return list;
    }


    //endregion


    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public ReportMergeRule getSingleObject(Integer id) {
        ReportMergeRule reportMergeRule = reportMergeRuleMapper.selectByPrimaryKey(id);
        return reportMergeRule;
    }

    public ReportMergeRule getSingleObject(ReportMergeRule reportMergeRule) {
        List<ReportMergeRule> MergeRuleList = getListObject(reportMergeRule);
        if(CollectionUtils.isNotEmpty(MergeRuleList))
            return MergeRuleList.get(0);
        return null;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param reportMergeRule
     * @return
     */
    public boolean addObject(ReportMergeRule reportMergeRule) {
        if (reportMergeRule == null) return false;
        return reportMergeRuleMapper.insertSelective(reportMergeRule) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param reportMergeRule
     * @return
     */
    public boolean updateObject(ReportMergeRule reportMergeRule) {
        if (reportMergeRule == null) return false;
        return reportMergeRuleMapper.updateByPrimaryKeySelective(reportMergeRule) > 0;
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @return
     */
    public boolean deleteObject(Integer id) {
        if (id == null) return false;
        return reportMergeRuleMapper.deleteByPrimaryKey(id) > 0;
    }
    //endregion

    
}

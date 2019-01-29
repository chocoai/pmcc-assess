package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.ToolRewardRate;
import com.copower.pmcc.assess.dal.basis.entity.ToolRewardRateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ToolRewardRateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ToolRewardRateDao {
    @Autowired
    private ToolRewardRateMapper toolRewardRateMapper;

    public List<ToolRewardRate> getListObject(ToolRewardRate toolRewardRate) {
        ToolRewardRateExample example = new ToolRewardRateExample();
        MybatisUtils.convertObj2Example(toolRewardRate, example);
        return toolRewardRateMapper.selectByExample(example);
    }

    public ToolRewardRate getSingleObject(ToolRewardRate toolRewardRate) {
        ToolRewardRateExample example = new ToolRewardRateExample();
        MybatisUtils.convertObj2Example(toolRewardRate, example);
        List<ToolRewardRate> vacationTypeList = toolRewardRateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public ToolRewardRate getSingleObject(Integer id) {
        return toolRewardRateMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(ToolRewardRate bidCostInfo) {
        return toolRewardRateMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(ToolRewardRate bidCostInfo) {
        return toolRewardRateMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return toolRewardRateMapper.deleteByPrimaryKey(id) == 1;
    }
}

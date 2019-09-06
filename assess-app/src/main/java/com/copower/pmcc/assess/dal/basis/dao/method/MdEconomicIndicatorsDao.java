package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdEconomicIndicatorsMapper;
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
public class MdEconomicIndicatorsDao {
    @Autowired
    private MdEconomicIndicatorsMapper mdEconomicIndicatorsMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdEconomicIndicators getEconomicIndicatorsById(Integer id) {
        return mdEconomicIndicatorsMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdEconomicIndicators
     * @return
     */
    public List<MdEconomicIndicators> getEconomicIndicatorsList(MdEconomicIndicators mdEconomicIndicators) {
        MdEconomicIndicatorsExample example = new MdEconomicIndicatorsExample();
        MybatisUtils.convertObj2Example(mdEconomicIndicators, example);
        return mdEconomicIndicatorsMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdEconomicIndicators
     * @return
     */
    public int addEconomicIndicators(MdEconomicIndicators mdEconomicIndicators) {
        mdEconomicIndicatorsMapper.insertSelective(mdEconomicIndicators);
        return mdEconomicIndicators.getId();
    }

    /**
     * 编辑
     * @param mdEconomicIndicators
     * @return
     */
    public boolean updateEconomicIndicators(MdEconomicIndicators mdEconomicIndicators) {
        return mdEconomicIndicatorsMapper.updateByPrimaryKeySelective(mdEconomicIndicators) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEconomicIndicators(Integer id){
        return mdEconomicIndicatorsMapper.deleteByPrimaryKey(id) > 0;
    }

}
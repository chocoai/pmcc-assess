package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastMonth;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastMonthExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeForecastMonthMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class MdIncomeForecastMonthDao {
    @Autowired
    private MdIncomeForecastMonthMapper mdIncomeForecastMonthMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeForecastMonth getForecastMonthById(Integer id) {
        return mdIncomeForecastMonthMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeForecastMonth
     * @return
     */
    public MdIncomeForecastMonth getForecastMonth(MdIncomeForecastMonth mdIncomeForecastMonth) {
        MdIncomeForecastMonthExample example = new MdIncomeForecastMonthExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastMonth, example);
        List<MdIncomeForecastMonth> mdIncomeForecastMonths = mdIncomeForecastMonthMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeForecastMonths)) return mdIncomeForecastMonths.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeForecastMonth
     * @return
     */
    public List<MdIncomeForecastMonth> getForecastMonthList(MdIncomeForecastMonth mdIncomeForecastMonth) {
        MdIncomeForecastMonthExample example = new MdIncomeForecastMonthExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastMonth, example);
        return mdIncomeForecastMonthMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeForecastMonth
     * @return
     */
    public int addForecastMonth(MdIncomeForecastMonth mdIncomeForecastMonth) {
        mdIncomeForecastMonthMapper.insertSelective(mdIncomeForecastMonth);
        return mdIncomeForecastMonth.getId();
    }

    /**
     * 编辑
     *
     * @param mdIncomeForecastMonth
     * @return
     */
    public boolean updateForecastMonth(MdIncomeForecastMonth mdIncomeForecastMonth) {
        return mdIncomeForecastMonthMapper.updateByPrimaryKeySelective(mdIncomeForecastMonth) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteForecastMonth(Integer id) {
        return mdIncomeForecastMonthMapper.deleteByPrimaryKey(id) > 0;
    }

}
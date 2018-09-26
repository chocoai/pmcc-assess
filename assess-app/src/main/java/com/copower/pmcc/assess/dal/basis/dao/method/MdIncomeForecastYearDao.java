package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastYear;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastYearExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeForecastYearMapper;
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
public class MdIncomeForecastYearDao {
    @Autowired
    private MdIncomeForecastYearMapper mdIncomeForecastYearMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeForecastYear getForecastYearById(Integer id) {
        return mdIncomeForecastYearMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeForecastYear
     * @return
     */
    public MdIncomeForecastYear getForecastYear(MdIncomeForecastYear mdIncomeForecastYear) {
        MdIncomeForecastYearExample example = new MdIncomeForecastYearExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastYear, example);
        List<MdIncomeForecastYear> mdIncomeForecastYears = mdIncomeForecastYearMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeForecastYears)) return mdIncomeForecastYears.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeForecastYear
     * @return
     */
    public List<MdIncomeForecastYear> getForecastYearList(MdIncomeForecastYear mdIncomeForecastYear) {
        MdIncomeForecastYearExample example = new MdIncomeForecastYearExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastYear, example);
        return mdIncomeForecastYearMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeForecastYear
     * @return
     */
    public int addForecastYear(MdIncomeForecastYear mdIncomeForecastYear) {
        mdIncomeForecastYearMapper.insertSelective(mdIncomeForecastYear);
        return mdIncomeForecastYear.getId();
    }

    /**
     * 编辑
     *
     * @param mdIncomeForecastYear
     * @return
     */
    public boolean updateForecastYear(MdIncomeForecastYear mdIncomeForecastYear) {
        return mdIncomeForecastYearMapper.updateByPrimaryKeySelective(mdIncomeForecastYear) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteForecastYear(Integer id) {
        return mdIncomeForecastYearMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 删除
     *
     * @param forecastId
     * @return
     */
    public boolean deleteByForecastId(Integer forecastId) {
        MdIncomeForecastYearExample example = new MdIncomeForecastYearExample();
        example.createCriteria().andForecastIdEqualTo(forecastId);
        return mdIncomeForecastYearMapper.deleteByExample(example) > 0;
    }

}
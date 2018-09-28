package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecast;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeForecastMapper;
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
public class MdIncomeForecastDao {
    @Autowired
    private MdIncomeForecastMapper mdIncomeForecastMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeForecast getForecastById(Integer id) {
        return mdIncomeForecastMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeForecast
     * @return
     */
    public MdIncomeForecast getForecast(MdIncomeForecast mdIncomeForecast) {
        MdIncomeForecastExample example = new MdIncomeForecastExample();
        MybatisUtils.convertObj2Example(mdIncomeForecast, example);
        List<MdIncomeForecast> mdIncomeForecasts = mdIncomeForecastMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeForecasts)) return mdIncomeForecasts.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeForecast
     * @return
     */
    public List<MdIncomeForecast> getForecastList(MdIncomeForecast mdIncomeForecast) {
        MdIncomeForecastExample example = new MdIncomeForecastExample();
        MybatisUtils.convertObj2Example(mdIncomeForecast, example);
        return mdIncomeForecastMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeForecast
     * @return
     */
    public int addForecast(MdIncomeForecast mdIncomeForecast) {
        mdIncomeForecastMapper.insertSelective(mdIncomeForecast);
        return mdIncomeForecast.getId();
    }

    /**
     * 更新
     *
     * @param mdIncomeForecast
     * @return
     */
    public boolean updateForecast(MdIncomeForecast mdIncomeForecast) {
        return mdIncomeForecastMapper.updateByPrimaryKeySelective(mdIncomeForecast) > 0;
    }

    public boolean updateForecast(MdIncomeForecast where,MdIncomeForecast mdIncomeForecast) {
        MdIncomeForecastExample example = new MdIncomeForecastExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomeForecastMapper.updateByExampleSelective(mdIncomeForecast,example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteForecast(Integer id) {
        return mdIncomeForecastMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数量
     * @param sectionId
     * @return
     */
    public int getCountBySectionId(Integer sectionId){
        MdIncomeForecastExample example = new MdIncomeForecastExample();
        example.createCriteria().andSectionIdEqualTo(sectionId);
        return mdIncomeForecastMapper.countByExample(example);
    }

}
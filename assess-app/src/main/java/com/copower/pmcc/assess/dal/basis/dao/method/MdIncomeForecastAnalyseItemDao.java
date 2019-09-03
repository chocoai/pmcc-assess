package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseItem;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeForecastAnalyseItemMapper;
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
public class MdIncomeForecastAnalyseItemDao {
    @Autowired
    private MdIncomeForecastAnalyseItemMapper mdIncomeForecastAnalyseItemMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeForecastAnalyseItem getForecastAnalyseItemById(Integer id) {
        return mdIncomeForecastAnalyseItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeForecastAnalyseItem
     * @return
     */
    public MdIncomeForecastAnalyseItem getForecastAnalyseItem(MdIncomeForecastAnalyseItem mdIncomeForecastAnalyseItem) {
        MdIncomeForecastAnalyseItemExample example = new MdIncomeForecastAnalyseItemExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastAnalyseItem, example);
        List<MdIncomeForecastAnalyseItem> mdIncomeForecastAnalyseItems = mdIncomeForecastAnalyseItemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeForecastAnalyseItems)) return mdIncomeForecastAnalyseItems.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeForecastAnalyseItem
     * @return
     */
    public List<MdIncomeForecastAnalyseItem> getForecastAnalyseItemList(MdIncomeForecastAnalyseItem mdIncomeForecastAnalyseItem) {
        MdIncomeForecastAnalyseItemExample example = new MdIncomeForecastAnalyseItemExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastAnalyseItem, example);
        example.setOrderByClause("year");
        return mdIncomeForecastAnalyseItemMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeForecastAnalyseItem
     * @return
     */
    public int addForecastAnalyseItem(MdIncomeForecastAnalyseItem mdIncomeForecastAnalyseItem) {
        mdIncomeForecastAnalyseItemMapper.insertSelective(mdIncomeForecastAnalyseItem);
        return mdIncomeForecastAnalyseItem.getId();
    }

    /**
     * 编辑
     *
     * @param mdIncomeForecastAnalyseItem
     * @return
     */
    public boolean updateForecastAnalyseItem(MdIncomeForecastAnalyseItem mdIncomeForecastAnalyseItem) {
        return mdIncomeForecastAnalyseItemMapper.updateByPrimaryKeySelective(mdIncomeForecastAnalyseItem) > 0;
    }

    public int getForecastAnalyseItemCount(MdIncomeForecastAnalyseItem mdIncomeForecastAnalyseItem){
        MdIncomeForecastAnalyseItemExample example = new MdIncomeForecastAnalyseItemExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastAnalyseItem, example);
        return mdIncomeForecastAnalyseItemMapper.countByExample(example);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteForecastAnalyseItem(Integer id) {
        return mdIncomeForecastAnalyseItemMapper.deleteByPrimaryKey(id) > 0;
    }


}
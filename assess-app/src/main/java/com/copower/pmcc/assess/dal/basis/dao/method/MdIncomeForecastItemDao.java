package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastItem;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeForecastItemMapper;
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
public class MdIncomeForecastItemDao {
    @Autowired
    private MdIncomeForecastItemMapper mdIncomeForecastItemMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeForecastItem getIncomeForecastItemById(Integer id) {
        return mdIncomeForecastItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeForecastItem
     * @return
     */
    public MdIncomeForecastItem getIncomeForecastItem(MdIncomeForecastItem mdIncomeForecastItem) {
        MdIncomeForecastItemExample example = new MdIncomeForecastItemExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastItem, example);
        List<MdIncomeForecastItem> mdIncomeForecastItems = mdIncomeForecastItemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeForecastItems)) return mdIncomeForecastItems.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeForecastItem
     * @return
     */
    public List<MdIncomeForecastItem> getIncomeForecastItemList(MdIncomeForecastItem mdIncomeForecastItem) {
        MdIncomeForecastItemExample example = new MdIncomeForecastItemExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastItem, example);
        return mdIncomeForecastItemMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeForecastItem
     * @return
     */
    public int addIncomeForecastItem(MdIncomeForecastItem mdIncomeForecastItem) {
        mdIncomeForecastItemMapper.insertSelective(mdIncomeForecastItem);
        return mdIncomeForecastItem.getId();
    }

    /**
     * 更新
     *
     * @param mdIncomeForecastItem
     * @return
     */
    public boolean updateIncomeForecastItem(MdIncomeForecastItem mdIncomeForecastItem) {
        return mdIncomeForecastItemMapper.updateByPrimaryKeySelective(mdIncomeForecastItem) > 0;
    }

    public boolean updateIncomeForecastItem(MdIncomeForecastItem where,MdIncomeForecastItem mdIncomeForecastItem) {
        MdIncomeForecastItemExample example = new MdIncomeForecastItemExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomeForecastItemMapper.updateByExampleSelective(mdIncomeForecastItem,example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteIncomeForecastItem(Integer id) {
        return mdIncomeForecastItemMapper.deleteByPrimaryKey(id) > 0;
    }

}
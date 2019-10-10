package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItem;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostApproachItemMapper;
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
public class MdCostApproachItemDao {
    @Autowired
    private MdCostApproachItemMapper mdCostApproachItemMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdCostApproachItem getCostApproachItemById(Integer id) {
        return mdCostApproachItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdCostApproachItem
     * @return
     */
    public MdCostApproachItem getCostApproachItem(MdCostApproachItem mdCostApproachItem) {
        MdCostApproachItemExample example = new MdCostApproachItemExample();
        MybatisUtils.convertObj2Example(mdCostApproachItem, example);
        List<MdCostApproachItem> mdCostApproachItems = mdCostApproachItemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdCostApproachItems)) return mdCostApproachItems.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdCostApproachItem
     * @return
     */
    public List<MdCostApproachItem> getCostApproachItemList(MdCostApproachItem mdCostApproachItem) {
        MdCostApproachItemExample example = new MdCostApproachItemExample();
        MybatisUtils.convertObj2Example(mdCostApproachItem, example);
        return mdCostApproachItemMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdCostApproachItem
     * @return
     */
    public int addCostApproachItem(MdCostApproachItem mdCostApproachItem) {
        mdCostApproachItemMapper.insertSelective(mdCostApproachItem);
        return mdCostApproachItem.getId();
    }

    /**
     * 更新
     *
     * @param mdCostApproachItem
     * @return
     */
    public boolean updateCostApproachItem(MdCostApproachItem mdCostApproachItem) {
        return mdCostApproachItemMapper.updateByPrimaryKeySelective(mdCostApproachItem) > 0;
    }

    public boolean updateCostApproachItem(MdCostApproachItem where,MdCostApproachItem mdCostApproachItem) {
        MdCostApproachItemExample example = new MdCostApproachItemExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdCostApproachItemMapper.updateByExampleSelective(mdCostApproachItem,example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCostApproachItem(Integer id) {
        return mdCostApproachItemMapper.deleteByPrimaryKey(id) > 0;
    }

}
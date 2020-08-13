package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItem;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdEconomicIndicatorsItemMapper;
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
public class MdEconomicIndicatorsItemDao {
    @Autowired
    private MdEconomicIndicatorsItemMapper mdEconomicIndicatorsItemMapper;

    public void batchInset(List<MdEconomicIndicatorsItem> list){
        mdEconomicIndicatorsItemMapper.batchInsert(list) ;
    }

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdEconomicIndicatorsItem getEconomicIndicatorsItemById(Integer id) {
        return mdEconomicIndicatorsItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param mdEconomicIndicatorsItem
     * @return
     */
    public List<MdEconomicIndicatorsItem> getEconomicIndicatorsItemList(MdEconomicIndicatorsItem mdEconomicIndicatorsItem) {
        MdEconomicIndicatorsItemExample example = new MdEconomicIndicatorsItemExample();
        MybatisUtils.convertObj2Example(mdEconomicIndicatorsItem, example);
        return mdEconomicIndicatorsItemMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdEconomicIndicatorsItem
     * @return
     */
    public int addEconomicIndicatorsItem(MdEconomicIndicatorsItem mdEconomicIndicatorsItem) {
        mdEconomicIndicatorsItemMapper.insertSelective(mdEconomicIndicatorsItem);
        return mdEconomicIndicatorsItem.getId();
    }

    /**
     * 编辑
     *
     * @param mdEconomicIndicatorsItem
     * @return
     */
    public boolean updateEconomicIndicatorsItem(MdEconomicIndicatorsItem mdEconomicIndicatorsItem) {
        return mdEconomicIndicatorsItemMapper.updateByPrimaryKeySelective(mdEconomicIndicatorsItem) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteEconomicIndicatorsItem(Integer id) {
        return mdEconomicIndicatorsItemMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean deleteItemByEconomicId(Integer economicId) {
        MdEconomicIndicatorsItemExample example = new MdEconomicIndicatorsItemExample();
        example.createCriteria().andEconomicIdEqualTo(economicId);
        return mdEconomicIndicatorsItemMapper.deleteByExample(example) > 0;
    }
}
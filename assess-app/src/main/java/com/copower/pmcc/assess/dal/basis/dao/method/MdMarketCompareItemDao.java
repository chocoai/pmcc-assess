package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdMarketCompareItemMapper;
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
public class MdMarketCompareItemDao {
    @Autowired
    private MdMarketCompareItemMapper mdMarketCompareItemMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdMarketCompareItem getMarketCompareItemById(Integer id) {
        return mdMarketCompareItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMarketCompareItem
     * @return
     */
    public List<MdMarketCompareItem> getMarketCompareItemList(MdMarketCompareItem examineMarketCompareItem) {
        MdMarketCompareItemExample example = new MdMarketCompareItemExample();
        MybatisUtils.convertObj2Example(examineMarketCompareItem, example);
        return mdMarketCompareItemMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMarketCompareItem
     * @return
     */
    public boolean addMarketCompareItem(MdMarketCompareItem examineMarketCompareItem) {
        return mdMarketCompareItemMapper.insertSelective(examineMarketCompareItem) > 0;
    }

    /**
     * 编辑
     * @param examineMarketCompareItem
     * @return
     */
    public boolean updateMarketCompareItem(MdMarketCompareItem examineMarketCompareItem) {
        return mdMarketCompareItemMapper.updateByPrimaryKeySelective(examineMarketCompareItem) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMarketCompareItem(Integer id){
        return mdMarketCompareItemMapper.deleteByPrimaryKey(id) > 0;
    }

}
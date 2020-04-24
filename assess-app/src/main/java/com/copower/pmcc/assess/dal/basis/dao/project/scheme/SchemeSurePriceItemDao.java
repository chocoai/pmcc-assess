package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeSurePriceItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class SchemeSurePriceItemDao {
    @Autowired
    private SchemeSurePriceItemMapper schemeSurePriceItemMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeSurePriceItem getSurePriceItemById(Integer id) {
        return schemeSurePriceItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeSurePriceItem
     * @return
     */
    public SchemeSurePriceItem getSchemeSurePriceItem(SchemeSurePriceItem schemeSurePriceItem) {
        SchemeSurePriceItemExample example = new SchemeSurePriceItemExample();
        MybatisUtils.convertObj2Example(schemeSurePriceItem, example);
        List<SchemeSurePriceItem> schemeSurePriceItems = schemeSurePriceItemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeSurePriceItems)) return schemeSurePriceItems.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineSurePriceItem
     * @return
     */
    public List<SchemeSurePriceItem> getSurePriceItemList(SchemeSurePriceItem examineSurePriceItem) {
        SchemeSurePriceItemExample example = new SchemeSurePriceItemExample();
        MybatisUtils.convertObj2Example(examineSurePriceItem, example);
        return schemeSurePriceItemMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineSurePriceItem
     * @return
     */
    public boolean addSurePriceItem(SchemeSurePriceItem examineSurePriceItem) {
        return schemeSurePriceItemMapper.insertSelective(examineSurePriceItem) > 0;
    }

    /**
     * 编辑
     *
     * @param examineSurePriceItem
     * @return
     */
    public boolean updateSurePriceItem(SchemeSurePriceItem examineSurePriceItem) {
        return schemeSurePriceItemMapper.updateByPrimaryKeySelective(examineSurePriceItem) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteSurePriceItem(Integer id) {
        return schemeSurePriceItemMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean deleteSurePriceItemByJudgeId(Integer judgeId) {
        if(judgeId==null||judgeId<=0) return false;
        SchemeSurePriceItemExample example = new SchemeSurePriceItemExample();
        example.createCriteria().andJudgeObjectIdEqualTo(judgeId);
        return schemeSurePriceItemMapper.deleteByExample(example) > 0;
    }
}

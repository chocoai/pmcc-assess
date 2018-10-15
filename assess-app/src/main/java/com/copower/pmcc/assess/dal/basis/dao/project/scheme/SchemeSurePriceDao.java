package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeSurePriceMapper;
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
public class SchemeSurePriceDao {
    @Autowired
    private SchemeSurePriceMapper schemeSurePriceMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeSurePrice getSurePriceById(Integer id) {
        return schemeSurePriceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeSurePrice
     * @return
     */
    public SchemeSurePrice getSchemeSurePrice(SchemeSurePrice schemeSurePrice) {
        SchemeSurePriceExample example = new SchemeSurePriceExample();
        MybatisUtils.convertObj2Example(schemeSurePrice, example);
        List<SchemeSurePrice> schemeSurePrices = schemeSurePriceMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeSurePrices)) return schemeSurePrices.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineSurePrice
     * @return
     */
    public List<SchemeSurePrice> getSurePriceList(SchemeSurePrice examineSurePrice) {
        SchemeSurePriceExample example = new SchemeSurePriceExample();
        MybatisUtils.convertObj2Example(examineSurePrice, example);
        return schemeSurePriceMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineSurePrice
     * @return
     */
    public boolean addSurePrice(SchemeSurePrice examineSurePrice) {
        return schemeSurePriceMapper.insertSelective(examineSurePrice) > 0;
    }

    /**
     * 编辑
     *
     * @param examineSurePrice
     * @return
     */
    public boolean updateSurePrice(SchemeSurePrice examineSurePrice) {
        return schemeSurePriceMapper.updateByPrimaryKeySelective(examineSurePrice) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteSurePrice(Integer id) {
        return schemeSurePriceMapper.deleteByPrimaryKey(id) > 0;
    }


}

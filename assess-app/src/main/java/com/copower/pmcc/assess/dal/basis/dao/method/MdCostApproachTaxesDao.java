package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxes;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxesExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostApproachTaxesMapper;
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
public class MdCostApproachTaxesDao {
    @Autowired
    private MdCostApproachTaxesMapper mdCostApproachTaxesMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdCostApproachTaxes getCostApproachTaxesById(Integer id) {
        return mdCostApproachTaxesMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdCostApproachTaxes
     * @return
     */
    public MdCostApproachTaxes getCostApproachTaxes(MdCostApproachTaxes mdCostApproachTaxes) {
        MdCostApproachTaxesExample example = new MdCostApproachTaxesExample();
        MybatisUtils.convertObj2Example(mdCostApproachTaxes, example);
        List<MdCostApproachTaxes> mdCostApproachTaxess = mdCostApproachTaxesMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdCostApproachTaxess)) return mdCostApproachTaxess.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdCostApproachTaxes
     * @return
     */
    public List<MdCostApproachTaxes> getCostApproachTaxesList(MdCostApproachTaxes mdCostApproachTaxes) {
        MdCostApproachTaxesExample example = new MdCostApproachTaxesExample();
        MybatisUtils.convertObj2Example(mdCostApproachTaxes, example);
        return mdCostApproachTaxesMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdCostApproachTaxes
     * @return
     */
    public int addCostApproachTaxes(MdCostApproachTaxes mdCostApproachTaxes) {
        mdCostApproachTaxesMapper.insertSelective(mdCostApproachTaxes);
        return mdCostApproachTaxes.getId();
    }

    /**
     * 更新
     *
     * @param mdCostApproachTaxes
     * @return
     */
    public boolean updateCostApproachTaxes(MdCostApproachTaxes mdCostApproachTaxes) {
        return mdCostApproachTaxesMapper.updateByPrimaryKeySelective(mdCostApproachTaxes) > 0;
    }

    public boolean updateCostApproachTaxes(MdCostApproachTaxes where,MdCostApproachTaxes mdCostApproachTaxes) {
        MdCostApproachTaxesExample example = new MdCostApproachTaxesExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdCostApproachTaxesMapper.updateByExampleSelective(mdCostApproachTaxes,example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCostApproachTaxes(Integer id) {
        return mdCostApproachTaxesMapper.deleteByPrimaryKey(id) > 0;
    }

}
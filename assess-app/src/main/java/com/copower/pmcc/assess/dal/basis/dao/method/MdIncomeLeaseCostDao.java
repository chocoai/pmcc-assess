package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseCost;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseCostExample;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeLeaseCostMapper;
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
public class MdIncomeLeaseCostDao {
    @Autowired
    private MdIncomeLeaseCostMapper mdIncomeLeaseCostMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeLeaseCost getLeaseCostById(Integer id) {
        return mdIncomeLeaseCostMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeLeaseCost
     * @return
     */
    public MdIncomeLeaseCost getLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) {
        MdIncomeLeaseCostExample example = new MdIncomeLeaseCostExample();
        MybatisUtils.convertObj2Example(mdIncomeLeaseCost, example);
        List<MdIncomeLeaseCost> mdIncomeLeaseCosts = mdIncomeLeaseCostMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeLeaseCosts)) return mdIncomeLeaseCosts.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeLeaseCost
     * @return
     */
    public List<MdIncomeLeaseCost> getLeaseCostList(MdIncomeLeaseCost mdIncomeLeaseCost) {
        MdIncomeLeaseCostExample example = new MdIncomeLeaseCostExample();
        MybatisUtils.convertObj2Example(mdIncomeLeaseCost, example);
        return mdIncomeLeaseCostMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeLeaseCost
     * @return
     */
    public int addLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) {
        mdIncomeLeaseCostMapper.insertSelective(mdIncomeLeaseCost);
        return mdIncomeLeaseCost.getId();
    }

    /**
     * 编辑
     *
     * @param mdIncomeLeaseCost
     * @return
     */
    public boolean updateLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) {
        return mdIncomeLeaseCostMapper.updateByPrimaryKeySelective(mdIncomeLeaseCost) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteLeaseCost(Integer id) {
        return mdIncomeLeaseCostMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数量
     * @param sectionId
     * @return
     */
    public int getCountBySectionId(Integer sectionId){
        MdIncomeLeaseCostExample example = new MdIncomeLeaseCostExample();
        example.createCriteria().andSectionIdEqualTo(sectionId);
        return mdIncomeLeaseCostMapper.countByExample(example);
    }

}
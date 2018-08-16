package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLease;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseExample;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCost;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeSelfSupportCostMapper;
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
public class MdIncomeSelfSupportCostDao {
    @Autowired
    private MdIncomeSelfSupportCostMapper mdIncomeSelfSupportCostMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdIncomeSelfSupportCost getSelfSupportCostById(Integer id) {
        return mdIncomeSelfSupportCostMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdIncomeSelfSupportCost
     * @return
     */
    public List<MdIncomeSelfSupportCost> getSelfSupportCostList(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        MdIncomeSelfSupportCostExample example = new MdIncomeSelfSupportCostExample();
        MybatisUtils.convertObj2Example(mdIncomeSelfSupportCost, example);
        return mdIncomeSelfSupportCostMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdIncomeSelfSupportCost
     * @return
     */
    public int addSelfSupportCost(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        mdIncomeSelfSupportCostMapper.insertSelective(mdIncomeSelfSupportCost);
        return mdIncomeSelfSupportCost.getId();
    }

    /**
     * 编辑
     * @param mdIncomeSelfSupportCost
     * @return
     */
    public boolean updateSelfSupportCost(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        return mdIncomeSelfSupportCostMapper.updateByPrimaryKeySelective(mdIncomeSelfSupportCost) > 0;
    }

    /**
     * 更新
     * @param mdIncomeSelfSupportCost
     * @return
     */
    public boolean updateSelfSupportCost(MdIncomeSelfSupportCost mdIncomeSelfSupportCost, MdIncomeSelfSupportCost where) {
        MdIncomeSelfSupportCostExample example = new MdIncomeSelfSupportCostExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomeSelfSupportCostMapper.updateByExample(mdIncomeSelfSupportCost,example) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteSelfSupportCost(Integer id){
        return mdIncomeSelfSupportCostMapper.deleteByPrimaryKey(id) > 0;
    }

}
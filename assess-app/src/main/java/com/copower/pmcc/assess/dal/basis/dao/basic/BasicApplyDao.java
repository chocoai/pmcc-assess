package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicApplyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kings on 2018-10-24.
 */
@Repository
public class BasicApplyDao {
    @Autowired
    private BasicApplyMapper basicApplyMapper;

    public BasicApply getBasicApplyById(Integer id) {
        return basicApplyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据信息
     *
     * @param houseId
     * @return
     */
    public BasicApply getBasicApplyByHouseId(Integer houseId) {
        BasicApplyExample example = new BasicApplyExample();
        BasicApplyExample.Criteria criteria = example.createCriteria();
        criteria.andBasicHouseIdEqualTo(houseId);
        List<BasicApply> applyList = basicApplyMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(applyList)) return null;
        return applyList.get(0);
    }

    public List<BasicApply> getBasicApplyListByIds(List<Integer> ids){
        BasicApplyExample example = new BasicApplyExample();
        BasicApplyExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return basicApplyMapper.selectByExample(example);
    }

    public List<BasicApply> getBasicApplyList(BasicApply basicApply) {
        BasicApplyExample example = new BasicApplyExample();
        BasicApplyExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicApply,criteria);
        example.setOrderByClause("id desc");
        return basicApplyMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param basicApply
     * @return
     */
    public boolean addBasicApply(BasicApply basicApply) {
        return basicApplyMapper.insertSelective(basicApply) > 0;
    }


    /**
     * 编辑
     *
     * @param basicApply
     * @return
     */
    public boolean updateBasicApply(BasicApply basicApply) {
        return basicApplyMapper.updateByPrimaryKeySelective(basicApply) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteBasicApply(Integer id) {
        BasicApply basicApply = getBasicApplyById(id);
        if (basicApply == null) return false;
        basicApply.setBisDelete(true);
        return basicApplyMapper.updateByPrimaryKeySelective(basicApply) > 0;
    }

    /**
     * 根据条件查询申请的数量
     *
     * @param basicApply
     * @param applyId
     * @return
     */
    public int getBasicApplyCount(BasicApply basicApply, Integer applyId) {
        BasicApplyExample example = new BasicApplyExample();
        example = MybatisUtils.convertObj2Example(basicApply, example);
        example.createCriteria().andIdNotEqualTo(applyId);
        return basicApplyMapper.countByExample(example);
    }

    public List<BasicApply> getBasicApplysByBatchDetailIds(List<Integer> batchDetailIds) {
        BasicApplyExample example = new BasicApplyExample();
        example.createCriteria().andBatchDetailIdIn(batchDetailIds);
        return basicApplyMapper.selectByExample(example);
    }
}

package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicApplyExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicApplyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public BasicApply getBlockById(Integer id) {
        return basicApplyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBlock
     * @return
     */
    public List<BasicApply> getBlockList(BasicApply caseBlock) {
        BasicApplyExample example = new BasicApplyExample();
        MybatisUtils.convertObj2Example(caseBlock, example);
        return basicApplyMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBlock
     * @return
     */
    public boolean addBlock(BasicApply caseBlock) {
        return basicApplyMapper.insertSelective(caseBlock) > 0;
    }

    public Integer saveBasicApply(BasicApply basicApply){
        basicApplyMapper.insertSelective(basicApply) ;
        return basicApply.getId();
    }

    /**
     * 编辑
     * @param caseBlock
     * @return
     */
    public boolean updateBlock(BasicApply caseBlock) {
        return basicApplyMapper.updateByPrimaryKeySelective(caseBlock) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBlock(Integer id){
        return basicApplyMapper.deleteByPrimaryKey(id) > 0;
    }
}

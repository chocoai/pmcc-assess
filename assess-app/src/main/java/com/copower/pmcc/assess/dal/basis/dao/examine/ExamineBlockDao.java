package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBlock;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBlockExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBlockMapper;
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
public class ExamineBlockDao {
    @Autowired
    private ExamineBlockMapper examineBlockMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBlock getBlockById(Integer id) {
        return examineBlockMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据信息
     * @param declareId
     * @return
     */
    public ExamineBlock getBlockByDeclareId(Integer declareId) {
        ExamineBlockExample example = new ExamineBlockExample();
        example.createCriteria().andDeclareIdEqualTo(declareId);
        List<ExamineBlock> blockList = examineBlockMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    /**
     * 获取数据列表
     * @param examineBlock
     * @return
     */
    public List<ExamineBlock> getBlockList(ExamineBlock examineBlock) {
        ExamineBlockExample example = new ExamineBlockExample();
        MybatisUtils.convertObj2Example(examineBlock, example);
        return examineBlockMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBlock
     * @return
     */
    public boolean addBlock(ExamineBlock examineBlock) {
        return examineBlockMapper.insertSelective(examineBlock) > 0;
    }

    /**
     * 编辑
     * @param examineBlock
     * @return
     */
    public boolean updateBlock(ExamineBlock examineBlock) {
        return examineBlockMapper.updateByPrimaryKeySelective(examineBlock) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBlock(Integer id){
        return examineBlockMapper.deleteByPrimaryKey(id) > 0;
    }

}
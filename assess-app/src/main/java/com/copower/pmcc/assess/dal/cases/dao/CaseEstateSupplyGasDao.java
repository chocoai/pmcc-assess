package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBlock;
import com.copower.pmcc.assess.dal.cases.entity.CaseBlockExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBlockMapper;
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
public class CaseEstateSupplyGasDao {
    @Autowired
    private CaseBlockMapper caseBlockMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBlock getBlockById(Integer id) {
        return caseBlockMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBlock
     * @return
     */
    public List<CaseBlock> getBlockList(CaseBlock caseBlock) {
        CaseBlockExample example = new CaseBlockExample();
        MybatisUtils.convertObj2Example(caseBlock, example);
        return caseBlockMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBlock
     * @return
     */
    public boolean addBlock(CaseBlock caseBlock) {
        return caseBlockMapper.insertSelective(caseBlock) > 0;
    }

    /**
     * 编辑
     * @param caseBlock
     * @return
     */
    public boolean updateBlock(CaseBlock caseBlock) {
        return caseBlockMapper.updateByPrimaryKeySelective(caseBlock) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBlock(Integer id){
        return caseBlockMapper.deleteByPrimaryKey(id) > 0;
    }

}
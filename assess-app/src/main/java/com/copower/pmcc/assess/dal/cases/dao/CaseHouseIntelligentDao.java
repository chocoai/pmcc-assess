package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseIntelligent;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseIntelligentExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseIntelligentMapper;
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
public class CaseHouseIntelligentDao {
    @Autowired
    private CaseHouseIntelligentMapper caseHouseIntelligentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseIntelligent getHouseIntelligentById(Integer id) {
        return caseHouseIntelligentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseIntelligent
     * @return
     */
    public List<CaseHouseIntelligent> getHouseIntelligentList(CaseHouseIntelligent caseHouseIntelligent) {
        CaseHouseIntelligentExample example = new CaseHouseIntelligentExample();
        MybatisUtils.convertObj2Example(caseHouseIntelligent, example);
        return caseHouseIntelligentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseIntelligent
     * @return
     */
    public boolean addHouseIntelligent(CaseHouseIntelligent caseHouseIntelligent) {
        return caseHouseIntelligentMapper.insertSelective(caseHouseIntelligent) > 0;
    }

    /**
     * 编辑
     * @param caseHouseIntelligent
     * @return
     */
    public boolean updateHouseIntelligent(CaseHouseIntelligent caseHouseIntelligent) {
        return caseHouseIntelligentMapper.updateByPrimaryKeySelective(caseHouseIntelligent) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseIntelligent(Integer id){
        return caseHouseIntelligentMapper.deleteByPrimaryKey(id) > 0;
    }

}
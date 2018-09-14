package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseUnitMapper;
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
public class CaseUnitDao {
    @Autowired
    private CaseUnitMapper caseUnitMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseUnit getUnitById(Integer id) {
        return caseUnitMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseUnit
     * @return
     */
    public List<CaseUnit> getUnitList(CaseUnit caseUnit) {
        CaseUnitExample example = new CaseUnitExample();
        MybatisUtils.convertObj2Example(caseUnit, example);
        return caseUnitMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseUnit
     * @return
     */
    public Integer addUnit(CaseUnit caseUnit) {
        caseUnitMapper.insertSelective(caseUnit);
        return caseUnit.getId();
    }

    /**
     * 编辑
     * @param caseUnit
     * @return
     */
    public boolean updateUnit(CaseUnit caseUnit) {
        return caseUnitMapper.updateByPrimaryKeySelective(caseUnit) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnit(Integer id){
        return caseUnitMapper.deleteByPrimaryKey(id) > 0;
    }

}
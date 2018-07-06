package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseNewWind;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseNewWindExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseNewWindMapper;
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
public class CaseHouseNewWindDao {
    @Autowired
    private CaseHouseNewWindMapper caseHouseNewWindMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseNewWind getHouseNewWindById(Integer id) {
        return caseHouseNewWindMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseNewWind
     * @return
     */
    public List<CaseHouseNewWind> getHouseNewWindList(CaseHouseNewWind caseHouseNewWind) {
        CaseHouseNewWindExample example = new CaseHouseNewWindExample();
        MybatisUtils.convertObj2Example(caseHouseNewWind, example);
        return caseHouseNewWindMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseNewWind
     * @return
     */
    public boolean addHouseNewWind(CaseHouseNewWind caseHouseNewWind) {
        return caseHouseNewWindMapper.insertSelective(caseHouseNewWind) > 0;
    }

    /**
     * 编辑
     * @param caseHouseNewWind
     * @return
     */
    public boolean updateHouseNewWind(CaseHouseNewWind caseHouseNewWind) {
        return caseHouseNewWindMapper.updateByPrimaryKeySelective(caseHouseNewWind) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseNewWind(Integer id){
        return caseHouseNewWindMapper.deleteByPrimaryKey(id) > 0;
    }

}
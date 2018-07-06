package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseHeating;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseHeatingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseHeatingMapper;
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
public class CaseHouseHeatingDao {
    @Autowired
    private CaseHouseHeatingMapper caseHouseHeatingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseHeating getHouseHeatingById(Integer id) {
        return caseHouseHeatingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseHeating
     * @return
     */
    public List<CaseHouseHeating> getHouseHeatingList(CaseHouseHeating caseHouseHeating) {
        CaseHouseHeatingExample example = new CaseHouseHeatingExample();
        MybatisUtils.convertObj2Example(caseHouseHeating, example);
        return caseHouseHeatingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseHeating
     * @return
     */
    public boolean addHouseHeating(CaseHouseHeating caseHouseHeating) {
        return caseHouseHeatingMapper.insertSelective(caseHouseHeating) > 0;
    }

    /**
     * 编辑
     * @param caseHouseHeating
     * @return
     */
    public boolean updateHouseHeating(CaseHouseHeating caseHouseHeating) {
        return caseHouseHeatingMapper.updateByPrimaryKeySelective(caseHouseHeating) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseHeating(Integer id){
        return caseHouseHeatingMapper.deleteByPrimaryKey(id) > 0;
    }

}
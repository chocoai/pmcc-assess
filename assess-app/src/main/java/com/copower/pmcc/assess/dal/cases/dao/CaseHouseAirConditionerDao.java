package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseAirConditioner;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseAirConditionerExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseAirConditionerMapper;
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
public class CaseHouseAirConditionerDao {
    @Autowired
    private CaseHouseAirConditionerMapper caseHouseAirConditionerMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseAirConditioner getHouseAirConditionerById(Integer id) {
        return caseHouseAirConditionerMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseAirConditioner
     * @return
     */
    public List<CaseHouseAirConditioner> getHouseAirConditionerList(CaseHouseAirConditioner caseHouseAirConditioner) {
        CaseHouseAirConditionerExample example = new CaseHouseAirConditionerExample();
        MybatisUtils.convertObj2Example(caseHouseAirConditioner, example);
        return caseHouseAirConditionerMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseAirConditioner
     * @return
     */
    public boolean addHouseAirConditioner(CaseHouseAirConditioner caseHouseAirConditioner) {
        return caseHouseAirConditionerMapper.insertSelective(caseHouseAirConditioner) > 0;
    }

    /**
     * 编辑
     * @param caseHouseAirConditioner
     * @return
     */
    public boolean updateHouseAirConditioner(CaseHouseAirConditioner caseHouseAirConditioner) {
        return caseHouseAirConditionerMapper.updateByPrimaryKeySelective(caseHouseAirConditioner) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseAirConditioner(Integer id){
        return caseHouseAirConditionerMapper.deleteByPrimaryKey(id) > 0;
    }

}
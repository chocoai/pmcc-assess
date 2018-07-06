package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevator;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevatorExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseUnitElevatorMapper;
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
public class CaseUnitElevatorDao {
    @Autowired
    private CaseUnitElevatorMapper caseUnitElevatorMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseUnitElevator getUnitElevatorById(Integer id) {
        return caseUnitElevatorMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseUnitElevator
     * @return
     */
    public List<CaseUnitElevator> getUnitElevatorList(CaseUnitElevator caseUnitElevator) {
        CaseUnitElevatorExample example = new CaseUnitElevatorExample();
        MybatisUtils.convertObj2Example(caseUnitElevator, example);
        return caseUnitElevatorMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseUnitElevator
     * @return
     */
    public boolean addUnitElevator(CaseUnitElevator caseUnitElevator) {
        return caseUnitElevatorMapper.insertSelective(caseUnitElevator) > 0;
    }

    /**
     * 编辑
     * @param caseUnitElevator
     * @return
     */
    public boolean updateUnitElevator(CaseUnitElevator caseUnitElevator) {
        return caseUnitElevatorMapper.updateByPrimaryKeySelective(caseUnitElevator) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnitElevator(Integer id){
        return caseUnitElevatorMapper.deleteByPrimaryKey(id) > 0;
    }

}
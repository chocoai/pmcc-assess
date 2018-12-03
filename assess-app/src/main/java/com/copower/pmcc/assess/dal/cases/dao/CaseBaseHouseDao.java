package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouseExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBaseHouseMapper;
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
public class CaseBaseHouseDao {
    @Autowired
    private CaseBaseHouseMapper caseBaseHouseMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBaseHouse getBaseHouseById(Integer id) {
        return caseBaseHouseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBaseHouse
     * @return
     */
    public List<CaseBaseHouse> getBaseHouseList(CaseBaseHouse caseBaseHouse) {
        CaseBaseHouseExample example = new CaseBaseHouseExample();
        MybatisUtils.convertObj2Example(caseBaseHouse, example);
        return caseBaseHouseMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBaseHouse
     * @return
     */
    public boolean addBaseHouse(CaseBaseHouse caseBaseHouse) {
        return caseBaseHouseMapper.insertSelective(caseBaseHouse) > 0;
    }

    /**
     * 编辑
     * @param caseBaseHouse
     * @return
     */
    public boolean updateBaseHouse(CaseBaseHouse caseBaseHouse) {
        return caseBaseHouseMapper.updateByPrimaryKeySelective(caseBaseHouse) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBaseHouse(Integer id){
        return caseBaseHouseMapper.deleteByPrimaryKey(id) > 0;
    }

}
package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class CaseHouseDao {
    @Autowired
    private CaseHouseMapper caseHouseMapper;

    public List<CaseHouse> autoCompleteCaseHouse(Integer unitId,String houseNumber)throws SQLException{
        CaseHouseExample example = new CaseHouseExample();
        CaseHouseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotEmpty(houseNumber)){
            criteria.andHouseNumberLike(String.format("%s%s%s","%",houseNumber,"%"));
        }
        if (unitId != null){
            criteria.andUnitIdEqualTo(unitId);
        }
        return caseHouseMapper.selectByExample(example);
    }

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouse getHouseById(Integer id) {
        return caseHouseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouse
     * @return
     */
    public List<CaseHouse> getHouseList(CaseHouse caseHouse) {
        CaseHouseExample example = new CaseHouseExample();
        MybatisUtils.convertObj2Example(caseHouse, example);
        return caseHouseMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouse
     * @return
     */
    public Integer addHouse(CaseHouse caseHouse) {
        caseHouseMapper.insertSelective(caseHouse);
        return caseHouse.getId();
    }

    /**
     * 编辑
     * @param caseHouse
     * @return
     */
    public boolean updateHouse(CaseHouse caseHouse) {
        return caseHouseMapper.updateByPrimaryKeySelective(caseHouse) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouse(Integer id){
        return caseHouseMapper.deleteByPrimaryKey(id) > 0;
    }

}
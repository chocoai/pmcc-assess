package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreet;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreetExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseFaceStreetMapper;
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
public class CaseHouseFaceStreetDao {
    @Autowired
    private CaseHouseFaceStreetMapper caseHouseFaceStreetMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseFaceStreet getHouseFaceStreetById(Integer id) {
        return caseHouseFaceStreetMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseFaceStreet
     * @return
     */
    public List<CaseHouseFaceStreet> getHouseFaceStreetList(CaseHouseFaceStreet caseHouseFaceStreet) {
        CaseHouseFaceStreetExample example = new CaseHouseFaceStreetExample();
        MybatisUtils.convertObj2Example(caseHouseFaceStreet, example);
        return caseHouseFaceStreetMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseFaceStreet
     * @return
     */
    public boolean addHouseFaceStreet(CaseHouseFaceStreet caseHouseFaceStreet) {
        return caseHouseFaceStreetMapper.insertSelective(caseHouseFaceStreet) > 0;
    }

    /**
     * 编辑
     * @param caseHouseFaceStreet
     * @return
     */
    public boolean updateHouseFaceStreet(CaseHouseFaceStreet caseHouseFaceStreet) {
        return caseHouseFaceStreetMapper.updateByPrimaryKeySelective(caseHouseFaceStreet) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseFaceStreet(Integer id){
        return caseHouseFaceStreetMapper.deleteByPrimaryKey(id) > 0;
    }

}
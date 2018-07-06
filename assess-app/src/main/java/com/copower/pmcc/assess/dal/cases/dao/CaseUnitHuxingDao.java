package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseUnitHuxingMapper;
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
public class CaseUnitHuxingDao {
    @Autowired
    private CaseUnitHuxingMapper caseUnitHuxingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseUnitHuxing getUnitHuxingById(Integer id) {
        return caseUnitHuxingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseUnitHuxing
     * @return
     */
    public List<CaseUnitHuxing> getUnitHuxingList(CaseUnitHuxing caseUnitHuxing) {
        CaseUnitHuxingExample example = new CaseUnitHuxingExample();
        MybatisUtils.convertObj2Example(caseUnitHuxing, example);
        return caseUnitHuxingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseUnitHuxing
     * @return
     */
    public boolean addUnitHuxing(CaseUnitHuxing caseUnitHuxing) {
        return caseUnitHuxingMapper.insertSelective(caseUnitHuxing) > 0;
    }

    /**
     * 编辑
     * @param caseUnitHuxing
     * @return
     */
    public boolean updateUnitHuxing(CaseUnitHuxing caseUnitHuxing) {
        return caseUnitHuxingMapper.updateByPrimaryKeySelective(caseUnitHuxing) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnitHuxing(Integer id){
        return caseUnitHuxingMapper.deleteByPrimaryKey(id) > 0;
    }

}
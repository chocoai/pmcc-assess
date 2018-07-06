package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironment;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironmentExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingEnvironmentMapper;
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
public class CaseMatchingEnvironmentDao {
    @Autowired
    private CaseMatchingEnvironmentMapper caseMatchingEnvironmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingEnvironment getMatchingEnvironmentById(Integer id) {
        return caseMatchingEnvironmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingEnvironment
     * @return
     */
    public List<CaseMatchingEnvironment> getMatchingEnvironmentList(CaseMatchingEnvironment caseMatchingEnvironment) {
        CaseMatchingEnvironmentExample example = new CaseMatchingEnvironmentExample();
        MybatisUtils.convertObj2Example(caseMatchingEnvironment, example);
        return caseMatchingEnvironmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingEnvironment
     * @return
     */
    public boolean addMatchingEnvironment(CaseMatchingEnvironment caseMatchingEnvironment) {
        return caseMatchingEnvironmentMapper.insertSelective(caseMatchingEnvironment) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingEnvironment
     * @return
     */
    public boolean updateMatchingEnvironment(CaseMatchingEnvironment caseMatchingEnvironment) {
        return caseMatchingEnvironmentMapper.updateByPrimaryKeySelective(caseMatchingEnvironment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingEnvironment(Integer id){
        return caseMatchingEnvironmentMapper.deleteByPrimaryKey(id) > 0;
    }

}
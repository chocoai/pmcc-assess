package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEducation;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEducationExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingEducationMapper;
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
public class CaseMatchingEducationDao {
    @Autowired
    private CaseMatchingEducationMapper caseMatchingEducationMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingEducation getMatchingEducationById(Integer id) {
        return caseMatchingEducationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingEducation
     * @return
     */
    public List<CaseMatchingEducation> getMatchingEducationList(CaseMatchingEducation caseMatchingEducation) {
        CaseMatchingEducationExample example = new CaseMatchingEducationExample();
        MybatisUtils.convertObj2Example(caseMatchingEducation, example);
        return caseMatchingEducationMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingEducation
     * @return
     */
    public boolean addMatchingEducation(CaseMatchingEducation caseMatchingEducation) {
        return caseMatchingEducationMapper.insertSelective(caseMatchingEducation) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingEducation
     * @return
     */
    public boolean updateMatchingEducation(CaseMatchingEducation caseMatchingEducation) {
        return caseMatchingEducationMapper.updateByPrimaryKeySelective(caseMatchingEducation) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingEducation(Integer id){
        return caseMatchingEducationMapper.deleteByPrimaryKey(id) > 0;
    }

}
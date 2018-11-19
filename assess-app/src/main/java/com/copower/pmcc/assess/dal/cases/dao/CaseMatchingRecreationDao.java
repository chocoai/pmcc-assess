package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedicalExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRecreation;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRecreationExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingRecreationMapper;
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
public class CaseMatchingRecreationDao {
    @Autowired
    private CaseMatchingRecreationMapper caseMatchingRecreationMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingRecreation getMatchingRecreationById(Integer id) {
        return caseMatchingRecreationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingRecreation
     * @return
     */
    public List<CaseMatchingRecreation> getMatchingRecreationList(CaseMatchingRecreation caseMatchingRecreation) {
        CaseMatchingRecreationExample example = new CaseMatchingRecreationExample();
        MybatisUtils.convertObj2Example(caseMatchingRecreation, example);
        return caseMatchingRecreationMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingRecreation
     * @return
     */
    public boolean addMatchingRecreation(CaseMatchingRecreation caseMatchingRecreation) {
        return caseMatchingRecreationMapper.insertSelective(caseMatchingRecreation) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingRecreation
     * @return
     */
    public boolean updateMatchingRecreation(CaseMatchingRecreation caseMatchingRecreation) {
        return caseMatchingRecreationMapper.updateByPrimaryKeySelective(caseMatchingRecreation) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingRecreation(Integer id){
        return caseMatchingRecreationMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param estateId
     * @return
     */
    public int countByEstateId(Integer estateId){
        CaseMatchingRecreationExample example = new CaseMatchingRecreationExample();
        example.createCriteria().andEstateIdEqualTo(estateId);
        return caseMatchingRecreationMapper.countByExample(example);
    }
}
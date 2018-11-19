package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterialExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedical;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedicalExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingMedicalMapper;
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
public class CaseMatchingMedicalDao {
    @Autowired
    private CaseMatchingMedicalMapper caseMatchingMedicalMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingMedical getMatchingMedicalById(Integer id) {
        return caseMatchingMedicalMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingMedical
     * @return
     */
    public List<CaseMatchingMedical> getMatchingMedicalList(CaseMatchingMedical caseMatchingMedical) {
        CaseMatchingMedicalExample example = new CaseMatchingMedicalExample();
        MybatisUtils.convertObj2Example(caseMatchingMedical, example);
        return caseMatchingMedicalMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingMedical
     * @return
     */
    public boolean addMatchingMedical(CaseMatchingMedical caseMatchingMedical) {
        return caseMatchingMedicalMapper.insertSelective(caseMatchingMedical) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingMedical
     * @return
     */
    public boolean updateMatchingMedical(CaseMatchingMedical caseMatchingMedical) {
        return caseMatchingMedicalMapper.updateByPrimaryKeySelective(caseMatchingMedical) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMedical(Integer id){
        return caseMatchingMedicalMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param estateId
     * @return
     */
    public int countByEstateId(Integer estateId){
        CaseMatchingMedicalExample example = new CaseMatchingMedicalExample();
        example.createCriteria().andEstateIdEqualTo(estateId);
        return caseMatchingMedicalMapper.countByExample(example);
    }
}
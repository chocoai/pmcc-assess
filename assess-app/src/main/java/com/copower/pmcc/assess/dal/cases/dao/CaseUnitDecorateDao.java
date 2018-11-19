package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedicalExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitDecorate;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitDecorateExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseUnitDecorateMapper;
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
public class CaseUnitDecorateDao {
    @Autowired
    private CaseUnitDecorateMapper caseUnitDecorateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseUnitDecorate getUnitDecorateById(Integer id) {
        return caseUnitDecorateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseUnitDecorate
     * @return
     */
    public List<CaseUnitDecorate> getUnitDecorateList(CaseUnitDecorate caseUnitDecorate) {
        CaseUnitDecorateExample example = new CaseUnitDecorateExample();
        MybatisUtils.convertObj2Example(caseUnitDecorate, example);
        return caseUnitDecorateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseUnitDecorate
     * @return
     */
    public boolean addUnitDecorate(CaseUnitDecorate caseUnitDecorate) {
        return caseUnitDecorateMapper.insertSelective(caseUnitDecorate) > 0;
    }

    /**
     * 编辑
     * @param caseUnitDecorate
     * @return
     */
    public boolean updateUnitDecorate(CaseUnitDecorate caseUnitDecorate) {
        return caseUnitDecorateMapper.updateByPrimaryKeySelective(caseUnitDecorate) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnitDecorate(Integer id){
        return caseUnitDecorateMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param unitId
     * @return
     */
    public int countByUnitId(Integer unitId){
        CaseUnitDecorateExample example = new CaseUnitDecorateExample();
        example.createCriteria().andUnitIdEqualTo(unitId);
        return caseUnitDecorateMapper.countByExample(example);
    }
}
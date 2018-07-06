package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterial;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterialExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingMaterialMapper;
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
public class CaseMatchingMaterialDao {
    @Autowired
    private CaseMatchingMaterialMapper caseMatchingMaterialMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingMaterial getMatchingMaterialById(Integer id) {
        return caseMatchingMaterialMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingMaterial
     * @return
     */
    public List<CaseMatchingMaterial> getMatchingMaterialList(CaseMatchingMaterial caseMatchingMaterial) {
        CaseMatchingMaterialExample example = new CaseMatchingMaterialExample();
        MybatisUtils.convertObj2Example(caseMatchingMaterial, example);
        return caseMatchingMaterialMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingMaterial
     * @return
     */
    public boolean addMatchingMaterial(CaseMatchingMaterial caseMatchingMaterial) {
        return caseMatchingMaterialMapper.insertSelective(caseMatchingMaterial) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingMaterial
     * @return
     */
    public boolean updateMatchingMaterial(CaseMatchingMaterial caseMatchingMaterial) {
        return caseMatchingMaterialMapper.updateByPrimaryKeySelective(caseMatchingMaterial) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMaterial(Integer id){
        return caseMatchingMaterialMapper.deleteByPrimaryKey(id) > 0;
    }

}
package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterial;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterialExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingMaterialMapper;
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
public class ExamineMatchingMaterialDao {
    @Autowired
    private ExamineMatchingMaterialMapper examineMatchingMaterialMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingMaterial getMatchingMaterialById(Integer id) {
        return examineMatchingMaterialMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingMaterial
     * @return
     */
    public List<ExamineMatchingMaterial> getMatchingMaterialList(ExamineMatchingMaterial examineMatchingMaterial) {
        ExamineMatchingMaterialExample example = new ExamineMatchingMaterialExample();
        MybatisUtils.convertObj2Example(examineMatchingMaterial, example);
        return examineMatchingMaterialMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingMaterial
     * @return
     */
    public boolean addMatchingMaterial(ExamineMatchingMaterial examineMatchingMaterial) {
        return examineMatchingMaterialMapper.insertSelective(examineMatchingMaterial) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingMaterial
     * @return
     */
    public boolean updateMatchingMaterial(ExamineMatchingMaterial examineMatchingMaterial) {
        return examineMatchingMaterialMapper.updateByPrimaryKeySelective(examineMatchingMaterial) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMaterial(Integer id){
        return examineMatchingMaterialMapper.deleteByPrimaryKey(id) > 0;
    }

}
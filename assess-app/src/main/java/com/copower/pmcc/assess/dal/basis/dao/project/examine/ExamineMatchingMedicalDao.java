package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedical;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedicalExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingMedicalMapper;
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
public class ExamineMatchingMedicalDao {
    @Autowired
    private ExamineMatchingMedicalMapper examineExamineMatchingMedicalMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingMedical getExamineMatchingMedicalById(Integer id) {
        return examineExamineMatchingMedicalMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingMedical
     * @return
     */
    public List<ExamineMatchingMedical> getExamineMatchingMedicalList(ExamineMatchingMedical examineMatchingMedical) {
        ExamineMatchingMedicalExample example = new ExamineMatchingMedicalExample();
        MybatisUtils.convertObj2Example(examineMatchingMedical, example);
        return examineExamineMatchingMedicalMapper.selectByExample(example);
    }

    public List<ExamineMatchingMedical> getExamineMatchingMedicalList(Integer planDetailsId) {
        ExamineMatchingMedicalExample example = new ExamineMatchingMedicalExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return examineExamineMatchingMedicalMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineExamineMatchingMedical
     * @return
     */
    public boolean addExamineMatchingMedical(ExamineMatchingMedical examineExamineMatchingMedical) {
        return examineExamineMatchingMedicalMapper.insertSelective(examineExamineMatchingMedical) > 0;
    }

    /**
     * 编辑
     * @param examineExamineMatchingMedical
     * @return
     */
    public boolean updateExamineMatchingMedical(ExamineMatchingMedical examineExamineMatchingMedical) {
        return examineExamineMatchingMedicalMapper.updateByPrimaryKeySelective(examineExamineMatchingMedical) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingMedical(Integer id){
        return examineExamineMatchingMedicalMapper.deleteByPrimaryKey(id) > 0;
    }

}
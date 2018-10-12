package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducation;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducationExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingEducationMapper;
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
public class ExamineMatchingEducationDao {
    @Autowired
    private ExamineMatchingEducationMapper examineMatchingEducationMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingEducation getMatchingEducationById(Integer id) {
        return examineMatchingEducationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingEducation
     * @return
     */
    public List<ExamineMatchingEducation> getMatchingEducationList(ExamineMatchingEducation examineMatchingEducation) {
        ExamineMatchingEducationExample example = new ExamineMatchingEducationExample();
        MybatisUtils.convertObj2Example(examineMatchingEducation, example);
        return examineMatchingEducationMapper.selectByExample(example);
    }

    public List<ExamineMatchingEducation> getMatchingEducationList(Integer planDetailsId) {
        ExamineMatchingEducationExample example = new ExamineMatchingEducationExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return examineMatchingEducationMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingEducation
     * @return
     */
    public boolean addMatchingEducation(ExamineMatchingEducation examineMatchingEducation) {
        return examineMatchingEducationMapper.insertSelective(examineMatchingEducation) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingEducation
     * @return
     */
    public boolean updateMatchingEducation(ExamineMatchingEducation examineMatchingEducation) {
        return examineMatchingEducationMapper.updateByPrimaryKeySelective(examineMatchingEducation) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingEducation(Integer id){
        return examineMatchingEducationMapper.deleteByPrimaryKey(id) > 0;
    }

}
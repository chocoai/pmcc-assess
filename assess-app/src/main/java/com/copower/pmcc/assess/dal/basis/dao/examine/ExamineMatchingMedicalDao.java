package com.copower.pmcc.assess.dal.basis.dao.examine;

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
    private ExamineMatchingMedicalMapper examineMatchingMedicalMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingMedical getMatchingMedicalById(Integer id) {
        return examineMatchingMedicalMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingMedical
     * @return
     */
    public List<ExamineMatchingMedical> getMatchingMedicalList(ExamineMatchingMedical examineMatchingMedical) {
        ExamineMatchingMedicalExample example = new ExamineMatchingMedicalExample();
        MybatisUtils.convertObj2Example(examineMatchingMedical, example);
        return examineMatchingMedicalMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingMedical
     * @return
     */
    public boolean addMatchingMedical(ExamineMatchingMedical examineMatchingMedical) {
        return examineMatchingMedicalMapper.insertSelective(examineMatchingMedical) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingMedical
     * @return
     */
    public boolean updateMatchingMedical(ExamineMatchingMedical examineMatchingMedical) {
        return examineMatchingMedicalMapper.updateByPrimaryKeySelective(examineMatchingMedical) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMedical(Integer id){
        return examineMatchingMedicalMapper.deleteByPrimaryKey(id) > 0;
    }

}
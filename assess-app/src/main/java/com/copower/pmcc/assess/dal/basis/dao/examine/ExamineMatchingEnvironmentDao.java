package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironment;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingEnvironmentMapper;
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
public class ExamineMatchingEnvironmentDao {
    @Autowired
    private ExamineMatchingEnvironmentMapper examineMatchingEnvironmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingEnvironment getMatchingEnvironmentById(Integer id) {
        return examineMatchingEnvironmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingEnvironment
     * @return
     */
    public List<ExamineMatchingEnvironment> getMatchingEnvironmentList(ExamineMatchingEnvironment examineMatchingEnvironment) {
        ExamineMatchingEnvironmentExample example = new ExamineMatchingEnvironmentExample();
        MybatisUtils.convertObj2Example(examineMatchingEnvironment, example);
        return examineMatchingEnvironmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingEnvironment
     * @return
     */
    public boolean addMatchingEnvironment(ExamineMatchingEnvironment examineMatchingEnvironment) {
        return examineMatchingEnvironmentMapper.insertSelective(examineMatchingEnvironment) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingEnvironment
     * @return
     */
    public boolean updateMatchingEnvironment(ExamineMatchingEnvironment examineMatchingEnvironment) {
        return examineMatchingEnvironmentMapper.updateByPrimaryKeySelective(examineMatchingEnvironment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingEnvironment(Integer id){
        return examineMatchingEnvironmentMapper.deleteByPrimaryKey(id) > 0;
    }

}
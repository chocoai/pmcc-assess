package com.copower.pmcc.assess.dal.basis.dao.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTaskExample;
import com.copower.pmcc.assess.dal.basis.mapper.SurveyExamineTaskMapper;
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
public class SurveyExamineTaskDao {
    @Autowired
    private SurveyExamineTaskMapper surveyExamineTaskMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SurveyExamineTask getSurveyExamineTaskById(Integer id) {
        return surveyExamineTaskMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param surveyExamineTask
     * @return
     */
    public List<SurveyExamineTask> getSurveyExamineTaskList(SurveyExamineTask surveyExamineTask) {
        SurveyExamineTaskExample example = new SurveyExamineTaskExample();
        MybatisUtils.convertObj2Example(surveyExamineTask, example);
        example.setOrderByClause("sorting");
        return surveyExamineTaskMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param surveyExamineTask
     * @return
     */
    public boolean addSurveyExamineTask(SurveyExamineTask surveyExamineTask) {
        return surveyExamineTaskMapper.insertSelective(surveyExamineTask) > 0;
    }

    /**
     * 编辑
     *
     * @param surveyExamineTask
     * @return
     */
    public boolean updateSurveyExamineTask(SurveyExamineTask surveyExamineTask) {
        return surveyExamineTaskMapper.updateByPrimaryKeySelective(surveyExamineTask) > 0;
    }

    /**
     * 编辑
     *
     * @param surveyExamineTask
     * @return
     */
    public boolean updateSurveyExamineTask(SurveyExamineTask surveyExamineTask,SurveyExamineTask where) {
        SurveyExamineTaskExample example = new SurveyExamineTaskExample();
        MybatisUtils.convertObj2Example(where, example);
        return surveyExamineTaskMapper.updateByExampleSelective(surveyExamineTask,example) > 0;
    }

    public int getSurveyExamineTaskCount(SurveyExamineTask surveyExamineTask){
        SurveyExamineTaskExample example = new SurveyExamineTaskExample();
        MybatisUtils.convertObj2Example(surveyExamineTask, example);
        return surveyExamineTaskMapper.countByExample(example);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteSurveyExamineTask(Integer id) {
        return surveyExamineTaskMapper.deleteByPrimaryKey(id) > 0;
    }


    public boolean deleteSurveyExamineTask(SurveyExamineTask surveyExamineTask) {
        SurveyExamineTaskExample example = new SurveyExamineTaskExample();
        MybatisUtils.convertObj2Example(surveyExamineTask, example);
        return surveyExamineTaskMapper.deleteByExample(example) > 0;
    }
}
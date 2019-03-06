package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;
import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssignExample;
import com.copower.pmcc.assess.dal.basis.mapper.TaskCaseAssignMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/13 09:18
 */
@Repository
public class TaskCaseAssignDao {
    @Autowired
    private TaskCaseAssignMapper taskCaseAssignMapper;

    public List<TaskCaseAssign> getTaskCaseAssignList(String executor) {
        TaskCaseAssignExample example = new TaskCaseAssignExample();
        TaskCaseAssignExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(executor)) {
            criteria.andExecutorLike("%" + executor + "%");
        }
        example.setOrderByClause("id desc");
        return taskCaseAssignMapper.selectByExample(example);
    }

    public List<TaskCaseAssign> getListObject(TaskCaseAssign taskCaseAssign) {
        TaskCaseAssignExample example = new TaskCaseAssignExample();
        MybatisUtils.convertObj2Example(taskCaseAssign, example);
        return taskCaseAssignMapper.selectByExample(example);
    }

    public TaskCaseAssign getSingleObject(TaskCaseAssign taskCaseAssign) {
        TaskCaseAssignExample example = new TaskCaseAssignExample();
        MybatisUtils.convertObj2Example(taskCaseAssign, example);
        List<TaskCaseAssign> vacationTypeList = taskCaseAssignMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public TaskCaseAssign getSingleObject(Integer id) {
        return taskCaseAssignMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(TaskCaseAssign bidCostInfo) {
        return taskCaseAssignMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(TaskCaseAssign bidCostInfo) {
        return taskCaseAssignMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return taskCaseAssignMapper.deleteByPrimaryKey(id) == 1;
    }
}

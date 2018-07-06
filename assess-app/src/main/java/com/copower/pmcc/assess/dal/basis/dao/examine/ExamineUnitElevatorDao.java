package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitElevator;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitElevatorExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineUnitElevatorMapper;
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
public class ExamineUnitElevatorDao {
    @Autowired
    private ExamineUnitElevatorMapper examineUnitElevatorMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineUnitElevator getUnitElevatorById(Integer id) {
        return examineUnitElevatorMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineUnitElevator
     * @return
     */
    public List<ExamineUnitElevator> getUnitElevatorList(ExamineUnitElevator examineUnitElevator) {
        ExamineUnitElevatorExample example = new ExamineUnitElevatorExample();
        MybatisUtils.convertObj2Example(examineUnitElevator, example);
        return examineUnitElevatorMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineUnitElevator
     * @return
     */
    public boolean addUnitElevator(ExamineUnitElevator examineUnitElevator) {
        return examineUnitElevatorMapper.insertSelective(examineUnitElevator) > 0;
    }

    /**
     * 编辑
     * @param examineUnitElevator
     * @return
     */
    public boolean updateUnitElevator(ExamineUnitElevator examineUnitElevator) {
        return examineUnitElevatorMapper.updateByPrimaryKeySelective(examineUnitElevator) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnitElevator(Integer id){
        return examineUnitElevatorMapper.deleteByPrimaryKey(id) > 0;
    }

}
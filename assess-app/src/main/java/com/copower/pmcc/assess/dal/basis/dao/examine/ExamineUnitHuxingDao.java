package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxingExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineUnitHuxingMapper;
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
public class ExamineUnitHuxingDao {
    @Autowired
    private ExamineUnitHuxingMapper examineUnitHuxingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineUnitHuxing getUnitHuxingById(Integer id) {
        return examineUnitHuxingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineUnitHuxing
     * @return
     */
    public List<ExamineUnitHuxing> getUnitHuxingList(ExamineUnitHuxing examineUnitHuxing) {
        ExamineUnitHuxingExample example = new ExamineUnitHuxingExample();
        MybatisUtils.convertObj2Example(examineUnitHuxing, example);
        return examineUnitHuxingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineUnitHuxing
     * @return
     */
    public boolean addUnitHuxing(ExamineUnitHuxing examineUnitHuxing) {
        return examineUnitHuxingMapper.insertSelective(examineUnitHuxing) > 0;
    }

    public int saveReturnID(ExamineUnitHuxing examineUnitHuxing){
        examineUnitHuxingMapper.insertSelective(examineUnitHuxing);
        return examineUnitHuxing.getId();
    }

    /**
     * 编辑
     * @param examineUnitHuxing
     * @return
     */
    public boolean updateUnitHuxing(ExamineUnitHuxing examineUnitHuxing) {
        return examineUnitHuxingMapper.updateByPrimaryKeySelective(examineUnitHuxing) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnitHuxing(Integer id){
        return examineUnitHuxingMapper.deleteByPrimaryKey(id) > 0;
    }

}
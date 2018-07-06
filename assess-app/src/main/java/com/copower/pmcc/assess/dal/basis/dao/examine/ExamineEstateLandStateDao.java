package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandState;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandStateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateLandStateMapper;
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
public class ExamineEstateLandStateDao {
    @Autowired
    private ExamineEstateLandStateMapper examineEstateLandStateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateLandState getEstateLandStateById(Integer id) {
        return examineEstateLandStateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateLandState
     * @return
     */
    public List<ExamineEstateLandState> getEstateLandStateList(ExamineEstateLandState examineEstateLandState) {
        ExamineEstateLandStateExample example = new ExamineEstateLandStateExample();
        MybatisUtils.convertObj2Example(examineEstateLandState, example);
        return examineEstateLandStateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstateLandState
     * @return
     */
    public boolean addEstateLandState(ExamineEstateLandState examineEstateLandState) {
        return examineEstateLandStateMapper.insertSelective(examineEstateLandState) > 0;
    }

    /**
     * 编辑
     * @param examineEstateLandState
     * @return
     */
    public boolean updateEstateLandState(ExamineEstateLandState examineEstateLandState) {
        return examineEstateLandStateMapper.updateByPrimaryKeySelective(examineEstateLandState) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateLandState(Integer id){
        return examineEstateLandStateMapper.deleteByPrimaryKey(id) > 0;
    }

}
package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSuppyHeating;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSuppyHeatingExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateSuppyHeatingMapper;
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
public class ExamineEstateSuppyHeatingDao {
    @Autowired
    private ExamineEstateSuppyHeatingMapper examineEstateSuppyHeatingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateSuppyHeating getEstateSuppyHeatingById(Integer id) {
        return examineEstateSuppyHeatingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateSuppyHeating
     * @return
     */
    public List<ExamineEstateSuppyHeating> getEstateSuppyHeatingList(ExamineEstateSuppyHeating examineEstateSuppyHeating) {
        ExamineEstateSuppyHeatingExample example = new ExamineEstateSuppyHeatingExample();
        MybatisUtils.convertObj2Example(examineEstateSuppyHeating, example);
        return examineEstateSuppyHeatingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstateSuppyHeating
     * @return
     */
    public boolean addEstateSuppyHeating(ExamineEstateSuppyHeating examineEstateSuppyHeating) {
        return examineEstateSuppyHeatingMapper.insertSelective(examineEstateSuppyHeating) > 0;
    }

    /**
     * 编辑
     * @param examineEstateSuppyHeating
     * @return
     */
    public boolean updateEstateSuppyHeating(ExamineEstateSuppyHeating examineEstateSuppyHeating) {
        return examineEstateSuppyHeatingMapper.updateByPrimaryKeySelective(examineEstateSuppyHeating) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateSuppyHeating(Integer id){
        return examineEstateSuppyHeatingMapper.deleteByPrimaryKey(id) > 0;
    }

}
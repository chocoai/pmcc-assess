package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilder;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilderExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBuilderMapper;
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
public class ExamineBuilderDao {
    @Autowired
    private ExamineBuilderMapper examineBuilderMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBuilder getBuilderById(Integer id) {
        return examineBuilderMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineBuilder
     * @return
     */
    public List<ExamineBuilder> getBuilderList(ExamineBuilder examineBuilder) {
        ExamineBuilderExample example = new ExamineBuilderExample();
        MybatisUtils.convertObj2Example(examineBuilder, example);
        return examineBuilderMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBuilder
     * @return
     */
    public boolean addBuilder(ExamineBuilder examineBuilder) {
        return examineBuilderMapper.insertSelective(examineBuilder) > 0;
    }

    /**
     * 编辑
     * @param examineBuilder
     * @return
     */
    public boolean updateBuilder(ExamineBuilder examineBuilder) {
        return examineBuilderMapper.updateByPrimaryKeySelective(examineBuilder) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuilder(Integer id){
        return examineBuilderMapper.deleteByPrimaryKey(id) > 0;
    }

}
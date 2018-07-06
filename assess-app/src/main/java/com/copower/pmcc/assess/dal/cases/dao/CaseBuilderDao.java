package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilder;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilderExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuilderMapper;
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
public class CaseBuilderDao {
    @Autowired
    private CaseBuilderMapper caseBuilderMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBuilder getBuilderById(Integer id) {
        return caseBuilderMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBuilder
     * @return
     */
    public List<CaseBuilder> getBuilderList(CaseBuilder caseBuilder) {
        CaseBuilderExample example = new CaseBuilderExample();
        MybatisUtils.convertObj2Example(caseBuilder, example);
        return caseBuilderMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBuilder
     * @return
     */
    public boolean addBuilder(CaseBuilder caseBuilder) {
        return caseBuilderMapper.insertSelective(caseBuilder) > 0;
    }

    /**
     * 编辑
     * @param caseBuilder
     * @return
     */
    public boolean updateBuilder(CaseBuilder caseBuilder) {
        return caseBuilderMapper.updateByPrimaryKeySelective(caseBuilder) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuilder(Integer id){
        return caseBuilderMapper.deleteByPrimaryKey(id) > 0;
    }

}
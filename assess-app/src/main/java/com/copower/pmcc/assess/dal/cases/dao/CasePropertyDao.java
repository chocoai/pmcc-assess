package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseProperty;
import com.copower.pmcc.assess.dal.cases.entity.CasePropertyExample;
import com.copower.pmcc.assess.dal.cases.mapper.CasePropertyMapper;
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
public class CasePropertyDao {
    @Autowired
    private CasePropertyMapper casePropertyMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseProperty getPropertyById(Integer id) {
        return casePropertyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseProperty
     * @return
     */
    public List<CaseProperty> getPropertyList(CaseProperty caseProperty) {
        CasePropertyExample example = new CasePropertyExample();
        MybatisUtils.convertObj2Example(caseProperty, example);
        return casePropertyMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseProperty
     * @return
     */
    public boolean addProperty(CaseProperty caseProperty) {
        return casePropertyMapper.insertSelective(caseProperty) > 0;
    }

    /**
     * 编辑
     * @param caseProperty
     * @return
     */
    public boolean updateProperty(CaseProperty caseProperty) {
        return casePropertyMapper.updateByPrimaryKeySelective(caseProperty) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteProperty(Integer id){
        return casePropertyMapper.deleteByPrimaryKey(id) > 0;
    }

}
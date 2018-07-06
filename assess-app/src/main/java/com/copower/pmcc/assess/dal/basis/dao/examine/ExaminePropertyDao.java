package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineProperty;
import com.copower.pmcc.assess.dal.basis.entity.ExaminePropertyExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExaminePropertyMapper;
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
public class ExaminePropertyDao {
    @Autowired
    private ExaminePropertyMapper examinePropertyMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineProperty getPropertyById(Integer id) {
        return examinePropertyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineProperty
     * @return
     */
    public List<ExamineProperty> getPropertyList(ExamineProperty examineProperty) {
        ExaminePropertyExample example = new ExaminePropertyExample();
        MybatisUtils.convertObj2Example(examineProperty, example);
        return examinePropertyMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineProperty
     * @return
     */
    public boolean addProperty(ExamineProperty examineProperty) {
        return examinePropertyMapper.insertSelective(examineProperty) > 0;
    }

    /**
     * 编辑
     * @param examineProperty
     * @return
     */
    public boolean updateProperty(ExamineProperty examineProperty) {
        return examinePropertyMapper.updateByPrimaryKeySelective(examineProperty) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteProperty(Integer id){
        return examinePropertyMapper.deleteByPrimaryKey(id) > 0;
    }

}
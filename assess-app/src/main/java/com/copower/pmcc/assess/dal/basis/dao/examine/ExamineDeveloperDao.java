package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.ExamineDeveloperExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineDeveloperMapper;
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
public class ExamineDeveloperDao {
    @Autowired
    private ExamineDeveloperMapper examineDeveloperMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineDeveloper getDeveloperById(Integer id) {
        return examineDeveloperMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineDeveloper
     * @return
     */
    public List<ExamineDeveloper> getDeveloperList(ExamineDeveloper examineDeveloper) {
        ExamineDeveloperExample example = new ExamineDeveloperExample();
        MybatisUtils.convertObj2Example(examineDeveloper, example);
        return examineDeveloperMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineDeveloper
     * @return
     */
    public boolean addDeveloper(ExamineDeveloper examineDeveloper) {
        return examineDeveloperMapper.insertSelective(examineDeveloper) > 0;
    }

    /**
     * 编辑
     * @param examineDeveloper
     * @return
     */
    public boolean updateDeveloper(ExamineDeveloper examineDeveloper) {
        return examineDeveloperMapper.updateByPrimaryKeySelective(examineDeveloper) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteDeveloper(Integer id){
        return examineDeveloperMapper.deleteByPrimaryKey(id) > 0;
    }

}
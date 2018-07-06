package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseDeveloper;
import com.copower.pmcc.assess.dal.cases.entity.CaseDeveloperExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseDeveloperMapper;
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
public class CaseDeveloperDao {
    @Autowired
    private CaseDeveloperMapper caseDeveloperMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseDeveloper getDeveloperById(Integer id) {
        return caseDeveloperMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseDeveloper
     * @return
     */
    public List<CaseDeveloper> getDeveloperList(CaseDeveloper caseDeveloper) {
        CaseDeveloperExample example = new CaseDeveloperExample();
        MybatisUtils.convertObj2Example(caseDeveloper, example);
        return caseDeveloperMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseDeveloper
     * @return
     */
    public boolean addDeveloper(CaseDeveloper caseDeveloper) {
        return caseDeveloperMapper.insertSelective(caseDeveloper) > 0;
    }

    /**
     * 编辑
     * @param caseDeveloper
     * @return
     */
    public boolean updateDeveloper(CaseDeveloper caseDeveloper) {
        return caseDeveloperMapper.updateByPrimaryKeySelective(caseDeveloper) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteDeveloper(Integer id){
        return caseDeveloperMapper.deleteByPrimaryKey(id) > 0;
    }

}
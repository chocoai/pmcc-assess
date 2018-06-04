package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseProjectCategory;
import com.copower.pmcc.assess.dal.entity.BaseProjectCategoryExample;
import com.copower.pmcc.assess.dal.mapper.BaseProjectCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/24
 * @time: 14:13
 */
@Repository
public class BaseProjectCategoryDao {
    @Autowired
    private BaseProjectCategoryMapper baseProjectCategoryMapper;

    public BaseProjectCategory getBidProjectCategoryById(Integer id) {
        return baseProjectCategoryMapper.selectByPrimaryKey(id);
    }

    public List<BaseProjectCategory> getBidProjectCategoryListByPid(Integer pid) {
        BaseProjectCategoryExample example = new BaseProjectCategoryExample();
        example.createCriteria().andBisEnableEqualTo(true).andPidEqualTo(pid);
        return baseProjectCategoryMapper.selectByExample(example);
    }
}

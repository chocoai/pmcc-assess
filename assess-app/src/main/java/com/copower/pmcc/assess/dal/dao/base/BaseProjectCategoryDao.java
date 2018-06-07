package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseProjectCategory;
import com.copower.pmcc.assess.dal.entity.BaseProjectCategoryExample;
import com.copower.pmcc.assess.dal.mapper.BaseProjectCategoryMapper;
import org.apache.commons.collections.CollectionUtils;
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

    public BaseProjectCategory getProjectCategoryById(Integer id) {
        return baseProjectCategoryMapper.selectByPrimaryKey(id);
    }

    public List<BaseProjectCategory> getProjectCategoryListByPid(Integer pid) {
        BaseProjectCategoryExample example = new BaseProjectCategoryExample();
        example.createCriteria().andBisEnableEqualTo(true).andPidEqualTo(pid);
        return baseProjectCategoryMapper.selectByExample(example);
    }

    public BaseProjectCategory getProjectCategoryByKey(String key) {
        BaseProjectCategoryExample example = new BaseProjectCategoryExample();
        example.createCriteria().andBisEnableEqualTo(true).andUKeyEqualTo(key);
        List<BaseProjectCategory> projectCategoryList = baseProjectCategoryMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectCategoryList))
            return projectCategoryList.get(0);
        return null;
    }
}

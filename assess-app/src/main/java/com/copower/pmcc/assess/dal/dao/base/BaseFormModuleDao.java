package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.mapper.BaseFormModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zly on 2018/6/14.
 */
@Repository
public class BaseFormModuleDao {
    @Autowired
    private BaseFormModuleMapper baseFormModuleMapper;
    public BaseFormModule getDataById(Integer id) {
        return baseFormModuleMapper.selectByPrimaryKey(id);
    }
}

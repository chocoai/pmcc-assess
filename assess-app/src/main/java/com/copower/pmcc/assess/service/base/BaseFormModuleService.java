package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseFormModuleDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zly on 2018/6/14.
 */
@Service
public class BaseFormModuleService {

    @Autowired
    private BaseFormModuleDao baseFormModuleDao;

    public BaseFormModule getDataById(Integer id) {
       return baseFormModuleDao.getDataById(id);
    }
}

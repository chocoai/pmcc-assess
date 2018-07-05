package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 方案信息
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeInfoDetailService {
    @Autowired
    private SchemeInfoDetailDao dao;

    public int addReturnID(SchemeInfoDetail SchemeInfoDetail) {
        return dao.addReturnID(SchemeInfoDetail);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(SchemeInfoDetail SchemeInfoDetail) {
        return dao.update(SchemeInfoDetail);
    }
}

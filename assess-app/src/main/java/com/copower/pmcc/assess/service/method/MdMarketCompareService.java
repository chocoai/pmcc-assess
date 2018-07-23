package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import org.springframework.stereotype.Service;

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdMarketCompareService {
    private MdMarketCompareDao mdMarketCompareDao;

    public MdMarketCompare getMdMarketCompare(Integer id){
        return mdMarketCompareDao.getMarketCompareById(id);
    }
}

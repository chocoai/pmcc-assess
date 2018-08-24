package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentArchitecturalDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentHypothesisDao;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/8/24 18:48
 * @Description:假设开发法
 */
@Service
public class MdDevelopmentService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdDevelopmentArchitecturalDao mdDevelopmentArchitecturalDao;
    @Autowired
    private MdDevelopmentDao mdDevelopmentDao;
    @Autowired
    private MdDevelopmentHypothesisDao mdDevelopmentHypothesisDao;
    private Logger logger = LoggerFactory.getLogger(getClass());
}

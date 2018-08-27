package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentArchitecturalDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentHypothesisDao;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentArchitectural;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentHypothesis;
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

    public void saveAndUpdateMdDevelopmentHypothesis(MdDevelopmentHypothesis mdDevelopmentHypothesis){
        if (mdDevelopmentHypothesis.getId()==null){
            mdDevelopmentHypothesis.setCreator(commonService.thisUserAccount());
            int id = mdDevelopmentHypothesisDao.addMdDevelopmentHypothesis(mdDevelopmentHypothesis);
        }else {
            mdDevelopmentHypothesisDao.updateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
        }
    }

    public void saveAndUpdateMdDevelopment(MdDevelopment mdDevelopment){
        if (mdDevelopment.getId()==null){
            mdDevelopment.setCreator(commonService.thisUserAccount());
            int id = mdDevelopmentDao.addMdDevelopment(mdDevelopment);
        }else {
            mdDevelopmentDao.updateMdDevelopment(mdDevelopment);
        }
    }

    public void saveAndUpdateMdDevelopmentArchitectural(MdDevelopmentArchitectural mdDevelopmentArchitectural){
        if (mdDevelopmentArchitectural.getId()==null){
            int id = mdDevelopmentArchitecturalDao.addMdDevelopmentArchitectural(mdDevelopmentArchitectural);
        }else {
            mdDevelopmentArchitecturalDao.updateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
        }
    }
}

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

import java.util.List;

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

    public MdDevelopmentHypothesis getMdDevelopmentHypothesis(Integer id){
        return mdDevelopmentHypothesisDao.getMdDevelopmentHypothesisById(id);
    }

    public MdDevelopmentArchitectural getMdDevelopmentArchitectural(Integer id){
        return mdDevelopmentArchitecturalDao.getMdDevelopmentArchitecturalById(id);
    }

    public Integer saveAndUpdateMdDevelopmentHypothesis(MdDevelopmentHypothesis mdDevelopmentHypothesis) {
        if (mdDevelopmentHypothesis.getId() == null) {
            mdDevelopmentHypothesis.setCreator(commonService.thisUserAccount());
            Integer id = mdDevelopmentHypothesisDao.addMdDevelopmentHypothesis(mdDevelopmentHypothesis);
            return id;
        } else {
            mdDevelopmentHypothesisDao.updateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
            return null;
        }
    }

    public Integer saveAndUpdateMdDevelopment(MdDevelopment mdDevelopment) {
        if (mdDevelopment.getId() == null) {
            mdDevelopment.setCreator(commonService.thisUserAccount());
            Integer id = mdDevelopmentDao.addMdDevelopment(mdDevelopment);
            return id;
        } else {
            mdDevelopmentDao.updateMdDevelopment(mdDevelopment);
            return null;
        }
    }

    public Integer saveAndUpdateMdDevelopmentArchitectural(MdDevelopmentArchitectural mdDevelopmentArchitectural) {
        if (mdDevelopmentArchitectural.getId() == null) {
            mdDevelopmentArchitectural.setCreator(commonService.thisUserAccount());
            Integer id = mdDevelopmentArchitecturalDao.addMdDevelopmentArchitectural(mdDevelopmentArchitectural);
            return  id;
        } else {
            mdDevelopmentArchitecturalDao.updateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
            return null;
        }
    }

    public List<MdDevelopmentArchitectural> getMdDevelopmentArchitecturalList(MdDevelopmentArchitectural mdDevelopmentArchitectural){
        return mdDevelopmentArchitecturalDao.getMdDevelopmentArchitecturalList(mdDevelopmentArchitectural);
    }

    public List<MdDevelopmentHypothesis> getMdDevelopmentHypothesisList(MdDevelopmentHypothesis mdDevelopmentHypothesis){
        return mdDevelopmentHypothesisDao.getMdDevelopmentHypothesisList(mdDevelopmentHypothesis);
    }
}

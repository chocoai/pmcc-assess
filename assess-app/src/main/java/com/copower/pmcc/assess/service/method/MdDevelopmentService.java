package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentEngineeringDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentLandDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.project.declare.DeclareEconomicIndicatorsContentService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
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
    private MdDevelopmentDao mdDevelopmentDao;
    @Autowired
    private MdDevelopmentLandDao mdDevelopmentLandDao;
    @Autowired
    private MdDevelopmentEngineeringDao mdDevelopmentEngineeringDao;
    @Autowired
    private DeclareEconomicIndicatorsContentService declareEconomicIndicatorsContentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MdDevelopment initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdDevelopment mdDevelopment = new MdDevelopment();
        mdDevelopment.setName(schemeJudgeObject.getName());
        mdDevelopment.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdDevelopment(mdDevelopment);
        return mdDevelopment;
    }

    public MdDevelopment getMdDevelopmentById(Integer id) {
        return mdDevelopmentDao.getMdDevelopmentById(id);
    }

    public MdDevelopmentEngineering getByMdDevelopmentEngineeringId(Integer id) {
        return mdDevelopmentEngineeringDao.getByMdDevelopmentEngineeringId(id);
    }

    public MdDevelopmentLand getByMdDevelopmentLandId(Integer id) {
        return mdDevelopmentLandDao.getByMdDevelopmentLandId(id);
    }

    public void saveAndUpdateMdDevelopment(MdDevelopment oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentDao.addMdDevelopment(oo);
        } else {
            mdDevelopmentDao.updateMdDevelopment(oo);
        }
    }

    public void deleteMdDevelopmentEngineering(Integer id, Integer planDetailsId) {
        mdDevelopmentEngineeringDao.deleteMdDevelopmentEngineering(id);
        DeclareEconomicIndicatorsContent select = new DeclareEconomicIndicatorsContent();
        select.setPlanDetailsId(planDetailsId);
        select.setIndicatorsHeadId(id);
        List<DeclareEconomicIndicatorsContent> contentList = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentList(select);
        if (CollectionUtils.isNotEmpty(contentList)) {
            for (DeclareEconomicIndicatorsContent oo : contentList) {
                declareEconomicIndicatorsContentService.deleteDeclareEconomicIndicatorsContent(oo.getId());
            }
        }
    }

    public List<MdDevelopmentEngineering> getMdDevelopmentEngineeringList(MdDevelopmentEngineering oo) {
        return mdDevelopmentEngineeringDao.getMdDevelopmentEngineeringList(oo);
    }

    public void saveAndUpdateMdDevelopmentEngineering(MdDevelopmentEngineering oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentEngineeringDao.addMdDevelopmentEngineering(oo);
        } else {
            mdDevelopmentEngineeringDao.updateMdDevelopmentEngineering(oo);
        }
    }

    public void saveAndUpdateMdDevelopmentLand(MdDevelopmentLand oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentLandDao.addMdDevelopmentLand(oo);
        } else {
            mdDevelopmentLandDao.updateMdDevelopmentLand(oo);
        }
    }

    public List<MdDevelopmentLand> getMdDevelopmentLandList(MdDevelopmentLand mdDevelopmentLand) {
        return mdDevelopmentLandDao.getMdDevelopmentLandList(mdDevelopmentLand);
    }

    public void deleteMdDevelopmentLand(Integer id, Integer planDetailsId) {
        mdDevelopmentLandDao.deleteMdDevelopmentLand(id);
        DeclareEconomicIndicatorsContent select = new DeclareEconomicIndicatorsContent();
        select.setPlanDetailsId(planDetailsId);
        select.setIndicatorsHeadId(id);
        List<DeclareEconomicIndicatorsContent> contentList = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentList(select);
        if (CollectionUtils.isNotEmpty(contentList)) {
            for (DeclareEconomicIndicatorsContent oo : contentList) {
                declareEconomicIndicatorsContentService.deleteDeclareEconomicIndicatorsContent(oo.getId());
            }
        }
    }

}

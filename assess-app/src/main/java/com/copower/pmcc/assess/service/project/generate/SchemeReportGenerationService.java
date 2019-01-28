package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.dao.project.generate.SchemeReportGenerationDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/28 10:53
 * @Description:报告生成
 */
@Service
public class SchemeReportGenerationService {
    
    @Autowired
    private SchemeReportGenerationDao schemeReportGenerationDao;

    public SchemeReportGeneration getSchemeReportGenerationByAreaGroupId(Integer areaGroupId,Integer projectPlanId)throws Exception{
        return schemeReportGenerationDao.getSchemeReportGenerationByAreaGroupId(areaGroupId,projectPlanId);
    }


    public boolean updateSchemeReportGeneration(SchemeReportGeneration schemeReportGeneration) throws Exception {
        return schemeReportGenerationDao.updateSchemeReportGeneration(schemeReportGeneration);
    }

    public boolean addSchemeReportGeneration(SchemeReportGeneration schemeReportGeneration) throws Exception {
        return schemeReportGenerationDao.addSchemeReportGeneration(schemeReportGeneration);
    }

    public boolean deleteSchemeReportGeneration(Integer id) throws Exception {
        return schemeReportGenerationDao.deleteSchemeReportGeneration(id);
    }

    public SchemeReportGeneration getBySchemeReportGenerationId(Integer id) throws Exception {
        return schemeReportGenerationDao.getBySchemeReportGenerationId(id);
    }

    public SchemeReportGeneration getSchemeReportGeneration(SchemeReportGeneration schemeReportGeneration) throws Exception {
        return schemeReportGenerationDao.getSchemeReportGeneration(schemeReportGeneration);
    }

    public List<SchemeReportGeneration> schemeReportGenerationList(SchemeReportGeneration schemeReportGeneration) throws Exception {
        return schemeReportGenerationDao.schemeReportGenerationList(schemeReportGeneration);
    }
    
}

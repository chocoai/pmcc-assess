package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportGenerationDao;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/28 10:53
 * @Description:报告生成
 */
@Service
public class GenerateReportGenerationService {
    
    @Autowired
    private GenerateReportGenerationDao generateReportGenerationDao;

    public GenerateReportGeneration getGenerateReportGenerationByAreaGroupId(Integer areaGroupId,Integer projectPlanId)throws Exception{
        return generateReportGenerationDao.getGenerateReportGenerationByAreaGroupId(areaGroupId,projectPlanId);
    }


    public boolean updateGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws Exception {
        return generateReportGenerationDao.updateGenerateReportGeneration(generateReportGeneration);
    }

    public boolean addGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws Exception {
        return generateReportGenerationDao.addGenerateReportGeneration(generateReportGeneration);
    }

    public boolean deleteGenerateReportGeneration(Integer id) throws Exception {
        return generateReportGenerationDao.deleteGenerateReportGeneration(id);
    }

    public GenerateReportGeneration getByGenerateReportGenerationId(Integer id) throws Exception {
        return generateReportGenerationDao.getByGenerateReportGenerationId(id);
    }

    public GenerateReportGeneration getGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws Exception {
        return generateReportGenerationDao.getGenerateReportGeneration(generateReportGeneration);
    }

    public List<GenerateReportGeneration> generateReportGenerationList(GenerateReportGeneration generateReportGeneration) throws Exception {
        return generateReportGenerationDao.generateReportGenerationList(generateReportGeneration);
    }
    
}

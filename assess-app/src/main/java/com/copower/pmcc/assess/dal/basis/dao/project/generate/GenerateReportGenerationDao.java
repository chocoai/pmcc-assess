package com.copower.pmcc.assess.dal.basis.dao.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGeneration;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGenerationExample;
import com.copower.pmcc.assess.dal.basis.mapper.GenerateReportGenerationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/28 10:55
 * @Description:
 */
@Repository
public class GenerateReportGenerationDao {

    @Autowired
    private GenerateReportGenerationMapper generateReportGenerationMapper;

    public boolean updateGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws SQLException {
        int i = generateReportGenerationMapper.updateByPrimaryKeySelective(generateReportGeneration);
        return i > 0;
    }

    public GenerateReportGeneration getGenerateReportGenerationByAreaGroupId(Integer areaGroupId, Integer projectPlanId) throws SQLException {
        GenerateReportGenerationExample example = new GenerateReportGenerationExample();
        GenerateReportGenerationExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andAreaGroupIdEqualTo(areaGroupId);
        criteria.andProjectPlanIdEqualTo(projectPlanId);
        List<GenerateReportGeneration> generateReportGenerations = generateReportGenerationMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(generateReportGenerations)) {
            return generateReportGenerations.get(0);
        }
        return null;
    }

    public boolean addGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws SQLException {
        int i = generateReportGenerationMapper.insertSelective(generateReportGeneration);
        return i > 0;
    }

    public boolean deleteGenerateReportGeneration(Integer id) throws SQLException {
        int i = generateReportGenerationMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public GenerateReportGeneration getByGenerateReportGenerationId(Integer id) throws SQLException {
        return generateReportGenerationMapper.selectByPrimaryKey(id);
    }

    public GenerateReportGeneration getGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws SQLException {
        List<GenerateReportGeneration> generateReportGenerations = generateReportGenerationList(generateReportGeneration);
        if (CollectionUtils.isNotEmpty(generateReportGenerations)) {
            return generateReportGenerations.get(0);
        }
        return null;
    }

    public List<GenerateReportGeneration> generateReportGenerationList(GenerateReportGeneration generateReportGeneration) throws SQLException {
        GenerateReportGenerationExample example = new GenerateReportGenerationExample();
        MybatisUtils.convertObj2Example(generateReportGeneration, example);
        List<GenerateReportGeneration> generateReportGenerations = generateReportGenerationMapper.selectByExample(example);
        return generateReportGenerations;
    }

}

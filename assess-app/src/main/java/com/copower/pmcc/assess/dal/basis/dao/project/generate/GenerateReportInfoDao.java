package com.copower.pmcc.assess.dal.basis.dao.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.GenerateReportInfoMapper;
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
public class GenerateReportInfoDao {

    @Autowired
    private GenerateReportInfoMapper generateReportInfoMapper;

    public boolean updateGenerateReportInfo(GenerateReportInfo generateReportGeneration) throws SQLException {
        int i = generateReportInfoMapper.updateByPrimaryKeySelective(generateReportGeneration);
        return i > 0;
    }

    public GenerateReportInfo getGenerateReportInfoByAreaGroupId(Integer areaGroupId, Integer projectPlanId) throws SQLException {
        GenerateReportInfoExample example = new GenerateReportInfoExample();
        GenerateReportInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andAreaGroupIdEqualTo(areaGroupId);
        criteria.andProjectPlanIdEqualTo(projectPlanId);
        List<GenerateReportInfo> generateReportGenerations = generateReportInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(generateReportGenerations)) {
            return generateReportGenerations.get(0);
        }
        return null;
    }

    public boolean addGenerateReportInfo(GenerateReportInfo generateReportGeneration) throws SQLException {
        int i = generateReportInfoMapper.insertSelective(generateReportGeneration);
        return i > 0;
    }

    public boolean deleteGenerateReportInfo(Integer id) throws SQLException {
        int i = generateReportInfoMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public GenerateReportInfo getByGenerateReportInfoId(Integer id) throws SQLException {
        return generateReportInfoMapper.selectByPrimaryKey(id);
    }

    public GenerateReportInfo getGenerateReportInfo(GenerateReportInfo generateReportGeneration)  {
        List<GenerateReportInfo> generateReportGenerations = generateReportGenerationList(generateReportGeneration);
        if (CollectionUtils.isNotEmpty(generateReportGenerations)) {
            return generateReportGenerations.get(0);
        }
        return null;
    }

    public List<GenerateReportInfo> generateReportGenerationList(GenerateReportInfo generateReportGeneration)  {
        GenerateReportInfoExample example = new GenerateReportInfoExample();
        MybatisUtils.convertObj2Example(generateReportGeneration, example);
        example.setOrderByClause("id desc");
        List<GenerateReportInfo> generateReportGenerations = generateReportInfoMapper.selectByExample(example);
        return generateReportGenerations;
    }

}

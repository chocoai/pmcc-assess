package com.copower.pmcc.assess.dal.basis.dao.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportGeneration;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportGenerationExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeReportGenerationMapper;
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
public class SchemeReportGenerationDao {

    @Autowired
    private SchemeReportGenerationMapper schemeReportGenerationMapper;

    public boolean updateSchemeReportGeneration(SchemeReportGeneration schemeReportGeneration) throws SQLException {
        int i = schemeReportGenerationMapper.updateByPrimaryKeySelective(schemeReportGeneration);
        return i > 0;
    }

    public SchemeReportGeneration getSchemeReportGenerationByAreaGroupId(Integer areaGroupId, Integer projectPlanId) throws SQLException {
        SchemeReportGenerationExample example = new SchemeReportGenerationExample();
        SchemeReportGenerationExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andAreaGroupIdEqualTo(areaGroupId);
        criteria.andProjectPlanIdEqualTo(projectPlanId);
        List<SchemeReportGeneration> schemeReportGenerations = schemeReportGenerationMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeReportGenerations)) {
            return schemeReportGenerations.get(0);
        }
        return null;
    }

    public boolean addSchemeReportGeneration(SchemeReportGeneration schemeReportGeneration) throws SQLException {
        int i = schemeReportGenerationMapper.insertSelective(schemeReportGeneration);
        return i > 0;
    }

    public boolean deleteSchemeReportGeneration(Integer id) throws SQLException {
        int i = schemeReportGenerationMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SchemeReportGeneration getBySchemeReportGenerationId(Integer id) throws SQLException {
        return schemeReportGenerationMapper.selectByPrimaryKey(id);
    }

    public SchemeReportGeneration getSchemeReportGeneration(SchemeReportGeneration schemeReportGeneration) throws SQLException {
        List<SchemeReportGeneration> schemeReportGenerations = schemeReportGenerationList(schemeReportGeneration);
        if (CollectionUtils.isNotEmpty(schemeReportGenerations)) {
            return schemeReportGenerations.get(0);
        }
        return null;
    }

    public List<SchemeReportGeneration> schemeReportGenerationList(SchemeReportGeneration schemeReportGeneration) throws SQLException {
        SchemeReportGenerationExample example = new SchemeReportGenerationExample();
        MybatisUtils.convertObj2Example(schemeReportGeneration, example);
        List<SchemeReportGeneration> schemeReportGenerations = schemeReportGenerationMapper.selectByExample(example);
        return schemeReportGenerations;
    }

}

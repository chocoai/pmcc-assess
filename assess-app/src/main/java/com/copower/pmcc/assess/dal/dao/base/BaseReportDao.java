package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.ReportColumnsMapper;
import com.copower.pmcc.assess.dal.mapper.ReportTableMapper;
import com.copower.pmcc.assess.dal.mapper.ReportTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 17:16
 */
@Repository
public class BaseReportDao {
    @Autowired
    private ReportTableMapper baseReportTableMapper;
    @Autowired
    private ReportColumnsMapper baseReportColumnsMapper;
    @Autowired
    private ReportTemplateMapper baseReportTemplateMapper;

    public List<ReportTable> getReportTableList(Integer typeId) {
        ReportTableExample example = new ReportTableExample();
        ReportTableExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (typeId != null) {
            criteria.andTableTypeEqualTo(typeId);
        }
        List<ReportTable> baseReportTables = baseReportTableMapper.selectByExample(example);
        return baseReportTables;
    }

    public ReportTable getReportTableById(Integer id) {
        return baseReportTableMapper.selectByPrimaryKey(id);
    }

    public List<ReportColumns> getReportColumnsList(Integer tableId) {
        ReportColumnsExample example = new ReportColumnsExample();
        example.createCriteria().andBisEnableEqualTo(true).andTableIdEqualTo(tableId);
        List<ReportColumns> baseReportColumns = baseReportColumnsMapper.selectByExample(example);
        return baseReportColumns;
    }

    public ReportColumns getReportColumnsById(Integer id) {
        return baseReportColumnsMapper.selectByPrimaryKey(id);
    }

    public List<ReportTemplate> getReportTemplateByExample(ReportTemplate baseReportTemplate) {
        ReportTemplateExample example = new ReportTemplateExample();
        MybatisUtils.convertObj2Criteria(baseReportTemplate, example);
        List<ReportTemplate> baseReportTemplates = baseReportTemplateMapper.selectByExample(example);
        return baseReportTemplates;

    }

    public ReportTemplate getReportTemplateById(Integer id) {
        return baseReportTemplateMapper.selectByPrimaryKey(id);
    }

    public Boolean deleteReportTemplate(Integer id) {
        ReportTemplate baseReportTemplate = baseReportTemplateMapper.selectByPrimaryKey(id);
        baseReportTemplate.setBisEnable(false);
        return baseReportTemplateMapper.updateByPrimaryKeySelective(baseReportTemplate) >= 0;
    }

    public Boolean addReportTemplate(ReportTemplate baseReportTemplate) {
        return baseReportTemplateMapper.insertSelective(baseReportTemplate) == 1;
    }

    public Boolean updateReportTemplate(ReportTemplate baseReportTemplate) {
        return baseReportTemplateMapper.updateByPrimaryKeySelective(baseReportTemplate) >= 0;
    }

}

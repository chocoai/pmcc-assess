package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.BaseReportColumnsMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportTableMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportTemplateMapper;
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
    private BaseReportTableMapper baseReportTableMapper;
    @Autowired
    private BaseReportColumnsMapper baseReportColumnsMapper;
    @Autowired
    private BaseReportTemplateMapper baseReportTemplateMapper;

    public List<BaseReportTable> getBaseReportTableList() {
        BaseReportTableExample example = new BaseReportTableExample();
        example.createCriteria().andBisEnableEqualTo(true);
        List<BaseReportTable> baseReportTables = baseReportTableMapper.selectByExample(example);
        return baseReportTables;
    }

    public List<BaseReportColumns> getBaseReportColumnsList(Integer tableId) {
        BaseReportColumnsExample example = new BaseReportColumnsExample();
        example.createCriteria().andBisEnableEqualTo(true).andTableIdEqualTo(tableId);
        List<BaseReportColumns> baseReportColumns = baseReportColumnsMapper.selectByExample(example);
        return baseReportColumns;
    }

    public List<BaseReportTemplate> getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate, String bookMarkName) {
        BaseReportTemplateExample example = new BaseReportTemplateExample();
        BaseReportTemplateExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(bookMarkName)) {
            criteria.andBookmarkNameLike(bookMarkName);
        }
        MybatisUtils.convertObj2Criteria(baseReportTemplate, criteria);
        List<BaseReportTemplate> baseReportTemplates = baseReportTemplateMapper.selectByExample(example);
        return baseReportTemplates;

    }

    public Boolean deleteBaseReportTemplate(Integer id) {
        BaseReportTemplate baseReportTemplate = baseReportTemplateMapper.selectByPrimaryKey(id);
        baseReportTemplate.setBisEnable(false);
        return baseReportTemplateMapper.updateByPrimaryKeySelective(baseReportTemplate) >= 0;
    }

    public Boolean addBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        return baseReportTemplateMapper.insertSelective(baseReportTemplate) == 1;
    }

    public Boolean updateBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        return baseReportTemplateMapper.updateByPrimaryKeySelective(baseReportTemplate) >= 0;
    }

}

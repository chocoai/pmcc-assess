package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BaseReportTemplateMapper;
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
    private BaseReportTemplateMapper baseReportTemplateMapper;


    public List<BaseReportTemplate> getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate, Integer entrustPurpose) {
        BaseReportTemplateExample example = new BaseReportTemplateExample();
        BaseReportTemplateExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(baseReportTemplate, criteria);
        if (entrustPurpose != null) {
            criteria.andEntrustPurposeLike(String.format("%%,%s,%%", entrustPurpose));
        }
        List<BaseReportTemplate> baseReportTemplates = baseReportTemplateMapper.selectByExample(example);
        return baseReportTemplates;
    }

    public BaseReportTemplate getBaseReportTemplateById(Integer id) {
        return baseReportTemplateMapper.selectByPrimaryKey(id);
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

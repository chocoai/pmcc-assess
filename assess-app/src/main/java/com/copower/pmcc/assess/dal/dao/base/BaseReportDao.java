package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.ReportColumnsMapper;
import com.copower.pmcc.assess.dal.mapper.ReportTableMapper;
import com.copower.pmcc.assess.dal.mapper.ReportTemplateBookmarkMapper;
import com.copower.pmcc.assess.dal.mapper.ReportTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
    @Autowired
    private ReportTemplateBookmarkMapper reportTemplateBookmarkMapper;

    public List<ReportTable> getReportTableList(Integer typeId) {
        ReportTableExample example = new ReportTableExample();
        ReportTableExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (typeId != null) {
            criteria.andTableTypeEqualTo(typeId);
        }
        List<ReportTable> baseReportTables = baseReportTableMapper.selectByExample(example);
        return baseReportTables;
    }

    public List<ReportTable> getReportTableList(List<Integer> ids){
        ReportTableExample example = new ReportTableExample();
        ReportTableExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        criteria.andIdIn(ids);
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

    public List<ReportColumns> getReportColumnsList(List<Integer> ids){
        ReportColumnsExample example = new ReportColumnsExample();
        ReportColumnsExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        criteria.andIdIn(ids);
        List<ReportColumns> reportColumns = baseReportColumnsMapper.selectByExample(example);
        return reportColumns;
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

    /**
     * 获取最新可用的主模板
     * @param customerId
     * @param entrustId
     * @param reportTypeId
     * @param csType
     * @return
     */
    public ReportTemplate getNewsReportTemplate(Integer customerId,Integer entrustId,Integer reportTypeId,Integer csType) {
        ReportTemplateExample example = new ReportTemplateExample();
        ReportTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customerId).andEntrustIdEqualTo(entrustId).andReportTypeIdEqualTo(reportTypeId).andCsTypeEqualTo(csType);
        criteria.andBisEnableEqualTo(true).andTemplateTypeEqualTo(BaseReportTemplateTypeEnum.BOOKMARK.getKey());
        example.setOrderByClause("version_number desc");
        List<ReportTemplate> reportTemplates = baseReportTemplateMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(reportTemplates))
            return reportTemplates.get(0);
        return null;
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

    public List<ReportTemplateBookmark> getBookMarkListByTemplateId(Integer templateId){
        ReportTemplateBookmarkExample example = new ReportTemplateBookmarkExample();
        ReportTemplateBookmarkExample.Criteria criteria = example.createCriteria().andTemplateIdEqualTo(templateId);
        List<ReportTemplateBookmark> bookmarkList = reportTemplateBookmarkMapper.selectByExample(example);
        return bookmarkList;
    }

}

package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dal.entity.ReportTemplateExample;
import com.copower.pmcc.assess.dal.mapper.ReportTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:23
 */
@Repository
public class ReportTemplateDao {
    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    //region 获取数据列表

    /**
     * 获取数据列表
     *
     * @return
     */
    public List<ReportTemplate> getListObject(ReportTemplate reportTemplate) {
        ReportTemplateExample example = new ReportTemplateExample();
        MybatisUtils.convertObj2Example(reportTemplate, example);
        example.setOrderByClause("id");
        List<ReportTemplate> list = reportTemplateMapper.selectByExample(example);
        return list;
    }

    public List<ReportTemplate> getListObject(List<Integer> ids) {
        if(CollectionUtils.isEmpty(ids)) return null;
        ReportTemplateExample example = new ReportTemplateExample();
        ReportTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        example.setOrderByClause("id");
        List<ReportTemplate> list = reportTemplateMapper.selectByExample(example);
        return list;
    }


    //endregion


    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public ReportTemplate getSingleObject(Integer id) {
        ReportTemplate reportTemplate = reportTemplateMapper.selectByPrimaryKey(id);
        return reportTemplate;
    }

    public ReportTemplate getSingleObject(ReportTemplate reportTemplate) {
        List<ReportTemplate> templateList = getListObject(reportTemplate);
        if(CollectionUtils.isNotEmpty(templateList))
            return templateList.get(0);
        return null;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param reportTemplate
     * @return
     */
    public boolean addObject(ReportTemplate reportTemplate) {
        if (reportTemplate == null) return false;
        return reportTemplateMapper.insertSelective(reportTemplate) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param reportTemplate
     * @return
     */
    public boolean updateObject(ReportTemplate reportTemplate) {
        if (reportTemplate == null) return false;
        return reportTemplateMapper.updateByPrimaryKeySelective(reportTemplate) > 0;
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @return
     */
    public boolean deleteObject(Integer id) {
        if (id == null) return false;
        return reportTemplateMapper.deleteByPrimaryKey(id) > 0;
    }
    //endregion

    
}

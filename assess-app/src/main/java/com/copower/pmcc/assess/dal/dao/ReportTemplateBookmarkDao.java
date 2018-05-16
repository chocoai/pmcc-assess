package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;
import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmarkExample;
import com.copower.pmcc.assess.dal.mapper.ReportTemplateBookmarkMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class ReportTemplateBookmarkDao {
    @Autowired
    private ReportTemplateBookmarkMapper reportTemplateBookmarkMapper;

    //region 获取数据列表

    /**
     * 获取数据列表
     *
     * @return
     */
    public List<ReportTemplateBookmark> getListObject(ReportTemplateBookmark reportTemplateBookmark) {
        ReportTemplateBookmarkExample example = new ReportTemplateBookmarkExample();
        MybatisUtils.convertObj2Example(reportTemplateBookmark, example);
        example.setOrderByClause("id");
        List<ReportTemplateBookmark> list = reportTemplateBookmarkMapper.selectByExample(example);
        return list;
    }

    public List<ReportTemplateBookmark> getListObject(Integer templateId, String name) {
        ReportTemplateBookmarkExample example = new ReportTemplateBookmarkExample();
        example.createCriteria().andTemplateIdEqualTo(templateId).andNameLike(String.format("%%%s%%", name));
        example.setOrderByClause("id");
        List<ReportTemplateBookmark> list = reportTemplateBookmarkMapper.selectByExample(example);
        return list;
    }
    public List<ReportTemplateBookmark> getListObject(Integer templateId) {
        ReportTemplateBookmarkExample example = new ReportTemplateBookmarkExample();
        example.createCriteria().andTemplateIdEqualTo(templateId);
        example.setOrderByClause("id");
        List<ReportTemplateBookmark> list = reportTemplateBookmarkMapper.selectByExample(example);
        return list;
    }
    public List<ReportTemplateBookmark> getListObject(List<Integer> ids) {
        ReportTemplateBookmarkExample example = new ReportTemplateBookmarkExample();
        example.createCriteria().andIdIn(ids);
        example.setOrderByClause("id");
        List<ReportTemplateBookmark> list = reportTemplateBookmarkMapper.selectByExample(example);
        return list;
    }

    //endregion

    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public ReportTemplateBookmark getSingleObject(Integer id) {
        ReportTemplateBookmark cmsTemplate = reportTemplateBookmarkMapper.selectByPrimaryKey(id);
        return cmsTemplate;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param cmsTemplate
     * @return
     */
    public boolean addObject(ReportTemplateBookmark cmsTemplate) {
        if (cmsTemplate == null) return false;
        return reportTemplateBookmarkMapper.insertSelective(cmsTemplate) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param cmsTemplate
     * @return
     */
    public boolean updateObject(ReportTemplateBookmark cmsTemplate) {
        if (cmsTemplate == null) return false;
        return reportTemplateBookmarkMapper.updateByPrimaryKeySelective(cmsTemplate) > 0;
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
        return reportTemplateBookmarkMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean deleteObjectByTemplateId(Integer templateId) {
        if (templateId == null) return false;
        ReportTemplateBookmarkExample example = new ReportTemplateBookmarkExample();
        example.createCriteria().andTemplateIdEqualTo(templateId);
        return reportTemplateBookmarkMapper.deleteByExample(example) > 0;
    }
    //endregion


}

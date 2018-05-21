package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.BaseReportDao;
import com.copower.pmcc.assess.dal.entity.BaseReportColumns;
import com.copower.pmcc.assess.dal.entity.BaseReportTable;
import com.copower.pmcc.assess.dal.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.entity.BaseReportTemplateExample;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 18:36
 */
@Service
public class BaseReportService {
    @Autowired
    private BaseReportDao baseReportDao;

    public List<BaseReportTable> getBaseReportTableList() {
        return baseReportDao.getBaseReportTableList();
    }

    public List<BaseReportColumns> getBaseReportColumnsList(Integer tableId) {
        return baseReportDao.getBaseReportColumnsList(tableId);
    }

    public void deleteBaseReportTemplate(Integer id) {
        baseReportDao.deleteBaseReportTemplate(id);
    }

    public void updateBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportDao.updateBaseReportTemplate(baseReportTemplate);
    }

    public void addBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportDao.addBaseReportTemplate(baseReportTemplate);
    }

    //根据条件取得设置的报告模板书签
    public BootstrapTableVo getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate) {
        BaseReportTemplateExample example = new BaseReportTemplateExample();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, requestBaseParam.getSearch());
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(baseReportTemplates != null ? baseReportTemplates : new ArrayList<BaseReportTemplate>());
        return bootstrapTableVo;
    }
    //取得定义的报告模板的子模板
}

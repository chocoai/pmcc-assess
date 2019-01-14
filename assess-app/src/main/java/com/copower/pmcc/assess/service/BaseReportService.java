package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseReportDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    //报告模板======================================================
    public void changeBaseReportTemplate(Integer id, Integer type) {
        if (type == 0) {
            baseReportDao.deleteBaseReportTemplate(id);
        } else {
            BaseReportTemplate baseReportTemplate = baseReportDao.getBaseReportTemplateById(id);
            baseReportTemplate.setBisEnable(true);
            baseReportDao.updateBaseReportTemplate(baseReportTemplate);
        }
    }

    public void updateBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportDao.updateBaseReportTemplate(baseReportTemplate);
    }

    public void addBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportTemplate.setCreator(processControllerComponent.getThisUser());
        baseReportTemplate.setBisEnable(false);
        baseReportDao.addBaseReportTemplate(baseReportTemplate);
        //更新附件信息
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        baseAttachment.setTableId(0);
        baseAttachment.setCreater(processControllerComponent.getThisUser());
        SysAttachmentDto baseAttachmentNew = new SysAttachmentDto();
        baseAttachmentNew.setTableId(baseReportTemplate.getId());
        baseAttachmentService.updateAttachementByExample(baseAttachment, baseAttachmentNew);
    }

    public BaseReportTemplate getBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, "");
        if (CollectionUtils.isNotEmpty(baseReportTemplates)) {
            return baseReportTemplates.get(0);
        }
        return null;
    }

    public BootstrapTableVo getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, requestBaseParam.getSearch());
        List<BaseReportTemplateVo> transform = LangUtils.transform(baseReportTemplates, o -> getBaseReportTemplateVo(o));
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(transform != null ? transform : new ArrayList<BaseReportTemplateVo>());
        return bootstrapTableVo;
    }

    private BaseReportTemplateVo getBaseReportTemplateVo(BaseReportTemplate baseReportTemplate) {

        BaseReportTemplateVo baseReportTemplateVo = new BaseReportTemplateVo();
        BeanUtils.copyProperties(baseReportTemplate, baseReportTemplateVo);
        baseReportTemplateVo.setEntrustPurposeName(baseDataDicService.getNameById(baseReportTemplate.getEntrustPurpose()));

        List<SysAttachmentDto> baseAttachments = baseAttachmentService.getByField_tableId(baseReportTemplate.getId(),null,FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        if (CollectionUtils.isNotEmpty(baseAttachments)) {
            List<String> report = LangUtils.transform(baseAttachments, o -> baseAttachmentService.getViewHtml(o));
            baseReportTemplateVo.setReport(report);
        }
        return baseReportTemplateVo;
    }


    /**
     * 根据条件查询报告模板
     *
     * @param customerId        客户id
     * @param reportTypeId      报告类型
     * @param csTypeId          客户类型
     * @param projectTypeId     项目类别
     * @param projectCategoryId 项目范围
     * @return
     */
    public BaseReportTemplate getReportTemplateFile(Integer customerId, Integer reportTypeId, Integer csTypeId, Integer projectTypeId, Integer projectCategoryId) {
        //1.根据传递过来的参数获取模板
        //2.未找到对应的模板 先以范围为全部进行查询 如果依然未找到 则取公司下的模板
        BaseReportTemplate baseReportTemplateWhere = new BaseReportTemplate();
        baseReportTemplateWhere.setReportType(reportTypeId);
        baseReportTemplateWhere.setUseUnit(customerId);//客户单位
        baseReportTemplateWhere.setEntrustPurpose(projectTypeId);
        baseReportTemplateWhere.setCategory(projectCategoryId);
        baseReportTemplateWhere.setBisEnable(true);
        BaseReportTemplate baseReportTemplate = getBaseReportTemplate(baseReportTemplateWhere);

        if (baseReportTemplate == null) {
            customerId = 0;//公司下的模板
            baseReportTemplateWhere.setUseUnit(customerId);
            baseReportTemplate = getBaseReportTemplate(baseReportTemplateWhere);
        }
        if (baseReportTemplate == null)
            return null;
        return baseReportTemplate;
    }

}

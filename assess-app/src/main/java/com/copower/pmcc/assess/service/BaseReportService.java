package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseReportDataPoolTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.BaseReportDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.BaseReportColumns;
import com.copower.pmcc.assess.dal.entity.BaseReportTable;
import com.copower.pmcc.assess.dal.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
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
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public List<BaseReportTable> getBaseReportTableList(Integer typeId) {
        return baseReportDao.getBaseReportTableList(typeId);
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
        //更新附件信息
        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setTableName("tb_base_report_template");
        baseAttachment.setTableId(0);
        baseAttachment.setCreater(processControllerComponent.getThisUser());

        BaseAttachment baseAttachmentNew = new BaseAttachment();
        baseAttachmentNew.setTableId(baseReportTemplate.getId());

        baseAttachmentDao.updateAttachementByExample(baseAttachment, baseAttachmentNew);
    }

    //根据条件取得设置的报告模板书签
    public BootstrapTableVo getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, requestBaseParam.getSearch());
        if (CollectionUtils.isEmpty(baseReportTemplates)) {
            baseReportTemplates = new ArrayList<BaseReportTemplate>();
        }
        List<BaseReportTemplateVo> transform = LangUtils.transform(baseReportTemplates, o -> getBaseReportTemplateVo(o));
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(transform != null ? transform : new ArrayList<BaseReportTemplateVo>());
        return bootstrapTableVo;
    }

    public BaseReportTemplate getBaseReportTemplateById(Integer id) {
        return baseReportDao.getBaseReportTemplateById(id);
    }

    private BaseReportTemplateVo getBaseReportTemplateVo(BaseReportTemplate baseReportTemplate) {
        BaseReportTemplateVo baseReportTemplateVo = new BaseReportTemplateVo();
        if (baseReportTemplate != null) {
            BeanUtils.copyProperties(baseReportTemplate, baseReportTemplateVo);
            if (baseReportTemplate.getTemplateType() == BaseReportTemplateTypeEnum.TEMPLATE.getKey()) {
                baseReportTemplateVo.setTypeName("");
                List<BaseAttachment> baseAttachments = baseAttachmentDao.getAttachmentListByTableName("tb_base_report_template", Lists.newArrayList(baseReportTemplate.getId()));
                if (CollectionUtils.isNotEmpty(baseAttachments)) {
                    List<KeyValueDto> transform = LangUtils.transform(baseAttachments, o -> {
                        KeyValueDto keyValueDto = new KeyValueDto();
                        keyValueDto.setKey(String.valueOf(o.getId()));
                        keyValueDto.setValue(o.getFileName());
                        keyValueDto.setExplain(o.getFileExtension());
                        return keyValueDto;
                    });
                    baseReportTemplateVo.setKeyValueDtos(transform);

                }
                baseReportTemplateVo.setDataPoolTypename("");
            } else {
                BaseReportDataPoolTypeEnum enumByName = BaseReportDataPoolTypeEnum.getEnumByName(baseReportTemplate.getDataPoolType());
                baseReportTemplateVo.setTypeName(enumByName.getName());
                switch (enumByName) {
                    case FILES:
                    case COLUMNS: {
                        BaseReportTable baseReportTableById = baseReportDao.getBaseReportTableById(baseReportTemplate.getDataPoolTableId());
                        BaseReportColumns baseReportColumnsById = baseReportDao.getBaseReportColumnsById(baseReportTemplate.getDataPoolColumnsId());
                        if (baseReportTableById != null && baseReportColumnsById != null) {
                            baseReportTemplateVo.setDataPoolTypename(String.format("%s - %s", baseReportTableById.getCnName(), baseReportColumnsById.getColumnsCnName()));
                        }

                        break;
                    }
                    case TEMPLATE: {
                        BaseReportTemplate baseReportTemplateById = baseReportDao.getBaseReportTemplateById(baseReportTemplate.getDataPoolTemplateId());
                        if (baseReportTemplateById != null) {
                            baseReportTemplateVo.setDataPoolTypename(String.format("模板 - %s", baseReportTemplateById.getBookmarkName()));
                        }
                        break;
                    }
                }
            }
        }
        return baseReportTemplateVo;
    }

    //取得定义的报告模板的子模板
    public List<BaseReportTemplate> getBaseReportTemplateByTemplate(Integer customerId) {
        BaseReportTemplate baseReportTemplate = new BaseReportTemplate();
        baseReportTemplate.setTemplateType(BaseReportTemplateTypeEnum.TEMPLATE.getKey());
        baseReportTemplate.setCustomerId(customerId);
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, "");
        return baseReportTemplates;
    }

}

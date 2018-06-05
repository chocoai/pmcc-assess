package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.base.BaseReportDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.output.report.ReportTemplateVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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

    public List<ReportTable> getReportTableList(List<Integer> ids){
        return baseReportDao.getReportTableList(ids);
    }

    /**
     * 获取表名
     * @param ids
     * @return
     */
    public List<String> getReportTableNameList(List<Integer> ids){
        List<ReportTable> reportTableList = getReportTableList(ids);
        if(CollectionUtils.isEmpty(reportTableList)) return null;
        return LangUtils.transform(reportTableList, p -> p.getTableName());
    }

    public List<ReportTable> getReportTableList(Integer typeId) {
        return baseReportDao.getReportTableList(typeId);
    }

    public List<ReportColumns> getReportColumnsList(List<Integer> ids){
        return baseReportDao.getReportColumnsList(ids);
    }

    public List<ReportColumns> getReportColumnsList(Integer tableId) {
        return baseReportDao.getReportColumnsList(tableId);
    }

    /**
     * 获取字段名
     * @param ids
     * @return
     */
    public List<String> getReportColumnsNameList(List<Integer> ids){
        List<ReportColumns> reportColumnsList = getReportColumnsList(ids);
        if(CollectionUtils.isEmpty(reportColumnsList)) return null;
        return LangUtils.transform(reportColumnsList, p -> p.getColumnsName());
    }


    public void deleteReportTemplate(Integer id) {
        baseReportDao.deleteReportTemplate(id);
    }

    public void updateReportTemplate(ReportTemplate baseReportTemplate) {
        baseReportDao.updateReportTemplate(baseReportTemplate);
    }

    public void addReportTemplate(ReportTemplate baseReportTemplate) {
        baseReportDao.addReportTemplate(baseReportTemplate);
        //更新附件信息
        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(ReportTemplate.class));
        baseAttachment.setTableId(0);
        baseAttachment.setCreater(processControllerComponent.getThisUser());

        BaseAttachment baseAttachmentNew = new BaseAttachment();
        baseAttachmentNew.setTableId(baseReportTemplate.getId());

        baseAttachmentDao.updateAttachementByExample(baseAttachment, baseAttachmentNew);
    }

    //根据条件取得设置的报告模板书签
    public BootstrapTableVo getReportTemplateByExample(ReportTemplate baseReportTemplate) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ReportTemplate> baseReportTemplates = baseReportDao.getReportTemplateByExample(baseReportTemplate);
        if (CollectionUtils.isEmpty(baseReportTemplates)) {
            baseReportTemplates = new ArrayList<ReportTemplate>();
        }
        List<ReportTemplateVo> transform = LangUtils.transform(baseReportTemplates, o -> getReportTemplateVo(o));
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(transform != null ? transform : new ArrayList<ReportTemplateVo>());
        return bootstrapTableVo;
    }

    public ReportTemplate getReportTemplateById(Integer id) {
        return baseReportDao.getReportTemplateById(id);
    }

    private ReportTemplateVo getReportTemplateVo(ReportTemplate baseReportTemplate) {
        ReportTemplateVo baseReportTemplateVo = new ReportTemplateVo();
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
//                BaseReportDataPoolTypeEnum enumByName = BaseReportDataPoolTypeEnum.getEnumByName(baseReportTemplate.getDataPoolType());
//                if (enumByName != null) {
//                    baseReportTemplateVo.setTypeName(enumByName.getName());
//                    switch (enumByName) {
//                        case FILES:
//                        case COLUMNS: {
//                            ReportTable baseReportTableById = baseReportDao.getReportTableById(baseReportTemplate.getDataPoolTableId());
//                            ReportColumns baseReportColumnsById = baseReportDao.getReportColumnsById(baseReportTemplate.getDataPoolColumnsId());
//                            if (baseReportTableById != null && baseReportColumnsById != null) {
//                                baseReportTemplateVo.setDataPoolTypename(String.format("%s - %s", baseReportTableById.getCnName(), baseReportColumnsById.getColumnsCnName()));
//                            }
//
//                            break;
//                        }
//                        case TEMPLATE: {
//                            ReportTemplate baseReportTemplateById = baseReportDao.getReportTemplateById(baseReportTemplate.getDataPoolTemplateId());
//                            if (baseReportTemplateById != null) {
//                                baseReportTemplateVo.setDataPoolTypename(String.format("模板 - %s", baseReportTemplateById.getBookmarkName()));
//                            }
//                            break;
//                        }
//                    }
//                }
            }
        }
        return baseReportTemplateVo;
    }

    //取得定义的报告模板的子模板
    public List<ReportTemplate> getReportTemplateByTemplate(Integer customerId) {
        ReportTemplate baseReportTemplate = new ReportTemplate();
        baseReportTemplate.setTemplateType(BaseReportTemplateTypeEnum.TEMPLATE.getKey());
        baseReportTemplate.setCustomerId(customerId);
        List<ReportTemplate> baseReportTemplates = baseReportDao.getReportTemplateByExample(baseReportTemplate);
        return baseReportTemplates;
    }

    /**
     * 获取最新可用的报告模板
     *
     * @param customerId
     * @param entrustId
     * @param reportTypeId
     * @param csType
     * @return
     */
    public ReportTemplate getNewsReportTemplate(Integer customerId, Integer entrustId, Integer reportTypeId, Integer csType) {
        ReportTemplate reportTemplate = baseReportDao.getNewsReportTemplate(customerId, entrustId, reportTypeId, csType);
        if (reportTemplate == null) {
            //找公司配置的对应类型模板
            reportTemplate = baseReportDao.getNewsReportTemplate(0, entrustId, reportTypeId, csType);
        }
        return reportTemplate;
    }

    /**
     * 获取书签列表
     * @param templateId
     * @return
     */
    public List<ReportTemplateBookmark> getBookMarkListByTemplateId(Integer templateId){
        return baseReportDao.getBookMarkListByTemplateId(templateId);
    }

}

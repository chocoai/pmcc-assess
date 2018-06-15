package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseReportDataPoolTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportMarkbookTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.base.BaseReportDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.base.BaseReportTemplateFilesDto;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateFilesVo;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
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
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    public List<BaseReportTable> getBaseReportTableList(Integer typeId) {
        return baseReportDao.getBaseReportTableList(typeId);
    }

    public List<BaseReportTable> getBaseReportTableList(List<Integer> tableIdId) {
        return baseReportDao.getBaseReportTableList(tableIdId);
    }

    public List<BaseReportColumns> getBaseReportColumnsList(Integer tableId) {
        return baseReportDao.getBaseReportColumnsList(tableId);
    }

    public BaseReportColumns getBaseReportColumnsById(Integer id) {
        return baseReportDao.getBaseReportColumnsById(id);
    }

    public void deleteBaseReportTemplate(Integer id) {
        baseReportDao.deleteBaseReportTemplate(id);
    }

    public void updateBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportDao.updateBaseReportTemplate(baseReportTemplate);
    }

    public void addBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        baseReportTemplate.setCreator(processControllerComponent.getThisUser());
        baseReportDao.addBaseReportTemplate(baseReportTemplate);
        //更新附件信息
        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
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

    //根据条件取得设置的报告模板书签
    public List<BaseReportTemplate> getBaseReportTemplateList(BaseReportTemplate baseReportTemplate) {
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, "");
        return baseReportTemplates;
    }

    public BaseReportTemplate getBaseReportTemplateById(Integer id) {
        return baseReportDao.getBaseReportTemplateById(id);
    }

    private BaseReportTemplateVo getBaseReportTemplateVo(BaseReportTemplate baseReportTemplate) {
        BaseReportTemplateVo baseReportTemplateVo = new BaseReportTemplateVo();
        if (baseReportTemplate != null) {
            BeanUtils.copyProperties(baseReportTemplate, baseReportTemplateVo);
            if (baseReportTemplate.getTemplateType() == BaseReportMarkbookTypeEnum.TEMPLATE.getKey()) {
                baseReportTemplateVo.setTypeName("");
                List<BaseAttachment> baseAttachments = baseAttachmentDao.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class), Lists.newArrayList(baseReportTemplate.getId()));
                if (CollectionUtils.isNotEmpty(baseAttachments)) {
                    List<KeyValueDto> transform = getKeyValueDtos(baseAttachments);
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
        baseReportTemplate.setTemplateType(BaseReportMarkbookTypeEnum.TEMPLATE.getKey());
        baseReportTemplate.setCustomerId(customerId);
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, "");
        return baseReportTemplates;
    }

    //报告模板======================================================
    public void changeBaseReportTemplateFiles(Integer id, Integer type) {
        if (type == 0) {
            baseReportDao.deleteBaseReportTemplateFiles(id);
        } else {
            baseReportDao.deleteAllBaseReportTemplateFiles(id);
            BaseReportTemplateFiles baseReportTemplateFiles = baseReportDao.getBaseReportTemplateFilesById(id);
            baseReportTemplateFiles.setBisEnable(true);
            baseReportDao.updateBaseReportTemplateFiles(baseReportTemplateFiles);
        }
    }

    public void updateBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        baseReportDao.updateBaseReportTemplateFiles(baseReportTemplateFiles);
    }

    public void addBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        baseReportTemplateFiles.setCreator(processControllerComponent.getThisUser());
        baseReportTemplateFiles.setBisEnable(false);
        baseReportDao.addBaseReportTemplateFiles(baseReportTemplateFiles);
        //更新附件信息
        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplateFiles.class));
        baseAttachment.setTableId(0);
        baseAttachment.setCreater(processControllerComponent.getThisUser());
        BaseAttachment baseAttachmentNew = new BaseAttachment();
        baseAttachmentNew.setTableId(baseReportTemplateFiles.getId());
        baseAttachmentDao.updateAttachementByExample(baseAttachment, baseAttachmentNew);
    }

    public BaseReportTemplateFiles getBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        List<BaseReportTemplateFiles> baseReportTemplateFiless = baseReportDao.getBaseReportTemplateFilesByExample(baseReportTemplateFiles, "");
        if (CollectionUtils.isNotEmpty(baseReportTemplateFiless)) {
            return baseReportTemplateFiless.get(0);
        }
        return null;
    }

    public BootstrapTableVo getBaseReportTemplateFilesByExample(BaseReportTemplateFiles baseReportTemplateFiles) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportTemplateFiles> baseReportTemplateFiless = baseReportDao.getBaseReportTemplateFilesByExample(baseReportTemplateFiles, requestBaseParam.getSearch());
        if (CollectionUtils.isEmpty(baseReportTemplateFiless)) {
            baseReportTemplateFiless = new ArrayList<BaseReportTemplateFiles>();
        }
        List<BaseReportTemplateFilesVo> transform = LangUtils.transform(baseReportTemplateFiless, o -> getBaseReportTemplateFilesVo(o));
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(transform != null ? transform : new ArrayList<BaseReportTemplateFilesVo>());
        return bootstrapTableVo;
    }

    private BaseReportTemplateFilesVo getBaseReportTemplateFilesVo(BaseReportTemplateFiles baseReportTemplateFiles) {

        BaseReportTemplateFilesVo baseReportTemplateFilesVo = new BaseReportTemplateFilesVo();
        if (baseReportTemplateFiles != null) {
            BeanUtils.copyProperties(baseReportTemplateFiles, baseReportTemplateFilesVo);
        }
        baseReportTemplateFilesVo.setClassifyName("全部");
        if (baseReportTemplateFiles.getClassifyId() > 0) {
            BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(baseReportTemplateFiles.getClassifyId());
            if (baseProjectClassify != null) {
                baseReportTemplateFilesVo.setClassifyName(baseProjectClassify.getName());
            }
        }

        List<BaseAttachment> baseAttachments = baseAttachmentDao.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplateFiles.class), Lists.newArrayList(baseReportTemplateFiles.getId()));

        //取报告模板
        List<BaseAttachment> filter = LangUtils.filter(baseAttachments, o -> {
            return BaseReportTemplateTypeEnum.getEnumByName(o.getFieldsName()) == BaseReportTemplateTypeEnum.REPORT;
        });

        if (CollectionUtils.isNotEmpty(filter)) {
            List<String> reportFiles = LangUtils.transform(filter, o -> baseAttachmentService.getViewHtml(o));
            baseReportTemplateFilesVo.setReportFiles(reportFiles);
        }
        //取导出汇总模板
        filter = LangUtils.filter(baseAttachments, o -> {
            return BaseReportTemplateTypeEnum.getEnumByName(o.getFieldsName()) == BaseReportTemplateTypeEnum.EXPORT;
        });

        if (CollectionUtils.isNotEmpty(filter)) {
            List<String> exportFiles = LangUtils.transform(filter, o -> baseAttachmentService.getViewHtml(o));
            baseReportTemplateFilesVo.setExportFiles(exportFiles);
        }

        return baseReportTemplateFilesVo;
    }

    private List<KeyValueDto> getKeyValueDtos(List<BaseAttachment> filter) {
        return LangUtils.transform(filter, o -> {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(o.getId()));
            keyValueDto.setValue(o.getFileName());
            keyValueDto.setExplain(o.getFileExtension());
            return keyValueDto;
        });
    }

    public Integer getReportTemplate(Integer customerId, Integer entrustId, Integer reportTypeId, Integer csTypeId, Integer classifyId) {
        BaseReportTemplateFiles baseReportTemplateFiles = new BaseReportTemplateFiles();
        baseReportTemplateFiles.setCustomerId(customerId);
        baseReportTemplateFiles.setEntrustId(entrustId);
        baseReportTemplateFiles.setReportTypeId(reportTypeId);
        baseReportTemplateFiles.setCsType(csTypeId);
        baseReportTemplateFiles.setClassifyId(classifyId);
        List<BaseReportTemplateFiles> baseReportTemplateFilesList = baseReportDao.getBaseReportTemplateFilesByExample(baseReportTemplateFiles, "");
        if (CollectionUtils.isNotEmpty(baseReportTemplateFilesList)) {
            baseReportTemplateFiles = baseReportTemplateFilesList.get(0);
            BaseAttachment baseAttachment = new BaseAttachment();
            baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplateFiles.class));
            baseAttachment.setTableId(baseReportTemplateFiles.getId());
            baseAttachment.setFieldsName(BaseReportTemplateTypeEnum.REPORT.getKey());
            List<BaseAttachment> attachmentList = baseAttachmentService.getAttachmentList(baseAttachment);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                return attachmentList.get(0).getId();
            }

        }
        return 0;

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
    public BaseReportTemplateFilesDto getReportTemplateFile(Integer customerId, Integer reportTypeId, Integer csTypeId, Integer projectTypeId, Integer projectCategoryId) {
        //1.根据传递过来的参数获取模板
        //2.未找到对应的模板 先以范围为全部进行查询 如果依然未找到 则取公司下的模板
        BaseReportTemplateFiles baseReportTemplateFilesWhere = new BaseReportTemplateFiles();
        baseReportTemplateFilesWhere.setCsType(csTypeId);//客户类型 1、自然人、法人
        baseReportTemplateFilesWhere.setReportTypeId(reportTypeId);//取预评报告
        baseReportTemplateFilesWhere.setCustomerId(customerId);//客户单位
        baseReportTemplateFilesWhere.setEntrustId(projectTypeId);
        baseReportTemplateFilesWhere.setClassifyId(projectCategoryId);
        baseReportTemplateFilesWhere.setBisEnable(true);
        BaseReportTemplateFiles baseReportTemplateFiles = getBaseReportTemplateFiles(baseReportTemplateFilesWhere);
        if (baseReportTemplateFiles == null) {
            projectCategoryId = 0;
            baseReportTemplateFilesWhere.setClassifyId(projectCategoryId);
            baseReportTemplateFiles = getBaseReportTemplateFiles(baseReportTemplateFilesWhere);
        }
        if (baseReportTemplateFiles == null) {
            customerId = 0;
            baseReportTemplateFilesWhere.setCustomerId(customerId);
            baseReportTemplateFiles = getBaseReportTemplateFiles(baseReportTemplateFilesWhere);
        }
        if (baseReportTemplateFiles == null) return null;
        BaseReportTemplateFilesDto baseReportTemplateFilesDto = new BaseReportTemplateFilesDto();
        baseReportTemplateFilesDto.setBaseReportTemplateFiles(baseReportTemplateFiles);
        //取对应的书签
        BaseReportTemplate baseReportTemplate = new BaseReportTemplate();
        baseReportTemplate.setCsType(csTypeId);//客户类型 1、自然人、法人
        baseReportTemplate.setReportTypeId(reportTypeId);//取预评报告
        baseReportTemplate.setCustomerId(customerId);//客户单位
        baseReportTemplate.setEntrustId(projectTypeId);
        baseReportTemplate.setBisEnable(true);
        List<BaseReportTemplate> baseReportTemplateList = getBaseReportTemplateList(baseReportTemplate);
        baseReportTemplateFilesDto.setBaseReportTemplateList(baseReportTemplateList);
        return baseReportTemplateFilesDto;
    }

}

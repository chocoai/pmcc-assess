package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseReportDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
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
 * @author: Calvin(qiudong @ copowercpa.com)
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
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private InitiateUnitInformationService initiateUnitInformationService;

    public BaseReportTemplate getBaseReportTemplateById(Integer id) {
        return baseReportDao.getBaseReportTemplateById(id);
    }

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

    public List<BaseReportTemplate> getBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        List<BaseReportTemplate> baseReportTemplates = baseReportDao.getBaseReportTemplateByExample(baseReportTemplate, "");
        return baseReportTemplates;
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
        baseReportTemplateVo.setLoanTypeName(baseDataDicService.getNameById(baseReportTemplate.getLoanType()));
        List<SysAttachmentDto> baseAttachments = baseAttachmentService.getByField_tableId(baseReportTemplate.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportTemplate.class));
        if (CollectionUtils.isNotEmpty(baseAttachments)) {
            List<String> report = LangUtils.transform(baseAttachments, o -> baseAttachmentService.getViewHtml(o));
            baseReportTemplateVo.setReport(report);
        }
        return baseReportTemplateVo;
    }

    /**
     * 根据条件查询报告模板
     *
     * @param projectId  项目id
     * @param reportType 报告类型
     * @return
     */
    public BaseReportTemplate getReportTemplate(Integer projectId, Integer reportType) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        if (projectInfo == null) {
            return null;
        }
        InitiateUnitInformationVo unitInformationVo = initiateUnitInformationService.getDataByProjectId(projectInfo.getId());
        if (unitInformationVo == null) {
            return null;
        }
        BaseReportTemplate template = getReportTemplate(Integer.valueOf(unitInformationVo.getuUseUnit()),
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(),
                reportType, projectInfo.getEntrustPurpose(), projectInfo.getLoanType());
        return template;
    }

    /**
     * 根据条件查询报告模板
     *
     * @param useUnit           报告使用单位
     * @param projectTypeId     项目类型
     * @param projectCategoryId 项目类别
     * @param reportType        报告类型
     * @param entrustPurpose    委托目的
     * @return
     */
    public BaseReportTemplate getReportTemplate(Integer useUnit, Integer projectTypeId, Integer projectCategoryId, Integer reportType, Integer entrustPurpose,Integer loanType) {
        //1.先查询报告使用单位是否设置模板，如果未设置则取系统内置模板，
        //2.如果设置了则根据参数获取对应模板，如果还是未找到则取统内置模板，
        BaseReportTemplate resultTemplate = null;

        BaseReportTemplate baseReportTemplateWhere = new BaseReportTemplate();
        baseReportTemplateWhere.setReportType(reportType);
        baseReportTemplateWhere.setUseUnit(useUnit);//报告使用单位
        baseReportTemplateWhere.setType(projectTypeId);
        baseReportTemplateWhere.setCategory(projectCategoryId);
        baseReportTemplateWhere.setBisEnable(true);
        List<BaseReportTemplate> reportTemplateList = getBaseReportTemplate(baseReportTemplateWhere);
        resultTemplate = getReportTemplateByPurpose(reportTemplateList, entrustPurpose,loanType);
        if (CollectionUtils.isEmpty(reportTemplateList)) {//系统内置模板
            useUnit = 0;
            baseReportTemplateWhere.setUseUnit(useUnit);
            reportTemplateList = getBaseReportTemplate(baseReportTemplateWhere);
            resultTemplate = getReportTemplateByPurpose(reportTemplateList, entrustPurpose,loanType);
        }
        return resultTemplate;
    }

    /**
     * 现有模板数据中根据委托目的获取报告模板
     *
     * @param reportTemplateList
     * @param entrustPurpose
     * @return
     */
    private BaseReportTemplate getReportTemplateByPurpose(List<BaseReportTemplate> reportTemplateList, Integer entrustPurpose, Integer loanType) {
        if (CollectionUtils.isEmpty(reportTemplateList)) return null;
        if (reportTemplateList.size() == 1) return reportTemplateList.get(0);
        //1.先循环模板查询是否与参数委托目的一致的模板,找到后直接返回
        //2.如果未找到则寻找没有设置委托目的的模板
        for (BaseReportTemplate template : reportTemplateList) {
            if (template.getEntrustPurpose() != null && template.getLoanType() != null) {
                if (template.getEntrustPurpose().equals(entrustPurpose) && template.getLoanType().equals(loanType))
                    return template;
            } else if (template.getEntrustPurpose() != null && template.getEntrustPurpose().equals(entrustPurpose)) {
                return template;
            } else if (template.getLoanType() != null && template.getLoanType().equals(loanType)) {
                return template;
            }
            if (template.getEntrustPurpose() != null && template.getEntrustPurpose().equals(entrustPurpose))
                return template;
        }
        return reportTemplateList.get(0);
    }


}

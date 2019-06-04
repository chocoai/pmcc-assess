package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseReportDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
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
    @Autowired
    private CrmCustomerService crmCustomerService;

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
                reportType, projectInfo.getLoanType());
        if (template == null) {
            template = getCompanyTemplate(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), reportType, projectInfo.getLoanType());
        }
        return template;
    }

    /**
     * 根据条件查询报告模板
     *
     * @param useUnit           报告使用单位
     * @param projectTypeId     项目类型
     * @param projectCategoryId 项目类别
     * @param reportType        报告类型
     * @return
     */
    public BaseReportTemplate getReportTemplate(Integer useUnit, Integer projectTypeId, Integer projectCategoryId, Integer reportType, Integer loanType) {
        //1.根据类型查看当前报告使用单位是否有报告模板
        //2.如果没有报告模板则到上级客户获取
        //3.如何客户配置了模板则获取对应的模板
        //4.如果都没有配置模板则获取公司默认模板
        BaseReportTemplate baseReportTemplateWhere = new BaseReportTemplate();
        baseReportTemplateWhere.setReportType(reportType);
        baseReportTemplateWhere.setUseUnit(useUnit);//报告使用单位
        baseReportTemplateWhere.setType(projectTypeId);
        baseReportTemplateWhere.setCategory(projectCategoryId);
        baseReportTemplateWhere.setBisEnable(true);
        List<BaseReportTemplate> reportTemplateList = getBaseReportTemplate(baseReportTemplateWhere);
        if (CollectionUtils.isEmpty(reportTemplateList)) {
            CrmCustomerDto customer = crmCustomerService.getCustomer(useUnit);
            if (customer == null) return null;
            return getReportTemplate(customer.getPid(), projectTypeId, projectCategoryId, reportType, loanType);
        }
        return getReportTemplateByLoanType(reportTemplateList, loanType);
    }

    /**
     * 获取公司默认模板
     *
     * @param projectTypeId
     * @param projectCategoryId
     * @param reportType
     * @param loanType
     * @return
     */
    public BaseReportTemplate getCompanyTemplate(Integer projectTypeId, Integer projectCategoryId, Integer reportType, Integer loanType) {
        BaseReportTemplate baseReportTemplateWhere = new BaseReportTemplate();
        baseReportTemplateWhere.setReportType(reportType);
        baseReportTemplateWhere.setUseUnit(0);
        baseReportTemplateWhere.setType(projectTypeId);
        baseReportTemplateWhere.setCategory(projectCategoryId);
        baseReportTemplateWhere.setBisEnable(true);
        List<BaseReportTemplate> reportTemplateList = getBaseReportTemplate(baseReportTemplateWhere);
        return getReportTemplateByLoanType(reportTemplateList, loanType);
    }

    /**
     * 现有模板数据中根据委托目的获取报告模板
     *
     * @param reportTemplateList
     * @return
     */
    private BaseReportTemplate getReportTemplateByLoanType(List<BaseReportTemplate> reportTemplateList, Integer loanType) {
        if (CollectionUtils.isEmpty(reportTemplateList)) return null;
        if (reportTemplateList.size() == 1) return reportTemplateList.get(0);
        for (BaseReportTemplate template : reportTemplateList) {
            if (template.getLoanType() != null && template.getLoanType().equals(loanType)) {
                return template;
            }
        }
        return reportTemplateList.get(0);
    }


}

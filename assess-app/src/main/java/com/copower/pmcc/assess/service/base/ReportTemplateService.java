package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.ReportTemplateBookmarkDao;
import com.copower.pmcc.assess.dal.dao.ReportTemplateDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;
import com.copower.pmcc.assess.dto.output.report.ReportTemplateBookmarkVo;
import com.copower.pmcc.assess.dto.output.report.ReportTemplateVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-3-5.
 */
@Service
public class ReportTemplateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ReportTemplateDao reportTemplateDao;
    @Autowired
    private BaseDataDicService cmsBaseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ReportTemplateBookmarkDao reportTemplateBookmarkDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;

    /**
     * 获取模板信息列表
     *
     * @return
     */
    public BootstrapTableVo getTemplateList(Integer type, Integer category) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setType(type);
        reportTemplate.setCategory(category);
        List<ReportTemplate> list = reportTemplateDao.getListObject(reportTemplate);
        List<ReportTemplateVo> voList = LangUtils.transform(list, o -> getReportTemplateVos(o));
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<ReportTemplateVo>() : voList);
        return bootstrapTableVo;
    }


    public ReportTemplate getDbTemplate(Integer type, Integer category) {

        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setType(type);
        reportTemplate.setCategory(category);
        List<ReportTemplate> list = reportTemplateDao.getListObject(reportTemplate);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public ReportTemplateVo getReportTemplateVos(ReportTemplate reportTemplate) {
        ReportTemplateVo reportTemplateVo = new ReportTemplateVo();
        BeanUtils.copyProperties(reportTemplate, reportTemplateVo);
        if (reportTemplate.getType() != null)
            reportTemplateVo.setTypeName(cmsBaseDataDicService.getCacheDataDicById(reportTemplate.getType()).getName());
        if (reportTemplate.getCategory() != null)
            reportTemplateVo.setCategoryName(cmsBaseDataDicService.getCacheDataDicById(reportTemplate.getCategory()).getName());
        return reportTemplateVo;
    }

    /**
     * 保存模板数据
     *
     * @param reportTemplateDto
     */
    public void saveTemplate(ReportTemplate reportTemplateDto) throws BusinessException {
        ReportTemplate reportTemplate = null;
        if (reportTemplateDto.getId() != null && reportTemplateDto.getId() > 0) {
            reportTemplate = reportTemplateDao.getSingleObject(reportTemplateDto.getId());
            if (reportTemplate != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                //将节点存入模型节点表
                try {
                    BeanUtils.copyProperties(reportTemplateDto, reportTemplate);
                } catch (Exception e) {
                    throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
                }
                if (!reportTemplateDao.updateObject(reportTemplate)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }
        } else {
            reportTemplate = new ReportTemplate();
            try {
                BeanUtils.copyProperties(reportTemplateDto, reportTemplate);
            } catch (Exception e) {
                throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
            }
            if (!reportTemplateDao.addObject(reportTemplate)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            //更新附件id
            BaseAttachment baseAttachment = new BaseAttachment();
            baseAttachment.setTableName("tb_report_template");
            baseAttachment.setTableId(reportTemplateDto.getId());
            baseAttachment.setCreater(processControllerComponent.getThisUser());
            BaseAttachment sysAttachmentNew = new BaseAttachment();
            sysAttachmentNew.setTableId(reportTemplate.getId());
            baseAttachmentDao.updateAttachementByExample(baseAttachment, sysAttachmentNew);
        }
        //processControllerComponentCms.RemoveRedisKeyValues(CmsCacheConstant.PMCC_CONTRACT_TEMPLATE, "");
    }

    /**
     * 删除模板信息
     *
     * @param id
     */
    public void deleteTemplate(Integer id) {
        reportTemplateDao.deleteObject(id);
    }

    /**
     * 获取模板书签信息列表
     *
     * @return
     */

    public ReportTemplateBookmarkVo getReportTemplateBookmarkVo(ReportTemplateBookmark reportTemplateBookmark) {
        ReportTemplateBookmarkVo reportTemplateBookmarkVo = new ReportTemplateBookmarkVo();
        BeanUtils.copyProperties(reportTemplateBookmark, reportTemplateBookmarkVo);
        return reportTemplateBookmarkVo;
    }

    public BootstrapTableVo getTemplateBookmarkList(Integer templateId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ReportTemplateBookmark> list = reportTemplateBookmarkDao.getListObject(templateId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<ReportTemplateBookmark>() : list);
        return bootstrapTableVo;
    }

    /**
     * 保存模板书签数据
     *
     * @param reportTemplateBookmarkDto
     */
    public void saveTemplateBookmark(ReportTemplateBookmark reportTemplateBookmarkDto) throws BusinessException {
        ReportTemplateBookmark reportTemplateBookmark = null;
        if (!reportTemplateBookmarkDto.getName().startsWith("PO_"))
            throw new BusinessException("word设置的书签，书签名称必须以PO_作为前缀");
        if (reportTemplateBookmarkDto.getId() != null && reportTemplateBookmarkDto.getId() > 0) {
            reportTemplateBookmark = reportTemplateBookmarkDao.getSingleObject(reportTemplateBookmarkDto.getId());
            if (reportTemplateBookmark != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                try {
                    BeanUtils.copyProperties(reportTemplateBookmarkDto, reportTemplateBookmark);
                } catch (Exception e) {
                    throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
                }
                if (!reportTemplateBookmarkDao.updateObject(reportTemplateBookmark)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }
        } else {
            reportTemplateBookmark = new ReportTemplateBookmark();
            try {
                BeanUtils.copyProperties(reportTemplateBookmarkDto, reportTemplateBookmark);
            } catch (Exception e) {
                throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
            }
            if (!reportTemplateBookmarkDao.addObject(reportTemplateBookmark)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }

    /**
     * 删除模板书签信息
     *
     * @param id
     */
    public void deleteTemplateBookmark(Integer id) {
        reportTemplateBookmarkDao.deleteObject(id);
    }

    /**
     * 删除模板书签信息by templateId
     *
     * @param templateId
     */
    public void deleteBookmarkByTemplateId(Integer templateId) {
        reportTemplateBookmarkDao.deleteObjectByTemplateId(templateId);
    }

}

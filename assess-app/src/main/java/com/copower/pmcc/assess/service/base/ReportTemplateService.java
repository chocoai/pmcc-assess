package com.copower.pmcc.assess.service.base;


import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.ReportTemplateBookmarkDao;
import com.copower.pmcc.assess.dal.dao.ReportTemplateDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;
import com.copower.pmcc.assess.dto.output.report.ReportTemplateBookmarkVo;
import com.copower.pmcc.assess.dto.output.report.ReportTemplateVo;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
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
    private ServiceComponent serviceComponent;
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
    public BootstrapTableVo getTemplateList(Integer subjectId, Integer contractType) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setSubjectId(subjectId);
        reportTemplate.setContractType(contractType);
        List<ReportTemplate> list = reportTemplateDao.getListObject(reportTemplate);
        List<ReportTemplateVo> voList = LangUtils.transform(list, o -> getReportTemplateVos(o));
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<ReportTemplateVo>() : voList);
        return bootstrapTableVo;
    }

    public ReportTemplate getTemplate(Integer subjectId, Integer contractType) {
        return null;
//        try {
//            String key = String.format("%s:%s", subjectId.toString(), contractType.toString());
//            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(CmsCacheConstant.PMCC_CONTRACT_TEMPLATE_SUBJECT, key);
//            return LangUtils.singleCache(costsKeyPrefix, key, ReportTemplate.class, input -> getDbTemplate(subjectId, contractType));
//        } catch (Exception e) {
//            return getDbTemplate(subjectId, contractType);
//        }
    }

    public ReportTemplate getDbTemplate(Integer subjectId, Integer contractType) {

        ReportTemplate reportTemplate = new ReportTemplate();
        reportTemplate.setSubjectId(subjectId);
        reportTemplate.setContractType(contractType);
        List<ReportTemplate> list = reportTemplateDao.getListObject(reportTemplate);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public ReportTemplateVo getReportTemplateVos(ReportTemplate reportTemplate) {
        ReportTemplateVo reportTemplateVo = new ReportTemplateVo();
        BeanUtils.copyProperties(reportTemplate, reportTemplateVo);
        if (reportTemplate.getSubjectId() != null)
            reportTemplateVo.setSubjectIdName(cmsBaseDataDicService.getCacheDataDicById(reportTemplate.getSubjectId()).getName());
        if (reportTemplate.getContractType() != null)
            reportTemplateVo.setContractTypeName(cmsBaseDataDicService.getCacheDataDicById(reportTemplate.getContractType()).getName());
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
            baseAttachment.setTableName("tb_cms_template");
            baseAttachment.setTableId(reportTemplateDto.getId());
            baseAttachment.setCreater(serviceComponent.getThisUser());
            BaseAttachment sysAttachmentNew = new BaseAttachment();
            sysAttachmentNew.setTableId(reportTemplate.getId());
            baseAttachmentDao.updateAttachementByExample(baseAttachment, sysAttachmentNew);
        }
        //serviceComponentCms.RemoveRedisKeyValues(CmsCacheConstant.PMCC_CONTRACT_TEMPLATE, "");
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
        List<ReportTemplateBookmarkVo> transform = getTemplateBookmarkVos(list);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(transform) ? new ArrayList<ReportTemplateBookmarkVo>() : transform);
        return bootstrapTableVo;
    }

    public List<ReportTemplateBookmarkVo> getTemplateBookmarkVos(List<ReportTemplateBookmark> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, input -> {
            ReportTemplateBookmarkVo reportTemplateBookmarkVo = new ReportTemplateBookmarkVo();
            BeanUtils.copyProperties(input, reportTemplateBookmarkVo);
            return reportTemplateBookmarkVo;
        });
    }

    public List<ReportTemplateBookmark> getReportTemplateBookmarks(Integer templateId) {
        return null;
//        try {
//            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(CmsCacheConstant.PMCC_CONTRACT_BOOKMARK_TEMPLATE, templateId.toString());
//            return LangUtils.listCache(costsKeyPrefix, templateId, ReportTemplateBookmark.class, input -> reportTemplateBookmarkDao.getListObject(input));
//        } catch (Exception e) {
//            return reportTemplateBookmarkDao.getListObject(templateId);
//        }
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

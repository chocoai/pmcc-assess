package com.copower.pmcc.assess.service.document;

import com.copower.pmcc.assess.common.DocumentWordUtils;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataNumberRuleDao;
import com.copower.pmcc.assess.dal.basis.dao.document.DocumentDao;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateBookmark;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateField;
import com.copower.pmcc.assess.dto.output.DocumentTemplateFieldVo;
import com.copower.pmcc.assess.dto.output.document.DocumentTemplateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2019-05-30
 * @time: 18:07
 */
@Service
public class DocumentTemplateService {
    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private DocumentWordUtils documentWordUtils;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataNumberRuleDao dataNumberRuleDao;

    ///region 模板
    public void saveTemplate(DocumentTemplate documentTemplate) throws BusinessException {
        if (documentTemplate.getId() != null && documentTemplate.getId() > 0) {
            documentDao.updateDocumentTemplate(documentTemplate);
        } else {
            documentDao.addDocumentTemplate(documentTemplate);
            //处理附件
            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableId(0);
            where.setCreater(processControllerComponent.getThisUser());
            where.setTableName(FormatUtils.entityNameConvertToTableName(DocumentTemplate.class));
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(documentTemplate.getId());

            erpRpcAttachmentService.updateAttachmentByParam(where, sysAttachmentDto);
        }

        String templateText = "";//模板内容
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(DocumentTemplate.class));
        baseAttachment.setTableId(documentTemplate.getId());
        List<SysAttachmentDto> attachmentList = erpRpcAttachmentService.getAttachmentList(baseAttachment);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            templateText = documentWordUtils.getStringBuilder(attachmentList.get(0));
        }
        //取出模板中对应的需要处理的数据
        if (StringUtils.isNotBlank(templateText)) {
            getBasetemplateMarks(documentTemplate.getId(), templateText);
        }

    }

    private void getBasetemplateMarks(Integer templateId, String templateText) {
        Matcher m = Pattern.compile("\\$\\{.*?\\}").matcher(templateText);

        HashSet h = new HashSet();
        while (m.find()) {
            h.add(m.group());
        }
        List<String> stringList = new ArrayList<>();
        stringList.addAll(h);
        List<DocumentTemplateBookmark> baseTemplateBookmarks = documentDao.getDocumentTemplateBookmarkList(templateId);
        if (CollectionUtils.isNotEmpty(baseTemplateBookmarks)) {
            //删除已在模板中找不到书签
            List<DocumentTemplateBookmark> filter = LangUtils.filter(baseTemplateBookmarks, o -> !stringList.contains(o.getName()));
            if (CollectionUtils.isNotEmpty(filter)) {
                List<Integer> bookmarkIds = LangUtils.transform(filter, o -> o.getId());
                documentDao.deleteDocumentTemplateBookmark(bookmarkIds);
            }
            //将模板中新增加的书签添加到数据库中
            List<String> transform = LangUtils.transform(baseTemplateBookmarks, o -> o.getName());
            stringList.removeAll(transform);
            baseTemplateBookmarks.clear();
            for (String s : stringList) {
                DocumentTemplateBookmark cmsTemplateBookmark = new DocumentTemplateBookmark();
                cmsTemplateBookmark.setTemplateId(templateId);
                cmsTemplateBookmark.setName(s);
                documentDao.addDocumentTemplateBookmark(cmsTemplateBookmark);
            }

        } else {//如果还没有书签，则将取得书签加入数据库
            baseTemplateBookmarks.clear();
            for (String s : stringList) {
                DocumentTemplateBookmark cmsTemplateBookmark = new DocumentTemplateBookmark();
                cmsTemplateBookmark.setTemplateId(templateId);
                cmsTemplateBookmark.setName(s);
                documentDao.addDocumentTemplateBookmark(cmsTemplateBookmark);
            }
        }
    }

    public List<DocumentTemplate> getDocumentTemplateList(String search,Integer templateType,String projectType) {
        if (StringUtils.isNotBlank(search)) {
            search = String.format("%%%s%%", search);
        }
        List<DocumentTemplate> documentTemplateList = documentDao.getDocumentTemplateList(search, templateType,projectType);
        return documentTemplateList;
    }

    public DocumentTemplate getDocumentTemplate(Integer id) {
        return documentDao.getDocumentTemplate(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDocumentTemplate(Integer id) {
        documentDao.deleteDocumentTemplate(id);
    }

    public DocumentTemplateVo getDocumentTemplateVo(DocumentTemplate documentTemplate) {
        if (documentTemplate == null) {
            return null;
        }
        DocumentTemplateVo vo = new DocumentTemplateVo();
        BeanUtils.copyProperties(documentTemplate, vo);
        if(documentTemplate.getTemplateType()!=null){
            vo.setTemplateTypeName(baseDataDicService.getNameById(documentTemplate.getTemplateType()));
        }
        if(documentTemplate.getAssessProjectType()!=null){
            vo.setAssessProjectTypeName(AssessProjectTypeEnum.getDecByKey(documentTemplate.getAssessProjectType()));
        }
        if(documentTemplate.getNumbetRuleId()!=null){
            DataNumberRule numberRule = dataNumberRuleDao.getDataNumberRuleById(documentTemplate.getNumbetRuleId());
            if(numberRule!=null&&numberRule.getReportType()!=null){
                vo.setReportTypeName(baseDataDicService.getNameById(numberRule.getReportType()));
            }
        }
        return vo;
    }

    ///endregion

    ///region 模板书签
    public List<DocumentTemplateBookmark> getCmsTemplateBookmark(Integer templateId) {
        DocumentTemplateBookmark cmsTemplateBookmark = new DocumentTemplateBookmark();
        cmsTemplateBookmark.setTemplateId(templateId);
        List<DocumentTemplateBookmark> listObject = documentDao.getDocumentTemplateBookmarkList(cmsTemplateBookmark);
        return listObject;
    }

    public void saveCmsTemplateBookmark(DocumentTemplateBookmark documentTemplateBookmark) throws BusinessException {
        try {
            if (documentTemplateBookmark.getId() != null && documentTemplateBookmark.getId() > 0) {
                documentDao.updateDocumentTemplateBookmark(documentTemplateBookmark);
            } else {
                documentDao.addDocumentTemplateBookmark(documentTemplateBookmark);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
    ///endregion

    ///region 模板字段
    public List<DocumentTemplateFieldVo> getDocumentTemplateField(Integer templateId) {
        DocumentTemplateField cmsTemplateField = new DocumentTemplateField();
        cmsTemplateField.setTemplateId(templateId);
        cmsTemplateField.setBisDelete(false);
        List<DocumentTemplateField> listObject = documentDao.getDocumentTemplateFieldList(cmsTemplateField);
        List<DocumentTemplateFieldVo> transform = LangUtils.transform(listObject, o -> {
            DocumentTemplateFieldVo cmsTemplateFieldVo = new DocumentTemplateFieldVo();
            BeanUtils.copyProperties(o, cmsTemplateFieldVo);
            CustomTableTypeEnum customTableTypeEnum = CustomTableTypeEnum.getCustomTypeByColumnsPrefix(o.getFieldType());
            if (customTableTypeEnum != null) {
                cmsTemplateFieldVo.setFieldTypeName(customTableTypeEnum.getName());
            }
            return cmsTemplateFieldVo;
        });
        return transform;
    }

    public List<DocumentTemplateField> getFieldList(Integer templateId) {
        DocumentTemplateField cmsTemplateField = new DocumentTemplateField();
        cmsTemplateField.setTemplateId(templateId);
        cmsTemplateField.setBisDelete(false);
        List<DocumentTemplateField> list = documentDao.getDocumentTemplateFieldList(cmsTemplateField);
        return list;
    }

    public void saveDocumentTemplateField(DocumentTemplateField cmsTemplateField) throws BusinessException {
        try {
            if (cmsTemplateField.getId() != null && cmsTemplateField.getId() > 0) {
                documentDao.updateDocumentTemplateField(cmsTemplateField);
            } else {
                cmsTemplateField.setCreator(processControllerComponent.getThisUser());
                cmsTemplateField.setBisDelete(false);
                documentDao.addDocumentTemplateField(cmsTemplateField);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public void deleteDocumentTemplateField(Integer id) throws BusinessException {
        documentDao.deleteDocumentTemplateField(id);
    }
    ///endregion
}

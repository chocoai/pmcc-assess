package com.copower.pmcc.assess.dal.basis.dao.document;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.*;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2019-05-30
 * @time: 18:05
 */
@Repository
public class DocumentDao {
    @Autowired
    private DocumentSendMapper documentSendMapper;
    @Autowired
    private DocumentTemplateMapper documentTemplateMapper;
    @Autowired
    private DocumentTemplateBookmarkMapper documentTemplateBookmarkMapper;
    @Autowired
    private DocumentTemplateFieldMapper documentTemplateFieldMapper;
    @Autowired
    private DocumentOpinionMapper documentOpinionMapper;

    ///region 项目发文
    public List<DocumentSend> getDocumentSendList(DocumentSend documentSend) {
        DocumentSendExample example = new DocumentSendExample();
        DocumentSendExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(documentSend, criteria);
        return documentSendMapper.selectByExampleWithBLOBs(example);
    }

    public DocumentSend getDocumentSend(Integer id) {
        return documentSendMapper.selectByPrimaryKey(id);
    }

    public void addDocumentSend(DocumentSend documentSend) {
        documentSendMapper.insertSelective(documentSend);
    }

    public void deleteDocumentSend(Integer id) {
        documentSendMapper.deleteByPrimaryKey(id);
    }

    public void updateDocumentSend(DocumentSend documentSend) {
        documentSendMapper.updateByPrimaryKeySelective(documentSend);
    }
    ///endregion

    ///region 项目发文模板

    public List<DocumentTemplate> getDocumentTemplateList(DocumentTemplate documentTemplate) {
        DocumentTemplateExample example = new DocumentTemplateExample();
        MybatisUtils.convertObj2Example(documentTemplate, example);
        return documentTemplateMapper.selectByExample(example);
    }
    public List<DocumentTemplate> getDocumentTemplateList(String search, Integer templateType) {
        DocumentTemplateExample example = new DocumentTemplateExample();
       if(StringUtils.isNotBlank(search))
       {
           example.createCriteria().andTemplateNameLike(search);
       }
       if(templateType != null){
           example.createCriteria().andTemplateTypeEqualTo(templateType);
       }
        return documentTemplateMapper.selectByExample(example);
    }
    public DocumentTemplate getDocumentTemplate(Integer id) {
        return documentTemplateMapper.selectByPrimaryKey(id);
    }

    public void addDocumentTemplate(DocumentTemplate documentTemplate) {
        documentTemplateMapper.insertSelective(documentTemplate);
    }

    public void deleteDocumentTemplate(Integer id) {
        documentTemplateMapper.deleteByPrimaryKey(id);
        DocumentTemplateBookmarkExample example = new DocumentTemplateBookmarkExample();
        example.createCriteria().andTemplateIdEqualTo(id);
        documentTemplateBookmarkMapper.deleteByExample(example);
        DocumentTemplateFieldExample documentTemplateFieldExample = new DocumentTemplateFieldExample();
        documentTemplateFieldExample.createCriteria().andTemplateIdEqualTo(id);
        documentTemplateFieldMapper.deleteByExample(documentTemplateFieldExample);
    }

    public void updateDocumentTemplate(DocumentTemplate documentTemplate) {
        documentTemplateMapper.updateByPrimaryKeySelective(documentTemplate);
    }
    ///endregion

    ///region 项目发文书签
    public List<DocumentTemplateBookmark> getDocumentTemplateBookmarkList(DocumentTemplateBookmark documentBookmark) {
        DocumentTemplateBookmarkExample example = new DocumentTemplateBookmarkExample();
        MybatisUtils.convertObj2Example(documentBookmark, example);
        return documentTemplateBookmarkMapper.selectByExample(example);
    }
    public List<DocumentTemplateBookmark> getDocumentTemplateBookmarkList(Integer templateId) {
        DocumentTemplateBookmarkExample example = new DocumentTemplateBookmarkExample();
        example.createCriteria().andTemplateIdEqualTo(templateId);
        return documentTemplateBookmarkMapper.selectByExample(example);
    }
    public DocumentTemplateBookmark getDocumentTemplateBookmark(Integer id) {
        return documentTemplateBookmarkMapper.selectByPrimaryKey(id);
    }

    public void addDocumentTemplateBookmark(DocumentTemplateBookmark documentBookmark) {
        documentTemplateBookmarkMapper.insertSelective(documentBookmark);
    }

    public void deleteDocumentTemplateBookmark(Integer id) {
        documentTemplateBookmarkMapper.deleteByPrimaryKey(id);
    }
    public void deleteDocumentTemplateBookmark(List<Integer> ids) {
        DocumentTemplateBookmarkExample example = new DocumentTemplateBookmarkExample();
        example.createCriteria().andIdIn(ids);
        documentTemplateBookmarkMapper.deleteByExample(example);
    }
    public void updateDocumentTemplateBookmark(DocumentTemplateBookmark documentBookmark) {
        documentTemplateBookmarkMapper.updateByPrimaryKeySelective(documentBookmark);
    }
    ///endregion

    ///region 项目发文字段
    public List<DocumentTemplateField> getDocumentTemplateFieldList(DocumentTemplateField documentField) {
        DocumentTemplateFieldExample example = new DocumentTemplateFieldExample();
        MybatisUtils.convertObj2Example(documentField, example);
        return documentTemplateFieldMapper.selectByExample(example);
    }

    public DocumentTemplateField getDocumentTemplateField(Integer id) {
        return documentTemplateFieldMapper.selectByPrimaryKey(id);
    }

    public void addDocumentTemplateField(DocumentTemplateField documentField) {
        documentTemplateFieldMapper.insertSelective(documentField);
    }

    public void deleteDocumentTemplateField(Integer id) {
        documentTemplateFieldMapper.deleteByPrimaryKey(id);
    }

    public void updateDocumentTemplateField(DocumentTemplateField documentField) {
        documentTemplateFieldMapper.updateByPrimaryKeySelective(documentField);
    }
    ///endregion


    ///region 意见稿
    public List<DocumentOpinion> getDocumentOpinionList(DocumentOpinion documentOpinion) {
        DocumentOpinionExample example = new DocumentOpinionExample();
        MybatisUtils.convertObj2Example(documentOpinion, example);
        return documentOpinionMapper.selectByExampleWithBLOBs(example);
    }

    public DocumentOpinion getDocumentOpinion(Integer id) {
        return documentOpinionMapper.selectByPrimaryKey(id);
    }

    public void addDocumentOpinion(DocumentOpinion documentOpinion) {
        documentOpinionMapper.insertSelective(documentOpinion);
    }

    public void deleteDocumentOpinion(Integer id) {
        documentOpinionMapper.deleteByPrimaryKey(id);
    }

    public void updateDocumentOpinion(DocumentOpinion documentOpinion) {
        documentOpinionMapper.updateByPrimaryKeySelective(documentOpinion);
    }
    ///endregion
}

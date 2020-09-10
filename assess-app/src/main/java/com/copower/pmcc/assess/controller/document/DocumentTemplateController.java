package com.copower.pmcc.assess.controller.document;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.document.DocumentBaseEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateBookmark;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateField;
import com.copower.pmcc.assess.dto.output.DocumentTemplateFieldVo;
import com.copower.pmcc.assess.dto.output.document.DocumentTemplateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.CustomTableTypeDto;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2019-05-30
 * @time: 18:34
 */
@Controller
@RequestMapping(value = "/DocumentTemplate", name = "项目过程发文模板")
public class DocumentTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentTemplateController.class);

    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @GetMapping(value = "/index", name = "模板首页")
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/document/templateManage");
        List<CustomTableTypeDto> customSetType = CustomTableTypeEnum.getCustomSetType();
        modelAndView.addObject("customSetType", customSetType);
        List<BaseDataDic> templateTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE);
        modelAndView.addObject("templateTypes", templateTypes);
        modelAndView.addObject("assessProjectTypeList", projectInfoService.getAssessProjectTypeList());

        return modelAndView;
    }

    //region 模板类型
    @ResponseBody
    @GetMapping(value = "/getDocumentTemplate", name = "取得模板类型")
    public BootstrapTableVo getDocumentTemplate(String templateName, Integer templateType, String assessProjectType) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DocumentTemplate> documentTemplateList = documentTemplateService.getDocumentTemplateList(templateName, templateType, assessProjectType);
        List<DocumentTemplateVo> transform = LangUtils.transform(documentTemplateList, o -> documentTemplateService.getDocumentTemplateVo(o));
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(transform) ? new ArrayList<DocumentTemplateVo>() : transform);
        return bootstrapTableVo;
    }

    @ResponseBody
    @PostMapping(value = "/saveTemplate", name = "保存模板类型")
    public HttpResult saveTemplate(String formData) {
        try {
            DocumentTemplate documentTemplate = JSON.parseObject(formData, DocumentTemplate.class);
            documentTemplateService.saveTemplate(documentTemplate);
        } catch (Exception e) {
            logger.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    //endregion

    //region 模板书签操作
    @ResponseBody
    @GetMapping(value = "/getCmsTemplateBookmark", name = "取得书签列表")
    public BootstrapTableVo getCmsTemplateBookmark(Integer templateId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DocumentTemplateBookmark> cmsTemplateBookmark = documentTemplateService.getCmsTemplateBookmark(templateId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(cmsTemplateBookmark) ? new ArrayList<DocumentTemplateBookmark>() : cmsTemplateBookmark);
        return bootstrapTableVo;
    }

    @ResponseBody
    @PostMapping(value = "/saveCmsTemplateBookmark", name = "保存模板书签")
    public HttpResult saveCmsTemplateBookmark(DocumentTemplateBookmark cmsTemplateBookmark) {
        try {
            documentTemplateService.saveCmsTemplateBookmark(cmsTemplateBookmark);
        } catch (Exception e) {
            logger.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @GetMapping(value = "/getBookmarkDatasource", name = "取得模板书签数据源")
    public HttpResult getBookmarkDatasource(Integer templateId) {
        try {
            List<DocumentTemplateField> fieldList = documentTemplateService.getFieldList(templateId);
            List<KeyValueDto> keyValueDtoList = LangUtils.transform(fieldList, o -> {
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(o.getName());
                keyValueDto.setValue(o.getDisplayName());
                return keyValueDto;
            });
            //取得相应的数据配置的字段
            List<KeyValueDto> valueDtos = DocumentBaseEnum.getEnumList();
            keyValueDtoList.addAll(valueDtos);
            return HttpResult.newCorrectResult(keyValueDtoList);
        } catch (Exception e) {
            logger.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    //endregion

    //region 模板字段操作
    @ResponseBody
    @GetMapping(value = "/getCmsTemplateField", name = "取得字段列表")
    public BootstrapTableVo getCmsTemplateField(Integer templateId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());

        List<DocumentTemplateFieldVo> documentTemplateField = documentTemplateService.getDocumentTemplateField(templateId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(documentTemplateField) ? new ArrayList<DocumentTemplateField>() : documentTemplateField);
        return bootstrapTableVo;
    }

    @ResponseBody
    @GetMapping(value = "/getCmsTemplateFieldAll", name = "取得字段列表")
    public HttpResult getCmsTemplateFieldAll(Integer templateId) {
        List<DocumentTemplateField> fieldList = documentTemplateService.getFieldList(templateId);
        return HttpResult.newCorrectResult(fieldList);
    }

    @ResponseBody
    @PostMapping(value = "/saveCmsTemplateField", name = "保存模板字段")
    public HttpResult saveCmsTemplateField(DocumentTemplateField cmsTemplateField) {
        try {
            documentTemplateService.saveDocumentTemplateField(cmsTemplateField);
        } catch (BusinessException e) {
            logger.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/deleteCmsTemplateField", name = "删除字段")
    public HttpResult deleteCmsTemplateField(Integer id) {
        try {
            documentTemplateService.deleteDocumentTemplateField(id);
        } catch (Exception e) {
            logger.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    //endregion

}

package com.copower.pmcc.assess.controller.office;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.BaseReplaceRecord;
import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseReplaceRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.PageOfficeLink;
import com.zhuozhengsoft.pageoffice.wordreader.DataRegion;
import com.zhuozhengsoft.pageoffice.wordreader.WordDocument;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpc on 2017/9/7.
 */
@Controller
@RequestMapping("/zhuozheng")
public class ZhuoZhengController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZhuoZhengController.class);

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private BaseReplaceRecordService baseReplaceRecordService;
    @Autowired
    private ApplicationConstant applicationConstant;

    /**
     * 卓正首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(request.getContextPath() + "/office/zhuozheng/index");
        return modelAndView;
    }


    /**
     * 显示消息
     */
    @RequestMapping(value = "/showMessage", method = RequestMethod.GET)
    public String showMessage(String message) {
        return message;
    }

    /**
     * 更新文件信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateFileInfo", method = RequestMethod.POST)
    public HttpResult updateFileInfo(Integer id) {
        try {
            //hrBaseAttachmentService.updateFileInfo(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 文档查看
     *
     * @param attachmentId
     * @return
     */
    @RequestMapping(value = "/viewFile", method = RequestMethod.GET)
    public ModelAndView viewFile(Integer attachmentId) {
        BaseAttachment sysAttachment = baseAttachmentService.getBaseAttachment(attachmentId);
        if (sysAttachment == null)
            return new ModelAndView(MessageFormat.format("redirect:/zhuozheng/showMessage?message={0}", encode("附件不存在或已删除")));
        String viewName = "/office/zhuozheng/viewOffice";
        String fileExtension = sysAttachment.getFileExtension();
        fileExtension = StringUtils.defaultIfBlank(fileExtension, "").replaceAll("\\.*", "");
        OpenModeType openModeType = null;
        switch (fileExtension) {
            case "pdf":
                viewName = "/office/zhuozheng/viewPdf";
                break;
            case "doc":
            case "docx":
                openModeType = OpenModeType.docReadOnly;
                break;
            case "xls":
            case "xlsx":
                openModeType = OpenModeType.xlsReadOnly;
                break;
            case "ppt":
                openModeType = OpenModeType.pptReadOnly;
                break;
        }
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("openModeType", openModeType);
        modelAndView.addObject("attachmentId", attachmentId);
        return modelAndView;
    }

    /**
     * 文档在线编辑
     *
     * @param attachmentId
     * @return
     */
    @RequestMapping(value = "/editFile", method = RequestMethod.GET)
    public ModelAndView editFile(Integer attachmentId) {
        BaseAttachment sysAttachment = baseAttachmentService.getBaseAttachment(attachmentId);
        if (sysAttachment == null)
            return new ModelAndView(MessageFormat.format("redirect:/zhuozheng/showMessage?message={0}", encode("附件不存在或已删除")));
        String viewName = "/office/zhuozheng/editOffice";
        OpenModeType openModeType = null;
        switch (sysAttachment.getFileExtension()) {
            case "doc":
            case "docx":
                openModeType = OpenModeType.docNormalEdit;
                break;
            case "xls":
            case "xlsx":
                openModeType = OpenModeType.xlsNormalEdit;
                break;
            case "ppt":
                openModeType = OpenModeType.pptNormalEdit;
        }
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("openModeType", openModeType);
        modelAndView.addObject("attachmentId", attachmentId);
        return modelAndView;
    }

    /**
     * 获取打开的url
     *
     * @param attachmentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getViewFileUrl", method = RequestMethod.GET)
    public HttpResult getViewFileUrl(Integer attachmentId, HttpServletRequest request) {
        String url = PageOfficeLink.openWindow(request,
                MessageFormat.format("/zhuozheng/viewFile?attachmentId={0}", String.valueOf(attachmentId)), "width=1400px;height=800px;");
        return HttpResult.newCorrectResult(url);
    }

    /**
     * 获取编辑的url
     *
     * @param attachmentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEditFileUrl", method = RequestMethod.GET)
    public HttpResult getEditFileUrl(Integer attachmentId, HttpServletRequest request) {
        String url = PageOfficeLink.openWindow(request,
                MessageFormat.format("/zhuozheng/editFile?attachmentId={0}", String.valueOf(attachmentId)), "width=1400px;height=800px;");
        return HttpResult.newCorrectResult(url);
    }

    /**
     * 文本编码
     *
     * @param str
     * @return
     */
    private String encode(String str) {
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {

        }
        return str;
    }

    /**
     * 保存编辑后的附件
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public void saveFile(HttpServletRequest request, HttpServletResponse response) {
        FileSaver fs = new FileSaver(request, response);
        try {
            Integer attachmentId = Integer.parseInt(fs.getFormField("attachmentId"));
            BaseAttachment sysAttachment = baseAttachmentService.getBaseAttachment(attachmentId);
            try {
                String isKeep = fs.getFormField("isKeep");
                if (StringUtils.equals(isKeep, "true")) {//先将原文件存档一次
                    baseAttachmentService.saveKeepFile(attachmentId);
                }
            } catch (Exception e) {
                LOGGER.error("保存在线编辑Keep附件异常，异常原因：" + e.getMessage(), e);
            }
            baseAttachmentService.updateFileSize(attachmentId, fs.getFileSize());
            ftpUtilsExtense.uploadFilesToFTP(sysAttachment.getFilePath(), fs.getFileStream(), sysAttachment.getFtpFilesName());
        } catch (Exception e) {
            LOGGER.error("保存在线编辑附件异常，异常原因：" + e.getMessage(), e);
        } finally {
            fs.close();
        }
    }

    /**
     * 自动记录书签数据
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/autoWriteBookmark", method = RequestMethod.POST)
    public void autoWriteBookmark(HttpServletRequest request, HttpServletResponse response) {
        WordDocument wordDocument = new WordDocument(request, response);
        ArrayList<DataRegion> dataRegions = wordDocument.getDataRegions();
        String strTemplateId = wordDocument.getFormField("templateId");
        Integer templateId = Integer.valueOf(strTemplateId);
        //先清空数据
//        reportTemplateService.deleteBookmarkByTemplateId(templateId);
//        if (CollectionUtils.isNotEmpty(dataRegions)) {
//            for (DataRegion dataRegion : dataRegions) {
//                ReportTemplateBookmark cmsTemplateBookmark = new ReportTemplateBookmark();
//                cmsTemplateBookmark.setTemplateId(templateId);
//                cmsTemplateBookmark.setName("PO_" + dataRegion.getName());
//                try {
//                    reportTemplateService.saveTemplateBookmark(cmsTemplateBookmark);
//                } catch (BusinessException e) {
//                    LOGGER.error(e.getMessage());
//                }
//            }
//        }
        wordDocument.close();
    }

    /**
     * 书签读取
     *
     * @param templateId
     * @return
     */
    @RequestMapping(value = "/readBookmark", method = RequestMethod.GET)
    public ModelAndView readBookmark(Integer templateId) {
        BaseAttachment cmsBaseAttachment = new BaseAttachment();
        cmsBaseAttachment.setTableId(templateId);
        cmsBaseAttachment.setTableName("tb_cms_template");
        List<BaseAttachment> cmsBaseAttachments = baseAttachmentService.getAttachmentList(cmsBaseAttachment);
        if (CollectionUtils.isEmpty(cmsBaseAttachments))
            return new ModelAndView(MessageFormat.format("redirect:/zhuozheng/showMessage?message={0}", encode("请上传合同模板附件")));
        Integer attachmentId = cmsBaseAttachments.get(0).getId();
        String viewName = "/office/zhuozheng/readBookmark";
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("templateId", templateId);
        modelAndView.addObject("attachmentId", attachmentId);

        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
        poCtrl.setCaption("读取书签");
        poCtrl.setSaveDataPage(String.format("/%s/zhuozheng/autoWriteBookmark", applicationConstant.getAppKey()));
        String url = String.format("/%s/attachment/downloadFileFromServer?id=%s", applicationConstant.getAppKey(), attachmentId);
        poCtrl.webOpen(url, OpenModeType.docNormalEdit, "admin");
        modelAndView.addObject("poCtrl", poCtrl);
        return modelAndView;
    }



    /**
     * 书签替换
     *
     * @param dataId
     * @return
     */
    @RequestMapping(value = "/replaceBookmark", method = RequestMethod.GET)
    public ModelAndView replaceBookmark(Integer dataId) {
        BaseReplaceRecord baseReplaceRecord = baseReplaceRecordService.getRecordById(dataId);
        if (baseReplaceRecord == null)
            return new ModelAndView(MessageFormat.format("redirect:/zhuozheng/showMessage?message={0}", encode("附件不存在或已删除")));
        String viewName = "/office/zhuozheng/replaceBookmark";
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("attachmentId", baseReplaceRecord.getAttachmentId());
        List<KeyValueDto> keyValueDtos = JSON.parseArray(baseReplaceRecord.getContent().toString(), KeyValueDto.class);
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
        poCtrl.setCaption("书签替换");
        com.zhuozhengsoft.pageoffice.wordwriter.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordwriter.WordDocument();
        if (CollectionUtils.isNotEmpty(keyValueDtos)) {
            for (KeyValueDto keyValueDto : keyValueDtos) {
                try {
                    doc.openDataRegion(keyValueDto.getKey()).setValue(StringUtils.defaultIfBlank(keyValueDto.getValue(),""));
                } catch (Exception e) {
                    //不处理
                    LOGGER.error(e.getMessage());
                }
            }
        }
        poCtrl.setWriter(doc);
        poCtrl.setSaveFilePage(String.format("/%s/zhuozheng/saveFile", applicationConstant.getAppKey()));
        String url = String.format("/%s/attachment/downloadFileFromServer?id=%s", applicationConstant.getAppKey(), baseReplaceRecord.getAttachmentId());
        poCtrl.webOpen(url, OpenModeType.docNormalEdit, "admin");
        modelAndView.addObject("poCtrl", poCtrl);
        return modelAndView;
    }
}

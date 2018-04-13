package com.copower.pmcc.assess.controller;

import com.copower.pmcc.erp.api.dto.model.AttachmentDto;
import com.copower.pmcc.erp.api.dto.model.AttachmentSaveVo;
import com.copower.pmcc.erp.api.dto.model.AttachmentVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/10
 * @time: 17:22
 */
@Controller
@RequestMapping(value = "/attachment")
public class BaseAttachmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAttachmentController.class);
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    /**
     * 上传文件到服务器
     *
     * @param attachmentDto
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFileToServer", method = RequestMethod.POST)
    public HttpResult uploadFileToServer(AttachmentDto attachmentDto, HttpServletRequest request) {

        AttachmentSaveVo attachmentSaveVo = null;
        try {
            MultipartHttpServletRequest multipartRequest =
                    (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());

            Integer integer = null;
            try {
                integer = baseAttachmentService.uploadFileToServer(attachmentDto, multipartFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<AttachmentVo> filesFromServer = baseAttachmentService.getFilesFromServer(attachmentDto);
            attachmentSaveVo = new AttachmentSaveVo();
            attachmentSaveVo.setId(integer);
            attachmentSaveVo.setAttachmentVos(filesFromServer);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(attachmentSaveVo);
    }

    /**
     * 从文件服务器取得文件列表
     *
     * @param attachmentDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFilesFromServer", method = RequestMethod.GET)
    public HttpResult getFilesFromServer(AttachmentDto attachmentDto) {

        List<AttachmentVo> filesFromServer = null;
        try {
            filesFromServer = baseAttachmentService.getFilesFromServer(attachmentDto);
        } catch (Exception e) {
            HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(filesFromServer);
    }

    /**
     * 从文件服务器取得文件列表
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFilesFromServerById", method = RequestMethod.GET)
    public HttpResult getFilesFromServerById(String id) {
        List<AttachmentVo> filesFromServer = null;
        try {
            filesFromServer = baseAttachmentService.getFilesFromServer(id);
        } catch (Exception e) {
            HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(filesFromServer);
    }

    /**
     * 从文件服务器取得文件列表
     *
     * @param attachmentDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAttachmentList", method = RequestMethod.GET)
    public BootstrapTableVo getAttachmentList(AttachmentDto attachmentDto) {
        BootstrapTableVo bootstrapTableVo = null;
        try {
            bootstrapTableVo = baseAttachmentService.getAttachmentList(attachmentDto);
        } catch (Exception e) {
            LOGGER.error("获取附件列表异常根据processInsId" + attachmentDto.getProcessInsId(), e);
        }
        return bootstrapTableVo;
    }

    /**
     * 从文件服务器删除数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteFileToServer", method = RequestMethod.POST)
    public HttpResult deleteFileToServer(Integer id) {
        try {
            baseAttachmentService.deleteFileToServer(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 服务器删除数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDataToServer", method = RequestMethod.POST)
    public HttpResult deleteDataToServer(Integer id) {
        try {
            baseAttachmentService.deleteDataToServer(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/downloadFileFromServer", method = RequestMethod.GET)
    public void downloadFileFromServer(Integer id, HttpServletResponse response) throws Exception {
        baseAttachmentService.downloadFileFromServer(id, response);
    }

    @ResponseBody
    @RequestMapping(value = "/downloadKeepFileFromServer", method = RequestMethod.GET)
    public void downloadKeepFileFromServer(Integer id, HttpServletResponse response) throws Exception {
        baseAttachmentService.downloadKeepFileFromServer(id, response);
    }

    /**
     * 从文件服务器取得文件列表
     *
     * @param attachmentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAttachmentKeepList", method = RequestMethod.GET)
    public BootstrapTableVo getAttachmentKeepList(Integer attachmentId) {
        BootstrapTableVo bootstrapTableVo = null;
        try {
            bootstrapTableVo = baseAttachmentService.getAttachmentKeepList(attachmentId);
        } catch (Exception e) {
            LOGGER.error("获取附件列表异常根据", e);
        }
        return bootstrapTableVo;
    }

    /**
     * 上传base64图片到服务器
     *
     * @param attachmentDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadBase64Image", method = RequestMethod.POST)
    public HttpResult uploadBase64Image(AttachmentDto attachmentDto) {
        try {
            Integer integer = baseAttachmentService.uploadBase64Image(attachmentDto);
            return HttpResult.newCorrectResult(integer);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    /**
     * 获取附件查看地址
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getViewImageUrl", method = RequestMethod.POST)
    public HttpResult getViewImageUrl(Integer id) {
        try {
            String viewUrl = baseAttachmentService.getViewImageUrl(id);
            return HttpResult.newCorrectResult(viewUrl);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }
}

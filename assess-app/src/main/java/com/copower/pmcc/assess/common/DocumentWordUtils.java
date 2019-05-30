package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.AsposeUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018-11-13
 * @time: 17:28
 */

@Component
public class DocumentWordUtils {
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;

    public Map<String, String> createDocument(DocumentTemplate documentTemplate, Map<String, String> textValues, SysAttachmentDto ftpAttachmentDto) throws BusinessException {
        SysAttachmentDto attachmentTemplate = new SysAttachmentDto();
        attachmentTemplate.setTableName(FormatUtils.entityNameConvertToTableName(DocumentTemplate.class));
        attachmentTemplate.setTableId(documentTemplate.getId());
        List<SysAttachmentDto> attachmentList = erpRpcAttachmentService.getAttachmentList(attachmentTemplate);
        if (CollectionUtils.isEmpty(attachmentList)) {
            throw new BusinessException("没有找到相应的文件模板");
        }

        Map<String, String> map = new HashMap<String, String>();
        try {
            SysAttachmentDto sysAttachmentDto = erpRpcAttachmentService.copyFtpAttachment(attachmentList.get(0).getId(), ftpAttachmentDto);
            sysAttachmentDto.setFilePath(ftpAttachmentDto.getFilePath());
            erpRpcAttachmentService.updateAttachment(sysAttachmentDto);
            map.put("ftpPath", sysAttachmentDto.getFilePath());
            map.put("ftpFileName", sysAttachmentDto.getFtpFileName());
            String localFullPath = ftpUtilsExtense.downloadFileToLocal(attachmentList.get(0));
            //将对应的值写入文件中
            if (textValues.size() > 0) {
                AsposeUtils.replaceText(localFullPath, textValues);
            }
            map.put("localFullPath", localFullPath);

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return map;
    }

    /**
     * 读取WORD文件时，进行相应的格式化
     *
     * @param ftpAttachment
     * @return
     */
    public String getStringBuilder(SysAttachmentDto ftpAttachment) throws BusinessException {
        List<String> fileExtention = Lists.newArrayList("doc", "docx");
        if (!fileExtention.contains(ftpAttachment.getFileExtension())) {
            throw new BusinessException("不是有效的WORD文件");
        }
        String localFullPath = ftpUtilsExtense.downloadFileToLocal(ftpAttachment);
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(new File(localFullPath));
        } catch (FileNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
        String s = "";
        switch (ftpAttachment.getFileExtension()) {
            case "doc": {
                WordExtractor wordExtractor = null;
                try {
                    HWPFDocument document = new HWPFDocument(stream);
                    wordExtractor = new WordExtractor(document);
                    s = wordExtractor.getText();
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
                break;
            }
            case "docx": {
                XWPFWordExtractor xwpf = null;//得到word文档的信息
                try {
                    XWPFDocument document = new XWPFDocument(stream);
                    xwpf = new XWPFWordExtractor(document);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
                s = xwpf.getText();
                break;
            }
        }
        s = s.trim().replaceAll("\t", "").replaceAll("\n", "").replaceAll("　", "").replaceAll("\uE5E5", "");

        return s;
    }
}

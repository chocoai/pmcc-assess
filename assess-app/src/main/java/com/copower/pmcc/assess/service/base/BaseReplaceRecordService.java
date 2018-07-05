package com.copower.pmcc.assess.service.base;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseReplaceRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseReplaceRecord;
import com.copower.pmcc.assess.dto.input.word.DataReplaceDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-6-4.
 */
@Service
public class BaseReplaceRecordService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseReplaceRecordDao baseReplaceRecordDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;

    /**
     * 根据附件id获取替换数据
     *
     * @param attachmentId
     * @return
     */
    public BaseReplaceRecord getRecordByAttachmentId(Integer attachmentId, Boolean bisReplace) {
        return baseReplaceRecordDao.getRecordByAttachmentId(attachmentId, bisReplace);
    }

    public BaseReplaceRecord getRecordById(Integer id) {
        return baseReplaceRecordDao.getBaseReplaceRecordById(id);
    }

    /**
     * 保存替换数据
     *
     * @param baseReplaceRecord
     */
    public void saveBaseReplaceRecord(BaseReplaceRecord baseReplaceRecord) {
        if (baseReplaceRecord.getId() != null && baseReplaceRecord.getId() > 0) {
            baseReplaceRecordDao.updateBaseReplaceRecord(baseReplaceRecord);
        } else {
            //新增时先检查是否已存在相同的附件id，并且还未被替换过的数据
            if (baseReplaceRecord.getAttachmentId() != null) {
                BaseReplaceRecord record = getRecordByAttachmentId(baseReplaceRecord.getAttachmentId(), false);
                if (record == null) {
                    baseReplaceRecordDao.addBaseReplaceRecord(baseReplaceRecord);
                } else {
                    record.setContent(baseReplaceRecord.getContent());
                    baseReplaceRecordDao.updateBaseReplaceRecord(record);
                }
            }
        }
    }

    /**
     * 替换记录数据
     *
     * @param baseReplaceRecord
     */
    public void replaceRecordContent(BaseReplaceRecord baseReplaceRecord) throws Exception {
        Integer attachmentId = baseReplaceRecord.getAttachmentId();
        SysAttachmentDto baseAttachment = baseAttachmentService.getSysAttachmentDto(attachmentId);
        String localFullPath = getReplaceFile(baseReplaceRecord);
        //再将附件上传到相同位置
        try {
            ftpUtilsExtense.uploadFilesToFTP(baseAttachment.getFilePath(), new FileInputStream(localFullPath), baseAttachment.getFtpFileName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //更新已替换状态
        baseReplaceRecord.setBisReplace(true);
        baseReplaceRecordDao.updateBaseReplaceRecord(baseReplaceRecord);
    }

    /**
     * 获取替换完成的文件
     *
     * @param baseReplaceRecord
     * @return
     */
    private String getReplaceFile(BaseReplaceRecord baseReplaceRecord) throws Exception {
        Integer attachmentId = baseReplaceRecord.getAttachmentId();
        //将附件下载到本地处理
        String localFullPath = baseAttachmentService.downloadFtpFileToLocal(attachmentId);
        String content = baseReplaceRecord.getContent();
        if (StringUtils.isNotBlank(content)) {
            List<DataReplaceDto> dataReplaceDtoList = JSON.parseArray(content, DataReplaceDto.class);
            //特殊处理
            //1.循环所有需要替换的内容，将只是文本分一组，将只是书签的分一组，将文件的分一组
            //2.如果是文件则找出文件，并检查需替换的内容，如果内容为空则只替换文件，如果不为空则循环替换，可能存在递归操作
            if (CollectionUtils.isNotEmpty(dataReplaceDtoList)) {
                Map<String, String> textMap = Maps.newHashMap();
                Map<String, String> bookmarkMap = Maps.newHashMap();
                Map<String, String> fileMap = Maps.newHashMap();
                for (DataReplaceDto dataReplaceDto : dataReplaceDtoList) {
                    switch (dataReplaceDto.getDataReplaceTypeEnum()) {
                        case FILE:
                            BaseReplaceRecord replaceRecord = getRecordById(dataReplaceDto.getReplaceRecordId());
                            if (replaceRecord == null) continue;
                            if (StringUtils.isBlank(replaceRecord.getContent())) {
                                fileMap.put(dataReplaceDto.getKey(), baseAttachmentService.downloadFtpFileToLocal(replaceRecord.getAttachmentId()));
                            } else {
                                fileMap.put(dataReplaceDto.getKey(), getReplaceFile(replaceRecord));
                            }
                            break;
                        case TEXT:
                            textMap.put(dataReplaceDto.getKey(), dataReplaceDto.getValue());
                            break;
                        case BOOKMARK:
                            bookmarkMap.put(dataReplaceDto.getKey(), dataReplaceDto.getValue());
                            break;
                    }
                }
                //分组完成后，分别做对应的替换操作
                if (!textMap.isEmpty())
                    AsposeUtils.replaceText(localFullPath, textMap);
                if (!bookmarkMap.isEmpty())
                    AsposeUtils.replaceBookmark(localFullPath, bookmarkMap);
                if (!fileMap.isEmpty())
                    AsposeUtils.insertDocument(localFullPath, fileMap);
            }
        }
        return localFullPath;
    }
}

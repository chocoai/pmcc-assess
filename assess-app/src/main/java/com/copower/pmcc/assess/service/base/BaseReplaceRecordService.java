package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.dao.base.BaseReplaceRecordDao;
import com.copower.pmcc.assess.dal.entity.BaseReplaceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kings on 2018-6-4.
 */
@Service
public class BaseReplaceRecordService {
    @Autowired
    private BaseReplaceRecordDao baseReplaceRecordDao;

    /**
     * 根据附件id获取替换数据
     *
     * @param attachmentId
     * @return
     */
    public BaseReplaceRecord getRecordByAttachmentId(Integer attachmentId,Boolean bisReplace) {
        return baseReplaceRecordDao.getRecordByAttachmentId(attachmentId,bisReplace);
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
            if(baseReplaceRecord.getAttachmentId()!=null){
                BaseReplaceRecord record = getRecordByAttachmentId(baseReplaceRecord.getAttachmentId(),false);
                if(record==null){
                    baseReplaceRecordDao.addBaseReplaceRecord(baseReplaceRecord);
                }else{
                    record.setContent(baseReplaceRecord.getContent());
                    baseReplaceRecordDao.updateBaseReplaceRecord(record);
                }
            }
        }
    }
}

package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseReplaceRecord;
import com.copower.pmcc.assess.dal.entity.BaseReplaceRecordExample;
import com.copower.pmcc.assess.dal.mapper.BaseReplaceRecordMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/8
 * @time: 15:28
 */
@Repository
public class BaseReplaceRecordDao {
    @Autowired
    private BaseReplaceRecordMapper baseReplaceRecordMapper;

    public BaseReplaceRecord getBaseReplaceRecordById(Integer id) {
        return baseReplaceRecordMapper.selectByPrimaryKey(id);
    }

    public boolean addBaseReplaceRecord(BaseReplaceRecord BaseReplaceRecord) {
        return baseReplaceRecordMapper.insertSelective(BaseReplaceRecord) > 0;
    }

    public boolean updateBaseReplaceRecord(BaseReplaceRecord BaseReplaceRecord) {
        return baseReplaceRecordMapper.updateByPrimaryKeySelective(BaseReplaceRecord) > 0;
    }

    public BaseReplaceRecord getRecordByAttachmentId(Integer attachmentId,Boolean bisReplace) {
        BaseReplaceRecordExample example = new BaseReplaceRecordExample();
        example.createCriteria().andAttachmentIdEqualTo(attachmentId).andBisReplaceEqualTo(bisReplace);
        List<BaseReplaceRecord> replaceRecords = baseReplaceRecordMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(replaceRecords))
            return replaceRecords.get(0);
        return null;
    }
}

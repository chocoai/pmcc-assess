package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRecordDao declareRecordDao;


    public Integer saveAndUpdateDeclareRecord(DeclareRecord declareRecord) throws BusinessException {
        if (declareRecord == null) {
            throw new BusinessException("null point");
        }
        if (declareRecord.getId() == null || declareRecord.getId().intValue() == 0) {
            return declareRecordDao.saveReturnId(declareRecord);
        } else {
            declareRecordDao.updateDeclareRecord(declareRecord);
            return declareRecord.getId();
        }
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordListByProjectId(projectId);
        return declareRecords;
    }

    public DeclareRecord getDeclareRecordById(Integer id) {
        return declareRecordDao.getDeclareRecordById(id);
    }

    public List<DeclareRecord> getDeclareRecordListByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return null;
        return declareRecordDao.getDeclareRecordListByIds(ids);
    }

}

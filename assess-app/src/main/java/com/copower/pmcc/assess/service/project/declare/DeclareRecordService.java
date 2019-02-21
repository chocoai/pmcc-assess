package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private ProjectInfoService projectInfoService;


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
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordByProjectId(projectId);
        return declareRecords;
    }

    public DeclareRecord getDeclareRecordById(Integer id) {
        return declareRecordDao.getDeclareRecordById(id);
    }


}

package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.BaseReportBatchDao;
import com.copower.pmcc.assess.dal.entity.BaseReportBatchTemplate;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/28
 * @time: 9:50
 */
@Service
public class BaseReportBatchService {
    @Autowired
    private BaseReportBatchDao baseReportBatchDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private CommonService commonService;

    //生成数据库表数据
    @Transactional(rollbackFor = Exception.class)
    public void importAttendanceData(BaseReportBatchTemplate baseReportBatchTemplate) throws BpmException {
        baseReportBatchDao.addBaseReportBatchTemplate(baseReportBatchTemplate);


    }

}

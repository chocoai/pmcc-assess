package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectQrcodeRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectQrcodeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kings on 2019-6-5.
 */
@Service
public class ProjectQrcodeRecordService {
    @Autowired
    private ProjectQrcodeRecordDao projectQrcodeRecordDao;

    /**
     * 获取数据
     * @param projectId
     * @param areaId
     * @param reportType
     * @return
     */
    public ProjectQrcodeRecord getProjectQrcodeRecode(Integer projectId, Integer areaId, Integer reportType) {
        return projectQrcodeRecordDao.getProjectQrcodeRecord(projectId, areaId, reportType);
    }

    /**
     * 保存数据
     * @param projectQrcodeRecord
     */
    public void saveProjectQrcodeRecode(ProjectQrcodeRecord projectQrcodeRecord) {
        if (projectQrcodeRecord.getId() == null || projectQrcodeRecord.getId() <= 0) {
            projectQrcodeRecordDao.addProjectQrcodeRecord(projectQrcodeRecord);
        } else {
            projectQrcodeRecordDao.editProjectQrcodeRecord(projectQrcodeRecord);
        }
    }
}

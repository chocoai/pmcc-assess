package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectXlxPigeonholeConfigDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectXlxPigeonholeRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfig;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeRecord;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/4/26.
 */
@Service
public class ProjectXlxPigeonholeRecordService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectXlxPigeonholeRecordDao projectXlxPigeonholeRecordDao;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectXlxPigeonholeConfigDao projectXlxPigeonholeConfigDao;

    /**
     * 保存数据
     *
     * @param projectXlxPigeonholeRecord
     */
    public void saveAndUpdate(ProjectXlxPigeonholeRecord projectXlxPigeonholeRecord) {
        if (projectXlxPigeonholeRecord.getId() != null && projectXlxPigeonholeRecord.getId() > 0) {
            projectXlxPigeonholeRecordDao.updateProjectXlxPigeonholeRecord(projectXlxPigeonholeRecord);
        } else {
            projectXlxPigeonholeRecord.setCreator(commonService.thisUserAccount());
            projectXlxPigeonholeRecordDao.addProjectXlxPigeonholeRecord(projectXlxPigeonholeRecord);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeProjectXlxPigeonholeRecord(Integer id) {
        return projectXlxPigeonholeRecordDao.removeProjectXlxPigeonholeRecord(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ProjectXlxPigeonholeRecord getProjectXlxPigeonholeRecord(Integer id) {
        return projectXlxPigeonholeRecordDao.getProjectXlxPigeonholeRecord(id);
    }


    /**
     * 获取数据列表
     *
     * @return
     */
    public BootstrapTableVo getVoList(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectXlxPigeonholeRecord> hypothesisList = projectXlxPigeonholeRecordDao.getProjectXlxPigeonholeRecordList(projectId);
        vo.setRows(CollectionUtils.isEmpty(hypothesisList) ? new ArrayList<ProjectXlxPigeonholeRecord>() : hypothesisList);
        vo.setTotal(page.getTotal());
        return vo;
    }


    /**
     * 获取数据
     *
     * @return
     */
    public List<ProjectXlxPigeonholeRecord> getListByProjectId(Integer projectId) {
        List<ProjectXlxPigeonholeRecord> list = projectXlxPigeonholeRecordDao.getProjectXlxPigeonholeRecordList(projectId);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return null;
    }

    public void init(Integer projectId) {
        List<ProjectXlxPigeonholeRecord> list = getListByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(list)) {
            return;
        } else {
            writeData(projectId);
        }
    }

    public void writeData(Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectXlxPigeonholeConfig config = new ProjectXlxPigeonholeConfig();
        config.setProjectType(projectInfo.getProjectCategoryId());
        config.setBisEnable(true);
        List<ProjectXlxPigeonholeConfig> configList = projectXlxPigeonholeConfigDao.getProjectXlxPigeonholeConfigList(config);
        if (CollectionUtils.isNotEmpty(configList)) {
            for (ProjectXlxPigeonholeConfig configItem : configList) {
                ProjectXlxPigeonholeRecord record = new ProjectXlxPigeonholeRecord();
                record.setProjectId(projectId);
                record.setSorting(configItem.getSorting());
                record.setFileName(configItem.getFileName());
                saveAndUpdate(record);
            }

        }
    }

    public void refresh(Integer projectId) {
        //先清除后再写入
        List<ProjectXlxPigeonholeRecord> list = getListByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (ProjectXlxPigeonholeRecord item : list) {
                removeProjectXlxPigeonholeRecord(item.getId());
            }
        }
        writeData(projectId);
    }
}

package com.copower.pmcc.assess.service.project.change;


import com.copower.pmcc.assess.common.ApprovalUser;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectWorkStageDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/05 13:34
 */
@Service
public class ProjectWorkStageService {
    @Autowired
    private ProjectWorkStageDao projectWorkStageDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ApprovalUser approvalUser;

    public ProjectWorkStage cacheProjectWorkStage(int id) {

        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_STAGE_ID, String.valueOf(id));
        try {
            return LangUtils.singleCache(rdsKey, id, ProjectWorkStage.class, o -> projectWorkStageDao.getProjectWorkStageById(o));
        } catch (Exception e) {
            return projectWorkStageDao.getProjectWorkStageById(id);
        }
        //   return  queryWorkStageById(id);
    }

    public ProjectWorkStage getProjectWorkStageFirst(Integer projectClassId, Integer projectTypeId) {
        ProjectWorkStage where = new ProjectWorkStage();
        where.setProjectClassId(projectClassId);
        where.setProjectTypeId(projectTypeId);
        List<ProjectWorkStage> projectWorkStages = projectWorkStageDao.getProjectWorkStage(where);
        if (CollectionUtils.isEmpty(projectWorkStages)) return null;
        return projectWorkStages.get(0);
    }

    public List<ProjectWorkStage> queryWorkStageByClassIdAndTypeId(int typeId, Boolean formCache) {
        if (formCache == Boolean.TRUE) {
            String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_STAGE_TYPEID, String.valueOf(typeId));
            try {
                return LangUtils.listCache(rdsKey, typeId, ProjectWorkStage.class, o -> projectWorkStageDao.getWorkStageByTypeId(o));
            } catch (Exception e) {
                return projectWorkStageDao.getWorkStageByTypeId(typeId);
            }
        } else {
            return projectWorkStageDao.getWorkStageByTypeId(typeId);
        }
    }


    public void createOrUpdateWorkStage(ProjectWorkStage projectWorkStage) throws BusinessException {
        if (projectWorkStage == null)
            return;
        if (projectWorkStage.getId() == null) {
            //新增一项
            projectWorkStageDao.createWorkStage(projectWorkStage);
        } else {
            //修改数据
            if (StringUtils.isBlank(projectWorkStage.getBoxName()))
                projectWorkStage.setBoxName(""); //填一个空串
            if (StringUtils.isBlank(projectWorkStage.getReviewBoxName()))
                projectWorkStage.setReviewBoxName(""); //为null时赋值空串,mybatis-sql判断的为null则不更新,变态处理一下
            if (projectWorkStage.getBisEnable() == null)
                projectWorkStage.setBisEnable(Boolean.FALSE); //修改如果没有值说明已经被禁用(主要是为了处理页面中checkbox不选中不会有值的情况)
            if (projectWorkStage.getAllowIssued() == null)
                projectWorkStage.setAllowIssued(Boolean.FALSE);
            if (projectWorkStage.getBisLoadDefalut() == null)
                projectWorkStage.setBisLoadDefalut(Boolean.FALSE);
            projectWorkStageDao.updateWorkStageById(projectWorkStage);
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_WORK_STAGE, "");
    }

    public boolean updateWorkStage(ProjectWorkStage projectWorkStage) {
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_WORK_STAGE, "");
        return projectWorkStageDao.updateWorkStageById(projectWorkStage);
    }

    public String getWorkStageUserAccounts(Integer stageId, Integer projectId) {
        ProjectWorkStage projectWorkStage = cacheProjectWorkStage(stageId);
        if (projectWorkStage == null) {
            return "";
        }
        String boxRoleType = projectWorkStage.getBoxRoleType();
        if (StringUtils.isBlank(boxRoleType)) {
            return "";
        }
        String userAccounts = "";
        //<option value="1">部门角色</option>
        //<option value="2">私有角色</option>
        switch (boxRoleType) {
            case "1": {
                Integer boxRoleId = projectWorkStage.getBoxRoleId();
                if (boxRoleId != null) {
                    List<String> userListByRoleId = bpmRpcBoxRoleUserService.getUserListByRoleId(-boxRoleId);
                    if (CollectionUtils.isNotEmpty(userListByRoleId)) {
                        userAccounts = FormatUtils.transformListString(userListByRoleId);
                    }
                }
                break;
            }
            case "2": {
                String boxRoleKey = projectWorkStage.getBoxRoleKey();
                if (StringUtils.isNotBlank(boxRoleKey)) {
                    userAccounts = approvalUser.getRoleUserAccountList(boxRoleKey, commonService.thisUserAccount(), projectId);
                }
                break;
            }
            default: {
                break;
            }
        }
        return userAccounts;
    }
}

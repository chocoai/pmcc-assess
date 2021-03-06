package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMemberHistory;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 描述:项目成员信息
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:06
 */
@Service
public class ProjectMemberService {
    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private PublicService publicService;

    public boolean deleteProjectMemberById(Integer id) {
        return projectMemberDao.deleteProjectMemberById(id);
    }

    public Map<String, SysUserDto> relationUserMap(List<SysUserDto> sysUserList) {
        Map<String, SysUserDto> map = Maps.newHashMap();
        if (CollectionUtils.isEmpty(sysUserList)) {
            return map;
        }
        for (SysUserDto dto : sysUserList) {
            map.put(dto.getUserAccount(), dto);
        }
        return map;
    }

    public void saveProjectMemeber(ProjectMember projectMember) throws BusinessException {
        if (projectMember == null) {
            return;
        }
        List<ProjectMember> projectMemberList = projectMemberDao.getProjectMemberList(projectMember.getProjectId());
        if (CollectionUtils.isNotEmpty(projectMemberList)) {
            projectMember.setId(projectMemberList.get(0).getId());
        }
        if (projectMember.getId() != null && projectMember.getId() > 0) {
            projectMemberDao.updateProjectMember(projectMember);
        } else {
            projectMember.setCreator(commonService.thisUserAccount());
            projectMemberDao.saveProjectMember(projectMember);
        }
        upateProjectMemeberToErp(projectMember.getProjectId(), projectMember.getUserAccountManager(), projectMember.getUserAccountMember());
    }

    @Transactional
    public boolean updateProjectMember(ProjectMember projectMember) {
        return projectMemberDao.updateProjectMember(projectMember);
    }

    public ProjectMember get(Integer id) {
        return projectMemberDao.getProjectMemberItem(id);
    }

    public ProjectMember getById(Integer id) {
        return projectMemberDao.get(id);
    }

    public void save(ProjectMember projectMember) throws BusinessException {
        if (projectMember == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        saveProjectMemeber(projectMember);
    }

    public int saveReturnId(ProjectMember projectMember) throws BusinessException {
        if (projectMember == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return projectMemberDao.saveProjectMemeberID(projectMember);
    }

    private void upateProjectMemeberToErp(Integer projectId, String projectManager, String projectMember) {
        if (projectId == null) {
            return;
        }
        SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoByProjectId(projectId, applicationConstant.getAppKey());
        if (sysProjectDto == null || sysProjectDto.getId() == null) {
            return;
        }
        if (sysProjectDto.getId() > 0) {
            sysProjectDto.setProjectManager(projectManager);
            sysProjectDto.setProjectMember(projectMember);
            erpRpcProjectService.saveProject(sysProjectDto);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveChangeProjectMemeber(ProjectMember projectMember) throws BusinessException {
        if (projectMember == null) {
            return;
        }
        if (projectMember.getProjectId() == null) {
            return;
        }
        ProjectMember projectMemberItem = projectMemberDao.getProjectMemberItem(projectMember.getProjectId());
        if (projectMemberItem == null) {
            projectMemberItem = new ProjectMember();
            projectMemberItem.setUserAccountManager(projectMember.getUserAccountManager());
            projectMemberItem.setUserAccountMember(projectMember.getUserAccountMember());
            projectMemberItem.setUserAccountQuality(projectMember.getUserAccountQuality());
            projectMemberItem.setProjectId(projectMember.getProjectId());
            projectMemberItem.setCreator(commonService.thisUserAccount());
            projectMemberItem.setRemarks("项目成员变更");
            if (!projectMemberDao.saveProjectMember(projectMemberItem)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            ProjectMemberHistory projectMemberHistory = new ProjectMemberHistory();
            projectMemberHistory.setUserAccountManagerNew(projectMember.getUserAccountManager());
            projectMemberHistory.setUserAccountMemberNew(projectMember.getUserAccountMember());
            projectMemberHistory.setUserAccountQualityNew(projectMember.getUserAccountQuality());
            projectMemberHistory.setUserAccountManagerOld(projectMemberItem.getUserAccountManager());
            projectMemberHistory.setUserAccountMemberOld(projectMemberItem.getUserAccountMember());
            projectMemberHistory.setUserAccountQualityOld(projectMemberItem.getUserAccountQuality());
            projectMemberHistory.setProjectId(projectMember.getProjectId());
            projectMemberHistory.setCreator(commonService.thisUserAccount());
            if (!projectMemberDao.saveProjectMemberHistory(projectMemberHistory)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            projectMemberItem.setUserAccountManager(projectMember.getUserAccountManager());
            projectMemberItem.setUserAccountMember(projectMember.getUserAccountMember());
            projectMemberItem.setUserAccountQuality(projectMember.getUserAccountQuality());
            if (!projectMemberDao.updateProjectMember(projectMemberItem)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
        upateProjectMemeberToErp(projectMember.getProjectId(), projectMember.getUserAccountManager(), projectMember.getUserAccountMember());
    }

    public String getProjectManager(Integer projectId) {
        if (projectId == null) {
            return null;
        }
        ProjectMember projectMemberItem = projectMemberDao.getProjectMemberItem(projectId);
        if (projectMemberItem != null) {
            if (StringUtils.isNotEmpty(projectMemberItem.getUserAccountManager())) {
                return projectMemberItem.getUserAccountManager();
            }
        }
        return "";
    }

    public ProjectMemberVo getProjectMemberVo(ProjectMember projectMember) {
        if (projectMember == null){
            return null;
        }
        ProjectMemberVo vo = new ProjectMemberVo();
        BeanUtils.copyProperties(projectMember, vo);
        if (StringUtils.isNotBlank(projectMember.getUserAccountManager())) {
            vo.setUserAccountManagerName(publicService.getUserNameByAccount(projectMember.getUserAccountManager()));
        }
        if (StringUtils.isNotBlank(projectMember.getUserAccountMember())) {
            vo.setUserAccountMemberName(publicService.getUserNameByAccount(projectMember.getUserAccountMember()));
        }
        if (StringUtils.isNotBlank(projectMember.getUserAccountQuality())) {
            vo.setUserAccountQualityName(publicService.getUserNameByAccount(projectMember.getUserAccountQuality()));
        }
        return vo;
    }

    /**
     * 获取项目成员数据
     *
     * @param projectId
     * @return
     */
    public ProjectMemberVo getProjectMember(Integer projectId) {
        if (projectId == null){
            return null;
        }
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);
        return getProjectMemberVo(projectMember);
    }

    /**
     * 是否为项目成员
     *
     * @param projectId
     * @param userAccount
     * @return
     */
    public boolean isProjectMember(Integer projectId, String userAccount) {
        ProjectMemberVo projectMember = getProjectMember(projectId);
        if (projectMember == null) return false;
        if (StringUtils.defaultString(projectMember.getUserAccountManager()).contains(userAccount))
            return true;
        if (StringUtils.defaultString(projectMember.getUserAccountMember()).contains(userAccount))
            return true;
        return false;
    }

    /**
     * 确定每次任务完成时任务自动添加为项目成员
     *
     * @param projectPlanDetails
     */
    public void autoAddFinishTaskMember(ProjectPlanDetails projectPlanDetails) {
        if (projectPlanDetails == null) {
            return;
        }
        if (StringUtils.isEmpty(projectPlanDetails.getExecuteUserAccount())) {
            return;
        }
        if (projectPlanDetails.getProjectId() == null) {
            return;
        }
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectPlanDetails.getProjectId());
        if (projectMember == null) {
            return;
        }
        //在原来得基础上添加项目成员
        if (StringUtils.isNotBlank(projectMember.getUserAccountMember())) {
            try {
                List<String> stringList = FormatUtils.transformString2List(projectMember.getUserAccountMember());
                try {
                    List<String> stringList2 = FormatUtils.transformString2List(projectPlanDetails.getExecuteUserAccount());
                    Iterator<String> stringIterator = stringList2.iterator();
                    while (stringIterator.hasNext()) {
                        if (stringList.contains(stringIterator.next())) {
                            stringIterator.remove();
                        }
                    }
                    stringList.addAll(stringList2);
                } catch (Exception e) {
                    if (!stringList.contains(projectPlanDetails.getExecuteUserAccount())) {
                        stringList.add(projectPlanDetails.getExecuteUserAccount());
                    }
                }
                projectMember.setUserAccountMember(StringUtils.join(stringList, ","));
            } catch (Exception e) {
                if (!StringUtils.containsIgnoreCase(projectMember.getUserAccountMember(), projectPlanDetails.getExecuteUserAccount())) {
                    String v = String.join("", projectMember.getUserAccountMember(), ",", projectPlanDetails.getExecuteUserAccount());
                    projectMember.setUserAccountMember(v);
                }
            }
        }
        //从未添加过项目成员情况
        if (StringUtils.isEmpty(projectMember.getUserAccountMember())) {
            projectMember.setUserAccountMember(projectPlanDetails.getExecuteUserAccount());
        }
        projectMemberDao.updateProjectMember(projectMember);
    }
}
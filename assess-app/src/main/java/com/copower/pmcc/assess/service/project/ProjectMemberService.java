package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMemberHistory;
import com.copower.pmcc.assess.dto.input.project.ProjectMemberDto;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysProjectDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 描述:项目成员信息
 *
 * @author: Calvin(qiudong@copowercpa.com)
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
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private PublicService publicService;

    public Map<String, SysUserDto> relationUserMap(List<SysUserDto> sysUserList) {
        Map<String, SysUserDto> map = Maps.newHashMap();
        for (SysUserDto dto : sysUserList) {
            map.put(dto.getUserAccount(), dto);
        }

        return map;
    }

    public void saveProjectMemeber(ProjectMember projectMember) throws BusinessException {
        if (projectMember == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (projectMember.getId() != null && projectMember.getId() > 0) {
            projectMemberDao.updateProjectMember(projectMember);
        } else {
            projectMember.setCreator(processControllerComponent.getThisUser());
            projectMemberDao.saveProjectMember(projectMember);
        }
        upateProjectMemeberToErp(projectMember.getProjectId(), projectMember.getUserAccountManager(), projectMember.getUserAccountMember());
    }

    @Transactional
    public boolean updateProjectMember(ProjectMember projectMember){
        return projectMemberDao.updateProjectMember(projectMember);
    }

    public ProjectMember get(Integer id){
        return projectMemberDao.getProjectMemberItem(id);
    }

    public ProjectMember getById(Integer id){
        return projectMemberDao.get(id);
    }

    public void save(ProjectMemberDto dto)throws BusinessException {
        if (dto==null)throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        saveProjectMemeber(change(dto));
    }

    public int saveReturnId(ProjectMemberDto dto)throws BusinessException{
        if (dto==null)throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return projectMemberDao.saveProjectMemeberID(change(dto));
    }

    private void upateProjectMemeberToErp(Integer projectId, String projectManager, String projectMember) {
        SysProjectDto sysProjectDto = erpRpcProjectService.getProjectInfoByProjectId(projectId,applicationConstant.getAppKey());
        if (sysProjectDto.getId() > 0) {
            sysProjectDto.setProjectManager(projectManager);
            sysProjectDto.setProjectMember(projectMember);
            erpRpcProjectService.saveProject(sysProjectDto);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveChangeProjectMemeber(ProjectMember projectMember) throws BusinessException {
        ProjectMember projectMemberItem = projectMemberDao.getProjectMemberItem(projectMember.getProjectId());
        if (projectMemberItem == null) {
            projectMemberItem = new ProjectMember();
            projectMemberItem.setUserAccountManager(projectMember.getUserAccountManager());
            projectMemberItem.setUserAccountMember(projectMember.getUserAccountMember());
            projectMemberItem.setUserAccountQuality(projectMember.getUserAccountQuality());
            projectMemberItem.setProjectId(projectMember.getProjectId());
            projectMemberItem.setCreator(processControllerComponent.getThisUser());
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
            projectMemberHistory.setCreator(processControllerComponent.getThisUser());
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
        ProjectMember projectMemberItem = projectMemberDao.getProjectMemberItem(projectId);
        if (projectMemberItem != null) {
            return projectMemberItem.getUserAccountManager();
        }
        return "";
    }

    public ProjectMemberVo loadProjectMemberList(Integer projectId) {
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);
        if (projectMember == null) {
            return new ProjectMemberVo();
        }
        ProjectMemberVo projectMemberVo = new ProjectMemberVo();
        BeanUtils.copyProperties(projectMember, projectMemberVo);
        if (StringUtils.isNotBlank(projectMember.getUserAccountMember())) {
            projectMemberVo.setUserAccountMemberName(publicService.getUserNameByAccount(projectMember.getUserAccountMember()));
        }

        if (StringUtils.isNotBlank(projectMember.getUserAccountManager())) {
            projectMemberVo.setUserAccountManagerName(publicService.getUserNameByAccount(projectMember.getUserAccountManager()));
        }
        if (StringUtils.isNotBlank(projectMember.getUserAccountQuality())) {
            projectMemberVo.setUserAccountQualityName(publicService.getUserNameByAccount(projectMember.getUserAccountQuality()));
        }
        return projectMemberVo;
    }

    public ProjectMember change(ProjectMemberDto dto){
        ProjectMember projectMember = new ProjectMember();
        BeanUtils.copyProperties(dto,projectMember);
        return projectMember;
    }

}

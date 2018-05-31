package com.copower.pmcc.assess.service.project.plan.service;

import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.DataDeclareForm;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.data.DataDeclareFormService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:09
 */
@Service
public class ProjectPlanDetailsService {
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private BaseAttachmentService bidBaseAttachmentService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataDeclareFormService dataDeclareFormService;

    public ProjectPlanDetails getProjectPlanDetailsById(Integer id) {
        return projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
    }

    public ProjectPlanDetails getProjectPlanDetailsByProcessInsId(String processInsId) {
        return projectPlanDetailsDao.getProjectPlanDetailsItemByProcessInsId(processInsId);
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsByPlanId(Integer planId) {
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
        return getProjectPlanDetailsVos(projectPlanDetails, true);
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsByPlanApply(Integer planId) {
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
        return getProjectPlanDetailsVos(projectPlanDetails, false);
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsByProjectid(Integer projectId) {
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByProjectid(projectId);
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = getProjectPlanDetailsVos(projectPlanDetails, true);
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProjectId(projectId);
        projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
        if (CollectionUtils.isNotEmpty(projectTaskList) && CollectionUtils.isNotEmpty(projectPlanDetailsVos)) {
            for (ProjectPlanDetailsVo projectPlanDetailsVo : projectPlanDetailsVos) {
                for (ProjectResponsibilityDto responsibilityDto : projectTaskList) {
                    if (projectPlanDetailsVo.getId().intValue() == responsibilityDto.getPlanDetailsId().intValue()) {
                        projectPlanDetailsVo.setUrl(responsibilityDto.getUrl());
                    }
                }
            }
        }
        return projectPlanDetailsVos;
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsVos(List<ProjectPlanDetails> projectPlanDetails, Boolean bisAttachment) {
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
            List<BaseAttachment> bidBaseAttachments = new ArrayList<>();
            if (bisAttachment) {
                List<Integer> detailsIds = LangUtils.transform(projectPlanDetails, o -> o.getId());
                bidBaseAttachments = bidBaseAttachmentService.getAttachmentListByTableName("tb_project_plan_details", detailsIds);
            }
            for (ProjectPlanDetails item : projectPlanDetails) {
                ProjectPlanDetailsVo projectPlanDetailsVo = new ProjectPlanDetailsVo();
                BeanUtils.copyProperties(item, projectPlanDetailsVo);
                if (StringUtils.isNotBlank(item.getExecuteUserAccount())) {
                    SysUserDto sysUser = erpRpcUserService.getSysUser(item.getExecuteUserAccount());
                    projectPlanDetailsVo.setExecuteUserName(sysUser == null ? "" : sysUser.getUserName());
                }
                if (item.getExecuteDepartmentId() != null) {
                    SysDepartmentDto departmentById = erpRpcDepartmentService.getDepartmentById(item.getExecuteDepartmentId());
                    projectPlanDetailsVo.setExecuteDepartmentName(departmentById == null ? "" : departmentById.getName());
                }
                if (item.getPid() > 0) {
                    projectPlanDetailsVo.setSorting(item.getPid() * 100 + item.getSorting());
                    projectPlanDetailsVo.set_parentId(item.getPid().toString());
                } else {
                    projectPlanDetailsVo.setSorting(1000 + item.getSorting());
                }
                if (bidBaseAttachments != null && bidBaseAttachments.size() > 0) {
                    List<BaseAttachment> filter = LangUtils.filter(bidBaseAttachments, o -> {
                        return o.getTableId().intValue() == item.getId().intValue();
                    });
                    if (CollectionUtils.isNotEmpty(filter)) {
                        List<KeyValueDto> transform = LangUtils.transform(filter, o -> {
                            KeyValueDto keyValueDto = new KeyValueDto();
                            keyValueDto.setKey(o.getId().toString());
                            keyValueDto.setValue(String.format("%s(%s)", o.getFileName(), o.getFileSize()));
                            keyValueDto.setExplain(o.getFileExtension());
                            return keyValueDto;
                        });
                        projectPlanDetailsVo.setTasks(transform);

                    }
                }
                if (item.getDeclareFormId() != null && item.getDeclareFormId() > 0) {
                    DataDeclareForm declareForm = dataDeclareFormService.getCacheDataDeclareForm(item.getDeclareFormId());
                    if(declareForm!=null){
                        projectPlanDetailsVo.setDeclareFormName(declareForm.getName());
                    }
                }
                projectPlanDetailsVos.add(projectPlanDetailsVo);
            }
        }
        return projectPlanDetailsVos;
    }


}

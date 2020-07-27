package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectSpotCheckDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheck;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItem;
import com.copower.pmcc.assess.dto.output.project.ProjectSpotCheckItemVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectSpotCheckEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectSpotCheckService {
    @Autowired
    private ProjectSpotCheckDao projectSpotCheckDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public ProjectSpotCheck getSpotCheckById(Integer id) {
        return projectSpotCheckDao.getProjectSpotCheckById(id);
    }

    /**
     * 获取抽查数据列表
     * @return
     */
    public BootstrapTableVo getProjectSpotCheckList() {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectSpotCheck projectSpotCheck = new ProjectSpotCheck();
        List<ProjectSpotCheck> spotChecks = projectSpotCheckDao.getProjectSpotCheckList(projectSpotCheck);
        vo.setTotal(page.getTotal());
        vo.setRows(spotChecks);
        return vo;
    }

    public ProjectSpotCheck getSpotCheckByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        ProjectSpotCheck where = new ProjectSpotCheck();
        where.setProcessInsId(processInsId);
        List<ProjectSpotCheck> projectSpotCheckList = projectSpotCheckDao.getProjectSpotCheckList(where);
        if (CollectionUtils.isEmpty(projectSpotCheckList)) return null;
        return projectSpotCheckList.get(0);
    }

    public void saveSpotCheck(ProjectSpotCheck spotCheck) {
        if (spotCheck.getId() != null && spotCheck.getId() > 0) {
            projectSpotCheckDao.modifyProjectSpotCheck(spotCheck);
        } else {
            spotCheck.setCreator(commonService.thisUserAccount());
            projectSpotCheckDao.addProjectSpotCheck(spotCheck);
        }
    }

    /**
     * 申请提交
     *
     * @param formData
     * @param projectId
     */
    public void applyCommit(String formData, Integer projectId) throws BusinessException, BpmException {
        if (StringUtils.isBlank(formData) || projectId == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ProjectSpotCheck spotCheck = new ProjectSpotCheck();
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        spotCheck.setStatus(ProcessStatusEnum.RUN.getValue());
        saveSpotCheck(spotCheck);

        ProcessInfo processInfo = new ProcessInfo();
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_REVIEW_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        processInfo.setFolio(String.format("%s【复核工时考核】%s", thisUserInfo.getUserName(), projectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectSpotCheck.class));
        processInfo.setProcessEventExecutor(ProjectSpotCheckEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(spotCheck.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(commonService.thisUserAccount());
        ProcessUserDto processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);

        spotCheck.setProcessInsId(processUserDto.getProcessInsId());
        saveSpotCheck(spotCheck);

        ProjectSpotCheckItem projectSpotCheckItem = JSON.parseObject(formData, ProjectSpotCheckItem.class);
        projectSpotCheckItem.setMasterId(spotCheck.getId());
        projectSpotCheckItem.setBisEnable(true);
        addSpotCheckItem(projectSpotCheckItem);
    }

    //----------------------------------------------------------------------------------------------------

    public void addSpotCheckItem(ProjectSpotCheckItem SpotCheckItem) {
        //先将上一条数据置为无效状态
        ProjectSpotCheckItem scoreItem = getEnableSpotCheckItemsByMasterId(SpotCheckItem.getMasterId());
        if (scoreItem != null) {
            scoreItem.setBisEnable(false);
            projectSpotCheckDao.modifyProjectSpotCheckItem(scoreItem);
        }
        SpotCheckItem.setBisEnable(true);
        SpotCheckItem.setCreator(commonService.thisUserAccount());
        projectSpotCheckDao.addProjectSpotCheckItem(SpotCheckItem);
    }

    public ProjectSpotCheckItem getEnableSpotCheckItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        ProjectSpotCheckItem where = new ProjectSpotCheckItem();
        where.setBisEnable(true);
        where.setMasterId(masterId);
        List<ProjectSpotCheckItem> list = projectSpotCheckDao.getProjectSpotCheckItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public List<ProjectSpotCheckItem> getHistorySpotCheckItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        ProjectSpotCheckItem where = new ProjectSpotCheckItem();
        where.setBisEnable(false);
        where.setMasterId(masterId);
        List<ProjectSpotCheckItem> list = projectSpotCheckDao.getProjectSpotCheckItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, o -> getSpotCheckItemVo(o));
    }

    public ProjectSpotCheckItemVo getSpotCheckItemVo(ProjectSpotCheckItem SpotCheckItem) {
        if (SpotCheckItem == null) return null;
        ProjectSpotCheckItemVo vo = new ProjectSpotCheckItemVo();
        BeanUtils.copyProperties(SpotCheckItem, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(SpotCheckItem.getCreator()));
        if (StringUtils.isNotBlank(SpotCheckItem.getContent())) {
            vo.setKeyValueDtos(JSON.parseArray(SpotCheckItem.getContent(), KeyValueDto.class));
        }
        return vo;
    }
}

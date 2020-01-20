package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectTakeNumberDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberDetail;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2020-1-19.
 * 项目拿号 从表
 */
@Service
public class ProjectTakeNumberDetailService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectTakeNumberDetailDao projectTakeNumberDetailDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean updateProjectTakeNumberDetail(ProjectTakeNumberDetail oo, boolean updateNull) {
        return projectTakeNumberDetailDao.updateProjectTakeNumberDetail(oo, updateNull);
    }

    public boolean saveProjectTakeNumberDetail(ProjectTakeNumberDetail oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        boolean b = projectTakeNumberDetailDao.saveProjectTakeNumberDetail(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumberDetail.class),oo.getId());
        return b;

    }

    public void saveAndUpdateProjectTakeNumberDetail(ProjectTakeNumberDetail oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateProjectTakeNumberDetail(oo, updateNull);
        } else {
            saveProjectTakeNumberDetail(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumberDetail.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteProjectTakeNumberDetailById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                projectTakeNumberDetailDao.deleteProjectTakeNumberDetailById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                projectTakeNumberDetailDao.deleteProjectTakeNumberDetailByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(ProjectTakeNumberDetail oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectTakeNumberDetail> declareApplyExtensionList = getProjectTakeNumberDetailListByQuery(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }

    public List<ProjectTakeNumberDetail> getProjectTakeNumberDetailByIds(List<Integer> ids) {
        return projectTakeNumberDetailDao.getProjectTakeNumberDetailByIds(ids);
    }

    public ProjectTakeNumberDetail getProjectTakeNumberDetailById(Integer id) {
        return projectTakeNumberDetailDao.getProjectTakeNumberDetailById(id);
    }

    public List<ProjectTakeNumberDetail> getProjectTakeNumberDetailListByMasterId(Integer masterId){
        ProjectTakeNumberDetail query = new ProjectTakeNumberDetail();
        query.setMasterId(masterId);
        return getProjectTakeNumberDetailListByQuery(query) ;
    }

    public List<ProjectTakeNumberDetail> getProjectTakeNumberDetailListByQuery(ProjectTakeNumberDetail oo) {
        return projectTakeNumberDetailDao.getProjectTakeNumberDetailListByExample(oo);
    }
    
}

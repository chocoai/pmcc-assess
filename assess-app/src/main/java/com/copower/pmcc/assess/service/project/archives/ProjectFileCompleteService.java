package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.assess.common.enums.archives.ArchivesFileTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.archives.ProjectFileCompleteDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFileComplete;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 项目归档
 */
@Service
public class ProjectFileCompleteService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectFileCompleteDao projectFileCompleteDao;
    private Logger logger = LoggerFactory.getLogger(getClass()) ;

    public Long getFileNumberCount(String fileNumber) {
        if (StringUtils.isBlank(fileNumber)) {
            return Long.valueOf(0);
        }
        return projectFileCompleteDao.getFileNumber(fileNumber);
    }

    /**
     * 获取项目档案编号
     *
     * @param year                 年份
     * @param projectNumber        当前年份项目编号【001】
     * @param archivesFileTypeEnum 类型
     * @param count                份数编号
     * @return
     */
    public String getFileNumberByRole(Integer year, Integer projectNumber, ArchivesFileTypeEnum archivesFileTypeEnum, int count) {
        //编号规则，年份【2020】，当前年份项目编号【001】，类型【委托书001，其他000】，份数编号【001】

        return null;
    }

    public void  clearProjectFileCompleteByProjectId(Integer projectId){
        projectFileCompleteDao.clearProjectFileCompleteByProjectId(projectId);
    }

    /**
     * 自动生成档案  (目前只考虑委托书)
     * @param projectId
     */
    public void autoCreateProjectFileCompleteNow(Integer projectId) {
        projectFileCompleteDao.clearProjectFileCompleteByProjectId(projectId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        Date now = DateUtils.now();
        int year = DateUtils.getYear(now);//年份
        String projectIndex = "001";//项目编号
        Integer serialNumber = projectInfo.getSerialNumber();
        //委托书 id
        final String project_proxy = "project_proxy";
        if (serialNumber != null) {
            String s = serialNumber.toString();
            if (s.length() < 3) {
                int count = 3 - s.length();
                projectIndex = String.format("%s%s", StringUtils.repeat("0", count), s);
            } else if (s.length() == 3) {
                projectIndex = s;
            } else {
                projectIndex = s;
            }
        }
        List<ProjectFileComplete> completeList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        ProjectFileComplete projectFileComplete = null;
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(projectId, project_proxy, FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            int count = 1;
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                linkedList.add(String.valueOf(year));
                linkedList.add(String.valueOf(projectIndex));
                linkedList.add(String.valueOf(ArchivesFileTypeEnum.POWER_ATTORNEY_ENUM.getKey()));
                String countIndex = String.valueOf(count);
                if (countIndex.length() < 3) {
                    int k = 3 - countIndex.length();
                    countIndex = String.format("%s%s", StringUtils.repeat("0", k), countIndex);
                }
                linkedList.add(countIndex);
                String value = StringUtils.join(linkedList, "-");
                linkedList.clear();
                projectFileComplete = new ProjectFileComplete();
                projectFileComplete.setFileType(ArchivesFileTypeEnum.POWER_ATTORNEY_ENUM.getKey());
                projectFileComplete.setFileName(FilenameUtils.getBaseName(sysAttachmentDto.getFileName()));
                projectFileComplete.setYear(year);
                projectFileComplete.setFileNumber(value);
                projectFileComplete.setProjectId(projectId);
                projectFileComplete.setFileSort(count);
                projectFileComplete.setFileTypeName(ArchivesFileTypeEnum.POWER_ATTORNEY_ENUM.getName());
                projectFileComplete.setCreated(now);
                projectFileComplete.setModified(now);
                projectFileComplete.setCreator(commonService.thisUserAccount());
                projectFileComplete.setBisEnable(true);
                count++;
                completeList.add(projectFileComplete);
            }
        }
        //附件不使用批量插入
        if (CollectionUtils.isNotEmpty(completeList)) {
            projectFileCompleteDao.batchInsert(completeList);
            int count = 0;
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList){
                ProjectFileComplete complete = completeList.get(count);
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(complete.getId());
                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectFileComplete.class));
                attachmentDto.setFieldsName(String.format("%s%s",project_proxy ,complete.getId().toString()));
                try {
                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(),attachmentDto);
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
                count++ ;
            }
        }
    }

    public ProjectFileComplete getProjectFileCompleteById(Integer id) {
        return projectFileCompleteDao.getProjectFileCompleteById(id);
    }

    public Integer addProjectFileComplete(ProjectFileComplete projectFileComplete) {
        return projectFileCompleteDao.addProjectFileComplete(projectFileComplete);
    }

    public boolean updateProjectFileComplete(ProjectFileComplete projectFileComplete, boolean updateNull) {
        return projectFileCompleteDao.updateProjectFileComplete(projectFileComplete, updateNull);
    }

    public boolean deleteProjectFileComplete(Integer id) {
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(id);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectFileComplete.class));
        baseAttachmentService.deleteAttachmentByDto(attachmentDto) ;
        return projectFileCompleteDao.deleteProjectFileComplete(id);
    }

    public List<ProjectFileComplete> getProjectFileCompleteList(ProjectFileComplete projectFileComplete) {
        return projectFileCompleteDao.getProjectFileCompleteList(projectFileComplete);
    }

    public void saveAndUpdate(ProjectFileComplete obj) {
        if (obj.getId() == null || obj.getId() == 0) {
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            addProjectFileComplete(obj);
        } else {
            updateProjectFileComplete(obj, false);
        }
    }

    public BootstrapTableVo getProjectFileCompleteDatList(Integer projectId, Integer year, String fileNumber, String fileName, String fileType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (StringUtils.isBlank(fileName)) {
            fileName = requestBaseParam.getSearch();
        }
        List<ProjectFileComplete> list = projectFileCompleteDao.getProjectFileCompleteList(projectId, year, fileNumber, fileName, fileType);
        if (CollectionUtils.isNotEmpty(list)) {
            for (ProjectFileComplete obj : list) {
                if (StringUtils.isNotBlank(obj.getFileTypeName())) {
                    continue;
                }
                ArchivesFileTypeEnum typeEnumByKey = ArchivesFileTypeEnum.getTypeEnumByKey(obj.getFileType());
                if (typeEnumByKey != null) {
                    obj.setFileTypeName(typeEnumByKey.getName());
                }
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(list) ? list : new ArrayList());
        return vo;
    }

}

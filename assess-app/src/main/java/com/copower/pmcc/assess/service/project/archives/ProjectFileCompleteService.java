package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.assess.common.enums.archives.ArchivesFileTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.archives.ProjectFileCompleteDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectFileComplete;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目归档
 */
@Service
public class ProjectFileCompleteService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectFileCompleteDao projectFileCompleteDao;

    /**
     * 获取项目档案编号
     * @param year 年份
     * @param projectNumber 当前年份项目编号【001】
     * @param archivesFileTypeEnum 类型
     * @param count 份数编号
     * @return
     */
    public String getFileNumberByRole(Integer year, Integer projectNumber, ArchivesFileTypeEnum archivesFileTypeEnum,int count){
        //编号规则，年份【2020】，当前年份项目编号【001】，类型【委托书001，其他000】，份数编号【001】

        return null;
    }

    public ProjectFileComplete getProjectFileCompleteById(Integer id){
        return projectFileCompleteDao.getProjectFileCompleteById(id) ;
    }

    public Integer addProjectFileComplete(ProjectFileComplete projectFileComplete){
        return projectFileCompleteDao.addProjectFileComplete(projectFileComplete) ;
    }

    public boolean updateProjectFileComplete(ProjectFileComplete projectFileComplete, boolean updateNull){
        return projectFileCompleteDao.updateProjectFileComplete(projectFileComplete, updateNull) ;
    }

    public boolean deleteProjectFileComplete(Integer id){
        return projectFileCompleteDao.deleteProjectFileComplete(id) ;
    }

    public List<ProjectFileComplete> getProjectFileCompleteList(ProjectFileComplete projectFileComplete){
        return projectFileCompleteDao.getProjectFileCompleteList(projectFileComplete) ;
    }

}

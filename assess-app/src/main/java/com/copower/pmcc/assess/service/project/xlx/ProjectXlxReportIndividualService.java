package com.copower.pmcc.assess.service.project.xlx;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.xlx.ProjectXlxReportIndividualDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;
import com.copower.pmcc.assess.dto.output.project.xlx.ProjectXlxReportIndividualVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2020-3-20.
 * xlx-生成报告单独事项 兴良信 报告信息
 */
@Service
public class ProjectXlxReportIndividualService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectXlxReportIndividualDao projectXlxReportIndividualDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;

    public List<String> getReportNumber(Integer projectCategory, Integer projectId) {
        List<String> stringList =  new ArrayList<>() ;
        try {
//            stringList =  projectNumberRecordService.getReportNumberList(projectId,getAssessProjectTypeEnum(projectCategory),null) ;
            ProjectNumberRecord query = new ProjectNumberRecord();
            query.setProjectId(projectId);
            stringList =  projectNumberRecordService.getProjectNumberRecordList(query) ;
        } catch (Exception e) {
        }
        return stringList;
    }

    public AssessProjectTypeEnum getAssessProjectTypeEnum(Integer projectCategoryId){
        BaseProjectClassify classifyById = baseProjectClassifyService.getCacheProjectClassifyById(projectCategoryId);
        AssessProjectTypeEnum typeEnum = null;
        if (Objects.equal(classifyById.getFieldName(), AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE)) {
            typeEnum = AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE;
        }
        if (Objects.equal(classifyById.getFieldName(), AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE)) {
            typeEnum = AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND;
        }
        if (Objects.equal(classifyById.getFieldName(), AssessProjectClassifyConstant.COMPREHENSIVE_ASSETS_TYPE)) {
            typeEnum = AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_ASSETS;
        }
        return typeEnum;
    }

    public ProjectXlxReportIndividual getProjectXlxReportIndividualByPlanDetailsId(Integer planDetailsId) {
        ProjectXlxReportIndividual query = new ProjectXlxReportIndividual();
        query.setPlanDetailsId(planDetailsId);
        List<ProjectXlxReportIndividual> xlxReportIndividuals = getProjectXlxReportIndividualListByQuery(query);
        if (CollectionUtils.isNotEmpty(xlxReportIndividuals)) {
            return xlxReportIndividuals.get(0);
        }
        return null;
    }

    public boolean updateProjectXlxReportIndividual(ProjectXlxReportIndividual oo, boolean updateNull) {
        return projectXlxReportIndividualDao.updateProjectXlxReportIndividual(oo, updateNull);
    }

    public boolean saveProjectXlxReportIndividual(ProjectXlxReportIndividual oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        if (StringUtils.isBlank(oo.getProjectName())) {
            if (oo.getProjectId() != null) {
                ProjectInfo projectInfo = projectInfoService.getProjectInfoById(oo.getProjectId());
                oo.setProjectName(projectInfo.getProjectName());
            }
        }
        boolean b = projectXlxReportIndividualDao.saveProjectXlxReportIndividual(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ProjectXlxReportIndividual.class), oo.getId());
        return b;

    }

    public void saveAndUpdateProjectXlxReportIndividual(ProjectXlxReportIndividual oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateProjectXlxReportIndividual(oo, updateNull);
        } else {
            saveProjectXlxReportIndividual(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectXlxReportIndividual.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteProjectXlxReportIndividualById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                projectXlxReportIndividualDao.deleteProjectXlxReportIndividualById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                projectXlxReportIndividualDao.deleteProjectXlxReportIndividualByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(ProjectXlxReportIndividual oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectXlxReportIndividual> projectXlxReportIndividuals = getProjectXlxReportIndividualListLikeQuery(oo);
        List<ProjectXlxReportIndividualVo> vos = new ArrayList<>() ;
        if (CollectionUtils.isNotEmpty(projectXlxReportIndividuals)){
            projectXlxReportIndividuals.forEach(individual -> {
                vos.add(getProjectXlxReportIndividualVo(individual)) ;
            });
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<ProjectXlxReportIndividual> getProjectXlxReportIndividualByIds(List<Integer> ids) {
        return projectXlxReportIndividualDao.getProjectXlxReportIndividualByIds(ids);
    }

    public ProjectXlxReportIndividual getProjectXlxReportIndividualById(Integer id) {
        return projectXlxReportIndividualDao.getProjectXlxReportIndividualById(id);
    }


    public List<ProjectXlxReportIndividual> getProjectXlxReportIndividualListByQuery(ProjectXlxReportIndividual oo) {
        return projectXlxReportIndividualDao.getProjectXlxReportIndividualListByExample(oo);
    }

    public List<ProjectXlxReportIndividual> getProjectXlxReportIndividualListLikeQuery(ProjectXlxReportIndividual oo){
        return projectXlxReportIndividualDao.getProjectXlxReportIndividualListLikeQuery(oo);
    }

    public ProjectXlxReportIndividualVo getProjectXlxReportIndividualVo(ProjectXlxReportIndividual target){
        ProjectXlxReportIndividualVo vo = new ProjectXlxReportIndividualVo();
        BeanUtils.copyProperties(target,vo);
        if (NumberUtils.isNumber(target.getAssessType())){
            BaseProjectClassify classifyById = baseProjectClassifyService.getCacheProjectClassifyById(Integer.parseInt(target.getAssessType()));
            vo.setAssessTypeName(classifyById.getName());
            String assessType = getAssessProjectTypeEnum(classifyById.getId()).getKey() ;
            vo.setAssessType(assessType);
        }
        return vo;
    }

}

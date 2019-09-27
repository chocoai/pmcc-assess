package com.copower.pmcc.assess.service.project.assets;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.assets.AssetsCustomizeDataFieldTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.assets.AssetsCustomizeDataFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.assets.AssetsCustomizeDataFieldDto;
import com.copower.pmcc.assess.dto.output.project.assets.AssetsCustomizeDataFieldVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zch on 2019-9-23.
 * 资产评估 阶段 自定义名称表
 */
@Service
public class AssetsCustomizeDataFieldService {
    @Autowired
    private AssetsCustomizeDataFieldDao dataAssetsAppraisalDicDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    public void setParams(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails) {
        for (AssetsCustomizeDataFieldTypeEnum typeEnum : AssetsCustomizeDataFieldTypeEnum.values()) {
            modelAndView.addObject(StringUtils.uncapitalize(typeEnum.name()), getDataAssetsAppraisalDicListByTypeAndPlanDetailsIdVo(projectPlanDetails.getId(),String.valueOf(typeEnum.getFileType())));
        }
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        if (projectPlanDetails.getProjectPhaseId() != null){
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId()) ;
            modelAndView.addObject(StringUtils.uncapitalize(ProjectPhase.class.getSimpleName()), projectPhase);
        }

        AssetsCustomizeDataField customizeDataField = new AssetsCustomizeDataField();
        customizeDataField.setType(String.valueOf(AssetsCustomizeDataFieldTypeEnum.OtherTypeData.getFileType()));
        customizeDataField.setPlanDetailsId(projectPlanDetails.getId());
        customizeDataField.setProjectId(projectPlanDetails.getProjectId());
        List<AssetsCustomizeDataField> assetsCustomizeDataFieldList = getDataAssetsAppraisalDicListByExample(customizeDataField);
        if (CollectionUtils.isNotEmpty(assetsCustomizeDataFieldList)) {
            modelAndView.addObject(StringUtils.uncapitalize(AssetsCustomizeDataField.class.getSimpleName()), assetsCustomizeDataFieldList.stream().findFirst().get());
        } else {
           saveDataAssetsAppraisalDic(customizeDataField);
            modelAndView.addObject(StringUtils.uncapitalize(AssetsCustomizeDataField.class.getSimpleName()), customizeDataField);
        }

    }



    public boolean saveDataAssetsAppraisalDic(AssetsCustomizeDataField assetsCustomizeDataField)  {
        if (assetsCustomizeDataField == null) {
            return false;
        }
        if (assetsCustomizeDataField.getId() != null && assetsCustomizeDataField.getId() != 0) {
            return dataAssetsAppraisalDicDao.updateAssetsCustomizeDataField(assetsCustomizeDataField);
        } else {
            assetsCustomizeDataField.setCreator(commonService.thisUserAccount());
            dataAssetsAppraisalDicDao.addAssetsCustomizeDataField(assetsCustomizeDataField);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(AssetsCustomizeDataField.class), assetsCustomizeDataField.getId());
            return true;
        }
    }

    public void saveAll(List<AssetsCustomizeDataFieldDto> assetsCustomizeDataFieldList)throws Exception{
        if (CollectionUtils.isNotEmpty(assetsCustomizeDataFieldList)){
            for (AssetsCustomizeDataFieldDto target:assetsCustomizeDataFieldList){
                this.save(target) ;
            }
        }
    }

    public void save(AssetsCustomizeDataFieldDto target)throws Exception{
        saveDataAssetsAppraisalDic(target);
        if (StringUtils.isEmpty(target.getFileId()) ){
            return;
        }
        List<String> stringList = FormatUtils.transformString2List(target.getFileId()) ;
        if (CollectionUtils.isEmpty(stringList)){
            return;
        }
        List<String> ids = Lists.newArrayList();
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableName(FormatUtils.entityNameConvertToTableName(AssetsCustomizeDataField.class));
        for (String string:stringList){
            example.setTableId(target.getId());
            SysAttachmentDto sysAttachmentDto = baseAttachmentService.copyFtpAttachment(Integer.parseInt(string),example);
            ids.add(sysAttachmentDto.getId().toString()) ;
        }
        if (CollectionUtils.isNotEmpty(ids)){
            target.setFileId(StringUtils.join(ids,","));
        }
    }

    public BootstrapTableVo getBootstrapTableVo(AssetsCustomizeDataField oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<AssetsCustomizeDataField> childrenList = getDataAssetsAppraisalDicListByExample(oo);
        List<AssetsCustomizeDataFieldVo> voList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(childrenList)) {
            childrenList.forEach(target -> voList.add(getAssetsCustomizeDataFieldVo(target)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public AssetsCustomizeDataField getDataAssetsAppraisalDicById(Integer id) {
        return dataAssetsAppraisalDicDao.getAssetsCustomizeDataFieldById(id);
    }

    public boolean deleteDataAssetsAppraisalDicById(Integer id) {
        return dataAssetsAppraisalDicDao.deleteAssetsCustomizeDataFieldById(id);
    }

    public void deleteDataAssetsAppraisalDic(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        dataAssetsAppraisalDicDao.deleteAssetsCustomizeDataField(FormatUtils.transformString2Integer(id));
    }

    public List<AssetsCustomizeDataField> getDataAssetsAppraisalDicListByExample(AssetsCustomizeDataField oo) {
        return dataAssetsAppraisalDicDao.getAssetsCustomizeDataFieldListByExample(oo);
    }

    public List<AssetsCustomizeDataField> getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(Integer planDetailsId, String type) {
        return dataAssetsAppraisalDicDao.getAssetsCustomizeDataFieldListByTypeAndPlanDetailsId(planDetailsId, type);
    }

    public List<AssetsCustomizeDataFieldVo> getDataAssetsAppraisalDicListByTypeAndPlanDetailsIdVo(Integer planDetailsId, String type) {
        List<AssetsCustomizeDataFieldVo> voList = Lists.newArrayList();
        List<AssetsCustomizeDataField> dataFieldList = getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(planDetailsId, type);
        if (CollectionUtils.isNotEmpty(dataFieldList)){
            dataFieldList.forEach(po -> voList.add(getAssetsCustomizeDataFieldVo(po)));
        }
        return voList;
    }

    public List<AssetsCustomizeDataField> getDataAssetsAppraisalDicListByType(String type) {
        return dataAssetsAppraisalDicDao.getAssetsCustomizeDataFieldListByType(type);
    }

    public AssetsCustomizeDataFieldVo getAssetsCustomizeDataFieldVo(AssetsCustomizeDataField target) {
        if (target == null) {
            return null;
        }
        AssetsCustomizeDataFieldVo vo = new AssetsCustomizeDataFieldVo();
        BeanUtils.copyProperties(target, vo);
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(target.getId(), null, FormatUtils.entityNameConvertToTableName(AssetsCustomizeDataField.class));
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            StringBuilder builder = new StringBuilder();
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                builder.append(baseAttachmentService.getEditHtml(sysAttachmentDto, true)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        vo.setJsonString(JSONObject.toJSONString(target));
        vo.setCategoryName(baseDataDicService.getNameById(target.getCategory()));
        vo.setTypeCustomizeName(baseDataDicService.getNameById(target.getTypeCustomize()));
        return vo;
    }

    public String getName(String id) {
        if (StringUtils.isEmpty(id)) {
            return "";
        }
        if (!NumberUtils.isNumber(id)) {
            return "";
        }
        AssetsCustomizeDataField dic = getDataAssetsAppraisalDicById(Integer.parseInt(id));
        if (dic != null) {
            return dic.getName();
        }
        return "";
    }

    public String getName(Integer id) {
        if (id == null) {
            return "";
        } else {
            return getName(id.toString());
        }
    }


}

package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.common.enums.AssessUploadEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileCustomDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by kings on 2019-1-22.
 */
@Service
public class SchemeReportFileService extends BaseService {
    @Autowired
    private SchemeReportFileDao schemeReportFileDao;
    @Autowired
    private SchemeReportFileCustomDao schemeReportFileCustomDao;
    @Autowired
    private SchemeReportFileItemDao schemeReportFileItemDao;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private SchemeReimbursementService schemeReimbursementService;

    /**
     * 保存数据
     *
     * @param schemeReportFile
     */
    public void saveSchemeReportFile(SchemeReportFile schemeReportFile) {
        if (schemeReportFile.getId() == null || schemeReportFile.getId() <= 0) {
            schemeReportFile.setCreator(commonService.thisUserAccount());
            schemeReportFileDao.addReportFile(schemeReportFile);
        } else {
            schemeReportFileDao.updateReportFile(schemeReportFile);
        }
    }

    /**
     * 获取委估对象下所有的实况图片
     *
     * @param judgeObjectId
     * @return
     */
    public List<SysAttachmentDto> getLiveSituationAll(Integer judgeObjectId, Integer projectId) throws Exception {
        //1.楼盘外观图 2.楼栋外装图、外观图
        //2.委估对象是合并对象，楼盘楼栋相关图值取一份
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        if (judgeObject == null) return null;
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(judgeObject.getDeclareRecordId(), projectPhase.getId());//现场查勘任务
        BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetails.getId());
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        if (basicApply != null) {
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(basicEstate.getId(), AssessUploadEnum.ESTATE_FLOOR_APPEARANCE_FIGURE.getKey(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            if (CollectionUtils.isNotEmpty(dtoList))
                attachmentDtoList.addAll(dtoList);
            dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FIGURE_OUTSIDE.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            if (CollectionUtils.isNotEmpty(dtoList))
                attachmentDtoList.addAll(dtoList);
            dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FLOOR_APPEARANCE_FIGURE.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            if (CollectionUtils.isNotEmpty(dtoList))
                attachmentDtoList.addAll(dtoList);
        }
        return attachmentDtoList;
    }

    /**
     * 选择实况图片
     *
     * @param schemeReportFileItem
     */
    public Integer selectLiveSituation(SchemeReportFileItem schemeReportFileItem) throws BusinessException {
        //验证是否重复选择
        if (schemeReportFileItemDao.hasReportFileItem(schemeReportFileItem.getPlanDetailsId(),
                schemeReportFileItem.getJudgeObjectId(), AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey(), schemeReportFileItem.getAttachmentId()))
            throw new BusinessException("该图片已被选择");
        schemeReportFileItem.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        schemeReportFileItem.setCreator(commonService.thisUserAccount());
        schemeReportFileItemDao.addReportFileItem(schemeReportFileItem);
        return schemeReportFileItem.getId();
    }

    /**
     * 移除实况图片
     *
     * @param id
     */
    public void removeLiveSituation(Integer id) {
        schemeReportFileItemDao.deleteReportFileItem(id);
    }

    public void updateReportFileItem(SchemeReportFileItem schemeReportFileItem) {
        schemeReportFileItemDao.updateReportFileItem(schemeReportFileItem);
    }

    /**
     * 获取委估对象下已选的实况图片
     *
     * @param judgeObjectId
     * @return
     */
    public List<SchemeReportFileItem> getLiveSituationSelect(Integer planDetailsId, Integer judgeObjectId) {
        SchemeReportFileItem where = new SchemeReportFileItem();
        where.setPlanDetailsId(planDetailsId);
        where.setJudgeObjectId(judgeObjectId);
        where.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        return schemeReportFileItemDao.getReportFileItemList(where);
    }

    /**
     * 获取该区域下所有权属证明文件
     *
     * @param areaId
     * @return
     */
    public List<SysAttachmentDto> getOwnershipCertFileList(Integer areaId) {
        //1.找出该区域下的所有申报记录
        //2.根据申报记录获取对应的权证附件
        HashSet<Integer> hashSet = Sets.newHashSet();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            hashSet.add(schemeJudgeObject.getDeclareRecordId());
        }
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(hashSet)) {
            for (Integer id : hashSet) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(id);
                List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
                if (CollectionUtils.isNotEmpty(attachmentDtos))
                    attachmentDtoList.addAll(attachmentDtos);
            }
        }
        return attachmentDtoList;
    }

    /**
     * 获取该区域证书清查地址不一致附件
     *
     * @param areaId
     * @return
     */
    public List<SysAttachmentDto> getInventoryAddressFileList(Integer areaId) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            hashSet.add(schemeJudgeObject.getDeclareRecordId());
        }
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        Integer inventoryContent = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS).getId();
        if (CollectionUtils.isNotEmpty(hashSet)) {
            for (Integer id : hashSet) {
                SurveyAssetInventory inventory = surveyAssetInventoryService.getDataByDeclareId(id);
                if (inventory != null) {
                    List<SurveyAssetInventoryContent> contents = surveyAssetInventoryContentService.getContentListByPlanDetailsId(inventory.getPlanDetailId());
                    if (CollectionUtils.isNotEmpty(contents)) {
                        for (SurveyAssetInventoryContent content : contents) {
                            if (inventoryContent.equals(content.getInventoryContent()) && StringUtils.equals(content.getAreConsistent(), "不一致")) {
                                //取附件
                                List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getByField_tableId(content.getId(), null, FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class));
                                if (CollectionUtils.isNotEmpty(attachmentDtos))
                                    attachmentDtoList.addAll(attachmentDtos);
                            }
                        }
                    }
                }
            }
        }
        return attachmentDtoList;
    }

    /**
     * 获取法定优先受偿款附件
     *
     * @param areaId
     * @return
     */
    public List<SysAttachmentDto> getReimbursementFileList(Integer areaId) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        List<Integer> judgeIds = LangUtils.transform(judgeObjectList, o -> o.getId());
        List<SchemeReimbursement> reimbursements = schemeReimbursementService.getSchemeReimbursements(judgeIds);
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        for (SchemeReimbursement reimbursement : reimbursements) {
            List<SysAttachmentDto> dtos = baseAttachmentService.getByField_tableId(reimbursement.getId(), null, FormatUtils.entityNameConvertToTableName(SchemeReimbursement.class));
            if (CollectionUtils.isNotEmpty(dtos)) {
                attachmentDtoList.addAll(dtos);
            }
        }
        return attachmentDtoList;
    }

    /**
     * 获取自定义块
     *
     * @param planDetailsId
     * @return
     */
    public List<SchemeReportFileCustom> getReportFileCustomList(Integer planDetailsId) {
        SchemeReportFileCustom where = new SchemeReportFileCustom();
        where.setPlanDetailsId(planDetailsId);
        return schemeReportFileCustomDao.getReportFileCustomList(where);
    }

    /**
     * 新增自定义块
     *
     * @param schemeReportFileCustom
     */
    public SchemeReportFileCustom addReportFileCustom(SchemeReportFileCustom schemeReportFileCustom) {
        schemeReportFileCustom.setCreator(commonService.thisUserAccount());
        schemeReportFileCustomDao.addReportFileCustom(schemeReportFileCustom);
        return schemeReportFileCustom;
    }

    /**
     * 删除自定义块
     *
     * @param id
     */
    public void deleteReportFileCustom(Integer id) {
        //有相关附件一并删除
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableId(id);
        where.setTableName(FormatUtils.entityNameConvertToTableName(SchemeReportFileCustom.class));
        baseAttachmentService.deleteAttachmentByDto(where);
        schemeReportFileCustomDao.deleteReportFileCustom(id);
    }
}

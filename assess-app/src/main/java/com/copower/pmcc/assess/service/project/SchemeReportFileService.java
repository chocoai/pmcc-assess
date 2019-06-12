package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.AssessUploadEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileCustomDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    private SurveyCommonService surveyCommonService;
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
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;

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

    public SchemeReportFile getReportFileByPlanDetailsId(Integer planDetailsId) {
        SchemeReportFile where = new SchemeReportFile();
        where.setPlanDetailsId(planDetailsId);
        List<SchemeReportFile> reportFileList = schemeReportFileDao.getReportFileList(where);
        if (CollectionUtils.isEmpty(reportFileList)) return null;
        baseAttachmentService.createTempDirPath(commonService.thisUserAccount());
        return reportFileList.get(0);
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
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        if (basicApply != null) {
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            List<SysAttachmentDto> dtoList = null;
            if (basicEstate != null) {
                dtoList = baseAttachmentService.getByField_tableId(basicEstate.getId(), AssessUploadEnum.ESTATE_FLOOR_APPEARANCE_FIGURE.getKey(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    dtoList.forEach(o -> {
                        o.setReName(AssessUploadEnum.ESTATE_FLOOR_APPEARANCE_FIGURE.getValue());
                        attachmentDtoList.add(o);
                    });
                }
            }

            if(basicBuilding!=null){
                dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FIGURE_OUTSIDE.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    dtoList.forEach(o -> {
                        o.setReName(AssessUploadEnum.BUILDING_FIGURE_OUTSIDE.getValue());
                        attachmentDtoList.add(o);
                    });
                }

                dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FLOOR_APPEARANCE_FIGURE.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    dtoList.forEach(o -> {
                        o.setReName(AssessUploadEnum.BUILDING_FLOOR_APPEARANCE_FIGURE.getValue());
                        attachmentDtoList.add(o);
                    });
                }
            }

            if(basicUnit!=null){
                dtoList = baseAttachmentService.getByField_tableId(basicUnit.getId(), AssessUploadEnum.UNIT_APPEARANCE.getKey(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    dtoList.forEach(o -> {
                        o.setReName(AssessUploadEnum.UNIT_APPEARANCE.getValue());
                        attachmentDtoList.add(o);
                    });
                }
            }

            if(basicHouse!=null){
                dtoList = baseAttachmentService.getByField_tableId(basicHouse.getId(), AssessUploadEnum.HOUSE_DECORATE.getKey(), FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    dtoList.forEach(o -> {
                        o.setReName(AssessUploadEnum.HOUSE_DECORATE.getValue());
                        attachmentDtoList.add(o);
                    });
                }
            }

            List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
            if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                for (BasicHouseRoom item: basicHouseRoomList) {
                    dtoList = baseAttachmentService.getByField_tableId(item.getId(), AssessUploadEnum.HOUSE_ROOM_FILE.getKey(), FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class));
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(String.format("%s",item.getRoomType()));
                            attachmentDtoList.add(o);
                        });
                    }
                }
            }
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
        if (schemeReportFileItemDao.hasReportFileItem(schemeReportFileItem.getJudgeObjectId(), AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey(), schemeReportFileItem.getAttachmentId()))
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
     * 获取估价委托书附件
     *
     * @param projectId
     * @return
     */
    public SysAttachmentDto getProjectProxyFileList(Integer projectId) {
        List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(projectId, AssessUploadEnum.PROJECT_PROXY.getKey(), FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        if (CollectionUtils.isEmpty(attachmentDtoList)) return null;
        return attachmentDtoList.get(attachmentDtoList.size() - 1);
    }

    /**
     * 获取估价对象位置示意图附件
     *
     * @param judgeObjectId
     * @return
     */
    public List<SysAttachmentDto> getJudgeObjectPositionFileList(Integer judgeObjectId) {
        return baseAttachmentService.getByField_tableId(judgeObjectId, AssessUploadEnum.JUDGE_OBJECT_POSITION.getKey(),
                FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
    }

    /**
     * 获取委估对象下已选的实况图片
     *
     * @param judgeObjectId
     * @return
     */
    public List<SchemeReportFileItem> getLiveSituationSelect(Integer judgeObjectId) {
        SchemeReportFileItem where = new SchemeReportFileItem();
        where.setJudgeObjectId(judgeObjectId);
        where.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        return schemeReportFileItemDao.getReportFileItemList(where);
    }

    /**
     * 选择的图片修改名称
     *
     * @param id
     * @return
     */
    public boolean reportFileEditName(Integer id,String name) {
        SchemeReportFileItem reportFileItemById = schemeReportFileItemDao.getReportFileItemById(id);
        reportFileItemById.setFileName(name);
        return schemeReportFileItemDao.updateReportFileItem(reportFileItemById);
    }

    /**
     * 获取该区域下所有权属证明文件
     *
     * @param areaId
     * @return
     */
    public Map<Integer, List<SysAttachmentDto>> getOwnershipCertFileList(Integer areaId) {
        //1.找出该区域下的所有申报记录
        //2.根据申报记录获取对应的权证附件
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        Map<Integer, List<SysAttachmentDto>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            List<SysAttachmentDto> resultAttachments = Lists.newArrayList();
            if (schemeJudgeObject.getBisMerge() == Boolean.TRUE) {
                List<SchemeJudgeObject> childrenJudgeObject = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(childrenJudgeObject)) {
                    for (SchemeJudgeObject judgeObject : childrenJudgeObject) {
                        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
                        List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
                        if (CollectionUtils.isNotEmpty(attachmentDtoList))
                            resultAttachments.addAll(attachmentDtoList);
                    }
                }
            } else {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
                if (CollectionUtils.isNotEmpty(attachmentDtoList))
                    resultAttachments.addAll(attachmentDtoList);
            }
            map.put(schemeJudgeObject.getId(), resultAttachments);
        }
        return map;
    }

    /**
     * 获取该区域证书清查地址不一致附件
     *
     * @param areaId
     * @return
     */
    public Map<Integer, List<SysAttachmentDto>> getInventoryAddressFileList(Integer areaId) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        Map<Integer, List<SysAttachmentDto>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            List<SysAttachmentDto> resultList = Lists.newArrayList();
            if (schemeJudgeObject.getBisMerge() == Boolean.TRUE) {
                List<SchemeJudgeObject> childrenJudgeObject = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(childrenJudgeObject)) {
                    for (SchemeJudgeObject judgeObject : childrenJudgeObject) {
                        List<SysAttachmentDto> list = getInventoryContentFile(judgeObject);
                        if (CollectionUtils.isNotEmpty(list))
                            resultList.addAll(list);
                    }
                }
            } else {
                List<SysAttachmentDto> list = getInventoryContentFile(schemeJudgeObject);
                if (CollectionUtils.isNotEmpty(list))
                    resultList.addAll(list);
            }
            map.put(schemeJudgeObject.getId(), resultList);
        }
        return map;
    }

    private List<SysAttachmentDto> getInventoryContentFile(SchemeJudgeObject schemeJudgeObject) {
        Integer inventoryContent = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS).getId();
        SurveyAssetInventory inventory = surveyAssetInventoryService.getDataByDeclareId(schemeJudgeObject.getDeclareRecordId());
        if (inventory != null) {
            List<SurveyAssetInventoryContent> contents = surveyAssetInventoryContentService.getContentListByPlanDetailsId(inventory.getPlanDetailId());
            if (CollectionUtils.isNotEmpty(contents)) {
                for (SurveyAssetInventoryContent content : contents) {
                    if (inventoryContent.equals(content.getInventoryContent()) && StringUtils.equals(content.getAreConsistent(), "不一致")) {
                        //取附件
                        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getByField_tableId(content.getId(), null, FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class));
                        return attachmentDtos;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取法定优先受偿款附件
     *
     * @param areaId
     * @return
     */
    public Map<Integer, List<SysAttachmentDto>> getReimbursementFileList(Integer areaId) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(areaId);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        Map<Integer, List<SysAttachmentDto>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            List<SchemeReimbursement> reimbursements = null;
            if (CollectionUtils.isNotEmpty(reimbursements)) {
                SchemeReimbursement schemeReimbursement = reimbursements.get(0);
                List<SysAttachmentDto> dtos = baseAttachmentService.getByField_tableId(schemeReimbursement.getId(), null, FormatUtils.entityNameConvertToTableName(SchemeReimbursement.class));
                if (CollectionUtils.isNotEmpty(dtos))
                    map.put(schemeJudgeObject.getId(), dtos);
            }
        }
        return map;
    }

    /**
     * 获取自定义块
     *
     * @param areaId
     * @return
     */
    public List<SchemeReportFileCustom> getReportFileCustomList(Integer areaId) {
        SchemeReportFileCustom where = new SchemeReportFileCustom();
        where.setAreaId(areaId);
        return schemeReportFileCustomDao.getReportFileCustomList(where);
    }

    /**
     * 获取自定义块附件
     *
     * @param customId
     * @return
     */
    public List<SysAttachmentDto> getCustomFileList(Integer customId) {
        return baseAttachmentService.getByField_tableId(customId, null, FormatUtils.entityNameConvertToTableName(SchemeReportFileCustom.class));
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

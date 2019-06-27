package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.AssessUploadEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileCustomDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateTaggingVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReportFileItemVo;
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
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    private BasicEstateTaggingService basicEstateTaggingService;
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
     * 移除实况图片
     *
     * @param id
     */
    public void removeLiveSituation(Integer id) {
        //删除相关附件
        SysAttachmentDto reportAttachment = new SysAttachmentDto();
        reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRecord.class));
        reportAttachment.setFieldsName("live_situation_select_supplement");
        reportAttachment.setTableId(id);
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(reportAttachment);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (SysAttachmentDto attachmentDto : attachmentList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
            }
        }
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
     * 生成位置示意图
     *
     * @param declareRecordList
     */
    public void makeJudgeObjectPosition(List<DeclareRecord> declareRecordList) {
        if (CollectionUtils.isEmpty(declareRecordList)) return;
        for (DeclareRecord declareRecord : declareRecordList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(declareRecord.getId());
            if (basicApply == null) continue;
            List<BasicEstateTaggingVo> taggingList = basicEstateTaggingService.getEstateTaggingList(basicApply.getId(), EstateTaggingTypeEnum.UNIT.getKey());
            if (CollectionUtils.isNotEmpty(taggingList)) {
                BasicEstateTagging tagging = taggingList.get(0);
                SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
                sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRecord.class));
                sysAttachmentDto.setProjectId(declareRecord.getProjectId());
                sysAttachmentDto.setTableId(declareRecord.getId());
                sysAttachmentDto.setFieldsName(AssessUploadEnum.JUDGE_OBJECT_POSITION.getKey());
                sysAttachmentDto.setFileName("位置示意图.jpg");
                // 已存在则不生成
                List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
                if(CollectionUtils.isNotEmpty(attachmentList)) continue;
                publicService.downLoadLocationImage(tagging.getLng(), tagging.getLat(), sysAttachmentDto);
            }
        }
    }

    /**
     * 获取估价对象位置示意图附件
     *
     * @param declareRecordId
     * @return
     */
    public List<SysAttachmentDto> getJudgeObjectPositionFileList(Integer declareRecordId) {
        return baseAttachmentService.getByField_tableId(declareRecordId, AssessUploadEnum.JUDGE_OBJECT_POSITION.getKey(),
                FormatUtils.entityNameConvertToTableName(DeclareRecord.class));
    }


    /**
     * 获取实况图片ByDeclareRecordId
     *
     * @param declareRecordId
     * @return
     */
    public List<SchemeReportFileItem> getListByDeclareRecordId(Integer declareRecordId) {
        SchemeReportFileItem where = new SchemeReportFileItem();
        where.setDeclareRecordId(declareRecordId);
        where.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        List<SchemeReportFileItem> reportFileItemList = schemeReportFileItemDao.getReportFileItemList(where);
        return LangUtils.transform(reportFileItemList,o->getSchemeReportFileItemVo(o));
    }


    public SchemeReportFileItem getSchemeReportFileItemById(Integer schemeReportFileItemId) {
        return schemeReportFileItemDao.getReportFileItemById(schemeReportFileItemId);
    }

    /**
     * 选择的图片修改名称
     *
     * @param id
     * @return
     */
    public boolean reportFileEditName(Integer id, String name, Integer sorting) {
        SchemeReportFileItem reportFileItemById = schemeReportFileItemDao.getReportFileItemById(id);
        reportFileItemById.setFileName(name);
        reportFileItemById.setSorting(sorting);
        return schemeReportFileItemDao.updateReportFileItem(reportFileItemById);
    }

    /**
     * 获取该区域下所有权属证明文件
     *
     * @param projectId
     * @return
     */
    public Map<Integer, List<SysAttachmentDto>> getOwnershipCertFileList(Integer projectId) {
        Map<Integer, List<SysAttachmentDto>> map = Maps.newHashMap();
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        if(CollectionUtils.isNotEmpty(declareRecordList)) {
            for (DeclareRecord declareRecord:declareRecordList){
                List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
                map.put(declareRecord.getId(), attachmentDtoList);
            }
        }
        return map;
    }

    /**
     * 获取该区域证书清查地址不一致附件
     *
     * @param projectId
     * @return
     */
    public Map<Integer, List<SysAttachmentDto>> getInventoryAddressFileList(Integer projectId) {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        Map<Integer, List<SysAttachmentDto>> map = Maps.newHashMap();
        if(CollectionUtils.isNotEmpty(declareRecordList)) {
            for (DeclareRecord declareRecord : declareRecordList) {
                List<SysAttachmentDto> attachmentDtoList = getInventoryContentFile(declareRecord);
                map.put(declareRecord.getId(), attachmentDtoList);
            }
        }
        return map;
    }

    private List<SysAttachmentDto> getInventoryContentFile(DeclareRecord declareRecord) {
        Integer inventoryContent = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS).getId();
        SurveyAssetInventory inventory = surveyAssetInventoryService.getDataByDeclareId(declareRecord.getId());
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
     * @param projectId
     * @return
     */
    public Map<Integer, List<SysAttachmentDto>> getReimbursementFileList(Integer projectId) {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isEmpty(declareRecordList)) return null;
        Map<Integer, List<SysAttachmentDto>> map = Maps.newHashMap();
        for (DeclareRecord declareRecord : declareRecordList) {
            List<SchemeReimbursement> reimbursements = null;
            if (CollectionUtils.isNotEmpty(reimbursements)) {
                SchemeReimbursement schemeReimbursement = reimbursements.get(0);
                List<SysAttachmentDto> dtos = baseAttachmentService.getByField_tableId(schemeReimbursement.getId(), null, FormatUtils.entityNameConvertToTableName(SchemeReimbursement.class));
                if (CollectionUtils.isNotEmpty(dtos))
                    map.put(declareRecord.getId(), dtos);
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

    //添加实况照片
    public void saveToReportFileItem(SchemeReportFileItem schemeReportFileItem) {
        if (schemeReportFileItem.getId() != null && schemeReportFileItem.getId() > 0) {
            schemeReportFileItemDao.updateReportFileItem(schemeReportFileItem);
        } else {
            schemeReportFileItem.setCreator(commonService.thisUserAccount());
            schemeReportFileItem.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
            schemeReportFileItemDao.addReportFileItem(schemeReportFileItem);
            //更新附件信息
            SysAttachmentDto reportAttachment = new SysAttachmentDto();
            reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRecord.class));
            reportAttachment.setFieldsName("live_situation_select_supplement");
            reportAttachment.setTableId(0);
            reportAttachment.setCreater(commonService.thisUserAccount());
            SysAttachmentDto attachmentNew = new SysAttachmentDto();
            attachmentNew.setTableId(schemeReportFileItem.getId());
            baseAttachmentService.updateAttachementByExample(reportAttachment, attachmentNew);
        }
    }

    public SchemeReportFileItemVo getSchemeReportFileItemVo(SchemeReportFileItem schemeReportFileItem){
        SchemeReportFileItemVo vo = new SchemeReportFileItemVo();
        BeanUtils.copyProperties(schemeReportFileItem,vo);
        //获取附件
        SysAttachmentDto reportAttachment = new SysAttachmentDto();
        reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRecord.class));
        reportAttachment.setFieldsName("live_situation_select_supplement");
        reportAttachment.setTableId(schemeReportFileItem.getId());

        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(reportAttachment);
        if(!org.springframework.util.CollectionUtils.isEmpty(attachmentList)){
            StringBuilder stringBuilder=new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentList) {
                stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
            }
            vo.setFileViewName(stringBuilder.toString());
        }
        return vo;
    }


    public List<SysAttachmentDto> getAttachmentListBySchemeReportFile(SchemeReportFileItem schemeReportFileItem){
        SysAttachmentDto reportAttachment = new SysAttachmentDto();
        reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRecord.class));
        reportAttachment.setFieldsName("live_situation_select_supplement");
        reportAttachment.setTableId(schemeReportFileItem.getId());
        return baseAttachmentService.getAttachmentList(reportAttachment);
    }
}

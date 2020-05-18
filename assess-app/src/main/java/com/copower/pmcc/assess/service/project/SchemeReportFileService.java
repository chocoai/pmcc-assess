package com.copower.pmcc.assess.service.project;

import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.AssessUploadEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringAndEquipmentCenterDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileCustomDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataLocaleSurveyPictureVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRecordVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReportFileItemVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataLocaleSurveyPictureService;
import com.copower.pmcc.assess.service.data.DataLocaleSurveyService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
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
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicApplyService basicApplyService;
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
    @Autowired
    private DataLocaleSurveyPictureService dataLocaleSurveyPictureService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterDao declareBuildEngineeringAndEquipmentCenterDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private DataLocaleSurveyService dataLocaleSurveyService;

    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private SchemeReportFileService schemeReportFileService;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;


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
        reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        reportAttachment.setFieldsName("live_situation_select_supplement");
        reportAttachment.setTableId(id);
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(reportAttachment);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (SysAttachmentDto attachmentDto : attachmentList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
            }
        }
        //先删除原来对应查勘的附件
        this.deleteSurveyFile(id);

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
     * @param schemeJudgeObjectId
     */
    public void makeJudgeObjectPosition(Integer schemeJudgeObjectId) throws Exception {
        if (schemeJudgeObjectId == null) return;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObjectId);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
        if (basicApply == null) return;

        // 删除原位置图
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        sysAttachmentDto.setProjectId(schemeJudgeObject.getProjectId());
        sysAttachmentDto.setTableId(schemeJudgeObject.getId());
        sysAttachmentDto.setFieldsName(AssessUploadEnum.JUDGE_OBJECT_POSITION.getKey());
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (SysAttachmentDto attachment : attachmentList) {
                baseAttachmentService.deleteAttachmentByDto(attachment);
            }
        }
        sysAttachmentDto.setFileName("位置示意图.jpg");
        BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
        BasicEstateTagging where = new BasicEstateTagging();
        where.setType(BasicFormClassifyEnum.UNIT.getKey());
        where.setTableId(basicUnit.getId());
        List<BasicEstateTagging> taggings = basicEstateTaggingService.getBasicEstateTaggingList(where);
        if (CollectionUtils.isNotEmpty(taggings)) {
            taggings.forEach(o -> publicService.downLoadLocationImage(o.getLng(), o.getLat(), sysAttachmentDto));
        }
    }

    /**
     * 获取估价对象位置示意图附件
     *
     * @param schemeJudgeObjectId
     * @return
     */
    public List<SysAttachmentDto> getJudgeObjectPositionFileList(Integer schemeJudgeObjectId) {
        return baseAttachmentService.getByField_tableId(schemeJudgeObjectId, AssessUploadEnum.JUDGE_OBJECT_POSITION.getKey(),
                FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
    }


    /**
     * 获取上报告的实况图片BySchemeJudgeObjectId
     *
     * @param schemeJudgeObjectId
     * @return
     */
    public List<SchemeReportFileItem> getReportListBySchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        SchemeReportFileItem where = new SchemeReportFileItem();
        where.setSchemeJudgeObjectId(schemeJudgeObjectId);
        where.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        where.setBisEnable(true);
        List<SchemeReportFileItem> reportFileItemList = schemeReportFileItemDao.getReportFileItemList(where);
        return LangUtils.transform(reportFileItemList, o -> getSchemeReportFileItemVo(o));
    }

    /**
     * 获取实况图片BySchemeJudgeObjectId
     *
     * @param schemeJudgeObjectId
     * @return
     */
    public List<SchemeReportFileItem> getListBySchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        SchemeReportFileItem where = new SchemeReportFileItem();
        where.setSchemeJudgeObjectId(schemeJudgeObjectId);
        where.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        List<SchemeReportFileItem> reportFileItemList = schemeReportFileItemDao.getReportFileItemList(where);
        return LangUtils.transform(reportFileItemList, o -> getSchemeReportFileItemVo(o));
    }


    public SchemeReportFileItem getSchemeReportFileItemById(Integer schemeReportFileItemId) {
        return schemeReportFileItemDao.getReportFileItemById(schemeReportFileItemId);
    }

    /**
     * 获取委估对象下所有的实况图片
     *
     * @param schemeJudgeObjectId
     * @return
     */
    public List<SysAttachmentDto> getLiveSituationAll(Integer schemeJudgeObjectId) throws Exception {
        //1.楼盘外观图 2.楼栋外装图、外观图
        //2.委估对象是合并对象，楼盘楼栋相关图值取一份
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObjectId);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());


        BaseDataDic basicEstateDic = baseDataDicService.getCacheDataDicByFieldName("basicEstate");
        BaseDataDic basicBuildingDic = baseDataDicService.getCacheDataDicByFieldName("basicBuilding");
        BaseDataDic basicUnitDic = baseDataDicService.getCacheDataDicByFieldName("basicUnit");
        BaseDataDic basicHouseDic = baseDataDicService.getCacheDataDicByFieldName("basicHouse");
        List<BaseDataDic> sysDataDics = Lists.newArrayList();
        baseDataDicService.getBestLowDicListByPid(sysDataDics, basicEstateDic.getId());
        baseDataDicService.getBestLowDicListByPid(sysDataDics, basicBuildingDic.getId());
        baseDataDicService.getBestLowDicListByPid(sysDataDics, basicUnitDic.getId());
        baseDataDicService.getBestLowDicListByPid(sysDataDics, basicHouseDic.getId());

        Map<String, List<SysAttachmentDto>> map = new HashMap<String, List<SysAttachmentDto>>();
        //返回的数据
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        if (basicApply != null&& CollectionUtils.isNotEmpty(sysDataDics)) {
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            List<SysAttachmentDto> dtoList = null;
            if (basicEstate != null) {
                for (BaseDataDic item : sysDataDics) {
                    dtoList = baseAttachmentService.getByField_tableId(basicEstate.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        removeGenerateFile(dtoList);
                        map.put(String.format("%s%s","楼盘",item.getName()), dtoList);
                    }
                }
            }

            if (basicBuilding != null) {
                for (BaseDataDic item : sysDataDics) {
                    dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        removeGenerateFile(dtoList);
                        map.put(String.format("%s%s","楼栋",item.getName()), dtoList);
                    }
                }
            }


            if (basicUnit != null) {
                for (BaseDataDic item : sysDataDics) {
                    dtoList = baseAttachmentService.getByField_tableId(basicUnit.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        removeGenerateFile(dtoList);
                        map.put(String.format("%s%s","单元",item.getName()), dtoList);
                    }
                }
            }

            if (basicHouse != null) {
                for (BaseDataDic item : sysDataDics) {
                    dtoList = baseAttachmentService.getByField_tableId(basicHouse.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        removeGenerateFile(dtoList);
                        map.put(String.format("%s%s","房屋",item.getName()), dtoList);
                    }
                }
                //房间
                List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
                if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                    for (BasicHouseRoom item : basicHouseRoomList) {
                        dtoList = baseAttachmentService.getByField_tableId(item.getId(), AssessUploadEnum.HOUSE_ROOM_FILE.getKey(), FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class));
                        removeGenerateFile(dtoList);
                        if (CollectionUtils.isNotEmpty(dtoList)) {
                            dtoList.forEach(o -> {
                                o.setReName(AssessUploadEnum.HOUSE_ROOM_FILE.getValue());
                                attachmentDtoList.add(o);
                            });
                        }
                    }
                }
            }

            for (Map.Entry<String, List<SysAttachmentDto>> entry : map.entrySet()) {
                if (CollectionUtils.isNotEmpty(entry.getValue())) {
                    entry.getValue().forEach(o -> {
                        o.setReName(entry.getKey());
                        attachmentDtoList.add(o);
                    });
                }
            }
        }
        return attachmentDtoList;
    }


    /**
     * 根据类型获取委估对象下实况图片
     *
     * @param certifyPart
     * @param schemeJudgeObjectId
     * @return
     */
    public List<SysAttachmentDto> getLiveSituationByCertifyPart(Integer certifyPart, Integer schemeJudgeObjectId) throws Exception {
        BaseDataDic dataDic = baseDataDicService.getDataDicById(certifyPart);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObjectId);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());

        //1.楼盘外观图 2.楼栋外装图、外观图
        //2.委估对象是合并对象，楼盘楼栋相关图值取一份
        //通过pid获取所有最低级的BaseDataDic数据
        List<BaseDataDic> sysDataDics = Lists.newArrayList();
        baseDataDicService.getBestLowDicListByPid(sysDataDics, dataDic.getId());
        Map<String, List<SysAttachmentDto>> map = new HashMap<String, List<SysAttachmentDto>>();
        //返回的数据
        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        List<SysAttachmentDto> dtoList = null;
        if (basicApply != null) {
            if ("basicEstate".equals(dataDic.getFieldName())) {
                BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                if (basicEstate != null && CollectionUtils.isNotEmpty(sysDataDics)) {
                    for (BaseDataDic item : sysDataDics) {
                        dtoList = baseAttachmentService.getByField_tableId(basicEstate.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                        if (CollectionUtils.isNotEmpty(dtoList)) {
                            removeGenerateFile(dtoList);
                            map.put(String.format("%s%s","楼盘",item.getName()), dtoList);
                        }
                    }
                }
            }
            if ("basicBuilding".equals(dataDic.getFieldName())) {
                BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
                if (basicBuilding != null && CollectionUtils.isNotEmpty(sysDataDics)) {
                    for (BaseDataDic item : sysDataDics) {
                        dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                        if (CollectionUtils.isNotEmpty(dtoList)) {
                            removeGenerateFile(dtoList);
                            map.put(String.format("%s%s","楼栋",item.getName()), dtoList);
                        }
                    }
                }
            }
            if ("basicUnit".equals(dataDic.getFieldName())) {
                BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
                if (basicUnit != null && CollectionUtils.isNotEmpty(sysDataDics)) {
                    for (BaseDataDic item : sysDataDics) {
                        dtoList = baseAttachmentService.getByField_tableId(basicUnit.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                        if (CollectionUtils.isNotEmpty(dtoList)) {
                            removeGenerateFile(dtoList);
                            map.put(String.format("%s%s","单元",item.getName()), dtoList);
                        }
                    }
                }
            }
            if ("basicHouse".equals(dataDic.getFieldName())) {
                BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
                if (basicHouse != null && CollectionUtils.isNotEmpty(sysDataDics)) {
                    for (BaseDataDic item : sysDataDics) {
                        dtoList = baseAttachmentService.getByField_tableId(basicHouse.getId(), item.getFieldName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                        if (CollectionUtils.isNotEmpty(dtoList)) {
                            removeGenerateFile(dtoList);
                            map.put(String.format("%s%s","房屋",item.getName()), dtoList);
                        }
                    }
                    //房间
                    List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
                    if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                        for (BasicHouseRoom item : basicHouseRoomList) {
                            dtoList = baseAttachmentService.getByField_tableId(item.getId(), AssessUploadEnum.HOUSE_ROOM_FILE.getKey(), FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class));
                            removeGenerateFile(dtoList);
                            if (CollectionUtils.isNotEmpty(dtoList)) {
                                dtoList.forEach(o -> {
                                    o.setReName(AssessUploadEnum.HOUSE_ROOM_FILE.getValue());
                                    attachmentDtoList.add(o);
                                });
                            }
                        }
                    }
                }
            }
            for (Map.Entry<String, List<SysAttachmentDto>> entry : map.entrySet()) {
                if (CollectionUtils.isNotEmpty(entry.getValue())) {
                    entry.getValue().forEach(o -> {
                        o.setReName(entry.getKey());
                        attachmentDtoList.add(o);
                    });
                }
            }

        }

        return attachmentDtoList;
    }

    /**
     * 获取委估对象下对应位置的实况图片
     *
     * @param schemeJudgeObjectId
     * @param certifyPartCategory
     * @return
     */
    public List<SysAttachmentDto> correspondingSitePic(Integer schemeJudgeObjectId, Integer certifyPartCategory) throws Exception {
        BaseDataDic correspondingSite = baseDataDicService.getDataDicById(certifyPartCategory);
        String fieldName = correspondingSite.getFieldName();
        //1.楼盘外观图 2.楼栋外装图、外观图
        //2.委估对象是合并对象，楼盘楼栋相关图值取一份
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObjectId);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());

        List<SysAttachmentDto> attachmentDtoList = Lists.newArrayList();
        if (basicApply != null) {
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            List<SysAttachmentDto> dtoList = null;
            if (basicEstate != null) {
                if (fieldName.equals(AssessUploadEnum.ESTATE_FLOOR_APPEARANCE_FIGURE.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicEstate.getId(), AssessUploadEnum.ESTATE_FLOOR_APPEARANCE_FIGURE.getKey(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.ESTATE_FLOOR_APPEARANCE_FIGURE.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
                if (fieldName.equals(AssessUploadEnum.ESTATE_FLOOR_TOTAL_PLAN.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicEstate.getId(), AssessUploadEnum.ESTATE_FLOOR_TOTAL_PLAN.getKey(), FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.ESTATE_FLOOR_TOTAL_PLAN.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
            }

            if (basicBuilding != null) {
                if (fieldName.equals(AssessUploadEnum.BUILDING_FIGURE_OUTSIDE.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FIGURE_OUTSIDE.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.BUILDING_FIGURE_OUTSIDE.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
                if (fieldName.equals(AssessUploadEnum.BUILDING_FLOOR_APPEARANCE_FIGURE.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FLOOR_APPEARANCE_FIGURE.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.BUILDING_FLOOR_APPEARANCE_FIGURE.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
                if (fieldName.equals(AssessUploadEnum.BUILDING_FLOOR_PLAN.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicBuilding.getId(), AssessUploadEnum.BUILDING_FLOOR_PLAN.getKey(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.BUILDING_FLOOR_PLAN.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
            }

            if (basicUnit != null) {
                if (fieldName.equals(AssessUploadEnum.UNIT_APPEARANCE.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicUnit.getId(), AssessUploadEnum.UNIT_APPEARANCE.getKey(), FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.UNIT_APPEARANCE.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
            }

            if (basicHouse != null) {
                if (fieldName.equals(AssessUploadEnum.HOUSE_DECORATE.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicHouse.getId(), AssessUploadEnum.HOUSE_DECORATE.getKey(), FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.HOUSE_DECORATE.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
                if (fieldName.equals(AssessUploadEnum.HOUSE_IMG_PLAN.getKey())) {
                    dtoList = baseAttachmentService.getByField_tableId(basicHouse.getId(), AssessUploadEnum.HOUSE_IMG_PLAN.getKey(), FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                    removeGenerateFile(dtoList);
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        dtoList.forEach(o -> {
                            o.setReName(AssessUploadEnum.HOUSE_IMG_PLAN.getValue());
                            attachmentDtoList.add(o);
                        });
                    }
                }
            }

        }
        return attachmentDtoList;
    }

    //删除自己生成附件
    public void removeGenerateFile(List<SysAttachmentDto> attachmentDtoList) {
        if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
            Iterator<SysAttachmentDto> it = attachmentDtoList.iterator();
            while (it.hasNext()) {
                SysAttachmentDto item = it.next();
                if (StringUtil.isNotEmpty(item.getReName())) {
                    it.remove();
                }
            }
        }
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
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            for (DeclareRecord declareRecord : declareRecordList) {
                List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
                map.put(declareRecord.getId(), attachmentDtoList);
            }
        }
        return map;
    }

    /**
     * 关联的土地证附件
     *
     * @param declareRecordId
     * @return
     */
    public List<String> getLandFilePathList(Integer declareRecordId) throws Exception {
        List<String> paths = Lists.newArrayList();
        //关联的土地证附件
        Integer landCertId = getLandCertId(declareRecordId);
        if (landCertId != null) {
            List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(landCertId, null, "tb_declare_realty_land_cert");
            if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                for (SysAttachmentDto item : attachmentDtoList) {
                    String path = baseAttachmentService.downloadFtpFileToLocal(item.getId());
                    if (FileUtils.checkImgSuffix(path)) {
                        paths.add(path);
                    }
                }
            }
        }
        return paths;
    }


    /**
     * 获取权属证明文件
     *
     * @param declareRecordId
     * @return
     */
    public List<SysAttachmentDto> getOwnershipCertFileAll(Integer declareRecordId) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareRecordId);
        List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(declareRecord.getDataTableId(), null, declareRecord.getDataTableName());
        return attachmentDtoList;
    }


    /**
     * 获取关联土地证id
     *
     * @param declareRecordId
     * @return
     */
    public Integer getLandCertId(Integer declareRecordId) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareRecordId);
        DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = new DeclareBuildEngineeringAndEquipmentCenter();
        equipmentCenter.setType("DeclareRealtyHouseCert");
        equipmentCenter.setHouseId(declareRecord.getDataTableId());
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(equipmentCenter);
        if (CollectionUtils.isNotEmpty(centerList)) {
            return centerList.get(0).getLandId();
        }
        return null;
    }

    /**
     * 获取关联土地证附件
     *
     * @param tableId
     * @return
     */
    public List<SysAttachmentDto> getLandFileAll(Integer tableId) {
        List<SysAttachmentDto> attachmentDtoList = baseAttachmentService.getByField_tableId(tableId, null, "tb_declare_realty_land_cert");
        return attachmentDtoList;
    }

    /**
     * 移除权属证明复印件图片
     *
     * @param id
     */
    public void removeOwnershipCertFile(Integer id) {
        baseAttachmentService.deleteAttachment(id);
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
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            for (DeclareRecord declareRecord : declareRecordList) {
                List<SysAttachmentDto> attachmentDtoList = getInventoryContentFile(declareRecord);
                map.put(declareRecord.getId(), attachmentDtoList);
            }
        }
        return map;
    }

    /**
     * 获取权证证书清查地址不一致附件
     *
     * @param declareRecordId
     * @return
     */
    public List<SysAttachmentDto> getAddressFileListByDeclareRecordId(Integer declareRecordId) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareRecordId);
        List<SysAttachmentDto> attachmentDtoList = getInventoryContentFile(declareRecord);
        return attachmentDtoList;
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
     * @param schemeJudgeObjectId
     * @return
     */
    public List<SchemeReportFileCustom> getReportFileCustomList(Integer schemeJudgeObjectId) {
        SchemeReportFileCustom where = new SchemeReportFileCustom();
        where.setSchemeJudgeObjectId(schemeJudgeObjectId);
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
    public void saveToReportFileItem(SchemeReportFileItem schemeReportFileItem) throws Exception {
        if (schemeReportFileItem.getBisEnable() == null)
            schemeReportFileItem.setBisEnable(false);
        if (schemeReportFileItem.getId() != null && schemeReportFileItem.getId() > 0) {
            schemeReportFileItemDao.updateReportFileItem(schemeReportFileItem);
        } else {
            schemeReportFileItem.setCreator(commonService.thisUserAccount());
            schemeReportFileItem.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
            schemeReportFileItemDao.addReportFileItem(schemeReportFileItem);
            //更新附件信息
            SysAttachmentDto reportAttachment = new SysAttachmentDto();
            reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
            reportAttachment.setFieldsName("live_situation_select_supplement");
            reportAttachment.setTableId(0);
            reportAttachment.setCreater(commonService.thisUserAccount());
            SysAttachmentDto attachmentNew = new SysAttachmentDto();
            attachmentNew.setTableId(schemeReportFileItem.getId());
            baseAttachmentService.updateAttachementByExample(reportAttachment, attachmentNew);
        }

        if (schemeReportFileItem.getCertifyPart() != null && schemeReportFileItem.getCertifyPartCategory() != null) {
            //先删除原来对应查勘的附件
            this.deleteSurveyFile(schemeReportFileItem.getId());
            //关联到查勘
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeReportFileItem.getSchemeJudgeObjectId());
            BasicApply basicApply =basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            BaseDataDic part = baseDataDicService.getDataDicById(schemeReportFileItem.getCertifyPart());
            BaseDataDic category = baseDataDicService.getDataDicById(schemeReportFileItem.getCertifyPartCategory());
            SysAttachmentDto reportAttachment = new SysAttachmentDto();
            reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
            reportAttachment.setFieldsName("live_situation_select_supplement");
            reportAttachment.setCreater(commonService.thisUserAccount());
            reportAttachment.setTableId(schemeReportFileItem.getId());
            List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(reportAttachment);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                SysAttachmentDto reportAttachment2 = new SysAttachmentDto();
                for (SysAttachmentDto dto : attachmentList) {
                    BeanUtils.copyProperties(dto, reportAttachment2);
                    switch (part.getFieldName()) {
                        case "basicEstate":
                            reportAttachment2.setTableId(basicEstate.getId());
                            reportAttachment2.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                            break;
                        case "basicUnit":
                            reportAttachment2.setTableId(basicUnit.getId());
                            reportAttachment2.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                            break;
                        case "basicBuilding":
                            reportAttachment2.setTableId(basicBuilding.getId());
                            reportAttachment2.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                            break;
                        case "basicHouse":
                            reportAttachment2.setTableId(basicHouse.getId());
                            reportAttachment2.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                            break;
                    }
                    reportAttachment2.setFieldsName(category.getFieldName());
                    //reName存放关联的id
                    reportAttachment2.setReName(String.valueOf(schemeReportFileItem.getId()));
                    reportAttachment2.setId(null);
                    reportAttachment2.setProjectId(schemeJudgeObject.getProjectId());
                    baseAttachmentService.addAttachment(reportAttachment2);
                }
            }
        }

    }

    //删除原来对应查勘的附件
    public void deleteSurveyFile(Integer id) {
        SysAttachmentDto old = new SysAttachmentDto();
        old.setReName(String.valueOf(id));
        List<SysAttachmentDto> oldAttachmentList = baseAttachmentService.getAttachmentList(old);
        if (CollectionUtils.isNotEmpty(oldAttachmentList)) {
            for (SysAttachmentDto oldItem : oldAttachmentList) {
                baseAttachmentService.deleteAttachment(oldItem.getId());
            }
        }
    }

    //选择实况图片
    public void selectLiveSituation(Integer attachmentId, Integer schemeJudgeObjectId, String fileName) {
        SchemeReportFileItem schemeReportFileItem = new SchemeReportFileItem();
        schemeReportFileItem.setSchemeJudgeObjectId(schemeJudgeObjectId);
        schemeReportFileItem.setFileName(fileName);
        schemeReportFileItem.setCreator(commonService.thisUserAccount());
        schemeReportFileItem.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
        schemeReportFileItemDao.addReportFileItem(schemeReportFileItem);
        //更新附件信息
        SysAttachmentDto sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(attachmentId);
        SysAttachmentDto copyAttachment = new SysAttachmentDto();
        BeanUtils.copyProperties(sysAttachmentDto, copyAttachment);
        copyAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        copyAttachment.setFieldsName("live_situation_select_supplement");
        copyAttachment.setTableId(schemeReportFileItem.getId());
        copyAttachment.setCreater(commonService.thisUserAccount());
        baseAttachmentService.addAttachment(copyAttachment);

    }

    public SchemeReportFileItemVo getSchemeReportFileItemVo(SchemeReportFileItem schemeReportFileItem) {
        SchemeReportFileItemVo vo = new SchemeReportFileItemVo();
        BeanUtils.copyProperties(schemeReportFileItem, vo);
        //获取附件
        SysAttachmentDto reportAttachment = new SysAttachmentDto();
        reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        reportAttachment.setFieldsName("live_situation_select_supplement");
        reportAttachment.setTableId(schemeReportFileItem.getId());

        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(reportAttachment);
        if (!org.springframework.util.CollectionUtils.isEmpty(attachmentList)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentList) {
                stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
            }
            vo.setFileViewName(stringBuilder.toString());
        } else {
            vo.setFileViewName("");
        }
        vo.setCertifyPartName(schemeReportFileItem.getCertifyPart() != null ? baseDataDicService.getNameById(schemeReportFileItem.getCertifyPart()) : "");
        vo.setCertifyPartCategoryName(schemeReportFileItem.getCertifyPartCategory() != null ? baseDataDicService.getNameById(schemeReportFileItem.getCertifyPartCategory()) : "");
        if (schemeReportFileItem.getBisEnable() != null && schemeReportFileItem.getBisEnable()) {
            vo.setBisEnableName("是");
        } else {
            vo.setBisEnableName("否");
        }
        return vo;
    }


    public List<SysAttachmentDto> getAttachmentListBySchemeReportFile(SchemeReportFileItem schemeReportFileItem) {
        SysAttachmentDto reportAttachment = new SysAttachmentDto();
        reportAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        reportAttachment.setFieldsName("live_situation_select_supplement");
        reportAttachment.setTableId(schemeReportFileItem.getId());
        return baseAttachmentService.getAttachmentList(reportAttachment);
    }

    public void affirmPictureTemplate(Integer masterId, Integer schemeJudgeObjectId) {
        //获取模板
        DataLocaleSurveyPicture dataLocaleSurveyPicture = new DataLocaleSurveyPicture();
        dataLocaleSurveyPicture.setMasterId(masterId);
        List<DataLocaleSurveyPictureVo> localeSurveyPictures = dataLocaleSurveyPictureService.getDataLocaleSurveyPictureVos(dataLocaleSurveyPicture);
        if (CollectionUtils.isNotEmpty(localeSurveyPictures)) {
            for (DataLocaleSurveyPictureVo surveyPictureVo : localeSurveyPictures) {
                SchemeReportFileItem schemeReportFileItem = new SchemeReportFileItem();
                BeanUtils.copyProperties(surveyPictureVo, schemeReportFileItem);
                schemeReportFileItem.setSchemeJudgeObjectId(schemeJudgeObjectId);
                schemeReportFileItem.setType(AssessUploadEnum.JUDGE_OBJECT_LIVE_SITUATION.getKey());
                schemeReportFileItemDao.addReportFileItem(schemeReportFileItem);
            }
        }

    }

    //选择指定位置查勘图片
    public void selectCorrespondingSitePic(Integer attachmentId, Integer reportFileItemId) {
        SchemeReportFileItem reportFileItemById = schemeReportFileItemDao.getReportFileItemById(reportFileItemId);
        //更新附件信息
        SysAttachmentDto sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(attachmentId);
        SysAttachmentDto copyAttachment = new SysAttachmentDto();
        BeanUtils.copyProperties(sysAttachmentDto, copyAttachment);
        copyAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        copyAttachment.setFieldsName("live_situation_select_supplement");
        if (reportFileItemById != null) {
            copyAttachment.setTableId(reportFileItemById.getId());
        } else {
            copyAttachment.setTableId(0);
        }
        copyAttachment.setCreater(commonService.thisUserAccount());
        baseAttachmentService.addAttachment(copyAttachment);

    }

    //保存到模板
    public void saveToTemplate(String name, Integer schemeJudgeObjectId) {
        if(schemeJudgeObjectId==null) return;
        DataLocaleSurvey dataLocaleSurvey = new DataLocaleSurvey();
        dataLocaleSurvey.setName(name);
        dataLocaleSurveyService.saveAndUpdateDataLocaleSurvey(dataLocaleSurvey);
        SchemeReportFileItem schemeReportFileItem = new SchemeReportFileItem();
        schemeReportFileItem.setSchemeJudgeObjectId(schemeJudgeObjectId);
        List<SchemeReportFileItem> reportFileItemList = schemeReportFileItemDao.getReportFileItemList(schemeReportFileItem);
        if (CollectionUtils.isNotEmpty(reportFileItemList)) {
            for (SchemeReportFileItem fileItem : reportFileItemList) {
                DataLocaleSurveyPicture dataLocaleSurveyPicture = new DataLocaleSurveyPicture();
                dataLocaleSurveyPicture.setMasterId(dataLocaleSurvey.getId());
                dataLocaleSurveyPicture.setFileName(fileItem.getFileName());
                dataLocaleSurveyPicture.setSorting(fileItem.getSorting());
                dataLocaleSurveyPicture.setCertifyPart(fileItem.getCertifyPart());
                dataLocaleSurveyPicture.setCertifyPartCategory(fileItem.getCertifyPartCategory());
                dataLocaleSurveyPictureService.saveAndUpdateDataLocaleSurveyPicture(dataLocaleSurveyPicture);
            }
        }

    }

    //预览实况照片
    public SysAttachmentDto generateLiveSituation(Integer schemeJudgeObjectId) {
        try {
            // word文档
            String localPath = generateCommonMethod.getLocalPath();
            Document document = new Document();
            DocumentBuilder builder = getDefaultDocumentBuilderSetting(document);
            List<SchemeReportFileItem> schemeReportFileItemList = schemeReportFileService.getReportListBySchemeJudgeObjectId(schemeJudgeObjectId);
            if (CollectionUtils.isNotEmpty(schemeReportFileItemList)) {
                builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
                if (schemeReportFileItemList.size() > 1) {
                    generateCommonMethod.imageInsertToWrod3(schemeReportFileItemList, 3, builder);
                } else {
                    generateCommonMethod.imageInsertToWrod3(schemeReportFileItemList, 1, builder);
                }
            }
            document.save(localPath);
            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
            //生成png
            String imgLocalPath = generateCommonMethod.getLocalPath("实况照片报告", "png");
            File file = new File(imgLocalPath);
            FileOutputStream os = new FileOutputStream(file);
            options.setPageIndex(0);
            document.save(os, options);
            os.close();


            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(schemeJudgeObjectId);
            sysAttachmentDto.setFieldsName("实况照片报告");
            baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
            //上传形成附件
            return baseAttachmentService.importAjaxFileHandle(imgLocalPath, sysAttachmentDto);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 功能描述: 设置默认字体
     *
     * @param:
     * @return:
     * @author: huhao
     * @date: 2019/3/1 14:32
     */
    private DocumentBuilder getDefaultDocumentBuilderSetting(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        AsposeUtils.setDefaultFontSettings(builder);
        return builder;
    }

    //获取项目完整并没合并估价对象
    public BootstrapTableVo reloadSchemeJudgeObjectListByQuery(Integer projectId, String name, String certName,String seat) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectDao.reloadSchemeJudgeObjectListByQuery(projectId, name, certName, seat);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(schemeJudgeObjects) ? new ArrayList<SchemeJudgeObject>() : schemeJudgeObjects);
        return vo;
    }
}

package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.ad.api.dto.*;
import com.copower.pmcc.ad.api.enums.*;
import com.copower.pmcc.ad.api.provider.*;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.archives.ArchivesFileComprehensiveEnum;
import com.copower.pmcc.assess.common.enums.archives.ArchivesFileProfessionEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.AdPlaceFileItemDtoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicEstateSurveyRecordService;
import com.copower.pmcc.assess.service.document.DocumentSendService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGroupService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightItemService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.SysAppEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 档案
 */
@Service
public class ProjectArchivesDataService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private AdRpcBasePlaceFileService adRpcBasePlaceFileService;
    @Autowired
    private AdRpcPlaceFileGroupService adRpcPlaceFileGroupService;
    @Autowired
    private AdRpcPlaceFileVolumeNumberService adRpcPlaceFileVolumeNumberService;
    @Autowired
    private AdRpcPlaceFileItemService adRpcPlaceFileItemService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private DocumentSendService documentSendService;
    @Autowired
    private BasicEstateSurveyRecordService basicEstateSurveyRecordService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private AdRpcPlaceFileItemDetailService adRpcPlaceFileItemDetailService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private GenerateReportGroupService generateReportGroupService;
    @Autowired
    private SurveyAssetRightItemService surveyAssetRightItemService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public long getAdPlaceFileItemDetailDtoCount(Integer masterId) {
        return adRpcPlaceFileItemDetailService.getCount(masterId);
    }


    public BootstrapTableVo<AdPlaceFileItemDetailDto> getAdPlaceFileItemDetailDtoListByParam(AdPlaceFileItemDetailDto obj) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo<AdPlaceFileItemDetailDto> bootstrapTableVo = adRpcPlaceFileItemDetailService.getAdPlaceFileItemDetailDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<AdPlaceFileItemDetailDto> rows = bootstrapTableVo.getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            for (AdPlaceFileItemDetailDto vo : rows) {
                List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(vo.getId(), null, FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDetailDto.class));
                StringBuilder builder = new StringBuilder();
                if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
                    }
                    vo.setFileViewName(builder.toString());
                }
            }
        }
        bootstrapTableVo.setRows(rows);
        return bootstrapTableVo;
    }

    public void saveAdPlaceFileItemDetailDto(AdPlaceFileItemDetailDto obj) {
        if (obj == null) {
            return;
        }
        if (obj.getId() == null || obj.getId() == 0) {
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            Integer integer = adRpcPlaceFileItemDetailService.saveAdPlaceFileItemDetailDto(obj);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDetailDto.class), integer);
            obj.setId(integer);
        } else {
            adRpcPlaceFileItemDetailService.updateAdPlaceFileItemDetailDto(obj);
        }
    }

    public void deleteAdPlaceFileItemDetailDtoByIds(String id) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        adRpcPlaceFileItemDetailService.deleteAdPlaceFileItemDetailDtoByIds(ids);
    }


    public AdBasePlaceFileDto getAdBasePlaceFileByFieldName(String fieldName) {
        AdBasePlaceFileDto query = new AdBasePlaceFileDto();
        query.setAppKey(applicationConstant.getAppKey());
        query.setFieldName(fieldName);
        query.setBisEnable(true);
        List<AdBasePlaceFileDto> adBasePlaceFileDtoList = adRpcBasePlaceFileService.getAdBasePlaceFileListByObj(query);
        if (CollectionUtils.isNotEmpty(adBasePlaceFileDtoList)) {
            return adBasePlaceFileDtoList.get(0);
        }
        return null;
    }

    /**
     * 档案判断是否全部设置了卷号
     *
     * @param projectId
     * @return
     */
    public List<AdPlaceFileItemDto> getAdPlaceFileItemDtoValidList(Integer projectId) {
        AdPlaceFileItemDto itemDto = new AdPlaceFileItemDto();
        itemDto.setAppKey(applicationConstant.getAppKey());
        itemDto.setPublicProjectId(projectId);
        itemDto.setBisBinding(true);
        List<AdPlaceFileItemDto> list = adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByObj(itemDto);
        if (CollectionUtils.isNotEmpty(list)) {
            Iterator<AdPlaceFileItemDto> iterator = list.iterator();
            while (iterator.hasNext()) {
                AdPlaceFileItemDto fileItemDto = iterator.next();
                if (fileItemDto.getGroupId() != null && fileItemDto.getGroupId() != 0) {
                    iterator.remove();
                }
            }
        }
        return list;
    }

    /**
     * 档案目录填充
     *
     * @param basePlaceFileDto
     * @param name
     * @param list
     * @param comprehensive
     * @param adPlaceFileItemDtoList
     * @param saveList
     * @param atomicInteger
     */
    private void consumerAdPlaceFileItemDto(AdBasePlaceFileDto basePlaceFileDto, String name, List<AdPlaceFileItemDto> list, AdBasePlaceFileDto comprehensive, List<AdPlaceFileItemDto> adPlaceFileItemDtoList, List<AdPlaceFileItemDto> saveList, AtomicInteger atomicInteger, ProjectInfo projectInfo) {
        AdPlaceFileItemDto adPlaceFileItemDto = null;
        if (!StringUtils.contains(basePlaceFileDto.getName(), name)) {
            return;
        }
        List<AdPlaceFileItemDto> filter = null;
        if (CollectionUtils.isNotEmpty(list)) {
            filter = LangUtils.filter(list, obj -> {
                if (obj.getFileType() == null) {
                    return false;
                }
                if (obj.getFileCategory() == null) {
                    return false;
                }
                if (obj.getFileType().equals(comprehensive.getId()) && obj.getFileCategory().equals(basePlaceFileDto.getId())) {
                    return true;
                }
                return false;
            });
        }
        if (CollectionUtils.isNotEmpty(filter)) {
            adPlaceFileItemDtoList.addAll(filter);
            return;
        }
        adPlaceFileItemDto = new AdPlaceFileItemDto();
        adPlaceFileItemDto.setAppKey(applicationConstant.getAppKey());
        adPlaceFileItemDto.setModified(DateUtils.now());
        adPlaceFileItemDto.setCreated(DateUtils.now());
        adPlaceFileItemDto.setCreator(commonService.thisUserAccount());
        adPlaceFileItemDto.setFileType(comprehensive.getId());
        adPlaceFileItemDto.setFileTypeName(comprehensive.getName());
        adPlaceFileItemDto.setSorting(atomicInteger.get());
        adPlaceFileItemDto.setName(basePlaceFileDto.getName());
        adPlaceFileItemDto.setFileCategory(basePlaceFileDto.getId());
        adPlaceFileItemDto.setFileCategoryName(basePlaceFileDto.getName());
        adPlaceFileItemDto.setPublicProjectId(projectInfo.getId());
        adPlaceFileItemDto.setPublicProjectName(projectInfo.getProjectName());
        atomicInteger.incrementAndGet();
        saveList.add(adPlaceFileItemDto);
    }


    /**
     * 创建档案目录
     *
     * @param projectId
     * @return
     */
    public Map<String, List<AdPlaceFileItemDto>> createArchivesDirectory(Integer projectId) throws BusinessException {
        Map<String, List<AdPlaceFileItemDto>> map = new HashMap<>();
        AdPlaceFileItemDto query = new AdPlaceFileItemDto();
        query.setAppKey(applicationConstant.getAppKey());
        query.setPublicProjectId(projectId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        List<AdPlaceFileItemDto> list = adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByObj(query);
        AdBasePlaceFileDto comprehensive = getAdBasePlaceFileByFieldName(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_COMPREHENSIVE);
        AdBasePlaceFileDto profession = getAdBasePlaceFileByFieldName(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_PROFESSION);
        List<AdBasePlaceFileDto> comprehensive_list = getAdBasePlaceFileList(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_COMPREHENSIVE);
        List<AdBasePlaceFileDto> profession_list = getAdBasePlaceFileList(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_PROFESSION);
        if (CollectionUtils.isEmpty(comprehensive_list) || CollectionUtils.isEmpty(profession_list)) {
            throw new BusinessException("没有配置档案类型");
        }
        if (comprehensive == null || profession == null) {
            throw new BusinessException("没有配置档案类型");
        }
        List<AdPlaceFileItemDto> adPlaceFileItemDtoList = new ArrayList<>();
        List<AdPlaceFileItemDto> saveList = new ArrayList<>();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (AdBasePlaceFileDto basePlaceFileDto : comprehensive_list) {
            for (ArchivesFileComprehensiveEnum comprehensiveEnum : ArchivesFileComprehensiveEnum.values()) {
                consumerAdPlaceFileItemDto(basePlaceFileDto, comprehensiveEnum.getName(), list, comprehensive, adPlaceFileItemDtoList, saveList, atomicInteger, projectInfo);
            }
        }
        if (CollectionUtils.isNotEmpty(saveList)) {
            saveList = adRpcPlaceFileItemService.batchInsert(saveList);
        }
        adPlaceFileItemDtoList.addAll(saveList);
        map.put(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_COMPREHENSIVE, Lists.newArrayList(adPlaceFileItemDtoList));
        adPlaceFileItemDtoList.clear();
        saveList.clear();

        for (AdBasePlaceFileDto basePlaceFileDto : profession_list) {
            for (ArchivesFileProfessionEnum comprehensiveEnum : ArchivesFileProfessionEnum.values()) {
                consumerAdPlaceFileItemDto(basePlaceFileDto, comprehensiveEnum.getName(), list, profession, adPlaceFileItemDtoList, saveList, atomicInteger, projectInfo);
            }
        }
        if (CollectionUtils.isNotEmpty(saveList)) {
            saveList = adRpcPlaceFileItemService.batchInsert(saveList);
        }
        adPlaceFileItemDtoList.addAll(saveList);
        map.put(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_PROFESSION, Lists.newArrayList(adPlaceFileItemDtoList));
        adPlaceFileItemDtoList.clear();
        saveList.clear();


        return map;
    }

    /**
     * 自动生成档案
     *
     * @param projectId
     */
    public void autoCreateProjectFileCompleteNow(Integer projectId) throws BusinessException {
        if (projectId == null) {
            return;
        }
        //获取档案目录
        Map<String, List<AdPlaceFileItemDto>> map = createArchivesDirectory(projectId);
        if (map.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, List<AdPlaceFileItemDto>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<AdPlaceFileItemDto>> entry = iterator.next();
            List<AdPlaceFileItemDto> itemDtoList = entry.getValue();
            if (CollectionUtils.isEmpty(itemDtoList)) {
                continue;
            }
            switch (entry.getKey()) {
                //综合类
                case AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_COMPREHENSIVE: {
                    for (AdPlaceFileItemDto adPlaceFileItemDto : itemDtoList) {
                        //合同
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileComprehensiveEnum.contract.getName())) {
                            //合同  附件
                            continue;
                        }
                        //委托书
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileComprehensiveEnum.POWER_ATTORNEY.getName())) {
                            List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(projectId, "project_proxy", FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
                            if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
                                continue;
                            }
                            createAdPlaceFileItemDetailDto(sysAttachmentDtoList, adPlaceFileItemDto.getId());
                            continue;
                        }
                        //发文信函
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileComprehensiveEnum.LETTER.getName())) {
                            List<DocumentSend> documentSendList = documentSendService.getDocumentSendList(projectId, ProcessStatusEnum.FINISH.getValue());
                            if (CollectionUtils.isEmpty(documentSendList)) {
                                continue;
                            }
                            List<DocumentTemplate> documentTemplateList = documentTemplateService.getDocumentTemplateList("", baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_DISPATCH).getId());
                            if (CollectionUtils.isEmpty(documentTemplateList)) {
                                continue;
                            }
                            List<Integer> integerList = LangUtils.transform(documentTemplateList, obj -> obj.getId());
                            List<DocumentSend> filter = LangUtils.filter(documentSendList, obj -> {
                                if (obj.getContractType() != null) {
                                    return integerList.contains(obj.getContractType());
                                }
                                return false;
                            });
                            if (CollectionUtils.isEmpty(filter)) {
                                continue;
                            }
                            for (DocumentSend documentSend : filter) {
                                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(documentSend.getId(), null, FormatUtils.entityNameConvertToTableName(DocumentSend.class));
                                if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
                                    continue;
                                }
                                createAdPlaceFileItemDetailDto(sysAttachmentDtoList, adPlaceFileItemDto.getId());
                            }
                            continue;
                        }
                        //签收文件
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileComprehensiveEnum.SIGN_DOCUMENTS.getName())) {
                            //报告签收单
                            List<DocumentTemplate> signBill = null;
                            BaseDataDic signBillDataDic = null;
                            signBillDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_TEMPLATE_TYPE_REPORT_SIGNFOR);
                            if (signBillDataDic != null) {
                                signBill = documentTemplateService.getDocumentTemplateList("", signBillDataDic.getId());
                            }
                            if (CollectionUtils.isEmpty(signBill)) {
                                continue;
                            }
                            List<DocumentSend> documentSendList = documentSendService.getDocumentSendList(projectId, ProcessStatusEnum.FINISH.getValue());
                            if (CollectionUtils.isEmpty(documentSendList)) {
                                continue;
                            }
                            List<Integer> integerList = LangUtils.transform(signBill, obj -> obj.getId());
                            List<DocumentSend> filter = LangUtils.filter(documentSendList, obj -> {
                                if (obj.getContractType() != null) {
                                    return integerList.contains(obj.getContractType());
                                }
                                return false;
                            });
                            if (CollectionUtils.isEmpty(filter)) {
                                continue;
                            }
                            for (DocumentSend documentSend : filter) {
                                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(documentSend.getId(), null, FormatUtils.entityNameConvertToTableName(DocumentSend.class));
                                if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
                                    continue;
                                }
                                createAdPlaceFileItemDetailDto(sysAttachmentDtoList, adPlaceFileItemDto.getId());
                            }
                        }
                    }
                    break;
                }
                //专业类
                case AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_PROFESSION: {
                    for (AdPlaceFileItemDto adPlaceFileItemDto : itemDtoList) {
                        //房屋查勘表附件
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileProfessionEnum.SITE_SURVEY.getName())) {
                            ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsByQuery(projectId, AssessPhaseKeyConstant.SCENE_EXPLORE);
                            if (projectPlanDetails == null) {
                                continue;
                            }
                            List<Integer> houseIds = new ArrayList<>();
                            List<BasicApplyBatchDetail> filterBasicApplyBatchDetailList = LangUtils.filter(basicApplyBatchDetailService.getBasicApplyBatchDetailByProjectId(projectId), o -> o.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey()));
                            if (CollectionUtils.isEmpty(filterBasicApplyBatchDetailList)) {
                                continue;
                            }
                            List<BasicApplyBatchDetail> filter = LangUtils.filter(filterBasicApplyBatchDetailList, obj -> obj.getPlanDetailsId().equals(projectPlanDetails.getId()));
                            String tableName = FormatUtils.entityNameConvertToTableName(BasicHouse.class);
                            for (BasicApplyBatchDetail batchDetail : filter) {
                                if (batchDetail.getTableName().equals(tableName)) {
                                    houseIds.add(batchDetail.getTableId());
                                }
                            }
                            if (CollectionUtils.isEmpty(houseIds)) {
                                continue;
                            }
                            List<Integer> integerList = new ArrayList<>();
                            for (Integer houseId : houseIds) {
                                BasicEstateSurveyRecord recordByHouseId = basicEstateSurveyRecordService.getEstateSurveyRecordByHouseId(houseId);
                                if (recordByHouseId == null) {
                                    continue;
                                }
                                integerList.add(recordByHouseId.getId());
                            }
                            if (CollectionUtils.isEmpty(integerList)) {
                                continue;
                            }
                            SysAttachmentDto query = new SysAttachmentDto();
                            query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateSurveyRecord.class));
                            List<SysAttachmentDto> serviceAttachmentList = baseAttachmentService.getAttachmentList(integerList, query);
                            if (CollectionUtils.isEmpty(serviceAttachmentList)) {
                                continue;
                            }
                            createAdPlaceFileItemDetailDto(serviceAttachmentList, adPlaceFileItemDto.getId());
                            continue;
                        }

                        //权证
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileProfessionEnum.Warrant.getName())) {
                            List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
                            if (CollectionUtils.isEmpty(declareRecordList)) {
                                continue;
                            }
                            Map<String, List<Integer>> stringListMap = new HashMap<>();
                            for (DeclareRecord declareRecord : declareRecordList) {
                                if (stringListMap.containsKey(declareRecord.getDataTableName())) {
                                    stringListMap.get(declareRecord.getDataTableName()).add(declareRecord.getDataTableId());
                                } else {
                                    stringListMap.put(declareRecord.getDataTableName(), Lists.newArrayList(declareRecord.getDataTableId()));
                                }
                            }
                            if (stringListMap.isEmpty()) {
                                continue;
                            }
                            Iterator<Map.Entry<String, List<Integer>>> entryIterator = stringListMap.entrySet().iterator();
                            while (entryIterator.hasNext()) {
                                Map.Entry<String, List<Integer>> stringListEntry = entryIterator.next();
                                SysAttachmentDto query = new SysAttachmentDto();
                                query.setTableName(stringListEntry.getKey());
                                List<SysAttachmentDto> serviceAttachmentList = baseAttachmentService.getAttachmentList(stringListEntry.getValue(), query);
                                if (CollectionUtils.isEmpty(serviceAttachmentList)) {
                                    continue;
                                }
                                createAdPlaceFileItemDetailDto(serviceAttachmentList, adPlaceFileItemDto.getId());
                            }
                            continue;
                        }
                        //经济指标
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileProfessionEnum.ECONOMIC_INDICATORS.getName())) {
                            continue;
                        }


                        //他权
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileProfessionEnum.OTHER_RIGHT.getName())) {
                            SurveyAssetRightItem itemQuery = new SurveyAssetRightItem();
                            itemQuery.setProjectId(projectId);
                            List<SurveyAssetRightItem> surveyAssetRightItemList = surveyAssetRightItemService.getSurveyAssetRightItemListByExample(itemQuery);
                            if (CollectionUtils.isEmpty(surveyAssetRightItemList)) {
                                continue;
                            }
                            List<Integer> integerList = LangUtils.transform(surveyAssetRightItemList, obj -> obj.getId());
                            SysAttachmentDto query = new SysAttachmentDto();
                            query.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRightItem.class));
                            List<SysAttachmentDto> serviceAttachmentList = baseAttachmentService.getAttachmentList(integerList, query);
                            if (CollectionUtils.isEmpty(serviceAttachmentList)) {
                                continue;
                            }
                            createAdPlaceFileItemDetailDto(serviceAttachmentList, adPlaceFileItemDto.getId());
                            continue;
                        }


                        //案例调查
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileProfessionEnum.CASE_INVESTIGATION.getName())) {
                            ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsByQuery(projectId, AssessPhaseKeyConstant.CASE_STUDY_EXTEND);
                            if (projectPlanDetails == null) {
                                continue;
                            }
                            List<Integer> houseIds = new ArrayList<>();
                            List<BasicApplyBatchDetail> filterBasicApplyBatchDetailList = LangUtils.filter(basicApplyBatchDetailService.getBasicApplyBatchDetailByProjectId(projectId), o -> o.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey()));
                            if (CollectionUtils.isEmpty(filterBasicApplyBatchDetailList)) {
                                continue;
                            }
                            List<BasicApplyBatchDetail> filter = LangUtils.filter(filterBasicApplyBatchDetailList, obj -> obj.getPlanDetailsId().equals(projectPlanDetails.getId()));
                            String tableName = FormatUtils.entityNameConvertToTableName(BasicHouse.class);
                            for (BasicApplyBatchDetail batchDetail : filter) {
                                if (batchDetail.getTableName().equals(tableName)) {
                                    houseIds.add(batchDetail.getTableId());
                                }
                            }
                            if (CollectionUtils.isEmpty(houseIds)) {
                                continue;
                            }
                            List<Integer> integerList = new ArrayList<>();
                            for (Integer houseId : houseIds) {
                                BasicEstateSurveyRecord recordByHouseId = basicEstateSurveyRecordService.getEstateSurveyRecordByHouseId(houseId);
                                if (recordByHouseId == null) {
                                    continue;
                                }
                                integerList.add(recordByHouseId.getId());
                            }
                            if (CollectionUtils.isEmpty(integerList)) {
                                continue;
                            }
                            SysAttachmentDto query = new SysAttachmentDto();
                            query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateSurveyRecord.class));
                            List<SysAttachmentDto> serviceAttachmentList = baseAttachmentService.getAttachmentList(integerList, query);
                            if (CollectionUtils.isEmpty(serviceAttachmentList)) {
                                continue;
                            }
                            createAdPlaceFileItemDetailDto(serviceAttachmentList, adPlaceFileItemDto.getId());
                            continue;
                        }


                        //评估报告
                        if (StringUtils.contains(adPlaceFileItemDto.getName(), ArchivesFileProfessionEnum.EVALUATION_REPORT.getName())) {
                            GenerateReportGroup group = new GenerateReportGroup();
                            group.setProjectId(projectId);
                            List<GenerateReportGroup> generateReportGroupList = generateReportGroupService.getGenerateReportGroupListByQuery(group);
                            if (CollectionUtils.isEmpty(generateReportGroupList)) {
                                continue;
                            }
                            List<Integer> integerList = LangUtils.transform(generateReportGroupList, obj -> obj.getId());
                            SysAttachmentDto query = new SysAttachmentDto();
                            query.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class));
                            List<SysAttachmentDto> serviceAttachmentList = baseAttachmentService.getAttachmentList(integerList, query);
                            if (CollectionUtils.isEmpty(serviceAttachmentList)) {
                                continue;
                            }
                            createAdPlaceFileItemDetailDto(serviceAttachmentList, adPlaceFileItemDto.getId());
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    private ProjectPlanDetails getProjectPlanDetailsByQuery(Integer projectId, String key) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectPhase otherRightPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(key, projectInfo.getProjectCategoryId());
        ProjectPlanDetails query = new ProjectPlanDetails();
        query.setProjectId(projectId);
        query.setProjectPhaseId(otherRightPhase.getId());
        List<ProjectPlanDetails> detailsList = projectPlanDetailsService.getProjectDetails(query);
        if (CollectionUtils.isNotEmpty(detailsList)) {
            return detailsList.get(0);
        }
        return null;
    }

    /**
     * 自动生成档案记录
     *
     * @param sysAttachmentDtoList
     * @param masterId
     */
    private void createAdPlaceFileItemDetailDto(List<SysAttachmentDto> sysAttachmentDtoList, Integer masterId) {
        final String project_proxy = "project_proxy";
        AdPlaceFileItemDetailDto detailDto = null;
        Date now = DateUtils.now();
        String userAccount = commonService.thisUserAccount();
        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
            detailDto = new AdPlaceFileItemDetailDto();
            detailDto.setMasterId(masterId);
            detailDto.setCreated(now);
            detailDto.setModified(now);
            detailDto.setCreator(userAccount);
            detailDto.setName(FilenameUtils.getBaseName(sysAttachmentDto.getFileName()));
            Integer integer = adRpcPlaceFileItemDetailService.saveAdPlaceFileItemDetailDto(detailDto);
            SysAttachmentDto attachmentDto = new SysAttachmentDto();
            attachmentDto.setTableId(integer);
            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDetailDto.class));
            attachmentDto.setFieldsName(project_proxy);
            try {
                baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public SysSymbolListDto getSymbolDto(Integer ruleId) throws Exception {
        int year = DateUtils.getYear(DateUtils.today());
        SysSymbolListDto sysSymbol = erpRpcToolsService.getSysSymbol(applicationConstant.getAppKey(), ruleId, year);
        if (sysSymbol != null)
            erpRpcToolsService.updateSymbolExamine(applicationConstant.getAppKey(), sysSymbol.getSymbol());
        return sysSymbol;
    }

    /**
     * 项目归档-行政节点
     *
     * @return
     */
    public String getBoxName() {
        return baseParameterService.getParameterValues(BaseParameterEnum.PROJECT_ARCHIVES_ADMINISTRATIVE_NODE_KEY.getParameterKey());
    }


    /**
     * 卷号规则
     *
     * @return
     */
    public List<SysSymbolRuleDto> getSysSymbolRuleDtoList() {
        List<SysSymbolRuleDto> sysSymbolRuleDtoList = erpRpcToolsService.getSysSymbolRuleDto(SysAppEnum.AD.getValue());
        List<SysSymbolGroupDto> symbolGroupDtoList = erpRpcToolsService.getSysSymbolGroupDto(SysAppEnum.AD.getValue(), AdArchivesRoleGroupEnum.FILE_GROUP_ENUM.getKey());
        List<Integer> groupList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(symbolGroupDtoList)) {
            groupList = LangUtils.transform(symbolGroupDtoList, obj -> obj.getId());
        }
        if (CollectionUtils.isNotEmpty(sysSymbolRuleDtoList) && CollectionUtils.isNotEmpty(groupList)) {
            Iterator<SysSymbolRuleDto> iterator = sysSymbolRuleDtoList.iterator();
            while (iterator.hasNext()) {
                SysSymbolRuleDto ruleDto = iterator.next();
                if (groupList.contains(ruleDto.getGroupId())) {
                    continue;
                }
                //非约定组全部删除
                iterator.remove();
            }
        }
        return sysSymbolRuleDtoList;
    }


    public List<AdBasePlaceFileDto> getAdBasePlaceFileList(String fieldName) {
        return adRpcBasePlaceFileService.getAdBasePlaceFileList(applicationConstant.getAppKey(), fieldName);
    }

    public AdPlaceFileItemDto getAdPlaceFileItemDtoById(Integer id) {
        return adRpcPlaceFileItemService.getAdPlaceFileItemDtoById(id);
    }

    public boolean deleteAdPlaceFileItemDtoByIds(String id) {
        return adRpcPlaceFileItemService.deleteAdPlaceFileItemDtoByIds(FormatUtils.transformString2Integer(id));
    }

    public void saveAdPlaceFileItemDto(AdPlaceFileItemDto obj) {
        if (obj == null) {
            return;
        }
        if (obj.getId() == null || obj.getId() == 0) {
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            if (StringUtils.isBlank(obj.getAppKey())) {
                obj.setAppKey(applicationConstant.getAppKey());
            }
            Integer integer = adRpcPlaceFileItemService.saveAdPlaceFileItemDto(obj);
            obj.setId(integer);
        } else {
            adRpcPlaceFileItemService.updateAdPlaceFileItemDto(obj);
        }
    }

    public void saveAdPlaceFileGroupDto(AdPlaceFileGroupDto obj) {
        if (obj == null) {
            return;
        }
        if (obj.getId() == null || obj.getId() == 0) {
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            Integer integer = adRpcPlaceFileGroupService.saveAdPlaceFileGroupDto(obj);
            obj.setId(integer);
        } else {
            adRpcPlaceFileGroupService.updateAdPlaceFileGroupDto(obj);
        }
    }

    public boolean deleteAdPlaceFileGroupDtoByIds(String id) {
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        return adRpcPlaceFileGroupService.deleteAdPlaceFileGroupDtoByIds(ids);
    }

    public void saveAdPlaceFileVolumeNumberDto(AdPlaceFileVolumeNumberDto obj) {
        if (obj == null) {
            return;
        }
        if (obj.getId() == null || obj.getId() == 0) {
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            if (StringUtils.isBlank(obj.getAppKey())) {
                obj.setAppKey(applicationConstant.getAppKey());
            }
            Integer integer = adRpcPlaceFileVolumeNumberService.saveAdPlaceFileVolumeNumberDto(obj);
            obj.setId(integer);
        } else {
            adRpcPlaceFileVolumeNumberService.updateAdPlaceFileVolumeNumberDto(obj);
        }
    }

    public boolean deleteAdPlaceFileVolumeNumberDtoByIds(String id) {
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        return adRpcPlaceFileVolumeNumberService.deleteAdPlaceFileVolumeNumberDtoByIds(ids);
    }

    /**
     * 卷号
     *
     * @param obj
     * @return
     */
    public BootstrapTableVo<AdPlaceFileVolumeNumberDto> getAdPlaceFileVolumeNumberDtoListByParam(AdPlaceFileVolumeNumberDto obj, String activityReName) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        if (StringUtils.isBlank(obj.getAppKey())) {
            obj.setAppKey(applicationConstant.getAppKey());
        }
        if (StringUtils.isBlank(obj.getCreator())) {
            obj.setCreator(commonService.thisUserAccount());
        }
        if (StringUtils.isNotBlank(activityReName)) {
            String boxName = getBoxName();
            if (boxName.equals(activityReName)) {
                //行政的时候可以查看所以卷号
                obj.setCreator(null);
            }
        }
        return adRpcPlaceFileVolumeNumberService.getAdPlaceFileVolumeNumberDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
    }

    /**
     * 档案
     *
     * @return
     */
    public BootstrapTableVo getAdPlaceFileItemDtoListByParam(Integer publicProjectId, Integer groupId, String publicProjectName, String name, Integer fileType, Integer fileCategory, Boolean bisBinding) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        String appKey = applicationConstant.getAppKey();
        BootstrapTableVo<AdPlaceFileItemDto> bootstrapTableVo = adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByParam(appKey, publicProjectName, publicProjectId, groupId, name, fileType, fileCategory, bisBinding, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<AdPlaceFileItemDtoVo> voList = LangUtils.transform(bootstrapTableVo.getRows(), oo -> getAdPlaceFileItemDtoVo(oo));
        BootstrapTableVo tableVo = new BootstrapTableVo();
        tableVo.setTotal(bootstrapTableVo.getTotal());
        tableVo.setRows(voList);
        return tableVo;
    }

    public BootstrapTableVo<AdPlaceFileGroupDto> getAdPlaceFileGroupDtoListByParam(AdPlaceFileGroupDto obj) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        return adRpcPlaceFileGroupService.getAdPlaceFileGroupDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
    }

    public AdPlaceFileGroupDto getAdPlaceFileGroupDtoById(Integer id) {
        return adRpcPlaceFileGroupService.getAdPlaceFileGroupDtoById(id);
    }

    public AdPlaceFileItemDtoVo getAdPlaceFileItemDtoVo(AdPlaceFileItemDto obj) {
        AdPlaceFileItemDtoVo vo = new AdPlaceFileItemDtoVo();
        BeanUtils.copyProperties(obj, vo);
        if (obj.getGroupId() != null) {
            AdPlaceFileGroupDto fileGroupDto = getAdPlaceFileGroupDtoById(obj.getGroupId());
            if (fileGroupDto != null) {
                vo.setNumber(fileGroupDto.getNumber());
                vo.setSaveLocation(fileGroupDto.getSaveLocation());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(obj.getId(), null, FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDto.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        vo.setCount(getAdPlaceFileItemDetailDtoCount(vo.getId()));
        return vo;
    }

    public List<AdBasePlaceFileDto> getAdBasePlaceFileListByPid(Integer pid) {
        return adRpcBasePlaceFileService.getAdBasePlaceFileListByPid(pid);
    }

    /**
     * 解除卷号绑定,这里不会删除卷号记录
     *
     * @param id
     */
    public void lockOpenDataDicAdPlaceFileItemDtoById(String id) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isEmpty(integerList)) {
            return;
        }
        List<AdPlaceFileItemDto> list = adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByIds(integerList);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String appKey = applicationConstant.getAppKey();
        Integer publicProjectId = list.get(0).getPublicProjectId();
        for (AdPlaceFileItemDto adPlaceFileItemDto : list) {
            Integer groupId = adPlaceFileItemDto.getGroupId();
            adPlaceFileItemDto.setGroupId(null);
            adRpcPlaceFileItemService.updateQuietlyAdPlaceFileItemDto(adPlaceFileItemDto);
            long count = adRpcPlaceFileItemService.getCount(groupId, publicProjectId, appKey);
            if (count == 0) {
                AdPlaceFileGroupDto fileGroupDto = adRpcPlaceFileGroupService.getAdPlaceFileGroupDtoById(groupId);
                //设为废弃
                fileGroupDto.setBisDisuse(true);
                adRpcPlaceFileGroupService.updateAdPlaceFileGroupDto(fileGroupDto);
            }
        }
    }
}

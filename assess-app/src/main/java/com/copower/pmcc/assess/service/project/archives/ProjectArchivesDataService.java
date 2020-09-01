package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.ad.api.dto.*;
import com.copower.pmcc.ad.api.enums.*;
import com.copower.pmcc.ad.api.provider.*;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.AdPlaceFileItemDtoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicEstateSurveyRecordService;
import com.copower.pmcc.assess.service.document.DocumentSendService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.SysAppEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
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
    private Logger logger = LoggerFactory.getLogger(getClass());

    public long getAdPlaceFileItemDetailDtoCount(Integer masterId ){
        return adRpcPlaceFileItemDetailService.getCount(masterId);
    }


    public BootstrapTableVo<AdPlaceFileItemDetailDto> getAdPlaceFileItemDetailDtoListByParam(AdPlaceFileItemDetailDto obj){
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo<AdPlaceFileItemDetailDto> bootstrapTableVo = adRpcPlaceFileItemDetailService.getAdPlaceFileItemDetailDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<AdPlaceFileItemDetailDto> rows = bootstrapTableVo.getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            for (AdPlaceFileItemDetailDto vo:rows) {
                List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(obj.getId(), null, FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDetailDto.class));
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

    public void saveAdPlaceFileItemDetailDto(AdPlaceFileItemDetailDto obj){
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

    public void deleteAdPlaceFileItemDetailDtoByIds(String id){
        if (StringUtils.isBlank(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id) ;
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        adRpcPlaceFileItemDetailService.deleteAdPlaceFileItemDetailDtoByIds(ids) ;
    }




    public AdBasePlaceFileDto getAdBasePlaceFileByFieldName( String fieldName){
        AdBasePlaceFileDto query = new AdBasePlaceFileDto();
        query.setAppKey(applicationConstant.getAppKey());
        query.setFieldName(fieldName);
        query.setBisEnable(true);
        List<AdBasePlaceFileDto> adBasePlaceFileDtoList = adRpcBasePlaceFileService.getAdBasePlaceFileListByObj(query);
        if (CollectionUtils.isNotEmpty(adBasePlaceFileDtoList)) {
            return adBasePlaceFileDtoList.get(0) ;
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
     * 自动生成档案
     *
     * @param projectId
     */
    public void autoCreateProjectFileCompleteNow(Integer projectId) {
        if (projectId == null){
            return;
        }
        Date now = DateUtils.now();
        AdBasePlaceFileDto operating = getAdBasePlaceFileByFieldName(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_OPERATING) ;
        AdBasePlaceFileDto attorney = getAdBasePlaceFileByFieldName(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_OPERATING_ATTORNEY) ;
        AdBasePlaceFileDto survey = getAdBasePlaceFileByFieldName(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_OPERATING_SURVEY) ;
        AdBasePlaceFileDto sendDoc = getAdBasePlaceFileByFieldName(AssessDataDicKeyConstant.AD_PLACE_FILE_MARK_OPERATING_SEND_DOC) ;
        //委托书 id
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(projectId, "project_proxy", FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        int count = 1;
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                createAdPlaceFileItemDto(sysAttachmentDto, projectId, now, count ,operating ,attorney);
                count++;
            }
        }
        //必须是流程结束后才能获取 发文
        List<DocumentSend> documentSendList = documentSendService.getDocumentSendList(projectId, ProcessStatusEnum.FINISH.getValue());
        if (CollectionUtils.isNotEmpty(documentSendList)) {
            for (DocumentSend documentSend : documentSendList) {
                sysAttachmentDtoList = baseAttachmentService.getByField_tableId(documentSend.getId(), null, FormatUtils.entityNameConvertToTableName(DocumentSend.class));
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        createAdPlaceFileItemDto(sysAttachmentDto, projectId, now, count ,operating ,sendDoc);
                        count++;
                    }
                }
            }
        }

        //房屋查勘表附件
        List<Integer> houseIds = new ArrayList<>();
        List<BasicApplyBatchDetail> filterBasicApplyBatchDetailList = LangUtils.filter(basicApplyBatchDetailService.getBasicApplyBatchDetailByProjectId(projectId), o -> o.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey()));
        if (CollectionUtils.isNotEmpty(filterBasicApplyBatchDetailList)) {
            String tableName = FormatUtils.entityNameConvertToTableName(BasicHouse.class);
            for (BasicApplyBatchDetail batchDetail : filterBasicApplyBatchDetailList) {
                if (batchDetail.getTableName().equals(tableName)) {
                    houseIds.add(batchDetail.getTableId());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(houseIds)) {
            List<Integer> integerList = new ArrayList<>();
            for (Integer houseId : houseIds) {
                BasicEstateSurveyRecord recordByHouseId = basicEstateSurveyRecordService.getEstateSurveyRecordByHouseId(houseId);
                if (recordByHouseId == null) {
                    continue;
                }
                integerList.add(recordByHouseId.getId());
            }
            if (CollectionUtils.isNotEmpty(integerList)) {
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateSurveyRecord.class));
                List<SysAttachmentDto> serviceAttachmentList = baseAttachmentService.getAttachmentList(integerList, query);
                if (CollectionUtils.isNotEmpty(serviceAttachmentList)) {
                    for (SysAttachmentDto sysAttachmentDto : serviceAttachmentList) {
                        createAdPlaceFileItemDto(sysAttachmentDto, projectId, now, count ,operating ,survey);
                        count++;
                    }
                }
            }
        }
    }

    private void createAdPlaceFileItemDto(SysAttachmentDto sysAttachmentDto, Integer projectId, Date now, Integer count ,AdBasePlaceFileDto operating,AdBasePlaceFileDto basePlaceFileDto) {
        final String project_proxy = "project_proxy";
        AdPlaceFileItemDto itemDto = new AdPlaceFileItemDto();
        itemDto.setName(FilenameUtils.getBaseName(sysAttachmentDto.getFileName()));
        itemDto.setPublicProjectId(projectId);
        itemDto.setSorting(count);
        itemDto.setCreated(now);
        itemDto.setModified(now);
        itemDto.setAppKey(applicationConstant.getAppKey());
        itemDto.setCreator(commonService.thisUserAccount());
        if (operating != null) {
            itemDto.setFileType(operating.getId());
            itemDto.setFileTypeName(operating.getName());
        }
        if (basePlaceFileDto != null) {
            itemDto.setFileCategory(basePlaceFileDto.getId());
            itemDto.setFileCategoryName(basePlaceFileDto.getName());
        }
        Integer integer = adRpcPlaceFileItemService.saveAdPlaceFileItemDto(itemDto);
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(integer);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDto.class));
        attachmentDto.setFieldsName(project_proxy);
        try {
            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
    public BootstrapTableVo<AdPlaceFileVolumeNumberDto> getAdPlaceFileVolumeNumberDtoListByParam(AdPlaceFileVolumeNumberDto obj) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        if (StringUtils.isBlank(obj.getAppKey())) {
            obj.setAppKey(applicationConstant.getAppKey());
        }
        if (StringUtils.isBlank(obj.getCreator())) {
            obj.setCreator(commonService.thisUserAccount());
        }
        return adRpcPlaceFileVolumeNumberService.getAdPlaceFileVolumeNumberDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
    }

    /**
     * 档案
     *
     * @return
     */
    public BootstrapTableVo getAdPlaceFileItemDtoListByParam(Integer publicProjectId, Integer groupId, String publicProjectName,String name, Integer fileType, Integer fileCategory, Boolean bisBinding) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        String appKey = applicationConstant.getAppKey();
        BootstrapTableVo<AdPlaceFileItemDto> bootstrapTableVo = adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByParam(appKey, publicProjectName,publicProjectId, groupId, name, fileType, fileCategory, bisBinding, requestBaseParam.getOffset(), requestBaseParam.getLimit());
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

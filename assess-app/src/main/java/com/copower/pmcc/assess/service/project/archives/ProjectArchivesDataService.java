package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.ad.api.dto.AdBasePlaceFileDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileGroupDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileItemDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileVolumeNumberDto;
import com.copower.pmcc.ad.api.enums.*;
import com.copower.pmcc.ad.api.provider.AdRpcBasePlaceFileService;
import com.copower.pmcc.ad.api.provider.AdRpcPlaceFileGroupService;
import com.copower.pmcc.ad.api.provider.AdRpcPlaceFileItemService;
import com.copower.pmcc.ad.api.provider.AdRpcPlaceFileVolumeNumberService;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.project.AdPlaceFileItemDtoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.SysAppEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
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
    private ErpRpcProjectService rpcProjectService;
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
    private BpmRpcBoxService bpmRpcBoxService;
    private Logger logger = LoggerFactory.getLogger(getClass());

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
     * 自动生成档案  (目前只考虑委托书)
     *
     * @param projectId
     */
    public void autoCreateProjectFileCompleteNow(Integer projectId) {
        Date now = DateUtils.now();
        //委托书 id
        final String project_proxy = "project_proxy";
        List<AdPlaceFileItemDto> placeFileItemDtoArrayList = new ArrayList<>();
        AdPlaceFileItemDto itemDto = null;
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(projectId, project_proxy, FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            int count = 1;
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                itemDto = new AdPlaceFileItemDto();
                itemDto.setName(FilenameUtils.getBaseName(sysAttachmentDto.getFileName()));
                itemDto.setPublicProjectId(projectId);
                itemDto.setSorting(count);
                itemDto.setCreated(now);
                itemDto.setModified(now);
                itemDto.setFileType(Integer.valueOf(ArchivesFileTypeEnum.POWER_ATTORNEY_ENUM.getKey()));
                itemDto.setAppKey(applicationConstant.getAppKey());
                itemDto.setCreator(commonService.thisUserAccount());
                count++;
                placeFileItemDtoArrayList.add(itemDto);
            }
        }
        //附件不使用批量插入
        if (CollectionUtils.isNotEmpty(placeFileItemDtoArrayList)) {
            placeFileItemDtoArrayList = adRpcPlaceFileItemService.batchInsert(placeFileItemDtoArrayList);
            int count = 0;
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                AdPlaceFileItemDto fileItemDto = placeFileItemDtoArrayList.get(count);
                count++;
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(fileItemDto.getId());
                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDto.class));
                attachmentDto.setFieldsName(String.format("%s%s", project_proxy, fileItemDto.getId().toString()));
                try {
                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
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
     * @return
     */
    public String getBoxName(){
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT_ARCHIVES_ADMINISTRATIVE_NODE_KEY.getParameterKey());
        return boxName ;
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

    /**
     * 档案类型
     *
     * @return
     */
    public List<KeyValueDto> getFileArchivesTypeData() {
        ArchivesFileTypeEnum[] enums = ArchivesFileTypeEnum.values();
        List<KeyValueDto> list = new ArrayList<>(enums.length);
        for (ArchivesFileTypeEnum dataPublicEnum : enums) {
            list.add(new KeyValueDto(String.valueOf(dataPublicEnum.getKey()), dataPublicEnum.getName()));
        }
        return list;
    }

    /**
     * 档案方式
     *
     * @return
     */
    public List<KeyValueDto> getFilePublicData() {
        AdArchivesDataPublicEnum[] enums = AdArchivesDataPublicEnum.values();
        List<KeyValueDto> list = new ArrayList<>(enums.length);
        for (AdArchivesDataPublicEnum dataPublicEnum : enums) {
            list.add(new KeyValueDto(String.valueOf(dataPublicEnum.getKey()), dataPublicEnum.getName()));
        }
        return list;
    }

    /**
     * 档案来源
     *
     * @return
     */
    public List<KeyValueDto> getFileSourceData() {
        AdArchivesDataSourceEnum[] enums = AdArchivesDataSourceEnum.values();
        List<KeyValueDto> list = new ArrayList<>(enums.length);
        for (AdArchivesDataSourceEnum sourceEnum : enums) {
            list.add(new KeyValueDto(String.valueOf(sourceEnum.getKey()), sourceEnum.getName()));
        }
        return list;
    }

    /**
     * 档案 保存期限 和 档案分类标识(档案类别)
     *
     * @param dataTypeEnum
     * @return
     */
    public List<AdBasePlaceFileDto> getAdBasePlaceFileDtoListByAppKeyAndType(AdArchivesDataTypeEnum dataTypeEnum) {
        return adRpcBasePlaceFileService.getAdBasePlaceFileDtoListByAppKeyAndType(dataTypeEnum, applicationConstant.getAppKey());
    }

    public List<AdBasePlaceFileDto> getAdBasePlaceFileList( String fieldName){
        return adRpcBasePlaceFileService.getAdBasePlaceFileList(applicationConstant.getAppKey(),fieldName) ;
    }

    public AdPlaceFileItemDto getAdPlaceFileItemDtoById(Integer id) {
        return adRpcPlaceFileItemService.getAdPlaceFileItemDtoById(id);
    }

    public boolean deleteAdPlaceFileItemDtoByIds(String id) {
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        return adRpcPlaceFileItemService.deleteAdPlaceFileItemDtoByIds(ids);
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
            //获取erp中真正的项目id
            if (obj.getPublicProjectId() != null) {
                SysProjectDto projectId = rpcProjectService.getProjectInfoByProjectId(obj.getPublicProjectId(), obj.getAppKey());
                if (projectId != null) {
                    obj.setPublicProjectId(projectId.getProjectId());
                }
            }
            Integer integer = adRpcPlaceFileItemService.saveAdPlaceFileItemDto(obj);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(AdPlaceFileItemDto.class),integer);
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
        return adRpcPlaceFileVolumeNumberService.getAdPlaceFileVolumeNumberDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
    }

    /**
     * 档案
     *
     * @return
     */
    public BootstrapTableVo getAdPlaceFileItemDtoListByParam(Integer publicProjectId, Integer groupId, String name, Integer fileType, Integer fileCategory, Boolean bisBinding) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        String appKey = applicationConstant.getAppKey();
        //获取erp中真正的项目id
        if (publicProjectId != null) {
            SysProjectDto projectId = rpcProjectService.getProjectInfoByProjectId(publicProjectId, appKey);
            if (projectId != null) {
                publicProjectId = projectId.getProjectId();
            }
        }
        BootstrapTableVo<AdPlaceFileItemDto> bootstrapTableVo = adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByParam(appKey, publicProjectId, groupId, name, fileType, fileCategory, bisBinding, requestBaseParam.getOffset(), requestBaseParam.getLimit());
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
        return adRpcBasePlaceFileService.getAdBasePlaceFileListByPid(pid) ;
    }
}

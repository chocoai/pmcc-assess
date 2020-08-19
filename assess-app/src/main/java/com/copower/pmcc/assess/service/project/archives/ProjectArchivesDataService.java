package com.copower.pmcc.assess.service.project.archives;

import com.copower.pmcc.ad.api.dto.AdBasePlaceFileDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileGroupDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileItemDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileVolumeNumberDto;
import com.copower.pmcc.ad.api.enums.AdArchivesDataPublicEnum;
import com.copower.pmcc.ad.api.enums.AdArchivesDataSourceEnum;
import com.copower.pmcc.ad.api.enums.AdArchivesDataTypeEnum;
import com.copower.pmcc.ad.api.enums.ArchivesFileTypeEnum;
import com.copower.pmcc.ad.api.provider.AdRpcBasePlaceFileService;
import com.copower.pmcc.ad.api.provider.AdRpcPlaceFileGroupService;
import com.copower.pmcc.ad.api.provider.AdRpcPlaceFileItemService;
import com.copower.pmcc.ad.api.provider.AdRpcPlaceFileVolumeNumberService;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 档案
 */
@Service
public class ProjectArchivesDataService {
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
    private Logger logger = LoggerFactory.getLogger(getClass());

    public SysSymbolListDto getSymbolDto(Integer ruleId)throws Exception{
        int year = DateUtils.getYear(DateUtils.today());
        SysSymbolListDto sysSymbol = erpRpcToolsService.getSysSymbol(applicationConstant.getAppKey(), ruleId, year);
        if (sysSymbol != null)
            erpRpcToolsService.updateSymbolExamine(applicationConstant.getAppKey(), sysSymbol.getSymbol());
        return sysSymbol;
    }


    /**
     * 卷号规则
     * @return
     */
    public List<SysSymbolRuleDto> getSysSymbolRuleDtoList() {
        List<SysSymbolRuleDto> sysSymbolRuleDtoList = erpRpcToolsService.getSysSymbolRuleDto(SysAppEnum.AD.getValue());
        List<SysSymbolGroupDto> symbolGroupDtoList = erpRpcToolsService.getSysSymbolGroupDto(SysAppEnum.AD.getValue(), "file.group");
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
     * @param obj
     * @return
     */
    public BootstrapTableVo getAdPlaceFileItemDtoListByParam(AdPlaceFileItemDto obj) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        obj.setAppKey(applicationConstant.getAppKey());
        //获取erp中真正的项目id
        if (obj.getPublicProjectId() != null) {
            SysProjectDto projectId = rpcProjectService.getProjectInfoByProjectId(obj.getPublicProjectId(), obj.getAppKey());
            if (projectId != null) {
                obj.setPublicProjectId(projectId.getProjectId());
            }
        }
        return adRpcPlaceFileItemService.getAdPlaceFileItemDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
    }

    public BootstrapTableVo<AdPlaceFileGroupDto> getAdPlaceFileGroupDtoListByParam(AdPlaceFileGroupDto obj) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        return adRpcPlaceFileGroupService.getAdPlaceFileGroupDtoListByParam(obj, requestBaseParam.getOffset(), requestBaseParam.getLimit());
    }

    public AdPlaceFileGroupDto getAdPlaceFileGroupDtoById(Integer id) {
        return adRpcPlaceFileGroupService.getAdPlaceFileGroupDtoById(id);
    }

}

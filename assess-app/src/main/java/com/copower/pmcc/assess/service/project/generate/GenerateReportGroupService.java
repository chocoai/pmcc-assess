package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.GenerateReportGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.erp.api.dto.ProjectDocumentDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zch on 2020-3-20.
 * 委估对象 分组
 */
@Service
public class GenerateReportGroupService {

    @Autowired
    private BaseService baseService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private GenerateReportGroupDao generateReportGroupDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    @Autowired
    private GenerateReportItemService generateReportItemService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    public List<String> getValidData(Integer projectId) {
        List<String> stringList = new ArrayList<>();
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupService.getAreaGroupEnableByProjectId(projectId);
        GenerateReportGroup query = null;
        Iterator<SchemeAreaGroup> iterator = schemeAreaGroupList.iterator();
        while (iterator.hasNext()) {
            SchemeAreaGroup schemeAreaGroup = iterator.next();
            query = new GenerateReportGroup();
            query.setAreaGroupId(schemeAreaGroup.getId());
            List<GenerateReportGroup> reportGroups = getGenerateReportGroupListByQuery(query);
            if (CollectionUtils.isEmpty(reportGroups)) {
                stringList.add(String.join("", schemeAreaGroup.getAreaName(), "  没有添加分组"));
                continue;
            }
            Iterator<GenerateReportGroup> groupIterator = reportGroups.iterator();
            int count = 0;
            while (groupIterator.hasNext()) {
                GenerateReportGroup reportGroup = groupIterator.next();
                List<GenerateReportItem> generateReportItemListByMasterIdList = generateReportItemService.getGenerateReportItemListByMasterIdList(reportGroup.getId());
                if (CollectionUtils.isNotEmpty(generateReportItemListByMasterIdList)) {
                    count++;
                }
            }
            if (count == 0) {
                stringList.add(String.join("", schemeAreaGroup.getAreaName(), "  组中没有添加估价对象"));
            }
        }
        return stringList;
    }

    public List<SchemeJudgeObject> getSchemeJudgeObjectByGroupId(Integer groupId) {
        List<GenerateReportItem> generateReportItemListByMasterIdList = generateReportItemService.getGenerateReportItemListByMasterIdList(groupId);
        List<SchemeJudgeObject> schemeJudgeObjectList = new ArrayList<>(generateReportItemListByMasterIdList.size());
        if (CollectionUtils.isNotEmpty(generateReportItemListByMasterIdList)) {
            Iterator<GenerateReportItem> iterator = generateReportItemListByMasterIdList.iterator();
            while (iterator.hasNext()) {
                GenerateReportItem reportItem = iterator.next();
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(reportItem.getJudgeObjectId());
                schemeJudgeObjectList.add(schemeJudgeObject);
            }
        }
        return schemeJudgeObjectList;
    }


    public boolean updateGenerateReportGroup(GenerateReportGroup oo, boolean updateNull) {
        return generateReportGroupDao.updateGenerateReportGroup(oo, updateNull);
    }

    public boolean saveGenerateReportGroup(GenerateReportGroup oo) throws BusinessException {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }

        boolean b = generateReportGroupDao.saveGenerateReportGroup(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class), oo.getId());
        return b;

    }

    public void saveAndUpdateGenerateReportGroup(GenerateReportGroup oo, boolean updateNull) throws BusinessException {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateGenerateReportGroup(oo, updateNull);
        } else {
            saveGenerateReportGroup(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        List<GenerateReportItem> generateReportItemList = generateReportItemService.getGenerateReportItemListByMasterIdList(tableId);
        if (CollectionUtils.isNotEmpty(generateReportItemList)) {
            List<Integer> collect = generateReportItemList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            generateReportItemService.deleteGenerateReportItemById(StringUtils.join(collect, ","));
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportGroup.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteGenerateReportGroupById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                generateReportGroupDao.deleteGenerateReportGroupById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                generateReportGroupDao.deleteGenerateReportGroupByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        if (projectId == null) return vo;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        GenerateReportGroup where = new GenerateReportGroup();
        where.setProjectId(projectId);
        List<GenerateReportGroup> list = generateReportGroupDao.getGenerateReportGroupListByExample(where);
        if(CollectionUtils.isNotEmpty(list)){
            for (GenerateReportGroup generateReportGroup : list) {
                SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(generateReportGroup.getAreaGroupId());
                generateReportGroup.setName(areaGroup.getAreaName()+"/"+generateReportGroup.getName());
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(list);
        return vo;
    }

    public GenerateReportGroup getGenerateReportGroupById(Integer id) {
        GenerateReportGroup generateReportGroup = generateReportGroupDao.getGenerateReportGroupById(id);
        handle(generateReportGroup);
        return generateReportGroup;
    }


    public List<GenerateReportGroup> getGenerateReportGroupListByQuery(GenerateReportGroup oo) {
        List<GenerateReportGroup> list = generateReportGroupDao.getGenerateReportGroupListByExample(oo);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(obj -> handle(obj));
        }
        return list;
    }

    /**
     * 生成估价对象编码名称
     *
     * @param generateReportGroup
     */
    public void handle(GenerateReportGroup generateReportGroup) {
        if (generateReportGroup == null) {
            return;
        }
        if (NumberUtils.isNumber(generateReportGroup.getReportType())) {
            generateReportGroup.setReportTypeName(baseDataDicService.getNameById(generateReportGroup.getReportType()));
        } else {
            List<String> stringList = FormatUtils.transformString2List(generateReportGroup.getReportType());
            if (CollectionUtils.isNotEmpty(stringList)) {
                List<String> lists = new ArrayList<>(stringList.size());
                stringList.forEach(s -> {
                    lists.add(baseDataDicService.getNameById(s));
                });
                generateReportGroup.setReportTypeName(StringUtils.join(lists, ","));
            }
        }
        List<GenerateReportItem> generateReportItemList = generateReportItemService.getGenerateReportItemListByMasterIdList(generateReportGroup.getId());
        if (CollectionUtils.isEmpty(generateReportItemList)) {
            generateReportGroup.setFullName("");
            return;
        }
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        Iterator<GenerateReportItem> iterator = generateReportItemList.iterator();
        while (iterator.hasNext()) {
            GenerateReportItem reportItem = iterator.next();
            String name = reportItem.getName();
            if (StringUtils.containsAny(name, "估价对象")) {
                name = StringUtils.remove(name, "估价对象");
            }
            stringList.add(name);
            Integer parseIntJudgeNumber = null;
            try {
                parseIntJudgeNumber = generateCommonMethod.parseIntJudgeNumber(reportItem.getNumber());
                integerList.add(parseIntJudgeNumber);
            } catch (Exception e) {
                baseService.writeExceptionInfo(e,e.getMessage());
            }
        }
        if (CollectionUtils.isEmpty(integerList)) {
            return;
        }
        String name = StringUtils.join(stringList, ",");
        generateReportGroup.setFullName(name);
    }

}

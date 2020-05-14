package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.GenerateReportGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroup;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItem;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
    private CommonService commonService;
    @Autowired
    private GenerateReportGroupDao generateReportGroupDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private GenerateReportItemService generateReportItemService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;


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

    public BootstrapTableVo getBootstrapTableVo(GenerateReportGroup query) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<GenerateReportGroup> list = generateReportGroupDao.getGenerateReportGroupListByLike(query);
        if (CollectionUtils.isNotEmpty(list)) {
            Iterator<GenerateReportGroup> iterator = list.iterator();
            while (iterator.hasNext()) {
                GenerateReportGroup generateReportGroup = iterator.next();
                handle(generateReportGroup) ;
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(list);
        return vo;
    }

    public List<GenerateReportGroup> getGenerateReportGroupByIds(List<Integer> ids) {
        return generateReportGroupDao.getGenerateReportGroupByIds(ids);
    }

    public GenerateReportGroup getGenerateReportGroupById(Integer id) {
        return generateReportGroupDao.getGenerateReportGroupById(id);
    }


    public List<GenerateReportGroup> getGenerateReportGroupListByQuery(GenerateReportGroup oo) {
        List<GenerateReportGroup> list = generateReportGroupDao.getGenerateReportGroupListByExample(oo);
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach( obj  -> handle(obj));
        }
        return list;
    }

    /**
     * 生成估价对象编码名称
     * @param generateReportGroup
     */
    public void handle(GenerateReportGroup generateReportGroup) {
        if (generateReportGroup == null){
            return;
        }
        if (NumberUtils.isNumber(generateReportGroup.getReportType())){
            generateReportGroup.setReportTypeName(baseDataDicService.getNameById(generateReportGroup.getReportType()));
        }
        List<GenerateReportItem> generateReportItemList = generateReportItemService.getGenerateReportItemListByMasterIdList(generateReportGroup.getId());
        if (CollectionUtils.isEmpty(generateReportItemList)) {
            return;
        }
        List<Integer> integerList = new ArrayList<>();
        Iterator<GenerateReportItem> iterator = generateReportItemList.iterator();
        while (iterator.hasNext()) {
            GenerateReportItem reportItem = iterator.next();
            if (org.apache.commons.lang3.StringUtils.isBlank(reportItem.getNumber())) {
                continue;
            }
            Integer parseIntJudgeNumber = null;
            try {
                parseIntJudgeNumber = generateCommonMethod.parseIntJudgeNumber(reportItem.getNumber());
                integerList.add(parseIntJudgeNumber);
            } catch (Exception e) {
            }
        }
        if (CollectionUtils.isEmpty(integerList)) {
            return;
        }
        String name = String.join("", generateCommonMethod.convertNumber(integerList), "号");
        generateReportGroup.setFullName(name);
    }


}

package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.GenerateReportItemDao;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2020-3-20.
 * 委估对象 item 属于估价对象分组
 */
@Service
public class GenerateReportItemService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private GenerateReportItemDao generateReportItemDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;



    public boolean updateGenerateReportItem(GenerateReportItem oo, boolean updateNull) {
        return generateReportItemDao.updateGenerateReportItem(oo, updateNull);
    }

    public boolean saveGenerateReportItem(GenerateReportItem oo) throws BusinessException {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        handle(oo) ;
        boolean b = generateReportItemDao.saveGenerateReportItem(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(GenerateReportItem.class), oo.getId());
        return b;

    }



    public  List<GenerateReportItem> getGenerateReportItemByJudgeObjectIds(Integer areaGroupId,List<Integer> judgeObjectIds){
        return generateReportItemDao.getGenerateReportItemByJudgeObjectIds(areaGroupId, judgeObjectIds) ;
    }


    private void handle(GenerateReportItem oo){
        if (oo.getJudgeObjectId() == null || oo.getJudgeObjectId() == 0){
            return;
        }
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(oo.getJudgeObjectId());
        oo.setName(schemeJudgeObject.getName());
        oo.setNumber(schemeJudgeObject.getNumber());
        oo.setProjectId(oo.getProjectId());
        oo.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
    }

    public void saveAndUpdateGenerateReportItem(GenerateReportItem oo, boolean updateNull) throws BusinessException {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateGenerateReportItem(oo, updateNull);
        } else {
            saveGenerateReportItem(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportItem.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteGenerateReportItemById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                generateReportItemDao.deleteGenerateReportItemById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                generateReportItemDao.deleteGenerateReportItemByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(GenerateReportItem query) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<GenerateReportItem> loanBenchmarkInterestRates = generateReportItemDao.getGenerateReportItemListByExample(query);
        vo.setTotal(page.getTotal());
        vo.setRows(loanBenchmarkInterestRates);
        return vo;
    }

    public List<GenerateReportItem> getGenerateReportItemByIds(List<Integer> ids) {
        return generateReportItemDao.getGenerateReportItemByIds(ids);
    }

    public GenerateReportItem getGenerateReportItemById(Integer id) {
        return generateReportItemDao.getGenerateReportItemById(id);
    }


    public List<GenerateReportItem> getGenerateReportItemListByQuery(GenerateReportItem oo) {
        return generateReportItemDao.getGenerateReportItemListByExample(oo);
    }

    public List<GenerateReportItem> getGenerateReportItemListByMasterIdList(Integer masterId) {
        GenerateReportItem  oo = new GenerateReportItem();
        oo.setMasterId(masterId);
        return getGenerateReportItemListByQuery(oo);
    }




}

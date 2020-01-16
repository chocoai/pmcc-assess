package com.copower.pmcc.assess.service.chks;

import com.copower.pmcc.assess.dal.basis.dao.chks.ChksCustomerAssessmentPlanDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.ChksCustomerAssessmentPlanDetail;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2020-1-16.
 * 评估中对考核计划的service
 */
@Service
public class ChksCustomerAssessmentPlanDetailService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ChksCustomerAssessmentPlanDetailDao chksCustomerAssessmentPlanDetailDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean updateChksCustomerAssessmentPlanDetail(ChksCustomerAssessmentPlanDetail oo, boolean updateNull) {
        return chksCustomerAssessmentPlanDetailDao.updateChksCustomerAssessmentPlanDetail(oo, updateNull);
    }

    public boolean saveChksCustomerAssessmentPlanDetail(ChksCustomerAssessmentPlanDetail oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        boolean b = chksCustomerAssessmentPlanDetailDao.saveChksCustomerAssessmentPlanDetail(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ChksCustomerAssessmentPlanDetail.class),oo.getId());
        return b;

    }

    public void saveAndUpdateChksCustomerAssessmentPlanDetail(ChksCustomerAssessmentPlanDetail oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            chksCustomerAssessmentPlanDetailDao.updateChksCustomerAssessmentPlanDetail(oo, updateNull);
        } else {
            saveChksCustomerAssessmentPlanDetail(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ChksCustomerAssessmentPlanDetail.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteChksCustomerAssessmentPlanDetailById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                chksCustomerAssessmentPlanDetailDao.deleteChksCustomerAssessmentPlanDetailById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                chksCustomerAssessmentPlanDetailDao.deleteChksCustomerAssessmentPlanDetailByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(ChksCustomerAssessmentPlanDetail oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ChksCustomerAssessmentPlanDetail> declareApplyExtensionList = getChksCustomerAssessmentPlanDetailListByQuery(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }

    public List<ChksCustomerAssessmentPlanDetail> getChksCustomerAssessmentPlanDetailByIds(List<Integer> ids) {
        return chksCustomerAssessmentPlanDetailDao.getChksCustomerAssessmentPlanDetailByIds(ids);
    }

    public ChksCustomerAssessmentPlanDetail getChksCustomerAssessmentPlanDetailById(Integer id) {
        return chksCustomerAssessmentPlanDetailDao.getChksCustomerAssessmentPlanDetailById(id);
    }

    public List<ChksCustomerAssessmentPlanDetail> getChksCustomerAssessmentPlanDetailListByQuery(ChksCustomerAssessmentPlanDetail oo) {
        return chksCustomerAssessmentPlanDetailDao.getChksCustomerAssessmentPlanDetailListByExample(oo);
    }

    //com.copower.pmcc.assess.controller.chks.ChksCustomerAssessmentPlanDetailController.apply()
    public static String applyUrl = "/chksCustomerAssessmentPlanDetail/apply" ;

    //com.copower.pmcc.assess.controller.chks.ChksCustomerAssessmentPlanDetailController.approval()
    public static String approvalUrl = "/chksCustomerAssessmentPlanDetail/approval" ;

}

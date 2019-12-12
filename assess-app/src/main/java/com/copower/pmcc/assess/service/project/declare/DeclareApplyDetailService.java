package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyDetail;
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
 * Created by zch on 2019-12-10.
 * 申报扩展自定义字段
 */
@Service
public class DeclareApplyDetailService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareApplyDetailDao declareApplyExtensionDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean updateDeclareApplyDetail(DeclareApplyDetail oo, boolean updateNull) {
        return declareApplyExtensionDao.updateDeclareApplyDetail(oo, updateNull);
    }

    public boolean saveDeclareApplyDetail(DeclareApplyDetail oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return declareApplyExtensionDao.saveDeclareApplyDetail(oo);
    }

    public void saveAndUpdateDeclareApplyDetail(DeclareApplyDetail oo,boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            declareApplyExtensionDao.updateDeclareApplyDetail(oo, updateNull);
        } else {
            saveDeclareApplyDetail(oo);
        }
    }

    private void removeFileByTableId(Integer tableId){
        if (tableId == null){
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DeclareApplyDetail.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto) ;
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)){
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteDeclareApplyDetailById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0)) ;
                declareApplyExtensionDao.deleteDeclareApplyDetailById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                declareApplyExtensionDao.deleteDeclareApplyDetailByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DeclareApplyDetail oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareApplyDetail> declareApplyExtensionList = getDeclareApplyDetailListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }

    protected List<DeclareApplyDetail> getDeclareApplyDetailListByDeclareId(Integer declareId){
        if (declareId == null){
            return new ArrayList<>(0) ;
        }
        DeclareApplyDetail query = new DeclareApplyDetail();
        query.setDeclareId(declareId);
        return getDeclareApplyDetailListByExample(query) ;
    }

    public List<DeclareApplyDetail> getDeclareApplyDetailByIds(List<Integer> ids) {
        return declareApplyExtensionDao.getDeclareApplyDetailByIds(ids);
    }

    public DeclareApplyDetail getDeclareApplyDetailById(Integer id) {
        return declareApplyExtensionDao.getDeclareApplyDetailById(id);
    }

    public List<DeclareApplyDetail> getDeclareApplyDetailListByExample(DeclareApplyDetail oo) {
        return declareApplyExtensionDao.getDeclareApplyDetailListByExample(oo);
    }
}

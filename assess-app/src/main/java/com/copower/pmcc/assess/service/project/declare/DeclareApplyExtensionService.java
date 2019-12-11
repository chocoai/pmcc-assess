package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyExtensionDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExtension;
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
public class DeclareApplyExtensionService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareApplyExtensionDao declareApplyExtensionDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean updateDeclareApplyExtension(DeclareApplyExtension oo, boolean updateNull) {
        return declareApplyExtensionDao.updateDeclareApplyExtension(oo, updateNull);
    }

    public boolean saveDeclareApplyExtension(DeclareApplyExtension oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return declareApplyExtensionDao.saveDeclareApplyExtension(oo);
    }

    public void saveAndUpdateDeclareApplyExtension(DeclareApplyExtension oo,boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            declareApplyExtensionDao.updateDeclareApplyExtension(oo, updateNull);
        } else {
            saveDeclareApplyExtension(oo);
        }
    }

    private void removeFileByTableId(Integer tableId){
        if (tableId == null){
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DeclareApplyExtension.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto) ;
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)){
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteDeclareApplyExtensionById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0)) ;
                declareApplyExtensionDao.deleteDeclareApplyExtensionById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                declareApplyExtensionDao.deleteDeclareApplyExtensionByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DeclareApplyExtension oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareApplyExtension> declareApplyExtensionList = getDeclareApplyExtensionListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }

    protected List<DeclareApplyExtension> getDeclareApplyExtensionListByDeclareId(Integer declareId){
        if (declareId == null){
            return new ArrayList<>(0) ;
        }
        DeclareApplyExtension query = new DeclareApplyExtension();
        query.setDeclareId(declareId);
        return getDeclareApplyExtensionListByExample(query) ;
    }

    public List<DeclareApplyExtension> getDeclareApplyExtensionByIds(List<Integer> ids) {
        return declareApplyExtensionDao.getDeclareApplyExtensionByIds(ids);
    }

    public DeclareApplyExtension getDeclareApplyExtensionById(Integer id) {
        return declareApplyExtensionDao.getDeclareApplyExtensionById(id);
    }

    public List<DeclareApplyExtension> getDeclareApplyExtensionListByExample(DeclareApplyExtension oo) {
        return declareApplyExtensionDao.getDeclareApplyExtensionListByExample(oo);
    }
}

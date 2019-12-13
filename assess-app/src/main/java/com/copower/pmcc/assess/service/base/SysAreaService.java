package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.SysAreaDao;
import com.copower.pmcc.assess.dal.basis.entity.SysArea;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wangpc on 2019-12-11.
 * 地区 区域
 */
@Service
public class SysAreaService {
    @Autowired
    private SysAreaDao sysAreaDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean updateSysArea(SysArea oo, boolean updateNull) {
        return sysAreaDao.updateSysArea(oo, updateNull);
    }

    public boolean saveSysArea(SysArea oo) {
        if (oo == null) {
            return false;
        }
        oo.setAreaId(getAreaId());
        return sysAreaDao.saveSysArea(oo);
    }

    private String getAreaId(){
        List<SysArea> sysAreaList = sysAreaDao.getSysAreaListByExample(new SysArea()) ;
        TreeSet<Integer> treeSet = new TreeSet<>() ;
        if (CollectionUtils.isNotEmpty(sysAreaList)){
            Iterator<SysArea> sysAreaIterator = sysAreaList.iterator();
            while (sysAreaIterator.hasNext()){
                SysArea sysArea = sysAreaIterator.next() ;
                if (StringUtils.isEmpty(sysArea.getAreaId())){
                    continue;
                }
                treeSet.add(Integer.parseInt(sysArea.getAreaId())) ;
            }
        }
        if (CollectionUtils.isNotEmpty(treeSet)){
            int count = treeSet.last().intValue() ;
            count++ ;
            return String.valueOf(count);
        }
        //第一次情况
        return "1" ;
    }

    public void saveAndUpdateSysArea(SysArea oo,boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            sysAreaDao.updateSysArea(oo, updateNull);
        } else {
            saveSysArea(oo);
        }
    }

    private void removeFileByTableId(Integer tableId){
        if (tableId == null){
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SysArea.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto) ;
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)){
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    @Transactional(rollbackFor = {BusinessException.class})
    public void deleteSysAreaByIdEnable(String id)throws BusinessException{
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            List<SysArea> declareApplyExtensionList = getSysAreaByIds(ids) ;
            if (CollectionUtils.isEmpty(declareApplyExtensionList)){
                return;
            }
            Iterator<SysArea> sysAreaIterator = declareApplyExtensionList.iterator();
            while (sysAreaIterator.hasNext()){
                SysArea sysArea = sysAreaIterator.next() ;
                SysArea query = new SysArea();
                query.setBisEnable(true);
                query.setParentId(sysArea.getAreaId());
                List<SysArea> sysAreaList = getSysAreaListSelect(query) ;
                if (CollectionUtils.isNotEmpty(sysAreaList)){
                    throw new BusinessException("请删除下级目标在删除此数据!") ;
                }
                sysArea.setBisEnable(false);
                sysAreaDao.updateSysArea(sysArea, true);
            }
        }
    }

    public void deleteSysAreaById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0)) ;
                sysAreaDao.deleteSysAreaById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                sysAreaDao.deleteSysAreaByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SysArea oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SysArea> declareApplyExtensionList = getSysAreaListSelect(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareApplyExtensionList) ? declareApplyExtensionList : new ArrayList(0));
        return vo;
    }


    public List<SysArea> getSysAreaByIds(List<Integer> ids) {
        return sysAreaDao.getSysAreaByIds(ids);
    }

    public SysArea getSysAreaById(Integer id) {
        return sysAreaDao.getSysAreaById(id);
    }

    public List<SysArea> getSysAreaListByExample(SysArea oo) {
        return sysAreaDao.getSysAreaListByExample(oo);
    }

    public List<SysArea> getSysAreaListSelect(SysArea sysArea){
        return sysAreaDao.getSysAreaListSelect(sysArea);
    }

}

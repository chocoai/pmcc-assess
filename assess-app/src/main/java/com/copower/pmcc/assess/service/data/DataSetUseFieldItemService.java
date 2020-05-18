package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataSetUseFieldItemDao;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldItem;
import com.copower.pmcc.assess.dto.output.data.DataSetUseFieldItemVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zch on 2020-3-20.
 * 设定用途  子表
 */
@Service
public class DataSetUseFieldItemService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DataSetUseFieldItemDao dataSetUseFieldItemDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ErpAreaService erpAreaService;

    public boolean updateDataSetUseFieldItem(DataSetUseFieldItem oo, boolean updateNull) {
        if (updateNull) {
            if (oo.getMasterId() == null || oo.getMasterId() == 0) {
                DataSetUseFieldItem item = getDataSetUseFieldItemById(oo.getId());
                oo.setMasterId(item.getMasterId());
            }
        }
        return dataSetUseFieldItemDao.updateDataSetUseFieldItem(oo, updateNull);
    }

    public boolean saveDataSetUseFieldItem(DataSetUseFieldItem oo) throws BusinessException {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }

        boolean b = dataSetUseFieldItemDao.saveDataSetUseFieldItem(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataSetUseFieldItem.class), oo.getId());
        return b;

    }

    public void saveAndUpdateDataSetUseFieldItem(DataSetUseFieldItem oo, boolean updateNull) throws BusinessException {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateDataSetUseFieldItem(oo, updateNull);
        } else {
            saveDataSetUseFieldItem(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DataSetUseFieldItem.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteDataSetUseFieldItemById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                dataSetUseFieldItemDao.deleteDataSetUseFieldItemById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                dataSetUseFieldItemDao.deleteDataSetUseFieldItemByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DataSetUseFieldItem query) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataSetUseFieldItem> loanBenchmarkInterestRates = getDataSetUseFieldItemListByQuery(query);
        List<DataSetUseFieldItemVo> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(loanBenchmarkInterestRates)) {
            loanBenchmarkInterestRates.forEach(po -> vos.add(getDataSetUseFieldItemVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataSetUseFieldItem> getDataSetUseFieldItemByIds(List<Integer> ids) {
        return dataSetUseFieldItemDao.getDataSetUseFieldItemByIds(ids);
    }

    public DataSetUseFieldItem getDataSetUseFieldItemById(Integer id) {
        return dataSetUseFieldItemDao.getDataSetUseFieldItemById(id);
    }


    public List<DataSetUseFieldItem> getDataSetUseFieldItemListByQuery(DataSetUseFieldItem oo) {
        return dataSetUseFieldItemDao.getDataSetUseFieldItemListByExample(oo);
    }

    public DataSetUseFieldItemVo getDataSetUseFieldItemVo(DataSetUseFieldItem oo) {
        if (oo == null) {
            return null;
        }
        DataSetUseFieldItemVo vo = new DataSetUseFieldItemVo();
        BeanUtils.copyProperties(oo, vo);
        return vo;
    }

    public Integer getDataSetUseFieldItemCount(Integer masterId, String type, String category) {
        if (masterId == null) return 0;
        return dataSetUseFieldItemDao.getDataSetUseFieldItemCount(masterId, type, category);
    }

    public DataSetUseFieldItem getDataSetUseFieldItem(Integer masterId, String type, String category) {
        if (masterId == null) return null;
        return dataSetUseFieldItemDao.getDataSetUseFieldItem(masterId, type, category);
    }
}

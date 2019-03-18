package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportTemplateItemDao;
import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItem;
import com.copower.pmcc.assess.dto.output.data.DataReportTemplateItemVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataReportTemplateItemService {
    @Autowired
    private DataReportTemplateItemDao dataReportTemplateItemDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;


    public DataReportTemplateItemVo getByDataReportTemplateItemId(Integer id) {
        DataReportTemplateItem object = dataReportTemplateItemDao.getSingleObject(id);
        return getDataReportTemplateItemVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(Integer masterId, String type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<DataReportTemplateItemVo> vos = new ArrayList<>();
        List<DataReportTemplateItem> dataReportTemplateItemList = dataReportTemplateItemDao.getListByMasterId(masterId, type);
        if (CollectionUtils.isNotEmpty(dataReportTemplateItemList)) {
            for (DataReportTemplateItem item : dataReportTemplateItemList) {
                vos.add(getDataReportTemplateItemVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataReportTemplateItemVo>() : vos);
        return vo;
    }


    public DataReportTemplateItemVo getDataReportTemplateItemVo(DataReportTemplateItem dataReportTemplateItem) {
        if (dataReportTemplateItem == null) return null;
        DataReportTemplateItemVo dataReportTemplateItemVo = new DataReportTemplateItemVo();
        BeanUtils.copyProperties(dataReportTemplateItem, dataReportTemplateItemVo);
        dataReportTemplateItemVo.setTypeName(SchemeSupportTypeEnum.getName(dataReportTemplateItem.getType()));
        return dataReportTemplateItemVo;
    }

    /**
     * 保存
     *
     * @param dataReportTemplateItem
     * @throws BusinessException
     */
    public boolean addDataReportTemplateItemReturnId(DataReportTemplateItem dataReportTemplateItem) {
        dataReportTemplateItem.setCreator(processControllerComponent.getThisUser());
        return dataReportTemplateItemDao.addObject(dataReportTemplateItem);
    }

    public boolean updateDataReportTemplateItem(DataReportTemplateItem dataReportTemplateItem) {
        return dataReportTemplateItemDao.updateObject(dataReportTemplateItem);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataReportTemplateItem(Integer id) throws BusinessException {
        return dataReportTemplateItemDao.deleteObject(id);
    }


    //设置masterId
    public void templateItemToSetMasterId(Integer masterId, String type){
        DataReportTemplateItem dataReportTemplateItem = new DataReportTemplateItem();
        dataReportTemplateItem.setMasterId(0);
        dataReportTemplateItem.setType(type);
        List<DataReportTemplateItem> listObject = dataReportTemplateItemDao.getListObject(dataReportTemplateItem);
        for (DataReportTemplateItem item :listObject) {
            item.setMasterId(masterId);
            dataReportTemplateItemDao.updateObject(item);
        }
    }

    //清除masterId为0的数据
    public void initClean(){
        DataReportTemplateItem dataReportTemplateItem = new DataReportTemplateItem();
        dataReportTemplateItem.setMasterId(0);
        List<DataReportTemplateItem> listObject = dataReportTemplateItemDao.getListObject(dataReportTemplateItem);
        for (DataReportTemplateItem item :listObject) {
            dataReportTemplateItemDao.deleteObject(item.getId());
        }
    }

    //通过feildName获取
    public DataReportTemplateItem getDataReportTemplateByField(String fieldName) {
        DataReportTemplateItem dataReportTemplateItem = new DataReportTemplateItem();
        dataReportTemplateItem.setFieldName(fieldName);
        return dataReportTemplateItemDao.getSingleObject(dataReportTemplateItem);
    }
}

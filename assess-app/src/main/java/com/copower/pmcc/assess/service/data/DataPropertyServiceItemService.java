package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataPropertyServiceItemDao;
import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItem;
import com.copower.pmcc.assess.dto.output.data.DataPropertyServiceItemVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataPropertyServiceItemService {
    @Autowired
    private DataPropertyServiceItemDao dataPropertyServiceItemDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;


    public DataPropertyServiceItemVo getByDataPropertyServiceItemId(Integer id) {
        DataPropertyServiceItem object = dataPropertyServiceItemDao.getSingleObject(id);
        return getDataPropertyServiceItemVo(object);
    }

    public List<DataPropertyServiceItemVo> getListByMasterId(Integer masterId){
        List<DataPropertyServiceItemVo> vos = Lists.newArrayList();
        List<DataPropertyServiceItem> dataPropertyServiceItems = dataPropertyServiceItemDao.getListByMasterId(masterId);
        if (CollectionUtils.isNotEmpty(dataPropertyServiceItems)){
            dataPropertyServiceItems.stream().forEach( oo ->{
                vos.add(getDataPropertyServiceItemVo(oo));
            });
        }
        return vos;
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(Integer masterId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<DataPropertyServiceItemVo> vos = new ArrayList<>();
        List<DataPropertyServiceItem> dataPropertyServiceItemList = dataPropertyServiceItemDao.getListByMasterId(masterId);
        if (CollectionUtils.isNotEmpty(dataPropertyServiceItemList)) {
            for (DataPropertyServiceItem item : dataPropertyServiceItemList) {
                vos.add(getDataPropertyServiceItemVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataPropertyServiceItemVo>() : vos);
        return vo;
    }


    public DataPropertyServiceItemVo getDataPropertyServiceItemVo(DataPropertyServiceItem dataPropertyServiceItem) {
        if (dataPropertyServiceItem == null) return null;
        DataPropertyServiceItemVo dataPropertyServiceItemVo = new DataPropertyServiceItemVo();
        BeanUtils.copyProperties(dataPropertyServiceItem, dataPropertyServiceItemVo);
        if(dataPropertyServiceItem.getServiceType()!=null){
            dataPropertyServiceItemVo.setServiceTypeName(baseDataDicService.getNameById(dataPropertyServiceItem.getServiceType()));
        }
        if(dataPropertyServiceItem.getServiceContent()!=null){
            dataPropertyServiceItemVo.setServiceContentName(baseDataDicService.getNameById(dataPropertyServiceItem.getServiceContent()));
        }
        if(dataPropertyServiceItem.getGradeEvaluation()!=null){
            dataPropertyServiceItemVo.setGradeEvaluationName(baseDataDicService.getNameById(dataPropertyServiceItem.getGradeEvaluation()));
        }
        return dataPropertyServiceItemVo;
    }

    /**
     * 保存
     *
     * @param dataPropertyServiceItem
     * @throws BusinessException
     */
    public boolean addDataPropertyServiceItemReturnId(DataPropertyServiceItem dataPropertyServiceItem) {
        dataPropertyServiceItem.setCreator(processControllerComponent.getThisUser());
        return dataPropertyServiceItemDao.addObject(dataPropertyServiceItem);
    }

    public boolean updateDataPropertyServiceItem(DataPropertyServiceItem dataPropertyServiceItem) {
        return dataPropertyServiceItemDao.updateObject(dataPropertyServiceItem);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataPropertyServiceItem(Integer id) throws BusinessException {
        return dataPropertyServiceItemDao.deleteObject(id);
    }


    //设置masterId
    public void templateItemToSetMasterId(Integer masterId){
        DataPropertyServiceItem dataPropertyServiceItem = new DataPropertyServiceItem();
        dataPropertyServiceItem.setMasterId(0);
        List<DataPropertyServiceItem> listObject = dataPropertyServiceItemDao.getListObject(dataPropertyServiceItem);
        for (DataPropertyServiceItem item :listObject) {
            item.setMasterId(masterId);
            dataPropertyServiceItemDao.updateObject(item);
        }
    }

    //清除masterId为0的数据
    public void initClean(){
        DataPropertyServiceItem dataPropertyServiceItem = new DataPropertyServiceItem();
        dataPropertyServiceItem.setMasterId(0);
        List<DataPropertyServiceItem> listObject = dataPropertyServiceItemDao.getListObject(dataPropertyServiceItem);
        for (DataPropertyServiceItem item :listObject) {
            dataPropertyServiceItemDao.deleteObject(item.getId());
        }
    }

}

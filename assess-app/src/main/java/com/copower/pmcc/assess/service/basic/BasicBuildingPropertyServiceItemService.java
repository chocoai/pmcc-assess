package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingPropertyServiceItemDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItem;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2019/7/24.
 */
@Service
public class BasicBuildingPropertyServiceItemService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private BasicBuildingPropertyServiceItemDao serviceItemDao;

    public boolean saveBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem BasicBuildingPropertyServiceItem){
        if (BasicBuildingPropertyServiceItem == null){
            return false;
        }
        if (BasicBuildingPropertyServiceItem.getId() != null && BasicBuildingPropertyServiceItem.getId() != 0){
            return serviceItemDao.updateBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem);
        }else {
            BasicBuildingPropertyServiceItem.setCreator(commonService.thisUserAccount());
            return serviceItemDao.addBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem) ;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingPropertyServiceItem oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingPropertyServiceItem> serviceItemList = getBasicBuildingPropertyServiceItemListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(serviceItemList) ? new ArrayList<BasicBuildingPropertyServiceItem>(10) : serviceItemList);
        return vo;
    }

    public BasicBuildingPropertyServiceItem getBasicBuildingPropertyServiceItemById(Integer id){
        return serviceItemDao.getBasicBuildingPropertyServiceItemById(id) ;
    }

    public boolean deleteBasicBuildingPropertyServiceItemById(Integer id){
        return serviceItemDao.deleteBasicBuildingPropertyServiceItemById(id) ;
    }


    public List<BasicBuildingPropertyServiceItem> getBasicBuildingPropertyServiceItemListByExample(BasicBuildingPropertyServiceItem oo){
        return serviceItemDao.getBasicBuildingPropertyServiceItemListByExample(oo);
    }
    
}

package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingPropertyServiceItemDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingPropertyServiceItem;
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
public class CaseBuildingPropertyServiceItemService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private CaseBuildingPropertyServiceItemDao serviceItemDao;

    public boolean saveCaseBuildingPropertyServiceItem(CaseBuildingPropertyServiceItem CaseBuildingPropertyServiceItem){
        if (CaseBuildingPropertyServiceItem == null){
            return false;
        }
        if (CaseBuildingPropertyServiceItem.getId() != null && CaseBuildingPropertyServiceItem.getId() != 0){
            return serviceItemDao.updateCaseBuildingPropertyServiceItem(CaseBuildingPropertyServiceItem);
        }else {
            CaseBuildingPropertyServiceItem.setCreator(commonService.thisUserAccount());
            return serviceItemDao.addCaseBuildingPropertyServiceItem(CaseBuildingPropertyServiceItem) ;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(CaseBuildingPropertyServiceItem oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuildingPropertyServiceItem> serviceItemList = getCaseBuildingPropertyServiceItemListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(serviceItemList) ? new ArrayList<CaseBuildingPropertyServiceItem>(10) : serviceItemList);
        return vo;
    }

    public CaseBuildingPropertyServiceItem getCaseBuildingPropertyServiceItemById(Integer id){
        return serviceItemDao.getCaseBuildingPropertyServiceItemById(id) ;
    }

    public boolean deleteCaseBuildingPropertyServiceItemById(Integer id){
        return serviceItemDao.deleteCaseBuildingPropertyServiceItemById(id) ;
    }


    public List<CaseBuildingPropertyServiceItem> getCaseBuildingPropertyServiceItemListByExample(CaseBuildingPropertyServiceItem oo){
        return serviceItemDao.getCaseBuildingPropertyServiceItemListByExample(oo);
    }
    
}

package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingPropertyServiceItemDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingPropertyServiceItem;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingPropertyServiceItemVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
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
    private BaseDataDicService baseDataDicService;

    @Autowired
    private BasicBuildingPropertyServiceItemDao serviceItemDao;

    public boolean saveBasicBuildingPropertyServiceItem(BasicBuildingPropertyServiceItem basicBuildingPropertyServiceItem, boolean updateNull) {
        if (basicBuildingPropertyServiceItem == null) return false;
        if (basicBuildingPropertyServiceItem.getId() != null && basicBuildingPropertyServiceItem.getId() != 0) {
            if(updateNull){
               BasicBuildingPropertyServiceItem propertyServiceItem = serviceItemDao.getBasicBuildingPropertyServiceItemById(basicBuildingPropertyServiceItem.getId());
               if(propertyServiceItem!=null){
                   basicBuildingPropertyServiceItem.setCreator(propertyServiceItem.getCreator());
                   basicBuildingPropertyServiceItem.setGmtCreated(propertyServiceItem.getGmtCreated());
               }
            }
            return serviceItemDao.updateBasicBuildingPropertyServiceItem(basicBuildingPropertyServiceItem,updateNull);
        } else {
            basicBuildingPropertyServiceItem.setCreator(commonService.thisUserAccount());
            return serviceItemDao.addBasicBuildingPropertyServiceItem(basicBuildingPropertyServiceItem);
        }
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingPropertyServiceItem oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingPropertyServiceItem> serviceItemList = getBasicBuildingPropertyServiceItemListByExample(oo);
        List<BasicBuildingPropertyServiceItemVo> vos = Lists.newArrayList();
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(serviceItemList)) {
            serviceItemList.forEach(po -> {
                vos.add(getBasicBuildingPropertyServiceItemVo(po));
            });
        }
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicBuildingPropertyServiceItemVo>(10) : vos);
        return vo;
    }

    public BasicBuildingPropertyServiceItem getBasicBuildingPropertyServiceItemById(Integer id) {
        return serviceItemDao.getBasicBuildingPropertyServiceItemById(id);
    }

    public void removeIds(List<Integer> integerList) {
        serviceItemDao.removeIds(integerList);
    }

    public boolean deleteBasicBuildingPropertyServiceItemById(Integer id) {
        return serviceItemDao.deleteBasicBuildingPropertyServiceItemById(id);
    }


    public List<BasicBuildingPropertyServiceItem> getBasicBuildingPropertyServiceItemListByExample(BasicBuildingPropertyServiceItem oo) {
        return serviceItemDao.getBasicBuildingPropertyServiceItemListByExample(oo);
    }


    public BasicBuildingPropertyServiceItemVo getBasicBuildingPropertyServiceItemVo(BasicBuildingPropertyServiceItem oo) {
        if (oo == null) {
            return null;
        }
        BasicBuildingPropertyServiceItemVo vo = new BasicBuildingPropertyServiceItemVo();
        BeanUtils.copyProperties(oo, vo);
        vo.setGradeEvaluationName(baseDataDicService.getNameById(oo.getGradeEvaluation()));
        vo.setServiceTypeName(baseDataDicService.getNameById(oo.getServiceType()));
        vo.setServiceContentName(baseDataDicService.getNameById(oo.getServiceContent()));
        return vo;
    }

}

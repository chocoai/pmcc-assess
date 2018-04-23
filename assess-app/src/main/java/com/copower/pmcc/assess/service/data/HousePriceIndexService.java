package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.HousePriceIndexDao;
import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HousePriceIndexService {
    @Autowired
    private HousePriceIndexDao housePriceIndexDao;

    public boolean addHousePriceIndex(HousePriceIndex housePriceIndex)throws BusinessException{
        return housePriceIndexDao.add(housePriceIndex);
    }

    public boolean removeHousePriceIndeX(Integer id)throws BusinessException{
        return housePriceIndexDao.remove(id);
    }

    public HousePriceIndex get(Integer id)throws BusinessException{
        return housePriceIndexDao.getById(id);
    }

    public boolean update(HousePriceIndex housePriceIndex){
        return housePriceIndexDao.updateHousePriceIndex(housePriceIndex);
    }

    public List<HousePriceIndex> list()throws BusinessException{
        List<HousePriceIndex> housePriceIndices = housePriceIndexDao.list(null);
        housePriceIndices.forEach(housePriceIndex -> {
            housePriceIndex.setYearMonthString(change(housePriceIndex.getYearMonthCalendar()));
        });
        return housePriceIndices;
    }

    public List<HousePriceIndex> list(String index)throws BusinessException{
        Map<String,Object> map = new HashMap<>();
        map.put(HousePriceIndex.INDEX_CALENDAR_KEY,index);
        List<HousePriceIndex> housePriceIndices = housePriceIndexDao.list(map);
        housePriceIndices.forEach(housePriceIndex -> {
            housePriceIndex.setYearMonthString(change(housePriceIndex.getYearMonthCalendar()));
        });
        return housePriceIndices;
    }
    public String change(Date date){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        StringBuilder builder = new StringBuilder();
        builder.append(rightNow.get(Calendar.YEAR)+"年");
        builder.append(rightNow.get(Calendar.MINUTE)+"月");
        return builder.toString();
    }

    public BootstrapTableVo getListVo(String index)throws BusinessException{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        vo.setTotal(page.getTotal());
        if (index!=null){
            List<HousePriceIndex> housePriceIndices = list(index);
            vo.setRows(CollectionUtils.isEmpty(housePriceIndices) ? new ArrayList<HousePriceIndex>() : housePriceIndices);
        }else {
            List<HousePriceIndex> housePriceIndices = list();
            vo.setRows(CollectionUtils.isEmpty(housePriceIndices) ? new ArrayList<HousePriceIndex>() : housePriceIndices);
        }
        return vo;
    }
}

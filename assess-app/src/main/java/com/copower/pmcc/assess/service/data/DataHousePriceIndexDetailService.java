package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexDetailVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:17
 * @description:房价指数 详情(从表)
 */
@Service
public class DataHousePriceIndexDetailService {

    @Autowired
    private DataHousePriceIndexDetailDao dataLandDetailAchievementDao;
    @Autowired
    private CommonService commonService;

    public boolean saveDataHousePriceIndexDetail(DataHousePriceIndexDetail oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataHousePriceIndexDetail(oo);
        } else {
            return dataLandDetailAchievementDao.editDataHousePriceIndexDetail(oo);
        }
    }

    public boolean deleteDataHousePriceIndexDetail(Integer id){
        return dataLandDetailAchievementDao.deleteDataHousePriceIndexDetail(id);
    }

    public DataHousePriceIndexDetail getDataHousePriceIndexDetailById(Integer id){
        return dataLandDetailAchievementDao.getDataHousePriceIndexDetailById(id);
    }

    public List<DataHousePriceIndexDetail> getDataHousePriceIndexDetailList(DataHousePriceIndexDetail oo){
        return dataLandDetailAchievementDao.getDataHousePriceIndexDetailList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataHousePriceIndexDetail oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataHousePriceIndexDetail> list = getDataHousePriceIndexDetailList(oo);
        List<DataHousePriceIndexDetailVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(po -> voList.add(getDataHousePriceIndexDetailVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataHousePriceIndexDetailVo getDataHousePriceIndexDetailVo(DataHousePriceIndexDetail oo){
        if (oo==null){
            return null;
        }
        DataHousePriceIndexDetailVo vo = new DataHousePriceIndexDetailVo();
        org.springframework.beans.BeanUtils.copyProperties(oo,vo);
        return vo;
    }
}

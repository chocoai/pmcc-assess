package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitHuxingPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingPrice;
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
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 * @Description:
 */
@Service
public class BasicUnitHuxingPriceService {
    @Autowired
    private BasicUnitHuxingPriceDao basicUnitHuxingPriceDao;
    @Autowired
    private CommonService commonService;

    public Integer saveAndUpdateBasicUnitHuxingPrice(BasicUnitHuxingPrice basicUnitHuxingPrice, boolean updateNull) throws Exception {
        if (basicUnitHuxingPrice.getId() == null || basicUnitHuxingPrice.getId().intValue() == 0) {
            basicUnitHuxingPrice.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitHuxingPriceDao.addBasicUnitHuxingPrice(basicUnitHuxingPrice);
            return id;
        } else {
            if (updateNull) {
                BasicUnitHuxingPrice unitHuxingPrice = basicUnitHuxingPriceDao.getBasicUnitHuxingPriceById(basicUnitHuxingPrice.getId());
                if (unitHuxingPrice != null) {
                    basicUnitHuxingPrice.setCreator(unitHuxingPrice.getCreator());
                    basicUnitHuxingPrice.setGmtCreated(unitHuxingPrice.getGmtCreated());
                }
            }
            basicUnitHuxingPriceDao.updateBasicUnitHuxingPrice(basicUnitHuxingPrice, updateNull);
            return basicUnitHuxingPrice.getId();
        }
    }

    public BasicUnitHuxingPrice getBasicUnitHuxingPriceById(Integer id) {
        return basicUnitHuxingPriceDao.getBasicUnitHuxingPriceById(id);
    }

    public BootstrapTableVo getBasicUnitHuxingPriceListVos(BasicUnitHuxingPrice basicUnitHuxingPrice) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());

        List<BasicUnitHuxingPrice> basicUnitHuxingPrices = basicUnitHuxingPriceDao.basicUnitHuxingList(basicUnitHuxingPrice);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(basicUnitHuxingPrices) ? new ArrayList<BasicUnitHuxingPrice>() : basicUnitHuxingPrices);
        return vo;
    }


    public boolean deleteBasicUnitHuxingPrice(Integer id) {
        return basicUnitHuxingPriceDao.deleteBasicUnitHuxingPrice(id);
    }
}

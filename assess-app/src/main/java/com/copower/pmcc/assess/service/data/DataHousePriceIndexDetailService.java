package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:17
 * @description:房价指数 详情(从表)
 */
@Service
public class DataHousePriceIndexDetailService {

    @Autowired
    private DataHousePriceIndexDetailDao detailDao;

    public List<DataHousePriceIndexDetail> getDataHousePriceIndexDetailList(DataHousePriceIndexDetail query){
        List<DataHousePriceIndexDetail> dataHousePriceIndexDetails = Lists.newArrayList();
        return dataHousePriceIndexDetails;
    }
}

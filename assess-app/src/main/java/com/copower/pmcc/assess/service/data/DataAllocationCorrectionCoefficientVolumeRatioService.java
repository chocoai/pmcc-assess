package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zch
 * @date: 2019/4/29 18:17
 * @description:容积率修正系数配置
 */
@Service
public class DataAllocationCorrectionCoefficientVolumeRatioService {
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailService detailService;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDao volumeRatioDao;

}

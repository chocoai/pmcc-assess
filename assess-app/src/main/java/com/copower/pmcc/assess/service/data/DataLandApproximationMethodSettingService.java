package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLandApproximationMethodSettingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zch
 * @date: 2019/4/29 18:18
 * @description:土地逼近法补偿配置
 */
@Service
public class DataLandApproximationMethodSettingService {

    @Autowired
    private DataLandApproximationMethodSettingDao settingDao;

}

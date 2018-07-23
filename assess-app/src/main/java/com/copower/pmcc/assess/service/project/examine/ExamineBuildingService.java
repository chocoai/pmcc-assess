package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineBuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/7/23 17:29
 * @Description:楼栋基础信息
 */
@Service
public class ExamineBuildingService {
    @Autowired
    private ExamineBuildingDao examineBuildingDao;
}

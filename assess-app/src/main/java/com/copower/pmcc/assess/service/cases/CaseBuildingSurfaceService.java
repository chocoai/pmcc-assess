package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingSurfaceDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/9/18 12:06
 * @Description:
 */
@Service
public class CaseBuildingSurfaceService {
    @Autowired
    private CaseBuildingSurfaceDao caseBuildingSurfaceDao;
}

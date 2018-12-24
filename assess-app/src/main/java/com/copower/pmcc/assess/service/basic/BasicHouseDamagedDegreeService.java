package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicHouseDamagedDegreeDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:59
 * @Description:房间
 */
@Service
public class BasicHouseDamagedDegreeService {
    @Autowired
    private BasicHouseDamagedDegreeDao basicHouseDamagedDegreeDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseDamagedDegree getBasicHouseDamagedDegreeById(Integer id) throws Exception {
        return basicHouseDamagedDegreeDao.getBasicHouseDamagedDegreeById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseDamagedDegree
     * @return
     * @throws Exception
     */
    public void saveAndUpdateBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) throws Exception {
        if (basicHouseDamagedDegree.getId() == null || basicHouseDamagedDegree.getId().intValue() == 0) {
            basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
            basicHouseDamagedDegreeDao.saveBasicHouseDamagedDegree(basicHouseDamagedDegree);
        } else {
            BasicHouseDamagedDegree oo = basicHouseDamagedDegreeDao.getBasicHouseDamagedDegreeById(basicHouseDamagedDegree.getId());
            basicHouseDamagedDegreeDao.updateBasicHouseDamagedDegree(basicHouseDamagedDegree);
        }
    }

    /**
     * 删除数据
     * @param basicHouseDamagedDegree
     * @return
     */
    public Boolean deleteBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree){
        return basicHouseDamagedDegreeDao.deleteBasicHouseDamagedDegree(basicHouseDamagedDegree);
    }



}

package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataPropertyDao;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:40
 * @Description:物业
 */
@Service
public class DataPropertyService {

    @Autowired
    private DataPropertyDao dataPropertyDao;
    @Autowired
    private CommonService commonService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean addDataProperty(DataProperty dataProperty){
        dataProperty.setCreator(commonService.thisUserAccount());
        return dataPropertyDao.addDataProperty(dataProperty);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public int addDataPropertyReturnId(DataProperty dataProperty){
        return dataPropertyDao.addDataPropertyReturnId(dataProperty);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public List<DataProperty> getDataPropertyList(String name){
        return dataPropertyDao.getDataPropertyList(name);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean deleteDataProperty(Integer id){
        return dataPropertyDao.deleteDataProperty(id);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public DataProperty getByDataPropertyId(Integer id){
        return dataPropertyDao.getByDataPropertyId(id);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean updateDataProperty(DataProperty dataProperty){
        return dataPropertyDao.updateDataProperty(dataProperty);
    }
}

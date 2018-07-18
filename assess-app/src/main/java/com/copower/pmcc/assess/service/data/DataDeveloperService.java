package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataDeveloperDao;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:36
 * @Description:开发商
 */
@Service
public class DataDeveloperService {
    @Autowired
    private DataDeveloperDao dataDeveloperDao;
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
    public boolean addDataDeveloper(DataDeveloper dataDeveloper){
        dataDeveloper.setCreator(commonService.thisUserAccount());
        return dataDeveloperDao.addDataDeveloper(dataDeveloper);
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
    public int addDataDeveloperReturnId(DataDeveloper dataDeveloper){
        return dataDeveloperDao.addDataDeveloperReturnId(dataDeveloper);
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
    public List<DataDeveloper> getDataDeveloperList(String name){
       return dataDeveloperDao.getDataDeveloperList(name);
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
    public boolean deleteDataDeveloper(Integer id){
        return  dataDeveloperDao.deleteDataDeveloper(id);
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
    public DataDeveloper getByDataDeveloperId(Integer id){
        return dataDeveloperDao.getByDataDeveloperId(id);
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
    public boolean updateDataDeveloper(DataDeveloper dataDeveloper){
        return dataDeveloperDao.updateDataDeveloper(dataDeveloper);
    }


}

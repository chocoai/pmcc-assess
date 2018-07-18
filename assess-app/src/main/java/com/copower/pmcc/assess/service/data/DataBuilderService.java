package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataBuilderDao;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:38
 * @Description:建造商
 */
@Service
public class DataBuilderService {

    @Autowired
    private DataBuilderDao dataBuilderDao;
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
    public boolean addDataBuilder(DataBuilder dataBuilder){
        dataBuilder.setCreator(commonService.thisUserAccount());
        return dataBuilderDao.addDataBuilder(dataBuilder);
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
    public int addDataBuilderReturnId(DataBuilder dataBuilder){
        return dataBuilderDao.addDataBuilderReturnId(dataBuilder);
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
    public List<DataBuilder> getDataBuilderList(String name){
        return dataBuilderDao.getDataBuilderList(name);
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
    public boolean deleteDataBuilder(Integer id){
        return  dataBuilderDao.deleteDataBuilder(id);
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
    public DataBuilder getByDataBuilderId(Integer id){
        return dataBuilderDao.getByDataBuilderId(id);
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
    public boolean updateDataBuilder(DataBuilder dataBuilder){
        return dataBuilderDao.updateDataBuilder(dataBuilder);
    }
}

package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloperExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataDeveloperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:14
 * @Description:
 */
@Repository
public class DataDeveloperDao {

    @Autowired
    private DataDeveloperMapper dataDeveloperMapper;

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
        return dataDeveloperMapper.insertSelective(dataDeveloper)==1;
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
        dataDeveloperMapper.insertSelective(dataDeveloper);
        return dataDeveloper.getId();
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
        StringBuilder builder = new StringBuilder(100);
        DataDeveloperExample example = new DataDeveloperExample();
        DataDeveloperExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!StringUtils.isEmpty(name)){
            builder.append("%").append(name).append("%");
            criteria.andNameLike(builder.toString());
        }
        return dataDeveloperMapper.selectByExample(example);
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
        return  dataDeveloperMapper.deleteByPrimaryKey(id)==1;
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
        return dataDeveloperMapper.selectByPrimaryKey(id);
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
        return dataDeveloperMapper.updateByPrimaryKeySelective(dataDeveloper)==1;
    }
}

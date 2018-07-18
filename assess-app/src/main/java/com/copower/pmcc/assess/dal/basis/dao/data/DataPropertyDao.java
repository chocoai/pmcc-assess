package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dal.basis.entity.DataPropertyExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataPropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:13
 * @Description:
 */
@Repository
public class DataPropertyDao {
    @Autowired
    private DataPropertyMapper dataPropertyMapper;

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
        return dataPropertyMapper.insertSelective(dataProperty)==1;
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
        dataPropertyMapper.insertSelective(dataProperty);
        return dataProperty.getId();
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
        StringBuilder builder = new StringBuilder(100);
        DataPropertyExample example = new DataPropertyExample();
        DataPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!StringUtils.isEmpty(name)){
            builder.append("%").append(name).append("%");
            criteria.andNameLike(builder.toString());
        }
        return dataPropertyMapper.selectByExample(example);
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
        return  dataPropertyMapper.deleteByPrimaryKey(id)==1;
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
        return dataPropertyMapper.selectByPrimaryKey(id);
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
        return dataPropertyMapper.updateByPrimaryKeySelective(dataProperty)==1;
    }
}

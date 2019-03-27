package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilderExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataBuilderMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class DataBuilderDao {
    @Autowired
    private DataBuilderMapper dataBuilderMapper;

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
        return dataBuilderMapper.insertSelective(dataBuilder)==1;
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
        dataBuilderMapper.insertSelective(dataBuilder);
        return dataBuilder.getId();
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
        StringBuilder builder = new StringBuilder(100);
        DataBuilderExample example = new DataBuilderExample();
        DataBuilderExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!StringUtils.isEmpty(name)){
            builder.append("%").append(name).append("%");
            criteria.andNameLike(builder.toString());
        }
        return dataBuilderMapper.selectByExample(example);
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
        return  dataBuilderMapper.deleteByPrimaryKey(id)==1;
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
        return dataBuilderMapper.selectByPrimaryKey(id);
    }

    public List<DataBuilder> dataBuilderList(DataBuilder oo){
        DataBuilderExample example = new DataBuilderExample();
        MybatisUtils.convertObj2Example(oo, example);
        return dataBuilderMapper.selectByExample(example);
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
        return dataBuilderMapper.updateByPrimaryKeySelective(dataBuilder)==1;
    }
}

package com.copower.pmcc.assess.dal.basis.dao.data;


import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegreeExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataDamagedDegreeMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by wangpc on 2017/8/16.
 */
@Repository
public class DataDamagedDegreeDao {
    @Autowired
    private DataDamagedDegreeMapper sysDamagedDegreeMapper;

    //region 获取所有数据字典列表

    /**
     * 获取所有数据字典列表
     *
     * @param fieldName
     * @param name
     * @return
     */
    public List<DataDamagedDegree> getListObject(String fieldName, String name) {
        DataDamagedDegreeExample example = new DataDamagedDegreeExample();
        DataDamagedDegreeExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (StringUtils.isBlank(fieldName) && StringUtils.isBlank(name)) {
            criteria.andPidEqualTo(0);
        }
        if (StringUtils.isNotBlank(fieldName)) {
            criteria.andFieldNameLike(MessageFormat.format("%{0}%", fieldName));
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        example.setOrderByClause("sorting");
        List<DataDamagedDegree> list = sysDamagedDegreeMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取数据根据pid

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public List<DataDamagedDegree> getListByPid(Integer pid, String search) {
        DataDamagedDegreeExample example = new DataDamagedDegreeExample();
        DataDamagedDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid).andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike(String.format("%%%s%%", search));
        }
        example.setOrderByClause("sorting");
        List<DataDamagedDegree> list = sysDamagedDegreeMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取可用数据根据pid

    /**
     * 获取可用数据根据pid
     *
     * @return
     */
    public List<DataDamagedDegree> getEnableListByPid(Integer pid) {
        DataDamagedDegreeExample example = new DataDamagedDegreeExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<DataDamagedDegree> list = sysDamagedDegreeMapper.selectByExample(example);
        return list;
    }
    //endregion

    /**
     * 获取可用数据根据字段名称
     *
     * @return
     */
    public List<DataDamagedDegree> getEnableList(String fieldName) {
        DataDamagedDegree sysDamagedDegree = getSingleObject(fieldName);
        if (sysDamagedDegree == null) return null;
        return getEnableListByPid(sysDamagedDegree.getId());
    }
    //endregion

    /**
     * 获取单条数据by字段名称
     *
     * @return
     */
    public DataDamagedDegree getSingleObject(String fieldName) {
        if (StringUtils.isBlank(fieldName)) return null;
        DataDamagedDegreeExample example = new DataDamagedDegreeExample();
        example.createCriteria().andFieldNameEqualTo(fieldName);
        example.setOrderByClause("sorting");
        List<DataDamagedDegree> list = sysDamagedDegreeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list))
            return null;
        return list.get(0);
    }
    //endregion


    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public DataDamagedDegree getSingleObject(Integer id) {
        DataDamagedDegree sysDamagedDegree = sysDamagedDegreeMapper.selectByPrimaryKey(id);
        return sysDamagedDegree;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param sysDamagedDegree
     * @return
     */
    public boolean addObject(DataDamagedDegree sysDamagedDegree) {
        if (sysDamagedDegree == null) return false;
        return sysDamagedDegreeMapper.insertSelective(sysDamagedDegree) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param sysDamagedDegree
     * @return
     */
    public boolean updateObject(DataDamagedDegree sysDamagedDegree) {
        if (sysDamagedDegree == null) return false;
        return sysDamagedDegreeMapper.updateByPrimaryKeySelective(sysDamagedDegree) > 0;
    }
    //endregion

    /**
     * 根据名称判断是否已存在
     *
     * @param filedName
     * @param id
     * @return
     */
    public boolean isExist(String filedName, Integer id) {
        if (StringUtils.isBlank(filedName)) return false;
        DataDamagedDegreeExample example = new DataDamagedDegreeExample();
        DataDamagedDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andFieldNameEqualTo(filedName);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return sysDamagedDegreeMapper.countByExample(example) > 0;
    }


}

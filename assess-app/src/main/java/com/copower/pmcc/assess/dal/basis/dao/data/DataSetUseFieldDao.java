package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataSetUseFieldMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class DataSetUseFieldDao {
    @Autowired
    private DataSetUseFieldMapper dataSetUseFieldMapper;

    //region 获取所有项目分类列表

    /**
     * 获取所有项目分类列表
     *
     * @param fieldName
     * @param name
     * @return
     */
    public List<DataSetUseField> getListObject(String name, String fieldName, Integer pid) {
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        DataSetUseFieldExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(fieldName)) {
            criteria.andFieldNameLike(MessageFormat.format("%{0}%", fieldName));
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (pid != null) {
            criteria.andPidEqualTo(pid);
        }
        example.setOrderByClause("sorting");
        List<DataSetUseField> list = dataSetUseFieldMapper.selectByExample(example);
        return list;
    }
    //endregion


    public List<DataSetUseField> getProjectClassifyList(DataSetUseField baseProjectClassify) {
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        MybatisUtils.convertObj2Example(baseProjectClassify, example);
        return dataSetUseFieldMapper.selectByExample(example);
    }

    //region 获取数据根据pid

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public List<DataSetUseField> getListByPid(Integer pid, String search) {
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        DataSetUseFieldExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid).andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike(search);
        }
        example.setOrderByClause("sorting");
        List<DataSetUseField> list = dataSetUseFieldMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取可用数据根据pid

    /**
     * 获取可用数据根据pid
     *
     * @return
     */
    public List<DataSetUseField> getEnableListByPid(Integer pid) {
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<DataSetUseField> list = dataSetUseFieldMapper.selectByExample(example);
        return list;
    }
    //endregion

    public List<DataSetUseField> getEnableListByPids(List<Integer> integers) {
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        example.createCriteria().andPidIn(integers);
        return dataSetUseFieldMapper.selectByExample(example);
    }

    /**
     * 获取可用数据根据字段名称
     *
     * @return
     */
    public List<DataSetUseField> getEnableList(String fieldName) {
        DataSetUseField sysProjectClassify = getSingleObject(fieldName);
        if (sysProjectClassify == null) return null;
        return getEnableListByPid(sysProjectClassify.getId());
    }
    //endregion

    /**
     * 获取单条数据by字段名称
     *
     * @return
     */
    public DataSetUseField getSingleObject(String fieldName) {
        if (StringUtils.isBlank(fieldName)) return null;
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        example.createCriteria().andFieldNameEqualTo(fieldName);
        example.setOrderByClause("sorting");
        List<DataSetUseField> list = dataSetUseFieldMapper.selectByExample(example);
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
    public DataSetUseField getSingleObject(Integer id) {
        DataSetUseField sysProjectClassify = dataSetUseFieldMapper.selectByPrimaryKey(id);
        return sysProjectClassify;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param sysProjectClassify
     * @return
     */
    public boolean addObject(DataSetUseField sysProjectClassify) {
        if (sysProjectClassify == null) return false;
        return dataSetUseFieldMapper.insertSelective(sysProjectClassify) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param sysProjectClassify
     * @return
     */
    public boolean updateObject(DataSetUseField sysProjectClassify) {
        if (sysProjectClassify == null) return false;
        return dataSetUseFieldMapper.updateByPrimaryKeySelective(sysProjectClassify) > 0;
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
        DataSetUseFieldExample example = new DataSetUseFieldExample();
        DataSetUseFieldExample.Criteria criteria = example.createCriteria();
        criteria.andFieldNameEqualTo(filedName);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return dataSetUseFieldMapper.countByExample(example) > 0;
    }


}

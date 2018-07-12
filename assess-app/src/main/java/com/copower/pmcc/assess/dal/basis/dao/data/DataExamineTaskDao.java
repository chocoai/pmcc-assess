package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.DataExamineTaskExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataExamineTaskMapper;
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
public class DataExamineTaskDao {
    @Autowired
    private DataExamineTaskMapper dataExamineTaskMapper;

    //region 获取所有项目分类列表

    /**
     * 获取所有项目分类列表
     *
     * @param fieldName
     * @param name
     * @return
     */
    public List<DataExamineTask> getListObject(String name, String fieldName, Integer pid) {
        DataExamineTaskExample example = new DataExamineTaskExample();
        DataExamineTaskExample.Criteria criteria = example.createCriteria()
                .andBisEnableEqualTo(true)
                .andBisDeleteEqualTo(false);
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
        List<DataExamineTask> list = dataExamineTaskMapper.selectByExample(example);
        return list;
    }
    //endregion


    public List<DataExamineTask> getProjectClassifyList(DataExamineTask baseProjectClassify) {
        DataExamineTaskExample example = new DataExamineTaskExample();
        MybatisUtils.convertObj2Example(baseProjectClassify, example);
        return dataExamineTaskMapper.selectByExample(example);
    }

    //region 获取数据根据pid

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public List<DataExamineTask> getListByPid(Integer pid, String search) {
        DataExamineTaskExample example = new DataExamineTaskExample();
        DataExamineTaskExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid).andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike(search);
        }
        example.setOrderByClause("sorting");
        List<DataExamineTask> list = dataExamineTaskMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取可用数据根据pid

    /**
     * 获取可用数据根据pid
     *
     * @return
     */
    public List<DataExamineTask> getEnableListByPid(Integer pid) {
        DataExamineTaskExample example = new DataExamineTaskExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<DataExamineTask> list = dataExamineTaskMapper.selectByExample(example);
        return list;
    }
    //endregion

    public List<DataExamineTask> getEnableListByPids(List<Integer> integers) {
        DataExamineTaskExample example = new DataExamineTaskExample();
        example.createCriteria().andPidIn(integers);
        return dataExamineTaskMapper.selectByExample(example);
    }

    /**
     * 获取可用数据根据字段名称
     *
     * @return
     */
    public List<DataExamineTask> getEnableList(String fieldName) {
        DataExamineTask sysProjectClassify = getSingleObject(fieldName);
        if (sysProjectClassify == null) return null;
        return getEnableListByPid(sysProjectClassify.getId());
    }
    //endregion

    /**
     * 获取单条数据by字段名称
     *
     * @return
     */
    public DataExamineTask getSingleObject(String fieldName) {
        if (StringUtils.isBlank(fieldName)) return null;
        DataExamineTaskExample example = new DataExamineTaskExample();
        example.createCriteria().andFieldNameEqualTo(fieldName);
        example.setOrderByClause("sorting");
        List<DataExamineTask> list = dataExamineTaskMapper.selectByExample(example);
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
    public DataExamineTask getSingleObject(Integer id) {
        DataExamineTask sysProjectClassify = dataExamineTaskMapper.selectByPrimaryKey(id);
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
    public boolean addObject(DataExamineTask sysProjectClassify) {
        if (sysProjectClassify == null) return false;
        return dataExamineTaskMapper.insertSelective(sysProjectClassify) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param sysProjectClassify
     * @return
     */
    public boolean updateObject(DataExamineTask sysProjectClassify) {
        if (sysProjectClassify == null) return false;
        return dataExamineTaskMapper.updateByPrimaryKeySelective(sysProjectClassify) > 0;
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
        DataExamineTaskExample example = new DataExamineTaskExample();
        DataExamineTaskExample.Criteria criteria = example.createCriteria();
        criteria.andFieldNameEqualTo(filedName);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return dataExamineTaskMapper.countByExample(example) > 0;
    }


}

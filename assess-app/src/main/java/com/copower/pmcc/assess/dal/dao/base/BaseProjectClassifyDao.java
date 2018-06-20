package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.entity.BaseProjectClassifyExample;
import com.copower.pmcc.assess.dal.mapper.BaseProjectClassifyMapper;
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
public class BaseProjectClassifyDao {
    @Autowired
    private BaseProjectClassifyMapper baseProjectClassifyMapper;

    //region 获取所有项目分类列表

    /**
     * 获取所有项目分类列表
     *
     * @param fieldName
     * @param name
     * @return
     */
    public List<BaseProjectClassify> getListObject(String name, String fieldName, Integer pid) {
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        BaseProjectClassifyExample.Criteria criteria = example.createCriteria()
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
        List<BaseProjectClassify> list = baseProjectClassifyMapper.selectByExample(example);
        return list;
    }
    //endregion


    public List<BaseProjectClassify> getProjectClassifyList(BaseProjectClassify baseProjectClassify) {
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        MybatisUtils.convertObj2Example(baseProjectClassify, example);
        return baseProjectClassifyMapper.selectByExample(example);
    }

    //region 获取数据根据pid

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public List<BaseProjectClassify> getListByPid(Integer pid, String search) {
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        BaseProjectClassifyExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid).andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike(search);
        }
        example.setOrderByClause("sorting");
        List<BaseProjectClassify> list = baseProjectClassifyMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取可用数据根据pid

    /**
     * 获取可用数据根据pid
     *
     * @return
     */
    public List<BaseProjectClassify> getEnableListByPid(Integer pid) {
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<BaseProjectClassify> list = baseProjectClassifyMapper.selectByExample(example);
        return list;
    }
    //endregion

    public List<BaseProjectClassify> getEnableListByPids(List<Integer> integers) {
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        example.createCriteria().andPidIn(integers);
        return baseProjectClassifyMapper.selectByExample(example);
    }

    /**
     * 获取可用数据根据字段名称
     *
     * @return
     */
    public List<BaseProjectClassify> getEnableList(String fieldName) {
        BaseProjectClassify sysProjectClassify = getSingleObject(fieldName);
        if (sysProjectClassify == null) return null;
        return getEnableListByPid(sysProjectClassify.getId());
    }
    //endregion

    /**
     * 获取单条数据by字段名称
     *
     * @return
     */
    public BaseProjectClassify getSingleObject(String fieldName) {
        if (StringUtils.isBlank(fieldName)) return null;
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        example.createCriteria().andFieldNameEqualTo(fieldName);
        example.setOrderByClause("sorting");
        List<BaseProjectClassify> list = baseProjectClassifyMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list))
            return null;
        return list.get(0);
    }
    //endregion

    /**
     * 获取可用数据根据groupName
     *
     * @return
     */
    public List<BaseProjectClassify> getEnableListByGroupName(String groupKey) {
        if (StringUtils.isBlank(groupKey)) return null;
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<BaseProjectClassify> list = baseProjectClassifyMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public BaseProjectClassify getSingleObject(Integer id) {
        BaseProjectClassify sysProjectClassify = baseProjectClassifyMapper.selectByPrimaryKey(id);
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
    public boolean addObject(BaseProjectClassify sysProjectClassify) {
        if (sysProjectClassify == null) return false;
        return baseProjectClassifyMapper.insertSelective(sysProjectClassify) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param sysProjectClassify
     * @return
     */
    public boolean updateObject(BaseProjectClassify sysProjectClassify) {
        if (sysProjectClassify == null) return false;
        return baseProjectClassifyMapper.updateByPrimaryKeySelective(sysProjectClassify) > 0;
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
        BaseProjectClassifyExample example = new BaseProjectClassifyExample();
        BaseProjectClassifyExample.Criteria criteria = example.createCriteria();
        criteria.andFieldNameEqualTo(filedName);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return baseProjectClassifyMapper.countByExample(example) > 0;
    }


}

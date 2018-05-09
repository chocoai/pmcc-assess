package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.BaseDataDicExample;
import com.copower.pmcc.assess.dal.mapper.BaseDataDicMapper;
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
public class BaseDataDicDao {
    @Autowired
    private BaseDataDicMapper sysDataDicMapper;

    //region 获取所有数据字典列表

    /**
     * 获取所有数据字典列表
     *
     * @param fieldName
     * @param name
     * @return
     */
    public List<BaseDataDic> getListObject(String fieldName, String name) {
        BaseDataDicExample example = new BaseDataDicExample();
        BaseDataDicExample.Criteria criteria = example.createCriteria()
                .andPidEqualTo(0)
                .andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(fieldName)) {
            criteria.andFieldNameLike(MessageFormat.format("%{0}%",fieldName));
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike("%"+name+"%");
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike("%"+name+"%");
        }
        example.setOrderByClause("sorting");
        List<BaseDataDic> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取数据根据pid

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public List<BaseDataDic> getListByPid(Integer pid,String search) {
        BaseDataDicExample example = new BaseDataDicExample();
        BaseDataDicExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid).andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike(search);
        }
        example.setOrderByClause("sorting");
        List<BaseDataDic> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取可用数据根据pid

    /**
     * 获取可用数据根据pid
     *
     * @return
     */
    public List<BaseDataDic> getEnableListByPid(Integer pid) {
        BaseDataDicExample example = new BaseDataDicExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<BaseDataDic> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    /**
     * 获取可用数据根据字段名称
     *
     * @return
     */
    public List<BaseDataDic> getEnableList(String fieldName) {
        BaseDataDic sysDataDic = getSingleObject(fieldName);
        if(sysDataDic==null) return null;
        return getEnableListByPid(sysDataDic.getId());
    }
    //endregion

    /**
     * 获取单条数据by字段名称
     *
     * @return
     */
    public BaseDataDic getSingleObject(String fieldName) {
        if(StringUtils.isBlank(fieldName)) return null;
        BaseDataDicExample example = new BaseDataDicExample();
        example.createCriteria().andFieldNameEqualTo(fieldName);
        example.setOrderByClause("sorting");
        List<BaseDataDic> list = sysDataDicMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list))
            return null;
        return list.get(0);
    }
    //endregion

    /**
     * 获取可用数据根据groupName
     *
     * @return
     */
    public List<BaseDataDic> getEnableListByGroupName(String groupKey) {
        if(StringUtils.isBlank(groupKey))return null;
        BaseDataDicExample example = new BaseDataDicExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<BaseDataDic> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public BaseDataDic getSingleObject(Integer id) {
        BaseDataDic sysDataDic = sysDataDicMapper.selectByPrimaryKey(id);
        return sysDataDic;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param sysDataDic
     * @return
     */
    public boolean addObject(BaseDataDic sysDataDic) {
        if (sysDataDic == null) return false;
        return sysDataDicMapper.insertSelective(sysDataDic) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param sysDataDic
     * @return
     */
    public boolean updateObject(BaseDataDic sysDataDic) {
        if (sysDataDic == null) return false;
        return sysDataDicMapper.updateByPrimaryKeySelective(sysDataDic) > 0;
    }
    //endregion

    /**
     * 根据名称判断是否已存在
     * @param filedName
     * @param id
     * @return
     */
    public boolean isExist(String filedName, Integer id) {
        if(StringUtils.isBlank(filedName)) return false;
        BaseDataDicExample example = new BaseDataDicExample();
        BaseDataDicExample.Criteria criteria = example.createCriteria();
        criteria.andFieldNameEqualTo(filedName);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return sysDataDicMapper.countByExample(example) > 0;
    }
}

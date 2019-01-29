package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.BaseReportFieldMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/14 14:13
 * @Description:
 */
@Repository
public class BaseReportFieldDao {

    @Autowired
    private BaseReportFieldMapper sysDataDicMapper;

    public boolean delete(Integer id){
        return sysDataDicMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BaseReportField> query(BaseReportField baseReportField){
        BaseReportFieldExample example = new BaseReportFieldExample();
        MybatisUtils.convertObj2Example(baseReportField, example);
        example.setOrderByClause("sorting");
        return sysDataDicMapper.selectByExample(example);
    }

    //region 获取所有数据字典列表

    /**
     * 获取所有数据字典列表
     *
     * @param fieldName
     * @param name
     * @return
     */
    public List<BaseReportField> getListObject(String fieldName, String name) {
        BaseReportFieldExample example = new BaseReportFieldExample();
        BaseReportFieldExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (StringUtils.isBlank(fieldName) && StringUtils.isBlank(name)) {
            criteria.andPidEqualTo(0);
        }
        if (StringUtils.isNotBlank(fieldName)) {
            criteria.andFieldNameLike(MessageFormat.format("%{0}%", fieldName));
        }
        if (StringUtils.isNotBlank(name)) {
//            criteria.andNameLike(String.format("%%%s%%", name));
            criteria.andNameEqualTo(name);
        }
        example.setOrderByClause("sorting");
        List<BaseReportField> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取数据根据pid

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public List<BaseReportField> getListByPid(Integer pid, String search) {
        BaseReportFieldExample example = new BaseReportFieldExample();
        BaseReportFieldExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid).andBisDeleteEqualTo(false);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike(String.format("%%%s%%", search));
        }
        example.setOrderByClause("sorting");
        List<BaseReportField> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    //region 获取可用数据根据pid

    /**
     * 获取可用数据根据pid
     *
     * @return
     */
    public List<BaseReportField> getEnableListByPid(Integer pid) {
        BaseReportFieldExample example = new BaseReportFieldExample();
        example.createCriteria()
                .andBisEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting");
        List<BaseReportField> list = sysDataDicMapper.selectByExample(example);
        return list;
    }
    //endregion

    /**
     * 获取可用数据根据字段名称
     *
     * @return
     */
    public List<BaseReportField> getEnableList(String fieldName) {
        BaseReportField sysDataDic = getSingleObject(fieldName);
        if (sysDataDic == null){
            return null;
        }
        return getEnableListByPid(sysDataDic.getId());
    }
    //endregion

    /**
     * 获取单条数据by字段名称
     *
     * @return
     */
    public BaseReportField getSingleObject(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        BaseReportFieldExample example = new BaseReportFieldExample();
        example.createCriteria().andFieldNameEqualTo(fieldName);
        example.setOrderByClause("sorting");
        List<BaseReportField> list = sysDataDicMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
    //endregion


    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public BaseReportField getSingleObject(Integer id) {
        BaseReportField sysDataDic = sysDataDicMapper.selectByPrimaryKey(id);
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
    public boolean addObject(BaseReportField sysDataDic) {
        if (sysDataDic == null) {
            return false;
        }
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
    public boolean updateObject(BaseReportField sysDataDic) {
        if (sysDataDic == null) {
            return false;
        }
        return sysDataDicMapper.updateByPrimaryKeySelective(sysDataDic) > 0;
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
        if (StringUtils.isBlank(filedName)) {
            return false;
        }
        BaseReportFieldExample example = new BaseReportFieldExample();
        BaseReportFieldExample.Criteria criteria = example.createCriteria();
        criteria.andFieldNameEqualTo(filedName);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return sysDataDicMapper.countByExample(example) > 0;
    }

}

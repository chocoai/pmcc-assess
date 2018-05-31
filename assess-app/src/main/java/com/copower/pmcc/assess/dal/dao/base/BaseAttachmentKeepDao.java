package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseAttachmentKeep;
import com.copower.pmcc.assess.dal.entity.BaseAttachmentKeepExample;
import com.copower.pmcc.assess.dal.mapper.BaseAttachmentKeepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangpc on 2017/8/16.
 */
@Repository
public class BaseAttachmentKeepDao {
    @Autowired
    private BaseAttachmentKeepMapper sysAttachmentKeepMapper;

    //region 获取数据列表

    /**
     * 获取数据列表
     *
     * @param attachmentId
     * @return
     */
    public List<BaseAttachmentKeep> getListObject(Integer attachmentId) {
        BaseAttachmentKeepExample example = new BaseAttachmentKeepExample();
        BaseAttachmentKeepExample.Criteria criteria = example.createCriteria();
        criteria.andAttachmentIdEqualTo(attachmentId);
        example.setOrderByClause("id");
        List<BaseAttachmentKeep> list = sysAttachmentKeepMapper.selectByExample(example);
        return list;
    }
    //endregion


    //region 获取单条数据

    /**
     * 获取数据根据pid
     *
     * @return
     */
    public BaseAttachmentKeep getSingleObject(Integer id) {
        BaseAttachmentKeep sysAttachmentKeep = sysAttachmentKeepMapper.selectByPrimaryKey(id);
        return sysAttachmentKeep;
    }
    //endregion

    //region 添加数据信息

    /**
     * 添加数据信息
     *
     * @param sysAttachmentKeep
     * @return
     */
    public boolean addObject(BaseAttachmentKeep sysAttachmentKeep) {
        if (sysAttachmentKeep == null) return false;
        return sysAttachmentKeepMapper.insertSelective(sysAttachmentKeep) > 0;
    }
    //endregion

    //region 更新数据信息

    /**
     * 更新数据信息
     *
     * @param sysAttachmentKeep
     * @return
     */
    public boolean updateObject(BaseAttachmentKeep sysAttachmentKeep) {
        if (sysAttachmentKeep == null) return false;
        return sysAttachmentKeepMapper.updateByPrimaryKeySelective(sysAttachmentKeep) > 0;
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @return
     */
    public boolean deleteObject(Integer id) {
        if (id == null) return false;
        return sysAttachmentKeepMapper.deleteByPrimaryKey(id) > 0;
    }
    //endregion

    /**
     * 获取数据条数
     *
     * @param attachmentId
     * @return
     */
    public int getCount(Integer attachmentId) {
        if (attachmentId == null) return 0;
        BaseAttachmentKeepExample example = new BaseAttachmentKeepExample();
        BaseAttachmentKeepExample.Criteria criteria = example.createCriteria();
        criteria.andAttachmentIdEqualTo(attachmentId);
        return sysAttachmentKeepMapper.countByExample(example);
    }
}

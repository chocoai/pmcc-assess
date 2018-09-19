package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseFileTemplate;
import com.copower.pmcc.assess.dal.basis.entity.BaseFileTemplateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BaseFileTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class BaseFileTemplateDao {
    @Autowired
    private BaseFileTemplateMapper baseFileTemplateMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public BaseFileTemplate getFileTemplateById(Integer id) {
        return baseFileTemplateMapper.selectByPrimaryKey(id);
    }


    public BaseFileTemplate getFileTemplateByName(String name) {
        BaseFileTemplateExample example = new BaseFileTemplateExample();
        BaseFileTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<BaseFileTemplate> baseFileTemplates = baseFileTemplateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(baseFileTemplates)) return null;
        return baseFileTemplates.get(0);
    }

    /**
     * 获取数据列表
     *
     * @param name
     * @param remark
     * @return
     */
    public List<BaseFileTemplate> getFileTemplateList(String name, String remark) {
        BaseFileTemplateExample example = new BaseFileTemplateExample();
        BaseFileTemplateExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (StringUtils.isNotBlank(remark)) {
            criteria.andRemarkLike(String.format("%%%s%%", remark));
        }
        return baseFileTemplateMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineFileTemplate
     * @return
     */
    public boolean addFileTemplate(BaseFileTemplate examineFileTemplate) {
        return baseFileTemplateMapper.insertSelective(examineFileTemplate) > 0;
    }

    /**
     * 编辑
     *
     * @param examineFileTemplate
     * @return
     */
    public boolean updateFileTemplate(BaseFileTemplate examineFileTemplate) {
        return baseFileTemplateMapper.updateByPrimaryKeySelective(examineFileTemplate) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteFileTemplate(Integer id) {
        return baseFileTemplateMapper.deleteByPrimaryKey(id) > 0;
    }

}
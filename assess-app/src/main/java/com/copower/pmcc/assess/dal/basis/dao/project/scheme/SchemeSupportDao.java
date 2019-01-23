package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupport;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeSupportMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class SchemeSupportDao {
    @Autowired
    private SchemeSupportMapper schemeSupportMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeSupport getSupportById(Integer id) {
        return schemeSupportMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeSupport
     * @return
     */
    public SchemeSupport getSchemeSupport(SchemeSupport schemeSupport) {
        SchemeSupportExample example = new SchemeSupportExample();
        MybatisUtils.convertObj2Example(schemeSupport, example);
        List<SchemeSupport> schemeSupports = schemeSupportMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeSupports)) return schemeSupports.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineSupport
     * @return
     */
    public List<SchemeSupport> getSupportList(SchemeSupport examineSupport) {
        SchemeSupportExample example = new SchemeSupportExample();
        MybatisUtils.convertObj2Example(examineSupport, example);
        return schemeSupportMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineSupport
     * @return
     */
    public boolean addSupport(SchemeSupport examineSupport) {
        return schemeSupportMapper.insertSelective(examineSupport) > 0;
    }

    /**
     * 编辑
     *
     * @param examineSupport
     * @return
     */
    public boolean updateSupport(SchemeSupport examineSupport) {
        return schemeSupportMapper.updateByPrimaryKeySelective(examineSupport) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteSupport(Integer id) {
        return schemeSupportMapper.deleteByPrimaryKey(id) > 0;
    }


}

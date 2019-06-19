package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeLiquidationAnalysisGroupMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class SchemeLiquidationAnalysisGroupDao {
    @Autowired
    private SchemeLiquidationAnalysisGroupMapper schemeLiquidationAnalysisGroupMapper;

    public SchemeLiquidationAnalysisGroup getSchemeLiquidationAnalysisGroup(Integer id) {
        return schemeLiquidationAnalysisGroupMapper.selectByPrimaryKey(id);
    }


    public List<SchemeLiquidationAnalysisGroup> getObjectList(SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup) {
        SchemeLiquidationAnalysisGroupExample example = new SchemeLiquidationAnalysisGroupExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysisGroup, example);
        return schemeLiquidationAnalysisGroupMapper.selectByExample(example);
    }

    public boolean addSchemeLiquidationAnalysisGroup(SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup) {
        int i = schemeLiquidationAnalysisGroupMapper.insertSelective(schemeLiquidationAnalysisGroup);
        return i > 0;
    }

    public boolean editSchemeLiquidationAnalysisGroup(SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup) {
        int i = schemeLiquidationAnalysisGroupMapper.updateByPrimaryKeySelective(schemeLiquidationAnalysisGroup);
        return i > 0;
    }

    public boolean deleteSchemeLiquidationAnalysisGroup(Integer id) {
        int i = schemeLiquidationAnalysisGroupMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SchemeLiquidationAnalysisGroup getSchemeLiquidationAnalysisGroup(SchemeLiquidationAnalysisGroup schemeLiquidationAnalysisGroup) {
        SchemeLiquidationAnalysisGroupExample example = new SchemeLiquidationAnalysisGroupExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysisGroup, example);
        List<SchemeLiquidationAnalysisGroup> list = schemeLiquidationAnalysisGroupMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) return list.get(0);
        return null;
    }

}

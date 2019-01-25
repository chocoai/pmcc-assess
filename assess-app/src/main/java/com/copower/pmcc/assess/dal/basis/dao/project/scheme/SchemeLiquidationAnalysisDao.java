package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeLiquidationAnalysisMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class SchemeLiquidationAnalysisDao {
    @Autowired
    private SchemeLiquidationAnalysisMapper schemeLiquidationAnalysisMapper;

    public SchemeLiquidationAnalysis getSchemeLiquidationAnalysis(Integer id) {
        return schemeLiquidationAnalysisMapper.selectByPrimaryKey(id);
    }


    public List<SchemeLiquidationAnalysis> getObjectList(SchemeLiquidationAnalysis schemeLiquidationAnalysis) {
        SchemeLiquidationAnalysisExample example = new SchemeLiquidationAnalysisExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysis, example);
        return schemeLiquidationAnalysisMapper.selectByExample(example);
    }

    public boolean addSchemeLiquidationAnalysis(SchemeLiquidationAnalysis schemeLiquidationAnalysis) {
        int i = schemeLiquidationAnalysisMapper.insertSelective(schemeLiquidationAnalysis);
        return i > 0;
    }

    public boolean editSchemeLiquidationAnalysis(SchemeLiquidationAnalysis schemeLiquidationAnalysis) {
        int i = schemeLiquidationAnalysisMapper.updateByPrimaryKeySelective(schemeLiquidationAnalysis);
        return i > 0;
    }

    public boolean deleteSchemeLiquidationAnalysis(Integer id) {
        int i = schemeLiquidationAnalysisMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SchemeLiquidationAnalysis getSchemeLiquidationAnalysis(SchemeLiquidationAnalysis schemeLiquidationAnalysis) {
        SchemeLiquidationAnalysisExample example = new SchemeLiquidationAnalysisExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysis, example);
        List<SchemeLiquidationAnalysis> list = schemeLiquidationAnalysisMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) return list.get(0);
        return null;
    }
}

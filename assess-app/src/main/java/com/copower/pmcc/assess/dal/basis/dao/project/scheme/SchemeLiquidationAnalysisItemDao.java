package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeLiquidationAnalysisItemMapper;
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
public class SchemeLiquidationAnalysisItemDao {
    @Autowired
    private SchemeLiquidationAnalysisItemMapper schemeLiquidationAnalysisItemMapper;

    public SchemeLiquidationAnalysisItem getSchemeLiquidationAnalysisItem(Integer id) {
        return schemeLiquidationAnalysisItemMapper.selectByPrimaryKey(id);
    }


    public List<SchemeLiquidationAnalysisItem> getObjectList(SchemeLiquidationAnalysisItem schemeLiquidationAnalysisItem) {
        SchemeLiquidationAnalysisItemExample example = new SchemeLiquidationAnalysisItemExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysisItem, example);
        return schemeLiquidationAnalysisItemMapper.selectByExample(example);
    }

    public boolean addSchemeLiquidationAnalysisItem(SchemeLiquidationAnalysisItem schemeLiquidationAnalysisItem) {
        int i = schemeLiquidationAnalysisItemMapper.insert(schemeLiquidationAnalysisItem);
        return i > 0;
    }

    public boolean editSchemeLiquidationAnalysisItem(SchemeLiquidationAnalysisItem schemeLiquidationAnalysisItem) {
        int i = schemeLiquidationAnalysisItemMapper.updateByPrimaryKeySelective(schemeLiquidationAnalysisItem);
        return i > 0;
    }

    public boolean deleteSchemeLiquidationAnalysisItem(Integer id) {
        int i = schemeLiquidationAnalysisItemMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public SchemeLiquidationAnalysisItem getSchemeLiquidationAnalysisItem(SchemeLiquidationAnalysisItem schemeLiquidationAnalysisItem) {
        SchemeLiquidationAnalysisItemExample example = new SchemeLiquidationAnalysisItemExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysisItem, example);
        List<SchemeLiquidationAnalysisItem> list = schemeLiquidationAnalysisItemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) return list.get(0);
        return null;
    }

}

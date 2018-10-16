package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeCertAdjustmentFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeCertAdjustmentFactorExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeCertAdjustmentFactorMapper;
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
public class SchemeCertAdjustmentFactorDao {
    @Autowired
    private SchemeCertAdjustmentFactorMapper schemeCertAdjustmentFactorMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeCertAdjustmentFactor getCertAdjustmentFactorById(Integer id) {
        return schemeCertAdjustmentFactorMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeCertAdjustmentFactor
     * @return
     */
    public SchemeCertAdjustmentFactor getSchemeCertAdjustmentFactor(SchemeCertAdjustmentFactor schemeCertAdjustmentFactor) {
        SchemeCertAdjustmentFactorExample example = new SchemeCertAdjustmentFactorExample();
        MybatisUtils.convertObj2Example(schemeCertAdjustmentFactor, example);
        List<SchemeCertAdjustmentFactor> schemeCertAdjustmentFactors = schemeCertAdjustmentFactorMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeCertAdjustmentFactors)) return schemeCertAdjustmentFactors.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineCertAdjustmentFactor
     * @return
     */
    public List<SchemeCertAdjustmentFactor> getCertAdjustmentFactorList(SchemeCertAdjustmentFactor examineCertAdjustmentFactor) {
        SchemeCertAdjustmentFactorExample example = new SchemeCertAdjustmentFactorExample();
        MybatisUtils.convertObj2Example(examineCertAdjustmentFactor, example);
        return schemeCertAdjustmentFactorMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineCertAdjustmentFactor
     * @return
     */
    public boolean addCertAdjustmentFactor(SchemeCertAdjustmentFactor examineCertAdjustmentFactor) {
        return schemeCertAdjustmentFactorMapper.insertSelective(examineCertAdjustmentFactor) > 0;
    }

    /**
     * 编辑
     *
     * @param examineCertAdjustmentFactor
     * @return
     */
    public boolean updateCertAdjustmentFactor(SchemeCertAdjustmentFactor examineCertAdjustmentFactor) {
        return schemeCertAdjustmentFactorMapper.updateByPrimaryKeySelective(examineCertAdjustmentFactor) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCertAdjustmentFactor(Integer id) {
        return schemeCertAdjustmentFactorMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean deleteCertAdjustmentFactorByDeclareId(Integer declareId) {
        SchemeCertAdjustmentFactorExample example = new SchemeCertAdjustmentFactorExample();
        example.createCriteria().andDeclareIdEqualTo(declareId);
        return schemeCertAdjustmentFactorMapper.deleteByExample(example) > 0;
    }
}

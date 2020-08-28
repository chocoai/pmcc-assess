package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactorExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeSurePriceFactorMapper;
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
public class SchemeSurePriceFactorDao {
    @Autowired
    private SchemeSurePriceFactorMapper schemeSurePriceFactorMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeSurePriceFactor getSurePriceFactorById(Integer id) {
        return schemeSurePriceFactorMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeSurePriceFactor
     * @return
     */
    public SchemeSurePriceFactor getSchemeSurePriceFactor(SchemeSurePriceFactor schemeSurePriceFactor) {
        SchemeSurePriceFactorExample example = new SchemeSurePriceFactorExample();
        MybatisUtils.convertObj2Example(schemeSurePriceFactor, example);
        List<SchemeSurePriceFactor> schemeSurePriceFactors = schemeSurePriceFactorMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeSurePriceFactors)) return schemeSurePriceFactors.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineSurePriceFactor
     * @return
     */
    public List<SchemeSurePriceFactor> getSurePriceFactorList(SchemeSurePriceFactor examineSurePriceFactor) {
        SchemeSurePriceFactorExample example = new SchemeSurePriceFactorExample();
        MybatisUtils.convertObj2Example(examineSurePriceFactor, example);
        return schemeSurePriceFactorMapper.selectByExample(example);
    }

    public List<SchemeSurePriceFactor> getFactorListByJudgeObjectId(Integer judgeObjectId) {
        SchemeSurePriceFactorExample example = new SchemeSurePriceFactorExample();
        example.createCriteria().andJudgeObjectIdEqualTo(judgeObjectId);
        return schemeSurePriceFactorMapper.selectByExample(example);
    }

    public List<SchemeSurePriceFactor> getFactorListByJudgeObjectIds(List<Integer> judgeObjectIds) {
        SchemeSurePriceFactorExample example = new SchemeSurePriceFactorExample();
        example.createCriteria().andJudgeObjectIdIn(judgeObjectIds);
        return schemeSurePriceFactorMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineSurePriceFactor
     * @return
     */
    public boolean addSurePriceFactor(SchemeSurePriceFactor examineSurePriceFactor) {
        return schemeSurePriceFactorMapper.insertSelective(examineSurePriceFactor) > 0;
    }

    /**
     * 编辑
     *
     * @param examineSurePriceFactor
     * @return
     */
    public boolean updateSurePriceFactor(SchemeSurePriceFactor examineSurePriceFactor) {
        return schemeSurePriceFactorMapper.updateByPrimaryKeySelective(examineSurePriceFactor) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteSurePriceFactor(Integer id) {
        return schemeSurePriceFactorMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean deleteSurePriceFactorByJudgeObjectId(Integer declareId) {
        SchemeSurePriceFactorExample example = new SchemeSurePriceFactorExample();
        example.createCriteria().andJudgeObjectIdEqualTo(declareId);
        return schemeSurePriceFactorMapper.deleteByExample(example) > 0;
    }
}

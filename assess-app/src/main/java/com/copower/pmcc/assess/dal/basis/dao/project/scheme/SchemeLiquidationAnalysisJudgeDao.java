package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisJudge;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisJudgeExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeLiquidationAnalysisJudgeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class SchemeLiquidationAnalysisJudgeDao {
    @Autowired
    private SchemeLiquidationAnalysisJudgeMapper schemeLiquidationAnalysisJudgeMapper;

    public Integer addSchemeLiquidationAnalysisJudge(SchemeLiquidationAnalysisJudge schemeLiquidationAnalysisJudge) {
        schemeLiquidationAnalysisJudgeMapper.insertSelective(schemeLiquidationAnalysisJudge);
        return schemeLiquidationAnalysisJudge.getId();
    }

    public SchemeLiquidationAnalysisJudge getSchemeLiquidationAnalysisJudgeById(Integer id) {
        return schemeLiquidationAnalysisJudgeMapper.selectByPrimaryKey(id);
    }

    public boolean updateSchemeLiquidationAnalysisJudge(SchemeLiquidationAnalysisJudge schemeLiquidationAnalysisJudge) {
        return schemeLiquidationAnalysisJudgeMapper.updateByPrimaryKeySelective(schemeLiquidationAnalysisJudge) == 1;
    }

    public boolean deleteInfo(Integer id) {
        return schemeLiquidationAnalysisJudgeMapper.deleteByPrimaryKey(id) > 0;
    }

    public List<SchemeLiquidationAnalysisJudge> getSchemeLiquidationAnalysisJudgeList(SchemeLiquidationAnalysisJudge schemeLiquidationAnalysisJudge) {
        SchemeLiquidationAnalysisJudgeExample example = new SchemeLiquidationAnalysisJudgeExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysisJudge, example);
        return schemeLiquidationAnalysisJudgeMapper.selectByExample(example);
    }

    public List<SchemeLiquidationAnalysisJudge> getListByAreaId(Integer areaId) {
        SchemeLiquidationAnalysisJudgeExample example = new SchemeLiquidationAnalysisJudgeExample();
        example.createCriteria().andAreaIdEqualTo(areaId);
        return schemeLiquidationAnalysisJudgeMapper.selectByExample(example);
    }

    public List<SchemeLiquidationAnalysisJudge> getListByGroupId(Integer groupId) {
        SchemeLiquidationAnalysisJudgeExample example = new SchemeLiquidationAnalysisJudgeExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        return schemeLiquidationAnalysisJudgeMapper.selectByExample(example);
    }

    public List<SchemeLiquidationAnalysisJudge> getListByQuery(String name,String certName, String seat,String ownership,Integer areaGroupId, Integer groupId) {
        SchemeLiquidationAnalysisJudgeExample example = new SchemeLiquidationAnalysisJudgeExample();
        SchemeLiquidationAnalysisJudgeExample.Criteria criteria = example.createCriteria();
        if(areaGroupId!=null){
            criteria.andAreaIdEqualTo(areaGroupId);
        }
        if(groupId!=null){
            criteria.andGroupIdEqualTo(groupId);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (StringUtils.isNotBlank(seat)) {
            criteria.andSeatLike(String.format("%%%s%%", seat));
        }
        if (StringUtils.isNotBlank(certName)) {
            criteria.andCertNameLike(String.format("%%%s%%", certName));
        }
        if (StringUtils.isNotBlank(ownership)) {
            criteria.andOwnershipLike(String.format("%%%s%%", ownership));
        }

        return schemeLiquidationAnalysisJudgeMapper.selectByExample(example);
    }

}

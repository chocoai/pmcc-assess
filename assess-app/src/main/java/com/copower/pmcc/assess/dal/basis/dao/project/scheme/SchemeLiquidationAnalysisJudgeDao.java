package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisJudge;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisJudgeExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeLiquidationAnalysisJudgeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return schemeLiquidationAnalysisJudgeMapper.deleteByPrimaryKey(id) > 0;
    }

    public List<SchemeLiquidationAnalysisJudge> getSchemeLiquidationAnalysisJudgeList(SchemeLiquidationAnalysisJudge schemeLiquidationAnalysisJudge) {
        SchemeLiquidationAnalysisJudgeExample example = new SchemeLiquidationAnalysisJudgeExample();
        MybatisUtils.convertObj2Example(schemeLiquidationAnalysisJudge, example);
        return schemeLiquidationAnalysisJudgeMapper.selectByExample(example);
    }

}

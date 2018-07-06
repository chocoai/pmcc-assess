package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSuppyHeating;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSuppyHeatingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateSuppyHeatingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class CaseEstateSuppyHeatingDao {
    @Autowired
    private CaseEstateSuppyHeatingMapper caseEstateSuppyHeatingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateSuppyHeating getEstateSuppyHeatingById(Integer id) {
        return caseEstateSuppyHeatingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateSuppyHeating
     * @return
     */
    public List<CaseEstateSuppyHeating> getEstateSuppyHeatingList(CaseEstateSuppyHeating caseEstateSuppyHeating) {
        CaseEstateSuppyHeatingExample example = new CaseEstateSuppyHeatingExample();
        MybatisUtils.convertObj2Example(caseEstateSuppyHeating, example);
        return caseEstateSuppyHeatingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateSuppyHeating
     * @return
     */
    public boolean addEstateSuppyHeating(CaseEstateSuppyHeating caseEstateSuppyHeating) {
        return caseEstateSuppyHeatingMapper.insertSelective(caseEstateSuppyHeating) > 0;
    }

    /**
     * 编辑
     * @param caseEstateSuppyHeating
     * @return
     */
    public boolean updateEstateSuppyHeating(CaseEstateSuppyHeating caseEstateSuppyHeating) {
        return caseEstateSuppyHeatingMapper.updateByPrimaryKeySelective(caseEstateSuppyHeating) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateSuppyHeating(Integer id){
        return caseEstateSuppyHeatingMapper.deleteByPrimaryKey(id) > 0;
    }

}
package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandStateExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateLandStateMapper;
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
public class CaseEstateLandStateDao {
    @Autowired
    private CaseEstateLandStateMapper caseEstateLandStateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateLandState getEstateLandStateById(Integer id) {
        return caseEstateLandStateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateLandState
     * @return
     */
    public List<CaseEstateLandState> getEstateLandStateList(CaseEstateLandState caseEstateLandState) {
        CaseEstateLandStateExample example = new CaseEstateLandStateExample();
        MybatisUtils.convertObj2Example(caseEstateLandState, example);
        return caseEstateLandStateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateLandState
     * @return
     */
    public boolean addEstateLandState(CaseEstateLandState caseEstateLandState) {
        return caseEstateLandStateMapper.insertSelective(caseEstateLandState) > 0;
    }

    public Integer saveCaseEstateLandState(CaseEstateLandState caseEstateLandState){
        caseEstateLandStateMapper.insertSelective(caseEstateLandState);
        return caseEstateLandState.getId();
    }

    /**
     * 编辑
     * @param caseEstateLandState
     * @return
     */
    public boolean updateEstateLandState(CaseEstateLandState caseEstateLandState) {
        return caseEstateLandStateMapper.updateByPrimaryKeySelective(caseEstateLandState) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateLandState(Integer id){
        return caseEstateLandStateMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param estateId
     * @return
     */
    public int countByEstateId(Integer estateId){
        CaseEstateLandStateExample example = new CaseEstateLandStateExample();
        example.createCriteria().andEstateIdEqualTo(estateId);
        return caseEstateLandStateMapper.countByExample(example);
    }

}
package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlaceExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingLeisurePlaceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:34
 * @Description:
 */
@Repository
public class CaseMatchingLeisurePlaceDao {
    @Autowired
    private CaseMatchingLeisurePlaceMapper caseMatchingLeisurePlaceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingLeisurePlace getMatchingMedicalById(Integer id) {
        return caseMatchingLeisurePlaceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingLeisurePlace
     * @return
     */
    public List<CaseMatchingLeisurePlace> getMatchingMedicalList(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        CaseMatchingLeisurePlaceExample example = new CaseMatchingLeisurePlaceExample();
        MybatisUtils.convertObj2Example(caseMatchingLeisurePlace, example);
        return caseMatchingLeisurePlaceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingLeisurePlace
     * @return
     */
    public boolean addMatchingMedical(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        return caseMatchingLeisurePlaceMapper.insertSelective(caseMatchingLeisurePlace) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingLeisurePlace
     * @return
     */
    public boolean updateMatchingMedical(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        return caseMatchingLeisurePlaceMapper.updateByPrimaryKeySelective(caseMatchingLeisurePlace) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingMedical(Integer id){
        return caseMatchingLeisurePlaceMapper.deleteByPrimaryKey(id) > 0;
    }

}

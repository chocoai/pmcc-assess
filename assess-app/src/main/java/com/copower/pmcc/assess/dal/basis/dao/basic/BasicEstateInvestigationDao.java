package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateInvestigation;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateInvestigationExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateInvestigationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class BasicEstateInvestigationDao {
    @Autowired
    private BasicEstateInvestigationMapper basicEstateInvestigationMapper;

    public BasicEstateInvestigation getBasicEstateInvestigationById(Integer id) {
        return basicEstateInvestigationMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstateInvestigation(BasicEstateInvestigation basicUnitHuxing) {
        basicEstateInvestigationMapper.insertSelective(basicUnitHuxing);
        return basicUnitHuxing.getId();
    }

    public boolean updateBasicEstateInvestigation(BasicEstateInvestigation basicUnitHuxing, boolean updateNull) {
        return updateNull ? basicEstateInvestigationMapper.updateByPrimaryKey(basicUnitHuxing) == 1 : basicEstateInvestigationMapper.updateByPrimaryKeySelective(basicUnitHuxing) == 1;
    }

    public boolean deleteBasicEstateInvestigation(Integer id) {
        return basicEstateInvestigationMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicEstateInvestigation> basicUnitHuxingList(BasicEstateInvestigation basicUnitHuxing) {
        BasicEstateInvestigationExample example = new BasicEstateInvestigationExample();
        BasicEstateInvestigationExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(basicUnitHuxing, criteria);
        return basicEstateInvestigationMapper.selectByExample(example);
    }

}

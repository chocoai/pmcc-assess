package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegreeDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseDamagedDegreeDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:50
 * @Description:
 */
@Repository
public class BasicHouseDamagedDegreeDetailDao {
    @Autowired
    private BasicHouseDamagedDegreeDetailMapper basicHouseDamagedDegreeDetailMapper;

    public BasicHouseDamagedDegreeDetail getBasicHouseDamagedDegreeDetailById(Integer id) {
        return basicHouseDamagedDegreeDetailMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        basicHouseDamagedDegreeDetailMapper.insertSelective(basicHouseDamagedDegreeDetail);
        return basicHouseDamagedDegreeDetail.getId();
    }

    public boolean updateBasicHouseDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail, boolean updateNull) {
        basicHouseDamagedDegreeDetail.setBisDelete(false);
        return updateNull ? basicHouseDamagedDegreeDetailMapper.updateByPrimaryKey(basicHouseDamagedDegreeDetail) == 1 : basicHouseDamagedDegreeDetailMapper.updateByPrimaryKeySelective(basicHouseDamagedDegreeDetail) == 1;
    }

    public boolean deleteBasicHouseDamagedDegreeDetail(Integer id) {
        BasicHouseDamagedDegreeDetail damagedDegreeDetail = getBasicHouseDamagedDegreeDetailById(id);
        if(damagedDegreeDetail==null) return false;
        damagedDegreeDetail.setBisDelete(true);
        return basicHouseDamagedDegreeDetailMapper.updateByPrimaryKeySelective(damagedDegreeDetail) == 1;
    }


    public List<BasicHouseDamagedDegreeDetail> getDamagedDegreeDetailList(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        BasicHouseDamagedDegreeDetailExample example = new BasicHouseDamagedDegreeDetailExample();
        BasicHouseDamagedDegreeDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseDamagedDegreeDetail, criteria);
        return basicHouseDamagedDegreeDetailMapper.selectByExample(example);
    }
}

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

    public Integer saveBasicHouseDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        basicHouseDamagedDegreeDetailMapper.insertSelective(basicHouseDamagedDegreeDetail);
        return basicHouseDamagedDegreeDetail.getId();
    }

    public boolean updateBasicHouseDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail, boolean updateNull) {
        return updateNull ? basicHouseDamagedDegreeDetailMapper.updateByPrimaryKey(basicHouseDamagedDegreeDetail) == 1 : basicHouseDamagedDegreeDetailMapper.updateByPrimaryKeySelective(basicHouseDamagedDegreeDetail) == 1;
    }

    public boolean deleteBasicHouseDamagedDegreeDetail(Integer id) {
        return basicHouseDamagedDegreeDetailMapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean deleteBasicHouseDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        BasicHouseDamagedDegreeDetailExample example = new BasicHouseDamagedDegreeDetailExample();
        MybatisUtils.convertObj2Example(basicHouseDamagedDegreeDetail, example);
        return basicHouseDamagedDegreeDetailMapper.deleteByExample(example) > 0;
    }


    public List<BasicHouseDamagedDegreeDetail> getDamagedDegreeDetailList(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        BasicHouseDamagedDegreeDetailExample example = new BasicHouseDamagedDegreeDetailExample();
        MybatisUtils.convertObj2Example(basicHouseDamagedDegreeDetail, example);
        return basicHouseDamagedDegreeDetailMapper.selectByExample(example);
    }
}

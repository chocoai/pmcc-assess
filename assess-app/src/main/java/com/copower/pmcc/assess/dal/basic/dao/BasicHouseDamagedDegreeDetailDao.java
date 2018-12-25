package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeDetailExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseDamagedDegreeDetailMapper;
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

    public boolean updateBasicHouseDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        return basicHouseDamagedDegreeDetailMapper.updateByPrimaryKeySelective(basicHouseDamagedDegreeDetail) == 1;
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

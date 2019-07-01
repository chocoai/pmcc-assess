package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentLand;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentLandExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentLandMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/7/1.
 */
@Repository
public class MdDevelopmentLandDao {

    @Autowired
    private MdDevelopmentLandMapper mapper;


    public boolean updateMdDevelopmentLand(MdDevelopmentLand oo) {
        int i = mapper.updateByPrimaryKeySelective(oo);
        return i > 0;
    }


    public boolean addMdDevelopmentLand(MdDevelopmentLand oo) {
        int i = mapper.insertSelective(oo);
        return i > 0;
    }

    public boolean deleteMdDevelopmentLand(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public MdDevelopmentLand getByMdDevelopmentLandId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<MdDevelopmentLand> getMdDevelopmentLandList(MdDevelopmentLand oo) {
        MdDevelopmentLandExample example = new MdDevelopmentLandExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id desc");
        List<MdDevelopmentLand> mdDevelopmentLands = mapper.selectByExample(example);
        return mdDevelopmentLands;
    }
}

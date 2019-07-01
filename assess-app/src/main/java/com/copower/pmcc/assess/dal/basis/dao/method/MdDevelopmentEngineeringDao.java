package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentEngineering;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentEngineeringExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentEngineeringMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/7/1.
 */
@Repository
public class MdDevelopmentEngineeringDao {
    
    @Autowired
    private MdDevelopmentEngineeringMapper mapper;

    public boolean updateMdDevelopmentEngineering(MdDevelopmentEngineering oo) {
        int i = mapper.updateByPrimaryKeySelective(oo);
        return i > 0;
    }


    public boolean addMdDevelopmentEngineering(MdDevelopmentEngineering oo) {
        int i = mapper.insertSelective(oo);
        return i > 0;
    }

    public boolean deleteMdDevelopmentEngineering(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public MdDevelopmentEngineering getByMdDevelopmentEngineeringId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<MdDevelopmentEngineering> getMdDevelopmentEngineeringList(MdDevelopmentEngineering oo) {
        MdDevelopmentEngineeringExample example = new MdDevelopmentEngineeringExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id desc");
        List<MdDevelopmentEngineering> mdDevelopmentEngineerings = mapper.selectByExample(example);
        return mdDevelopmentEngineerings;
    }
    
}

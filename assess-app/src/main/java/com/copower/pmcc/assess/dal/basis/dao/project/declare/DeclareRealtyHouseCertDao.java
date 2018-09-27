package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCertExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyHouseCertMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 09:55
 * @Description:
 */
@Repository
public class DeclareRealtyHouseCertDao {
    @Autowired
    private DeclareRealtyHouseCertMapper declareRealtyHouseCertMapper;

    public Integer addDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert){
        declareRealtyHouseCertMapper.insertSelective(declareRealtyHouseCert);
        return declareRealtyHouseCert.getId();
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id){
        return declareRealtyHouseCertMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert){
        return declareRealtyHouseCertMapper.updateByPrimaryKeySelective(declareRealtyHouseCert)==1;
    }

    public void removeDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert){
        DeclareRealtyHouseCertExample example = new DeclareRealtyHouseCertExample();
        MybatisUtils.convertObj2Example(declareRealtyHouseCert, example);
        declareRealtyHouseCertMapper.deleteByExample(example);
    }

    public List<DeclareRealtyHouseCert> getDeclareRealtyHouseCertList(DeclareRealtyHouseCert declareRealtyHouseCert){
        DeclareRealtyHouseCertExample example = new DeclareRealtyHouseCertExample();
        MybatisUtils.convertObj2Example(declareRealtyHouseCert, example);
        return declareRealtyHouseCertMapper.selectByExample(example);
    }
}

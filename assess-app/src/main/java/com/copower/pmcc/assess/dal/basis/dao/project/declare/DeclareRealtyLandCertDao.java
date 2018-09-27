package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCertExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyLandCertMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 09:56
 * @Description:
 */
@Repository
public class DeclareRealtyLandCertDao {
    @Autowired
    private DeclareRealtyLandCertMapper declareRealtyLandCertMapper;

    public Integer addDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert){
        declareRealtyLandCertMapper.insertSelective(declareRealtyLandCert);
        return declareRealtyLandCert.getId();
    }

    public DeclareRealtyLandCert getDeclareRealtyLandCertById(Integer id){
        return declareRealtyLandCertMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert){
        return declareRealtyLandCertMapper.updateByPrimaryKeySelective(declareRealtyLandCert)==1;
    }

    public void removeDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert){
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        MybatisUtils.convertObj2Example(declareRealtyLandCert, example);
        declareRealtyLandCertMapper.deleteByExample(example);
    }

    public List<DeclareRealtyLandCert> getDeclareRealtyLandCertList(DeclareRealtyLandCert declareRealtyLandCert){
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        MybatisUtils.convertObj2Example(declareRealtyLandCert, example);
        return declareRealtyLandCertMapper.selectByExample(example);
    }
}

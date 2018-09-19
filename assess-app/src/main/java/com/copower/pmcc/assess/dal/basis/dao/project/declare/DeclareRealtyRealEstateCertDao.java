package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCertExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyRealEstateCertMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 09:57
 * @Description:
 */
@Repository
public class DeclareRealtyRealEstateCertDao {
    @Autowired
    private DeclareRealtyRealEstateCertMapper declareRealtyRealEstateCertMapper;

    public Integer addDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        declareRealtyRealEstateCertMapper.insertSelective(declareRealtyRealEstateCert);
        return declareRealtyRealEstateCert.getId();
    }

    public DeclareRealtyRealEstateCert getDeclareRealtyRealEstateCertById(Integer id){
        return declareRealtyRealEstateCertMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        return declareRealtyRealEstateCertMapper.updateByPrimaryKeySelective(declareRealtyRealEstateCert)==1;
    }

    public void removeDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        DeclareRealtyRealEstateCertExample example = new DeclareRealtyRealEstateCertExample();
        MybatisUtils.convertObj2Example(declareRealtyRealEstateCert, example);
        try {
            declareRealtyRealEstateCertMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<DeclareRealtyRealEstateCert> getDeclareRealtyRealEstateCertList(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        DeclareRealtyRealEstateCertExample example = new DeclareRealtyRealEstateCertExample();
        MybatisUtils.convertObj2Example(declareRealtyRealEstateCert, example);
        return declareRealtyRealEstateCertMapper.selectByExample(example);
    }
}

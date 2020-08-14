package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCertExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyLandCertMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
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

    public boolean updateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert,boolean updateNull){
        if (updateNull){
            DeclareRealtyLandCert target = getDeclareRealtyLandCertById(declareRealtyLandCert.getId()) ;
            if (declareRealtyLandCert.getPlanDetailsId() == null){
                declareRealtyLandCert.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareRealtyLandCert.getEnable())){
                declareRealtyLandCert.setEnable(target.getEnable());
            }
            if (declareRealtyLandCert.getBisRecord() == null){
                declareRealtyLandCert.setBisRecord(target.getBisRecord());
            }
            if (declareRealtyLandCert.getPid() == null){
                declareRealtyLandCert.setPid(target.getPid());
            }
            if (StringUtils.isEmpty(declareRealtyLandCert.getCreator())){
                declareRealtyLandCert.setCreator(target.getCreator());
            }
            if (declareRealtyLandCert.getGmtCreated() == null){
                declareRealtyLandCert.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declareRealtyLandCertMapper.updateByPrimaryKey(declareRealtyLandCert) == 1 : declareRealtyLandCertMapper.updateByPrimaryKeySelective(declareRealtyLandCert) == 1;
    }

    public boolean updateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert){
        return  declareRealtyLandCertMapper.updateByPrimaryKeySelective(declareRealtyLandCert) == 1;
    }

    public List<DeclareRealtyLandCert> getList(Integer planDetailsId){
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        DeclareRealtyLandCertExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause("auto_init_number,id");
        return declareRealtyLandCertMapper.selectByExample(example);
    }



    public boolean deleteDeclareRealtyLandCertById(Integer id){
        return declareRealtyLandCertMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteDeclareRealtyLandCertById(List<Integer> ids){
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        example.createCriteria().andIdIn(ids);
        return declareRealtyLandCertMapper.deleteByExample(example) > 0;
    }


    public List<DeclareRealtyLandCert> getDeclareRealtyLandCertList(DeclareRealtyLandCert declareRealtyLandCert){
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        MybatisUtils.convertObj2Example(declareRealtyLandCert, example);
        example.setOrderByClause("auto_init_number,id");
        return declareRealtyLandCertMapper.selectByExample(example);
    }

    public Integer getCountByPlanDetailsId(Integer planDetailsId){
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        long count = declareRealtyLandCertMapper.countByExample(example);
        return (int)count ;
    }

    public List<DeclareRealtyLandCert> getDataIds(List<Integer> dataIds) {
        DeclareRealtyLandCertExample example = new DeclareRealtyLandCertExample();
        example.createCriteria().andIdIn(dataIds);
        return declareRealtyLandCertMapper.selectByExample(example);
    }
}

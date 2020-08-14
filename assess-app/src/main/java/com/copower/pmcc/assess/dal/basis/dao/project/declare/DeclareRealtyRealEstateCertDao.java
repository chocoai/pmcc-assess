package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCertExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyRealEstateCertMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public boolean updateDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert,boolean updateNull){
        if (updateNull){
            DeclareRealtyRealEstateCert target = getDeclareRealtyRealEstateCertById(declareRealtyRealEstateCert.getId()) ;
            if (declareRealtyRealEstateCert.getPlanDetailsId() == null){
                declareRealtyRealEstateCert.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareRealtyRealEstateCert.getEnable())){
                declareRealtyRealEstateCert.setEnable(target.getEnable());
            }
            if (declareRealtyRealEstateCert.getBisRecord() == null){
                declareRealtyRealEstateCert.setBisRecord(target.getBisRecord());
            }
            if (declareRealtyRealEstateCert.getPid() == null){
                declareRealtyRealEstateCert.setPid(target.getPid());
            }
            if (StringUtils.isEmpty(declareRealtyRealEstateCert.getCreator())){
                declareRealtyRealEstateCert.setCreator(target.getCreator());
            }
            if (declareRealtyRealEstateCert.getGmtCreated() == null){
                declareRealtyRealEstateCert.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declareRealtyRealEstateCertMapper.updateByPrimaryKey(declareRealtyRealEstateCert) == 1 : declareRealtyRealEstateCertMapper.updateByPrimaryKeySelective(declareRealtyRealEstateCert) == 1;
    }

    public boolean deleteDeclareRealtyRealEstateCertById(Integer id){
        return declareRealtyRealEstateCertMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteDeclareRealtyRealEstateCertById(List<Integer> ids){
        DeclareRealtyRealEstateCertExample example = new DeclareRealtyRealEstateCertExample();
        example.createCriteria().andIdIn(ids);
        return declareRealtyRealEstateCertMapper.deleteByExample(example) > 0;
    }

    public List<DeclareRealtyRealEstateCert> getDeclareRealtyRealEstateCertList(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        DeclareRealtyRealEstateCertExample example = new DeclareRealtyRealEstateCertExample();
        MybatisUtils.convertObj2Example(declareRealtyRealEstateCert, example);
        example.setOrderByClause("auto_init_number,id");
        return declareRealtyRealEstateCertMapper.selectByExample(example);
    }

    public Integer getCountByPlanDetailsId(Integer planDetailsId){
        DeclareRealtyRealEstateCertExample example = new DeclareRealtyRealEstateCertExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        long count = declareRealtyRealEstateCertMapper.countByExample(example);
        return (int) count ;
    }
}

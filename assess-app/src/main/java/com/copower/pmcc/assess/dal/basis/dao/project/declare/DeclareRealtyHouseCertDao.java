package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCertExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyHouseCertMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
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

    public Integer addDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        declareRealtyHouseCertMapper.insertSelective(declareRealtyHouseCert);
        return declareRealtyHouseCert.getId();
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertMapper.selectByPrimaryKey(id);
    }


    public boolean updateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert, boolean updateNull) {
        if (updateNull){
            DeclareRealtyHouseCert target = getDeclareRealtyHouseCertById(declareRealtyHouseCert.getId()) ;
            if (declareRealtyHouseCert.getPlanDetailsId() == null){
                declareRealtyHouseCert.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareRealtyHouseCert.getEnable())){
                declareRealtyHouseCert.setEnable(target.getEnable());
            }
            if (declareRealtyHouseCert.getBisRecord() == null){
                declareRealtyHouseCert.setBisRecord(target.getBisRecord());
            }
            if (declareRealtyHouseCert.getPid() == null){
                declareRealtyHouseCert.setPid(target.getPid());
            }
            if (StringUtils.isEmpty(declareRealtyHouseCert.getCreator())){
                declareRealtyHouseCert.setCreator(target.getCreator());
            }
            if (declareRealtyHouseCert.getGmtCreated() == null){
                declareRealtyHouseCert.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declareRealtyHouseCertMapper.updateByPrimaryKey(declareRealtyHouseCert) == 1 : declareRealtyHouseCertMapper.updateByPrimaryKeySelective(declareRealtyHouseCert) == 1;
    }

    public boolean updateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        return declareRealtyHouseCertMapper.updateByPrimaryKeySelective(declareRealtyHouseCert) == 1;
    }

    public boolean deleteDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteDeclareRealtyHouseCertById(List<Integer> ids) {
        DeclareRealtyHouseCertExample example = new DeclareRealtyHouseCertExample();
        example.createCriteria().andIdIn(ids);
        return declareRealtyHouseCertMapper.deleteByExample(example) > 0;
    }


    public List<DeclareRealtyHouseCert> getDeclareRealtyHouseCertList(DeclareRealtyHouseCert declareRealtyHouseCert) {
        DeclareRealtyHouseCertExample example = new DeclareRealtyHouseCertExample();
        MybatisUtils.convertObj2Example(declareRealtyHouseCert, example);
        example.setOrderByClause("auto_init_number,id");
        return declareRealtyHouseCertMapper.selectByExample(example);
    }

    public Integer getCountByPlanDetailsId(Integer planDetailsId){
        DeclareRealtyHouseCertExample example = new DeclareRealtyHouseCertExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        long count = declareRealtyHouseCertMapper.countByExample(example);
        return (int)count ;
    }
}

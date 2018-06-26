package com.copower.pmcc.assess.dal.dao.project.scheme;

import com.copower.pmcc.assess.dal.entity.SchemeInfoDetail;
import com.copower.pmcc.assess.dal.mapper.SchemeInfoDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 方案信息
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class SchemeInfoDetailDao {

    @Autowired
    private SchemeInfoDetailMapper mapper;

    public int addReturnID(SchemeInfoDetail SchemeInfoDetail){
        mapper.insertSelective(SchemeInfoDetail);
        return SchemeInfoDetail.getId();
    }

    public boolean update(SchemeInfoDetail SchemeInfoDetail){
        return mapper.updateByPrimaryKey(SchemeInfoDetail)==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }
}

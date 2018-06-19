package com.copower.pmcc.assess.dal.dao.csr;

import com.copower.pmcc.assess.dal.entity.CsrBorrowerEntering;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerEnteringExample;
import com.copower.pmcc.assess.dal.mapper.CsrBorrowerEnteringMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 11:07
 */
@Repository
public class CsrBorrowerEnteringDao {

    @Autowired
    private CsrBorrowerEnteringMapper csrBorrowerEnteringMapper;

    public boolean addCsrBorrowerEntering(CsrBorrowerEntering csrBorrowerEntering){
        return csrBorrowerEnteringMapper.insertSelective(csrBorrowerEntering)>0;
    }


    public boolean update(CsrBorrowerEntering csrBorrowerEntering){
        return csrBorrowerEnteringMapper.updateByPrimaryKey(csrBorrowerEntering)==1;
    }

    public CsrBorrowerEntering getCsrBorrowerEnteringByBorrowerId(String id){
        CsrBorrowerEnteringExample example=new CsrBorrowerEnteringExample();
        example.createCriteria().andBorrowerIdEqualTo(id);
        List<CsrBorrowerEntering> csrBorrowerEnterings = csrBorrowerEnteringMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(csrBorrowerEnterings))
        {
            return csrBorrowerEnterings.get(0);
        }
        return null;
    }


}

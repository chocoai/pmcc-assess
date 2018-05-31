package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerExample;
import com.copower.pmcc.assess.dal.mapper.CsrBorrowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 借款人
 */
@Repository
public class CsrBorrowerDao {

    @Autowired
    private CsrBorrowerMapper csrBorrowerMapper;

    public List<CsrBorrower> borrowerLists(){
        CsrBorrowerExample example = new CsrBorrowerExample();
        example.createCriteria().andIdIsNotNull().andGroupIdIsNull();
        return csrBorrowerMapper.selectByExample(example);
    }

}

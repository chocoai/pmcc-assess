package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.Csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 11:10
 */
@Service
public class CsrBorrowerService {
    @Autowired
    private CsrBorrowerDao csrBorrowerDao;

    public List<CsrBorrower> getCsrBorrowerByProjectId(Integer projectId)
    {
        CsrBorrower csrBorrower=new CsrBorrower();
        csrBorrower.setProjectId(projectId);
        return csrBorrowerDao.getCsrBorrowerList(csrBorrower);
    }
}

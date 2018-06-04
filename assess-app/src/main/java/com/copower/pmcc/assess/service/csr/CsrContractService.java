package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrContractDao;
import com.copower.pmcc.assess.dal.entity.CsrContract;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:11
 */
@Service
public class CsrContractService {
    @Autowired
    private CsrContractDao csrContractDao;

    public CsrContract saveCsrContract(CsrContract csrContract) throws BusinessException
    {
        try {
            if(csrContract.getId()==null && csrContract.getId()<=0)
            {
                csrContractDao.addCsrContract(csrContract);
            }
            else
            {
                csrContractDao.updateCsrContract(csrContract);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrContract;
    }
}

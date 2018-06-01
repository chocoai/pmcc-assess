package com.copower.pmcc.assess.service.csr;


import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 借款人
 */
@Service
public class CsrBorrowerService {

    @Autowired
    private CsrBorrowerDao csrBorrowerDao;

    public BootstrapTableVo borrowerLists(String secondLevelBranch,String firstLevelBranch) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrBorrower> vos = csrBorrowerDao.borrowerLists(secondLevelBranch,firstLevelBranch);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CsrBorrower>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }


    public List<CsrBorrower> getCsrBorrowerByProjectId(Integer projectId) {
        CsrBorrower csrBorrower = new CsrBorrower();
        csrBorrower.setProjectId(projectId);
        return csrBorrowerDao.getCsrBorrowerList(projectId);
    }

}

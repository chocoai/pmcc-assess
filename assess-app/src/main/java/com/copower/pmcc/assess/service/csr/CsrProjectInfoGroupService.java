package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.Csr.CsrProjectInfoGroupDao;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目组
 */

@Service
public class CsrProjectInfoGroupService {


    @Autowired
    private CsrProjectInfoGroupDao projectInfoGroupDao;

    @Transactional
    public boolean add(CsrProjectInfoGroup csrProjectInfoGroup){
        return projectInfoGroupDao.add(csrProjectInfoGroup);
    }

    @Transactional(readOnly = true)
    public List<CsrProjectInfoGroup> groupList(Integer projectID){
        return projectInfoGroupDao.groupList(projectID);
    }

    public BootstrapTableVo groupVoList(Integer projectID){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrProjectInfoGroup> vos = groupList(projectID);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CsrProjectInfoGroup>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }
}

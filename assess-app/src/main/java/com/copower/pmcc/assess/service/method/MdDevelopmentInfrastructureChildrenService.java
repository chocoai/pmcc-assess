package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentInfrastructureChildrenDao;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildren;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2019/7/31.
 */
@Service
public class MdDevelopmentInfrastructureChildrenService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private MdDevelopmentInfrastructureChildrenDao mdDevelopmentInfrastructureChildrenDao;

    public boolean saveMdDevelopmentInfrastructureChildren(MdDevelopmentInfrastructureChildren mdDevelopmentInfrastructureChildren){
        if (mdDevelopmentInfrastructureChildren == null){
            return false;
        }
        if (mdDevelopmentInfrastructureChildren.getId() != null && mdDevelopmentInfrastructureChildren.getId() != 0){
            return mdDevelopmentInfrastructureChildrenDao.updateMdDevelopmentInfrastructureChildren(mdDevelopmentInfrastructureChildren);
        }else {
            mdDevelopmentInfrastructureChildren.setCreator(commonService.thisUserAccount());
            return mdDevelopmentInfrastructureChildrenDao.addMdDevelopmentInfrastructureChildren(mdDevelopmentInfrastructureChildren) ;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(MdDevelopmentInfrastructureChildren oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdDevelopmentInfrastructureChildren> childrenList = getMdDevelopmentInfrastructureChildrenListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }

    public MdDevelopmentInfrastructureChildren getMdDevelopmentInfrastructureChildrenById(Integer id){
        return mdDevelopmentInfrastructureChildrenDao.getMdDevelopmentInfrastructureChildrenById(id) ;
    }

    public boolean deleteMdDevelopmentInfrastructureChildrenById(Integer id){
        return mdDevelopmentInfrastructureChildrenDao.deleteMdDevelopmentInfrastructureChildrenById(id) ;
    }

    public List<MdDevelopmentInfrastructureChildren> getMdDevelopmentInfrastructureChildrenListByExample(MdDevelopmentInfrastructureChildren oo){
        return mdDevelopmentInfrastructureChildrenDao.getMdDevelopmentInfrastructureChildrenListByExample(oo);
    }

}

package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdCalculatingMethodEngineeringCostDao;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
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
 * Created by zch on 2019-8-28.
 */
@Service
public class MdCalculatingMethodEngineeringCostService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private MdCalculatingMethodEngineeringCostDao mdCalculatingMethodEngineeringCostDao;

    public boolean saveMdCalculatingMethodEngineeringCost(MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost){
        if (mdCalculatingMethodEngineeringCost == null){
            return false;
        }
        if (mdCalculatingMethodEngineeringCost.getId() != null && mdCalculatingMethodEngineeringCost.getId() != 0){
            return mdCalculatingMethodEngineeringCostDao.updateMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);
        }else {
            mdCalculatingMethodEngineeringCost.setCreator(commonService.thisUserAccount());
            return mdCalculatingMethodEngineeringCostDao.addMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost) ;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(MdCalculatingMethodEngineeringCost oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdCalculatingMethodEngineeringCost> childrenList = getMdCalculatingMethodEngineeringCostListByExample(oo) ;
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }

    public MdCalculatingMethodEngineeringCost getMdCalculatingMethodEngineeringCostById(Integer id){
        return mdCalculatingMethodEngineeringCostDao.getMdCalculatingMethodEngineeringCostById(id) ;
    }

    public boolean deleteMdCalculatingMethodEngineeringCostById(Integer id){
        return mdCalculatingMethodEngineeringCostDao.deleteMdCalculatingMethodEngineeringCostById(id) ;
    }

    public List<MdCalculatingMethodEngineeringCost> getMdCalculatingMethodEngineeringCostListByExample(MdCalculatingMethodEngineeringCost oo){
        return mdCalculatingMethodEngineeringCostDao.getMdCalculatingMethodEngineeringCostListByExample(oo);
    }

}

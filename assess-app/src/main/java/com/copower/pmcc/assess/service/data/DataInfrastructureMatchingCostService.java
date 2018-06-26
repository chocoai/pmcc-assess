package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.data.DataInfrastructureMatchingCostDao;
import com.copower.pmcc.assess.dal.entity.InfrastructureMatchingCost;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei
 */
@Service(value = "dataInfrastructureMatchingCostService")
public class DataInfrastructureMatchingCostService {
    @Autowired
    private DataInfrastructureMatchingCostDao infrastructureMatchingCostDao;

    public BootstrapTableVo getInfrastructureCost(String name){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InfrastructureMatchingCost> infrastructureCostList = infrastructureMatchingCostDao.getInfrastructureCostList(name);
        vo.setRows(CollectionUtils.isEmpty(infrastructureCostList) ? new ArrayList<InfrastructureMatchingCost>() : infrastructureCostList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public boolean addInfrastructureCost(InfrastructureMatchingCost infrastructureMatchingCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureMatchingCostDao.addInfrastructureCost(infrastructureMatchingCost);
        return flag;
    }

    public boolean editInfrastructureCost(InfrastructureMatchingCost infrastructureCost) throws BusinessException{
        boolean flag = false;
        flag = infrastructureMatchingCostDao.editInfrastructureCost(infrastructureCost);
        return  flag;
    }

    public boolean deleteInfrastructure(Integer id){
        boolean flag = false;
        flag = infrastructureMatchingCostDao.deleteInfrastructureCost(id);
        return flag;
    }
}

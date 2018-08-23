package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureCostDao;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCost;
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
@Service
public class DataInfrastructureCostService {
    @Autowired
    private DataInfrastructureCostDao infrastructureCostDao;

    public BootstrapTableVo getDataInfrastructureCost(String name){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataInfrastructureCost> infrastructureCostList = infrastructureCostDao.getDataInfrastructureCostList(name);
        vo.setRows(CollectionUtils.isEmpty(infrastructureCostList) ? new ArrayList<DataInfrastructureCost>() : infrastructureCostList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<DataInfrastructureCost> infrastructureCostList(){
        return  infrastructureCostDao.getDataInfrastructureCostList(null);
    }

    public boolean addDataInfrastructureCost(DataInfrastructureCost infrastructureCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureCostDao.addDataInfrastructureCost(infrastructureCost);
        return flag;
    }

    public boolean editDataInfrastructureCost(DataInfrastructureCost infrastructureCost) throws BusinessException{
        boolean flag = false;
        flag = infrastructureCostDao.editDataInfrastructureCost(infrastructureCost);
        return  flag;
    }

    public boolean deleteInfrastructure(Integer id){
        boolean flag = false;
        flag = infrastructureCostDao.deleteDataInfrastructureCost(id);
        return flag;
    }


}

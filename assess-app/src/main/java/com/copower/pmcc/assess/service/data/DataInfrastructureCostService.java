package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureCostDao;
import com.copower.pmcc.assess.dal.basis.entity.InfrastructureCost;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei
 */
@Service(value = "dataInfrastructureCostService")
public class DataInfrastructureCostService {
    @Autowired
    private DataInfrastructureCostDao infrastructureCostDao;

    public BootstrapTableVo getInfrastructureCost(String name){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InfrastructureCost> infrastructureCostList = infrastructureCostDao.getInfrastructureCostList(name);
        vo.setRows(CollectionUtils.isEmpty(infrastructureCostList) ? new ArrayList<InfrastructureCost>() : infrastructureCostList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<InfrastructureCost> infrastructureCostList(){
        return  infrastructureCostDao.getInfrastructureCostList(null);
    }

    public boolean addInfrastructureCost(InfrastructureCost infrastructureCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureCostDao.addInfrastructureCost(infrastructureCost);
        return flag;
    }

    public boolean editInfrastructureCost(InfrastructureCost infrastructureCost) throws BusinessException{
        boolean flag = false;
        flag = infrastructureCostDao.editInfrastructureCost(infrastructureCost);
        return  flag;
    }

    public boolean deleteInfrastructure(Integer id){
        boolean flag = false;
        flag = infrastructureCostDao.deleteInfrastructureCost(id);
        return flag;
    }


}

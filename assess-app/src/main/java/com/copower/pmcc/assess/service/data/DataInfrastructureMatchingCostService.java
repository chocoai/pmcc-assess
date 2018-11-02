package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureMatchingCostDao;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCost;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei
 */
@Service(value = "dataInfrastructureMatchingCostService")
public class DataInfrastructureMatchingCostService {
    @Autowired
    private DataInfrastructureMatchingCostDao infrastructureMatchingCostDao;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    public DataInfrastructureMatchingCost getByDataInfrastructureMatchingCost(Integer id) {
        return infrastructureMatchingCostDao.getByDataInfrastructureMatchingCost(id);
    }

    public BootstrapTableVo getDataInfrastructureCost(DataInfrastructureMatchingCost dataInfrastructureMatchingCost) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataInfrastructureMatchingCost> infrastructureCostList = infrastructureMatchingCostDao.getDataInfrastructureCostList(dataInfrastructureMatchingCost);
        vo.setRows(CollectionUtils.isEmpty(infrastructureCostList) ? new ArrayList<DataInfrastructureMatchingCost>() : infrastructureCostList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<DataInfrastructureMatchingCost> infrastructureMatchingCosts() {
        return infrastructureMatchingCostDao.getDataInfrastructureCostList(new DataInfrastructureMatchingCost());
    }

    public List<DataInfrastructureMatchingCost> infrastructureMatchingCosts(DataInfrastructureMatchingCost dataInfrastructureMatchingCost) {
        return infrastructureMatchingCostDao.getDataInfrastructureCostList(dataInfrastructureMatchingCost);
    }

    /**
     * 获取金额合计
     *
     * @param pid
     * @return
     */
    public BigDecimal getNumberTotal(Integer pid) {
        BigDecimal total = new BigDecimal("0");
        DataInfrastructureMatchingCost where = new DataInfrastructureMatchingCost();
        where.setPid(pid);
        List<DataInfrastructureMatchingCost> list = infrastructureMatchingCostDao.getDataInfrastructureCostList(where);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DataInfrastructureMatchingCost matchingCost : list) {
                total = total.add(matchingCost.getNumber());
            }
        }
        return total;
    }

    @Transactional
    public boolean addDataInfrastructureCost(DataInfrastructureMatchingCost infrastructureMatchingCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureMatchingCostDao.addDataInfrastructureCost(infrastructureMatchingCost);
        dataInfrastructureService.updateMatchingCostTotal(infrastructureMatchingCost.getPid(), getNumberTotal(infrastructureMatchingCost.getPid()));
        return flag;
    }

    @Transactional
    public boolean editDataInfrastructureCost(DataInfrastructureMatchingCost infrastructureMatchingCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureMatchingCostDao.editDataInfrastructureCost(infrastructureMatchingCost);
        dataInfrastructureService.updateMatchingCostTotal(infrastructureMatchingCost.getPid(), getNumberTotal(infrastructureMatchingCost.getPid()));
        return flag;
    }

    @Transactional
    public boolean deleteInfrastructure(Integer id) {
        boolean flag = false;
        DataInfrastructureMatchingCost infrastructureMatchingCost = infrastructureMatchingCostDao.getByDataInfrastructureMatchingCost(id);
        flag = infrastructureMatchingCostDao.deleteDataInfrastructureCost(id);
        dataInfrastructureService.updateMatchingCostTotal(infrastructureMatchingCost.getPid(), getNumberTotal(infrastructureMatchingCost.getPid()));
        return flag;
    }
}

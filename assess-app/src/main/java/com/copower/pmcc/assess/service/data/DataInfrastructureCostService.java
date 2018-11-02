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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei
 */
@Service
public class DataInfrastructureCostService {
    @Autowired
    private DataInfrastructureCostDao infrastructureCostDao;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    public DataInfrastructureCost getByDataInfrastructureCost(Integer id) {
        return infrastructureCostDao.getByDataInfrastructureCost(id);
    }

    public BootstrapTableVo getDataInfrastructureCost(DataInfrastructureCost dataInfrastructureCost) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataInfrastructureCost> infrastructureCostList = infrastructureCostDao.getDataInfrastructureCostList(dataInfrastructureCost);
        vo.setRows(CollectionUtils.isEmpty(infrastructureCostList) ? new ArrayList<DataInfrastructureCost>() : infrastructureCostList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<DataInfrastructureCost> infrastructureCostList() {
        return infrastructureCostDao.getDataInfrastructureCostList(new DataInfrastructureCost());
    }

    public List<DataInfrastructureCost> getDataInfrastructureCostList(DataInfrastructureCost dataInfrastructureCost) {
        return infrastructureCostDao.getDataInfrastructureCostList(dataInfrastructureCost);
    }

    /**
     * 获取金额合计
     *
     * @param pid
     * @return
     */
    public BigDecimal getNumberTotal(Integer pid) {
        BigDecimal total = new BigDecimal("0");
        DataInfrastructureCost where = new DataInfrastructureCost();
        where.setPid(pid);
        List<DataInfrastructureCost> list = infrastructureCostDao.getDataInfrastructureCostList(where);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DataInfrastructureCost infrastructureCost : list) {
                total = total.add(infrastructureCost.getNumber());
            }
        }
        return total;
    }

    @Transactional
    public boolean addDataInfrastructureCost(DataInfrastructureCost infrastructureCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureCostDao.addDataInfrastructureCost(infrastructureCost);
        dataInfrastructureService.updateCostTotal(infrastructureCost.getPid(), getNumberTotal(infrastructureCost.getPid()));
        return flag;
    }

    @Transactional
    public boolean editDataInfrastructureCost(DataInfrastructureCost infrastructureCost) throws BusinessException {
        boolean flag = false;
        flag = infrastructureCostDao.editDataInfrastructureCost(infrastructureCost);
        dataInfrastructureService.updateCostTotal(infrastructureCost.getPid(), getNumberTotal(infrastructureCost.getPid()));
        return flag;
    }

    @Transactional
    public boolean deleteInfrastructure(Integer id) {
        boolean flag = false;
        DataInfrastructureCost infrastructureCost = infrastructureCostDao.getByDataInfrastructureCost(id);
        flag = infrastructureCostDao.deleteDataInfrastructureCost(id);
        dataInfrastructureService.updateCostTotal(infrastructureCost.getPid(), getNumberTotal(infrastructureCost.getPid()));
        return flag;
    }


}

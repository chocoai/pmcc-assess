package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureDevTaxDao;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTax;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/18 11:51
 * @Description:开发期间税费费用
 */
@Service
public class DataInfrastructureDevTaxService {
    @Autowired
    private DataInfrastructureDevTaxDao dataInfrastructureDevTaxDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    @Transactional
    public void saveAndUpdateDataInfrastructureDevTax(DataInfrastructureDevTax dataInfrastructureDevTax) {
        if (dataInfrastructureDevTax.getId() == null) {
            dataInfrastructureDevTax.setCreator(commonService.thisUserAccount());
            dataInfrastructureDevTaxDao.addDataInfrastructureDevTax(dataInfrastructureDevTax);
        } else {
            dataInfrastructureDevTaxDao.updateDataInfrastructureDevTax(dataInfrastructureDevTax);
        }
        dataInfrastructureService.updateDevTaxTotal(dataInfrastructureDevTax.getPid(), getNumberTotal(dataInfrastructureDevTax.getPid()));
    }

    /**
     * 获取金额合计
     *
     * @param pid
     * @return
     */
    public BigDecimal getNumberTotal(Integer pid) {
        BigDecimal total = new BigDecimal("0");
        DataInfrastructureDevTax where = new DataInfrastructureDevTax();
        where.setPid(pid);
        List<DataInfrastructureDevTax> list = dataInfrastructureDevTaxDao.getDataInfrastructureDevTaxList(where);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DataInfrastructureDevTax devTax : list) {
                total = total.add(devTax.getNumber());
            }
        }
        return total;
    }

    public DataInfrastructureDevTax getDataInfrastructureDevTaxById(Integer id) {
        return dataInfrastructureDevTaxDao.getDataInfrastructureDevTaxById(id);
    }

    public BootstrapTableVo getDataInfrastructureDevTaxListVos(DataInfrastructureDevTax dataInfrastructureDevTax) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataInfrastructureDevTax> vos = dataInfrastructureDevTaxes(dataInfrastructureDevTax);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataInfrastructureDevTax> dataInfrastructureDevTaxes(DataInfrastructureDevTax dataInfrastructureDevTax) {
        List<DataInfrastructureDevTax> dataInfrastructureDevTaxs = dataInfrastructureDevTaxDao.getDataInfrastructureDevTaxList(dataInfrastructureDevTax);
        return dataInfrastructureDevTaxs;
    }

    @Transactional
    public void removeDataInfrastructureDevTax(DataInfrastructureDevTax devTax) {
        DataInfrastructureDevTax dataInfrastructureDevTax = dataInfrastructureDevTaxDao.getDataInfrastructureDevTaxById(devTax.getId());
        dataInfrastructureDevTaxDao.removeDataInfrastructureDevTax(devTax);
        dataInfrastructureService.updateDevTaxTotal(dataInfrastructureDevTax.getPid(), getNumberTotal(dataInfrastructureDevTax.getPid()));
    }


}

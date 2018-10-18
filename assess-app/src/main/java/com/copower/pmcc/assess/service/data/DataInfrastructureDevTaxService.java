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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Integer saveAndUpdateDataInfrastructureDevTax(DataInfrastructureDevTax dataInfrastructureDevTax){
        if (dataInfrastructureDevTax.getId()==null){
            dataInfrastructureDevTax.setCreator(commonService.thisUserAccount());
            return dataInfrastructureDevTaxDao.addDataInfrastructureDevTax(dataInfrastructureDevTax);
        }else {
            dataInfrastructureDevTaxDao.updateDataInfrastructureDevTax(dataInfrastructureDevTax);
            return null;
        }
    }

    public DataInfrastructureDevTax getDataInfrastructureDevTaxById(Integer id){
        return dataInfrastructureDevTaxDao.getDataInfrastructureDevTaxById(id);
    }

    public BootstrapTableVo getDataInfrastructureDevTaxListVos(DataInfrastructureDevTax dataInfrastructureDevTax){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataInfrastructureDevTax> vos = dataInfrastructureDevTaxes(dataInfrastructureDevTax);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }
    public  List<DataInfrastructureDevTax> dataInfrastructureDevTaxes(DataInfrastructureDevTax dataInfrastructureDevTax){
        List<DataInfrastructureDevTax> dataInfrastructureDevTaxs = dataInfrastructureDevTaxDao.getDataInfrastructureDevTaxList(dataInfrastructureDevTax);
        return dataInfrastructureDevTaxs;
    }

    public void removeDataInfrastructureDevTax(DataInfrastructureDevTax dataInfrastructureDevTax){
        dataInfrastructureDevTaxDao.removeDataInfrastructureDevTax(dataInfrastructureDevTax);
    }


}

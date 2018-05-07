package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataInfrastructureDao;
import com.copower.pmcc.assess.dal.entity.Infrastructure;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础设施及公共配套设施维护
 * @author liuwei
 */
@Service(value = "dataInfrastructureService")
public class DataInfrastructureService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataInfrastructureDao dataInfrastructureDao;

    public BootstrapTableVo getInfrastructure(String name){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<Infrastructure> infrastructureList = dataInfrastructureDao.getInfrastructureList(name);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(infrastructureList) ? new ArrayList<Infrastructure>() : infrastructureList);
        return vo;
    }

    public boolean addInfrastructure(Infrastructure infrastructure) throws BusinessException{
        boolean flag = false;
        flag = dataInfrastructureDao.addInfrastructure(infrastructure);
        return flag;
    }

    public boolean editInfrastructure(Infrastructure infrastructure) throws BusinessException{
        boolean flag = false;
        flag = dataInfrastructureDao.editInfrastructure(infrastructure);
        return  flag;
    }

    public boolean deleteInfrastructure(Integer id){
        boolean flag = false;
        flag = dataInfrastructureDao.deleteInfrastructure(id);
        return flag;
    }
}

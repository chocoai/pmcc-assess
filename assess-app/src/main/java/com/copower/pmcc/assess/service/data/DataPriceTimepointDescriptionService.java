package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataPriceTimepointDescriptionDao;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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

@Service(value = "dataPriceTimepointDescriptionService")
public class DataPriceTimepointDescriptionService {
    @Autowired
    private DataPriceTimepointDescriptionDao dataPriceTimepointDescriptionDao;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public BootstrapTableVo getPriceTimepointListVo(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataPriceTimepointDescription> dataPriceTimepointDescriptionList = dataPriceTimepointDescriptionDao.getDataPriceTimepointDescriptionList(name);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataPriceTimepointDescriptionList) ? new ArrayList<DataPriceTimepointDescription>() : dataPriceTimepointDescriptionList);
        return vo;
    }

    public boolean addPriceTimepointDescription(DataPriceTimepointDescription dataPriceTimepointDescription) throws BusinessException {
        boolean flag = false;
        dataPriceTimepointDescription.setCreator(processControllerComponent.getThisUser());
        flag = dataPriceTimepointDescriptionDao.addDataPriceTimepointDescription(dataPriceTimepointDescription);
        return flag;
    }

    public boolean editPriceTimepointDescription(DataPriceTimepointDescription dataPriceTimepointDescription) throws BusinessException {
        boolean flag = false;
        flag = dataPriceTimepointDescriptionDao.editDataPriceTimepointDescription(dataPriceTimepointDescription);
        return flag;
    }

    public boolean deletePriceTimepointDescription(Integer id) {
        boolean flag = false;
        flag = dataPriceTimepointDescriptionDao.deleteDataPriceTimepointDescription(id);
        return flag;
    }

}
package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataPriceTimepointDescriptionDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dto.output.data.DataPriceTimepointDescriptionVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "dataPriceTimepointDescriptionService")
public class DataPriceTimepointDescriptionService {
    @Autowired
    private DataPriceTimepointDescriptionDao dataPriceTimepointDescriptionDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    public BootstrapTableVo getPriceTimepointListVo(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataPriceTimepointDescription> dataPriceTimepointDescriptionList = dataPriceTimepointDescriptionDao.getDataPriceTimepointDescriptionList(name);
        List<DataPriceTimepointDescriptionVo> vos = LangUtils.transform(dataPriceTimepointDescriptionList,p -> getDataPriceTimepointDescriptionVo(p));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataPriceTimepointDescriptionVo>() : vos);
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

    public DataPriceTimepointDescriptionVo getDataPriceTimepointDescriptionVo(DataPriceTimepointDescription oo){
        DataPriceTimepointDescriptionVo vo = new DataPriceTimepointDescriptionVo();
        BeanUtils.copyProperties(oo,vo);
        BaseProjectClassify baseProjectClassify = null;
        if (oo.getCategory() != null){
            baseProjectClassify = baseProjectClassifyService.getProjectClassifyById(oo.getCategory());
            if (baseProjectClassify != null){
                vo.setCategoryName(baseProjectClassify.getName());
                baseProjectClassify = null;
            }
        }
        if (oo.getType() != null){
            baseProjectClassify = baseProjectClassifyService.getProjectClassifyById(oo.getType());
            if (baseProjectClassify != null){
                vo.setTypeName(baseProjectClassify.getName());
                baseProjectClassify = null;
            }
        }
        return vo;
    }

}
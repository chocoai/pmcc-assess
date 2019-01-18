package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataBestUseDescriptionDao;
import com.copower.pmcc.assess.dal.basis.entity.DataBestUseDescription;
import com.copower.pmcc.assess.dto.output.data.DataBestUseDescriptionVo;
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

@Service(value = "dataBestUseDescriptionService")
public class DataBestUseDescriptionService {

    @Autowired
    private DataBestUseDescriptionDao dataBestUseDescriptionDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    public List<DataBestUseDescription> dataBestUseDescriptionList(){
        return dataBestUseDescriptionDao.dataBestUseDescriptionList();
    }

    public BootstrapTableVo getBestUseListVo(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBestUseDescription> dataBestUseDescriptionList = dataBestUseDescriptionDao.getDataBestUseDescriptionList(name);
        List<DataBestUseDescriptionVo> vos = LangUtils.transform(dataBestUseDescriptionList,b -> getDataBestUseDescription(b));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataBestUseDescriptionVo>() : vos);
        return vo;
    }

    public boolean addDataBestUseDescription(DataBestUseDescription dataBestUseDescription) throws BusinessException {
        boolean flag = false;
        dataBestUseDescription.setCreator(processControllerComponent.getThisUser());
        flag = dataBestUseDescriptionDao.addDataBestUseDescription(dataBestUseDescription);
        return flag;
    }

    public boolean editDataBestUseDescription(DataBestUseDescription dataBestUseDescription) throws BusinessException {
        boolean flag = false;
        flag = dataBestUseDescriptionDao.editDataBestUseDescription(dataBestUseDescription);
        return flag;
    }

    public boolean deleteDataBestUseDescription(Integer id) {
        boolean flag = false;
        flag = dataBestUseDescriptionDao.deleteDataBestUseDescription(id);
        return flag;
    }

    public DataBestUseDescriptionVo getDataBestUseDescription(DataBestUseDescription bestUseDescription){
        DataBestUseDescriptionVo vo = new DataBestUseDescriptionVo();
        BeanUtils.copyProperties(bestUseDescription,vo);
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(bestUseDescription.getType(),bestUseDescription.getCategory()));
        return vo;
    }

}

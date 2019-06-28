package com.copower.pmcc.assess.service.data;


import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureChildrenDao;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureChildren;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019/6/28.
 * 配套设施费用
 */
@org.springframework.stereotype.Service
public class DataInfrastructureChildrenService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DataInfrastructureChildrenDao dao;

    public boolean updateDataInfrastructureChildren(DataInfrastructureChildren oo) {
        return dao.updateDataInfrastructureChildren(oo);
    }

    public void removeDataInfrastructureChildrenIds(List<Integer> ids){
        dao.removeDataInfrastructureChildrenIds(ids);
    }

    public void removeDataInfrastructureChildrenByPid(Integer pid){
        DataInfrastructureChildren select = new DataInfrastructureChildren();
        select.setPid(pid);
        List<DataInfrastructureChildren> dataInfrastructureChildrenList = getDataInfrastructureChildrenList(select) ;
        if (CollectionUtils.isNotEmpty(dataInfrastructureChildrenList)){
            List<Integer> ids = dataInfrastructureChildrenList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            removeDataInfrastructureChildrenIds(ids);
        }
    }

    public boolean addDataInfrastructureChildren(DataInfrastructureChildren oo) {
        if (oo != null){
            if (StringUtils.isEmpty(oo.getCreator())){
                oo.setCreator(commonService.thisUserAccount());
            }
        }
        return dao.addDataInfrastructureChildren(oo);
    }

    public boolean deleteDataInfrastructureChildren(Integer id) {
        return dao.deleteDataInfrastructureChildren(id);
    }

    public DataInfrastructureChildren getByDataInfrastructureChildrenId(Integer id) {
        return dao.getByDataInfrastructureChildrenId(id);
    }

    public BootstrapTableVo getBootstrapTableVo(DataInfrastructureChildren oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataInfrastructureChildren> dataInfrastructureChildrenList = getDataInfrastructureChildrenList(oo) ;
        vo.setTotal(page.getTotal());
        vo.setRows(dataInfrastructureChildrenList);
        return vo;
    }


    public List<DataInfrastructureChildren> getDataInfrastructureChildrenList(DataInfrastructureChildren oo) {
        return dao.getDataInfrastructureChildrenList(oo);
    }
    
}

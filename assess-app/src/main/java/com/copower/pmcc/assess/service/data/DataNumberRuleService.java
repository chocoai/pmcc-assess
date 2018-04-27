package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataNumberRuleDao;
import com.copower.pmcc.assess.dal.entity.DataNumberRule;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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

@Service(value="dataNumberRuleService")
public class DataNumberRuleService {

    @Autowired
    private DataNumberRuleDao dataNumberRuleDao;

    public BootstrapTableVo getDataNumberRuleListVo(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataNumberRule> dataNumberRulesList = dataNumberRuleDao.getDataNumberRule(name);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataNumberRulesList) ? new ArrayList<DataNumberRule>() : dataNumberRulesList);
        return vo;
    }


}

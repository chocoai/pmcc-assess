package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.CsrInvalidRuleDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.CsrInvalidRule;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-5-31.
 */
@Service
public class CsrInvalidRuleService {
    @Autowired
    private CsrInvalidRuleDao csrInvalidRuleDao;
    @Autowired
    private CommonService commonService;

    /**
     * 获取过滤规则数据列表
     *
     * @param csrProjectId
     * @return
     */
    public BootstrapTableVo getInvalidRuleList(Integer csrProjectId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        CsrInvalidRule queryParam = new CsrInvalidRule();
        if (csrProjectId != null && csrProjectId > 0) {
            queryParam.setCsrProjectId(csrProjectId);
        } else {
            queryParam.setCsrProjectId(0);
            queryParam.setCreator(commonService.thisUserAccount());
        }
        List<CsrInvalidRule> list = csrInvalidRuleDao.getCsrInvalidRuleList(queryParam);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<CsrInvalidRule>() : list);
        return bootstrapTableVo;
    }

    /**
     * 保存过滤规则
     *
     * @param csrInvalidRule
     */
    public void saveInvalidRule(CsrInvalidRule csrInvalidRule) {
        if (csrInvalidRule.getId() != null && csrInvalidRule.getId() > 0) {
            csrInvalidRuleDao.updateCsrInvalidRule(csrInvalidRule);
        } else {
            csrInvalidRule.setCreator(commonService.thisUserAccount());
            csrInvalidRuleDao.addCsrInvalidRule(csrInvalidRule);
        }
    }

    /**
     * 删除过滤规则
     *
     * @param id
     */
    public void deleteInvalidRule(Integer id) {
        csrInvalidRuleDao.deleteCsrInvalidRule(id);
    }

    /**
     * 更新CsrProjectId
     *
     * @param csrProjectId
     */
    public void updateCsrProjectId(Integer csrProjectId) {
        CsrInvalidRule queryParam = new CsrInvalidRule();
        queryParam.setCsrProjectId(0);
        queryParam.setCreator(commonService.thisUserAccount());
        List<CsrInvalidRule> list = csrInvalidRuleDao.getCsrInvalidRuleList(queryParam);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(p -> {
                p.setCsrProjectId(csrProjectId);
                csrInvalidRuleDao.updateCsrInvalidRule(p);
            });
        }
    }
}

package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.common.ReflectUtils;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.csr.*;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.csr.CsrImportColumnDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 获取过滤规则数据列表
     *
     * @param csrProjectId
     * @return
     */
    public List<CsrInvalidRule> getRuleList(Integer csrProjectId) {
        CsrInvalidRule queryParam = new CsrInvalidRule();
        queryParam.setCsrProjectId(csrProjectId);
        List<CsrInvalidRule> list = csrInvalidRuleDao.getCsrInvalidRuleList(queryParam);
        return list;
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

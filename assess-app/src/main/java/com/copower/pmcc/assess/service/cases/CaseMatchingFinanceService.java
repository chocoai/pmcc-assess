package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingFinanceDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinance;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingFinanceVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:31
 * @Description:
 */
@Service
public class CaseMatchingFinanceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingFinanceDao caseMatchingFinanceDao;

    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseMatchingFinance getCaseMatchingFinanceById(Integer id) {
        return caseMatchingFinanceDao.getMatchingFinanceById(id);
    }

    public void upgradeVersion(CaseMatchingFinance po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            this.addCaseMatchingFinance(po);
        }else {
            CaseMatchingFinance oo = getCaseMatchingFinanceById(po.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanUtils.copyProperties(po,oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            this.addCaseMatchingFinance(oo);
        }
    }

    /**
     * 获取数据列表
     *
     * @param caseMatchingFinance
     * @return
     */
    public List<CaseMatchingFinance> getCaseMatchingFinanceList(CaseMatchingFinance caseMatchingFinance) {
        return caseMatchingFinanceDao.getMatchingFinanceList(caseMatchingFinance);
    }

    public BootstrapTableVo getCaseMatchingFinanceLists(CaseMatchingFinance caseMatchingFinance) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingFinanceVo> vos = Lists.newArrayList();
        getCaseMatchingFinanceList(caseMatchingFinance).stream().forEach(oo -> vos.add(getCaseMatchingFinanceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingFinanceVo>() : vos);
        return vo;
    }

    public CaseMatchingFinanceVo getCaseMatchingFinanceVo(CaseMatchingFinance caseMatchingFinance) {
        CaseMatchingFinanceVo vo = new CaseMatchingFinanceVo();
        BeanUtils.copyProperties(caseMatchingFinance, vo);
        if (!StringUtils.isEmpty(caseMatchingFinance.getServiceContent())) {
            if (org.apache.commons.lang.StringUtils.isNumeric(caseMatchingFinance.getServiceContent())) {
                vo.setServiceContentName(getValue(AssessExamineTaskConstant.ESTATE_FINANCE_SERVICE_CONTENT, Integer.parseInt(caseMatchingFinance.getServiceContent())));
            }
        }
        if (caseMatchingFinance.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_FINANCE_CATEGORY, caseMatchingFinance.getCategory()));
        }
        if (caseMatchingFinance.getNature() != null) {
            vo.setNatureName(getValue(AssessExamineTaskConstant.ESTATE_FINANCE_NATURE, caseMatchingFinance.getNature()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param caseMatchingFinance
     * @return
     */
    public boolean addCaseMatchingFinance(CaseMatchingFinance caseMatchingFinance) {
        caseMatchingFinance.setCreator(commonService.thisUserAccount());
        return caseMatchingFinanceDao.addMatchingFinance(caseMatchingFinance);
    }

    /**
     * 编辑
     *
     * @param caseMatchingFinance
     * @return
     */
    public boolean updateCaseMatchingFinance(CaseMatchingFinance caseMatchingFinance) {
        return caseMatchingFinanceDao.updateMatchingFinance(caseMatchingFinance);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseMatchingFinance(Integer id) {
        return caseMatchingFinanceDao.deleteMatchingFinance(id);
    }
}

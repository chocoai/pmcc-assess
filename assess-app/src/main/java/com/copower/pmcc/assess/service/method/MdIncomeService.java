package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeSelfSupportCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeSelfSupportDao;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCost;
import com.copower.pmcc.assess.dto.output.data.EvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeSelfSupportCostVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdIncomeService {
    @Autowired
    private MdIncomeDao mdIncomeDao;
    @Autowired
    private MdIncomeLeaseDao mdIncomeLeaseDao;
    @Autowired
    private MdIncomeSelfSupportDao mdIncomeSelfSupportDao;
    @Autowired
    private MdIncomeSelfSupportCostDao mdIncomeSelfSupportCostDao;
    @Autowired
    private CommonService commonService;

    /**
     * 保存数据
     *
     * @param mdIncomeSelfSupportCost
     */
    public void saveSelfSupportCost(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        if (mdIncomeSelfSupportCost.getId() != null && mdIncomeSelfSupportCost.getId() > 0) {
            mdIncomeSelfSupportCostDao.updateSelfSupportCost(mdIncomeSelfSupportCost);
        } else {
            mdIncomeSelfSupportCost.setCreator(commonService.thisUserAccount());
            mdIncomeSelfSupportCostDao.addSelfSupportCost(mdIncomeSelfSupportCost);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteSelfSupportCost(Integer id) {
        return mdIncomeSelfSupportCostDao.deleteSelfSupportCost(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public MdIncomeSelfSupportCost getSelfSupportCostById(Integer id) {
        return mdIncomeSelfSupportCostDao.getSelfSupportCostById(id);
    }


    /**
     * 获取数据列表
     *
     * @param supportId
     * @return
     */
    public BootstrapTableVo getSelfSupportCostList(Integer supportId,Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeSelfSupportCost where = new MdIncomeSelfSupportCost();
        if (supportId != null && supportId > 0) {
            where.setSupportId(supportId);
        } else {
            where.setSupportId(0);
            where.setType(type);
            where.setCreator(commonService.thisUserAccount());
        }

        List<MdIncomeSelfSupportCost> selfSupportCostList = mdIncomeSelfSupportCostDao.getSelfSupportCostList(where);
        List<MdIncomeSelfSupportCostVo> vos = LangUtils.transform(selfSupportCostList, p -> getSelfSupportCostVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeSelfSupportCostVo getSelfSupportCostVo(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        if (mdIncomeSelfSupportCost == null) return null;
        MdIncomeSelfSupportCostVo mdIncomeSelfSupportCostVo = new MdIncomeSelfSupportCostVo();
        BeanUtils.copyProperties(mdIncomeSelfSupportCost, mdIncomeSelfSupportCostVo);

        return mdIncomeSelfSupportCostVo;
    }
}

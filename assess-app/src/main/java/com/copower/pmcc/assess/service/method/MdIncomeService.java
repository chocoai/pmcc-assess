package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeLeaseDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeSelfSupportCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeSelfSupportDao;
import com.copower.pmcc.assess.dal.basis.entity.MdIncome;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLease;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupport;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportCost;
import com.copower.pmcc.assess.dto.input.method.MdIncomeResultDto;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeSelfSupportCostVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Autowired
    private BaseDataDicService baseDataDicService;

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
    public BootstrapTableVo getSelfSupportCostList(Integer supportId, Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeSelfSupportCost where = new MdIncomeSelfSupportCost();
        where.setType(type);
        if (supportId != null && supportId > 0) {
            where.setSupportId(supportId);
        } else {
            where.setSupportId(0);
            where.setCreator(commonService.thisUserAccount());
        }

        List<MdIncomeSelfSupportCost> selfSupportCostList = mdIncomeSelfSupportCostDao.getSelfSupportCostList(where);
        List<MdIncomeSelfSupportCostVo> vos = LangUtils.transform(selfSupportCostList, p -> getSelfSupportCostVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeSelfSupportCostVo getSelfSupportCostVo(MdIncomeSelfSupportCost mdIncomeSelfSupportCost) {
        if (mdIncomeSelfSupportCost == null) return null;
        MdIncomeSelfSupportCostVo mdIncomeSelfSupportCostVo = new MdIncomeSelfSupportCostVo();
        BeanUtils.copyProperties(mdIncomeSelfSupportCost, mdIncomeSelfSupportCostVo);
        if (mdIncomeSelfSupportCost.getAccountingSubject() != null && mdIncomeSelfSupportCost.getAccountingSubject() > 0) {
            mdIncomeSelfSupportCostVo.setAccountingSubjectName(baseDataDicService.getNameById(mdIncomeSelfSupportCost.getAccountingSubject()));
        }
        if (mdIncomeSelfSupportCost.getCostType() != null && mdIncomeSelfSupportCost.getCostType() > 0) {
            mdIncomeSelfSupportCostVo.setCostTypeName(baseDataDicService.getNameById(mdIncomeSelfSupportCost.getCostType()));
        }
        if (mdIncomeSelfSupportCost.getCostCategory() != null && mdIncomeSelfSupportCost.getCostCategory() > 0) {
            mdIncomeSelfSupportCostVo.setCostCategoryName(baseDataDicService.getNameById(mdIncomeSelfSupportCost.getCostCategory()));
        }
        return mdIncomeSelfSupportCostVo;
    }

    /**
     * 获取金额合计
     *
     * @param supportId
     * @param type
     * @return
     */
    public BigDecimal getAmountMoneyTotal(Integer supportId, Integer type) {
        String creator = supportId == 0 ? commonService.thisUserAccount() : null;
        return mdIncomeSelfSupportCostDao.getAmountMoneyTotal(supportId, type, creator);
    }

    /**
     租赁-----------
     */

    /**
     * 保存数据
     *
     * @param mdIncomeLease
     */
    public void saveLease(MdIncomeLease mdIncomeLease) {
        if (mdIncomeLease.getId() != null && mdIncomeLease.getId() > 0) {
            mdIncomeLeaseDao.updateIncomeLease(mdIncomeLease);
        } else {
            mdIncomeLease.setCreator(commonService.thisUserAccount());
            mdIncomeLeaseDao.addIncomeLease(mdIncomeLease);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteLease(Integer id) {
        return mdIncomeLeaseDao.deleteIncomeLease(id);
    }

    /**
     * 复制数据
     *
     * @param id
     * @return
     */
    public void copyLease(Integer id) {
        MdIncomeLease mdIncomeLease = mdIncomeLeaseDao.getIncomeLeaseById(id);
        mdIncomeLease.setId(0);
        mdIncomeLeaseDao.addIncomeLease(mdIncomeLease);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public MdIncomeLease getLeaseById(Integer id) {
        return mdIncomeLeaseDao.getIncomeLeaseById(id);
    }


    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getLeaseList(Integer incomeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeLease where = new MdIncomeLease();
        if (incomeId != null && incomeId > 0) {
            where.setIncomeId(incomeId);
        } else {
            where.setIncomeId(0);
            where.setCreator(commonService.thisUserAccount());
        }

        List<MdIncomeLease> leaseList = mdIncomeLeaseDao.getIncomeLeaseList(where);
        List<MdIncomeLeaseVo> vos = LangUtils.transform(leaseList, p -> getLeaseVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdIncomeLeaseVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeLeaseVo getLeaseVo(MdIncomeLease mdIncomeLease) {
        if (mdIncomeLease == null) return null;
        MdIncomeLeaseVo mdIncomeLeaseVo = new MdIncomeLeaseVo();
        BeanUtils.copyProperties(mdIncomeLease, mdIncomeLeaseVo);

        return mdIncomeLeaseVo;
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public MdIncomeSelfSupport getSelfSupportById(Integer id) {
        return mdIncomeSelfSupportDao.getSelfSupportById(id);
    }

    /**
     * 获取数据
     *
     * @param incomeId
     * @return
     */
    public MdIncomeSelfSupport getSelfSupportByIncomeId(Integer incomeId) {
        MdIncomeSelfSupport where = new MdIncomeSelfSupport();
        where.setIncomeId(incomeId);
        return mdIncomeSelfSupportDao.getSelfSupport(where);
    }

    /**
     * 保存数据
     *
     * @param mdIncomeSelfSupport
     */
    public void saveSelfSupport(MdIncomeSelfSupport mdIncomeSelfSupport) {
        if (mdIncomeSelfSupport.getId() != null && mdIncomeSelfSupport.getId() > 0) {
            mdIncomeSelfSupportDao.updateSelfSupport(mdIncomeSelfSupport);
        } else {
            mdIncomeSelfSupport.setCreator(commonService.thisUserAccount());
            mdIncomeSelfSupportDao.addSelfSupport(mdIncomeSelfSupport);

            //更新从表数据
            MdIncomeSelfSupportCost incomeSelfSupportCost = new MdIncomeSelfSupportCost();
            incomeSelfSupportCost.setSupportId(mdIncomeSelfSupport.getId());

            MdIncomeSelfSupportCost where = new MdIncomeSelfSupportCost();
            where.setSupportId(0);
            where.setCreator(commonService.thisUserAccount());
            mdIncomeSelfSupportCostDao.updateSelfSupportCost(incomeSelfSupportCost, where);
        }
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public MdIncome getIncomeById(Integer id) {
        return mdIncomeDao.getIncomeById(id);
    }

    /**
     * 保存数据
     *
     * @param mdIncome
     */
    public void saveIncome(MdIncome mdIncome) {
        if (mdIncome.getId() != null && mdIncome.getId() > 0) {
            mdIncomeDao.updateIncome(mdIncome);
        } else {
            mdIncome.setCreator(commonService.thisUserAccount());
            mdIncomeDao.addIncome(mdIncome);
            //更新从表数据
            MdIncomeLease mdIncomeLease = new MdIncomeLease();
            mdIncomeLease.setIncomeId(mdIncome.getId());

            MdIncomeLease where = new MdIncomeLease();
            where.setIncomeId(0);
            where.setCreator(commonService.thisUserAccount());
            mdIncomeLeaseDao.updateIncomeLease(mdIncomeLease, where);
        }
    }

    /**
     * 保存市场比较法的结果信息
     *
     * @param mdIncomeResultDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MdIncome saveResult(MdIncomeResultDto mdIncomeResultDto) {
        MdIncome mdIncome = mdIncomeResultDto.getMdIncome();
        MdIncomeSelfSupport selfSupport = mdIncomeResultDto.getMdIncomeSelfSupport();
        if (mdIncome != null) {
            mdIncome.setPrice(new BigDecimal("0"));
            saveIncome(mdIncome);
            if (selfSupport != null) {
                selfSupport.setIncomeId(mdIncome.getId());
                saveSelfSupport(selfSupport);
            }
        }
        return mdIncome;
    }
}

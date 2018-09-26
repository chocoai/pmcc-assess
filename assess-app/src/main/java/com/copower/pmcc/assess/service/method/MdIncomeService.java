package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdIncomeResultDto;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeForecastVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeHistoryVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
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
    private MdIncomeHistoryDao mdIncomeHistoryDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private MdIncomeForecastDao mdIncomeForecastDao;
    @Autowired
    private MdIncomeLeaseCostDao mdIncomeLeaseCostDao;
    @Autowired
    private MdIncomeDateSectionDao mdIncomeDateSectionDao;

    /**
     * 保存数据
     *
     * @param mdIncomeHistoryCost
     */
    public void saveHistory(MdIncomeHistory mdIncomeHistoryCost) {
        if (mdIncomeHistoryCost.getId() != null && mdIncomeHistoryCost.getId() > 0) {
            mdIncomeHistoryDao.updateHistory(mdIncomeHistoryCost);
        } else {
            mdIncomeHistoryCost.setCreator(commonService.thisUserAccount());
            mdIncomeHistoryDao.addHistory(mdIncomeHistoryCost);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteHistory(Integer id) {
        return mdIncomeHistoryDao.deleteHistory(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public MdIncomeHistory getHistoryById(Integer id) {
        return mdIncomeHistoryDao.getHistoryById(id);
    }

    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getHistoryList(Integer incomeId, Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeHistory where = new MdIncomeHistory();
        where.setType(type);
        if (incomeId != null && incomeId > 0) {
            where.setIncomeId(incomeId);
        } else {
            where.setIncomeId(0);
            where.setCreator(commonService.thisUserAccount());
        }

        List<MdIncomeHistory> historyList = mdIncomeHistoryDao.getHistoryList(where);
        List<MdIncomeHistoryVo> vos = LangUtils.transform(historyList, p -> getHistoryVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeHistoryVo getHistoryVo(MdIncomeHistory mdIncomeHistory) {
        if (mdIncomeHistory == null) return null;
        MdIncomeHistoryVo mdIncomeHistoryVo = new MdIncomeHistoryVo();
        BeanUtils.copyProperties(mdIncomeHistory, mdIncomeHistoryVo);
        if (mdIncomeHistory.getAccountingSubject() != null && mdIncomeHistory.getAccountingSubject() > 0) {
            mdIncomeHistoryVo.setAccountingSubjectName(baseDataDicService.getNameById(mdIncomeHistory.getAccountingSubject()));
        }
        if (mdIncomeHistory.getFirstLevelNumber() != null && mdIncomeHistory.getFirstLevelNumber() > 0) {
            mdIncomeHistoryVo.setFirstLevelNumberName(baseDataDicService.getNameById(mdIncomeHistory.getFirstLevelNumber()));
        }
        if (mdIncomeHistory.getSecondLevelNumber() != null && mdIncomeHistory.getSecondLevelNumber() > 0) {
            mdIncomeHistoryVo.setSecondLevelNumberName(baseDataDicService.getNameById(mdIncomeHistory.getSecondLevelNumber()));
        }
        return mdIncomeHistoryVo;
    }

    /**
     * 保存预测数据
     *
     * @param mdIncomeForecast
     * @return
     */
    public void saveForecast(MdIncomeForecast mdIncomeForecast) {
        if (mdIncomeForecast.getId() != null && mdIncomeForecast.getId() > 0) {
            mdIncomeForecastDao.updateForecast(mdIncomeForecast);
        } else {
            mdIncomeForecast.setCreator(commonService.thisUserAccount());
            mdIncomeForecastDao.addForecast(mdIncomeForecast);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteForecast(Integer id) {
        return mdIncomeForecastDao.deleteForecast(id);
    }

    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getForecastList(Integer incomeId, Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeForecast where = new MdIncomeForecast();
        where.setType(type);
        if (incomeId != null && incomeId > 0) {
            where.setIncomeId(incomeId);
        } else {
            where.setIncomeId(0);
            where.setCreator(commonService.thisUserAccount());
        }

        List<MdIncomeForecast> historyList = mdIncomeForecastDao.getForecastList(where);
        List<MdIncomeForecastVo> vos = LangUtils.transform(historyList, p -> getForecastVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeForecastVo getForecastVo(MdIncomeForecast mdIncomeForecast) {
        if (mdIncomeForecast == null) return null;
        MdIncomeForecastVo mdIncomeForecastVo = new MdIncomeForecastVo();
        BeanUtils.copyProperties(mdIncomeForecast, mdIncomeForecastVo);
        MdIncomeDateSection dateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeForecast.getSectionId());
        if (dateSection != null) {
            mdIncomeForecastVo.setBeginDate(dateSection.getBeginDate());
            mdIncomeForecastVo.setEndDate(dateSection.getEndDate());
            mdIncomeForecastVo.setYearCount(dateSection.getYearCount());
        }
        return mdIncomeForecastVo;
    }

    /**
     * 获取数量
     *
     * @param sectionId
     * @return
     */
    public int getForecastCountBySectionId(Integer sectionId) {
        return mdIncomeForecastDao.getCountBySectionId(sectionId);
    }


    /**
     * 获取金额合计
     *
     * @param supportId
     * @param type
     * @return
     */
    public BigDecimal getAmountMoneyTotal(Integer supportId, Integer type) {
        return null;
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
     * 获取数量
     *
     * @param sectionId
     * @return
     */
    public int getLeaseCountBySectionId(Integer sectionId) {
        return mdIncomeLeaseDao.getCountBySectionId(sectionId);
    }

    /**
     * 获取数据列表
     *
     * @param sectionId
     * @return
     */
    public BootstrapTableVo getLeaseList(Integer sectionId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeLease where = new MdIncomeLease();
        if (sectionId != null && sectionId > 0) {
            where.setSectionId(sectionId);
        } else {
            where.setSectionId(0);
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
            mdIncomeLease.setSectionId(mdIncome.getId());

            MdIncomeLease where = new MdIncomeLease();
            where.setSectionId(0);
            where.setCreator(commonService.thisUserAccount());
            mdIncomeLeaseDao.updateIncomeLease(mdIncomeLease, where);
        }
    }

    /**
     * 保存租赁成本数据
     *
     * @param mdIncomeLeaseCost
     */
    public void saveLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) {
        if (mdIncomeLeaseCost.getId() != null && mdIncomeLeaseCost.getId() > 0) {
            mdIncomeLeaseCostDao.updateLeaseCost(mdIncomeLeaseCost);
        } else {
            mdIncomeLeaseCost.setCreator(commonService.thisUserAccount());
            mdIncomeLeaseCostDao.addLeaseCost(mdIncomeLeaseCost);
        }
    }

    /**
     * 获取数量
     *
     * @param sectionId
     * @return
     */
    public int getLeaseCostCountBySectionId(Integer sectionId) {
        return mdIncomeLeaseCostDao.getCountBySectionId(sectionId);
    }

    /**
     * 保存收益法法的结果信息
     *
     * @param mdIncomeResultDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MdIncome saveResult(MdIncomeResultDto mdIncomeResultDto) {
        MdIncome mdIncome = mdIncomeResultDto.getMdIncome();
        MdIncomeHistory selfSupport = null;
        if (mdIncome != null) {
            mdIncome.setPrice(new BigDecimal("0"));
            saveIncome(mdIncome);
            if (selfSupport != null) {
                selfSupport.setIncomeId(mdIncome.getId());
                saveHistory(selfSupport);
            }
        }
        return mdIncome;
    }
}

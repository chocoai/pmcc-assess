package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.method.MethodDataTypeEnum;
import com.copower.pmcc.assess.common.enums.method.MethodIncomeFormTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.*;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdIncomeResultDto;
import com.copower.pmcc.assess.dto.output.method.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.Collator;
import java.util.*;

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
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private MdIncomeForecastYearDao mdIncomeForecastYearDao;
    @Autowired
    private MdIncomeForecastMonthDao mdIncomeForecastMonthDao;
    @Autowired
    private PublicService publicService;
    @Autowired
    private MdIncomeForecastAnalyseDao mdIncomeForecastAnalyseDao;
    @Autowired
    private MdIncomeForecastItemDao mdIncomeForecastItemDao;
    @Autowired
    private MdIncomeForecastAnalyseItemDao mdIncomeForecastAnalyseItemDao;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private MdIncomePriceInvestigationDao mdIncomePriceInvestigationDao;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private SchemeInfoDao schemeInfoDao;

    /**
     * 保存数据
     *
     * @param mdIncomeHistory
     */
    @Transactional
    public void saveHistory(MdIncomeHistory mdIncomeHistory) {
        if (mdIncomeHistory.getId() != null && mdIncomeHistory.getId() > 0) {
            mdIncomeHistoryDao.updateHistory(mdIncomeHistory);
        } else {
            mdIncomeHistory.setBisForecast(false);
            mdIncomeHistory.setCreator(commonService.thisUserAccount());
            mdIncomeHistoryDao.addHistory(mdIncomeHistory);
        }
        generatorForecastAnalyse(mdIncomeHistory);
    }

    /**
     * 历史数据添加到预测数据
     *
     * @param ids
     */
    @Transactional
    public void historyToForecast(List<Integer> ids, Integer year, String formType) {
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Integer id : ids) {
                MdIncomeHistory history = mdIncomeHistoryDao.getHistoryById(id);
                //找到来源一致的预测分析
                MdIncomeForecastAnalyse where = new MdIncomeForecastAnalyse();
                where.setIncomeId(history.getIncomeId());
                where.setType(history.getType());
                where.setSourceType(history.getSourceType());
                where.setYear(year);
                where.setFormType(Integer.valueOf(formType));
                MdIncomeForecastAnalyse forecastAnalyse = mdIncomeForecastAnalyseDao.getForecastAnalyse(where);
                if (forecastAnalyse != null) {
                    history.setBisForecast(true);
                    history.setForecastAnalyseId(forecastAnalyse.getId());
                    mdIncomeHistoryDao.updateHistory(history);
                    forecastAnalyse.setBisParticipateIn(true);
                    mdIncomeForecastAnalyseDao.updateForecastAnalyse(forecastAnalyse);
                }
            }

        }
    }

    /**
     * 预测数据还原成历史数据
     *
     * @param ids
     */
    @Transactional
    public void forecastToHistory(List<Integer> ids, Integer incomeId, Integer type, Integer formType) {
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Integer id : ids) {
                MdIncomeHistory history = mdIncomeHistoryDao.getHistoryById(id);
                history.setBisForecast(false);
                history.setForecastAnalyseId(0);
                mdIncomeHistoryDao.updateHistory(history);
            }

            //查找历史数据分析中是否还存在对应关联的数据
            MdIncomeForecastAnalyse whereForecastAnalyse = new MdIncomeForecastAnalyse();
            whereForecastAnalyse.setIncomeId(incomeId);
            whereForecastAnalyse.setType(type);
            whereForecastAnalyse.setFormType(formType);
            whereForecastAnalyse.setBisParticipateIn(true);
            List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(whereForecastAnalyse);
            if (CollectionUtils.isNotEmpty(analyseList)) {
                for (MdIncomeForecastAnalyse analyse : analyseList) {
                    MdIncomeHistory historyWhere = new MdIncomeHistory();
                    historyWhere.setForecastAnalyseId(analyse.getId());
                    int count = mdIncomeHistoryDao.getHistoryCount(historyWhere);
                    if (count <= 0) {
                        analyse.setBisParticipateIn(false);
                        mdIncomeForecastAnalyseDao.updateForecastAnalyse(analyse);
                    }
                }
            }
        }
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteHistory(String ids) {
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        if (CollectionUtils.isEmpty(integers)) return;
        for (Integer id : integers) {
            MdIncomeHistory history = mdIncomeHistoryDao.getHistoryById(id);
            mdIncomeHistoryDao.deleteHistory(id);
            //如果该年份下的历史数据分析没有关联的数据，则将该历史数据分析删除
            MdIncomeHistory historyWhere = new MdIncomeHistory();
            historyWhere.setIncomeId(history.getIncomeId());
            historyWhere.setType(history.getType());
            historyWhere.setYear(history.getYear());
            if (mdIncomeHistoryDao.getHistoryCount(historyWhere) <= 0) {
                MdIncomeForecastAnalyse whereForecastAnalyse = new MdIncomeForecastAnalyse();
                whereForecastAnalyse.setIncomeId(history.getIncomeId());
                whereForecastAnalyse.setType(history.getType());
                whereForecastAnalyse.setYear(history.getYear());
                List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(whereForecastAnalyse);
                if (CollectionUtils.isNotEmpty(analyseList)) {
                    analyseList.forEach(o -> mdIncomeForecastAnalyseDao.deleteForecastAnalyse(o.getId()));
                }
            }
        }
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
     * 生成历史数据分析
     *
     * @param mdIncomeHistory
     */
    public void generatorForecastAnalyse(MdIncomeHistory mdIncomeHistory) {
        MdIncomeForecastAnalyse where = new MdIncomeForecastAnalyse();
        where.setIncomeId(mdIncomeHistory.getIncomeId());
        where.setType(mdIncomeHistory.getType());
        where.setFormType(mdIncomeHistory.getFormType());
        where.setSourceType(mdIncomeHistory.getSourceType());
        Integer year = null;
        switch (MethodIncomeFormTypeEnum.create(mdIncomeHistory.getFormType())) {
            case DEFUALT:
                year = mdIncomeHistory.getYear();
                break;
            case RESTAURANT:
                year = DateUtils.getYear(mdIncomeHistory.getBeginDate());
                break;
        }
        where.setYear(year);
        if (mdIncomeForecastAnalyseDao.getForecastAnalyseCount(where) <= 0) {
            MdIncomeForecastAnalyse mdIncomeForecastAnalyse = new MdIncomeForecastAnalyse();
            mdIncomeForecastAnalyse.setIncomeId(mdIncomeHistory.getIncomeId());
            mdIncomeForecastAnalyse.setType(mdIncomeHistory.getType());
            mdIncomeForecastAnalyse.setFormType(mdIncomeHistory.getFormType());
            mdIncomeForecastAnalyse.setYear(year);
            mdIncomeForecastAnalyse.setSourceType(mdIncomeHistory.getSourceType());
            mdIncomeForecastAnalyse.setBisParticipateIn(false);
            mdIncomeForecastAnalyse.setCreator(commonService.thisUserAccount());
            mdIncomeForecastAnalyseDao.addForecastAnalyse(mdIncomeForecastAnalyse);
        }
    }

    /**
     * 开始分析
     *
     * @param incomeId
     * @param type
     */
    @Transactional
    public void startAnalyse(Integer incomeId, Integer type, Integer formType) {
        MdIncomeForecastAnalyse analyseWhere = new MdIncomeForecastAnalyse();
        analyseWhere.setIncomeId(incomeId);
        analyseWhere.setType(type);
        analyseWhere.setFormType(formType);
        analyseWhere.setBisParticipateIn(true);
        List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(analyseWhere);

        //删除原来的明细
        MdIncomeForecastAnalyseItem oldItem = new MdIncomeForecastAnalyseItem();
        oldItem.setIncomeId(incomeId);
        oldItem.setType(type);
        oldItem.setFormType(formType);
        List<MdIncomeForecastAnalyseItem> oldForecastAnalyseItemList = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(oldItem);
        if (CollectionUtils.isNotEmpty(oldForecastAnalyseItemList)) {
            for (MdIncomeForecastAnalyseItem old : oldForecastAnalyseItemList) {
                mdIncomeForecastAnalyseItemDao.deleteForecastAnalyseItem(old.getId());
            }
        }
        if (CollectionUtils.isNotEmpty(analyseList)) {
            for (int i = 0; i < analyseList.size(); i++) {
                //历史数据添加到分析明细
                MdIncomeHistory historyWhere = new MdIncomeHistory();
                historyWhere.setForecastAnalyseId(analyseList.get(i).getId());
                List<MdIncomeHistory> currhistoryList = mdIncomeHistoryDao.getHistoryList(historyWhere);
                //同年同来源数据总价
                BigDecimal total = new BigDecimal("0");
                //经营成本
                BigDecimal manageCost = new BigDecimal("0");
                //经营费用
                BigDecimal engageCost = new BigDecimal("0");
                //增值税及其附加
                BigDecimal operatingCost = new BigDecimal("0");
                //管理费用
                BigDecimal managerCost = new BigDecimal("0");
                //财务费用
                BigDecimal financeCost = new BigDecimal("0");
                if (CollectionUtils.isNotEmpty(currhistoryList)) {
                    for (MdIncomeHistory mdIncomeHistory : currhistoryList) {
                        total = total.add(mdIncomeHistory.getAmountMoney());
                        //经营成本 = 主成本业务+其他成本业务
                        if (type == 1) {
                            //主经营成本
                            BaseDataDic mainManagerCost = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_MAIN_MANAGER_COST);
                            //其他经营成本
                            BaseDataDic otherManagerCost = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_OTHER_MANAGER_COST);
                            if (mainManagerCost.getId().equals(mdIncomeHistory.getAccountingSubject()) || otherManagerCost.getId().equals(mdIncomeHistory.getAccountingSubject())) {
                                if (mdIncomeHistory.getAmountMoney() != null) {
                                    manageCost = manageCost.add(mdIncomeHistory.getAmountMoney());
                                }
                            }
                            //经营费用
                            BaseDataDic engageCostDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_ENGAGE_COST);
                            if (engageCostDic.getId().equals(mdIncomeHistory.getAccountingSubject())) {
                                if (mdIncomeHistory.getAmountMoney() != null) {
                                    engageCost = engageCost.add(mdIncomeHistory.getAmountMoney());
                                }
                            }
                            //增值税及其附加
                            BaseDataDic operatingCostDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_OPERATING_COST);
                            if (operatingCostDic.getId().equals(mdIncomeHistory.getAccountingSubject())) {
                                if (mdIncomeHistory.getAmountMoney() != null) {
                                    operatingCost = operatingCost.add(mdIncomeHistory.getAmountMoney());
                                }
                            }
                            //管理费用
                            BaseDataDic managerCostDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_MANAGER_COST);
                            if (managerCostDic.getId().equals(mdIncomeHistory.getAccountingSubject())) {
                                if (mdIncomeHistory.getAmountMoney() != null) {
                                    managerCost = managerCost.add(mdIncomeHistory.getAmountMoney());
                                }
                            }
                            //财务费用
                            BaseDataDic financeCostDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_FINANCE_COST);
                            if (financeCostDic.getId().equals(mdIncomeHistory.getAccountingSubject())) {
                                if (mdIncomeHistory.getAmountMoney() != null) {
                                    financeCost = financeCost.add(mdIncomeHistory.getAmountMoney());
                                }
                            }
                        }
                    }
                }
                analyseList.get(i).setAmountMoney(total);
                if (type == 1) {
                    //找到当年度的收入
                    MdIncomeForecastAnalyse incomeForecastAnalyse = new MdIncomeForecastAnalyse();
                    incomeForecastAnalyse.setIncomeId(incomeId);
                    incomeForecastAnalyse.setType(0);
                    List<MdIncomeForecastAnalyse> incomeList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(incomeForecastAnalyse);
                    if (CollectionUtils.isNotEmpty(incomeList)) {
                        for (MdIncomeForecastAnalyse incomeItem : incomeList) {
                            if (StringUtils.equals(String.valueOf(analyseList.get(i).getYear()), String.valueOf(incomeItem.getYear()))) {
                                //成本比率=成本金额（主营、其他）/年收入
                                analyseList.get(i).setCostRatio(manageCost.divide(incomeItem.getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP));
                                //经营利润=年收入-经营成本-经营费用-经营税金及附加-管理费用-财务费用
                                BigDecimal earnedProfit = incomeItem.getAmountMoney().subtract(total);
                                analyseList.get(i).setEarnedProfit(earnedProfit);
                                //经营利润比率=经营利润/年收入
                                BigDecimal earnedProfitRatio = earnedProfit.divide(incomeItem.getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                                analyseList.get(i).setEarnedProfitRatio(earnedProfitRatio);
                                //经营费用比率
                                BigDecimal operatingExpensesRatio = engageCost.divide(incomeItem.getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                                analyseList.get(i).setOperatingExpensesRatio(operatingExpensesRatio);
                                //增值税及其附加比率
                                BigDecimal operatingTaxRatio = operatingCost.divide(incomeItem.getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                                analyseList.get(i).setOperatingTaxRatio(operatingTaxRatio);
                                //管理费用比率
                                BigDecimal managementCostRatio = managerCost.divide(incomeItem.getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                                analyseList.get(i).setManagementCostRatio(managementCostRatio);
                                //财务费用比率
                                BigDecimal financialCostRatio = financeCost.divide(incomeItem.getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                                analyseList.get(i).setFinancialCostRatio(financialCostRatio);
                            }
                        }
                    }

                }
                mdIncomeForecastAnalyseDao.updateForecastAnalyse(analyseList.get(i));
                //生成预测明细数据
                List<List<MdIncomeHistory>> goodsList = getGoodsList(currhistoryList);
                if (CollectionUtils.isNotEmpty(goodsList)) {
                    for (List<MdIncomeHistory> goods : goodsList) {
                        if (CollectionUtils.isNotEmpty(goods)) {
                            //同种物品一年总价
                            BigDecimal sameGoodsTotal = new BigDecimal("0");
                            //同种物品一年个数
                            Integer sameNumTotal = 0;
                            for (MdIncomeHistory item : goods) {
                                sameGoodsTotal = sameGoodsTotal.add(item.getAmountMoney());
                                if (item.getNumber() != null) {
                                    sameNumTotal += item.getNumber();
                                }
                            }
                            //新增明细
                            MdIncomeForecastAnalyseItem mdIncomeForecastAnalyseItem = new MdIncomeForecastAnalyseItem();
                            mdIncomeForecastAnalyseItem.setForecastAnalyseId(analyseList.get(i).getId());
                            mdIncomeForecastAnalyseItem.setFirstLevelNumber(goods.get(0).getFirstLevelNumber());
                            mdIncomeForecastAnalyseItem.setSecondLevelNumber(goods.get(0).getSecondLevelNumber());
                            mdIncomeForecastAnalyseItem.setAccountingSubject(goods.get(0).getAccountingSubject());
                            mdIncomeForecastAnalyseItem.setSourceType(goods.get(0).getSourceType());
                            mdIncomeForecastAnalyseItem.setYear(analyseList.get(i).getYear());
                            mdIncomeForecastAnalyseItem.setIncomeId(analyseList.get(i).getIncomeId());
                            mdIncomeForecastAnalyseItem.setAmountMoney(sameGoodsTotal);
                            mdIncomeForecastAnalyseItem.setNumber(sameNumTotal);
                            mdIncomeForecastAnalyseItem.setType(analyseList.get(i).getType());
                            mdIncomeForecastAnalyseItem.setFormType(analyseList.get(i).getFormType());
                            mdIncomeForecastAnalyseItemDao.addForecastAnalyseItem(mdIncomeForecastAnalyseItem);
                        }
                    }
                }

            }
        }

    }

    //一条历史数据分析对应的List<MdIncomeHistory>有几种物品
    public List<List<MdIncomeHistory>> getGoodsList(List<MdIncomeHistory> MdIncomeHistorys) {
        if (CollectionUtils.isEmpty(MdIncomeHistorys)) return null;
        List<List<MdIncomeHistory>> allGoods = Lists.newArrayList();
        for (MdIncomeHistory item : MdIncomeHistorys) {
            if (CollectionUtils.isEmpty(allGoods)) {
                List<MdIncomeHistory> goods = Lists.newArrayList();
                goods.add(item);
                allGoods.add(goods);
            } else {
                //该类型物品是否已存在
                boolean flag = false;
                step:
                for (List<MdIncomeHistory> list : allGoods) {
                    for (MdIncomeHistory good : list) {
                        if (item.getAccountingSubject().equals(good.getAccountingSubject())
                                && item.getFirstLevelNumber().equals(good.getFirstLevelNumber()) && item.getSecondLevelNumber().equals(good.getSecondLevelNumber())) {
                            list.add(item);
                            flag = true;
                            break step;
                        }
                    }
                }
                //不存在则造一个list存放
                if (!flag) {
                    List<MdIncomeHistory> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                }
            }
        }
        return allGoods;
    }


    //写入数量趋势和金额趋势(二级编号)
    public List<MdIncomeForecastAnalyseItemVo> getSecondLevelTrend(Integer forecastAnalyseId) {
        MdIncomeForecastAnalyse analyse = mdIncomeForecastAnalyseDao.getForecastAnalyseById(forecastAnalyseId);
        //1.来源相同二级编号相同物品归类
        MdIncomeForecastAnalyseItem forecastAnalyseItem = new MdIncomeForecastAnalyseItem();
        forecastAnalyseItem.setSourceType(analyse.getSourceType());
        forecastAnalyseItem.setFormType(analyse.getFormType());
        forecastAnalyseItem.setIncomeId(analyse.getIncomeId());
        forecastAnalyseItem.setType(analyse.getType());
        List<MdIncomeForecastAnalyseItem> forecastAnalyseItemList = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(forecastAnalyseItem);
        List<List<MdIncomeForecastAnalyseItem>> allGoods = Lists.newArrayList();
        for (MdIncomeForecastAnalyseItem item : forecastAnalyseItemList) {
            if (CollectionUtils.isEmpty(allGoods)) {
                List<MdIncomeForecastAnalyseItem> goods = Lists.newArrayList();
                goods.add(item);
                allGoods.add(goods);
            } else {
                //该类型物品是否已存在
                boolean flag = false;
                step:
                for (List<MdIncomeForecastAnalyseItem> list : allGoods) {
                    for (MdIncomeForecastAnalyseItem good : list) {
                        if (item.getForecastAnalyseId().equals(good.getForecastAnalyseId()) && item.getYear().equals(good.getYear()) && item.getAccountingSubject().equals(good.getAccountingSubject()) && item.getSourceType().equals(good.getSourceType())
                                && item.getFirstLevelNumber().equals(good.getFirstLevelNumber()) && item.getSecondLevelNumber().equals(good.getSecondLevelNumber())) {
                            list.add(item);
                            flag = true;
                            break step;
                        }
                    }
                }
                //不存在则造一个list存放
                if (!flag) {
                    List<MdIncomeForecastAnalyseItem> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                }
            }
        }
        //得到年份不同但二级编号相同 的金额与数量
        List<MdIncomeForecastAnalyseItem> tempLists = Lists.newArrayList();
        for (List<MdIncomeForecastAnalyseItem> goods : allGoods) {
            BigDecimal sumMoney = new BigDecimal("0");
            Integer sumQuantity = 0;
            MdIncomeForecastAnalyseItem item = new MdIncomeForecastAnalyseItem();
            Integer year = null;
            Integer forecastAnalyseId2 = null;
            Integer accountingSubject = null;
            String firstLevelNumber = null;
            String secondLevelNumber = null;
            for (int i = 0; i < goods.size(); i++) {
                sumMoney = sumMoney.add(goods.get(i).getAmountMoney());
                sumQuantity += goods.get(i).getNumber();
                year = goods.get(i).getYear();
                forecastAnalyseId2 = goods.get(i).getForecastAnalyseId();
                accountingSubject = goods.get(i).getAccountingSubject();
                firstLevelNumber = goods.get(i).getFirstLevelNumber();
                secondLevelNumber = goods.get(i).getSecondLevelNumber();
            }
            item.setYear(year);
            item.setForecastAnalyseId(forecastAnalyseId2);
            item.setAccountingSubject(accountingSubject);
            item.setFirstLevelNumber(firstLevelNumber);
            item.setSecondLevelNumber(secondLevelNumber);
            item.setAmountMoney(sumMoney);
            item.setNumber(sumQuantity);
            tempLists.add(item);

        }
        Map<List<MdIncomeForecastAnalyseItem>, String> tempMaps = Maps.newHashMap();
        //所有二级编号类型
        List<String> AllSecondLevelTypes = Lists.newArrayList();
        for (MdIncomeForecastAnalyseItem item : tempLists) {
            AllSecondLevelTypes.add(String.format("%s/%s/%s", item.getAccountingSubject(), item.getFirstLevelNumber(), item.getSecondLevelNumber()));
        }
        //去重
        List<String> secondLevelList = generateCommonMethod.removeDuplicate(AllSecondLevelTypes);
        for (String secondLevel : secondLevelList) {
            List<MdIncomeForecastAnalyseItem> secondLevelAnalyseItemLists = Lists.newArrayList();
            for (MdIncomeForecastAnalyseItem item : tempLists) {
                String formatName = String.format("%s/%s/%s", item.getAccountingSubject(), item.getFirstLevelNumber(), item.getSecondLevelNumber());
                if (secondLevel.equals(formatName)) {
                    secondLevelAnalyseItemLists.add(item);
                }
            }
            tempMaps.put(secondLevelAnalyseItemLists, secondLevel);
        }

        //后续每年与前一条数据做比较
        List<MdIncomeForecastAnalyseItemVo> vos = Lists.newArrayList();
        for (Map.Entry<List<MdIncomeForecastAnalyseItem>, String> map : tempMaps.entrySet()) {
            List<MdIncomeForecastAnalyseItem> analyseItems = map.getKey();
            for (int i = 0; i < analyseItems.size(); i++) {
                MdIncomeForecastAnalyseItem item = analyseItems.get(i);
                MdIncomeForecastAnalyseItemVo mdIncomeForecastAnalyseItemVo = new MdIncomeForecastAnalyseItemVo();
                if (item.getForecastAnalyseId().equals(forecastAnalyseId)) {
                    BeanUtils.copyProperties(analyseItems.get(i), mdIncomeForecastAnalyseItemVo);
                    if (analyseItems.get(i).getAccountingSubject() != null && analyseItems.get(i).getAccountingSubject() > 0) {
                        String accountingSubjectName = baseDataDicService.getNameById(analyseItems.get(i).getAccountingSubject());
                        StringBuilder name = new StringBuilder();
                        if (StringUtils.isNotEmpty(accountingSubjectName)) {
                            name.append(accountingSubjectName);
                        }
                        if (StringUtils.isNotEmpty(analyseItems.get(i).getFirstLevelNumber())) {
                            name.append("/").append(analyseItems.get(i).getFirstLevelNumber());
                        }
                        if (StringUtils.isNotEmpty(analyseItems.get(i).getSecondLevelNumber())) {
                            name.append("/").append(analyseItems.get(i).getSecondLevelNumber());
                        }
                        mdIncomeForecastAnalyseItemVo.setName(name.toString());
                    }
                    if (i == 0) {
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
                if (i > 0) {
                    //金额趋势=现在金额：昨年金额
                    BigDecimal univalentTrend = analyseItems.get(i).getAmountMoney().divide(analyseItems.get(i - 1).getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                    //数量趋势=现在数量：昨年数量
                    BigDecimal quantitativeTrend = null;
                    if (analyseItems.get(i).getNumber() != null && 0 != analyseItems.get(i).getNumber() && analyseItems.get(i - 1).getNumber() != null && 0 != analyseItems.get(i - 1).getNumber()) {
                        quantitativeTrend = new BigDecimal(analyseItems.get(i).getNumber()).divide(new BigDecimal(analyseItems.get(i - 1).getNumber()), 2, BigDecimal.ROUND_HALF_UP);
                    }
                    if (item.getForecastAnalyseId().equals(forecastAnalyseId)) {
                        mdIncomeForecastAnalyseItemVo.setMoneyTrend(univalentTrend.toString());
                        if (quantitativeTrend != null) {
                            mdIncomeForecastAnalyseItemVo.setQuantitativeTrend(quantitativeTrend.toString());
                            //单价趋势：金额趋势：数量趋势
                            mdIncomeForecastAnalyseItemVo.setUnitPriceTrend(univalentTrend.divide(quantitativeTrend, 2, BigDecimal.ROUND_HALF_UP).toString());
                        }
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
            }
        }
        this.orderVos(vos);
        return vos;
    }


    //写入数量趋势和金额趋势(一级编号)
    public List<MdIncomeForecastAnalyseItemVo> getStairTrend(Integer forecastAnalyseId) {
        MdIncomeForecastAnalyse analyse = mdIncomeForecastAnalyseDao.getForecastAnalyseById(forecastAnalyseId);
        //1.来源相同一级编号相同物品归类
        MdIncomeForecastAnalyseItem forecastAnalyseItem = new MdIncomeForecastAnalyseItem();
        forecastAnalyseItem.setSourceType(analyse.getSourceType());
        forecastAnalyseItem.setFormType(analyse.getFormType());
        forecastAnalyseItem.setIncomeId(analyse.getIncomeId());
        forecastAnalyseItem.setType(analyse.getType());
        List<MdIncomeForecastAnalyseItem> forecastAnalyseItemList = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(forecastAnalyseItem);
        List<List<MdIncomeForecastAnalyseItem>> allGoods = Lists.newArrayList();
        for (MdIncomeForecastAnalyseItem item : forecastAnalyseItemList) {
            if (CollectionUtils.isEmpty(allGoods)) {
                List<MdIncomeForecastAnalyseItem> goods = Lists.newArrayList();
                goods.add(item);
                allGoods.add(goods);
            } else {
                //该类型物品是否已存在
                boolean flag = false;
                step:
                for (List<MdIncomeForecastAnalyseItem> list : allGoods) {
                    for (MdIncomeForecastAnalyseItem good : list) {
                        if (item.getForecastAnalyseId().equals(good.getForecastAnalyseId()) && item.getYear().equals(good.getYear()) && item.getAccountingSubject().equals(good.getAccountingSubject()) && item.getSourceType().equals(good.getSourceType())
                                && item.getFirstLevelNumber().equals(good.getFirstLevelNumber())) {
                            list.add(item);
                            flag = true;
                            break step;
                        }
                    }
                }
                //不存在则造一个list存放
                if (!flag) {
                    List<MdIncomeForecastAnalyseItem> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                }
            }
        }
        //得到年份不同但一级编号相同 的金额与数量
        List<MdIncomeForecastAnalyseItem> tempLists = Lists.newArrayList();
        for (List<MdIncomeForecastAnalyseItem> goods : allGoods) {
            BigDecimal sumMoney = new BigDecimal("0");
            Integer sumQuantity = 0;
            MdIncomeForecastAnalyseItem item = new MdIncomeForecastAnalyseItem();
            Integer year = null;
            Integer forecastAnalyseId2 = null;
            Integer accountingSubject = null;
            String firstLevelNumber = null;
            for (int i = 0; i < goods.size(); i++) {
                sumMoney = sumMoney.add(goods.get(i).getAmountMoney());
                sumQuantity += goods.get(i).getNumber();
                year = goods.get(i).getYear();
                forecastAnalyseId2 = goods.get(i).getForecastAnalyseId();
                accountingSubject = goods.get(i).getAccountingSubject();
                firstLevelNumber = goods.get(i).getFirstLevelNumber();
            }
            item.setYear(year);
            item.setForecastAnalyseId(forecastAnalyseId2);
            item.setAccountingSubject(accountingSubject);
            item.setFirstLevelNumber(firstLevelNumber);
            item.setAmountMoney(sumMoney);
            item.setNumber(sumQuantity);
            tempLists.add(item);

        }
        Map<List<MdIncomeForecastAnalyseItem>, String> tempMaps = Maps.newHashMap();
        //所有一级编号类型
        List<String> AllFirstLevelTypes = Lists.newArrayList();
        for (MdIncomeForecastAnalyseItem item : tempLists) {
            AllFirstLevelTypes.add(String.format("%s/%s", item.getAccountingSubject(), item.getFirstLevelNumber()));
        }
        //去重
        List<String> FirstLevelList = generateCommonMethod.removeDuplicate(AllFirstLevelTypes);
        for (String firstLevel : FirstLevelList) {
            List<MdIncomeForecastAnalyseItem> FirstLevelAnalyseItemLists = Lists.newArrayList();
            for (MdIncomeForecastAnalyseItem item : tempLists) {
                String formatName = String.format("%s/%s", item.getAccountingSubject(), item.getFirstLevelNumber());
                if (firstLevel.equals(formatName)) {
                    FirstLevelAnalyseItemLists.add(item);
                }
            }
            tempMaps.put(FirstLevelAnalyseItemLists, firstLevel);
        }

        //后续每年与前一条数据做比较
        List<MdIncomeForecastAnalyseItemVo> vos = Lists.newArrayList();
        for (Map.Entry<List<MdIncomeForecastAnalyseItem>, String> map : tempMaps.entrySet()) {
            List<MdIncomeForecastAnalyseItem> analyseItems = map.getKey();
            for (int i = 0; i < analyseItems.size(); i++) {
                MdIncomeForecastAnalyseItem item = analyseItems.get(i);
                MdIncomeForecastAnalyseItemVo mdIncomeForecastAnalyseItemVo = new MdIncomeForecastAnalyseItemVo();
                if (item.getForecastAnalyseId().equals(forecastAnalyseId)) {
                    BeanUtils.copyProperties(analyseItems.get(i), mdIncomeForecastAnalyseItemVo);
                    if (analyseItems.get(i).getAccountingSubject() != null && analyseItems.get(i).getAccountingSubject() > 0) {
                        String accountingSubjectName = baseDataDicService.getNameById(analyseItems.get(i).getAccountingSubject());
                        mdIncomeForecastAnalyseItemVo.setName(String.format("%s/%s", accountingSubjectName, analyseItems.get(i).getFirstLevelNumber()));
                    }
                    if (i == 0) {
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
                if (i > 0) {
                    //金额趋势=现在金额：昨年金额
                    BigDecimal univalentTrend = analyseItems.get(i).getAmountMoney().divide(analyseItems.get(i - 1).getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                    //数量趋势=现在数量：昨年数量
                    BigDecimal quantitativeTrend = null;
                    if (analyseItems.get(i).getNumber() != null && 0 != analyseItems.get(i).getNumber() && analyseItems.get(i - 1).getNumber() != null && 0 != analyseItems.get(i - 1).getNumber()) {
                        quantitativeTrend = new BigDecimal(analyseItems.get(i).getNumber()).divide(new BigDecimal(analyseItems.get(i - 1).getNumber()), 2, BigDecimal.ROUND_HALF_UP);
                    }
                    if (item.getForecastAnalyseId().equals(forecastAnalyseId)) {
                        mdIncomeForecastAnalyseItemVo.setMoneyTrend(univalentTrend.toString());
                        if (quantitativeTrend != null) {
                            mdIncomeForecastAnalyseItemVo.setQuantitativeTrend(quantitativeTrend.toString());
                            //单价趋势：金额趋势：数量趋势
                            mdIncomeForecastAnalyseItemVo.setUnitPriceTrend(univalentTrend.divide(quantitativeTrend, 2, BigDecimal.ROUND_HALF_UP).toString());
                        }
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
            }
        }
        this.orderVos(vos);
        return vos;
    }

    //写入数量趋势和金额趋势(会计科目)
    public List<MdIncomeForecastAnalyseItemVo> getAccountingSubjectTrend(Integer forecastAnalyseId) {
        MdIncomeForecastAnalyse analyse = mdIncomeForecastAnalyseDao.getForecastAnalyseById(forecastAnalyseId);
        //1.来源相同会计科目相同物品归类
        MdIncomeForecastAnalyseItem forecastAnalyseItem = new MdIncomeForecastAnalyseItem();
        forecastAnalyseItem.setSourceType(analyse.getSourceType());
        forecastAnalyseItem.setFormType(analyse.getFormType());
        forecastAnalyseItem.setIncomeId(analyse.getIncomeId());
        forecastAnalyseItem.setType(analyse.getType());
        List<MdIncomeForecastAnalyseItem> forecastAnalyseItemList = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(forecastAnalyseItem);
        List<List<MdIncomeForecastAnalyseItem>> allGoods = Lists.newArrayList();
        for (MdIncomeForecastAnalyseItem item : forecastAnalyseItemList) {
            if (CollectionUtils.isEmpty(allGoods)) {
                List<MdIncomeForecastAnalyseItem> goods = Lists.newArrayList();
                goods.add(item);
                allGoods.add(goods);
            } else {
                //该类型物品是否已存在
                boolean flag = false;
                step:
                for (List<MdIncomeForecastAnalyseItem> list : allGoods) {
                    for (MdIncomeForecastAnalyseItem good : list) {
                        if (item.getForecastAnalyseId().equals(good.getForecastAnalyseId()) && item.getYear().equals(good.getYear()) && item.getAccountingSubject().equals(good.getAccountingSubject()) && item.getSourceType().equals(good.getSourceType())) {
                            list.add(item);
                            flag = true;
                            break step;
                        }
                    }
                }
                //不存在则造一个list存放
                if (!flag) {
                    List<MdIncomeForecastAnalyseItem> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                }
            }
        }
        //得到年份不同但会计科目相同的金额与数量
        List<MdIncomeForecastAnalyseItem> tempLists = Lists.newArrayList();
        for (List<MdIncomeForecastAnalyseItem> goods : allGoods) {
            BigDecimal sumMoney = new BigDecimal("0");
            Integer sumQuantity = 0;
            MdIncomeForecastAnalyseItem item = new MdIncomeForecastAnalyseItem();
            Integer year = null;
            Integer forecastAnalyseId2 = null;
            Integer accountingSubject = null;
            for (int i = 0; i < goods.size(); i++) {
                sumMoney = sumMoney.add(goods.get(i).getAmountMoney());
                sumQuantity += goods.get(i).getNumber();
                year = goods.get(i).getYear();
                forecastAnalyseId2 = goods.get(i).getForecastAnalyseId();
                accountingSubject = goods.get(i).getAccountingSubject();
            }
            item.setYear(year);
            item.setForecastAnalyseId(forecastAnalyseId2);
            item.setAccountingSubject(accountingSubject);
            item.setAmountMoney(sumMoney);
            item.setNumber(sumQuantity);
            tempLists.add(item);

        }
        Map<List<MdIncomeForecastAnalyseItem>, Integer> tempMaps = Maps.newHashMap();
        //所有一级编号类型
        List<Integer> AllAccountingSubjectTypes = LangUtils.transform(tempLists, o -> o.getAccountingSubject());
        //去重
        List<Integer> accountingSubjectTypes = generateCommonMethod.removeDuplicate(AllAccountingSubjectTypes);
        for (Integer accountingSubjectType : accountingSubjectTypes) {
            List<MdIncomeForecastAnalyseItem> FirstLevelAnalyseItemLists = Lists.newArrayList();
            for (MdIncomeForecastAnalyseItem item : tempLists) {
                if (accountingSubjectType.equals(item.getAccountingSubject())) {
                    FirstLevelAnalyseItemLists.add(item);
                }
            }
            tempMaps.put(FirstLevelAnalyseItemLists, accountingSubjectType);
        }

        //后续每年与前一条数据做比较
        List<MdIncomeForecastAnalyseItemVo> vos = Lists.newArrayList();
        for (Map.Entry<List<MdIncomeForecastAnalyseItem>, Integer> map : tempMaps.entrySet()) {
            List<MdIncomeForecastAnalyseItem> analyseItems = map.getKey();
            for (int i = 0; i < analyseItems.size(); i++) {
                MdIncomeForecastAnalyseItem item = analyseItems.get(i);
                MdIncomeForecastAnalyseItemVo mdIncomeForecastAnalyseItemVo = new MdIncomeForecastAnalyseItemVo();
                if (item.getForecastAnalyseId().equals(forecastAnalyseId)) {
                    BeanUtils.copyProperties(analyseItems.get(i), mdIncomeForecastAnalyseItemVo);
                    if (analyseItems.get(i).getAccountingSubject() != null && analyseItems.get(i).getAccountingSubject() > 0) {
                        String accountingSubjectName = baseDataDicService.getNameById(analyseItems.get(i).getAccountingSubject());
                        mdIncomeForecastAnalyseItemVo.setName(String.format("%s", accountingSubjectName));
                    }
                    if (i == 0) {
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
                if (i > 0) {
                    //金额趋势=现在金额：昨年金额
                    BigDecimal univalentTrend = analyseItems.get(i).getAmountMoney().divide(analyseItems.get(i - 1).getAmountMoney(), 2, BigDecimal.ROUND_HALF_UP);
                    //数量趋势=现在数量：昨年数量
                    BigDecimal quantitativeTrend = null;
                    if (analyseItems.get(i).getNumber() != null && 0 != analyseItems.get(i).getNumber() && analyseItems.get(i - 1).getNumber() != null && 0 != analyseItems.get(i - 1).getNumber()) {
                        quantitativeTrend = new BigDecimal(analyseItems.get(i).getNumber()).divide(new BigDecimal(analyseItems.get(i - 1).getNumber()), 2, BigDecimal.ROUND_HALF_UP);
                    }
                    if (item.getForecastAnalyseId().equals(forecastAnalyseId)) {
                        mdIncomeForecastAnalyseItemVo.setMoneyTrend(univalentTrend.toString());
                        if (quantitativeTrend != null) {
                            mdIncomeForecastAnalyseItemVo.setQuantitativeTrend(quantitativeTrend.toString());
                            //单价趋势：金额趋势：数量趋势
                            mdIncomeForecastAnalyseItemVo.setUnitPriceTrend(univalentTrend.divide(quantitativeTrend, 2, BigDecimal.ROUND_HALF_UP).toString());
                        }
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
            }
        }
        this.orderVos(vos);
        return vos;
    }

    //根据会计科目/一级编号/二级编号排序
    public void orderVos(List<MdIncomeForecastAnalyseItemVo> vos) {
        Comparator<MdIncomeForecastAnalyseItemVo> comparator1 = Comparator.comparing(MdIncomeForecastAnalyseItemVo::getAccountingSubject);
        Comparator<MdIncomeForecastAnalyseItemVo> comparator2 = new Comparator<MdIncomeForecastAnalyseItemVo>() {
            @Override
            public int compare(MdIncomeForecastAnalyseItemVo o1, MdIncomeForecastAnalyseItemVo o2) {
                //获取中文环境
                Comparator<Object> com = Collator.getInstance(Locale.CHINA);
                return com.compare(o1.getFirstLevelNumber(), o2.getFirstLevelNumber());
            }
        };
        Comparator<MdIncomeForecastAnalyseItemVo> comparator3 = new Comparator<MdIncomeForecastAnalyseItemVo>() {
            @Override
            public int compare(MdIncomeForecastAnalyseItemVo o1, MdIncomeForecastAnalyseItemVo o2) {
                //获取中文环境
                Comparator<Object> com = Collator.getInstance(Locale.CHINA);
                return com.compare(o1.getSecondLevelNumber(), o2.getSecondLevelNumber());
            }
        };
        Collections.sort(vos, comparator1.thenComparing(comparator2.thenComparing(comparator3)));
    }

    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getForecastAnalyseList(Integer incomeId, Integer type, Integer formType, Boolean bisParticipateIn) {
        BootstrapTableVo vo = new BootstrapTableVo();
        MdIncomeForecastAnalyse where = new MdIncomeForecastAnalyse();
        where.setType(type);
        where.setFormType(formType);
        where.setIncomeId(incomeId);
        if (bisParticipateIn != null)
            where.setBisParticipateIn(bisParticipateIn);
        List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(where);
        vo.setRows(CollectionUtils.isEmpty(analyseList) ? new ArrayList<MdIncomeForecastAnalyse>() : analyseList);
        vo.setTotal((long) analyseList.size());
        return vo;
    }

    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public List<Integer> getAnalyseCountByYear(Integer incomeId, Integer type, Integer formType) {
        MdIncomeForecastAnalyse where = new MdIncomeForecastAnalyse();
        where.setType(type);
        where.setFormType(formType);
        where.setIncomeId(incomeId);
        List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(where);
        List<Integer> years = LangUtils.transform(analyseList, o -> o.getYear());
        //去重
        List<Integer> listTemp = Lists.newArrayList();
        for (int i = 0; i < years.size(); i++) {
            if (!listTemp.contains(years.get(i))) {
                listTemp.add(years.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 获取分析数据明细列表
     *
     * @param forecastAnalyseId
     * @return
     */
    public BootstrapTableVo getForecastAnalyseItemList(Integer forecastAnalyseId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        MdIncomeForecastAnalyseItem where = new MdIncomeForecastAnalyseItem();
        where.setForecastAnalyseId(forecastAnalyseId);
        List<MdIncomeForecastAnalyseItem> list = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(where);
        List<MdIncomeForecastAnalyseItemVo> vos = Lists.newArrayList();
        //如果只填主营业务收入，则只以主营业务分析，如果细填到二级编号则以二级编号分析，则不显示一级编号与主营业务收入的分析
        //以第一条数据为例
        MdIncomeForecastAnalyseItem example = list.get(0);
//        if (StringUtils.isNotEmpty(example.getFirstLevelNumber()) && StringUtils.isNotEmpty(example.getSecondLevelNumber())) {
//            vos = getSecondLevelTrend(forecastAnalyseId);
//        } else if (StringUtils.isNotEmpty(example.getFirstLevelNumber()) && StringUtils.isEmpty(example.getSecondLevelNumber())) {
//            vos = getStairTrend(forecastAnalyseId);
//        } else {
//            vos = getAccountingSubjectTrend(forecastAnalyseId);
//        }
        vos = getSecondLevelTrend(forecastAnalyseId);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdIncomeForecastAnalyseItemVo>() : vos);
        vo.setTotal((long) list.size());
        return vo;
    }

//    public MdIncomeForecastAnalyseItemVo getVoBySecondLevelTrend(MdIncomeForecastAnalyseItem forecastAnalyseItem) {
//        if (forecastAnalyseItem == null) return null;
//        MdIncomeForecastAnalyseItemVo forecastAnalyseItemVo = new MdIncomeForecastAnalyseItemVo();
//        BeanUtils.copyProperties(forecastAnalyseItem, forecastAnalyseItemVo);
//        if (forecastAnalyseItem.getAccountingSubject() != null && forecastAnalyseItem.getAccountingSubject() > 0) {
//            String accountingSubjectName = baseDataDicService.getNameById(forecastAnalyseItem.getAccountingSubject());
//            forecastAnalyseItemVo.setName(String.format("%s/%s/%s", accountingSubjectName, forecastAnalyseItem.getFirstLevelNumber(), forecastAnalyseItem.getSecondLevelNumber()));
//        }
//        //写入数量趋势和单价趋势
//        forecastAnalyseItemVo = getSecondLevelTrend(forecastAnalyseItemVo);
//        return forecastAnalyseItemVo;
//    }

    /**
     * 导入历史数据
     *
     * @param history
     * @param multipartFile
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    public String importHistory(MdIncomeHistory history, MultipartFile multipartFile) throws Exception {
        if (history == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook hssfWorkbook = WorkbookFactory.create(inputStream);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int startRowNumber = 1;//读取数据的起始行
        int rowCount = sheet.getLastRowNum() + 1 - startRowNumber;//数据总行数
        StringBuilder errorMsg = new StringBuilder();
        int successCount = 0;//导入成功数据条数
        String key = history.getType().equals(0) ? AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_INCOME : AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_COST;
        List<BaseDataDic> subjectList = baseDataDicService.getCacheDataDicList(key);
        switch (MethodIncomeFormTypeEnum.create(history.getFormType())) {
            case DEFUALT:
                for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
                    try {
                        Row row = sheet.getRow(i);
                        MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
                        mdIncomeHistory.setIncomeId(history.getIncomeId());
                        mdIncomeHistory.setType(history.getType());
                        mdIncomeHistory.setFormType(history.getFormType());
                        mdIncomeHistory.setCreator(commonService.thisUserAccount());
                        String typeCellValue = PoiUtils.getCellValue(row.getCell(0));
                        if (StringUtils.equals(typeCellValue, "历史") || StringUtils.equals(typeCellValue, "调查")) {
                            mdIncomeHistory.setSourceType(typeCellValue);
                        } else {
                            errorMsg.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i + 1));
                            continue;
                        }
                        BaseDataDic subjectDic = baseDataDicService.getDataDicByName(subjectList, PoiUtils.getCellValue(row.getCell(1)));
                        if (subjectDic == null) {
                            errorMsg.append(String.format("\n第%s行异常：会计科目与系统配置的名称不一致", i + 1));
                            continue;
                        }
                        mdIncomeHistory.setAccountingSubject(subjectDic.getId());
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(2)))) {
                            mdIncomeHistory.setYear(Integer.valueOf(PoiUtils.getCellValue(row.getCell(2))));
                        }
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(3)))) {
                            mdIncomeHistory.setMonth(Integer.valueOf(PoiUtils.getCellValue(row.getCell(3))));
                        }
                        mdIncomeHistory.setFirstLevelNumber(PoiUtils.getCellValue(row.getCell(4)));
                        mdIncomeHistory.setSecondLevelNumber(PoiUtils.getCellValue(row.getCell(5)));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(6)))) {
                            mdIncomeHistory.setUnit(PoiUtils.getCellValue(row.getCell(6)));
                        }
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(7)))) {
                            mdIncomeHistory.setUnitPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(7))));
                        }
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(8)))) {
                            mdIncomeHistory.setNumber(Integer.valueOf(PoiUtils.getCellValue(row.getCell(8))));
                        }
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(9)))) {
                            mdIncomeHistory.setAmountMoney(new BigDecimal(PoiUtils.getCellValue(row.getCell(9))));
                        }
                        mdIncomeHistory.setDeprecitionRoyalty(PoiUtils.getCellValue(row.getCell(10)));
                        mdIncomeHistory.setBisForecast(false);
                        mdIncomeHistoryDao.addHistory(mdIncomeHistory);
                        generatorForecastAnalyse(mdIncomeHistory);
                        successCount++;
                    } catch (Exception e) {
                        errorMsg.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
                    }
                }
                break;
            case RESTAURANT:
                for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
                    try {
                        Row row = sheet.getRow(i);
                        MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
                        mdIncomeHistory.setIncomeId(history.getIncomeId());
                        mdIncomeHistory.setType(history.getType());
                        mdIncomeHistory.setFormType(history.getFormType());
                        mdIncomeHistory.setCreator(commonService.thisUserAccount());
                        String typeCellValue = PoiUtils.getCellValue(row.getCell(0));
                        if (StringUtils.equals(typeCellValue, "历史") || StringUtils.equals(typeCellValue, "调查")) {
                            mdIncomeHistory.setSourceType(typeCellValue);
                        } else {
                            errorMsg.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i + 1));
                            continue;
                        }
                        BaseDataDic subjectDic = baseDataDicService.getDataDicByName(subjectList, PoiUtils.getCellValue(row.getCell(1)));
                        if (subjectDic == null) {
                            errorMsg.append(String.format("\n第%s行异常：会计科目与系统配置的名称不一致", i + 1));
                            continue;
                        }
                        mdIncomeHistory.setAccountingSubject(subjectDic.getId());
                        mdIncomeHistory.setFirstLevelNumber(PoiUtils.getCellValue(row.getCell(2)));
                        mdIncomeHistory.setSecondLevelNumber(PoiUtils.getCellValue(row.getCell(3)));
                        mdIncomeHistory.setBeginDate(DateUtils.convertDate(PoiUtils.getCellValue(row.getCell(4))));
                        mdIncomeHistory.setEndDate(DateUtils.convertDate(PoiUtils.getCellValue(row.getCell(5))));
                        mdIncomeHistory.setUnit(PoiUtils.getCellValue(row.getCell(6)));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(7)))) {
                            mdIncomeHistory.setNumber(Integer.valueOf(PoiUtils.getCellValue(row.getCell(7))));
                        }
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(8)))) {
                            mdIncomeHistory.setUtilizationRatio(new BigDecimal(PoiUtils.getCellValue(row.getCell(8))));
                        }
                        mdIncomeHistory.setUtilizationRatioExplain(PoiUtils.getCellValue(row.getCell(9)));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(10)))) {
                            mdIncomeHistory.setMakePrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(10))));
                        }
                        mdIncomeHistory.setMakePriceExplain(PoiUtils.getCellValue(row.getCell(11)));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(12)))) {
                            mdIncomeHistory.setDiscountRate(new BigDecimal(PoiUtils.getCellValue(row.getCell(12))));
                        }
                        mdIncomeHistory.setDiscountRateExplain(PoiUtils.getCellValue(row.getCell(13)));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(14)))) {
                            mdIncomeHistory.setUnitPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(14))));
                        }
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(15)))) {
                            mdIncomeHistory.setAmountMoney(new BigDecimal(PoiUtils.getCellValue(row.getCell(15))));
                        }
                        mdIncomeHistory.setDeprecitionRoyalty(PoiUtils.getCellValue(row.getCell(16)));
                        mdIncomeHistory.setBisForecast(false);
                        mdIncomeHistoryDao.addHistory(mdIncomeHistory);
                        generatorForecastAnalyse(mdIncomeHistory);
                        successCount++;
                    } catch (Exception e) {
                        errorMsg.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
                    }
                }
                break;
        }

        inputStream.close();
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowCount, successCount, rowCount - successCount, errorMsg.toString());
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeHistory
     * @return
     */
    public BootstrapTableVo getHistoryList(MdIncomeHistory mdIncomeHistory) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (mdIncomeHistory.getIncomeId() == null || mdIncomeHistory.getIncomeId() <= 0) {
            mdIncomeHistory.setIncomeId(0);
            mdIncomeHistory.setCreator(commonService.thisUserAccount());
        }
        List<MdIncomeHistory> historyList = mdIncomeHistoryDao.getHistoryList(mdIncomeHistory);
        List<MdIncomeHistoryVo> vos = LangUtils.transform(historyList, p -> getHistoryVo(p));
        //表单类型为餐饮、酒店、宾馆时排序
        if (mdIncomeHistory.getFormType() == 1) {
            Comparator<MdIncomeHistoryVo> comparator = new Comparator<MdIncomeHistoryVo>() {
                @Override
                public int compare(MdIncomeHistoryVo o1, MdIncomeHistoryVo o2) {
                    return o1.getBeginDate().compareTo(o2.getBeginDate());
                }
            };
            Collections.sort(vos, comparator);
        }
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdIncomeHistoryVo>() : vos);
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
        return mdIncomeHistoryVo;
    }

    public void addForecast(MdIncomeForecast mdIncomeForecast) {
        mdIncomeForecast.setCreator(commonService.thisUserAccount());
        mdIncomeForecastDao.addForecast(mdIncomeForecast);
    }


    public void updateForecast(MdIncomeForecast mdIncomeForecast) {
        mdIncomeForecastDao.updateForecast(mdIncomeForecast);
        MdIncomeDateSection dateSectionById = mdIncomeDateSectionDao.getDateSectionById(mdIncomeForecast.getSectionId());
        dateSectionById.setCostTotal(mdIncomeForecast.getInitialAmount());
        dateSectionById.setOperatingProfit(mdIncomeForecast.getOperatingProfit());
        mdIncomeDateSectionDao.updateDateSection(dateSectionById);
    }

    /**
     * 生成年度预测
     *
     * @param incomeForecastId
     * @return
     */
    @Transactional
    public void createForecastIncomeYear(Integer incomeForecastId) {
        MdIncomeForecast forecast = mdIncomeForecastDao.getForecastById(incomeForecastId);

        //先清除原数据
        mdIncomeForecastYearDao.deleteByForecastId(incomeForecastId);

        //生成预测数据的年数据
        MdIncomeDateSection incomeDateSection = mdIncomeDateSectionDao.getDateSectionById(forecast.getSectionId());
        //开始时间
        Date beginDate = incomeDateSection.getBeginDate();
        //结束时间
        Date endDate = incomeDateSection.getEndDate();
        //开始年最后一天
        Date lastDayOfBeginYear = getLastDayOfYear(DateUtils.getYear(beginDate));
        //结束年第一天
        Date firstDayOfEndYear = getFirstDayOfYear(DateUtils.getYear(endDate));
        //中间时段第一天
        Date firstDayOfTemp = getFirstDayOfYear(DateUtils.getYear(beginDate) + 1);

        //明细
        List<MdIncomeForecastItem> mdIncomeForecastItems = getIncomeForecastItemListByMasterId(forecast.getId());

        //计算增长率 = 盈利:本金
        //本金
        BigDecimal principal = new BigDecimal("0");
        for (MdIncomeForecastItem item : mdIncomeForecastItems) {
            principal = principal.add(item.getAmountMoney());
        }
        //盈利
        BigDecimal profit = new BigDecimal("0");
        for (MdIncomeForecastItem item : mdIncomeForecastItems) {
            profit = profit.add(item.getAmountMoney().multiply(item.getRateIncrease()));
        }
        //增长率
        BigDecimal growthRate = profit.divide(principal, 4, BigDecimal.ROUND_HALF_UP);
        incomeDateSection.setRentalGrowthRate(growthRate);

        //开始年最后一天在结束时间之后
        if (DateUtils.compareDate(lastDayOfBeginYear, endDate) >= 0) {
            BigDecimal yearCount = publicService.diffDateYear(endDate, beginDate);
            MdIncomeForecastYear mdIncomeForecastYear = new MdIncomeForecastYear();
            mdIncomeForecastYear.setForecastId(forecast.getId());
            mdIncomeForecastYear.setIncomeId(forecast.getIncomeId());
            mdIncomeForecastYear.setType(forecast.getType());
            mdIncomeForecastYear.setBeginDate(beginDate);
            mdIncomeForecastYear.setEndDate(endDate);

            BigDecimal totalAmount = new BigDecimal("0");//总金额
            for (MdIncomeForecastItem item : mdIncomeForecastItems) {
                totalAmount = totalAmount.add(item.getAmountMoney().multiply(new BigDecimal("1").add(item.getRateIncrease())).multiply(yearCount));
            }
            mdIncomeForecastYear.setAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
            mdIncomeForecastYear.setCreator(commonService.thisUserAccount());
            mdIncomeForecastYearDao.addForecastYear(mdIncomeForecastYear);
        } else {
            //第一年和最后一年单独处理
            int beginTempYear = DateUtils.getYear(firstDayOfTemp);
            int endYear = DateUtils.getYear(endDate);
            //除去第一年与最后一年的循环次数
            int calcNum = endYear - beginTempYear;

            for (int i = 1; i <= calcNum + 2; i++) {
                MdIncomeForecastYear mdIncomeForecastYear = new MdIncomeForecastYear();
                mdIncomeForecastYear.setForecastId(forecast.getId());
                mdIncomeForecastYear.setIncomeId(forecast.getIncomeId());
                mdIncomeForecastYear.setType(forecast.getType());
                if (i == 1) {
                    //第一年金额
                    BigDecimal beginYearCount = publicService.diffDateYear(lastDayOfBeginYear, beginDate);
                    BigDecimal firstYearAmount = new BigDecimal("0");
                    firstYearAmount = principal.multiply(new BigDecimal("1").add(growthRate)).multiply(beginYearCount);
                    mdIncomeForecastYear.setAmount(firstYearAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
                    mdIncomeForecastYear.setBeginDate(beginDate);
                    mdIncomeForecastYear.setEndDate(lastDayOfBeginYear);
                } else if (i == calcNum + 2) {
                    //最后一年金额
                    BigDecimal LastYearCount = publicService.diffDateYear(endDate, firstDayOfEndYear);
                    BigDecimal LastAmount = new BigDecimal("0");
                    LastAmount = principal.multiply(new BigDecimal("1").add(growthRate).pow(calcNum + 2)).multiply(LastYearCount);
                    mdIncomeForecastYear.setAmount(LastAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
                    mdIncomeForecastYear.setBeginDate(firstDayOfEndYear);
                    mdIncomeForecastYear.setEndDate(endDate);
                } else {
                    BigDecimal tempAmount = new BigDecimal("0");//总金额
                    tempAmount = principal.multiply(new BigDecimal("1").add(growthRate).pow(i));
                    mdIncomeForecastYear.setAmount(tempAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
                    mdIncomeForecastYear.setBeginDate(DateUtils.addYear(firstDayOfTemp, i - 2));
                    Date lastDayOfBeginTempYear = getLastDayOfYear(beginTempYear);
                    mdIncomeForecastYear.setEndDate(DateUtils.addYear(lastDayOfBeginTempYear, i - 2));
                }

                mdIncomeForecastYear.setCreator(commonService.thisUserAccount());
                mdIncomeForecastYearDao.addForecastYear(mdIncomeForecastYear);
            }
        }


        //更新总收入
        if (forecast.getType().equals(MethodDataTypeEnum.INCOME.getId())) {
            List<MdIncomeForecastYear> list = getIncomeForecastYearListByMasterId(incomeForecastId);
            BigDecimal totalAmount = new BigDecimal("0");//总金额
//            for (MdIncomeForecastYear yearItem : list) {
//                totalAmount = totalAmount.add(yearItem.getAmount());
//            }
            totalAmount = totalAmount.add(list.get(0).getAmount());
            incomeDateSection.setIncomeTotal(totalAmount);
        }

        mdIncomeDateSectionDao.updateDateSection(incomeDateSection);
        //更新成本中的经营成本明细
        List<MdIncomeForecastItemVo> operatingCostItems = Lists.newArrayList();
        for (MdIncomeForecastItem item : mdIncomeForecastItems) {
            MdIncomeForecastItemVo vo = new MdIncomeForecastItemVo();
            BeanUtils.copyProperties(item, vo);
            vo.setTotal(principal);
            if (item.getAccountingSubject() != null && item.getAccountingSubject() > 0) {
                String accountingSubjectName = baseDataDicService.getNameById(item.getAccountingSubject());
                vo.setName(String.format("%s/%s/%s", accountingSubjectName, item.getFirstLevelNumber(), item.getSecondLevelNumber()));
            }
            operatingCostItems.add(vo);
        }
        String jsonString = JSONObject.toJSONString(operatingCostItems);
        MdIncomeForecast mdIncomeForecast = new MdIncomeForecast();
        mdIncomeForecast.setIncomeId(forecast.getIncomeId());
        mdIncomeForecast.setSectionId(forecast.getSectionId());
        mdIncomeForecast.setType(MethodDataTypeEnum.COST.getId());
        MdIncomeForecast forecastCost = mdIncomeForecastDao.getForecast(mdIncomeForecast);
        forecastCost.setOperatingCostItem(jsonString);
        mdIncomeForecastDao.updateForecast(forecastCost);
    }

    /**
     * 获取指定年份的最后一天
     *
     * @param
     * @return
     */
    public Date getLastDayOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //获取最大月
        int maxMonth = cal.getActualMaximum(Calendar.MONTH);
        //设置月份
        cal.set(Calendar.MONTH, maxMonth);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, lastDay);
        return cal.getTime();
    }

    /**
     * 获取指定年份的第一天
     *
     * @param
     * @return
     */
    public Date getFirstDayOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //获取最大月
        int maxMonth = cal.getActualMinimum(Calendar.MONTH);
        //设置月份
        cal.set(Calendar.MONTH, maxMonth);
        //获取某月最大天数
        int lastDay = cal.getActualMinimum(Calendar.DATE);
        cal.set(Calendar.DATE, lastDay);
        return cal.getTime();
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

    public boolean deleteForecastBySectionId(Integer sectionId) {
        return mdIncomeForecastDao.deleteForecastBySectionId(sectionId);
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
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdIncomeForecastVo>() : vos);
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
            mdIncomeForecastVo.setIncomeTotal(dateSection.getIncomeTotal());
            mdIncomeForecastVo.setRateIncrease(dateSection.getRentalGrowthRate());
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
     * 获取预测年度数据列表
     *
     * @param forecastId
     * @return
     */
    public BootstrapTableVo getForecastYearList(Integer forecastId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeForecastYear where = new MdIncomeForecastYear();
        where.setForecastId(forecastId);
        List<MdIncomeForecastYear> forecastYearList = mdIncomeForecastYearDao.getForecastYearList(where);
        vo.setRows(CollectionUtils.isEmpty(forecastYearList) ? new ArrayList<MdIncomeForecastYear>() : forecastYearList);
        vo.setTotal(page.getTotal());
        return vo;
    }


    /**
     * 保存数据
     *
     * @param mdIncomeForecastMonth
     */
    public void saveForecastMonth(MdIncomeForecastMonth mdIncomeForecastMonth) {
        if (mdIncomeForecastMonth.getId() != null && mdIncomeForecastMonth.getId() > 0) {
            mdIncomeForecastMonthDao.updateForecastMonth(mdIncomeForecastMonth);
        } else {
            mdIncomeForecastMonth.setCreator(commonService.thisUserAccount());
            mdIncomeForecastMonthDao.addForecastMonth(mdIncomeForecastMonth);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteForecastMonth(Integer id) {
        return mdIncomeForecastMonthDao.deleteForecastMonth(id);
    }

    /**
     * 获取数据列表
     *
     * @param yearId
     * @return
     */
    public BootstrapTableVo getForecastMonthList(Integer yearId, Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeForecastMonth where = new MdIncomeForecastMonth();
        where.setType(type);
        where.setYearId(yearId);

        List<MdIncomeForecastMonth> historyList = mdIncomeForecastMonthDao.getForecastMonthList(where);
        List<MdIncomeForecastMonthVo> vos = LangUtils.transform(historyList, p -> getForecastMonthVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdIncomeForecastMonthVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeForecastMonthVo getForecastMonthVo(MdIncomeForecastMonth mdIncomeForecastMonth) {
        if (mdIncomeForecastMonth == null) return null;
        MdIncomeForecastMonthVo mdIncomeForecastMonthVo = new MdIncomeForecastMonthVo();
        BeanUtils.copyProperties(mdIncomeForecastMonth, mdIncomeForecastMonthVo);
        if (mdIncomeForecastMonth.getAccountingSubject() != null && mdIncomeForecastMonth.getAccountingSubject() > 0) {
            mdIncomeForecastMonthVo.setAccountingSubjectName(baseDataDicService.getNameById(mdIncomeForecastMonth.getAccountingSubject()));
        }
        return mdIncomeForecastMonthVo;
    }


    /**
     租赁-----------
     */

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
        List<MdIncomeLeaseVo> leaseVoList = LangUtils.transform(leaseList, o -> getMdIncomeLeaseVo(o));
        vo.setRows(CollectionUtils.isEmpty(leaseVoList) ? new ArrayList<MdIncomeLeaseVo>() : leaseVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeLeaseVo getMdIncomeLeaseVo(MdIncomeLease mdIncomeLease) {
        if (mdIncomeLease == null) return null;
        MdIncomeLeaseVo mdIncomeLeaseVo = new MdIncomeLeaseVo();
        BeanUtils.copyProperties(mdIncomeLease, mdIncomeLeaseVo);
        MdIncomeDateSection dateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeLease.getSectionId());
        if (dateSection != null) {
            mdIncomeLeaseVo.setBeginDate(dateSection.getBeginDate());
            mdIncomeLeaseVo.setEndDate(dateSection.getEndDate());
            mdIncomeLeaseVo.setYearCount(dateSection.getYearCount());
        }
        return mdIncomeLeaseVo;
    }

    //新增租赁收入
    public void addLease(MdIncomeLease mdIncomeLease) {
        mdIncomeLeaseDao.addIncomeLease(mdIncomeLease);
    }

    //更新租赁收入
    @Transactional
    public void updateLease(MdIncomeLease mdIncomeLease) throws BusinessException {
        mdIncomeLeaseDao.updateIncomeLease(mdIncomeLease);

        //计算总收入 月租金*出租率*月份数+其它收入
        BigDecimal total = mdIncomeLease.getRentalIncome().multiply(mdIncomeLease.getRentals())
                .multiply(new BigDecimal(mdIncomeLease.getMonthNumber())).multiply(new BigDecimal(mdIncomeLease.getAdditionalCapture()));
        total = total.add(mdIncomeLease.getOtherIncome());
        MdIncomeDateSection incomeDateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeLease.getSectionId());
        if (incomeDateSection != null) {
            incomeDateSection.setIncomeTotal(total);
            mdIncomeDateSectionDao.updateDateSection(incomeDateSection);

            //同步更新成本
            MdIncomeLeaseCost where = new MdIncomeLeaseCost();
            where.setSectionId(incomeDateSection.getId());
            MdIncomeLeaseCost leaseCost = mdIncomeLeaseCostDao.getLeaseCost(where);
            if (leaseCost != null)
                updateLeaseCost(leaseCost);
        }
    }

    public void deleteLeaseBySectionId(Integer sectionId) {
        mdIncomeLeaseDao.deleteLeaseBySectionId(sectionId);
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

    //新增租赁成本
    public void addLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) {
        mdIncomeLeaseCostDao.addLeaseCost(mdIncomeLeaseCost);
    }

    /**
     * 更新租赁成本数据
     *
     * @param mdIncomeLeaseCost
     */
    public void updateLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) throws BusinessException {
        mdIncomeLeaseCostDao.updateLeaseCost(mdIncomeLeaseCost);
        //计算总成本 毛收入*管理费率+重置价格*维护保养费率+毛收入*租赁税费率+毛收入*保险费率+土地使用税
        MdIncomeDateSection incomeDateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeLeaseCost.getSectionId());
        if (incomeDateSection != null) {
            BigDecimal incomeTotal = incomeDateSection.getIncomeTotal();//毛收入
            if (incomeTotal == null) {
                throw new BusinessException("请先保存毛收入信息");
            }
            BigDecimal total = new BigDecimal("0");
            if (mdIncomeLeaseCost.getManagementCostRatio() != null)
                total = total.add(incomeTotal.multiply(mdIncomeLeaseCost.getManagementCostRatio()));//管理费
            if (mdIncomeLeaseCost.getReplacementValue() != null && mdIncomeLeaseCost.getMaintenanceCostRatio() != null)
                total = total.add(mdIncomeLeaseCost.getReplacementValue().multiply(mdIncomeLeaseCost.getMaintenanceCostRatio()));//维护保养费
            if (mdIncomeLeaseCost.getAdditionalRatio() != null)
                total = total.add(incomeTotal.multiply(mdIncomeLeaseCost.getAdditionalRatio()));//房产税、增值税及附加
            if (mdIncomeLeaseCost.getReplacementValue() != null && mdIncomeLeaseCost.getInsurancePremiumRatio() != null)
                total = total.add(mdIncomeLeaseCost.getReplacementValue().multiply(mdIncomeLeaseCost.getInsurancePremiumRatio()));//保险费
            if (mdIncomeLeaseCost.getLandUseTax() != null)
                total = total.add(mdIncomeLeaseCost.getLandUseTax());//土地使用费
            if (mdIncomeLeaseCost.getTransactionTaxeFeeRatio() != null) {
                MdIncomeLease mdIncomeLease = mdIncomeLeaseDao.getIncomeLeaseBySectionId(mdIncomeLeaseCost.getSectionId());
                total = total.add(mdIncomeLease.getRentalIncome().multiply(new BigDecimal(mdIncomeLease.getMonthNumber()))
                        .multiply(mdIncomeLeaseCost.getTransactionTaxeFeeRatio()));//其它相关税费
            }
            incomeDateSection.setCostTotal(total);
            mdIncomeDateSectionDao.updateDateSection(incomeDateSection);
        }
    }

    public void deleteLeaseCostBySectionId(Integer sectionId) {
        mdIncomeLeaseCostDao.deleteLeaseCostBySectionId(sectionId);
    }

    /**
     * 获取数据列表
     *
     * @param setIncomeId
     * @return
     */
    public BootstrapTableVo getLeaseCostList(Integer setIncomeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeLeaseCost where = new MdIncomeLeaseCost();
        if (setIncomeId != null && setIncomeId > 0) {
            where.setIncomeId(setIncomeId);
        } else {
            where.setIncomeId(0);
            where.setCreator(commonService.thisUserAccount());
        }
        List<MdIncomeLeaseCost> leaseList = mdIncomeLeaseCostDao.getLeaseCostList(where);
        List<MdIncomeLeaseCostVo> leaseCostVoList = LangUtils.transform(leaseList, o -> getMdIncomeLeaseCostVo(o));
        vo.setRows(CollectionUtils.isEmpty(leaseCostVoList) ? new ArrayList<MdIncomeLeaseCostVo>() : leaseCostVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public MdIncomeLeaseCostVo getMdIncomeLeaseCostVo(MdIncomeLeaseCost mdIncomeLeaseCost) {
        if (mdIncomeLeaseCost == null) return null;
        MdIncomeLeaseCostVo mdIncomeLeaseCostVo = new MdIncomeLeaseCostVo();
        BeanUtils.copyProperties(mdIncomeLeaseCost, mdIncomeLeaseCostVo);
        MdIncomeDateSection dateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeLeaseCost.getSectionId());
        if (dateSection != null) {
            mdIncomeLeaseCostVo.setBeginDate(dateSection.getBeginDate());
            mdIncomeLeaseCostVo.setEndDate(dateSection.getEndDate());
            mdIncomeLeaseCostVo.setYearCount(dateSection.getYearCount());
        }
        return mdIncomeLeaseCostVo;
    }

    //成本粘贴
    public void pasteLeaseCost(Integer sourceId, Integer targetId) {
        MdIncomeLeaseCost sourceLeaseCost = mdIncomeLeaseCostDao.getLeaseCostById(sourceId);
        MdIncomeLeaseCost targetLeaseCost = mdIncomeLeaseCostDao.getLeaseCostById(targetId);
        BeanUtils.copyProperties(sourceLeaseCost, targetLeaseCost, "id", "sectionId", "incomeId", "sorting");
        mdIncomeLeaseCostDao.updateLeaseCost(targetLeaseCost);
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
        }
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
        saveIncome(mdIncome);
        List<MdIncomeDateSection> dateSectionList = mdIncomeResultDto.getDateSectionList();
        if (CollectionUtils.isNotEmpty(dateSectionList)) {
            for (MdIncomeDateSection mdIncomeDateSection : dateSectionList) {
                mdIncomeDateSectionDao.updateDateSection(mdIncomeDateSection);
            }
        }
        //更新从表数据
        MdIncomeHistory where = new MdIncomeHistory();
        where.setIncomeId(0);
        where.setCreator(commonService.thisUserAccount());
        MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
        mdIncomeHistory.setIncomeId(mdIncome.getId());
        mdIncomeHistoryDao.updateHistory(where, mdIncomeHistory);

        MdIncomeDateSection whereDateSection = new MdIncomeDateSection();
        whereDateSection.setIncomeId(0);
        whereDateSection.setCreator(commonService.thisUserAccount());
        MdIncomeDateSection mdIncomeDateSection = new MdIncomeDateSection();
        mdIncomeDateSection.setIncomeId(mdIncome.getId());
        mdIncomeDateSectionDao.updateDateSection(whereDateSection, mdIncomeDateSection);

        MdIncomeForecast whereForecast = new MdIncomeForecast();
        whereForecast.setIncomeId(0);
        whereForecast.setCreator(commonService.thisUserAccount());
        MdIncomeForecast mdIncomeForecast = new MdIncomeForecast();
        mdIncomeForecast.setIncomeId(mdIncome.getId());
        mdIncomeForecastDao.updateForecast(whereForecast, mdIncomeForecast);

        MdIncomeLease whereLease = new MdIncomeLease();
        whereLease.setIncomeId(0);
        whereLease.setCreator(commonService.thisUserAccount());
        MdIncomeLease mdIncomeLease = new MdIncomeLease();
        mdIncomeLease.setIncomeId(mdIncome.getId());
        mdIncomeLeaseDao.updateIncomeLease(whereLease, mdIncomeLease);

        MdIncomeLeaseCost whereLeaseCost = new MdIncomeLeaseCost();
        whereLeaseCost.setIncomeId(0);
        whereLeaseCost.setCreator(commonService.thisUserAccount());
        MdIncomeLeaseCost mdIncomeLeaseCost = new MdIncomeLeaseCost();
        mdIncomeLeaseCost.setIncomeId(mdIncome.getId());
        mdIncomeLeaseCostDao.updateIncomeLeaseCost(whereLeaseCost, mdIncomeLeaseCost);
        return mdIncome;
    }


    /**
     * 获取土地剩余使用年限
     *
     * @param landUseEndDate
     * @param valueTimePoint
     * @return
     */
    public BigDecimal getLandSurplusYear(Date landUseEndDate, Date valueTimePoint) {
        if (landUseEndDate == null || valueTimePoint == null) return null;
        return publicService.diffDateYear(landUseEndDate, valueTimePoint);
    }

    /**
     * 获取房产剩余使用年限
     *
     * @param completedTime
     * @param valueTimePoint
     * @param canUseYear
     * @return
     */
    public BigDecimal getHouseSurplusYear(Date completedTime, Date valueTimePoint, Integer canUseYear) {
        if (completedTime == null || valueTimePoint == null || canUseYear == null) return null;
        BigDecimal houseSurplusYear = new BigDecimal(canUseYear).subtract(publicService.diffDateYear(valueTimePoint, completedTime));
        return houseSurplusYear;
    }


    /**
     * 获取有效毛收入数据列表
     *
     * @param incomeForecastId
     * @return
     */
    public BootstrapTableVo loadIncomeForecastItemList(Integer incomeForecastId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        MdIncomeForecastItem where = new MdIncomeForecastItem();
        where.setIncomeForecastId(incomeForecastId);
        List<MdIncomeForecastItem> list = mdIncomeForecastItemDao.getIncomeForecastItemList(where);
        List<MdIncomeForecastItemVo> vos = LangUtils.transform(list, o -> getMdIncomeForecastItemVo(o));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdIncomeForecastItemVo>() : vos);
        vo.setTotal((long) list.size());
        return vo;
    }


    /**
     * 保存有效毛收入数据
     *
     * @param mdIncomeForecastItem
     */
    public void saveIncomeForecastItem(MdIncomeForecastItem mdIncomeForecastItem) {
        if (mdIncomeForecastItem.getId() != null && mdIncomeForecastItem.getId() > 0) {
            mdIncomeForecastItemDao.updateIncomeForecastItem(mdIncomeForecastItem);
        } else {
            mdIncomeForecastItem.setCreator(commonService.thisUserAccount());
            mdIncomeForecastItemDao.addIncomeForecastItem(mdIncomeForecastItem);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteForecastIncomeItem(Integer id) {
        return mdIncomeForecastItemDao.deleteIncomeForecastItem(id);
    }


    public MdIncomeForecastItemVo getMdIncomeForecastItemVo(MdIncomeForecastItem mdIncomeForecastItem) {
        if (mdIncomeForecastItem == null) return null;
        MdIncomeForecastItemVo mdIncomeForecastItemVo = new MdIncomeForecastItemVo();
        BeanUtils.copyProperties(mdIncomeForecastItem, mdIncomeForecastItemVo);
        StringBuilder name = new StringBuilder();
        if (mdIncomeForecastItem.getAccountingSubject() != null) {
            name.append(baseDataDicService.getNameById(mdIncomeForecastItem.getAccountingSubject()));
        }
        if (StringUtils.isNotEmpty(mdIncomeForecastItem.getFirstLevelNumber())) {
            name.append("/").append(mdIncomeForecastItem.getFirstLevelNumber());
        }
        if (StringUtils.isNotEmpty(mdIncomeForecastItem.getSecondLevelNumber())) {
            name.append("/").append(mdIncomeForecastItem.getSecondLevelNumber());
        }
        mdIncomeForecastItemVo.setName(name.toString());
        return mdIncomeForecastItemVo;
    }

    /**
     * 引用数据
     *
     * @param incomeId
     * @param formType
     */
    public void forecastIncomeItemQuoteData(Integer incomeId, Integer formType, Integer incomeForecastId) {
        //删除老数据
        List<MdIncomeForecastItem> oldData = getIncomeForecastItemListByMasterId(incomeForecastId);
        if (CollectionUtils.isNotEmpty(oldData)) {
            for (MdIncomeForecastItem item : oldData) {
                mdIncomeForecastItemDao.deleteIncomeForecastItem(item.getId());
            }
        }
        //拿到对应的id集合
        //判断当前id的索引位置
        //不为0则取上一个时间段收入预测的物品
        MdIncomeForecast mdIncomeForecast = new MdIncomeForecast();
        mdIncomeForecast.setIncomeId(incomeId);
        mdIncomeForecast.setType(MethodDataTypeEnum.INCOME.getId());
        List<MdIncomeForecast> forecastList = mdIncomeForecastDao.getForecastList(mdIncomeForecast);
        List<MdIncomeForecastVo> vos = LangUtils.transform(forecastList, p -> getForecastVo(p));
        int index = -1;
        //当前时间段开始年份
        int nowYear = 0;
        for (int i = 0; i < vos.size(); i++) {
            if (vos.get(i).getId().equals(incomeForecastId)) {
                index = i;
                nowYear = DateUtils.getYear(vos.get(i).getBeginDate());
                break;
            }
        }

        if (index < 0) return;
        if (index == 0) {
            MdIncomeForecastAnalyse forecastAnalyse = new MdIncomeForecastAnalyse();
            forecastAnalyse.setIncomeId(incomeId);
            forecastAnalyse.setType(0);
            forecastAnalyse.setFormType(formType);
            List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(forecastAnalyse);
            if (CollectionUtils.isNotEmpty(analyseList)) {
                Integer forecastAnalyseId = analyseList.get(analyseList.size() - 1).getId();
                List<MdIncomeForecastAnalyseItem> forecastAnalyseItemList = getForecastAnalyseItemListByMasterId(forecastAnalyseId);
                for (MdIncomeForecastAnalyseItem analyseItem : forecastAnalyseItemList) {
                    MdIncomeForecastItem incomeForecastItem = new MdIncomeForecastItem();
                    BeanUtils.copyProperties(analyseItem, incomeForecastItem);
                    incomeForecastItem.setIncomeForecastId(incomeForecastId);
                    incomeForecastItem.setCreator(commonService.thisUserAccount());
                    mdIncomeForecastItemDao.addIncomeForecastItem(incomeForecastItem);
                }
            }
        } else {
            //获取次方值：当前时间段开始年份-上一个时间段开始年份
            int initialYear = DateUtils.getYear(vos.get(index - 1).getBeginDate());
            int yearCount = nowYear - initialYear;
            //获取到上一个时间段收入预测的物品
            MdIncomeForecast forecast = forecastList.get(index - 1);
            List<MdIncomeForecastItem> frontData = getIncomeForecastItemListByMasterId(forecast.getId());
            if (CollectionUtils.isNotEmpty(frontData)) {
                for (MdIncomeForecastItem item : frontData) {
                    MdIncomeForecastItem incomeForecastItem = new MdIncomeForecastItem();
                    BeanUtils.copyProperties(item, incomeForecastItem);
                    incomeForecastItem.setRateIncreaseExplain(null);
                    incomeForecastItem.setRateIncrease(null);
                    incomeForecastItem.setIncomeForecastId(incomeForecastId);
                    incomeForecastItem.setCreator(commonService.thisUserAccount());
                    //计算物品价格
                    incomeForecastItem.setAmountMoney(item.getAmountMoney().multiply(new BigDecimal("1").add(item.getRateIncrease()).pow(yearCount)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    mdIncomeForecastItemDao.addIncomeForecastItem(incomeForecastItem);

                }
            }
        }
    }


    public List<MdIncomeForecastAnalyseItem> getForecastAnalyseItemListByMasterId(Integer forecastAnalyseId) {
        MdIncomeForecastAnalyseItem item = new MdIncomeForecastAnalyseItem();
        item.setForecastAnalyseId(forecastAnalyseId);
        List<MdIncomeForecastAnalyseItem> forecastAnalyseItemList = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(item);
        return forecastAnalyseItemList;
    }

    public List<MdIncomeForecastItem> getIncomeForecastItemListByMasterId(Integer forecastId) {
        MdIncomeForecastItem item = new MdIncomeForecastItem();
        item.setIncomeForecastId(forecastId);
        List<MdIncomeForecastItem> forecastItemList = mdIncomeForecastItemDao.getIncomeForecastItemList(item);
        return forecastItemList;
    }

    public List<MdIncomeForecastYear> getIncomeForecastYearListByMasterId(Integer forecastId) {
        MdIncomeForecastYear where = new MdIncomeForecastYear();
        where.setForecastId(forecastId);
        List<MdIncomeForecastYear> forecastYearList = mdIncomeForecastYearDao.getForecastYearList(where);
        return forecastYearList;
    }


    public void saveMdIncomePriceInvestigation(MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        if (mdIncomePriceInvestigation.getId() == null) {
            mdIncomePriceInvestigation.setCreator(commonService.thisUserAccount());
            mdIncomePriceInvestigationDao.addIncomePriceInvestigation(mdIncomePriceInvestigation);
        } else {
            mdIncomePriceInvestigationDao.updateIncomePriceInvestigation(mdIncomePriceInvestigation);
        }
    }

    public MdIncomePriceInvestigation getMdIncomePriceInvestigationById(Integer id) {
        return mdIncomePriceInvestigationDao.getIncomePriceInvestigationById(id);
    }

    public BootstrapTableVo getMdIncomePriceInvestigationList(Integer incomeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomePriceInvestigation mdIncomePriceInvestigation = new MdIncomePriceInvestigation();
        mdIncomePriceInvestigation.setIncomeId(incomeId);
        List<MdIncomePriceInvestigation> list = mdIncomePriceInvestigationDao.getIncomePriceInvestigationList(mdIncomePriceInvestigation);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<MdIncomePriceInvestigation>() : list);
        return vo;
    }

    public void removeMdIncomePriceInvestigation(Integer id) {
        mdIncomePriceInvestigationDao.deleteIncomePriceInvestigation(id);
    }

    /**
     * 获取同类物品历史数据列表
     *
     * @param
     * @return
     */
    public BootstrapTableVo getSameNameHistoryList(Integer historyId, Integer formType, Integer incomeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeForecastItem incomeForecastItem = mdIncomeForecastItemDao.getIncomeForecastItemById(historyId);
        MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
        mdIncomeHistory.setIncomeId(incomeId);
        mdIncomeHistory.setType(0);
        mdIncomeHistory.setFormType(formType);
        List<MdIncomeHistory> historyList = mdIncomeHistoryDao.getHistoryList(mdIncomeHistory);
        List<MdIncomeHistory> sameNameList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(historyList)) {
            for (MdIncomeHistory item : historyList) {
                if (Objects.equals(incomeForecastItem.getAccountingSubject(), item.getAccountingSubject()) && Objects.equals(incomeForecastItem.getFirstLevelNumber(), item.getFirstLevelNumber())
                        && Objects.equals(incomeForecastItem.getSecondLevelNumber(), item.getSecondLevelNumber())) {
                    sameNameList.add(item);
                }
            }
        }

        vo.setRows(CollectionUtils.isEmpty(sameNameList) ? new ArrayList<MdIncomeHistory>() : sameNameList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public void affirmQuoteMoney(List<Integer> ids, Integer historyId) {
        BigDecimal totalPrice = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Integer id : ids) {
                MdIncomeHistory history = mdIncomeHistoryDao.getHistoryById(id);
                totalPrice = totalPrice.add(history.getAmountMoney());
            }
            MdIncomeForecastItem incomeForecastItem = mdIncomeForecastItemDao.getIncomeForecastItemById(historyId);
            incomeForecastItem.setAmountMoney(totalPrice.divide(new BigDecimal(ids.size()), 2, BigDecimal.ROUND_HALF_UP));
            mdIncomeForecastItemDao.updateIncomeForecastItem(incomeForecastItem);
        }
    }

    //收益法自营经营情况
    public String getEngageSituation(Integer incomeId, Integer formType, Integer type, String fieldName) {
        BaseDataDic dic = baseDataDicService.getCacheDataDicByFieldName(fieldName);
        MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
        mdIncomeHistory.setIncomeId(incomeId);
        mdIncomeHistory.setFormType(formType);
        mdIncomeHistory.setAccountingSubject(dic.getId());
        mdIncomeHistory.setType(type);
        List<MdIncomeHistory> MdIncomeHistorys = mdIncomeHistoryDao.getHistoryList(mdIncomeHistory);
        if (CollectionUtils.isEmpty(MdIncomeHistorys)) return null;
        List<List<MdIncomeHistory>> allGoods = Lists.newArrayList();
        for (MdIncomeHistory item : MdIncomeHistorys) {
            if (StringUtils.isNotEmpty(item.getFirstLevelNumber())) {
                if (CollectionUtils.isEmpty(allGoods)) {
                    List<MdIncomeHistory> goods = Lists.newArrayList();
                    goods.add(item);
                    allGoods.add(goods);
                } else {
                    //该类型物品是否已存在
                    boolean flag = false;
                    step:
                    for (List<MdIncomeHistory> list : allGoods) {
                        for (MdIncomeHistory good : list) {
                            if (item.getFirstLevelNumber().equals(good.getFirstLevelNumber())) {
                                list.add(item);
                                flag = true;
                                break step;
                            }
                        }
                    }
                    //不存在则造一个list存放
                    if (!flag) {
                        List<MdIncomeHistory> goods = Lists.newArrayList();
                        goods.add(item);
                        allGoods.add(goods);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dic.getName()).append(":");
        for (List<MdIncomeHistory> sameFirstLevel : allGoods) {
            sb.append(sameFirstLevel.get(0).getFirstLevelNumber()).append("(");
            boolean flag = false;
            for (MdIncomeHistory item : sameFirstLevel) {
                if (StringUtils.isNotEmpty(item.getSecondLevelNumber()) && !sb.toString().contains
                        (item.getSecondLevelNumber())) {
                    sb.append(item.getSecondLevelNumber()).append("、");
                    flag = true;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            if (flag) {
                sb.append(")");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //复制收益法
    public void pasteMdIncome(Integer sourcePlanDetailsId, Integer targetPlanDetailsId) throws Exception {
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(targetPlanDetailsId);
        String processInsId = "";
        if (schemeInfo != null) {
            processInsId = schemeInfo.getProcessInsId();
            //先删除再添加
            this.deleteMdIncomeByPlanDetailsId(targetPlanDetailsId);
        }

        SchemeInfo sourceSchemeInfo = schemeInfoService.getSchemeInfo(sourcePlanDetailsId);
        //复制MdIncome数据
        MdIncome sourceIncome = getIncomeById(sourceSchemeInfo.getMethodDataId());
        MdIncome targetIncome = new MdIncome();
        BeanUtils.copyProperties(sourceIncome, targetIncome, "id");
        this.saveIncome(targetIncome);

        //主表复制
        SchemeInfo targetSchemeInfo = new SchemeInfo();
        BeanUtils.copyProperties(sourceSchemeInfo, targetSchemeInfo, "id");
        targetSchemeInfo.setMethodDataId(targetIncome.getId());
        targetSchemeInfo.setProcessInsId(processInsId);
        targetSchemeInfo.setPlanDetailsId(targetPlanDetailsId);
        schemeInfoService.saveSchemeInfo(targetSchemeInfo);

        //复制MdIncomeDateSection数据
        MdIncomeDateSection section = new MdIncomeDateSection();
        section.setIncomeId(sourceIncome.getId());
        List<MdIncomeDateSection> sourceSectionList = mdIncomeDateSectionDao.getDateSectionList(section);
        if (CollectionUtils.isNotEmpty(sourceSectionList)) {
            for (MdIncomeDateSection sourceIncomeDateSection : sourceSectionList) {
                MdIncomeDateSection targetIncomeDateSection = new MdIncomeDateSection();
                BeanUtils.copyProperties(sourceIncomeDateSection, targetIncomeDateSection, "id");
                targetIncomeDateSection.setIncomeId(targetIncome.getId());
                targetIncomeDateSection.setCreator(commonService.thisUserAccount());
                mdIncomeDateSectionDao.addDateSection(targetIncomeDateSection);

                //复制MdIncomeForecast
                MdIncomeForecast mdIncomeForecast = new MdIncomeForecast();
                mdIncomeForecast.setIncomeId(sourceIncome.getId());
                mdIncomeForecast.setSectionId(sourceIncomeDateSection.getId());
                List<MdIncomeForecast> sourceForecastList = mdIncomeForecastDao.getForecastList(mdIncomeForecast);
                if (CollectionUtils.isNotEmpty(sourceForecastList)) {
                    for (MdIncomeForecast sourceForecast : sourceForecastList) {
                        MdIncomeForecast targetForecast = new MdIncomeForecast();
                        BeanUtils.copyProperties(sourceForecast, targetForecast, "id");
                        targetForecast.setIncomeId(targetIncome.getId());
                        targetForecast.setSectionId(targetIncomeDateSection.getId());
                        targetForecast.setCreator(commonService.thisUserAccount());
                        mdIncomeForecastDao.addForecast(targetForecast);

                        //复制mdIncomeForecastItem
                        List<MdIncomeForecastItem> sourceForecastItemList = getIncomeForecastItemListByMasterId(sourceForecast.getId());
                        if (CollectionUtils.isNotEmpty(sourceForecastItemList)) {
                            for (MdIncomeForecastItem sourceForecastItem : sourceForecastItemList) {
                                MdIncomeForecastItem targetForecastItem = new MdIncomeForecastItem();
                                BeanUtils.copyProperties(sourceForecastItem, targetForecastItem, "");
                                targetForecastItem.setIncomeForecastId(targetForecast.getId());
                                targetForecastItem.setCreator(commonService.thisUserAccount());
                                mdIncomeForecastItemDao.addIncomeForecastItem(targetForecastItem);
                            }
                        }

                        //复制MdIncomeForecastYear
                        List<MdIncomeForecastYear> sourceForecastYearList = getIncomeForecastYearListByMasterId(sourceForecast.getId());
                        if (CollectionUtils.isNotEmpty(sourceForecastYearList)) {
                            for (MdIncomeForecastYear sourceForecastYear : sourceForecastYearList) {
                                MdIncomeForecastYear targetForecastYear = new MdIncomeForecastYear();
                                BeanUtils.copyProperties(sourceForecastYear, targetForecastYear, "id");
                                targetForecastYear.setIncomeId(targetIncome.getId());
                                targetForecastYear.setForecastId(targetForecast.getId());
                                targetForecastYear.setCreator(commonService.thisUserAccount());
                                mdIncomeForecastYearDao.addForecastYear(targetForecastYear);

                                //复制MdIncomeForecastMonth
                                MdIncomeForecastMonth forecastMonth = new MdIncomeForecastMonth();
                                forecastMonth.setYearId(sourceForecastYear.getId());
                                List<MdIncomeForecastMonth> sourceForecastMonthList = mdIncomeForecastMonthDao.getForecastMonthList(forecastMonth);
                                if (CollectionUtils.isNotEmpty(sourceForecastMonthList)) {
                                    for (MdIncomeForecastMonth sourceForecastMonth : sourceForecastMonthList) {
                                        MdIncomeForecastMonth targetForecastMonth = new MdIncomeForecastMonth();
                                        BeanUtils.copyProperties(sourceForecastMonth, targetForecastMonth, "id");
                                        targetForecastMonth.setYearId(targetForecastYear.getId());
                                        targetForecastMonth.setCreator(commonService.thisUserAccount());
                                        mdIncomeForecastMonthDao.addForecastMonth(targetForecastMonth);
                                    }
                                }

                            }
                        }

                    }
                }

                //复制mdIncomeLease
                MdIncomeLease where = new MdIncomeLease();
                where.setIncomeId(sourceIncome.getId());
                where.setSectionId(sourceIncomeDateSection.getId());
                List<MdIncomeLease> sourceLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(where);
                if (CollectionUtils.isNotEmpty(sourceLeaseList)) {
                    for (MdIncomeLease sourceLease : sourceLeaseList) {
                        MdIncomeLease targetLease = new MdIncomeLease();
                        BeanUtils.copyProperties(sourceLease, targetLease, "id");
                        targetLease.setIncomeId(targetIncome.getId());
                        targetLease.setSectionId(targetIncomeDateSection.getId());
                        targetLease.setCreator(commonService.thisUserAccount());
                        mdIncomeLeaseDao.addIncomeLease(targetLease);
                    }
                }

                //复制mdIncomeLeaseCost
                MdIncomeLeaseCost inComeLeaseCost = new MdIncomeLeaseCost();
                inComeLeaseCost.setIncomeId(sourceIncome.getId());
                inComeLeaseCost.setSectionId(sourceIncomeDateSection.getId());
                List<MdIncomeLeaseCost> sourceLeaseCostList = mdIncomeLeaseCostDao.getLeaseCostList(inComeLeaseCost);
                if (CollectionUtils.isNotEmpty(sourceLeaseCostList)) {
                    for (MdIncomeLeaseCost sourceLeaseCost : sourceLeaseCostList) {
                        MdIncomeLeaseCost targetLeaseCost = new MdIncomeLeaseCost();
                        BeanUtils.copyProperties(sourceLeaseCost, targetLeaseCost, "id");
                        targetLeaseCost.setIncomeId(targetIncome.getId());
                        targetLeaseCost.setSectionId(targetIncomeDateSection.getId());
                        targetLeaseCost.setCreator(commonService.thisUserAccount());
                        mdIncomeLeaseCostDao.addLeaseCost(targetLeaseCost);
                    }
                }
            }
        }

        //复制MdIncomeForecastAnalyse
        MdIncomeForecastAnalyse forecastAnalyse = new MdIncomeForecastAnalyse();
        forecastAnalyse.setIncomeId(sourceIncome.getId());
        List<MdIncomeForecastAnalyse> sourceForecastAnalyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(forecastAnalyse);
        if (CollectionUtils.isNotEmpty(sourceForecastAnalyseList)) {
            for (MdIncomeForecastAnalyse sourceForecastAnalyse : sourceForecastAnalyseList) {
                MdIncomeForecastAnalyse targetForecastAnalyse = new MdIncomeForecastAnalyse();
                BeanUtils.copyProperties(sourceForecastAnalyse, targetForecastAnalyse, "id");
                targetForecastAnalyse.setIncomeId(targetIncome.getId());
                targetForecastAnalyse.setCreator(commonService.thisUserAccount());
                mdIncomeForecastAnalyseDao.addForecastAnalyse(targetForecastAnalyse);

                //复制MdIncomeForecastAnalyseItem
                List<MdIncomeForecastAnalyseItem> sourceForecastAnalyseItemList = getForecastAnalyseItemListByMasterId(sourceForecastAnalyse.getId());
                if (CollectionUtils.isNotEmpty(sourceForecastAnalyseItemList)) {
                    for (MdIncomeForecastAnalyseItem sourceForecastAnalyseItem : sourceForecastAnalyseItemList) {
                        MdIncomeForecastAnalyseItem targetForecastAnalyseItem = new MdIncomeForecastAnalyseItem();
                        BeanUtils.copyProperties(sourceForecastAnalyseItem, targetForecastAnalyseItem, "id");
                        targetForecastAnalyseItem.setIncomeId(targetIncome.getId());
                        targetForecastAnalyseItem.setForecastAnalyseId(targetForecastAnalyse.getId());
                        targetForecastAnalyseItem.setCreator(commonService.thisUserAccount());
                        mdIncomeForecastAnalyseItemDao.addForecastAnalyseItem(targetForecastAnalyseItem);
                    }
                }

                //复制MdIncomeHistory
                MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
                mdIncomeHistory.setIncomeId(sourceIncome.getId());
                mdIncomeHistory.setForecastAnalyseId(sourceForecastAnalyse.getId());
                List<MdIncomeHistory> sourceHistoryList = mdIncomeHistoryDao.getHistoryList(mdIncomeHistory);
                if (CollectionUtils.isNotEmpty(sourceHistoryList)) {
                    for (MdIncomeHistory sourceHistory : sourceHistoryList) {
                        MdIncomeHistory targetHistory = new MdIncomeHistory();
                        BeanUtils.copyProperties(sourceHistory, targetHistory, "id");
                        targetHistory.setIncomeId(targetIncome.getId());
                        targetHistory.setForecastAnalyseId(targetForecastAnalyse.getId());
                        targetHistory.setCreator(commonService.thisUserAccount());
                        mdIncomeHistoryDao.addHistory(targetHistory);
                    }
                }
            }
        }


        //复制MdIncomePriceInvestigation
        MdIncomePriceInvestigation mdIncomePriceInvestigation = new MdIncomePriceInvestigation();
        mdIncomePriceInvestigation.setIncomeId(sourceIncome.getId());
        List<MdIncomePriceInvestigation> sourcePriceInvestigationList = mdIncomePriceInvestigationDao.getIncomePriceInvestigationList(mdIncomePriceInvestigation);
        if (CollectionUtils.isNotEmpty(sourcePriceInvestigationList)) {
            for (MdIncomePriceInvestigation sourcePriceInvestigation : sourcePriceInvestigationList) {
                MdIncomePriceInvestigation targetPriceInvestigation = new MdIncomePriceInvestigation();
                BeanUtils.copyProperties(sourcePriceInvestigation, targetPriceInvestigation, "id");
                targetPriceInvestigation.setIncomeId(targetIncome.getId());
                targetPriceInvestigation.setCreator(commonService.thisUserAccount());
                mdIncomePriceInvestigationDao.addIncomePriceInvestigation(targetPriceInvestigation);
            }
        }


    }


    //删除收益法
    public void deleteMdIncomeByPlanDetailsId(Integer targetPlanDetailsId) throws Exception {
        //主表删除
        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(targetPlanDetailsId);

        //删除MdIncome
        MdIncome income = getIncomeById(schemeInfo.getMethodDataId());

        //删除MdIncomeDateSection
        MdIncomeDateSection section = new MdIncomeDateSection();
        section.setIncomeId(schemeInfo.getMethodDataId());
        List<MdIncomeDateSection> sourceSectionList = mdIncomeDateSectionDao.getDateSectionList(section);
        if (CollectionUtils.isNotEmpty(sourceSectionList)) {
            for (MdIncomeDateSection sourceIncomeDateSection : sourceSectionList) {
                //删除MdIncomeForecast
                MdIncomeForecast mdIncomeForecast = new MdIncomeForecast();
                mdIncomeForecast.setIncomeId(schemeInfo.getMethodDataId());
                mdIncomeForecast.setSectionId(sourceIncomeDateSection.getId());
                List<MdIncomeForecast> sourceForecastList = mdIncomeForecastDao.getForecastList(mdIncomeForecast);
                if (CollectionUtils.isNotEmpty(sourceForecastList)) {
                    for (MdIncomeForecast sourceForecast : sourceForecastList) {
                        //删除mdIncomeForecastItem
                        List<MdIncomeForecastItem> sourceForecastItemList = getIncomeForecastItemListByMasterId(sourceForecast.getId());
                        if (CollectionUtils.isNotEmpty(sourceForecastItemList)) {
                            for (MdIncomeForecastItem sourceForecastItem : sourceForecastItemList) {
                                mdIncomeForecastItemDao.deleteIncomeForecastItem(sourceForecastItem.getId());
                            }
                        }

                        //删除MdIncomeForecastYear
                        List<MdIncomeForecastYear> sourceForecastYearList = getIncomeForecastYearListByMasterId(sourceForecast.getId());
                        if (CollectionUtils.isNotEmpty(sourceForecastYearList)) {
                            for (MdIncomeForecastYear sourceForecastYear : sourceForecastYearList) {
                                //删除MdIncomeForecastMonth
                                MdIncomeForecastMonth forecastMonth = new MdIncomeForecastMonth();
                                forecastMonth.setYearId(sourceForecastYear.getId());
                                List<MdIncomeForecastMonth> sourceForecastMonthList = mdIncomeForecastMonthDao.getForecastMonthList(forecastMonth);
                                if (CollectionUtils.isNotEmpty(sourceForecastMonthList)) {
                                    for (MdIncomeForecastMonth sourceForecastMonth : sourceForecastMonthList) {
                                        mdIncomeForecastMonthDao.deleteForecastMonth(sourceForecastMonth.getId());
                                    }
                                }
                                mdIncomeForecastYearDao.deleteByForecastId(sourceForecastYear.getId());
                            }

                        }
                        mdIncomeForecastDao.deleteForecast(sourceForecast.getId());
                    }
                }

                //删除mdIncomeLease
                MdIncomeLease where = new MdIncomeLease();
                where.setIncomeId(schemeInfo.getMethodDataId());
                where.setSectionId(sourceIncomeDateSection.getId());
                List<MdIncomeLease> sourceLeaseList = mdIncomeLeaseDao.getIncomeLeaseList(where);
                if (CollectionUtils.isNotEmpty(sourceLeaseList)) {
                    for (MdIncomeLease sourceLease : sourceLeaseList) {
                        mdIncomeLeaseDao.deleteLease(sourceLease.getId());
                    }
                }

                //删除mdIncomeLeaseCost
                MdIncomeLeaseCost inComeLeaseCost = new MdIncomeLeaseCost();
                inComeLeaseCost.setIncomeId(schemeInfo.getMethodDataId());
                inComeLeaseCost.setSectionId(sourceIncomeDateSection.getId());
                List<MdIncomeLeaseCost> sourceLeaseCostList = mdIncomeLeaseCostDao.getLeaseCostList(inComeLeaseCost);
                if (CollectionUtils.isNotEmpty(sourceLeaseCostList)) {
                    for (MdIncomeLeaseCost sourceLeaseCost : sourceLeaseCostList) {
                        mdIncomeLeaseCostDao.deleteLeaseCost(sourceLeaseCost.getId());
                    }
                }
                mdIncomeDateSectionDao.deleteDateSection(sourceIncomeDateSection.getId());
            }
        }

        //删除MdIncomeForecastAnalyse
        MdIncomeForecastAnalyse forecastAnalyse = new MdIncomeForecastAnalyse();
        forecastAnalyse.setIncomeId(schemeInfo.getMethodDataId());
        List<MdIncomeForecastAnalyse> sourceForecastAnalyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(forecastAnalyse);
        if (CollectionUtils.isNotEmpty(sourceForecastAnalyseList)) {
            for (MdIncomeForecastAnalyse sourceForecastAnalyse : sourceForecastAnalyseList) {
                //删除MdIncomeForecastAnalyseItem
                List<MdIncomeForecastAnalyseItem> sourceForecastAnalyseItemList = getForecastAnalyseItemListByMasterId(sourceForecastAnalyse.getId());
                if (CollectionUtils.isNotEmpty(sourceForecastAnalyseItemList)) {
                    for (MdIncomeForecastAnalyseItem sourceForecastAnalyseItem : sourceForecastAnalyseItemList) {
                        mdIncomeForecastAnalyseItemDao.deleteForecastAnalyseItem(sourceForecastAnalyseItem.getId());
                    }
                }
                //删除MdIncomeHistory
                MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
                mdIncomeHistory.setIncomeId(schemeInfo.getMethodDataId());
                mdIncomeHistory.setForecastAnalyseId(sourceForecastAnalyse.getId());
                List<MdIncomeHistory> sourceHistoryList = mdIncomeHistoryDao.getHistoryList(mdIncomeHistory);
                if (CollectionUtils.isNotEmpty(sourceHistoryList)) {
                    for (MdIncomeHistory sourceHistory : sourceHistoryList) {
                        mdIncomeHistoryDao.deleteHistory(sourceHistory.getId());
                    }
                }
                mdIncomeForecastAnalyseDao.deleteForecastAnalyse(sourceForecastAnalyse.getId());
            }
        }


        //删除MdIncomePriceInvestigation
        MdIncomePriceInvestigation mdIncomePriceInvestigation = new MdIncomePriceInvestigation();
        mdIncomePriceInvestigation.setIncomeId(schemeInfo.getMethodDataId());
        List<MdIncomePriceInvestigation> sourcePriceInvestigationList = mdIncomePriceInvestigationDao.getIncomePriceInvestigationList(mdIncomePriceInvestigation);
        if (CollectionUtils.isNotEmpty(sourcePriceInvestigationList)) {
            for (MdIncomePriceInvestigation sourcePriceInvestigation : sourcePriceInvestigationList) {
                mdIncomePriceInvestigationDao.deleteIncomePriceInvestigation(sourcePriceInvestigation.getId());
            }
        }

        mdIncomeDao.deleteIncome(income.getId());
        schemeInfoDao.deleteInfo(schemeInfo.getId());
    }
}

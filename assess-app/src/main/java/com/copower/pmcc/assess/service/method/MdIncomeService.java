package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.MethodDataTypeEnum;
import com.copower.pmcc.assess.common.enums.MethodIncomeFormTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdIncomeResultDto;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.method.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private MdIncomeForecastAnalyseItemDao mdIncomeForecastAnalyseItemDao;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;

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

            //查找预测分析数据中是否还存在对应关联的数据
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
            //如果该年份下的预测分析数据没有关联的数据，则将该预测分析数据删除
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
     * 生成预测分析数据
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

        if (CollectionUtils.isNotEmpty(analyseList)) {
            for (int i = 0; i < analyseList.size(); i++) {
                //删除原来的明细
                MdIncomeForecastAnalyseItem oldItem = new MdIncomeForecastAnalyseItem();
                oldItem.setForecastAnalyseId(analyseList.get(i).getId());
                List<MdIncomeForecastAnalyseItem> oldForecastAnalyseItemList = mdIncomeForecastAnalyseItemDao.getForecastAnalyseItemList(oldItem);
                if (CollectionUtils.isNotEmpty(oldForecastAnalyseItemList)) {
                    for (MdIncomeForecastAnalyseItem old : oldForecastAnalyseItemList) {
                        mdIncomeForecastAnalyseItemDao.deleteForecastAnalyseItem(old.getId());
                    }
                }
                //历史数据添加到分析明细
                MdIncomeHistory historyWhere = new MdIncomeHistory();
                historyWhere.setForecastAnalyseId(analyseList.get(i).getId());
                List<MdIncomeHistory> currhistoryList = mdIncomeHistoryDao.getHistoryList(historyWhere);
                //同年同来源数据总价
                BigDecimal total = new BigDecimal("0");
                if (CollectionUtils.isNotEmpty(currhistoryList)) {
                    for (MdIncomeHistory mdIncomeHistory : currhistoryList) {
                        total = total.add(mdIncomeHistory.getAmountMoney());
                    }

                }
                analyseList.get(i).setAmountMoney(total);
                mdIncomeForecastAnalyseDao.updateForecastAnalyse(analyseList.get(i));
                //生成预测明细数据
                List<List<MdIncomeHistory>> goodsList = getGoodsList(currhistoryList);
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
                        mdIncomeForecastAnalyseItem.setFormType(analyseList.get(i).getFormType());
                        mdIncomeForecastAnalyseItemDao.addForecastAnalyseItem(mdIncomeForecastAnalyseItem);
                    }
                }
            }
        }

    }

    //一条预测分析数据对应的List<MdIncomeHistory>有几种物品
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
                    mdIncomeForecastAnalyseItemVo.setAmountMoney(analyseItems.get(i).getAmountMoney());
                    mdIncomeForecastAnalyseItemVo.setNumber(analyseItems.get(i).getNumber());
                    if (analyseItems.get(i).getAccountingSubject() != null && analyseItems.get(i).getAccountingSubject() > 0) {
                        String accountingSubjectName = baseDataDicService.getNameById(analyseItems.get(i).getAccountingSubject());
                        mdIncomeForecastAnalyseItemVo.setName(String.format("%s/%s/%s", accountingSubjectName, analyseItems.get(i).getFirstLevelNumber(), analyseItems.get(i).getSecondLevelNumber()));
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
                        if (quantitativeTrend != null)
                            mdIncomeForecastAnalyseItemVo.setQuantitativeTrend(quantitativeTrend.toString());
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
            }
        }
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
                    mdIncomeForecastAnalyseItemVo.setAmountMoney(analyseItems.get(i).getAmountMoney());
                    mdIncomeForecastAnalyseItemVo.setNumber(analyseItems.get(i).getNumber());
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
                        if (quantitativeTrend != null)
                            mdIncomeForecastAnalyseItemVo.setQuantitativeTrend(quantitativeTrend.toString());
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
            }
        }
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
                    mdIncomeForecastAnalyseItemVo.setAmountMoney(analyseItems.get(i).getAmountMoney());
                    mdIncomeForecastAnalyseItemVo.setNumber(analyseItems.get(i).getNumber());
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
                        if (quantitativeTrend != null)
                            mdIncomeForecastAnalyseItemVo.setQuantitativeTrend(quantitativeTrend.toString());
                        vos.add(mdIncomeForecastAnalyseItemVo);
                    }
                }
            }
        }
        return vos;
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
        List<MdIncomeForecastAnalyseItemVo> accountingSubjectTrendVos = getAccountingSubjectTrend(forecastAnalyseId);
        List<MdIncomeForecastAnalyseItemVo> firstLevelVos = getStairTrend(forecastAnalyseId);
        List<MdIncomeForecastAnalyseItemVo> secondLevelVos = getSecondLevelTrend(forecastAnalyseId);
        vos.addAll(accountingSubjectTrendVos);
        vos.addAll(firstLevelVos);
        vos.addAll(secondLevelVos);
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
                        mdIncomeHistory.setUnit(PoiUtils.getCellValue(row.getCell(6)));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(7)))) {
                            mdIncomeHistory.setUnitPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(7))));
                        }
                        mdIncomeHistory.setNumber(Integer.valueOf(PoiUtils.getCellValue(row.getCell(8))));
                        if (StringUtils.isNotEmpty(PoiUtils.getCellValue(row.getCell(9)))) {
                            mdIncomeHistory.setAmountMoney(new BigDecimal(PoiUtils.getCellValue(row.getCell(9))));
                        }
                        //mdIncomeHistory.setMoneyExplain(PoiUtils.getCellValue(row.getCell(10)));
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
                        //mdIncomeHistory.setMoneyExplain(PoiUtils.getCellValue(row.getCell(16)));
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
        return mdIncomeHistoryVo;
    }

    public void addForecast(MdIncomeForecast mdIncomeForecast) {
        mdIncomeForecast.setCreator(commonService.thisUserAccount());
        mdIncomeForecastDao.addForecast(mdIncomeForecast);
    }

    /**
     * 保存预测数据
     *
     * @param mdIncomeForecast
     * @return
     */
    @Transactional
    public void updateForecast(MdIncomeForecast mdIncomeForecast) {
        mdIncomeForecastDao.updateForecast(mdIncomeForecast);

        //先清除原数据
        mdIncomeForecastYearDao.deleteByForecastId(mdIncomeForecast.getId());

        //生成预测数据的年数据
        MdIncomeDateSection incomeDateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeForecast.getSectionId());
        BigDecimal preYearAmount = null;//上一年金额
        BigDecimal totalAmount = new BigDecimal("0");//总金额
        for (int i = 1; i <= incomeDateSection.getYearCount().intValue(); i++) {
            MdIncomeForecastYear mdIncomeForecastYear = new MdIncomeForecastYear();
            mdIncomeForecastYear.setForecastId(mdIncomeForecast.getId());
            mdIncomeForecastYear.setType(mdIncomeForecast.getType());
            mdIncomeForecastYear.setBeginDate(DateUtils.addYear(incomeDateSection.getBeginDate(), i - 1));
            mdIncomeForecastYear.setEndDate(DateUtils.addYear(incomeDateSection.getBeginDate(), i));
            if (i == 1 || mdIncomeForecast.getGrowthRate() == null) {
                mdIncomeForecastYear.setAmount(mdIncomeForecast.getInitialAmount());
                preYearAmount = mdIncomeForecast.getInitialAmount();
            } else {
                //根据增长率计算各年金额，增长率与上一年金额相关 （1+增长率）*上一年金额
                mdIncomeForecastYear.setAmount(mdIncomeForecast.getGrowthRate().add(new BigDecimal("1")).multiply(preYearAmount));
                preYearAmount = mdIncomeForecastYear.getAmount();
            }
            totalAmount = totalAmount.add(mdIncomeForecastYear.getAmount());
            mdIncomeForecastYear.setCreator(commonService.thisUserAccount());
            mdIncomeForecastYearDao.addForecastYear(mdIncomeForecastYear);
        }

        //更新总收入或总成本
        if (mdIncomeForecast.getType().equals(MethodDataTypeEnum.INCOME.getId())) {
            incomeDateSection.setIncomeTotal(totalAmount);
        }
        if (mdIncomeForecast.getType().equals(MethodDataTypeEnum.COST.getId())) {
            incomeDateSection.setCostTotal(totalAmount);
        }
        mdIncomeDateSectionDao.updateDateSection(incomeDateSection);
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
}

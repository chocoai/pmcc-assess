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
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private DataTaxRateAllocationService dataTaxRateAllocationService;
    @Autowired
    private MdIncomeForecastAnalyseDao mdIncomeForecastAnalyseDao;

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
            mdIncomeHistory.setCreator(commonService.thisUserAccount());
            mdIncomeHistoryDao.addHistory(mdIncomeHistory);
            generatorForecastAnalyse(mdIncomeHistory.getIncomeId(),mdIncomeHistory.getType(),mdIncomeHistory.getYear());
        }
    }

    /**
     * 历史数据添加到预测数据
     *
     * @param ids
     */
    @Transactional
    public void historyToForecast(List<Integer> ids,Integer forecastAnalyseId) {
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Integer id : ids) {
                MdIncomeHistory history = mdIncomeHistoryDao.getHistoryById(id);
                history.setBisForecast(true);
                history.setForecastAnalyseId(forecastAnalyseId);
                mdIncomeHistoryDao.updateHistory(history);
                generatorForecastAnalyse(history.getIncomeId(),history.getType(),history.getYear());
            }
        }
    }

    /**
     * 历史数据添加到预测数据
     *
     * @param ids
     */
    @Transactional
    public void historyToForecast(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Integer id : ids) {
                MdIncomeHistory history = mdIncomeHistoryDao.getHistoryById(id);
                history.setBisForecast(false);
                mdIncomeHistoryDao.updateHistory(history);
            }
        }
    }

    /**
     * 预测数据分析
     *
     * @param incomeId
     * @param type
     */
    public void forecastBaseAnalyse(Integer incomeId, Integer type) {
        //1.从预测基础数据的第一条数据算起，找出该时间推迟一年的时间，作为结束时间

        //如果表单类型为默认，则取最早的预测数据做基准，往后推与下一年的月份数与当月对应时做为一年
        //计算出当前的合计金额，再依次推算出后几年的 数量趋势与单价趋势
        MdIncome mdIncome = mdIncomeDao.getIncomeById(incomeId);
        if (mdIncome == null) return;
        if (MethodIncomeFormTypeEnum.DEFUALT.equals(mdIncome.getFormType())) {
            MdIncomeHistory whereHistory = new MdIncomeHistory();
            whereHistory.setIncomeId(incomeId);
            whereHistory.setBisForecast(true);
            List<MdIncomeHistory> historyList = mdIncomeHistoryDao.getHistoryList(whereHistory);
            if (CollectionUtils.isNotEmpty(historyList)) {
                MdIncomeHistory baseForecast = historyList.get(0);//基准预测数据
                Integer baseYear = baseForecast.getYear();
                Integer baseMonth = baseForecast.getMonth();
                Integer endMonth = baseForecast.getMonth().equals(1) ? 12 : baseForecast.getMonth() - 1;//结束月份
                Integer endYear = endMonth.equals(12) ? baseYear : baseYear + 1;
                //将数据拆分为多个年份数据
                Map<Integer, List<MdIncomeHistory>> map = Maps.newHashMap();

            }
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
     * 生成预测分析数据
     *
     * @param incomeId
     */
    public void generatorForecastAnalyse(Integer incomeId, Integer type, Integer year) {
        MdIncomeForecastAnalyse where = new MdIncomeForecastAnalyse();
        where.setIncomeId(incomeId);
        where.setType(type);
        where.setYear(year);
        if (mdIncomeForecastAnalyseDao.getForecastAnalyseCount(where) <= 0) {
            MdIncomeForecastAnalyse mdIncomeForecastAnalyse = new MdIncomeForecastAnalyse();
            mdIncomeForecastAnalyse.setIncomeId(incomeId);
            mdIncomeForecastAnalyse.setType(type);
            mdIncomeForecastAnalyse.setYear(year);
            mdIncomeForecastAnalyse.setCreator(commonService.thisUserAccount());
            mdIncomeForecastAnalyseDao.addForecastAnalyse(mdIncomeForecastAnalyse);
        }
    }

    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getForecastAnalyseList(Integer incomeId, Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        MdIncomeForecastAnalyse where = new MdIncomeForecastAnalyse();
        where.setType(type);
        where.setIncomeId(incomeId);
        List<MdIncomeForecastAnalyse> analyseList = mdIncomeForecastAnalyseDao.getForecastAnalyseList(where);
        vo.setRows(CollectionUtils.isEmpty(analyseList) ? new ArrayList<MdIncomeForecastAnalyse>() : analyseList);
        vo.setTotal((long)analyseList.size());
        return vo;
    }

    /**
     * 导入历史数据
     *
     * @param history
     * @param multipartFile
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    public String importHistory(MdIncomeHistory history, MultipartFile multipartFile) throws BusinessException, IOException {
        if (history == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook hssfWorkbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int startRowNumber = 1;//读取数据的起始行
        int rowCount = sheet.getLastRowNum() + 1 - startRowNumber;//数据总行数
        StringBuilder errorMsg = new StringBuilder();
        int successCount = 0;//导入成功数据条数
        String key = history.getType().equals(0) ? AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_INCOME : AssessDataDicKeyConstant.MD_INCOME_HISTORY_TYPE_COST;
        List<BaseDataDic> subjectList = baseDataDicService.getCacheDataDicList(key);
        for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                MdIncomeHistory mdIncomeHistory = new MdIncomeHistory();
                mdIncomeHistory.setIncomeId(history.getIncomeId());
                mdIncomeHistory.setType(history.getType());
                mdIncomeHistory.setCreator(commonService.thisUserAccount());
                mdIncomeHistory.setYear(Integer.valueOf(PoiUtils.getCellValue(row.getCell(1))));
                BaseDataDic subjectDic = baseDataDicService.getDataDicByName(subjectList, PoiUtils.getCellValue(row.getCell(2)));
                if (subjectDic == null) {
                    errorMsg.append(String.format("\n第%s行异常：会计科目与系统配置的名称不一致", i + 1));
                    continue;
                }
                mdIncomeHistory.setAccountingSubject(subjectDic.getId());
                mdIncomeHistory.setFirstLevelNumber(PoiUtils.getCellValue(row.getCell(3)));
                mdIncomeHistory.setSecondLevelNumber(PoiUtils.getCellValue(row.getCell(4)));
                mdIncomeHistory.setMonth(Integer.valueOf(PoiUtils.getCellValue(row.getCell(5))));
                mdIncomeHistory.setUnitPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(6))));
                mdIncomeHistory.setNumber(Integer.valueOf(PoiUtils.getCellValue(row.getCell(7))));
                mdIncomeHistory.setAmountMoney(new BigDecimal(PoiUtils.getCellValue(row.getCell(8))));
                mdIncomeHistoryDao.addHistory(mdIncomeHistory);
                generatorForecastAnalyse(mdIncomeHistory.getIncomeId(),mdIncomeHistory.getType(),mdIncomeHistory.getYear());
                successCount++;
            } catch (Exception e) {
                errorMsg.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        inputStream.close();
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowCount, successCount, rowCount - successCount, errorMsg.toString());
    }

    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getHistoryList(Integer incomeId, Integer type, Boolean bisForecast) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeHistory where = new MdIncomeHistory();
        where.setType(type);
        where.setBisForecast(bisForecast);
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
        for (int i = 1; i <= incomeDateSection.getYearCount(); i++) {
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
    public void updateLease(MdIncomeLease mdIncomeLease) {
        mdIncomeLeaseDao.updateIncomeLease(mdIncomeLease);

        //计算总收入 月租金*出租率*月份数+押金*押金利率+其它收入
        BigDecimal total = mdIncomeLease.getRentalIncome().multiply(mdIncomeLease.getRentals()).multiply(new BigDecimal(mdIncomeLease.getMonthNumber()));
        total = total.add(mdIncomeLease.getDeposit().multiply(mdIncomeLease.getDepositRate()));
        total = total.add(mdIncomeLease.getOtherIncome());
        MdIncomeDateSection incomeDateSection = mdIncomeDateSectionDao.getDateSectionById(mdIncomeLease.getSectionId());
        if (incomeDateSection != null) {
            incomeDateSection.setIncomeTotal(total);
            mdIncomeDateSectionDao.updateDateSection(incomeDateSection);
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
            BigDecimal total = incomeTotal.multiply(mdIncomeLeaseCost.getManagementCostRatio());
            total = total.add(mdIncomeLeaseCost.getReplacementValue().multiply(mdIncomeLeaseCost.getMaintenanceCostRatio()));
            total = total.add(incomeTotal.multiply(mdIncomeLeaseCost.getAdditionalRatio()));
            total = total.add(incomeTotal.multiply(mdIncomeLeaseCost.getInsurancePremiumRatio()));
            total = total.add(mdIncomeLeaseCost.getLandUseTax());
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
     * 获取租赁税费 房产税+印花税+增值税*(1+城建税+地方教育费附加+教育费附加)
     *
     * @param province
     * @param city
     * @param district
     * @return
     */
    public BigDecimal getAdditionalRatio(String province, String city, String district) {
        BigDecimal propertyTax = new BigDecimal("0");//房产税
        BigDecimal stampDuty = new BigDecimal("0");//印花税
        BigDecimal salesTax = new BigDecimal("0");//增值税
        BigDecimal constructionTax = new BigDecimal("0");//城建税
        BigDecimal localEducationTax = new BigDecimal("0");//地方教育费附加
        BigDecimal educationFeePlus = new BigDecimal("0");//教育费附加
        //房产税
        DataTaxRateAllocation propertyTaxAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_PROPERTY_TAX);
        if (propertyTaxAllocation != null)
            propertyTax = propertyTaxAllocation.getTaxRate();
        //印花税
        propertyTaxAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_STAMP_DUTY);
        if (propertyTaxAllocation != null)
            stampDuty = propertyTaxAllocation.getTaxRate();
        //增值税
        propertyTaxAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_SALES_TAX);
        if (propertyTaxAllocation != null)
            salesTax = propertyTaxAllocation.getTaxRate();
        //城建税
        propertyTaxAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX);
        if (propertyTaxAllocation != null)
            constructionTax = propertyTaxAllocation.getTaxRate();
        //地方教育费附加
        propertyTaxAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL, province, city, district);
        if (propertyTaxAllocation != null)
            localEducationTax = propertyTaxAllocation.getTaxRate();
        //教育费附加
        propertyTaxAllocation = dataTaxRateAllocationService.getTaxRateByKey(AssessDataDicKeyConstant.DATA_TAX_RATE_ALLOCATION_EDUCATION_FEE_PLUS);
        if (propertyTaxAllocation != null)
            educationFeePlus = propertyTaxAllocation.getTaxRate();

        BigDecimal total = new BigDecimal("0");
        total = total.add(propertyTax).add(stampDuty);
        total = total.add(salesTax.multiply(new BigDecimal("1").add(constructionTax).add(localEducationTax).add(educationFeePlus)));
        total = total.setScale(4, BigDecimal.ROUND_HALF_UP);
        return total;
    }
}

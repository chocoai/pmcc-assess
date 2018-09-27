package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.MethodDataTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdIncomeResultDto;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeForecastMonthVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeForecastVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeHistoryVo;
import com.copower.pmcc.assess.dto.output.method.MdIncomeLeaseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
                mdIncomeHistory.setYear(PoiUtils.getCellValue(row.getCell(1)));
                BaseDataDic subjectDic = baseDataDicService.getDataDicByName(subjectList, PoiUtils.getCellValue(row.getCell(2)));
                if (subjectDic == null) {
                    errorMsg.append(String.format("\n第%s行异常：会计科目与系统配置的名称不一致", i + 1));
                    continue;
                }
                mdIncomeHistory.setAccountingSubject(subjectDic.getId());
                List<BaseDataDic> firstNoList = baseDataDicService.getCacheDataDicListByPid(subjectDic.getId());
                BaseDataDic firstNoDic = baseDataDicService.getDataDicByName(firstNoList, PoiUtils.getCellValue(row.getCell(3)));
                if (firstNoDic == null) {
                    errorMsg.append(String.format("\n第%s行异常：一级编号与系统配置的名称不一致", i + 1));
                    continue;
                }
                mdIncomeHistory.setFirstLevelNumber(firstNoDic.getId());
                List<BaseDataDic> secondNoList = baseDataDicService.getCacheDataDicListByPid(firstNoDic.getId());
                BaseDataDic secondNoDic = baseDataDicService.getDataDicByName(secondNoList, PoiUtils.getCellValue(row.getCell(4)));
                if (secondNoDic == null) {
                    errorMsg.append(String.format("\n第%s行异常：一级编号与系统配置的名称不一致", i + 1));
                    continue;
                }
                mdIncomeHistory.setSecondLevelNumber(secondNoDic.getId());
                mdIncomeHistory.setMonth(PoiUtils.getCellValue(row.getCell(5)));
                mdIncomeHistory.setUnitPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(6))));
                mdIncomeHistory.setNumber(Integer.valueOf(PoiUtils.getCellValue(row.getCell(7))));
                mdIncomeHistory.setAmountMoney(new BigDecimal(PoiUtils.getCellValue(row.getCell(8))));
                mdIncomeHistoryDao.addHistory(mdIncomeHistory);
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
    @Transactional
    public void saveForecast(MdIncomeForecast mdIncomeForecast) {
        if (mdIncomeForecast.getId() != null && mdIncomeForecast.getId() > 0) {
            mdIncomeForecastDao.updateForecast(mdIncomeForecast);
        } else {
            mdIncomeForecast.setCreator(commonService.thisUserAccount());
            mdIncomeForecastDao.addForecast(mdIncomeForecast);
        }

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
            totalAmount.add(mdIncomeForecastYear.getAmount());
            mdIncomeForecastYear.setCreator(commonService.thisUserAccount());
            mdIncomeForecastYearDao.addForecastYear(mdIncomeForecastYear);
        }

        //更新总收入或总成本
        if(mdIncomeForecast.getType().equals(MethodDataTypeEnum.INCOME.getId())){
            incomeDateSection.setIncomeTotal(totalAmount);
        }
        if(mdIncomeForecast.getType().equals(MethodDataTypeEnum.COST.getId())){
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
        if (mdIncomeForecastMonth.getFirstLevelNumber() != null && mdIncomeForecastMonth.getFirstLevelNumber() > 0) {
            mdIncomeForecastMonthVo.setFirstLevelNumberName(baseDataDicService.getNameById(mdIncomeForecastMonth.getFirstLevelNumber()));
        }
        if (mdIncomeForecastMonth.getSecondLevelNumber() != null && mdIncomeForecastMonth.getSecondLevelNumber() > 0) {
            mdIncomeForecastMonthVo.setSecondLevelNumberName(baseDataDicService.getNameById(mdIncomeForecastMonth.getSecondLevelNumber()));
        }
        return mdIncomeForecastMonthVo;
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

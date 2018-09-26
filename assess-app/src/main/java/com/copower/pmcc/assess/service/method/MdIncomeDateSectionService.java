package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.enums.MethodDataTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDateSectionDao;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeDateSection;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecast;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLease;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeLeaseCost;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataCommonService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Service
public class MdIncomeDateSectionService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCommonService dataCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private MdIncomeDateSectionDao mdIncomeDateSectionDao;
    @Autowired
    private MdIncomeService mdIncomeService;

    /**
     * 保存数据
     *
     * @param mdIncomeDateSection
     */
    @Transactional
    public void saveDateSection(MdIncomeDateSection mdIncomeDateSection) {
        if (mdIncomeDateSection.getId() != null && mdIncomeDateSection.getId() > 0) {
            mdIncomeDateSection.setYearCount(DateUtils.diffDate(mdIncomeDateSection.getEndDate(), mdIncomeDateSection.getBeginDate()) / DateUtils.DAYS_PER_YEAR);
            mdIncomeDateSectionDao.updateDateSection(mdIncomeDateSection);
        } else {
            mdIncomeDateSection.setCreator(commonService.thisUserAccount());
            mdIncomeDateSection.setYearCount(DateUtils.diffDate(mdIncomeDateSection.getEndDate(), mdIncomeDateSection.getBeginDate()) / DateUtils.DAYS_PER_YEAR);
            mdIncomeDateSectionDao.addDateSection(mdIncomeDateSection);

            //为各项从表添加对应数据
            //自营 收入预测 成本预测
            MdIncomeForecast mdIncomeForecast = new MdIncomeForecast();
            mdIncomeForecast.setIncomeId(mdIncomeDateSection.getIncomeId());
            mdIncomeForecast.setSectionId(mdIncomeDateSection.getId());
            mdIncomeForecast.setType(MethodDataTypeEnum.INCOME.getId());
            mdIncomeForecast.setCreator(commonService.thisUserAccount());
            mdIncomeService.saveForecast(mdIncomeForecast);
            mdIncomeForecast.setId(null);
            mdIncomeForecast.setType(MethodDataTypeEnum.COST.getId());
            mdIncomeService.saveForecast(mdIncomeForecast);
            //租赁 收入类 成本类 参数
            MdIncomeLease mdIncomeLease = new MdIncomeLease();
            mdIncomeLease.setIncomeId(mdIncomeDateSection.getIncomeId());
            mdIncomeLease.setSectionId(mdIncomeDateSection.getId());
            mdIncomeLease.setCreator(commonService.thisUserAccount());
            mdIncomeService.saveLease(mdIncomeLease);

            MdIncomeLeaseCost mdIncomeLeaseCost = new MdIncomeLeaseCost();
            mdIncomeLeaseCost.setIncomeId(mdIncomeDateSection.getIncomeId());
            mdIncomeLeaseCost.setSectionId(mdIncomeDateSection.getId());
            mdIncomeLeaseCost.setCreator(commonService.thisUserAccount());
            mdIncomeService.saveLeaseCost(mdIncomeLeaseCost);

        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteDateSection(Integer id) throws BusinessException {
        //先检查各子项有无相关联的数据
        if (mdIncomeService.getForecastCountBySectionId(id) > 0)
            throw new BusinessException("请先删除相关自营的预测数据");
        if (mdIncomeService.getLeaseCountBySectionId(id) > 0)
            throw new BusinessException("请先删除相关租赁的收入数据");
        if (mdIncomeService.getLeaseCostCountBySectionId(id) > 0)
            throw new BusinessException("请先删除相关租赁的成本数据");
        return mdIncomeDateSectionDao.deleteDateSection(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public MdIncomeDateSection getDateSectionById(Integer id) {
        return mdIncomeDateSectionDao.getDateSectionById(id);
    }


    /**
     * 获取数据列表
     *
     * @param incomeId
     * @return
     */
    public BootstrapTableVo getDateSectionList(Integer incomeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        MdIncomeDateSection mdIncomeDateSection = new MdIncomeDateSection();
        mdIncomeDateSection.setIncomeId(incomeId);
        if (incomeId == 0) {
            mdIncomeDateSection.setCreator(commonService.thisUserAccount());
        }
        List<MdIncomeDateSection> sectionList = mdIncomeDateSectionDao.getDateSectionList(mdIncomeDateSection);
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(sectionList) ? new ArrayList<MdIncomeDateSection>() : sectionList);
        vo.setTotal(page.getTotal());
        return vo;
    }

}

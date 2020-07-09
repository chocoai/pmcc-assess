package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseTradingDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicHouseTradingService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseTradingDao basicHouseTradingDao;
    @Autowired
    private BasicHouseTradingSellService basicHouseTradingSellService;
    @Autowired
    private BasicHouseTradingLeaseService basicHouseTradingLeaseService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseTrading getBasicHouseTradingById(Integer id) throws Exception {
        return basicHouseTradingDao.getBasicHouseTradingById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseTrading
     * @return
     * @throws Exception
     */
    public BasicHouseTrading saveAndUpdateBasicHouseTrading(BasicHouseTrading basicHouseTrading, boolean updateNull) throws Exception {
        if (basicHouseTrading.getId() == null || basicHouseTrading.getId().intValue() == 0) {
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTradingDao.addBasicHouseTrading(basicHouseTrading);
            return basicHouseTrading;
        } else {
            if (updateNull) {
                BasicHouseTrading houseTrading = basicHouseTradingDao.getBasicHouseTradingById(basicHouseTrading.getId());
                if (houseTrading != null) {
                    basicHouseTrading.setBisDelete(houseTrading.getBisDelete());
                    basicHouseTrading.setCreator(houseTrading.getCreator());
                    basicHouseTrading.setGmtCreated(houseTrading.getGmtCreated());
                    basicHouseTrading.setGmtModified(DateUtils.now());
                }
            }
            basicHouseTradingDao.updateBasicHouseTrading(basicHouseTrading, updateNull);
            return basicHouseTrading;
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouseTrading(Integer id) throws Exception {
        return basicHouseTradingDao.deleteBasicHouseTrading(id);
    }

    public boolean deleteBasicHouseTradingByHouseId(Integer houseId) throws Exception {
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setHouseId(houseId);
        List<BasicHouseTrading> tradings = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
        if (CollectionUtils.isEmpty(tradings)) return true;
        tradings.forEach(o -> basicHouseTradingDao.deleteBasicHouseTrading(o.getId()));
        return true;
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseTrading
     * @return
     * @throws Exception
     */
    public List<BasicHouseTrading> basicHouseTradingList(BasicHouseTrading basicHouseTrading) throws Exception {
        return basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
    }

    /**
     * 获取单个时，取的是标准交易信息
     * @param houseId
     * @return
     */
    public BasicHouseTrading getTradingByHouseId(Integer houseId) {
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setHouseId(houseId);
        basicHouseTrading.setBisMark(true);
        List<BasicHouseTrading> tradings = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
        if (CollectionUtils.isEmpty(tradings)) return null;
        return tradings.get(0);
    }

    public List<BasicHouseTrading> getTradingListByHouseId(Integer houseId) {
        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setHouseId(houseId);
        return basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicHouseTrading basicHouseTrading) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseTrading> basicHouseTradingList = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicHouseTradingList) ? new ArrayList<BasicHouseTrading>(10) : basicHouseTradingList);
        return vo;
    }

    public BasicHouseTradingVo getBasicHouseTradingVo(BasicHouseTrading basicHouseTrading) {
        if (basicHouseTrading == null) {
            return null;
        }
        BasicHouseTradingVo vo = new BasicHouseTradingVo();
        BeanUtils.copyProperties(basicHouseTrading, vo);
        if (basicHouseTrading.getTradingTime() != null) {
            vo.setTradingTimeName(DateUtils.format(basicHouseTrading.getTradingTime()));
        }
        vo.setTradingTypeName(baseDataDicService.getNameById(basicHouseTrading.getTradingType()));
        vo.setPaymentMethodName(baseDataDicService.getNameById(basicHouseTrading.getPaymentMethod()));
        vo.setTransactionSituationName(baseDataDicService.getNameById(basicHouseTrading.getTransactionSituation()));
        vo.setDescriptionTypeName(baseDataDicService.getNameById(basicHouseTrading.getDescriptionType()));
        vo.setTaxBurdenName(baseDataDicService.getNameById(basicHouseTrading.getTaxBurden()));
        vo.setInformationTypeName(baseDataDicService.getNameById(basicHouseTrading.getInformationType()));
        vo.setInformationCategoryName(baseDataDicService.getNameById(basicHouseTrading.getInformationCategory()));
        vo.setScopePropertyName(baseDataDicService.getNameById(basicHouseTrading.getScopeProperty()));
        vo.setPriceConnotationName(baseDataDicService.getNameById(basicHouseTrading.getPriceConnotation()));
        vo.setPriceTypeName(baseDataDicService.getNameById(basicHouseTrading.getPriceType()));
        return vo;
    }


    public List<BasicHouseTradingVo> basicHouseTradingVoList(BasicHouseTrading basicHouseTrading) throws Exception {
        List<BasicHouseTrading> basicHouseTradings = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
        List<BasicHouseTradingVo> transform = LangUtils.transform(basicHouseTradings, o -> getBasicHouseTradingVo(o));
        return transform;
    }

    //出租出售写入tradingId
    public void fixOldData() throws Exception {
        //出售
        BasicHouseTradingSell basicHouseTradingSell = new BasicHouseTradingSell();
        List<BasicHouseTradingSell> basicHouseTradingSells = basicHouseTradingSellService.basicHouseTradingSells(basicHouseTradingSell);
        if (!CollectionUtils.isEmpty(basicHouseTradingSells)) {
            for (BasicHouseTradingSell sell : basicHouseTradingSells) {
                BasicHouseTrading trading = getTradingByHouseId(sell.getHouseId());
                sell.setTradingId(trading.getId());
                basicHouseTradingSellService.saveAndUpdateBasicHouseTradingSell(sell, true);
            }
        }
        //出租
        BasicHouseTradingLease basicHouseTradingLease = new BasicHouseTradingLease();
        List<BasicHouseTradingLease> basicHouseTradingLeases = basicHouseTradingLeaseService.basicHouseTradingLeaseList(basicHouseTradingLease);
        if (!CollectionUtils.isEmpty(basicHouseTradingLeases)) {
            for (BasicHouseTradingLease lease : basicHouseTradingLeases) {
                BasicHouseTrading trading = getTradingByHouseId(lease.getHouseId());
                lease.setTradingId(trading.getId());
                basicHouseTradingLeaseService.saveAndUpdateBasicHouseTradingLease(lease, true);
            }
        }
    }

    public void updateBisMark(BasicHouseTrading basicHouseTrading) throws Exception {
        List<BasicHouseTrading> tradingList = getTradingListByHouseId(basicHouseTrading.getHouseId());
        if (!CollectionUtils.isEmpty(tradingList)) {
            if (basicHouseTrading.getBisMark() == true) {
                for (BasicHouseTrading item : tradingList) {
                    item.setBisMark(false);
                    saveAndUpdateBasicHouseTrading(item, true);
                }

            } else {
                List<BasicHouseTrading> filter = LangUtils.filter(tradingList, p -> {
                    return p.getBisMark() == true&&!p.getId().equals(basicHouseTrading.getId());
                });
                if (CollectionUtils.isEmpty(filter)) {
                    throw new BusinessException("失败，至少保证存在一个标识");
                }
            }
            saveAndUpdateBasicHouseTrading(basicHouseTrading, true);
        }

    }
}

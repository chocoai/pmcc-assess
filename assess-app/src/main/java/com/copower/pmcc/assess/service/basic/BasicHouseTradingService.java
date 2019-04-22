package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseTradingDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
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

import java.sql.SQLException;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseTrading getBasicHouseTradingById(Integer id)throws Exception{
        return basicHouseTradingDao.getBasicHouseTradingById(id);
    }

    /**
     * 新增或者修改
     * @param basicHouseTrading
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseTrading(BasicHouseTrading basicHouseTrading)throws Exception{
        if (basicHouseTrading.getId()== null || basicHouseTrading.getId().intValue()==0){
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            return basicHouseTradingDao.saveBasicHouseTrading(basicHouseTrading);
        }else {
            basicHouseTradingDao.updateBasicHouseTrading(basicHouseTrading);
            return null;
        }
    }


    /**
     * 删除数据
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouseTrading(Integer id)throws Exception{
        return basicHouseTradingDao.deleteBasicHouseTrading(id);
    }

    /**
     * 获取数据列表
     * @param basicHouseTrading
     * @return
     * @throws Exception
     */
    public List<BasicHouseTrading> basicHouseTradingList(BasicHouseTrading basicHouseTrading)throws Exception{
        return basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
    }

    public BasicHouseTrading getTradingByHouseId(Integer houseId){
        try {
            BasicHouseTrading basicHouseTrading=new BasicHouseTrading();
            basicHouseTrading.setHouseId(houseId);
            List<BasicHouseTrading> tradings = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
            if(CollectionUtils.isEmpty(tradings)) return null;
            return tradings.get(0);
        } catch (SQLException e) {
            return null;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseTrading basicHouseTrading)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseTrading> basicHouseTradingList = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicHouseTradingList)?new ArrayList<BasicHouseTrading>(10):basicHouseTradingList);
        return vo;
    }

    public BasicHouseTradingVo getBasicHouseTradingVo(BasicHouseTrading basicHouseTrading){
        if (basicHouseTrading == null){
            return null;
        }
        BasicHouseTradingVo vo = new BasicHouseTradingVo();
        BeanUtils.copyProperties(basicHouseTrading,vo);
        if (basicHouseTrading.getTradingTime() != null){
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
        return vo;
    }

}

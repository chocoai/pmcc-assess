package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.dao.BasicHouseTradingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Integer upgradeVersion(BasicHouseTrading basicHouseTrading)throws Exception{
        if (basicHouseTrading.getId()== null || basicHouseTrading.getId().intValue()==0){
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            if (basicHouseTrading.getVersion() == null){
                basicHouseTrading.setVersion(0);
            }
            Integer id = basicHouseTradingDao.saveBasicHouseTrading(basicHouseTrading);
            basicHouseTrading.setId(id);
            return id;
        }else {
            BasicHouseTrading oo = basicHouseTradingDao.getBasicHouseTradingById(basicHouseTrading.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            basicHouseTrading.setVersion(oo.getVersion()+1);
            basicHouseTradingDao.updateBasicHouseTrading(basicHouseTrading);
            return basicHouseTrading.getId();
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
        BasicHouseTradingVo vo = new BasicHouseTradingVo();
        BaseDataDic dataDic = null;
        BeanUtils.copyProperties(basicHouseTrading,vo);
        if (basicHouseTrading.getTradingTime() != null){
            vo.setTradingTimeName(DateUtils.format(basicHouseTrading.getTradingTime()));
        }
        if (basicHouseTrading.getTradingType() != null){
            dataDic = baseDataDicService.getDataDicById(basicHouseTrading.getTradingType());
            if (dataDic != null){
                vo.setTradingTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (StringUtils.isNotEmpty(basicHouseTrading.getInformationType())){
            if (NumberUtils.isNumber(basicHouseTrading.getInformationType())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseTrading.getInformationType()));
                if (dataDic != null){
                    vo.setInformationTypeName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(basicHouseTrading.getPaymentMethod())){
            if (NumberUtils.isNumber(basicHouseTrading.getPaymentMethod())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseTrading.getPaymentMethod()));
                if (dataDic != null){
                    vo.setPaymentMethodName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(basicHouseTrading.getNormalTransaction())){
            if (NumberUtils.isNumber(basicHouseTrading.getNormalTransaction())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseTrading.getNormalTransaction()));
                if (dataDic != null){
                    vo.setNormalTransactionName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(basicHouseTrading.getDescriptionContent())){
            if (NumberUtils.isNumber(basicHouseTrading.getDescriptionContent())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseTrading.getDescriptionContent()));
                if (dataDic != null){
                    vo.setDescriptionContentName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (basicHouseTrading.getDescriptionType() != null){
            dataDic = baseDataDicService.getDataDicById(basicHouseTrading.getDescriptionType());
            if (dataDic != null){
                vo.setDescriptionTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (StringUtils.isNotEmpty(basicHouseTrading.getTaxBurden())){
            if (NumberUtils.isNumber(basicHouseTrading.getTaxBurden())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseTrading.getTaxBurden()));
                if (dataDic != null){
                    vo.setTaxBurdenName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        return vo;
    }

}

package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.cases.dao.CaseBaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.dto.output.cases.CaseBaseHouseVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-12-3.
 */
@Service
public class CaseBaseHouseService {
    @Autowired
    private CaseBaseHouseDao caseBaseHouseDao;
    @Lazy
    @Autowired
    private CustomCaseMapper customCaseMapper;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public CaseBaseHouse getBaseHouseById(Integer id){
        return caseBaseHouseDao.getBaseHouseById(id);
    }

    public List<CaseBaseHouse> findCaseBaseHouseList(BigDecimal tradingUnitPriceStart, BigDecimal tradingUnitPriceEnd, Date tradingTimeStart, Date tradingTimeEnd, CaseBaseHouse caseBaseHouse) throws Exception {
        if (caseBaseHouse != null){
            if (StringUtils.isBlank(caseBaseHouse.getBlockName())){
                caseBaseHouse.setBlockName(null);
            }
            if (StringUtils.isBlank(caseBaseHouse.getStreet())){
                caseBaseHouse.setStreet(null);
            }
            if (StringUtils.isBlank(caseBaseHouse.getFullName())){
                caseBaseHouse.setFullName(null);
            }
            if (StringUtils.isBlank(caseBaseHouse.getProvince())){
                caseBaseHouse.setProvince(null);
            }
            if (StringUtils.isBlank(caseBaseHouse.getCity())){
                caseBaseHouse.setCity(null);
            }
            if (StringUtils.isBlank(caseBaseHouse.getDistrict())){
                caseBaseHouse.setDistrict(null);
            }
            if (caseBaseHouse.getTradingType() ==null){
                caseBaseHouse.setTradingType(null);
            }
            if (caseBaseHouse.getPracticalUse() ==null){
                caseBaseHouse.setPracticalUse(null);
            }
        }
        return customCaseMapper.findCaseBaseHouseList(tradingUnitPriceStart, tradingUnitPriceEnd, tradingTimeStart, tradingTimeEnd, caseBaseHouse);
    }

    public BootstrapTableVo getBootstrapTableVo(BigDecimal tradingUnitPriceStart, BigDecimal tradingUnitPriceEnd, Date tradingTimeStart, Date tradingTimeEnd, CaseBaseHouse caseBaseHouse)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBaseHouse> list = this.findCaseBaseHouseList(tradingUnitPriceStart, tradingUnitPriceEnd, tradingTimeStart, tradingTimeEnd, caseBaseHouse);
        List<CaseBaseHouseVo> vos= LangUtils.transform(list,o->getCaseBaseHouseVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public CaseBaseHouseVo getCaseBaseHouseVo(CaseBaseHouse caseBaseHouse){
        if(caseBaseHouse==null) return null;
        CaseBaseHouseVo caseBaseHouseVo=new CaseBaseHouseVo();
        BeanUtils.copyProperties(caseBaseHouse,caseBaseHouseVo);
        if(caseBaseHouse.getPracticalUse()!=null)
            caseBaseHouseVo.setPracticalUseName(baseDataDicService.getNameById(caseBaseHouse.getPracticalUse()));
        if(caseBaseHouse.getTradingType()!=null)
            caseBaseHouseVo.setTradingTypeName(baseDataDicService.getNameById(caseBaseHouse.getTradingType()));
        return caseBaseHouseVo;
    }

    /**
     * 新增数据
     *
     * @param caseBaseHouse
     */
    public void addBaseHouse(CaseBaseHouse caseBaseHouse) {
        caseBaseHouseDao.addBaseHouse(caseBaseHouse);
    }

    /**
     * 更新数据
     *
     * @param caseBaseHouse
     */
    public void updateBaseHouse(CaseBaseHouse caseBaseHouse) {
        caseBaseHouseDao.updateBaseHouse(caseBaseHouse);
    }

    /**
     * 获取数据列表
     *
     * @param caseBaseHouse
     * @return
     */
    public List<CaseBaseHouse> getBaseHouseList(CaseBaseHouse caseBaseHouse) {
        return caseBaseHouseDao.getBaseHouseList(caseBaseHouse);
    }

    /**
     * 删除数据by caseHouseId
     *
     * @param caseHouseId
     */
    public void deleteBaseHouseByHouseId(Integer caseHouseId) {
        CaseBaseHouse caseBaseHouse = new CaseBaseHouse();
        caseBaseHouse.setCaseHouseId(caseHouseId);
        List<CaseBaseHouse> houseList = getBaseHouseList(caseBaseHouse);
        if (!CollectionUtils.isEmpty(houseList)) {
            houseList.forEach(o -> caseBaseHouseDao.deleteBaseHouse(o.getId()));
        }
    }
}

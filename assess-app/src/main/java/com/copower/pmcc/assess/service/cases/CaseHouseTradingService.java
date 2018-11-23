package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseTradingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/13 17:46
 * @Description:房屋交易信息
 */
@Service
public class CaseHouseTradingService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CaseHouseTradingDao caseHouseTradingDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateCaseHouseTrading(CaseHouseTrading caseHouseTrading) {
        Integer id = null;
        if (caseHouseTrading.getId() == null || caseHouseTrading.getId().intValue() == 0) {
            id = caseHouseTradingDao.addCaseHouseTrading(caseHouseTrading);
            return id;
        }else {
            caseHouseTradingDao.updateCaseHouseTrading(caseHouseTrading);
            return null;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(CaseHouseTrading caseHouseTrading){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseTradingVo> vos = caseHouseTradingList(caseHouseTrading);
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseHouseTradingVo> caseHouseTradingList(CaseHouseTrading caseHouseTrading){
        List<CaseHouseTradingVo> vos = Lists.newArrayList();
        List<CaseHouseTrading> caseHouseTradings = caseHouseTradingDao.getCaseHouseTradingList(caseHouseTrading);
        if (!ObjectUtils.isEmpty(caseHouseTradings)){
            for (CaseHouseTrading oo :caseHouseTradings){
                vos.add(getCaseHouseTradingVo(oo));
            }
        }
        return vos;
    }

    public List<CaseHouseTrading> caseHouseTradingLists(CaseHouseTrading caseHouseTrading){
        return caseHouseTradingDao.getCaseHouseTradingList(caseHouseTrading);
    }

    public boolean deleteCaseHouseTrading(CaseHouseTrading caseHouseTrading){
        try {
            caseHouseTradingDao.removeCaseHouseTrading(caseHouseTrading);
            return true ;
        } catch (Exception e1) {
            logger.error("error:"+e1.getMessage());
            return  false;
        }
    }

    public CaseHouseTrading getCaseHouseTradingById(Integer id){
        return caseHouseTradingDao.getCaseHouseTradingById(id);
    }

    public CaseHouseTradingVo getCaseHouseTradingVo(CaseHouseTrading caseHouseTrading){
        CaseHouseTradingVo vo = new CaseHouseTradingVo();
        BeanUtils.copyProperties(caseHouseTrading,vo);
        BaseDataDic dataDic = null;
        if (caseHouseTrading.getTradingTime() != null){
            vo.setTradingTimeName(DateUtils.format(caseHouseTrading.getTradingTime()));
        }
        if (caseHouseTrading.getTradingType() != null){
            dataDic = baseDataDicService.getDataDicById(caseHouseTrading.getTradingType());
            if (dataDic != null){
                vo.setTradingTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (StringUtils.isNotEmpty(caseHouseTrading.getInformationType())){
            if (NumberUtils.isNumber(caseHouseTrading.getInformationType())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(caseHouseTrading.getInformationType()));
                if (dataDic != null){
                    vo.setInformationTypeName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(caseHouseTrading.getPaymentMethod())){
            if (NumberUtils.isNumber(caseHouseTrading.getPaymentMethod())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(caseHouseTrading.getPaymentMethod()));
                if (dataDic != null){
                    vo.setPaymentMethodName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(caseHouseTrading.getNormalTransaction())){
            if (NumberUtils.isNumber(caseHouseTrading.getNormalTransaction())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(caseHouseTrading.getNormalTransaction()));
                if (dataDic != null){
                    vo.setNormalTransactionName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(caseHouseTrading.getDescriptionContent())){
            if (NumberUtils.isNumber(caseHouseTrading.getDescriptionContent())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(caseHouseTrading.getDescriptionContent()));
                if (dataDic != null){
                    vo.setDescriptionContentName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (caseHouseTrading.getDescriptionType() != null){
            dataDic = baseDataDicService.getDataDicById(caseHouseTrading.getDescriptionType());
            if (dataDic != null){
                vo.setDescriptionTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (StringUtils.isNotEmpty(caseHouseTrading.getTaxBurden())){
            if (NumberUtils.isNumber(caseHouseTrading.getTaxBurden())){
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(caseHouseTrading.getTaxBurden()));
                if (dataDic != null){
                    vo.setTaxBurdenName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        vo.setInformationName(baseDataDicService.getNameById(org.apache.commons.lang3.math.NumberUtils.isNumber(caseHouseTrading.getInformation())?Integer.parseInt(caseHouseTrading.getInformation()):null));
        vo.setScopePropertyName(baseDataDicService.getNameById(org.apache.commons.lang3.math.NumberUtils.isNumber(caseHouseTrading.getScopeProperty())?Integer.parseInt(caseHouseTrading.getScopeProperty()):null));
        vo.setFinancingConditionsName(baseDataDicService.getNameById(org.apache.commons.lang3.math.NumberUtils.isNumber(caseHouseTrading.getFinancingConditions())?Integer.parseInt(caseHouseTrading.getFinancingConditions()):null));
        return vo;
    }
}

package com.copower.pmcc.assess.service.cases;

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
        List<CaseHouseTradingVo> vos = caseHouseTradingVoList(caseHouseTrading);
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseHouseTradingVo> caseHouseTradingVoList(CaseHouseTrading caseHouseTrading){
        List<CaseHouseTradingVo> vos = Lists.newArrayList();
        List<CaseHouseTrading> caseHouseTradings = caseHouseTradingDao.getCaseHouseTradingList(caseHouseTrading);
        if (!ObjectUtils.isEmpty(caseHouseTradings)){
            for (CaseHouseTrading oo :caseHouseTradings){
                vos.add(getCaseHouseTradingVo(oo));
            }
        }
        return vos;
    }

    public List<CaseHouseTrading> caseHouseTradingList(CaseHouseTrading caseHouseTrading){
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
        if (caseHouseTrading.getTradingTime() != null){
            vo.setTradingTimeName(DateUtils.format(caseHouseTrading.getTradingTime()));
        }
        vo.setTradingTypeName(baseDataDicService.getNameById(caseHouseTrading.getTradingType()));
        vo.setPaymentMethodName(baseDataDicService.getNameById(caseHouseTrading.getPaymentMethod()));
        vo.setNormalTransactionName(baseDataDicService.getNameById(caseHouseTrading.getNormalTransaction()));
        vo.setDescriptionTypeName(baseDataDicService.getNameById(caseHouseTrading.getDescriptionType()));
        vo.setTaxBurdenName(baseDataDicService.getNameById(caseHouseTrading.getTaxBurden()));
        vo.setInformationTypeName(baseDataDicService.getNameById(caseHouseTrading.getInformationType()));
        vo.setInformationCategoryName(baseDataDicService.getNameById(caseHouseTrading.getInformationCategory()));
        vo.setScopePropertyName(baseDataDicService.getNameById(caseHouseTrading.getScopeProperty()));
        return vo;
    }
}

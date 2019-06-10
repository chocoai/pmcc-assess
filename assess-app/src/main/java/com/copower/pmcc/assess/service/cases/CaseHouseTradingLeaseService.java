package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseTradingLeaseDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingLease;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingLeaseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
 * @Date: 2018/9/13 17:52
 * @Description:房屋出租
 */
@Service
public class CaseHouseTradingLeaseService {
    @Autowired
    private CaseHouseTradingLeaseDao caseHouseTradingLeaseDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateCaseHouseTradingLease(CaseHouseTradingLease caseHouseTradingLease) {
        Integer id = null;

        if (caseHouseTradingLease.getId() == null || caseHouseTradingLease.getId().intValue() == 0) {
            id = caseHouseTradingLeaseDao.addCaseHouseTradingLease(caseHouseTradingLease);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class), id);
            return id;
        }else {
            caseHouseTradingLeaseDao.updateCaseHouseTradingLease(caseHouseTradingLease);
            return null;
        }
    }

    public void upgradeVersion(CaseHouseTradingLease po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            Integer id = caseHouseTradingLeaseDao.addCaseHouseTradingLease(po);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class), id);
        }else {
            CaseHouseTradingLease oo = getCaseHouseTradingLeaseById(po.getId());
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            Integer id = caseHouseTradingLeaseDao.addCaseHouseTradingLease(oo);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class), id);
        }
    }

    public BootstrapTableVo getBootstrapTableVo(CaseHouseTradingLease caseHouseTradingLease,String type){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseTradingLeaseVo> vos = caseHouseTradingLeaseList(caseHouseTradingLease,type);
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseHouseTradingLeaseVo> caseHouseTradingLeaseList(CaseHouseTradingLease caseHouseTradingLease,String type){
        List<CaseHouseTradingLeaseVo> vos = Lists.newArrayList();
        List<CaseHouseTradingLease> caseHouseTradingLeases = caseHouseTradingLeaseDao.getCaseHouseTradingLeaseList(caseHouseTradingLease);
        if (!ObjectUtils.isEmpty(caseHouseTradingLeases)){
            for (CaseHouseTradingLease oo :caseHouseTradingLeases){
                CaseHouseTradingLeaseVo vo = getCaseHouseTradingLeaseVo(oo);
                vo.setTradingType(type);
                vos.add(vo);
            }
        }
        return vos;
    }

    public boolean deleteCaseHouseTradingLease(CaseHouseTradingLease caseHouseTradingLease){
        try {
            caseHouseTradingLeaseDao.removeCaseHouseTradingLease(caseHouseTradingLease);
            return true ;
        } catch (Exception e1) {
            logger.error("error:"+e1.getMessage());
            return  false;
        }
    }

    public CaseHouseTradingLease getCaseHouseTradingLeaseById(Integer id){
        return caseHouseTradingLeaseDao.getCaseHouseTradingLeaseById(id);
    }

    public CaseHouseTradingLeaseVo getCaseHouseTradingLeaseVo(CaseHouseTradingLease caseHouseTradingLease){
        CaseHouseTradingLeaseVo vo = new CaseHouseTradingLeaseVo();
        BeanUtils.copyProperties(caseHouseTradingLease,vo);
        if (caseHouseTradingLease.getRentPaymentTimeEnd() != null){
            vo.setRentPaymentTimeEndName(DateUtils.format(caseHouseTradingLease.getRentPaymentTimeEnd(),DateUtils.DATE_CHINESE_PATTERN));
        }
        if (caseHouseTradingLease.getRentPaymentTimeStart() != null){
            vo.setRentPaymentTimeStartName(DateUtils.format(caseHouseTradingLease.getRentPaymentTimeStart() , DateUtils.DATE_CHINESE_PATTERN));
        }
        return vo;
    }
}

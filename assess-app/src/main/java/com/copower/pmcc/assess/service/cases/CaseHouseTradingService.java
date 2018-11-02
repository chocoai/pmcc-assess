package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseTradingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
 * @Date: 2018/9/13 17:46
 * @Description:房屋交易信息
 */
@Service
public class CaseHouseTradingService {
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
            caseHouseTrading.setCreator(commonService.thisUserAccount());
            id = caseHouseTradingDao.addCaseHouseTrading(caseHouseTrading);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouseTrading.class), id);
            caseHouseTrading.setId(id);
            return id;
        }else {
            caseHouseTradingDao.updateCaseHouseTrading(caseHouseTrading);
            return null;
        }
    }

    public Integer upgradeVersion(CaseHouseTrading caseHouseTrading)throws Exception{
        Integer id = null;
        if (caseHouseTrading.getId() == null || caseHouseTrading.getId().intValue() == 0) {
            caseHouseTrading.setCreator(commonService.thisUserAccount());
            caseHouseTrading.setVersion(0);
            id = caseHouseTradingDao.addCaseHouseTrading(caseHouseTrading);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouseTrading.class), id);
            caseHouseTrading.setId(id);
            return id;
        }else {
            CaseHouseTrading oo = caseHouseTradingDao.getCaseHouseTradingById(caseHouseTrading.getId());
            if (oo != null){
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseHouseTrading, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            id = caseHouseTradingDao.addCaseHouseTrading(caseHouseTrading);
            caseHouseTrading.setId(id);
            return id;
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
        return vo;
    }
}

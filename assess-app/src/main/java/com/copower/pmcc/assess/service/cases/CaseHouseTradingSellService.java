package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseTradingSellDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSell;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingSellVo;
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
 * @Date: 2018/9/13 17:53
 * @Description:房屋出售
 */
@Service
public class CaseHouseTradingSellService {
    @Autowired
    private CaseHouseTradingSellDao caseHouseTradingSellDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateCaseHouseTradingSell(CaseHouseTradingSell caseHouseTradingSell) {
        Integer id = null;
        if (caseHouseTradingSell == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseHouseTradingSell != null && caseHouseTradingSell.getId().intValue() != 0) {
            caseHouseTradingSell.setCreator(commonService.thisUserAccount());
            caseHouseTradingSell.setVersion(0);
            id = caseHouseTradingSellDao.addCaseHouseTradingSell(caseHouseTradingSell);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseHouseTradingSell.class), id);
            return id;
        }else {
            CaseHouseTradingSell oo = caseHouseTradingSellDao.getCaseHouseTradingSellById(caseHouseTradingSell.getId());
            if (oo != null){
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
                caseHouseTradingSell.setVersion(oo.getVersion()+1);
            }
            caseHouseTradingSellDao.updateCaseHouseTradingSell(caseHouseTradingSell);
            return null;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(CaseHouseTradingSell caseHouseTradingSell){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseTradingSellVo> vos = caseHouseTradingSellList(caseHouseTradingSell);
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseHouseTradingSellVo> caseHouseTradingSellList(CaseHouseTradingSell caseHouseTradingSell){
        List<CaseHouseTradingSellVo> vos = Lists.newArrayList();
        List<CaseHouseTradingSell> caseHouseTradingSells = caseHouseTradingSellDao.getCaseHouseTradingSellList(caseHouseTradingSell);
        if (!ObjectUtils.isEmpty(caseHouseTradingSells)){
            for (CaseHouseTradingSell oo :caseHouseTradingSells){
                vos.add(getCaseHouseTradingSellVo(oo));
            }
        }
        return vos;
    }

    public boolean deleteCaseHouseTradingSell(CaseHouseTradingSell caseHouseTradingSell){
        try {
            caseHouseTradingSellDao.removeCaseHouseTradingSell(caseHouseTradingSell);
            return true ;
        } catch (Exception e1) {
            logger.error("error:"+e1.getMessage());
            return  false;
        }
    }

    public CaseHouseTradingSell getCaseHouseTradingSellById(Integer id){
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseHouseTradingSellDao.getCaseHouseTradingSellById(id);
    }

    public CaseHouseTradingSellVo getCaseHouseTradingSellVo(CaseHouseTradingSell caseHouseTradingSell){
        CaseHouseTradingSellVo vo = new CaseHouseTradingSellVo();
        BeanUtils.copyProperties(caseHouseTradingSell,vo);
        return vo;
    }
}

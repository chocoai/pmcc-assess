package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseTradingSellDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingSellVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:38
 * @Description:
 */
@Service
public class BasicHouseTradingSellService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseTradingSellDao basicHouseTradingSellDao;

    public Integer saveAndUpdateBasicHouseTradingSell(BasicHouseTradingSell basicHouseTradingSell, boolean updateNull) throws Exception {
        if (basicHouseTradingSell.getId() == null || basicHouseTradingSell.getId().intValue() == 0) {
            basicHouseTradingSell.setCreator(commonService.thisUserAccount());
            return basicHouseTradingSellDao.addBasicHouseTradingSell(basicHouseTradingSell);
        } else {
            if (updateNull) {
                BasicHouseTradingSell houseTradingSell = basicHouseTradingSellDao.getBasicHouseTradingSellById(basicHouseTradingSell.getId());
                if (houseTradingSell != null) {
                    basicHouseTradingSell.setBisDelete(houseTradingSell.getBisDelete());
                    basicHouseTradingSell.setCreator(houseTradingSell.getCreator());
                    basicHouseTradingSell.setGmtCreated(houseTradingSell.getGmtCreated());
                    basicHouseTradingSell.setGmtModified(DateUtils.now());
                }
            }
            basicHouseTradingSellDao.updateBasicHouseTradingSell(basicHouseTradingSell, updateNull);
            return basicHouseTradingSell.getId();
        }
    }

    public BasicHouseTradingSell getByBasicHouseTradingSellId(Integer id) throws Exception {
        return basicHouseTradingSellDao.getBasicHouseTradingSellById(id);
    }

    public boolean deleteBasicHouseTradingSell(Integer id) throws Exception {
        return basicHouseTradingSellDao.deleteBasicHouseTradingSell(id);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseTradingSell basicHouseTradingSell, String type) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseTradingSellVo> vos = Lists.newArrayList();
        List<BasicHouseTradingSell> basicHouseTradingSellList = basicHouseTradingSellDao.basicHouseTradingSellList(basicHouseTradingSell);
        if (!ObjectUtils.isEmpty(basicHouseTradingSellList)) {
            for (BasicHouseTradingSell oo : basicHouseTradingSellList) {
                BasicHouseTradingSellVo sellVo = getBasicHouseTradingSellVo(oo);
                sellVo.setTradingType(type);
                vos.add(sellVo);
            }
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BasicHouseTradingSellVo getBasicHouseTradingSellVo(BasicHouseTradingSell basicHouseTradingSell) {
        BasicHouseTradingSellVo vo = new BasicHouseTradingSellVo();
        BeanUtils.copyProperties(basicHouseTradingSell, vo);
        if (basicHouseTradingSell.getInstalmentPeriodStart() != null) {
            vo.setInstalmentPeriodStartName(DateUtils.format(basicHouseTradingSell.getInstalmentPeriodStart()));
        }
        if (basicHouseTradingSell.getInstalmentPeriodEnd() != null) {
            vo.setInstalmentPeriodEndName(DateUtils.format(basicHouseTradingSell.getInstalmentPeriodEnd()));
        }
        return vo;
    }

    public List<BasicHouseTradingSell> basicHouseTradingSells(BasicHouseTradingSell basicHouseTradingSell) throws Exception {
        return basicHouseTradingSellDao.basicHouseTradingSellList(basicHouseTradingSell);
    }


    public List<BasicHouseTradingSell> basicHouseTradingSellsGetByTradingId(Integer tradingId) throws Exception {
        BasicHouseTradingSell basicHouseTradingSell = new BasicHouseTradingSell();
        basicHouseTradingSell.setTradingId(tradingId);
        return basicHouseTradingSellDao.basicHouseTradingSellList(basicHouseTradingSell);
    }

}

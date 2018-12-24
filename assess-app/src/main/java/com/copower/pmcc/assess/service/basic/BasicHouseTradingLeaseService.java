package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicHouseTradingLeaseDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingLeaseVo;
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
 * @Date: 2018/11/2 10:37
 * @Description:
 */
@Service
public class BasicHouseTradingLeaseService {

    @Autowired
    private BasicHouseTradingLeaseDao basicHouseTradingLeaseDao;
    @Autowired
    private CommonService commonService;

    public Integer saveAndUpdateBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease)throws Exception{
        if (basicHouseTradingLease.getId()==null || basicHouseTradingLease.getId().intValue()==0){
            basicHouseTradingLease.setCreator(commonService.thisUserAccount());
            return basicHouseTradingLeaseDao.saveBasicHouseTradingLease(basicHouseTradingLease);
        }else {
            basicHouseTradingLeaseDao.updateBasicHouseTradingLease(basicHouseTradingLease);
            return null;
        }
    }

    public boolean deleteBasicHouseTradingLease(Integer id)throws Exception{
        return basicHouseTradingLeaseDao.deleteBasicHouseTradingLease(id);
    }

    public boolean deleteBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease)throws Exception{
        return basicHouseTradingLeaseDao.deleteBasicHouseTradingLease(basicHouseTradingLease);
    }

    public BasicHouseTradingLease getByBasicHouseTradingLeaseId(Integer id)throws Exception{
        return basicHouseTradingLeaseDao.getBasicHouseTradingLeaseById(id);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseTradingLease basicHouseTradingLease,String type)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseTradingLeaseVo> vos = Lists.newArrayList();
        List<BasicHouseTradingLease> basicHouseTradingLeases = basicHouseTradingLeaseDao.basicHouseTradingLeaseList(basicHouseTradingLease);
        if (!ObjectUtils.isEmpty(basicHouseTradingLeases)){
            basicHouseTradingLeases.forEach(oo -> {
                BasicHouseTradingLeaseVo leaseVo = getBasicHouseTradingLease(oo);
                leaseVo.setTradingType(type);
                vos.add(leaseVo);
            });
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BasicHouseTradingLeaseVo getBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease){
        BasicHouseTradingLeaseVo vo = new BasicHouseTradingLeaseVo();
        BeanUtils.copyProperties(basicHouseTradingLease,vo);
        if (basicHouseTradingLease.getRentPaymentTimeStart() != null){
            vo.setRentPaymentTimeStartName(DateUtils.format(basicHouseTradingLease.getRentPaymentTimeStart()));
        }
        if (basicHouseTradingLease.getRentPaymentTimeEnd() != null){
            vo.setRentPaymentTimeEndName(DateUtils.format(basicHouseTradingLease.getRentPaymentTimeEnd()));
        }
        return vo;
    }

    public List<BasicHouseTradingLease> basicHouseTradingLeaseList(BasicHouseTradingLease basicHouseTradingLease)throws Exception{
        return basicHouseTradingLeaseDao.basicHouseTradingLeaseList(basicHouseTradingLease);
    }

}

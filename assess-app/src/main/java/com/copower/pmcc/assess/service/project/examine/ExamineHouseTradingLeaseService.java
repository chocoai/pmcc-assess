package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseTradingLeaseDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLease;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseTradingSellAndLeaseVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/30 11:33
 * @Description:房屋出租
 */
@Service
public class ExamineHouseTradingLeaseService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ExamineHouseTradingLeaseDao examineHouseTradingLeaseDao;

    public boolean addExamineHouseTradingLease(ExamineHouseTradingLease examineHouseTradingLease) {
        examineHouseTradingLease.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseTradingLease.getDeclareId())) {
            examineHouseTradingLease.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseTradingLease.getExamineType())) {
            examineHouseTradingLease.setExamineType(0);
        }
        return examineHouseTradingLeaseDao.addExamineHouseTradingLease(examineHouseTradingLease);
    }

    public List<ExamineHouseTradingLease> examineHouseTradingLeaseList(ExamineHouseTradingLease examineHouseTradingLease) {
        return examineHouseTradingLeaseDao.examineHouseTradingLeaseList(examineHouseTradingLease);
    }

    public boolean updateExamineHouseTradingLease(ExamineHouseTradingLease examineHouseTradingLease) {
        return examineHouseTradingLeaseDao.updateExamineHouseTradingLease(examineHouseTradingLease);
    }

    public BootstrapTableVo getExamineHouseTradingLeaseLists(ExamineHouseTradingLease examineHouseTradingLease,String type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseTradingSellAndLeaseVo> vos = Lists.newArrayList();
        List<ExamineHouseTradingLease> examineHouseTradingLeases = examineHouseTradingLeaseList(examineHouseTradingLease);
        examineHouseTradingLeases.stream().forEach( oo -> {
            ExamineHouseTradingSellAndLeaseVo examineHouseTradingSellAndLeaseVo = getExamineHouseTradingSellAndLeaseVo(oo);
            examineHouseTradingSellAndLeaseVo.setTradingType(type);
            vos.add(examineHouseTradingSellAndLeaseVo);
        });
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseTradingSellAndLeaseVo>() : vos);
        return vo;
    }

    public boolean removeExamineHouseTradingLease(Integer id) {
        return examineHouseTradingLeaseDao.removeExamineHouseTradingLease(id);
    }

    public ExamineHouseTradingSellAndLeaseVo getExamineHouseTradingSellAndLeaseVo(ExamineHouseTradingLease examineHouseTradingSell){
        ExamineHouseTradingSellAndLeaseVo vo = new ExamineHouseTradingSellAndLeaseVo();
        BeanUtils.copyProperties(examineHouseTradingSell,vo);
        if (examineHouseTradingSell.getRentPaymentTimeStart() != null){
            vo.setRentPaymentTimeStartName(DateHelp.getDateHelp().printDate(examineHouseTradingSell.getRentPaymentTimeStart()));
        }
        if (examineHouseTradingSell.getRentPaymentTimeEnd() != null){
            vo.setRentPaymentTimeEndName(DateHelp.getDateHelp().printDate(examineHouseTradingSell.getRentPaymentTimeEnd()));
        }
        return vo;
    }
}

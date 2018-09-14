package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseTradingSellDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSell;
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
 * @Date: 2018/7/30 11:31
 * @Description:房屋出售
 */
@Service
public class ExamineHouseTradingSellService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ExamineHouseTradingSellDao examineHouseTradingSellDao;

    public boolean addExamineHouseTradingSell(ExamineHouseTradingSell examineHouseTradingSell){
        if (ObjectUtils.isEmpty(examineHouseTradingSell.getDeclareId())) {
            examineHouseTradingSell.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseTradingSell.getExamineType())) {
            examineHouseTradingSell.setExamineType(0);
        }
        examineHouseTradingSell.setCreator(commonService.thisUserAccount());
        return examineHouseTradingSellDao.addExamineHouseTradingSell(examineHouseTradingSell);
    }

    public List<ExamineHouseTradingSell> examineHouseTradingSells(ExamineHouseTradingSell examineHouseTradingSell){
        return examineHouseTradingSellDao.examineHouseTradingSells(examineHouseTradingSell);
    }

    public BootstrapTableVo getExamineHouseTradingSellLists(ExamineHouseTradingSell examineHouseTradingSell,String type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseTradingSell> examineHouseTradingLeaseList = examineHouseTradingSells(examineHouseTradingSell);
        List<ExamineHouseTradingSellAndLeaseVo> vos = Lists.newArrayList();
        examineHouseTradingLeaseList.parallelStream().forEach(examineHouseTradingSell1 -> {
            ExamineHouseTradingSellAndLeaseVo examineHouseTradingSellAndLeaseVo = getExamineHouseTradingSellAndLeaseVo(examineHouseTradingSell1);
            examineHouseTradingSellAndLeaseVo.setTradingType(type);
            vos.add(examineHouseTradingSellAndLeaseVo);
        });
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseTradingSellAndLeaseVo>() : vos);
        return vo;
    }

    public boolean removeExamineHouseTradingSell(Integer id){
        return examineHouseTradingSellDao.removeExamineHouseTradingSell(id);
    }

    public boolean updateExamineHouseTradingSell(ExamineHouseTradingSell examineHouseTradingSell){
        return examineHouseTradingSellDao.updateExamineHouseTradingSell(examineHouseTradingSell);
    }

    public ExamineHouseTradingSellAndLeaseVo getExamineHouseTradingSellAndLeaseVo(ExamineHouseTradingSell examineHouseTradingSell){
        ExamineHouseTradingSellAndLeaseVo vo = new ExamineHouseTradingSellAndLeaseVo();
        BeanUtils.copyProperties(examineHouseTradingSell,vo);
        if (examineHouseTradingSell.getInstalmentPeriodStart() != null){
            vo.setInstalmentPeriodStartName(DateHelp.getDateHelp().printDate(examineHouseTradingSell.getInstalmentPeriodStart()));
        }
        if (examineHouseTradingSell.getInstalmentPeriodEnd() != null){
            vo.setInstalmentPeriodEndName(DateHelp.getDateHelp().printDate(examineHouseTradingSell.getInstalmentPeriodEnd()));
        }
        return vo;
    }
}

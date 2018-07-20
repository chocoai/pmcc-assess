package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineMatchingFinanceDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinance;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingFinanceVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/20 17:10
 * @Description:金融服务
 */
@Service
public class ExamineMatchingFinanceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingFinanceDao examineMatchingFinanceDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public ExamineMatchingFinance getExamineMatchingFinanceById(Integer id) {
        return examineMatchingFinanceDao.getMatchingFinanceById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineMatchingFinance
     * @return
     */
    public List<ExamineMatchingFinance> getExamineMatchingFinanceList(ExamineMatchingFinance examineMatchingFinance) {
        return examineMatchingFinanceDao.getMatchingFinanceList(examineMatchingFinance);
    }

    public BootstrapTableVo getExamineMatchingFinanceLists(ExamineMatchingFinance examineMatchingFinance) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingFinanceVo> vos = Lists.newArrayList();
        getExamineMatchingFinanceList(examineMatchingFinance).stream().forEach(oo -> vos.add(getExamineMatchingFinanceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingFinanceVo>() : vos);
        return vo;
    }

    public ExamineMatchingFinanceVo getExamineMatchingFinanceVo(ExamineMatchingFinance examineMatchingFinance) {
        ExamineMatchingFinanceVo vo = new ExamineMatchingFinanceVo();
        BeanUtils.copyProperties(examineMatchingFinance, vo);
        if (!StringUtils.isEmpty(examineMatchingFinance.getServiceContent())) {
            if (org.apache.commons.lang.StringUtils.isNumeric(examineMatchingFinance.getServiceContent())) {
                vo.setServiceContentName(getValue(AssessExamineTaskConstant.ESTATE_FINANCE_SERVICE_CONTENT, Integer.parseInt(examineMatchingFinance.getServiceContent())));
            }
        }
        if (examineMatchingFinance.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_FINANCE_CATEGORY, examineMatchingFinance.getCategory()));
        }
        if (examineMatchingFinance.getNature() != null) {
            vo.setNatureName(getValue(AssessExamineTaskConstant.ESTATE_FINANCE_NATURE, examineMatchingFinance.getNature()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param examineMatchingFinance
     * @return
     */
    public boolean addExamineMatchingFinance(ExamineMatchingFinance examineMatchingFinance) {
        examineMatchingFinance.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingFinance.getDeclareId())) {
            examineMatchingFinance.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingFinance.getExamineType())) {
            examineMatchingFinance.setExamineType(0);
        }
        return examineMatchingFinanceDao.addMatchingFinance(examineMatchingFinance);
    }

    /**
     * 编辑
     *
     * @param examineMatchingFinance
     * @return
     */
    public boolean updateExamineMatchingFinance(ExamineMatchingFinance examineMatchingFinance) {
        return examineMatchingFinanceDao.updateMatchingFinance(examineMatchingFinance);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingFinance(Integer id) {
        return examineMatchingFinanceDao.deleteMatchingFinance(id);
    }

}

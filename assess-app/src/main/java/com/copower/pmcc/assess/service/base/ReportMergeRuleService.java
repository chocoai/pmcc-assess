package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.dao.ReportMergeRuleDao;
import com.copower.pmcc.assess.dal.dao.ReportTemplateDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ReportMergeRule;
import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dto.output.report.ReportMergeRuleVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-3-5.
 */
@Service
public class ReportMergeRuleService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ReportMergeRuleDao reportMergeRuleDao;
    @Autowired
    private ReportTemplateDao reportTemplateDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    /**
     * 获取合并规则信息列表
     *
     * @return
     */
    public BootstrapTableVo getMergeRuleList(Integer reportType) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ReportMergeRule reportMergeRule = new ReportMergeRule();
        reportMergeRule.setReportType(reportType);
        List<ReportMergeRule> list = reportMergeRuleDao.getListObject(reportMergeRule);
        List<ReportMergeRuleVo> voList = getReportMergeRuleVos(list);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<ReportMergeRuleVo>() : voList);
        return bootstrapTableVo;
    }



    public List<ReportMergeRuleVo> getReportMergeRuleVos(List<ReportMergeRule> reportMergeRules) {
        if(CollectionUtils.isEmpty(reportMergeRules)) return null;
        List<ReportTemplate> reportTemplates = reportTemplateDao.getListObject(LangUtils.transform(reportMergeRules, p -> p.getTemplateId()));
        return LangUtils.transform(reportMergeRules, reportMergeRule -> {
            ReportMergeRuleVo reportMergeRuleVo = new ReportMergeRuleVo();
            BeanUtils.copyProperties(reportMergeRule, reportMergeRuleVo);
            if (reportMergeRule.getReportType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(reportMergeRule.getReportType());
                reportMergeRuleVo.setReportTypeName(baseDataDic.getName());
            }
            if (reportMergeRule.getTemplateId() != null) {
                reportTemplates.forEach(p->{
                    if(p.getId().equals(reportMergeRule.getTemplateId())){
                        reportMergeRuleVo.setTemplateName(p.getName());
                    }
                });
            }
            return reportMergeRuleVo;
        });

    }

    /**
     * 保存模板数据
     *
     * @param reportMergeRuleDto
     */
    public void saveMergeRule(ReportMergeRule reportMergeRuleDto) throws BusinessException {
        if (reportMergeRuleDto.getId() != null && reportMergeRuleDto.getId() > 0) {
            if (!reportMergeRuleDao.updateObject(reportMergeRuleDto)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            reportMergeRuleDto.setCreator(processControllerComponent.getThisUser());
            if (!reportMergeRuleDao.addObject(reportMergeRuleDto)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }

    /**
     * 删除模板信息
     *
     * @param id
     */
    public void deleteMergeRule(Integer id) {
        reportMergeRuleDao.deleteObject(id);
    }

}

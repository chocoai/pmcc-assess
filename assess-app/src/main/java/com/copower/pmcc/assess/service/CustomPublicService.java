package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyEstateTimes;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomPublicMapper;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomPublicService {
    @Autowired
    private CustomPublicMapper customPublicMapper;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    public BootstrapTableVo getSurveyEstateTimesList(String userAccount) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        if (StringUtils.isBlank(userAccount)) return bootstrapTableVo;
        Integer projectPhaseId = 0;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE);
        if (projectPhase != null)
            projectPhaseId = projectPhase.getId();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomSurveyEstateTimes> surveyEstateTimesList = customPublicMapper.getSurveyEstateTimesList(userAccount, projectPhaseId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(surveyEstateTimesList);
        return bootstrapTableVo;
    }
}

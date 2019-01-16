package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;

/**
 * Created by kings on 2019-1-16.
 */
public class GenerateBaseDataService {
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private SchemeAreaGroupService schemeAreaGroupService;

    private Integer projectId;
    private Integer areaId;
    private SchemeAreaGroup schemeAreaGroup;

    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(Integer projectId, Integer areaId) {
        this.projectId = projectId;
        this.areaId = areaId;
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
    }

    /**
     * 获取区域信息
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        if (schemeAreaGroup != null) return schemeAreaGroup;
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaId);
        this.schemeAreaGroup = schemeAreaGroup;
        return schemeAreaGroup;
    }
}

package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;

/**
 * Created by zch on 2019/7/9.
 * 假设开发法
 */
public class GenerateMdDevelopmentService {

    private MdDevelopmentVo mdDevelopmentVo;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;

    private Integer areaId ;
    private SchemeInfo schemeInfo;
    private Integer projectId ;

    private SchemeJudgeObjectService schemeJudgeObjectService;
    private DeclareRecordService declareRecordService;
    private BaseDataDicService baseDataDicService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private SchemeInfoService schemeInfoService;

    public GenerateMdDevelopmentService(Integer projectId, SchemeInfo schemeInfo, Integer areaId) {
        this.projectId = projectId;
        this.schemeInfo = schemeInfo;
        this.areaId = areaId;
    }
}

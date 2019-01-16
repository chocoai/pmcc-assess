package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kings on 2019-1-16.
 */
public class GenerateBaseDataService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //spring bean
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private ProjectNumberRecordService projectNumberRecordService;
    private BaseReportService baseReportService;
    private ProjectInfoService projectInfoService;

    //构造器必须传入的参数
    private Integer projectId;
    private Integer areaId;
    private Integer baseReportTemplateId;

    //中间变量
    private SchemeAreaGroup schemeAreaGroup;
    private BaseReportTemplate baseReportTemplate;
    private ProjectInfoVo projectInfo;

    //===========================================获取的值===============================
    //文号
    private String wordNumber;
    //委托人
    private String principal;

    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(Integer projectId, Integer areaId, Integer baseReportTemplateId) {
        this.projectId = projectId;
        this.areaId = areaId;
        this.baseReportTemplateId = baseReportTemplateId;
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.projectNumberRecordService = SpringContextUtils.getBean(ProjectNumberRecordService.class);
        this.baseReportService = SpringContextUtils.getBean(BaseReportService.class);
        this.projectInfoService = SpringContextUtils.getBean(ProjectInfoService.class);
    }

    /**
     * 获取区域信息
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        if (schemeAreaGroup != null) {
            return schemeAreaGroup;
        }
        SchemeAreaGroup areaGroup = schemeAreaGroupService.get(areaId);
        this.schemeAreaGroup = areaGroup;
        return schemeAreaGroup;
    }

    /**
     * 获取文号
     *
     * @return
     */
    public String getWordNumber() {
        if (StringUtils.isEmpty(wordNumber)) {
            try {
                String number = projectNumberRecordService.getReportNumber(this.getProjectId(), areaId, this.getBaseReportTemplate().getReportType());
                this.wordNumber = number;
            } catch (BusinessException e) {
                logger.error("获取文号异常", e);
            }

        }
        return wordNumber;
    }

    public BaseReportTemplate getBaseReportTemplate() {
        if (baseReportTemplate == null) {
            BaseReportTemplate reportTemplate = baseReportService.getBaseReportTemplateById(baseReportTemplateId);
            this.baseReportTemplate = reportTemplate;
        }
        return baseReportTemplate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 委托人
     * @return
     */
    public String getPrincipal() {
        if (StringUtils.isBlank(principal)) {
            String principalStr = StringUtils.isNotBlank(getProjectInfo().getConsignorVo().getCsName()) ? getProjectInfo().getConsignorVo().getCsName() : getProjectInfo().getConsignorVo().getCsEntrustmentUnit();
            this.principal = principalStr;
        }
        return principal;
    }

    /**
     * 获取项目info
     * @return
     */
    public ProjectInfoVo getProjectInfo() {
        if (projectInfo == null) {
            ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(getProjectId()));
            this.projectInfo = projectInfoVo;
        }
        return projectInfo;
    }
}

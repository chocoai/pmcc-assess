package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.method.MdCostAndDevelopmentOtherService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "假设开发法成果")
public class ProjectTaskDevelopmentAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeSupportInfoService schemeSupportInfoService;
    @Autowired
    private MdCostAndDevelopmentOtherService mdCostAndDevelopmentOtherService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", "", 0, "0", "");
        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails, projectInfo, AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        mdCostAndDevelopmentOtherService.removePid();
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentIndex", processInsId, boxId, taskId, agentUserAccount);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        setViewParam(projectPlanDetails, modelAndView);
        mdCostAndDevelopmentOtherService.removePid();
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdDevelopment mdDevelopment = new MdDevelopment();
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null;
        Integer id = 0;
        Integer pid = 0;
        String keyMdDevelopment = null;
        JSONObject jsonObject = JSON.parseObject(formData);
        List<SchemeSupportInfo> supportInfoList = null;
        MdDevelopmentHypothesis mdDevelopmentHypothesis = null;
        MdDevelopmentArchitectural mdDevelopmentArchitectural = null;
        String jsonContent = null;
        id = mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);

        //解析实体 ,并且对json 进行一些处理
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
                //必须初始化,否则下面判定失败
                jsonContent = null;
            }
            jsonContent = jsonObject.getString("mdDevelopmentHypothesis");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentHypothesis = JSONObject.parseObject(jsonContent, MdDevelopmentHypothesis.class);
                mdDevelopmentHypothesis.setJsonContent(JSON.toJSONString(jsonContent));
                //必须初始化,否则下面判定失败
                jsonContent = null;
            }
            jsonContent = jsonObject.getString("mdDevelopmentArchitectural");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentArchitectural = JSONObject.parseObject(jsonContent, MdDevelopmentArchitectural.class);
                mdDevelopmentArchitectural.setJsonContent(JSON.toJSONString(jsonContent));
            }
            //确定假设开发法具体选择的哪一个方法来测算的
            keyMdDevelopment = jsonObject.getString("mdDevelopment");
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyMdDevelopment)){
                if (Objects.equal("MdDevelopmentArchitectural",keyMdDevelopment)){
                    if (mdDevelopmentArchitectural != null){
                        mdDevelopment.setId(id);
                        mdDevelopment.setPrice(new BigDecimal(mdDevelopmentArchitectural.getEstimateunitpricelandc33()));
                        mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
                        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
                    }
                }
                if (Objects.equal("MdDevelopmentHypothesis",keyMdDevelopment)){
                    if (mdDevelopmentHypothesis != null){
                        mdDevelopment.setId(id);
                        mdDevelopment.setPrice(new BigDecimal(mdDevelopmentHypothesis.getEstimateunitpricelandc33()));
                        mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
                        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
                    }
                }
            }
        } catch (Exception e1) {
            //不需要抛出异常
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));
        }

        //处理评估方法
        if (!ObjectUtils.isEmpty(supportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : supportInfoList) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        //处理假设开发法 在建工程
        if (!ObjectUtils.isEmpty(mdDevelopmentHypothesis)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdDevelopmentHypothesis.class.getSimpleName(), 0);
            if (mdCostAndDevelopmentOther != null) {
                //存入从表id
                mdDevelopmentHypothesis.setEngineeringId(mdCostAndDevelopmentOther.getId());
            }
            //存入上级主表id
            mdDevelopmentHypothesis.setPid(id);
            pid = mdDevelopmentService.saveAndUpdateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
            //处理从表
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);
                mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }

        //处理假设开发法 土地
        if (!ObjectUtils.isEmpty(mdDevelopmentArchitectural)) {
            mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdDevelopmentArchitectural.class.getSimpleName(), 0);
            if (mdCostAndDevelopmentOther != null) {
                //存入从表id
                mdDevelopmentArchitectural.setEngineeringId(mdCostAndDevelopmentOther.getId());
            }
            //存入上级主表id
            mdDevelopmentArchitectural.setPid(id);
            pid = mdDevelopmentService.saveAndUpdateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
            //处理从表
            if (mdCostAndDevelopmentOther != null) {
                mdCostAndDevelopmentOther.setPid(pid);
                mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
            }
        }

        //处理评估方案中的各个评估方法
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setMethodType(AssessDataDicKeyConstant.MD_HYPOTHESIS);
        schemeInfo.setMethodDataId(id);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        JSONObject jsonObject = JSON.parseObject(formData);
        String keyMdDevelopment = null;
        List<SchemeSupportInfo> supportInfoList = null;
        MdDevelopmentHypothesis mdDevelopmentHypothesis = null;
        MdDevelopmentArchitectural mdDevelopmentArchitectural = null;
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = null ;
        String jsonContent = null;
        //(id至少会有一个实体含有 否则保存不会提交成功)
        Integer id = 0;
        Integer pid = 0;

        //解析实体 ,并且对json 进行一些处理
        try {
            jsonContent = jsonObject.getString("supportInfoList");
            if (!StringUtils.isEmpty(jsonContent)) {
                supportInfoList = JSONObject.parseArray(jsonContent, SchemeSupportInfo.class);
                //必须初始化,否则下面判定失败
                jsonContent = null;
            }
            jsonContent = jsonObject.getString("mdDevelopmentHypothesis");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentHypothesis = JSONObject.parseObject(jsonContent, MdDevelopmentHypothesis.class);
                mdDevelopmentHypothesis.setJsonContent(JSON.toJSONString(jsonContent));
                //必须初始化,否则下面判定失败
                jsonContent = null;
                if (!ObjectUtils.isEmpty(mdDevelopmentHypothesis.getId())){
                    id = mdDevelopmentService.getMdDevelopmentHypothesis(mdDevelopmentHypothesis.getId()).getPid();
                }
            }
            jsonContent = jsonObject.getString("mdDevelopmentArchitectural");
            if (!StringUtils.isEmpty(jsonContent)) {
                mdDevelopmentArchitectural = JSONObject.parseObject(jsonContent, MdDevelopmentArchitectural.class);
                mdDevelopmentArchitectural.setJsonContent(JSON.toJSONString(jsonContent));
                if (!ObjectUtils.isEmpty(mdDevelopmentArchitectural.getId())){
                    id = mdDevelopmentService.getMdDevelopmentArchitectural(mdDevelopmentArchitectural.getId()).getPid();
                }
            }
        } catch (Exception e1) {
            //不需要抛出异常
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));
        }

        //处理评估方法
        if (!ObjectUtils.isEmpty(supportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : supportInfoList) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }

        //处理假设开发法 在建工程
        if (mdDevelopmentHypothesis != null) {
            if (mdDevelopmentHypothesis.getId() == null){
                mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdDevelopmentHypothesis.class.getSimpleName(), 0);
                if (mdCostAndDevelopmentOther != null) {
                    //存入从表id
                    mdDevelopmentHypothesis.setEngineeringId(mdCostAndDevelopmentOther.getId());
                }
                //存入上级主表id
                mdDevelopmentHypothesis.setPid(id);
                pid = mdDevelopmentService.saveAndUpdateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
                //处理从表
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(pid);
                    mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            }else {
                mdDevelopmentService.saveAndUpdateMdDevelopmentHypothesis(mdDevelopmentHypothesis);
            }
        }

        //处理假设开发法 土地
        if (mdDevelopmentArchitectural != null) {
            if (mdDevelopmentArchitectural.getId() == null){
                mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(MdDevelopmentArchitectural.class.getSimpleName(), 0);
                if (mdCostAndDevelopmentOther != null) {
                    //存入从表id
                    mdDevelopmentArchitectural.setEngineeringId(mdCostAndDevelopmentOther.getId());
                }
                //存入上级主表id
                mdDevelopmentArchitectural.setPid(id);
                pid = mdDevelopmentService.saveAndUpdateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
                //处理从表
                if (mdCostAndDevelopmentOther != null) {
                    mdCostAndDevelopmentOther.setPid(pid);
                    mdCostAndDevelopmentOtherService.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
                }
            }else {
                mdDevelopmentService.saveAndUpdateMdDevelopmentArchitectural(mdDevelopmentArchitectural);
            }
        }
        //确定假设开发法具体选择的哪一个方法来测算的
        keyMdDevelopment = jsonObject.getString("mdDevelopment");
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyMdDevelopment)){
            if (pid != null){
                MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(pid);
                if (Objects.equal("MdDevelopmentArchitectural",keyMdDevelopment)){
                    if (mdDevelopmentArchitectural != null){
                        mdDevelopment.setId(id);
                        mdDevelopment.setPrice(new BigDecimal(mdDevelopmentArchitectural.getEstimateunitpricelandc33()));
                        mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
                        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
                    }
                }
                if (Objects.equal("MdDevelopmentHypothesis",keyMdDevelopment)){
                    if (mdDevelopmentHypothesis != null){
                        mdDevelopment.setId(id);
                        mdDevelopment.setPrice(new BigDecimal(mdDevelopmentHypothesis.getEstimateunitpricelandc33()));
                        mdDevelopment.setType(FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
                        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
                    }
                }
            }
        }
    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        Integer judgeObjectId = projectPlanDetails.getJudgeObjectId();
        if (judgeObjectId != null) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
            if (schemeJudgeObject != null){
                Integer areaGroupId = schemeJudgeObject.getAreaGroupId();
                if (areaGroupId != null){
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(areaGroupId);
                    modelAndView.addObject("schemeAreaGroup",schemeAreaGroup);
                }
            }
        }
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(supportInfoList));
        SchemeInfo schemeInfo = null;
        MdDevelopmentHypothesis mdDevelopmentHypothesis = null;
        MdDevelopmentArchitectural mdDevelopmentArchitectural = null;
        try {
            schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        } catch (Exception e1) {
            //不需要抛出异常
            logger.error(String.format("没有获取到数据 ==> %s", e1.getMessage()));
        }
        Integer pid = null;
        if (schemeInfo != null) {
            pid = schemeInfo.getMethodDataId();
        }
        //设置假设开发法 在建工程
        if (pid != null) {
            MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(pid);
            modelAndView.addObject("mdDevelopment",mdDevelopment);
            mdDevelopmentHypothesis = new MdDevelopmentHypothesis();
            mdDevelopmentHypothesis.setPid(pid);
            List<MdDevelopmentHypothesis> mdDevelopmentHypothesisList = mdDevelopmentService.getMdDevelopmentHypothesisList(mdDevelopmentHypothesis);
            if (!ObjectUtils.isEmpty(mdDevelopmentHypothesisList)) {
                //一定会是只有一个或者没有,关于原因 查看save method
                mdDevelopmentHypothesis = mdDevelopmentHypothesisList.get(0);
                modelAndView.addObject("mdDevelopmentHypothesisJSON", mdDevelopmentHypothesis.getJsonContent());
                modelAndView.addObject("mdDevelopmentHypothesis", mdDevelopmentHypothesis);
                MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdDevelopmentHypothesis.getEngineeringId());
                if (mdCostAndDevelopmentOther != null){
                    modelAndView.addObject("mdCostAndDevelopmentOtherHypothesis", mdCostAndDevelopmentOther);
                    modelAndView.addObject("mdCostAndDevelopmentOtherHypothesisJSON", mdCostAndDevelopmentOther.getJsonContent());
                }
            }
        }
        //假设开发法 土地
        if (pid != null) {
            mdDevelopmentArchitectural = new MdDevelopmentArchitectural();
            mdDevelopmentArchitectural.setPid(pid);
            List<MdDevelopmentArchitectural> mdDevelopmentArchitecturalList = mdDevelopmentService.getMdDevelopmentArchitecturalList(mdDevelopmentArchitectural);
            if (!ObjectUtils.isEmpty(mdDevelopmentArchitecturalList)){
                //一定会是 只有一个或者没有,原因...
                mdDevelopmentArchitectural = mdDevelopmentArchitecturalList.get(0);
                modelAndView.addObject("mdDevelopmentArchitecturalJSON",mdDevelopmentArchitectural.getJsonContent());
                modelAndView.addObject("mdDevelopmentArchitectural",mdDevelopmentArchitectural);
                MdCostAndDevelopmentOther mdCostAndDevelopmentOther = mdCostAndDevelopmentOtherService.getMdCostAndDevelopmentOther(mdDevelopmentArchitectural.getEngineeringId());
                if (mdCostAndDevelopmentOther != null){
                    modelAndView.addObject("mdCostAndDevelopmentOtherArchitectural", mdCostAndDevelopmentOther);
                    modelAndView.addObject("mdCostAndDevelopmentOtherArchitecturalJSON", mdCostAndDevelopmentOther.getJsonContent());
                }
            }
        }
    }
}

package com.copower.pmcc.assess.controller.document;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.entity.DocumentOpinion;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.document.DocumentOpinionService;
import com.copower.pmcc.assess.service.document.DocumentTemplateService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/11/17
 * @time: 14:49
 */
@Controller
@RequestMapping(value = "/documentOpinion", name = "意见稿控制类")
public class DocumentOpinionController {
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private DocumentOpinionService documentOpinionService;
    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @RequestMapping(value = "/applyIndex/{templateId}&{projectId}&{reportFieldName}&{areaGroupId}&{reportTypeId}&{tableId}", name = "进入意见稿页面")
    public ModelAndView applyIndex(@PathVariable("templateId") Integer templateId, @PathVariable("projectId") Integer projectId, @PathVariable("reportFieldName") String reportFieldName,
                                   @PathVariable("areaGroupId") Integer areaGroupId, @PathVariable("reportTypeId") Integer reportTypeId, @PathVariable("tableId") Integer tableId) {
        DocumentTemplate documentTemplate = documentTemplateService.getDocumentTemplate(templateId);
        String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT_DETAILS_DOCUMENT_OPINION_PROCESS_KEY.getParameterKey());
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/opinionApply", "0", boxId, "0", "");
        modelAndView.addObject("currUserInfo", processControllerComponent.getThisUserInfo());
        modelAndView.addObject("activityReName", ProcessActivityEnum.START.getValue());
        DocumentOpinion documentOpinion = new DocumentOpinion();
        documentOpinion.setCreator(processControllerComponent.getThisUser());
        documentOpinion.setContractType(templateId);
        documentOpinion.setProcessInsId("0");
        documentOpinion.setProjectId(projectId);
        documentOpinion.setAreaGroupId(areaGroupId);
        documentOpinion.setGenerationId(tableId);
        documentOpinion.setReportTypeId(reportTypeId);
        List<DocumentOpinion> documentOpinionList = documentOpinionService.getDocumentOpinion(documentOpinion);
        if (CollectionUtils.isNotEmpty(documentOpinionList)) {
            documentOpinion = documentOpinionList.get(0);
        } else {
            documentOpinion.setExtendConten("");
            documentOpinion.setId(0);
            documentOpinion.setTitle(documentTemplate.getTemplateName());
        }
        modelAndView.addObject("documentOpinion", documentOpinion);
        String fieldsHtml = documentOpinionService.getFieldsHtml(templateId, documentOpinion.getExtendConten().toString(), false);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("tableId", tableId);
        modelAndView.addObject("fieldsName", String.format("%s%s", reportFieldName, areaGroupId));
        return modelAndView;
    }

    @RequestMapping(value = "/approvalIndex", name = "进入审批页面")
    public ModelAndView approvalIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        DocumentOpinion documentOpinion = documentOpinionService.getDocumentOpinionByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/opinionApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("documentOpinion", documentOpinion);
        modelAndView.addObject("projectId", documentOpinion.getProjectId());
        String fieldsHtml = documentOpinionService.getFieldsHtml(documentOpinion.getContractType(), documentOpinion.getExtendConten().toString(), true);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        String fieldName = baseDataDicService.getDataDicById(documentOpinion.getReportTypeId()).getFieldName();
        String reportFieldName = fieldName.toUpperCase().replace(".", "_");
        modelAndView.addObject("fieldsName", String.format("%s%s", reportFieldName, documentOpinion.getAreaGroupId()));
        return modelAndView;
    }

    @RequestMapping(value = "/editIndex", name = "返回修改页面")
    public ModelAndView editIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {

        DocumentOpinion documentOpinion = documentOpinionService.getDocumentOpinionByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/opinionApply", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("documentOpinion", documentOpinion);
        modelAndView.addObject("projectId", documentOpinion.getProjectId());
        String fieldsHtml = documentOpinionService.getFieldsHtml(documentOpinion.getContractType(), documentOpinion.getExtendConten().toString(), false);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("bisEdit", 1);
        String fieldName = baseDataDicService.getDataDicById(documentOpinion.getReportTypeId()).getFieldName();
        String reportFieldName = fieldName.toUpperCase().replace(".", "_");
        modelAndView.addObject("fieldsName", String.format("%s%s", reportFieldName, documentOpinion.getAreaGroupId()));
        modelAndView.addObject("tableId", documentOpinion.getGenerationId());
        return modelAndView;

    }

    @RequestMapping(value = "/detailsIndex", name = "进入详情页面")
    public ModelAndView detailsIndex(String processInsId) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        DocumentOpinion documentOpinion = documentOpinionService.getDocumentOpinionByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/opinionApproval", processInsId, boxRuDto.getBoxId(), "-1", "");
        modelAndView.addObject("documentOpinion", documentOpinion);
        modelAndView.addObject("projectId", documentOpinion.getProjectId());
        String fieldsHtml = documentOpinionService.getFieldsHtml(documentOpinion.getContractType(), documentOpinion.getExtendConten().toString(), true);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        String fieldName = baseDataDicService.getDataDicById(documentOpinion.getReportTypeId()).getFieldName();
        String reportFieldName = fieldName.toUpperCase().replace(".", "_");
        modelAndView.addObject("fieldsName", String.format("%s%s", reportFieldName, documentOpinion.getAreaGroupId()));
        return modelAndView;
    }

    @RequestMapping(value = "/details/{uuid}", name = "进入详情页面")
    public ModelAndView details(@PathVariable("uuid") String uuid) throws BusinessException {
        DocumentOpinion documentOpinion = new DocumentOpinion();
        documentOpinion.setUuid(uuid);
        List<DocumentOpinion> documentOpinionList = documentOpinionService.getDocumentOpinion(documentOpinion);
        if (CollectionUtils.isEmpty(documentOpinionList)) {
            throw new BusinessException("没有找到相应的数据");
        }
        documentOpinion = documentOpinionList.get(0);
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(documentOpinion.getProcessInsId());
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/opinionApproval", documentOpinion.getProcessInsId(), boxRuDto.getBoxId(), "-1", "");
        modelAndView.addObject("documentOpinion", documentOpinion);
        String fieldsHtml = documentOpinionService.getFieldsHtml(documentOpinion.getContractType(), documentOpinion.getExtendConten().toString(), true);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        String fieldName = baseDataDicService.getDataDicById(documentOpinion.getReportTypeId()).getFieldName();
        String reportFieldName = fieldName.toUpperCase().replace(".", "_");
        modelAndView.addObject("fieldsName", String.format("%s%s", reportFieldName, documentOpinion.getAreaGroupId()));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/applySubmit", name = "提交审批，发起审批流程", method = RequestMethod.POST)
    public HttpResult applySubmit(Integer boxId, String appointUserAccount, DocumentOpinion documentOpinion) {
        try {
            documentOpinionService.applySubmit(appointUserAccount, boxId, documentOpinion);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/approvalSubmit", name = "审批流程", method = RequestMethod.POST)
    public HttpResult approvalSubmit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            documentOpinionService.approvalSubmit(approvalModelDto);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/editSubmit", name = "返回修改审批流程", method = RequestMethod.POST)
    public HttpResult editSubmit(ApprovalModelDto approvalModelDto, DocumentOpinion documentOpinion) {
        try {
            documentOpinionService.editSubmit(approvalModelDto, documentOpinion);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/closeSubmit", name = "关闭流程", method = RequestMethod.POST)
    public HttpResult closeSubmit(ApprovalModelDto approvalModelDto) {

        try {
            documentOpinionService.closeSubmit(approvalModelDto);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/buildDoc", name = "生成文件")
    public HttpResult buildContractDoc(DocumentOpinion documentOpinion) {
        //保存业务数据
        try {
            Integer integer = documentOpinionService.buildDoc(documentOpinion);
            return HttpResult.newCorrectResult(integer);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getDocumentOpinionVoList", name = "项目意见稿列表", method = RequestMethod.GET)
    public BootstrapTableVo getDocumentOpinionVoList(Integer projectId) {
        return documentOpinionService.getDocumentOpinionVoList(projectId);
    }

}

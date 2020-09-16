package com.copower.pmcc.assess.controller.document;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.entity.DocumentSend;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.document.DocumentSendService;
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
@RequestMapping(value = "/documentSend", name = "项目发文控制类")
public class DocumentSendController {
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private DocumentSendService documentSendService;
    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private PublicService publicService;

    @RequestMapping(value = "/applyIndex/{templateId}&{projectId}", name = "公司盖章发文页面")
    public ModelAndView applyIndex(@PathVariable("templateId") Integer templateId, @PathVariable("projectId") Integer projectId) {
        String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT_DETAILS_DOCUMENT_SEND_PROCESS_KEY.getParameterKey());
        return getModelAndView(templateId, projectId, boxName);
    }

    @RequestMapping(value = "/applyClientIndex/{templateId}&{projectId}", name = "委托方盖章发文")
    public ModelAndView applyClientIndex(@PathVariable("templateId") Integer templateId, @PathVariable("projectId") Integer projectId) {
        String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT_DETAILS_DOCUMENT_SEND_CLIENT_PROCESS_KEY.getParameterKey());
        return getModelAndView(templateId, projectId, boxName);
    }

    @RequestMapping(value = "/applySignBillIndex/{templateId}&{projectId}", name = "报告签收单")
    public ModelAndView applySignBillIndex(@PathVariable("templateId") Integer templateId, @PathVariable("projectId") Integer projectId) {
        String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT_DETAILS_DOCUMENT_SIGN_BILL_PROCESS_KEY.getParameterKey());
        return getModelAndView(templateId, projectId, boxName);
    }

    private ModelAndView getModelAndView(Integer templateId, Integer projectId, String boxName) {
        DocumentTemplate documentTemplate = documentTemplateService.getDocumentTemplate(templateId);

        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/sendApply", "0", boxId, "0", "");
        modelAndView.addObject("currUserInfo", processControllerComponent.getThisUserInfo());
        modelAndView.addObject("activityReName", ProcessActivityEnum.START.getValue());
        DocumentSend documentSend = new DocumentSend();
        documentSend.setCreator(processControllerComponent.getThisUser());
        documentSend.setContractType(templateId);
        documentSend.setProcessInsId("0");
        documentSend.setProjectId(projectId);
        List<DocumentSend> documentSendList = documentSendService.getDocumentSend(documentSend);
        if (CollectionUtils.isNotEmpty(documentSendList)) {
            documentSend = documentSendList.get(0);
        } else {
            documentSend.setExtendConten("");
            documentSend.setId(0);
            documentSend.setTitle(documentTemplate.getTemplateName());
        }
        modelAndView.addObject("documentSend", documentSend);
        String fieldsHtml = documentSendService.getFieldsHtml(templateId, documentSend.getExtendConten().toString(), false);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("lastActivity", publicService.getLastActivityByBoxId(boxId));
        return modelAndView;
    }


    @RequestMapping(value = "/approvalIndex", name = "进入审批页面")
    public ModelAndView approvalIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        DocumentSend documentSend = documentSendService.getDocumentSendByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/sendApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("documentSend", documentSend);
        modelAndView.addObject("projectId", documentSend.getProjectId());
        String fieldsHtml = documentSendService.getFieldsHtml(documentSend.getContractType(), documentSend.getExtendConten().toString(), true);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("lastActivity", publicService.getLastActivityByBoxId(boxId));
        return modelAndView;
    }

    @RequestMapping(value = "/editIndex", name = "返回修改页面")
    public ModelAndView editIndex(String processInsId, String taskId, Integer boxId, String agentUserAccount) {

        DocumentSend documentSend = documentSendService.getDocumentSendByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/sendApply", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("documentSend", documentSend);
        modelAndView.addObject("projectId", documentSend.getProjectId());
        String fieldsHtml = documentSendService.getFieldsHtml(documentSend.getContractType(), documentSend.getExtendConten().toString(), false);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("bisEdit", 1);
        modelAndView.addObject("lastActivity", publicService.getLastActivityByBoxId(boxId));
        return modelAndView;

    }

    @RequestMapping(value = "/detailsIndex", name = "进入详情页面")
    public ModelAndView detailsIndex(String processInsId) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        DocumentSend documentSend = documentSendService.getDocumentSendByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/sendApproval", processInsId, boxRuDto.getBoxId(), "-1", "");
        modelAndView.addObject("documentSend", documentSend);
        modelAndView.addObject("projectId", documentSend.getProjectId());
        String fieldsHtml = documentSendService.getFieldsHtml(documentSend.getContractType(), documentSend.getExtendConten().toString(), true);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("lastActivity", publicService.getLastActivityByBoxId(boxRuDto.getBoxId()));
        return modelAndView;
    }

    @RequestMapping(value = "/details/{uuid}", name = "进入详情页面")
    public ModelAndView details(@PathVariable("uuid") String uuid) throws BusinessException {
        DocumentSend documentSend = new DocumentSend();
        documentSend.setUuid(uuid);
        List<DocumentSend> documentSendList = documentSendService.getDocumentSend(documentSend);
        if (CollectionUtils.isEmpty(documentSendList)) {
            throw new BusinessException("没有找到相应的数据");
        }
        documentSend = documentSendList.get(0);
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(documentSend.getProcessInsId());
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/document/sendApproval", documentSend.getProcessInsId(), boxRuDto.getBoxId(), "-1", "");
        modelAndView.addObject("documentSend", documentSend);
        String fieldsHtml = documentSendService.getFieldsHtml(documentSend.getContractType(), documentSend.getExtendConten().toString(), true);
        modelAndView.addObject("fieldsHtml", fieldsHtml);
        modelAndView.addObject("lastActivity", publicService.getLastActivityByBoxId(boxRuDto.getBoxId()));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/applySubmit", name = "提交审批，发起审批流程", method = RequestMethod.POST)
    public HttpResult applySubmit(Integer boxId, String appointUserAccount, DocumentSend documentSend) {
        try {
            documentSendService.applySubmit(appointUserAccount, boxId, documentSend);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/approvalSubmit", name = "审批流程", method = RequestMethod.POST)
    public HttpResult approvalSubmit(ApprovalModelDto approvalModelDto, String formData) {
        try {
            documentSendService.approvalSubmit(approvalModelDto);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/editSubmit", name = "返回修改审批流程", method = RequestMethod.POST)
    public HttpResult editSubmit(ApprovalModelDto approvalModelDto, DocumentSend documentSend) {
        try {
            documentSendService.editSubmit(approvalModelDto, documentSend);

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/closeSubmit", name = "关闭流程", method = RequestMethod.POST)
    public HttpResult closeSubmit(ApprovalModelDto approvalModelDto) {

        try {
            documentSendService.closeSubmit(approvalModelDto);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/buildDoc", name = "生成文件")
    public HttpResult buildContractDoc(DocumentSend documentSend) {
        //保存业务数据
        try {
            Integer integer = documentSendService.buildDoc(documentSend);
            return HttpResult.newCorrectResult(integer);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getDocumentSendVoList", name = "项目发文列表", method = RequestMethod.GET)
    public BootstrapTableVo getDocumentSendVoList(Integer projectId) {
        return documentSendService.getDocumentSendVoList(projectId);
    }
}

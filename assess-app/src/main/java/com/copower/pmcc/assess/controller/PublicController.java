package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.dto.AttachmentVo;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kings on 2018-5-10.
 */
@Controller
@RequestMapping("/public")
public class PublicController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    @ResponseBody
    @RequestMapping(value = "/importAjaxFile", name = "导入文件", method = RequestMethod.POST)
    public HttpResult importAjaxFile(HttpServletRequest request,String tableName,@RequestParam(defaultValue = "0") String tableId,String fieldsName) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            return HttpResult.newCorrectResult(baseAttachmentService.importAjaxFile(multipartFile,tableName,tableId,fieldsName));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSysAttachmentDto", method = {RequestMethod.GET}, name = "获取附件")
    public HttpResult getSysAttachmentDto(Integer attachmentId) {
        SysAttachmentDto sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(attachmentId);
        if (sysAttachmentDto != null) {
            return HttpResult.newCorrectResult(sysAttachmentDto);
        }
        return HttpResult.newErrorResult("异常");
    }

    @ResponseBody
    @RequestMapping(value = "/downloadFtpFileToLocal", method = {RequestMethod.GET}, name = "下载ftp附件到本地")
    public HttpResult downloadFtpFileToLocal(Integer attachmentId) {
        try {
            return HttpResult.newCorrectResult(baseAttachmentService.downloadFtpFileToLocal(attachmentId));
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateSysAttachmentDto", method = {RequestMethod.POST}, name = "新增或者更新附件")
    public HttpResult saveAndUpdateSysAttachmentDto(SysAttachmentDto sysAttachmentDto) {
        if (sysAttachmentDto != null) {
            if (sysAttachmentDto.getId() == null) {
                baseAttachmentService.addAttachment(sysAttachmentDto);
            } else {
                baseAttachmentService.updateAttachment(sysAttachmentDto);
            }
            return HttpResult.newCorrectResult(sysAttachmentDto);
        }
        return HttpResult.newErrorResult("异常");
    }

    @ResponseBody
    @RequestMapping(value = "/getAreaList", name = "获取区域信息", method = RequestMethod.POST)
    public Object getAreaList(String pid) {
        try {
            if (StringUtils.isNotBlank(pid)) {
                List<SysAreaDto> sysAreaDtos = erpAreaService.getAreaList(pid);
                return sysAreaDtos;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getAreaById", name = "获取区域单个信息", method = RequestMethod.GET)
    public HttpResult getAreaById(String id) {
        SysAreaDto sysAreaDto = null;
        try {
            if (StringUtils.isNotBlank(id)) {
                sysAreaDto = erpAreaService.getSysAreaDto(id);
                if (sysAreaDto != null) {
                    return HttpResult.newCorrectResult(sysAreaDto);
                } else {
                    return HttpResult.newErrorResult("没有获取到数据!");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newErrorResult("没有获取到数据!");
    }

    @ResponseBody
    @RequestMapping(value = "/getApprovalLogByProject", name = "获取项目日志", method = RequestMethod.GET)
    public BootstrapTableVo getApprovalLogByProject(Integer projectId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        String search = requestBaseParam.getSearch();
        if (StringUtils.isNotBlank(search)) {
            search = String.format("%%%s%%", search);
        }
        BootstrapTableVo approvalLog = bpmRpcProcessInsManagerService.getApprovalLogByProjectForSubSystem(projectId, applicationConstant.getAppKey(), requestBaseParam.getOffset(), requestBaseParam
                .getLimit(), search);

        List<BoxApprovalLogVo> rows = (List<BoxApprovalLogVo>) approvalLog.getRows();
        SysAttachmentDto bidBaseAttachment = new SysAttachmentDto();
        bidBaseAttachment.setProjectId(projectId);
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(bidBaseAttachment);
        List<BoxApprovalLogVo> resultRows = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (BoxApprovalLogVo item : rows) {
                if (item.getWorkPhaseId() != null && item.getWorkPhaseId() > 0) {
                    ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(item.getWorkPhaseId());
                    if (projectPhase != null) {
                        item.setOpinions(String.format("[%s]%s", projectPhase.getProjectPhaseName(), item.getOpinions()));
                    }
                }
                List<SysAttachmentDto> filter = LangUtils.filter(attachmentList, o -> {
                    return StringUtils.equals(o.getProcessInsId(), item.getProcessInsId()) && StringUtils.equals(o.getReActivityName(), item.getActivityNameKey()) && StringUtils.equals(o
                            .getProcessTaskId(), item.getProcessTaskId());
                });
                if (CollectionUtils.isNotEmpty(filter)) {
                    List<AttachmentVo> attachmentVos = getAttachmentVos(filter);
                    item.setAttachmentVos(attachmentVos);
                }
                resultRows.add(item);
            }
            approvalLog.setRows(resultRows);
        }
        return approvalLog;
    }

    @ResponseBody
    @RequestMapping(value = "/getApprovalLog", name = "获取流程审批日志", method = RequestMethod.GET)
    public BootstrapTableVo getApprovalLog(String processInsId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo approvalLog = bpmRpcProcessInsManagerService.getApprovalLog(processInsId, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BoxApprovalLogVo> rows = (List<BoxApprovalLogVo>) approvalLog.getRows();
        List<String> transform = LangUtils.transform(rows, o -> o.getProcessTaskId());
        List<SysAttachmentDto> approvalLogList = baseAttachmentService.getApprovalLogList(processInsId, transform);
        if (CollectionUtils.isNotEmpty(approvalLogList)) {
            for (BoxApprovalLogVo item : rows) {
                List<SysAttachmentDto> filter = LangUtils.filter(approvalLogList, o -> {
                    return o.getProcessTaskId().equals(item.getProcessTaskId());
                });
                if (CollectionUtils.isNotEmpty(filter)) {
                    List<AttachmentVo> attachmentVos = getAttachmentVos(filter);
                    item.setAttachmentVos(attachmentVos);
                }
            }
            approvalLog.setRows(rows);
        }
        return approvalLog;
    }

    private List<AttachmentVo> getAttachmentVos(List<SysAttachmentDto> filter) {
        return LangUtils.transform(filter, o -> {
            AttachmentVo attachmentVo = new AttachmentVo();
            attachmentVo.setId(o.getId());
            attachmentVo.setFileName(o.getFileName());
            attachmentVo.setFileExtension(o.getFileExtension());
            attachmentVo.setHasKeep(false);
            return attachmentVo;
        });
    }

    @ResponseBody
    @RequestMapping(value = "/closeProcess", name = "流程实例关闭")
    public HttpResult closeProcess(String processInsId) {
        try {
            bpmRpcActivitiProcessManageService.closeProcess(processInsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("流程关闭异常");
        }
    }


}

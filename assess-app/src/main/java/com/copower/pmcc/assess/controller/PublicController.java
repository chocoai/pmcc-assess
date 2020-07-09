package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.assess.common.JSONChange;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dto.input.map.PolygonMapData;
import com.copower.pmcc.assess.service.AdRpcQualificationsAppService;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.dto.AttachmentVo;
import com.copower.pmcc.bpm.api.dto.BoxApprovalLogVo;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-5-10.
 */
@RestController
@RequestMapping("/public")
public class PublicController {
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
    @Autowired
    private BaseService baseService;
    @Autowired
    private AdRpcQualificationsAppService adRpcQualificationsAppService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private PublicService publicService;

    @GetMapping(value = "/getAdPersonalIdentityDto", name = "获取资质")
    public HttpResult getAdPersonalIdentityDto(String userAccount, String qualificationType) {
        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = new ArrayList<>();
        try {
            adPersonalQualificationDtoList = adRpcQualificationsAppService.getAdPersonalQualificationDto(userAccount, qualificationType);
            if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                adPersonalQualificationDtoList.forEach(adPersonalQualificationDto -> {
                    SysUserDto sysUserDto = erpRpcUserService.getSysUser(adPersonalQualificationDto.getUserAccount());
                    if (sysUserDto != null && StringUtils.isNotBlank(sysUserDto.getUserName())) {
                        adPersonalQualificationDto.setName(sysUserDto.getUserName());
                    }
                });
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取资质异常");
        }
        return HttpResult.newCorrectResult(adPersonalQualificationDtoList);
    }


    @RequestMapping(value = "/importAjaxFile", name = "导入文件", method = RequestMethod.POST)
    public HttpResult importAjaxFile(HttpServletRequest request, String tableName, @RequestParam(defaultValue = "0") String tableId, String fieldsName) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultiValueMap<String, MultipartFile> multipartFileMultiValueMap = multipartRequest.getMultiFileMap();
            List<MultipartFile> multipartFileList = Lists.newArrayList();
            if (!multipartFileMultiValueMap.isEmpty()) {
                multipartFileMultiValueMap.forEach((s, multipartFiles) -> {
                    if (!multipartFiles.isEmpty()) {
                        multipartFileList.addAll(multipartFiles);
                    }
                });
            }
            if (CollectionUtils.isEmpty(multipartFileList)) {
                return HttpResult.newErrorResult(500, new Exception("上传的文件不能为空"));
            }
            return HttpResult.newCorrectResult(200, baseAttachmentService.importAjaxFile(multipartFileList, tableName, tableId, fieldsName));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @RequestMapping(value = "/getSysAttachmentDto", method = {RequestMethod.GET}, name = "获取附件")
    public HttpResult getSysAttachmentDto(Integer attachmentId) {
        try {
            SysAttachmentDto sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(attachmentId);
            return HttpResult.newCorrectResult(sysAttachmentDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/getSysAttachmentDtoList", method = {RequestMethod.GET}, name = "获取附件列表")
    public HttpResult getSysAttachmentDtoList(SysAttachmentDto sysAttachmentDto) {
        try {
            return HttpResult.newCorrectResult(baseAttachmentService.getAttachmentList(sysAttachmentDto));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/downloadFtpFileToLocal", method = {RequestMethod.GET}, name = "下载ftp附件到本地")
    public HttpResult downloadFtpFileToLocal(Integer attachmentId) {
        try {
            return HttpResult.newCorrectResult(baseAttachmentService.downloadFtpFileToLocal(attachmentId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/getSysAttachmentViewHtml", method = {RequestMethod.GET}, name = "获取附件的html")
    public HttpResult getSysAttachmentViewHtml(Integer attachmentId) {
        try {
            return HttpResult.newCorrectResult(baseAttachmentService.getViewHtml(baseAttachmentService.getSysAttachmentDto(attachmentId)));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/saveAndUpdateSysAttachmentDto", method = {RequestMethod.POST}, name = "新增或者更新附件")
    public HttpResult saveAndUpdateSysAttachmentDto(SysAttachmentDto sysAttachmentDto, String formData) {
        try {
            if (StringUtils.isNotBlank(formData)) {
                sysAttachmentDto = JSONObject.parseObject(formData, SysAttachmentDto.class);
            }
            saveAndUpdateSysAttachmentDto2(sysAttachmentDto);
            return HttpResult.newCorrectResult(sysAttachmentDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    private void saveAndUpdateSysAttachmentDto2(SysAttachmentDto sysAttachmentDto) {
        if (sysAttachmentDto.getId() == null && sysAttachmentDto.getId() != 0) {
            baseAttachmentService.addAttachment(sysAttachmentDto);
        } else {
            baseAttachmentService.updateAttachment(sysAttachmentDto);
        }
    }

    @RequestMapping(value = "/batchUpdateSysAttachmentDto", method = {RequestMethod.POST}, name = "批量新增或者更新附件")
    public HttpResult batchUpdateSysAttachmentDto(String formData) {
        try {
            List<SysAttachmentDto> sysAttachmentDtoList = JSONObject.parseArray(formData, SysAttachmentDto.class);
            if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                    saveAndUpdateSysAttachmentDto2(sysAttachmentDto);
                }
            }
            return HttpResult.newCorrectResult(sysAttachmentDtoList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/html2canvasNetDownloadUtils", name = "canvas 转为正常图片  并且形成附件")
    public HttpResult html2canvasNetDownloadUtils(String canvasCode, Integer tableId, String tableName, String fieldsName) {
        try {
            List<String> stringList = FormatUtils.transformString2List(fieldsName, ",");
            for (String str : stringList) {
                publicService.html2canvasNetDownloadUtils(canvasCode, tableId, tableName, str);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }


    @PostMapping(value = "/newHtml2canvasNetDownloadUtils", name = "高德地图 折线  转为正常图片  并且形成附件")
    public HttpResult newHtml2canvasNetDownloadUtils(String canvasCode, Integer tableId, String tableName, String fieldsName) {
        try {
            List<PolygonMapData> polygonMapDataList = JSONObject.parseArray(canvasCode, PolygonMapData.class);
            List<String> stringList = FormatUtils.transformString2List(fieldsName, ",");
            for (String str : stringList) {
                publicService.newHtml2canvasNetDownloadUtils(polygonMapDataList, tableId, tableName, str);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    /**
     * @param sysAttachmentDto 目标数据
     * @param masterId         拷贝的源附件
     * @return
     */
    @RequestMapping(value = "/copyFtpAttachment", method = {RequestMethod.POST}, name = "拷贝FTP附件")
    public HttpResult copyFtpAttachment(SysAttachmentDto sysAttachmentDto, Integer masterId) {
        try {
            return HttpResult.newCorrectResult(200, baseAttachmentService.copyFtpAttachment(masterId, sysAttachmentDto));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @RequestMapping(value = "/deleteAttachmentById", method = {RequestMethod.POST}, name = "删除FTP附件")
    public HttpResult deleteAttachmentById(String id) {
        try {
            baseAttachmentService.deleteAttachmentByIdList(id);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, "异常");
        }
    }

    @RequestMapping(value = "/getAreaList", name = "获取区域信息", method = RequestMethod.POST)
    public Object getAreaList(String pid) {
        try {
            if (StringUtils.isNotBlank(pid)) {
                List<SysAreaDto> sysAreaDtos = erpAreaService.getAreaList(pid);
                return sysAreaDtos;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/getAreaById", name = "获取区域单个信息", method = RequestMethod.GET)
    public HttpResult getAreaById(String id) {
        try {
            return HttpResult.newCorrectResult(erpAreaService.getSysAreaDto(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("没有获取到数据!");
        }
    }

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

    @RequestMapping(value = "/getApprovalLog", name = "获取流程审批日志", method = RequestMethod.GET)
    public BootstrapTableVo getApprovalLog(String processInsId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo approvalLog = bpmRpcProcessInsManagerService.getApprovalLogForApp(applicationConstant.getAppKey(), processInsId, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BoxApprovalLogVo> rows = (List<BoxApprovalLogVo>) approvalLog.getRows();
        List<String> transform = LangUtils.transform(rows, o -> o.getProcessTaskId());
        if (StringUtils.isNotBlank(processInsId) && !"0".equals(processInsId)) {
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
            }
        }
        approvalLog.setRows(rows);
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

    @RequestMapping(value = "/closeProcess", name = "流程实例关闭")
    @ResponseBody
    public HttpResult closeProcess(String processInsId) {
        try {
            bpmRpcActivitiProcessManageService.closeProcess(processInsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("流程关闭异常");
        }
    }


}

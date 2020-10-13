package com.copower.pmcc.assess.service.document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.assess.common.DocumentWordUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.document.DocumentBaseEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataNumberRuleDao;
import com.copower.pmcc.assess.dal.basis.dao.document.DocumentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.DocumentTemplateFieldVo;
import com.copower.pmcc.assess.dto.output.document.DocumentSendVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.ProjectTakeNumberService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectQrcodeRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateBaseDataService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.*;

/**
 * 描述:发文
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/11/17
 * @time: 14:50
 */
@Service
public class DocumentSendService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentSendService.class);
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private DocumentTemplateService documentTemplateService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private DocumentWordUtils documentWordUtils;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;
    @Autowired
    private DataNumberRuleDao dataNumberRuleDao;
    @Autowired
    private ProjectQrcodeRecordService projectQrcodeRecordService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ProjectTakeNumberService projectTakeNumberService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;

    public List<DocumentSend> getDocumentSendList(Integer projectId,String status){
        return documentDao.getDocumentSendList(projectId, status) ;
    }

    public List<DocumentSend> getDocumentSend(DocumentSend documentSend) {
        return documentDao.getDocumentSendList(documentSend);
    }

    public DocumentSend getDocumentSendByProcessInsId(String processInsId) {
        DocumentSend documentSend = new DocumentSend();
        documentSend.setProcessInsId(processInsId);
        List<DocumentSend> documentSendList = documentDao.getDocumentSendList(documentSend);
        if (CollectionUtils.isNotEmpty(documentSendList)) {
            return documentSendList.get(0);
        }
        return documentSend;
    }

    public DocumentSend saveDocumentSend(DocumentSend documentSend) {
        if (documentSend.getId() != null && documentSend.getId() > 0) {
            documentDao.updateDocumentSend(documentSend);
        } else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            documentSend.setCreator(processControllerComponent.getThisUser());
            documentSend.setCreated(DateUtils.now());
            documentSend.setUuid(uuid);
            documentDao.addDocumentSend(documentSend);
        }
        return documentSend;
    }

    @Transactional(rollbackFor = Exception.class)
    public void applySubmit(String appointUserAccount, Integer boxId, DocumentSend documentSend) throws BusinessException {
        documentSend = saveDocumentSend(documentSend);//保存数据
        //发起流程
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        String porcessNumber = MessageFormat.format("{0}({1}){2}", boxReDto.getId(), processControllerComponent.getThisUserInfo().getId(), DateUtils.formatDateToYMDHMS(new Date()));
        DocumentTemplate documentTemplate = documentDao.getDocumentTemplate(documentSend.getContractType());
        String folio = String.format("[%s]%s", documentTemplate.getTemplateName(), documentSend.getTitle());
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessEventExecutor(BaseProcessEvent.class);
        processInfo.setWorkPhaseId(documentSend.getContractType());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(DocumentSend.class));
        processInfo.setTableId(documentSend.getId());
        processInfo.setProjectId(documentSend.getProjectId());
        processInfo.setStartUser(processControllerComponent.getThisUser());
        String processInsId = "";
        try {
            if (StringUtils.isBlank(appointUserAccount)) {
                appointUserAccount = processControllerComponent.getThisUser();
            }
            ProcessUserDto processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, appointUserAccount, false);//发起流程，并返回流程实例编号
            processInsId = processUserDto.getProcessInsId();
            documentSend.setProcessInsId(processInsId);
            documentSend.setStatus(ProcessStatusEnum.RUN.getValue());
            documentSend.setNumber(porcessNumber);
            saveDocumentSend(documentSend);
            //更新附件
            SysAttachmentDto sysAttachment = new SysAttachmentDto();
            sysAttachment.setProcessInsId("0");
            sysAttachment.setCreater(processControllerComponent.getThisUser());
            sysAttachment.setTableName(FormatUtils.entityNameConvertToTableName(DocumentSend.class));
            sysAttachment.setProjectId(documentSend.getProjectId());
            SysAttachmentDto sysAttachmentNew = new SysAttachmentDto();
            sysAttachmentNew.setProcessInsId(processInsId);
            sysAttachmentNew.setTableId(processInfo.getTableId());
            sysAttachmentNew.setProjectId(documentSend.getProjectId());
            erpRpcAttachmentService.updateAttachmentByParam(sysAttachment, sysAttachmentNew);
        } catch (BpmException e) {
            bpmRpcActivitiProcessManageService.closeProcess(processInsId);
            logger.error("写入业务数据出错", e);
            throw new BusinessException("提交申请数据时发生异常", e);
        }

    }

    public void approvalSubmit(ApprovalModelDto approvalModelDto) throws BusinessException {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    public void editSubmit(ApprovalModelDto approvalModelDto, DocumentSend documentSend) throws BusinessException {

        saveDocumentSend(documentSend);
        approvalModelDto.setOpinions("返回修改");
        approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setCurrentStep(-1);
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public void closeSubmit(ApprovalModelDto approvalModelDto) throws BusinessException {
        approvalModelDto.setConclusion(TaskHandleStateEnum.CLOSE.getValue());
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public String getFieldsHtml(Integer templateId, String jsonValue, Boolean readOnly) {

        List<DocumentTemplateFieldVo> documentTemplateField = documentTemplateService.getDocumentTemplateField(templateId);
        if (CollectionUtils.isNotEmpty(documentTemplateField)) {
            List<DynamicFormField> transform = LangUtils.transform(documentTemplateField, o -> {
                DynamicFormField dynamicFormField = new DynamicFormField();
                dynamicFormField.setFormField(o.getName());
                dynamicFormField.setLabelName(o.getDisplayName());
                dynamicFormField.setFieldType(o.getFieldType().toString());
                dynamicFormField.setBisRequired(o.getBisRequired());
                dynamicFormField.setValueLength(o.getFieldLength());
                dynamicFormField.setDisplaySort(o.getSorting());
                if (StringUtils.isNotBlank(o.getDataSource())) {
                    List<Map> maps = ddlMySqlAssist.customTableSelect(o.getDataSource());
                    if (CollectionUtils.isNotEmpty(maps)) {
                        List<KeyValueDto> keyValueDtos = new ArrayList<>();
                        for (Map rs : maps) {
                            KeyValueDto keyValue = new KeyValueDto();
                            keyValue.setKey(String.valueOf(rs.get("key")));
                            keyValue.setValue(String.valueOf(rs.get("value")));
                            keyValueDtos.add(keyValue);
                        }
                        dynamicFormField.setData(keyValueDtos);
                    }
                }
                return dynamicFormField;
            });
            GenerateDynamicFormUtil generateDynamicFormUtil = new GenerateDynamicFormUtil();
            String s = generateDynamicFormUtil.buildColumn(3).buildDynamicForm(transform, readOnly, jsonValue);
            return s;
        }
        return "";

    }

    public Integer buildDoc(DocumentSend documentSend) throws BusinessException {
        if (documentSend.getId() == null || documentSend.getId() == 0) {
            documentSend.setProcessInsId("0");
        }
        documentSend = saveDocumentSend(documentSend);
        Map<String, String> textValues = new HashMap<String, String>();
        List<DocumentTemplateField> getFieldList = documentTemplateService.getFieldList(documentSend.getContractType());
        JSONObject jsonObject = JSON.parseObject(documentSend.getExtendConten());
        for (DocumentTemplateField item : getFieldList) {
            CustomTableTypeEnum customTableTypeEnum = CustomTableTypeEnum.getCustomTypeByColumnsPrefix(Integer.valueOf(item.getFieldType()));
            switch (customTableTypeEnum) {
                case DATE: {
                    String string = jsonObject.getString(item.getName());
                    if (StringUtils.isNotBlank(string)) {
                        textValues.put(item.getName(), DateUtils.format(DateUtils.convertDate(string), DateUtils.DATE_CHINESE_PATTERN));
                    }
                    break;
                }
                case RADIO: {
                    textValues.put(item.getName(), jsonObject.getString(String.format("%s_name", item.getName())));
                    break;
                }
                default: {
                    textValues.put(item.getName(), jsonObject.getString(item.getName()));
                }
            }
        }
        Map<String, String> textMap = getBaseEnumValue(documentSend.getProjectId());
        textValues.putAll(textMap);

        //取得数据替换成模板书签数据

        List<DocumentTemplateBookmark> listObject = documentTemplateService.getCmsTemplateBookmark(documentSend.getContractType());

        Map<String, String> wordValues = new HashMap<String, String>();
        for (DocumentTemplateBookmark item : listObject) {
            if (StringUtils.isNotBlank(item.getFieldName())) {
                String s = textValues.get(item.getFieldName());
                if (StringUtils.isNotBlank(s)) {
                    wordValues.put(item.getName(), textValues.get(item.getFieldName()));
                }
            }
        }

        //生成文件
        ///region 设置文件附件表中相关信息
        String tableName = FormatUtils.entityNameConvertToTableName(DocumentSend.class);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(documentSend.getId());
        sysAttachmentDto.setTableName(tableName);
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setProjectId(documentSend.getProjectId());
        sysAttachmentDto.setFieldsName("document");
        erpRpcAttachmentService.deleteAttachmentByDto(sysAttachmentDto);//先删除老数据
        String ftpFilePath = applicationConstant.getAppKey() + "/" + FormatUtils.underlineToCamel(tableName, false) + "/" + DateUtils.format(new Date(), DateUtils.MONTH_PATTERN);
        sysAttachmentDto.setFilePath(ftpFilePath);
        sysAttachmentDto.setProcessInsId(documentSend.getProcessInsId());
        String filesName = documentSend.getTitle();
        if (StringUtils.isBlank(filesName)) {
            filesName = "新建文件";
        }
        sysAttachmentDto.setFileName(String.format("%s", filesName));
        ///endregion

        //再将附件上传到相同位置
        try {
            DocumentTemplate documentTemplate = documentTemplateService.getDocumentTemplate(documentSend.getContractType());
            Map<String, String> document = documentWordUtils.createDocument(documentTemplate, wordValues, sysAttachmentDto);//将文本字段进行替换，并返回相应本地文件路径
            //生成文号
//            String symbolNumber = new String();
//            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(documentSend.getProjectId());
//            AssessProjectTypeEnum assessProjectTypeEnum = AssessProjectTypeEnum.getAssessProjectTypeEnumByKey(documentTemplate.getAssessProjectType());
//            DataNumberRule numberRule = dataNumberRuleDao.getDataNumberRuleById(documentTemplate.getNumbetRuleId());
//            if (numberRule != null) {
//                SysSymbolListDto symbolListDto = projectNumberRecordService.getReportNumber(projectInfo, 0, assessProjectTypeEnum, numberRule.getReportType(), false);
//                symbolNumber = symbolListDto.getSymbol();
//            }
//            //生成二维码
//            String reportQrcodePath = this.getReportQrcode(documentSend, symbolNumber);
//            //替换
//            projectTakeNumberService.replaceDocument(document.get("localFullPath"), symbolNumber, reportQrcodePath);
            //对Word文件进行格式化操作
            ftpUtilsExtense.uploadFilesToFTP(document.get("ftpPath"), new FileInputStream(document.get("localFullPath")), document.get("ftpFileName"));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return documentSend.getId();
    }

    //取相应的项目相关固定值
    public Map<String, String> getBaseEnumValue(Integer projectId) {
        Map<String, String> wordValues = new HashMap<String, String>();
        wordValues.put(DocumentBaseEnum.PROJECTNAME.getValue(), "");
        return wordValues;
    }

    /**
     * 获取数据列表
     *
     * @param projectId
     * @return
     */
    public BootstrapTableVo getDocumentSendVoList(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        DocumentSend documentSend = new DocumentSend();
        documentSend.setProjectId(projectId);
        List<DocumentSend> list = documentDao.getDocumentSendList(documentSend);
        List<DocumentSendVo> vos = LangUtils.transform(list, p -> getDocumentSendVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DocumentSendVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public DocumentSendVo getDocumentSendVo(DocumentSend documentSend) {
        if (documentSend == null) return null;
        DocumentSendVo vo = new DocumentSendVo();
        BeanUtils.copyProperties(documentSend, vo);
        vo.setUserName(publicService.getUserNameByAccount(documentSend.getCreator()));
        return vo;
    }
}

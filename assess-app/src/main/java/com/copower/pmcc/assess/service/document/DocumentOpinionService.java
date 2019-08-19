package com.copower.pmcc.assess.service.document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.DocumentWordUtils;
import com.copower.pmcc.assess.common.enums.document.DocumentBaseEnum;
import com.copower.pmcc.assess.dal.basis.dao.document.DocumentDao;
import com.copower.pmcc.assess.dal.basis.entity.DocumentOpinion;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplate;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateBookmark;
import com.copower.pmcc.assess.dal.basis.entity.DocumentTemplateField;
import com.copower.pmcc.assess.dto.output.DocumentTemplateFieldVo;
import com.copower.pmcc.assess.dto.output.document.DocumentOpinionVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
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
import com.copower.pmcc.erp.api.dto.DynamicFormField;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
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
public class DocumentOpinionService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentOpinionService.class);
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
    private BaseDataDicService baseDataDicService;

    public List<DocumentOpinion> getDocumentOpinion(DocumentOpinion documentOpinion) {
        return documentDao.getDocumentOpinionList(documentOpinion);
    }

    public DocumentOpinion getDocumentOpinionByProcessInsId(String processInsId) {
        DocumentOpinion documentOpinion = new DocumentOpinion();
        documentOpinion.setProcessInsId(processInsId);
        List<DocumentOpinion> documentOpinionList = documentDao.getDocumentOpinionList(documentOpinion);
        if (CollectionUtils.isNotEmpty(documentOpinionList)) {
            return documentOpinionList.get(0);
        }
        return documentOpinion;
    }

    public DocumentOpinion saveDocumentOpinion(DocumentOpinion documentOpinion) {
        if (documentOpinion.getId() != null && documentOpinion.getId() > 0) {
            documentDao.updateDocumentOpinion(documentOpinion);
        } else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            documentOpinion.setCreator(processControllerComponent.getThisUser());
            documentOpinion.setCreated(DateUtils.now());
            documentOpinion.setUuid(uuid);
            documentDao.addDocumentOpinion(documentOpinion);
        }
        return documentOpinion;
    }

    @Transactional(rollbackFor = Exception.class)
    public void applySubmit(String appointUserAccount, Integer boxId, DocumentOpinion documentOpinion) throws BusinessException {
        documentOpinion = saveDocumentOpinion(documentOpinion);//保存数据
        //发起流程
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        String porcessNumber = MessageFormat.format("{0}({1}){2}", boxReDto.getId(), processControllerComponent.getThisUserInfo().getId(), DateUtils.formatDateToYMDHMS(new Date()));
        DocumentTemplate documentTemplate = documentDao.getDocumentTemplate(documentOpinion.getContractType());
        String folio = String.format("[%s]%s", documentTemplate.getTemplateName(), documentOpinion.getTitle());
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessEventExecutor(BaseProcessEvent.class);
        processInfo.setWorkPhaseId(documentOpinion.getContractType());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(DocumentOpinion.class));
        processInfo.setTableId(documentOpinion.getId());
        processInfo.setProjectId(documentOpinion.getProjectId());
        String processInsId = "";
        try {
            if (StringUtils.isBlank(appointUserAccount)) {
                appointUserAccount = processControllerComponent.getThisUser();
            }
            ProcessUserDto processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, appointUserAccount, false);//发起流程，并返回流程实例编号
            processInsId = processUserDto.getProcessInsId();
            documentOpinion.setProcessInsId(processInsId);
            documentOpinion.setStatus(ProcessStatusEnum.RUN.getValue());
            documentOpinion.setNumber(porcessNumber);
            saveDocumentOpinion(documentOpinion);
            documentOpinion = getDocumentOpinionByProcessInsId(processInsId);
            //更新附件
            SysAttachmentDto sysAttachment = new SysAttachmentDto();
            sysAttachment.setProcessInsId("0");
            sysAttachment.setTableId(processInfo.getTableId());
            sysAttachment.setCreater(processControllerComponent.getThisUser());
            String fieldName = baseDataDicService.getDataDicById(documentOpinion.getReportTypeId()).getFieldName();
            String reportFieldName = fieldName.toUpperCase().replace(".", "_");
            sysAttachment.setFieldsName(String.format("%s%s", reportFieldName, documentOpinion.getAreaGroupId()));
            sysAttachment.setTableName(processInfo.getTableName());
            SysAttachmentDto sysAttachmentNew = new SysAttachmentDto();
            sysAttachmentNew.setProcessInsId(processInsId);
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

    public void editSubmit(ApprovalModelDto approvalModelDto, DocumentOpinion documentOpinion) throws BusinessException {

        saveDocumentOpinion(documentOpinion);
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

    public Integer buildDoc(DocumentOpinion documentOpinion) throws BusinessException {
        if (documentOpinion.getId() == null || documentOpinion.getId() == 0) {
            documentOpinion.setProcessInsId("0");
        }
        documentOpinion = saveDocumentOpinion(documentOpinion);
        Map<String, String> textValues = new HashMap<String, String>();
        List<DocumentTemplateField> getFieldList = documentTemplateService.getFieldList(documentOpinion.getContractType());
        JSONObject jsonObject = JSON.parseObject(documentOpinion.getExtendConten());
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
        Map<String, String> textMap = getBaseEnumValue(documentOpinion.getProjectId());
        textValues.putAll(textMap);

        //取得数据替换成模板书签数据

        List<DocumentTemplateBookmark> listObject = documentTemplateService.getCmsTemplateBookmark(documentOpinion.getContractType());

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
        String tableName = FormatUtils.entityNameConvertToTableName(DocumentOpinion.class);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(documentOpinion.getId());
        sysAttachmentDto.setTableName(tableName);
        String fieldName = baseDataDicService.getDataDicById(documentOpinion.getReportTypeId()).getFieldName();
        String reportFieldName = fieldName.toUpperCase().replace(".", "_");
        sysAttachmentDto.setFieldsName(String.format("%s%s", reportFieldName, documentOpinion.getAreaGroupId()));
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setProjectId(documentOpinion.getProjectId());
        erpRpcAttachmentService.deleteAttachmentByDto(sysAttachmentDto);//先删除老数据
        String ftpFilePath = applicationConstant.getAppKey() + "/" + FormatUtils.underlineToCamel(tableName, false) + "/" + DateUtils.format(new Date(), DateUtils.MONTH_PATTERN);
        sysAttachmentDto.setFilePath(ftpFilePath);
        sysAttachmentDto.setProcessInsId(documentOpinion.getProcessInsId());
        String filesName = documentOpinion.getTitle();
        if (StringUtils.isBlank(filesName)) {
            filesName = "新建文件";
        }
        sysAttachmentDto.setFileName(String.format("%s", filesName));
        ///endregion

        //再将附件上传到相同位置
        try {
            DocumentTemplate documentTemplate = documentTemplateService.getDocumentTemplate(documentOpinion.getContractType());
            Map<String, String> document = documentWordUtils.createDocument(documentTemplate, wordValues, sysAttachmentDto);//将文本字段进行替换，并返回相应本地文件路径
            //对Word文件进行格式化操作
            ftpUtilsExtense.uploadFilesToFTP(document.get("ftpPath"), new FileInputStream(document.get("localFullPath")), document.get("ftpFileName"));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return documentOpinion.getId();
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
    public BootstrapTableVo getDocumentOpinionVoList(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        DocumentOpinion documentOpinion = new DocumentOpinion();
        documentOpinion.setProjectId(projectId);
        List<DocumentOpinion> list = documentDao.getDocumentOpinionList(documentOpinion);
        List<DocumentOpinionVo> vos = LangUtils.transform(list, p -> getDocumentOpinionVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DocumentOpinionVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public DocumentOpinionVo getDocumentOpinionVo(DocumentOpinion documentOpinion) {
        if (documentOpinion == null) return null;
        DocumentOpinionVo vo = new DocumentOpinionVo();
        BeanUtils.copyProperties(documentOpinion, vo);
        vo.setUserName(publicService.getUserNameByAccount(documentOpinion.getCreator()));
        return vo;
    }
}

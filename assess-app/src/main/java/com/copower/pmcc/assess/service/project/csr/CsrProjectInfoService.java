package com.copower.pmcc.assess.service.project.csr;

import com.alibaba.fastjson.JSON;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.CreateInsertHelp;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.common.enums.word.DataReplaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessParameterConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.dao.csr.*;
import com.copower.pmcc.assess.dto.input.base.BaseReportTemplateFilesDto;
import com.copower.pmcc.assess.dto.input.project.ProjectMemberDto;
import com.copower.pmcc.assess.dto.input.project.csr.CsrImportBorrowerDto;
import com.copower.pmcc.assess.dto.input.project.csr.CsrImportColumnDto;
import com.copower.pmcc.assess.dto.input.DataReplaceDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoGroupVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoVo;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;
import com.copower.pmcc.assess.service.BaseReportService;
import com.copower.pmcc.assess.service.base.*;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by kings on 2018-5-31.
 */
@Service
public class CsrProjectInfoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CsrProjectInfoDao csrProjectInfoDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CsrInvalidRuleService csrInvalidRuleService;
    @Autowired
    private CsrProjectInfoGroupService projectInfoGroupService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseParameterServcie baseParameterServcie;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private CsrBorrowerService csrBorrowerService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private CsrBorrowerDao csrBorrowerDao;
    @Autowired
    private CsrBorrowerMortgageDao csrBorrowerMortgageDao;
    @Autowired
    private CsrcalculationDao csrcalculationDao;
    @Autowired
    private CsrContractDao csrContractDao;
    @Autowired
    private CsrGuarantorDao csrGuarantorDao;
    @Autowired
    private CsrLitigationDao csrLitigationDao;
    @Autowired
    private CsrPrincipalInterestDao csrPrincipalInterestDao;
    @Autowired
    private BaseReplaceRecordService baseReplaceRecordService;
    @Autowired
    private BaseReportService baseReportService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DatabaseJdbcService databaseJdbcService;

    /**
     * 获取vo
     *
     * @param csrProjectInfo
     * @return
     */
    public CsrProjectInfoVo getCsrProjectInfoVo(CsrProjectInfo csrProjectInfo) {
        if (csrProjectInfo == null)
            return null;
        CsrProjectInfoVo csrProjectInfoVo = new CsrProjectInfoVo();
        BeanUtils.copyProperties(csrProjectInfo, csrProjectInfoVo);
        //项目类型
        if (csrProjectInfo.getProjectClassId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getProjectClassifyById(csrProjectInfo.getProjectClassId());
            if (projectClassify != null) {
                csrProjectInfoVo.setProjectClassName(projectClassify.getName());
            }
        }
        //项目类别
        if (csrProjectInfo.getProjectTypeId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getProjectClassifyById(csrProjectInfo.getProjectTypeId());
            if (projectClassify != null) {
                csrProjectInfoVo.setProjectTypeName(projectClassify.getName());
            }
        }
        //项目范围
        if (csrProjectInfo.getProjectCategoryId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getProjectClassifyById(csrProjectInfo.getProjectCategoryId());
            if (projectClassify != null) {
                csrProjectInfoVo.setProjectCategoryName(projectClassify.getName());
            }
        }

        if (StringUtils.isNotBlank(csrProjectInfo.getDistributionUser())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(csrProjectInfo.getDistributionUser());
            if (sysUser != null)
                csrProjectInfoVo.setDistributionUserName(sysUser.getUserName());
        }
        if (csrProjectInfo.getEntrustPurpose() != null) {
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(csrProjectInfo.getEntrustPurpose());
            if (baseDataDic != null)
                csrProjectInfoVo.setEntrustPurposeName(baseDataDic.getName());
        }
        if (csrProjectInfo.getCustomerType() != null) {
            CustomerTypeEnum[] customerTypeEnums = CustomerTypeEnum.values();
            for (CustomerTypeEnum customerTypeEnum : customerTypeEnums) {
                if (csrProjectInfo.getCustomerType().equals(customerTypeEnum.getId())) {
                    csrProjectInfoVo.setCustomerTypeName(customerTypeEnum.getName());
                }
            }
        }
        List<CsrProjectInfoGroup> csrProjectInfoGroupVos = projectInfoGroupService.groupList(csrProjectInfo.getId());
        List<CsrProjectInfoGroupVo> vos = new ArrayList<>();
        for (CsrProjectInfoGroup csrProjectInfoGroup : csrProjectInfoGroupVos) {
            vos.add(projectInfoGroupService.change(csrProjectInfoGroup));
        }
        csrProjectInfoVo.setCsrProjectInfoGroupVos(vos);
        return csrProjectInfoVo;
    }

    /**
     * 根据流程实例id获取数据
     *
     * @param processInsId
     * @return
     */
    public CsrProjectInfoVo getCsrProjectInfoVo(String processInsId) {
        CsrProjectInfo csrProjectInfo = csrProjectInfoDao.getCsrProjectInfo(processInsId);
        return getCsrProjectInfoVo(csrProjectInfo);
    }

    public CsrProjectInfoVo getById(Integer id) {
        CsrProjectInfo csrProjectInfo = csrProjectInfoDao.getCsrProjectInfoById(id);
        return getCsrProjectInfoVo(csrProjectInfo);
    }

    /**
     * 保存债权项目信息
     *
     * @param csrProjectInfo
     */
    public void saveCsrProjectInfo(CsrProjectInfo csrProjectInfo) {
        if (csrProjectInfo.getId() != null && csrProjectInfo.getId() > 0) {
            csrProjectInfoDao.updateCsrProjectInfo(csrProjectInfo);
        } else {
            csrProjectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getName());
            csrProjectInfo.setCreator(commonService.thisUserAccount());
            csrProjectInfoDao.addCsrProjectInfo(csrProjectInfo);
        }
    }

    /**
     * 发起项目
     *
     * @param csrProjectInfo
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void csrProjectApply(CsrProjectInfo csrProjectInfo) throws BusinessException {
        //1.保存项目信息 2发起流程
        if (csrProjectInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        saveCsrProjectInfo(csrProjectInfo);

        //更新过滤规则外键
        csrInvalidRuleService.updateCsrProjectId(csrProjectInfo.getId());

        //更新附件表tableId
        baseAttachmentService.updateTableIdByTableName(AssessTableNameConstant.CSR_PROJECT_INFO, csrProjectInfo.getId());

        //清空原数据
        cleanImportData(csrProjectInfo);

        //读取导入的数据
        readImportData(csrProjectInfo);

        //发起流程
        String boxName = baseParameterServcie.getParameterValues(AssessParameterConstant.CSR_PROJECT_APPLY_PROCESS_KEY);
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(csrProjectInfo.getName());//流程描述
        processInfo.setTableName(AssessTableNameConstant.CSR_PROJECT_INFO);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setStartUser(commonService.thisUserAccount());
        processInfo.setProcessEventExecutorName(BaseProcessEvent.class.getSimpleName());
        try {
            processInfo.setTableId(csrProjectInfo.getId());
            ProcessUserDto processUserDto = processControllerComponent.processStart(processInfo, csrProjectInfo.getDistributionUser(), false);

            csrProjectInfo.setStatus(ProcessStatusEnum.RUN.getValue());
            csrProjectInfo.setProcessInsId(processUserDto.getProcessInsId());
            csrProjectInfoDao.updateCsrProjectInfo(csrProjectInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 清空原导入的数据
     *
     * @param csrProjectInfo
     */
    private void cleanImportData(CsrProjectInfo csrProjectInfo) {
        csrPrincipalInterestDao.deleteByCsrProjectId(csrProjectInfo.getId());
        csrLitigationDao.deleteByCsrProjectId(csrProjectInfo.getId());
        csrGuarantorDao.deleteByCsrProjectId(csrProjectInfo.getId());
        csrContractDao.deleteByCsrProjectId(csrProjectInfo.getId());
        csrcalculationDao.deleteByCsrProjectId(csrProjectInfo.getId());
        csrBorrowerMortgageDao.deleteByCsrProjectId(csrProjectInfo.getId());
        csrBorrowerDao.deleteByCsrProjectId(csrProjectInfo.getId());

    }

    /**
     * 读取数据
     *
     * @param csrProjectInfo
     */
    private void readImportData(CsrProjectInfo csrProjectInfo) {
        try {
            //将ftp的附件下载到本地
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(AssessTableNameConstant.CSR_PROJECT_INFO);
            queryParam.setTableId(csrProjectInfo.getId());
            List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(queryParam);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                SysAttachmentDto sysAttachment = attachmentList.get(0);
                String fullPath = "";
                if (!FileUtils.checkFileExists(new File(fullPath))) {
                    try {
                        fullPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachment.getId());
                    } catch (Exception e) {
                        //拷贝失败
                        logger.error(e.getMessage(), e);
                    }
                }
                importData(fullPath, csrProjectInfo);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 立项审批
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void crsProjectApproval(ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        //组项目 立项
        if (StringUtils.equalsIgnoreCase(approvalModelDto.getConclusion(), TaskHandleStateEnum.AGREE.getValue())) {
            CsrProjectInfo csrProjectInfo = csrProjectInfoDao.getCsrProjectInfo(approvalModelDto.getProcessInsId());
            initGroupProject(csrProjectInfo);
        }
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    /**
     * 返回修改
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void crsProjectEdit(CsrProjectInfo csrProjectInfo, ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        if (csrProjectInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        saveCsrProjectInfo(csrProjectInfo);
        //清空原数据
        cleanImportData(csrProjectInfo);
        //读取数据
        readImportData(csrProjectInfo);
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setNextApproval(Lists.newArrayList(csrProjectInfo.getDistributionUser()));
        approvalModelDto.setAppointUserAccount(csrProjectInfo.getDistributionUser());
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    /**
     * 发起 组项目 项目立项
     *
     * @param csrProjectInfo
     */
    public void initGroupProject(CsrProjectInfo csrProjectInfo) {
        List<CsrProjectInfoGroup> csrProjectInfoGroups = projectInfoGroupService.groupList(csrProjectInfo.getId());
        ProjectInfo projectInfo = null;
        for (CsrProjectInfoGroup infoGroup : csrProjectInfoGroups) {
            if (!ObjectUtils.isEmpty(infoGroup)) {
                try {
                    //保存项目信息
                    projectInfo = new ProjectInfo();
                    projectInfo.setProjectClassId(0);
                    projectInfo.setEntrustPurpose(csrProjectInfo.getEntrustPurpose());
                    projectInfo.setProjectClassId(csrProjectInfo.getProjectClassId());
                    projectInfo.setProjectTypeId(csrProjectInfo.getProjectTypeId());
                    projectInfo.setProjectCategoryId(csrProjectInfo.getProjectCategoryId());
                    projectInfo.setCreator(csrProjectInfo.getCreator());
                    projectInfo.setProjectName(infoGroup.getProjectName());
                    projectInfo.setRemarks(csrProjectInfo.getRemark());
                    projectInfo.setValuationDate(csrProjectInfo.getValuationDate());
                    projectInfo.setCompleteDateStart(csrProjectInfo.getValuationDate());
                    int projectId = projectInfoService.saveProjectInfo_returnID(projectInfo);

                    //保存项目成员
                    ProjectMemberDto projectMemberDto = new ProjectMemberDto();
                    projectMemberDto.setProjectId(projectId);
                    projectMemberDto.setCreator(csrProjectInfo.getCreator());
                    projectMemberDto.setUserAccountManager(infoGroup.getProjectManager());
                    projectMemberDto.setUserAccountMember(infoGroup.getProjectMember());
                    int k = projectMemberService.saveReturnId(projectMemberDto);

                    //回写借款人的项目id
                    List<CsrBorrower> csrBorrowers = csrBorrowerService.getCsrBorrowerListByCsrProjectID(csrProjectInfo.getId(), infoGroup.getId());
                    for (CsrBorrower csrBorrower : csrBorrowers) {
                        csrBorrower.setProjectId(projectId);
                        csrBorrowerService.update(csrBorrower);
                    }
                    projectInfoService.updateProjectInfo(projectInfo);
                    projectMemberDto.setProjectId(projectId);
                    projectMemberService.updateProjectMember(projectMemberDto);
                    //项目立项
                    projectInfoService.initProjectInfo(projectInfo);
                } catch (Exception e) {
                    try {
                        logger.error("初始化项目异常，原因：" + e.getMessage());
                        throw e;
                    } catch (Exception e1) {

                    }
                }
            }
        }
    }

    /**
     * 是否过滤
     *
     * @param ruleList
     * @param columnName
     * @param columnValue
     * @return
     */
    private boolean isFilter(List<CsrInvalidRule> ruleList, String columnName, String columnValue) {
        for (CsrInvalidRule csrInvalidRule : ruleList) {
            if (StringUtils.equals(csrInvalidRule.getColumnName(), columnName) && StringUtils.equals(csrInvalidRule.getColumnValue(), columnValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数据导入
     *
     * @param path
     * @param csrProjectInfo
     * @throws IOException
     */
    public void importData(String path, CsrProjectInfo csrProjectInfo) throws IOException {
        InputStream is = new FileInputStream(path);
        Workbook hssfWorkbook = PoiUtils.isExcel2003(path) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        int startRowNumber = csrProjectInfo.getStartRowNumber();//读取业务数据的起始行序号
        List<BaseReportTemplateVo> reportTemplateList = getReportTemplateList(csrProjectInfo);
        List<CsrInvalidRule> ruleList = csrInvalidRuleService.getRuleList(csrProjectInfo.getId());//过滤规则数据
        HashMap<Integer, String> invalidRuleIndexMap = Maps.newHashMap();//需要参与过滤的列
        HashMap<Integer, CsrImportColumnDto> hashMap = Maps.newHashMap();
        Row row = null;
        Cell cell = null;
        List<CsrImportBorrowerDto> importBorrowerDtoList = Lists.newArrayList();
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            //没有在基础配置的字段中不做处理
            row = sheet.getRow(rowNum);
            if (row == null)
                return;
            if (rowNum == 0) {
                //确定单元格对应的字段 将每一列与配置的字段对应
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                    if (cell == null)
                        continue;
                    String value = PoiUtils.getCellValue(cell);
                    if (StringUtils.isBlank(value))
                        continue;
                    //关联到配置 需读取报告对应的配置
                    CsrImportColumnDto csrImportColumnDto = new CsrImportColumnDto();
                    csrImportColumnDto.setColumnIndex(i);
                    csrImportColumnDto.setColumnName(value);
                    //待处理 设置书签对应的表与字段
                    BaseReportTemplateVo baseReportTemplateVo = baseReportService.getBaseReportTemplate(reportTemplateList, value);
                    if (baseReportTemplateVo != null) {
                        csrImportColumnDto.setTableName(baseReportTemplateVo.getTableName());
                        csrImportColumnDto.setFieldName(baseReportTemplateVo.getColumnName());
                    }
                    hashMap.put(i, csrImportColumnDto);

                    //记录需要过滤行的 序号
                    for (CsrInvalidRule csrInvalidRule : ruleList) {
                        if (StringUtils.equals(csrInvalidRule.getColumnName(), value)) {
                            invalidRuleIndexMap.put(i, value);
                        }
                    }
                }

            } else if (rowNum + 1 >= startRowNumber) {
                try {
                    //读取数据 过滤行 过滤数据
                    if (!invalidRuleIndexMap.isEmpty()) {
                        boolean isFilter = false;
                        for (Map.Entry<Integer, String> integerStringEntry : invalidRuleIndexMap.entrySet()) {
                            isFilter = isFilter(ruleList, integerStringEntry.getValue(), PoiUtils.getCellValue(row.getCell(integerStringEntry.getKey())));
                            if (isFilter)
                                continue;
                        }
                        if (isFilter)
                            continue;
                    }

                    //取到核心字段验证 确定数据的保存条数
                    //先取到该行需要保存的数据
                    if (!hashMap.isEmpty()) {
                        String secondLevelBranch = "";//二级分行
                        String idNumber = "";//客户证件号
                        String contractNumber = "";//合同编号

                        CsrBorrower csrBorrower = null;//借款人
                        CsrBorrowerMortgage csrBorrowerMortgage = null;//抵押物
                        CsrContract csrContract = null;//合同
                        CsrGuarantor csrGuarantor = null;//保证人
                        CsrLitigation csrLitigation = null;//诉讼保全
                        CsrPrincipalInterest csrPrincipalInterest = null;//本金利息
                        //region 处理数据到核心字段 实体类中

                        for (Map.Entry<Integer, CsrImportColumnDto> entry : hashMap.entrySet()) {
                            CsrImportColumnDto columnDto = entry.getValue();
                            if (StringUtils.isNotBlank(columnDto.getTableName()) && StringUtils.isNotBlank(columnDto.getFieldName())) {
                                //找出 二级分行 客户证件号 合同编号 并将数据包含到对应的实体对象中
                                cell = row.getCell(entry.getKey());
                                if (cell == null)
                                    continue;
                                if (StringUtils.equals(PoiUtils.getCellValue(cell), "黄运全")) {
                                    int j = 0;
                                    int f = j;//
                                }
                                switch (columnDto.getTableName()) {
                                    case AssessTableNameConstant.CSR_BORROWER:
                                        if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_SECOND_LEVEL_BRANCH)) {
                                            secondLevelBranch = PoiUtils.getCellValue(cell);
                                        }
                                        if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_ID_NUMBER)) {
                                            idNumber = PoiUtils.getCellValue(cell);
                                        }
                                        csrBorrower = csrBorrower == null ? new CsrBorrower() : csrBorrower;
                                        try {
                                            Reflections.setFieldValue(csrBorrower, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case AssessTableNameConstant.CSR_BORROWER_MORTGAGE:
                                        csrBorrowerMortgage = csrBorrowerMortgage == null ? new CsrBorrowerMortgage() : csrBorrowerMortgage;
                                        try {
                                            Reflections.setFieldValue(csrBorrowerMortgage, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case AssessTableNameConstant.CSR_CONTRACT:
                                        if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_CONTRACT_CONTRACT_NUMBER)) {
                                            contractNumber = PoiUtils.getCellValue(cell);
                                        }
                                        csrContract = csrContract == null ? new CsrContract() : csrContract;
                                        try {
                                            Reflections.setFieldValue(csrContract, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case AssessTableNameConstant.CSR_GUARANTOR:
                                        csrGuarantor = csrGuarantor == null ? new CsrGuarantor() : csrGuarantor;
                                        try {
                                            Reflections.setFieldValue(csrGuarantor, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case AssessTableNameConstant.CSR_LITIGATION:
                                        csrLitigation = csrLitigation == null ? new CsrLitigation() : csrLitigation;
                                        try {
                                            Reflections.setFieldValue(csrLitigation, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case AssessTableNameConstant.CSR_PRINCIPAL_INTEREST:
                                        csrPrincipalInterest = csrPrincipalInterest == null ? new CsrPrincipalInterest() : csrPrincipalInterest;
                                        try {
                                            Reflections.setFieldValue(csrPrincipalInterest, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                }
                            }
                        }
                        //endregion

                        //确定客户数据是否保存
                        CsrImportBorrowerDto csrImportBorrowerDto = useExistBorrower(importBorrowerDtoList, secondLevelBranch, idNumber, contractNumber);
                        CsrBorrower borrower = csrImportBorrowerDto.getCsrBorrower();
                        if (borrower == null) {
                            csrImportBorrowerDto.setCsrBorrower(csrBorrower);
                        }
                        if (csrBorrowerMortgage != null) {
                            csrImportBorrowerDto.getCsrBorrowerMortgageList().add(csrBorrowerMortgage);
                        }
                        if (csrContract != null) {
                            csrImportBorrowerDto.getCsrContractList().add(csrContract);
                        }
                        if (csrGuarantor != null) {
                            csrImportBorrowerDto.getCsrGuarantorList().add(csrGuarantor);
                        }
                        if (csrLitigation != null) {
                            csrImportBorrowerDto.getCsrLitigationList().add(csrLitigation);
                        }
                        if (csrPrincipalInterest != null) {
                            csrImportBorrowerDto.getCsrPrincipalInterestList().add(csrPrincipalInterest);
                        }
                    }
                } catch (Exception e) {
                    //找出错误行数据，并返回错误给前端用户
                    e.printStackTrace();
                }

            }
        }
        importToDataBaseExtend(csrProjectInfo.getId(), importBorrowerDtoList);
        is.close();
    }

    /**
     * 获取债权报告模板所有配置的书签
     *
     * @param csrProjectInfo
     * @return
     */
    private List<BaseReportTemplateVo> getReportTemplateList(CsrProjectInfo csrProjectInfo) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        BaseReportTemplateFilesDto reportTemplateFileDto = baseReportService.getReportTemplateFile(csrProjectInfo.getEntrustmentUnitId(), baseDataDic.getId(), csrProjectInfo.getCustomerType(),
                csrProjectInfo.getProjectTypeId(), csrProjectInfo.getProjectCategoryId());
        return reportTemplateFileDto.getBaseReportTemplateVoList();
    }

    /**
     * 数据库字段名转换成类字段名
     *
     * @param dbFieldName
     * @return
     */
    private String dbFieldToBeanField(String dbFieldName) {
        return FormatUtils.toLowerCaseFirstChar(FormatUtils.underlineToCamel(dbFieldName, false));
    }

    /**
     * 生成报告
     */
    public void generateReport(Integer csrProjectId, String borrowerIds) throws BusinessException {
        //1.找到替换模板，复制模板
        //2.针对模板配置的书签，找到书签所对应的值
        //3.将替换数据写入到替换记录表中

        //债权目前所有的数据都源于 固定的7张表

        //提供寻找模板的方法 与 客户 客户类型 委托目的相关
        if (StringUtils.isBlank(borrowerIds))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());

        CsrProjectInfo csrProjectInfo = csrProjectInfoDao.getCsrProjectInfoById(csrProjectId);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);

        BaseReportTemplateFilesDto reportTemplateFileDto = baseReportService.getReportTemplateFile(csrProjectInfo.getEntrustmentUnitId(), baseDataDic.getId(), csrProjectInfo.getCustomerType(),
                csrProjectInfo.getProjectTypeId(), csrProjectInfo.getProjectCategoryId());
        if (reportTemplateFileDto == null)
            throw new BusinessException("未找到对应的报告模板");

        SysAttachmentDto queryParam = new SysAttachmentDto();
        queryParam.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplateFiles.class));
        queryParam.setTableId(reportTemplateFileDto.getBaseReportTemplateFiles().getId());
        queryParam.setFieldsName(BaseReportTemplateTypeEnum.REPORT.getKey());
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(queryParam);
        if (CollectionUtils.isEmpty(attachmentList))
            throw new BusinessException("未找到对应的报告模板附件");
        SysAttachmentDto baseAttachment = attachmentList.get(0);//模板附件
        List<BaseReportTemplateVo> baseReportTemplateList = reportTemplateFileDto.getBaseReportTemplateVoList();
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(borrowerIds));
        SysAttachmentDto attachment = new SysAttachmentDto();
        attachment.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrower.class));
        attachment.setFieldsName(AssessFieldNameConstant.CSR_BORROWER_REPORT);
        for (Integer integer : list) {
            try {
                //拷贝表数据及FTP附件
                attachment.setTableId(integer);
                SysAttachmentDto ftpAttachment = baseAttachmentService.copyFtpAttachment(baseAttachment.getId(), attachment);
                //获取数据
                if (CollectionUtils.isNotEmpty(baseReportTemplateList)) {
                    //1.检查配置的书签有几张表
                    //2.先找到主表信息,再找从表数据
                    //3.循环书签
                    HashSet<String> tableSet = Sets.newHashSet();
                    for (BaseReportTemplateVo reportTemplateVo : baseReportTemplateList) {
                        BaseReportDataPoolTypeEnum dataPoolTypeEnum = BaseReportDataPoolTypeEnum.getEnumByName(reportTemplateVo.getDataPoolType());
                        switch (dataPoolTypeEnum) {
                            case COLUMNS:
                                tableSet.add(reportTemplateVo.getTableName());
                                break;
                        }
                    }
                    Map<String, Map<String, Object>> map = Maps.newHashMap();
                    Map<String, Object> objectMap = null;
                    CsrBorrower csrBorrower = csrBorrowerDao.getCsrBorrowerByID(integer);
                    String borrowerId = csrBorrower.getBorrowerId();
                    String sql = "";
                    for (String tableName : tableSet) {
                        switch (tableName) {
                            case AssessTableNameConstant.CSR_BORROWER:
                                objectMap = databaseJdbcService.getObjectSingle(AssessTableNameConstant.CSR_BORROWER, integer);
                                if (objectMap != null)
                                    map.put(tableName, objectMap);
                                break;
                            case AssessTableNameConstant.CSR_BORROWER_MORTGAGE:
                            case AssessTableNameConstant.CSR_CONTRACT:
                            case AssessTableNameConstant.CSR_GUARANTOR:
                            case AssessTableNameConstant.CSR_LITIGATION:
                            case AssessTableNameConstant.CSR_PRINCIPAL_INTEREST:
                            case AssessTableNameConstant.CSR_CALCULATION:
                                sql = String.format("select * from %s where borrower_id='%s'", tableName, borrowerId);
                                List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
                                if (CollectionUtils.isNotEmpty(mapList)) {
                                    map.put(tableName, mapList.get(0));
                                }
                                break;
                            case AssessTableNameConstant.CSR_PROJECT_INFO:
                                sql = String.format("select * from %s where id=%s", tableName, csrProjectId);
                                objectMap = databaseJdbcService.getObjectSingle(sql, new Object[0]);
                                if (objectMap != null)
                                    map.put(tableName, objectMap);
                                break;
                        }
                    }

                    List<DataReplaceDto> dataReplaceDtoList = Lists.newArrayList();
                    for (BaseReportTemplateVo baseReportTemplateVo : baseReportTemplateList) {
                        //循环所有书签 依次找到书签或文本对应的值

                        Map<String, Object> stringObjectMap = map.get(baseReportTemplateVo.getTableName());
                        if (stringObjectMap == null)
                            continue;//不处理
                        Object o = stringObjectMap.get(baseReportTemplateVo.getColumnName());
                        if (o == null)
                            continue;//不处理
                        String value = String.valueOf(o);
                        if (StringUtils.isEmpty(value))
                            continue;//空值不处理

                        DataReplaceDto dataReplaceDto = new DataReplaceDto();
                        dataReplaceDto.setKey(String.format("${%s}", baseReportTemplateVo.getBookmarkName()));
                        dataReplaceDto.setValue(value);
                        BaseReportMarkbookTypeEnum reportMarkbookTypeEnum = BaseReportMarkbookTypeEnum.getEnumByName(baseReportTemplateVo.getTemplateType());
                        switch (reportMarkbookTypeEnum) {
                            case TEXT:
                                dataReplaceDto.setDataReplaceTypeEnum(DataReplaceTypeEnum.TEXT);
                                break;
                            case BOOKMARK:
                                dataReplaceDto.setDataReplaceTypeEnum(DataReplaceTypeEnum.BOOKMARK);

                                //也可能是文件的替换
                                break;
                            case TEMPLATE:
                                //暂不做处理
                                break;
                        }
                        dataReplaceDtoList.add(dataReplaceDto);
                    }
                    //写入到替换数据表
                    BaseReplaceRecord baseReplaceRecord = new BaseReplaceRecord();
                    baseReplaceRecord.setAttachmentId(ftpAttachment.getId());
                    baseReplaceRecord.setBisReplace(false);
                    baseReplaceRecord.setCreator(commonService.thisUserAccount());
                    baseReplaceRecord.setContent(JSON.toJSONString(dataReplaceDtoList));
                    baseReplaceRecordService.saveBaseReplaceRecord(baseReplaceRecord);

                    //将书签的替换成相应内容
                    baseReplaceRecordService.replaceRecordContent(baseReplaceRecord);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用已存在的借款人
     *
     * @param borrowerDtos
     * @param secondLevelBranch
     * @param idNumber
     * @param contractNumber
     * @return
     */
    private CsrImportBorrowerDto useExistBorrower(List<CsrImportBorrowerDto> borrowerDtos, String secondLevelBranch, String idNumber, String contractNumber) {
        CsrImportBorrowerDto csrImportBorrowerDto = null;
        if (CollectionUtils.isNotEmpty(borrowerDtos)) {
            for (CsrImportBorrowerDto borrowerDto : borrowerDtos) {
                CsrBorrower csrBorrower = borrowerDto.getCsrBorrower();
                if (csrBorrower != null) {
                    List<CsrContract> csrContractList = borrowerDto.getCsrContractList();
                    if (StringUtils.equals(csrBorrower.getSecondLevelBranch(), secondLevelBranch) && StringUtils.equals(csrBorrower.getIdNumber(), idNumber)) {
                        if (CollectionUtils.isNotEmpty(csrContractList)) {
                            for (CsrContract csrContract : csrContractList) {
                                if (StringUtils.equals(csrContract.getContractNumber(), contractNumber)) {
                                    return borrowerDto;
                                }
                            }
                        }
                    }
                }
            }
        }
        csrImportBorrowerDto = new CsrImportBorrowerDto();
        csrImportBorrowerDto.setCsrBorrowerMortgageList(Lists.newArrayList());
        csrImportBorrowerDto.setCsrContractList(Lists.newArrayList());
        csrImportBorrowerDto.setCsrGuarantorList(Lists.newArrayList());
        csrImportBorrowerDto.setCsrLitigationList(Lists.newArrayList());
        csrImportBorrowerDto.setCsrPrincipalInterestList(Lists.newArrayList());
        borrowerDtos.add(csrImportBorrowerDto);
        return csrImportBorrowerDto;
    }

    /**
     * 数据导入到数据库中
     *
     * @param borrowerDtos
     */
    private void importToDataBaseExtend(Integer csrProjectId, List<CsrImportBorrowerDto> borrowerDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(borrowerDtos)) {
            for (CsrImportBorrowerDto borrowerDto : borrowerDtos) {
                CsrBorrower csrBorrower = borrowerDto.getCsrBorrower();
                if (csrBorrower != null) {
                    if (StringUtils.isBlank(csrBorrower.getBorrowerId())) {
                        //做新增处理
                        csrBorrower.setBorrowerId(UUID.randomUUID().toString());
                        csrBorrower.setCsrProjectId(csrProjectId);
                        csrBorrower.setBisImport(true);
                        csrBorrower.setCreator(commonService.thisUserAccount());
                        stringBuilder.append(new CreateInsertHelp().createInsert(csrBorrower));
                    }
                }
                List<CsrBorrowerMortgage> csrBorrowerMortgageList = borrowerDto.getCsrBorrowerMortgageList();
                if (CollectionUtils.isNotEmpty(csrBorrowerMortgageList)) {
                    for (CsrBorrowerMortgage csrBorrowerMortgage : csrBorrowerMortgageList) {
                        csrBorrowerMortgage.setCsrProjectId(csrProjectId);
                        csrBorrowerMortgage.setBorrowerId(csrBorrower.getBorrowerId());
                        csrBorrowerMortgage.setBisImport(true);
                        csrBorrowerMortgage.setCreator(commonService.thisUserAccount());
                        stringBuilder.append(new CreateInsertHelp().createInsert(csrBorrowerMortgage));
                    }
                }
                List<CsrContract> csrContractList = borrowerDto.getCsrContractList();
                if (CollectionUtils.isNotEmpty(csrContractList)) {
                    for (CsrContract csrContract : csrContractList) {
                        csrContract.setCsrProjectId(csrProjectId);
                        csrContract.setBorrowerId(csrBorrower.getBorrowerId());
                        csrContract.setBisImport(true);
                        csrContract.setCreator(commonService.thisUserAccount());
                        stringBuilder.append(new CreateInsertHelp().createInsert(csrContract));
                    }
                }
                List<CsrGuarantor> csrGuarantorList = borrowerDto.getCsrGuarantorList();
                if (CollectionUtils.isNotEmpty(csrGuarantorList)) {
                    for (CsrGuarantor csrGuarantor : csrGuarantorList) {
                        csrGuarantor.setCsrProjectId(csrProjectId);
                        csrGuarantor.setBorrowerId(csrBorrower.getBorrowerId());
                        csrGuarantor.setBisImport(true);
                        csrGuarantor.setCreator(commonService.thisUserAccount());
                        stringBuilder.append(new CreateInsertHelp().createInsert(csrGuarantor));
                    }
                }
                List<CsrLitigation> csrLitigationList = borrowerDto.getCsrLitigationList();
                if (CollectionUtils.isNotEmpty(csrLitigationList)) {
                    for (CsrLitigation csrLitigation : csrLitigationList) {
                        csrLitigation.setCsrProjectId(csrProjectId);
                        csrLitigation.setBorrowerId(csrBorrower.getBorrowerId());
                        csrLitigation.setBisImport(true);
                        csrLitigation.setCreator(commonService.thisUserAccount());
                        stringBuilder.append(new CreateInsertHelp().createInsert(csrLitigation));
                    }
                }
                List<CsrPrincipalInterest> csrPrincipalInterestList = borrowerDto.getCsrPrincipalInterestList();
                if (CollectionUtils.isNotEmpty(csrPrincipalInterestList)) {
                    for (CsrPrincipalInterest csrPrincipalInterest : csrPrincipalInterestList) {
                        csrPrincipalInterest.setCsrProjectId(csrProjectId);
                        csrPrincipalInterest.setBorrowerId(csrBorrower.getBorrowerId());
                        csrPrincipalInterest.setBisImport(true);
                        csrPrincipalInterest.setCreator(commonService.thisUserAccount());
                        stringBuilder.append(new CreateInsertHelp().createInsert(csrPrincipalInterest));
                    }
                }
            }
            jdbcTemplate.execute(stringBuilder.toString());
        }
    }

    /**
     * 债权人列表信息
     *
     * @return
     */
    public BootstrapTableVo csrProjectInfoList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrProjectInfo> csrProjectInfos = csrProjectInfoDao.getCsrProjectInfoList(name);
        List<CsrProjectInfoVo> vos = new ArrayList<CsrProjectInfoVo>();
        for (CsrProjectInfo projectInfo : csrProjectInfos) {
            vos.add(change(projectInfo));
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public CsrProjectInfoVo change(CsrProjectInfo csrProjectInfo) {
        CsrProjectInfoVo vo = new CsrProjectInfoVo();
        BeanUtils.copyProperties(csrProjectInfo, vo);
        if (!org.springframework.util.StringUtils.isEmpty(csrProjectInfo.getStatus())) {
            vo.setStatusName(getProjectStatusEnum(csrProjectInfo.getStatus()));
        }
        if (!ObjectUtils.isEmpty(csrProjectInfo.getCustomerType())) {
            vo.setCustomerTypeName(getCustomerTypeEnum(csrProjectInfo.getCustomerType()));
        }
        return vo;
    }

    public String getProjectStatusEnum(String key) {
        String name = "";
        for (ProjectStatusEnum s : ProjectStatusEnum.values()) {
            if (key.equals(s.getKey())) {
                name = s.getName();
            }
        }
        return name;
    }

    public String getCustomerTypeEnum(Integer key) {
        String name = "";
        for (CustomerTypeEnum s : CustomerTypeEnum.values()) {
            if (s.getId().equals(key)) {
                name = s.getName();
            }
        }
        return name;
    }

    /**
     * @param ids
     */
    public void generateTemp(String ids) {
        List<String> integerList = FormatUtils.transformString2List(ids);
        int i = 1;
        for (String sjhth : integerList) {
            SysAttachmentDto attachment = new SysAttachmentDto();
            attachment.setTableName("sheet1");
            attachment.setFieldsName("report");
            try {
                List<Map<String, Object>> mapList = jdbcTemplate.queryForList("SELECT  * from sheet1 where sjhth='" + sjhth + "'");
                attachment.setFileName(String.format("%s%s-%s号%s的报告", String.valueOf(mapList.get(0).get("role")), String.valueOf(mapList.get(0).get("version")), String.valueOf(mapList.get(0).get
                        ("number")), String.valueOf(mapList.get(0).get("khxm"))));
                //templateSetService 取报告模板

                //

                BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName("single.csr");
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName("report.type.preaudit");
                Integer templateId = baseReportService.getReportTemplateFiles(0, baseProjectClassify.getId(), baseDataDic.getId(), 0, 0);

                SysAttachmentDto ftpAttachment = baseAttachmentService.copyFtpAttachment(templateId, attachment);
                String localFullPath = baseAttachmentService.downloadFtpFileToLocal(ftpAttachment.getId());

                List<KeyValueDto> valueDtoList = baseReportService.getReportTemplate(0, baseProjectClassify.getId(), baseDataDic.getId(), 0, 0);
                int k = mapList.size();
                for (Map<String, Object> map : mapList) {
                    for (KeyValueDto keyValueDto : valueDtoList) {
                        String local = baseAttachmentService.downloadFtpFileToLocal(Integer.valueOf(keyValueDto.getValue()));
                        Map<String, String> stringMap = toMapString(map);
                        if (mapList.size() > 1) {
                            stringMap.put("${dywxh}", "抵押物" + k);
                            stringMap.put("${jzfxdywxh}", "抵押物" + k);
                        } else {
                            stringMap.put("${dywxh}", "抵押物");
                            stringMap.put("${jzfxdywxh}", "抵押物");
                        }
                        AsposeUtils.replaceText(local, stringMap);

                        Document doc = new Document(localFullPath);
                        DocumentBuilder builder = new DocumentBuilder(doc);
                        builder.moveToBookmark(keyValueDto.getKey());
                        Document document = new Document(local);
                        builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
                        doc.save(localFullPath);
                    }
                    k--;
                }

                if (CollectionUtils.isNotEmpty(mapList)) {
                    try {
                        Map<String, String> stringMap = toMapString(mapList.get(0));
                        AsposeUtils.replaceText(localFullPath, stringMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //再将附件上传到相同位置
                try {
                    ftpUtilsExtense.uploadFilesToFTP(ftpAttachment.getFilePath(), new FileInputStream(localFullPath), ftpAttachment.getFtpFileName());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

                jdbcTemplate.update(String.format("update sheet1 set attachment_id=%s where sjhth='%s'", ftpAttachment.getId(), sjhth));
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, String> toMapString(Map<String, Object> map) {
        Map<String, String> stringMap = Maps.newHashMap();
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            String value = String.valueOf(stringObjectEntry.getValue());
            if (StringUtils.isBlank(value) || value == "null") {
                value = "";
            }
            switch (stringObjectEntry.getKey()) {
                case "tdxz":

                {
                    if (StringUtils.isNotBlank(value)) {
                        value = String.format("土地性质%s，面积${tdmj}平方米;", value);
                    } else {
                        value = "土地证未提供;";
                    }
                    break;
                }
                case "dkffsj":
                case "htqdr":
                case "fxjzr":
                case "bgyxq": {
                    if (StringUtils.isNotBlank(value)) {

                        try {
                            value = value.replaceAll("/", "-");
                            String format = DateUtils.format(DateUtils.parse(value), DateUtils.DATE_CHINESE_PATTERN);
                            value = format;
                        } catch (Exception e) {

                        }
                    }
                    break;
                }
                case "bgcjsj": {
                    if (StringUtils.isNotBlank(value)) {
                        value = value.replaceAll("/", "-");
                        String format = DateUtils.format(DateUtils.parse(value), DateUtils.DATE_PATTERN);
                        value = DateUtils.getUpperDate(format);
                    }
                    break;
                }
                case "bjscbl":
                case "bxscbl":
                case "dywbxl":
                case "pmfbl":
                case "ssfbl":
                case "zxfbl":
                case "sfjdfbl": {
                    if (StringUtils.isNotBlank(value)) {
                        value = FormatUtils.numberToPercent(Double.parseDouble(value), 2);
                    }
                    break;
                }
                case "bzqxnmsxgnr": {
                    if (StringUtils.isNotBlank(value)) {
                        value = String.valueOf(stringObjectEntry.getValue());
                    }
                    break;
                }
            }
            stringMap.put("${" + stringObjectEntry.getKey() + "}", value);
        }
        return stringMap;
    }

}

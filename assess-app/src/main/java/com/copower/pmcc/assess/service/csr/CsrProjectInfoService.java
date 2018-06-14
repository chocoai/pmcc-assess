package com.copower.pmcc.assess.service.csr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.ReflectUtils;
import com.copower.pmcc.assess.common.enums.BaseReportDataPoolTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseReportTemplateTypeEnum;
import com.copower.pmcc.assess.common.enums.CustomerTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessParameterConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.csr.CsrProjectInfoDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectMemberDto;
import com.copower.pmcc.assess.dal.dao.csr.*;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.csr.CsrImportBorrowerDto;
import com.copower.pmcc.assess.dto.input.project.csr.CsrImportColumnDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoGroupVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoVo;
import com.copower.pmcc.assess.service.BaseReportService;
import com.copower.pmcc.assess.service.TemplateSetService;
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
import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.xml.bind.Element;
import java.math.BigDecimal;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    private DataCsrFieldRelationService dataCsrFieldRelationService;
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
    private FormConfigureService formConfigureService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

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
        if (!org.springframework.util.StringUtils.isEmpty(csrProjectInfoVo.getProjectTypeName())) {
            csrProjectInfoVo.setProjectTypeName("债券项目");
        } else {
            csrProjectInfoVo.setProjectTypeName("默认项目");
        }
        if (!org.springframework.util.StringUtils.isEmpty(csrProjectInfoVo.getProjectCategoryId())) {
            csrProjectInfoVo.setProjectCategoryName("债券项目");
        } else {
            csrProjectInfoVo.setProjectCategoryName("默认项目");
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
        baseAttachmentService.relationToTable(AssessTableNameConstant.CSR_PROJECT_INFO, null, csrProjectInfo.getId());

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
            BaseAttachment queryParam = new BaseAttachment();
            queryParam.setTableName(AssessTableNameConstant.CSR_PROJECT_INFO);
            queryParam.setTableId(csrProjectInfo.getId());
            List<BaseAttachment> attachmentList = baseAttachmentService.getAttachmentList(queryParam);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                BaseAttachment sysAttachment = attachmentList.get(0);
                String localDirPath = baseAttachmentService.createTempBasePath(commonService.thisUserAccount());
                String localFileName = baseAttachmentService.createNoRepeatFileName(sysAttachment.getFileExtension());
                String fullPath = localDirPath + File.separator + localFileName;
                if (!FileUtils.checkFileExists(new File(fullPath))) {
                    try {
                        ftpUtilsExtense.downloadFileToLocal(sysAttachment.getFtpFilesName(), sysAttachment.getFilePath(), localFileName, localDirPath);
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
                    projectInfo.setProjectMemberId(k);
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
        List<DataCsrFieldRelation> fieldRelations = dataCsrFieldRelationService.getAllList();
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
                    //关联到配置
                    CsrImportColumnDto csrImportColumnDto = new CsrImportColumnDto();
                    csrImportColumnDto.setColumnIndex(i);
                    csrImportColumnDto.setColumnName(value);
                    DataCsrFieldRelation fieldRelation = dataCsrFieldRelationService.getFieldRelationFromList(fieldRelations, value);
                    if (fieldRelation != null) {
                        csrImportColumnDto.setTableName(fieldRelation.getTableName());
                        csrImportColumnDto.setFieldName(fieldRelation.getFieldName());
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
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_BORROWER)) {
                                    if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_SECOND_LEVEL_BRANCH)) {
                                        secondLevelBranch = PoiUtils.getCellValue(cell);
                                    }
                                    if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_ID_NUMBER)) {
                                        idNumber = PoiUtils.getCellValue(cell);
                                    }
                                    if (csrBorrower == null)
                                        csrBorrower = new CsrBorrower();
                                    try {
                                        ReflectUtils.setProperty(csrBorrower, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_BORROWER_MORTGAGE)) {
                                    if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_MORTGAGE_CONTRACT_NUMBER)) {
                                        contractNumber = PoiUtils.getCellValue(cell);
                                    }
                                    if (csrBorrowerMortgage == null)
                                        csrBorrowerMortgage = new CsrBorrowerMortgage();
                                    try {
                                        ReflectUtils.setProperty(csrBorrowerMortgage, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_CONTRACT)) {
                                    if (csrContract == null)
                                        csrContract = new CsrContract();
                                    try {
                                        ReflectUtils.setProperty(csrContract, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_GUARANTOR)) {
                                    if (csrGuarantor == null)
                                        csrGuarantor = new CsrGuarantor();
                                    try {
                                        ReflectUtils.setProperty(csrGuarantor, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_LITIGATION)) {
                                    if (csrLitigation == null)
                                        csrLitigation = new CsrLitigation();
                                    try {
                                        ReflectUtils.setProperty(csrLitigation, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_PRINCIPAL_INTEREST)) {
                                    if (csrPrincipalInterest == null)
                                        csrPrincipalInterest = new CsrPrincipalInterest();
                                    try {
                                        ReflectUtils.setProperty(csrPrincipalInterest, dbFieldToBeanField(columnDto.getFieldName()), PoiUtils.getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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
        importToDataBase(csrProjectInfo.getId(), importBorrowerDtoList);
        is.close();
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

        CsrProjectInfo csrProjectInfo = csrProjectInfoDao.getCsrProjectInfoById(csrProjectId);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);

        BaseReportTemplateFiles baseReportTemplateFilesWhere = new BaseReportTemplateFiles();
        baseReportTemplateFilesWhere.setCsType(csrProjectInfo.getCustomerType());//客户类型 1、自然人、法人
        baseReportTemplateFilesWhere.setReportTypeId(baseDataDic.getId());//取预评报告
        baseReportTemplateFilesWhere.setCustomerId(csrProjectInfo.getEntrustmentUnitId());//客户单位
        baseReportTemplateFilesWhere.setEntrustId(csrProjectInfo.getEntrustPurpose());
        baseReportTemplateFilesWhere.setBisEnable(true);
        BaseReportTemplateFiles baseReportTemplateFiles = baseReportService.getBaseReportTemplateFiles(baseReportTemplateFilesWhere);
        if (baseReportTemplateFiles == null)
            throw new BusinessException("未找到对应的报告模板");
        BaseAttachment queryParam = new BaseAttachment();
        queryParam.setTableName(FormatUtils.entityNameConvertToTableName(BaseReportTemplateFiles.class));
        queryParam.setTableId(baseReportTemplateFiles.getId());
        queryParam.setFieldsName(BaseReportTemplateTypeEnum.REPORT.getKey());
        List<BaseAttachment> attachmentList = baseAttachmentService.getAttachmentList(queryParam);
        if (CollectionUtils.isEmpty(attachmentList))
            throw new BusinessException("未找到对应的报告模板附件");
        BaseAttachment baseAttachment = attachmentList.get(0);//模板附件
        BaseAttachment attachment = new BaseAttachment();
        attachment.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrower.class));
        attachment.setFieldsName(AssessFieldNameConstant.CSR_BORROWER_REPORT);
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(borrowerIds));

        BaseReportTemplate baseReportTemplate = new BaseReportTemplate();
        baseReportTemplate.setCsType(csrProjectInfo.getCustomerType());//客户类型 1、自然人、法人
        baseReportTemplate.setReportTypeId(baseDataDic.getId());//取预评报告
        baseReportTemplate.setCustomerId(csrProjectInfo.getEntrustmentUnitId());//客户单位
        baseReportTemplate.setEntrustId(csrProjectInfo.getEntrustPurpose());
        baseReportTemplate.setBisEnable(true);
        List<BaseReportTemplate> baseReportTemplateList = baseReportService.getBaseReportTemplateList(baseReportTemplate);
        for (Integer integer : list) {
            try {
                //拷贝表数据及FTP附件
                attachment.setTableId(integer);
                BaseAttachment ftpAttachment = baseAttachmentService.copyFtpAttachment(baseAttachment.getId(), attachment);
                //获取数据
                if (CollectionUtils.isNotEmpty(baseReportTemplateList)) {
                    //1.检查配置的书签有几张表
                    //2.先找到主表信息,再找从表数据
                    //3.循环书签
                    HashSet<Integer> tableSet = Sets.newHashSet();
                    for (BaseReportTemplate bookmark : baseReportTemplateList) {
                        BaseReportDataPoolTypeEnum dataPoolTypeEnum = BaseReportDataPoolTypeEnum.getEnumByName(bookmark.getDataPoolType());
                        switch (dataPoolTypeEnum) {
                            case COLUMNS:
                                tableSet.add(bookmark.getDataPoolTableId());
                                break;
                        }
                    }
                    Map<Integer, Map<String, Object>> map = Maps.newHashMap();
                    Map<String, Object> objectMap = null;
                    List<BaseReportTable> baseReportTableList = baseReportService.getBaseReportTableList(Lists.newArrayList(tableSet));

                    String sql = "";
                    for (BaseReportTable reportTable : baseReportTableList) {
                        switch (reportTable.getTableName()) {
                            case AssessTableNameConstant.CSR_BORROWER:
                                objectMap = formConfigureService.getObjectSingle(AssessTableNameConstant.CSR_BORROWER, integer);
                                if (objectMap != null)
                                    map.put(reportTable.getId(), objectMap);
                                break;
                            case AssessTableNameConstant.CSR_BORROWER_MORTGAGE:
                            case AssessTableNameConstant.CSR_CONTRACT:
                            case AssessTableNameConstant.CSR_GUARANTOR:
                            case AssessTableNameConstant.CSR_LITIGATION:
                            case AssessTableNameConstant.CSR_PRINCIPAL_INTEREST:
                            case AssessTableNameConstant.CSR_CALCULATION:
                                sql = String.format("select * from %s where borrower_id=%s", reportTable.getTableName(), integer);
                                objectMap = formConfigureService.getObjectSingle(sql, new Object[0]);
                                if (objectMap != null)
                                    map.put(reportTable.getId(), objectMap);
                                break;
                            case AssessTableNameConstant.CSR_PROJECT_INFO:
                                sql = String.format("select * from %s where borrower_id=%s", reportTable.getTableName(), csrProjectId);
                                objectMap = formConfigureService.getObjectSingle(sql, new Object[0]);
                                if (objectMap != null)
                                    map.put(reportTable.getId(), objectMap);
                                break;
                        }
                    }

                    List<KeyValueDto> keyValueDtoList = Lists.newArrayList();//书签对应值数据

                    for (BaseReportTemplate bookmark : baseReportTemplateList) {
                        KeyValueDto keyValueDto = new KeyValueDto();
                        //对应类型不同处理方式有区别
                        keyValueDto.setExplain(String.valueOf(BaseReportDataPoolTypeEnum.COLUMNS.getKey()));
                        keyValueDto.setKey(bookmark.getBookmarkName());
                        Map<String, Object> stringObjectMap = map.get(bookmark.getDataPoolTableId());

                        BaseReportColumns baseReportColumns = baseReportService.getBaseReportColumnsById(bookmark.getDataPoolColumnsId());
                        keyValueDto.setValue(String.valueOf(stringObjectMap.get(baseReportColumns.getColumnsName())));
                        keyValueDtoList.add(keyValueDto);
                    }

                    //写入到替换数据表
                    BaseReplaceRecord baseReplaceRecord = new BaseReplaceRecord();
                    baseReplaceRecord.setAttachmentId(ftpAttachment.getId());
                    baseReplaceRecord.setBisReplace(false);
                    baseReplaceRecord.setCreator(commonService.thisUserAccount());
                    baseReplaceRecord.setContent(JSON.toJSONString(keyValueDtoList));
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
     * 确定是否使用已有的借款人信息
     *
     * @param csrProjectId
     * @param secondLevelBranch
     * @param idNumber
     * @param contractNumber
     * @return
     */
    public CsrBorrower useHistoryBorrower(Integer csrProjectId, String secondLevelBranch, String idNumber, String contractNumber) {
        CsrBorrower queryParam = new CsrBorrower();
        queryParam.setCsrProjectId(csrProjectId);
        queryParam.setSecondLevelBranch(secondLevelBranch);
        queryParam.setIdNumber(idNumber);
        List<CsrBorrower> csrBorrowerList = csrBorrowerDao.getCsrBorrowerList(queryParam);
        if (CollectionUtils.isNotEmpty(csrBorrowerList)) {
            List<Integer> borrowerIds = LangUtils.transform(csrBorrowerList, p -> p.getId());
            List<CsrBorrowerMortgage> mortgageList = csrBorrowerMortgageDao.getCsrBorrowerMortgageList(borrowerIds);
            if (CollectionUtils.isNotEmpty(mortgageList)) {
                for (CsrBorrowerMortgage csrBorrowerMortgage : mortgageList) {
                    if (StringUtils.equals(csrBorrowerMortgage.getContractNumber(), contractNumber)) {
                        for (CsrBorrower csrBorrower : csrBorrowerList) {
                            if (csrBorrower.getId().equals(csrBorrowerMortgage.getBorrowerId())) {
                                return csrBorrower;
                            }
                        }
                    }
                }
            }
        }
        return null;
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
                    List<CsrBorrowerMortgage> csrBorrowerMortgageList = borrowerDto.getCsrBorrowerMortgageList();
                    if (StringUtils.equals(csrBorrower.getSecondLevelBranch(), secondLevelBranch) && StringUtils.equals(csrBorrower.getIdNumber(), idNumber)) {
                        if (CollectionUtils.isNotEmpty(csrBorrowerMortgageList)) {
                            for (CsrBorrowerMortgage csrBorrowerMortgage : csrBorrowerMortgageList) {
                                if (StringUtils.equals(csrBorrowerMortgage.getContractNumber(), contractNumber)) {
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
    private void importToDataBase(Integer csrProjectId, List<CsrImportBorrowerDto> borrowerDtos) {
        if (CollectionUtils.isNotEmpty(borrowerDtos)) {
            for (CsrImportBorrowerDto borrowerDto : borrowerDtos) {
                CsrBorrower csrBorrower = borrowerDto.getCsrBorrower();
                if (csrBorrower != null) {
                    csrBorrower.setCsrProjectId(csrProjectId);
                    csrBorrower.setBisImport(true);
                    csrBorrower.setCreator(commonService.thisUserAccount());
                    csrBorrowerDao.addCsrBorrower(csrBorrower);
                }
                List<CsrBorrowerMortgage> csrBorrowerMortgageList = borrowerDto.getCsrBorrowerMortgageList();
                if (CollectionUtils.isNotEmpty(csrBorrowerMortgageList)) {
                    for (CsrBorrowerMortgage csrBorrowerMortgage : csrBorrowerMortgageList) {
                        csrBorrowerMortgage.setCsrProjectId(csrProjectId);
                        csrBorrowerMortgage.setBorrowerId(csrBorrower.getId());
                        csrBorrowerMortgage.setBisImport(true);
                        csrBorrowerMortgage.setCreator(commonService.thisUserAccount());
                        csrBorrowerMortgageDao.addCsrBorrowerMortgage(csrBorrowerMortgage);
                    }
                }
                List<CsrContract> csrContractList = borrowerDto.getCsrContractList();
                if (CollectionUtils.isNotEmpty(csrContractList)) {
                    for (CsrContract csrContract : csrContractList) {
                        csrContract.setCsrProjectId(csrProjectId);
                        csrContract.setBorrowerId(csrBorrower.getId());
                        csrContract.setBisImport(true);
                        csrContract.setCreator(commonService.thisUserAccount());
                        csrContractDao.addCsrContract(csrContract);
                    }
                }
                List<CsrGuarantor> csrGuarantorList = borrowerDto.getCsrGuarantorList();
                if (CollectionUtils.isNotEmpty(csrGuarantorList)) {
                    for (CsrGuarantor csrGuarantor : csrGuarantorList) {
                        csrGuarantor.setCsrProjectId(csrProjectId);
                        csrGuarantor.setBorrowerId(csrBorrower.getId());
                        csrGuarantor.setBisImport(true);
                        csrGuarantor.setCreator(commonService.thisUserAccount());
                        csrGuarantorDao.addCsrGuarantor(csrGuarantor);
                    }
                }
                List<CsrLitigation> csrLitigationList = borrowerDto.getCsrLitigationList();
                if (CollectionUtils.isNotEmpty(csrLitigationList)) {
                    for (CsrLitigation csrLitigation : csrLitigationList) {
                        csrLitigation.setCsrProjectId(csrProjectId);
                        csrLitigation.setBorrowerId(csrBorrower.getId());
                        csrLitigation.setBisImport(true);
                        csrLitigation.setCreator(commonService.thisUserAccount());
                        csrLitigationDao.addCsrLitigation(csrLitigation);
                    }
                }
                List<CsrPrincipalInterest> csrPrincipalInterestList = borrowerDto.getCsrPrincipalInterestList();
                if (CollectionUtils.isNotEmpty(csrPrincipalInterestList)) {
                    for (CsrPrincipalInterest csrPrincipalInterest : csrPrincipalInterestList) {
                        csrPrincipalInterest.setCsrProjectId(csrProjectId);
                        csrPrincipalInterest.setBorrowerId(csrBorrower.getId());
                        csrPrincipalInterest.setBisImport(true);
                        csrPrincipalInterest.setCreator(commonService.thisUserAccount());
                        csrPrincipalInterestDao.addCsrPrincipalInterest(csrPrincipalInterest);
                    }
                }
            }
        }
    }

    /**
     * 债权人列表信息
     *
     * @return
     */
    public BootstrapTableVo csrProjectInfoListA(String name) {
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
        List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        int i = 1;
        for (Integer integer : integerList) {
            BaseAttachment attachment = new BaseAttachment();
            attachment.setTableName("sheet1");
            attachment.setFieldsName("report");
            try {
                List<Map<String, Object>> mapList = jdbcTemplate.queryForList("SELECT  * from sheet1 where id=" + integer);
                attachment.setFileName(String.valueOf(mapList.get(0).get("khxm") + "报告"));
                //templateSetService 取报告模板

                //

                BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName("single.csr");
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName("report.type.preaudit");
                Integer templateId = baseReportService.getReportTemplate(0, baseProjectClassify.getId(), baseDataDic.getId(), 0, 0);
                BaseAttachment ftpAttachment = baseAttachmentService.copyFtpAttachment(templateId, attachment);
                String loaclFileName = baseAttachmentService.createNoRepeatFileName(ftpAttachment.getFileExtension());
                String localFileDir = baseAttachmentService.createTempBasePath();
                String localFullPath = localFileDir + File.separator + loaclFileName;
                ftpUtilsExtense.downloadFileToLocal(ftpAttachment.getFtpFilesName(), ftpAttachment.getFilePath(), loaclFileName, localFileDir);
                if (CollectionUtils.isNotEmpty(mapList)) {

                    for (Map<String, Object> map : mapList) {
                        try {
                            Map<String, String> stringMap = toMapString(map);
                            stringMap.put("${number}", String.valueOf(i));
                            AsposeUtils.replaceText(localFullPath, stringMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                //再将附件上传到相同位置
                try {
                    ftpUtilsExtense.uploadFilesToFTP(ftpAttachment.getFilePath(), new FileInputStream(localFullPath), ftpAttachment.getFtpFilesName());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

                jdbcTemplate.update(String.format("update sheet1 set attachment_id=%s where id=%s", ftpAttachment.getId(), integer));
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
            switch (stringObjectEntry.getKey()) {
                case "dkffsj":
                case "htqdr":
                case "fxjzr":
                case "bgyxq": {
                    value = value.replaceAll("/", "-");
                    String format = DateUtils.format(DateUtils.parse(value), DateUtils.DATE_CHINESE_PATTERN);
                    value = format;
                    break;
                }
                case "bgcjsj": {
                    value = value.replaceAll("/", "-");
                    String format = DateUtils.format(DateUtils.parse(value), DateUtils.DATE_PATTERN);
                    value = DateUtils.getUpperDate(format);
                    break;
                }
                case "bjscbl":
                case "bxscbl":
                case "dywbxl":
                case "pmfbl":
                case "ssfbl":
                case "zxfbl":
                case "sfjdfbl": {
                    value = FormatUtils.numberToPercent(Double.parseDouble(value), 2);
                    break;
                }
                case "bzqxnmsxgnr": {
                    value = String.valueOf(stringObjectEntry.getValue());
                    break;
                }
            }
            stringMap.put("${" + stringObjectEntry.getKey() + "}", value);
        }
        return stringMap;
    }

}

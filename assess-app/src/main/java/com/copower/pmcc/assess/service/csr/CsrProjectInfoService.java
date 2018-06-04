package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.common.ReflectUtils;
import com.copower.pmcc.assess.common.enums.CustomerTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessParameterConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.csr.CsrProjectInfoDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectMemberDto;
import com.copower.pmcc.assess.dal.dao.csr.*;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.csr.CsrImportColumnDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterServcie;
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
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    /**
     * 获取vo
     *
     * @param csrProjectInfo
     * @return
     */
    public CsrProjectInfoVo getCsrProjectInfoVo(CsrProjectInfo csrProjectInfo) {
        if (csrProjectInfo == null) return null;
        CsrProjectInfoVo csrProjectInfoVo = new CsrProjectInfoVo();
        BeanUtils.copyProperties(csrProjectInfo, csrProjectInfoVo);
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

    public CsrProjectInfoVo getById(Integer id){
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
     * @param csrProjectInfo
     */
    private void cleanImportData(CsrProjectInfo csrProjectInfo){
        List<CsrBorrower> csrBorrowers = csrBorrowerDao.getCsrBorrowerListByCsrProjectID(csrProjectInfo.getId());
        if(CollectionUtils.isNotEmpty(csrBorrowers)){
            csrBorrowers.forEach(p->{
                csrBorrowerMortgageDao.deleteByBorrowerId(p.getId());
                csrcalculationDao.deleteByBorrowerId(p.getId());
                csrContractDao.deleteByBorrowerId(p.getId());
                csrGuarantorDao.deleteByBorrowerId(p.getId());
                csrLitigationDao.deleteByBorrowerId(p.getId());
                csrPrincipalInterestDao.deleteByBorrowerId(p.getId());
            });
            csrBorrowerDao.deleteByCsrProjectId(csrProjectInfo.getId());
        }

    }

    /**
     * 读取数据
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
        if(StringUtils.equalsIgnoreCase(approvalModelDto.getConclusion(),TaskHandleStateEnum.AGREE.getValue()) ){
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
                    projectInfo.setProjectTypeId(csrProjectInfo.getProjectTypeId());
                    projectInfo.setProjectCategoryId(csrProjectInfo.getProjectCategoryId());
                    projectInfo.setCreator(csrProjectInfo.getCreator());
                    projectInfo.setProjectName(infoGroup.getProjectName());
                    int projectId = projectInfoService.saveProjectInfo_returnID(projectInfo);

                    //保存项目成员
                    ProjectMemberDto projectMemberDto = new ProjectMemberDto();
                    projectMemberDto.setProjectId(projectId);
                    projectMemberDto.setCreator(csrProjectInfo.getCreator());
                    projectMemberDto.setUserAccountManager(infoGroup.getProjectManager());
                    projectMemberDto.setUserAccountMember(infoGroup.getProjectMember());
                    projectMemberService.saveReturnId(projectMemberDto);

                    //回写借款人的项目id
                    List<CsrBorrower> csrBorrowers = csrBorrowerService.getCsrBorrowerListByCsrProjectID(csrProjectInfo.getId());
                    for (CsrBorrower csrBorrower : csrBorrowers) {
                        csrBorrower.setProjectId(k);
                    for (CsrBorrower csrBorrower:csrBorrowers){
                        csrBorrower.setProjectId(projectId);
                        csrBorrowerService.update(csrBorrower);
                    }
                    //项目立项
                    projectInfoService.initProjectInfo(projectInfo);
                } catch (Exception e) {
                    try {
                        logger.error("异常!");
                        throw e;
                    } catch (Exception e1) {

                    }
                }catch (Exception e){
                    logger.error("初始化项目异常，原因："+e.getMessage());
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
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        int startRowNumber = csrProjectInfo.getStartRowNumber();//读取业务数据的起始行序号
        List<DataCsrFieldRelation> fieldRelations = dataCsrFieldRelationService.getAllList();
        List<CsrInvalidRule> ruleList = csrInvalidRuleService.getRuleList(csrProjectInfo.getId());//过滤规则数据
        HashMap<Integer, String> invalidRuleIndexMap = Maps.newHashMap();//需要参与过滤的列
        HashMap<Integer, CsrImportColumnDto> hashMap = Maps.newHashMap();
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            //没有在基础配置的字段中不做处理
            row = sheet.getRow(rowNum);
            if (row == null) return;
            if (rowNum == 0) {
                //确定单元格对应的字段 将每一列与配置的字段对应
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                    if (cell == null) continue;
                    String value = getCellValue(cell);
                    if (StringUtils.isBlank(value)) continue;
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
                            isFilter = isFilter(ruleList, integerStringEntry.getValue(),getCellValue(row.getCell(integerStringEntry.getKey())));
                            if (isFilter) continue;
                        }
                        if (isFilter) continue;
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
                                if (cell == null) continue;
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_BORROWER)) {
                                    if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_SECOND_LEVEL_BRANCH)) {
                                        secondLevelBranch = getCellValue(cell);
                                    }
                                    if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_ID_NUMBER)) {
                                        idNumber = getCellValue(cell);
                                    }
                                    if (csrBorrower == null)
                                        csrBorrower = new CsrBorrower();
                                    try {
                                        ReflectUtils.setProperty(csrBorrower, dbFieldToBeanField(columnDto.getFieldName()), getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_BORROWER_MORTGAGE)) {
                                    if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_MORTGAGE_CONTRACT_NUMBER)) {
                                        contractNumber = getCellValue(cell);
                                    }
                                    if (csrBorrowerMortgage == null)
                                        csrBorrowerMortgage = new CsrBorrowerMortgage();
                                    try {
                                        ReflectUtils.setProperty(csrBorrowerMortgage, dbFieldToBeanField(columnDto.getFieldName()), getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_CONTRACT)) {
                                    if (csrContract == null)
                                        csrContract = new CsrContract();
                                    try {
                                        ReflectUtils.setProperty(csrContract, dbFieldToBeanField(columnDto.getFieldName()), getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_GUARANTOR)) {
                                    if (csrGuarantor == null)
                                        csrGuarantor = new CsrGuarantor();
                                    try {
                                        ReflectUtils.setProperty(csrGuarantor, dbFieldToBeanField(columnDto.getFieldName()), getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_LITIGATION)) {
                                    if (csrLitigation == null)
                                        csrLitigation = new CsrLitigation();
                                    try {
                                        ReflectUtils.setProperty(csrLitigation, dbFieldToBeanField(columnDto.getFieldName()), getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_PRINCIPAL_INTEREST)) {
                                    if (csrPrincipalInterest == null)
                                        csrPrincipalInterest = new CsrPrincipalInterest();
                                    try {
                                        ReflectUtils.setProperty(csrPrincipalInterest, dbFieldToBeanField(columnDto.getFieldName()), getCellValue(cell));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        //endregion

                        //确定客户数据是否保存
                        CsrBorrower borrower = useHistoryBorrower(csrProjectInfo.getId(), secondLevelBranch, idNumber, contractNumber);
                        if (borrower == null) {//保存客户信息
                            if (csrBorrower != null) {
                                csrBorrower.setCsrProjectId(csrProjectInfo.getId());
                                csrBorrower.setBisImport(true);
                                csrBorrower.setCreator(commonService.thisUserAccount());
                                csrBorrowerDao.addCsrBorrower(csrBorrower);
                            }
                        } else {
                            csrBorrower = borrower;
                        }
                        if (csrBorrowerMortgage != null) {
                            csrBorrowerMortgage.setBorrowerId(csrBorrower.getId());
                            csrBorrowerMortgage.setExcelRowIndex(rowNum + 1);
                            csrBorrowerMortgage.setBisImport(true);
                            csrBorrowerMortgage.setCreator(commonService.thisUserAccount());
                            csrBorrowerMortgageDao.addCsrBorrowerMortgage(csrBorrowerMortgage);
                        }
                        if (csrContract != null) {
                            csrContract.setBorrowerId(csrBorrower.getId());
                            csrContract.setBisImport(true);
                            csrContract.setCreator(commonService.thisUserAccount());
                            csrContractDao.addCsrContract(csrContract);
                        }
                        if (csrGuarantor != null) {
                            csrGuarantor.setBorrowerId(csrBorrower.getId());
                            csrGuarantor.setBisImport(true);
                            csrGuarantor.setCreator(commonService.thisUserAccount());
                            csrGuarantorDao.addCsrGuarantor(csrGuarantor);
                        }
                        if (csrLitigation != null) {
                            csrLitigation.setBorrowerId(csrBorrower.getId());
                            csrLitigation.setBisImport(true);
                            csrLitigation.setCreator(commonService.thisUserAccount());
                            csrLitigationDao.addCsrLitigation(csrLitigation);
                        }
                        if (csrPrincipalInterest != null) {
                            csrPrincipalInterest.setBorrowerId(csrBorrower.getId());
                            csrPrincipalInterest.setBisImport(true);
                            csrPrincipalInterest.setCreator(commonService.thisUserAccount());
                            csrPrincipalInterestDao.addCsrPrincipalInterest(csrPrincipalInterest);
                        }

                    }
                } catch (Exception e) {
                    //找出错误行数据，并返回错误给前端用户
                    e.printStackTrace();
                }

            }
        }
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
     * 根据类型获取cell的值
     *
     * @param cell
     * @return
     */
    private String getCellValue(HSSFCell cell) {
        String value = "";
        try {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    //如果为时间格式的内容
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell.
                                getNumericCellValue())).toString();
                        break;
                    } else {
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    value = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    value = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    value = "error";
                    break;
                default:
                    value = "unknown";//未知
                    break;
            }
        } catch (Exception e) {
            value = "";
            e.printStackTrace();
        }
        return value;
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
            if (key.equals(s.getKey())){
                name = s.getName();
            }
        }
        return name;
    }

    public String getCustomerTypeEnum(Integer key){
        String name = "";
        for (CustomerTypeEnum s : CustomerTypeEnum.values()) {
            if (s.getId().equals(key)){
                name = s.getName();
            }
        }
        return name;
    }
}

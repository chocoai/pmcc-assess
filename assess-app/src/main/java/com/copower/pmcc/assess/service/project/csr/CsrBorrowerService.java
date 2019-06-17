package com.copower.pmcc.assess.service.project.csr;

import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.CsrBorrowerEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.basis.dao.csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.basis.dao.csr.CsrBorrowerEnteringDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerEnteringVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerVo;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 借款人
 */
@Service
public class CsrBorrowerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CsrBorrowerDao csrBorrowerDao;
    @Autowired
    private CsrBorrowerEnteringDao csrBorrowerEnteringDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;
    @Autowired
    private BaseReportService baseReportService;
    @Autowired
    private CsrBorrowerMortgageService csrBorrowerMortgageService;

    public BootstrapTableVo borrowerLists(String secondLevelBranch, String firstLevelBranch, Integer csrProjectInfoID, Integer csrProjectInfoGroupID) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrBorrower> vos = csrBorrowerDao.borrowerListsA(secondLevelBranch, firstLevelBranch, csrProjectInfoID, csrProjectInfoGroupID);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CsrBorrower>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BootstrapTableVo borrowerListVos(String secondLevelBranch, String firstLevelBranch, Integer csrProjectInfoID) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrBorrower> csrBorrowers = csrBorrowerDao.borrowerListsB(secondLevelBranch, firstLevelBranch, csrProjectInfoID);
        List<CsrBorrowerVo> vos = getCsrBorrowerVos(csrBorrowers);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CsrBorrower>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public CsrBorrower getCsrBorrowerByID(Integer id) {
        id = Preconditions.checkNotNull(id, "不能为null");
        return csrBorrowerDao.getCsrBorrowerByID(id);
    }

    /**
     * 查询分派是否完成
     *
     * @param csrProjectInfoID
     * @return
     */
    public Integer checkCsrBorrower(Integer csrProjectInfoID) {
        return csrBorrowerDao.borrowerListsA(null, null, csrProjectInfoID, null).size();
    }

    /**
     * 多个附件下载
     *
     * @param borrowerIds
     * @return
     */
    @Transactional
    public ResponseEntity<byte[]> downloadBorrower(String borrowerIds, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            List<String> filePaths = changeSysAttachmentDto(borrowerIds, request);
            String zipName = UUID.randomUUID().toString().substring(0, 5) + ".zip";
            responseEntity = appleDownloadSysAttachmentDto(filePaths, zipName, request);
        } catch (Exception e) {
            try {
                logger.error("异常!" + e.getMessage());
                throw e;
            } catch (Exception e1) {

            }
        }
        return responseEntity;
    }

    /**
     * 返回 下载的数据流
     *
     * @param filePaths 多个临时附件的路径
     * @param zipName   压缩文件名
     * @param request
     * @return
     */
    private ResponseEntity<byte[]> appleDownloadSysAttachmentDto(List<String> filePaths, String zipName, HttpServletRequest request) throws Exception {
        ResponseEntity<byte[]> responseEntity = null;
        String localDirPath = baseAttachmentService.createTempDirPath();
        String zipPathAndName = localDirPath + zipName;
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < filePaths.size(); i++) {
            fileList.add(new File(filePaths.get(i)));
        }
        byte[] bytes = FileUtils.getZipFile(fileList, zipPathAndName);
        responseEntity = FileUtils.getFileUtils().createResponse(zipName, bytes);
        return responseEntity;
    }

    /**
     * 查找附件 并且把附件下载到临时的目录下
     *
     * @param borrowerIds
     * @param request
     * @return
     * @throws Exception
     */
    private List<String> changeSysAttachmentDto(String borrowerIds, HttpServletRequest request) throws Exception {
        String report = AssessFieldNameConstant.CSR_BORROWER_REPORT;
        List<String> filePaths = new ArrayList<>();
        String[] ids = borrowerIds.split(",");
        List<SysAttachmentDto> baseAttachments = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            if (!org.springframework.util.StringUtils.isEmpty(ids[i])) {
                List<SysAttachmentDto> baseAttachmentList = baseAttachmentService.getByField_tableId(Integer.parseInt(ids[i]), report, null);
                baseAttachments.addAll(baseAttachmentList);
            }
        }
        for (SysAttachmentDto baseAttachment : baseAttachments) {
            String localDirPath = request.getSession().getServletContext().getRealPath("/") + CsrBorrowerEnum.CSR_BORROWER_ENUM.getFilePath();
            String localFileName = UUID.randomUUID().toString().substring(0, 7) + CsrBorrowerEnum.ZIP_NAME.getFilePath() + "." + baseAttachment.getFileExtension();
            //临时下载
            String s = readImportData(localDirPath, localFileName, baseAttachment, report);
            //收集 临时目录地址
//            filePaths.add(localDirPath + localFileName);
            filePaths.add(s);
        }
        return filePaths;
    }

    /**
     * 查找附件 并且把附件下载到临时的目录下
     *
     * @param tableID
     * @return
     */
    private List<String> changeSysAttachmentDto(Integer tableID) {
        List<String> filePaths = new ArrayList<>();
        String localDirPath = baseAttachmentService.createTempDirPath();
        List<SysAttachmentDto> baseAttachmentList = baseAttachmentService.getByField_tableId(tableID, AssessFieldNameConstant.CSR_BORROWER_EXPORT, AssessTableNameConstant.CSR_REPORT_TEMPLATE_FILES);
        for (SysAttachmentDto baseAttachment : baseAttachmentList) {
            if (!ObjectUtils.isEmpty(baseAttachment)) {
                String fileSuffix = baseAttachment.getFileExtension();
                String localFileName = UUID.randomUUID().toString().substring(0, 7) + "." + fileSuffix;
                //临时下载
                String ss = readImportData(localDirPath, localFileName, baseAttachment, AssessFieldNameConstant.CSR_BORROWER_REPORT);
                //必须保证 baseAttachmentList size==1 的情况下才能这样做 ,因为在当前情况是唯一
//                filePaths.add(localDirPath +"/"+ localFileName);//第一个存入地址
                filePaths.add(ss);//第一个存入地址
                filePaths.add(localFileName);//第二个存入名称
            }
        }
        return filePaths;
    }


    /**
     * 将ftp的附件下载到本地
     *
     * @param localDirPath
     * @param localFileName
     * @param baseAttachment
     */
    private String readImportData(String localDirPath, String localFileName, SysAttachmentDto baseAttachment, String fileName) {
        try {
            String s = baseAttachmentService.downloadFtpFileToLocal(baseAttachment.getId());
            if (!org.springframework.util.StringUtils.isEmpty(s)) {
                return s;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getByField_tableId(baseAttachment.getTableId(), fileName, null);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            SysAttachmentDto sysAttachment = attachmentList.get(0);
            String fullPath = localDirPath + File.separator + localFileName;
            if (!com.copower.pmcc.erp.common.utils.FileUtils.checkFileExists(new File(fullPath))) {
                try {
//                    ftpUtilsExtense.downloadFileToLocal(sysAttachment.getFtpName(), sysAttachment.getFilePath(), localFileName, localDirPath);
                } catch (Exception e) {
                    //拷贝失败
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 取消分派
     *
     * @param csrProjectInfoID
     * @param id
     */
    public void cancel(Integer csrProjectInfoID, String id, Integer csrProjectInfoGroupID) {
        String[] ids = id.split(",");
        List<Integer> integerList = new ArrayList<>();
        for (String s : ids) {
            if (!StringUtils.isEmpty(s)) {
                integerList.add(Integer.valueOf(s));
            }
        }
        csrBorrowerDao.cancel(csrProjectInfoID, integerList, csrProjectInfoGroupID);
    }

    public boolean update(CsrBorrower csrBorrower) {
        return csrBorrowerDao.update(csrBorrower);
    }

    public List<CsrBorrowerVo> getCsrBorrowerByProjectId(Integer projectId) {
        CsrBorrower csrBorrower = new CsrBorrower();
        csrBorrower.setProjectId(projectId);
        List<CsrBorrower> csrBorrowerList = csrBorrowerDao.getCsrBorrowerList(projectId);
        return getCsrBorrowerVos(csrBorrowerList);
    }

    public List<CsrBorrowerVo> getCsrBorrowerVos(List<CsrBorrower> csrBorrowerList) {
        if (CollectionUtils.isEmpty(csrBorrowerList))
            return null;
        List<Integer> tableIds = LangUtils.transform(csrBorrowerList, p -> p.getId());
        SysAttachmentDto queryParam = new SysAttachmentDto();
        queryParam.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrower.class));
        queryParam.setFieldsName(AssessFieldNameConstant.CSR_BORROWER_REPORT);
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(tableIds, queryParam);
        return LangUtils.transform(csrBorrowerList, p -> {
            CsrBorrowerVo csrBorrowerVo = new CsrBorrowerVo();
            BeanUtils.copyProperties(p, csrBorrowerVo);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                StringBuilder html = new StringBuilder();
                for (SysAttachmentDto baseAttachment : attachmentList) {
                    if (baseAttachment.getTableId().equals(p.getId())) {
                        html.append(baseAttachmentService.getViewHtml(baseAttachment));
                    }
                }
                csrBorrowerVo.setAttachmentHtml(html.toString());
            }
            return csrBorrowerVo;
        });
    }

    public List<CsrBorrower> getCsrBorrowerListByCsrProjectID(Integer csrProjectID, Integer groupID) {
        return csrBorrowerDao.getCsrBorrowerListByCsrProjectID(csrProjectID, groupID);
    }

    public CsrBorrowerEntering saveCsrBorrower(CsrBorrowerEntering csrBorrowerEntering, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrBorrowerEntering.getId() == null || csrBorrowerEntering.getId() <= 0) {
                csrBorrowerEnteringDao.addCsrBorrowerEntering(csrBorrowerEntering);
                //更新附件信息
                SysAttachmentDto baseAttachment = new SysAttachmentDto();
                baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrowerEntering.class));
                baseAttachment.setTableId(0);
                baseAttachment.setCreater(processControllerComponent.getThisUser());

                SysAttachmentDto baseAttachmentNew = new SysAttachmentDto();
                baseAttachmentNew.setTableId(csrBorrowerEntering.getId());

                baseAttachmentService.updateAttachementByExample(baseAttachment, baseAttachmentNew);
            } else {
                csrBorrowerEnteringDao.update(csrBorrowerEntering);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrBorrowerEntering;
    }

    public CsrBorrowerEnteringVo loadLoanBorrower(String borrowerId, Integer detailsId) throws BusinessException {

        CsrBorrowerEntering csrBorrowerEntering = csrBorrowerEnteringDao.getCsrBorrowerEnteringByBorrowerId(borrowerId);
        if (csrBorrowerEntering == null) {
            CsrBorrower csrBorrower = csrBorrowerDao.getCsrBorrowerByID(borrowerId);
            if (csrBorrower != null) {
                csrBorrowerEntering = new CsrBorrowerEntering();
                BeanUtils.copyProperties(csrBorrower, csrBorrowerEntering);
            }
            csrBorrowerEntering.setId(0);
            csrBorrowerEntering.setBorrowerId(borrowerId);
            try {
                csrBorrowerEnteringDao.addCsrBorrowerEntering(csrBorrowerEntering);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }
        CsrBorrowerEnteringVo csrBorrowerEnteringVo = new CsrBorrowerEnteringVo();
        if (csrBorrowerEntering != null) {
            BeanUtils.copyProperties(csrBorrowerEntering, csrBorrowerEnteringVo);
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            if (projectPlanDetails != null) {
                if (projectPlanDetails.getActualHours() != null) {
                    csrBorrowerEnteringVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrBorrowerEnteringVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
            }
        }
        return csrBorrowerEnteringVo;
    }

    public ResponseEntity<byte[]> exportFormBorrowers(HttpServletRequest request, HttpServletResponse response, Integer csrProjectInfoID) {
        ResponseEntity<byte[]> responseEntity = null;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        CsrProjectInfo csrProjectInfo = csrProjectInfoService.getById(csrProjectInfoID);
        BaseReportTemplate baseReportTemplate = null;
        baseReportTemplate = null;
        responseEntity = toExportFormBorrowers(request, response, baseReportTemplate, csrProjectInfoID);
        return responseEntity;
    }

    private ResponseEntity<byte[]> toExportFormBorrowers(HttpServletRequest request, HttpServletResponse response, BaseReportTemplate templateDto, Integer csrProjectInfoID) {
        ResponseEntity<byte[]> responseEntity = null;
        CsrBorrowerMortgage csrBorrowerMortgage = null;
        Integer dataIndex = null;
        List<CsrBorrowerMortgage> csrBorrowerMortgages = csrBorrowerMortgageService.getCsrProjectMortgages(csrProjectInfoID);
        //获取下载到临时目录的模型报表文件 这里可能是word 也可能是 excel
        List<String> filePaths = changeSysAttachmentDto(null);
        Set<CsrBorrower> csrBorrowers = new HashSet<>();
        if (!ObjectUtils.isEmpty(csrBorrowerMortgages)) {
            for (int i = 0; i < csrBorrowerMortgages.size(); i++) {
                csrBorrowerMortgage = csrBorrowerMortgages.get(i);
                if (!ObjectUtils.isEmpty(csrBorrowerMortgage)) {
                    CsrBorrower csrBorrower = getCsrBorrowerByID(Integer.parseInt(csrBorrowerMortgage.getBorrowerId()));
                    if (!ObjectUtils.isEmpty(csrBorrower)) {
                        csrBorrowers.add(csrBorrower);
                        dataIndex = csrBorrowerMortgage.getExcelRowIndex();
                    }
                }
            }
        }
        //根据模型约定只会有一个模型文件
        String filePath = filePaths.get(0);
        List<Map<String, Object>> mapList = null;
//        responseEntity = writeCsrBorrowerModel(filePath,mapList);
        try {
            if (ObjectUtils.isEmpty(dataIndex)) {
                dataIndex = 6;
            }
            exportReportExcel(filePath, dataIndex, mapList);
            byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath));
            responseEntity = FileUtils.getFileUtils().createResponse(filePaths.get(1), bytes);
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {

            }
        }
        return responseEntity;
    }

    @Deprecated
    private ResponseEntity<byte[]> writeCsrBorrowerModel(String filePath, List<Map<String, String>> mapList) {
        ResponseEntity<byte[]> responseEntity = null;
        String localDirPath = baseAttachmentService.createTempDirPath();
        List<File> fileList = new ArrayList<>();
        //暂时先写死
        String suffix = "doc";
        String zipName = "数据回写" + UUID.randomUUID().toString().substring(0, 4) + ".zip";
        String zipPath = localDirPath + "/" + zipName;
        try {
            for (Map<String, String> map : mapList) {
                String path = localDirPath + "/" + UUID.randomUUID().toString().substring(0, 4) + "." + suffix;
                org.apache.commons.io.FileUtils.copyFile(new File(filePath), new File(path));
                if (!ObjectUtils.isEmpty(map)) {
                    AsposeUtils.replaceBookmark(path, map);
                    fileList.add(new File(path));
                }
            }
            byte[] bytes = FileUtils.getZipFile(fileList, zipPath);
            responseEntity = FileUtils.getFileUtils().createResponse(zipName, bytes);
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {

            }
        }
        return responseEntity;
    }

    /**
     * 利用反射 获取成员变量 和 数据字段进行比较
     *
     * @param csrBorrowerClass
     * @param fieldName
     * @return
     */
    public String equalsCsrBorrowerFieldName(Class<CsrBorrower> csrBorrowerClass, String fieldName) {
        Field[] fields = csrBorrowerClass.getDeclaredFields();
        String[] strings = fieldName.split("_");
        StringBuilder builder = new StringBuilder(1024);
        for (String s : strings) {
            builder.append(s);
        }
        for (Field field : fields) {
            String name = field.getName();
            if (!org.springframework.util.StringUtils.isEmpty(fieldName)) {
                if (name.equalsIgnoreCase(builder.toString())) {
                    return name;
                }

            }
        }
        return null;
    }

    private String getDataBaseField(List<KeyValueDto> keyValueDtos, String field) {
        String temp = "";
        for (KeyValueDto keyValue : keyValueDtos) {
            if (Objects.equal(keyValue.getKey(), field)) {
                temp = keyValue.getKey();
            }
        }
        return temp;
    }


    /**
     * 导出报告excle
     *
     * @param filePath  附件路径
     * @param dataIndex 数据开始行
     * @param mapList   数据  list行 Map<String,Object> Map<PO_jkr,涂桂芝></>
     */
    public void exportReportExcel(String filePath, Integer dataIndex, List<Map<String, Object>> mapList) {
        try {
            Map<String, Integer> report = getReportExcelOneKey(filePath);
            FileInputStream inputStream = new FileInputStream(filePath);
            int size = mapList.size();
            if (dataIndex <= 0)
                throw new Exception("数据输入非法");
            if (!(size >= dataIndex))
                throw new Exception("数据列表非法");
//            Workbook workbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
            Workbook workbook = null;
            String suffix = filePath.substring(filePath.length() - 4, filePath.length());
            try {
                if (Objects.equal("xlsx", suffix)) {//07版
                    workbook = new XSSFWorkbook(inputStream);
                } else {
                    workbook = new HSSFWorkbook(inputStream);
                }
            } catch (Exception e) {
                try {
                    logger.error("----------->" + e.getMessage());
                    throw e;
                } catch (Exception e1) {

                }
            }
            //暂时只取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            CellStyle cellStyle = sheet.getColumnStyle(dataIndex);//获取样式
            Row row = null;
            Cell cell = null;
            for (int i = dataIndex; i < size; i++) {
//                row = sheet.getRow(i);//行
                row = sheet.createRow(i);//行
                if (!ObjectUtils.isEmpty(row)) {
                    Map<String, Object> objectMap = mapList.get(i);
                    if (!ObjectUtils.isEmpty(objectMap)) {
                        for (Map.Entry<String, Object> objectEntry : objectMap.entrySet()) {
                            if (!ObjectUtils.isEmpty(objectEntry)) {
                                String key = objectEntry.getKey();
                                Object value = objectEntry.getValue();
                                Integer cellIndex = getReportExcelColumn(report, key);
                                if (!ObjectUtils.isEmpty(cellIndex)) {
//                                    cell = row.getCell(cellIndex);//列
                                    cell = row.createCell(cellIndex);//列
                                    if (cell != null) {
                                        if (!ObjectUtils.isEmpty(value)) {
//                                            cell.setCellStyle(PoiUtils.getStyle(workbook));
                                            PoiUtils.setStyle(cellStyle);//设置边框
                                            cell.setCellStyle(cellStyle);
                                            cell.setCellValue(value.toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
//            inputStream.close();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            outputStream.flush();
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {

            }
        }
    }

    private Integer getReportExcelColumn(Map<String, Integer> report, String key) throws Exception {
        Integer temp = null;
        try {
            for (Map.Entry<String, Integer> entry : report.entrySet()) {
                Map.Entry<String, Integer> integerEntry = entry;
                String k = integerEntry.getKey();
                if (k.equals(key)) {//匹配成功!
                    temp = integerEntry.getValue();
                }
            }
        } catch (Exception e) {
            logger.error("exception:" + e.getMessage());
        }
        return temp;
    }

    /**
     * 获取第一行的key
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    private Map<String, Integer> getReportExcelOneKey(String filePath) throws Exception {
        //列数以2003版为基准 最大是16384
//        final Integer MAX_COLUMN = 16384;
        final Integer MAX_COLUMN = 84;
        Map<String, Integer> integerMap = new HashMap<>();
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        if (file.isFile()) {
//            logger.info("测试!  "  +"------------");
        }
        if (file.exists()) {
//            logger.info("测试!  "  +"..............");
        }
//        Workbook workbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
//        Workbook workbook = null;
        String suffix = filePath.substring(filePath.length() - 4, filePath.length());
        try {
            if (Objects.equal("xlsx", suffix)) {//07版
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                //暂时只取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                //因为是第一行作为key所以只取第一行
                Row row = sheet.getRow(0);
                Cell cell = null;
                if (row != null) {
                    for (int i = 0; i < MAX_COLUMN; i++) {
                        cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValue = cell.getStringCellValue();
                            if (!org.springframework.util.StringUtils.isEmpty(cellValue)) {
                                //取key
                                integerMap.put(cellValue, i);
                            }
                        }
                    }
                }
            } else {
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                //暂时只取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                //因为是第一行作为key所以只取第一行
                Row row = sheet.getRow(0);
                Cell cell = null;
                if (row != null) {
                    for (int i = 0; i < MAX_COLUMN; i++) {
                        cell = row.getCell(i);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellValue = cell.getStringCellValue();
                            if (!org.springframework.util.StringUtils.isEmpty(cellValue)) {
                                //取key
                                integerMap.put(cellValue, i);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {
                logger.error("----------->" + e.getMessage());
                throw e;
            } catch (Exception e1) {

            }
        }

        inputStream.close();
        return integerMap;
    }
}

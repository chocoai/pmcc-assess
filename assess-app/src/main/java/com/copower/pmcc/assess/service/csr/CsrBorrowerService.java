package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.CsrBorrowerEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerEnteringDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.base.BaseReportTemplateFilesDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerEnteringVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerVo;
import com.copower.pmcc.assess.service.BaseReportService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private BaseAttachmentService attachmentService;
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
    private BaseAttachmentDao baseAttachmentDao;
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
            List<String> filePaths = changeBaseAttachment(borrowerIds, request);
            String zipName = UUID.randomUUID().toString().substring(0, 5) + ".zip";
            responseEntity = appleDownloadBaseAttachment(filePaths, zipName, request);
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
    private ResponseEntity<byte[]> appleDownloadBaseAttachment(List<String> filePaths, String zipName, HttpServletRequest request) throws Exception {
        ResponseEntity<byte[]> responseEntity = null;
        String zipPathAndName = request.getSession().getServletContext().getRealPath("/") + CsrBorrowerEnum.CSR_BORROWER_ENUM.getFilePath() + commonService.thisUserAccount() + zipName;
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
    private List<String> changeBaseAttachment(String borrowerIds, HttpServletRequest request) throws Exception {
        String report = AssessFieldNameConstant.CSR_BORROWER_REPORT;
        List<String> filePaths = new ArrayList<>();
        String[] ids = borrowerIds.split(",");
        List<BaseAttachment> baseAttachments = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            if (!org.springframework.util.StringUtils.isEmpty(ids[i])) {
                List<BaseAttachment> baseAttachmentList = attachmentService.getByField_tableId(Integer.parseInt(ids[i]), report);
                baseAttachments.addAll(baseAttachmentList);
            }
        }
        for (BaseAttachment baseAttachment : baseAttachments) {
            String localDirPath = request.getSession().getServletContext().getRealPath("/") + CsrBorrowerEnum.CSR_BORROWER_ENUM.getFilePath();
            String localFileName = UUID.randomUUID().toString().substring(0, 7) + CsrBorrowerEnum.ZIP_NAME.getFilePath() + "." + baseAttachment.getFileExtension();
            //临时下载
            readImportData(localDirPath, localFileName, baseAttachment);
            //收集 临时目录地址
            filePaths.add(localDirPath + localFileName);
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
    private void readImportData(String localDirPath, String localFileName, BaseAttachment baseAttachment) {
        List<BaseAttachment> attachmentList = baseAttachmentService.getByField_tableId(baseAttachment.getTableId(), AssessFieldNameConstant.CSR_BORROWER_REPORT);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            BaseAttachment sysAttachment = attachmentList.get(0);
            String fullPath = localDirPath + File.separator + localFileName;
            if (!com.copower.pmcc.erp.common.utils.FileUtils.checkFileExists(new File(fullPath))) {
                try {
                    ftpUtilsExtense.downloadFileToLocal(sysAttachment.getFtpFilesName(), sysAttachment.getFilePath(), localFileName, localDirPath);
                } catch (Exception e) {
                    //拷贝失败
                    logger.error(e.getMessage(), e);
                }
            }
        }
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
        BaseAttachment queryParam = new BaseAttachment();
        queryParam.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrower.class));
        queryParam.setFieldsName(AssessFieldNameConstant.CSR_BORROWER_REPORT);
        List<BaseAttachment> attachmentList = baseAttachmentService.getAttachmentList(tableIds, queryParam);
        return LangUtils.transform(csrBorrowerList, p -> {
            CsrBorrowerVo csrBorrowerVo = new CsrBorrowerVo();
            BeanUtils.copyProperties(p, csrBorrowerVo);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                StringBuilder html = new StringBuilder();
                for (BaseAttachment baseAttachment : attachmentList) {
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
                BaseAttachment baseAttachment = new BaseAttachment();
                baseAttachment.setTableName("tb_csr_borrower_entering");
                baseAttachment.setTableId(0);
                baseAttachment.setCreater(processControllerComponent.getThisUser());

                BaseAttachment baseAttachmentNew = new BaseAttachment();
                baseAttachmentNew.setTableId(csrBorrowerEntering.getId());

                baseAttachmentDao.updateAttachementByExample(baseAttachment, baseAttachmentNew);
            } else {
                csrBorrowerEnteringDao.update(csrBorrowerEntering);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrBorrowerEntering;
    }

    public CsrBorrowerEnteringVo loadLoanBorrower(Integer borrowerId, Integer detailsId) throws BusinessException {

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

    public ResponseEntity<byte[]>  exportFormBorrowers(HttpServletRequest request, HttpServletResponse response,Integer csrProjectInfoID){
        ResponseEntity<byte[]> responseEntity = null;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        CsrProjectInfo csrProjectInfo = csrProjectInfoService.getById(csrProjectInfoID);
        BaseReportTemplateFilesDto baseReportTemplateFilesDto = null;
        baseReportTemplateFilesDto = baseReportService.getReportTemplateFile(csrProjectInfo.getEntrustmentUnitId(),baseDataDic.getId(),csrProjectInfo.getCustomerType(),csrProjectInfo.getProjectTypeId(),csrProjectInfo.getProjectCategoryId());
        responseEntity = toExportFormBorrowers(request,response,baseReportTemplateFilesDto,csrProjectInfoID);
        return  responseEntity;
    }

    private ResponseEntity<byte[]> toExportFormBorrowers(HttpServletRequest request, HttpServletResponse response,BaseReportTemplateFilesDto templateFilesDto,Integer csrProjectInfoID){
        ResponseEntity<byte[]> responseEntity = null;
        CsrBorrowerMortgage csrBorrowerMortgage = null;
        List<CsrBorrowerMortgage> csrBorrowerMortgages = csrBorrowerMortgageService.getCsrProjectMortgages(csrProjectInfoID);
        return responseEntity;
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
            Workbook workbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
            //暂时只取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            for (int i = dataIndex; i < size; i++) {
                row = sheet.getRow(i);//行
                Map<String, Object> objectMap = mapList.get(i);
                for (Map.Entry<String, Object> objectEntry : objectMap.entrySet()) {
                    String key = objectEntry.getKey();
                    Object value = objectEntry.getValue();
                    Integer cellIndex = getReportExcelColumn(report, key);
                    cell = row.getCell(cellIndex);//列
                    if (cell != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            workbook.write(outputStream);
            outputStream.flush();
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
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            Map.Entry<String, Integer> integerEntry = entry;
            String k = integerEntry.getKey();
            if (k.equals(key)) {//匹配成功!
                temp = integerEntry.getValue();
            }
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
        final Integer MAX_COLUMN = 16384;
        Map<String, Integer> integerMap = new HashMap<>();
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
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
        return integerMap;
    }
}

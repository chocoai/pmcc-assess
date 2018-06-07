package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.CsrBorrowerEnum;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerEnteringDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerEntering;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerEnteringVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        if (CollectionUtils.isEmpty(csrBorrowerList)) return null;
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

}
